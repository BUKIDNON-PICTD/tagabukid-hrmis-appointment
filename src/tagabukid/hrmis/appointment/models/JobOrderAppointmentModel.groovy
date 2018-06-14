package tagabukid.hrmis.appointment.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class JobOrderAppointmentModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Service('SequenceService')
    def seqSvc
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service('AppointmentJobOrderService')
    def joSvc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    boolean isAllowApprove() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }
    
    
    def selectedAppointmentMemberItem;
     
    public void afterCreate(){
        
        entity.appointmentMemberItems = [];
        entity.signatoryGroupItems = [];
        appointmentMemberItemHandler.reload();
        signatoryItemHandler.reload();
        // entity.farmerid = OsirisContext.env.ORGID + "-FARM" + seqSvc.getNextFormattedSeries('farmer');
            
    }
    
    public void afterEdit(){
        entity.recordlog_dateupdated = dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
    }
    
    public void afterOpen(){
               
        entity.natureofappointment = persistenceSvc.read( [_schemaname:'master_tblappointmententrycode', objid:entity.natureofappointmentid] );
        entity.org = persistenceSvc.read( [_schemaname:'master_tblorganizationunit', orgunitid:entity.org.objid] );
        entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroupid] );
        
        entity.appointmentMemberItems.each{
            it.employee = tgbkdSvc.getEntityByObjid([entityid:it.entityid]);
            it.position = persistenceSvc.read( [_schemaname:'master_tbljobposition', objid:it.positionid] );
            it.fund = persistenceSvc.read( [_schemaname:'master_tblfinfund', objid:it.fundid] );
            it.account = persistenceSvc.read( [_schemaname:'master_tblfinaccounttitle', objid:it.accountid] );
        }
    }

    public void beforeSave(o){
        if(!entity.appointmentMemberItems)throw new Exception("Appointment Group items must not be empty");
            
        entity.recordlog_datecreated = dtSvc.getServerDate();
        entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_createdbyuserid = OsirisContext.env.USERID;
        entity.state = "DRAFT";
        entity.natureofappointmentid = entity.natureofappointment.objid;
        entity.org.objid = entity.org.orgunitid;
        entity.signatorygroupid = entity.signatorygroup.objid;
            
        entity.appointmentMemberItems.each{
              
            it.entityid = it.employee.objid
            it.positionid = it.position.objid
            it.fundid = it.fund.objid
            it.accountid = it.account.objid
                
        }
            
            
            
        //entity.code = "sig" + seqSvc.getNextFormattedSeries('signatory')
     
    }
 
    def appointmentMemberItemHandler = [
        fetchList: { o->
            def p = [_schemaname: 'hrmis_appointmentjoborderitems'];
            p.findBy = [ 'parentid': entity.objid];
            p.select = "objid,parentid,entityid,positionid,fundid,accountid,dailysalary,lastemploymentfrom,lastemploymentuntil";
            if(!entity.appointmentMemberItems){
                entity.appointmentMemberItems = queryService.getList( p );
            }
            return entity.appointmentMemberItems;
        },
        createItem : {
            return[
                objid : 'JOI' + new java.rmi.server.UID(),
                newitem : false,
            ]
        },
        onAddItem : {
            entity.appointmentMemberItems << it;
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')) {
                //service.deleteFarmerItems(it)
                entity.appointmentMemberItems.remove(it)
                appointmentMemberItemHandler.reload();
                return true;
            }
            return false;
        }
            
        
    ] as EditorListModel;
    
        def signatoryItemHandler = [
            fetchList: { o->
                def p = [_schemaname: 'hrmis_appointment_signatorygroupingitems'];
                p.findBy = [ 'parentid': entity.signatorygroup?.objid];
                p.select = "objid,parentid,signatoryname,signatorytitle,org,level";
                println entity.signatorygroup?.objid
                if(!entity.signatoryGroupItems){
                    entity.signatoryGroupItems = queryService.getList( p );
                    println entity.signatoryGroupItems;
                }
                
                return entity.signatoryGroupItems;
            }            
            
        ] as BasicListModel;
    
//    def signatoryItemHandler = [
//        fetchList: { o->
//            
//            
//            return entity?.signatoryGroupItems;
//        }            
//        
//    ] as BasicListModel;
    
    
    def groupHandler = [
        fetchList: { o->
            return joSvc.getGroupName(o).appointmentgroupname;
        }
    ] as SuggestModel;
    
    //========== Lookup Signatory Group ========= 
    def getLookupSignatory(){
        return Inv.lookupOpener('signatorygroup:lookup')
    }
    
//    //========== Lookup Signatory Group ========= 
//    def getLookupSignatory1(){
//        return Inv.lookupOpener('signatorygroup:lookup',[
//                onselect :{ def p = [_schemaname: 'hrmis_appointment_signatorygroupingitems'];
//                    p.findBy = [ 'parentid': entity.signatorygroup?.objid];
//                    p.select = "objid,parentid,signatoryname,signatorytitle,org,level";
//                    println entity.signatorygroup?.objid
//                    if(!entity.signatoryGroupItems){
//                        entity.signatoryGroupItems = queryService.getList( p );
//                        println entity.signatoryGroupItems;
//                    }
//                }
//               
//            ])
//             signatoryItemHandler.reload();
//    }
    
    
//    def getLookupSignatory(){
//        return Inv.lookupOpener('signatorygroup:lookup',[
//                onselect :{
//                    entity.signatorygroupname = it.signatorygroupname;
//                    entity.signatoryGroupItems.signatoryname = it.signatoryGroupItems.signatoryname;
//                    //entity.paidby = it.name;
//                    //entity.paidbyaddress = it.address.text;
//                    binding.refresh('entity.signatorygroupname.*')
//                },
//                
//                onempty: {
//                    //
//                }
//        ])
//    }

    void approve() { 
        if ( MsgBox.confirm('You are about to approve this information. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'hrmis_appointmentjoborder', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }
        

}

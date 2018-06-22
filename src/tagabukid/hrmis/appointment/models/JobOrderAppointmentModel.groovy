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
    
    @Caller
    def renewcaller
    
    @Service('SequenceService')
    def seqSvc
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service('AppointmentJobOrderService')
    def joSvc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    def tag
    
    boolean isAllowApprove() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }
    
        boolean isallowPreviewAppointment() {
        return ( mode=='read' && entity.state=='APPROVED' ); 
    }
    
    boolean isAllowRenew() {
        def currdate = new java.sql.Date(dtSvc.getServerDate().time);
        def datediff = entity.effectiveuntil.time - currdate.time
        def range = 0..60
        return (mode=='read' && entity.state=='APPROVED' && range.contains((datediff / (60*60*24*1000)) as int)); 
    }

    boolean isDeleteAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }

    boolean isEditAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }

    boolean isViewReportAllowed(){
        return false
    }

    boolean isPrintReportAllowed(){
        return false
    }
    
    
    def selectedAppointmentMemberItem;
     
    public void afterCreate(){
        
        tag = invoker?.properties?.tag;
        if(tag=='renew'){
            entity = joSvc.initRenew(renewcaller.entity)
        }else{
        entity.appointmentMemberItems = [];
        entity.signatoryGroupItems = [];
        appointmentMemberItemHandler.reload();
        signatoryItemHandler.reload();
        // entity.farmerid = OsirisContext.env.ORGID + "-FARM" + seqSvc.getNextFormattedSeries('farmer');
        }    
    }
    
    public void afterEdit(){
        entity.recordlog_dateupdated = dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
        appointmentMemberItemHandler.reload();
    }
    
    public void afterOpen(){
        //println entity.signatorygroup       
        //entity.natureofappointment = persistenceSvc.read( [_schemaname:'master_tblappointmententrycode', objid:entity.natureofappointmentid] );
        //entity.org = persistenceSvc.read( [_schemaname:'master_tblorganizationunit', orgunitid:entity.org.objid] );
        //entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
        
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
        entity.status = "JO";
        //entity.natureofappointmentid = entity.natureofappointment.objid;
        //entity.org.objid = entity.org.orgunitid;
//        entity.signatorygroupid = entity.signatorygroup.objid;
            
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
         
        fetchList: { 
            if(entity.signatorygroup)
            entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
            //println entity.signatorygroup
            return entity.signatorygroup?.signatoryGroupItems 
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.signatorygroup.signatorygroupitems.remove(it)
                signatoryItemHandler?.load();
                return true;
            }
            return false;
        },
        validate:{li->
            def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
    

    def groupHandler = [
        fetchList: { o->
            return joSvc.getGroupName(o).appointmentgroupname;
        }
    ] as SuggestModel;


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
    
    def renew(){
        return InvokerUtil.lookupOpener('hrmis_appointmentjoborder:renew:create')
    }
        

}

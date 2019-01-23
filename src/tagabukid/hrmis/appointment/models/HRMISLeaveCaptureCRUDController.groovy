
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat

class HRMISLeaveCaptureCRUDController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def caller
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("TagabukidHRMISLeaveCaptureService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    def tag
    def selectedAppointmentItem

    
//    @PropertyChangeListener
//    def listener = [
//        'entity.currentsalarystep' : { 
//               calculatewage() 
//        }
//        
//    ]
    def time = [
        '1:00:00',
        '2:00:00',
        '3:00:00',
        '4:00:00',
        '5:00:00',
        '6:00:00',
        '7:00:00',
        '8:00:00',
        '9:00:00',
        '10:00:00',
        '11:00:00',
        '12:00:00',
        '13:00:00',
        '14:00:00',
        '15:00:00',
        '16:00:00',
        '17:00:00',
        '18:00:00',
        '19:00:00',
        '20:00:00',
        '21:00:00',
        '22:00:00',
        '23:00:00',
        '00:00:00'
    ]
    boolean isAllowApprove() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT|CUTOFF') ); 
    }
    
    boolean isAllowPreviewAppointment() {
        return ( mode=='read'); 
    }
    
//    boolean isAllowRenew() {
//        def currdate = new java.sql.Date(dtSvc.getServerDate().time);
//        def datediff = entity.effectiveuntil.time - currdate.time
//        def range = 0..60
////        return (mode=='read' && entity.state=='APPROVED' && range.contains((datediff / (60*60*24*1000)) as int)); 
//        return (mode=='read' && entity.state=='APPROVED' && ((datediff / (60*60*24*1000)) as int) < 0); 
//    }
    
//    boolean isAllowCutoff() {
//        return (mode=='read' && entity.state=='APPROVED'); 
//    }
    

    boolean isEditAllowed() {
        return ( mode=='read' && entity.state.matches('DRAFT|CUTOFF')); 
    }

    boolean isViewReportAllowed(){
        return false
    }

    boolean isPrintReportAllowed(){
        return false
    }

//    public void beforeSave(o){
//        entity.leavedetails.each{
//            def pds = persistenceSvc.read([_schemaname:'hrmis_leave', objid:it.pds.objid])
//            it.personnel = pds.person
//        }
//    }
//
    public void afterCreate(){
        entity = svc.initCreate();
        entity.pds = caller.entity
    }
    public void afterSave(){
        caller?.reloadEntity();
    }

    public void afterOpen(){
//        println entity
//        entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
//        entity.appointmentitems.each{
//            def pds = persistenceSvc.read([_schemaname:'hrmis_pds', objid:it.pds.objid])
//            it.personnel = pds
//        }
    }
//    def suggestGroupName = [
//        fetchList: { o->
//            return svc.getList(o).appointmentgroupname;
//        }
////        ,
////        onselect:{ o->
////            entity.appointmentitems = svc.getAppointmentItemsByGroup(o)
////
////        }
//    ] as SuggestModel;

    def leaveDetailHandler = [
        fetchList: { 
            return entity?.leavedetails 
        },
        createItem : {
            return[
                objid : 'LD' + new java.rmi.server.UID(),
            ]
        },
//        onRemoveItem : {
//            if (entity.state != 'DRAFT'){
//                MsgBox.alert("Delete is not allowed.")
//                return false
//            }else if (MsgBox.confirm('Delete item?')){                
//                entity.leavedetails.remove(it)
//                persistenceSvc.removeEntity([_schemaname:'hrmis_leaveitems',objid:it.objid])
//                leaveDetailHandler?.load();
//                return true;
//            }
//            return false;
//        },
//        onColumnUpdate: { o,col-> 
//            if(col == 'plantilla'){
//                o.salaryscheduleitem  = svc.getDailyWageByTranch(entity.currentsalarystep,o.plantilla);
//                o.monthlywage = o.salaryscheduleitem.amount
//                o.dailywage = o.salaryscheduleitem.amount / 22
//            }
//            
//            if(col == 'cutoffdate'){
//                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date date = sdf1.parse(o.cutoffdate);
//                java.sql.Date cutoffdate = new java.sql.Date(date.getTime()); 
//                if (entity.effectivefrom >= cutoffdate || cutoffdate >= entity.effectiveuntil){
//                    throw new Exception("Cut-off date must be between Effective From and Effective Until.");
//                }
//            }
//            
//        },
//        onCommitItem:{ x ->
////            println x
//            if (x.cutoffdate && !x.cutoffreason.objid){
//                throw new Exception("Cut-off Reason is required.");
//            }
//        },
        onAddItem : {
//            it.plantilla.Id = it.plantilla.Id.toString()
//            it.monthlywage = it.dailywage * 22
            entity.leavedetails.add(it);
        }
//        ,
//        validate:{li->
//            def item=li.item;
//            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
//        }
    ] as EditorListModel

    def signatoryItemHandler = [
        fetchList: { 
            if(entity.signatorygroup?.objid)
            entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
            return entity.signatorygroup?.signatoryGroupItems 
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.signatorygroup.signatoryGroupItems.remove(it)
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

//    void approve() { 
//        if ( MsgBox.confirm('You are about to approve this document. Proceed?')) { 
//            getPersistenceService().update([ 
//                    _schemaname: 'hrmis_appointmentcasual', 
//                    objid : entity.objid, 
//                    state : 'APPROVED' 
//                ]); 
//            loadData();
//        }
//    }
    
//     void cutoff() { 
//        if ( MsgBox.confirm('You are about to edit this document. Proceed?')) { 
//            getPersistenceService().update([ 
//                    _schemaname: 'hrmis_appointmentcasual', 
//                    objid : entity.objid, 
//                    state : 'CUTOFF' 
//                ]); 
//            loadData();
//        }
//    }
//    def getTranchLookupHandler(){
//        return Inv.lookupOpener('lookup:tagabukid_hrmis_tranche',[
//                onselect :{tranche ->
////                    entity.currentsalarystep.putAll(tranche)  
//                   
//                }
//            ]);
//    }
//    void calculatewage(){
//        entity.appointmentitems.each{
//            it.salaryscheduleitem  = svc.getDailyWageByTranch(entity.currentsalarystep,it.plantilla);
//            it.monthlywage = it.salaryscheduleitem.amount
//            it.dailywage = it.salaryscheduleitem.amount / 22
//        }
//        appointmentitemListHandler.reload();
//    }
    // def getPersonnelLookupHandler(){
    //     return Inv.lookupOpener('lookup:individualwide',[
    //             onselect :{personnel ->
    //                 validatecurrentappointment(personnel)
    //             }
    //         ]);
    // }

    // void validatecurrentappointment(o){
    //     if(!entity.effectivefrom){
    //         throw new Exception("Effective From is required.");
    //     }
    //     if(svc.findPersonnelHasActiveAppointment(o,entity.effectivefrom)){
    //         throw new Exception("Effective From is required.");
    //     }
    // }
   
//    def renew(){
//        if (MsgBox.confirm('you are about to renew this transaction?')){
//            return InvokerUtil.lookupOpener('hrmis_appointmentcasual:renew:create')
//        }
//        
//    }
}  
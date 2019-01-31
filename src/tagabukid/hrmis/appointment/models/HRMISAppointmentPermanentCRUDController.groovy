
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat

class HRMISAppointmentPermanentCRUDController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("TagabukidHRMISAppointmentPermanentService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc

    def tag
    
    @PropertyChangeListener
    def listener = [
        'entity.plantilla' : { 
               entity.paygradeandstepincrement = entity.plantilla.paygradeandstepincrement
               calculatewage() 
               binding.refresh()
        }
//        ,
//        'entity.paygradeandstepincrement' : { 
//               calculatewage() 
//        }
        
    ]
    boolean isAllowApprove() {
        return ( mode=='read' && entity.state.toString().matches('PENDING|CUTOFF') ); 
    }
    
    boolean isallowPreviewAssumptionOfDuty() {
        return ( mode=='read'); 
    }
    
//    boolean isAllowRenew() {
//        def currdate = new java.sql.Date(dtSvc.getServerDate().time);
//        def datediff = entity.effectiveuntil.time - currdate.time
//        def range = 0..60
////        return (mode=='read' && entity.state=='APPROVED' && range.contains((datediff / (60*60*24*1000)) as int)); 
//        return (mode=='read' && entity.state=='APPROVED' && ((datediff / (60*60*24*1000)) as int) < 0); 
//    }
    
    boolean isAllowCutoff() {
        return (mode=='read' && entity.state=='APPROVED'); 
    }
    
    boolean isAllowCancel() {
        return (mode=='read' && entity.state=='APPROVED'); 
    }
    
    boolean isAllowDisapprove() {
        return (mode=='read' && entity.state=='APPROVED'); 
    }
    

    boolean isEditAllowed() {
        return ( mode=='read' && entity.state.matches('PENDING|CUTOFF')); 
    }

    boolean isViewReportAllowed(){
        return false
    }

    boolean isPrintReportAllowed(){
        return false
    }

    public void beforeSave(o){
//        println entity
        if(o == 'update'){
            entity.recordlog.dateupdated = dtSvc.getServerDate();
            entity.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
        }
        entity.pds._schemaname = 'hrmis_pds'
        persistenceSvc.update(entity.pds)
    }
    public void afterSave(){
        entity.org = entity.plantilla.org
    }
    public void afterCreate(){
        entity = svc.initCreate();
    }

//    public void afterEdit(){
//        println entity
//    }
    
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


    def signatoryItemHandler = [
        fetchList: { 
            if(entity?.signatorygroup?.objid){
                entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
                return entity.signatorygroup?.signatoryGroupItems 
            }
            return []
           
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

    void approve() { 
         if ( MsgBox.confirm('You are about to approve this document. Proceed?')) { 
            getPersistenceService().update([ 
                    _schemaname: 'hrmis_appointmentpermanent', 
                    objid : entity.objid, 
                    state : 'APPROVED' 
                ]); 
            loadData();
        }
    }
    
    void disapprove() { 
       if ( MsgBox.confirm('You are about to approve this document. Proceed?')) { 
            def reason = MsgBox.prompt("Please enter the reason for disapproval.")
            if (!reason?.trim()){
                 MsgBox.alert('Disapproval reason is required'); 
            }else{
                entity.datedisapproved = dtSvc.getServerDate();
                entity.disapprovalreason = reason
                entity.recordlog.dateupdated = dtSvc.getServerDate();
                entity.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
                entity.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
                entity._schemaname = 'hrmis_appointmentpermanent'
                entity.state = "DISAPPROVED"
		persistenceSvc.update(entity)
                loadData();
            }
            
        }
    }
    
    void cutoff() { 
       if ( MsgBox.confirm('You are about to cutoff this document. Proceed?')) { 
            def reason = MsgBox.prompt("Please enter the reason for cutoff.")
            if (!reason?.trim()){
                 MsgBox.alert('Cutoff reason is required'); 
            }else{
                entity.cutoffdate = dtSvc.getServerDate();
                entity.cutoffreason = reason
                entity.recordlog.dateupdated = dtSvc.getServerDate();
                entity.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
                entity.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
                entity._schemaname = 'hrmis_appointmentpermanent'
                entity.state = "CUTOFF"
		persistenceSvc.update(entity)
                loadData();
            }
        }
    }
    
//     boolean verifyReprint() {
//        return (MsgBox.prompt("Please enter security code") == "etracs"); 
//    }
//    def getTranchLookupHandler(){
//        return Inv.lookupOpener('lookup:tagabukid_hrmis_tranche',[
//                onselect :{tranche ->
////                    entity.currentsalarystep.putAll(tranche)  
//                   
//                }
//            ]);
//    }
    void calculatewage(){
        def salaryscheduleitem  = svc.getDailyWageByTranch(entity.plantilla);
        entity.compensation = salaryscheduleitem.amount
    }
    // def getPersonnelLookupHandler(){
    //     return Inv.lookupOpener('lookup:individualwide',[
    //             onselect :{personnel ->
    //                 validatecurrentappointment(personnel)
    //             }
    //         ]);
    // }

     void validatecurrentappointment(o){
         if(!entity.effectivefrom){
             throw new Exception("Effective From is required.");
         }
         if(svc.findPersonnelHasActiveAppointment(o,entity.effectivefrom)){
             throw new Exception("Effective From is required.");
         }
     }
   
//    def renew(){
//        return InvokerUtil.lookupOpener('hrmis_appointmentcasual:renew:create')
//    }
}  
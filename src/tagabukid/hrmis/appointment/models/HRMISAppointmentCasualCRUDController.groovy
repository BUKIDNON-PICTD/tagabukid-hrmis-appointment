
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat

class HRMISAppointmentCasualCRUDController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def renewcaller
    
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("TagabukidHRMISAppointmentCasualService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    def tag
    def selectedAppointmentItem
    
    boolean isAllowApprove() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }
    
    boolean isAllowEditGrid() {
        return ( entity.currentsalarystep.objid != null ); 
    }
    
    boolean isAllowPreviewAppointment() {
        return ( mode=='read'); 
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

    public void beforeSave(){

    }

    public void afterCreate(){
        tag = invoker?.properties?.tag;
        if(tag=='renew'){
            entity.putAll(svc.initRenew(renewcaller.entity))
            println entity.currentsalarystep
            println mode
        }else{
            entity = svc.initCreate();
        }
      
    }

    public void afterOpen(){
//        println entity
//        entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
        entity.appointmentitems.each{
            //println it
            it.personnel = tgbkdSvc.getEntityByObjid([entityid:it.personnel.objid]);
            it.plantilla = tgbkdSvc.findPlantillaById([plantillaid:it.plantilla.objid]);
            //postgrehack
//            it.plantilla.Id = it.plantilla.objid

        }
    }
    def suggestGroupName = [
        fetchList: { o->
            return svc.getList(o).appointmentgroupname;
        }
//        ,
//        onselect:{ o->
//            entity.appointmentitems = svc.getAppointmentItemsByGroup(o)
//
//        }
    ] as SuggestModel;

    def appointmentitemListHandler = [
        fetchList: { entity.appointmentitems },
        createItem : {
            return[
                objid : 'ACI' + new java.rmi.server.UID() +"-"+ dtSvc.getServerDate().year,
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.appointmentitems.remove(it)
                appointmentitemListHandler?.load();
                return true;
            }
            return false;
        },
        onColumnUpdate: { o,col-> 
            if(col == 'plantilla'){
                o.salaryscheduleitem  = svc.getDailyWageByTranch(entity.currentsalarystep,o.plantilla);
                o.monthlywage = o.salaryscheduleitem.amount
                o.dailywage = o.salaryscheduleitem.amount / 22
            }
            
        },
        onAddItem : {
//            it.plantilla.Id = it.plantilla.Id.toString()
//            it.monthlywage = it.dailywage * 22
            entity.appointmentitems.add(it);
        },
        validate:{li->
            def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
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

    void approve() { 
        if ( MsgBox.confirm('You are about to approve this document. Proceed?')) { 
            getPersistenceService().update([ 
                    _schemaname: 'hrmis_appointmentcasual', 
                    objid : entity.objid, 
                    state : 'APPROVED' 
                ]); 
            loadData();
        }
    }
    def getTranchLookupHandler(){
        return Inv.lookupOpener('lookup:tagabukid_hrmis_tranche',[
                onselect :{tranche ->
                    entity.currentsalarystep.putAll(tranche)  
                    entity.appointmentitems.each{
                        it.salaryscheduleitem  = svc.getDailyWageByTranch(tranche,it.plantilla);
                        it.monthlywage = it.salaryscheduleitem.amount
                        it.dailywage = it.salaryscheduleitem.amount / 22
                    }
                    appointmentitemListHandler.reload();
                }
            ]);
    }

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
   
    def renew(){
        return InvokerUtil.lookupOpener('hrmis_appointmentcasual:renew:create')
    }
}  

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.annotations.Env
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat
import java.text.SimpleDateFormat

class HRMISAppointmentCasualCRUDController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def renewcaller
    
    @Env
    def env
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("TagabukidHRMISAppointmentCasualService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    def tag
    
    boolean isAllowApprove() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
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

    public void afterCreate(){
        tag = invoker?.properties?.tag;
        if(tag=='renew'){
            entity = svc.initRenew(renewcaller.entity)
        }else{
            entity = svc.initCreate();
        }
      
    }

    public void afterOpen(){
        println entity
//        entity.signatorygroup = persistenceSvc.read( [_schemaname:'hrmis_appointment_signatorygrouping', objid:entity.signatorygroup.objid] );
        entity.appointmentitems.each{
            //println it
            it.personnel = tgbkdSvc.getEntityByObjid([entityid:it.personnel.objid]);
            it.plantilla = tgbkdSvc.getPlantillaById([plantillaid:it.plantilla.Id]);
            //postgrehack
            it.plantilla.Id = it.plantilla.Id.toString()

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
//        onColumnUpdate: { o,col-> 
//            if(col == 'dailywage'){
//                o.monthlywage = o.dailywage * 22;
//                binding.refresh();  
//            }
//            
//        },
        onAddItem : {
            it.plantilla.Id = it.plantilla.Id.toString()
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
            return entity.signatorygroup?.signatorygroupitems 
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
    
    def renew(){
        return InvokerUtil.lookupOpener('hrmis_appointmentcasual:renew:create')
    }
}  
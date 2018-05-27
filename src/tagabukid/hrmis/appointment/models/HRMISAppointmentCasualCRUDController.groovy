
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.annotations.Env
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class HRMISAppointmentCasualCRUDController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Env
    def env
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("HRMISAppointmentCasualService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    public void afterCreate(){
        entity = svc.initCreate();
    }
    
    public void afterOpen(){
        entity.appointmentitems.each{
            println it
            it.personnel = tgbkdSvc.getEntityByObjid([entityid:it.personnel.objid]);
            it.plantilla = tgbkdSvc.getPlantillaById([plantillaid:it.plantilla.Id]);
            //postgrehack
            it.plantilla.Id = it.plantilla.Id.toString()

        }
    }
    def suggestGroupName = [
        fetchList: { o->
            return svc.getList(o).appointmentgroupname;
        },
        onselect:{ o->
            entity.appointmentitems = svc.getAppointmentItemsByGroup(o)
            
        }
    ] as SuggestModel;
   
    def appointmentitemListHandler = [
        fetchList: { entity.appointmentitems },
        createItem : {
            return[
                objid : 'ACI' + new java.rmi.server.UID(),
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
        onAddItem : {
            it.plantilla.Id = it.plantilla.Id.toString()
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

 
}  
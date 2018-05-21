
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
    
    public void afterCreate(){
        entity = svc.initCreate();
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

 
}  
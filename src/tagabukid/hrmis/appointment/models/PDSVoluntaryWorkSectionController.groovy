import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.seti2.models.*;
import com.rameses.annotations.Env
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat
        
        
class  PDSVoluntaryWorkSectionController extends CrudFormModel {
    @Binding
    def binding;
    
    @Caller
    def caller
    
    @Service("DateService")
    def dtSvc
    
    String title = "VOLUNTARY WORK OR INVOLVEMENT IN CIVIC / NON-GOVERNMENT / PEOPLE / VOLUNTARY ORGANIZATION/S";            
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('FOR REVIEW') ); 
    }
        boolean isDeleteAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }

    boolean isEditAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT|FOR REVIEW') ); 
    }

    void init(){
        
    }
    //     def appointmentitemListHandler = [
    //     fetchList: { entity.appointmentitems },
    //     createItem : {
    //         return[
    //             objid : 'ACI' + new java.rmi.server.UID() +"-"+ dtSvc.getServerDate().year,
    //         ]
    //     },
    //     onRemoveItem : {
    //         if (MsgBox.confirm('Delete item?')){                
    //             entity.appointmentitems.remove(it)
    //             appointmentitemListHandler?.load();
    //             return true;
    //         }
    //         return false;
    //     },
    //     onAddItem : {
    //         it.plantilla.Id = it.plantilla.Id.toString()
    //         entity.appointmentitems.add(it);
    //     },
    //     validate:{li->
    //         def item=li.item;
    //     }
    // ] as EditorListModel

    def voluntaryworkListHandler = [
        createItem : {
            return [
                objid       : "VWK" + new java.rmi.server.UID(),
                objid : entity.voluntaryworkid
            ]
        },
        
        fetchList : {
            return entity.voluntarywork
        },
        
        onAddItem : {item ->
            if(!entity.voluntarywork)
                entity.voluntarywork = [];
            entity.voluntarywork << item;
        },
            
        onRemoveItem : {item ->
            if(MsgBox.confirm("Remove selected item?")){
                entity.voluntarywork.remove(item);
                if(!entity._voluntarywork)
                    entity._voluntarywork = [];
                entity.voluntarywork << item;
                return true;
            }
            return false;
        }
    ] as EditorListModel;

    
}
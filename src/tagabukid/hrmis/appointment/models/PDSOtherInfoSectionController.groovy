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
        
class  PDSOtherInfoSectionController extends CrudFormModel{
    @Binding
    def binding;
    
    @Caller
    def caller
    
    @Service("DateService")
    def dtSvc
    String title = "OTHER INFORMATION";
    
            
    void init(){
        
    }

    // def associationorganizationListHandler = [
    //     fetchList: { entity.associationorganizationitems },
    //     createItem : {
    //         return[
    //             objid : 'POA' + new java.rmi.server.UID() +"-"+ dtSvc.getServerDate().year,
    //         ]
    //     },
    //     onRemoveItem : {
    //         if (MsgBox.confirm('Delete item?')){                
    //             entity.associationorganizationitems.remove(it)
    //             associationorganizationListHandler?.load();
    //             return true;
    //         }
    //         return false;
    //     },
    //     onAddItem : {
    //         it.associationorganization.Id = it.associationorganization.Id.toString()
    //         entity.associationorganizationitems.add(it);
    //     },
    //     validate:{li->
    //         def item=li.item;
    //     }
    // ] as EditorListModel

    // public void afterOpen(){
    //     println entity
    //     entity.associationorganizationitems.each{
    //         //println it
    //         it.associationorganization = tgbkdSvc.getEntityByObjid([entityid:it.personnel.objid]);
    //         //postgrehack
    //         it.associationorganization.Id = it.associationorganization.Id.toString()

    //     }
    // }

}
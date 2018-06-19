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
    
    @Env
    def env
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "VOLUNTARY WORK OR INVOLVEMENT IN CIVIC / NON-GOVERNMENT / PEOPLE / VOLUNTARY ORGANIZATION/S";            
    def parententity
    def svc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('FOR REVIEW') ); 
    }
        boolean isDeleteAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }

    boolean isEditAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT|FOR REVIEW') ); 
    }
    
    def selectedVoluntaryWorkItem;

    void beforeSave(o){
        entity.state = "DRAFT";
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;  
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID; 
        if (o == 'create'){
            parententity._schemaname = 'hrmis_pds'
            // parententity.pdsno = svc.getPDSNo();
            // parententity.entityid = entity.person.objid
            parententity.version._schemaname = 'hrmis_pds_version'
            // parententity.version.versionno = svc.getVersionNo();
            // parententity.currentversionno
            // // parententity.name = entity.person.name;
            // persistenceSvc.create(parententity);             
            // persistenceSvc.create(parententity.version);        
            
            //save sa ang pds
            //save dayon ang version
        }
        // entity.residentialaddress = residentialAddress
    }
    
    public void afterCreate(){
        entity = parententity.version.voluntarywork
        
    }
    
    def voluntaryworkItemHandler = [
        fetchList: { 
            if(entity.voluntarywork?.objid)
            entity.voluntarywork = persistenceSvc.read( [_schemaname:'hrmis_pds_version_voluntarywork', objid:entity.voluntarywork.versionid] );
            return entity.voluntarywork?.voluntaryworkitem
        },
         createItem : {
            parententity._schemaname = 'hrmis_pds'
            parententity.version._schemaname = 'hrmis_pds_version'
             return[
                 objid      : 'VW' + new java.rmi.server.UID() +"-"+ dtSvc.getServerDate().year,
                 versionid  : parententity.version.objid,
             ]
         },
         onAddItem : {
//             it.voluntarywork.Id = it.voluntarywork.Id.toString()
////             entity.voluntaryworkitem.add(it);
            parententity.version.voluntarywork.add(entity)

         },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.voluntarywork.voluntaryworkitems.remove(it)
                voluntaryworkItemHandler?.load();
                return true;
            }
            return false;
        },
        validate:{li->
            def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
//         def voluntaryWorkItemHandler = [
//         fetchList: { 
//            return entity.voluntaryworkitems; 
//        },
//         createItem : {
//             return[
//                 objid : 'VW' + new java.rmi.server.UID() +"-"+ dtSvc.getServerDate().year,
//             ]
//         },
//         onRemoveItem : {
//             if (MsgBox.confirm('Delete item?')){                
//                 entity.voluntaryworkitems.remove(it)
//                 voluntaryworkItemHandler?.load();
//                 return true;
//             }
//             return false;
//         },
//         onAddItem : {
//             it.voluntarywork.Id = it.voluntarywork.Id.toString()
//             entity.voluntaryworkitems.add(it);
//         },
//         validate:{li->
//             def item=li.item;
//         }
//     ] as EditorListModel
//
//    def voluntaryworkListHandler = [
//        createItem : {
//            return [
//                objid       : "VWK" + new java.rmi.server.UID(),
//                objid : entity.voluntaryworkid
//            ]
//        },
//        
//        fetchList : {
//            return entity.voluntarywork
//        },
//        
//        onAddItem : {item ->
//            if(!entity.voluntarywork)
//                entity.voluntarywork = [];
//            entity.voluntarywork << item;
//        },
//            
//        onRemoveItem : {item ->
//            if(MsgBox.confirm("Remove selected item?")){
//                entity.voluntarywork.remove(item);
//                if(!entity._voluntarywork)
//                    entity._voluntarywork = [];
//                entity.voluntarywork << item;
//                return true;
//            }
//            return false;
//        }
//    ] as EditorListModel;

    
}
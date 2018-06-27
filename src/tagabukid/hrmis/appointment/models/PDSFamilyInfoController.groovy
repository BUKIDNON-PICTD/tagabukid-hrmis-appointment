import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
class  PDSFamilyInfoController extends CrudFormModel {
     @Binding
    def binding;
    
    def parententity
    def svc

    @Service('DateService')
    def dtSvc
    
    @Service("PersistenceService")
    def persistenceSvc;

    String title = "Family Background";
    
    boolean isCreateAllowed(){
        return false
    }
    
    boolean isViewReportAllowed(){
        return false
    }

    boolean isPrintReportAllowed(){
        return false
    }
    
    boolean isShowNavigation(){
        return false
    }
    
   public void beforeOpen() {
        entity.putAll(parententity)
    }
        
    public void afterOpen(){
       loadpersonalinfo()
    }

    public void beforeSave(o){
       if (o == 'create'){
           entity.pdsid = parententity.objid

            // parententity.version._schemaname = 'hrmis_pds'
            // parententity.version.versionno = svc.getVersionNo();
           
        }
    }
   
//    def loadpersonalinfo(){
//         entity = persistenceSvc.read([ _schemaname: 'hrmis_pds_familybackground', objid: entity.objid])
//         entity.person.putAll(persistenceSvc.read([ _schemaname: 'entityindividual', objid: entity.person.objid])) 
//     }

    public void afterCreate(){

               //education section
        entity.childInfos = []
    }
    def familyBackgroundItemListHandler = [
        fetchList: { entity?.childInfos },
        createItem : {
            return[
                recordlog : [
                    datecreated : dtSvc.getServerDate(),
                    createdbyuser : OsirisContext.env.FULLNAME,
                    createdbyuserid : OsirisContext.env.USERID,
                    dateoflastupdate : dtSvc.getServerDate(),
                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
                    lastupdatedbyuserid : OsirisContext.env.USERID,
                ],
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.childInfos.remove(it)
                familyBackgroundItemListHandler?.load();
                return true;
            }
            return false;
        },
        onColumnUpdate: { o,col-> 
            o.recordlog.dateoflastupdate = dtSvc.getServerDate();
            o.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            o.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
            
        },
        onAddItem : {
            entity.childInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
    
}
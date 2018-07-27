import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
     
class PDSReferenceController extends CrudFormModel {
    @Binding
    def binding;

    def parententity
    def svc
        
    @Service("DateService")
    def dtSvc
    
    // @Service("TagabukidSubayDocumentService")
    // def svc;
            
    // @Service("TagabukidSubayCabinetService") 
    // def subaycabinetService;
         
    // @Service("TagabukidSubayTransactionService")
    // def txnsvc;
    
    String title = "REFERENCES (Person not related by consanguinity or affinity to applicant /appointee)";
    
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
   
    def selectedReferenceItem
    public void beforeOpen() {
       entity.putAll(parententity);
    }
    public void beforeSave(o){
        if(o=='create'){
//            entity.skills{
//                
//            }
           
        }
    }

    public void afterOpen(){
        entity.references.each{
            it.reference = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.reference.objid]);
        }
    }

    def referenceListHandler = [
        fetchList: { 
            entity?.references
             },
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
                // satte : 'DRAFT'
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.references.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_reference',objid:it.objid])
                referenceListHandler?.load();
                return true;
            }
            return false;
        },
        onColumnUpdate: { o,col-> 
            o.dateoflastupdate = dtSvc.getServerDate();
            o.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            o.lastupdatedbyuserid = OsirisContext.env.USERID;
            
        },
        onAddItem : {
            entity.references.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel



    def getReferenceLookupHandler(){
        return Inv.lookupOpener('lookup:individualwide',[
                onselect :{
                    selectedReferenceItem.reference = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
                }
            ]);
    }
}
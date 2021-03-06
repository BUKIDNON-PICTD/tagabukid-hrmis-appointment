import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
class PDSVoluntaryWorkController extends CrudFormModel {
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
    
    String title = "VOLUNTARY WORK OR INVOLVEMENT IN CIVIC / NON-GOVERNMENT / PEOPLE / VOLUNTARY ORGANIZATION/S";
    
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
   
    def selectedVoluntaryWorkItem
    
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
    def voluntaryworkListHandler = [
        fetchList: { entity?.voluntaryworks.sort{it.datefrom} },
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
                entity.voluntaryworks.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_voluntarywork',objid:it.objid])
                voluntaryworkListHandler?.load();
                return true;
            }
            return false;
        },
        onColumnUpdate: { o,col-> 
            o.dateoflastupdate = dtSvc.getServerDate();
            o.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            o.lastupdatedbyuserid = OsirisContext.env.USERID;
            
            //kani para ma kumpleto ang address pag local
            if (col == 'organizationaddress') { 
                if(o.organizationaddress.type == 'local'){
                    o.organizationaddress.text = svc.formatAddress(o.organizationaddress,"\n")
                }
            } 
            
        },
        onAddItem : {
            entity.voluntaryworks.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
    
    
    def getOrganizationAddressLookup(){

        if(!selectedVoluntaryWorkItem.organizationaddress?.objid) {
            def h = { o->
                selectedVoluntaryWorkItem.organizationaddress = o;
            };
            def m = selectedVoluntaryWorkItem.organizationaddress;
            if(!m) m = [:];
           
            return Inv.lookupOpener( "address:editor", [handler:h, entity:m] );
        }
        else {
            def h = { o->
                o._schemaname = "entity_address";
                persistenceSvc.update( o );
                selectedVoluntaryWorkItem.organizationaddress = o;
            };
            def address = persistenceSvc.read( [_schemaname:'entity_address', objid:selectedVoluntaryWorkItem.organizationaddress.objid] );
            return Inv.lookupOpener( "address:editor", [handler:h, entity:address ]);
            //binding.refresh();
           
        }
        
    }
}
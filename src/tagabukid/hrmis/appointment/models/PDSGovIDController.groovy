import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
/**
 *
 * @author P0RA
 */

class PDSGovIDController extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def caller
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "Government Issued ID";
    def parententity
    def svc
    
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
        entity.putAll(parententity);
    }

    def selectedGovIDItem
    
    public void beforeSave(o){
        
        // if(entity.govids.size()<=0)
        // {
        //     def govids = [
        //         idtype: entity.idtype,
        //         idno  : entity.idno,
        //         issuance : entity.issuance,
        //         recordlog : [
        //             datecreated : dtSvc.getServerDate(),
        //             createdbyuser : OsirisContext.env.FULLNAME,
        //             createdbyuserid : OsirisContext.env.USERID,  
        //             dateoflastupdate : dtSvc.getServerDate(),
        //             lastupdatedbyuser : OsirisContext.env.FULLNAME,
        //             lastupdatedbyuserid : OsirisContext.env.USERID 
        //         ]
        //     ]
        //     entity.govids << govids
        // }else{
        //     def govids = [
        //         objid : entity.govid,
        //         idtype: entity.idtype,
        //         idno  : entity.idno,
        //         issuance : entity.issuance,
        //         recordlog : [
        //             dateoflastupdate : dtSvc.getServerDate(),
        //             lastupdatedbyuser : OsirisContext.env.FULLNAME,
        //             lastupdatedbyuserid : OsirisContext.env.USERID 
        //         ]
        //     ]
        //     entity.govids << govids
        // }
    }
    
    def govIDListHandler = [
        fetchList: { entity?.govids.sort{it.idtype} },
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
                entity.govids.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_govid',objid:it.objid])
                govIDListHandler?.load();
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
            entity.govids.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel

    // public void afterSave(){
    //     entity.govid=entity.govids[0].objid
    //     entity.idtype=entity.govids[0].idtype
    //     entity.idno=entity.govids[0].idno
    //     entity.issuance=entity.govids[0].issuance
    // }

    // public void afterOpen(){
    //     if(entity.govids){
    //         entity.govid=entity.govids[0].objid
    //         entity.idtype=entity.govids[0].idtype
    //         entity.idno=entity.govids[0].idno
    //         entity.issuance=entity.govids[0].issuance
    //     }
    // }
}
import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.annotations.Env
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
    
    @Env
    def env
    
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
    
    public void beforeSave(o){
            def govids = [
                idtype: entity.idtype,
                idno  : entity.idno,
                issuance : entity.issuance,
                recordlog : [
                    datecreated : dtSvc.getServerDate(),
                    createdbyuser : OsirisContext.env.FULLNAME,
                    createdbyuserid : OsirisContext.env.USERID,  
                    dateoflastupdate : dtSvc.getServerDate(),
                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
                    lastupdatedbyuserid : OsirisContext.env.USERID 
                ]
            ]
            entity.govids = []
            entity.govids.add(govids)
            println o
        
//            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
//            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
//            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID; 
            
    }
    
    public void afterSave(){
        entity.idtype=entity.govids[0].idtype
        entity.idno=entity.govids[0].idno
        entity.issuance=entity.govids[0].issuance
    }

    public void afterOpen(){
        entity.idtype=entity.govids[0].idtype
        entity.idno=entity.govids[0].idno
        entity.issuance=entity.govids[0].issuance
    }
}
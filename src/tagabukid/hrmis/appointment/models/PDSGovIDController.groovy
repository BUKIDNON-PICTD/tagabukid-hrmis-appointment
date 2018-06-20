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
    
    public void beforeSave(o){
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
        entity = parententity.version.govid
    }
    
}


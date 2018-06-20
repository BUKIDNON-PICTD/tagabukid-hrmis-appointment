import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.annotations.Env
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
class  PDSPersonalInfoController extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Env
    def env
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "Personal Information";
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
        if (o == 'create'){
            parententity._schemaname = 'hrmis_pds'
            parententity.pdsno = svc.getPDSNo();
            parententity.entityid = entity.person.objid
            parententity.version._schemaname = 'hrmis_pds_version'
            parententity.version.versionno = svc.getVersionNo();
            parententity.currentversionno
            persistenceSvc.create(parententity);             
            persistenceSvc.create(parententity.version); 
            
            
            //save sa ang pds
            //save dayon ang version
        }
        entity.residentialaddress = residentialAddress
        entity.permanentaddress = permanentAddress
    }
    public void afterCreate(){
        entity = parententity.version.personalinfo
    }
    
    def getResidentialAddress(){
        return entity.person.address
    }
    def getPermanentAddress(){
        return entity.person.address
    }
//    def getPhoto() {
//        return selectedItem.benificiary.photo;
//    }
}
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
    public void beforeOpen() {
        entity.objid = parententity.currentversionid
    }
    public void afterOpen(){
       loadpersonalinfo()
    }
    public void beforeSave(o){

        entity.personalinfo.residentialaddress = residentialAddress
        entity.personalinfo.permanentaddress = permanentAddress
         def personalinfo = [:]
        personalinfo = entity.personalinfo
        personalinfo._schemaname = 'hrmis_pds_version_personalinfo'
        persistenceSvc.update(personalinfo); 
    }
    public void afterSave(){
        loadpersonalinfo()
    }
    
    def loadpersonalinfo(){
        entity.personalinfo = persistenceSvc.read([ _schemaname: 'hrmis_pds_version_personalinfo', objid: entity.objid])
        entity.personalinfo.person = persistenceSvc.read([ _schemaname: 'entityindividual', objid: entity.personalinfo.person.objid]) 
    }
    def setResidentialAddress(o){
        return o
    }
    
    def setPermanentAddress(o){
        return o
    }
    def getResidentialAddress(){
        return entity.personalinfo.person.address
    }
    def getPermanentAddress(){
        return entity.personalinfo.person.address
    }
//    def getPhoto() {
//        return selectedItem.benificiary.photo;
//    }
}
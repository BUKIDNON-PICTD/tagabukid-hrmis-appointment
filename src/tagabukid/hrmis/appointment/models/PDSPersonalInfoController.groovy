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
    
    @Caller
    def maincontroller
    
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
        entity.putAll(parententity)
    }
        
    public void afterOpen(){
       loadpersonalinfo()
    }
    public void beforeSave(o){
   
         if (o == 'create'){
            entity.pdsno = svc.getPDSNo();
            entity.versionno = svc.getVersionNo();
            entity.residentialaddress = residentialAddress
            entity.permanentaddress = permanentAddress
        }
    }
    public void afterCreate(){
        entity.putAll(parententity)
    }
    public void afterSave(){
        entity.person._schemaname = 'entityindividual'
        persistenceSvc.update(entity.person)
        maincontroller.reloadSections('open');
    }
    
    def loadpersonalinfo(){
        entity = persistenceSvc.read([ _schemaname: 'hrmis_pds', objid: entity.objid])
        entity.person.putAll(persistenceSvc.read([ _schemaname: 'entityindividual', objid: entity.person.objid])) 
    }
//    def setResidentialAddress(o){
//        return o
//    }
//    
//    def setPermanentAddress(o){
//        return o
//    }
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
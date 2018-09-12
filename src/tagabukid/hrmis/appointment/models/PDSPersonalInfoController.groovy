import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
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
    
        
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "Personal Information";
    
    def parententity
    def svc
    
    @PropertyChangeListener
    def listener = [
        'entity.person' : { 
          
            entity.person.address.text = svc.formatAddress(entity.person.address,"\n")
            entity.residential.address = entity.person.address
            entity.permanent.address = entity.person.address
       
            binding.refresh();
            maincontroller.reloadphoto(entity.person);
        }
        
    ]
    
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
    //    residentialAddress = entity.residential.address
    //    permanentAddress = entity.permanent.address
    }

//    def getResidentialAddress(){
////        if (entity.copyresidential){
////            entity.residential.address = entity.person.address
////            return entity.residential.address
////        }
//        if (entity.residential){
//            entity.residential.address.text = svc.formatAddress(entity.residential.address,"\n")
//        }else{
//            entity.person.address.text = svc.formatAddress(entity.person.address,"\n")
//            entity.residential.address = entity.person.address
//        }
//        
//        return entity.residential.address
//    }
//    def getPermanentAddress(){
////        if (entity.copypermanent){
////            entity.permanent.address = entity.residential.address
////            return entity.permanent.address
////        }
//        
//        if (entity.permanent.address != entity.person.address){
//            entity.permanent.address.text = svc.formatAddress(entity.permanent.address,"\n")
//        }else{
//            entity.person.address.text = svc.formatAddress(entity.person.address,"\n")
//            entity.permanent.address = entity.person.address
//        }
//        
//        println entity.permanent.address
//        return entity.permanent.address
//    }
//    def getPhoto() {
//        return selectedItem.benificiary.photo;
//    }
}

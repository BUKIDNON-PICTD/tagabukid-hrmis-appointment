import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.annotations.Env
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class PDSInfoController extends CrudFormModel{
//    @Script("TagabukidPDSInfoUtil")
//    def docinfo
    @Binding
    def binding;
    
    @Service("TagabukidHRMISPDSService")
    def svc;

    @Service("PersistenceService")
    def persistenceSvc;
    // @FormId
    // def formId

    @FormTitle
    def title

    // def entityName = "documentinfo";

    def sections;
    def currentSection;
//    def barcodeid;
//    def startstep;

            
//    void open() {
//        title = entity.objid;
//        loadSections();
//////        println entity
////        if (entity?.filetype?.matches('document_incoming|document_outgoing')){
//////            entity.objid = entity.data.objid;
////            entity.taskid = entity.data.taskid;
////        }
////        entity = service.open( [barcodeid: barcodeid,taskid:entity?.taskid,objid:entity?.objid ] );
////        title = entity.title + ' (' + entity.din + ')';
////        loadSections();
////        formId = entity.objid;
//////        println entity
//    }
    public void beforeSave(o){
        if (o == 'create'){
            entity.pdsno = svc.getPDSNo();
            entity.entityid = entity.person.objid
            entity.name = entity.person.name;
            entity.versions.each{
                it.versionno = svc.getVersionNo();
                entity.currentversionno = it.versionno
                it.personalinfo.person = entity.person
            }
        }
    }
    public void afterSave(){
        //save sa personal kay di ga work ang one-to-one
        def personalinfo = [:]
        personalinfo = entity.versions[0].personalinfo
        personalinfo._schemaname = 'hrmis_pds_version_personalinfo'
        persistenceSvc.create(personalinfo); 
        loadSections();
    }
    
    public void afterCreate (){
        title = "New PDS";
        entity = svc.initCreate()
    }
    public void afterOpen(){
        loadSections();
    }
    void reloadSections() {
//        binding.refresh("subform");
        def handlers = Inv.lookupOpeners("pds:section",[parententity:entity,svc:svc]);
        def selitemid = currentSection?.id; 
        sections.clear();
        sections.addAll( 
            handlers.findAll {
                def vw = it.properties.visibleWhen;
                return  ((!vw)  ||  ExpressionResolver.getInstance().evalBoolean( vw, [parententity:entity,svc:svc] ));     
            }
        ); 

        currentSection = sections.find{ it.id == selitemid } 
        if ( sections && currentSection==null ) {
            currentSection = sections.first(); 
        }
        binding.refresh();
    }

    void loadSections() {
        sections = InvokerUtil.lookupOpeners( "pds:section",[parententity:entity,svc:svc]).findAll {
            def vw = it.properties.visibleWhen;
            return  ((!vw)  ||  ExpressionResolver.getInstance().evalBoolean( vw, [parententity:entity,svc:svc] ));
        }
        if( sections.size()>0 ) {
            currentSection = sections[0];
        }  
    }

    void reloadCurrentSection() {
        MsgBox.alert( currentSection.name );
    }
                
//    def showParent() {
//        if( !entity.parentid )
//        throw new Exception("No parent document");
//        def parent = [:]
//        parent.objid = entity.parentid 
//        return Inv.lookupOpener( "subaydocument:open", [entity: parent] ); 
//    }
//            
//    def showDocInfo() {
//        boolean test = false;
//        docinfo.handler = {
//            test = true;
//        }
//        try{
//            Modal.show(docinfo.update());
//            if(!test) throw new BreakException();
//        }catch(e){
//
//        }
//
//    }


}
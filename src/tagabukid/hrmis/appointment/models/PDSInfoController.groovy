import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class PDSInfoController{
    @Binding
    def binding;
    
    @Controller
    def workunit;
    
    @Service("TagabukidHRMISPDSService")
    def svc;

    @Service("PersistenceService")
    def persistenceSvc;
 
    @FormTitle
    def title

    def sections;
    def currentSection;
    def entity;

    public void beforeSave(o){
        if (o == 'create'){
            entity.pdsno = svc.getPDSNo();
            entity.entityid = entity.person.objid
            entity.name = entity.person.name;
            entity.versions.each{
                it.versionno = svc.getVersionNo();
                entity.currentversionno = it.versionno
                it.person.putAll(entity.person)
            }
        }
    }
//    public void afterSave(){
//        loadSections();
//    }

    // boolean isAllowApprove() {
    //     return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    // }
    
    // boolean isAllowPreviewAppointment() {
    //     return ( mode=='read'); 
    // }
    def preview(){
       def op = Inv.lookupOpener("pds:report",[entity: entity]);
       op.target = 'self';
       return op;
    }
    def verify(){
       def op = Inv.lookupOpener("verification:report",[entity: entity]);
       op.target = 'self';
       return op;
    }
    
    

    public void approve(){
       loadSections('open');
    }

    public void create(){
        title = "New PDS";
        entity = svc.initCreate();
        loadSections('create');
    }
    public void open(){
        title = entity.pdsno + " - " + entity.person.name
        entity = persistenceSvc.read([ _schemaname: 'hrmis_pds', objid: entity.objid])
        entity.person.putAll(persistenceSvc.read([ _schemaname: 'entityindividual', objid: entity.person.objid])) 
        loadSections('open');
    }
//    public void afterCreate (){
//       
//    }
//    public void afterOpen(){
//        loadSections();
//    }
    void reloadSections(action) {
//        binding.refresh("subform");
        def handlers = Inv.lookupOpeners("pds:section:"+action,[parententity:entity,svc:svc]);
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

    void loadSections(action) {
        sections = InvokerUtil.lookupOpeners( "pds:section:"+action,[parententity:entity,svc:svc]).findAll {
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
    
    void reloadphoto(person) {
        entity.person = person
        binding.refresh("entity.person.*");
    }

}
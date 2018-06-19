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
class PDSReferencesSectionController extends CrudFormModel{
    @Binding
    def binding;
    
    @Caller
    def caller
    
    @Service("DateService")
    def dtSvc
    
    @Env
    def env
    
    @Service("PersistenceService")
    def persistenceSvc;

    String title = "REFERENCES";
    def parententity
    def svc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('FOR REVIEW') ); 
    }
        boolean isDeleteAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }

    boolean isEditAllowed() {
        return ( mode=='read' && entity.state.toString().matches('DRAFT|FOR REVIEW') ); 
    }

    def selectedReferenceItem;
    
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
            parententity.version._schemaname = 'hrmis_pds_version'
            //save sa ang pds
            //save dayon ang version
        }
    }
    
        public void afterCreate(){
        entity = parententity.version.references
    }

    def referenceItemHandler = [
        fetchList : {

        },

        createItem : {

        },

        onAddItem : {

        },

        onRemoveItem : {

        },
        validate : {li->
            def item=li.item;
        }
    ] as EditorListModel
    
    
}


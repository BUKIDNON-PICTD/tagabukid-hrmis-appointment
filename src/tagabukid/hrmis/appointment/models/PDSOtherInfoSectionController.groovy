import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat
        

class  PDSOtherInfoSectionController extends CrudFormModel{
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

    String title = "OTHER INFORMATION";
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

    def selectedSkillItem;
    def selectedRecognitionItem;
    def selectedAssociationOrganizationItem;
    
    void beforeSave(o){
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
        // entity = parententity.version.voluntarywork
        
    }

    def skillListHandler = [
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

    def recognitionListHandler = [
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

    def associationorganizationListHandler = [
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
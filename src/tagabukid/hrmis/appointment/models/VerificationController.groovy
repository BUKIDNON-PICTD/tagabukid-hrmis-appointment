import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class VerificationController extends CrudFormModel {

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc

    @Service
    def persistenceSvc

    @Service('TagabukidHRMISVerificationService')
    def svc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    // public void afterOpen(){   
    //     // if(entity.skillcategory)            
    //     // entity.skillcategory = persistenceSvc.read( [_schemaname:'master_tblskillcategory', objid:entity.skillcategoryid] );
    //     // if(entity.skillcompetencylevel)
    //     // entity.skillcompetencylevel = persistenceSvc.read( [_schemaname:'master_tblloccitymunicipality', objid:entity.skillcompetencylevelid] );
    // }

    public void afterCreate(){
        // title = "Applicant Verification";
        entity = svc.initCreate();
        
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        entity.father._schemaname = 'hrmis_verify_father';
        entity.father.applicantid = entity.objid;
        if(o == 'create'){
            entity.person_name = entity.person.lastname + ", " + entity.person.firstname + " " + entity.person.middlename;
            entity.itemno = svc.getApplicantNo();
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;  
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;

        }else{
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
        }
    }


    void approve() { 
        if ( MsgBox.confirm('You are about to approve this information. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'hrmis_verify_applicant', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }
}
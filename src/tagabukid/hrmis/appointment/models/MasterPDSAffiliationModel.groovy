import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
/**
 *
 * @author P0RA
 */
class MasterPDSAffiliationModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){   
        // if(entity.professionalorganizationorassociationcategory)            
        // entity.professionalorganizationorassociationcategory = persistenceSvc.read( [_schemaname:'master_tblprofessionalorganizationorassociationcategory', objid:entity.professionalorganizationorassociationcategoryid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        // if(entity.professionalorganizationorassociationcategory)
        // entity.professionalorganizationorassociationcategoryid = entity.professionalorganizationorassociationcategory.objid
        if(o == 'create'){
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
               _schemaname: 'hrmis_tblprofileaffiliation', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}

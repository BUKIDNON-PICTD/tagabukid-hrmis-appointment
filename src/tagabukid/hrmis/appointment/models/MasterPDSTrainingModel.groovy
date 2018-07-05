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
class MasterPDSTrainingModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){   
        if(entity.trainingsubcategory)            
        entity.trainingsubcategory = persistenceSvc.read( [_schemaname:'master_tbltrainingsubcategory', objid:entity.trainingsubcategoryid] );
        if(entity.sponsorinstitutiontype)
        entity.sponsorinstitutiontype = persistenceSvc.read( [_schemaname:'master_tblinstitutiontype', objid:entity.sponsorinstitutiontypeid] );
        if(entity.trainingcomprehensiveness)
        entity.trainingcomprehensiveness = persistenceSvc.read( [_schemaname:'master_tbltrainingcomprehensiveness', objid:trainingcomprehensivenessid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        if(entity.trainingsubcategory)
        entity.trainingsubcategoryid = entity.trainingsubcategory.objid
        if(entity.sponsorinstitutiontype)
        entity.sponsorinstitutiontypeid = entity.sponsorinstitutiontype.objid
        if(entity.trainingcomprehensiveness)
        entity.trainingcomprehensivenessid = entity.trainingcomprehensiveness.objid
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
               _schemaname: 'hrmis_tblprofiletrainingprogramattended', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}

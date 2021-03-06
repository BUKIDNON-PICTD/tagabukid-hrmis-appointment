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
class MasterAcadEducationalInstitutionBackModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc;
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){ 
        if(!entity.parentid)
            entity.parent = "";
        else            
            entity.parent = persistenceSvc.read( [_schemaname:'master_tblacadeducationalinstitutionback', objid:entity.parentid] );
        
        if(!entity.barangayid)
            entity.barangay = "";
        else
            entity.barangay = persistenceSvc.read( [_schemaname:'master_tbllocbarangay', objid:entity.barangayid] );
        
        if(!entity.citymunid)
            entity.citymun = "";
        else
            entity.citymun = persistenceSvc.read( [_schemaname:'master_tblloccitymunicipality', objid:entity.citymunid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        if(!entity.parent)
            entity.parentid = "";
        else
            entity.parentid = entity.parent.objid;

        if(!entity.barangay)
            entity.barangayid = "";
        else
            entity.barangayid = entity.barangay.objid;

        if(!entity.citymun)
            entity.citymunid = "";
        else
            entity.citymunid = entity.citymun.objid;

        if(o == 'create'){
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;  
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatebyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatebyuserid = OsirisContext.env.USERID;
        }else{
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatebyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatebyuserid = OsirisContext.env.USERID;
        }
    }
    
    void approve() { 
        if ( MsgBox.confirm('You are about to approve this information. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'master_tblacadeducationalinstitutionback', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;


class MasterInstitutionModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){               
        entity.citymunicipality = persistenceSvc.read( [_schemaname:'master_tblloccitymunicipality', objid:entity.citymunid] );
        entity.barangay = persistenceSvc.read( [_schemaname:'master_tbllocbarangay', objid:entity.barangayid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
            entity.citymunid = entity.citymunicipality.objid;
            entity.barangayid = entity.barangay.objid; 
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
               _schemaname: 'master_tblinstitution', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}
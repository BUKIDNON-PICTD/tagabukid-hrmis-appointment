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
class MasterPDSVoluntaryWorkModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){   
        // if(entity.institutiontype)            
        // entity.institutiontype = persistenceSvc.read( [_schemaname:'master_tblinstitutiontype', objid:entity.institutiontypeid] );
        // if(entity.muncityaddr)
        // entity.muncityaddr = persistenceSvc.read( [_schemaname:'master_tblloccitymunicipality', objid:entity.muncityaddrid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        // if(entity.institutiontype)
        // entity.institutiontypeid = entity.institutiontype.objid
        // if(entity.muncityaddr)
        // entity.muncityaddrid = entity.muncityaddr.objid
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
               _schemaname: 'master_tblvoluntarywork', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}

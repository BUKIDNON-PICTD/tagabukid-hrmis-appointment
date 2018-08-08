import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

/**
 *
 * @author pictd-training1
 */
class MasterFinAccountTitleModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Service("TagabukidHRMISAccountTitleManagementService") 
    def hrmisaccounttitleService
            
    def node;
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }
    
    public void afterOpen(){ 
        // if(!entity.parentaccounttitleid)
        // entity.parentaccounttitle = "";
        // else
        // entity.parentaccounttitle = persistenceSvc.read( [_schemaname:'master_tblfinaccounttitle', objid:entity.parentaccounttitleid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        // if(!entity.parentaccounttitle)
        //     entity.parentaccounttitleid = entity.parentaccounttitle;
        // else
        //     entity.parentaccounttitleid = entity.parentaccounttitle.objid;
        entity.parentaccounttitle_objid = node.objid

        if(o == 'create'){
            entity = hrmisaccounttitleService.manageLftRgt(entity)
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
               _schemaname: 'master_tblfinaccounttitle', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}
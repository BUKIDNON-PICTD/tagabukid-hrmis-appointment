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
class MasterFinAccountModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }
    
    public void afterOpen(){ 
        entity.fund = persistenceSvc.read( [_schemaname:'master_tblfinfund', objid:entity.fundid] );
        if(!entity.parentaccountid)
            entity.parentaccount = "";
        else
            entity.parentaccount = persistenceSvc.read( [_schemaname:'master_tblfinaccount', objid:entity.parentaccountid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        entity.fundid = entity.fund.objid;
            if(!entity.parentaccount)
                entity.parentaccountid = "";
            else
                entity.parentaccountid = entity.parentaccount.objid;
            
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
               _schemaname: 'master_tblfinaccount', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}
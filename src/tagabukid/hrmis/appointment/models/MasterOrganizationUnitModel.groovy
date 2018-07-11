import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class MasterOrganizationUnitModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Service("TagabukidHRMISOrganizationUntiManagementService") 
    def hrmisorgService
            
    def node;
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }
    
//     public void test() {
//         println entity
//         println node
////        entity.putAll(parententity);
//    }
    public void afterOpen(){ 
//        ((!entity.parentorgunitid) ? (entity.parentorgunit="") : (entity.parentorgunit = persistenceSvc.read( [_schemaname:'master_tblorganizationunit', orgunitid:entity.parentorgunitid] )))
        ((!entity.organizationunittypeid) ? (entity.organizationunittype="") : (entity.organizationunittype = persistenceSvc.read( [_schemaname:'master_tblorganizationunittype', orgunittypeid:entity.organizationunittypeid] )))
        ((!entity.addrcitymunid) ? (entity.addrcitymun="") : (entity.addrcitymun = persistenceSvc.read( [_schemaname:'master_tblloccitymunicipality', objid:entity.addrcitymunid] )))
        ((!entity.addrbarangayid) ? (entity.addrbarangay="") : (entity.addrbarangay = persistenceSvc.read( [_schemaname:'master_tbllocbarangay', objid:entity.addrbarangayid] )))
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        
        entity.parentorgunitid = node.orgunitid
//        ((!entity.parentorgunit) ? (entity.parentorgunitid = "") : (entity.parentorgunitid = entity.parentorgunit.orgunitid))
        ((!entity.organizationunittype) ? (entity.organizationunittypeid="") : (entity.organizationunittypeid=entity.organizationunittype.orgunittypeid))
        ((!entity.addrcitymun) ? (entity.addrcitymunid = "") : (entity.addrcitymunid = entity.addrcitymun.objid))
        ((!entity.addrbarangay) ? (entity.addrbarangayid = "") :  (entity.addrbarangayid = entity.addrbarangay.objid))
             
        if(o == 'create'){
            entity = hrmisorgService.manageLftRgt(entity)
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
               _schemaname: 'master_tblorganizationunit', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}
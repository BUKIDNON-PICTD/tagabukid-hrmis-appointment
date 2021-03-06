import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class MasterTrainingSubcategoryModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc 

    @Service("PersistenceService")
    def persistenceSvc;
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){               
        //entity.trainingcategory = persistenceSvc.read( [_schemaname:'master_tbltrainingcategory', objid:entity.trainingcategoryid] );
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
        //entity.trainingcategoryid = entity.trainingcategory.objid;
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
               _schemaname: 'master_tbltrainingsubcategory', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}

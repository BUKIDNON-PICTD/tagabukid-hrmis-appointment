import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class MasterlocCityMunicipalityModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    public void afterOpen(){               
        // entity.province = persistenceSvc.read( [_schemaname:'master_tbllocprovince', objid:entity.provinceid] );
        // if(entity.congressionaldistrict)
        // entity.congressionaldistrict = persistenceSvc.read( [_schemaname:'master_tblloccongressionaldistrict', objid:entity.congressionaldistrictid] );
    
    }

    public void beforeSave(o){
        entity.state = "DRAFT";
            // entity.provinceid = entity.province.objid;
            // if(entity.congressionaldistrict)
            // entity.congressionaldistrictid = entity.congressionaldistrict.objid;        
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
               _schemaname: 'master_tblloccitymunicipality', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

}
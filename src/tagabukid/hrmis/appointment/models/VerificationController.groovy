import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class VerificationController extends CrudFormModel {

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

    // public void afterOpen(){   
    //     // if(entity.skillcategory)            
    //     // entity.skillcategory = persistenceSvc.read( [_schemaname:'master_tblskillcategory', objid:entity.skillcategoryid] );
    //     // if(entity.skillcompetencylevel)
    //     // entity.skillcompetencylevel = persistenceSvc.read( [_schemaname:'master_tblloccitymunicipality', objid:entity.skillcompetencylevelid] );
    // }


    void approve() { 
        if ( MsgBox.confirm('You are about to approve this information. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'hrmis_verify_applicant', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }
}
package tagabukid.hrmis.appointment.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class JobOrderAppointmentSignatoryGroupModel extends CrudFormModel{

     @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Service('SequenceService')
    def seqSvc
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }
    
    
    def selectedSignatoryGroupItem;
     
    public void afterCreate(){
        
        entity.signatoryGroupItems = [];
        signatoryGroupItemHandler.reload();
       // entity.farmerid = OsirisContext.env.ORGID + "-FARM" + seqSvc.getNextFormattedSeries('farmer');
            
    }

        public void beforeSave(o){
            if(!entity.signatoryGroupItems)throw new Exception("Signatory Group items must not be empty");
            
            if(mode == 'create' ) {
                if(validateBeforeSave(entity)) throw new Exception("Farmer already exists");
            }
            
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;
            entity.state = "DRAFT";
     
        }
 
    def signatoryGroupItemHandler = [
        fetchList: { o->
            def p = [_schemaname: 'hrmis_appointmentjoborder_signatorygroupingitems'];
            p.findBy = [ 'parentid': entity.objid];
            p.select = "objid,parentid,signatoryname,signatorytitle,signatoryheading,remarks,level";
            if(!entity.signatoryGroupItems){
                entity.signatoryGroupItems = queryService.getList( p );
            }
            return entity.signatoryGroupItems;
        },
        createItem : {
           return[
               objid : 'SGI' + new java.rmi.server.UID(),
               newitem : false,
           ]
        },
        onAddItem : {
            entity.signatoryGroupItems << it;
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')) {
                //service.deleteFarmerItems(it)
                entity.signatoryGroupItems.remove(it)
                signatoryGroupItemHandler.reload();
                return true;
                }
            return false;
            }
            
        
    ] as EditorListModel;
    


    //    ========== Lookup Commodity =========
    //def getLookupCommodity(){
     //   return InvokerUtil.lookupOpener('pagricommodity:lookup');
   // }
   
    //def validateBeforeSave(o){
        
        
     //   return fSvc.validateBeforeSave(o);
    //}

}
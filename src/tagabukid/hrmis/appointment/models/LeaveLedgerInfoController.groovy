import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
import tagabukid.utils.*;
        
class  LeaveLegerInfoController extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def maincontroller
    
        
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "Leave Ledger";
    
    def parententity
    def svc
    
    @Service('QueryService') 
    def querySvc; 
    
    def selectedLeave
    def selectedLeaveDetail
    def attachmentSelectedItem;
    
    boolean isCreateAllowed(){
        return false
    }
    boolean isEditAllowed(){
        return false
    }
    boolean isViewReportAllowed(){
        return false
    }
    boolean isReloadAllowed(){
        return false
    }
    boolean isPrintReportAllowed(){
        return false
    }
    
    boolean isShowNavigation(){
        return false
    }
    
    boolean isAllowApprove() {
        return ( selectedLeaveDetail.status.matches('DRAFT')); 
    }
    
    boolean isAllowCancel() {
        return ( selectedLeaveDetail.status.matches('APPROVED')); 
    }
    
    boolean isAllowDraft() {
        return ( selectedLeaveDetail.status.matches('CANCELLED')); 
    }
    
    public void beforeOpen() {
        entity.putAll(parententity)
        
    }

    public void afterOpen(){
//        loadpersonalinfo()
        loadleave()
        leaveListHandler.reload();
//        attachmentListHandler.reload();
        
    }
    // public void beforeSave(o){
    //     if (o == 'create'){
    //         entity.pdsno = svc.getPDSNo();
    //         entity.versionno = svc.getVersionNo();
            
    //     }
    // }
    // public void afterCreate(){
    //     entity.putAll(parententity)
    // }
    public void afterSave(){
        entity.person._schemaname = 'entityindividual'
        
        persistenceSvc.update(entity.person)
        maincontroller.reloadSections('open');

        // loadBTACSidIncrement()
    }
    
    def capture(){
        if (MsgBox.confirm('you are about to capture an unposted transaction. Proceed?')){
            return InvokerUtil.lookupOpener('hrmis_leave:create')
        }
        
    }
    void loadpersonalinfo(){
        entity = persistenceSvc.read([ _schemaname: 'hrmis_pds', objid: entity.objid])
        entity.person.putAll(persistenceSvc.read([ _schemaname: 'entityindividual', objid: entity.person.objid])) 
        //    residentialAddress = entity.residential.address
        //    permanentAddress = entity.permanent.address
    }
    
    void loadleave(){
        def p = [_schemaname: 'hrmis_leave'];
            p.findBy = [ 'pds_objid': entity.objid];
        entity.leave = querySvc.getList( p )
        entity.leave.each{
            it.putAll(persistenceSvc.read([_schemaname:'hrmis_leave',objid:it.objid]))
            it.attachments = TagabukidDBImageUtil.getInstance().getImages(it.objid);
        }
//        entity.leave
        
    }   
    
    def leaveListHandler = [
        fetchList: { o->
            return entity.leave.sort{it.datefiled}.reverse();
        },
        createItem : {
            return[
                objid : 'LE' + new java.rmi.server.UID()
            ]
        }
//        onColumnUpdate: { o,col-> 
//            if(col == 'position'){
//                o.salaryscheduleitem  = joSvc.getDailyWageByTranch(entity.currentsalarystep,o.position);
//                o.monthlysalary = o.salaryscheduleitem.amount
//                o.dailysalary = o.salaryscheduleitem.amount / 22
//            }
//            
//             if(col == 'cutoffdate'){
//                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date date = sdf1.parse(o.cutoffdate);
//                java.sql.Date cutoffdate = new java.sql.Date(date.getTime()); 
//                if (entity.effectivefrom >= cutoffdate || cutoffdate >= entity.effectiveuntil){
//                    throw new Exception("Cut-off date must be between Effective From and Effective Until.");
//                }
//            }
//            
//        },
//        onCommitItem:{ x ->
//            if (x.cutoffdate && !x.cutoffreason.objid){
//                throw new Exception("Cut-off Reason is required.");
//            }
//        },
        
//        onAddItem : {
//            entity.appointmentMemberItems << it;
//        },
//        onRemoveItem : {
//            if (MsgBox.confirm('Delete item?')) {
//                //service.deleteFarmerItems(it)
//                entity.appointmentMemberItems.remove(it)
//                persistenceSvc.removeEntity([_schemaname:'hrmis_appointmentjoborderitems',objid:it.objid])
//                appointmentMemberItemHandler.reload();
//                return true;
//            }
//            return false;
//        },
//        
//        validate:{li->
//            def item=li.item;
//            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
//        }
    ] as EditorListModel;
    
    def leaveDetailHandler = [
        fetchList: { o->
            return selectedLeave?.leavedetails?.sort{it.fromdate};
        },
        createItem : {
            return[
                objid : 'LE' + new java.rmi.server.UID()
            ]
        }
//        onColumnUpdate: { o,col-> 
//            if(col == 'position'){
//                o.salaryscheduleitem  = joSvc.getDailyWageByTranch(entity.currentsalarystep,o.position);
//                o.monthlysalary = o.salaryscheduleitem.amount
//                o.dailysalary = o.salaryscheduleitem.amount / 22
//            }
//            
//             if(col == 'cutoffdate'){
//                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date date = sdf1.parse(o.cutoffdate);
//                java.sql.Date cutoffdate = new java.sql.Date(date.getTime()); 
//                if (entity.effectivefrom >= cutoffdate || cutoffdate >= entity.effectiveuntil){
//                    throw new Exception("Cut-off date must be between Effective From and Effective Until.");
//                }
//            }
//            
//        },
//        onCommitItem:{ x ->
//            if (x.cutoffdate && !x.cutoffreason.objid){
//                throw new Exception("Cut-off Reason is required.");
//            }
//        },
        
//        onAddItem : {
//            entity.appointmentMemberItems << it;
//        },
//        onRemoveItem : {
//            if (MsgBox.confirm('Delete item?')) {
//                //service.deleteFarmerItems(it)
//                entity.appointmentMemberItems.remove(it)
//                persistenceSvc.removeEntity([_schemaname:'hrmis_appointmentjoborderitems',objid:it.objid])
//                appointmentMemberItemHandler.reload();
//                return true;
//            }
//            return false;
//        },
//        
//        validate:{li->
//            def item=li.item;
//            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
//        }
    ] as EditorListModel;
    
    void approve() { 
        if ( MsgBox.confirm('You are about to approve this transaction. Proceed?')) { 
            getPersistenceService().update([ 
                    _schemaname: 'hrmis_leavedetails', 
                    objid : selectedLeaveDetail.objid, 
                    status : 'APPROVED' 
                ]); 
            maincontroller.reloadSections('open');
        }
    }
    void cancel() { 
        if ( MsgBox.confirm('You are about to cancel this transaction. Proceed?')) { 
            getPersistenceService().update([ 
                    _schemaname: 'hrmis_leavedetails', 
                    objid : selectedLeaveDetail.objid, 
                    status : 'CANCELLED' 
                ]); 
            maincontroller.reloadSections('open');
        }
    }
    void draft() { 
        if ( MsgBox.confirm('You are about to draft this transaction. Proceed?')) { 
            getPersistenceService().update([ 
                    _schemaname: 'hrmis_leavedetails', 
                    objid : selectedLeaveDetail.objid, 
                    status : 'DRAFT' 
                ]); 
            maincontroller.reloadSections('open');
        }
    }
    
    def attachmentListHandler = [
        fetchList : {return selectedLeave?.attachments },
    ] as BasicListModel
    
    def viewAttachment(){
        if (!attachmentSelectedItem) return null;

        if (attachmentSelectedItem.extension.contains("pdf")){
            return InvokerUtil.lookupOpener('attachmentpdf:view', [
                    entity : attachmentSelectedItem,
                ]); 
        }else{
            return InvokerUtil.lookupOpener('attachment:view', [
                    entity : attachmentSelectedItem,
                ]); 
        }

    }
    
    void deleteAttachment(){
        if (!attachmentSelectedItem) return;
        if (MsgBox.confirm('Delete selected Attachment?')){
            TagabukidDBImageUtil.getInstance().deleteImage(attachmentSelectedItem.objid);
            loadAttachments();
        }
    }
    
    def addAttachment(){
        return InvokerUtil.lookupOpener('upload:attachment', [
                entity : selectedLeave,
                afterupload: {
                    loadAttachments();
                }
            ]);
    }
    
    void loadAttachments(){
        selectedLeave?.attachments = [];
        try{
            selectedLeave.attachments = TagabukidDBImageUtil.getInstance().getImages(selectedLeave?.objid);
        }
        catch(e){
            println 'Load Attachment error ============';
            e.printStackTrace();
        }
        attachmentListHandler?.load();
    }
}

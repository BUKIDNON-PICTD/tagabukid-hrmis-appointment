import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
import tagabukid.utils.*;
        
class  LeaveCardController extends CrudFormModel{
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
    
    def selectedLeaveCard
    def selectedLeaveCardDetail
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
    public void beforeOpen() {
        entity.putAll(parententity)
    }
        
    public void afterOpen(){
//        loadpersonalinfo()
        loadleave()
//        loadAttachments()
        leaveCardListHandler.reload();
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
    
    def capturedr(){
        if (MsgBox.confirm('you are about to post DR transaction?')){
            return InvokerUtil.lookupOpener('hrmis_leavecard:dr:create',[entity:selectedLeaveCard])
        }
    }
    def capturecr(){
        if (MsgBox.confirm('you are about to post CR transaction?')){
            return InvokerUtil.lookupOpener('hrmis_leavecard:cr:create',[entity:selectedLeaveCard])
        }
    }
    
    void loadpersonalinfo(){
        entity = persistenceSvc.read([ _schemaname: 'hrmis_pds', objid: entity.objid])
        entity.person.putAll(persistenceSvc.read([ _schemaname: 'entityindividual', objid: entity.person.objid])) 
        //    residentialAddress = entity.residential.address
        //    permanentAddress = entity.permanent.address
    }
    
    void loadleave(){
        def p = [_schemaname: 'hrmis_leavecard'];
            p.findBy = [ 'pds_objid': entity.objid];
        entity.leavecard = querySvc.getList( p )
        entity.leavecard.each{
            it.putAll(persistenceSvc.read([_schemaname:'hrmis_leavecard',objid:it.objid]))
        }
    }   
    
    def leaveCardListHandler = [
        fetchList: { o->
            return entity.leavecard.sort{it.code}.reverse();
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
    
    def leaveCardDetailHandler = [
        fetchList: { 
            selectedLeaveCard?.leavecarddetails?.each{
                it.attachments = TagabukidDBImageUtil.getInstance().getImages(it?.objid);
            }
            return selectedLeaveCard?.leavecarddetails?.sort{it.txndate};
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
    
    //ATTACHMENT PART
    
    def attachmentListHandler = [
        fetchList : {
            return selectedLeaveCardDetail?.attachments 
        },
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
                entity : selectedLeaveCardDetail,
                afterupload: {
                    loadAttachments();
                }
            ]);
    }
    
    void loadAttachments(){
        selectedLeaveCardDetail?.attachments = [];
        try{
            selectedLeaveCardDetail.attachments = TagabukidDBImageUtil.getInstance().getImages(selectedLeaveCardDetail?.objid);
        }
        catch(e){
            println 'Load Attachment error ============';
            e.printStackTrace();
        }
        attachmentListHandler?.load();
    }
}

import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
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
    
    @PropertyChangeListener
    def listener = [
        'entity.person' : { 
       
            if(!isLeaveExist()){
                entity.person.address.text = svc.formatAddress(entity.person.address,"\n")
                entity.residential.address = entity.person.address
                entity.permanent.address = entity.person.address
                
                binding.refresh();
                maincontroller.reloadphoto(entity.person);
            }else{
                 MsgBox.err("Leave Ledger for " + entity.person.name + " already exist.");
                 entity.person = [:]
                 binding.refresh();
            }
        }
        
    ]
    
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
        loadpersonalinfo()
        loadleave()
        leaveListHandler.reload();
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
        if (MsgBox.confirm('you are about to capture an unposted transaction?')){
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
}

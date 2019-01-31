
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat

class LeaveCaptureCRUDController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def caller
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("TagabukidHRMISLeaveCaptureService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    def tag
    def selectedAppointmentItem

    def duration = [
        'FULL',
        'HALFAM',
        'HALFPM'
    ]
    
    def time = [
        '1:00:00',
        '2:00:00',
        '3:00:00',
        '4:00:00',
        '5:00:00',
        '6:00:00',
        '7:00:00',
        '8:00:00',
        '9:00:00',
        '10:00:00',
        '11:00:00',
        '12:00:00',
        '13:00:00',
        '14:00:00',
        '15:00:00',
        '16:00:00',
        '17:00:00',
        '18:00:00',
        '19:00:00',
        '20:00:00',
        '21:00:00',
        '22:00:00',
        '23:00:00',
        '00:00:00'
    ]
    boolean isAllowApprove() {
        return false; 
    }
    
    boolean isAllowPreviewAppointment() {
        return false; 
    }
    
    boolean isEditAllowed() {
        return false; 
    }

    boolean isViewReportAllowed(){
        return false
    }
    
    boolean isUndoAllowed(){
        return false
    }
    
    boolean isPrintReportAllowed(){
        return false
    }

    public void afterCreate(){
        entity = svc.initCreate();
        entity.pds = caller.entity
    }
    public void afterSave(){
        caller?.reloadEntity();
    }

    def leaveDetailHandler = [
        fetchList: { 
            return entity?.leavedetails 
        },
        createItem : {
            return[
                objid : 'LD' + new java.rmi.server.UID(),
                status: "DRAFT"
            ]
        },
//        onRemoveItem : {
//            if (entity.state != 'DRAFT'){
//                MsgBox.alert("Delete is not allowed.")
//                return false
//            }else if (MsgBox.confirm('Delete item?')){                
//                entity.leavedetails.remove(it)
//                persistenceSvc.removeEntity([_schemaname:'hrmis_leaveitems',objid:it.objid])
//                leaveDetailHandler?.load();
//                return true;
//            }
//            return false;
//        },
//        onColumnUpdate: { o,col-> 
//            if(col == 'plantilla'){
//                o.salaryscheduleitem  = svc.getDailyWageByTranch(entity.currentsalarystep,o.plantilla);
//                o.monthlywage = o.salaryscheduleitem.amount
//                o.dailywage = o.salaryscheduleitem.amount / 22
//            }
//            
//            if(col == 'cutoffdate'){
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
////            println x
//            if (x.cutoffdate && !x.cutoffreason.objid){
//                throw new Exception("Cut-off Reason is required.");
//            }
//        },
        onAddItem : {
//            it.plantilla.Id = it.plantilla.Id.toString()
//            it.monthlywage = it.dailywage * 22
            entity.leavedetails.add(it);
        }
//        ,
//        validate:{li->
//            def item=li.item;
//            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
//        }
    ] as EditorListModel

}  
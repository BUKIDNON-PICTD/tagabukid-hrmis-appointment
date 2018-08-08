import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
class  ServiceRecordController extends CrudFormModel {
    @Binding
    def binding;

    def parententity
    def svc
        
    @Service("DateService")
    def dtSvc
    
    @Service("ServiceRecordService")
    def srSvc

    String title = "Service Record";
    
     boolean isCreateAllowed(){
        return false
    }
    
    boolean isViewReportAllowed(){
        return false
    }

    boolean isPrintReportAllowed(){
        return false
    }
    
    boolean isShowNavigation(){
        return false
    }
    
    def reportdisplay = ['HOURLY', 'DAILY', 'WEEKLY', 'MONTHLY', 'ANNUALLY', 'HONORARIUM', 'NA'];

    def selectedserviceRecordItem
    public void beforeOpen() {
       entity.putAll(parententity);
    }
    public void beforeSave(o){
        if(o=='create'){
//            entity.skills{
//                
//            }
           
        }
    }
    
    def serviceRecordItemHandler = [
        fetchList: { entity?.servicerecordInfos.sort{it.datefrom} },
        createItem : {
           return[
               recordlog : [
                    datecreated : dtSvc.getServerDate(),
                    createdbyuser : OsirisContext.env.FULLNAME,
                    createdbyuserid : OsirisContext.env.USERID,
                    dateoflastupdate : dtSvc.getServerDate(),
                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
                    lastupdatedbyuserid : OsirisContext.env.USERID,
                ],
           ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                entity.servicerecordInfos.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_servicerecords',objid:it.objid])
                serviceRecordItemHandler?.load();
                return true;
            }
            return false;
        },
       onColumnUpdate: { o,col->
//            if(col == 'paygradestepincrement'){
//                o.salaryscheduleitem  = srSvc.getDailyWageByTranch(o.tranche,o.paygradestepincrement);
//                o.wage = o.salaryscheduleitem.amount
//                //o.wage = o.salaryscheduleitem.amount / 22
//            }

            if(col == 'compensationtype'){
                if (o.compensationtype.name == 'HOURLY'){
                    o.monthlysalary = o.wage * 8 * 22
                }
                if (o.compensationtype.name == 'DAILY'){
                    o.monthlysalary = o.wage * 22
                }
                if (o.compensationtype.name == 'WEEKLY'){
                    o.monthlysalary = (o.wage * 22) / 4
                }
                if (o.compensationtype.name == 'MONTHLY' || o.compensationtype.name == 'HONORARIUM' || o.compensationtype.name == 'NA'){
                    o.monthlysalary = o.wage
                }
                if (o.compensationtype.name == 'ANNUALLY'){
                    o.monthlysalary = o.wage / 12
                }

            }
            o.recordlog.dateoflastupdate = dtSvc.getServerDate();
            o.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            o.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
       },
        onAddItem : {
            entity.servicerecordInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
    
    def print() {
        def op = Inv.lookupOpener( "test:servicerecord", [entity: entity] );
        op.target = 'self';
        return op;
    }

}
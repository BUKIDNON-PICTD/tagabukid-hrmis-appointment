import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
class  PDSEducationalInfoController extends CrudFormModel {
    @Binding
    def binding;

    def parententity
    def svc
        
    @Service("DateService")
    def dtSvc

    String title = "Education";
    
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

    def selectedEducationalInfo
    public void beforeOpen() {
       entity.putAll(parententity);
    }
    
    public void afterOpen(){
        entity.educationalInfos.each{
            it.school = persistenceSvc.read( [_schemaname:'master_tblinstitution', objid:it.school.objid]);
            
        }
    }
    // public void beforeSave(o){
    //     entity.educationalInfos.each{
    //         it.school._schemaname='master_tblinstitution'
    //         it.school.address.addressdetails = it.address.addressdetails
    //         persistenceSvc.update(it.school)
    //     }
    // }

    def educationalBackgroundItemListHandler = [
        fetchList: { entity?.educationalInfos.sort{it.yeargraduated} },
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
                entity.educationinfoitems.remove(it)
                educationalBackgroundItemListHandler?.load();
                return true;
            }
            return false;
        },
       onColumnUpdate: { o,col-> 
            o.recordlog.dateoflastupdate = dtSvc.getServerDate();
            o.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            o.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
       },
        onAddItem : {
            entity.educationalInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel

    def getSchoolNameLookupHandler(){
        return Inv.lookupOpener('lookup:tagabukid_hrmis_institution',[
                onselect :{
                    selectedEducationalInfo.school = persistenceSvc.read( [_schemaname:'master_tblinstitution', objid:it.objid] );
                }
            ]);
    }
}
import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
        
class  PDSFamilyInfoController extends CrudFormModel {
    @Binding
    def binding;
    
    def parententity
    def svc

    @Service('DateService')
    def dtSvc
    
    @Service("PersistenceService")
    def persistenceSvc;

    String title = "Family Background";
    
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
   
    def selectedfatherInfo
    def selectedmotherInfo
    def selectedspouseInfo
    def selectedchildInfo

    public void beforeOpen() {
        entity.putAll(parententity);
    }
    public void afterOpen(){
        entity.familyfatherInfos.each{
            it.father = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.father.objid]);
        }
        entity.familymotherInfos.each{
            it.mother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.mother.objid]);
        }
        entity.familyspouseInfos.each{
            it.spouse = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spouse.objid]);
        }
        entity.familychildInfos.each{
            it.child = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.child.objid]);
            
        }
    }
    public void beforeSave(o){
        entity.familyfatherInfos[0].father._schemaname='entityindividual'
        entity.familymotherInfos[0].mother._schemaname='entityindividual'
        entity.familyspouseInfos[0].spouse._schemaname='entityindividual'
        persistenceSvc.update(entity.familyfatherInfos[0].father)
        persistenceSvc.update(entity.familymotherInfos[0].mother)
        persistenceSvc.update(entity.familyspouseInfos[0].spouse)
        entity.familychildInfos.each{
            it.child._schemaname='entityindividual'
            it.child.bithdate = it.birthdate
            persistenceSvc.update(it.child)
        }
    }
    
    public void afterSave(){
       
        
    }
    def familyBackgroundspousenameHandler = [
        fetchList: { entity?.familyspouseInfos },
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
                entity.familyspouseInfos.remove(it)
                familyBackgroundspousenameHandler?.load();
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
            entity.familyspouseInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel


    def familyBackgroundfathernameHandler = [
        fetchList: { entity?.familyfatherInfos },
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
                entity.familyfatherInfos.remove(it)
                familyBackgroundfathernameHandler?.load();
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
            entity.familyfatherInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel

    def familyBackgroundmothernameHandler = [
        fetchList: { entity?.familymotherInfos },
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
                entity.familymotherInfos.remove(it)
                familyBackgroundmothernameHandler?.load();
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
            entity.familymotherInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel

    def familyBackgroundchildnameHandler = [
        fetchList: { entity?.familychildInfos },
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
                entity.familychildInfos.remove(it)
                familyBackgroundchildnameHandler?.load();
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
            entity.familychildInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
    

    def getFatherLookupHandler(){
        return Inv.lookupOpener('lookup:individualwide',[
                onselect :{
                    selectedfatherInfo.father = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
                    //                    selectedfatherInfo.father.nameextension = ""
                    //                     binding.refresh();
                }
            ]);
    }
    def getMotherLookupHandler(){
        return Inv.lookupOpener('lookup:individualwide',[
                onselect :{
                    selectedmotherInfo.mother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
                    //                    selectedmotherInfo.mother.maidenname = ""
                    //                     binding.refresh();
                }
            ]);
    }
    def getSpouseLookupHandler(){
        return Inv.lookupOpener('lookup:individualwide',[
                onselect :{
                    selectedspouseInfo.spouse = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
                    //                     binding.refresh();
                }
            ]);
    }
    
    def getChildLookupHandler(){
        return Inv.lookupOpener('lookup:individualwide',[
                onselect :{
                    selectedchildInfo.child = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
                    selectedchildInfo.birthdate = selectedchildInfo.child.birthdate
                    //                     binding.refresh();
                }
            ]);
    }
}
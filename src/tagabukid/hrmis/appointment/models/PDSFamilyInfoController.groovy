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

    // addt'l for consanguinity / affinity
    def selectedApplicantMOTHERsidegrandFATHERItem
    def selectedApplicantMOTHERsidegrandMOTHERItem
    def selectedApplicantFATHERsidegrandFATHERItem
    def selectedApplicantFATHERsidegrandMOTHERItem
    def selectedSpouseFatherItem
    def selectedSpouseMotherItem
    def selectedSpouseMOTHERsidegrandFATHERItem
    def selectedSpouseMOTHERsidegrandMOTHERItem
    def selectedSpouseFATHERsidegrandFATHERItem
    def selectedSpouseFATHERsidegrandMOTHERItem


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
        // addt'l family
        entity.fatherGrandmother.each{
            it.fathersidegrandmother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.fathersidegrandmother.objid]);
            
        }
        entity.motherGrandfather.each{
            it.mothersidegrandfather = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.mothersidegrandfather.objid]);
            
        }
        entity.fatherGrandfather.each{
            it.fathersidegrandfather = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.fathersidegrandfather.objid]);
            
        }
        entity.motherGrandmother.each{
            it.mothersidegrandmother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.mothersidegrandmother.objid]);
            
        }
        entity.spouseFather.each{
            it.spousefather = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spousefather.objid]);
            
        }
        entity.spouseMother.each{
            it.spousemother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spousemother.objid]);
            
        }
        entity.spouseFatherGrandmother.each{
            it.spousefathersidegrandmother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spousefathersidegrandmother.objid]);
            
        }
        entity.spouseFatherGrandfather.each{
            it.spousefathersidegrandfather = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spousefathersidegrandfather.objid]);
            
        }
        entity.spouseMotherGrandfather.each{
            it.spousemothersidegrandfather = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spousemothersidegrandfather.objid]);
            
        }
        entity.spouseMotherGrandmother.each{
            it.spousemothersidegrandmother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.spousemothersidegrandmother.objid]);
            
        }


    }
    public void beforeSave(o){
       
        if (entity.familyfatherInfos.size > 0){
            entity.familyfatherInfos[0].father._schemaname='entityindividual'
            persistenceSvc.update(entity.familyfatherInfos[0].father)
        }
        if (entity.familymotherInfos.size > 0){
            entity.familymotherInfos[0].mother._schemaname='entityindividual'
            persistenceSvc.update(entity.familymotherInfos[0].mother)
        }
        if (entity.familyspouseInfos.size > 0){
            entity.familyspouseInfos[0].spouse._schemaname='entityindividual'
            persistenceSvc.update(entity.familyspouseInfos[0].spouse)
        }

        // addt'l family
        if (entity.fatherGrandmother.size > 0){
            entity.fatherGrandmother[0].fathersidegrandmother._schemaname='entityindividual'
            persistenceSvc.update(entity.fatherGrandmother[0].fathersidegrandmother)
        }
        if (entity.motherGrandfather.size > 0){
            entity.motherGrandfather[0].mothersidegrandfather._schemaname='entityindividual'
            persistenceSvc.update(entity.motherGrandfather[0].mothersidegrandfather)
        }
        if (entity.fatherGrandfather.size > 0){
            entity.fatherGrandfather[0].fathersidegrandfather._schemaname='entityindividual'
            persistenceSvc.update(entity.fatherGrandfather[0].fathersidegrandfather)
        }
        if (entity.motherGrandmother.size > 0){
            entity.motherGrandmother[0].mothersidegrandmother._schemaname='entityindividual'
            persistenceSvc.update(entity.motherGrandmother[0].mothersidegrandmother)
        }
        if (entity.spouseFather.size > 0){
            entity.spouseFather[0].spousefather._schemaname='entityindividual'
            persistenceSvc.update(entity.spouseFather[0].spousefather)
        }
        if (entity.spouseMother.size > 0){
            entity.spouseMother[0].spousemother._schemaname='entityindividual'
            persistenceSvc.update(entity.spouseMother[0].spousemother)
        }
        if (entity.spouseFatherGrandmother.size > 0){
            entity.spouseFatherGrandmother[0].spousefathersidegrandmother._schemaname='entityindividual'
            persistenceSvc.update(entity.spouseFatherGrandmother[0].spousefathersidegrandmother)
        }
        if (entity.spouseFatherGrandfather.size > 0){
            entity.spouseFatherGrandfather[0].spousefathersidegrandfather._schemaname='entityindividual'
            persistenceSvc.update(entity.spouseFatherGrandfather[0].spousefathersidegrandfather)
        }
        if (entity.spouseMotherGrandfather.size > 0){
            entity.spouseMotherGrandfather[0].spousemothersidegrandfather._schemaname='entityindividual'
            persistenceSvc.update(entity.spouseMotherGrandfather[0].spousemothersidegrandfather)
        }
        if (entity.spouseMotherGrandmother.size > 0){
            entity.spouseMotherGrandmother[0].spousemothersidegrandmother._schemaname='entityindividual'
            persistenceSvc.update(entity.spouseMotherGrandmother[0].spousemothersidegrandmother)
        }

        entity.familychildInfos.each{
            it.child._schemaname='entityindividual'
            it.child.birthdate = it.birthdate
            persistenceSvc.update(it.child)
        }
        println entity
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
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_familybackground_spousename',objid:it.objid])
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
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_familybackground_fathername',objid:it.objid])
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
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_familybackground_mothername',objid:it.objid])
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
        fetchList: { entity?.familychildInfos.sort{it.child.birthdate} },
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
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_familybackground_children',objid:it.objid])
                familyBackgroundchildnameHandler?.load();
                return true;
            }
            return false;
        },
        onColumnUpdate: { o,col-> 
            o.recordlog.dateoflastupdate = dtSvc.getServerDate();
            o.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
            o.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
            
            if (col == 'businessadd') { 
                if(o.businessadd.type == 'local'){
                    o.businessadd.text = svc.formatAddress(o.businessadd,"\n")
                }
            } 
        },
        onAddItem : {
            entity.familychildInfos.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel
    
    def getBusinessAddressLookup(){
        if(!selectedspouseInfo.businessadd?.objid) {
            def h = { o->
                selectedspouseInfo.businessadd = o;
            };
            def m = selectedspouseInfo.businessadd;
            if(!m) m = [:];
            return Inv.lookupOpener( "address:editor", [handler:h, entity:m] );
        }
        else {
            def h = { o->
                o._schemaname = "entity_address";
                persistenceSvc.update( o );
                selectedspouseInfo.businessadd = o;
            };
            def m = persistenceSvc.read( [_schemaname:'entity_address', objid:selectedspouseInfo.businessadd.objid] );
            return Inv.lookupOpener( "address:editor", [handler:h, entity:m] );
            //binding.refresh();
           
        }
        
    }

    def applicantMOTHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.motherGrandfather },
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
                entity.motherGrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_motherside_grandfather',objid:it.objid])
                applicantMOTHERsidegrandFATHERItemHandler?.load();
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
            entity.motherGrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel


    def applicantMOTHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.motherGrandmother },
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
                entity.motherGrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_motherside_grandmother',objid:it.objid])
                applicantMOTHERsidegrandMOTHERItemHandler?.load();
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
            entity.motherGrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel



    def applicantFATHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.fatherGrandfather },
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
                entity.fatherGrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_fatherside_grandfather',objid:it.objid])
                applicantFATHERsidegrandFATHERItemHandler?.load();
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
            entity.fatherGrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def applicantFATHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.fatherGrandmother },
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
                entity.fatherGrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_fatherside_grandmother',objid:it.objid])
                applicantFATHERsidegrandMOTHERItemHandler?.load();
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
            entity.fatherGrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    
    def spouseFatherItemHandler = [
        fetchList: { entity?.spouseFather },
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
                entity.spouseFather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_spouse_father',objid:it.objid])
                spouseFatherItemHandler?.load();
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
            entity.spouseFather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    
    def spouseMotherItemHandler = [
        fetchList: { entity?.spouseMother },
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
                entity.spouseMother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_spouse_mother',objid:it.objid])
                spouseMotherItemHandler?.load();
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
            entity.spouseMother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseMOTHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.spouseMotherGrandfather },
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
                entity.spouseMotherGrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_spouse_motherside_grandfather',objid:it.objid])
                spouseMOTHERsidegrandFATHERItemHandler?.load();
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
            entity.spouseMotherGrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseMOTHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.spouseMotherGrandmother },
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
                entity.spouseMotherGrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_spouse_motherside_grandmother',objid:it.objid])
                spouseMOTHERsidegrandMOTHERItemHandler?.load();
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
            entity.spouseMotherGrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseFATHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.spouseFatherGrandmother },
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
                entity.spouseFatherGrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_spouse_fatherside_grandmother',objid:it.objid])
                spouseFATHERsidegrandMOTHERItemHandler?.load();
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
            entity.spouseFatherGrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseFATHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.spouseFatherGrandfather },
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
                entity.spouseFatherGrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_pds_family_spouse_fatherside_grandfather',objid:it.objid])
                spouseFATHERsidegrandFATHERItemHandler?.load();
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
            entity.spouseFatherGrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    
//
//    def getFatherLookupHandler(){
//        return Inv.lookupOpener('lookup:individualwide',[
//                onselect :{
//                    selectedfatherInfo.father = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
//                    //                    selectedfatherInfo.father.nameextension = ""
//                    //                     binding.refresh();
//                }
//            ]);
//    }
//    def getMotherLookupHandler(){
//        return Inv.lookupOpener('lookup:individualwide',[
//                onselect :{
//                    selectedmotherInfo.mother = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
//                    //                    selectedmotherInfo.mother.maidenname = ""
//                    //                     binding.refresh();
//                }
//            ]);
//    }
//    def getSpouseLookupHandler(){
//        return Inv.lookupOpener('lookup:individualwide',[
//                onselect :{
//                    selectedspouseInfo.spouse = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
//                    //                     binding.refresh();
//                }
//            ]);
//    }
//    
//    def getChildLookupHandler(){
//        return Inv.lookupOpener('lookup:individualwide',[
//                onselect :{
//                    selectedchildInfo.child = persistenceSvc.read( [_schemaname:'entityindividual', objid:it.objid] );
//                    selectedchildInfo.birthdate = selectedchildInfo.child.birthdate
//                    //                     binding.refresh();
//                }
//            ]);
//    }
}
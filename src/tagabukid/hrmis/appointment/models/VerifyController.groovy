import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class VerifyController extends CrudFormModel {

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc

    @Service
    def persistenceSvc

    @Service('TagabukidHRMISVerificationService')
    def svc
    
    def parententity

    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
    }

       
    def selectedfatherItem
    def selectedmotherItem
    def selectedspouseItem

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


    def verify(){
       def op = Inv.lookupOpener("verify:report",[entity: entity]);
       op.target = 'self';
       return op;
    }

    public void afterCreate(){
        // title = "Applicant Verification";
        entity = svc.initCreate();        
    }

    public void beforeSave(o){
       // Father Side
        if (entity.father.size > 0){
            // entity.father[0]._schemaname='entityindividual'
            // persistenceSvc.update(entity.father[0])
        }
        
        if (entity.fathersidegrandfather.size > 0){
            // entity.fathersidegrandfather[0]._schemaname='entityindividual'
            // persistenceSvc.update(entity.father[0])
        }
        if (entity.fathersidegrandmother.size > 0){
            // entity.fathersidegrandmother[0]._schemaname='entityindividual'
            // persistenceSvc.update(entity.father[0])
        }

        // Mother Side
        if (entity.mother.size > 0){
            // entity.mother[0]._schemaname='entityindividual'
            // persistenceSvc.update(entity.mother[0])
        }
        if (entity.mothersidegrandfather.size > 0){
            // entity.mothersidegrandfather[0]._schemaname='entityindividual'
            // persistenceSvc.update(entity.father[0])
        }
        if (entity.mothersidegrandmother.size > 0){
            // entity.mothersidegrandmother[0]._schemaname='entityindividual'
            // persistenceSvc.update(entity.father[0])
        }


        entity.state = "DRAFT";
        if(o == 'create'){
            entity.person_name = entity.person.lastname + ", " + entity.person.firstname + " " + entity.person.middlename;
            entity.itemno = svc.getApplicantNo();
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;  
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;

        }else{
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
        }
    }


    def familyBackgroundfathernameHandler = [
        fetchList: { entity?.father },
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
                entity.father.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_father',objid:it.objid])
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
            entity.father.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel


    def applicantFATHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.fathersidegrandfather },
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
                entity.fathersidegrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_fatherside_grandfather',objid:it.objid])
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
            entity.fathersidegrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel
    

    def applicantFATHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.fathersidegrandmother },
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
                entity.fathersidegrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_fatherside_grandmother',objid:it.objid])
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
            entity.fathersidegrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel    


    def familyBackgroundmothernameHandler = [
        fetchList: { entity?.mother },
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
                entity.mother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_mother',objid:it.objid])
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
            entity.mother.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel

    def applicantMOTHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.mothersidegrandfather },
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
                entity.mothersidegrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_motherside_grandfather',objid:it.objid])
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
            entity.mothersidegrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel


    def applicantMOTHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.mothersidegrandmother },
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
                entity.mothersidegrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_motherside_grandfather',objid:it.objid])
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
            entity.mothersidegrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    def familyBackgroundspousenameHandler = [
        fetchList: { entity?.spouse },
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
                entity.spouse.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse',objid:it.objid])
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
            entity.spouse.add(it);
        },
        validate:{li->
            //def item=li.item;
            //checkDuplicateIPCR(selectedDPCR.ipcrlist,item);
        }
    ] as EditorListModel


    def spouseFatherItemHandler = [
        fetchList: { entity?.spousefather },
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
                entity.spousefather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse_father',objid:it.objid])
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
            entity.spousefather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    
    def spouseMotherItemHandler = [
        fetchList: { entity?.spousemother },
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
                entity.spousemother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse_mother',objid:it.objid])
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
            entity.spousemother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseMOTHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.spousemothersidegrandfather },
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
                entity.spousemothersidegrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse_motherside_grandfather',objid:it.objid])
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
            entity.spousemothersidegrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseMOTHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.spousemothersidegrandmother },
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
                entity.spousemothersidegrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse_motherside_grandmother',objid:it.objid])
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
            entity.spousemothersidegrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseFATHERsidegrandMOTHERItemHandler = [
        fetchList: { entity?.spousefathersidegrandmother },
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
                entity.spousefathersidegrandmother.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse_fatherside_grandmother',objid:it.objid])
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
            entity.spousefathersidegrandmother.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel

    

    def spouseFATHERsidegrandFATHERItemHandler = [
        fetchList: { entity?.spousefathersidegrandfather },
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
                entity.spousefathersidegrandfather.remove(it)
                persistenceSvc.removeEntity([_schemaname:'hrmis_verify_spouse_fatherside_grandfather',objid:it.objid])
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
            entity.spousefathersidegrandfather.add(it);
        },
        validate:{li->
        }
    ] as EditorListModel


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
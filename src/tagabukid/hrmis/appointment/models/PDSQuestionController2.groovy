import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
/**
 *
 * @author P0RA
 */
class PDSQuestionController extends CrudFormModel {
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def caller
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "PDS QUESTIONS";
    def parententity
    def svc
    
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
    public void beforeOpen() {
        entity.putAll(parententity);
    }
    
    def suggestCountry = [
        fetchList: { o->
            return svc.getList(o).name;
        }
    ] as SuggestModel;
    
    public void beforeSave(o){
//        def pdsqs = [
//            thirddegree             : entity.thirddegree,                    
//            fourthdegree            : entity.fourthdegree,                   
//            degreedetails           : entity.degreedetails,                  
//            offense                 : entity.offense,                        
//            offensedetails          : entity.offensedetails,                 
//            criminalcharge          : entity.criminalcharge,                 
//            criminalchargedate      : entity.criminalchargedate,
//            criminalchargestatus    : entity.criminalchargestatus,           
//            convicted               : entity.convicted,                      
//            convictiondetails       : entity.convictiondetails,              
//            separated               : entity.separated,                      
//            separationdetails       : entity.separationdetails,              
//            candidate               : entity.candidate,                      
//            candidacydetails        : entity.candidacydetails,               
//            resigncampaign          : entity.resigncampaign,                 
//            resigncampaigndetails   : entity.resigncampaigndetails,          
//            immigrant               : entity.immigrant,                      
//            immigrantcountry        : entity.immigrantcountry,               
//            indigenousgroup         : entity.indigenousgroup,                
//            indigenousgroupdetails  : entity.indigenousgroupdetails,         
//            pwd                     : entity.pwd,                            
//            pwdidno                 : entity.pwdidno,                        
//            soloparent              : entity.soloparent,                     
//            soloparentidno          : entity.soloparentidno, 
//            recordlog : [
//                datecreated         : dtSvc.getServerDate(),
//                createdbyuserid     : OsirisContext.env.FULLNAME,
//                createdbyuser       : OsirisContext.env.USERID,
//                dateoflastupdate    : dtSvc.getServerDate(),
//                lastupdatedbyuserid : OsirisContext.env.FULLNAME,
//                lastupdatedbyuser   : OsirisContext.env.USERID,
//            ],
//        ]
//        entity.pdsqs = []
//        entity.qustion
//        entity.pdsqs.add(pdsqs)
        
        if(entity.pdsqs.size()<=0)
        {
//            def govids = [
//                idtype: entity.idtype,
//                idno  : entity.idno,
//                issuance : entity.issuance,
//                recordlog : [
//                    datecreated : dtSvc.getServerDate(),
//                    createdbyuser : OsirisContext.env.FULLNAME,
//                    createdbyuserid : OsirisContext.env.USERID,  
//                    dateoflastupdate : dtSvc.getServerDate(),
//                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
//                    lastupdatedbyuserid : OsirisContext.env.USERID 
//                ]
//            ]
            entity.question.recordlog = [
                    datecreated : dtSvc.getServerDate(),
                    createdbyuser : OsirisContext.env.FULLNAME,
                    createdbyuserid : OsirisContext.env.USERID,  
                    dateoflastupdate : dtSvc.getServerDate(),
                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
                    lastupdatedbyuserid : OsirisContext.env.USERID 
                ]
            entity.pdsqs << entity.question
        }else{
//            def govids = [
//                objid : entity.govid,
//                idtype: entity.idtype,
//                idno  : entity.idno,
//                issuance : entity.issuance,
//                recordlog : [
//                    dateoflastupdate : dtSvc.getServerDate(),
//                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
//                    lastupdatedbyuserid : OsirisContext.env.USERID 
//                ]
//            ]
            entity.question.recordlog
            entity.question.recordlog = [
                    dateoflastupdate : dtSvc.getServerDate(),
                    lastupdatedbyuser : OsirisContext.env.FULLNAME,
                    lastupdatedbyuserid : OsirisContext.env.USERID 
                ]
            entity.pdsqs << entity.question
        }
    }
    public void afterSave(){
        entity.question = [:]
        entity.question.putAll(entity.pdsqs[0]);
        //        entity.thirddegree=entity.pdsqs[0].thirddegree
        //        entity.fourthdegree=entity.pdsqs[0].fourthdegree
        //        entity.degreedetails=entity.pdsqs[0].degreedetails
        //        entity.offense=entity.pdsqs[0].offense
        //        entity.offensedetails=entity.pdsqs[0].offensedetails
        //        entity.criminalcharge=entity.pdsqs[0].criminalcharge
        //        entity.criminalchargedate=entity.pdsqs[0].criminalchargedate
        //        entity.criminalchargestatus=entity.pdsqs[0].criminalchargestatus
        //        entity.convicted=entity.pdsqs[0].convicted
        //        entity.convictiondetails=entity.pdsqs[0].convictiondetails
        //        entity.separated=entity.pdsqs[0].separated
        //        entity.separationdetails=entity.pdsqs[0].separationdetails
        //        entity.candidate=entity.pdsqs[0].candidate
        //        entity.candidacydetails=entity.pdsqs[0].candidacydetails
        //        entity.resigncampaign=entity.pdsqs[0].resigncampaign
        //        entity.resigncampaigndetails=entity.pdsqs[0].resigncampaigndetails
        //        entity.immigrant=entity.pdsqs[0].immigrant
        //        entity.immigrantcountry=entity.pdsqs[0].immigrantcountry
        //        entity.indigenousgroup=entity.pdsqs[0].indigenousgroup
        //        entity.indigenousgroupdetails=entity.pdsqs[0].indigenousgroupdetails
        //        entity.pwd=entity.pdsqs[0].pwd
        //        entity.pwdido=entity.pdsqs[0].pwdidno
        //        entity.soloparent=entity.pdsqs[0].soloparent
        //        entity.soloparentidno=entity.pdsqs[0].soloparentidno
    }
    
    public void afterOpen() {
        entity.question = [:]
        if(entity.pdsqs){
            entity.question.putAll(entity.pdsqs[0]);
            //            binding.refresh();
            // entity.thirddegree=entity.pdsqs[0].thirddegree
            // entity.fourthdegree=entity.pdsqs[0].fourthdegree
            // entity.degreedetails=entity.pdsqs[0].degreedetails
            // entity.offense=entity.pdsqs[0].offense
            // entity.offensedetails=entity.pdsqs[0].offensedetails
            // entity.criminalcharge=entity.pdsqs[0].criminalcharge
            // entity.criminalchargedate=entity.pdsqs[0].criminalchargedate
            // entity.criminalchargestatus=entity.pdsqs[0].criminalchargestatus
            // entity.convicted=entity.pdsqs[0].convicted
            // entity.convictiondetails=entity.pdsqs[0].convictiondetails
            // entity.separated=entity.pdsqs[0].separated
            // entity.separationdetails=entity.pdsqs[0].separationdetails
            // entity.candidate=entity.pdsqs[0].candidate
            // entity.candidacydetails=entity.pdsqs[0].candidacydetails
            // entity.resigncampaign=entity.pdsqs[0].resigncampaign
            // entity.resigncampaigndetails=entity.pdsqs[0].resigncampaigndetails
            // entity.immigrant=entity.pdsqs[0].immigrant
            // entity.immigrantcountry=entity.pdsqs[0].immigrantcountry
            // entity.indigenousgroup=entity.pdsqs[0].indigenousgroup
            // entity.indigenousgroupdetails=entity.pdsqs[0].indigenousgroupdetails
            // entity.pwd=entity.pdsqs[0].pwd
            // entity.pwdidno=entity.pdsqs[0].pwdidno
            // entity.soloparent=entity.pdsqs[0].soloparent
            // entity.soloparentidno=entity.pdsqs[0].soloparentidno            
        }
        // else{
        //         entity.thirddegree = 0
        //         entity.fourthdegree = 0                
        //         entity.offense = 0                
        //         entity.criminalcharge = 0
        //         entity.convicted = 0                
        //         entity.separated = 0                
        //         entity.candidate = 0                
        //         entity.resigncampaign = 0                
        //         entity.immigrant = 0                
        //         entity.indigenousgroup = 0                
        //         entity.pwd = 0                
        //         entity.soloparent = 0
                
        //     }
    }
}
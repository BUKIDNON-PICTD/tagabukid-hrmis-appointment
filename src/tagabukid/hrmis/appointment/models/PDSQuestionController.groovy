import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.annotations.Env
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
    
    @Env
    def env
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    String title = "PDS QUESTIONS";
    def parententity
    def svc
    
    def selectedPDSQuestion
      
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
    
//    def suggestCountry = [
//        fetchList: { o->
//            return svc.getList(o).name;
//        }
//    ] as SuggestModel;
//    
  
    
    public void beforeSave(o){
        // println entity.pdsquestions
    }
    public void afterSave(){
        // entity.question = [:]
        // entity.question.putAll(entity.pdsqs[0]);
    }
    
    public void afterOpen() {
       entity.pdsq = svc.loadQuestions();
       println entity.pdsquestions
        if(!entity.pdsquestions){
            entity.pdsquestions = []
            entity.pdsq.each{
               def newpdsquestion = [
                    pdsq : it,
                    recordlog : [
                        datecreated : dtSvc.getServerDate(),
                        createdbyuser : OsirisContext.env.FULLNAME,
                        createdbyuserid : OsirisContext.env.USERID,  
                        dateoflastupdate : dtSvc.getServerDate(),
                        lastupdatedbyuser : OsirisContext.env.FULLNAME,
                        lastupdatedbyuserid : OsirisContext.env.USERID 
                    ]
               ]
               entity.pdsquestions.add(newpdsquestion)
            }
        }
        // pdsqustionListHandler.reload();
    }
    
    def pdsqustionListHandler = [
        fetchList: { entity.pdsquestions },
        // createItem : {
        //     return[
        //         recordlog : [
        //             datecreated : dtSvc.getServerDate(),
        //             createdbyuser : OsirisContext.env.FULLNAME,
        //             createdbyuserid : OsirisContext.env.USERID,
        //             dateoflastupdate : dtSvc.getServerDate(),
        //             lastupdatedbyuser : OsirisContext.env.FULLNAME,
        //             lastupdatedbyuserid : OsirisContext.env.USERID,
        //         ],
        //     ]
        // },
        onRemoveItem : {
            // if (MsgBox.confirm('Delete item?')){                
            //     entity.pdsquestions.remove(it)
            //     pdsqustionListHandler?.load();
            //     return true;
            // }
            return false;
        }
        // onColumnUpdate: { o,col-> 
        //     if (o.objid){
        //         o.datecreated = dtSvc.getServerDate(),
        //         o.createdbyuser = OsirisContext.env.FULLNAME,
        //         o.createdbyuserid = OsirisContext.env.USERID,
        //         o.dateoflastupdate = dtSvc.getServerDate(),
        //         o.lastupdatedbyuser = OsirisContext.env.FULLNAME,
        //         o.lastupdatedbyuserid = OsirisContext.env.USERID,
        //     }else{
        //         o.recordlog.dateoflastupdate = dtSvc.getServerDate();
        //         o.recordlog.lastupdatedbyuser = OsirisContext.env.FULLNAME;
        //         o.recordlog.lastupdatedbyuserid = OsirisContext.env.USERID;
        //     }
            
            
            
        // },
        // onAddItem : {
        //     throw new Exception('You are not allowed to add a question. Please contact PICTD.');
        //     // entity.pdsquestions.add(it);
        // },
        // validate:{li->
        // }
    ] as EditorListModel
}

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import java.text.SimpleDateFormat

class LeaveCardCaptureCRUDController {
    @Binding
    def binding;
    
    @Invoker
    def invoker;
    
    @Service('DateService')
    def dtSvc
    
    @Caller
    def caller
    
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("TagabukidHRMISLeaveCardPostService")
    def svc
    
    @Service('TagabukidLookupService')
    def tgbkdSvc
    
    @FormTitle
    def title
    
    def tag
    def entity
    
    public void create(){
        tag = invoker?.properties?.tag;
        entity.dr = 0.000;
        entity.cr = 0.000;
        title = entity.title
    }
    public def save(){
        if (MsgBox.confirm('You are about to post transaction?')){    
            if ((tag == 'dr' ? svc.postDR(entity): svc.postCR(entity))){
                caller?.reloadEntity();
                return '_close';
            }else{
                return "default";
            }
            
        }
        
    }

  
}  
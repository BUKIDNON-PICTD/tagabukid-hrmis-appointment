package tagabukid.hrmis.appointment.models

import com.rameses.osiris2.common.ExplorerViewController;

public abstract class AccountManagementListController extends ExplorerViewController {
    
    public abstract String getServiceName();
    public abstract Object getHrmisaccountService();
    public abstract String getTitle();
    
    def params = [:]; 
    
//    def types = ['Casual', 'Permanent'];
    public String getPrefixId() {
        return "ACC";
    }

    def selectedItem
    
    public String getDefaultFileType() {
        return "hrmisaccount";
    }
    
    public String getContext() {
        return "hrmisaccount";
    }    

    public void beforeFetchList( Map qry ) { 
        if ( params ) qry.putAll( params ); 
    }
    
    public void beforeNodes(Map qry){

        if ( params ) qry.putAll( params ); 
        
    }

}
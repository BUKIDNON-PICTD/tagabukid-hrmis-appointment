package tagabukid.hrmis.appointment.models

import com.rameses.osiris2.common.ExplorerViewController;

public abstract class AccountTitleManagementListController extends ExplorerViewController {
    
    public abstract String getServiceName();
    public abstract Object getHrmisaccounttitleService();
    public abstract String getTitle();
    
    def params = [:]; 
    
//    def types = ['Casual', 'Permanent'];
    public String getPrefixId() {
        return "ACT";
    }

    def selectedItem
    
    public String getDefaultFileType() {
        return "hrmisaccounttitle";
    }
    
    public String getContext() {
        return "hrmisaccounttitle";
    }    

    public void beforeFetchList( Map qry ) { 
        if ( params ) qry.putAll( params ); 
    }
    
    public void beforeNodes(Map qry){

        if ( params ) qry.putAll( params ); 
        
    }

}
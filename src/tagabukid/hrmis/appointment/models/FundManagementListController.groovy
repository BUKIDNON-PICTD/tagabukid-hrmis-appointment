package tagabukid.hrmis.appointment.models

import com.rameses.osiris2.common.ExplorerViewController;

public abstract class FundManagementListController extends ExplorerViewController {
    
    public abstract String getServiceName();
    public abstract Object getHrmisfundService();
    public abstract String getTitle();
    
    def params = [:]; 
    
//    def types = ['Casual', 'Permanent'];
    public String getPrefixId() {
        return "FUN";
    }
    
    public String getDefaultFileType() {
        return "hrmisfund";
    }
    
    public String getContext() {
        return "hrmisfund";
    }    

    public void beforeFetchList( Map qry ) { 
        if ( params ) qry.putAll( params ); 
    }
    
    public void beforeNodes(Map qry){

        if ( params ) qry.putAll( params ); 
        
    }

}
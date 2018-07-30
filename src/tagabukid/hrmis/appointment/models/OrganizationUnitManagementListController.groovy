package tagabukid.hrmis.appointment.models

import com.rameses.osiris2.common.ExplorerViewController;

public abstract class OrganizationUnitManagementListController extends ExplorerViewController {
    
    public abstract String getServiceName();
    public abstract Object getHrmisorgService();
    public abstract String getTitle();
    
    def params = [:]; 
    
//    def types = ['Casual', 'Permanent'];
    public String getPrefixId() {
        return "ORG";
    }

    def selectedItem
    
    public String getDefaultFileType() {
        return "hrmisorg";
    }
    
    public String getContext() {
        return "hrmisorg";
    }    

    public void beforeFetchList( Map qry ) { 
        if ( params ) qry.putAll( params ); 
    }
    
    public void beforeNodes(Map qry){

        if ( params ) qry.putAll( params ); 
        
    }

}
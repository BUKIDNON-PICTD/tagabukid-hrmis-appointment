package tagabukid.hrmis.appointment.models

import com.rameses.osiris2.common.ExplorerViewController;

public abstract class PlantillaManagementListController extends ExplorerViewController {
    
    public abstract String getServiceName();
    public abstract Object getPmService();
    public abstract String getTitle();
    
    def params = [:]; 

    def tag
    
    def types = ['Casual', 'Permanent'];
    public String getPrefixId() {
        return "PM";
    }
    
    public String getDefaultFileType() {
        return "pm";
    }
    
    public String getContext() {
        return "pm";
    }    
    
//    def getLookupOrg(){
//        return Inv.lookupOpener('pmis:lookuporg',[
//                onselect :{
//                    params.org = it
////                    println params.org
//                },
//        ])
//    }
        
    public void beforeFetchList( Map qry ) { 
        if ( params ) qry.putAll( params ); 
    }
//    
    public void beforeNodes(Map qry){
//        if (!params.searchtext){
//            params.searchtext = '%';
//        }else{
//            params.searchtext = '%';
//        }
        if ( params ) qry.putAll( params ); 
        
    }
//    void sync() {
//        if(! MsgBox.confirm("This will update your current records. Continue? ")) return;
//        getAccountService().syncFromCloud();
//    } 
}
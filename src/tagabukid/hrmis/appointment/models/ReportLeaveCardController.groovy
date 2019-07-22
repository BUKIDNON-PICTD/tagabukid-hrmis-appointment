import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportLeaveCardController extends com.rameses.etracs.shared.ReportController {
    
    @Service("TagabukidHRMISLeaveCardReportService")
    def svc;

    def title = "Leave Card";
    final String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/';
    String reportName = REPORT_PATH + 'leavecard.jasper';
    def data
//    def pagelist
//    def page
//    def hidenoa;
//    def hidenoaheader;    
//    def nothingfollows;

    def initReport() {
//        pagelist = svc.getPages( entity ); 
//        page = (pagelist ? pagelist.first() : null);
//        hidenoa = false;        
//        hidenoaheader = false;
//        nothingfollows = false;

        return preview();
    }
    
    def getReportData() { 
        data = svc.getLeaveCardById(entity);
        return data.reportdata
    } 
    
//    void setPage( o ) {
//        this.page = o; 
//        preview(); 
//        binding.refresh(); 
//    }
//    
//    void setHidenoa(o){
//        this.hidenoa = o;
//        preview();
//        binding.refresh();
//    }
//    
//    void setHidenoaheader(o){
//        this.hidenoaheader = o;
//        preview();
//        binding.refresh();
//    }
    
//    void setNothingfollows(o){
//        this.nothingfollows = o;
//        preview();
//        binding.refresh();
//    }

//    void buildReportData(entity, asyncHandler){
//        svc.getSIByIPCRId(entity, asyncHandler)
//    }

    Map getParameters(){
        return data.parameters;
    }
    
//    SubReport[] getSubReports() {
//        return [ 
//           new SubReport("AppointmentItemList", REPORT_PATH + "appointmentcasualitems.jasper"),           
//           new SubReport("SignatoryGroup", REPORT_PATH + "signatorygroupitems.jasper"),
//
//        ] as SubReport[];    
//    }
} 
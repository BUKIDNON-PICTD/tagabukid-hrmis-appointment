import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportAppointmentCasualController extends ReportController {
    
    @Service("TagabukidHRMISAppointmentCasualReportService")
    def svc;

    def title = "Appointment (Casual)";
    final String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/';
    String reportName = REPORT_PATH + 'appointmentcasual.jasper';
    def data
    
    def getReportData() { 
        entity._schemaname = 'hrmis_appointmentcasual'
        data = svc.getAppointmentCasualById(entity);
        return data.reportdata
    } 

//    void buildReportData(entity, asyncHandler){
//        svc.getSIByIPCRId(entity, asyncHandler)
//    }

    Map getParameters(){
        return data.parameters;
    }
    
    SubReport[] getSubReports() {
        return [ 
           new SubReport("AppointmentItemList", REPORT_PATH + "appointmentcasualitems.jasper"),           
           new SubReport("SignatoryGroup", REPORT_PATH + "signatorygroupitems.jasper"),

        ] as SubReport[];    
    }
} 
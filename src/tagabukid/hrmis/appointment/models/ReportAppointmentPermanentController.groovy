import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportAppointmentPermanentController extends com.rameses.etracs.shared.ReportController {
    
    @Service("TagabukidHRMISAppointmentPermanentReportService")
    def svc;

    def title = "Permanent Appointment";
    final String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/';
    String reportName = REPORT_PATH + 'appointment_permanent.jasper';
    def data

    def getReportData() { 
        data = svc.getAppointmentPermanentById(entity);
        return data.reportdata
    } 

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
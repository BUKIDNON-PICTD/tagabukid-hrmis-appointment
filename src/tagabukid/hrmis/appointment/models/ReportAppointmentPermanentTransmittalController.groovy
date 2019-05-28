import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportAppointmentPermanentTransmittalController extends com.rameses.etracs.shared.ReportController {
    
     @Service("TagabukidHRMISAppointmentPermanentReportService")
    def svc;

    def title = "APPOINTMENT TRANSMITTAL  AND ACTION FORM";
    final String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/';
    String reportName = REPORT_PATH + 'appointmentpermanent_transmittal.jasper';
    def data

    def getReportData() { 
        data = svc.getAppointmentPermanentTransmittalByDateIssued(entity);
        return data.reportdata
    } 

    Map getParameters(){
        return data.parameters;
    }
    SubReport[] getSubReports() {
        return [ 
           new SubReport("AppointmentListItems", REPORT_PATH + "appointmentpermanent_transmittal_items.jasper"),           
        ] as SubReport[];    
    }
} 
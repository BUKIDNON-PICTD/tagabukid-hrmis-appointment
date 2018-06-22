import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportAppointmentJobOrderModel extends ReportController {
    
    @Service("AppointmentJobOrderReportService")
    def svc;

    def title = "Appointment (JO)";
    final String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/';
    String reportName = REPORT_PATH + 'appointmentjoborder.jasper';
    def data
    
    def getReportData() {
        entity._schemaname = 'hrmis_appointmentjoborder'
        data = svc.getAppointmentJobOrderById(entity);
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
           new SubReport("AppointmentItemList", REPORT_PATH + "appointmentjoborderitems.jasper"),           
           new SubReport("SignatoryGroup", REPORT_PATH + "jobordersignatorygroupitems.jasper"),

        ] as SubReport[];    
    }
} 
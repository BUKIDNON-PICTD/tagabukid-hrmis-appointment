import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportEmployeeRetirement extends ReportController {

	@Service("TagabukidHRMISEmployeeRetirementReportService")
    def svc;

    def title = "EMPLOYEES ELIGIBLE FOR OPTIONAL/MANDATORY RETIREMENT";
    final String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/';
    String reportName = REPORT_PATH + 'employee_retirement.jasper';
    def data
    def entity

    def getReportData() {
        data = svc.generateList()         
        return data.reportdata;
    } 

    Map getParameters(){
        return data.parameters;
    }
}
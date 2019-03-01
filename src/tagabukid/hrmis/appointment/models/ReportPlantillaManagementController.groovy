import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportPlantillaManagementController extends ReportController {
    
    @Service("TagabukidHRMISPlantillaManagementReportService")
    def svc;

    String title = 'Plantilla Management Report'
        String REPORT_PATH = 'tagabukid/hrmis/appointment/reports/'
        String reportName = REPORT_PATH + 'plantilla_management_report.jasper';
        def data 
        def entity
        def origin

    def getReportData() {         
            data = svc.getPlantilla(origin) 
            return data.reportdata;
        } 



    Map getParameters(){
        return data.parameters;
    }
    
    SubReport[] getSubReports() {
        return [ 
           // new SubReport("TABLE", REPORT_PATH + "plantilla_management_report_items.jasper"), 
//
//           new SubReport("EDUCATION", REPORT_PATH + "psdreporttest_subreportedu.jasper"),
//
//           new SubReport("CIVILSERVICE", REPORT_PATH + "psdreporttest_subreportcivilservice.jasper"),
//           new SubReport("WORKEXPERIENCE", REPORT_PATH + "psdreporttest_subreportwork.jasper"),
//
//           new SubReport("VOLUNTARYWORK", REPORT_PATH + "psdreporttest_subreportvoluntarywork.jasper"),
//           new SubReport("TRAINING", REPORT_PATH + "psdreporttest_subreporttraining.jasper"),
//           new SubReport("SKILL", REPORT_PATH + "psdreporttest_subreportskills.jasper"),
//           new SubReport("NONACADEMIC", REPORT_PATH + "psdreporttest_subreportnonacademic.jasper"),
//           new SubReport("MEMBERSHIP", REPORT_PATH + "psdreporttest_subreportmembership.jasper"),
//
//           new SubReport("LASTPAGE", REPORT_PATH + "psdreporttest_subreportlastpage.jasper"),
//           new SubReport("REFERENCE", REPORT_PATH + "psdreporttest_subreportreferences.jasper"),
//
//
        ] as SubReport[];    
    }
} 
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportPDSController extends ReportController {
    
    @Service("TagabukidHRMISPDSReportService")
    def svc;

    def title = "PERSONAL DATA SHEET";
    final String REPORT_PATH = 'tagabukid/hrmis/pds/reports/';
    String reportName = REPORT_PATH + 'psdreporttest.jasper';
    def data
    def entity

    def getReportData() { 
        // entity._schemaname = 'hrmis_pds'
        // data = svc.getPersonalInfoByIds(entity);
        // return data.reportdata
        // println entity
        data = svc.getTestReportData(entity)
        return data.reportdata;
    } 

    Map getParameters(){
        return data.parameters;
    }
    
    SubReport[] getSubReports() {
        return [ 
           new SubReport("FAMILY", REPORT_PATH + "psdreporttest_subreportfam.jasper"),           
           new SubReport("FAMILY_CHILD", REPORT_PATH + "psdreporttest_subreportfam_subreportchild.jasper"),

           new SubReport("EDUCATION", REPORT_PATH + "psdreporttest_subreportedu.jasper"),

           new SubReport("CIVILSERVICE", REPORT_PATH + "psdreporttest_subreportcivilservice.jasper"),
           new SubReport("WORKEXPERIENCE", REPORT_PATH + "psdreporttest_subreportwork.jasper"),

           new SubReport("VOLUNTARYWORK", REPORT_PATH + "psdreporttest_subreportvoluntarywork.jasper"),
           new SubReport("TRAINING", REPORT_PATH + "psdreporttest_subreporttraining.jasper"),
           new SubReport("SKILL", REPORT_PATH + "psdreporttest_subreportskills.jasper"),
           new SubReport("NONACADEMIC", REPORT_PATH + "psdreporttest_subreportnonacademic.jasper"),
           new SubReport("MEMBERSHIP", REPORT_PATH + "psdreporttest_subreportmembership.jasper"),

           new SubReport("LASTPAGE", REPORT_PATH + "psdreporttest_subreportlastpage.jasper"),
           new SubReport("REFERENCE", REPORT_PATH + "psdreporttest_subreportreferences.jasper"),
           new SubReport("OATH", REPORT_PATH + "psdreporttest_subreportoath.jasper"),


        ] as SubReport[];    
    }
} 
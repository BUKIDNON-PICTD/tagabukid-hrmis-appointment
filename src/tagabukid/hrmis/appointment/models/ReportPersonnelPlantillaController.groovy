import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class ReportPersonnelPlantillaController extends tagabukid.common.models.AsyncReportController {
    
    @Service("TagabukidHRMISPersonnelPlantillaService")
    def svc;

    String title = 'Personnel Plantilla'
    String reportPath = 'tagabukid/hrmis/appointment/reports/'
    String reportName = reportPath + 'personnelplantillacscform.jasper';
    def data 
    // def entity
    // def origin

    // def ralph(){
    //         getReportData();
    // }

    // def getReportData() {             
    //     data = svc.getPersonnelPlantilla(origin) 
    //     return data.reportdata;
    // } 
    
    void buildReportData(entity, asyncHandler){
        svc.getPersonnelPlantilla(entity, asyncHandler)
    }

    Map getParameters(){
        return data.parameters;
    }
    

    def formControl = [
        getFormControls: {
            return [
                new FormControl( "lookup", [caption:'Org Name', name:'entity.org', handler:'lookup:tagabukid_hrmis_org', expression:'#{item.name}',  preferredSize:'250,19', captionWidth:100, allowNull:false, required:true]) 
            ]    
        },
    ] as FormPanelModel;
   
   
    // List getOrgs() {
    //     return subaylookupsvc.lookupOrgUnit([:]);
    // }
    
    // SubReport[] getSubReports() {
    //     return [ 
          
    //     ] as SubReport[];    
    // }
} 
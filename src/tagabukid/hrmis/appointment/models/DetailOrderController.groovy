
import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.swing.JFrame
import javax.swing.JOptionPane;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*;

class DetailOrderControlller extends CrudFormModel {

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc

    @Service
    def persistenceSvc

    // @Service('')
    // def svc
    
    def status = ["PERMANENT", "JO" ,"CASUAL"];

    boolean isAllowApprove() {
         return ( mode=='read' && entity.state.toString().matches('DRAFT') ); 
    }

    boolean isRecalled() {
        return ( mode=='read' && entity.returned.toString().matches('TRUE') );
    }

    boolean isEditAllowed() {
        return ( mode=='read' && entity.returned != 'TRUE' );
    }

    public void afterOpen(){
        println entity.returned
    }

	public void beforeSave(o){ 
            entity.state = "DRAFT";
            entity.returned = "FALSE";
        if(o == 'create'){
        	entity.pdsid = entity.personnel.pdsid;
        	// entity.personnel_name = entity.personnel.person.name;
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;  
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
        }else{
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
        }
    }

    void approve() { 
        if ( MsgBox.confirm('You are about to approve this information. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'hrmis_detailorder_list', 
               objid : entity.objid, 
               state : 'APPROVED' 
            ]); 
            loadData(); 
        }
    }

    void returned() { 

        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(frame, "Enter Return Date:");
        if ( result ) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                Date date = formatter.parse(result);


                getPersistenceService().update([
                   _schemaname: 'hrmis_detailorder_list', 
                   objid : entity.objid, 
                   returned : 'TRUE',
                   returndate: formatter.format(date),
                ]);        
            loadData(); 
        }

        // commented out

        // JDateChooser jd = new JDateChooser();
        // String message = "Choose start date:\n";
        // Object[] params = {message,jd};
        // JOptionPane.showConfirmDialog(null,params,"Start date", JOptionPane.PLAIN_MESSAGE);

    }
}
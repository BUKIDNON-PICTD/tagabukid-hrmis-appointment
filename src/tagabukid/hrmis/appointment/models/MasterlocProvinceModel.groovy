import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class MasterlocProvinceModel extends CrudFormModel{

    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc

    public void beforeSave(o){
        entity.state = "DRAFT";
        if(o == 'create'){
            entity.recordlog_datecreated = dtSvc.getServerDate();
            entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_createdbyuserid = OsirisContext.env.USERID;  
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatebyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatebyuserid = OsirisContext.env.USERID;
        }else{
            entity.recordlog_dateoflastupdate = dtSvc.getServerDate();
            entity.recordlog_lastupdatebyuser = OsirisContext.env.FULLNAME;
            entity.recordlog_lastupdatebyuserid = OsirisContext.env.USERID;
        }
    }

}
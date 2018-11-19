import com.rameses.rcp.annotations.*
import com.rameses.rcp.common.*
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import java.rmi.server.*
import tagabukid.utils.*;
import com.rameses.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class PDStoBTACSController extends CrudFormModel {

def svc

def loadBTACSidIncrement(){
    def btacsuserid = svc.getbtacsid()
    btacsuserid = btacsuserid + 1
}
    
}
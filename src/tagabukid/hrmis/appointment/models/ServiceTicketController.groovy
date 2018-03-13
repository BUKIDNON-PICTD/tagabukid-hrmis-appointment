
import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.annotations.Env
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class ServiceTicketController  extends CrudFormModel{
    @Binding
    def binding;
    
    @Service('DateService')
    def dtSvc
    
    @Env
    def env
    
//    @Service("PersistenceService")
//    def persistenceSvc;
    
    String serviceName = 'TagabukidPICTDServiceTicket';
    String entityName = 'pictd_servicetickets';
    
    
    public void afterCreate(){
        entity.employee = [:];        
        entity.org = [:];
        entity.serviceticket = [:];
        
        entity.recordlog_datecreated         = dtSvc.getServerDate();
        entity.recordlog_createdbyuserid     = env.USERID;
        entity.recordlog_createdbyuser       = env.FULLNAME;
        entity.recordlog_dateoflastupdate    =  dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuserid = env.USERID;
        entity.recordlog_lastupdatedbyuser   = env.FULLNAME;
    }

    def colors = ['BROWN', 'BLACK', 'WHITE','GREEN','BLUE'];
    def bodybuild = ['SMALL', 'MEDIUM', 'LARGE'];
    def counseltypes = ['PRIVATE', 'PUBLIC'];
    def complexion = ['FAIR', 'PALE', 'WHITE','BROWN','TAN'];
    //TABLEHANDLERS
    def listHandlerLanguage = [
        getRows : {entity.detainee.languageanddialects.size() + 1 },
        fetchList: { entity.detainee.languageanddialects },
        createItem : {
            return[
                objid : 'DTL' + new java.rmi.server.UID(),
                detainee : [objid:entity.objid],
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                //service.deleteLanguageAndDialectItem(it)               
                entity.detainee.languageanddialects.remove(it)
                listHandlerLanguage.reload();
                return true;
            }
            return false;
        },
        onAddItem : {
            if(!entity.detainee.languageanddialects){
                entity.detainee.languageanddialects = []
            }
            entity.detainee.languageanddialects << it; 
        },
        validate:{li->
            def item=li.item;
            checkDuplicateName(entity.detainee.languageanddialects,item);
        }
    ] as EditorListModel;
    
     def listHandlerCases = [
        getRows : {entity.detaineeCases.size() + 1 },
        fetchList: { entity.detaineeCases },
        createItem : {
            return[
                objid : 'DTC' + new java.rmi.server.UID(),
                detainee : [objid:entity.objid],
                counsellist : [],
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                //service.deleteLanguageAndDialectItem(it)               
                entity.detaineeCases.remove(it)
                listHandlerCases.reload()
                return true;
            }
            return false;
        },
        onAddItem : {
            if(!entity.detaineeCases){
                entity.detaineeCases = []
            }
            entity.detaineeCases << it; 
            println entity
        }
    ] as EditorListModel;
    
     def listHandlerCounsels = [
//        getRows : {selectedCase.counsellist.size() + 1 },
        fetchList: { selectedCase?.counsellist },
        createItem : {
            return[
                objid : 'DTCS' + new java.rmi.server.UID(),
                detainee : [objid:entity.objid],
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                //service.deleteLanguageAndDialectItem(it)               
                selectedCase.counsellist.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        onAddItem : {
            if(!entity.detainee.counsellist){
                entity.detainee.counsellist = []
            }
           selectedCase.counsellist << it; 
        },
        validate:{li->
            def item=li.item;
            checkDuplicateCounsel(selectedCase.counsellist,item);
        }
    ] as EditorListModel;
 
  
    
     def listHandlerCells = [
        getRows : {entity.detainee.cells.size() + 1 },
        fetchList: { entity.detainee.cells },
        createItem : {
            return[
                objid : 'DTCL' + new java.rmi.server.UID(),
                detainee : [objid:entity.objid],
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteLanguageAndDialectItem(it)               
                entity.detainee.cells.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        onAddItem : {
            if(!entity.detainee.cells){
                entity.detainee.cells = []
            }
            entity.detainee.cells << it; 
            
        },
        validate:{li->
            def item=li.item;
            checkDuplicate(entity.detainee.cells,item);
        }
        
    ] as EditorListModel;

    def listHandlerContacts = [
        getRows : {entity.detainee.contacts.size() + 1 },
        fetchList: { entity.detainee.contacts },
        createItem : {
            return[
                objid : 'DTCO' + new java.rmi.server.UID(),
                detainee : [objid:entity.objid],
                relationship:[]
            ]
        },
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteLanguageAndDialectItem(it)               
                entity.detainee.contacts.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        onAddItem : {
            if(!entity.detainee.contacts){
                entity.detainee.contacts =[]
            }
            entity.detainee.contacts << it; 
        },
        validate:{li->
            def item=li.item;
            checkDuplicate(entity.detainee.contacts,item);
        }
    ] as EditorListModel;
    
    //LOOKUP
    
    def getLookupCourts(){
        return InvokerUtil.lookupOpener('pdrc_courts:lookup', [
                onselect:{
                    entity.detainee.courtbranch = it
                }
        ]);
    }
    def getLookupCourts1(){
        return InvokerUtil.lookupOpener('pdrc_courts:lookup');
    }
    
    def getLookupCases(){
        return InvokerUtil.lookupOpener('pdrc_cases:lookup');
    }
    
    def getLookupCells(){
        return InvokerUtil.lookupOpener('pdrc_cells:lookup');
    }
    
    def getLookupCauseOfDropping(){
        return InvokerUtil.lookupOpener('pdrc_causeofdropping:lookup');
    }
    
    def getLookupCounsels(){
        return InvokerUtil.lookupOpener('pdrc_counsel:lookup');
    }
    
    void checkDuplicateName(listtofilter,item){
      def data = listtofilter.find{it.name == item.name }
      if (data)
      throw new Exception("Duplicate item is not allowed.")
    }
    
    void checkDuplicate(listtofilter,item){
      def data = listtofilter.find{it.objid == item.objid}
      if (data)
      throw new Exception("Duplicate item is not allowed.")
    }
    
    void checkDuplicateCounsel(listtofilter,item){
      def data = listtofilter.find{it.counsel.objid == item.counsel.objid && it.type == item.type}
      if (data)
      throw new Exception("Duplicate item is not allowed.")
    }
    
    def getLookupContacts() {
        return InvokerUtil.lookupOpener('entity:lookup', ['query.type': 'INDIVIDUAL','allowSelectEntityType' : false,
            onselect : {
                    selectedContact.objid = it.objid
                    selectedContact.name = it.name
                    selectedContact.address = it.address.text 
                }
            ]); 
    }
    
    def getLookupRelationship(){
        return InvokerUtil.lookupOpener('pdrc_contact_relationships:lookup');
    }
}  
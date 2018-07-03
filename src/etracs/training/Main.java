package etracs.training;

import com.rameses.osiris2.test.OsirisTestPlatform;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

public class Main {

//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//            Map env = new HashMap();
//            env.put("app.host", "localhost:8072");
//            env.put("app.context", "etracs25");
//            env.put("app.cluster", "osiris3");
//
//            Map roles = new HashMap();
//            roles.put("DEVELOPER.REPORT", null);
//            roles.put("HR.ENCODER", null);
//            roles.put("HR.APPROVER", null);
//            roles.put("HR.MASTER", null);
//            roles.put("HR.REPORTS", null);
//            Map profile = new HashMap();
//            profile.put("CLIENTTYPE", "desktop");
//            profile.put("USERID", "ADMIN");
//            profile.put("USER", "ADMIN");
//            profile.put("FULLNAME", "ADMINISTRATOR ROTARTSINIMDA");
//            profile.put("JOBTITLE", "DALTANS");
//            profile.put("ORGID", "059");
//            profile.put("ORGCODE", "059");
//            profile.put("ORGNAME", "PROVINCE OF BUKIDNON");
//            profile.put("ORGCLASS", "PROVINCE");
//            OsirisTestPlatform.runTest(env, roles, profile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch(Exception ex) {;}
        
        Map conf = new HashMap();
        conf.put("app.host","localhost:8072");
        conf.put("app.context","etracs25"); //bpls
        conf.put("app.custom","buk");
        conf.put("ws.host","localhost:8063");
        //conf.put("app.host","192.168.2.100:8070");
        //conf.put("app.context","etracs25");
        conf.put("app.cluster","osiris3");
        conf.put("app.debug",true);
        
        //conf.put("ws.host","localhost:8060");
        
        /*
        conf.put("app.custom","iligan");
        conf.put("report.custom","iligan");
        */
        //OsirisTestPlatform.setConf(conf);
        OsirisTestPlatform.runTest(conf, new HashMap(), new HashMap());



    }
}

package pora;


import com.rameses.osiris2.test.OsirisTestPlatform;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pictd-training1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex){;}
        
        
        
        Map env = new HashMap();
        env.put("app.host", "localhost:8072");
        env.put("app.context", "etracs25");
        env.put("app.cluster", "osiris3");
        env.put("app.debug", true);
        
        Map roles = new HashMap();
        Map profile = new HashMap();
        profile.put("CLIENTTYPE", "desktop");
        profile.put("USERID", "ADMIN");
        profile.put("USER", "ADMIN");
        profile.put("FULLNAME", "ADMINISTRATOR");
        profile.put("ORGID", "057");
        profile.put("ORGCODE", "057");
        profile.put("ORGNAME", "LANAO DEL NORTE");
        profile.put("ORGCLASS", "PROVINCE");
        OsirisTestPlatform.runTest(env, roles, profile);
        }
    
}

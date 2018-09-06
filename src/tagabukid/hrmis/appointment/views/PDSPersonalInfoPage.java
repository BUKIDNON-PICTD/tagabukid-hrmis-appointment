/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagabukid.hrmis.appointment.views;

import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;
import com.rameses.seti2.views.CrudFormPage;

/**
 *
 * @author user
 */
@StyleSheet
@Template(CrudFormPage.class)
public class PDSPersonalInfoPage extends javax.swing.JPanel {

    /**
     * Creates new form PDSPersonalInfoNewPage
     */
    public PDSPersonalInfoPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        xPanel1 = new com.rameses.rcp.control.XPanel();
        xHorizontalPanel3 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        entityAddress1 = new com.rameses.enterprise.components.EntityAddress();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        entityAddress2 = new com.rameses.enterprise.components.EntityAddress();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xHorizontalPanel1 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        entityLookup1 = new com.rameses.entity.components.EntityLookup();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        jScrollPane1 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        civilStatusList1 = new com.rameses.enterprise.components.CivilStatusList();
        genderList1 = new com.rameses.enterprise.components.GenderList();
        xDecimalField1 = new com.rameses.rcp.control.XDecimalField();
        xDecimalField2 = new com.rameses.rcp.control.XDecimalField();
        xTextField7 = new com.rameses.rcp.control.XTextField();
        xTextField8 = new com.rameses.rcp.control.XTextField();
        xTextField9 = new com.rameses.rcp.control.XTextField();
        xTextField10 = new com.rameses.rcp.control.XTextField();
        xTextField11 = new com.rameses.rcp.control.XTextField();
        xTextField12 = new com.rameses.rcp.control.XTextField();
        xTextField13 = new com.rameses.rcp.control.XTextField();
        citizenshipSuggest1 = new com.rameses.enterprise.components.CitizenshipSuggest();
        xHorizontalPanel2 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xTextField14 = new com.rameses.rcp.control.XTextField();
        xTextField15 = new com.rameses.rcp.control.XTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        xTextArea2 = new com.rameses.rcp.control.XTextArea();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Address");
        xHorizontalPanel3.setBorder(xTitledBorder1);

        entityAddress1.setCaption("Residential Address");
        entityAddress1.setCaptionWidth(150);
        entityAddress1.setDepends(new String[] {"entity.copyresidential"});
        entityAddress1.setName("entity.residential.address"); // NOI18N
        entityAddress1.setPreferredSize(new java.awt.Dimension(0, 86));
        entityAddress1.setRequired(true);
        xFormPanel3.add(entityAddress1);

        xTextField2.setText("entity.residentialzipcode");
        xTextField2.setCaption("ZIP Code");
        xTextField2.setCaptionWidth(150);
        xTextField2.setName("entity.residentialzipcode"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xTextField2);

        entityAddress2.setCaption("Permanent Address");
        entityAddress2.setCaptionWidth(150);
        entityAddress2.setDepends(new String[] {"entity.copypermanent"});
        entityAddress2.setName("entity.residential.address"); // NOI18N
        entityAddress2.setPreferredSize(new java.awt.Dimension(0, 86));
        entityAddress2.setRequired(true);
        xFormPanel3.add(entityAddress2);

        xTextField3.setText("entity.permanentzipcode");
        xTextField3.setCaption("ZIP Code");
        xTextField3.setCaptionFontStyle("");
        xTextField3.setCaptionWidth(150);
        xTextField3.setName("entity.permanentzipcode"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xTextField3);

        xHorizontalPanel3.add(xFormPanel3);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Personal");
        xHorizontalPanel1.setBorder(xTitledBorder2);

        entityLookup1.setCaption("Name");
        entityLookup1.setCaptionWidth(135);
        entityLookup1.setExpression("#{entity.name}");
        entityLookup1.setName("entity.person"); // NOI18N
        entityLookup1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(entityLookup1);

        xTextField1.setText("entity.maidenname");
        xTextField1.setCaption("Maiden Name:");
        xTextField1.setCaptionWidth(135);
        xTextField1.setName("entity.maidenname"); // NOI18N
        xTextField1.setPreferredSize(new java.awt.Dimension(300, 20));
        xFormPanel1.add(xTextField1);

        xTextField4.setCaption("Extension (Jr., Sr)");
        xTextField4.setCaptionWidth(135);
        xTextField4.setName("entity.nameextension"); // NOI18N
        xTextField4.setPreferredSize(new java.awt.Dimension(300, 20));
        xFormPanel1.add(xTextField4);

        xDateField1.setCaption("Date of Birth");
        xDateField1.setCaptionWidth(135);
        xDateField1.setDepends(new String[] {"entity.person"});
        xDateField1.setName("entity.person.birthdate"); // NOI18N
        xDateField1.setRequired(true);
        xFormPanel1.add(xDateField1);

        xTextArea1.setCaption("Birth Place");
        xTextArea1.setCaptionWidth(135);
        xTextArea1.setDepends(new String[] {"entity.person"});
        xTextArea1.setName("entity.person.birthplace"); // NOI18N
        xTextArea1.setPreferredSize(new java.awt.Dimension(300, 61));
        xTextArea1.setRequired(true);
        xTextArea1.setTextCase(com.rameses.rcp.constant.TextCase.UPPER);
        jScrollPane1.setViewportView(xTextArea1);

        xFormPanel1.add(jScrollPane1);

        civilStatusList1.setCaption("Civil Status");
        civilStatusList1.setCaptionWidth(135);
        civilStatusList1.setDepends(new String[] {"entity.person"});
        civilStatusList1.setName("entity.person.civilstatus"); // NOI18N
        civilStatusList1.setRequired(true);
        xFormPanel1.add(civilStatusList1);

        genderList1.setCaption("Gender");
        genderList1.setCaptionWidth(135);
        genderList1.setDepends(new String[] {"entity.person"});
        genderList1.setName("entity.person.gender"); // NOI18N
        genderList1.setRequired(true);
        xFormPanel1.add(genderList1);

        xDecimalField1.setCaption("Height (m)");
        xDecimalField1.setCaptionWidth(135);
        xDecimalField1.setName("entity.height"); // NOI18N
        xDecimalField1.setRequired(true);
        xFormPanel1.add(xDecimalField1);

        xDecimalField2.setCaption("Weight (kg)");
        xDecimalField2.setCaptionWidth(135);
        xDecimalField2.setName("entity.weight"); // NOI18N
        xDecimalField2.setRequired(true);
        xFormPanel1.add(xDecimalField2);

        xTextField7.setCaption("Blood Type");
        xTextField7.setCaptionWidth(135);
        xTextField7.setName("entity.bloodtype"); // NOI18N
        xTextField7.setPreferredSize(new java.awt.Dimension(150, 20));
        xFormPanel1.add(xTextField7);

        xTextField8.setCaption("GSIS ID No.");
        xTextField8.setCaptionWidth(135);
        xTextField8.setName("entity.gsisid"); // NOI18N
        xTextField8.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xTextField8);

        xTextField9.setCaption("PAG-IBIG ID No.");
        xTextField9.setCaptionWidth(135);
        xTextField9.setName("entity.pagibigid"); // NOI18N
        xTextField9.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xTextField9);

        xTextField10.setCaption("PHILHEALTH No.");
        xTextField10.setCaptionWidth(135);
        xTextField10.setName("entity.philhealth"); // NOI18N
        xTextField10.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xTextField10);

        xTextField11.setCaption("SSS No.");
        xTextField11.setCaptionWidth(135);
        xTextField11.setName("entity.sss"); // NOI18N
        xTextField11.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xTextField11);

        xTextField12.setCaption("TIN No.");
        xTextField12.setCaptionWidth(135);
        xTextField12.setName("entity.tin"); // NOI18N
        xTextField12.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xTextField12);

        xTextField13.setCaption("Employee No");
        xTextField13.setCaptionWidth(135);
        xTextField13.setName("entity.employeeno"); // NOI18N
        xTextField13.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel1.add(xTextField13);

        citizenshipSuggest1.setCaption("Citizenship");
        citizenshipSuggest1.setCaptionWidth(135);
        citizenshipSuggest1.setDepends(new String[] {"entity.person"});
        citizenshipSuggest1.setName("entity.person.citizenship"); // NOI18N
        citizenshipSuggest1.setPreferredSize(new java.awt.Dimension(0, 20));
        citizenshipSuggest1.setRequired(true);
        xFormPanel1.add(citizenshipSuggest1);

        xHorizontalPanel1.add(xFormPanel1);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder3 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder3.setTitle("Contact Info");
        xHorizontalPanel2.setBorder(xTitledBorder3);

        xTextField14.setEditable(false);
        xTextField14.setCaption("Telephone No.");
        xTextField14.setCaptionWidth(125);
        xTextField14.setDepends(new String[] {"entity.person"});
        xTextField14.setName("entity.person.phoneno"); // NOI18N
        xTextField14.setNullWhenEmpty(false);
        xTextField14.setPreferredSize(new java.awt.Dimension(175, 20));
        xFormPanel2.add(xTextField14);

        xTextField15.setEditable(false);
        xTextField15.setCaption("Mobile No.");
        xTextField15.setCaptionWidth(125);
        xTextField15.setDepends(new String[] {"entity.person"});
        xTextField15.setName("entity.person.mobileno"); // NOI18N
        xTextField15.setPreferredSize(new java.awt.Dimension(175, 20));
        xFormPanel2.add(xTextField15);

        xTextArea2.setCaption("E-mail Address (if any)");
        xTextArea2.setCaptionWidth(125);
        xTextArea2.setDepends(new String[] {"entity.person"});
        xTextArea2.setName("entity.person.email"); // NOI18N
        xTextArea2.setPreferredSize(new java.awt.Dimension(175, 61));
        jScrollPane2.setViewportView(xTextArea2);

        xFormPanel2.add(jScrollPane2);

        xHorizontalPanel2.add(xFormPanel2);

        javax.swing.GroupLayout xPanel1Layout = new javax.swing.GroupLayout(xPanel1);
        xPanel1.setLayout(xPanel1Layout);
        xPanel1Layout.setHorizontalGroup(
            xPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(xPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(xPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(xHorizontalPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addComponent(xHorizontalPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 171, Short.MAX_VALUE))
        );
        xPanel1Layout.setVerticalGroup(
            xPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(xPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(xPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(xPanel1Layout.createSequentialGroup()
                        .addComponent(xHorizontalPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xHorizontalPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(410, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(xPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.enterprise.components.CitizenshipSuggest citizenshipSuggest1;
    private com.rameses.enterprise.components.CivilStatusList civilStatusList1;
    private com.rameses.enterprise.components.EntityAddress entityAddress1;
    private com.rameses.enterprise.components.EntityAddress entityAddress2;
    private com.rameses.entity.components.EntityLookup entityLookup1;
    private com.rameses.enterprise.components.GenderList genderList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XDecimalField xDecimalField1;
    private com.rameses.rcp.control.XDecimalField xDecimalField2;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel1;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel2;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel3;
    private com.rameses.rcp.control.XPanel xPanel1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    private com.rameses.rcp.control.XTextArea xTextArea2;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField10;
    private com.rameses.rcp.control.XTextField xTextField11;
    private com.rameses.rcp.control.XTextField xTextField12;
    private com.rameses.rcp.control.XTextField xTextField13;
    private com.rameses.rcp.control.XTextField xTextField14;
    private com.rameses.rcp.control.XTextField xTextField15;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField7;
    private com.rameses.rcp.control.XTextField xTextField8;
    private com.rameses.rcp.control.XTextField xTextField9;
    // End of variables declaration//GEN-END:variables
}

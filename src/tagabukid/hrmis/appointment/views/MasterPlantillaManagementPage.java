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
 * @author pictd-training1
 */
@StyleSheet
@Template(CrudFormPage.class)
public class MasterPlantillaManagementPage extends javax.swing.JPanel {

    /**
     * Creates new form MasterFinFundView
     */
    public MasterPlantillaManagementPage() {
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

        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xLookupField3 = new com.rameses.rcp.control.XLookupField();
        xLookupField4 = new com.rameses.rcp.control.XLookupField();
        xLookupField5 = new com.rameses.rcp.control.XLookupField();
        xLookupField6 = new com.rameses.rcp.control.XLookupField();
        xLookupField7 = new com.rameses.rcp.control.XLookupField();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Plantilla Info");
        xFormPanel2.setBorder(xTitledBorder1);
        xFormPanel2.setCaptionWidth(190);

        xLookupField2.setCaption("Job Position");
        xLookupField2.setExpression("#{item.name}");
        xLookupField2.setHandler("lookup:tagabukid_hrmis_jobposition");
        xLookupField2.setName("entity.jobposition"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField2.setRequired(true);
        xFormPanel2.add(xLookupField2);

        xIntegerField1.setCaption("Item No.");
        xIntegerField1.setName("entity.itemno"); // NOI18N
        xIntegerField1.setRequired(true);
        xFormPanel2.add(xIntegerField1);

        xCheckBox1.setCaption("Is Funded");
        xCheckBox1.setName("entity.isfunded"); // NOI18N
        xCheckBox1.setShowCaption(false);
        xCheckBox1.setText("Is Funded?");
        xFormPanel2.add(xCheckBox1);

        xComboBox2.setAllowNull(false);
        xComboBox2.setCaption("Plantilla Type");
        xComboBox2.setItems("types");
        xComboBox2.setName("entity.type"); // NOI18N
        xComboBox2.setPreferredSize(new java.awt.Dimension(100, 20));
        xComboBox2.setRequired(true);
        xFormPanel2.add(xComboBox2);

        xLookupField3.setCaption("Increment Type");
        xLookupField3.setExpression("#{item.name + \"-\" + item.description}");
        xLookupField3.setHandler("lookup:tagabukid_hrmis_incrementtype");
        xLookupField3.setName("entity.incrementtype"); // NOI18N
        xLookupField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField3.setRequired(true);
        xFormPanel2.add(xLookupField3);

        xLookupField4.setCaption("Fund");
        xLookupField4.setExpression("#{item.name}");
        xLookupField4.setHandler("lookup:tagabukid_hrmis_fund");
        xLookupField4.setName("entity.fund"); // NOI18N
        xLookupField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField4.setRequired(true);
        xFormPanel2.add(xLookupField4);

        xLookupField5.setCaption("Account Title");
        xLookupField5.setExpression("#{item.name}");
        xLookupField5.setHandler("lookup:tagabukid_hrmis_account");
        xLookupField5.setName("entity.accounttitle"); // NOI18N
        xLookupField5.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField5.setRequired(true);
        xFormPanel2.add(xLookupField5);

        xLookupField6.setCaption("Pos. Service Classification");
        xLookupField6.setExpression("#{item.name}");
        xLookupField6.setHandler("lookup:tagabukid_hrmis_positionserviceclassification");
        xLookupField6.setName("entity.positionserviceclassification"); // NOI18N
        xLookupField6.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel2.add(xLookupField6);

        xLookupField7.setCaption("Pos. Service Sub Classification");
        xLookupField7.setDepends(new String[] {"entity.positionserviceclassification"});
        xLookupField7.setExpression("#{item.name}");
        xLookupField7.setHandler("lookuppositionservicesubclassification");
        xLookupField7.setName("entity.positionservicesubclassification"); // NOI18N
        xLookupField7.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel2.add(xLookupField7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XLookupField xLookupField3;
    private com.rameses.rcp.control.XLookupField xLookupField4;
    private com.rameses.rcp.control.XLookupField xLookupField5;
    private com.rameses.rcp.control.XLookupField xLookupField6;
    private com.rameses.rcp.control.XLookupField xLookupField7;
    // End of variables declaration//GEN-END:variables
}

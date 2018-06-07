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
 * @author P0RA
 */
@StyleSheet
@Template(CrudFormPage.class)
public class MasterAcadEducationalInstitutionBackView extends javax.swing.JPanel {

    /**
     * Creates new form MasterAcadEducationalInstitutionBackView
     */
    public MasterAcadEducationalInstitutionBackView() {
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

        xHorizontalPanel1 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xDateField3 = new com.rameses.rcp.control.XDateField();
        xTextField6 = new com.rameses.rcp.control.XTextField();
        xLookupField3 = new com.rameses.rcp.control.XLookupField();
        xTextField11 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xHorizontalPanel3 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xTextField8 = new com.rameses.rcp.control.XTextField();
        xTextField9 = new com.rameses.rcp.control.XTextField();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xHorizontalPanel4 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel4 = new com.rameses.rcp.control.XFormPanel();
        xTextField14 = new com.rameses.rcp.control.XTextField();
        xTextField15 = new com.rameses.rcp.control.XTextField();
        xTextField16 = new com.rameses.rcp.control.XTextField();
        xTextField17 = new com.rameses.rcp.control.XTextField();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Academic Educational Institution Back");
        xHorizontalPanel1.setBorder(xTitledBorder1);

        xTextField1.setCaption("School ID");
        xTextField1.setCaptionWidth(100);
        xTextField1.setName("entity.schoolid"); // NOI18N
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField1);

        xTextField3.setCaption("Name");
        xTextField3.setCaptionWidth(100);
        xTextField3.setName("entity.name"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField3);

        xTextField4.setCaption("Description");
        xTextField4.setCaptionWidth(100);
        xTextField4.setName("entity.description"); // NOI18N
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField4);

        xTextField5.setCaption("Code");
        xTextField5.setCaptionWidth(100);
        xTextField5.setName("entity.code"); // NOI18N
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xTextField5ActionPerformed(evt);
            }
        });
        xFormPanel1.add(xTextField5);

        xDateField1.setCaption("Date Established");
        xDateField1.setCaptionWidth(100);
        xDateField1.setName("entity.dateestablished"); // NOI18N
        xDateField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xDateField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xDateField1ActionPerformed(evt);
            }
        });
        xFormPanel1.add(xDateField1);

        xDateField3.setCaption("Date Closed");
        xDateField3.setCaptionWidth(100);
        xDateField3.setName("entity.dateclosed"); // NOI18N
        xDateField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xDateField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xDateField3ActionPerformed(evt);
            }
        });
        xFormPanel1.add(xDateField3);

        xTextField6.setCaption("Level");
        xTextField6.setCaptionWidth(100);
        xTextField6.setName("entity.level"); // NOI18N
        xTextField6.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xTextField6ActionPerformed(evt);
            }
        });
        xFormPanel1.add(xTextField6);

        xLookupField3.setCaption("Parent ID");
        xLookupField3.setCaptionWidth(100);
        xLookupField3.setExpression("#{item.name}");
        xLookupField3.setHandler("educationalinstitutionback:lookup");
        xLookupField3.setName("entity.parent"); // NOI18N
        xLookupField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLookupField3);

        xTextField11.setCaption("SDF");
        xTextField11.setCaptionWidth(100);
        xTextField11.setName("entity.sdf"); // NOI18N
        xTextField11.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField11);

        xTextField2.setCaption("objid");
        xTextField2.setCaptionWidth(100);
        xTextField2.setName("entity.objid"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField2.setVisible(false);
        xFormPanel1.add(xTextField2);

        xHorizontalPanel1.add(xFormPanel1);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Address Details");
        xHorizontalPanel3.setBorder(xTitledBorder2);

        xTextField8.setCaption("Address Details");
        xTextField8.setCaptionWidth(100);
        xTextField8.setName("entity.address_addressdetails"); // NOI18N
        xTextField8.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xTextField8);

        xTextField9.setCaption("Zip Code");
        xTextField9.setCaptionWidth(100);
        xTextField9.setName("entity.address_zipcode"); // NOI18N
        xTextField9.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xTextField9);

        xLookupField2.setCaption("Barangay");
        xLookupField2.setCaptionWidth(100);
        xLookupField2.setExpression("#{item.name}");
        xLookupField2.setHandler("barangay:lookup");
        xLookupField2.setName("entity.barangay"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLookupField2);

        xLookupField1.setCaption("City/Municipality");
        xLookupField1.setCaptionWidth(100);
        xLookupField1.setExpression("#{item.name}");
        xLookupField1.setHandler("citymunicipality:lookup");
        xLookupField1.setName("entity.citymun"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLookupField1);

        xHorizontalPanel3.add(xFormPanel3);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder3 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder3.setTitle("Contact Information");
        xHorizontalPanel4.setBorder(xTitledBorder3);

        xTextField14.setCaption("Landline");
        xTextField14.setCaptionWidth(100);
        xTextField14.setName("entity.contacts_landline"); // NOI18N
        xTextField14.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel4.add(xTextField14);

        xTextField15.setCaption("Mobile");
        xTextField15.setCaptionWidth(100);
        xTextField15.setName("entity.contacts_mobile"); // NOI18N
        xTextField15.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel4.add(xTextField15);

        xTextField16.setCaption("E-mail");
        xTextField16.setCaptionWidth(100);
        xTextField16.setName("entity.contacts_email"); // NOI18N
        xTextField16.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel4.add(xTextField16);

        xTextField17.setCaption("Fax");
        xTextField17.setCaptionWidth(100);
        xTextField17.setName("entity.contacts_fax"); // NOI18N
        xTextField17.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel4.add(xTextField17);

        xHorizontalPanel4.add(xFormPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xHorizontalPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xHorizontalPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xHorizontalPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xHorizontalPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void xDateField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xDateField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xDateField3ActionPerformed

    private void xTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xTextField6ActionPerformed

    private void xDateField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xDateField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xDateField1ActionPerformed

    private void xTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xTextField5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XDateField xDateField3;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XFormPanel xFormPanel4;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel1;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel3;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel4;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XLookupField xLookupField3;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField11;
    private com.rameses.rcp.control.XTextField xTextField14;
    private com.rameses.rcp.control.XTextField xTextField15;
    private com.rameses.rcp.control.XTextField xTextField16;
    private com.rameses.rcp.control.XTextField xTextField17;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField6;
    private com.rameses.rcp.control.XTextField xTextField8;
    private com.rameses.rcp.control.XTextField xTextField9;
    // End of variables declaration//GEN-END:variables
}

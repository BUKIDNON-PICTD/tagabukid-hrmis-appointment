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
public class MasterAcadEducationalInstitutionView extends javax.swing.JPanel {

    /**
     * Creates new form MasterAcadEducationalInstitutionView
     */
    public MasterAcadEducationalInstitutionView() {
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

        xHorizontalPanel2 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Academic Educational Institution");
        xHorizontalPanel2.setBorder(xTitledBorder1);

        xTextField2.setCaption("objid");
        xTextField2.setCaptionWidth(75);
        xTextField2.setName("entity.objid"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField2.setVisible(false);
        xFormPanel2.add(xTextField2);

        xTextField3.setCaption("School ID");
        xTextField3.setCaptionFontStyle("");
        xTextField3.setCaptionWidth(130);
        xTextField3.setName("entity.schoolid"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel2.add(xTextField3);

        xTextField4.setCaption("Level");
        xTextField4.setCaptionFontStyle("");
        xTextField4.setCaptionWidth(130);
        xTextField4.setName("entity.level"); // NOI18N
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel2.add(xTextField4);

        xHorizontalPanel2.add(xFormPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel2;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    // End of variables declaration//GEN-END:variables
}

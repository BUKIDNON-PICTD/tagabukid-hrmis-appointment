/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagabukid.hrmis.appointment.views;

/**
 *
 * @author pictdpc
 */
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;
import com.rameses.seti2.views.CrudFormPage;

@StyleSheet
@Template(CrudFormPage.class)
public class MasterAcadCourseMajorPage extends javax.swing.JPanel {

    /**
     * Creates new form MasterAcadCourseMajor
     */
    public MasterAcadCourseMajorPage() {
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

        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Academic Course Major");
        xFormPanel1.setBorder(xTitledBorder1);

        xLookupField1.setText("xLookupField1");
        xLookupField1.setCaption("Employee Name");
        xLookupField1.setCaptionWidth(100);
        xFormPanel1.add(xLookupField1);

        xTextField1.setCaption("Description");
        xTextField1.setCaptionWidth(100);
        xTextField1.setName("entity.description"); // NOI18N
        xFormPanel1.add(xTextField1);

        xTextField2.setCaption("Code");
        xTextField2.setCaptionWidth(100);
        xTextField2.setName("entity.code"); // NOI18N
        xFormPanel1.add(xTextField2);

        xTextField3.setCaption("Course ID");
        xTextField3.setCaptionWidth(100);
        xTextField3.setName("entity.courseid"); // NOI18N
        xFormPanel1.add(xTextField3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
}
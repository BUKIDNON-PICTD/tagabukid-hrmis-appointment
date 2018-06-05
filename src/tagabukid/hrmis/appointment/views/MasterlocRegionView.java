package tagabukid.hrmis.appointment.views;

import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;
import com.rameses.seti2.views.CrudFormPage;

@StyleSheet
@Template(CrudFormPage.class)
public class MasterlocRegionView extends javax.swing.JPanel {

    /**
     * Creates new form locRegionView
     */
    public MasterlocRegionView() {
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
        xTextField3 = new com.rameses.rcp.control.XTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        xTextArea2 = new com.rameses.rcp.control.XTextArea();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xTextField6 = new com.rameses.rcp.control.XTextField();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Region");
        xHorizontalPanel1.setBorder(xTitledBorder1);
        xHorizontalPanel1.setMinimumSize(new java.awt.Dimension(100, 396));
        xHorizontalPanel1.setPreferredSize(new java.awt.Dimension(100, 396));

        xTextField3.setCaption("Name");
        xTextField3.setCaptionWidth(130);
        xTextField3.setName("entity.name"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField3);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(0, 63));

        xTextArea2.setCaption("Description");
        xTextArea2.setCaptionWidth(130);
        xTextArea2.setItemExpression("");
        xTextArea2.setName("entity.description"); // NOI18N
        jScrollPane2.setViewportView(xTextArea2);

        xFormPanel1.add(jScrollPane2);

        xTextField4.setCaption("Acronym / Abbreviation");
        xTextField4.setCaptionWidth(130);
        xTextField4.setName("entity.acronymorabbreviation"); // NOI18N
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField4);

        xTextField5.setCaption("PSGC Code");
        xTextField5.setCaptionWidth(130);
        xTextField5.setName("entity.psgccode"); // NOI18N
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField5);

        xTextField6.setCaption("PIN No");
        xTextField6.setCaptionWidth(130);
        xTextField6.setName("entity.pinno"); // NOI18N
        xTextField6.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField6);

        xLookupField1.setCaption("Country ID");
        xLookupField1.setCaptionWidth(130);
        xLookupField1.setExpression("#{item.name}");
        xLookupField1.setHandler("country:lookup");
        xLookupField1.setName("entity.country"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField1.setRequired(true);
        xFormPanel1.add(xLookupField1);

        xHorizontalPanel1.add(xFormPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XTextArea xTextArea2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField6;
    // End of variables declaration//GEN-END:variables
}

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
 * @author rufino
 */
@StyleSheet
@Template(CrudFormPage.class)
public class JobOrderAppointmentView extends javax.swing.JPanel {

    /**
     * Creates new form NewDetaineePage
     */
    public JobOrderAppointmentView() {
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

        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        xDataTable7 = new com.rameses.rcp.control.XDataTable();
        jPanel2 = new javax.swing.JPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xLookupField3 = new com.rameses.rcp.control.XLookupField();
        xDataTable2 = new com.rameses.rcp.control.XDataTable();
        xHorizontalPanel1 = new com.rameses.rcp.control.XHorizontalPanel();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xSuggest1 = new com.rameses.rcp.control.XSuggest();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xDateField2 = new com.rameses.rcp.control.XDateField();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xDateField3 = new com.rameses.rcp.control.XDateField();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xLabel1 = new com.rameses.rcp.control.XLabel();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setPadding(new java.awt.Insets(25, 10, 10, 10));
        xTitledBorder1.setTitle("Job Order Appointment Group Details");
        jPanel1.setBorder(xTitledBorder1);

        xDataTable7.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "employee"}
                , new Object[]{"caption", "Employee Name"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.employee.name}", "lookup:tagabukid_hrmis_personnel")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "position"}
                , new Object[]{"caption", "Position"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.position.name}", "lookup:tagabukid_hrmis_jobposition")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "dailysalary"}
                , new Object[]{"caption", "Salary"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.DecimalColumnHandler("#,##0.00", -1.0, -1.0, false, 2)}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "account"}
                , new Object[]{"caption", "Account"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.account.name}", "lookup:tagabukid_hrmis_account")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "fund"}
                , new Object[]{"caption", "Fund"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.fund.name}", "lookup:tagabukid_hrmis_fund")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "lastemploymentfrom"}
                , new Object[]{"caption", "Employed From"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.DateColumnHandler(null, null, null)}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "lastemploymentuntil"}
                , new Object[]{"caption", "Employed Until"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.DateColumnHandler(null, null, null)}
            })
        });
        xDataTable7.setHandler("appointmentMemberItemHandler");
        xDataTable7.setName("selectedAppointmentMemberItem"); // NOI18N
        jTabbedPane1.addTab("Appointment Group Members", xDataTable7);

        xLookupField3.setCaption("Signatory Group");
        xLookupField3.setCaptionWidth(120);
        xLookupField3.setExpression("#{item.signatorygroupname}");
        xLookupField3.setHandler("lookupSignatory");
        xLookupField3.setName("entity.signatorygroup"); // NOI18N
        xLookupField3.setPreferredSize(new java.awt.Dimension(250, 20));
        xFormPanel1.add(xLookupField3);

        xDataTable2.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "signatoryname"}
                , new Object[]{"caption", "Signatory Name"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "signatorytitle"}
                , new Object[]{"caption", "Signatory Title"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "org"}
                , new Object[]{"caption", "Office"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "level"}
                , new Object[]{"caption", "Level"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            })
        });
        xDataTable2.setDepends(new String[] {"entity.signatorygroup"});
        xDataTable2.setHandler("signatoryItemHandler");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xFormPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addComponent(xDataTable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xDataTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Signatory Group", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setPadding(new java.awt.Insets(25, 10, 10, 10));
        xTitledBorder2.setTitle("Job Order Appointment Group Information");
        xHorizontalPanel1.setBorder(xTitledBorder2);
        xHorizontalPanel1.setPreferredSize(new java.awt.Dimension(1000, 300));

        xFormPanel2.setPreferredSize(new java.awt.Dimension(500, 100));

        xSuggest1.setCaption("Group Name");
        xSuggest1.setCaptionWidth(140);
        xSuggest1.setExpression("#{item.appointmentgroupname}");
        xSuggest1.setHandler("groupHandler");
        xSuggest1.setItemExpression("");
        xSuggest1.setName("entity.appointmentgroupname"); // NOI18N
        xSuggest1.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xSuggest1);

        xDateField1.setCaption("Effective from");
        xDateField1.setCaptionWidth(140);
        xDateField1.setName("entity.effectivefrom"); // NOI18N
        xDateField1.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xDateField1);

        xDateField2.setCaption("Effective Until");
        xDateField2.setCaptionWidth(140);
        xDateField2.setName("entity.effectiveuntil"); // NOI18N
        xDateField2.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xDateField2);

        xLookupField1.setCaption("Nature of Appointment");
        xLookupField1.setCaptionWidth(140);
        xLookupField1.setExpression("#{item.name}");
        xLookupField1.setHandler("lookup:tagabukid_hrmis_natureofappointment");
        xLookupField1.setName("entity.natureofappointment"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xLookupField1);

        xDateField3.setCaption("Date Issued");
        xDateField3.setCaptionWidth(140);
        xDateField3.setName("entity.dateissued"); // NOI18N
        xDateField3.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xDateField3);

        xLookupField2.setCaption("Office");
        xLookupField2.setCaptionWidth(140);
        xLookupField2.setExpression("#{item.name}");
        xLookupField2.setHandler("lookup:tagabukid_hrmis_org");
        xLookupField2.setName("entity.org"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xLookupField2);

        xTextField3.setCaption("Remarks");
        xTextField3.setCaptionWidth(140);
        xTextField3.setName("entity.remarks"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(200, 20));
        xFormPanel2.add(xTextField3);

        xLabel1.setCaption("State");
        xLabel1.setCaptionFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        xLabel1.setCaptionWidth(140);
        xLabel1.setExpression("#{entity.state}");
        xLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        xLabel1.setName("entity.state"); // NOI18N
        xLabel1.setPreferredSize(new java.awt.Dimension(200, 40));
        xFormPanel2.add(xLabel1);

        xHorizontalPanel1.add(xFormPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XDataTable xDataTable2;
    private com.rameses.rcp.control.XDataTable xDataTable7;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XDateField xDateField2;
    private com.rameses.rcp.control.XDateField xDateField3;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XLookupField xLookupField3;
    private com.rameses.rcp.control.XSuggest xSuggest1;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
}

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
public class HRMISAppointmentCasualCRUDPage extends javax.swing.JPanel {

    /**
     * Creates new form HRMISAppointmentCasualCRUDPage
     */
    public HRMISAppointmentCasualCRUDPage() {
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
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xSuggest1 = new com.rameses.rcp.control.XSuggest();
        xLookupField8 = new com.rameses.rcp.control.XLookupField();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xDateField2 = new com.rameses.rcp.control.XDateField();
        xLookupField4 = new com.rameses.rcp.control.XLookupField();
        xDateField3 = new com.rameses.rcp.control.XDateField();
        jPanel3 = new javax.swing.JPanel();
        xFormPanel5 = new com.rameses.rcp.control.XFormPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        xTextArea2 = new com.rameses.rcp.control.XTextArea();
        xLabel7 = new com.rameses.rcp.control.XLabel();
        xLabel4 = new com.rameses.rcp.control.XLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        jPanel1 = new javax.swing.JPanel();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xDataTable2 = new com.rameses.rcp.control.XDataTable();

        xHorizontalPanel1.setBorderSeparator(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xHorizontalPanel1.setPreferredSize(new java.awt.Dimension(1008, 165));

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Appointment Info");
        xFormPanel1.setBorder(xTitledBorder1);
        xFormPanel1.setCaptionWidth(160);
        xFormPanel1.setName(""); // NOI18N
        xFormPanel1.setPreferredSize(new java.awt.Dimension(500, 100));

        xLabel1.setBorder(new com.rameses.rcp.control.border.XLineBorder());
        xLabel1.setCaption("Doc No.");
        xLabel1.setExpression("#{entity.appno}");
        xLabel1.setOpaque(true);
        xLabel1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLabel1);

        xSuggest1.setCaption("Group Name");
        xSuggest1.setHandler("suggestGroupName");
        xSuggest1.setName("entity.appointmentgroupname"); // NOI18N
        xSuggest1.setPreferredSize(new java.awt.Dimension(0, 20));
        xSuggest1.setRequired(true);
        xSuggest1.setTextCase(com.rameses.rcp.constant.TextCase.UPPER);
        xFormPanel1.add(xSuggest1);

        xLookupField8.setCaption("Org Unit");
        xLookupField8.setExpression("#{item.name}");
        xLookupField8.setHandler("lookup:tagabukid_hrmis_org");
        xLookupField8.setName("entity.org"); // NOI18N
        xLookupField8.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField8.setRequired(true);
        xFormPanel1.add(xLookupField8);

        xLookupField1.setCaption("Tranche");
        xLookupField1.setExpression("#{item.name}");
        xLookupField1.setHandler("lookup:tagabukid_hrmis_tranche");
        xLookupField1.setName("entity.currentsalarystep"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField1.setRequired(true);
        xFormPanel1.add(xLookupField1);

        xDateField1.setCaption("Effective From");
        xDateField1.setName("entity.effectivefrom"); // NOI18N
        xDateField1.setRequired(true);
        xFormPanel1.add(xDateField1);

        xDateField2.setCaption("Effective Until");
        xDateField2.setName("entity.effectiveuntil"); // NOI18N
        xDateField2.setRequired(true);
        xFormPanel1.add(xDateField2);

        xLookupField4.setCaption("Nature of Appointment");
        xLookupField4.setExpression("#{item.name}");
        xLookupField4.setHandler("lookup:tagabukid_hrmis_natureofappointment");
        xLookupField4.setName("entity.natureofappointment"); // NOI18N
        xLookupField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField4.setRequired(true);
        xFormPanel1.add(xLookupField4);

        xDateField3.setCaption("Date Issued");
        xDateField3.setName("entity.dateissued"); // NOI18N
        xDateField3.setRequired(true);
        xFormPanel1.add(xDateField3);

        xHorizontalPanel1.add(xFormPanel1);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Transaction Info");
        jPanel3.setBorder(xTitledBorder2);
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 232));

        xFormPanel5.setPreferredSize(new java.awt.Dimension(500, 100));

        jScrollPane2.setBorder(new com.rameses.rcp.control.border.XLineBorder());
        jScrollPane2.setPreferredSize(new java.awt.Dimension(0, 40));

        xTextArea2.setEditable(false);
        xTextArea2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        xTextArea2.setForeground(new java.awt.Color(204, 0, 0));
        xTextArea2.setName("entity.state"); // NOI18N
        xTextArea2.setShowCaption(false);
        jScrollPane2.setViewportView(xTextArea2);

        xFormPanel5.add(jScrollPane2);

        xLabel7.setBorder(new com.rameses.rcp.control.border.XLineBorder());
        xLabel7.setCaption("Created By");
        xLabel7.setExpression("#{entity.recordlog.createdbyuser}");
        xLabel7.setFor("");
        xLabel7.setName(""); // NOI18N
        xLabel7.setOpaque(true);
        xLabel7.setPreferredSize(new java.awt.Dimension(0, 16));
        xFormPanel5.add(xLabel7);

        xLabel4.setBorder(new com.rameses.rcp.control.border.XLineBorder());
        xLabel4.setCaption("Date Created");
        xLabel4.setExpression("#{entity.recordlog.datecreated}");
        xLabel4.setFor("");
        xLabel4.setOpaque(true);
        xLabel4.setPreferredSize(new java.awt.Dimension(0, 16));
        xFormPanel5.add(xLabel4);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 63));

        xTextArea1.setCaption("Remarks");
        xTextArea1.setName("entity.remarks"); // NOI18N
        jScrollPane1.setViewportView(xTextArea1);

        xFormPanel5.add(jScrollPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(xFormPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(xFormPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );

        xHorizontalPanel1.add(jPanel3);

        xDataTable1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "pds"}
                , new Object[]{"caption", "Name"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", "entity.state == 'DRAFT'"}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{(item.pds.prenametitle == '' ||  !item.pds.prenametitle ?'': item.pds.prenametitle + ' ') + item.pds.person.name + (item.pds.nameextension=='' || !item.pds.nameextension?'' :' ' + item.pds.nameextension)}", "lookup:tagabukid_hrmis_pds")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "plantilla"}
                , new Object[]{"caption", "Plantilla Info"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", "entity.state == 'DRAFT'"}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.plantilla.jobposition.name + ' - ' + item.plantilla.itemno}", "lookup:tagabukid_hrmis_vacantcasualplantilla")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "dailywage"}
                , new Object[]{"caption", "Daily Wage"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", true}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.DecimalColumnHandler("#,##0.0000", -1.0, -1.0, false, 2)}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "attachment"}
                , new Object[]{"caption", "Attachment"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", "entity.state == 'DRAFT'"}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.attachment}", "lookupAttachment")}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "cutoffdate"}
                , new Object[]{"caption", "Cut-off Date"}
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
                new Object[]{"name", "cutoffreason"}
                , new Object[]{"caption", "Cut-off Reason"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", true}
                , new Object[]{"editableWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.LookupColumnHandler("#{item.cutoffreason.name}", "lookup:tagabukid_hrmis_servicerecordaction")}
            })
        });
        xDataTable1.setDepends(new String[] {"entity.currentsalarystep"});
        xDataTable1.setDynamic(true);
        xDataTable1.setHandler("appointmentitemListHandler");
        xDataTable1.setName("selectedAppointmentItem"); // NOI18N
        jTabbedPane1.addTab("Appointment Items", xDataTable1);

        xLookupField2.setCaption("Signatory Group");
        xLookupField2.setCaptionWidth(100);
        xLookupField2.setExpression("#{item.signatorygroupname}");
        xLookupField2.setHandler("signatorygroup:lookup");
        xLookupField2.setName("entity.signatorygroup"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(300, 20));
        xFormPanel3.add(xLookupField2);

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
                new Object[]{"name", "signatoryheading"}
                , new Object[]{"caption", "Heading"}
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
        xDataTable2.setDynamic(true);
        xDataTable2.setHandler("signatoryItemHandler");
        xDataTable2.setName("selectedSignaotryItem"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xFormPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                    .addComponent(xDataTable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xDataTable2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Signatory", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xHorizontalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XDataTable xDataTable2;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XDateField xDateField2;
    private com.rameses.rcp.control.XDateField xDateField3;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XFormPanel xFormPanel5;
    private com.rameses.rcp.control.XHorizontalPanel xHorizontalPanel1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel4;
    private com.rameses.rcp.control.XLabel xLabel7;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XLookupField xLookupField4;
    private com.rameses.rcp.control.XLookupField xLookupField8;
    private com.rameses.rcp.control.XSuggest xSuggest1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    private com.rameses.rcp.control.XTextArea xTextArea2;
    // End of variables declaration//GEN-END:variables
}

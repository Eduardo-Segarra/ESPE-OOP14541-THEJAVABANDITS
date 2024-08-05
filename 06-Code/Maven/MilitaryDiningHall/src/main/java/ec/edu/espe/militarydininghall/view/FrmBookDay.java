/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.controller.CloudController;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.util.Map;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import utils.DataCollection;
import utils.Validation;

/**
 *
 * @author ,The Javabandits, DCCO-ESPE
 */
public class FrmBookDay extends javax.swing.JFrame {

    /**
     * Creates new form ChefMenu
     */
    public FrmBookDay() {
        initComponents();
    }

    private double userBalance;
    private long id;
    private String userId, userName, userType;
    private static final double reservationCost = 7.5F;
    private static final int actualYear = LocalDate.now().getYear();

    public FrmBookDay(String id, String name, String type, double balance) {
        initComponents();
        this.userId = id;
        this.userName = name;
        this.userType = type;
        this.userBalance = balance;
        this.id = Long.parseLong(id);
        customizeComboBoxes();
    }

    private void customizeComboBoxes() {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbMonth.getModel();
        model.removeAllElements();
        for (int i = currentMonth; i <= 12; i++) {
            model.addElement(String.valueOf(i));
        }
        cmbMonth.addActionListener(new MonthComboBoxListener());

        updateDaysComboBox(currentMonth);
    }

    private void updateDaysComboBox(int selectedMonth) {
        cmbDay.removeAllItems();
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int daysInMonth = YearMonth.of(today.getYear(), selectedMonth).lengthOfMonth();
        for (int i = 1; i <= daysInMonth; i++) {
            if (selectedMonth == today.getMonthValue() && i < currentDay) {
                continue;
            }
            cmbDay.addItem(String.valueOf(i));
        }
    }

    private class MonthComboBoxListener implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent evt) {
            String selectedItem = (String) cmbMonth.getSelectedItem();
            if (selectedItem != null) {
                int selectedMonth = Integer.parseInt(selectedItem);
                updateDaysComboBox(selectedMonth);
            }
        }
    }

    private String getSelectedDate() {
        String day = cmbDay.getSelectedItem().toString();
        String month = cmbMonth.getSelectedItem().toString();
        return day + "/" + month + "/" + actualYear;
    }

    private boolean hasSufficientBalance(double balance) {
        return balance >= reservationCost;
    }

    private boolean isDateAlreadyReserved(DateBook dateBook, String date) {
        return dateBook.getReservedDays().containsKey(date);
    }

    private void saveReservation(DateBook dateBook, String date) {
        dateBook.addDay(date, false);
        DateBook orderedDays = CloudController.orderingOfDays(dateBook);
        CloudController.saveDateBook(orderedDays);
    }

    private void deductReservationCost() {
        CloudController.updateCommensalBalance(userId, -reservationCost);
        userBalance -= reservationCost;
    }

    private void navigateToUserMenu() {
        switch (userType) {
            case "commensal" -> {
                FrmCommensalMenu frmCommensalMenu = new FrmCommensalMenu(userName, userId, userBalance, userType);
                this.setVisible(false);
                frmCommensalMenu.setVisible(true);
            }
            case "administrators" -> {
                FrmAdminMenu frmAdminMenu = new FrmAdminMenu(userName, userBalance, userType, userId);
                this.setVisible(false);
                frmAdminMenu.setVisible(true);
            }
            case "generalAdministrator" -> {
                FrmGeneralAdmin frmGeneralAdmin = new FrmGeneralAdmin(userName, userId, userBalance, userType);
                this.setVisible(false);
                frmGeneralAdmin.setVisible(true);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btmSave = new javax.swing.JButton();
        cmbMonth = new javax.swing.JComboBox<>();
        cmbDay = new javax.swing.JComboBox<>();
        btmBack = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblAmountToPay = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(50, 91, 14));

        jLabel2.setBackground(new java.awt.Color(187, 187, 187));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Elija un mes para agendar:");

        jLabel7.setBackground(new java.awt.Color(187, 187, 187));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ingrese el dia:");

        btmSave.setBackground(new java.awt.Color(132, 82, 31));
        btmSave.setText("Guardar");
        btmSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSaveActionPerformed(evt);
            }
        });

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthActionPerformed(evt);
            }
        });

        cmbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        btmBack.setBackground(new java.awt.Color(132, 82, 31));
        btmBack.setText("Volver");
        btmBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmBackActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(187, 187, 187));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nota: Solo se podran agendar hasta el 31 de diciembre del presente año");

        jLabel1.setBackground(new java.awt.Color(187, 187, 187));
        jLabel1.setFont(new java.awt.Font("Artifakt Element Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reservar un dia");

        lblAmountToPay.setForeground(new java.awt.Color(255, 255, 255));
        lblAmountToPay.setText("La cantidad de dinero a pagar es: $7.50");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(133, 133, 133))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lblAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btmSave)
                        .addGap(81, 81, 81)
                        .addComponent(btmBack)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAmountToPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btmSave)
                    .addComponent(btmBack))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSaveActionPerformed
        String selectedDate = getSelectedDate();
        DateBook dateBook = CloudController.getDateBook(id);

        if (!hasSufficientBalance(userBalance)) {
            Validation.showInfoMessage(this, "No tiene el dinero suficiente para hacer una reservacion.");
            return;
        }

        if (isDateAlreadyReserved(dateBook, selectedDate)) {
            Validation.showInfoMessage(this, "El dia ya esta registrado en sus reservaciones.");
            return;
        }

        saveReservation(dateBook, selectedDate);
        deductReservationCost();
        Validation.showInfoMessage(this, "Guardada correctamente la reservacion.");
    }//GEN-LAST:event_btmSaveActionPerformed

    private void btmBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmBackActionPerformed
        navigateToUserMenu();
    }//GEN-LAST:event_btmBackActionPerformed

    private void cmbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMonthActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmBookDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBookDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBookDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBookDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBookDay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmBack;
    private javax.swing.JButton btmSave;
    private javax.swing.JComboBox<String> cmbDay;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAmountToPay;
    // End of variables declaration//GEN-END:variables
}

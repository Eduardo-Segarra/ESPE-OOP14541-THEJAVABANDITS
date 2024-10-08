/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.controller.CloudController;
import java.time.LocalDate;
import java.util.List;
import org.bson.Document;
import utils.LabelsActions;

/**
 *
 * @author The Javabandits, DCCO-ESPE
 */
public class FrmChefMenu extends javax.swing.JFrame {

    private String chefName;

    /**
     * Creates new form ChefMenu
     */
    public FrmChefMenu() {
        initComponents();
    }

    public FrmChefMenu(String name) {
        initComponents();
        List<Document> documents = CloudController.getInstance().getMenuInformation();

        this.chefName = name;

        LabelsActions.settingName(lblNameOfTheChef, chefName);
        LabelsActions.summaryOfTheMenu(documents, lblAvailablePlates, lblBreakfast, lblAvailableBreakfast, lblLunch, 
                lblAvailableLunch, lblSnack, lblAvailableSnack);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblNameOfTheChef = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblAvailablePlates1 = new javax.swing.JLabel();
        lblAvailablePlates = new javax.swing.JLabel();
        lblBreakfast = new javax.swing.JLabel();
        lblLunch = new javax.swing.JLabel();
        lblSnack = new javax.swing.JLabel();
        lblAvailableSnack = new javax.swing.JLabel();
        lblAvailableLunch = new javax.swing.JLabel();
        lblAvailableBreakfast = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itmLogout = new javax.swing.JMenuItem();
        itmExit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itmRegistMenuForADay = new javax.swing.JMenuItem();
        itmSeeTheRegisterOfTheMenus = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(50, 91, 14));

        lblNameOfTheChef.setFont(new java.awt.Font("Artifakt Element Black", 0, 20)); // NOI18N
        lblNameOfTheChef.setForeground(new java.awt.Color(255, 255, 255));
        lblNameOfTheChef.setText("Bienvenido \"name military chef\"");

        jPanel3.setBackground(new java.awt.Color(50, 54, 14));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        lblAvailablePlates1.setFont(new java.awt.Font("Artifakt Element Medium", 0, 14)); // NOI18N
        lblAvailablePlates1.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailablePlates1.setText("Proximas reservaciones:");

        lblAvailablePlates.setFont(new java.awt.Font("Artifakt Element Medium", 0, 14)); // NOI18N
        lblAvailablePlates.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailablePlates.setText("Este mes los platos son:");

        lblBreakfast.setFont(new java.awt.Font("Artifakt Element", 0, 14)); // NOI18N
        lblBreakfast.setForeground(new java.awt.Color(255, 255, 255));
        lblBreakfast.setText("Desayuno:");

        lblLunch.setFont(new java.awt.Font("Artifakt Element", 0, 14)); // NOI18N
        lblLunch.setForeground(new java.awt.Color(255, 255, 255));
        lblLunch.setText("Almuerzo:");

        lblSnack.setFont(new java.awt.Font("Artifakt Element", 0, 14)); // NOI18N
        lblSnack.setForeground(new java.awt.Color(255, 255, 255));
        lblSnack.setText("Merienda:");

        lblAvailableSnack.setFont(new java.awt.Font("Artifakt Element", 0, 14)); // NOI18N
        lblAvailableSnack.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailableSnack.setText("jLabel7");

        lblAvailableLunch.setFont(new java.awt.Font("Artifakt Element", 0, 14)); // NOI18N
        lblAvailableLunch.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailableLunch.setText("jLabel6");

        lblAvailableBreakfast.setFont(new java.awt.Font("Artifakt Element", 0, 14)); // NOI18N
        lblAvailableBreakfast.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailableBreakfast.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAvailableBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvailablePlates1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblAvailablePlates)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblSnack)
                                        .addGap(28, 28, 28)
                                        .addComponent(lblAvailableSnack, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblLunch)
                                        .addGap(31, 31, 31)
                                        .addComponent(lblAvailableLunch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblBreakfast)))
                            .addComponent(lblNameOfTheChef))))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblNameOfTheChef, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAvailablePlates1)
                .addGap(18, 18, 18)
                .addComponent(lblAvailablePlates)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBreakfast)
                    .addComponent(lblAvailableBreakfast))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLunch)
                    .addComponent(lblAvailableLunch))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSnack)
                    .addComponent(lblAvailableSnack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Comedor Militar");

        itmLogout.setText("Cerrar sesion");
        itmLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(itmLogout);

        itmExit.setText("Salir");
        itmExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExitActionPerformed(evt);
            }
        });
        jMenu1.add(itmExit);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Gestión del menú");

        itmRegistMenuForADay.setText("Registrar el menu para un día");
        itmRegistMenuForADay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRegistMenuForADayActionPerformed(evt);
            }
        });
        jMenu3.add(itmRegistMenuForADay);

        itmSeeTheRegisterOfTheMenus.setText("Ver los registros");
        itmSeeTheRegisterOfTheMenus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSeeTheRegisterOfTheMenusActionPerformed(evt);
            }
        });
        jMenu3.add(itmSeeTheRegisterOfTheMenus);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmLogoutActionPerformed
        FrmLogin frmLogin = new FrmLogin();
        this.setVisible(false);
        frmLogin.setVisible(true);
    }//GEN-LAST:event_itmLogoutActionPerformed

    private void itmExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itmExitActionPerformed

    private void itmRegistMenuForADayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRegistMenuForADayActionPerformed
        FrmChooseMontyMenu frmChooseMontyMenu = new FrmChooseMontyMenu(chefName);
        this.setVisible(false);
        frmChooseMontyMenu.setVisible(true);
    }//GEN-LAST:event_itmRegistMenuForADayActionPerformed

    private void itmSeeTheRegisterOfTheMenusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSeeTheRegisterOfTheMenusActionPerformed
        FrmSeeTheRegisterOfTheMenus frmSeeTheRegisterOfTheMenus = new FrmSeeTheRegisterOfTheMenus(chefName);
        this.setVisible(false);
        frmSeeTheRegisterOfTheMenus.setVisible(true);
    }//GEN-LAST:event_itmSeeTheRegisterOfTheMenusActionPerformed

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
            java.util.logging.Logger.getLogger(FrmChefMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmChefMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmChefMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmChefMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmChefMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itmExit;
    private javax.swing.JMenuItem itmLogout;
    private javax.swing.JMenuItem itmRegistMenuForADay;
    private javax.swing.JMenuItem itmSeeTheRegisterOfTheMenus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAvailableBreakfast;
    private javax.swing.JLabel lblAvailableLunch;
    private javax.swing.JLabel lblAvailablePlates;
    private javax.swing.JLabel lblAvailablePlates1;
    private javax.swing.JLabel lblAvailableSnack;
    private javax.swing.JLabel lblBreakfast;
    private javax.swing.JLabel lblLunch;
    private javax.swing.JLabel lblNameOfTheChef;
    private javax.swing.JLabel lblSnack;
    // End of variables declaration//GEN-END:variables
}

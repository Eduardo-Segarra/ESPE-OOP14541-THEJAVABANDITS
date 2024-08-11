/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ec.edu.espe.militarydininghall.controller.CloudController;
import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import ec.edu.espe.militarydininghall.model.Dish;
import ec.edu.espe.militarydininghall.view.FrmAdminMenu;
import ec.edu.espe.militarydininghall.view.FrmBookDay;
import static ec.edu.espe.militarydininghall.view.FrmBookDay.userBalance;
import ec.edu.espe.militarydininghall.view.FrmChefMenu;
import ec.edu.espe.militarydininghall.view.FrmCommensalMenu;
import ec.edu.espe.militarydininghall.view.FrmEditRegisterWithTheIDSearched;
import ec.edu.espe.militarydininghall.view.FrmEstablishAdministratorSearch;
import ec.edu.espe.militarydininghall.view.FrmGeneralAdmin;
import ec.edu.espe.militarydininghall.view.FrmUpdateAccountBalanceCommensalID;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class InterfacesActions {

    public static void navigateToUserMenuFromLogin(JFrame parentFrame, String loginResponse) {
        String id = DataCollection.obtainIdFromJSON(loginResponse);
        String name = DataCollection.obtainNameFromJSON(loginResponse);
        String type = DataCollection.obtainTypeFromJSON(loginResponse);
        double accountBalance = DataCollection.obtainBalanceFromJSON(loginResponse);

        switch (type) {
            case "commensal" -> {
                FrmCommensalMenu frmCommensalMenu = new FrmCommensalMenu(name, id, accountBalance, type);
                parentFrame.setVisible(false);
                frmCommensalMenu.setVisible(true);
            }
            case "administrators" -> {
                FrmAdminMenu frmAdminMenu = new FrmAdminMenu(name, accountBalance, type, id);
                parentFrame.setVisible(false);
                frmAdminMenu.setVisible(true);
            }
            case "militaryChef" -> {
                FrmChefMenu frmChefMenu = new FrmChefMenu(name);
                parentFrame.setVisible(false);
                frmChefMenu.setVisible(true);
            }
            case "generalAdministrator" -> {
                FrmGeneralAdmin frmGeneralAdmin = new FrmGeneralAdmin(name, id, accountBalance, type);
                parentFrame.setVisible(false);
                frmGeneralAdmin.setVisible(true);
            }
        }
    }

    public static void navigateToUserMenu(JFrame parentFrame, String name, String id, double accountBalance, String type) {
        switch (type) {
            case "commensal" -> {
                FrmCommensalMenu frmCommensalMenu = new FrmCommensalMenu(name, id, accountBalance, type);
                parentFrame.setVisible(false);
                frmCommensalMenu.setVisible(true);
            }
            case "administrators" -> {
                FrmAdminMenu frmAdminMenu = new FrmAdminMenu(name, accountBalance, type, id);
                parentFrame.setVisible(false);
                frmAdminMenu.setVisible(true);
            }
            case "militaryChef" -> {
                FrmChefMenu frmChefMenu = new FrmChefMenu(name);
                parentFrame.setVisible(false);
                frmChefMenu.setVisible(true);
            }
            case "generalAdministrator" -> {
                FrmGeneralAdmin frmGeneralAdmin = new FrmGeneralAdmin(name, id, accountBalance, type);
                parentFrame.setVisible(false);
                frmGeneralAdmin.setVisible(true);
            }
        }
    }

    public static void creatingAccount(JFrame parentFrame, String name, String email, String id, String password, String grade) {
        String modifiedPassword = Validation.modifyPassword(password, 1);
        Commensal commensal = new Commensal(id, name, email, modifiedPassword, grade, "commensal", 0.0F);
        CloudController.create(commensal);

        FrmCommensalMenu frmCommensalMenu = new FrmCommensalMenu(commensal.getName(), commensal.getId(), commensal.getBalance(),
                commensal.getType());
        parentFrame.setVisible(false);
        frmCommensalMenu.setVisible(true);
    }

    public static void saveDayActionPerformed(JFrame parentFrame, JComboBox cmbDay, JComboBox cmbMonth, long id,
            JTextField txfAmountOfPeople, String userId) {
        String selectedDate = DataCollection.getSelectedDate(cmbDay, cmbMonth);
        DateBook dateBook = CloudController.getDateBook(id);

        if (!Validation.theAmountOfPeopleIsCorrect(txfAmountOfPeople)) {
            Validation.showInfoMessage(parentFrame, "La cantidad de personas deber ser un numero entero positivo.");
            return;
        }

        if (Validation.isDateAlreadyReserved(dateBook, selectedDate)) {
            Validation.showInfoMessage(parentFrame, "El dia ya esta registrado en sus reservaciones.");
            return;
        }

        if (!Validation.hasSufficientBalance(userBalance, Integer.parseInt(txfAmountOfPeople.getText()))) {
            Validation.showInfoMessage(parentFrame, "No tiene el dinero suficiente para hacer una reservacion.");
            return;
        }

        DataCollection.saveReservation(dateBook, selectedDate);
        DataCollection.deductReservationCost(userId, Integer.parseInt(txfAmountOfPeople.getText()));
        Validation.showInfoMessage(parentFrame, "Guardada correctamente la reservacion.");
    }

    public static void cancelAppoinmentActionPerformer(JFrame parentFrame, long id, JComboBox cmbMonth, JComboBox cmbDay) {
        LocalDate today = LocalDate.now();
        DateBook dateBook = CloudController.getDateBook(id);

        String date = cmbDay.getSelectedItem().toString() + "/" + cmbMonth.getSelectedItem().toString() + "/" + today.getYear();
        LocalDate dateSearch = LocalDate.of(today.getYear(), Integer.parseInt(cmbMonth.getSelectedItem().toString()),
                Integer.parseInt(cmbDay.getSelectedItem().toString()));

        if (Validation.confirmReservationCancellation(date, parentFrame)) {
            Validation.processReservationCancellation(parentFrame, dateBook, dateSearch, today, date);
        }
    }

    public static void savingAMenu(String date, JTextField txfBreakfast, JTextField txfDinner, JTextField txfSnack) {
        Dish dish = new Dish(date, txfBreakfast.getText(), txfDinner.getText(), txfSnack.getText());
        CloudController.saveMenu(dish);
    }

    public static void theEnterIdIsRight(JFrame parentFrame, JTextField txfId, String id, String name, double balance, String type,
            String nameOfTheClass) {
        String accountData = DataCollection.gettingTheAccountData(txfId);

        if (!Validation.theEnteredIdExists(accountData)) {
            Validation.showErrorMessage(parentFrame, "La cedula ingresada no existe.");
            return;
        }

        switch (nameOfTheClass) {
            case "FrmEstablishAdministrator" -> {
                Commensal commensal = DataCollection.sendingTheData(accountData);

                FrmEstablishAdministratorSearch frmEstablishAdministratorSearch = new FrmEstablishAdministratorSearch(commensal, id,
                        name, balance, type);
                FrmEstablishAdministratorSearch.commensal = commensal;
                parentFrame.setVisible(false);
                frmEstablishAdministratorSearch.setVisible(true);
            }
            case "FrmEditRegister" -> {
                Commensal commensal = DataCollection.sendingTheData(accountData);

                FrmEditRegisterWithTheIDSearched frmEdit = new FrmEditRegisterWithTheIDSearched(commensal, id, name, balance, type);
                parentFrame.setVisible(false);
                frmEdit.setVisible(true);
            }
            case "FrmUpdateAccountBalance" -> {
                Commensal commensal = DataCollection.sendingTheData(accountData);

                FrmUpdateAccountBalanceCommensalID frmUpdateAccountBalanceCommensalID = new FrmUpdateAccountBalanceCommensalID(commensal,
                        name, balance, type, id);
                parentFrame.setVisible(false);
                frmUpdateAccountBalanceCommensalID.setVisible(true);
            }
        }
    }

    public static void establishANewAdministrator(JFrame parentFrame, String name, String id, double balance, String type) {
        if (DataCollection.creatingANewAdministrator()) {
            FrmGeneralAdmin frmGeneralAdmin = new FrmGeneralAdmin(name, id, balance, type);
            parentFrame.setVisible(false);
            frmGeneralAdmin.setVisible(true);
        } else {
            Validation.showErrorMessage(parentFrame, "Un error a ocurrido al crear un nuevo administrador.");
        }
    }

    public static void updateAccountBalance(JFrame parentFrame, Commensal commensal, JTextField txfMoney, String name,
            double balance, String type, String id) {
        try {
            double newBalance = Double.parseDouble(txfMoney.getText());

            if (!Validation.ValidBalance(newBalance)) {
                Validation.showInfoMessage(parentFrame, "El saldo no puede ser negativo.");
                return;
            }

            if (!Validation.ValidCommensal(commensal)) {
                Validation.showInfoMessage(parentFrame, "El objeto comensal no se inicializa correctamente.");
                return;
            }

            if (CloudController.updateCommensalBalance(commensal.getId(), newBalance)) {
                Validation.showInfoMessage(parentFrame, "Saldo de cuenta actualizado exitosamente!");
                if (commensal.getName().equalsIgnoreCase(name)) {
                    new FrmAdminMenu(name, balance + newBalance, type, id).setVisible(true);
                } else {
                    new FrmAdminMenu(name, balance, type, id).setVisible(true);
                }
                parentFrame.dispose();
            } else {
                Validation.showErrorMessage(parentFrame, "No se pudo actualizar el saldo");
            }
        } catch (NumberFormatException e) {
            Validation.showErrorMessage(parentFrame, "Por favor ingrese una cantidad válida.");
        }
    }
}

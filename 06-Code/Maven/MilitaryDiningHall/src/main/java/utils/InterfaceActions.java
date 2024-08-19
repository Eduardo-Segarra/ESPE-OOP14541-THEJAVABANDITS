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
public class InterfaceActions {
public static void navigateToUserMenuFromLogin(JFrame parentFrame, String loginResponse) {
        String id = DataCollection.obtainIdFromJSON(loginResponse);
        String name = DataCollection.obtainNameFromJSON(loginResponse);
        String type = DataCollection.obtainTypeFromJSON(loginResponse);
        double accountBalance = DataCollection.obtainBalanceFromJSON(loginResponse);
        
        navigateToUserMenu(parentFrame, name, id, accountBalance, type);
    }

    public static void navigateToUserMenu(JFrame parentFrame, String name, String id, double accountBalance, String type) {
        JFrame newFrame = null;

        switch (type) {
            case "commensal":
                newFrame = new FrmCommensalMenu(name, id, accountBalance, type);
                break;
            case "administrators":
                newFrame = new FrmAdminMenu(name, accountBalance, type, id);
                break;
            case "militaryChef":
                newFrame = new FrmChefMenu(name);
                break;
            case "generalAdministrator":
                newFrame = new FrmGeneralAdmin(name, id, accountBalance, type);
                break;
        }

        if (newFrame != null) {
            parentFrame.setVisible(false);
            newFrame.setVisible(true);
        }
    }

    public static void creatingAccount(JFrame parentFrame, String name, String email, String id, String password, String grade) {
        String modifiedPassword = Validation.modifyPassword(password, 1);
        Commensal commensal = new Commensal(id, name, email, modifiedPassword, grade, "commensal", 0.0F);
        CloudController.create(commensal);
        navigateToUserMenu(parentFrame, commensal.getName(), commensal.getId(), commensal.getBalance(), commensal.getType());
    }

    public static void saveDayActionPerformed(JFrame parentFrame, JComboBox<String> cmbDay, JComboBox<String> cmbMonth, long id,
            JTextField txfAmountOfPeople, String userId) {
        String selectedDate = DataCollection.getSelectedDate(cmbDay, cmbMonth);
        DateBook dateBook = CloudController.getDateBook(id);

        if (!isValidReservation(txfAmountOfPeople, dateBook, selectedDate)) {
            return;
        }

        DataCollection.saveReservation(dateBook, selectedDate);
        DataCollection.deductReservationCost(userId, Integer.parseInt(txfAmountOfPeople.getText()));
        Validation.showInfoMessage(parentFrame, "Guardada correctamente la reservacion.");
    }

    public static void cancelAppoinmentActionPerformer(JFrame parentFrame, long id, JComboBox<String> cmbMonth, JComboBox<String> cmbDay) {
        LocalDate today = LocalDate.now();
        DateBook dateBook = CloudController.getDateBook(id);

        String date = formatDate(cmbDay, cmbMonth, today);
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

        JFrame newFrame = createFrameFromClassName(nameOfTheClass, DataCollection.sendingTheData(accountData), id, name, balance, type);
        if (newFrame != null) {
            parentFrame.setVisible(false);
            newFrame.setVisible(true);
        }
    }

    public static void establishANewAdministrator(JFrame parentFrame, String name, String id, double balance, String type) {
        if (DataCollection.creatingANewAdministrator()) {
            navigateToUserMenu(parentFrame, name, id, balance, type);
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
                navigateToUserMenu(parentFrame, name, id, balance + newBalance, type);
            } else {
                Validation.showErrorMessage(parentFrame, "No se pudo actualizar el saldo");
            }
        } catch (NumberFormatException e) {
            Validation.showErrorMessage(parentFrame, "Por favor ingrese una cantidad válida.");
        }
    }

    private static boolean isValidReservation(JTextField txfAmountOfPeople, DateBook dateBook, String selectedDate) {
        if (!Validation.theAmountOfPeopleIsCorrect(txfAmountOfPeople)) {
            Validation.showInfoMessage(null, "La cantidad de personas debe ser un número entero positivo.");
            return false;
        }

        if (Validation.isDateAlreadyReserved(dateBook, selectedDate)) {
            Validation.showInfoMessage(null, "El día ya está registrado en sus reservaciones.");
            return false;
        }

        if (!Validation.hasSufficientBalance(userBalance, Integer.parseInt(txfAmountOfPeople.getText()))) {
            Validation.showInfoMessage(null, "No tiene el dinero suficiente para hacer una reservación.");
            return false;
        }
        return true;
    }

    private static String formatDate(JComboBox<String> cmbDay, JComboBox<String> cmbMonth, LocalDate today) {
        return cmbDay.getSelectedItem().toString() + "/" + cmbMonth.getSelectedItem().toString() + "/" + today.getYear();
    }

    private static JFrame createFrameFromClassName(String className, Commensal commensal, String id, String name, double balance, String type) {
        switch (className) {
            case "FrmEstablishAdministrator":
                return new FrmEstablishAdministratorSearch(commensal, id, name, balance, type);
            case "FrmEditRegister":
                return new FrmEditRegisterWithTheIDSearched(commensal, id, name, balance, type);
            case "FrmUpdateAccountBalance":
                return new FrmUpdateAccountBalanceCommensalID(commensal, name, balance, type, id);
            default:
                return null;
        }
    }
}

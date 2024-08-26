package utils;

import ec.edu.espe.militarydininghall.controller.CloudController;
import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import ec.edu.espe.militarydininghall.view.FrmBookDay;
import static ec.edu.espe.militarydininghall.view.FrmBookDay.reservationCost;
import ec.edu.espe.militarydininghall.view.FrmCancelAppointment;
import ec.edu.espe.militarydininghall.view.FrmRegistMenuForADay;
import java.awt.Color;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.bson.Document;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class Validation {

    public static boolean ValidCommensal(Commensal commensal) {
        return commensal != null && commensal.getId() != null && !commensal.getId().isEmpty();
    }

    public static boolean ValidBalance(double balance) {
        return balance >= 0;
    }

    public static boolean theEnteredIdExists(String accountData) {
        return accountData != null;
    }

    public static boolean validateId(String idInput) {
        long id = 0;
        if (idInput.length() == 10) {
            id = Long.parseLong(idInput);
        } else {
            return false;
        }
        long[] digits = new long[10];
        long remainder;
        long doubledDigit;
        long evenPositionSum = 0;
        long oddPositionSum = 0;
        long totalSum;
        long checkDigit;

        for (int i = 9; i >= 0; i--) {
            digits[i] = (int) (id % 10);
            id /= 10;
        }

        for (int i = 0; i < 9; i += 2) {
            doubledDigit = digits[i] * 2;
            if (doubledDigit > 9) {
                doubledDigit -= 9;
            }
            evenPositionSum += doubledDigit;
        }

        for (int i = 1; i < 9; i += 2) {
            oddPositionSum += digits[i];
        }

        totalSum = evenPositionSum + oddPositionSum;
        remainder = totalSum % 10;
        checkDigit = 10 - remainder;
        if (checkDigit == 10) {
            checkDigit = 0;
        }

        return checkDigit == digits[9];
    }

    public static boolean isValidEmailFormat(String email) {
        String regex = "^[\\w-\\.]+@(hotmail\\.com|gmail\\.com|outlook\\.com|yahoo\\.com)$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validBoolean() {
        Scanner scanner = new Scanner(System.in);
        boolean userInput;

        while (true) {
            try {
                userInput = scanner.nextBoolean();
                if (userInput == true || userInput == false) {
                    return userInput;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please type true or false.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showErrorMessage(JFrame parentFrame, String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(JFrame parentFrame, String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmReservationCancellation(String selectedDate, JFrame parentFrame) {
        String message = String.format("La reservación del día %s va a ser eliminada. ¿Está seguro de eliminar esta reservación?", selectedDate);
        int confirmation = JOptionPane.showConfirmDialog(parentFrame, message, "Confirmar", JOptionPane.YES_NO_OPTION);
        return confirmation == JOptionPane.YES_OPTION;
    }

    public static String modifyPassword(String password, int asqui) {
        StringBuilder modifiedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + asqui) % 26 + base);
            }
            modifiedPassword.append(c);
        }
        return modifiedPassword.toString();
    }

    public static void loginIsCorrect(JFrame parentFrame, JPasswordField pwfPassword, JTextField txfEmail) {
        String encryptedPassword = DataCollection.encryptingPassword(pwfPassword.getText());
        String loginResponse = CloudController.getInstance().login(txfEmail.getText(), encryptedPassword);
        if (loginResponse == null) {
            Validation.showErrorMessage(parentFrame, "Correo electronico o contraseña incorrectos");
            return;
        }
        InterfaceActions.navigateToUserMenuFromLogin(parentFrame, loginResponse);
    }

    public static void emailAndIdAreCorrect(JFrame parentFrame, JTextField txfName, JTextField txfEmail, JTextField txfId,
            JPasswordField txfPassword, JComboBox cmbGrade) {
        String name = txfName.getText();
        String email = txfEmail.getText();
        String id = txfId.getText();
        String password = txfPassword.getText();

        if (!Validation.isValidEmailFormat(email)) {
            Validation.showErrorMessage(parentFrame, "Formato de correo electronico invalido.");
            return;
        }
        if (!Validation.validateId(id)) {
            Validation.showErrorMessage(parentFrame, "Cedula invalida.");
            return;
        }

        InterfaceActions.creatingAccount(parentFrame, name, email, id, password, cmbGrade.getSelectedItem().toString());
    }

    public static boolean hasSufficientBalance(double balance, int amountOfPeople) {
        return balance >= (reservationCost * amountOfPeople);
    }

    public static boolean isDateAlreadyReserved(DateBook dateBook, String date) {
        return dateBook.getReservedDays().containsKey(date);
    }

    public static boolean theAmountOfPeopleIsCorrect(JTextField txfAmountOfPeople) {
        try {
            int amountOfPeople = Integer.parseInt(txfAmountOfPeople.getText());

            if (amountOfPeople > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException ex) {
            txfAmountOfPeople.setBackground(Color.red);
            return false;
        }
    }

    public static void updateTotal(JTextField txfAmountOfPeople, JLabel lblAmountToPay) {
        try {
            double totalToPay = reservationCost * Integer.parseInt(txfAmountOfPeople.getText());
            lblAmountToPay.setText("La cantidad de dinero a pagar es: $" + String.format("%.2f", totalToPay));
        } catch (NumberFormatException ex) {
            lblAmountToPay.setText("La cantidad de dinero a pagar es: $0.00");
        }
    }

    public static void processReservationCancellation(JFrame parentFrame, DateBook dateBook, LocalDate selectedDate, String date) {
        DateBook updatedDateBook = CloudController.getInstance().removeDay(dateBook, date);

        if (DataCollection.currentDate.isAfter(selectedDate)) {
            Validation.showErrorMessage(parentFrame, "No se puede cancelar reservaciones antiguas.");
        } else if (updatedDateBook == null) {
            Validation.showErrorMessage(parentFrame, "El día ingresado no existe en las reservaciones.");
        } else {
            CloudController.getInstance().saveDateBook(updatedDateBook);
            CloudController.getInstance().updateCommensalBalance(FrmCancelAppointment.userId, DataCollection.reservationCost);
            FrmCancelAppointment.userBalance += DataCollection.reservationCost;
        }
    }

    public static boolean dateIsAlreadyBooked(String date) {
        List<Document> documents = CloudController.getInstance().getMenuInformation();

        for (Document doc : documents) {
            String dateOfTheMenu = doc.getString("date");
            if (date.equals(dateOfTheMenu)) {
                return true;
            }
        }
        return false;
    }

    public static void enteringTheDay(JFrame parentFrame, JComboBox cmbDay, JComboBox cmbMonth, String chefName) {
        String date = DataCollection.getSelectedDate(cmbDay, cmbMonth);

        if (!Validation.dateIsAlreadyBooked(date)) {
            FrmRegistMenuForADay frmRegistMenuForADay = new FrmRegistMenuForADay(chefName, date);
            parentFrame.setVisible(false);
            frmRegistMenuForADay.setVisible(true);
        } else {
            Validation.showInfoMessage(parentFrame, "El dia ya esta registrado.");
        }
    }
}

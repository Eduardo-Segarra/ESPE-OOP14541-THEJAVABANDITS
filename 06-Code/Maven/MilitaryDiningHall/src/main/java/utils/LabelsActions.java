/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ec.edu.espe.militarydininghall.controller.CloudController;
import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.bson.Document;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class LabelsActions {

    public static void settingLabelsInvisible(JLabel lblBreakfast, JLabel lblAvailableBreakfast, JLabel lblLunch,
            JLabel lblAvailableLunch, JLabel lblSnack, JLabel lblAvailableSnack) {
        lblBreakfast.setVisible(false);
        lblAvailableBreakfast.setVisible(false);
        lblLunch.setVisible(false);
        lblAvailableLunch.setVisible(false);
        lblSnack.setVisible(false);
        lblAvailableSnack.setVisible(false);
    }

    public static void settingLabelsVisible(JLabel lblBreakfast, JLabel lblAvailableBreakfast, JLabel lblLunch,
            JLabel lblAvailableLunch, JLabel lblSnack, JLabel lblAvailableSnack) {
        lblBreakfast.setVisible(true);
        lblAvailableBreakfast.setVisible(true);
        lblLunch.setVisible(true);
        lblAvailableLunch.setVisible(true);
        lblSnack.setVisible(true);
        lblAvailableSnack.setVisible(true);
    }

    public static void settingLMeals(JLabel lblAvailableBreakfast, JLabel lblAvailableLunch, JLabel lblAvailableSnack,
            String Breakfast, String Lunch, String Snack) {
        lblAvailableBreakfast.setText(Breakfast);
        lblAvailableLunch.setText(Lunch);
        lblAvailableSnack.setText(Snack);
    }

    public static void settingName(JLabel lblName, String name) {
        lblName.setText("Bienvenido, " + name + "!");
    }

    public static void settingBalance(JLabel lblBalance, double balance) {
        lblBalance.setText(String.format("%.2f", balance));
    }

    public static void ifMenuIsAvailable(JLabel lblAvailablePlates, String date) {
        lblAvailablePlates.setText("Los platillos para el dia " + date + " son los siguientes:");
    }

    public static void ifMenuIsNotAvailable(JLabel lblAvailablePlates, String date) {
        lblAvailablePlates.setText("Parece que todavia no se han ingresado los platillos para el dia " + date + ".");
    }

    public static void ifMenuDoesNotExists(JLabel lblAvailablePlates) {
        lblAvailablePlates.setText("Parece que todavia no se ha registrado algun menu.");
    }

    public static void loopForShowingTheMenu(JLabel lblAvailablePlates, JLabel lblBreakfast, JLabel lblAvailableBreakfast,
            JLabel lblLunch, JLabel lblAvailableLunch, JLabel lblSnack, JLabel lblAvailableSnack, DateBook datebook,
            LocalDate today) {
        List<Document> documents = CloudController.getMenuInformation();
        Map<String, Boolean> reservedDays = datebook.getReservedDays();

        for (Document doc : documents) {
            String date = doc.getString("date");
            String breakfast = doc.getString("breakfast");
            String lunch = doc.getString("lunch");
            String dinner = doc.getString("dinner");

            for (Map.Entry<String, Boolean> entry : reservedDays.entrySet()) {
                String dateReserved = entry.getKey();

                String[] parts = dateReserved.split("/");
                String day = parts[0];
                String month = parts[1];
                String year = parts[2];
                LocalDate dateSearch = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                String dateToCompare = day + "/" + month + "/" + year;

                if (today.isBefore(dateSearch) || today.isEqual(dateSearch)) {
                    if (dateToCompare.contentEquals(date)) {
                        ifMenuIsAvailable(lblAvailablePlates, dateToCompare);
                        settingLabelsVisible(lblBreakfast, lblAvailableBreakfast, lblLunch, lblAvailableLunch, lblSnack,
                                lblAvailableSnack);
                        settingLMeals(lblAvailableBreakfast, lblAvailableLunch, lblAvailableSnack, breakfast, lunch, dinner);
                        break;
                    } else if (!dateToCompare.contentEquals(date)) {
                        ifMenuIsNotAvailable(lblAvailablePlates, dateToCompare);
                        break;
                    }
                }

            }
        }
    }

    public static void summaryOfTheMenu(List<Document> documents, LocalDate today, JLabel lblAvailablePlates, JLabel lblBreakfast,
            JLabel lblAvailableBreakfast, JLabel lblLunch, JLabel lblAvailableLunch, JLabel lblSnack, JLabel lblAvailableSnack) {
        if (documents == null) {
            LabelsActions.ifMenuDoesNotExists(lblAvailablePlates);
            LabelsActions.settingLabelsInvisible(lblBreakfast, lblAvailableBreakfast, lblLunch, lblAvailableLunch, lblSnack,
                    lblAvailableSnack);
        } else {
            loopForShowingTheMenuForChefs(documents, today, lblAvailablePlates, lblBreakfast, lblAvailableBreakfast, lblLunch,
                    lblAvailableLunch, lblSnack, lblAvailableSnack);
        }
    }

    public static void loopForShowingTheMenuForChefs(List<Document> documents, LocalDate today, JLabel lblAvailablePlates,
            JLabel lblBreakfast, JLabel lblAvailableBreakfast, JLabel lblLunch, JLabel lblAvailableLunch, JLabel lblSnack,
            JLabel lblAvailableSnack) {
        for (Document doc : documents) {
            String date = doc.getString("date");
            String[] parts = date.split("/");
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];
            String dateToCompare = day + "/" + month + "/" + year;
            LocalDate dateSearch = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            String breakfast = doc.getString("breakfast");
            String lunch = doc.getString("lunch");
            String dinner = doc.getString("dinner");

            if (today.isBefore(dateSearch) || today.isEqual(dateSearch)) {
                LabelsActions.ifMenuIsAvailable(lblAvailablePlates, dateToCompare);
                LabelsActions.settingLabelsVisible(lblBreakfast, lblAvailableBreakfast, lblLunch, lblAvailableLunch, lblSnack,
                        lblAvailableSnack);
                LabelsActions.settingLMeals(lblAvailableBreakfast, lblAvailableLunch, lblAvailableSnack, breakfast, lunch, dinner);
                break;
            }
        }
    }

    public static void establishingTheAmountToPay(JTextField txfAmountOfPeople, JLabel lblAmountToPay) {
        txfAmountOfPeople.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Validation.updateTotal(txfAmountOfPeople, lblAmountToPay);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validation.updateTotal(txfAmountOfPeople, lblAmountToPay);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validation.updateTotal(txfAmountOfPeople, lblAmountToPay);
            }
        });
    }

    public static void showingInformationOfThePerson(Commensal commensal, JLabel lblDataOfThePerson, JLabel lblAmountOfMoney,
            JLabel lblHowMuchMoney) {
        if (commensal != null) {
            lblDataOfThePerson.setText("Actualizacion del saldo de cuenta de " + commensal.getName() + " con cedula: " + commensal.getId());
            lblAmountOfMoney.setText(commensal.getName() + " tiene: $" + commensal.getBalance());
            lblHowMuchMoney.setText("¿Cuánto dinero" + commensal.getName() + "quiere ingresar?:");
        } else {
            lblDataOfThePerson.setText("Actualización del saldo de la cuenta");
            lblAmountOfMoney.setText("Detalles de la cuenta no disponibles");
        }
    }
}

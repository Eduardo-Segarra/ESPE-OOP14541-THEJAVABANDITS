package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ec.edu.espe.militarydininghall.controller.CloudController;
import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import ec.edu.espe.militarydininghall.view.FrmBookDay;
import static ec.edu.espe.militarydininghall.view.FrmBookDay.actualYear;
import static ec.edu.espe.militarydininghall.view.FrmBookDay.reservationCost;
import static ec.edu.espe.militarydininghall.view.FrmEstablishAdministratorSearch.commensal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class DataCollection {

    private static DataCollection instance;
    protected double reservationCost;
    protected LocalDate currentDate;

    private DataCollection() {
        this.reservationCost = 7.5F;
        this.currentDate = LocalDate.now();
    }

    public static DataCollection getInstance() {
        if (instance == null) {
            instance = new DataCollection();
        }

        return instance;
    }
    
    /**
     * @return the reservationCost
     */
    public double getReservationCost() {
        return reservationCost;
    }

    /**
     * @return the currentDate
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public static String gettingTheAccountData(JTextField txfId) {
        return CloudController.getInstance().findAccountById(txfId.getText());
    }

    public static String obtainInformationFromJSON(String json, String informationType) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get(informationType).getAsString();
    }

    public static String encryptingPassword(String password) {
        return Validation.modifyPassword(password, 1);
    }

    public static Commensal sendingTheData(String accountData) {
        Gson gson = new Gson();
        return gson.fromJson(accountData, Commensal.class);
    }

    public static String getSelectedDate(JComboBox cmbDay, JComboBox cmbMonth) {
        String day = cmbDay.getSelectedItem().toString();
        String month = cmbMonth.getSelectedItem().toString();
        return day + "/" + month + "/" + DataCollection.getInstance().getCurrentDate().getYear();
    }

    public static boolean creatingANewAdministrator() {
        CloudController.getInstance().delete(commensal);
        commensal.setType("administrators");
        return CloudController.getInstance().createANewAdministrator(commensal);
    }

    public static void saveReservation(DateBook dateBook, String date) {
        dateBook.addDay(date, false);
        DateBook orderedDays = CloudController.getInstance().orderingOfDays(dateBook);
        CloudController.getInstance().saveDateBook(orderedDays);
    }

    public static void deductReservationCost(String userId, int amountOfPeople) {
        CloudController.getInstance().updateCommensalBalance(userId, -(DataCollection.getInstance().getReservationCost() * 
                amountOfPeople));
        FrmBookDay.userBalance -= (DataCollection.getInstance().getReservationCost() * amountOfPeople);
    }

    public static String gettingThePdfFileNameForMenu() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        return "Menu_de_Platos_" + timestamp + ".pdf";
    }

    public static String gettingThePdfFileNameForAppointment() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        return "Reservaciones_" + timestamp + ".pdf";
    }

}

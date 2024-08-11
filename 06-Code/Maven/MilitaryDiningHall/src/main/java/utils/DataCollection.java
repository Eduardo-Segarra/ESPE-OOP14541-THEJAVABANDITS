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
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class DataCollection {
    
    public static String gettingTheAccountData(JTextField txfId) {
        return CloudController.findAccountById(txfId.getText());
    }

    public static String obtainIdFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("id").getAsString();
    }

    public static String obtainNameFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("name").getAsString();
    }

    public static String obtainEmailFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("email").getAsString();
    }

    public static String obtainPasswordFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("password").getAsString();
    }

    public static String obtainGradeFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("grade").getAsString();
    }

    public static String obtainTypeFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("type").getAsString();
    }

    public static Float obtainBalanceFromJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return Float.valueOf(jsonObject.get("balance").getAsString());
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
        return day + "/" + month + "/" + actualYear;
    }

    public static boolean creatingANewAdministrator() {
        CloudController.delete(commensal);
        commensal.setType("administrators");
        return CloudController.createANewAdministrator(commensal);
    }
    
    public static void saveReservation(DateBook dateBook, String date) {
        dateBook.addDay(date, false);
        DateBook orderedDays = CloudController.orderingOfDays(dateBook);
        CloudController.saveDateBook(orderedDays);
    }

    public static void deductReservationCost(String userId, int amountOfPeople) {
        CloudController.updateCommensalBalance(userId, -(reservationCost * amountOfPeople));
        FrmBookDay.userBalance -= (reservationCost * amountOfPeople);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ec.edu.espe.militarydininghall.controller.CloudController;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class Tables {

    public static void updateTableOfMenus(JTable tblTable) {
        List<Document> documents = CloudController.getMenuInformation();

        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0);

        for (Document doc : documents) {
            String date = doc.getString("date");
            String breakfast = doc.getString("breakfast");
            String lunch = doc.getString("lunch");
            String dinner = doc.getString("dinner");
            model.addRow(new Object[]{date, breakfast, lunch, dinner});
        }
    }

    public static void updateTableFromDateBook(DateBook dateBook, JTable tblTable) {
        if (tblTable == null) {
            System.err.println("Error: tblTable no está inicializada.");
            return;
        }

        Map<String, Boolean> reservedDays = dateBook.getReservedDays();

        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();

        model.setRowCount(0);

        for (Map.Entry<String, Boolean> entry : reservedDays.entrySet()) {
            String date = entry.getKey();
            boolean reserved = entry.getValue();

            String[] parts = date.split("/");
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            model.addRow(new Object[]{year, month, day, reserved});
        }
    }

    public static void updateTableToEditTheAssistance(DateBook dateBook, JTable tblTable) {
        if (tblTable == null) {
            System.err.println("Error: tblTable no está inicializada.");
            return;
        }

        Map<String, Boolean> reservedDays = dateBook.getReservedDays();

        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();

        model.setRowCount(0);

        for (Map.Entry<String, Boolean> entry : reservedDays.entrySet()) {
            String date = entry.getKey();
            boolean reserved = entry.getValue();

            model.addRow(new Object[]{date, reserved});
        }
    }
    
    public static void savingTheSelectionsOfTheTable(JTable tblTable, String idSearched) {
        for (int row = 0; row < tblTable.getRowCount(); row++) {
            String data = (String) tblTable.getValueAt(row, 0);
            boolean attendance = (boolean) tblTable.getValueAt(row, 1);
            DateBook datebook = CloudController.getDateBook(Long.parseLong(idSearched));
            datebook.changeAssistance(data, attendance);
            CloudController.saveDateBook(datebook);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Commensal;
import java.util.ArrayList;
import java.util.List;
import utils.FileManager;
import utils.Validations;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class ActionsMenuManager {

    public static void bookDay(int id, String fileName) {
        int day, month, year = 2024;
        String bookDay, commenalData;
        Commensal commensal;
        List<String> daysReserved = new ArrayList<>();

        commensal = FileManager.findAccount(fileName, id);

        daysReserved = commensal.getDaysReserved();

        System.out.println("Please enter the month of your booking:");
        month = Validations.validateMonth();
        System.out.println("Please enter the day of your booking:");
        day = Validations.validateDay(year, month);

        bookDay = day + "/" + month + "/" + year;
        daysReserved.add(bookDay);
        commensal.setDaysReserved(daysReserved);
        commenalData = commensal.toStringJSON();

        FileManager.updateAccount(commenalData, fileName, id);
    }

    public static void cancelDayBook(int id, String fileName) {

    }

    public static void seeAccountBalance(int id, String fileName) {
        Commensal commensal;
        commensal = FileManager.findAccount(fileName, id);

        System.out.println("Your account balance is: " + commensal.getBalance());
    }

}
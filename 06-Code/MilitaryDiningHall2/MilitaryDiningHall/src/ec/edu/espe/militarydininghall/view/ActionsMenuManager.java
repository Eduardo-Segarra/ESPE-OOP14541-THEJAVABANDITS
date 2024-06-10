/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.FileManager;
import utils.Validations;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class ActionsMenuManager {

    public static void bookDay(int id, String fileName) {
        int day, month, year = 2024;
        /*
        String bookDay = "", data;
        List<String> updatedDaysReserved = new ArrayList<>();
        List<String> days = new ArrayList<>();

        data = FileManager.findAccount(fileName + ".json", id);
        String[] parts = data.split(":");
         */

        DateBook dateBook = FileManager.loadDateBook(id);
        String date;

        System.out.print("Please enter the month of your booking ");
        month = Validations.validateMonth();
        System.out.print("Please enter the day of your booking ");
        day = Validations.validateDay(year, month);
        /*bookDay = day + "/" + month + "/" + year;
        
       
        if (!parts[7].equalsIgnoreCase("No days reserved yet")) {
            updatedDaysReserved.add(bookDay);
            updatedDaysReserved.add(parts[7]);
        }else{
            updatedDaysReserved.add(bookDay);
        }

        days.add(parts[7]);

        Commensal updatedCommensal = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]), updatedDaysReserved);
        Commensal outdatedcommensal = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]), days);

        FileManager.updateAccount(updatedCommensal, fileName, outdatedcommensal);
         */
        date = day + "/" + month + "/" + year;
        dateBook.addDay(date);
        FileManager.saveDateBook(dateBook);
    }

    public static void cancelDayBook(int id) {
        Scanner scanner = new Scanner(System.in);
        /*
        String data = FileManager.findAccount(fileName + ".json", id), bookDayCancel;
        List<String> updatedDaysReserved = new ArrayList<>();
        List<String> outdatedDaysReserved = new ArrayList<>();

        String[] parts = data.split(":");
        String[] days = parts[7].split(",");

        if (parts[7].equalsIgnoreCase("No days reserved yet")) {
            System.out.println("You don't have any days reserved yet.");

        } else {
            System.out.println(parts[7] + "\nWhat day you want to cancel the booking?(Type the whole date):");
            bookDayCancel = scanner.nextLine();

            for (int i = 0; i < days.length; i++) {
                if (!days[i].equalsIgnoreCase(bookDayCancel)) {
                    updatedDaysReserved.add(days[i]);
                }

            }
            outdatedDaysReserved.add(parts[7]);

            Commensal updatedCommensal = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]), updatedDaysReserved);
            Commensal outdatedcommensal = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]), outdatedDaysReserved);

            FileManager.updateAccount(updatedCommensal, fileName, outdatedcommensal);
        }
         */
        DateBook dateBook = FileManager.loadDateBook(id);
        if (dateBook.ListOfDays().isBlank()== false) {
            System.out.println(dateBook.ListOfDays());
            System.out.println("What day you want to cancel the booking?(Type the whole date):");
            String date = scanner.nextLine();
        }else{
            System.out.println("No dates added");
        }

    }

    public static void seeAccountBalance(int id, String fileName) {
        String data;
        data = FileManager.findAccount(fileName + ".json", id);

        String[] parts = data.split(":");

        System.out.println("Your account balance is: " + parts[6]);
    }
}

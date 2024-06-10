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

    public static void bookDay(int id) {
        int day, month, year = 2024;

        DateBook dateBook = FileManager.loadDateBook(id);
        String date;

        System.out.print("Please enter the month of your booking ");
        month = Validations.validateMonth();
        System.out.print("Please enter the day of your booking ");
        day = Validations.validateDay(year, month);
        
        date = day + "/" + month + "/" + year;
        dateBook.addDay(date);
        FileManager.saveDateBook(dateBook);
    }

    public static void cancelDayBook(int id) {
        Scanner scanner = new Scanner(System.in);
        
        DateBook dateBook = FileManager.loadDateBook(id);
        if (dateBook.ListOfDays().isBlank()== false) {
            System.out.println(dateBook.ListOfDays());
            System.out.println("What day you want to cancel the booking?(Type the whole date):");
            String date = scanner.nextLine();
        }else{
            System.out.println("No dates added");
        }

    }

    public static void seeAccountBalance(int id, String fileName, String email, String password) {
        String data = "";
        data = FileManager.findAccount(fileName + ".json", id, email, password);
        String[] parts = data.split(":");

        System.out.println("Your account balance is: " + parts[6]);
    }
    
    public static void setAdministrator(String fileName){
        Scanner scanner = new Scanner(System.in);
        int id;
        String foundAccount = "";
        String[] parts;
        
        System.out.println("Please type the ID to set a new administrator for the following month:");
        id = scanner.nextInt();
        
        foundAccount = FileManager.findAccount("commensals.json", id, "", "");
        
        parts = foundAccount.split(":");
        
        parts[5] = "administrators";
        
        Commensal newAdministrator = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]));
        
        FileManager.save(newAdministrator, "administrators");
        FileManager.eraseAccount("commensals", id);
        
        System.out.println("A new administrator has establish");
    }
    
}

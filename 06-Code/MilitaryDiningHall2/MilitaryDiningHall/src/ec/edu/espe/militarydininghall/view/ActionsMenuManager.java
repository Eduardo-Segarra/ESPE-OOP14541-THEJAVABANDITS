/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import ec.edu.espe.militarydininghall.model.Dishes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.FileManager;
import utils.Validations;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class ActionsMenuManager {

    public static void bookDay(int id) {
        int day, month, year = 2024;

        DateBook dateBook = FileManager.loadDateBook(id);
        String date;

        System.out.print("Please enter the month of your booking ");
        month = Validations.validateMonth();
        
        // Notification for the month selected
        Dishes dishes = FileManager.loadDishesByMonth(month);
        System.out.println(dishes.notification());
        
        System.out.print("Please enter the day of your booking ");
        day = Validations.validateDay(year, month);

        date = day + "/" + month + "/" + year;
        dateBook.addDay(date);
        FileManager.saveDateBook(dateBook);
    }

    public static void cancelDayBook(int id) {
        Scanner scanner = new Scanner(System.in);

        DateBook dateBook = FileManager.loadDateBook(id);
        if (dateBook.ListOfDays().isBlank() == false) {
            System.out.println(dateBook.ListOfDays());
            System.out.println("What day you want to cancel the booking?(Type the whole date):");
            String date = scanner.nextLine();
        } else {
            System.out.println("No dates added");
        }

    }

    public static void seeAccountBalance(int id, String fileName, String email, String password) {
        String data = "";
        data = FileManager.findAccount(fileName + ".json", id, email, password);
        String[] parts = data.split(":");

        System.out.println("Your account balance is: " + parts[6]);
    }

    public static void setAdministrator(int id, String email, String password) {
        Scanner scanner = new Scanner(System.in);
        int idSearch = 0, attempts;
        String foundAccount = "";
        String[] parts;

        for (attempts = 0; attempts < 5; attempts++) {
            System.out.println("Please type the ID to set a new administrator for the following month:");
            try {
                idSearch = scanner.nextInt();

                foundAccount = FileManager.findAccount("commensals.json", idSearch, "", "");
                if (foundAccount != null) {
                    break;
                }

                if (attempts < 4 && foundAccount == null) { // Check to avoid printing after the last attempt
                    System.out.println("Incorrect ID. You have " + (4 - attempts) + " attempt(s) left.");
                } else if (attempts == 4) {
                    MenuManager.generalAdminMenu(id, email, password);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                scanner.next(); // Consume the invalid input
                attempts--; // Do not count this as a valid attempt
            }

        }
        
        if (foundAccount == null) {
            System.out.println("Account not found. Returning to the general admin menu.");
            MenuManager.adminMenu(id, email, password);
            return;
        }

        parts = foundAccount.split(":");

        parts[5] = "administrators";

        Commensal newAdministrator = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]));

        FileManager.save(newAdministrator, "administrators");
        FileManager.eraseAccount("commensals", idSearch);

        System.out.println("A new administrator has establish");
    }
<<<<<<< Updated upstream

    public static void updateAccountBalance(int id, String email, String password) {
        Scanner scanner = new Scanner(System.in);
        int idSearch = 0, attempts;
        float updateBalance;
        String foundAccount = "", fileName = "";
        String[] partsData;

        for (attempts = 0; attempts < 5; attempts++) {
            System.out.println("Please type the ID of the account you want to update the account balance:");
            try {
                idSearch = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                foundAccount = FileManager.findAccount("commensals.json", idSearch, "", "");
                if (foundAccount != null) {
                    fileName = "commensals";
                    break;
                }

                foundAccount = FileManager.findAccount("administrators.json", idSearch, "", "");
                if (foundAccount != null) {
                    fileName = "administrators";
                    break;
                }

                foundAccount = FileManager.findAccount("generalAdministrator.json", idSearch, "", "");
                if (foundAccount != null) {
                    fileName = "generalAdministrator";
                    break;
                }

                if (attempts < 4) {
                    System.out.println("Incorrect ID. You have " + (4 - attempts) + " attempt(s) left.");
                } else if (attempts == 4) {
                    MenuManager.adminMenu(id, email, password);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                scanner.next(); // Consume the invalid input
                attempts--; // Do not count this as a valid attempt
            }
        }

        if (foundAccount == null) {
            System.out.println("Account not found. Returning to admin menu.");
            MenuManager.adminMenu(id, email, password);
            return;
        }

        partsData = foundAccount.split(":");

        Commensal outdatedAccountBalance = new Commensal(Integer.parseInt(partsData[0]), partsData[1], partsData[2], partsData[3], partsData[4], partsData[5], Float.parseFloat(partsData[6]));

        System.out.println("Enter the desired amount: ");
        updateBalance = scanner.nextFloat();
        partsData[6] = Float.toString(updateBalance);

        Commensal newAccountBalance = new Commensal(Integer.parseInt(partsData[0]), partsData[1], partsData[2], partsData[3], partsData[4], partsData[5], Float.parseFloat(partsData[6]));

        FileManager.updateAccount(newAccountBalance, fileName, outdatedAccountBalance);
    }

=======
    
        public static void chooseMonthMenu(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("In what month will the menu be made?:");
            int month = Validations.validateMonth();
            System.out.println("What dishes will be served that month?:");
            System.out.println("Breakfast:");
            String breakfast = scanner.nextLine();
            System.out.println("Lunch:");
            String lunch = scanner.nextLine();
            System.out.println("Dinner:");
            String dinner = scanner.nextLine();
            Dishes dishes = new Dishes(month, breakfast, lunch, dinner);
            FileManager.saveDishes(dishes);
        }
    
>>>>>>> Stashed changes
}

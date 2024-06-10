/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

import com.google.gson.Gson;
import ec.edu.espe.militarydininghall.view.MenuManager;
import java.util.List;
import java.util.Scanner;
import utils.FileManager;
import utils.Validations;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class GeneralAdmin {

    private int id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private String type;
    private float balance;

    public GeneralAdmin(int id, String name, String email, String password, String grade, String type, float balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.type = type;
        this.balance = balance;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

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

    @Override
    public String toString() {
        return "GeneralAdmin{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", grade=" + grade + ", type=" + type + ", balance=" + balance + '}';
    }

    public String toStringJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Dishes;
import java.time.LocalDate;
import utils.Accounts;
import utils.FileManager;
import utils.Validations;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class MenuManager {

    public static String mainMenu() {
        int option, loweOption = 1, higherOption = 3;
        String accountType = "";

        System.out.println("Welcome to the Military Dining Hall Application");
        System.out.println("Select an option: \n1.- Login \n2.- Create an account \n3.- Exit\nEnter the option: ");

        option = Validations.valideInt(loweOption, higherOption);

        switch (option) {
            case 1 -> {
                accountType = Accounts.logIn();
                return accountType;
            }
            case 2 -> {
                accountType = Accounts.createNewAccount();
                return accountType;
            }
            case 3 -> {
                return "0: : : : :exit: : ";
            }
            default -> {
                return "0: : : : :invalid: : ";
            }
        }

    }

    public static void commensalMenu(int id, String email, String password) {
        Dishes dishes = FileManager.loadDishesByMonth(LocalDate.now().getMonthValue());
        int option, loweOption = 1, higherOption = 4;
        String fileName = "commensals";

        do {
            System.out.println("Commensal Menu:");
            System.out.println(dishes.notification());
            System.out.println("1. Book a Day");
            System.out.println("2. Cancel a day's booking");
            System.out.println("3. See Account Balance");
            System.out.println("4. Exit");

            option = Validations.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    ActionsMenuManager.bookDay(id);

                case 2 ->
                    ActionsMenuManager.cancelDayBook(id);

                case 3 ->
                    ActionsMenuManager.seeAccountBalance(id, fileName, email, password);

            }
        } while (option != 4);
    }

    public static void adminMenu(int id, String email, String password) {
        int option, loweOption = 1, higherOption = 6;
        String fileName = "administrators";
        do {
            System.out.println("Admin Menu");
            System.out.println("1. Edit Register");
            System.out.println("2. Update the account balance for a commensal");
            System.out.println("3. Book a Day");
            System.out.println("4. Cancel a day's booking");
            System.out.println("5. See Account Balance");
            System.out.println("6. Exit");

            option = Validations.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    ActionsMenuManager.editRegister();

                case 2 ->
                    ActionsMenuManager.updateAccountBalance(id, email, password);

                case 3 ->
                    ActionsMenuManager.bookDay(id);

                case 4 ->
                    ActionsMenuManager.cancelDayBook(id);

                case 5 ->
                    ActionsMenuManager.seeAccountBalance(id, fileName, email, password);

            }
        } while (option != 6);
    }

    public static void generalAdminMenu(int id, String email, String password) {
        int option, loweOption = 1, higherOption = 5;
        String fileName = "generalAdministrator";
        do {
            System.out.println("Menu for general admins");
            System.out.println("1. Establish the administrator for the following month");
            System.out.println("2. Book a Day");
            System.out.println("3. Cancel a day's booking");
            System.out.println("4. See Account Balance");
            System.out.println("5. Exit");

            option = Validations.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    ActionsMenuManager.setAdministrator(id, email, password);

                case 2 ->
                    ActionsMenuManager.bookDay(id);

                case 3 ->
                    ActionsMenuManager.cancelDayBook(id);

                case 4 ->
                    ActionsMenuManager.seeAccountBalance(id, fileName, email, password);

            }
        } while (option != 5);
    }

    public static void chefMenu(int id, String email, String password) {
        int option, loweOption = 1, higherOption = 2;
        String fileName = "militaryChef";

        do {
            System.out.println("Chef Menu:");
            System.out.println("1. Choose the menu for the month");
            System.out.println("2. Exit");

            option = Validations.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    ActionsMenuManager.chooseMonthMenu();
            }
        } while (option != 2);
    }
}

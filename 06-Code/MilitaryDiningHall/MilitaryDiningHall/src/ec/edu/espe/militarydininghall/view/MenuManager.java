package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Admin;
import ec.edu.espe.militarydininghall.model.Chef;
import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import ec.edu.espe.militarydininghall.model.Dish;
import ec.edu.espe.militarydininghall.model.GeneralAdmin;
import java.time.LocalDate;
import utils.Accounts;
import utils.FileManager;
import utils.Validation;

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

        option = Validation.valideInt(loweOption, higherOption);

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

    public static void displayCommensalMenu(int id, String email, String password) {
        Dish dishes = FileManager.loadDishesByMonth(LocalDate.now().getMonthValue());
        int option, loweOption = 1, higherOption = 4;
        String fileName = "commensals";

        do {
            System.out.println("Commensal Menu:");
            System.out.println(dishes.notification());
            System.out.println("1. Book a Day");
            System.out.println("2. Cancel a day's booking");
            System.out.println("3. See Account Balance");
            System.out.println("4. See reservations");
            System.out.println("5. Exit");

            option = Validation.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    DateBook.bookDay(id);

                case 2 ->
                    DateBook.cancelDayBook(id);

                case 3 ->
                    Commensal.seeAccountBalance(id, fileName, email, password);
                case 4 -> {
                    DateBook dateBook = FileManager.loadDateBook(id);
                    if (dateBook.ListOfDays().isBlank() == false) {
                        System.out.println(dateBook.ListOfDays());
                    } else {
                        System.out.println("No dates added");
                    }
                }
            }
        } while (option
                != 5);
    }

    public static void displayAdminMenu(int id, String email, String password) {
        int option, loweOption = 1, higherOption = 6;
        String fileName = "administrators";
        do {
            System.out.println("Admin Menu");
            System.out.println("1. Edit Register");
            System.out.println("2. Update the account balance for a commensal");
            System.out.println("3. Book a Day");
            System.out.println("4. Cancel a day's booking");
            System.out.println("5. See Account Balance");
            System.out.println("6. See reservations");
            System.out.println("7. Exit");

            option = Validation.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    Admin.editRegister(id, email, password);

                case 2 ->
                    Admin.updateAccountBalance(id, email, password);

                case 3 ->
                    DateBook.bookDay(id);

                case 4 ->
                    DateBook.cancelDayBook(id);

                case 5 ->
                    Admin.seeAccountBalance(id, fileName, email, password);
                case 6 -> {
                    DateBook dateBook = FileManager.loadDateBook(id);
                    if (dateBook.ListOfDays().isBlank() == false) {
                        System.out.println(dateBook.ListOfDays());
                    } else {
                        System.out.println("No dates added");
                    }
                }
            }
        } while (option != 7);
    }

    public static void displayGeneralAdminMenu(int id, String email, String password) {
        int option, loweOption = 1, higherOption = 5;
        String fileName = "generalAdministrator";
        do {
            System.out.println("Menu for general admins");
            System.out.println("1. Establish the administrator for the following month");
            System.out.println("2. Book a Day");
            System.out.println("3. Cancel a day's booking");
            System.out.println("4. See Account Balance");
            System.out.println("5. See reservations");
            System.out.println("6. Exit");

            option = Validation.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    GeneralAdmin.setAdministrator(id, email, password);

                case 2 ->
                    DateBook.bookDay(id);

                case 3 ->
                    DateBook.cancelDayBook(id);

                case 4 ->
                    GeneralAdmin.seeAccountBalance(id, fileName, email, password);
                case 5 -> {
                    DateBook dateBook = FileManager.loadDateBook(id);
                    if (dateBook.ListOfDays().isBlank() == false) {
                        System.out.println(dateBook.ListOfDays());
                    } else {
                        System.out.println("No dates added");
                    }
                }
            }
        } while (option != 6);
    }

    public static void displayChefMenu(int id, String email, String password) {
        int option, loweOption = 1, higherOption = 2;
        String fileName = "militaryChef";

        do {
            System.out.println("Chef Menu:");
            System.out.println("1. Choose the menu for the month");
            System.out.println("2. Exit");

            option = Validation.valideInt(loweOption, higherOption);

            switch (option) {
                case 1 ->
                    Chef.chooseMonthMenu();
            }
        } while (option != 2);
    }

}

package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Accounts;
import java.util.Scanner;
import ec.edu.espe.militarydininghall.model.DinnerMenu;

/**
 *
 * @author Carlos Pérez, TheJavaBandits, DCCO-ESPE
 */
public class ActionsMenuManager {

    private static final Scanner scanner = new Scanner(System.in);

    public static void bookADay() {
        System.out.print("Enter the day to book (DD): ");
        String day = scanner.nextLine();
        
        System.out.print("Enter the month to book (MM): ");
        String month = scanner.nextLine();
        
        String date = "2024-" + month + "-" + day;
        
        System.out.print("Enter the breakfast name: ");
        String breakfast = scanner.nextLine();
        
        System.out.print("Enter the lunch name: ");
        String lunch = scanner.nextLine();
        
        System.out.print("Enter the snack name: ");
        String snack = scanner.nextLine();

        DinnerMenu dinnerMenu = new DinnerMenu(breakfast, lunch, snack);

        
        System.out.println("Booking for " + date + " completed successfully!"+dinnerMenu);
    }

    public static void unbookADay() {
        System.out.println("Booking cancelled successfully!");
    }

    public static void seeAccountBalance() {

        String accountName = Accounts.getCurrentAccountName();

        if (accountName != null) {
            //System.out.println("Account balance for " + accountName + ": " + Balance);
            System.out.println("Account balance for " + accountName + ": [Balance not available ]");
        } else {
            System.out.println("No account is currently logged in.");
        }
    }

    public static void createDiningMenu() {
        System.out.print("Enter the breakfast name: ");
        String breakfast = scanner.nextLine();
        
        System.out.print("Enter the lunch name: ");
        String lunch = scanner.nextLine();
        
        System.out.print("Enter the snack name: ");
        String snack = scanner.nextLine();

        DinnerMenu dinnerMenu = new DinnerMenu(breakfast, lunch, snack);

        
        System.out.println("Dining menu created successfully!"+dinnerMenu);
    }

    public static void declareTemporalAdmin() {
        
        System.out.print("Enter the email of the account to declare as temporal admin: ");
        String email = scanner.nextLine();

        
        System.out.println("Account with email " + email + " declared as temporal admin.");
    }
}


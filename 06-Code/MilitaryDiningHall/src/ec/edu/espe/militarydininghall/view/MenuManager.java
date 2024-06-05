package ec.edu.espe.militarydininghall.view;

import Utils.Accounts;
import Utils.Validations;
import java.io.IOException;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class MenuManager {
    public static void mainMenu(){
        try {
            Accounts accountManager = new Accounts("accounts.json");
            System.out.println("Wellcome to the The Military Dining Hall Aplication");
            System.out.println("Select a option: \n1.- Login \n2.- Create a account \nEnter the option: ");
            int option;
            do {
                option = Validations.valideInt();
                switch (option) {
                    case 1 -> {
                        while (!Accounts.login()) {
                            System.out.println("Account does not exist, try again.");
                        }

                    }
                    case 2 -> Accounts.createAccount();
                    default -> System.out.println("Invalid option, enter again: ");
                }
            } while (option != 1);
        } catch (IOException e) {
            System.out.println("An error occurred while initializing the account manager: " + e.getMessage());
        }
    }
    
    public static void commensalMenu(){
        System.out.println("Commensal Menu:");
        System.out.println("1. Book a Day");
        System.out.println("2. Unbook a Day");
        System.out.println("3. See Account Balance");
    }
    
    public static void chefMenu(){
        System.out.println("Chef Menu:");
        System.out.println("1. Create Dinner Menu");
    }
            
    public static void adminMenu(){
        System.out.println("Admin Menu");
        System.out.println("1. Edit Register");
    }
            
    public static void generalAdmin(){
        System.out.println("General Admin Menu:");
        System.out.println("1. Add Temporal Admin");
        System.out.println("2. Back to Main Menu");
    }        
        
}

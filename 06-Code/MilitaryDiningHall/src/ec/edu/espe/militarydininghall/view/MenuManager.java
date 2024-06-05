package ec.edu.espe.militarydininghall.view;

import Utils.Validations;
import java.io.IOException;
import java.util.Scanner;
import utils.AccountsActions;
import ec.edu.espe.militarydininghall.view.ActionsMenuManager;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class MenuManager {
    public static void mainMenu() throws IOException{
        int lowerOption = 1, higherOption = 2, option; //the integrers lowerOption and HIgherOption are usefulls for the valideInt function

        System.out.println("Wellcome to the The Military Dining Hall Aplication");
        System.out.println("Select a option: \n1.- Login \n2.- Create a account \nEnter the option: ");
            
        option = Validations.valideInt(lowerOption, higherOption);
            
        switch (option) {
            case 1 -> {
                while (!AccountsActions.login()) {
                    System.out.println("Account does not exist, try again.");
                }
            }
            case 2 -> AccountsActions.createAccount();
        }
    }
    
    public static void commensalMenu() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int option, loweOption = 1, higherOption = 4;
        
        System.out.println("Commensal Menu:");
        System.out.println("1. Book a Day");
        System.out.println("2. Unbook a Day");
        System.out.println("3. See Account Balance");
        System.out.println("4. Exit");
        option = Validations.valideInt(loweOption, higherOption);
        
        switch (option) {
            case 1 -> ActionsMenuManager.bookADay();

            case 2 -> ActionsMenuManager.unbookADay();
            
            case 3 -> ActionsMenuManager.seeAccountBalance();
            
            case 4 -> MenuManager.mainMenu();
        }
    }
    
    public static void chefMenu() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int option, loweOption = 1, higherOption = 5;
        
        System.out.println("Chef Menu:");
        System.out.println("1. Create Dinner Menu");
        System.out.println("2. Book a Day");
        System.out.println("3. Unbook a Day");
        System.out.println("4. See Account Balance");
        System.out.println("5. Exit");
        
        option = Validations.valideInt(loweOption, higherOption);
        
        switch (option) {
            case 1 -> ActionsMenuManager.createDiningMenu();

            case 2 -> ActionsMenuManager.bookADay();
            
            case 3 -> ActionsMenuManager.unbookADay();
            
            case 4 -> ActionsMenuManager.seeAccountBalance();
            
            case 5 -> MenuManager.mainMenu();
        }
    }
    
    public static void adminMenu() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int option, loweOption = 1, higherOption = 5;
        
        System.out.println("Admin Menu");
        System.out.println("1. Edit Register");
        System.out.println("2. Book a Day");
        System.out.println("3. Unbook a Day");
        System.out.println("4. See Account Balance");
        System.out.println("5. Exit");
        
        option = Validations.valideInt(loweOption, higherOption);
        
        switch (option) {
            case 1 -> ActionsMenuManager.createDiningMenu();

            case 2 -> ActionsMenuManager.bookADay();
            
            case 3 -> ActionsMenuManager.unbookADay();
            
            case 4 -> ActionsMenuManager.seeAccountBalance();
            
            case 5 -> MenuManager.mainMenu();
        }
    }
            
    public static void generalAdmin() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int option, loweOption = 1, higherOption = 5;
        
        System.out.println("General Admin Menu:");
        System.out.println("1. Add Temporal Admin");
        System.out.println("2. Book a Day");
        System.out.println("3. Unbook a Day");
        System.out.println("4. See Account Balance");
        System.out.println("5. Exit");
        
        option = Validations.valideInt(loweOption, higherOption);
        
        switch (option) {
            case 1 -> ActionsMenuManager.declareTemporalAdmin();

            case 2 -> ActionsMenuManager.bookADay();
            
            case 3 -> ActionsMenuManager.unbookADay();
            
            case 4 -> ActionsMenuManager.seeAccountBalance();
            
            case 5 -> MenuManager.mainMenu();
        }
    }               
}
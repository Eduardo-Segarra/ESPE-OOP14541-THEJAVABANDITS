package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.Accounts;
import java.io.IOException;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class MilitaryDiningHallAplication {

    public static void main(String[] args) throws IOException {
        MenuManager.mainMenu();
        
        switch (Accounts.getCurrentAccountType()) {
            case "commensal" -> MenuManager.commensalMenu();

            case "administrator" -> MenuManager.adminMenu();
            
            case "generalAdministrator" -> MenuManager.generalAdmin();
            
            case "militaryChef" -> MenuManager.chefMenu();
        }
        
        
    }
}
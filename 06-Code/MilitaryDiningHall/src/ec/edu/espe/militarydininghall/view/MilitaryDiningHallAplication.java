package ec.edu.espe.militarydininghall.view;

import Utils.Accounts;
import java.io.IOException;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class MilitaryDiningHallAplication {

    public static void main(String[] args) throws IOException {
        MenuManager.mainMenu();
        
        if ("Commensal".equals(Accounts.getCurrentAccountType())){
            MenuManager.commensalMenu();
        } 
    }
}

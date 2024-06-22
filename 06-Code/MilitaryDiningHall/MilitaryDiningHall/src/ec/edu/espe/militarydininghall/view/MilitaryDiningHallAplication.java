package ec.edu.espe.militarydininghall.view;

import java.io.IOException;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class MilitaryDiningHallAplication {

    public static void main(String[] args) throws IOException {
        String accountType = "";

        accountType = MenuManager.mainMenu();

        String[] parts = accountType.split(":");

        switch (parts[5]) {
            case "commensals" ->
                MenuManager.commensalMenu(Integer.parseInt(parts[0]), parts[2], parts[3]);
            case "administrators" ->
                MenuManager.adminMenu(Integer.parseInt(parts[0]), parts[2], parts[3]);
            case "generalAdministrator" ->
                MenuManager.generalAdminMenu(Integer.parseInt(parts[0]), parts[2], parts[3]);
            case "militaryChef" -> 
                    MenuManager.chefMenu(Integer.parseInt(parts[0]), parts[2], parts[3]);
            case "exit" ->
                System.out.println("Thanks for using the program.");
            default ->
                System.out.println("Unknown account type.");
        }

    }
}
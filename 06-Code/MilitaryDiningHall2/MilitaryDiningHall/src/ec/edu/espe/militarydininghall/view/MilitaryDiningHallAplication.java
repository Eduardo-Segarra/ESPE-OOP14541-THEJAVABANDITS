/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import ec.edu.espe.militarydininghall.model.DateBook;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import utils.FileManager;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class MilitaryDiningHallAplication {

    public static void main(String[] args) throws IOException {
    String accountType = "";

        accountType = MenuManager.mainMenu();

        if (accountType.contains(":")) {
            String[] parts = accountType.split(":");

            switch (parts[0]) {
                case "commensals" ->
                    MenuManager.commensalMenu(Integer.parseInt(parts[1]));
                case "administrators" ->
                    MenuManager.adminMenu(Integer.parseInt(parts[1]));
              /*case "generalAdministrator" -> 
                    MenuManager.generalAdmin();
                case "militaryChef" -> 
                    MenuManager.chefMenu();*/
                case "exit" ->
                    System.out.println("Thanks for using the program.");
                default ->
                    System.out.println("Unknown account type.");
            }
        } else {
            System.out.println("Invalid account type returned: " + accountType);
        }

    }
}

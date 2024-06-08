/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import java.io.IOException;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class MilitaryDiningHallAplication {

    public static void main(String[] args) throws IOException {
        String accountType = "";

        accountType = MenuManager.mainMenu();
        
        String[] parts = accountType.split(":");

        String type = parts[0];
        int id = Integer.parseInt(parts[1]);

        switch (type) {
            case "commensals" ->
                MenuManager.commensalMenu(id);

            case "administrators" ->
                MenuManager.adminMenu(id);

            //case "generalAdministrator" ->
            //MenuManager.generalAdmin();
            //case "militaryChef" ->
            //MenuManager.chefMenu();
            default -> {
                break;
            }
        }

    }
}

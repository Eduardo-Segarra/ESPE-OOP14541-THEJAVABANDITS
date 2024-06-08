/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class MilitaryDiningHallAplication {
    public static void main(String[] args){
        MenuManager.mainMenu();

        switch ("Commensal") {
            case "Commensal" -> {
                MenuManager.commensalMenu();
            }

            case "Administrator" ->
                MenuManager.adminMenu();

            //case "General Administrator" ->
                //MenuManager.generalAdmin();

            //case "Military Chef" ->
                //MenuManager.chefMenu();
        }

    }
}
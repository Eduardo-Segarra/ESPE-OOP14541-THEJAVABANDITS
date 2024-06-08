/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.view;

import java.util.Scanner;
import utils.Validations;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class MenuManager {

    public static void mainMenu() {
        int option = 0, loweOption = 1, higherOption = 2;
        System.out.println("Wellcome to the The Military Dining Hall Aplication");
        System.out.println("Select a option: \n1.- Login \n2.- Create a account \nEnter the option: ");

        Validations.valideInt(loweOption, higherOption, option);
        /*
        switch (option) {
            case 1 ->
                MenuManager.commensalMenu();{
            while (!Accounts.login()) {
                    System.out.println("Account does not exist, try again.");
                }
            }
            case 2 ->
                Accounts.createAccount();
        }
         */
    }

    public static void commensalMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = 0, loweOption = 1, higherOption = 4;

        System.out.println("Commensal Menu:");
        System.out.println("1. Book a Day");
        System.out.println("2. Unbook a Day");
        System.out.println("3. See Account Balance");
        System.out.println("4. Exit");

        Validations.valideInt(loweOption, higherOption, option);
        /*
            switch (option) {
                case 1 -> {
                    System.out.print("Enter the day to book (DD): ");
                    int day = Validations.valideInt(1, 31);
                    System.out.print("Enter the month to book (MM): ");
                    int month = Validations.valideInt(1, 12);

                    commensal.bookDay("2024" + "-" + String.valueOf(day) + "-" + String.valueOf(month), commensal.getName());
                    System.out.println("Day register!!");

                }
                case 2 -> {
                    System.out.print("Enter the day to remove (DD): ");
                    int day = Validations.valideInt(1, 31);
                    System.out.print("Enter the month to remove (MM): ");
                    int month = Validations.valideInt(1, 12);

                    commensal.unbookDay("2024" + "-" + String.valueOf(day) + "-" + String.valueOf(month));
                    System.out.println("Day remove!!");
                }

                case 3 ->
                    ActionsMenuManager.seeAccountBalance();

                case 4 -> {
                    commensals.put(commensal.getId(), commensal);
                    FileManager.saveCommensal(fileName, commensals);
                    MenuManager.mainMenu();
                }
            }
         */
    }

    public static void adminMenu() {
        int option = 0, loweOption = 1, higherOption = 5;

        System.out.println("Admin Menu");
        System.out.println("1. Edit Register");
        System.out.println("2. Book a Day");
        System.out.println("3. Unbook a Day");
        System.out.println("4. See Account Balance");
        System.out.println("5. Exit");

        Validations.valideInt(loweOption, higherOption, option);
        /*
        switch (option) {
            case 1 ->
                ActionsMenuManager.createDiningMenu();

            case 2 ->
                ActionsMenuManager.bookADay();

            case 3 ->
                ActionsMenuManager.unbookADay();

            case 4 ->
                ActionsMenuManager.seeAccountBalance();

            case 5 ->
                MenuManager.mainMenu();
        }
         */
    }
}

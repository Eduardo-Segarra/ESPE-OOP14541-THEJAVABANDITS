package utils;

import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class Accounts {

    public static String logIn() {
        Scanner scanner = new Scanner(System.in);
        String email, password, accountType = "";

        System.out.println("Enter your email:");
        email = scanner.nextLine();
        System.out.println("Enter your password:");
        password = scanner.nextLine();

        accountType = FileManager.findAccount("commensals.json", 0, email, password);
        if (accountType != null) {
            return accountType;
        }

        accountType = FileManager.findAccount("militaryChefs.json", 0, email, password);
        if(accountType != null){
            return accountType;
        }
        
        /*accountType = FileManager.findAccount("administratos.json", 0, email, password);
        if(accountType != null){
            return accountType;
        }*/
        accountType = FileManager.findAccount("generalAdministrator.json", 0, email, password);
        if (accountType != null) {
            return accountType;
        }

        return null;
    }

    public static String createNewAccount() {
        Scanner scanner = new Scanner(System.in);
        int id;
        String name, grade, type, email, password;

        System.out.println("Enter your id:");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your name:");
        name = scanner.nextLine();
        System.out.println("Enter your military grade (if your are a public servant type publicServant):");
        grade = scanner.nextLine();
        System.out.println("Enter your email:");
        email = scanner.nextLine();
        System.out.println("Enter your password:");
        password = scanner.nextLine();
        type = "commensals";

        Commensal newCommensal = new Commensal(id, name, email, password, grade, type, 0);

        FileManager.save(newCommensal, "commensals");

        // Crea su nombre dentro de la agenda
        Map<String, Boolean> emptyDays = new HashMap<>();
        DateBook datebook = new DateBook(id, emptyDays);
        FileManager.saveDateBook(datebook);

        return newCommensal.getId() + ":" + newCommensal.getName() + ":" + newCommensal.getEmail() + ":" + newCommensal.getPassword() + ":" + newCommensal.getGrade() + ":" + newCommensal.getType() + ":" + newCommensal.getBalance();
    }
}

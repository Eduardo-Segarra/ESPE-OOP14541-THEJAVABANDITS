package utils;

import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utils.Validations.IdValidator;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class Accounts {

    public static String logIn() {
        Scanner scanner = new Scanner(System.in);
        String email, password, accountType = "";
        int attempts;

        for (attempts = 0; attempts < 3; attempts++) {
            System.out.println("Enter your email:");
            email = scanner.nextLine();
            System.out.println("Enter your password:");
            password = scanner.nextLine();

            accountType = FileManager.findAccount("commensals.json", 0, email, password);
            if (accountType != null) {
                return accountType;
            }

            accountType = FileManager.findAccount("militaryChefs.json", 0, email, password);
            if (accountType != null) {
                return accountType;
            }

            accountType = FileManager.findAccount("administrators.json", 0, email, password);
            if (accountType != null) {
                return accountType;
            }

            accountType = FileManager.findAccount("generalAdministrator.json", 0, email, password);
            if (accountType != null) {
                return accountType;
            }

            if (attempts < 2) { 
                System.out.println("Incorrect email or password. You have " + (2 - attempts) + " attempt(s) left.");
            } else if (attempts == 2) {
                return "0: : : : :exit: : ";
            }
        }

        return null;
    }

    public static String createNewAccount() {
        Scanner scanner = new Scanner(System.in);
        long id = 0;
        boolean isValid = false;
        boolean isDuplicate = false;
        String name, grade, type, email, password;

        while (!isValid || isDuplicate) {
            System.out.println("Enter your ID (10 digits):");
            String input = scanner.nextLine();

            if (input.matches("\\d{10}")) {
                id = Long.parseLong(input);
                if (IdValidator.validateId(id)) {
                    isDuplicate=FileManager.findAccountById("commensals.json", id);
                    if (isDuplicate){
                    System.out.println("The ID has already been entered. Please try again.");    
                    }else{
                        isValid=true;
                    }
                } else {
                     System.out.println("Invalid ID format. Please enter a valid 10-digit ID.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric 10-digit ID.");
            }
        }
        System.out.println("Enter your name:");
        name = scanner.nextLine();
        System.out.println("Enter your military grade (if you are a public servant type publicServant):");
        grade = scanner.nextLine();

         
        List<String> existingEmails = FileManager.getAllEmails();

        
        while (true) {
            System.out.println("Enter your email:");
            email = scanner.nextLine();
            if (FileManager.isValidEmailFormat(email) && FileManager.isUniqueEmail(email, existingEmails)) {
                break;
            } else {
                System.out.println("Invalid email or email already exists. Please enter a valid email.");
            }
        }

        System.out.println("Enter your password:");
        password = scanner.nextLine();
        type = "commensals";

        Commensal newCommensal = new Commensal(id, name, email, password, grade, type, 0);

        FileManager.save(newCommensal, "commensals");

        
        Map<String, Boolean> emptyDays = new HashMap<>();
        DateBook datebook = new DateBook(id, emptyDays);
        FileManager.saveDateBook(datebook);

        return newCommensal.getId() + ":" + newCommensal.getName() + ":" + newCommensal.getEmail() + ":" + newCommensal.getPassword() + ":" + newCommensal.getGrade() + ":" + newCommensal.getType() + ":" + newCommensal.getBalance();
    }
}

package utils;

import ec.edu.espe.militarydininghall.model.Commensal;
import ec.edu.espe.militarydininghall.model.DateBook;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

            if (attempts < 2) { // Check to avoid printing after the last attempt
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
            System.out.println("Enter your id:");
            id = scanner.nextLong();
            isValid = Validations.IdValidator.validateId(id);
            if (!isValid) {
                System.out.println("The ID is not valid. Please try again.");
                continue;
            }

            isDuplicate = FileManager.findAccountById("commensals.json", id);
            if (isDuplicate) {
                System.out.println("The ID has already been entered. Please try again.");
            }
        }

        System.out.println("The ID is valid and not a duplicate.");

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

        // Create his name in the date book
        Map<String, Boolean> emptyDays = new HashMap<>();
        DateBook datebook = new DateBook(id, emptyDays);
        FileManager.saveDateBook(datebook);

        return newCommensal.getId() + ":" + newCommensal.getName() + ":" + newCommensal.getEmail() + ":" + newCommensal.getPassword() + ":" + newCommensal.getGrade() + ":" + newCommensal.getType() + ":" + newCommensal.getBalance();
    }
}

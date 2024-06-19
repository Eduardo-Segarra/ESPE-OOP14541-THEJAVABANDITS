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
        long id;
        String name, grade, type, email, password;

        while (true) {
            System.out.println("Enter your id:");
            id = scanner.nextLong();
            scanner.nextLine(); // Consumir el newline que queda en el buffer
            if (IdValidator.validateId(id)) {
                break;
            } else {
                System.out.println("Invalid ID. Please enter a valid 10-digit ID.");
            }
        }

        System.out.println("Enter your name:");
        name = scanner.nextLine();
        System.out.println("Enter your military grade (if you are a public servant type publicServant):");
        grade = scanner.nextLine();

         // FileManager para obtener la lista de correos electrónicos existentes
    List<String> existingEmails = FileManager.getAllEmailsFromCommensals();

    // Validación del correo electrónico hasta que sea correcto y único
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

    // Crear su nombre en el libro de fecha
    Map<String, Boolean> emptyDays = new HashMap<>();
    DateBook datebook = new DateBook(id, emptyDays);
    FileManager.saveDateBook(datebook);

    return newCommensal.getId() + ":" + newCommensal.getName() + ":" + newCommensal.getEmail() + ":" + newCommensal.getPassword() + ":" + newCommensal.getGrade() + ":" + newCommensal.getType() + ":" + newCommensal.getBalance();
}
}
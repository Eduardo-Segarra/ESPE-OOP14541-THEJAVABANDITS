/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ec.edu.espe.militarydininghall.model.Accounts;
import java.util.Scanner;
import Utils.FileManager;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */

public class AccountsActions {
    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the email: ");
        String email = scanner.nextLine();
        System.out.print("Enter the password: ");
        String password = scanner.nextLine();

        Account account = accounts.get(email);

        if (account != null && account.getPassword().equals(password)) {
            currentAccount = account;
            System.out.println("Welcome " + account.getName());
            System.out.println("You entered with a " + account.getType() + " account");
            return true;
        }
        return false;
    }

    public static void createAccount() throws IOException {
        String name, email, password, type, fileName = "Accounts", newAccountData;
        
        System.out.print("Enter the name: ");
        name = scanner.nextLine();
        System.out.print("Enter the email: ");
        email = scanner.nextLine();
        System.out.print("Enter the password: ");
        password = scanner.nextLine();
        type = "Commensal";

        if (accounts == null) {
            accounts = new HashMap<>();
        }

        Accounts.Account account = new Accounts.Account(name, email, password, type);
        accounts.put(email, account);
        objectMapper.writeValue(file, accounts);
        
        newAccountData = account.toStringFormatJSON();
        FileManager.save(newAccountData, fileName);
        
        System.out.println("Account successfully created!!!");
    }
}

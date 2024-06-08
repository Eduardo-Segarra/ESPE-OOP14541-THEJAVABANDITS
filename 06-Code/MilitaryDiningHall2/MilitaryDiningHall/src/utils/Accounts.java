/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ec.edu.espe.militarydininghall.model.Commensal;
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
        scanner.nextLine();               
        System.out.println("Enter your password:");
        password = scanner.nextLine();

        accountType = FileManager.findAccountByEmail("commensals.json", email);
        return accountType;
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
        scanner.nextLine();                
        System.out.println("Enter your military grade (if your are a public servant type publicServatn):");
        grade = scanner.nextLine();
        scanner.nextLine();                
        System.out.println("Enter your email:");
        email = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Enter your password:");
        password = scanner.nextLine();
        scanner.nextLine();                
        type = "commensals";

        Commensal newCommensal = new Commensal(id, name, email, password, grade, type, 0, null);

        FileManager.save(newCommensal.toStringJSON(), "commensals");
        return newCommensal.getType() + ":" + newCommensal.getId();
    }
}

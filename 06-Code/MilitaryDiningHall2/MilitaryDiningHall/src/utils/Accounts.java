/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ec.edu.espe.militarydininghall.model.Commensal;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Enter your military grade (if your are a public servant type publicServant):");
        grade = scanner.nextLine();
        System.out.println("Enter your email:");
        email = scanner.nextLine();
        System.out.println("Enter your password:");
        password = scanner.nextLine();
        type = "commensals";
        List<String> daysReserved = new ArrayList<>();
        daysReserved.add("No days reserved yet");

        Commensal newCommensal = new Commensal(id, name, email, password, grade, type, 0, daysReserved);

        FileManager.save(newCommensal, "commensals");
        return newCommensal.getType() + ":" + newCommensal.getId();
    }
}

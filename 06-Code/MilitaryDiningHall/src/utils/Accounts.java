/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author THEJAVABANDITS,DCCO-ESPE
 */


public class Accounts {

    private static final String ACCOUNTS_TXT_FILE_PATH = "accounts.txt"; // Ruta del archivo de cuentas en formato txt
    private static final String ACCOUNTS_CSV_FILE_PATH = "accounts.csv"; // Ruta del archivo de cuentas en formato csv
    private Map<String, String> accountDatabase; // Mapa para almacenar las cuentas

    public Accounts() {
        // Inicializamos el mapa de cuentas
        accountDatabase = new HashMap<>();
        // Cargamos las cuentas desde los archivos al iniciar la aplicación
        loadAccountsFromTxt();
        loadAccountsFromCsv();
    }

    // Método para iniciar sesión
    public boolean logIn(String username, String password) {
        if (accountDatabase.containsKey(username)) {
            String storedPassword = accountDatabase.get(username);
            if (storedPassword.equals(password)) {
                System.out.println("Inicio de sesión exitoso para el usuario: " + username);
                return true;
            } else {
                System.out.println("Error: Contraseña incorrecta para el usuario: " + username);
                return false;
            }
        } else {
            System.out.println("Error: El usuario " + username + " no existe");
            return false;
        }
    }

    // Método para crear cuenta
    public boolean createAccount(String username, String password) {
        if (accountDatabase.containsKey(username)) {
            System.out.println("Error: El usuario " + username + " ya existe");
            return false;
        } else {
            accountDatabase.put(username, password);
            saveAccountsToTxt(); // Guardamos la cuenta en el archivo txt
            saveAccountsToCsv(); // Guardamos la cuenta en el archivo csv
            System.out.println("Cuenta creada exitosamente para el usuario: " + username);
            return true;
        }
    }

    // Método para cargar las cuentas desde el archivo txt
    private void loadAccountsFromTxt() {
        File file = new File(ACCOUNTS_TXT_FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String username = parts[0];
                        String password = parts[1];
                        accountDatabase.put(username, password);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para guardar las cuentas en el archivo txt
    private void saveAccountsToTxt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_TXT_FILE_PATH))) {
            for (Map.Entry<String, String> entry : accountDatabase.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar las cuentas desde el archivo csv
    private void loadAccountsFromCsv() {
        File file = new File(ACCOUNTS_CSV_FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String username = parts[0];
                        String password = parts[1];
                        accountDatabase.put(username, password);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para guardar las cuentas en el archivo csv
    private void saveAccountsToCsv() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_CSV_FILE_PATH))) {
            for (Map.Entry<String, String> entry : accountDatabase.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


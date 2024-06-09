/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.militarydininghall.model.Commensal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class FileManager {

    public static void save(Commensal newCommensal, String fileName) {
        fileName = fileName + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Commensal> commensals = new ArrayList<>();

        // Leer el archivo JSON existente
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            Type listType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            commensals = gson.fromJson(bufferedReader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new one.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Agregar el nuevo objeto a la lista
        commensals.add(newCommensal);

        // Guardar la lista actualizada de nuevo en el archivo JSON
        try (FileWriter fileWriter = new FileWriter(fileName); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            gson.toJson(commensals, bufferedWriter);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void updateAccount(Commensal updatedCommensal, String fileName, Commensal oldCommensalInformation) {
        fileName = fileName + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Commensal> commensals = new ArrayList<>();

        // Leer el archivo JSON existente
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            Type listType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            commensals = gson.fromJson(bufferedReader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new one.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Reemplazar la información antigua con la nueva
        boolean found = false;
        for (int i = 0; i < commensals.size(); i++) {
            if (commensals.get(i).getId() == oldCommensalInformation.getId()) {
                commensals.set(i, updatedCommensal);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Old commensal information not found, adding new commensal.");
            commensals.add(updatedCommensal);
        }

        // Guardar la lista actualizada de nuevo en el archivo JSON
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            gson.toJson(commensals, bufferedWriter);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static String findAccountById(String fileName, int idSearch) {
        List<Commensal> foundAccount = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Commensal account : foundAccount) {
                if (account.getId() == idSearch) {
                    return gson.toJson(account);
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }

    public static String findAccountByEmail(String fileName, String Email) {
        List<Commensal> foundAccount = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Commensal account : foundAccount) {
                if (Email.equalsIgnoreCase(account.getEmail())) {
                    return account.getType() + ":" + account.getId();
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }

    public static String findAccount(String fileName, int idSearch) {
        List<Commensal> foundAccount = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Commensal>>() {
            }.getType();
            foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Commensal account : foundAccount) {
                if (account.getId() == idSearch) {
                    String data = account.getId() + ":" + account.getName() + ":" + account.getEmail() + ":" + account.getPassword() + ":" + account.getGrade() + ":" + account.getType() + ":" + account.getBalance() + ":" + account.getDaysReserved();
                    return data;
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }
}

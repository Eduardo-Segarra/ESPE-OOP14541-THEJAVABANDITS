/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.militarydininghall.model.Commensal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    public static void saveNewData(String data, String fileName) {
        fileName = fileName + ".json";

        try (FileWriter fileWriter = new FileWriter(fileName, false); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(data);
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void save(String data, String fileName) {
        fileName = fileName + ".json";

        try (FileWriter fileWriter = new FileWriter(fileName, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(data);
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void updateAccount(String newData, String fileName, int idSearch) {
        List<Commensal> foundAccount = new ArrayList<>();
        int interator = 0;
        Gson gson = new Gson();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
                
                Type accountListType = new TypeToken<ArrayList<Commensal>>(){}.getType();
                foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

                for (Commensal account : foundAccount) {
                    if (account.getId() == idSearch) {
                        if(interator == 0){
                            saveNewData(newData, fileName);
                            interator++;
                        }else{
                            save(newData, fileName);
                        }
                    }else{
                        if(interator == 0){
                            saveNewData(account.toStringJSON(), fileName);
                            interator++;
                        }else{
                            save(account.toStringJSON(), fileName);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
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
                if (account.getEmail() == Email) {
                    return account.getType() + ":" + account.getId();
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }

    public static Commensal findAccount(String fileName, int idSearch) {
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
                    return account;
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }
}

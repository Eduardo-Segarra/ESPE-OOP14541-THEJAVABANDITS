/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class FileManager {

    public static void saveNewData(String data, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName, false); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(data);
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void save(String data, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(data);
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void findBookedDay() {

    }
/*
    public static String findAccount(String fileName, int idSearch) {
        List<Account> foundAccount = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Account>>(){}.getType();
            foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Account account : foundAccount) {
                if (account.getId() == idSearch) {
                    return gson.toJson(account);
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return null;
    }
    */
    /*
    public static boolean changeAccountGrade(String fileName, int idSearch, String newGrade){
        List<Account> foundAccount = new ArrayList<>();
        Gson gson = new Gson();
        boolean result = false;

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;

            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Account>>(){}.getType();
            foundAccount = gson.fromJson(dataInJSON.toString(), accountListType);

            for (Account account : foundAccount) {
                if (account.getId() == idSearch) {
                    account.setGrade(newGrade);
                    result = true;
                }
            }

        } catch (IOException e) {
            System.err.println("Error at finding data of the file: " + e.getMessage());
        }
        return result;
    }
    */
    /*
    public static String loadData(String fileName) {
        List<Account> accountRecord = new ArrayList<>();
        Gson gson = new Gson();
        String data = "";

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataInJSON = new StringBuilder();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                dataInJSON.append(line);
            }

            Type accountListType = new TypeToken<ArrayList<Account>>(){}.getType();
            accountRecord = gson.fromJson(dataInJSON.toString(), accountListType);

            data = gson.toJson(accountRecord);
            return data;

        } catch (IOException e) {
            System.err.println("Error at reading the file: " + e.getMessage());
        }
        return null;
    }
    */
}

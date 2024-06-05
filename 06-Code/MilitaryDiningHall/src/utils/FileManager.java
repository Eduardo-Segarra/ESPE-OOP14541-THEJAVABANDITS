/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
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
    
    public static void findBookedDay(){
        
    }
    
    public static void findAccount(){
        
    }
    
    public static void changeAccountType(){
        
    }
    
    public static void changeAccountGrade(){
        
    }
    
    public static void loadData(){
        
    }
}

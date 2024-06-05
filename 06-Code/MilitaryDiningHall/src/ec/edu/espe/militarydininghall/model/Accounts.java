/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */

public class Accounts {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Utils.Accounts.Account> accounts;
    private static ObjectMapper objectMapper;
    private static File file;
    private static ec.edu.espe.militarydininghall.model.Accounts.Account currentAccount;

    public Accounts(String filename) throws IOException {
        objectMapper = new ObjectMapper();
        file = new File(filename);
        if (file.exists()) {
            accounts = objectMapper.readValue(file, TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Utils.Accounts.Account.class));
        } else {
            accounts = new HashMap<>();
        }
    }
        
    public static String getCurrentAccountName() {
        return currentAccount != null ? currentAccount.getName() : null;
    }

    public static String getCurrentAccountEmail() {
        return currentAccount != null ? currentAccount.getEmail() : null;
    }

    public static String getCurrentAccountType() {
        return currentAccount != null ? currentAccount.getType() : null;
    }
    
    public static class Account {

        private int id;
        private String name;
        private String email;
        private String password;
        private String grade;
        private String type;

        public Account(int id, String name, String email, String password, String grade, String type) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.grade = grade;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Accounts{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", grade=" + grade + ", type=" + type + '}';
        }

        public String toStringJSONFormat() {
            String accountData = "";

            accountData = "{"
                    + "\"id\":" + id + ","
                    + "\"name\":\"" + name + "\","
                    + "\"email\":" + email + ","
                    + "\"password\":" + password + ","
                    + "\"grade\":" + grade + ","
                    + "\"type\":" + type
                    + "}";

            return accountData;
        }
    }
}

package Utils;

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
    private static Map<String, Account> accounts;
    private static ObjectMapper objectMapper;
    private static File file;
    private static Account currentAccount;

    public Accounts(String filename) throws IOException {
        objectMapper = new ObjectMapper();
        file = new File(filename);
        if (file.exists()) {
            accounts = objectMapper.readValue(file, TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Account.class));
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
        
    public static boolean login() {
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

        Account account = new Account(name, email, password, type);
        accounts.put(email, account);
        objectMapper.writeValue(file, accounts);
        
        newAccountData = account.toStringFormatJSON();
        FileManager.save(newAccountData, fileName);
        
        System.out.println("Account successfully created!!!");
    }

    public static class Account {

        private final String name;
        private final String email;
        private final String password;
        private final String type;

        public Account(String name, String email, String password, String type) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.type = type;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Account{" + "name=" + name + ", email=" + email + ", password=" + password + ", type=" + type + '}';
        }

        public String toStringFormatJSON() {
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

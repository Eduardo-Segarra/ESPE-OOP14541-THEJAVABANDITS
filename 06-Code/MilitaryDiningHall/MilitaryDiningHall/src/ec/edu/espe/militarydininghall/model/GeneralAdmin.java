package ec.edu.espe.militarydininghall.model;

import com.google.gson.Gson;
import ec.edu.espe.militarydininghall.view.MenuManager;
import java.util.List;
import java.util.Scanner;
import utils.FileManager;
import utils.Validation;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class GeneralAdmin {

    private long id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private String type;
    private float balance;

    public GeneralAdmin(long id, String name, String email, String password, String grade, String type, float balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.type = type;
        this.balance = balance;
    }


    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public static void seeAccountBalance(int id, String fileName, String email, String password) {
        String data = "";
        data = FileManager.findAccount(fileName + ".json", id, email, password);
        String[] parts = data.split(":");

        System.out.println("Your account balance is: " + parts[6]);
    }

    public static void setAdministrator(int id, String email, String password) {
        Scanner scanner = new Scanner(System.in);
        int idSearch = 0, attempts;
        String foundAccount = "";
        String[] parts;

        for (attempts = 0; attempts < 5; attempts++) {
            System.out.println("Please type the ID to set a new administrator for the following month:");
            try {
                idSearch = scanner.nextInt();

                foundAccount = FileManager.findAccount("commensals.json", idSearch, "", "");
                if (foundAccount != null) {
                    break;
                }

                if (attempts < 4 && foundAccount == null) { // Check to avoid printing after the last attempt
                    System.out.println("Incorrect ID. You have " + (4 - attempts) + " attempt(s) left.");
                } else if (attempts == 4) {
                    MenuManager.displayGeneralAdminMenu(id, email, password);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                scanner.next(); // Consume the invalid input
                attempts--; // Do not count this as a valid attempt
            }

        }

        if (foundAccount == null) {
            System.out.println("Account not found. Returning to the general admin menu.");
            MenuManager.displayAdminMenu(id, email, password);
            return;
        }

        parts = foundAccount.split(":");

        parts[5] = "administrators";

        Commensal newAdministrator = new Commensal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]));

        FileManager.save(newAdministrator, "administrators");
        FileManager.eraseAccount("commensals", idSearch);

        System.out.println("A new administrator has establish");
    }

    @Override
    public String toString() {
        return "GeneralAdmin{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", grade=" + grade + ", type=" + type + ", balance=" + balance + '}';
    }

    public String toStringJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

package ec.edu.espe.militarydininghall.model;

import com.google.gson.Gson;
import java.util.List;
import java.util.Scanner;
import utils.FileManager;
import utils.Validations;

/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class Chef {

    private long id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private String type;
    private float balance;

    public Chef(long id, String name, String email, String password, String grade, String type, float balance) {
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

    public static void chooseMonthMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("In what month will the menu be made?:");
        int month = Validations.validateMonth();
        System.out.println("What dishes will be served that month?:");
        System.out.println("Breakfast:");
        String breakfast = scanner.nextLine();
        System.out.println("Lunch:");
        String lunch = scanner.nextLine();
        System.out.println("Dinner:");
        String dinner = scanner.nextLine();
        Dish dishes = new Dish(month, breakfast, lunch, dinner);
        FileManager.saveDishes(dishes);
    }

    @Override
    public String toString() {
        return "Chef{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", grade=" + grade + ", type=" + type + ", balance=" + balance + '}';
    }

    public String toStringJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

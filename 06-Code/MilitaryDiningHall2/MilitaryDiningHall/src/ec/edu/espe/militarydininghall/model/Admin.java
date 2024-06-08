/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class Admin {
    private int id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private String type;
    private float balance;
    private List<String> daysReserved;

    public Admin(int id, String name, String email, String password, String grade, String type, float balance, List<String> daysReserved) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.type = type;
        this.balance = balance;
        this.daysReserved = daysReserved;
    }

    /**
     * @return the id
     */
    public int getId() {
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

    /**
     * @return the daysReserved
     */
    public List<String> getDaysReserved() {
        return daysReserved;
    }

    /**
     * @param daysReserved the daysReserved to set
     */
    public void setDaysReserved(List<String> daysReserved) {
        this.daysReserved = daysReserved;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", grade=" + grade + ", type=" + type + ", balance=" + balance + ", daysReserved=" + daysReserved + '}';
    }

    public String toStringJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

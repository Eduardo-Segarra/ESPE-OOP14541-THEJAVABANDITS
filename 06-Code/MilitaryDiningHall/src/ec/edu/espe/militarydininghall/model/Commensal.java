/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

/**
 *
 * @author The Java Bandits, DCCO-ESPE
 */
public class Commensal {
    private int id;
    private String name;
    private String email;
    private String grade;

    public Commensal(int id, String name, String email, String grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    // Getters and Setters
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Operations
    public void bookDay() {
        System.out.println("Day booked by " + name);
    }

    public void unbookDay() {
        System.out.println("Day booking canceled by " + name);
    }

    public void seeAccountBalance() {
        System.out.println("Account balance displayed for " + name);
    }

    @Override
    public String toString() {
        return "Commensal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}




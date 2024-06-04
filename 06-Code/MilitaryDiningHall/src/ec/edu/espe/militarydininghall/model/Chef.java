/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

/**
 *
 * @author THEJAVABANDITS,DCCO-ESPE
 */
public class Chef {
    private int id;
    private String grade;

    public Chef(int id, String grade) {
        this.id = id;
        this.grade = grade;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Operations
    public void createDinnerMenu() {
        // Logic to create the dinner menu
        System.out.println("Dinner menu created");
    }

    @Override
    public String toString() {
        return "Chef{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                '}';
    }
}

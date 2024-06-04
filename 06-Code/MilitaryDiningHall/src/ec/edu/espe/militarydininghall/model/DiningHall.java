/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

import java.util.Arrays;

/**
 *
 * @author THEJAVABANDITS,DCCO-ESPE
 */
public class DiningHall {
    private int id;
    private Commensal[] commensals;

    public DiningHall(int id, Commensal[] commensals) {
        this.id = id;
        this.commensals = commensals;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commensal[] getCommensals() {
        return commensals;
    }

    public void setCommensals(Commensal[] commensals) {
        this.commensals = commensals;
    }

    // Operations
    public void mealSchedule() {
        System.out.println("Meal schedule created");
    }

    public void discountPayment() {
        System.out.println("Payment discounted");
    }

    @Override
    public String toString() {
        return "DiningHall{" +
                "id=" + id +
                ", commensals=" + Arrays.toString(commensals) +
                '}';
    }
}
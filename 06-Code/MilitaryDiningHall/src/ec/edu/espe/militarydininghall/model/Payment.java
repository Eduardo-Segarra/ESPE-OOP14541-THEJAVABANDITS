/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.model;

/**
 *
 * @author David Rodriguez,THEJAVABANDITS,DCCO-ESPE
 */
import java.util.Date;

public class Payment {
    private int idPayment;
    private Date datePayment;
    private float amount;
    private int idMilitary;

    public Payment(int idPayment, Date datePayment, float amount, int idMilitary) {
        this.idPayment = idPayment;
        this.datePayment = datePayment;
        this.amount = amount;
        this.idMilitary = idMilitary;
    }

    // Getters and Setters
    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getIdMilitary() {
        return idMilitary;
    }

    public void setIdMilitary(int idMilitary) {
        this.idMilitary = idMilitary;
    }

    public void payReceive() {
        System.out.println("Payment received: " + amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", datePayment=" + datePayment +
                ", amount=" + amount +
                ", idMilitary=" + idMilitary +
                '}';
    }
}


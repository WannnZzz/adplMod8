/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Lenovo
 */
public class Payment {

    private int id;
    private String namaCus;
    private double amount;
    private String paymentMethod;
    private String status;

    public Payment(String namaCus, double amount,
            String paymentMethod, String status) {

        this.namaCus = namaCus;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Payment(int id, String namaCus, double amount,
            String paymentMethod, String status) {

        this.id = id;
        this.namaCus = namaCus;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNamaCus() {
        return namaCus;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }
}

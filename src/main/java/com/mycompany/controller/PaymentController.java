/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.*;
import com.mycompany.view.PaymentView;
import com.mycompany.repository.PaymentRepository;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PaymentController {

    private PaymentView view;
    private PaymentRepository repository;

    public PaymentController(
            PaymentView view,
            PaymentRepository repository) {

        this.view = view;
        this.repository = repository;

        view.btnPay.addActionListener(e -> processPayment());

        loadPayments();
    }

    private void processPayment() {

        try {
            String name = view.txtName.getText().trim();
            String amountText = view.txtAmount.getText().trim();

            if (name.isEmpty() || amountText.isEmpty()) {
                view.showMessage("Semua data harus diisi!");
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (!Double.isFinite(amount) || amount <= 0) {
                view.showMessage("Nominal harus lebih dari 0!");
                return;
            }

            String selected = view.cmbMethod.getSelectedItem().toString().trim();

            PaymentMethod method;

            if (selected.equalsIgnoreCase("Cash")) {
                method = new Cash();

            } else if (selected.equalsIgnoreCase("QRIS")) {
                method = new Qris();

            } else if (selected.equalsIgnoreCase("Transfer Bank")) {
                method = new Transfer();

            } else {
                view.showMessage("Metode pembayaran tidak valid!");
                return;
            }

            Payment payment = new Payment(
                    name,
                    amount,
                    method.getName(),
                    "Berhasil Dicatat"
            );

            repository.save(payment);

            view.showMessage("Pembayaran berhasil dicatat!");

            view.clearForm();
            loadPayments();

        } catch (NumberFormatException e) {
            view.showMessage("Nominal harus berupa angka!");

        } catch (RuntimeException e) {
            view.showMessage("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private void loadPayments() {

        try {
            view.tableModel.setRowCount(0);

            List<Payment> payments = repository.findAll();

            for (Payment p : payments) {

                view.tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getNamaCus(),
                    p.getAmount(),
                    p.getPaymentMethod(),
                    p.getStatus()
                });
            }

        } catch (RuntimeException e) {
            e.printStackTrace();

            view.showMessage(
                    "Gagal memuat pembayaran: " + e.getMessage()
            );
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.*;
import com.mycompany.view.PaymentView;
import com.mycompany.repository.PaymentRepository;

import javax.swing.JOptionPane;
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

        view.btnDelete.addActionListener(e -> deletePayment());

        view.btnReset.addActionListener(e -> resetPayments());

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

            String selected
                    = (String) view.cmbMethod.getSelectedItem();

            PaymentMethod method;

            if (selected.equalsIgnoreCase("Cash")) {
                method = new Cash();

            } else if (selected.equalsIgnoreCase("Qris")) {
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
                    String.format("%.0f", p.getAmount()),
                    p.getPaymentMethod(),
                    p.getStatus()
                });
            }

        } catch (RuntimeException e) {

            view.showMessage(
                    "Gagal memuat riwayat pembayaran: " + e.getMessage()
            );
        }
    }

    private void deletePayment() {

        int selectedRow = view.table.getSelectedRow();

        if (selectedRow == -1) {

            view.showMessage("Pilih transaksi yang ingin dihapus!");

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Yakin ingin menghapus transaksi ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            try {

                int modelRow
                        = view.table.convertRowIndexToModel(selectedRow);

                int id = Integer.parseInt(
                        view.tableModel.getValueAt(modelRow, 0).toString()
                );

                repository.deleteById(id);

                view.showMessage("Transaksi berhasil dihapus!");

                loadPayments();

            } catch (RuntimeException e) {

                view.showMessage(
                        "Gagal menghapus transaksi: " + e.getMessage()
                );
            }
        }
    }

    private void resetPayments() {

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Yakin ingin menghapus SEMUA data pembayaran?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {

            try {

                repository.deleteAll();

                view.showMessage("Semua transaksi berhasil dihapus!");

                loadPayments();

            } catch (RuntimeException e) {

                view.showMessage(
                        "Gagal mereset data: " + e.getMessage()
                );
            }
        }
    }
}

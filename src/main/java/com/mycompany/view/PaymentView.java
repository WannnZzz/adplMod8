/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author Lenovo
 */
public class PaymentView extends JFrame {

    public JTextField txtName;
    public JTextField txtAmount;

    public JComboBox<String> cmbMethod;

    public JButton btnPay;
    public JButton btnDelete;
    public JButton btnReset;

    public JTable table;
    public DefaultTableModel tableModel;

    public PaymentView() {

        setTitle("Aplikasi Metode Pembayaran");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        txtName = new JTextField();
        txtAmount = new JTextField();

        cmbMethod = new JComboBox<>(
                new String[]{"Cash", "QRIS", "Transfer Bank"}
        );

        btnPay = new JButton("Bayar");

        panel.add(new JLabel("Nama Pembeli"));
        panel.add(txtName);

        panel.add(new JLabel("Jumlah Pembayaran"));
        panel.add(txtAmount);

        panel.add(new JLabel("Metode Pembayaran"));
        panel.add(cmbMethod);

        panel.add(new JLabel(""));
        panel.add(btnPay);

        String[] columns = {
            "ID", "Nama", "Jumlah", "Metode", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        btnDelete = new JButton("Hapus");
        btnReset = new JButton("Reset");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReset);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearForm() {
        txtName.setText("");
        txtAmount.setText("");
        cmbMethod.setSelectedIndex(0);
    }
}

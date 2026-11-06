/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.database.Database;
import com.mycompany.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class JdbcPaymentRepository implements PaymentRepository {

    @Override
    public void save(Payment payment) {

        String sql = "INSERT INTO payments "
                + "(customer_name, amount, payment_method, status) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, payment.getNamaCus());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Gagal menyimpan pembayaran", e);
        }
    }

    @Override
    public List<Payment> findAll() {

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payments ORDER BY id DESC";

        try (Connection conn = Database.getConection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Payment payment = new Payment(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getDouble("amount"),
                        rs.getString("payment_method"),
                        rs.getString("status")
                );

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil data pembayaran: "
                    + e.getMessage(), e);
        }

        return payments;
    }
}

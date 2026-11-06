/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.main;

import com.mycompany.view.PaymentView;
import com.mycompany.controller.PaymentController;
import com.mycompany.repository.JdbcPaymentRepository;
import com.mycompany.repository.PaymentRepository;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lenovo
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaymentView view = new PaymentView();
            PaymentRepository repository = new JdbcPaymentRepository();
            new PaymentController(view, repository);
            view.setVisible(true);
        });
    }
}

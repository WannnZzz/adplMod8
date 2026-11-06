/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.model.Payment;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface PaymentRepository {

    void save(Payment payment);

    List<Payment> findAll();

}

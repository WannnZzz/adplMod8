/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class Database {

    public static Connection getConection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/payment_method";
        String user = "root";
        String PW = "Akbar2207.";

        return DriverManager.getConnection(url, user, PW);
    }
}

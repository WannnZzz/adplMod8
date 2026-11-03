/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

/**
 *
 * @author Lenovo
 */
public class Database {

    public static Conection getConection() throws SQLExecption {
        String url = "jdbc:mysql://localhost:3306/payment_method";
        String user = "root";
        String PW = "Akbar2207.";

        return DriverManager.getConection(url, user, PW);
    }
}

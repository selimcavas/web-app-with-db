package com.paymentdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static String db_url = "jdbc:postgresql://localhost:5432/productDB";
    static String user = "postgres"; 
    static String password = "****";
    static Connection conn;
    

    //a method to execute queries in database
    public static void runQuery(String query){
        Statement statement;

        try {
            conn = DriverManager.getConnection(db_url, user, password);
            System.out.println("Connected to the database");
            
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Query executed");

            conn.close();
            statement.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } 
    }
    //inserts a product to the order table
    public static void insertProduct(String name, int price){
        try {
            conn = DriverManager.getConnection(db_url, user, password);
            String sql = "INSERT INTO orders (name, price, datetime) VALUES (?, ?, now())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            
            System.out.println("Inserted new order!");
            ps.executeUpdate();
            conn.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Failed to insert order!");
            e.printStackTrace();
        }
    }
}

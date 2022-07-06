package com.paymentdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class merchant {
    static String db_url = "jdbc:postgresql://localhost:5432/productDB";
    static String user = "postgres"; 
    static String password = "****";
    static Connection conn;
    static Statement statement;
    public static void main(String[] args) {
        getRequest();
    }
    //a method that creates a JSON Object for each order and adds them to a JSON Array
    public static JSONArray getAllOrdersJSONArray(){

        try {
            conn = DriverManager.getConnection(db_url, user, password);
            System.out.println("Connected to the database.");
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM orders");
            System.out.println("Got the result set!");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            JSONArray result = new JSONArray();
            while(rs.next()){
                JSONObject obj = new JSONObject();
                for(int i = 1; i <= columnsNumber; i++){
                    obj.put(rsmd.getColumnName(i), rs.getString(i));
                }
                result.put(obj);
            }
            return result;

        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
    }

    //a method that returns orders between time stamps
    public static JSONArray getOrdersBetweenTimePeriod(String lowerTime, String upperTime){
      
        try {
            conn = DriverManager.getConnection(db_url, user, password);
            System.out.println("Connected to the database.");
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM orders WHERE datetime <= "+ "'"+ upperTime + "'" + " AND datetime >= " + "'" + lowerTime + "'");
            System.out.println("Got the result set between given times!");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            JSONArray result = new JSONArray();
            while(rs.next()){
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnsNumber; i++) {
                    obj.put(rsmd.getColumnName(i), rs.getString(i));
                }
                result.put(obj);
            }
            
            return result;

        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }

    }

    public static void getRequest(){
        HttpClient client = HttpClient.newHttpClient();
        //create a request to the webserver on the localhost
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/paymentwebapp/")).build();

        try {
            //return the body from the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }


}

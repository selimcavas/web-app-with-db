
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/response")
public class responseHandler extends HttpServlet {
    static String db_url = "jdbc:postgresql://localhost:5432/productDB";
    static String user = "postgres"; 
    static String password = "****";
    static Connection conn;
    static Statement statement;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String lowerTime = request.getParameter("lowerTime");
        String upperTime = request.getParameter("upperTime");

        response.getWriter().println(getOrdersBetweenTimePeriod(lowerTime, upperTime));
        System.out.println(getOrdersBetweenTimePeriod(lowerTime, upperTime));
    }

    public JSONArray getOrdersBetweenTimePeriod(String lowerTime, String upperTime){
      
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
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

}



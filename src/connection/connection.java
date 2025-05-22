/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.*;


/**
 *
 * @author Lenovo
 */
public class connection {
    private Connection conn;
    
    public Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully Connected");
        } catch(ClassNotFoundException e){
            System.out.println("Failed To Connect "+ e);
        }
//        String url = "jdbc:mysql://localhost/db_smartware";
          String url = "jdbc:mysql://localhost:3308/db_smartware?useSSL=false&serverTimezone=UTC";
          
        
        try{
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Successful Database Connection");
        } catch(SQLException e){
            System.out.println("Database Connection Failed " + e);
        }
        return conn;
    }
}

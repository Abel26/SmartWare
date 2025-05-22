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
public class test_connection {
    public static void main(String[] args) {
        Connection conn = new connection().connect();
        
        if (conn != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Failed to Connect Database.");
        }
    }
}

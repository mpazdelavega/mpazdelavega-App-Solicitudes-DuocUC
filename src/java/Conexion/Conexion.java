/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;
/**
 *
 * @author Silvana
 */
public class Conexion {
    
    private static Connection c;
    
    
    //VARIABLES DE ENTORNO LOCAL
    
//    private static String url = "jdbc:mysql://localhost:3306/webdocs";
//    private static String user = "root";
//    private static String pass = "";
    
    //VARIABLES DE ENTORNO EN NUBE
    
    private static String url = "jdbc:mysql://64.227.0.160:3306/webdocs";
    private static String user = "root";
    private static String pass = "Duoc.2020.sql";
        
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
         public static Connection Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                c = DriverManager.getConnection(url,user,pass);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found."); 
        }
        return c;
    }
    
      public void cerrarConexion(Connection c){       
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("ERROR CONEXION AQUI -------------------");
            System.out.println(e.getMessage());
        }
    }          
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.systgastro.model.jdbc;

import java.sql.*;

/**
 * Clase Conexion JDBC 
 * En esta clase definimos algunas variables
 */
/**
 * Creamos el método getConnection 
 * Para que no haya problemas al obtener la conexion de manera concurrente, se 
 * usa la palabra synchronized 
 * Creamos los métodos close para cerrar los objetos de tipo ResulSet 
 * Creamos otro método close para cerrar los objetos de tipo PreparedStatement 
 * Creamos el ultimo método close para cerrar una Connection
 * @author Alumnos de IS II
 * @version 1.0
 */
public class Conexion {
    //Valores de conexion a MySql
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //El puerto es opcional, al igual que el parametro useSSL
    private static final String JDBC_URL = "jdbc:mysql://localhost/systgastro?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private static Driver driver = null;
    //Para que no haya problemas al obtener la conexion de
    //manera concurrente, se usa la palabra synchronized

    /**
     *
     * @return Retorna la conexion a la BD con las parametros requeridos
     * @throws SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    public static synchronized Connection getConnection()throws SQLException {
        if (driver == null) {
            try {
                //Se registra el driver y se levanta el driver MySQL
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        //crea un objeto de tipo conexion
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    //Cierre del resultSet

    /**
     *
     * @param rs Representa el ResultSet
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    //Cierre del PrepareStatement

    /**
     *
     * @param stmt Representa el PreparedStatement
     */
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //Cierre de la conexion

    /**
     *
     * @param conn Representa la coneccion a la BD
     */
     public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
     }
}

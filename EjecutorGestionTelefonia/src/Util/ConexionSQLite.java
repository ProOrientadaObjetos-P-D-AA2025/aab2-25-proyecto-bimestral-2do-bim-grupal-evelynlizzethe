package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {

    private static final String URL = "jdbc:sqlite:DB PROYECTO/clientes.db";

    public static Connection conectar(){
        try{ 
            
            return DriverManager.getConnection(URL);
            
        } catch (SQLException e){
            
            System.out.println("Error de conexion " + e.getMessage());
            return null;
        }
    }
}
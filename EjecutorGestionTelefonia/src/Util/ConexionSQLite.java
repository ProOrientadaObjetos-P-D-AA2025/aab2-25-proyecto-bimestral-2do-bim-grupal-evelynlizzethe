package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {

    private static final String URL = "jdbc:sqlite:DB PROYECTO/clientes.db";
    private static Connection conexion = null;

    public static Connection conectar(){
        try{ 
            
            return DriverManager.getConnection(URL);
            
        } catch (SQLException e){
            
            System.out.println("Error de conexion " + e.getMessage());
            return null;
        }
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:sqlite:clientes.db";
    private static Connection conexion = null;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL);
                System.out.println("Conexion establecida correctamente a la base de datos");
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        }
        return conexion;
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
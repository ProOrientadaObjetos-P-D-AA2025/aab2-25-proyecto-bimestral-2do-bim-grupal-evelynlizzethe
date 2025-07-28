package Util;

import java.nio.file.*;
import java.sql.*;

public class ConexionSQLite {

    private static final String DIRECTORY = "DB";
    private static final String URL = "jdbc:sqlite:" + DIRECTORY + "/clientes.db";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            crearTablas(conn);
            return conn;
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    private static void crearTablas(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS clientes (
                  id_cliente       INTEGER PRIMARY KEY AUTOINCREMENT,
                  nombres          TEXT    NOT NULL,
                  cedula_pasaporte TEXT    NOT NULL,
                  ciudad           TEXT,
                  correo           TEXT,
                  carrera          TEXT,
                  marca            TEXT,
                  modelo           TEXT,
                  numero           TEXT
                );
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS planes (
                  id_plan      INTEGER PRIMARY KEY AUTOINCREMENT,
                  id_cliente   INTEGER NOT NULL,
                  tipo_plan    TEXT    NOT NULL,
                  datos_plan   TEXT,
                  pago_mensual REAL,
                  FOREIGN KEY(id_cliente) REFERENCES clientes(id_cliente)
                );
            """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS facturas (
                    id_factura    INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_cliente    INTEGER NOT NULL,
                    id_plan       INTEGER NOT NULL,
                    total_pagar   REAL,
                    fecha_emision TEXT,   
                    FOREIGN KEY(id_cliente) REFERENCES clientes(id_cliente),
                    FOREIGN KEY(id_plan)    REFERENCES planes(id_plan)
                );
            """);
        }
    }

    public static void main(String[] args) {
        conectar();
    }
}

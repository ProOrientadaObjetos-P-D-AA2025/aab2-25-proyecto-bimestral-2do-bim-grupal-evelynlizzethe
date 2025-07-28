package DAO;

import Modelo.Cliente;
import Modelo.Dispositivo;
import Util.ConexionSQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean crearCliente(Cliente cliente) {
        String sql = """
      INSERT INTO clientes
        (nombres, cedula_pasaporte, ciudad, correo, carrera, marca, modelo, numero)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";

        try (Connection conn = ConexionSQLite.conectar(); // ① Pedimos que nos devuelva el ID autogenerado:
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // ② Aquí _sí_ vinculamos **todos** los parámetros:
            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getCedulaPasaporte());
            pstmt.setString(3, cliente.getCiudad());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setString(5, cliente.getCarrera());

            Dispositivo d = cliente.getDispositivo();
            if (d != null) {
                pstmt.setString(6, d.getMarca());
                pstmt.setString(7, d.getModelo());
                pstmt.setString(8, d.getNumero());
            } else {
                pstmt.setNull(6, Types.VARCHAR);
                pstmt.setNull(7, Types.VARCHAR);
                pstmt.setNull(8, Types.VARCHAR);
            }

            // ③ Ejecutamos la inserción:
            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                return false;  // no se insertó nada
            }

            // ④ Recuperamos el ID recién generado:
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombres"),
                            rs.getString("cedula_pasaporte"),
                            rs.getString("ciudad"),
                            rs.getString("correo"),
                            rs.getString("carrera"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getString("numero")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexionSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombres"),
                        rs.getString("cedula_pasaporte"),
                        rs.getString("ciudad"),
                        rs.getString("correo"),
                        rs.getString("carrera"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("numero")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener todos los clientes: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombres = ?, cedula_pasaporte = ?, ciudad = ?, correo = ?, carrera = ?, marca = ?, modelo = ?, numero = ? WHERE id_cliente = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getCedulaPasaporte());
            pstmt.setString(3, cliente.getCiudad());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setString(5, cliente.getCarrera());
            Dispositivo d = cliente.getDispositivo();
            pstmt.setString(6, d.getMarca());
            pstmt.setString(7, d.getModelo());
            pstmt.setString(8, d.getNumero());
            pstmt.setInt(9, cliente.getIdCliente());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}

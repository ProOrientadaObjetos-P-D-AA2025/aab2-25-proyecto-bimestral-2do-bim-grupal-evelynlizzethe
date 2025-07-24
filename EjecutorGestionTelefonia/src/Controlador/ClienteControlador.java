package Controlador;

import Modelo.Cliente;
import Modelo.Dispositivo;
import Util.ConexionSQLite;

import java.sql.*;
import java.util.*;

public class ClienteControlador {

    public void crear(Cliente c) {
        String sql = "INSERT INTO clientes (nombres, cedula_pasaporte, ciudad, marca_celular, modelo_celular, numero_celular, pago_mensual, email, direccion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getCedulaPasaporte());
            ps.setString(3, c.getCiudad());

            Dispositivo d = c.getDispositivo();
            ps.setString(4, d.getMarca());
            ps.setString(5, d.getModelo());
            ps.setString(6, d.getNumero());
            ps.setDouble(7, d.getPago());

            ps.setString(8, c.getEmail());
            ps.setString(9, c.getDireccion());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al crear cliente: " + ex.getMessage());
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConexionSQLite.conectar(); 
             Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNombres(rs.getString("nombres"));
                c.setCedulaPasaporte(rs.getString("cedula_pasaporte"));
                c.setCiudad(rs.getString("ciudad"));
                c.setEmail(rs.getString("email"));
                c.setDireccion(rs.getString("direccion"));

                Dispositivo d = new Dispositivo();
                d.setMarca(rs.getString("marca_celular"));
                d.setModelo(rs.getString("modelo_celular"));
                d.setNumero(rs.getString("numero_celular"));
                d.setPago(rs.getDouble("pago_mensual"));

                c.setDispositivo(d);

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Error al listar clientes: " + ex.getMessage());
        }

        return lista;
    }

    public void actualizar(Cliente c) {
        String sql = "UPDATE clientes SET nombres=?, ciudad=?, marca_celular=?, modelo_celular=?, numero_celular=?, pago_mensual=?, email=?, direccion=? "
                + "WHERE cedula_pasaporte=?";

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getCiudad());

            Dispositivo d = c.getDispositivo();
            ps.setString(3, d.getMarca());
            ps.setString(4, d.getModelo());
            ps.setString(5, d.getNumero());
            ps.setDouble(6, d.getPago());

            ps.setString(7, c.getEmail());
            ps.setString(8, c.getDireccion());
            ps.setString(9, c.getCedulaPasaporte());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al actualizar: " + ex.getMessage());
        }
    }

    public void eliminar(String cedulaPasaporte) {
        String sql = "DELETE FROM clientes WHERE cedula_pasaporte = ?";

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedulaPasaporte);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al eliminar cliente: " + ex.getMessage());
        }
    }
}

package DAO;

import Modelo.Factura;
import Util.ConexionSQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public boolean crearFactura(Factura factura) {
        String sql = """
            INSERT INTO facturas
              (id_cliente, id_plan, total_pagar, fecha_emision)
            VALUES (?, ?, ?, ?)
            """;
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, factura.getIdCliente());
            pstmt.setInt(2, factura.getIdPlan());
            pstmt.setDouble(3, factura.getTotalPagar());
            pstmt.setString(4, factura.getFechaEmision());  

            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                return false;
            }

            try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    factura.setIdFactura(rs.getInt(1));
                }
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear factura: " + e.getMessage());
            return false;
        }
    }

    public List<Factura> obtenerFacturas() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas";
        try (Connection conn = ConexionSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String fechaText = rs.getString("fecha_emision");  
                Factura f = new Factura(
                        rs.getInt("id_cliente"),
                        rs.getInt("id_plan"),
                        rs.getDouble("total_pagar"),
                        fechaText
                );
                f.setIdFactura(rs.getInt("id_factura"));
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener facturas: " + e.getMessage());
        }
        return lista;
    }

    public List<Factura> obtenerFacturasPorCliente(int idCliente) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas WHERE id_cliente = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String fechaText = rs.getString("fecha_emision");
                    Factura f = new Factura(
                            rs.getInt("id_cliente"),
                            rs.getInt("id_plan"),
                            rs.getDouble("total_pagar"),
                            fechaText
                    );
                    f.setIdFactura(rs.getInt("id_factura"));
                    lista.add(f);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener facturas por cliente: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminarFactura(int idFactura) {
        String sql = "DELETE FROM facturas WHERE id_factura = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idFactura);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
            return false;
        }
    }
}

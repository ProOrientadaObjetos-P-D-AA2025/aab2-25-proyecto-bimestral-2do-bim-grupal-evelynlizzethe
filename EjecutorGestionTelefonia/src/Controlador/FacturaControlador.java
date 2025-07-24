package Controlador;

import Modelo.*;
import Util.ConexionSQLite;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaControlador {

    public void crear(Factura factura) {
        String sql = "INSERT INTO facturas (cedula_pasaporte, tipo_plan, id_plan, fecha_emision) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, factura.getCliente().getCedulaPasaporte());
            ps.setString(2, factura.getPlan().getClass().getSimpleName());
            ps.setInt(3, factura.getPlan().getIdPlan()); // Asume que PlanMovil tiene idPlan
            ps.setString(4, factura.getFechaEmision().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear factura: " + e.getMessage());
        }
    }

    public List<Factura> listar() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT f.id_factura, f.fecha_emision, f.tipo_plan, " +
                     "c.nombres, c.cedula_pasaporte, c.ciudad, c.email, c.direccion, " +
                     "c.marca_celular, c.modelo_celular, c.numero_celular, c.pago_mensual " +
                     "FROM facturas f " +
                     "JOIN clientes c ON f.cedula_pasaporte = c.cedula_pasaporte";

        try (Connection conn = ConexionSQLite.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombres(rs.getString("nombres"));
                cliente.setCedulaPasaporte(rs.getString("cedula_pasaporte"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDireccion(rs.getString("direccion"));

                Dispositivo d = new Dispositivo();
                d.setMarca(rs.getString("marca_celular"));
                d.setModelo(rs.getString("modelo_celular"));
                d.setNumero(rs.getString("numero_celular"));
                d.setPago(rs.getDouble("pago_mensual"));
                cliente.setDispositivo(d);

                String tipoPlan = rs.getString("tipo_plan");
                PlanMovil plan = recuperarPlan(conn, tipoPlan, rs.getInt("id_plan")); // método auxiliar

                Factura factura = new Factura(
                    rs.getInt("id_factura"),
                    cliente,
                    plan,
                    LocalDate.parse(rs.getString("fecha_emision"))
                );

                lista.add(factura);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar facturas: " + e.getMessage());
        }

        return lista;
    }

    private PlanMovil recuperarPlan(Connection conn, String tipoPlan, int idPlan) {
        PlanMovil plan = null;
        String tabla = "";

        switch (tipoPlan) {
            case "PlanPostPagoMegas": tabla = "plan_postpago_megas"; break;
            case "PlanPostPagoMinutos": tabla = "plan_postpago_minutos"; break;
            case "PlanPostPagoMinutosMegas": tabla = "plan_postpago_minutos_megas"; break;
            case "PlanPostPagoMinutosMegasEconomico": tabla = "plan_postpago_minutos_megas_eco"; break;
            default: return null;
        }

        String sql = "SELECT * FROM " + tabla + " WHERE id_plan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPlan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
            }
        } catch (SQLException e) {
            System.err.println("Error al recuperar plan: " + e.getMessage());
        }

        return plan;
    }
}

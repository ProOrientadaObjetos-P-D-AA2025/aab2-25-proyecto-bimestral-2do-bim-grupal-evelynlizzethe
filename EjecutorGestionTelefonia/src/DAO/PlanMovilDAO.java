package DAO;

import Modelo.PlanMovil;
import Modelo.PlanPostPagoMinutos;
import Modelo.PlanPostPagoMinutosMegas;
import Modelo.PlanPostPagoMinutosMegasEconomico;
import Modelo.PlanPostPagosMegas;
import Util.ConexionSQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanMovilDAO {

    public boolean crearPlan(PlanMovil plan) {
        String sql = "INSERT INTO planes (id_cliente, tipo_plan, datos_plan, pago_mensual) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, plan.getIdCliente());
            pstmt.setString(2, plan.getTipoPlan());
            pstmt.setString(3, serializarDatos(plan));
            pstmt.setDouble(4, plan.calcularPagoMensual());

            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                return false;
            }

            try (Statement st2 = conn.createStatement();
                 ResultSet rs = st2.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    plan.setIdPlan(rs.getInt(1));
                }
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear plan: " + e.getMessage());
            return false;
        }
    }

    public List<PlanMovil> obtenerPlanes() {
        List<PlanMovil> lista = new ArrayList<>();
        String sql = "SELECT * FROM planes";

        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idPlan    = rs.getInt("id_plan");
                int idCliente = rs.getInt("id_cliente");
                String tipo   = rs.getString("tipo_plan");
                String datos  = rs.getString("datos_plan");
                PlanMovil p   = deserializarPlan(idPlan, idCliente, tipo, datos);
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener planes: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminarPlan(int idPlan) {
        String sql = "DELETE FROM planes WHERE id_plan = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPlan);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar plan: " + e.getMessage());
            return false;
        }
    }

    private String serializarDatos(PlanMovil plan) {
        if (plan instanceof PlanPostPagoMinutos) {
            PlanPostPagoMinutos p = (PlanPostPagoMinutos) plan;
            return p.getMinutosNacionales() + ";" +
                   p.getCostoMinutoNacional() + ";" +
                   p.getMinutosInternacionales() + ";" +
                   p.getCostoMinutoInternacional();
        } else if (plan instanceof PlanPostPagoMinutosMegasEconomico) {
            PlanPostPagoMinutosMegasEconomico p = (PlanPostPagoMinutosMegasEconomico) plan;
            return p.getMinutos() + ";" +
                   p.getCostoMinuto() + ";" +
                   p.getGigas() + ";" +
                   p.getCostoGiga() + ";" +
                   p.getPorcentajeDescuento();
        } else if (plan instanceof PlanPostPagoMinutosMegas) {
            PlanPostPagoMinutosMegas p = (PlanPostPagoMinutosMegas) plan;
            return p.getMinutos() + ";" +
                   p.getCostoMinuto() + ";" +
                   p.getGigas() + ";" +
                   p.getCostoGiga();
        } else if (plan instanceof PlanPostPagosMegas) {
            PlanPostPagosMegas p = (PlanPostPagosMegas) plan;
            return p.getGigas() + ";" +
                   p.getCostoGiga() + ";" +
                   p.getTarifaBase();
        }
        return "";
    }

    private PlanMovil deserializarPlan(int idPlan, int idCliente, String tipo, String datos) {
        String[] p = datos.split(";");
        try {
            if ("PostPagoMinutos".equals(tipo)) {
                return new PlanPostPagoMinutos(
                    idPlan,
                    idCliente,
                    Integer.parseInt(p[0]),
                    Double.parseDouble(p[1]),
                    Integer.parseInt(p[2]),
                    Double.parseDouble(p[3])
                );
            } else if ("PostPagoMinutosMegasEconomico".equals(tipo)) {
                return new PlanPostPagoMinutosMegasEconomico(
                    idPlan,
                    idCliente,
                    Integer.parseInt(p[0]),
                    Double.parseDouble(p[1]),
                    Double.parseDouble(p[2]),
                    Double.parseDouble(p[3]),
                    Double.parseDouble(p[4])
                );
            } else if ("PostPagoMinutosMegas".equals(tipo)) {
                return new PlanPostPagoMinutosMegas(
                    idPlan,
                    idCliente,
                    Integer.parseInt(p[0]),
                    Double.parseDouble(p[1]),
                    Double.parseDouble(p[2]),
                    Double.parseDouble(p[3])
                );
            } else if ("PostPagoMegas".equals(tipo)) {
                return new PlanPostPagosMegas(
                    idPlan,
                    idCliente,
                    Double.parseDouble(p[0]),
                    Double.parseDouble(p[1]),
                    Double.parseDouble(p[2])
                );
            }
        } catch (Exception e) {
            System.out.println("Error al deserializar plan (idPlan=" + idPlan + "): " + e.getMessage());
        }
        return null;
    }
}

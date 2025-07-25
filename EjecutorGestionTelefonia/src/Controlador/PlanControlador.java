package Controlador;

import Modelo.*;
import Util.ConexionSQLite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanControlador {

    public boolean crearPlan(PlanMovil plan) {
        String sql = "INSERT INTO planes (id_cliente, tipo_plan, datos_plan, pago_mensual) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, plan.getIdCliente());
            pstmt.setString(2, plan.getTipoPlan());
            pstmt.setString(3, serializarDatos(plan));
            pstmt.setDouble(4, plan.calcularPagoMensual());

            pstmt.executeUpdate();
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
                int id = rs.getInt("id_plan");
                int idCliente = rs.getInt("id_cliente");
                String tipo = rs.getString("tipo_plan");
                String datos = rs.getString("datos_plan");

                PlanMovil plan = deserializarPlan(id, idCliente, tipo, datos);
                if (plan != null) {
                    lista.add(plan);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener planes: " + e.getMessage());
        }

        return lista;
    }

    private String serializarDatos(PlanMovil plan) {
        if (plan instanceof PlanPostPagoMinutos) {
            PlanPostPagoMinutos p = (PlanPostPagoMinutos) plan;
            return p.getMinutosNacionales() + ";" + p.getCostoMinutoNacional() + ";" +
                   p.getMinutosInternacionales() + ";" + p.getCostoMinutoInternacional();

        } else if (plan instanceof PlanPostPagoMinutosMegasEconomico) {
            PlanPostPagoMinutosMegasEconomico p = (PlanPostPagoMinutosMegasEconomico) plan;
            return p.getMinutos() + ";" + p.getCostoMinuto() + ";" + p.getGigas() + ";" +
                   p.getCostoGiga() + ";" + p.getPorcentajeDescuento();

        } else if (plan instanceof PlanPostPagoMinutosMegas) {
            PlanPostPagoMinutosMegas p = (PlanPostPagoMinutosMegas) plan;
            return p.getMinutos() + ";" + p.getCostoMinuto() + ";" +
                   p.getGigas() + ";" + p.getCostoGiga();

        } else if (plan instanceof PlanPostPagosMegas) {
            PlanPostPagosMegas p = (PlanPostPagosMegas) plan;
            return p.getGigas() + ";" + p.getCostoGiga() + ";" + p.getTarifaBase();
        }

        return "";
    }

    /**
     * Convierte los datos guardados en un objeto del tipo correspondiente.
     */
    private PlanMovil deserializarPlan(int id, int idCliente, String tipo, String datos) {
        String[] partes = datos.split(";");

        try {
            switch (tipo) {
                case "Minutos":
                    return new PlanPostPagoMinutos(idCliente,
                            Integer.parseInt(partes[0]),
                            Double.parseDouble(partes[1]),
                            Integer.parseInt(partes[2]),
                            Double.parseDouble(partes[3]));

                case "MinutosMegasEconomico":
                    return new PlanPostPagoMinutosMegasEconomico(idCliente,
                            Integer.parseInt(partes[0]),
                            Double.parseDouble(partes[1]),
                            Double.parseDouble(partes[2]),
                            Double.parseDouble(partes[3]),
                            Double.parseDouble(partes[4]));

                case "MinutosMegas":
                    return new PlanPostPagoMinutosMegas(idCliente,
                            Integer.parseInt(partes[0]),
                            Double.parseDouble(partes[1]),
                            Double.parseDouble(partes[2]),
                            Double.parseDouble(partes[3]));

                case "Megas":
                    return new PlanPostPagosMegas(idCliente,
                            Double.parseDouble(partes[0]),
                            Double.parseDouble(partes[1]),
                            Double.parseDouble(partes[2]));

                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error al deserializar plan: " + e.getMessage());
            return null;
        }
    }
}

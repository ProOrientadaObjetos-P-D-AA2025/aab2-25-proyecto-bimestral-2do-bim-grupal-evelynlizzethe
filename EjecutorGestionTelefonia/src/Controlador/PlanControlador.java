package Controlador;

import DAO.PlanMovilDAO;
import Modelo.PlanMovil;
import java.util.List;

public class PlanControlador {
    private PlanMovilDAO planDAO = new PlanMovilDAO();

    public boolean crearPlan(PlanMovil plan) {
        return planDAO.crearPlan(plan);
    }

    public List<PlanMovil> obtenerPlanes() {
        return planDAO.obtenerPlanes();
    }

    public boolean eliminarPlan(int idPlan) {
        return planDAO.eliminarPlan(idPlan);
    }
}

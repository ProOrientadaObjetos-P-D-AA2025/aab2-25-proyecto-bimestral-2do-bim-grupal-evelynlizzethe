package DAO;

import Modelo.PlanMovil;
import Modelo.PlanPostPagoMinutos;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlanMovilDAOTest {

    private PlanMovilDAO planDAO;
    private PlanMovil plan;

    @Before
    public void setUp() {
        planDAO = new PlanMovilDAO();
        plan = new PlanPostPagoMinutos(1, 100, 0.10, 50, 0.20);
    }

    @Test
    public void testCrearPlan() {
        boolean resultado = planDAO.crearPlan(plan);
        assertTrue("El plan no fue creado correctamente", resultado);
        assertNotEquals("El ID del plan no fue asignado", 0, plan.getIdPlan());
    }

    @Test
    public void testObtenerPlanes() {
        planDAO.crearPlan(plan);
        assertFalse("La lista de planes está vacía", planDAO.obtenerPlanes().isEmpty());
    }

    @Test
    public void testEliminarPlan() {
        planDAO.crearPlan(plan);
        boolean resultado = planDAO.eliminarPlan(plan.getIdPlan());
        assertTrue("El plan no fue eliminado correctamente", resultado);
    }
}

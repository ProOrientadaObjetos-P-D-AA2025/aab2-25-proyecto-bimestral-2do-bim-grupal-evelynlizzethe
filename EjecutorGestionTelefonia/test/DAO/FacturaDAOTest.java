package DAO;

import Modelo.Factura;
import Util.ConexionSQLite;
import org.junit.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class FacturaDAOTest {

    private FacturaDAO dao;

    @BeforeClass
    public static void beforeAll() throws Exception {
        ConexionSQLite.conectar();
    }

    @Before
    public void setUp() throws Exception {
        dao = new FacturaDAO();
        try (Connection conn = ConexionSQLite.conectar(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM facturas");
        }
    }

    @Test
    public void testCrearYObtenerFacturas() {
        int clienteId = 42;
        int planId = 99;
        double total = 123.45;
        String fecha = "2025-07-27";

        Factura f = new Factura(clienteId, planId, total, fecha);

        boolean creado = dao.crearFactura(f);

        assertTrue("debería retornar true al crear", creado);
        assertTrue("debe asignarse un ID >0", f.getIdFactura() > 0);

        List<Factura> todas = dao.obtenerFacturas();
        assertEquals(1, todas.size());
        Factura f2 = todas.get(0);
        assertEquals(f.getIdFactura(), f2.getIdFactura());
        assertEquals(clienteId, f2.getIdCliente());
        assertEquals(planId, f2.getIdPlan());
        assertEquals(total, f2.getTotalPagar(), 0.001);
        assertEquals(fecha, f2.getFechaEmision());
    }

    @Test
    public void testObtenerFacturasPorCliente() {
        Factura a = new Factura(1, 10, 50.0, "2025-07-27");
        Factura b = new Factura(2, 11, 60.0, "2025-07-28");
        dao.crearFactura(a);
        dao.crearFactura(b);

        List<Factura> fc1 = dao.obtenerFacturasPorCliente(1);
        List<Factura> fc2 = dao.obtenerFacturasPorCliente(2);

        assertEquals(1, fc1.size());
        assertEquals(a.getIdFactura(), fc1.get(0).getIdFactura());

        assertEquals(1, fc2.size());
        assertEquals(b.getIdFactura(), fc2.get(0).getIdFactura());
    }

    @Test
    public void testEliminarFactura() {
        Factura f = new Factura(5, 20, 75.0, "2025-07-29");
        dao.crearFactura(f);
        int id = f.getIdFactura();

        boolean borrado = dao.eliminarFactura(id);
        assertTrue("debería borrar con éxito", borrado);

        List<Factura> todas = dao.obtenerFacturas();
        assertTrue("la lista debe quedar vacía tras eliminar", todas.isEmpty());

        assertFalse("no debe borrar de nuevo", dao.eliminarFactura(id));
    }
}

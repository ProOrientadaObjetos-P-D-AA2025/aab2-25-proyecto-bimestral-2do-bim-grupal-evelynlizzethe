package DAO;

import Modelo.Cliente;
import Modelo.Dispositivo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private Cliente cliente;

    @Before
    public void setUp() {
        clienteDAO = new ClienteDAO();
        cliente = new Cliente("Juan Perez", "1234567890", "Loja", "juanperez@gmail.com", "Informatica",
                new Dispositivo("Samsung", "Galaxy S21", "0987654321"));
    }

    @Test
    public void testCrearCliente() {
        boolean resultado = clienteDAO.crearCliente(cliente);
        assertTrue("El cliente no fue creado correctamente", resultado);
        assertNotEquals("El ID del cliente no fue asignado", 0, cliente.getIdCliente());
    }

    @Test
    public void testObtenerClientePorId() {
        clienteDAO.crearCliente(cliente);
        Cliente clienteObtenido = clienteDAO.obtenerClientePorId(cliente.getIdCliente());
        assertNotNull("El cliente no fue encontrado", clienteObtenido);
        assertEquals("Los nombres no coinciden", cliente.getNombres(), clienteObtenido.getNombres());
    }

    @Test
    public void testEliminarCliente() {
        clienteDAO.crearCliente(cliente);
        boolean resultado = clienteDAO.eliminarCliente(cliente.getIdCliente());
        assertTrue("El cliente no fue eliminado correctamente", resultado);
    }
}

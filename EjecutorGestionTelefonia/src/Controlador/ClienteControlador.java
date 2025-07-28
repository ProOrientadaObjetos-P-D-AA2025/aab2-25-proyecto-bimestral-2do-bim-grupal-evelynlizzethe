package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import java.util.List;

public class ClienteControlador {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public boolean crearCliente(Cliente cliente) {
        return clienteDAO.crearCliente(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteDAO.obtenerTodosClientes();
    }

    public Cliente buscarPorId(int id) {
        return clienteDAO.obtenerClientePorId(id);
    }

    public boolean actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminarCliente(id);
    }
}

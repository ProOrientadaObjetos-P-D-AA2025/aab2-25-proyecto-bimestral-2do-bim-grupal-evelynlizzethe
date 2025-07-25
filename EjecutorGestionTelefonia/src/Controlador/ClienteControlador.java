package Controlador;

import Modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteControlador {

    private List<Cliente> clientes;

    public ClienteControlador() {
        clientes = new ArrayList<>();
    }


    public boolean crearCliente(Cliente c) {
        return clientes.add(c);
    }

    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getIdCliente() == id) {
                return c;
            }
        }
        return null;
    }


    public boolean actualizarCliente(Cliente clienteActualizado) {
        Cliente c = buscarPorId(clienteActualizado.getIdCliente());
        if (c != null) {
            c.setNombres(clienteActualizado.getNombres());
            c.setCedulaPasaporte(clienteActualizado.getCedulaPasaporte());
            c.setCiudad(clienteActualizado.getCiudad());
            c.setCorreo(clienteActualizado.getCorreo());
            c.setCarrera(clienteActualizado.getCarrera());
            c.setDispositivo(clienteActualizado.getDispositivo());
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(int id) {
        Cliente c = buscarPorId(id);
        if (c != null) {
            return clientes.remove(c);
        }
        return false;
    }
}

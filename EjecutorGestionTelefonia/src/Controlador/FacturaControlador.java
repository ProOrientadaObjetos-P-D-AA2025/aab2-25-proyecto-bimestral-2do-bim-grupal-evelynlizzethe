package Controlador;

import Modelo.Cliente;
import Modelo.Factura;
import Modelo.PlanMovil;

import java.util.ArrayList;
import java.util.List;

public class FacturaControlador {

    private List<Factura> facturas;

    public FacturaControlador() {
        facturas = new ArrayList<>();
    }

    public Factura generarFactura(Cliente cliente, PlanMovil plan) {
        Factura factura = new Factura(cliente, plan);
        facturas.add(factura);
        return factura;
    }

    public List<Factura> obtenerFacturas() {
        return facturas;
    }

    public List<Factura> buscarFacturasPorCliente(int idCliente) {
        List<Factura> resultado = new ArrayList<>();
        for (Factura f : facturas) {
            if (f.getIdCliente() == idCliente) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    public boolean eliminarFactura(int idFactura) {
        Factura facturaEliminar = null;
        for (Factura f : facturas) {
            if (f.getIdFactura() == idFactura) {
                facturaEliminar = f;
                break;
            }
        }
        if (facturaEliminar != null) {
            return facturas.remove(facturaEliminar);
        }
        return false;
    }
}

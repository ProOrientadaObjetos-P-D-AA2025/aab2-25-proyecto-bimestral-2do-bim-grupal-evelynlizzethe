package Controlador;

import DAO.FacturaDAO;
import Modelo.Factura;
import java.util.List;

public class FacturaControlador {

    private FacturaDAO dao = new FacturaDAO();

    public boolean crearFactura(Factura f) {
        return dao.crearFactura(f);
    }

    public List<Factura> obtenerFacturas() {
        return dao.obtenerFacturas();
    }

    public boolean eliminarFactura(int id) {
        return dao.eliminarFactura(id);
    }
}

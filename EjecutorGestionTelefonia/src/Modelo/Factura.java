package Modelo;

public class Factura {

    private int idFactura;
    private int idCliente;
    private int idPlan;
    private double totalPagar;
    private String fechaEmision;

    public Factura(int idCliente, int idPlan, double totalPagar, String fechaEmision) {
        this.idCliente = idCliente;
        this.idPlan = idPlan;
        this.totalPagar = totalPagar;
        this.fechaEmision = fechaEmision;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }  
}

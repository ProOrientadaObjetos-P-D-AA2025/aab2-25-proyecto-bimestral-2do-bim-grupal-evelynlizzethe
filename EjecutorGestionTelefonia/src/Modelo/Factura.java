package Modelo;

public class Factura {
    private static int contador = 1; // Contador para asignar IDs únicos

    private int idFactura;
    private int idCliente;
    private int idPlan;
    private double totalPagar;
    private String fechaEmision;

    public Factura(int idCliente, int idPlan, double totalPagar, String fechaEmision) {
        this.idFactura = contador++;
        this.idCliente = idCliente;
        this.idPlan = idPlan;
        this.totalPagar = totalPagar;
        this.fechaEmision = fechaEmision;
    }

    public Factura(Cliente cliente, PlanMovil plan) {
        this.idFactura = contador++;
        this.idCliente = cliente.getIdCliente();
        this.idPlan = plan.getIdPlan();
        this.totalPagar = plan.calcularPagoMensual();
        this.fechaEmision = java.time.LocalDate.now().toString();
    }

    public int getIdFactura() {
        return idFactura;
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

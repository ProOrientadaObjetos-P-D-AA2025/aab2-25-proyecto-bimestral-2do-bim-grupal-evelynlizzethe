package Modelo;
   
import java.time.LocalDate;

public class Factura {
    private int idFactura;
    private Cliente cliente;
    private PlanMovil plan;
    private LocalDate fechaEmision;

    public Factura(int idFactura, Cliente cliente, PlanMovil plan, LocalDate fechaEmision) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.plan = plan;
        this.fechaEmision = fechaEmision;
    }

    public double calcularValorTotal() {
        return plan.calcularPagoMensual();
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlanMovil getPlan() {
        return plan;
    }

    public void setPlan(PlanMovil plan) {
        this.plan = plan;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
}


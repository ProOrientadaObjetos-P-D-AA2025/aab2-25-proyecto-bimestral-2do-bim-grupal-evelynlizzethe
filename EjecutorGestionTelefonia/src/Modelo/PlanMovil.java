package Modelo;

public abstract class PlanMovil {

    private int idPlan;
    private int idCliente;

    public PlanMovil(int idPlan, int idCliente) {
        this.idPlan = idPlan;
        this.idCliente = idCliente;
    }

    public PlanMovil(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public abstract double calcularPagoMensual();

    public abstract String getTipoPlan();
}

package Modelo;

public abstract class PlanMovil {
    private static int contador = 1;

    private int idPlan;
    private int idCliente;

    public PlanMovil(int idCliente) {
        this.idPlan = contador++;
        this.idCliente = idCliente;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public abstract double calcularPagoMensual();

    public abstract String getTipoPlan();
}


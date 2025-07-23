package Modelo;

public class PlanPostPagosMegas extends PlanMovil {
    private double megasGigas;
    private double costoPorGiga;
    private double tarifaBase;

    public PlanPostPagosMegas(int idPlan, String nombre, double megas, double costoGiga, double tarifaBase) {
        super(idPlan, nombre, "Megas");
        this.megasGigas = megas;
        this.costoPorGiga = costoGiga;
        this.tarifaBase = tarifaBase;
    }

    public double calcularPagoMensual() {
        return tarifaBase + (megasGigas * costoPorGiga);
    }
}

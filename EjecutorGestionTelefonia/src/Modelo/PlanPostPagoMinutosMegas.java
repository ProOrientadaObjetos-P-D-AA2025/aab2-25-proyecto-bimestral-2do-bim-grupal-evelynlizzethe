package Modelo;

public class PlanPostPagoMinutosMegas extends PlanMovil {
    private int minutos;
    private double costoMinuto;
    private double megasGigas;
    private double costoGiga;

    public PlanPostPagoMinutosMegas(int idPlan, String nombre, int minutos, double costoMinuto,
                                     double megas, double costoGiga) {
        super(idPlan, nombre, "MinutosMegas");
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.megasGigas = megas;
        this.costoGiga = costoGiga;
    }

    public double calcularPagoMensual() {
        return (minutos * costoMinuto) + (megasGigas * costoGiga);
    }
}


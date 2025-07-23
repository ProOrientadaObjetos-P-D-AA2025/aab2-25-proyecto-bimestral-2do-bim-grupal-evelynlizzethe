package Modelo;

public class PlanPostPagoMinutosMegasEconomico extends PlanMovil {
    private int minutos;
    private double costoMinuto;
    private double megasGigas;
    private double costoGiga;
    private double porcentajeDescuento; // Ej: 0.1 para 10%

    public PlanPostPagoMinutosMegasEconomico(int idPlan, String nombre, int minutos, double costoMinuto,
                                             double megas, double costoGiga, double porcentajeDescuento) {
        super(idPlan, nombre, "MinutosMegasEco");
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.megasGigas = megas;
        this.costoGiga = costoGiga;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double calcularPagoMensual() {
        double total = (minutos * costoMinuto) + (megasGigas * costoGiga);
        return total - (total * porcentajeDescuento);
    }
}


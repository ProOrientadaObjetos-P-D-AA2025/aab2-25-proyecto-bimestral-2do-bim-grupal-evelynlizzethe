package Modelo;

public class PlanPostPagoMinutosMegasEconomico extends PlanMovil {

    private int minutos;
    private double costoMinuto;
    private double gigas;
    private double costoGiga;
    private double porcentajeDescuento;

    public PlanPostPagoMinutosMegasEconomico(int idPlan, int idCliente,
            int minutos, double costoMinuto,
            double gigas, double costoGiga,
            double porcentajeDescuento) {
        super(idPlan, idCliente);
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.gigas = gigas;
        this.costoGiga = costoGiga;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public PlanPostPagoMinutosMegasEconomico(int idCliente,
            int minutos, double costoMinuto,
            double gigas, double costoGiga,
            double porcentajeDescuento) {
        super(idCliente);
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.gigas = gigas;
        this.costoGiga = costoGiga;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularPagoMensual() {
        double subtotal = minutos * costoMinuto + gigas * costoGiga;
        return subtotal - subtotal * porcentajeDescuento / 100.0;
    }

    @Override
    public String getTipoPlan() {
        return "PostPagoMinutosMegasEconomico";
    }

    public int getMinutos() {
        return minutos;
    }

    public double getCostoMinuto() {
        return costoMinuto;
    }

    public double getGigas() {
        return gigas;
    }

    public double getCostoGiga() {
        return costoGiga;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}

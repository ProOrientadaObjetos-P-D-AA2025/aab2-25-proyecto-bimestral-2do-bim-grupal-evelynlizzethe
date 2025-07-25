package Modelo;

public class PlanPostPagoMinutosMegas extends PlanMovil {
    private int minutos;
    private double costoMinuto;
    private double gigas;
    private double costoGiga;

    public PlanPostPagoMinutosMegas(int idCliente, int minutos, double costoMinuto,
                                   double gigas, double costoGiga) {
        super(idCliente);
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.gigas = gigas;
        this.costoGiga = costoGiga;
    }

    @Override
    public double calcularPagoMensual() {
        return (minutos * costoMinuto) + (gigas * costoGiga);
    }

    @Override
    public String getTipoPlan() {
        return "PostPagoMinutosMegas";
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
}

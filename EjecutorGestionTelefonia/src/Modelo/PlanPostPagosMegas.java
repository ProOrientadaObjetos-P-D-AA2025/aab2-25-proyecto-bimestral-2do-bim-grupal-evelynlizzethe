package Modelo;

public class PlanPostPagosMegas extends PlanMovil {

    private double gigas;
    private double costoGiga;
    private double tarifaBase;

    public PlanPostPagosMegas(int idPlan, int idCliente,
            double gigas, double costoGiga,
            double tarifaBase) {
        super(idPlan, idCliente);
        this.gigas = gigas;
        this.costoGiga = costoGiga;
        this.tarifaBase = tarifaBase;
    }

    public PlanPostPagosMegas(int idCliente,
            double gigas, double costoGiga,
            double tarifaBase) {
        super(idCliente);
        this.gigas = gigas;
        this.costoGiga = costoGiga;
        this.tarifaBase = tarifaBase;
    }

    @Override
    public double calcularPagoMensual() {
        return tarifaBase + gigas * costoGiga;
    }

    @Override
    public String getTipoPlan() {
        return "PostPagoMegas";
    }

    public double getGigas() {
        return gigas;
    }

    public double getCostoGiga() {
        return costoGiga;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }
}

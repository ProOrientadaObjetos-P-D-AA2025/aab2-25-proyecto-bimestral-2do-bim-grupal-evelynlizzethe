package Modelo;

public class PlanPostPagoMinutos extends PlanMovil {
    private int minutosNacionales;
    private double costoMinutoNacional;
    private int minutosInternacionales;
    private double costoMinutoInternacional;

    public PlanPostPagoMinutos(int idCliente, int minutosNacionales, double costoMinutoNacional,
                              int minutosInternacionales, double costoMinutoInternacional) {
        super(idCliente);
        this.minutosNacionales = minutosNacionales;
        this.costoMinutoNacional = costoMinutoNacional;
        this.minutosInternacionales = minutosInternacionales;
        this.costoMinutoInternacional = costoMinutoInternacional;
    }

    @Override
    public double calcularPagoMensual() {
        return (minutosNacionales * costoMinutoNacional) +
               (minutosInternacionales * costoMinutoInternacional);
    }

    @Override
    public String getTipoPlan() {
        return "PostPagoMinutos";
    }

    public int getMinutosNacionales() {
        return minutosNacionales;
    }

    public double getCostoMinutoNacional() {
        return costoMinutoNacional;
    }

    public int getMinutosInternacionales() {
        return minutosInternacionales;
    }

    public double getCostoMinutoInternacional() {
        return costoMinutoInternacional;
    }
}




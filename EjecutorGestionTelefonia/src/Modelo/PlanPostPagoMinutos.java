package Modelo;

public class PlanPostPagoMinutos extends PlanMovil{
    
    private int minutosNacionales;
    private double costoMinutoNacional;
    private int minutosInternacionales;
    private double costoMinutoInternacional;

     public PlanPostPagoMinutos(int idPlan, String nombre, int minutosNac, double costoNac,
                                int minutosInt, double costoInt) {
        super(idPlan, nombre, "Minutos");
        this.minutosNacionales = minutosNac;
        this.costoMinutoNacional = costoNac;
        this.minutosInternacionales = minutosInt;
        this.costoMinutoInternacional = costoInt;
    }
  
    public double calcularPagoMensual() {
        return (minutosNacionales * costoMinutoNacional) +
               (minutosInternacionales * costoMinutoInternacional);
    }
    
}

package Modelo;

public class PlanMovil {
    
    protected int idPlan;
    protected String nombre;
    protected String tipoPlan;

    public PlanMovil(int idPlan, String nombre, String tipoPlan) {
        this.idPlan = idPlan;
        this.nombre = nombre;
        this.tipoPlan = tipoPlan;
    }
    
    public abstract double calcularPagoMensual();

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }
    

 
}



package Modelo;

public class Beca {
    
    private String cedula;
    private String nombres;
    private String apellidos;
    private String carrera;
    private String tipoBeca;
    private int semestre;
    private double montoMensual;
    private Double ingresoFamiliar;    
    private Double promedioAcademico;

    public Beca(String cedula, String nombres, String apellidos, String carrera,
            String tipoBeca, int semestre, double montoMensual, Double ingresoFamiliar, Double promedioAcademico) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.carrera = carrera;
        this.tipoBeca = tipoBeca;
        this.semestre = semestre;
        this.montoMensual = montoMensual;
        this.ingresoFamiliar = ingresoFamiliar;
        this.promedioAcademico = promedioAcademico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(double montoMensual) {
        this.montoMensual = montoMensual;
    }

    public Double getIngresoFamiliar() {
        return ingresoFamiliar;
    }

    public void setIngresoFamiliar(Double ingresoFamiliar) {
        this.ingresoFamiliar = ingresoFamiliar;
    }

    public Double getPromedioAcademico() {
        return promedioAcademico;
    }

    public void setPromedioAcademico(Double promedioAcademico) {
        this.promedioAcademico = promedioAcademico;
    }
    
}

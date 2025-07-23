package Modelo;

public class Cliente {
    
    private int idCliente;
    private String nombres;
    private String cedulaPasaporte;
    private String ciudad;
    private String marcaCelular;
    private String modeloCelular;
    private String numeroCelular;
    private double pagoMensual;
    private String email;
    private int edad;

    public Cliente(int idCliente, String nombres, String cedulaPasaporte, String ciudad, String marcaCelular, String modeloCelular, String numeroCelular, double pagoMensual, String email, int edad) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.cedulaPasaporte = cedulaPasaporte;
        this.ciudad = ciudad;
        this.marcaCelular = marcaCelular;
        this.modeloCelular = modeloCelular;
        this.numeroCelular = numeroCelular;
        this.pagoMensual = pagoMensual;
        this.email = email;
        this.edad = edad;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCedulaPasaporte() {
        return cedulaPasaporte;
    }

    public void setCedulaPasaporte(String cedulaPasaporte) {
        this.cedulaPasaporte = cedulaPasaporte;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMarcaCelular() {
        return marcaCelular;
    }

    public void setMarcaCelular(String marcaCelular) {
        this.marcaCelular = marcaCelular;
    }

    public String getModeloCelular() {
        return modeloCelular;
    }

    public void setModeloCelular(String modeloCelular) {
        this.modeloCelular = modeloCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
}

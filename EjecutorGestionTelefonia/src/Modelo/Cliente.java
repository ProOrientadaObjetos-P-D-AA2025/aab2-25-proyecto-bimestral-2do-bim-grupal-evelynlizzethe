package Modelo;

public class Cliente {
    private static int contador = 1;

    private int idCliente;
    private String nombres;
    private String cedulaPasaporte;
    private String ciudad;
    private String correo;        
    private String carrera;       
    private Dispositivo dispositivo;

    public Cliente(String nombres, String cedulaPasaporte, String ciudad,
                   String correo, String carrera, Dispositivo dispositivo) {
        this.idCliente = contador++;
        this.nombres = nombres;
        this.cedulaPasaporte = cedulaPasaporte;
        this.ciudad = ciudad;
        this.correo = correo;
        this.carrera = carrera;
        this.dispositivo = dispositivo;
    }

    public Cliente(String nombres, String cedulaPasaporte, String ciudad,
                   String marca, String modelo, String numero) {
        this.idCliente = contador++;
        this.nombres = nombres;
        this.cedulaPasaporte = cedulaPasaporte;
        this.ciudad = ciudad;
        this.correo = ""; 
        this.carrera = ""; 
        this.dispositivo = new Dispositivo(marca, modelo, numero);
    }

    public Cliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public String getCedulaPasaporte() {
        return cedulaPasaporte;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setCedulaPasaporte(String cedulaPasaporte) {
        this.cedulaPasaporte = cedulaPasaporte;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
}


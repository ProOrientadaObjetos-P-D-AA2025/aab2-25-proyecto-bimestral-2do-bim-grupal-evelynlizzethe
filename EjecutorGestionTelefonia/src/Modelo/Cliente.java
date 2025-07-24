package Modelo;

public class Cliente {
    private String nombres;
    private String cedulaPasaporte;
    private String ciudad;
    private String email;
    private String direccion;
    private Dispositivo dispositivo;

    public Cliente(String nombres, String cedulaPasaporte, String ciudad, String email, String direccion, Dispositivo dispositivo) {
        this.nombres = nombres;
        this.cedulaPasaporte = cedulaPasaporte;
        this.ciudad = ciudad;
        this.email = email;
        this.direccion = direccion;
        this.dispositivo = dispositivo;
    }

    public Cliente() {
        
    }

    // Getters y setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
}

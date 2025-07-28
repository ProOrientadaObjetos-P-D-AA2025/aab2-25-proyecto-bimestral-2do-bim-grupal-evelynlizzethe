package Modelo;

public class Cliente {

    private int idCliente;
    private String nombres;
    private String cedulaPasaporte;
    private String ciudad;
    private String correo;
    private String carrera;
    private Dispositivo dispositivo;

    // Constructor principal (lectura desde BD)
    public Cliente(int idCliente,
            String nombres,
            String cedulaPasaporte,
            String ciudad,
            String correo,
            String carrera,
            String marca,
            String modelo,
            String numero) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.cedulaPasaporte = cedulaPasaporte;
        this.ciudad = ciudad;
        this.correo = correo;
        this.carrera = carrera;
        this.dispositivo = new Dispositivo(marca, modelo, numero);
    }

    // Para crear antes de persistir (id=0)
    public Cliente(String nombres,
            String cedulaPasaporte,
            String ciudad,
            String correo,
            String carrera,
            String marca,
            String modelo,
            String numero) {
        this(0, nombres, cedulaPasaporte, ciudad, correo, carrera, marca, modelo, numero);
    }

    // Este constructor acepta directamente un Dispositivo (puede ser null)
    public Cliente(int idCliente,
            String nombres,
            String cedulaPasaporte,
            String ciudad,
            String correo,
            String carrera,
            Dispositivo dispositivo) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.cedulaPasaporte = cedulaPasaporte;
        this.ciudad = ciudad;
        this.correo = correo;
        this.carrera = carrera;
        this.dispositivo = dispositivo;  // Asignación directa, sin llamar a getters
    }

    public Cliente(String nombres,
            String cedulaPasaporte,
            String ciudad,
            String correo,
            String carrera,
            Dispositivo dispositivo) {
        this(0, nombres, cedulaPasaporte, ciudad, correo, carrera, dispositivo);
    }

    public void setIdCliente(int id) {
        this.idCliente = id;
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

    public void setCedulaPasaporte(String cedula) {
        this.cedulaPasaporte = cedula;
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

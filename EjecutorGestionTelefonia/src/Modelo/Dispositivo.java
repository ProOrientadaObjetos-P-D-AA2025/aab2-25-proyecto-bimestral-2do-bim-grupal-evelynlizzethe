package Modelo;

public class Dispositivo {
    private String marca;
    private String modelo;
    private String numero;

    public Dispositivo(String marca, String modelo, String numero) {
        this.marca = marca;
        this.modelo = modelo;
        this.numero = numero;
    }

    public Dispositivo() {
        
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}

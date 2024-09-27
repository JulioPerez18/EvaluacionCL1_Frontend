package pe.edu.cibertec.evaluacionT1Frontend.clases;

public class Vehiculo {
    private String marca;
    private String modelo;
    private int nroAsientos;
    private double precio;
    private String color;

    public Vehiculo(String marca, String modelo, int nroAsientos, double precio, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.nroAsientos = nroAsientos;
        this.precio = precio;
        this.color = color;
    }

    public Vehiculo() {
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getNroAsientos() {
        return nroAsientos;
    }

    public double getPrecio() {
        return precio;
    }

    public String getColor() {
        return color;
    }
}

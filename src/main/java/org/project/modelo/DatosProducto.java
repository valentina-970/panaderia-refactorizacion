package org.project.modelo;

public class DatosProducto {
    private String nombre;
    private double precio;
    private int cantidad;
    private TipoProducto tipo;

    public DatosProducto(String nombre, double precio, int cantidad, TipoProducto tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public double getSubtotal() {
        return precio * cantidad;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public TipoProducto getTipo() { return tipo; }
}

package org.project.modelo;

// Code Smell #10: Herencia rechazada - sobreescribe todo lo del padre
// Code Smell #11: Clases alternativas con interfaces distintas - interfaz diferente a Pan
public class Pastel extends Producto {

    private String relleno;
    private int porciones;
    private boolean tieneDecoracion;
    private String colorDecoracion;

    public Pastel(String nombre, double precio, int cantidad) {
        super(nombre, precio, cantidad);
        this.relleno = "crema";
        this.porciones = 8;
    }

    @Override
    public String getCategoria() {
        return "Pastel";
    }

    // Implementa descuento de forma completamente diferente a Pan
    @Override
    public double calcularDescuento() {
        if (porciones >= 12) {
            return precio * 0.20;
        } else if (porciones >= 8) {
            return precio * 0.10;
        }
        return 0;
    }

    // Code Smell #11: Métodos propios que Pan no tiene
    public void decorar() {
        if (tieneDecoracion) {
            System.out.println("Decorando " + nombre + " con color " + colorDecoracion);
        }
    }

    public void cortarPorciones() {
        System.out.println("Cortando " + nombre + " en " + porciones + " porciones");
    }

    public void agregarRelleno() {
        System.out.println("Agregando relleno de " + relleno);
    }

    // Code Smell #10: Sobreescribe campos del padre con getters/setters propios
    public String getRelleno() { return relleno; }
    public void setRelleno(String relleno) { this.relleno = relleno; }
    public int getPorciones() { return porciones; }
    public void setPorciones(int porciones) { this.porciones = porciones; }
    public boolean isTieneDecoracion() { return tieneDecoracion; }
    public void setTieneDecoracion(boolean tieneDecoracion) { this.tieneDecoracion = tieneDecoracion; }
    public String getColorDecoracion() { return colorDecoracion; }
    public void setColorDecoracion(String colorDecoracion) { this.colorDecoracion = colorDecoracion; }
}

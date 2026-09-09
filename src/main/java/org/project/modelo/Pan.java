package org.project.modelo;

// Code Smell #10: Herencia rechazada - sobreescribe todo lo del padre
// Code Smell #11: Clases alternativas con interfaces distintas - interfaz diferente a Pastel
public class Pan extends Producto {

    private String tipoPan;
    private double peso;
    private boolean tieneMasaMadre;
    private int tiempoHorneado;

    public Pan(String nombre, double precio, int cantidad) {
        super(nombre, precio, cantidad);
        this.tipoPan = "tradicional";
    }

    // Sobreescribe el método del padre pero hace algo diferente
    @Override
    public String getCategoria() {
        return "Pan";
    }

    // Implementa descuento de forma completamente diferente a Pastel
    @Override
    public double calcularDescuento() {
        if (cantidad > 10) {
            return precio * 0.15;
        }
        return 0;
    }

    // Code Smell #11: Métodos propios que Pastel no tiene
    public void horneado() {
        System.out.println("Horneando " + nombre + " por " + tiempoHorneado + " minutos");
    }

    public void verificarMasa() {
        if (tieneMasaMadre) {
            System.out.println("Verificando masa madre...");
        }
    }

    public void cortarPan() {
        System.out.println("Cortando " + nombre);
    }

    // Code Smell #10: Sobreescribe campos del padre con getters/setters propios
    public String getTipoPan() { return tipoPan; }
    public void setTipoPan(String tipoPan) { this.tipoPan = tipoPan; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public boolean isTieneMasaMadre() { return tieneMasaMadre; }
    public void setTieneMasaMadre(boolean tieneMasaMadre) { this.tieneMasaMadre = tieneMasaMadre; }
    public int getTiempoHorneado() { return tiempoHorneado; }
    public void setTiempoHorneado(int tiempoHorneado) { this.tiempoHorneado = tiempoHorneado; }
}

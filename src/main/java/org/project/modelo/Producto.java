package org.project.modelo;

public abstract class Producto {

    // Code Smell #12: Generalidad especulativa - campos que nunca se usan
    protected String nombre;
    protected double precio;
    protected int cantidad;
    protected TipoProducto tipo;
    protected String sabor;
    protected String tamano;
    protected boolean esPremium;
    protected String codigoBarras;
    protected String fechaProduccion;
    protected String fechaVencimiento;
    protected int calorias;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Code Smell #12: Generalidad especulativa - método abstracto que nunca se usa en subclases
    public abstract String getCategoria();

    public abstract double calcularDescuento();

    // Code Smell #12: Generalidad especulativa - métodos que nadie llama
    public void verificarCalidad() {
        System.out.println("Verificando calidad de " + nombre);
    }

    public void generarEtiqueta() {
        System.out.println("Etiqueta: " + nombre + " - $" + precio);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public TipoProducto getTipo() { return tipo; }
    public void setTipo(TipoProducto tipo) { this.tipo = tipo; }
    public String getSabor() { return sabor; }
    public void setSabor(String sabor) { this.sabor = sabor; }
    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }
    public boolean isEsPremium() { return esPremium; }
    public void setEsPremium(boolean esPremium) { this.esPremium = esPremium; }
}

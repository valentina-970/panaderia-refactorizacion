package org.project.modelo;

import org.project.servicio.PanaderiaService;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int idPedido;
    private Cliente cliente;
    private List<Producto> productos;
    private String estado;
    private String fechaPedido;

    // Code Smell #7: Obsesión por primitivos - usando String para todo
    private String metodoPago;
    private String direccionEntrega;
    private String observaciones;

    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.estado = "pendiente";
    }

    // Code Smell #5: Sentencias switch - para calcular precio según tipo
    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            // Code Smell #8: Data Clumps - grupo repetido de parámetros
            total += calcularPrecioProducto(producto.getNombre(), producto.getPrecio(), producto.getCantidad(), producto.getTipo());
        }
        return total;
    }

    // Code Smell #8: Data Clumps - mismo grupo de parámetros que en otros métodos
    private double calcularPrecioProducto(String nombre, double precio, int cantidad, TipoProducto tipo) {
        double subtotal = precio * cantidad;
        // Code Smell #5: Sentencias switch
        switch (tipo) {
            case PAN:
                if (cantidad > 10) {
                    subtotal = subtotal * 0.85;
                }
                break;
            case PASTEL:
                if (cantidad > 3) {
                    subtotal = subtotal * 0.80;
                }
                break;
            case GALLETA:
                if (cantidad > 20) {
                    subtotal = subtotal * 0.90;
                }
                break;
            case POSTRE:
                if (cantidad > 5) {
                    subtotal = subtotal * 0.88;
                }
                break;
            default:
                break;
        }
        return subtotal;
    }

    // Code Smell #3: Feature Envy - accede mucho a PanaderiaService
    public boolean verificarDisponibilidad(PanaderiaService service) {
        for (Producto producto : productos) {
            // Envia a acceder a datos de PanaderiaService
            int stock = service.obtenerStock(producto.getNombre());
            if (stock < producto.getCantidad()) {
                return false;
            }
        }
        return true;
    }

    // Code Smell #15: Cirugía de escopeta - si se agrega un tipo de producto,
    // hay que cambiar este método y otros lugares
    public void aplicarDescuentos() {
        for (Producto producto : productos) {
            if (producto.getTipo() == TipoProducto.PAN) {
                if (producto.getCantidad() > 10) {
                    producto.setPrecio(producto.getPrecio() * 0.85);
                }
            } else if (producto.getTipo() == TipoProducto.PASTEL) {
                if (producto.getCantidad() > 3) {
                    producto.setPrecio(producto.getPrecio() * 0.80);
                }
            } else if (producto.getTipo() == TipoProducto.GALLETA) {
                if (producto.getCantidad() > 20) {
                    producto.setPrecio(producto.getPrecio() * 0.90);
                }
            }
        }
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void setEstado(String estado) { this.estado = estado; }
    public int getIdPedido() { return idPedido; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public String getEstado() { return estado; }
    public String getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(String fechaPedido) { this.fechaPedido = fechaPedido; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

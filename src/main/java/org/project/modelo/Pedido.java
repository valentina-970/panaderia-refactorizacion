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
    private String metodoPago;
    private String direccionEntrega;
    private String observaciones;

    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.estado = "pendiente";
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += calcularPrecioProducto(new DatosProducto(
                producto.getNombre(), producto.getPrecio(),
                producto.getCantidad(), producto.getTipo()));
        }
        return total;
    }

    private double calcularPrecioProducto(DatosProducto datos) {
        double subtotal = datos.getSubtotal();
        switch (datos.getTipo()) {
            case PAN:
                if (datos.getCantidad() > 10) {
                    subtotal *= 0.85;
                }
                break;
            case PASTEL:
                if (datos.getCantidad() > 3) {
                    subtotal *= 0.80;
                }
                break;
            case GALLETA:
                if (datos.getCantidad() > 20) {
                    subtotal *= 0.90;
                }
                break;
            case POSTRE:
                if (datos.getCantidad() > 5) {
                    subtotal *= 0.88;
                }
                break;
            default:
                break;
        }
        return subtotal;
    }

    public boolean verificarDisponibilidad(PanaderiaService service) {
        for (Producto producto : productos) {
            int stock = service.obtenerStock(producto.getNombre());
            if (stock < producto.getCantidad()) {
                return false;
            }
        }
        return true;
    }

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

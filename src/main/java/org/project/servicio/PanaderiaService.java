package org.project.servicio;

import org.project.modelo.*;

import java.util.ArrayList;
import java.util.List;

// Code Smell #2: Clase grande (God Class) - hace todo
// Code Smell #14: Cambio divergente - cambia por inventario, pedidos, clientes, facturación
public class PanaderiaService {

    private InventarioService inventario;
    private List<Cliente> clientes;
    private List<Pedido> pedidos;
    private List<Empleado> empleados;

    public PanaderiaService() {
        this.inventario = new InventarioService();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.empleados = new ArrayList<>();
    }

    // Code Smell #1: Método largo - 100+ líneas haciendo todo
    public void procesarPedido(int idPedido, String nombreCliente, String cedulaCliente,
                               String telefonoCliente, String nombreProducto, double precio,
                               int cantidad, String tipo, String metodoPago,
                               String direccionEntrega, String observaciones) {
        System.out.println("=== PROCESANDO PEDIDO ===");

        // Validar cliente
        if (nombreCliente == null || nombreCliente.isEmpty()) {
            System.out.println("Error: nombre de cliente inválido");
            return;
        }
        if (cedulaCliente == null || cedulaCliente.isEmpty()) {
            System.out.println("Error: cédula de cliente inválida");
            return;
        }
        if (telefonoCliente == null || telefonoCliente.isEmpty()) {
            System.out.println("Error: teléfono de cliente inválido");
            return;
        }

        // Validar producto
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            System.out.println("Error: nombre de producto inválido");
            return;
        }
        if (precio <= 0) {
            System.out.println("Error: precio inválido");
            return;
        }
        if (cantidad <= 0) {
            System.out.println("Error: cantidad inválida");
            return;
        }

        // Code Smell #4: Código duplicado - misma validación que en InventarioService
        Integer stock = inventario.getStock(nombreProducto);
        if (stock == null) {
            System.out.println("Error: producto no encontrado en inventario");
            return;
        }
        if (stock < cantidad) {
            System.out.println("Stock insuficiente para " + nombreProducto);
            return;
        }

        // Crear cliente
        Cliente cliente = new Cliente(nombreCliente, cedulaCliente, telefonoCliente);
        cliente.setDireccion(direccionEntrega);
        clientes.add(cliente);

        // Crear producto
        Producto producto = null;
        TipoProducto tipoProducto = TipoProducto.fromCodigo(tipo);
        if (tipoProducto == TipoProducto.PAN) {
            Pan pan = new Pan(nombreProducto, precio, cantidad);
            pan.horneado();
            producto = pan;
        } else if (tipoProducto == TipoProducto.PASTEL) {
            Pastel pastel = new Pastel(nombreProducto, precio, cantidad);
            pastel.decorar();
            producto = pastel;
        }

        // Crear pedido
        Pedido pedido = new Pedido(idPedido, cliente);
        pedido.setFechaPedido("2026-01-19");
        pedido.setMetodoPago(metodoPago);
        pedido.setDireccionEntrega(direccionEntrega);
        pedido.setObservaciones(observaciones);
        pedido.agregarProducto(producto);

        // Code Smell #6: Condicionales complejos
        if (pedido.verificarDisponibilidad(this)) {
            if (metodoPago.equals("efectivo") || metodoPago.equals("tarjeta") || metodoPago.equals("nequi") || metodoPago.equals("daviplata")) {
                if (direccionEntrega != null && !direccionEntrega.isEmpty()) {
                    pedido.setEstado("confirmado");
                    inventario.actualizarStock(nombreProducto, stock - cantidad);
                    pedidos.add(pedido);
                    System.out.println("Pedido #" + idPedido + " procesado exitosamente");
                    System.out.println("Total: $" + pedido.calcularTotal());
                } else {
                    System.out.println("Error: dirección de entrega requerida");
                }
            } else {
                System.out.println("Error: método de pago no válido");
            }
        } else {
            System.out.println("Error: stock insuficiente");
        }

        System.out.println("=== FIN PROCESAMIENTO ===");
    }

    public int obtenerStock(String nombreProducto) {
        return inventario.getStock(nombreProducto);
    }

    // Code Smell #14: Cambio divergente - métodos para diferentes responsabilidades
    // ===== RESPONSABILIDAD: Clientes =====
    public void registrarCliente(String nombre, String cedula, String telefono) {
        Cliente cliente = new Cliente(nombre, cedula, telefono);
        clientes.add(cliente);
        System.out.println("Cliente registrado: " + nombre);
    }

    public Cliente buscarCliente(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    // ===== RESPONSABILIDAD: Empleados =====
    // Code Smell #9: Lista de parámetros larga
    public void registrarEmpleado(String nombre, String cedula, String cargo,
                                  String turno, double salario, String fechaIngreso,
                                  String numeroContrato) {
        Empleado empleado = new Empleado(nombre, cedula, cargo, turno, salario);
        empleados.add(empleado);
        System.out.println("Empleado registrado: " + nombre);
    }

    // ===== RESPONSABILIDAD: Inventario =====
    public void agregarProductoInventario(String nombre, int stock, double precio) {
        inventario.agregarProducto(nombre, stock, precio);
    }

    // ===== RESPONSABILIDAD: Facturación =====
    public void facturarPedido(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                System.out.println("Factura generada para pedido #" + idPedido);
                System.out.println("Total: $" + pedido.calcularTotal());
                return;
            }
        }
        System.out.println("Pedido no encontrado");
    }

    public List<Pedido> getPedidos() { return pedidos; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Empleado> getEmpleados() { return empleados; }
}

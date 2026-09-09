package org.project.servicio;

import java.util.HashMap;
import java.util.Map;

public class InventarioService {

    private Map<String, Integer> stockProductos;
    private Map<String, Double> preciosProductos;

    public InventarioService() {
        stockProductos = new HashMap<>();
        preciosProductos = new HashMap<>();
    }

    public boolean esProductoValido(String nombreProducto) {
        return nombreProducto != null && !nombreProducto.isEmpty();
    }

    public boolean validarStock(String nombreProducto, int cantidadRequerida) {
        if (!esProductoValido(nombreProducto)) {
            System.out.println("Error: nombre de producto inválido");
            return false;
        }
        if (cantidadRequerida <= 0) {
            System.out.println("Error: cantidad inválida");
            return false;
        }
        Integer stock = stockProductos.get(nombreProducto);
        if (stock == null) {
            System.out.println("Error: producto no encontrado en inventario");
            return false;
        }
        if (stock < cantidadRequerida) {
            System.out.println("Stock insuficiente para " + nombreProducto);
            return false;
        }
        return true;
    }

    public boolean validarProducto(String nombreProducto, double precio, int cantidad) {
        if (!esProductoValido(nombreProducto)) {
            System.out.println("Error: nombre de producto inválido");
            return false;
        }
        if (precio <= 0) {
            System.out.println("Error: precio inválido");
            return false;
        }
        if (cantidad <= 0) {
            System.out.println("Error: cantidad inválida");
            return false;
        }
        if (stockProductos.containsKey(nombreProducto)) {
            System.out.println("El producto ya existe en el inventario");
            return false;
        }
        return true;
    }

    public void agregarProducto(String nombre, int stock, double precio) {
        stockProductos.put(nombre, stock);
        preciosProductos.put(nombre, precio);
    }

    public void actualizarStock(String nombre, int cantidad) {
        stockProductos.put(nombre, cantidad);
    }

    public int getStock(String nombre) {
        return stockProductos.getOrDefault(nombre, 0);
    }

    public double getPrecio(String nombre) {
        return preciosProductos.getOrDefault(nombre, 0.0);
    }
}

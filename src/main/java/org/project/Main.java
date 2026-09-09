package org.project;

import org.project.modelo.DatosEmpleado;
import org.project.servicio.PanaderiaService;

public class Main {
    public static void main(String[] args) {
        PanaderiaService panaderia = new PanaderiaService();

        // Agregar productos al inventario
        panaderia.agregarProductoInventario("Pan frances", 50, 1500);
        panaderia.agregarProductoInventario("Pan integral", 30, 2500);
        panaderia.agregarProductoInventario("Tres leches", 10, 45000);
        panaderia.agregarProductoInventario("Galletas", 100, 800);

        // Registrar clientes
        panaderia.registrarCliente("Juan Perez", "1234567890", "3001234567");
        panaderia.registrarCliente("Maria Garcia", "0987654321", "3109876543");

        // Registrar empleados
        DatosEmpleado datosEmpleado = new DatosEmpleado("Carlos Lopez", "1111111111",
                "panadero", "mañana", 1200000, "2025-01-01", "CTR-001");
        panaderia.registrarEmpleado(datosEmpleado);

        // Procesar pedidos
        panaderia.procesarPedido(1, "Juan Perez", "1234567890", "3001234567",
                "Pan frances", 1500, 12, "pan", "efectivo",
                "Calle 10 #5-20", "Sin sal");

        panaderia.procesarPedido(2, "Maria Garcia", "0987654321", "3109876543",
                "Tres leches", 45000, 2, "pastel", "tarjeta",
                "Carrera 15 #8-30", "Decoración con flores");

        // Facturar
        panaderia.facturarPedido(1);

        System.out.println("Sistema de panadería funcionando correctamente.");
    }
}

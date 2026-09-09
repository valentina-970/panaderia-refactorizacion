# Documentación de Refactorización - Taller de Ingeniería de Software II

## Proyecto: Sistema de Panadería

**Curso:** Ingeniería de Software II
**Docente:** Diana Marcela Henao Montoya
**Fecha:** 19/01/2026

---

## 1. Código muerto (#13)

**Técnica:** Hide Method (Simplificación de llamadas a métodos)

**Fragmento antes:**
```java
// PanaderiaService.java

// Método que nunca se invoca
public void generarReporteDiario() {
    System.out.println("=== REPORTE DIARIO ===");
    System.out.println("Total de pedidos: " + pedidos.size());
    System.out.println("Total de clientes: " + clientes.size());
    double totalVentas = 0;
    for (Pedido pedido : pedidos) {
        totalVentas += pedido.calcularTotal();
    }
    System.out.println("Total ventas: $" + totalVentas);
}

// Método que nunca se invoca
public void calcularImpuestos(double monto) {
    double iva = monto * 0.19;
    double retencion = monto * 0.025;
    System.out.println("IVA: $" + iva);
    System.out.println("Retención: $" + retencion);
}

// Método que nunca se invoca
public void enviarNotificacion(String mensaje, String destino) {
    System.out.println("Enviando notificación a " + destino + ": " + mensaje);
}
```

**Técnica de refactorización aplicada:** Hide Method - Se eliminaron los métodos que nunca eran invocados por ninguna otra parte del sistema.

**Fragmento después:**
```java
// PanaderiaService.java
// Los métodos generarReporteDiario(), calcularImpuestos() y enviarNotificacion()
// fueron eliminados completamente ya que no eran utilizados.
```

**Explicación:** El código muerto增加了 la complejidad innecesaria del sistema. Estos tres métodos estaban definidos en `PanaderiaService` pero nunca eran llamados desde `Main.java` ni desde ninguna otra clase. Eliminarlos reduce el tamaño de la clase y elimina confusión sobre qué métodos son realmente usados.

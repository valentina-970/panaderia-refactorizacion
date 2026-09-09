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

---

## 2. Obsesión por primitivos (#7)

**Técnica:** Replace Type Code with Class (Organización de datos)

**Fragmento antes:**
```java
// Producto.java - el campo tipo era un String
protected String tipo;

// Pedido.java - switch con strings
switch (tipo) {
    case "pan": ...
    case "pastel": ...
    case "galleta": ...
    case "postre": ...
}

// PanaderiaService.java - se comparaba con strings mágicos
if (tipo.equals("pan")) {
    Pan pan = new Pan(nombreProducto, precio, cantidad);
    ...
} else if (tipo.equals("pastel")) {
    Pastel pastel = new Pastel(nombreProducto, precio, cantidad);
    ...
}
```

**Técnica de refactorización aplicada:** Replace Type Code with Class - Se creó una enumeración `TipoProducto` para reemplazar los strings usados como tipo.

**Fragmento después:**
```java
// Nuevo archivo: modelo/TipoProducto.java
public enum TipoProducto {
    PAN("pan"),
    PASTEL("pastel"),
    GALLETA("galleta"),
    POSTRE("postre");

    private final String codigo;

    TipoProducto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoProducto fromCodigo(String codigo) {
        for (TipoProducto tipo : values()) {
            if (tipo.codigo.equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo no válido: " + codigo);
    }
}

// Producto.java - campo tipo ahora es de tipo TipoProducto
protected TipoProducto tipo;

// Pedido.java - switch ahora usa enum
switch (tipo) {
    case PAN: ...
    case PASTEL: ...
    case GALLETA: ...
    case POSTRE: ...
}
```

**Explicación:** Usar strings como tipo de código es propenso a errores (un typo silencioso compila igual). Al usar una enumeración, se obtiene type-safety, autocompletado del IDE, y se elimina la posibilidad de valores inválidos.

---

## 3. Generalidad especulativa (#12)

**Técnica:** Hide Field + Hide Method (Organización de datos / Simplificación de llamadas)

**Fragmento antes:**
```java
// Producto.java - campos que nunca se usaban
protected String sabor;
protected String tamano;
protected boolean esPremium;
protected String codigoBarras;
protected String fechaProduccion;
protected String fechaVencimiento;
protected int calorias;

// Métodos que nunca se invocaban
public void verificarCalidad() {
    System.out.println("Verificando calidad de " + nombre);
}

public void generarEtiqueta() {
    System.out.println("Etiqueta: " + nombre + " - $" + precio);
}

// Getters/Setters que nunca se usaban
public String getSabor() { return sabor; }
public void setSabor(String sabor) { this.sabor = sabor; }
public String getTamano() { return tamano; }
public void setTamano(String tamano) { this.tamano = tamano; }
public boolean isEsPremium() { return esPremium; }
public void setEsPremium(boolean esPremium) { this.esPremium = esPremium; }
```

**Técnica de refactorización aplicada:** Hide Field + Hide Method - Se eliminaron los campos, métodos y getters/setters que nunca eran utilizados.

**Fragmento después:**
```java
// Producto.java - solo se mantienen los campos realmente usados
protected String nombre;
protected double precio;
protected int cantidad;
protected TipoProducto tipo;

// Solo quedan los métodos abstractos y getters/setters necesarios
public abstract String getCategoria();
public abstract double calcularDescuento();
```

**Explicación:** La generalidad especulativa ocurre cuando se agrega código "por si acaso" se necesita en el futuro. Estos campos y métodos nunca fueron usados, lo que aumenta la complejidad de mantenimiento sin aportar valor.

---

## 4. Lista de parámetros larga (#9)

**Técnica:** Introduce Parameter Object (Simplificación de llamadas a métodos)

**Fragmento antes:**
```java
// Empleado.java - métodos con 7 y 8 parámetros
public void registrar(String nombre, String cedula, String cargo, String turno,
                      double salario, String fechaIngreso, String numeroContrato) {
    this.nombre = nombre;
    this.cedula = cedula;
    this.cargo = cargo;
    this.turno = turno;
    this.salario = salario;
    System.out.println("Empleado registrado: " + nombre + " - " + cargo);
}

public void actualizar(String nombre, String cedula, String cargo, String turno,
                       double salario, String direccion, String telefono, String email) {
    this.nombre = nombre;
    this.cedula = cedula;
    this.cargo = cargo;
    this.turno = turno;
    this.salario = salario;
    System.out.println("Empleado actualizado: " + nombre);
}

// PanaderiaService.java
public void registrarEmpleado(String nombre, String cedula, String cargo,
                              String turno, double salario, String fechaIngreso,
                              String numeroContrato) {
    Empleado empleado = new Empleado(nombre, cedula, cargo, turno, salario);
    empleados.add(empleado);
    System.out.println("Empleado registrado: " + nombre);
}
```

**Técnica de refactorización aplicada:** Introduce Parameter Object - Se creó una clase `DatosEmpleado` que agrupa todos los parámetros relacionados.

**Fragmento después:**
```java
// Nuevo archivo: modelo/DatosEmpleado.java
public class DatosEmpleado {
    private String nombre;
    private String cedula;
    private String cargo;
    private String turno;
    private double salario;
    private String fechaIngreso;
    private String numeroContrato;

    public DatosEmpleado(String nombre, String cedula, String cargo, String turno,
                         double salario, String fechaIngreso, String numeroContrato) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cargo = cargo;
        this.turno = turno;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.numeroContrato = numeroContrato;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getCargo() { return cargo; }
    public String getTurno() { return turno; }
    public double getSalario() { return salario; }
    public String getFechaIngreso() { return fechaIngreso; }
    public String getNumeroContrato() { return numeroContrato; }
}

// Empleado.java - ahora usa el objeto
public void registrar(DatosEmpleado datos) {
    this.nombre = datos.getNombre();
    this.cedula = datos.getCedula();
    this.cargo = datos.getCargo();
    this.turno = datos.getTurno();
    this.salario = datos.getSalario();
    System.out.println("Empleado registrado: " + datos.getNombre() + " - " + datos.getCargo());
}

// PanaderiaService.java
public void registrarEmpleado(DatosEmpleado datos) {
    Empleado empleado = new Empleado(datos.getNombre(), datos.getCedula(),
            datos.getCargo(), datos.getTurno(), datos.getSalario());
    empleados.add(empleado);
    System.out.println("Empleado registrado: " + datos.getNombre());
}
```

**Explicación:** Listas de parámetros largas son difíciles de recordar, propensas a errores de orden, y dificultan la extensión. Al agrupar en un objeto, se mejora la legibilidad y se facilita agregar nuevos campos sin cambiar todas las firmas de método.

---

## 5. Condicionales complejos (#6)

**Técnica:** Decompose Conditional (Simplificación de condicionales)

**Fragmento antes:**
```java
// PanaderiaService.java - en procesarPedido(), condicionales anidados
if (pedido.verificarDisponibilidad(this)) {
    if (metodoPago.equals("efectivo") || metodoPago.equals("tarjeta") ||
        metodoPago.equals("nequi") || metodoPago.equals("daviplata")) {
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
```

**Técnica de refactorización aplicada:** Decompose Conditional - Se extrajeron las condiciones a métodos con nombres descriptivos.

**Fragmento después:**
```java
// PanaderiaService.java - condiciones extraídas a métodos
if (pedido.verificarDisponibilidad(this)) {
    if (esMetodoPagoValido(metodoPago)) {
        if (tieneDireccionEntrega(direccionEntrega)) {
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

// Métodos extraídos
private boolean esMetodoPagoValido(String metodoPago) {
    return metodoPago.equals("efectivo") || metodoPago.equals("tarjeta") ||
           metodoPago.equals("nequi") || metodoPago.equals("daviplata");
}

private boolean tieneDireccionEntrega(String direccion) {
    return direccion != null && !direccion.isEmpty();
}
```

**Explicación:** Las condiciones anidadas difíciles de leer se descomponen en métodos que explican la intención. Esto mejora la legibilidad y facilita modificar las reglas de validación en el futuro.

---

## 6. Código duplicado (#4)

**Técnica:** Extract Method (Composición de métodos)

**Fragmento antes:**
```java
// PanaderiaService.java - en procesarPedido()
Integer stock = inventario.getStock(nombreProducto);
if (stock == null) {
    System.out.println("Error: producto no encontrado en inventario");
    return;
}
if (stock < cantidad) {
    System.out.println("Stock insuficiente para " + nombreProducto);
    return;
}

// InventarioService.java - verificarStock() tiene la misma lógica
public boolean verificarStock(String nombreProducto, int cantidadRequerida) {
    if (nombreProducto == null || nombreProducto.isEmpty()) {
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
```

**Técnica de refactorización aplicada:** Extract Method - Se consolidó la lógica de validación duplicada en un solo método reutilizable.

**Fragmento después:**
```java
// InventarioService.java - método único de validación
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

// PanaderiaService.java - ahora usa el método consolidado
if (!inventario.validarStock(nombreProducto, cantidad)) {
    return;
}
```

**Explicación:** La lógica de validación de stock estaba duplicada entre `PanaderiaService` e `InventarioService`. Al consolidarla en un solo método, se elimina la duplicación y se facilita el mantenimiento.

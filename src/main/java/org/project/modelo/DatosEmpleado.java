package org.project.modelo;

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

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getCargo() { return cargo; }
    public String getTurno() { return turno; }
    public double getSalario() { return salario; }
    public String getFechaIngreso() { return fechaIngreso; }
    public String getNumeroContrato() { return numeroContrato; }
}

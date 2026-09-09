package org.project.modelo;

// Code Smell #9: Lista de parámetros larga
public class Empleado {

    private String nombre;
    private String cedula;
    private String cargo;
    private String turno;
    private double salario;

    public Empleado(String nombre, String cedula, String cargo, String turno, double salario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cargo = cargo;
        this.turno = turno;
        this.salario = salario;
    }

    // Code Smell #9: Lista de parámetros larga - 7 parámetros
    public void registrar(String nombre, String cedula, String cargo, String turno,
                          double salario, String fechaIngreso, String numeroContrato) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cargo = cargo;
        this.turno = turno;
        this.salario = salario;
        System.out.println("Empleado registrado: " + nombre + " - " + cargo);
    }

    // Code Smell #9: Otro método con muchos parámetros
    public void actualizar(String nombre, String cedula, String cargo, String turno,
                           double salario, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cargo = cargo;
        this.turno = turno;
        this.salario = salario;
        System.out.println("Empleado actualizado: " + nombre);
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}

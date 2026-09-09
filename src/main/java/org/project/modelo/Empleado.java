package org.project.modelo;

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

    public void registrar(DatosEmpleado datos) {
        this.nombre = datos.getNombre();
        this.cedula = datos.getCedula();
        this.cargo = datos.getCargo();
        this.turno = datos.getTurno();
        this.salario = datos.getSalario();
        System.out.println("Empleado registrado: " + datos.getNombre() + " - " + datos.getCargo());
    }

    public void actualizar(DatosEmpleado datos) {
        this.nombre = datos.getNombre();
        this.cedula = datos.getCedula();
        this.cargo = datos.getCargo();
        this.turno = datos.getTurno();
        this.salario = datos.getSalario();
        System.out.println("Empleado actualizado: " + datos.getNombre());
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

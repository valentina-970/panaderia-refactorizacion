package org.project.modelo;

// Code Smell #7: Obsesión por primitivos - usando solo String para todo
public class Cliente {

    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    private String email;
    private String tipoCliente;

    public Cliente(String nombre, String cedula, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.tipoCliente = "regular";
    }

    // Code Smell #7: Obsesión por primitivos - método conmany Strings
    public String getInformacion() {
        return "Nombre: " + nombre + ", Cedula: " + cedula + ", Telefono: " + telefono +
                ", Direccion: " + direccion + ", Email: " + email;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCedula() { return cedula; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
}

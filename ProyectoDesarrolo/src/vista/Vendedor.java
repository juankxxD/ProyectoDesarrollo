/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Juan
 */
public class Vendedor {
    private String ID;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String direccion;
    private int Numero_Clientes;

    public Vendedor(String ID, String Nombre, String Apellido, String Telefono, String direccion, int Numero_Clientes) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.direccion = direccion;
        this.Numero_Clientes = Numero_Clientes;
    }
    public Vendedor(String ID){
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero_Clientes() {
        return Numero_Clientes;
    }

    public void setNumero_Clientes(int Numero_Clientes) {
        this.Numero_Clientes = Numero_Clientes;
    }
    
    
}

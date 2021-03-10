/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Conexion;

/**
 *
 * @author Juan
 */
public class Usuarios {
    private String ID;
    private String Nombre;
    private String Apellido;
    private String Telefono;

    public Usuarios(){
        
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

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    private String direccion;
    private String Contraseña;

    public Usuarios(String ID, String Nombre, String Apellido, String Telefono, String direccion, String Contraseña) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.direccion = direccion;
        this.Contraseña = Contraseña;
    }
    public ObservableList<Usuarios> getUsuarios(){
        ObservableList<Usuarios> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT P1.\"ID\", P1.\"Nombre\", P1.\"Telefono\", P1.direccion, P1.\"Contraseña\", P1.\"Apellido\" \n" +
"				 FROM public.\"Usuario\" P1;";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                String ID =rs.getString("ID");
                String nombre= rs.getString("Nombre");
                String Apellido= rs.getString("Apellido");
                String Telefono= rs.getString("Telefono");
                String direccion= rs.getString("direccion");
                String Contraseña= rs.getString("Contraseña");
                
                Usuarios u = new Usuarios(ID, nombre,Apellido, Telefono, direccion, Contraseña );
                
                obs.add(u);
            }
        }catch(SQLException e){
            System.err.println(e.toString());
        }
        return obs;
    }
}

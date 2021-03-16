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
import javax.swing.JOptionPane;
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
    private String TipoUsuario;

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

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
    public void guardarTipo(String Tipo){
        TipoUsuario = Tipo.trim();
        System.out.println(TipoUsuario);
    }
    public void registrarCliente(String Documento, String nombre, String Apellido, String telefono, String Direccion, String Puntos, String contraseña){
        try{
        String sql = " INSERT INTO PUBLIC.\"Usuario\"\n(" +
"             \"ID\", \"Nombre\",\"Apellido\", \"Telefono\", \"direccion\", \"Contraseña\")\n" +
"				 VALUES (?,?,?,?,? ,?);";
        String[] opcion = {"Si","No"};
        String n = (String)JOptionPane.showInputDialog(null,"Desea registrar el usuario","Aviso registro", JOptionPane.ERROR_MESSAGE, null, opcion, opcion[0]);
        if(n.equals("No")){
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
        }else{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, Documento.trim());
        ps.setString(2, nombre.trim());
        ps.setString(3, Apellido.trim());
        ps.setString(4, telefono.trim());
        ps.setString(5, Direccion.trim());
        ps.setString(6, contraseña.trim());
        ps.executeUpdate();
        EnCliente(Documento, Puntos);
        JOptionPane.showMessageDialog(null, "Usuario registrado");
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
   public void EnCliente(String ID, String Puntos){
       try{
       String sql= "INSERT INTO PUBLIC.\"clientes\"(\n" +
"             \"ID\", \"puntos\")\n" +
"				 VALUES (?,?)";
       int puntos= Integer.parseInt(Puntos);
       Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, ID.trim());
        ps.setInt(2, puntos);
        ps.executeUpdate();
        EnUsuario_Tipo(ID);
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
   }
   
   public void EnUsuario_Tipo(String ID){
       try{
       String sql= "INSERT INTO PUBLIC.\"Usuario_tipo\"(\n" +
"             \"id_U\", \"id_T\")VALUES (?, ?)";
       String cliente = "3";
       Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, ID.trim());
        ps.setString(2, cliente.trim());
        ps.executeUpdate();
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
   }
}

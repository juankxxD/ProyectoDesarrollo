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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author Juan
 */
public class Cliente {
    private static String ID;
    private static String Nombre;
    private static String Apellido;
    private static String Telefono;
    private static String direccion;
    private static int puntos;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public int getPuntos() {
        return puntos;
    }

    public Cliente(String ID, String Nombre, String Apellido, String Telefono,String direccion, int puntos) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.puntos = puntos;
        this.direccion = direccion;
    }
    
     public Cliente(String ID){
       this.ID = ID;  
     }
    public void completarDatos(){
        try{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(" SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.direccion, p2.puntos\n" +
"             FROM \"Usuario\" p1, \"clientes\" p2\n" +
"             WHERE p1.\"ID\"='" + ID +"' AND p1.\"ID\"=p2.\"ID\"");
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
             String ID = rs.getString("ID");
             String Nombre = rs.getString("Nombre");
             String Apellido = rs.getString("Apellido");
             String Telefono = rs.getString("Telefono");
             String direccion = rs.getString("direccion");
             int puntos = rs.getInt("puntos");
             
             System.out.println(Nombre);
             Cliente c = new Cliente(ID, Nombre, Apellido, Telefono, direccion, puntos);
             System.out.println(this.Apellido);
            // MenuController m = new MenuController();
             //m.recibirDatos(ID, Nombre, Apellido);
        }
        } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
   }
    }
    public ObservableList<Cliente> getCliente(){
        ObservableList<Cliente> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.direccion, p2.puntos\n" +
"             FROM \"Usuario\" p1, \"clientes\" p2\n" +
"             WHERE p1.\"ID\"=p2.\"ID\"";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                String ID =rs.getString("ID");
                String nombre= rs.getString("Nombre");
                String Apellido= rs.getString("Apellido");
                String Telefono= rs.getString("Telefono");
                String direccion= rs.getString("direccion");
                int puntos= rs.getInt("puntos");
                
                Cliente c = new Cliente(ID, nombre,Apellido, Telefono, direccion, puntos);
                obs.add(c);
                
            }
        }catch(SQLException e){
            System.err.println(e.toString());
        }
        return obs;
    }

    public Cliente() {
    }
}

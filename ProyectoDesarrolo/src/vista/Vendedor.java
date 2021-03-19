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
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author Juan
 */
public class Vendedor {
    private static String ID;
    private static String Nombre;
    private static String Apellido;
    private static String Telefono;
    private static String direccion;
    private static int Numero_Clientes;
    private static int Salario;
    private static int comision;

    public Vendedor(String ID, String Nombre, String Apellido, String Telefono, String direccion, int Numero_Clientes, int salario, int comision) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.direccion = direccion;
        this.Numero_Clientes = Numero_Clientes;
        this.Salario = salario;
        this.comision = comision;
    }
    public Vendedor(){
        
    }

    public static int getComision() {
        return comision;
    }

    public static void setComision(int comision) {
        Vendedor.comision = comision;
    }

    public int getSalario() {
        return Salario;
    }

    public void setSalario(int Salario) {
        this.Salario = Salario;
    }
    public Vendedor(String ID){
        this.ID = ID;
        completarDatos();
    }
    public void completarDatos(){
        try{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(" SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.\"direccion\", p3.\"Tota_clientes\", p3.\"Salario\", p3.\"Comision\"\n" +
"             FROM \"Usuario\" p1, \"Vendedores\" p3\n" +
"             WHERE p1.\"ID\"='" + ID + "' AND p1.\"ID\"=p3.\"ID\"");
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
             String ID = rs.getString("ID");
             String Nombre = rs.getString("Nombre");
             String Apellido = rs.getString("Apellido");
             String Telefono = rs.getString("Telefono");
             String direccion = rs.getString("direccion");
             int total_Clientes = rs.getInt("Tota_clientes");
             int Salario = rs.getInt("Salario");
             int comision = rs.getInt("Comision");
             System.out.println(total_Clientes);
             Vendedor v = new Vendedor(ID, Nombre, Apellido, Telefono, direccion, total_Clientes, Salario, comision);
             
        }
        } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
   }
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
    public void AumentarNumeroClientes(){
        String SQL = "UPDATE \"Vendedores\"\n" +
"				 SET \"Tota_clientes\" = ?\n" +
"				 WHERE \"ID\" = '" + ID +"'" ;
        try{
           
        int Numero = Numero_Clientes + 1;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setInt(1,Numero);
            System.out.println(Numero_Clientes);
        Numero_Clientes = Numero;
        Administracion a = new Administracion();
        a.Cambiarcomision();
        ps.executeUpdate();
        
       // ResultSet rs= ps.executeQuery();
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
    }
}

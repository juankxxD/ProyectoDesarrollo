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
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author Daniela
 */
public class producto {
    private  String Nombre;
    private  String  codigo;
    private  int valor;
    private  int ValorDescuento;
    private  LocalDate FechaVencimiento;
    private  int cantidad;

    public  int getCantidad() {
        return cantidad;
    }

    public  void setCantidad(int cantidad) {
        cantidad = cantidad;
    }

    public producto() {
         
    }

    public LocalDate getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValorDescuento() {
        return ValorDescuento;
    }

    public void setValorDescuento(int ValorDescuento) {
        this.ValorDescuento = ValorDescuento;
    }

   

    public producto(String codigo, String Nombre, LocalDate Fecha_Ven, int Valor, int ValorDescuento, int cantidad) {
        this.Nombre = Nombre;
        this.codigo = codigo;
        this.valor = Valor;
        this.ValorDescuento = ValorDescuento;
        this.FechaVencimiento = Fecha_Ven;
        this.cantidad = cantidad;
    }
            
    public ObservableList<producto> getProducto(){
        ObservableList<producto> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT *\n" +
"				 FROM \"Productos\"";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                String codigo =rs.getString("codigo");
                String Nombre= rs.getString("Nombre");
                LocalDate Fecha_Ven= rs.getDate("Fecha_Ven").toLocalDate();
                int Valor= rs.getInt("Valor");
                int valor_Descuento= rs.getInt("valor_Descuento");
                int cantidad= rs.getInt("cantidad");
                
                producto p = new producto(codigo, Nombre,Fecha_Ven, Valor, valor_Descuento, cantidad);
                obs.add(p);
                
            }
        }catch(SQLException e){
            System.err.println(e.toString());
        }
        return obs;
    }
    public void seleccion(String ID, String Cantidad){
        String SQL = "SELECT p4.\"cantidad\"\n" +
"				 FROM \"Productos\" p4\n" +
"				 WHERE p4.codigo='"+ ID +"'";
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            PreparedStatement ps= con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            int cantidad = 0;
            while(rs.next()){
                cantidad =rs.getInt("cantidad");
                }
            
            cantidad += Integer.parseInt(Cantidad);
            aumentar(ID, cantidad);
        }catch(SQLException e){
            System.err.println(e.toString());
        }
    }
    public void CambiarDescuento(String id){
        String cambiar = JOptionPane.showInputDialog(null, "Valor a ingresar", "Cambiar descuento");
        String SQL = "UPDATE \"Productos\"\n" +
"				SET \"valor_Descuento\" = ?\n" +
"				WHERE \"codigo\" = '" + id +"'";
        try{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setInt(1,Integer.parseInt(cambiar));
       
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cambio de descuento exitoso");
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
    }
    
    public void aumentar(String ID, int Cantidad){
        String SQL="UPDATE \"Productos\"\n" +
"				 SET \"cantidad\" = ?\n" +
"				 WHERE \"codigo\" = '" + ID +"'";
        try{
        int cantidad = Cantidad;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setInt(1,cantidad);
       
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Ha aumentado el producto exitosamente");
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
    }
}

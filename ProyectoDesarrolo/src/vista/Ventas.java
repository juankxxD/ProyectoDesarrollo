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
import modelo.Conexion;

/**
 *
 * @author Vicky
 */
public class Ventas {
    private String cod_producto;
    private String cod_cliente;
    private LocalDate fecha;
    private int descuento;
    private int valor;
   

    public Ventas(String cod_producto, String cod_cliente, LocalDate fecha, int valor) {
        this.cod_producto = cod_producto;
        this.cod_cliente = cod_cliente;
        this.fecha = fecha;
        this.valor = valor;
    }
 
    public ObservableList<Ventas> getVentas(){
        ObservableList<Ventas> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT \"cod_cliente\", \"cod_producto\", (\"fecha_compra\" + 2) AS fecha_compra, \"valor\"\n" +
" 				FROM \"ventas\"";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                String codigo =rs.getString("cod_cliente");
                String codigoProducto= rs.getString("cod_producto");
                LocalDate Fecha_Ven= rs.getDate("fecha_compra").toLocalDate();
                int Valor= rs.getInt("valor");
                
                
                Ventas v = new Ventas(codigo, codigoProducto,Fecha_Ven, Valor );
                obs.add(v);
                
            }
        }catch(SQLException e){
            System.err.println(e.toString());
        }
        return obs;
    }

    public Ventas() {
    }
    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
   
    
    
    
}

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Conexion;

/**
 *
 * @author Vicky
 */
public class Ventas {

    private String id_ventas;
    private String cod_producto;
    private String cod_cliente;
    private LocalDate fecha;
    private String estado;
    private int valor;
    private int cantidad;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(String id_ventas) {
        this.id_ventas = id_ventas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Ventas(String id_ventas, String cod_producto, String cod_cliente, LocalDate fecha, int valor, int cantidad, String estado) {

        this.id_ventas = id_ventas;
        this.cod_producto = cod_producto;
        this.cod_cliente = cod_cliente;
        this.fecha = fecha;
        this.valor = valor;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Ventas(String venta,String cod_producto, String cod_cliente, LocalDate fecha, int valor) {
        this.id_ventas = venta;
        this.cod_producto = cod_producto;
        this.cod_cliente = cod_cliente;
        this.fecha = fecha;

        this.valor = valor;
    }

    public ObservableList<Ventas> getVentas() {
        ObservableList<Ventas> obs = FXCollections.observableArrayList();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT \"id_venta\", \"cod_cliente\", \"cod_producto\", (\"fecha_compra\" + 2) AS fecha_compra, \"valor\"\n"
                    + " 				FROM \"ventas\"";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id_venta");
                String codigo = rs.getString("cod_cliente");
                String codigoProducto = rs.getString("cod_producto");
                LocalDate Fecha_Ven = rs.getDate("fecha_compra").toLocalDate();
                int Valor = rs.getInt("valor");

                Ventas v = new Ventas(id, codigo, codigoProducto, Fecha_Ven, Valor);
                obs.add(v);

            }
        } catch (SQLException e) {
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ObservableList<Ventas> getTotalVentas() {
        ObservableList<Ventas> obs = FXCollections.observableArrayList();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT *\n"
                    + "FROM \"ventas\"\n"
                    + "WHERE \"estado\" = 'entregado'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("id_venta");
                String Nombre = rs.getString("cod_cliente");
                String cod_producto = rs.getString("cod_producto");
                LocalDate Fecha_Ven = rs.getDate("fecha_compra").toLocalDate();
                int Valor = rs.getInt("valor");
                int cantidad = rs.getInt("cantidad");
                String estado = rs.getString("estado");

                Ventas v = new Ventas(codigo, Nombre, cod_producto, Fecha_Ven, Valor, cantidad, estado);
                obs.add(v);

            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return obs;
    }

    public ObservableList<Ventas> EliminarVentas(String id) {
        ObservableList<Ventas> obs = FXCollections.observableArrayList();
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int MES = fecha.get(Calendar.MONTH) + 1;
        int DIA = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = año + "-" + MES + "-" + DIA;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT *\n"
                    + "FROM \"ventas\"\n"
                    + "WHERE (\"fecha_compra\" + 3)< '" + fechaActual + "' AND \"cod_cliente\" = '" + id + "' AND \"estado\" = 'entregado'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("id_venta");
                String Nombre = rs.getString("cod_cliente");
                String cod_producto = rs.getString("cod_producto");
                LocalDate Fecha_Ven = rs.getDate("fecha_compra").toLocalDate();
                int Valor = rs.getInt("valor");
                int cantidad = rs.getInt("cantidad");
                String estado = rs.getString("estado");

                Ventas v = new Ventas(codigo, Nombre, cod_producto, Fecha_Ven, Valor, cantidad, estado);
                obs.add(v);

            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return obs;
    }

    public ObservableList<Ventas> esteCliente(String id) {
        ObservableList<Ventas> obs = FXCollections.observableArrayList();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT *\n"
                    + "FROM \"ventas\"\n"
                    + "WHERE \"cod_cliente\" = '" + id + "' AND \"estado\" = 'entregado'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("id_venta");
                String Nombre = rs.getString("cod_cliente");
                String cod_producto = rs.getString("cod_producto");
                LocalDate Fecha_Ven = rs.getDate("fecha_compra").toLocalDate();
                int Valor = rs.getInt("valor");
                int cantidad = rs.getInt("cantidad");
                String estado = rs.getString("estado");

                Ventas v = new Ventas(codigo, Nombre, cod_producto, Fecha_Ven, Valor, cantidad, estado);
                obs.add(v);

            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return obs;
    }

    public ObservableList<Ventas> Devolucion() {
        ObservableList<Ventas> obs = FXCollections.observableArrayList();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT *\n"
                    + "FROM \"ventas\"\n"
                    + "WHERE \"estado\" = 'pendiente'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("id_venta");
                String Nombre = rs.getString("cod_cliente");
                String cod_producto = rs.getString("cod_producto");
                LocalDate Fecha_Ven = rs.getDate("fecha_compra").toLocalDate();
                int Valor = rs.getInt("valor");
                int cantidad = rs.getInt("cantidad");
                String estado = rs.getString("estado");

                Ventas v = new Ventas(codigo, Nombre, cod_producto, Fecha_Ven, Valor, cantidad, estado);
                obs.add(v);

            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return obs;
    }
}

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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private static int puntos;
    private  String Telefono;
    private  String direccion;
    private  int puntosC;
    private String NombreC;
    private String ApellidoC;
    private String IDC;

    public int getPuntosC() {
        return puntosC;
    }

    public void setPuntosC(int puntosC) {
        this.puntosC = puntosC;
    }

    public String getIDC() {
        return IDC;
    }

    public void setIDC(String IDC) {
        this.IDC = IDC;
    }

    public String getNombreC() {
        return NombreC;
    }

    public void setNombreC(String aver) {
        this.NombreC = aver;
    }

    public String getApellidoC() {
        return ApellidoC;
    }

    public void setApellidoC(String ApellidoC) {
        this.ApellidoC = ApellidoC;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setID(String ID) {
        this.ID=ID;
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
        this.IDC=ID;
        this.Nombre = Nombre;
        this.NombreC= Nombre;
        this.ApellidoC=Apellido;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.puntosC = puntos;
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
    
    public ObservableList<Cliente> getConsultar(){
        ObservableList<Cliente> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            Vendedor v = new Vendedor();
            String sql = "SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.\"direccion\", p2.\"puntos\"\n" +
"            FROM \"Usuario\" p1, \"clientes\" p2, \"Clientes_Registrados\" P3\n" +
"             WHERE p1.\"ID\"=p2.\"ID\" AND p2.\"ID\" = P3.\"ID_C\" AND P3.\"ID_V\" = '" + v.getID() +"'";
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
    
    public ObservableList<Cliente> getActualizar(){
        ObservableList<Cliente> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            Vendedor v = new Vendedor();
            String sql = "SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.\"direccion\", p2.\"puntos\"\n" +
"            FROM \"Usuario\" p1, \"clientes\" p2\n" +
"             WHERE p1.\"ID\"=p2.\"ID\" AND p2.estado='SI'";
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
    
    public ObservableList<Cliente> getEliminar(){
        ObservableList<Cliente> obs = FXCollections.observableArrayList();
        try{
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int MES = fecha.get(Calendar.MONTH) + 1;
            int DIA = fecha.get(Calendar.DAY_OF_MONTH);
            String fechaActual = año +"-" + MES + "-" + DIA;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT DISTINCT \"cod_cliente\", p2.\"Nombre\", p2.\"Apellido\", p2.\"Telefono\", p2.\"direccion\", p1.\"puntos\"\n" +
"				from\n" +
"				(SELECT (\"fecha_compra\" + 365) AS fecha, P10.\"cod_cliente\" AS IDP\n" +
"				FROM \"ventas\" p10\n" +
"				GROUP BY IDP, fecha) AS pofavor, \"ventas\" p11, \"clientes\" p1, \"Usuario\" p2\n" +
"				WHERE \"fecha\" <= '"+ fechaActual +"' AND p11.\"cod_cliente\" = \"idp\" AND p1.\"ID\" = p11.cod_cliente AND p2.\"ID\" = p11.\"cod_cliente\"\n" +
"				AND p11.\"cod_cliente\" NOT in (SELECT \"cod_cliente\"\n" +
"				FROM (SELECT  \"cod_cliente\", (\"fecha_compra\" + 365) AS fechaActuales\n" +
"				FROM \"ventas\") AS prueba\n" +
"				WHERE fechaActuales > '"+ fechaActual +"')";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                String ID =rs.getString("cod_cliente");
                String nombre= rs.getString("Nombre");
                String Apellido= rs.getString("Apellido");
                String Telefono= rs.getString("Telefono");
                String direccion= rs.getString("direccion");
                int puntos= rs.getInt("puntos");
                System.out.println(ID);
                Cliente c = new Cliente(ID, nombre,Apellido, Telefono, direccion, puntos);
                obs.add(c);
                
            }
        }catch(SQLException e){
            System.err.println(e.toString());
        }
        return obs;
    }
    public ObservableList<Cliente> getPuntos1(){
        ObservableList<Cliente> obs = FXCollections.observableArrayList();
        try{
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            Vendedor v = new Vendedor();
            String sql = "SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.\"direccion\", p2.\"puntos\"\n" +
" 				FROM \"clientes\" p2, \"Usuario\" p1\n" +
" 				WHERE p1.\"ID\" IN \n" +
" 				(SELECT \"ID\"\n" +
" 				FROM \"clientes\"\n" +
" 				WHERE \"puntos\" >=100) AND p2.\"ID\" = p1.\"ID\"";
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
    public void Eliminar(String ID) throws SQLException{
        Conexion conn = new Conexion();
            Connection con = conn.getConexion();
        String SQL = "DELETE FROM \"ventas\"\n" +
" 				WHERE \"cod_cliente\" = '"+ ID +"'";
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.executeUpdate();
        String SQL1 = "DELETE FROM \"Clientes_Registrados\"\n" +
" 				WHERE \"ID_C\" = '"+ ID +"'";
        PreparedStatement ps1= con.prepareStatement(SQL1);
        ps1.executeUpdate();
        String SQL2 = "DELETE FROM \"Usuario_tipo\"\n" +
" 				WHERE \"id_U\" = '"+ ID +"'";
         PreparedStatement ps2= con.prepareStatement(SQL2);
        ps2.executeUpdate();
        String SQL3 = "DELETE FROM \"clientes\"\n" +
" 				WHERE \"ID\" = '" + ID +"'";
        PreparedStatement ps3= con.prepareStatement(SQL3);
        ps3.executeUpdate();
        String SQL4 = "DELETE FROM \"Usuario\"\n" +
" 				WHERE \"ID\" = '" + ID +"'";
        PreparedStatement ps4= con.prepareStatement(SQL4);
        ps4.executeUpdate();      
        JOptionPane.showMessageDialog(null, "Usuario Eliminado");
    }
    
    public Cliente() {
    }
}

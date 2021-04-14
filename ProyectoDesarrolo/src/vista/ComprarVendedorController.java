/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class ComprarVendedorController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private Button Comprar;
    @FXML
    private Button Volver;
    @FXML
    private TextField txtCod_pro;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_fecha;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtDescuento;
    @FXML
    private TextField txtcod_Cliente;
    @FXML
    private TextField txtCantidad;
    @FXML
    private AnchorPane txtDescuentoPuntos;
    @FXML
    private Button BtnComprarDescuento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Iniciar();
        } catch (SQLException ex) {
            Logger.getLogger(ComprarVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colocarImagenBotones();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }

    @FXML
    private void Comprar(ActionEvent event) throws SQLException, ParseException {
        producto p = new producto();
        Conexion conn = new Conexion();
        int cantidad = 0;
        Connection con = conn.getConexion();
        String sql = "SELECT *\n" +
" 				FROM \"Productos\"\n" +
" 				WHERE \"codigo\" = '"+ p.getID() +"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
    
            txtCod_pro.setText(rs.getString("codigo"));
            txt_nombre.setText(rs.getString("Nombre"));
            LocalDate fecha = rs.getDate("Fecha_Ven").toLocalDate();
            txtValor.setText(rs.getString("Valor"));
            txtDescuento.setText(rs.getString("valor_Descuento"));
            cantidad = rs.getInt("cantidad");
            txt_fecha.setText(fecha + "");
    }
        if(txtcod_Cliente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe de poner el codigo del cliente");
        }else{
        if(cantidad <= 0){
            JOptionPane.showMessageDialog(null, "No quedan productos existentes");
        } else {
            int resta = Integer.parseInt(txtCantidad.getText());
            cantidad -= resta;
            if(cantidad < 0){
                JOptionPane.showMessageDialog(null, "No hay la cantidad de productos solicitados");
            } else{
               Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int MES = fecha.get(Calendar.MONTH) + 1;
            int DIA = fecha.get(Calendar.DAY_OF_MONTH);
            String fechaActual = año +"-" + MES + "-" + DIA;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate;
            java.util.Date nfecha = formato.parse(fechaActual);
            fechaDate = new java.sql.Date(nfecha.getTime());
            String SQL = "INSERT INTO PUBLIC.\"ventas\"(\"cod_cliente\", \"cod_producto\", \"fecha_compra\", \"valor\", \"cantidad\")\n" +
" 				VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps1= con.prepareStatement(SQL);
            ps1.setString(1, txtcod_Cliente.getText().trim());
            ps1.setString(2, txtCod_pro.getText().trim());
            ps1.setDate(3, fechaDate);
            ps1.setInt(4, traerTotal());
        ps1.setInt(5, Integer.parseInt(txtCantidad.getText()));
        ps1.executeUpdate();
        cantidadTotal(cantidad);
        JOptionPane.showMessageDialog(null, "El cliente " + txtcod_Cliente.getText() + " a comprado " + txt_nombre.getText());
    }
        }
        }
    }
    public void cantidadTotal(int cantidad) throws SQLException{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        int resta = cantidad; 
        String sql = "UPDATE \"Productos\"\n" +
" 				SET \"cantidad\" = ?\n" +
" 				WHERE \"codigo\" = '" + txtCod_pro.getText() + "'";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1,resta);
         ps.executeUpdate();
         restarPuntos();
    }
     public void restarPuntos() throws SQLException{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        int puntos = 0;
        String sql = "SELECT \"puntos\"\n" +
" 				FROM \"clientes\"\n" +
" 				WHERE \"ID\" = '"+ txtcod_Cliente.getText() +"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        puntos = rs.getInt("puntos");
        }
         
        int suma = puntos + 5;
       
        String sql1 = "UPDATE \"clientes\"\n" +
" 				SET \"puntos\" = ?\n" +
" 				WHERE \"ID\" = '" + txtcod_Cliente.getText()+"'";
        PreparedStatement ps1= con.prepareStatement(sql1);
        ps1.setInt(1,suma);
         ps1.executeUpdate();
    }
         
    

    
     public int traerTotal() throws SQLException{
        int descuento1 = 0;
        int total = 0;
        Conexion conn = new Conexion();
        System.out.println("Llegue aqui");
        Connection con = conn.getConexion();
        String sql = "SELECT \"Valor\", \"valor_Descuento\"\n" +
" 				FROM \"Productos\"\n" +
" 				WHERE \"codigo\" = '" + txtCod_pro.getText() +"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int valor = rs.getInt("Valor");
            int descuento = rs.getInt("valor_Descuento");
            descuento1 = (valor * descuento) / 100;
            total = valor - descuento1;
            total *= Integer.parseInt(txtCantidad.getText());
    }
        return total;
    }
     
     
    public void Iniciar() throws SQLException{
        
        Conexion conn = new Conexion();
        int cantidad;
        Connection con = conn.getConexion();
         producto p = new producto();
        String sql = "SELECT *\n" +
" 				FROM \"Productos\"\n" +
" 				WHERE \"codigo\" = '"+ p.getID() +"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            txtCod_pro.setText(rs.getString("codigo"));
            txt_nombre.setText(rs.getString("Nombre"));
            LocalDate fecha = rs.getDate("Fecha_Ven").toLocalDate();
            txtValor.setText(rs.getString("Valor"));
            txtDescuento.setText(rs.getString("valor_Descuento"));
            cantidad = rs.getInt("cantidad");
            txt_fecha.setText(fecha + "");
        
    }
    }
    @FXML
    private void Volver(ActionEvent event) {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
         
    }
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/comprar.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        Volver.setGraphic(new ImageView(imagenNuevo));  
        Comprar.setGraphic(new ImageView(imagenNuevo1));  
    }
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtCantidad)||evt.equals(txtcod_Cliente)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }
    }

    @FXML
    private void ComprarDescuento(ActionEvent event){
        
    } 
    
}

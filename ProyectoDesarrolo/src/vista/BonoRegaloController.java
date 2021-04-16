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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class BonoRegaloController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private ComboBox<producto> Regalar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtPuntos;
    @FXML
    private Button volver;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button BtnDar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        datos();
        try {
            rellenar();
        } catch (SQLException ex) {
            Logger.getLogger(BonoRegaloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colocarImagenBotones();
    }

    public void setProgramaPrincipal(principal programa) {
        programaPrincipal = programa;
    }

    public void datos() {
        producto p = new producto();

        ObservableList<producto> item = p.getProducto();
        this.Regalar.setItems(item);
    }

    public void rellenar() throws SQLException {
        Cliente c = new Cliente();
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        String sql = "SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p1.\"Telefono\", p1.\"direccion\", p2.\"puntos\"\n"
                + " 				FROM \"clientes\" p2, \"Usuario\" p1\n"
                + " 				WHERE p1.\"ID\" = p2.\"ID\" AND p1.\"ID\" = '" + c.getID() + "'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            txtCodigo.setText(rs.getString("ID"));
            txtNombre.setText(rs.getString("Nombre"));
            txtApellido.setText(rs.getString("Apellido"));
            txtTelefono.setText(rs.getString("Telefono"));
            txtDireccion.setText(rs.getString("direccion"));
            txtPuntos.setText(rs.getInt("puntos") + "");
        }
    }

    @FXML
    private void Volver(ActionEvent event) {
        programaPrincipal.ConsultarClienteAdm();
    }

    @FXML
    private void Regalar(ActionEvent event) throws SQLException, ParseException {
        if(!txtCantidad.getText().equals("")){
        String codigo = "";
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        String sql = "SELECT \"codigo\"\n"
                + " 				FROM \"Productos\"\n"
                + " 				WHERE \"Nombre\" = '" + this.Regalar.getValue() + "'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            codigo = rs.getString("codigo");

        }
        int cantidad = restarCantidad(codigo);
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
                String SQL = "INSERT INTO PUBLIC.\"ventas\"(\"cod_cliente\", \"cod_producto\", \"fecha_compra\", \"valor\", \"cantidad\", \"estado\")\n" +
"                 				VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1= con.prepareStatement(SQL);
                 
        ps1.setString(1, txtCodigo.getText().trim());
        ps1.setString(2, codigo.trim());
        ps1.setDate(3, fechaDate);
        ps1.setInt(4, traerTotal(codigo));
        ps1.setInt(5, Integer.parseInt(txtCantidad.getText()));
        ps1.setString(6, "entregado");
        ps1.executeUpdate();
        cantidadTotal(codigo);
        JOptionPane.showMessageDialog(null, "Se le ha dado exitosamente un bono de regalo a " + txtNombre.getText());
            }
            
        }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar los datos");
        }
    }

    public int restarCantidad(String ID) throws SQLException {
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        int cantidad = 0;
        String sql = "SELECT \"cantidad\"\n" +
" 				FROM \"Productos\"\n" +
" 				WHERE \"codigo\" = '" + ID +"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
       
        while (rs.next()) {
            cantidad = rs.getInt("cantidad");
    }
     return cantidad;
    }
    public int traerTotal(String ID) throws SQLException{
        int descuento1 = 0;
        int total = 0;
        Conexion conn = new Conexion();
        System.out.println("Llegue aqui");
        Connection con = conn.getConexion();
        String sql = "SELECT \"Valor\", \"valor_Descuento\"\n" +
" 				FROM \"Productos\"\n" +
" 				WHERE \"codigo\" = '" + ID +"'";
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
    
    public void cantidadTotal(String ID) throws SQLException{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        int resta = restarCantidad(ID) - (Integer.parseInt(txtCantidad.getText()));
        String sql = "UPDATE \"Productos\"\n" +
" 				SET \"cantidad\" = ?\n" +
" 				WHERE \"codigo\" = '" + ID + "'";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1,resta);
         ps.executeUpdate();
         restarPuntos();
    }
    
    public void restarPuntos() throws SQLException{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        int resta = Integer.parseInt(txtPuntos.getText()) - 100;
        String sql = "UPDATE \"clientes\"\n" +
" 				SET \"puntos\" = ?\n" +
" 				WHERE \"ID\" = '" + txtCodigo.getText().trim() +"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1,resta);
         ps.executeUpdate();
    }
public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/comprar.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 15,15,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 15,15,false,true);
        volver.setGraphic(new ImageView(imagenNuevo));  
        BtnDar.setGraphic(new ImageView(imagenNuevo1));  
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtCantidad)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }
    }
}

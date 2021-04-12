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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class AgregarProductosController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private DatePicker txtFechaVen;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField TxtCantidad;
    @FXML
    private Button btnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colocarImagenBotones();
    }    
     public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    
    @FXML
    public void añadir() throws ParseException{
        String SQL = "INSERT INTO PUBLIC.\"Productos\"(\n" +
"             \"codigo\", \"Nombre\",\"Fecha_Ven\", \"Valor\", \"cantidad\")\n" +
"				 VALUES (?,?,?,?,?);";
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        java.util.Date nfecha = formato.parse(txtFechaVen.getValue().toString());
        fecha = new java.sql.Date(nfecha.getTime());
        int valor;
        valor = Integer.parseInt(txtValor.getText().trim());
        int cantidad = Integer.parseInt(TxtCantidad.getText().trim());
        if(txtCodigo.getText().equals("")||txtNombre.getText().equals("")||txtFechaVen.getValue().equals("")||txtValor.getText().equals("")||TxtCantidad.getText().equals("")){
        JOptionPane.showMessageDialog(null, "No puede tener datos nulos");
        }else{
            
                String[] opcion = {"Si","No"};
        String n = (String)JOptionPane.showInputDialog(null,"Desea registrar el Producto","Aviso registro", JOptionPane.ERROR_MESSAGE, null, opcion, opcion[0]);
        try{
        if(n.equals("No")){
            JOptionPane.showMessageDialog(null, "Producto no registrado");
        }else{
            
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setString(1, txtCodigo.getText().trim());
        ps.setString(2, txtNombre.getText().trim());
        ps.setDate(3, fecha);
        ps.setInt(4, valor);
        ps.setInt(5, cantidad);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Producto registrado");
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
                }
    }
    @FXML
    public void regresarAPrincipal()
    {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/caja.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        btnVolver.setGraphic(new ImageView(imagenNuevo));
        btnAgregar.setGraphic(new ImageView(imagenNuevo1));
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtCodigo)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }else if(evt.equals(TxtCantidad)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
       
    }
    }
}

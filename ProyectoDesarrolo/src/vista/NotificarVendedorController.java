/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class NotificarVendedorController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtContraseña;
    @FXML
    private Button BtnNotificar;
    @FXML
    private Button BtnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mandarDatos();
        colocarImagenBotones();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    public void volver(){
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    
    @FXML
    public void notificar(){
        if(!txtTelefono.getText().equals("")||!txtDireccion.getText().equals("")||!txtContraseña.getText().equals("")){
        Cliente u = new Cliente();
        String mensaje = "Cambiar  telefono a " + txtTelefono.getText() +"\n"
                + "Cambiar direccion a " + txtDireccion.getText() + " " +"\n"
                + "Cambiar contrseña a " + txtContraseña.getText()+ " ";
        String SQL = "UPDATE \"clientes\"\n" +
"				 SET \"estado\" = ? , \"mensajeA\" = ?\n" +
"				 WHERE \"ID\" = '" + u.getID() +"'";
        try{
          
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setString(1,"SI");
        ps.setString(2, mensaje);
       
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos notificados");
       // ResultSet rs= ps.executeQuery();
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar los datos");
        }
    }
    public void mandarDatos(){
        Cliente u = new Cliente();
        txtID.setText(u.getID());
        txtNombre.setText(u.getNombre());
        txtApellido.setText(u.getApellido());
    }
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/actualizar.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        BtnVolver.setGraphic(new ImageView(imagenNuevo));  
        BtnNotificar.setGraphic(new ImageView(imagenNuevo1));  
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtID)||evt.equals(txtTelefono)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }
    }
}

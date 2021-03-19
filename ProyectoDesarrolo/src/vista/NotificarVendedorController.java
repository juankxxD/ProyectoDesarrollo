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
    }
    public void mandarDatos(){
        Cliente u = new Cliente();
        txtID.setText(u.getID());
        txtNombre.setText(u.getNombre());
        txtApellido.setText(u.getApellido());
    }
    
}

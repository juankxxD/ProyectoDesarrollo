/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class ActualizarController implements Initializable {
    
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
    private TextArea txtMensaje;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button volver;
    Cliente u = new Cliente();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciar();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    @FXML
    public void volver(){
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    public void iniciar(){
        String SQL = "SELECT p1.\"ID\", p1.\"Nombre\", p1.\"Apellido\", p2.\"mensajeA\"\n" +
"	 FROM \"Usuario\" p1, \"clientes\" p2\n" +
"	 WHERE p1.\"ID\" = p2.\"ID\" AND p2.\"ID\"='" + u.getID() +"'";
        try{
          
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        PreparedStatement ps= con.prepareStatement(SQL);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
              txtID.setText(rs.getString("ID"));
              txtNombre.setText(rs.getString("Nombre"));
             txtApellido.setText(rs.getString("Apellido"));
             txtMensaje.setText(rs.getString("mensajeA"));
             
        }
        } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
   }
    }
    public void actualizar(){
        
        String SQL = "UPDATE \"Usuario\"\n" +
"				 SET \"direccion\" = ?, \"Contraseña\" = ? , \"Telefono\" = ?\n" +
"				 WHERE \"ID\" = '" + u.getID() +"'";
        try{
          
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setString(1,txtDireccion.getText().trim());
        ps.setString(2, txtContraseña.getText().trim());
        ps.setString(3, txtTelefono.getText().trim());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos Actualizados");
        CambiarEstado();
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
        
    }
    public void CambiarEstado(){
            String SQL = "UPDATE \"clientes\"\n" +
"				 SET \"estado\" = ? , \"mensajeA\" = ?\n" +
"				 WHERE \"ID\" = '" + u.getID() +"'";
        try{
          
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setString(1,"NO");
        ps.setString(2, "");
       
        ps.executeUpdate();
       // ResultSet rs= ps.executeQuery();
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
        }
}

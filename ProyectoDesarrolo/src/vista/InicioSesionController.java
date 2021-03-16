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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class InicioSesionController implements Initializable {

    private principal programaPrincipal;
    private Conexion BD = new Conexion();
    @FXML
    private Label Fuente;
    @FXML
    private TextField TxtDocumento;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Button BtnIniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    
    public void IniciarSesion(String Tipo){
        programaPrincipal.AbrirTerceraVentana(Tipo);
    }
    public void entrar(){
        try{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(" SELECT \"id_T\"\n" +
"             FROM \"Usuario_tipo\"\n" +
"             WHERE \"id_U\" =\n" +
"             (SELECT \"ID\"\n" +
"             FROM \"Usuario\"\n" +
"             WHERE \"ID\" = '" + TxtDocumento.getText().trim()  + "' AND \"Contraseña\" = '" + txtContraseña.getText().trim() + "');");
         ResultSet rs= ps.executeQuery();
         if(rs.next()){
             String tipo_nivel = rs.getString("id_T");
             Usuarios usuario = new Usuarios();
             usuario.guardarTipo(tipo_nivel);
             IniciarSesion(tipo_nivel);
         }else{
             JOptionPane.showMessageDialog(null, "Datos incorrectos");
             TxtDocumento.setText("");
             txtContraseña.setText("");
         }
         } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
   }
    }
    
    
}

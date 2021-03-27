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
import javafx.event.ActionEvent;
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
public class ComisionController implements Initializable {

     private principal programaPrincipal;
    @FXML
    private TextField txtCodig;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtComision;
    @FXML
    private Button btnCambiar;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnDatos;

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
    public void volver(){
        programaPrincipal.Vendedores();
    }
    

    @FXML
    private void Cambiar(ActionEvent event) {
        String SQL = "UPDATE \"Vendedores\"\n" +
"				SET \"Comision\" = ?\n" +
"				WHERE \"ID\" = '"+ txtCodig.getText() +"'";
        try{
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setInt(1,Integer.parseInt(txtComision.getText()));
       
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "El valor de comision ha sido cambiado exitosamente");
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
        
    }

    @FXML
    private void TraerDatos(ActionEvent event) throws SQLException {
        String SQL = "SELECT p2.\"Nombre\", p2.\"Apellido\", P1.\"Comision\"\n" +
"				FROM \"Vendedores\" P1, \"Usuario\" p2\n" +
"				WHERE P1.\"ID\" = p2.\"ID\" AND P1.\"ID\"='" + txtCodig.getText() +"'";
        Conexion conn = new Conexion();
        Connection con = Conexion.getConexion();
        PreparedStatement ps= con.prepareStatement(SQL);
        ResultSet rs= ps.executeQuery();
         while(rs.next()){
                 txtNombre.setText(rs.getString("Nombre"));
                 txtApellido.setText(rs.getString("Apellido"));
                 txtComision.setText(rs.getString("Comision"));
         }
      txtNombre.setDisable(true);
      txtCodig.setDisable(true);   
      txtApellido.setDisable(true);   
      txtComision.setDisable(false);
      txtComision.setEditable(true);
    }
    
}

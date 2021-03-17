/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.sun.prism.paint.Color;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class RegistrarController implements Initializable {

     private principal programaPrincipal;
    @FXML
    private Button BtonRegistrar;
    @FXML
    private Button BtonVolver;
    @FXML
    private TextField TxtTelefono;
    @FXML
    private TextField TxtNombreC;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtPuntos;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtContraseña;

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
    public void Registrar() throws SQLException{
        txtPuntos.setText("0");
        if(txtDocumento.getText().equals("")||TxtNombreC.getText().equals("")||txtApellido.getText().equals("")||TxtTelefono.getText().equals("")||txtDireccion.getText().equals("")||txtPuntos.getText().equals("")||txtContraseña.getText().equals("")){
        JOptionPane.showMessageDialog(null, "No puede tener datos nulos");
        }else{
        Usuarios cliente = new Usuarios();
        cliente.registrarCliente(txtDocumento.getText(), TxtNombreC.getText(), txtApellido.getText(), TxtTelefono.getText(), txtDireccion.getText(), txtPuntos.getText(), txtContraseña.getText());
        }
    }
    
    public void regresarAPrincipal()
    {
        programaPrincipal.initRootLayout();
    }
    
    }


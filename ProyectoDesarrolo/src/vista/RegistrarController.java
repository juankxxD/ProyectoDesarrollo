/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class RegistrarController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Registrar(){
        txtDocumento.getText();
        TxtNombre.getText();
        txtApellido.getText();
        TxtTelefono.getText();
        txtDireccion.getText();
        txtContraseña.getText();
        txtPuntos.getText();
        
        
    }
    
    public String validacionContra(){
        String mensaje = "";
        if(txtDocumento.getText().equals(null)){
            
        }
        return mensaje;
    }
}

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class MenuController implements Initializable {

    @FXML
    private PasswordField TxtContraseña;
    @FXML
    private Button BtnRegistrarse;
    @FXML
    private Button BtnIniciar;
    @FXML
    private Label labelContraseña;
    @FXML
    private TextField TxtUsuario;
    @FXML
    private Label LabelUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class VisitaController implements Initializable {

    @FXML
    private TextField txtCodig;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtSector;
    @FXML
    private TextArea txtComentario;
    @FXML
    private TextField txtFecha_visita;
    @FXML
    private Button btnDatos;
    @FXML
    private Button Aceptar;
    @FXML
    private Button Volver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TraerDatos(ActionEvent event) {
    }

    @FXML
    private void Aceptar(ActionEvent event) {
    }

    @FXML
    private void Volver(ActionEvent event) {
    }
    
}

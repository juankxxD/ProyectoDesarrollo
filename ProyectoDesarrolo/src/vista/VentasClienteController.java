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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class VentasClienteController implements Initializable {

    @FXML
    private TableView<?> Tabla;
    @FXML
    private TableColumn<?, ?> ProCod;
    @FXML
    private TableColumn<?, ?> ProNom;
    @FXML
    private TableColumn<?, ?> ProFec;
    @FXML
    private TableColumn<?, ?> columCantidad;
    @FXML
    private TableColumn<?, ?> ProDes;
    @FXML
    private TableColumn<?, ?> ProVa;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button Eliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

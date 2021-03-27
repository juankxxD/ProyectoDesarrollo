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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class TotalVentasController implements Initializable {

    @FXML
    private TableView<?> Tabla;
    @FXML
    private TableColumn<?, ?> columcod;
    @FXML
    private TableColumn<?, ?> columNombre;
    @FXML
    private TableColumn<?, ?> columProducto;
    @FXML
    private TableColumn<?, ?> columcantidad;
    @FXML
    private TableColumn<?, ?> columTotal;
    @FXML
    private Label labelInformacion;
    @FXML
    private Label labelTotal;
    @FXML
    private TextField txtTotalVentas;
    @FXML
    private Button btnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TotalVentas(ActionEvent event) {
    }

    @FXML
    private void Volver(ActionEvent event) {
    }
    
}

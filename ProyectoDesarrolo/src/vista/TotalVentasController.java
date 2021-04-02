/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class TotalVentasController implements Initializable {
 
    
    private principal programaPrincipal;
    @FXML
    private TableView<Ventas> Tabla;
    @FXML
    private TableColumn<Ventas, String> columid;
    @FXML
    private TableColumn<Ventas, String> columcod;
    @FXML
    private TableColumn<Ventas, String> columNombre;
    @FXML
    private TableColumn<Ventas, String> columProducto;
    @FXML
    private TableColumn<Ventas, Integer> columcantidad;
    @FXML
    private TableColumn<Ventas, Integer> columTotal;
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
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    @FXML
    private void TotalVentas(ActionEvent event) {
    }

    @FXML
    private void Volver(ActionEvent event) {
    }
    public void iniciar(){
     this.columid.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcod.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columcantidad.setCellValueFactory(new PropertyValueFactory("valor"));
       this.columTotal.setCellValueFactory(new PropertyValueFactory("cantidad"));
        Ventas v = new Ventas();
        ObservableList<Ventas> item = v.getTotalVentas();
        this.Tabla.setItems(item);  
    }
}


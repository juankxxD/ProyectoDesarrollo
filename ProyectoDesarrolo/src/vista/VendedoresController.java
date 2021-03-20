/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class VendedoresController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private TableView<Vendedor> TablaClient;
    @FXML
    private TableColumn<Vendedor, String> NombreV;
    @FXML
    private TableColumn<Vendedor, String> NombreC;
    @FXML
    private TableView<Vendedor> Tablacantidad;
    @FXML
    private TableColumn<Vendedor, String> ColumNombrev;
    @FXML
    private TableColumn<Vendedor, Integer> ColumCantidad;
    @FXML
    private Button BtnNumeroClientes;
    @FXML
    private Button BtnClientes;

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
    
    public void regresarAPrincipal()
    {
        Usuarios u = new Usuarios();
        System.out.println(u.getTipoUsuario());
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    public void CantidadClientes(){
        BtnNumeroClientes.setVisible(false);
        BtnClientes.setVisible(false);
        Tablacantidad.setVisible(true);
        this.ColumNombrev.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumCantidad.setCellValueFactory(new PropertyValueFactory("NumeroClientes"));
 
       Vendedor v = new Vendedor();
        ObservableList<Vendedor> item = v.getVendedor();
        this.Tablacantidad.setItems(item);
    }
}

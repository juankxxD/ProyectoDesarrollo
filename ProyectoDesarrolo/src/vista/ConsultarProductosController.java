/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class ConsultarProductosController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private TableColumn<producto, String> ProCod;
    @FXML
    private TableColumn<producto, String> ProNom;
    @FXML
    private TableColumn<producto, LocalDate> ProFec;
    @FXML
    private TableColumn<producto, Integer> ProVa;
    @FXML
    private TableColumn<producto, Integer> ProDes;
    @FXML
    private TableColumn<producto, Integer> columCantidad;
    @FXML
    private Button btnVolver;
    @FXML
    private SplitMenuButton Opciones;
    @FXML
    private MenuItem btnAumentarCan;
    @FXML
    private MenuItem btnCambiDes;
    @FXML
    private Button Cambiarbtn;
    @FXML
    private Label labelCodigo;
    @FXML
    private TextField txtCodigoPro;
    @FXML
    private Label labelCantidad;
    @FXML
    private TextField txtCantidadPro;
    @FXML
    private Button Aumentar;
    @FXML
    private TableView<producto> Tabla;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            consultarProductos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }

    @FXML
    private void cambiarCantidad(ActionEvent event) throws SQLException {
        txtCodigoPro.setVisible(true);
        txtCantidadPro.setVisible(true);    
        labelCodigo.setVisible(true);
        labelCantidad.setVisible(true);
        Aumentar.setVisible(true);
        Cambiarbtn.setVisible(false);  
        consultarProductos();
    }

    @FXML
    private void cambiarDescuento(ActionEvent event) throws SQLException {
         txtCodigoPro.setVisible(true);
         labelCodigo.setVisible(true);
        Opciones.setVisible(true);               
        Cambiarbtn.setVisible(true);
        Aumentar.setVisible(false);
        labelCantidad.setVisible(false);
        txtCantidadPro.setVisible(false);    
        consultarProductos();
    }

    @FXML
    private void aumentar(ActionEvent event) {
        producto p = new producto();
        p.seleccion(txtCodigoPro.getText(),txtCantidadPro.getText());   
    }

    @FXML
    private void Cambiar(ActionEvent event) {
        producto p = new producto();
        p.CambiarDescuento(txtCodigoPro.getText());
    }
    
    public void consultarProductos() throws SQLException{

        Usuarios u = new Usuarios();
            
        if(u.getTipoUsuario().equals("1")){
        Opciones.setVisible(true);              
        
        }
     
        this.ProCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.ProNom.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ProFec.setCellValueFactory(new PropertyValueFactory("FechaVencimiento"));
        this.ProVa.setCellValueFactory(new PropertyValueFactory("valor"));
        this.ProDes.setCellValueFactory(new PropertyValueFactory("ValorDescuento"));
       this.columCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        producto p = new producto();
        ObservableList<producto> item = p.getProducto();
        this.Tabla.setItems(item);
        }  
    @FXML
    public void regresarAPrincipal()
    {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
}

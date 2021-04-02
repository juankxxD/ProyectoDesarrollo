/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
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
public class ProductosVendedorController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private Button Volver;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnVentas;
    @FXML
    private TableView<producto> Tabla;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Label labelCodigo;
    @FXML
    private Button btnvolver;
    @FXML
    private TableView<Ventas> Tablaventas;
    @FXML
    private TableColumn<Ventas, String> columcodCliente;
    @FXML
    private TableColumn<Ventas,String> columcodProducto;
    @FXML
    private TableColumn<Ventas, LocalDate> ColumFecha_Entrega;
    @FXML
    private TableColumn<Ventas, Integer> columTotal;
    @FXML
    private ButtonBar Botones;
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
    private Button btnConsultarProducto;
    

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
    private void Volver(ActionEvent event) {
         Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }

    @FXML
    private void ConsultarProductos(ActionEvent event) {
        Botones.setVisible(false);
        Tabla.setVisible(true);
        btnvolver.setVisible(true);
        btnConsultarProducto.setVisible(true);
        txtCodigo.setVisible(true);
        labelCodigo.setVisible(true);
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
    private void Ventas(ActionEvent event) {
        Botones.setVisible(false);
        Tablaventas.setVisible(true);    
        btnvolver.setVisible(true);
        btnConsultarProducto.setVisible(false);
        this.columcodCliente.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columcodProducto.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.ColumFecha_Entrega.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        ObservableList<Ventas> item = v.getVentas() ;
        this.Tablaventas.setItems(item);
    }

    @FXML
    private void Volvermenu(ActionEvent event) {
        Botones.setVisible(true);
        Tabla.setVisible(false);
        Tablaventas.setVisible(false);
        txtCodigo.setVisible(false);
        labelCodigo.setVisible(false);
        btnvolver.setVisible(false);
        btnConsultarProducto.setVisible(false);
    }

    @FXML
    private void Consultar(ActionEvent event) {
        producto p = new producto();
        p.setID(txtCodigo.getText());
        programaPrincipal.ComprarVendedor();
        
    }
    
}

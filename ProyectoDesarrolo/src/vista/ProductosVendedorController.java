/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Conexion;

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
    private TableColumn<Ventas, String> columcodProducto;
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
    @FXML
    private SplitMenuButton opciones;
    @FXML
    private MenuItem Ventasc;
    @FXML
    private MenuItem Devolciones;
    @FXML
    private Button Devolution;
    @FXML
    private TableColumn<Ventas, String> ColumVenta;
    @FXML
    private MenuItem MenuEliminar;
    @FXML
    private Button Eliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colocarImagenBotones();
    }

    public void setProgramaPrincipal(principal programa) {
        programaPrincipal = programa;
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
        opciones.setVisible(true);
        txtCodigo.setVisible(false);
        Devolution.setVisible(false);
        Eliminar.setVisible(false);
        Botones.setVisible(false);
        Tablaventas.setVisible(true);
        btnvolver.setVisible(true);
        btnConsultarProducto.setVisible(false);
        this.ColumVenta.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcodCliente.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columcodProducto.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.ColumFecha_Entrega.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        ObservableList<Ventas> item = v.getVentas();
        this.Tablaventas.setItems(item);
    }

    @FXML
    private void Volvermenu(ActionEvent event) {
        opciones.setVisible(false);
        txtCodigo.setVisible(false);
        Eliminar.setVisible(false);
        Devolution.setVisible(false);
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

    public void colocarImagenBotones() {
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/caja.png");
        URL linkNuevo2 = getClass().getResource("/Imagenes/consultar.png");
        URL linkNuevo3 = getClass().getResource("/Imagenes/ventas.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24, 24, false, true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24, 24, false, true);
        Image imagenNuevo2 = new Image(linkNuevo2.toString(), 24, 24, false, true);
        Image imagenNuevo3 = new Image(linkNuevo3.toString(), 24, 24, false, true);
        btnvolver.setGraphic(new ImageView(imagenNuevo));
        Volver.setGraphic(new ImageView(imagenNuevo));
        btnProductos.setGraphic(new ImageView(imagenNuevo1));
        btnConsultarProducto.setGraphic(new ImageView(imagenNuevo2));
        btnVentas.setGraphic(new ImageView(imagenNuevo3));
    }

    public void jeje(KeyEvent a) {
        Object evt = a.getSource();
        if (evt.equals(txtCodigo)) {
            if (!Character.isDigit(a.getCharacter().charAt(0))) {
                a.consume();
            }
        }

    }

    @FXML
    private void Devoluciones(ActionEvent event) {
        opciones.setVisible(true);
        Eliminar.setVisible(false);
        txtCodigo.setVisible(true);
        Devolution.setVisible(true);
        Botones.setVisible(false);
        Tablaventas.setVisible(true);
        btnvolver.setVisible(true);
        btnConsultarProducto.setVisible(false);
        this.ColumVenta.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcodCliente.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columcodProducto.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.ColumFecha_Entrega.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        ObservableList<Ventas> item = v.Devolucion();
        this.Tablaventas.setItems(item);
    }

    @FXML
    private void DevolucionFun(ActionEvent event) {
        
    }

    @FXML
    private void EliminarProducto(ActionEvent event) {
        opciones.setVisible(true);
        txtCodigo.setVisible(true);
        Eliminar.setVisible(true);
        Devolution.setVisible(false);
        Botones.setVisible(false);
        Tablaventas.setVisible(true);
        btnvolver.setVisible(true);
        btnConsultarProducto.setVisible(false);
        this.ColumVenta.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcodCliente.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columcodProducto.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.ColumFecha_Entrega.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        ObservableList<Ventas> item = v.getVentas();
        this.Tablaventas.setItems(item);
    }

    @FXML
    private void Eliminarfun(ActionEvent event) {
    }
    
}

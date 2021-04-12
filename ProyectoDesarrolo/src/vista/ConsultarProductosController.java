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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

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
        colocarImagenBotones();
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
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/Aumentar.png");
        URL linkNuevo2 = getClass().getResource("/Imagenes/etiqueta.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        Image imagenNuevo2 = new Image(linkNuevo2.toString(), 24,24,false,true);
        btnVolver.setGraphic(new ImageView(imagenNuevo));  
        btnAumentarCan.setGraphic(new ImageView(imagenNuevo1)); 
        btnCambiDes.setGraphic(new ImageView(imagenNuevo2)); 
        Aumentar.setGraphic(new ImageView(imagenNuevo1));
        Cambiarbtn.setGraphic(new ImageView(imagenNuevo2)); 
        
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtCantidadPro)||evt.equals(txtCodigoPro)||evt.equals(btnCambiDes)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }
    }
}

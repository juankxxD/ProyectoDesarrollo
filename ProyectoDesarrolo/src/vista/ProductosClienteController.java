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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class ProductosClienteController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private Button btnVolver;
    @FXML
    private TableView<producto> Tabla;
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
    private Button btnConsultar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Label labelCodigo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicio();
        colocarImagenBotones();
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
    private void Consultar(ActionEvent event) {
        if(!txtCodigo.getText().equals("")){
        producto p = new producto();
        p.setID(txtCodigo.getText());
        programaPrincipal.ComprarCliente();
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar datos");
        }
    }
    public void inicio(){
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
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/consultar.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        btnVolver.setGraphic(new ImageView(imagenNuevo));  
        btnConsultar.setGraphic(new ImageView(imagenNuevo1));  
    }
    public void jeje(KeyEvent a) {
        Object evt = a.getSource();
        if (evt.equals(txtCodigo)) {
            if (!Character.isDigit(a.getCharacter().charAt(0))) {
                a.consume();
            }
        }
    }

}

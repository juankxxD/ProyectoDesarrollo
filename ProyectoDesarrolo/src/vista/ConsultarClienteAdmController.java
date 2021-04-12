/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class ConsultarClienteAdmController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private principal programaPrincipal;
    @FXML
    private TableView<Cliente> Tabla;
    @FXML
    private TableColumn<Cliente, String> ColumDocu;
    @FXML
    private TableColumn<Cliente, String> ColumNombre;
    @FXML
    private TableColumn<Cliente, String> ColumApe;
    @FXML
    private TableColumn<Cliente, String> ColumTel;
    @FXML
    private TableColumn<Cliente, String> ColumDire;
    @FXML
    private TableColumn<Cliente, Integer> ColumPunt;
    @FXML
    private Button salir;
    @FXML
    private SplitMenuButton btnOpciones1;
    @FXML
    private MenuItem btnTodos;
    @FXML
    private MenuItem btnEliminarU;
    @FXML
    private Label LabelCodigo;
    @FXML
    private TextField txtConsultar;
    @FXML
    private Button btnEliminar;
    @FXML
    private MenuItem BonoRegalo;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colocarImagenBotones();
    }

    public void setProgramaPrincipal(principal programa) {
        programaPrincipal = programa;
    }

    @FXML
    private void consultarUsuarios(ActionEvent event) {
        LabelCodigo.setVisible(false);
        txtConsultar.setVisible(false);
        btnEliminar.setVisible(false);
        btnOpciones1.setVisible(true);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("IDC"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("ApellidoC"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
        this.ColumPunt.setCellValueFactory(new PropertyValueFactory("puntosC"));

        Cliente c = new Cliente();
        ObservableList<Cliente> item = c.getCliente();
        this.Tabla.setItems(item);
    }

    @FXML
    private void EliminarUsuarios(ActionEvent event) {
        LabelCodigo.setVisible(true);
        txtConsultar.setVisible(true);
        btnEliminar.setVisible(true);
        btnOpciones1.setVisible(true);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("IDC"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("ApellidoC"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
        this.ColumPunt.setCellValueFactory(new PropertyValueFactory("puntosC"));

        Cliente c = new Cliente();
        ObservableList<Cliente> item = c.getEliminar();
        this.Tabla.setItems(item);
    }

    @FXML
    private void Eliminar(ActionEvent event) throws SQLException {
        Cliente c = new Cliente();
        c.Eliminar(txtConsultar.getText());
    }

    @FXML
    private void salir(ActionEvent event) {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }

    @FXML
    private void BonoRegalo1(ActionEvent event) {
        btnEliminar.setVisible(false);
        txtConsultar.setVisible(false);
        LabelCodigo.setVisible(false);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("IDC"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("ApellidoC"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
        this.ColumPunt.setCellValueFactory(new PropertyValueFactory("puntosC"));

        Cliente c = new Cliente();
        ObservableList<Cliente> item = c.getPuntos1();
        this.Tabla.setItems(item);
    }

    @FXML
    private void Seleccionar() {
        Cliente c = this.Tabla.getSelectionModel().getSelectedItem();
        if (c == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe Seleccionar");
            alert.showAndWait();

        } else {
            System.out.println(c.getIDC());
            c.setID(c.getIDC());
            programaPrincipal.BonoRegalo();
        }
    }
public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/grupo.png");
        URL linkNuevo2 = getClass().getResource("/Imagenes/eliminarUsuario.png");
        URL linkNuevo3 = getClass().getResource("/Imagenes/regalo.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        Image imagenNuevo2 = new Image(linkNuevo2.toString(), 24,24,false,true);
        Image imagenNuevo3 = new Image(linkNuevo3.toString(), 24,24,false,true);
        salir.setGraphic(new ImageView(imagenNuevo));  
        btnTodos.setGraphic(new ImageView(imagenNuevo1)); 
        btnEliminarU.setGraphic(new ImageView(imagenNuevo2)); 
        btnEliminar.setGraphic(new ImageView(imagenNuevo2)); 
        BonoRegalo.setGraphic(new ImageView(imagenNuevo3));
        
        
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtConsultar)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }
    }
}

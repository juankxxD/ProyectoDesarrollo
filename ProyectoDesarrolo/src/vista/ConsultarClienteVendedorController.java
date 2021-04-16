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

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class ConsultarClienteVendedorController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private Button Volver;
    @FXML
    private TableView<Cliente> Tabla;
    @FXML
    private TableColumn<Cliente,String> ColumDocu;
    @FXML
    private TableColumn<Cliente,String> ColumNombre;
    @FXML
    private TableColumn<Cliente,String> ColumApe;
    @FXML
    private TableColumn<Cliente,String> ColumTel;
    @FXML
    private TableColumn<Cliente,String> ColumDire;
    @FXML
    private TableColumn<Cliente,String> ColumPunt;
    @FXML
    private Label LabelCodigo;
    @FXML
    private TextField txtConsultar;
    @FXML
    private SplitMenuButton SplitBtn;
    @FXML
    private MenuItem ClientesDeyo;
    @FXML
    private MenuItem TodosClientes;
    @FXML
    private MenuItem ActualizarClientes;
    @FXML
    private Button btnConsultarCliente;

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
    private void consultarDeVendedorRegistrados(ActionEvent event) {
        
         btnConsultarCliente.setVisible(false);
         LabelCodigo.setVisible(false);
         txtConsultar.setVisible(false);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("IDC"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("ApellidoC"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
       this.ColumPunt.setCellValueFactory(new PropertyValueFactory("puntosC"));
       Cliente c = new Cliente();
        ObservableList<Cliente> item = c.getConsultar();
        this.Tabla.setItems(item);
    }

    @FXML
    private void consultarUsuarios(ActionEvent event) {
        btnConsultarCliente.setVisible(false);
         LabelCodigo.setVisible(false);
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
        ObservableList<Cliente> item = c.getCliente();
        this.Tabla.setItems(item);
          
    }

    @FXML
    private void ConsultarActualizar(ActionEvent event) {
        btnConsultarCliente.setVisible(true);
        LabelCodigo.setVisible(true);
        txtConsultar.setVisible(true);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("IDC"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("ApellidoC"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
       this.ColumPunt.setCellValueFactory(new PropertyValueFactory("puntosC"));
       Cliente c = new Cliente();
        ObservableList<Cliente> item = c.getActualizar();
        this.Tabla.setItems(item);
      
    }

    @FXML
    private void ActualizarDatos(ActionEvent event) {
        if(!txtConsultar.getText().equals("")){
        Cliente c = new Cliente();
        c.setID(txtConsultar.getText());
        programaPrincipal.Actualizar();
        }else{
            JOptionPane.showMessageDialog(null,"Debe llenar los datos");
        }
    }
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/grupo.png");
        URL linkNuevo2 = getClass().getResource("/Imagenes/customer.png");
        URL linkNuevo3 = getClass().getResource("/Imagenes/opciones.png");
        URL linkNuevo4 = getClass().getResource("/Imagenes/actualizar.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        Image imagenNuevo2 = new Image(linkNuevo2.toString(), 24,24,false,true);
        Image imagenNuevo3 = new Image(linkNuevo3.toString(), 15,15,false,true);
        Image imagenNuevo4 = new Image(linkNuevo4.toString(), 20,20,false,true);
        Volver.setGraphic(new ImageView(imagenNuevo));  
        TodosClientes.setGraphic(new ImageView(imagenNuevo1)); 
        ClientesDeyo.setGraphic(new ImageView(imagenNuevo2)); 
        btnConsultarCliente.setGraphic(new ImageView(imagenNuevo4)); 
        ActualizarClientes.setGraphic(new ImageView(imagenNuevo4));
        SplitBtn.setGraphic(new ImageView(imagenNuevo3));
        
        
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

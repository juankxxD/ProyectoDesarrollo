/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import modelo.Conexion;
import static modelo.Conexion.BD;
import static modelo.Conexion.Usuario;
import static modelo.Conexion.contraseña;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class MenuController implements Initializable {

    private principal programaPrincipal;
    private Conexion BD = new Conexion();
    
    @FXML
    private PasswordField TxtContraseña;
    @FXML
    private Button BtnRegistrarse;
    @FXML
    private Button BtnIniciar;
    @FXML
    private Label labelContraseña;
    @FXML
    private TextField TxtUsuario;
    @FXML
    private Label LabelUsuario;
    @FXML
    private MenuBar MenuBar;
    @FXML
    private Menu MenuUsuarios;
    @FXML
    private Menu MenuAdm;
    @FXML
    private Menu MenuVentas;
    @FXML
    private Menu MenuVendedores;
    Administracion adm = new Administracion();
    @FXML
    private Label Perfil;
    @FXML
    private TableView<Usuarios> Tabla;
    @FXML
    private TableColumn<Usuarios,String> ColumDocu;
    @FXML
    private TableColumn<Usuarios,String> ColumNombre;
    @FXML
    private TableColumn<Usuarios,String> ColumApe;
    @FXML
    private TableColumn<Usuarios,String> ColumTel;
    @FXML
    private TableColumn<Usuarios,String> ColumDire;
    @FXML
    private TableColumn<Usuarios,String> ColumPunt;
    private ObservableList<Conexion> MenuController;
    @FXML
    private MenuItem MenuConUsu;
    @FXML
    private TableView<?> TablaPro;
    @FXML
    private TableColumn<?, ?> ProCod;
    @FXML
    private TableColumn<?, ?> ProNom;
    @FXML
    private TableColumn<?, ?> ProFec;
    @FXML
    private TableColumn<?, ?> ProVa;
    @FXML
    private TableColumn<?, ?> ProDes;
    @FXML
    private Button BtnRegistrar;
    //private TableView<Productos> TablaPro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciar();
        
        
       // IniciarSesion();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    
    public void iniciar(){
        MenuBar.setVisible(false);
        Tabla.setVisible(false);
        TablaPro.setVisible(false);
        Perfil.setVisible(false);
        BtnRegistrar.setVisible(false);
    }
    @FXML
    public void IniciarSesion(){
        TxtContraseña.setVisible(false);
        TxtUsuario.setVisible(false);
        labelContraseña.setVisible(false);
        LabelUsuario.setVisible(false);
        BtnIniciar.setVisible(false);
        BtnRegistrarse.setVisible(false);
        Tabla.setVisible(false);
        
        if(adm.getUsuario().equals("administrador")){
            MenuBar.setVisible(true);
        }else{
            BtnRegistrar.setVisible(true);
        }
    }
    @FXML
    public void consultarUsuarios() throws SQLException{
        Perfil.setVisible(false);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("Apellido"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
       this.ColumPunt.setCellValueFactory(new PropertyValueFactory("Contraseña"));
        
        Usuarios u = new Usuarios();
        ObservableList<Usuarios> item = u.getUsuarios();
        this.Tabla.setItems(item);
        }  
                
    public void abrirOtraVentana()
    {
        programaPrincipal.abrirOtraVentana();
    }       
                  
    
}

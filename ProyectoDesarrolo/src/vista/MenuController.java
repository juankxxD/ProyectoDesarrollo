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
    private String ID;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    //Vendedor V;
    @FXML
    private Label LabelBienvenido;
    
    //private TableView<Productos> TablaPro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    //   iniciar();
        
        
       // IniciarSesion();
    }    
    public void recibirDatos(String ID, String Nombre, String Apellido){
        this.ID=ID;
        this.Nombre=Nombre;
        this.Apellido=Apellido;
        //V.getApellido();
    }
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    
    public void iniciar(){
        Usuarios u = new Usuarios();
        u.setTipoUsuario(programaPrincipal.getTipo());
        //u.setID(programaPrincipal.getID());
        Tabla.setVisible(false);
        MenuBar.setVisible(false);
        TablaPro.setVisible(false);
        Perfil.setVisible(false);
        BtnRegistrar.setVisible(false);
        if(u.getTipoUsuario().equals("1")){
            MenuBar.setVisible(true);
            Perfil.setText("Administrador");
            Perfil.setVisible(true);
            String aver =u.getNombre() + " " + u.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
        }else if(u.getTipoUsuario().equals("2")){
            Perfil.setText("vendedor");
            Perfil.setVisible(true);
            BtnRegistrar.setVisible(true);
            //Vendedor v = new Vendedor(u.getID());
            Vendedor v = new Vendedor();
            //v.completarDatos();
            String aver =v.getNombre() + " " + v.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
        }else{
            Perfil.setText("Cliente");
            Perfil.setVisible(true);
            Cliente c = new Cliente();
            String aver =c.getNombre() + " " + c.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
        }
        
        
        
        
        
    }
    @FXML
    public void consultarUsuarios() throws SQLException{
        LabelBienvenido.setVisible(false);
        Perfil.setVisible(false);
        Tabla.setVisible(true);
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("Apellido"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
       this.ColumPunt.setCellValueFactory(new PropertyValueFactory("puntos"));
        
        Cliente c = new Cliente();
        ObservableList<Cliente> item = c.getCliente();
        this.Tabla.setItems(item);
        }  
                
    @FXML
    public void abrirOtraVentana()
    {
        programaPrincipal.abrirOtraVentana();
    }       
                  
    
}

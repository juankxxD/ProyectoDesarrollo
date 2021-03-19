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
import java.time.LocalDate;
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
import javafx.scene.control.SplitMenuButton;
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
    private TableView<producto> TablaPro;
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
    private Button BtnRegistrar;
    private String ID;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    //Vendedor V;
    @FXML
    private Label LabelBienvenido;
    @FXML
    private Button agregarpro;
    @FXML
    private TableColumn<producto, Integer> columCantidad;
    @FXML
    private MenuItem ConsulPro;
    @FXML
    private Button ActualizarClien;
    @FXML
    private Button ConsultarVendedor;
    @FXML
    private SplitMenuButton SplitBtn;
    @FXML
    private MenuItem ClientesDeyo;
    @FXML
    private MenuItem TodosClientes;
    @FXML
    private MenuItem ActualizarClientes;
    @FXML
    private Label txtCodigo;
    @FXML
    private TextField txtConsultar;
    @FXML
    private Button btnConsultarCliente;
    
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
        ActualizarClien.setVisible(false);
        ConsultarVendedor.setVisible(false);
        SplitBtn.setVisible(false);
        LabelBienvenido.setVisible(true);
        btnConsultarCliente.setVisible(false);
        txtConsultar.setVisible(false);
        txtCodigo.setVisible(false);
        if(u.getTipoUsuario().equals("1")){
            MenuBar.setVisible(true);
            Perfil.setText("Administrador");
            Perfil.setVisible(true);
            String aver =u.getNombre() + " " + u.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
            agregarpro.setVisible(true);
        }else if(u.getTipoUsuario().equals("2")){
            Perfil.setText("vendedor");
            Perfil.setVisible(true);
            BtnRegistrar.setVisible(true);
            Vendedor v = new Vendedor();
            String aver =v.getNombre() + " " + v.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
            agregarpro.setVisible(false);
             ConsultarVendedor.setVisible(true);
        }else{
            Perfil.setText("Cliente");
            Perfil.setVisible(true);
            Cliente c = new Cliente();
            String aver =c.getNombre() + " " + c.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
            agregarpro.setVisible(false);
            ActualizarClien.setVisible(true);
        }
        
    }
    @FXML
    public void autorizarActualizar(){
            programaPrincipal.Notificar();
        }
    
    
    @FXML
    public void consultarUsuarios() throws SQLException{
        LabelBienvenido.setVisible(false);
        Perfil.setVisible(false);
        Tabla.setVisible(true);
        agregarpro.setVisible(false);
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
    public void consultarProductos() throws SQLException{
        LabelBienvenido.setVisible(false);
        Perfil.setVisible(false);
        TablaPro.setVisible(true);
        agregarpro.setVisible(false);
        this.ProCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.ProNom.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ProFec.setCellValueFactory(new PropertyValueFactory("FechaVencimiento"));
        this.ProVa.setCellValueFactory(new PropertyValueFactory("valor"));
        this.ProDes.setCellValueFactory(new PropertyValueFactory("ValorDescuento"));
       this.columCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        
        producto p = new producto();
        ObservableList<producto> item = p.getProducto();
        this.TablaPro.setItems(item);
        }  
    
    @FXML
    public void abrirOtraVentana()
    {
        programaPrincipal.abrirOtraVentana();
    }       
    @FXML
    public void AgregarPro(){
        programaPrincipal.AgregarP();
    }
    @FXML
    public void consultar(){
        Tabla.setVisible(true);
        BtnRegistrar.setVisible(false);
        LabelBienvenido.setVisible(true);
          SplitBtn.setVisible(true);
         ConsultarVendedor.setVisible(false);
        Perfil.setVisible(false);
        btnConsultarCliente.setVisible(true);
        txtConsultar.setVisible(true);
        txtCodigo.setVisible(true);
    }
                  
    @FXML
    public void consultarDeVendedorRegistrados(){
         LabelBienvenido.setVisible(false);
         
         ConsultarVendedor.setVisible(false);
        Perfil.setVisible(false);
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
    public void ConsultarActualizar(){
         LabelBienvenido.setVisible(false);
         
         ConsultarVendedor.setVisible(false);
        Perfil.setVisible(false);
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
}

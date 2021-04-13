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
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SortEvent;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private ObservableList<Conexion> MenuController;
    @FXML
    private MenuItem MenuConUsu;

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
    private MenuItem ConsulPro;
    @FXML
    private Button ActualizarClien;
    @FXML
    private Button ConsultarVendedor;

    private Label txtCodigo;
    @FXML
    private TextField txtConsultar;

    @FXML
    private Button btnConsultarProductosC;
    @FXML
    private Button BtnVolver;
    @FXML
    private Menu CerrarSesion;
    @FXML
    private TextField txtCodigoPro;

    @FXML
    private Button BtnConsultarVende;
    @FXML
    private ButtonBar BtonesVendedores;
    @FXML
    private Button btnProductos;
    @FXML
    private ButtonBar BtonesADM;
    @FXML
    private ButtonBar BtonesCliente;
    @FXML
    private MenuItem Total_Ventas;
    @FXML
    private Button btnConsultarVentas;
    @FXML
    private Button btnVentasCliente;
    @FXML
    private Button btnSECTORES;

    //private TableView<Productos> TablaPro;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //iniciar();
        colocarImagenBotones();
        // IniciarSesion();
    }

    public void recibirDatos(String ID, String Nombre, String Apellido) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        //V.getApellido();
    }

    public void setProgramaPrincipal(principal programa) {
        programaPrincipal = programa;
    }

    public void iniciar() {
        Usuarios u = new Usuarios();
        u.setTipoUsuario(programaPrincipal.getTipo());

        MenuBar.setVisible(false);

        Perfil.setVisible(false);

        LabelBienvenido.setVisible(true);
        txtConsultar.setVisible(false);

        BtnVolver.setVisible(false);
        BtonesCliente.setVisible(false);
        if (u.getTipoUsuario().equals("1")) {
            BtonesADM.setVisible(true);
            MenuBar.setVisible(true);
            Perfil.setText("Administrador");
            Perfil.setVisible(true);
            String aver = u.getNombre() + " " + u.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
            agregarpro.setVisible(true);
            BtnConsultarVende.setVisible(true);
        } else if (u.getTipoUsuario().equals("2")) {
            Perfil.setText("vendedor");
            Perfil.setVisible(true);
            BtonesVendedores.setVisible(true);
            Vendedor v = new Vendedor();
            String aver = v.getNombre() + " " + v.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
            agregarpro.setVisible(false);

        } else {
            Perfil.setText("Cliente");
            Perfil.setVisible(true);
            Cliente c = new Cliente();
            String aver = c.getNombre() + " " + c.getApellido();
            LabelBienvenido.setText("Bienvenido " + aver);
            agregarpro.setVisible(false);
            ActualizarClien.setVisible(true);
            btnConsultarProductosC.setVisible(true);
            BtonesCliente.setVisible(true);
        }

    }

    @FXML
    public void autorizarActualizar() {
        programaPrincipal.Notificar();
    }

    @FXML
    public void volver() {
        iniciar();
    }

    @FXML
    public void abrirOtraVentana() {
        programaPrincipal.abrirOtraVentana();
    }

    @FXML
    public void AgregarPro() {
        programaPrincipal.AgregarP();
    }

    @FXML
    public void consultarClientesadmin() {
        programaPrincipal.ConsultarClienteAdm();
    }

    @FXML
    public void consultar1Productos() {
        programaPrincipal.ProductosCliente();
    }

    @FXML
    public void consultar() {

        programaPrincipal.ConsultarVendedor();
    }

    public void ActualizarDatos() {
        Cliente c = new Cliente();
        c.setID(txtConsultar.getText());
        programaPrincipal.Actualizar();
    }

    @FXML
    private void CerrarSesion(ActionEvent event) {
        Cliente c = new Cliente("", "", "", "", "", 0);
        Vendedor v = new Vendedor("", "", "", "", "", 0, 0, 0);
        Usuarios u = new Usuarios("", "", "", "", "", "");
        u.setTipoUsuario("");
        programaPrincipal.initRootLayout();
    }

    @FXML
    public void vendedores() {
        programaPrincipal.Vendedores();
    }

    @FXML
    private void consultarProductos(ActionEvent event) {
        programaPrincipal.ProductosVendedor();
    }

    @FXML
    public void consultarProductosADM() {
        programaPrincipal.consultarP();
    }

    public void colocarImagenBotones() {
        URL linkNuevo = getClass().getResource("/Imagenes/AgregarUser.png");
        URL AgregarP = getClass().getResource("/Imagenes/caja.png");
        URL consultarP = getClass().getResource("/Imagenes/paquete.png");
        URL consultarV = getClass().getResource("/Imagenes/empleado.png");
        URL consultarC = getClass().getResource("/Imagenes/grupo.png");
        URL conVentas = getClass().getResource("/Imagenes/ventas.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24, 24, false, true);
        Image imagenNuevo1 = new Image(AgregarP.toString(), 24, 24, false, true);
        Image imagenNuevo2 = new Image(consultarP.toString(), 24, 24, false, true);
        Image imagenNuevo3 = new Image(consultarV.toString(), 24, 24, false, true);
        Image imagenNuevo4 = new Image(consultarC.toString(), 24, 24, false, true);
        Image imagenNuevo5 = new Image(conVentas.toString(), 24, 24, false, true);
        BtnRegistrar.setGraphic(new ImageView(imagenNuevo));
        agregarpro.setGraphic(new ImageView(imagenNuevo1));
        ConsulPro.setGraphic(new ImageView(imagenNuevo2));
        btnConsultarProductosC.setGraphic(new ImageView(imagenNuevo2));
        btnProductos.setGraphic(new ImageView(imagenNuevo2));
        BtnConsultarVende.setGraphic(new ImageView(imagenNuevo3));
        MenuConUsu.setGraphic(new ImageView(imagenNuevo4));
        ConsultarVendedor.setGraphic(new ImageView(imagenNuevo4));
        btnConsultarVentas.setGraphic(new ImageView(imagenNuevo5));
    }

    @FXML
    private void TotalVentas(ActionEvent event) {
        programaPrincipal.VentasAdministrador();
    }

    @FXML
    private void ConsultarVentasCliente(ActionEvent event) {
        programaPrincipal.Ventascliente();
    }

    @FXML
    private void sectores(){
    }
}

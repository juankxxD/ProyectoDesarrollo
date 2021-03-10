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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciar();
        
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("Apellido"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
       this.ColumPunt.setCellValueFactory(new PropertyValueFactory("Contraseña"));
        
        Usuarios u = new Usuarios();
        ObservableList<Usuarios> item = u.getUsuarios();
        this.Tabla.setItems(item);
       // IniciarSesion();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    public void iniciar(){
        MenuBar.setVisible(false);
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
        }
    }
    public void consultarUsuarios() throws SQLException{
        String sql = "SELECT P1.\"ID\", P1.\"Nombre\", P1.\"Telefono\", P1.direccion, P1.\"Contraseña\", P1.\"Apellido\" \n" +
"				 FROM public.\"Usuario\" P1;";
        try
    {   
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
     
        System.out.println(sql);
        ps= con.prepareStatement(sql);
        rs= ps.executeQuery();
        MenuController = FXCollections.observableArrayList();
        while(rs.next()){
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));  
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("Apellido"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));
        }  
                
                
                    
    }catch(SQLException ex){
        System.err.println(ex.toString());
    }
        /*
        ResultSet rs = BD.Algo(sql);
        String[] aver = new String[5];
       // aver=BD.getDatos();
        System.out.println(aver[1]);
        Usuarios = FXCollections.observableArrayList();
        
        Usuarios = FXCollections.observableArrayList();
        this.ColumDocu.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));  
        this.ColumApe.setCellValueFactory(new PropertyValueFactory("Apellido"));
        this.ColumTel.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.ColumDire.setCellValueFactory(new PropertyValueFactory("Direccion"));*/
        }
        /*private void AgregarUsuarios(){
            String 
        }*/
    
}

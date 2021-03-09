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
    private TableView<?> Tabla;
    @FXML
    private TableColumn<?, ?> ColumDocu;
    @FXML
    private TableColumn<?, ?> ColumNombre;
    @FXML
    private TableColumn<?, ?> ColumApe;
    @FXML
    private TableColumn<?, ?> ColumTel;
    @FXML
    private TableColumn<?, ?> ColumDire;
    @FXML
    private TableColumn<?, ?> ColumPunt;
    private ObservableList<Conexion> personas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciar();
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
        String sql = "Select * FROM Usuarios";
        String ID = "";
        String Nombre = "";
        
        personas = FXCollections.observableArrayList();
        //this.ColumDocu.setCellValueFactory(new PropertyValueFactory("documento"));
        ResultSet rs = BD.Algo(sql);
        while(rs.next()){
            String ID = rs.getString(Usuario);
            
        }
        
    }
}

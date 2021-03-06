/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class MenuController implements Initializable {

    private principal programaPrincipal;
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
    
}

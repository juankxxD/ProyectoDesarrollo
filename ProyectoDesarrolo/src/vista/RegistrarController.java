/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.sun.prism.paint.Color;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class RegistrarController implements Initializable {

     private principal programaPrincipal;
    @FXML
    private Button BtonRegistrar;
    @FXML
    private Button BtonVolver;
    @FXML
    private TextField TxtTelefono;
    @FXML
    private TextField TxtNombreC;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtPuntos;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colocarImagenBotones();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    public void Registrar() throws SQLException{
        txtPuntos.setText("0");
        if(txtDocumento.getText().equals("")||TxtNombreC.getText().equals("")||txtApellido.getText().equals("")||TxtTelefono.getText().equals("")||txtDireccion.getText().equals("")||txtPuntos.getText().equals("")||txtContraseña.getText().equals("")){
        JOptionPane.showMessageDialog(null, "No puede tener datos nulos");
        }else{
        Usuarios cliente = new Usuarios();
        cliente.registrarCliente(txtDocumento.getText(), TxtNombreC.getText(), txtApellido.getText(), TxtTelefono.getText(), txtDireccion.getText(), txtPuntos.getText(), txtContraseña.getText());
        }
    }
    
    public void regresarAPrincipal()
    {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/AgregarUser.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        BtonVolver.setGraphic(new ImageView(imagenNuevo));
        BtonRegistrar.setGraphic(new ImageView(imagenNuevo1));
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtDocumento)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }else if(evt.equals(TxtTelefono)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
       
    }
    }
    }


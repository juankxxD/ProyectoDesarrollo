/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class VentasClienteController implements Initializable {
     private principal programaPrincipal;
    @FXML
    private TableView<producto> Tabla;
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
    private TableColumn<producto, Integer> columCantidad;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField txtCodigo;
    @FXML 
    private TextField txtFecha;
    @FXML
    private Button Eliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }

    @FXML
    private void ConsultarProductos(ActionEvent event) {
      
        Tabla.setVisible(true);
        txtCodigo.setVisible(true);
       this.ProCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.ProNom.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ProFec.setCellValueFactory(new PropertyValueFactory("FechaVencimiento"));
        this.ProVa.setCellValueFactory(new PropertyValueFactory("valor"));
        this.ProDes.setCellValueFactory(new PropertyValueFactory("ValorDescuento"));
       this.columCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        producto p = new producto();
        ObservableList<producto> item = p.getProducto();
               
    } 
    @FXML
    private void Seleccionar(){
       
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            producto  p;
            p = this.Tabla.getSelectionModel().getSelectedItem();
            if(p == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debe Seleccionar");
                alert.showAndWait();
                
            } else{
                 
                     String codigo = this.ProCod.getText();
                     this.txtCodigo.setText(p.getCodigo());
                  
                     /*if(){
                         
                     }*/
                     
                 
            }
    }
    @FXML
    private void eliminar() throws SQLException{  
        Conexion conn = new Conexion();
            Connection con = conn.getConexion();
        String SQL ="DELETE FROM \"ventas\"\n" +
"WHERE \"cod_cliente\" = '"+txtCodigo.getText() +"' AND \"fecha_compra\" = '"+txtFecha.getText() +"'";
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.executeUpdate();
    }        
}

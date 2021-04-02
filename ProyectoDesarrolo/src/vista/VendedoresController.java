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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class VendedoresController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private TableView<Vendedor> TablaClient;
    @FXML
    private TableColumn<Vendedor, String> NombreV;
    @FXML
    private TableColumn<Vendedor, String> NombreC;
    @FXML
    private TableView<Vendedor> Tablacantidad;
    @FXML
    private TableColumn<Vendedor, String> ColumNombrev;
    @FXML
    private TableColumn<Vendedor, Integer> ColumCantidad;
    @FXML
    private Button BtnNumeroClientes;
    @FXML
    private Button BtnClientes;
    @FXML
    private Button Salir;
    @FXML
    private TableColumn<Vendedor, String> ColumApellido;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtSalario;
    @FXML
    private Label labelNombre;
    @FXML
    private Label LabelSalario;
    @FXML
    private TableColumn<Vendedor, String> ColumID;
    @FXML
    private Button Volverbtn;
    @FXML
    private Button btnCambiarComision;

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
    public void regresarAPrincipal()
    {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    @FXML
    public void CantidadClientes(){
        btnCambiarComision.setVisible(false);
        labelNombre.setVisible(true);
        LabelSalario.setVisible(true);
        txtNombre.setVisible(true);
        txtSalario.setVisible(true);     
        Volverbtn.setVisible(true);
        BtnNumeroClientes.setVisible(false);
        BtnClientes.setVisible(false);
        Tablacantidad.setVisible(true);
        TablaClient.setVisible(false);
        
        this.ColumID.setCellValueFactory(new PropertyValueFactory("IDC"));
        this.ColumNombrev.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.ColumCantidad.setCellValueFactory(new PropertyValueFactory("NumeroClientes"));
 
       Vendedor v = new Vendedor();
        ObservableList<Vendedor> item = v.getVendedor();
        this.Tablacantidad.setItems(item);
    }
    
    @FXML
    public void ClientesRegis(){
        btnCambiarComision.setVisible(false);
        labelNombre.setVisible(false);
        LabelSalario.setVisible(false);
        txtNombre.setVisible(false);
        txtSalario.setVisible(false);
        Volverbtn.setVisible(true);
        BtnNumeroClientes.setVisible(false);
        BtnClientes.setVisible(false);
        Tablacantidad.setVisible(false);
        TablaClient.setVisible(true);
        this.NombreV.setCellValueFactory(new PropertyValueFactory("NombreC"));
        this.NombreC.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        this.ColumApellido.setCellValueFactory(new PropertyValueFactory("ApellidoC"));
       
       Vendedor v = new Vendedor();
        ObservableList<Vendedor> item = v.getregistradosV();
        this.TablaClient.setItems(item);
    }
    
    @FXML
    private void Seleccionar(){
       
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            Vendedor  v;
            v = this.Tablacantidad.getSelectionModel().getSelectedItem();
            if(v == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debe Seleccionar");
                alert.showAndWait();
                
            } else{
                 
                     String nombre = this.ColumNombrev.getText();
                     String ID = this.ColumID.getText();
                     int Salario = completar(v.getIDC());
                     String salario =  Salario + "";

                     this.txtNombre.setText(v.getNombreC());
                     this.txtSalario.setText(salario);
                     /*if(){
                         
                     }*/
                     
                 
            }
    }
    public int completar(String ID){
        int Salario = 0;
        try{
            //System.out.println(ID);
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
        String SQL = "SELECT P1.\"ID\", p8.Clientes_Registrados*P1.\"Comision\" AS salario\n" +
"				FROM \"Vendedores\" P1, (SELECT P7.\"ID\", COUNT (\"ID_C\") AS Clientes_Registrados\n" +
"				FROM \"Vendedores\" P7,\"Clientes_Registrados\" P2\n" +
"				WHERE P2.\"ID_V\" = P7.\"ID\"\n" +
"				GROUP BY P7.\"ID\") p8\n" +
"				WHERE p8.\"ID\" = P1.\"ID\" AND P1.\"ID\" = '"+ ID +"'";
        PreparedStatement ps= con.prepareStatement(SQL);
     
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
               
                String IDs =rs.getString("ID");
                int salario= rs.getInt("salario");
                Salario = salario;
                
            }
        }catch(SQLException e){
            System.err.println(e.toString());
        }
        return Salario;
    }
    @FXML
    public void volver(){
        btnCambiarComision.setVisible(true);
        labelNombre.setVisible(false);
        LabelSalario.setVisible(false);
        txtNombre.setVisible(false);
        txtSalario.setVisible(false);
        Volverbtn.setVisible(false);
        BtnNumeroClientes.setVisible(true);
        BtnClientes.setVisible(true);
        Tablacantidad.setVisible(false);
        TablaClient.setVisible(false);
    }

    @FXML
    private void CambiarComision(ActionEvent event) {
        programaPrincipal.Comision();
    }
}

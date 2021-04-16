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
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class VentasClienteController implements Initializable {

    private principal programaPrincipal;
    @FXML
    private TableView<Ventas> Tabla;
    @FXML
    private TableColumn<Ventas, String> columid;
    @FXML
    private TableColumn<Ventas, String> columcod;
    @FXML
    private TableColumn<Ventas, String> columNombre;
    @FXML
    private TableColumn<Ventas, String> columProducto;
    @FXML
    private TableColumn<Ventas, Integer> columcantidad;
    @FXML
    private TableColumn<Ventas, Integer> columTotal;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField txtCodigo;
    private TextField txtFecha;
    @FXML
    private Button Eliminar;
    @FXML
    private SplitMenuButton Opciones;
    @FXML
    private MenuItem MenuDevolution;
    @FXML
    private Button Devolucion;
    @FXML
    private MenuItem ItemEliminar;
    @FXML
    private MenuItem itemVentas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConsultarProductos();
        colocarImagenBotones();
    }

    public void setProgramaPrincipal(principal programa) {
        programaPrincipal = programa;
    }

    @FXML
    private void ConsultarProductos() {

        Tabla.setVisible(true);
        txtCodigo.setVisible(false);
        Devolucion.setVisible(false);
        Eliminar.setVisible(false);
        this.columid.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcod.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columcantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        Cliente c = new Cliente();
        ObservableList<Ventas> item = v.esteCliente(c.getID());
        this.Tabla.setItems(item);

    }

    @FXML
    private void consultarEliminar() {
        txtCodigo.setText("");
        Tabla.setVisible(true);
        Devolucion.setVisible(false);
        txtCodigo.setVisible(true);
        Eliminar.setVisible(true);
        this.columid.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcod.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columcantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        Cliente c = new Cliente();
        ObservableList<Ventas> item = v.EliminarVentas(c.getID());
        this.Tabla.setItems(item);
    }

    @FXML
    private void Seleccionar() {

        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        Ventas v;
        v = this.Tabla.getSelectionModel().getSelectedItem();
        if (v == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe Seleccionar");
            alert.showAndWait();

        } else {

            String codigo = this.columid.getText();
            this.txtCodigo.setText(v.getId_ventas());

        }
    }

    @FXML
    private void eliminar() throws SQLException {
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar los datos");
        } else {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            int cantidad = 0;
            String codigo = "";
            String SQL = "SELECT \"cantidad\", \"cod_producto\"\n"
                    + "FROM \"ventas\"\n"
                    + "WHERE \"id_venta\" = '" + txtCodigo.getText() + "'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt("cantidad");
                codigo = rs.getString("cod_producto");
            }

            String SQL1 = "UPDATE \"ventas\"\n"
                    + "SET \"estado\" = ?\n"
                    + "WHERE \"id_venta\" = '" + txtCodigo.getText() + "';";
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            ps1.setString(1, "eliminado");
            ps1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto eliminado");
            String SQL2 = "SELECT \"cantidad\"\n"
                    + "FROM \"Productos\"\n"
                    + "WHERE \"codigo\" = '" + codigo + "'";
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                cantidad += rs2.getInt("cantidad");
            }
            String SQL3 = "UPDATE  \"Productos\"\n"
                    + "SET \"cantidad\" = ?\n"
                    + "WHERE \"codigo\" = '" + codigo + "'";
            PreparedStatement ps3 = con.prepareStatement(SQL3);
            ps3.setInt(1, cantidad);
            ps3.executeUpdate();
        }
    }

    @FXML
    private void Volver(ActionEvent event) {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }

    @FXML
    private void Devolucion(ActionEvent event) {
        Tabla.setVisible(true);
        txtCodigo.setVisible(true);
        Eliminar.setVisible(false);
        Devolucion.setVisible(true);
        this.columid.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcod.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columcantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        Cliente c = new Cliente();
        ObservableList<Ventas> item = v.esteCliente(c.getID());
        this.Tabla.setItems(item);
    }

    @FXML
    private void funDevolucion(ActionEvent event) throws SQLException {
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar los datos");
        } else {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            int cantidad = 0;
            String codigo = "";
            String SQL = "SELECT \"cantidad\", \"cod_producto\"\n"
                    + "FROM \"ventas\"\n"
                    + "WHERE \"id_venta\" = '" + txtCodigo.getText() + "'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt("cantidad");
                codigo = rs.getString("cod_producto");
            }

            String SQL1 = "UPDATE \"ventas\"\n"
                    + "SET \"estado\" = ?\n"
                    + "WHERE \"id_venta\" = '" + txtCodigo.getText() + "';";
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            ps1.setString(1, "pendiente");
            ps1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto pendiente para devolucion");
            String SQL2 = "SELECT \"cantidad\"\n"
                    + "FROM \"Productos\"\n"
                    + "WHERE \"codigo\" = '" + codigo + "'";
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                cantidad += rs2.getInt("cantidad");
            }
            String SQL3 = "UPDATE  \"Productos\"\n"
                    + "SET \"cantidad\" = ?\n"
                    + "WHERE \"codigo\" = '" + codigo + "'";
            PreparedStatement ps3 = con.prepareStatement(SQL3);
            ps3.setInt(1, cantidad);
            ps3.executeUpdate();
        }
    }
    public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/devolucion.png");
        URL linkNuevo1 = getClass().getResource("/Imagenes/consultar.png");
        URL linkNuevo2 = getClass().getResource("/Imagenes/opciones.png");
        URL linkNuevo3 = getClass().getResource("/Imagenes/ventas.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        Image imagenNuevo1 = new Image(linkNuevo1.toString(), 24,24,false,true);
        Image imagenNuevo2 = new Image(linkNuevo2.toString(), 15,15,false,true);
        Image imagenNuevo3 = new Image(linkNuevo3.toString(), 24,24,false,true);
        Devolucion.setGraphic(new ImageView(imagenNuevo)); 
        MenuDevolution.setGraphic(new ImageView(imagenNuevo)); 
        Eliminar.setGraphic(new ImageView(imagenNuevo1));  
        ItemEliminar.setGraphic(new ImageView(imagenNuevo1));
        Opciones.setGraphic(new ImageView(imagenNuevo2));
        itemVentas.setGraphic(new ImageView(imagenNuevo3));
    }
    @FXML
    public void jeje(KeyEvent a){
       Object evt = a.getSource();
       if(evt.equals(txtCodigo)){
                    if(!Character.isDigit(a.getCharacter().charAt(0))){
             a.consume();
                    }
        }
    }
}

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class TotalVentasController implements Initializable {
 
    
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
    private Label labelInformacion;
    @FXML
    private Label labelTotal;
    @FXML
    private TextField txtTotalVentas;
    @FXML
    private Button btnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciar();
        try {
            TotalVentas();
        } catch (SQLException ex) {
            Logger.getLogger(TotalVentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtTotalVentas.setEditable(false);
        colocarImagenBotones();
    }    
    public void setProgramaPrincipal(principal programa)
    {
        programaPrincipal= programa;
    }
    @FXML
    private void TotalVentas() throws SQLException {
        Ventas v = new Ventas();
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
            int MES = fecha.get(Calendar.MONTH) + 1;
            int DIA = fecha.get(Calendar.DAY_OF_MONTH);
            String fechaActual = año +"-" + MES + "-" + DIA;
            int año1 = fecha.get(Calendar.YEAR);
            int MES1 = fecha.get(Calendar.MONTH) ;
            int DIA1 = fecha.get(Calendar.DAY_OF_MONTH);
            String fechaAnterior = año1 +"-" + MES1 + "-" + DIA1;
            System.out.println(fechaActual);
            System.out.println(fechaAnterior);
        String sql = "SELECT SUM(valor) \n" +
" 				FROM \"ventas\" \n" +
"				WHERE \"id_venta\" in\n" +
" 				(SELECT \"id_venta\"\n" +
" 				FROM \"ventas\"\n" +
" 				WHERE \"fecha_compra\"BETWEEN '" + fechaAnterior + "' AND '" + fechaActual + "')\n" +
" 				AND \"estado\" = 'entregado'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           txtTotalVentas.setText(rs.getInt("sum")+"");
    }
    }
    @FXML
    private void Volver(ActionEvent event) {
        Usuarios u = new Usuarios();
        programaPrincipal.AbrirTerceraVentana(u.getTipoUsuario());
    }
    
    public void iniciar(){
     this.columid.setCellValueFactory(new PropertyValueFactory("id_ventas"));
        this.columcod.setCellValueFactory(new PropertyValueFactory("cod_producto"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("cod_cliente"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columcantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
       this.columTotal.setCellValueFactory(new PropertyValueFactory("valor"));
        Ventas v = new Ventas();
        ObservableList<Ventas> item = v.getTotalVentas();
        this.Tabla.setItems(item);  
    }
     public void colocarImagenBotones(){
        URL linkNuevo = getClass().getResource("/Imagenes/volver.png");
        Image imagenNuevo = new Image(linkNuevo.toString(), 24,24,false,true);
        btnVolver.setGraphic(new ImageView(imagenNuevo));       
    }
}


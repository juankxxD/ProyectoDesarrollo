/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Juan
 */
public class principal extends Application{
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private String tipo;
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
        primaryStage= stage;
        primaryStage.setTitle("JVD Inversion");
        initRootLayout();
        
    }
    public void initRootLayout()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/InicioSesion.fxml"));
            rootLayout= (BorderPane) loader.load();
            
            //Aquí se carga la escena principal
            Scene scene= new Scene(rootLayout);
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            InicioSesionController controller= loader.getController();
            controller.setProgramaPrincipal(this);
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void abrirOtraVentana()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Registrar.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Registro de Usuarios");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            RegistrarController registrar= loader.getController();
            
            registrar.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AbrirTerceraVentana(String tipo)//, String Id)
    {
        try 
        {
            //this.ID=Id;
            this.tipo=tipo;
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Menu.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Ventana alternativa");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            MenuController AbrirTerceraVentana= loader.getController();
            
            AbrirTerceraVentana.setProgramaPrincipal(this);
            AbrirTerceraVentana.iniciar();
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void AgregarP()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/AgregarProductos.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Agregar Producto");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            AgregarProductosController productos= loader.getController();
            
            productos.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void Notificar()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/NotificarVendedor.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Noticiar datos");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            NotificarVendedorController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void Actualizar()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Actualizar.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Actualizar Datos");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ActualizarController actualizar= loader.getController();
            
            actualizar.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void Vendedores()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Vendedores.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Consultar");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            VendedoresController clase= loader.getController();
            
            clase.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void Comision()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Comision.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Cambiar Comision");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ComisionController clase= loader.getController();
            
            clase.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void consultarP()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/consultarProductos.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("consultar Producto");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ConsultarProductosController productos= loader.getController();
            
            productos.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void ConsultarClienteAdm()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/ConsultarClienteAdm.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Noticiar datos");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ConsultarClienteAdmController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void BonoRegalo()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/BonoRegalo.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Bono de regalo");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            BonoRegaloController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void ConsultarVendedor()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/ConsultarClienteVendedor.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Consultar Cliente");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ConsultarClienteVendedorController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void ProductosVendedor()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/ProductosVendedor.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Productos");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ProductosVendedorController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void ComprarVendedor()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/comprarVendedor.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Comprar");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ComprarVendedorController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void ProductosCliente()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/ProductosCliente.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Consultar productos");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ProductosClienteController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void ComprarCliente()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/ComprarCliente.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Comprar");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            ComprarClienteController Cliente= loader.getController();
            
            Cliente.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args)
    {
        launch(args); 
    }    
}
    


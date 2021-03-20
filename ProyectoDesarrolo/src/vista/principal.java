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
            
            primaryStage.setTitle("Agregar Producto");
            
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
    public static void main(String[] args)
    {
        launch(args); 
    }    
}
    


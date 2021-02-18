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
            loader.setLocation(principal.class.getResource("/vista/menu.fxml"));
            rootLayout= (BorderPane) loader.load();
            
            //Aquí se carga la escena principal
            Scene scene= new Scene(rootLayout);
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            MenuController controller= loader.getController();
            controller.setProgramaPrincipal(this);
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }/*
    public void abrirOtraVentana()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Tablero.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Ventana alternativa");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            TableroController otraVentana= loader.getController();
            
            otraVentana.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AbrirTerceraVentana()
    {
        try 
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(principal.class.getResource("/vista/Jugadores.fxml"));
            BorderPane ventana= (BorderPane) loader.load();
            
            primaryStage.setTitle("Ventana alternativa");
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            
            JugadoresController AbrirTerceraVentana= loader.getController();
            
            AbrirTerceraVentana.setProgramaPrincipal(this);
            
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public static void main(String[] args)
    {
        launch(args); 
    }    
}
    


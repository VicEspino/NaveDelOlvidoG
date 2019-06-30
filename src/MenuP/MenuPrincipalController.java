/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuP;

import ConfiguracionVentana.VentanaConfiguracionController;
import Logica.MeteoroClass;
import Logica.MusicLoader;
import Logica.RecursosGlobales;
import VentanaJuego.VentanaJuegoController;
import com.jfoenix.controls.JFXSlider;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author VAPESIN
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private AnchorPane barraMover;
    @FXML
    private Button btnJugar;
    @FXML
    private Button btnOpciones;
    @FXML
    private Button Salir;
    @FXML
    private Circle circleMeteoro;

    MeteoroClass meteoroP;
    @FXML
    private AnchorPane fondo;
    
    Thread iniciadorLluvia;
    @FXML
    private JFXSlider deslizadorVelocidad;
    @FXML
    private Rectangle fondoNegro;
    VentanaJuegoController ventanaJuego;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//       RecursosGlobales.music   

        RecursosGlobales.lluviaMeteoros = true;
        deslizadorVelocidad.valueProperty().addListener(
          (observable, oldValue, newValue) -> {
              RecursosGlobales.velocidadLluvia = (long) this.deslizadorVelocidad.getValue();
              RecursosGlobales.velocidadGeneracionLluvia = (long) (5.78*this.deslizadorVelocidad.getValue())+44;
        });
        iniciadorLluvia = new Thread(){
            
         //con clase anonimaa tira excepcion
           
         @Override
         public void run(){
              
             while(RecursosGlobales.lluviaMeteoros){
              new MeteoroClass(fondo).start();
              new MeteoroClass(fondo).start();
     //         new MeteoroClass(fondo).start();
                 try {
                     //sleep(RecursosGlobales.velocidadLluvia);
                     sleep(RecursosGlobales.velocidadGeneracionLluvia);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             
         }
         
       };
      iniciadorLluvia.setDaemon(true);
      iniciadorLluvia.start();
      RecursosGlobales.ml = new MusicLoader();
        
    }    
    
    

    @FXML
    private void btnJugar_OnAction(ActionEvent event) {
      
        ventanaJuego = new VentanaJuegoController();
        Stage ventana = (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(new Scene(ventanaJuego));                                      
        RecursosGlobales.lluviaMeteoros=false;
                //ventanaJuego.setLayoutX(ventanaJuego.getLayoutX()-80);                            
    }

    @FXML
    private void btnOpciones_OnAction(ActionEvent event) throws IOException {
         Stage s = ( (Stage) ((Node)event.getSource()).getScene().getWindow() );

               VentanaConfiguracionController ventana = new VentanaConfiguracionController(fondo);        
                ventana.setLayoutX(RecursosGlobales.largoMenu-10);
                ventana.setLayoutY(RecursosGlobales.altoMenu/11);
                fondo.getChildren().add(ventana);
     new Thread(()->{                  
             while(ventana.getLayoutX()>450){
                  Platform.runLater(()->{
                                    
                    ventana.setLayoutX(ventana.getLayoutX()-10);
                 });
                  
                try {
                     sleep(4);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                 }  

            }

     }).start();
    
      
    }

    @FXML
    private void btnSalir_OnAction(ActionEvent event) {
        //saldrá del del programa
        System.exit(0);
        
    }

    private void cambiarVentana(ActionEvent event, String ventanaJuegoVentanaJuegofxml) {
        
        //Parent root = FXMLL
        Stage newStage = new Stage();
        newStage = (Stage)( ((Node) (event.getSource() ) ).getScene().getWindow() );
        
        try {
            newStage.setScene(new Scene((Parent)FXMLLoader.load(getClass().getResource(ventanaJuegoVentanaJuegofxml))));
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuP;

import Logica.MeteoroClass;
import Logica.RecursosGlobales;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
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
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
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
    private JFXSlider deslizador;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       RecursosGlobales.music = new AudioClip( this.getClass().getResource("/Recursos/Musica/angel.mp4").toString() );
         RecursosGlobales.music.play();
        deslizador.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    RecursosGlobales.velocidadLluvia = (long) this.deslizador.getValue();
                    
        });
      iniciadorLluvia = new Thread(){

         //con clase anonimaa tira excepcion
           
         @Override
         public void run(){
              
             while(RecursosGlobales.lluviaMeteoros){
              new MeteoroClass(fondo).start();
              new MeteoroClass(fondo).start();
                 try {
                     sleep(50);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             
         }
         
       };
      iniciadorLluvia.setDaemon(true);
      iniciadorLluvia.start();
      
    }    

    @FXML
    private void btnJugar_OnAction(ActionEvent event) {
      
        //meteoroP = 
       
       
      
       
         
    }

    @FXML
    private void btnOpciones_OnAction(ActionEvent event) throws IOException {
        
       /* Parent root = FXMLLoader.load(getClass().getResource("/ConfiguracionVentana/VentanaConfiguracion.fxml"));
        
        VBox v = new VBox();
        v.getChildren().addAll(root,(Node) event.getSource()  );
         Stage newStage = new Stage();
        newStage = (Stage)( ((Node) (event.getSource() ) ).getScene().getWindow() );
        newStage.setScene(new Scene(v));
        */
    }

    @FXML
    private void btnSalir_OnAction(ActionEvent event) {
        //saldrá del del programa
        System.exit(0);
        
    }
 
}

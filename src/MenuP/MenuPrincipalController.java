/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuP;

import Logica.MeteoroClass;
import Logica.RecursosGlobales;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

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
    private Circle circleMeteoro,meteoro;

    MeteoroClass meteoroP;
    @FXML
    private AnchorPane fondo;
    
    Thread iniciadorLluvia;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
    private void btnOpciones_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnSalir_OnAction(ActionEvent event) {
        //saldrá del del programa
        System.exit(0);
        
    }
    
}

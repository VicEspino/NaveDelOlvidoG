/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfiguracionVentana;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author VAPESIN
 */
public class VentanaConfiguracionController extends AnchorPane{

    @FXML
    private Button btnCerrar;
    @FXML
    private AnchorPane AnchorPane;
    private AnchorPane fondo;
    

    FXMLLoader fxmlLoader;
    @FXML
    private JFXSlider sliderMusica;
    
    public VentanaConfiguracionController(AnchorPane fondo) {
       this.fondo = fondo;
    
        try {
            fxmlLoader= new FXMLLoader(
                getClass().getResource("VentanaConfiguracion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(VentanaConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         sliderMusica.valueProperty().addListener(
               (observable, oldValue, newValue) -> {
                   RecursosGlobales.musiquita.volumeProperty().set(newValue.doubleValue()/100);
       } );
                       
    }

    @FXML
    private void btnCerrar_Click(ActionEvent event) {
        //AnchorPane p = fxmlLoader.getRoot();
    //    p.getChildren().remove(p);
    
    fondo.getChildren().remove(this);
    }

    
    
  
    
}

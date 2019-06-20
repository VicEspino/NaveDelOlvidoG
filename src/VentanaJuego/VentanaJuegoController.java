/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaJuego;

import Logica.RecursosGlobales;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author VAPESIN
 */
public class VentanaJuegoController implements Initializable {

    @FXML
    private ImageView Nave;
    @FXML
    private Rectangle laser;
    Juego juego;
    @FXML
    private AnchorPane fondoJuego;
    @FXML
    private Label lblPuntuacion;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        juego = new Juego(Nave,laser,fondoJuego,lblPuntuacion);
    }    

    @FXML
    private void teclaPresionada(KeyEvent event) {
        
        if(event.getCode() == KeyCode.LEFT){
            Juego.naveIzquierda= true;
            Juego.naveDerecha = false;
        }else if(event.getCode()== KeyCode.RIGHT){
             Juego.naveIzquierda= false;
             Juego.naveDerecha = true;
        
        }
            
    }

    @FXML
    private void teclaLevantada(KeyEvent event) {
        
          if(event.getCode() == KeyCode.LEFT){
            Juego.naveIzquierda= false;
        }else if(event.getCode()== KeyCode.RIGHT){
            Juego.naveDerecha = false;
        }else if(event.getCode() == KeyCode.SPACE){
            System.out.println("  dispario");
           RecursosGlobales.contadorDisparos++;
            this.juego.disparar();
        }

    }


    
    
    
}

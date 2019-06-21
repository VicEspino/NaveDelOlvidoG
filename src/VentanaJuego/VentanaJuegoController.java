/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaJuego;

import Logica.RecursosGlobales;
import MenuP.MenuPrincipalController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.plugin.javascript.navig.Anchor;


/**
 * FXML Controller class
 *
 * @author VAPESIN
 */
public class VentanaJuegoController extends AnchorPane {

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
    
    FXMLLoader fxmlLoader;
    public VentanaJuegoController() {

     
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("VentanaJuego.fxml"));       
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);        
            fxmlLoader.load();
            juego = new Juego(Nave,laser,fondoJuego,lblPuntuacion);
        } catch (IOException ex) {
            Logger.getLogger(VentanaJuegoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }    

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //juego = new Juego(Nave,laser,fondoJuego,lblPuntuacion);
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
        
          if(null != event.getCode())switch (event.getCode()) {
            case LEFT:
                Juego.naveIzquierda= false;
                break;
            case RIGHT:
                Juego.naveDerecha = false;
                break;
            case SPACE:
                System.out.println("  dispario");
                RecursosGlobales.contadorDisparos++;
                this.juego.disparar();
                break;
            case ESCAPE:
               
                cambiarVentana(event, "/MenuP/menuPrincipal.fxml");
                RecursosGlobales.lluviaMeteoros=true;
                break;
            default:
                break;
        }

    }
    private void cambiarVentana(Event event, String ventanaJuegoVentanaJuegofxml) {

            //Parent root = FXMLL
            Stage newStage = new Stage();
            newStage = (Stage)( ((Node) (event.getSource() ) ).getScene().getWindow() );

            try {
                newStage.setScene(new Scene((Parent)FXMLLoader.load(getClass().getResource(ventanaJuegoVentanaJuegofxml))));
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    private void generarVentanaConfirmacion(KeyEvent event) {
     /*      Stage dialog = new Stage();
        //dialog.initStyle(StageStyle.);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(((Node)event.getSource()).getScene().getWindow());
        
        Button salir = new Button("Salir alv");
        salir.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   //System.exit(0);
                   juego.pausarJuego();
               }
           });
        
        Scene scene = new Scene(salir,200,300);
        dialog.setScene(scene);
        dialog.show();
*/
    }


    
    
    
}

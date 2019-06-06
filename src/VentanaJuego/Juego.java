/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaJuego;

import Logica.RecursosGlobales;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author VAPESIN
 */
public class Juego {
    
    public static boolean naveIzquierda= false;
    public static boolean naveDerecha = false;
    private ImageView Nave;
    private Rectangle laser;
    private Thread jugando;
    private AnchorPane fondoJuego;
    
    public Juego() {
        
        
    }

    Juego(ImageView Nave, Rectangle laser,AnchorPane fondoJuego) {
        RecursosGlobales.jugando = true;
        this.Nave = Nave;
        this.laser = laser;
        inicializarHilo();
        jugando.setDaemon(true);
        jugando.start();
        this.fondoJuego = fondoJuego;
        
    }

    private void inicializarHilo() {

        jugando = new Thread(()->{
        
            while(RecursosGlobales.jugando){
                
                if(Juego.naveIzquierda&& Nave.getLayoutX()>0){
                    Platform.runLater(()->{
                    //cambiar el aumento en recursos globales, para alterar ahí la sensibilidad de la nave*
                        Nave.setLayoutX(Nave.getLayoutX()-3);
                    
                    });
                }
                if(Juego.naveDerecha && Nave.getLayoutX()<RecursosGlobales.largoMenu+Nave.getFitWidth()){
                    Platform.runLater(()->{
                    //cambiar el aumento en recursos globales, para alterar ahí la sensibilidad de la nave*
                        Nave.setLayoutX(Nave.getLayoutX()+3);
                    
                    });

                }
                
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
        });

    }

    void disparar() {

        Rectangle laserNuevo = new Rectangle(laser.getWidth(), laser.getHeight(), laser.getFill());

        
        fondoJuego.getChildren().add(laserNuevo);
        laserNuevo.setLayoutX(Nave.getLayoutX());
        laserNuevo.setLayoutY(Nave.getLayoutY());
        Thread hiloTemp =
        new Thread(()->{
            
            while(laserNuevo.getLayoutY()+laserNuevo.getHeight()>0){
                
                Platform.runLater(()->{
                    
                    laserNuevo.setLayoutY(laserNuevo.getLayoutY() -5);

                });
                
                try {
                    sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
            Platform.runLater(()->{
            
                fondoJuego.getChildren().remove(laserNuevo);                
                
            });
            
        });
        
        hiloTemp.setDaemon(false);
        hiloTemp.start();

    }
    
    
    
    
}

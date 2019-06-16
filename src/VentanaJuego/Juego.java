/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaJuego;

import Logica.RecursosGlobales;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
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
    private Thread generadorPiedrasRandom;
    private Label contador;
    
    private AnchorPane fondoJuego;
 
    private ArrayList<Rectangle> listaLasers;
    private ArrayList<ImageView> listaPiedras;
    
    public Juego() {
        
        
    }

    Juego(ImageView Nave, Rectangle laser,AnchorPane fondoJuego,Label contador) {
        RecursosGlobales.jugando = true;
        this.Nave = Nave;
        this.laser = laser;
        inicializarHilos();
        this.contador = contador;
        this.fondoJuego = fondoJuego;        
        listaLasers = new ArrayList<>();
        listaPiedras = new ArrayList<>();
        
        
    
    }

    //inicializa los hilos del movimiento de la nave  y el que genera las piedras automaticas (hilo padre que genera hilos hijos)
    private void inicializarHilos() {

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
        
        generadorPiedrasRandom = new Thread(()->{
            
            while(RecursosGlobales.jugando){
                Platform.runLater(()->{ 
                    generarPiedra();

                });
                try {
                    sleep(900);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //mover los daemon y starts al metodo de inicializarHilos
        jugando.setDaemon(true);
        jugando.start();
        
        generadorPiedrasRandom.setDaemon(true);
        generadorPiedrasRandom.start();
        

    }
    //se invocará desde afuera para hacer un disparo
    public void disparar() {
        AudioClip music = new AudioClip( this.getClass().getResource("/Recursos/Musica/laser.mp3").toString() );
        music.play();

        Rectangle laserNuevo = new Rectangle(laser.getWidth(), laser.getHeight(), laser.getFill());

        listaLasers.add(laserNuevo);
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
                listaLasers.remove(laserNuevo);
            });
            
        });
        
        hiloTemp.setDaemon(true);
        hiloTemp.start();

    }
    
    //generará una unica piedra en la pantalla de juego, en la posicionX random
    private void generarPiedra(){
        ImageView ivPiedra =new ImageView("Recursos/Imagenes/piedra.png");
        listaPiedras.add(ivPiedra);
        fondoJuego.getChildren().add(ivPiedra);
     
        ivPiedra.setLayoutX( (double)(new Random().nextInt((int) RecursosGlobales.largoMenu)) );
        ivPiedra.setLayoutY(0);
        ivPiedra.setFitHeight(RecursosGlobales.largoMenu/8);
        ivPiedra.setFitWidth(RecursosGlobales.largoMenu/8);

      Thread hiloTemp =
        new Thread(()->{
        boolean colision = false;
            while(ivPiedra.getLayoutY()<700 && colision == false){
                
               Platform.runLater(()->{
                   
                   ivPiedra.setLayoutY(ivPiedra.getLayoutY()+6);
                   
               });               
               for(Rectangle laserActual : listaLasers){
                   if(ivPiedra.getBoundsInParent().intersects(laserActual.getBoundsInParent())){
                       colision = true;
                       Platform.runLater(()->{
                           //RecursosGlobales.contadorPiedrasDestruidas++;
                           this.contador.setText(++RecursosGlobales.contadorPiedrasDestruidas +"");
                           fondoJuego.getChildren().remove(laserActual);
                           listaLasers.remove(laserActual);
                       });
                       break;

                   }
               }               
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);                    
                }                                    
            }
            
            Platform.runLater(()->{
            
                listaPiedras.remove(ivPiedra);
                fondoJuego.getChildren().remove(ivPiedra);
            
            });
        });
        hiloTemp.setDaemon(true);
        hiloTemp.start();
    
    } 
    
    
    
}




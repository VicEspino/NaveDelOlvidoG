/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Logica.MeteoroClass;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Circle;

/**
 *
 * @author VAPESIN
 */
public class MeteoroClass extends Thread {

    private  Circle meteoro;
    private AnchorPane fondo;
    private Random random;
    
    public MeteoroClass( AnchorPane fondo) {
        this.fondo = fondo;
    random = new Random();
    //    this.meteoro = new Circle(50, 50, 20);
    /*
    Se multiplica x2, porque le estamos restanto ya la altura de la ventana, por lo tanto, en la coordenada resultante, jamás llegará en Y a la coordenada maxima,
    así que si multiplicamos x2 el rango de generacion, se cubre esa zona faltante.
    */
        this.meteoro = new Circle( -50,-50 -RecursosGlobales.altoMenu + random.nextInt((int) (RecursosGlobales.altoMenu * 2)), 7);
            Platform.runLater(()->{
 
          
                fondo.getChildren().add(0,this.meteoro);

            }); 
        this.setDaemon(true);
    }
    
    @Override
    public void run(){
        
    
      //  if(meteoro!=null)
        while(meteoro.getCenterX() + RecursosGlobales.aumentoX < RecursosGlobales.largoMenu
                    && meteoro.getCenterY() + RecursosGlobales.aumentoY < RecursosGlobales.altoMenu){
            
            Platform.runLater(()->{
                 meteoro.setCenterX(meteoro.getCenterX() + RecursosGlobales.aumentoX);
                 meteoro.setCenterY(meteoro.getCenterY() + RecursosGlobales.aumentoY);
            });

            try {
                sleep(RecursosGlobales.velocidadLluvia);
            } catch (InterruptedException ex) {
                Logger.getLogger(MeteoroClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Platform.runLater(()->{
        
            fondo.getChildren().remove(this.meteoro);

        });
        
    }
    
}

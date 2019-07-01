/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.net.URI;
import java.net.URL;
import javafx.scene.media.Media;
 import javafx.scene.media.MediaPlayer;

/**
 *
 * @author VAPESIN
 */
public class RecursosGlobales {

    public static String path = "src/Recursos/Musica/angel.mp4";
    public static File grafic ;
    public static String rutaG;
    public static String cancionActual = "Angel - Elefante :v";
    public static int contadorPiedrasDestruidas = 0;
    public static int contadorDisparos = 0;
    public static Media media; 

    public static MediaPlayer musiquita;
    
    public static boolean jugando = false;
    public static boolean lluviaMeteoros = true; 
    public static double aumentoX= 3;
    public static double aumentoY= 3;
    //el sleep de cada particula
    public static long velocidadLluvia = 2;
    //cada cuando ms se generan 2 pelotas (sleep)
    public static long velocidadGeneracionLluvia = 50;
    
    public static double largoMenu=790;
    public static double altoMenu=570;
    public static double volumenMusic = 100;
    //cargará la lista de canciones
    //se inicializará en main o en menu principal
    public static MusicLoader ml;

    
    //por si se manda la ruta
   public static void iniciarMusica(String path){
       grafic = new File(path);
       
       //rutaG = grafic.getAbsoluteFile().toString();
      // rutaG = grafic.getAbsolutePath().toString();
    
       //media = new Media(new File(path).toURI().toString());
       media = new Media(path);
       
       musiquita  = new MediaPlayer(media);
       musiquita.play();
       musiquita.volumeProperty().set(0.4);

   }
   //cuando se manda una instancia
   public static void iniciarMusica(MusicaC cancion){
        musiquita.stop();
        RecursosGlobales.cancionActual = cancion.getNombre();
        
        if( cancion.isRecursoLocal()){
            iniciarMusica(cancion.getPaht());
            return;
        }
       try{
           //si no puede iniciar la instancia, entonces la cancion se trata de una de las que ya tenemos por defecto
           //por lo tanto pasa al catch

    //        System.out.println(cancion.getPaht());
      //      System.out.println(new File(cancion.getPaht()).toURI().toString());
                //estas 2 funcionan también
                // media = new Media( new File(cancion.getPaht()).toURI().toURL().toExternalForm());
                // media = new Media( new File(cancion.getPaht()).toURI().toURL().toString());
            media = new Media(new File(cancion.getPaht()).toURI().toString());
            musiquita = new MediaPlayer( media );
            musiquita.play();
            musiquita.volumeProperty().set(0.4);

       }catch(Exception ex){
          //iniciarMusica(cancion.getPaht());
            // cancionActual= cancion.getNombre();
            System.out.println("    Error al cargar el archivo");
       }
       
   }
    
    
}

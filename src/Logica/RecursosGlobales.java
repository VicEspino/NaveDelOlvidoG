/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
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
    public static long velocidadLluvia = 2;
    public static double largoMenu=790;
    public static double altoMenu=570;
    public static double volumenMusic = 100;

   public static void iniciarMusica(String path){
       grafic = new File(path);
       
       rutaG = grafic.getAbsoluteFile().toString();
       media = new Media(new File(path).toURI().toString());
       
       musiquita  = new MediaPlayer(media);
       musiquita.play();

   }
   
   public static void iniciarMusica(MusicaC cancion){
       musiquita.stop();
       iniciarMusica(cancion.getPaht());
       cancionActual= cancion.getNombre();
   }
    
    
}

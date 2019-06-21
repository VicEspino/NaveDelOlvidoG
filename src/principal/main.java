/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Logica.RecursosGlobales;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author VAPESIN
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
      
                   
        Parent root = FXMLLoader.load(getClass().getResource("/MenuP/menuPrincipal.fxml"));
        //ssaaasddssf
        primaryStage.setScene(new Scene(root,790,570));
        
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/Recursos/Imagenes/cabeza_icono.png")); 
        primaryStage.setTitle("La Nave del Olvido");
   
        RecursosGlobales.iniciarMusica("src/Recursos/Musica/angel.mp4");

    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfiguracionVentana;


import Logica.MusicaC;
import Logica.RecursosGlobales;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author VAPESIN
 */
public class VentanaConfiguracionController extends AnchorPane{

    @FXML
    private Button btnCerrar;
    private AnchorPane fondo;
    

    FXMLLoader fxmlLoader;
    @FXML
    private JFXSlider sliderMusica;
    @FXML
    private AnchorPane raiz;
    @FXML
    private JFXComboBox<MusicaC> cbCancionesLista;
    @FXML
    private Button btnAnadirCanciones;
    
    public VentanaConfiguracionController(AnchorPane fondo) {
       this.fondo = fondo;
       //carga el controlador y la vista
        try {
            fxmlLoader= new FXMLLoader(
                getClass().getResource("VentanaConfiguracion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(VentanaConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //añade listener para el slider, y modificar el volumen
         sliderMusica.valueProperty().addListener(
               (observable, oldValue, newValue) -> {
                  
                   RecursosGlobales.musiquita.volumeProperty().set(newValue.doubleValue()/100);
       } );

         //establece la lista para el combobox (creo, no me acuerdo xd )
        Callback<ListView<MusicaC>, ListCell<MusicaC>> factory = lv -> new ListCell<MusicaC>() {

            @Override
            protected void updateItem(MusicaC item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };
        //agrega la lista de canciones al combobox
        cbCancionesLista.setCellFactory(factory);
      //  cbCancionesLista.getItems().add(new MusicaC(getClass().getResource("/Recursos/Musica/Teminite-Ascent2.mp3").toString(), "Ascent"));
        //cbCancionesLista.getItems().add(new MusicaC(getClass().getResource("/Recursos/Musica/angel.mp4").toString(), "Angel - Elefante :v"));

        cbCancionesLista.getItems().addAll(RecursosGlobales.ml.getListaMusicaDirectorios());
        
        //cbCancionesLista.setItems(value);
       // cbCancionesLista.getSelectionModel().select(0);
        //cbCancionesLista.selectionModelProperty().setValue( cbCancionesLista.getSelectionModel() );
        cbCancionesLista.setPromptText(RecursosGlobales.cancionActual);
    }

    @FXML
    private void btnCerrar_Click(ActionEvent event) {
        //ocultará el menu configuración, desplazandolo a la derecha y luego borrandolo del fondo.
        new Thread(()->{
            while(this.getLayoutX()<RecursosGlobales.largoMenu+10){
                
                Platform.runLater(()->{
                    
                    this.setLayoutX(this.getLayoutX()+10);
                    
                });
                
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VentanaConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //remueve el menu configuración, una vez que ya no está visible
            Platform.runLater(()->{ 
            
                fondo.getChildren().remove(this);

            });

        }).start();
        
    }

    @FXML
    private void clickCancion(ActionEvent event) {
        
        JFXComboBox a = ((JFXComboBox)event.getSource());
        MusicaC musi = (MusicaC)a.getSelectionModel().getSelectedItem();
        
        if(musi!=null)
            RecursosGlobales.iniciarMusica(musi);

        
    }

    @FXML
    private void btnAnadirCanciones_Click(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona la(s) canción(es) a agregar... ");
        //si se pone solo uno, solo filtrará ese
      //  fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 FILES xd", "*.mp3"));
      
      //si se ponen 2 o más en el extension filter, agrupará los que contengan esas extensiones
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Musica o Video xd", "*.mp4","*.mp3"));
        //fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
       // new Thread( ()->{
         //   Platform.runLater(()->{
         RecursosGlobales.velocidadGeneracionLluvia = 150;
         RecursosGlobales.velocidadLluvia = 15;
        RecursosGlobales.ml.anadirNuevasRutas( fileChooser.showOpenMultipleDialog( ((Node)event.getSource()).getScene().getWindow() ) ) ;
           // });
           cbCancionesLista.getItems().clear();
        cbCancionesLista.getItems().addAll(RecursosGlobales.ml.getListaMusicaDirectorios());
       //}).start();
    }

    
    
  
    
}

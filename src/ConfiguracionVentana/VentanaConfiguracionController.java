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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

        Callback<ListView<MusicaC>, ListCell<MusicaC>> factory = lv -> new ListCell<MusicaC>() {

            @Override
            protected void updateItem(MusicaC item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };
        cbCancionesLista.setCellFactory(factory);
        cbCancionesLista.getItems().add(new MusicaC("src/Recursos/Musica/Teminite-Ascent2.mp3", "Ascent"));
        cbCancionesLista.getItems().add(new MusicaC("src/Recursos/Musica/angel.mp4", "Angel - Elefante :v"));
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

        RecursosGlobales.iniciarMusica(musi);

        
    }

    
    
  
    
}

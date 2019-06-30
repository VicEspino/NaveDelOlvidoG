/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author VAPESIN
 */
/*
Esta clase leerá y escribirá el archivo de registro de las canciones 
(donde se guardarán las direccines de cada archivo),
y esas rutas las cargará en una lista, que será usada por la ventana de configuaración

*/
public class MusicLoader {
    
    ArrayList<MusicaC> listaMusicaDirectorios;

    public MusicLoader() {
        
        listaMusicaDirectorios = new ArrayList<>();
        listaMusicaDirectorios.add(new MusicaC(getClass().getResource("/Recursos/Musica/Teminite-Ascent2.mp3").toString(), "Ascent"));
        listaMusicaDirectorios.add(new MusicaC(getClass().getResource("/Recursos/Musica/angel.mp4").toString(), "Angel - Elefante :v"));
        
    }
    //comprobará la existencia del archivo de registros
    //si encuentra el archivo, extraerá las rutas, y generará
    //objetos MusicaC, y los llevará a una lista, para retornarla
    //esto solo se hará en una unica instancia, despues, para acceder a la lista
    //habrá un get
    private List<MusicaC> buscarRegistros(){
        File archivoDeRegistros;
        return null;
    }
    
    public boolean escribirArchivo(){
        try{
            FileOutputStream a = new FileOutputStream(new File("src/Recursos/Archivos/Creg.nod"));
            byte rutaEscribir[];
            rutaEscribir = new byte[200];
            rutaEscribir[1] = 65;
            rutaEscribir[2] = 66;
            rutaEscribir[3] = 67;
            a.write(rutaEscribir);
            a.close();
        }
        catch(FileNotFoundException FE){
            System.out.println("erros escrbiir archivo");
        }
        catch(IOException IOe){
            System.out.println("Error al guardar el archivo");
        }
        catch(Exception ex){
            System.out.println("Error general");
        }
        
        return true;
    }

    public ArrayList<MusicaC> getListaMusicaDirectorios() {
        return listaMusicaDirectorios;
    }
    

    public boolean anadirNuevasRutas(List<File> nuevosRegistros) {
        

        
        if(nuevosRegistros==null){
            return false;
        }else{
            
            for(File elemento: nuevosRegistros){
                this.listaMusicaDirectorios.add(new MusicaC(elemento.getAbsolutePath(), elemento.getName()));
            }
            return true;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *Clase musica, cada instancia de esta clase, contendrá los datos de una cancion, 
 * @author VAPESIN
 */
public class MusicaC {
    
    private String paht;
    private String nombre;
    private boolean recursoLocal;
    
    public MusicaC(String paht, String nombre , boolean recursoLocal) {
        this.paht = paht;
        this.nombre = nombre;
        this.recursoLocal = recursoLocal;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString(){
        return nombre;
    }

    public String getPaht() {
        return paht;
    }
    
    public boolean isRecursoLocal(){
        return recursoLocal;
    }
}

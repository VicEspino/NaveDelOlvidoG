/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author VAPESIN
 */
public class MusicaC {
    
    private String paht;
    private String nombre;

    public MusicaC(String paht, String nombre) {
        this.paht = paht;
        this.nombre = nombre;
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
    
}

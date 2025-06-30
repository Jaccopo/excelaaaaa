/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Vector;

/**
 *
 * @author aldoj
 */
public class EspecRepEspe {
    private float simple,dual,tandem,tridem;

    public EspecRepEspe(String[] datos) {
        this.simple = Float.parseFloat(datos[0]);
        this.dual = Float.parseFloat(datos[1]);
        this.tandem = Float.parseFloat(datos[2]);
        this.tridem = Float.parseFloat(datos[3]);
    }
     public Vector getVector(){
        Vector fila = new Vector();
        fila.add(this.simple);
        fila.add(this.dual);
        fila.add(this.tandem);
        fila.add(this.tridem);
        return fila;
    }

    public float getSimple() {
        return simple;
    }

    public void setSimple(float simple) {
        this.simple = simple;
    }

    public float getDual() {
        return dual;
    }

    public void setDual(float dual) {
        this.dual = dual;
    }

    public float getTandem() {
        return tandem;
    }

    public void setTandem(float tandem) {
        this.tandem = tandem;
    }

    public float getTridem() {
        return tridem;
    }

    public void setTridem(float tridem) {
        this.tridem = tridem;
    }
    
}

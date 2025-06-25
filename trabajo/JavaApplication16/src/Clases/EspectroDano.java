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
public class EspectroDano {
    private float simpleDef,simpleFat;
    private float dualDef,dualFat;
    private float TANDEMDef,TANDEMFat;
    private float TRIDEMEDef,TRIDEMFat;

    public EspectroDano(String[] datos) {
        this.simpleDef = Float.parseFloat(datos[0]);
        this.simpleFat = Float.parseFloat(datos[1]);
        this.dualDef = Float.parseFloat(datos[2]);
        this.dualFat = Float.parseFloat(datos[3]);
        this.TANDEMDef = Float.parseFloat(datos[4]);
        this.TANDEMFat = Float.parseFloat(datos[5]);
        this.TRIDEMEDef = Float.parseFloat(datos[6]);
        this.TRIDEMFat = Float.parseFloat(datos[7]);
    }
    public Vector getVector(){
        var fila = new Vector();
        fila.add(this.simpleDef);
        fila.add(this.simpleFat);
        fila.add(this.dualDef);
        fila.add(this.dualFat);
        fila.add(this.TANDEMDef);
        fila.add(this.TANDEMFat);
        fila.add(this.TRIDEMEDef);
        fila.add(this.TRIDEMFat);
        return fila;
    }

    public float sumaDeformacion(){
        return this.simpleDef+this.dualDef+this.TRIDEMEDef+this.TANDEMDef;
    }
     public float sumaFatiga(){
        return this.simpleFat+this.dualFat+this.TANDEMFat+this.TRIDEMFat;
    }
    
    public float getSimpleDef() {
        return simpleDef;
    }

    public void setSimpleDef(float simpleDef) {
        this.simpleDef = simpleDef;
    }

    public float getSimpleFat() {
        return simpleFat;
    }

    public void setSimpleFat(float simpleFat) {
        this.simpleFat = simpleFat;
    }

    public float getDualDef() {
        return dualDef;
    }

    public void setDualDef(float dualDef) {
        this.dualDef = dualDef;
    }

    public float getDualFat() {
        return dualFat;
    }

    public void setDualFat(float dualFat) {
        this.dualFat = dualFat;
    }

    public float getTANDEMDef() {
        return TANDEMDef;
    }

    public void setTANDEMDef(float TANDEMDef) {
        this.TANDEMDef = TANDEMDef;
    }

    public float getTANDEMFat() {
        return TANDEMFat;
    }

    public void setTANDEMFat(float TANDEMFat) {
        this.TANDEMFat = TANDEMFat;
    }

    public float getTRIDEMEDef() {
        return TRIDEMEDef;
    }

    public void setTRIDEMEDef(float TRIDEMEDef) {
        this.TRIDEMEDef = TRIDEMEDef;
    }

    public float getTRIDEMFat() {
        return TRIDEMFat;
    }

    public void setTRIDEMFat(float TRIDEMFat) {
        this.TRIDEMFat = TRIDEMFat;
    }
    
}

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
    private double simpleDef,simpleFat;
    private double dualDef,dualFat;
    private double TANDEMDef,TANDEMFat;
    private double TRIDEMEDef,TRIDEMFat;

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

    public double sumaDeformacion(){
        return this.simpleDef+this.dualDef+this.TRIDEMEDef+this.TANDEMDef;
    }
     public double sumaFatiga(){
        return this.simpleFat+this.dualFat+this.TANDEMFat+this.TRIDEMFat;
    }
    
    public double getSimpleDef() {
        return simpleDef;
    }

    public void setSimpleDef(double simpleDef) {
        this.simpleDef = simpleDef;
    }

    public double getSimpleFat() {
        return simpleFat;
    }

    public void setSimpleFat(double simpleFat) {
        this.simpleFat = simpleFat;
    }

    public double getDualDef() {
        return dualDef;
    }

    public void setDualDef(double dualDef) {
        this.dualDef = dualDef;
    }

    public double getDualFat() {
        return dualFat;
    }

    public void setDualFat(double dualFat) {
        this.dualFat = dualFat;
    }

    public double getTANDEMDef() {
        return TANDEMDef;
    }

    public void setTANDEMDef(double TANDEMDef) {
        this.TANDEMDef = TANDEMDef;
    }

    public double getTANDEMFat() {
        return TANDEMFat;
    }

    public void setTANDEMFat(double TANDEMFat) {
        this.TANDEMFat = TANDEMFat;
    }

    public double getTRIDEMEDef() {
        return TRIDEMEDef;
    }

    public void setTRIDEMEDef(double TRIDEMEDef) {
        this.TRIDEMEDef = TRIDEMEDef;
    }

    public double getTRIDEMFat() {
        return TRIDEMFat;
    }

    public void setTRIDEMFat(double TRIDEMFat) {
        this.TRIDEMFat = TRIDEMFat;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author aldoj
 */
public class NumRepeEje {
    private float simDeformacion,simFatiga;
    private float dualDeformacion,dualFatiga;
    private float TANDEMDeformacion,TANDEMFatiga;
    private float TRIDEMDeformacion,TRIDEMFatiga;

    public NumRepeEje(String[] datos) {
        this.simDeformacion = Float.parseFloat(datos[0]);
        this.simFatiga = Float.parseFloat(datos[1]);
        this.dualDeformacion = Float.parseFloat(datos[2]);
        this.dualFatiga = Float.parseFloat(datos[3]);
        this.TANDEMDeformacion = Float.parseFloat(datos[4]);
        this.TANDEMFatiga = Float.parseFloat(datos[5]);
        this.TRIDEMDeformacion = Float.parseFloat(datos[6]);
        this.TRIDEMFatiga = Float.parseFloat(datos[7]);
    }

   
    public float getSimDeformacion() {
        return simDeformacion;
    }

    public void setSimDeformacion(float simDeformacion) {
        this.simDeformacion = simDeformacion;
    }

    public float getSimFatiga() {
        return simFatiga;
    }

    public void setSimFatiga(float simFatiga) {
        this.simFatiga = simFatiga;
    }

    public float getDualDeformacion() {
        return dualDeformacion;
    }

    public void setDualDeformacion(float dualDeformacion) {
        this.dualDeformacion = dualDeformacion;
    }

    public float getDualFatiga() {
        return dualFatiga;
    }

    public void setDualFatiga(float dualFatiga) {
        this.dualFatiga = dualFatiga;
    }

    public float getTANDEMDeformacion() {
        return TANDEMDeformacion;
    }

    public void setTANDEMDeformacion(float TANDEMDeformacion) {
        this.TANDEMDeformacion = TANDEMDeformacion;
    }

    public float getTANDEMFatiga() {
        return TANDEMFatiga;
    }

    public void setTANDEMFatiga(float TANDEMFatiga) {
        this.TANDEMFatiga = TANDEMFatiga;
    }

    public float getTRIDEMDeformacion() {
        return TRIDEMDeformacion;
    }

    public void setTRIDEMDeformacion(float TRIDEMDeformacion) {
        this.TRIDEMDeformacion = TRIDEMDeformacion;
    }

    public float getTRIDEMFatiga() {
        return TRIDEMFatiga;
    }

    public void setTRIDEMFatiga(float TRIDEMFatiga) {
        this.TRIDEMFatiga = TRIDEMFatiga;
    }
    
    
}

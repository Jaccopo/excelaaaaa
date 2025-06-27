/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author aldoj
 */
public class VidaUtil {
    private float defSim,defDual,defTANDM,defTRIDEM,defTodos;
    private float fafSim,fafDual,fafTANDM,fafTRIDEM,fafTodos;

    public VidaUtil(String datos[]) {
        this.defSim = Float.parseFloat(datos[0]);
        this.defDual = Float.parseFloat(datos[1]);
        this.defTANDM = Float.parseFloat(datos[2]);
        this.defTRIDEM = Float.parseFloat(datos[3]);
        this.defTodos = Float.parseFloat(datos[4]);
        this.fafSim = Float.parseFloat(datos[5]);
        this.fafDual = Float.parseFloat(datos[6]);
        this.fafTANDM = Float.parseFloat(datos[7]);
        this.fafTRIDEM = Float.parseFloat(datos[8]);
        this.fafTodos = Float.parseFloat(datos[9]);

    }
     public VidaUtil() {
        this.defSim = 0;
        this.defDual = 0;
        this.defTANDM = 0;
        this.defTRIDEM = 0;
        this.defTodos = 0;
        this.fafSim = 0;
        this.fafDual = 0;
        this.fafTANDM = 0;
        this.fafTRIDEM = 0;
        this.fafTodos = 0;

    }

    public float getDefSim() {
        return defSim;
    }

    public void setDefSim(float defSim) {
        this.defSim = defSim;
    }

    public float getDefDual() {
        return defDual;
    }

    public void setDefDual(float defDual) {
        this.defDual = defDual;
    }

    public float getDefTANDM() {
        return defTANDM;
    }

    public void setDefTANDM(float defTANDM) {
        this.defTANDM = defTANDM;
    }

    public float getDefTRIDEM() {
        return defTRIDEM;
    }

    public void setDefTRIDEM(float defTRIDEM) {
        this.defTRIDEM = defTRIDEM;
    }

    public float getDefTodos() {
        return defTodos;
    }

    public void setDefTodos(float defTodos) {
        this.defTodos = defTodos;
    }

    public float getFafSim() {
        return fafSim;
    }

    public void setFafSim(float fafSim) {
        this.fafSim = fafSim;
    }

    public float getFafDual() {
        return fafDual;
    }

    public void setFafDual(float fafDual) {
        this.fafDual = fafDual;
    }

    public float getFafTANDM() {
        return fafTANDM;
    }

    public void setFafTANDM(float fafTANDM) {
        this.fafTANDM = fafTANDM;
    }

    public float getFafTRIDEM() {
        return fafTRIDEM;
    }

    public void setFafTRIDEM(float fafTRIDEM) {
        this.fafTRIDEM = fafTRIDEM;
    }

    public float getFafTodos() {
        return fafTodos;
    }

    public void setFafTodos(float fafTodos) {
        this.fafTodos = fafTodos;
    }
}

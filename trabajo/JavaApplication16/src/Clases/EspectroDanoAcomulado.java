/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author aldoj
 */
public class EspectroDanoAcomulado {
    private float defSimple,defDual,defTANDEM,defTRIDEM,defTodos;
    private float fatSimple,fatDual,fatTANDEM,fatTrideem,fatTODOS;

    public EspectroDanoAcomulado(String datos[]) {
       
        this.defSimple = Float.parseFloat(datos[0]);
        this.defDual = Float.parseFloat(datos[1]);
        this.defTANDEM = Float.parseFloat(datos[2]);
        this.defTRIDEM = Float.parseFloat(datos[3]);
        this.defTodos = Float.parseFloat(datos[4]);
        this.fatSimple = Float.parseFloat(datos[5]);
        this.fatDual = Float.parseFloat(datos[6]);
        this.fatTANDEM = Float.parseFloat(datos[7]);
        this.fatTrideem = Float.parseFloat(datos[8]);
        this.fatTODOS = Float.parseFloat(datos[9]);
        
    }
    public void sumaFatiga(){
        this.fatTODOS= this.fatSimple+this.fatDual+this.fatTANDEM+this.fatTrideem;
    }
    public void sumaDeformaciones(){
        this.defTodos = this.defSimple+this.defDual+this.defTANDEM+this.defTrideem;
    }
    
    public float getDefSimple() {
        return defSimple;
    }

    public void setDefSimple(float defSimple) {
        this.defSimple = defSimple;
    }

    public float getDefDual() {
        return defDual;
    }

    public void setDefDual(float defDual) {
        this.defDual = defDual;
    }

    public float getDefTANDEM() {
        return defTANDEM;
    }

    public void setDefTANDEM(float defTANDEM) {
        this.defTANDEM = defTANDEM;
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

    public float getFatSimple() {
        return fatSimple;
    }

    public void setFatSimple(float fatSimple) {
        this.fatSimple = fatSimple;
    }

    public float getFatDual() {
        return fatDual;
    }

    public void setFatDual(float fatDual) {
        this.fatDual = fatDual;
    }

    public float getFatTANDEM() {
        return fatTANDEM;
    }

    public void setFatTANDEM(float fatTANDEM) {
        this.fatTANDEM = fatTANDEM;
    }

    public float getFatTrideem() {
        return fatTrideem;
    }

    public void setFatTrideem(float fatTrideem) {
        this.fatTrideem = fatTrideem;
    }

    public float getFatTODOS() {
        return fatTODOS;
    }

    public void setFatTODOS(float fatTODOS) {
        this.fatTODOS = fatTODOS;
    }
    
    
}

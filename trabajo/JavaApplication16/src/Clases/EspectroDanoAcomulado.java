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
public class EspectroDanoAcomulado {
    private double defSimple,defDual,defTANDEM,defTRIDEM,defTodos;
    private double fatSimple,fatDual,fatTANDEM,fatTrideem,fatTODOS;

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
     public Vector getVector(){
        var fila = new Vector();
        fila.add(this.defSimple);
        fila.add(this.defDual);
        fila.add(this.defTANDEM);
        fila.add(this.defTRIDEM);
        fila.add(this.defTodos);
        fila.add(this.fatSimple);
        fila.add(this.fatDual);
        fila.add(this.fatTANDEM);
        fila.add(this.fatTrideem);
        fila.add(this.fatTODOS);
        return fila;
    }
    public void sumaFatiga(){
        this.fatTODOS= this.fatSimple+this.fatDual+this.fatTANDEM+this.fatTrideem;
    }
    public void sumaDeformaciones(){
        this.defTodos = this.defSimple+this.defDual+this.defTANDEM+this.defTRIDEM;
    }
    
    public double getDefSimple() {
        return defSimple;
    }

    public void setDefSimple(double defSimple) {
        this.defSimple = defSimple;
    }

    public double getDefDual() {
        return defDual;
    }

    public void setDefDual(double defDual) {
        this.defDual = defDual;
    }

    public double getDefTANDEM() {
        return defTANDEM;
    }

    public void setDefTANDEM(double defTANDEM) {
        this.defTANDEM = defTANDEM;
    }

    public double getDefTRIDEM() {
        return defTRIDEM;
    }

    public void setDefTRIDEM(double defTRIDEM) {
        this.defTRIDEM = defTRIDEM;
    }

    public double getDefTodos() {
        return defTodos;
    }

    public void setDefTodos(double defTodos) {
        this.defTodos = defTodos;
    }

    public double getFatSimple() {
        return fatSimple;
    }

    public void setFatSimple(double fatSimple) {
        this.fatSimple = fatSimple;
    }

    public double getFatDual() {
        return fatDual;
    }

    public void setFatDual(double fatDual) {
        this.fatDual = fatDual;
    }

    public double getFatTANDEM() {
        return fatTANDEM;
    }

    public void setFatTANDEM(double fatTANDEM) {
        this.fatTANDEM = fatTANDEM;
    }

    public double getFatTrideem() {
        return fatTrideem;
    }

    public void setFatTrideem(double fatTrideem) {
        this.fatTrideem = fatTrideem;
    }

    public double getFatTODOS() {
        return fatTODOS;
    }

    public void setFatTODOS(double fatTODOS) {
        this.fatTODOS = fatTODOS;
    }
    
    
}

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
public class EspectroTotal {
    private double defTotal,fatTodos;

    public EspectroTotal(String[] datos) {
        this.defTotal = Float.parseFloat(datos[0]);
        this.fatTodos = Float.parseFloat(datos[1]);
    }
    public Vector getVector(){
        var fila = new Vector();
        fila.add(this.defTotal);
        fila.add(this.fatTodos);
        return fila;
    }

    public double getDefTotal() {
        return defTotal;
    }

    public void setDefTotal(double defTotal) {
        this.defTotal = defTotal;
    }

    public double getFatTodos() {
        return fatTodos;
    }

    public void setFatTodos(double fatTodos) {
        this.fatTodos = fatTodos;
    }
}

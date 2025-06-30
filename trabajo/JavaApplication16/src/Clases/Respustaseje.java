package Clases;

import java.util.Vector;


public class Respustaseje {
    private float tetacapa1,tetaterraceria,esterraceria,escapa1,deflexionVertical;
    
    public Respustaseje(String []respuesta){
        tetacapa1 = Float.parseFloat(respuesta[0]);
        tetaterraceria = Float.parseFloat(respuesta[1]);
        esterraceria = Float.parseFloat(respuesta[2]);
        escapa1 = Float.parseFloat(respuesta[3]);
        deflexionVertical = Float.parseFloat(respuesta[4]);
    }
    
    public Vector getVector(){
        var fila = new Vector();
        fila.add(this.tetacapa1);
        fila.add(this.tetaterraceria);
        fila.add(this.esterraceria);
        fila.add(this.escapa1);
        fila.add(this.deflexionVertical);
        return fila;
    }

    @Override
    public String toString() {
        return "Respustaseje{" + "tetacapa1=" + tetacapa1 + ", tetaterraceria=" + tetaterraceria + ", esterraceria=" + esterraceria + ", escapa1=" + escapa1 + ", deflexionVertical=" + deflexionVertical + '}';
    }
    
    public float getTetacapa1() {
        return tetacapa1;
    }

    public void setTetacapa1(float tetacapa1) {
        this.tetacapa1 = tetacapa1;
    }

    public float getTetaterraceria() {
        return tetaterraceria;
    }

    public void setTetaterraceria(float tetaterraceria) {
        this.tetaterraceria = tetaterraceria;
    }

    public float getEsterraceria() {
        return esterraceria;
    }

    public void setEsterraceria(float esterraceria) {
        this.esterraceria = esterraceria;
    }

    public float getEscapa1() {
        return escapa1;
    }

    public void setEscapa1(float escapa1) {
        this.escapa1 = escapa1;
    }


    public float getDeflexionVertical() {
        return deflexionVertical;
    }

    public void setDeflexionVertical(float deflexionVertical) {
        this.deflexionVertical = deflexionVertical;
    }
}

package Clases;

import java.util.Vector;

public class LarguilloNormal {

    private int dato;
    private float cargaPromedio;
    private float simple;
    private float dual;
    private float tandem;
    private float tridem;

    public LarguilloNormal(){
        dato = 0;
        cargaPromedio = 0;
        simple = 0;
        dual = 0;
        tandem = 0;
        tridem = 0;
    }

    public void setMuchos(int dato,float cargaPromedio,float simple,float dual,float tandem,float tridem){
        this.dato = dato;
        this.cargaPromedio = cargaPromedio;
        this.simple = simple;
        this.dual = dual;
        this.tandem = tandem;
        this.tridem = tridem;
    }
    
    public LarguilloNormal(String[] archivo) {
        this.dato = Integer.parseInt(archivo[0]);
        this.cargaPromedio = Float.parseFloat(archivo[1]);
        this.simple = Float.parseFloat(archivo[2]);
        this.dual =Float.parseFloat(archivo[3]);
        this.tandem = Float.parseFloat(archivo[4]);
        this.tridem = Float.parseFloat(archivo[5]);
    }
    
    public Vector getVector(){
        Vector fila = new Vector();
        fila.add(this.dato);
        fila.add(this.cargaPromedio);
        fila.add(this.simple);
        fila.add(this.dual);
        fila.add(this.tandem);
        fila.add(this.tridem);
        return fila;
    }
    
    @Override
    public String toString() {
        return "LarguilloNormal{" + "dato=" + dato + ", cargaPromedio=" + cargaPromedio + ", simple=" + simple + ", dual=" + dual + ", tandem=" + tandem + ", tridem=" + tridem + '}';
    }

    public  int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public float getCargaPromedio() {
        return cargaPromedio;
    }

    public void setCargaPromedio(float cargaPromedio) {
        this.cargaPromedio = cargaPromedio;
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

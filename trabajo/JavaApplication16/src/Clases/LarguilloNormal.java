package Clases;

public class LarguilloNormal {

    private int dato;
    private float cargaPromedio;
    private float simple;
    private float dual;
    private float tandem;
    private float tridem;

    public LarguilloNormal(String[] archivo) {
        this.dato = Integer.parseInt(archivo[0]);
        this.cargaPromedio = Float.parseFloat(archivo[1]);
        this.simple = Float.parseFloat(archivo[2]);
        this.dual =Float.parseFloat(archivo[3]);
        this.tandem = Float.parseFloat(archivo[4]);
        this.tridem = Float.parseFloat(archivo[5]);
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

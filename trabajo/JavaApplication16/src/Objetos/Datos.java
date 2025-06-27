package Objetos;

import Clases.CargarClases;

/**
 *
 * @author aldoj
 */
public class Datos {

    //Datos de nuevo formato pav
    public double pesoEje;
    public String tipoEje;
    public double presion=90;
    public int numCapas;
    public double poisson = .35;
    
    public EstructuraPavimiento ep[];
    public double X = 0;
    public double Y = 18;
    public double S = 36;
    public double D = 122;
    
    public Calculos cal;
    public Llantas llantas;
    
    public final double PI = 3.14159265358979;
    
    public CargarClases cc;
    public boolean activos[];
    
    public boolean damy = false;
    
    public String porcentajeConfiabilidad;
    
    public String tipoCarga;
    

    
    public void setPorcentajeConfiabilidad(String porcentajeConfiabilidad){
        this.porcentajeConfiabilidad = porcentajeConfiabilidad;
    }
    public String getPorcentajeConfiabilidad(){
        return this.porcentajeConfiabilidad;
    }

    public Datos(){
        ep = new EstructuraPavimiento[4];
        for (int i = 0; i<4;i++) {
           ep[i] = new EstructuraPavimiento();
        }
        damy = false;
        cal = new Calculos();
        llantas = new Llantas();
    }
    public CargarClases getCc() {
        return cc;
    }

    public void setCc(CargarClases cc) {
        this.cc = cc;
    }
    
    public double getPesoEje() {
        return pesoEje;
    }

    public void setPesoEje(double pesoEje) {
        this.pesoEje = pesoEje;
    }

    public String getTipoEje() {
        return tipoEje;
    }

    public void setTipoEje(String tipoEje) {
        this.tipoEje = tipoEje;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public int getNumCapas() {
        return numCapas;
    }

    public void setNumCapas(int numCapas) {
        this.numCapas = numCapas;
    }

    public double getPoisson() {
        return poisson;
    }

    public void setPoisson(double poisson) {
        this.poisson = poisson;
    }

    public EstructuraPavimiento getEp(int index) {
        return ep[index];
    }

    public void setEp(EstructuraPavimiento ep,int index) {
        this.ep[index] = ep;
    }

    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public double getS() {
        return S;
    }

    public void setS(double S) {
        this.S = S;
    }

    public double getD() {
        return D;
    }

    public void setD(double D) {
        this.D = D;
    }
    
    
    
    

    
}

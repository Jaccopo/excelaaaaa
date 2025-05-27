package Objetos;


public class llanta {

    private double anguloHorizontal;
    private double distanciaHorizontal;
    private double distanciaRadialCarpeta;
    private double distanciaRadialSubrasante;

    public llanta() {
        this.anguloHorizontal = 0;
        this.distanciaHorizontal = 0;
        this.distanciaRadialCarpeta = 0;
        this.distanciaRadialSubrasante = 0;
    }

    public llanta(double anguloHorizontal, double distanciaHorizontal, double distanciaRadialCarpeta, double distanciaRadialSubrasante) {
        this.anguloHorizontal = anguloHorizontal;
        this.distanciaHorizontal = distanciaHorizontal;
        this.distanciaRadialCarpeta = distanciaRadialCarpeta;
        this.distanciaRadialSubrasante = distanciaRadialSubrasante;
    }

    public double getAnguloHorizontal() {
        return anguloHorizontal;
    }

    public void setAnguloHorizontal(double anguloHorizontal) {
        this.anguloHorizontal = anguloHorizontal;
    }

    public double getDistanciaHorizontal() {
        return distanciaHorizontal;
    }

    public void setDistanciaHorizontal(double distanciaHorizontal) {
        this.distanciaHorizontal = distanciaHorizontal;
    }

    public double getDistanciaRadialCarpeta() {
        return distanciaRadialCarpeta;
    }

    public void setDistanciaRadialCarpeta(double distanciaRadialCarpeta) {
        this.distanciaRadialCarpeta = distanciaRadialCarpeta;
    }

    public double getDistanciaRadialSubrasante() {
        return distanciaRadialSubrasante;
    }

    public void setDistanciaRadialSubrasante(double distanciaRadialSubrasante) {
        this.distanciaRadialSubrasante = distanciaRadialSubrasante;
    }
    
    
    
}

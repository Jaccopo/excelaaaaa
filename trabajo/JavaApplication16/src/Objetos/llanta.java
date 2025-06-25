package Objetos;

public class Llanta {

    private double anguloHorizontal;
    private double distanciaHorizontal;
    private double distanciaRadialCarpeta;
    private double distanciaRadialSubrasante;
    private double ra, factorEVertical, factorERadial, factorETangencial;
    private double res1, res2;
    private double orNeumatico1, orNeumatico2, otNeumatico1, otNeumatico2;
    double valorCapa;
    double numcapa;

    public double getNumcapa() {
        return numcapa;
    }

    public void setNumcapa(double numcapa) {
        this.numcapa = numcapa;
    }

    
    public Llanta() {
        this.anguloHorizontal = 0;
        this.distanciaHorizontal = 0;
        this.distanciaRadialCarpeta = 0;
        this.distanciaRadialSubrasante = 0;
        this.ra = 0;
        this.factorEVertical = 0;
        this.factorERadial = 0;
        this.factorETangencial = 0;
        this.res1 = 0;
        this.res2 = 0;
        this.orNeumatico1 = 0;
        this.orNeumatico2 = 0;
        this.otNeumatico1 = 0;
        this.otNeumatico2 = 0;
        this.valorCapa = 0;
    }


    public Llanta(double ra, double factorEVertical,double factorERadial,
                    double factorETangencial,double res1,double res2,
                    double anguloHorizontal, double distanciaHorizontal, 
                    double distanciaRadialCarpeta, 
                    double distanciaRadialSubrasante,
                    double orNeumatico1,double orNeumatico2,double otNeumatico1,
                    double otNeumatico2,double valorCapa) {
        
        this.anguloHorizontal = anguloHorizontal;
        this.distanciaHorizontal = distanciaHorizontal;
        this.distanciaRadialCarpeta = distanciaRadialCarpeta;
        this.distanciaRadialSubrasante = distanciaRadialSubrasante;
        this.ra = ra;
        this.factorEVertical = factorEVertical;
        this.factorERadial = factorERadial;
        this.factorETangencial = factorETangencial;
        this.res1 = res1;
        this.res2 = res2;
        this.orNeumatico1 = orNeumatico1;
        this.orNeumatico2 = orNeumatico2;
        this.otNeumatico1 = otNeumatico1;
        this.otNeumatico2 = otNeumatico2;
        this.valorCapa = valorCapa;
    }

    
    public void setValorCapa(double valorCapa){
        this.valorCapa = valorCapa;
    }

    public double getValorCapa(){
        return this.valorCapa;
    }

    //Getters and setters 
    public double getValorCapa1() {
        return valorCapa;
    }

    public void setValorCapa1(double valorCapa) {
        this.valorCapa = valorCapa;
    }
    
    public double getRa() {
        return ra;
    }

    public void setRa(double ra) {
        this.ra = ra;
    }

    public double getFactorEVertical() {
        return factorEVertical;
    }

    public void setFactorEVertical(double factorEVertical) {
        this.factorEVertical = factorEVertical;
    }

    public double getFactorERadial() {
        return factorERadial;
    }

    public void setFactorERadial(double factorERadial) {
        this.factorERadial = factorERadial;
    }

    public double getFactorETangencial() {
        return factorETangencial;
    }

    public void setFactorETangencial(double factorETangencial) {
        this.factorETangencial = factorETangencial;
    }

    public double getRes1() {
        return res1;
    }

    public void setRes1(double res1) {
        this.res1 = res1;
    }

    public double getRes2() {
        return res2;
    }

    public void setRes2(double res2) {
        this.res2 = res2;
    }

    public double getOrNeumatico1() {
        return orNeumatico1;
    }

    public void setOrNeumatico1(double orNeumatico1) {
        this.orNeumatico1 = orNeumatico1;
    }

    public double getOrNeumatico2() {
        return orNeumatico2;
    }

    public void setOrNeumatico2(double orNeumatico2) {
        this.orNeumatico2 = orNeumatico2;
    }

    public double getOtNeumatico1() {
        return otNeumatico1;
    }

    public void setOtNeumatico1(double otNeumatico1) {
        this.otNeumatico1 = otNeumatico1;
    }

    public double getOtNeumatico2() {
        return otNeumatico2;
    }

    public void setOtNeumatico2(double otNeumatico2) {
        this.otNeumatico2 = otNeumatico2;
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

    @Override
    public String toString() {
        return "Llanta{" + "anguloHorizontal=" + anguloHorizontal + ", distanciaHorizontal=" + distanciaHorizontal + ", distanciaRadialCarpeta=" + distanciaRadialCarpeta + ", distanciaRadialSubrasante=" + distanciaRadialSubrasante + ", ra=" + ra + ", factorEVertical=" + factorEVertical + ", factorERadial=" + factorERadial + ", factorETangencial=" + factorETangencial + ", res1=" + res1 + ", res2=" + res2 + ", orNeumatico1=" + orNeumatico1 + ", orNeumatico2=" + orNeumatico2 + ", otNeumatico1=" + otNeumatico1 + ", otNeumatico2=" + otNeumatico2 + ", valorCapa=" + valorCapa + '}';
    }

}

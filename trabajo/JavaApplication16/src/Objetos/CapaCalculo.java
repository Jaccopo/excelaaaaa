package Objetos;

public class CapaCalculo {

    private int numCapas;
    private double espesor;
    private double moduloElastico;
    private double pesoNeumatico;
    private double v;
    private double RCC;
    private double espesorParcialEquivalente;
    private double convencion;
    private double espesorEquivalenteCapa;
    private double esfuerzoVerticalO, esfuerzoRadialO, esfuerzoTangencialO;
    private double deformacionVerticalE, deformacionRadialE, deformacionTangencialE;
    private double deflexionTotal;
    private double esfuerzoNormalX, esfuerzoNormalY, esfuerzoCortanteXY;
    private double deformacionVerticalE2, deformacionPorTension;
    private double a, b, c, d, e, lesimaDeflexion;
    private double esfuerzoCortanteRZ, esfuerzoCortanteYZ, esfuerzoCortanteXZ;
    
    private double complemento1,complemento2,complemento3,complemento4;

    public double getComplemento1() {
        return complemento1;
    }

    public void setComplemento1(double complemento1) {
        this.complemento1 = complemento1;
    }

    public double getComplemento2() {
        return complemento2;
    }

    public void setComplemento2(double complemento2) {
        this.complemento2 = complemento2;
    }

    public double getComplemento3() {
        return complemento3;
    }

    public void setComplemento3(double complemento3) {
        this.complemento3 = complemento3;
    }

    public double getComplemento4() {
        return complemento4;
    }

    public void setComplemento4(double complemento4) {
        this.complemento4 = complemento4;
    }

    
    public int getNumCapas() {
        return numCapas;
    }

    public void setNumCapas(int numCapas) {
        this.numCapas = numCapas;
    }

    public double getEspesor() {
        return espesor;
    }

    public void setEspesor(double espesor) {
        this.espesor = espesor;
    }

    public double getModuloElastico() {
        return moduloElastico;
    }

    public void setModuloElastico(double moduloElastico) {
        this.moduloElastico = moduloElastico;
    }

    public double getPesoNeumatico() {
        return pesoNeumatico;
    }

    public void setPesoNeumatico(double pesoNeumatico) {
        this.pesoNeumatico = pesoNeumatico;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getRCC() {
        return RCC;
    }

    public void setRCC(double RCC) {
        this.RCC = RCC;
    }

    public double getEspesorParcialEquivalente() {
        return espesorParcialEquivalente;
    }

    public void setEspesorParcialEquivalente(double espesorParcialEquivalente) {
        this.espesorParcialEquivalente = espesorParcialEquivalente;
    }

    public double getConvencion() {
        return convencion;
    }

    public void setConvencion(double convencion) {
        this.convencion = convencion;
    }

    public double getEspesorEquivalenteCapa() {
        return espesorEquivalenteCapa;
    }

    public void setEspesorEquivalenteCapa(double espesorEquivalenteCapa) {
        this.espesorEquivalenteCapa = espesorEquivalenteCapa;
    }

    public double getEsfuerzoVerticalO() {
        return esfuerzoVerticalO;
    }

    public void setEsfuerzoVerticalO(double esfuerzoVerticalO) {
        this.esfuerzoVerticalO = esfuerzoVerticalO;
    }

    public double getEsfuerzoRadialO() {
        return esfuerzoRadialO;
    }

    public void setEsfuerzoRadialO(double esfuerzoRadialO) {
        this.esfuerzoRadialO = esfuerzoRadialO;
    }

    public double getEsfuerzoTangencialO() {
        return esfuerzoTangencialO;
    }

    public void setEsfuerzoTangencialO(double esfuerzoTangencialO) {
        this.esfuerzoTangencialO = esfuerzoTangencialO;
    }

    public double getDeformacionVerticalE() {
        return deformacionVerticalE;
    }

    public void setDeformacionVerticalE(double deformacionVerticalE) {
        this.deformacionVerticalE = deformacionVerticalE;
    }

    public double getDeformacionRadialE() {
        return deformacionRadialE;
    }

    public void setDeformacionRadialE(double deformacionRadialE) {
        this.deformacionRadialE = deformacionRadialE;
    }

    public double getDeformacionTangencialE() {
        return deformacionTangencialE;
    }

    public void setDeformacionTangencialE(double deformacionTangencialE) {
        this.deformacionTangencialE = deformacionTangencialE;
    }

    public double getDeflexionTotal() {
        return deflexionTotal;
    }

    public void setDeflexionTotal(double deflexionTotal) {
        this.deflexionTotal = deflexionTotal;
    }

    public double getEsfuerzoNormalX() {
        return esfuerzoNormalX;
    }

    public void setEsfuerzoNormalX(double esfuerzoNormalX) {
        this.esfuerzoNormalX = esfuerzoNormalX;
    }

    public double getEsfuerzoNormalY() {
        return esfuerzoNormalY;
    }

    public void setEsfuerzoNormalY(double esfuerzoNormalY) {
        this.esfuerzoNormalY = esfuerzoNormalY;
    }

    public double getEsfuerzoCortanteXY() {
        return esfuerzoCortanteXY;
    }

    public void setEsfuerzoCortanteXY(double esfuerzoCortanteXY) {
        this.esfuerzoCortanteXY = esfuerzoCortanteXY;
    }

    public double getDeformacionVerticalE2() {
        return deformacionVerticalE2;
    }

    public void setDeformacionVerticalE2(double deformacionVerticalE2) {
        this.deformacionVerticalE2 = deformacionVerticalE2;
    }

    public double getDeformacionPorTension() {
        return deformacionPorTension;
    }

    public void setDeformacionPorTension(double deformacionPorTension) {
        this.deformacionPorTension = deformacionPorTension;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getLesimaDeflexion() {
        return lesimaDeflexion;
    }

    public void setLesimaDeflexion(double lesimaDeflexion) {
        this.lesimaDeflexion = lesimaDeflexion;
    }

    public double getEsfuerzoCortanteRZ() {
        return esfuerzoCortanteRZ;
    }

    public void setEsfuerzoCortanteRZ(double esfuerzoCortanteRZ) {
        this.esfuerzoCortanteRZ = esfuerzoCortanteRZ;
    }

    public double getEsfuerzoCortanteYZ() {
        return esfuerzoCortanteYZ;
    }

    public void setEsfuerzoCortanteYZ(double esfuerzoCortanteYZ) {
        this.esfuerzoCortanteYZ = esfuerzoCortanteYZ;
    }

    public double getEsfuerzoCortanteXZ() {
        return esfuerzoCortanteXZ;
    }

    public void setEsfuerzoCortanteXZ(double esfuerzoCortanteXZ) {
        this.esfuerzoCortanteXZ = esfuerzoCortanteXZ;
    }

   
}

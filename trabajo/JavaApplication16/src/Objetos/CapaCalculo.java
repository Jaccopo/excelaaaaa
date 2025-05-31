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

    public CapaCalculo() {
        this.numCapas = 0;
        this.espesor = 0;
        this.moduloElastico = 0;
        this.pesoNeumatico = 0;
        this.v = 0;
        this.RCC = 0;
        this.espesorParcialEquivalente = 0;
        this.convencion = 0;
        this.espesorEquivalenteCapa = 0;
        this.esfuerzoVerticalO = 0;
        this.esfuerzoRadialO = 0;
        this.esfuerzoTangencialO = 0;
        this.deformacionVerticalE = 0;
        this.deformacionRadialE = 0;
        this.deformacionTangencialE = 0;
        this.deflexionTotal = 0;
        this.esfuerzoNormalX = 0;
        this.esfuerzoNormalY = 0;
        this.esfuerzoCortanteXY = 0;
        this.deformacionVerticalE2 = 0;
        this.deformacionPorTension = 0;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.lesimaDeflexion = 0;
        this.esfuerzoCortanteRZ = 0;
        this.esfuerzoCortanteYZ = 0;
        this.esfuerzoCortanteXZ = 0;
    }

    public CapaCalculo(double  esfuerzoCortanteRZ,double esfuerzoCortanteYZ,
            double esfuerzoCortanteXZ,double e, double a, double b, double c,
            double d, double lesimaDeflexion, int numCapas, double espesor,
            double moduloElastico, double pesoNeumatico, double v, double RCC,
            double espesorParcialEquivalente, double convencion,
            double espesorEquivalenteCapa, double esfuerzoVerticalO,
            double esfuerzoRadialO, double esfuerzoTangencialO,
            double deformacionVerticalE, double deformacionRadialE,
            double deformacionTangencialE, double deflexionTotal,
            double esfuerzoNormalX, double esfuerzoNormalY,
            double esfuerzoCortanteXY, double deformacionVerticalE2,
            double deformacionPorTension) {
        
        this.numCapas = numCapas;
        this.espesor = espesor;
        this.moduloElastico = moduloElastico;
        this.pesoNeumatico = pesoNeumatico;
        this.v = v;
        this.RCC = RCC;
        this.espesorParcialEquivalente = espesorParcialEquivalente;
        this.convencion = convencion;
        this.espesorEquivalenteCapa = espesorEquivalenteCapa;
        this.esfuerzoVerticalO = esfuerzoVerticalO;
        this.esfuerzoRadialO = esfuerzoRadialO;
        this.esfuerzoTangencialO = esfuerzoTangencialO;
        this.deformacionVerticalE = deformacionVerticalE;
        this.deformacionRadialE = deformacionRadialE;
        this.deformacionTangencialE = deformacionTangencialE;
        this.deflexionTotal = deflexionTotal;
        this.esfuerzoNormalX = esfuerzoNormalX;
        this.esfuerzoNormalY = esfuerzoNormalY;
        this.esfuerzoCortanteXY = esfuerzoCortanteXY;
        this.deformacionVerticalE2 = deformacionVerticalE2;
        this.deformacionPorTension = deformacionPorTension;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
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

    @Override
    public String toString() {
        String r = "numCapas=" + numCapas + ", espesor=" + espesor + ", moduloElastico=" + moduloElastico + ", pesoNeumatico=" + pesoNeumatico + ", v=" + v + ", RCC=" + RCC + ", espesorParcialEquivalente=" + espesorParcialEquivalente + ", convencion=" + convencion + ", espesorEquivalenteCapa=" + espesorEquivalenteCapa + ", esfuerzoVerticalO=" + esfuerzoVerticalO + ", esfuerzoRadialO=" + esfuerzoRadialO + ", esfuerzoTangencialO=" + esfuerzoTangencialO + ", deformacionVerticalE=" + deformacionVerticalE + ", deformacionRadialE=" + deformacionRadialE + ", deformacionTangencialE=" + deformacionTangencialE + ", deflexionTotal=" + deflexionTotal + ", esfuerzoNormalX=" + esfuerzoNormalX + ", esfuerzoNormalY=" + esfuerzoNormalY + ", esfuerzoCortanteXY=" + esfuerzoCortanteXY + ", deformacionVerticalE2=" + deformacionVerticalE2 + ", deformacionPorTension=" + deformacionPorTension + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", lesimaDeflexion=" + lesimaDeflexion + ", esfuerzoCortanteRZ=" + esfuerzoCortanteRZ + ", esfuerzoCortanteYZ=" + esfuerzoCortanteYZ + ", esfuerzoCortanteXZ=" + esfuerzoCortanteXZ;     
        return r.replaceAll(",", "\n");
    }


}

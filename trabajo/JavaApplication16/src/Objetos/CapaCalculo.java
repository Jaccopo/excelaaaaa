package Objetos;

public class CapaCalculo {

    int numCapas;
    double espesor;
    double moduloElastico;
    double pesoNeumatico;
    double v;
    double RCC;
    double espesorParcialEquivalente;
    double convencion;
    double espesorEquivalenteCapa;
    double esfuerzoVerticalO, esfuerzoRadialO, esfuerzoTangencialO;
    double deformacionVerticalE, deformacionRadialE, deformacionTangencialE;
    double deflexionTotal;
    double esfuerzoNormalX, esfuerzoNormalY, esfuerzoCortanteXY;
    double deformacionVerticalE2, deformacionPorTension;

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
    }

    public CapaCalculo(int numCapas, double espesor, double moduloElastico, double pesoNeumatico, double v, double RCC, double espesorParcialEquivalente, double convencion, double espesorEquivalenteCapa, double esfuerzoVerticalO, double esfuerzoRadialO, double esfuerzoTangencialO, double deformacionVerticalE, double deformacionRadialE, double deformacionTangencialE, double deflexionTotal, double esfuerzoNormalX, double esfuerzoNormalY, double esfuerzoCortanteXY, double deformacionVerticalE2, double deformacionPorTension) {
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
        return "Capa{" + "numCapas=" + numCapas + ", espesor=" + espesor + ", moduloElastico=" + moduloElastico + ", pesoNeumatico=" + pesoNeumatico + ", v=" + v + ", RCC=" + RCC + ", espesorParcialEquivalente=" + espesorParcialEquivalente + ", convencion=" + convencion + ", espesorEquivalenteCapa=" + espesorEquivalenteCapa + ", esfuerzoVerticalO=" + esfuerzoVerticalO + ", esfuerzoRadialO=" + esfuerzoRadialO + ", esfuerzoTangencialO=" + esfuerzoTangencialO + ", deformacionVerticalE=" + deformacionVerticalE + ", deformacionRadialE=" + deformacionRadialE + ", deformacionTangencialE=" + deformacionTangencialE + ", deflexionTotal=" + deflexionTotal + ", esfuerzoNormalX=" + esfuerzoNormalX + ", esfuerzoNormalY=" + esfuerzoNormalY + ", esfuerzoCortanteXY=" + esfuerzoCortanteXY + ", deformacionVerticalE2=" + deformacionVerticalE2 + ", deformacionPorTension=" + deformacionPorTension + '}';
    }

}

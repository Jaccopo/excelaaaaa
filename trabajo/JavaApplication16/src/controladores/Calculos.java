/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

/**
 *
 * @author aldoj
 */
public class Calculos {
    float poisson = (float) 0.35;
    final double PI = 3.14159265358979;
    int numCapa;
    float espesor;//cm
    float modElastico;
    float pesoNeumatico;
    float v;
    float rcc;
    float espeParEqui;//espesor parcial equivalente cm
    float convencion;
    float espEquiCapa;//espesor parcial equivalente
    float esfuerzoVertical;
    float esfuerzoRadial;
    float esfuerzoTangencial;
    float deformacionVertical;
    float deformacionRadial;
    float deformacionTangencial;
    float deflexionTotal;
    float esfierzoNormalX;
    float esfuerzoNormalY;
    float esfuerzoCortanteXY;
    float deformacionVerticalE;
    float deformacionTension;
    
    float llantas[]= new float[4];// angulo, distancia horizontal,distancia radial (carpeta),distancia radial (subrasante)
    float F_E_Vertical_ra[] = new float[4];//r/a,factor e vertical,, factor e radial, factor e tangencial
    float llantasIntefaz[] = new float[4];// σr (Kpa) NEUMÁTICO	->1,2	 σθ (Kpa) NEUMÁTICO-> 3,4

    public Calculos(int numCapa, float espesor, float modElastico, float pesoNeumatico, float v, float rcc, float espeParEqui, float convencion, float espEquiCapa, float esfuerzoVertical, float esfuerzoRadial, float esfuerzoTangencial, float deformacionVertical, float deformacionRadial, float deformacionTangencial, float deflexionTotal, float esfierzoNormalX, float esfuerzoNormalY, float esfuerzoCortanteXY, float deformacionVerticalE, float deformacionTension) {
        this.numCapa = numCapa;
        this.espesor = espesor;
        this.modElastico = modElastico;
        this.pesoNeumatico = pesoNeumatico;
        this.v = v;
        this.rcc = rcc;
        this.espeParEqui = espeParEqui;
        this.convencion = convencion;
        this.espEquiCapa = espEquiCapa;
        this.esfuerzoVertical = esfuerzoVertical;
        this.esfuerzoRadial = esfuerzoRadial;
        this.esfuerzoTangencial = esfuerzoTangencial;
        this.deformacionVertical = deformacionVertical;
        this.deformacionRadial = deformacionRadial;
        this.deformacionTangencial = deformacionTangencial;
        this.deflexionTotal = deflexionTotal;
        this.esfierzoNormalX = esfierzoNormalX;
        this.esfuerzoNormalY = esfuerzoNormalY;
        this.esfuerzoCortanteXY = esfuerzoCortanteXY;
        this.deformacionVerticalE = deformacionVerticalE;
        this.deformacionTension = deformacionTension;
    }

    public int getNumCapa() {
        return numCapa;
    }

    public void setNumCapa(int numCapa) {
        this.numCapa = numCapa;
    }

    public float getEspesor() {
        return espesor;
    }

    public void setEspesor(float espesor) {
        this.espesor = espesor;
    }

    public float getModElastico() {
        return modElastico;
    }

    public void setModElastico(float modElastico) {
        this.modElastico = modElastico;
    }

    public float getPesoNeumatico() {
        return pesoNeumatico;
    }

    public void setPesoNeumatico(float pesoNeumatico) {
        this.pesoNeumatico = pesoNeumatico;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }

    public float getRcc() {
        return rcc;
    }

    public void setRcc(float rcc) {
        this.rcc = rcc;
    }

    public float getEspeParEqui() {
        return espeParEqui;
    }

    public void setEspeParEqui(float espeParEqui) {
        this.espeParEqui = espeParEqui;
    }

    public float getConvencion() {
        return convencion;
    }

    public void setConvencion(float convencion) {
        this.convencion = convencion;
    }

    public float getEspEquiCapa() {
        return espEquiCapa;
    }

    public void setEspEquiCapa(float espEquiCapa) {
        this.espEquiCapa = espEquiCapa;
    }

    public float getEsfuerzoVertical() {
        return esfuerzoVertical;
    }

    public void setEsfuerzoVertical(float esfuerzoVertical) {
        this.esfuerzoVertical = esfuerzoVertical;
    }

    public float getEsfuerzoRadial() {
        return esfuerzoRadial;
    }

    public void setEsfuerzoRadial(float esfuerzoRadial) {
        this.esfuerzoRadial = esfuerzoRadial;
    }

    public float getEsfuerzoTangencial() {
        return esfuerzoTangencial;
    }

    public void setEsfuerzoTangencial(float esfuerzoTangencial) {
        this.esfuerzoTangencial = esfuerzoTangencial;
    }

    public float getDeformacionVertical() {
        return deformacionVertical;
    }

    public void setDeformacionVertical(float deformacionVertical) {
        this.deformacionVertical = deformacionVertical;
    }

    public float getDeformacionRadial() {
        return deformacionRadial;
    }

    public void setDeformacionRadial(float deformacionRadial) {
        this.deformacionRadial = deformacionRadial;
    }

    public float getDeformacionTangencial() {
        return deformacionTangencial;
    }

    public void setDeformacionTangencial(float deformacionTangencial) {
        this.deformacionTangencial = deformacionTangencial;
    }

    public float getDeflexionTotal() {
        return deflexionTotal;
    }

    public void setDeflexionTotal(float deflexionTotal) {
        this.deflexionTotal = deflexionTotal;
    }

    public float getEsfierzoNormalX() {
        return esfierzoNormalX;
    }

    public void setEsfierzoNormalX(float esfierzoNormalX) {
        this.esfierzoNormalX = esfierzoNormalX;
    }

    public float getEsfuerzoNormalY() {
        return esfuerzoNormalY;
    }

    public void setEsfuerzoNormalY(float esfuerzoNormalY) {
        this.esfuerzoNormalY = esfuerzoNormalY;
    }

    public float getEsfuerzoCortanteXY() {
        return esfuerzoCortanteXY;
    }

    public void setEsfuerzoCortanteXY(float esfuerzoCortanteXY) {
        this.esfuerzoCortanteXY = esfuerzoCortanteXY;
    }

    public float getDeformacionVerticalE() {
        return deformacionVerticalE;
    }

    public void setDeformacionVerticalE(float deformacionVerticalE) {
        this.deformacionVerticalE = deformacionVerticalE;
    }

    public float getDeformacionTension() {
        return deformacionTension;
    }

    public void setDeformacionTension(float deformacionTension) {
        this.deformacionTension = deformacionTension;
    }

    public float[] getLlantas() {
        return llantas;
    }

    public void setLlantas(float[] llantas) {
        this.llantas = llantas;
    }

    public float[] getF_E_Vertical_ra() {
        return F_E_Vertical_ra;
    }

    public void setF_E_Vertical_ra(float[] F_E_Vertical_ra) {
        this.F_E_Vertical_ra = F_E_Vertical_ra;
    }

    public float[] getLlantasIntefaz() {
        return llantasIntefaz;
    }

    public void setLlantasIntefaz(float[] llantasIntefaz) {
        this.llantasIntefaz = llantasIntefaz;
    }
    
            
}

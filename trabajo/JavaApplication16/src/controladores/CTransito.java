/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

/**
 *
 * @author aldoj
 */
public class CTransito {

    double A, B2, B3, C2, C3, T3S2, T3S3, T3S2R4, otros;
    double Tasa, TDPA, años, FDS, FDC;
    double CoefAcumTrans, TTotalAcum, Trans_Prom_anual;
    double Porcentaje_sencillo, Porcentaje_dual, Porcentaje_tandem, Porcentaje_tridem;
    double RepEjes_sencillo, RepEjes_dual, RepEjes_tandem, RepEjes_tridem;
    double NumEjes_Sencillos_año, NumEjes_dual_año, NumEjes_tandem_año, NumEjes_tridem_año;
    double TransProm_anual_carga, Total_ejes;
    double SumaPorcentajes;

    

    public CTransito() {
        CoefAcumTrans = 0;
        TTotalAcum = 0;
        Trans_Prom_anual = 0;
        Porcentaje_sencillo = 0;
        Porcentaje_dual = 0;
        Porcentaje_tandem = 0;
        Porcentaje_tridem = 0;
        RepEjes_sencillo = 0;
        RepEjes_dual = 0;
        RepEjes_tandem = 0;
        RepEjes_tridem = 0;
        NumEjes_Sencillos_año = 0;
        NumEjes_dual_año = 0;
        NumEjes_tandem_año = 0;
        NumEjes_tridem_año = 0;
        TransProm_anual_carga = 0;
        Total_ejes = 0;
        SumaPorcentajes = 0;
    }
    //Getters and setters
    public double getNumEjes_Sencillos_año() {
        return NumEjes_Sencillos_año;
    }

    public double getNumEjes_dual_año() {
        return NumEjes_dual_año;
    }

    public double getNumEjes_tandem_año() {
        return NumEjes_tandem_año;
    }

    public double getNumEjes_tridem_año() {
        return NumEjes_tridem_año;
    }
    
    public double getA() {
        return A;
    }

    public void setA(double A) {
        this.A = A;
    }

    public double getB2() {
        return B2;
    }

    public void setB2(double B2) {
        this.B2 = B2;
    }

    public double getB3() {
        return B3;
    }

    public void setB3(double B3) {
        this.B3 = B3;
    }

    public double getC2() {
        return C2;
    }

    public void setC2(double C2) {
        this.C2 = C2;
    }

    public double getC3() {
        return C3;
    }

    public void setC3(double C3) {
        this.C3 = C3;
    }

    public double getT3S2() {
        return T3S2;
    }

    public void setT3S2(double T3S2) {
        this.T3S2 = T3S2;
    }

    public double getT3S3() {
        return T3S3;
    }

    public void setT3S3(double T3S3) {
        this.T3S3 = T3S3;
    }

    public double getT3S2R4() {
        return T3S2R4;
    }

    public void setT3S2R4(double T3S2R4) {
        this.T3S2R4 = T3S2R4;
    }

    public double getOtros() {
        return otros;
    }

    public void setOtros(double otros) {
        this.otros = otros;
    }

    public double getTasa() {
        return Tasa;
    }

    public void setTasa(double Tasa) {
        this.Tasa = Tasa;
    }

    public double getTDPA() {
        return TDPA;
    }

    public void setTDPA(double TDPA) {
        this.TDPA = TDPA;
    }

    public double getAños() {
        return años;
    }

    public void setAños(double años) {
        this.años = años;
    }

    public double getFDS() {
        return FDS;
    }

    public void setFDS(double FDS) {
        this.FDS = FDS;
    }

    public double getFDC() {
        return FDC;
    }

    public void setFDC(double FDC) {
        this.FDC = FDC;
    }

    public String sumaPorcentaje() {
        SumaPorcentajes = A + B2 + B3 + C2 + C3 + T3S2 + T3S3 + T3S2R4 + otros;
        return SumaPorcentajes + "";
    }

    public void operar() {
        Tasa = Tasa / 100;
        
        double a1 =años;
        double v1= 1+(Tasa);
        
        CoefAcumTrans = 365 * ((Math.pow(v1,a1) -1)/ Tasa);
     
        TTotalAcum = TDPA * CoefAcumTrans * FDS * FDC;
        Trans_Prom_anual = TTotalAcum / años;

        Porcentaje_sencillo = (B2 + B3 + C2 + C3 + T3S2 + T3S3 + T3S2R4) / 100;
        Porcentaje_dual = (B2 + C2) / 100;
        Porcentaje_tandem = (B3 + C3 + T3S2 * 2 + T3S3 + T3S2R4 * 4) / 100;
        Porcentaje_tridem = T3S3 / 100;

        RepEjes_sencillo = TTotalAcum * Porcentaje_sencillo;
        RepEjes_dual = TTotalAcum * Porcentaje_dual;
        RepEjes_tandem = TTotalAcum * Porcentaje_tandem;
        RepEjes_tridem = TTotalAcum * Porcentaje_tridem;

        NumEjes_Sencillos_año = RepEjes_sencillo / años;
        NumEjes_dual_año = RepEjes_dual / años;
        NumEjes_tandem_año = RepEjes_tandem / años;
        NumEjes_tridem_año = RepEjes_tridem / años;

        TransProm_anual_carga = RepEjes_sencillo / años;
        Total_ejes = NumEjes_Sencillos_año + NumEjes_dual_año + NumEjes_tandem_año + NumEjes_tridem_año;
    }
    
    public double numsen(){
        //System.out.println(""+ NumEjes_Sencillos_año);
        return 100 * NumEjes_Sencillos_año / Total_ejes;
    }
    public double numdua(){
        //System.out.println(""+ NumEjes_dual_año);
        return 100 * NumEjes_dual_año / Total_ejes;
    }
    public double numtran(){
        //System.out.println(""+ NumEjes_tandem_año);
        return 100 * NumEjes_tandem_año / Total_ejes;
    }
    public double numtri(){
               // System.out.println(""+ NumEjes_tridem_año);

        return 100 * NumEjes_tridem_año / Total_ejes;
    }
}

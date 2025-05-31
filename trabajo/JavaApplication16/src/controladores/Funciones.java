package controladores;

import Clases.CargarClases;
import Datos.TablaDistribucionDeCarga;
import Objetos.CapaCalculo;
import Objetos.EstructuraPavimiento;
import Objetos.Llantas;

public class Funciones {

    private TablaDistribucionDeCarga tdc;
    private CargarClases cc;
    private final double PI = 3.14159265358979;
    private double poisson, presion;
    private boolean seleccion[];
    private int numCapas;
    private EstructuraPavimiento ep[];//private DefaultTableModel dtm;
    private Llantas llantas;
    private CapaCalculo capaCalculo[];
    
    
    public void Esfuerzos_Damy(String tipoeje, int numCapa) {

        double xp, yp, parcial, suma, sigmaZ,Q,z,radio,SD,xq,yq,sumaporllanta;
        int num_de_calculos,calculos;
        double[] X = new double[13];//(1 To 13) As Double
        double[] Y = new double[13];//(1 To 13) As Double
        double[] xprima = new double[13];//(1 To 13) As Double
        double[] yprima = new double[13];//(1 To 13) As Double
        double[][] Teta = new double[2][12];//(1 To 2, 1 To 12) As Double
        double[][] C = new double[2][12];// As Double
        double[] F = new double[12];// As Double
        double[] A = new double[12];// As Double
        double[] L = new double[12];// As Double
        double[][] B = new double[2][12];// As Double
        double[][] W = new double[2][12];// As Double
        double[][] j = new double[2][12];// As Double
        double[][] n = new double[2][12];// As Double
        double[] posicionx = new double[6];
        double[] posiciony = new double[6];
        double[] llantax = new double[6];
        double[] llantay = new double[6];
        //Dim Pi As Double

         
         
         
         
         
           'datos de entrada
Q = 6.895 * Sheets("NuevoFormatoPav").Cells(9, 6)
        radio = Sheets("calculos").Cells(3, 6) / 100
        xq = Sheets("avanzado").Cells(17, 2) / 100
        yq = Sheets("avanzado").Cells(18, 2) / 100
        S = Sheets("avanzado").Cells(17, 4) / 100
        D = Sheets("avanzado").Cells(18, 4) / 100

        llantax(1) = 0
        llantax(2) = 0
        llantax(3) = D
        llantax(4) = D
        llantax(5) = 2 * D
        llantax(6) = 2 * D
        llantay(1) = 0
        llantay(2) = S
        llantay(3) = 0
        llantay(4) = S
        llantay(5) = 0
        llantay(6) = S

        Select Case tipoeje Case "Sencillo"
        calculos = 1
    Case "Sencillo Dual"
        calculos = 2
    Case "Tandem"
        calculos = 4
    Case "Tridem"
        calculos = 6
        End Select


        z = Sheets("calculos").Cells(2 + NumCapa, 7) / 100

        Pi = 3.14159265358979
        sumaporllanta = 0
    
        For num_de_calculos = 1 To calculos
      
        posicionx(num_de_calculos) = Abs(llantax(num_de_calculos) - xq)
        posiciony(num_de_calculos) = Abs(llantay(num_de_calculos) - yq)
      
        xp = posicionx(num_de_calculos)
        yp = posiciony(num_de_calculos)
      
        For i = 1 To 12
            
            X(i) = radio * Cos((i - 1) * Pi / 6)
        Y(i) = radio * Sin((i - 1) * Pi / 6)
        X(1 + i) = radio * Cos(i * Pi / 6)
        Y(1 + i) = radio * Sin(i * Pi / 6)
            
        xprima(i) = X(i) - xp
        xprima(i + 1) = X(i + 1) - xp
        yprima(i) = Y(i) - yp
        yprima(i + 1) = Y(i + 1) - yp
            
        F(i) = xprima(i) * yprima(i + 1) - xprima(i + 1) * yprima(i)
        L(i) = ((xprima(i + 1) - xprima(i)) ^ 2 + (yprima(i + 1) - yprima(i)) ^ 2) ^ (1 / 2)
        A(i) = Abs(z * L(i) / F(i))
        C(1, i) = (xprima(i) * (xprima(i + 1) - xprima(i)) + yprima(i) * (yprima(i + 1) - yprima(i))) / F(i)
        C(2, i) = (xprima(i + 1) * (xprima(i + 1) - xprima(i)) + yprima(i + 1) * (yprima(i + 1) - yprima(i))) / F(i)
            
        Teta(1, i) = Atn(C(1, i))
        Teta(2, i) = Atn(C(2, i))
            
        B(1, i) = (A(i) * C(1, i)) / ((1 + ((A(i)) ^ 2) + ((C(1, i)) ^ 2)) ^ (1 / 2))
        B(2, i) = (A(i) * C(2, i)) / ((1 + ((A(i)) ^ 2) + ((C(2, i)) ^ 2)) ^ (1 / 2))
        W(1, i) = (1 * A(i) * C(1, i)) / ((1 + 1 ^ 2 * ((A(i)) ^ 2 + (C(1, i)) ^ 2)) ^ (1 / 2))
        W(2, i) = (2 * A(i) * C(2, i)) / ((1 + 1 ^ 2 * ((A(i)) ^ 2 + (C(2, i)) ^ 2)) ^ (1 / 2))
        j(1, i) = C(1, i) / ((1 + (A(i)) ^ 2) ^ (1 / 2))
        j(2, i) = C(2, i) / ((1 + (A(i)) ^ 2) ^ (1 / 2))
        n(1, i) = (A(i) ^ 2 * C(1, i)) / (1 + A(i) ^ 2 + C(1, i) ^ 2)
        n(2, i) = (A(i) ^ 2 * C(2, i)) / (1 + A(i) ^ 2 + C(2, i) ^ 2)
                          
        parcial = Teta(2, i) - Teta(1, i) - Atn(B(2, i)) + Atn(B(1, i)) + (B(2, i) - B(1, i)) / (A(i) ^ 2 + 1)
        suma = suma + parcial
        
        Next i
        sigmaZ = (Q * suma) / (2 * Pi)
        Sheets("calculos").Cells(30 + num_de_calculos, 2 + NumCapa) = sigmaZ  
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
        ' es el resultado de un calculo o un area y una profundidad
      suma = 0
        sumaporllanta = sumaporllanta + sigmaZ
        Next num_de_calculos
        Sheets("calculos").Cells(5 + NumCapa, 10) = sumaporllanta
    }

}

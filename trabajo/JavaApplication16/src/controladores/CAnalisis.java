package controladores;

import Clases.CargarClases;
import Datos.TablaDistribucionDeCarga;
import Objetos.CapaCalculo;
import Objetos.Llanta;
import Objetos.Llantas;
import javax.swing.JOptionPane;

public class CAnalisis {

    private TablaDistribucionDeCarga tdc;
    private CargarClases cc;
    private final double PI = 3.14159265358979;
    private double poisson, presion;
    private boolean seleccion[];
    private int numCapas;
    private Objetos.EstructuraPavimiento ep[];//private DefaultTableModel dtm;
    private Llantas llantas;
    private CapaCalculo capaCalculo[];

    //datoss en cm
    private final double X = 0;
    private final double Y = 18;
    private final double S = 36;
    private final double D = 122;

    //variables auxiliares
    private double aux;

    public CAnalisis(CargarClases cc) {
        this.cc = cc;
        this.poisson = 0.35; // poisson por defecto
        this.presion = 90; // presion por defecto
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public double getPoisson() {
        return this.poisson;
    }

    public void setPoisson(double poisson) {
        this.poisson = poisson;
    }

    public CargarClases getClases() {
        return this.cc;
    }

    public void IniciarAnalisisEspectral(String tipoCarga, boolean[] seleccion, Objetos.EstructuraPavimiento ep[]) {

        this.seleccion = seleccion;
        this.numCapas = ep.length;
        this.ep = ep;
        if (numCapas > 3) {
            JOptionPane.showMessageDialog(null, "Solo se puede calcular tres capas", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (tipoCarga) {
                case "Legal" -> {
                    tdc = new TablaDistribucionDeCarga(1);
                }
                case "Ligera Sobrecarga" -> {

                    tdc = new TablaDistribucionDeCarga(2);
                }
                case "Alta Sobrecarga" -> {

                    tdc = new TablaDistribucionDeCarga(3);
                }
                case "Muy Alta Sobrecarga" -> {

                    tdc = new TablaDistribucionDeCarga(4);
                }
                case "Avanzado" -> {
                    tdc = new TablaDistribucionDeCarga(5);
                }
            }
            espectros();
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 100; i++) {
                    calculoDiferencial(j + 1, cc.data[i].getCargaPromedio());
                }
            }

        }

    }

    private void espectros() {
        var valor = 0.0;
        var altura = 0.0;

        var ayuda1 = 0.0;
        var ayuda2 = 0.0;
        var ayuda3 = 0.0;
        for (int j = 0; j < 4; j++) {
            var w1 = tdc.getW1()[j];
            var w2 = tdc.getW2()[j];
            var w3 = tdc.getW3()[j];

            var m1 = tdc.getM1()[j];
            var m2 = tdc.getM2()[j];
            var m3 = tdc.getM3()[j];

            var s1 = tdc.getS1()[j] == 0 ? 0.000000001 : tdc.getS1()[j];
            var s2 = tdc.getS2()[j] == 0 ? 0.000000001 : tdc.getS2()[j];
            var s3 = tdc.getS3()[j] == 0 ? 0.000000001 : tdc.getS3()[j];

            for (int i = 0; i < 100; i++) {
                valor = (1 + i) * 0.5;

                ayuda1 = (Math.log(valor) - m1) / s1;
                ayuda2 = (Math.log(valor) - m2) / s2;
                ayuda3 = (Math.log(valor) - m3) / s3;

                altura = w1 / (2.506628274631 * valor * s1) * Math.exp(-0.5 * Math.pow(ayuda1, 2))
                        + w2 / (2.506628274631 * valor * s2) * Math.exp(-0.5 * Math.pow(ayuda2, 2))
                        + w3 / (2.506628274631 * valor * s3) * Math.exp(-0.5 * Math.pow(ayuda3, 2));
                cc.data[i].setDato(i + 1);
                cc.data[i].setCargaPromedio((float) valor);

                switch (j + 1) {
                    case 1 -> {
                        cc.data[i].setSimple((float) altura * 100 > 0.01 ? 0 : (float) altura * 100);
                    }
                    case 2 -> {
                        cc.data[i].setDual((float) altura * 100 > 0.01 ? 0 : (float) altura * 100);
                    }
                    case 3 -> {
                        cc.data[i].setTridem((float) altura * 100 > 0.01 ? 0 : (float) altura * 100);
                    }
                    case 4 -> {
                        cc.data[i].setTandem((float) altura * 100 > 0.01 ? 0 : (float) altura * 100);
                    }
                }
            }

        }
        /* for (int i = 0; i < 100; i++) {
            System.out.println(cc.data[i].toString());
        }*/

    }

    private void calculoDiferencial(int tipoeje, float pesoEje) {

        //se inicia el objeto capaCalculo y llantas
        capaCalculo1 = new Objetos.CapaCalculo[ep.length];//donde se iran guardando los calculos creo jaja ta raro porque es de gmmmmm
        llantas = new Llantas();

        for (int i = 0; i < ep.length; i++) {
            capaCalculo1[i] = new Objetos.CapaCalculo();
        }

        //Se inician las variables
        double pesoNeum, radioContacto;
        int numLlantas;// u no sirve ya que era utilizada para sobre escribir en el excel
        double espesor[] = new double[numCapas];
        double modElastico[] = new double[numCapas];
        double fCorrecion[] = new double[numCapas];
        double he[] = new double[numCapas - 1];

        numLlantas = tipoeje == 1 ? 1 : (tipoeje == 2 ? 2 : (tipoeje == 3 ? 4 : 6));// 1.- sencilla 1 llanta, 2.- dual dos llantas. 3.-tandem 4 llantas, 4.-tridem 6 llantas

        // operaciones
        pesoNeum = pesoEje / (2 * numLlantas);
        radioContacto = ((pesoNeum * 2204.623) / Math.pow(PI * this.presion, 0.5)) * 2.54;

        capaCalculo1[0].setRCC(radioContacto);
        capaCalculo1[0].setV(this.poisson);
        capaCalculo1[0].setPesoNeumatico(pesoNeum);

        for (int j = 0; j < numCapas; j++) {
            espesor[j] = Double.parseDouble(ep[j].getEspesor() + "");
            modElastico[j] = Double.parseDouble(ep[j].getModulo() + "") * 1000;
            fCorrecion[j] = numLlantas == 1 ? 0.9 : (j == 0 ? 1 : 0.8);// ni idea el porque pero bueno esto es asi
        }

        for (int j = 0; j < numCapas - 1; j++) {
            he[j] = fCorrecion[j] * (espesor[j] + he[(j - 1 == 0 ? j - 1 : 0)]) * Math.pow((modElastico[j] / modElastico[j + 1]), (1 / 3));
            System.out.println(he[j]);
            if (j == 0 || j == numCapas - 1) {
                capaCalculo1[j].setEspesorParcialEquivalente(he[j]);
            }
        }

        if (numCapas == 2) {
            capaCalculo1[1].setEspesorParcialEquivalente(capaCalculo1[0].getEspesorParcialEquivalente());
        }

        capaCalculo1[0].setModuloElastico(modElastico[1]);
        capaCalculo1[1].setModuloElastico(modElastico[numCapas - 1]);

    }

    public void coordenadas() {
        double r1[] = new double[4];
        double rllanta[] = new double[6];//rLlanta1, rLlanta2, rLlanta3, rLlanta4, rLlanta5, rLlanta6;
        double rLlantaCapa[] = new double[6];/*rLlantaCapa1, rLlantaCapa2, rLlantaCapa3, rLlantaCapa4, rLlantaCapa5, rLlantaCapa6,*/
        double rLlantaCapaNatur[] = new double[6];//rLlantaCapa1Natur, rLlantaCapa2Natur, rLlantaCapa3Natur, rLlantaCapa4Natur, rLlantaCapa5Natur, rLlantaCapa6Natur;
        double anguloLlanta[] = new double[6];//anguloLlanta1, anguloLlanta2, anguloLlanta3, anguloLlanta4, anguloLlanta5, anguloLlanta6;
        double fEVerticalra1[] = new double[6];//fEVerticalra11, fEVerticalra12, fEVerticalra13, fEVerticalra14;
        double interfaz[];
        double rContactoCircular = capaCalculo1[0].getRCC();
        interfaz = new double[numCapas];

        interfaz[0] = capaCalculo1[0].getEspesorParcialEquivalente();
        interfaz[1] = capaCalculo1[1].getEspesorParcialEquivalente();

        rllanta[0] = Math.pow((Math.pow(X, 2) + Math.pow(Y, 2)), 0.5);
        rllanta[1] = Math.pow((Math.pow(X, 2) + Math.pow((Y - S), 2)), 0.5);
        rllanta[2] = Math.pow((Math.pow((X - D), 2) + Math.pow(Y, 2)), 0.5);
        rllanta[3] = Math.pow((Math.pow((X - D), 2) + Math.pow((Y - S), 2)), 0.5);
        rllanta[4] = Math.pow((Math.pow((X - 2 * D), 2) + Math.pow(Y, 2)), 0.5);
        rllanta[5] = Math.pow((Math.pow((X - 2 * D), 2) + Math.pow((Y - S), 2)), 0.5);

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < rllanta.length; i++) {

                if (j == 0) {
                    rLlantaCapa[i] = Math.pow(Math.pow(rllanta[i], 2) + Math.pow(interfaz[0], 2), 0.5);
                }
                if (j == 1) {
                    rLlantaCapaNatur[i] = Math.pow(Math.pow(rllanta[i], 2) + Math.pow(interfaz[1], 2), 0.5);
                }
            }
        }

        anguloLlanta[0] = X == 0 ? 90 : Math.atan(Math.abs(Y) / X) / 1.74532925199433E-02;
        anguloLlanta[1] = X == 0 ? 90 : Math.atan(Math.abs((Y - S) / X)) / 1.74532925199433E-02;

        anguloLlanta[2] = X == D ? 90 : Math.atan(Math.abs(Y / (X - D))) / 1.74532925199433E-02;
        anguloLlanta[3] = X == D ? 90 : Math.atan(Math.abs((Y - S) / (X - D))) / 1.74532925199433E-02;

        anguloLlanta[4] = X == 2 * D ? 90 : Math.atan(Math.abs(Y / (X - 2 * D))) / 1.74532925199433E-02;
        anguloLlanta[5] = X == 2 * D ? 90 : Math.atan(Math.abs((Y - S) / (X - 2 * D))) / 1.74532925199433E-02;

        for (int i = 0; i < 4; i++) {
            r1[i] = rllanta[i] / rContactoCircular;
            fEVerticalra1[i] = r1[i] < 14 ? r1[i] : 13.99;
        }

        for (int i = 0; i < 6; i++) {
            llantas.getLlanta(i).setAnguloHorizontal(anguloLlanta[i]);
            llantas.getLlanta(i).setDistanciaHorizontal(rllanta[i]);
            llantas.getLlanta(i).setDistanciaRadialSubrasante(rLlantaCapa[i]);
            llantas.getLlanta(i).setDistanciaRadialCarpeta(rLlantaCapaNatur[i]);
        }

        capaCalculo1[0].setEsfuerzoCortanteRZ(fEVerticalra1[0]);
        capaCalculo1[0].setEsfuerzoCortanteYZ(fEVerticalra1[1]);
        capaCalculo1[0].setEsfuerzoCortanteXZ(fEVerticalra1[2]);
        capaCalculo1[0].setDeformacionVerticalE2(fEVerticalra1[3]);

    }

    public void Factores_Aglvin() {
        int n;
        double x_inter[], y_inter[];
        x_inter = new double[18];
        y_inter = new double[23];
        double r_sobre_a;
        double z_sobre_a;
        double X;
        double Y;
        int buscax;
        int buscay;

        int posx1 = 0, posx2 = 0, posy1 = 0, posy2 = 0;

        double valorA;
        double valref;
        double valorC;
        double valorI;
        double valorK;
        double llantas;

        double res_interpolacion1;
        double res_interpolacion2;
        double res_interpolacion3;

        double radioContacto;
        double z;

        x_inter[0] = 0;
        x_inter[1] = 0.2;
        x_inter[2] = 0.4;
        x_inter[3] = 0.6;
        x_inter[4] = 0.8;
        x_inter[5] = 1;
        x_inter[6] = 1.2;
        x_inter[7] = 1.5;
        x_inter[8] = 2;
        x_inter[9] = 3;
        x_inter[10] = 4;
        x_inter[11] = 5;
        x_inter[12] = 6;
        x_inter[13] = 7;
        x_inter[14] = 8;
        x_inter[15] = 10;
        x_inter[16] = 12;
        x_inter[17] = 14;

        y_inter[0] = 0;
        y_inter[1] = 0.1;
        y_inter[2] = 0.2;
        y_inter[3] = 0.3;
        y_inter[4] = 0.4;
        y_inter[5] = 0.5;
        y_inter[6] = 0.6;
        y_inter[7] = 0.7;
        y_inter[8] = 0.8;
        y_inter[9] = 0.9;
        y_inter[10] = 1;
        y_inter[11] = 1.2;
        y_inter[12] = 1.5;
        y_inter[13] = 2;
        y_inter[14] = 2.5;
        y_inter[15] = 3;
        y_inter[16] = 4;
        y_inter[17] = 5;
        y_inter[18] = 6;
        y_inter[19] = 7;
        y_inter[20] = 8;
        y_inter[21] = 9;
        y_inter[22] = 10;

        for (int i = 0; i < 4; i++) {
            radioContacto = capaCalculo1[0].getRCC();
            z = capaCalculo1[0].getEspesorParcialEquivalente();
            r_sobre_a = this.llantas.getLlanta(i).getRa();
            z_sobre_a = z / radioContacto;

            r_sobre_a = r_sobre_a > 14 ? 13.99 : r_sobre_a;
            z_sobre_a = z_sobre_a > 10 ? 9.99 : z_sobre_a;

            X = r_sobre_a;
            Y = z_sobre_a;

            for (buscax = 0; buscax < 18; buscax++) {
                if (X >= x_inter[buscax] && X < x_inter[buscax + 1]) {
                    posx1 = buscax;
                    posx2 = buscax + 1;
                    break;
                }
            }
            for (buscay = 0; buscay < 23; buscay++) {
                if (Y >= y_inter[buscay] && Y < y_inter[buscay + 1]) {
                    posy1 = buscay;
                    posy2 = buscay + 1;
                    break;
                }
            }

            valorA = y_inter[buscay];
            valorC = y_inter[buscay + 1];
            valorI = Datos.FactorEVertical.getValor(posy1, posx1);//Sheets("factor_e_vert").Cells(posy1 + 2, posx1 + 2)
            valorK = Datos.FactorEVertical.getValor(posy2, posx1);

            valref = Y;

            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK);

            valorI = Datos.FactorEVertical.getValor(posy1, posx2);// Sheets("factor_e_vert").Cells(posy1 + 2, posx1 + 2)  Peguntar
            valorK = Datos.FactorEVertical.getValor(posy2, posx2);

            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK);

            valorA = x_inter[buscax];
            valref = X;
            valorC = x_inter[buscax + 1];

            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2);

            this.llantas.getLlanta(i).setDistanciaHorizontal(res_interpolacion3);

            //intepolación para la segunda tabla
            valorA = y_inter[buscay];
            valorC = y_inter[buscay + 1];
            valorI = Datos.FactorERadial.getValor(posy1, posx1);
            valorK = Datos.FactorERadial.getValor(posy2, posx1);

            valref = Y;

            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK);

            valorI = Datos.FactorERadial.getValor(posy1, posx2);
            valorK = Datos.FactorERadial.getValor(posy2, posx2);

            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK);

            valorA = x_inter[buscax];
            valref = X;
            valorC = x_inter[buscax + 1];

            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2);

            this.llantas.getLlanta(i).setDistanciaRadialCarpeta(res_interpolacion3);

            //interpolación para la tercera tabla
            valorA = y_inter[buscay];
            valorC = y_inter[buscay + 1];
            valorI = Datos.FactorETangencial.getValor(posy1, posx1);
            valorK = Datos.FactorETangencial.getValor(posy2, posx1);

            valref = Y;

            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK);

            valorI = Datos.FactorETangencial.getValor(posy1, posx2);
            valorK = Datos.FactorETangencial.getValor(posy2, posx2);

            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK);

            valorA = x_inter[buscax];
            valref = X;
            valorC = x_inter[buscax + 1];

            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2);

            this.llantas.getLlanta(i).setDistanciaRadialSubrasante(res_interpolacion3);

        }
    }

    private double interpola(double a, double b, double c, double i, double k) {
        return (((k - i) * (b - a)) / (c - a)) + i;
    }

    private void deflexionCircular(double pesoEje, String tipoEje) {

        double numLlantas = 0;
        double pesoNeum;
        double radioContacto;
        // ya definida double PresionInflado;
        // ya definida int NumCapas As Integer '
        double espesor[];
        double modElastico[];
        //ua definida double Poisson As Double '
        double fCorreccion[];
        int i;
        double he[], z[], dz[];
        double A;
        double psiAMpa;
        double deflexionTotal;

        espesor = new double[numCapas];
        modElastico = new double[numCapas];
        fCorreccion = new double[numCapas];
        he = new double[numCapas - 1];
        z = new double[numCapas];
        dz = new double[numCapas];

        switch (tipoEje) {
            case ("Sencillo") -> {
                numLlantas = 1;
            }
            case ("Sencillo Dual") -> {
                numLlantas = 2;
            }
            case ("Tandem") -> {
                numLlantas = 4;
            }
            case ("Tridem") -> {
                numLlantas = 6;
            }
            case ("Medio Tridem") -> {
                numLlantas = 3;
            }
        }

        pesoNeum = pesoEje / (2 * numLlantas);
        radioContacto = Math.pow(((pesoNeum * 2204.623) / (PI * presion)), 0.5) * 2.54;

        for (int j = 0; j < numCapas; j++) {
            espesor[j] = ep[j].getEspesor();
            modElastico[j] = ep[j].getModulo();//Sheets("NuevoFormatoPav").Cells(13 + i, 6)
            fCorreccion[j] = numCapas == 2 ? 0.9 : numCapas == 1 ? 1 : 0.8;

        }

        for (i = 0; i < numCapas - 1; i++) {
            he[i] = fCorreccion[i] * (espesor[i] + he[i - 1]) * Math.pow((modElastico[i] / modElastico[i + 1]), (1 / 3));
            this.llantas.getLlanta(i).setValorCapa(he[i]);
        }

        psiAMpa = 0.00689475719;
        deflexionTotal = 0;
        A = radioContacto;

        for (i = 0; i < 10; i++) {
            if (i != numCapas) {
                double ayu1 = (1 / Math.pow(Math.pow(1 + ((espesor[i] + he[i - 1]) / A), 2), 0.5)) + (1 - 2 * poisson);
                double ayu = ((1 + Math.pow(Math.pow(((espesor[i] + he[i - 1]) / A), 2), 0.5) - (espesor[i] + he[i - 1]) / A));

                dz[i] = (((1 + poisson) * presion * psiAMpa * A) / (modElastico[i] * 0.001))
                        * (1 / Math.pow(Math.pow(1 + (he[i - 1] / A), 2), 0.5) + (1 - 2 * poisson)
                        * (Math.pow((1 + Math.pow((he[i - 1] / A), 2)), 0.5) - he[i - 1] / A))
                        - (((1 + poisson) * presion * psiAMpa * A) / (modElastico[i] * 0.001)) * ayu1 * ayu;
            } else {
                dz[i] = (((1 + poisson) * presion * psiAMpa * A) / (modElastico[i] * 0.001))
                        * (1 / Math.pow((1 + Math.pow((he[i - 1] / A), 2)), 0.5)
                        + (1 - 2 * poisson) * (Math.pow(Math.pow(1 + (he[i - 1] / A), 2), 0.5) - he[i - 1] / A));
            }
            deflexionTotal = deflexionTotal + dz[i];
        }

        this.capaCalculo1[0].setDeflexionTotal(deflexionTotal * 10);
    }

    private void esfuerzosEjeSencillo() {
        double evx, er1, et1, radioContacto;
        double he[] = new double[numCapas];
        String tipoEje;
        radioContacto=this.capaCalculo1[0].getRCC();
        
        
        for (int i = 0; i < numCapas-1; i++) {
            //Call Esfuerzos_Damy(tipoeje, NumCapa);
        }
        /*
    Dim i As Integer
    Dim NumCapas As Integer
    Dim EvZ, Er1, Et1 As Double
    Dim he() As Double
    Dim PresionInflado, RadioContacto, Poisson As Double
    Dim tipoeje As String
    Dim NumCapa As Integer


    NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
    PresionInflado = Sheets("NuevoFormatoPav").Cells(9, 6)
    RadioContacto = Sheets("calculos").Cells(3, 6)
    Poisson = Sheets("calculos").Cells(3, 5)
    tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)


    ReDim he(NumCapas)

    'Esfuerzo Vertical, Radial y Tangencial
    For i = 1 To 2 'NumCapas - 1

        he(i) = Sheets("calculos").Cells(2 + i, 7)

        NumCapa = i
        Call Esfuerzos_Damy(tipoeje, NumCapa)

        EvZ = 6.894757 * (PresionInflado * (1 - ((he(i) ^ 3) / ((RadioContacto ^ 2 + he(i) ^ 2) ^ 1.5))))
        Er1 = 6.894757 * (PresionInflado / 2) * (1 + (2 * Poisson) - (((2 * he(i) * (1 + Poisson)) / ((RadioContacto ^ 2 + he(i) ^ 2) ^ 0.5))) + (((he(i) ^ 3) / ((RadioContacto ^ 2 + he(i) ^ 2) ^ 1.5))))
        Et1 = Er1

        Sheets("calculos").Cells(2 + i, 10) = EvZ
        Sheets("calculos").Cells(2 + i, 11) = Er1
        Sheets("calculos").Cells(2 + i, 12) = Et1
        Sheets("calculos").Cells(41, 2 + i) = Er1
        Sheets("calculos").Cells(41, 4 + i) = Et1

    Next i*/
    }

}

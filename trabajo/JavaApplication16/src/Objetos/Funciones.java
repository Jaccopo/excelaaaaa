package Objetos;

import Objetos.Datos;
import DatosTablas.TablaDistribucionDeCarga;
import javax.swing.JOptionPane;
import vista.Dialogos.Cargando;

public class Funciones extends Datos {

    private TablaDistribucionDeCarga tdc;
    private Cargando car;

    public Funciones() {
        super();
    }

    public void IniciarAnalisisEspectral(String tipoCarga, boolean activos[]) {

        this.activos = activos;
        if (numCapas > 3) {
            JOptionPane.showMessageDialog(null, "Solo se puede calcular tres capas", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (tipoCarga) {
                case "Legal" -> {
                    tdc = new TablaDistribucionDeCarga(1);
                }
                case "Ligera Sobre Carga" -> {

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
                this.setTipoEje(j == 0 ? "Sencillo" : j == 1 ? "Sencillo Dual" : j == 2 ? "Tandem" : "Tridem");
                for (int i = 0; i < 100; i++) {
                    this.pesoEje = this.cc.data[i].getCargaPromedio();
                    calculoDiferencial(i, j);
                    switch (j) {
                        case 0:
                            cc.simpleRespuesta[i].setTetacapa1(damy ? (float) this.cal.getCapaCalculo(0).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento1());
                            cc.simpleRespuesta[i].setTetaterraceria(damy ? (float) this.cal.getCapaCalculo(1).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento2());
                            cc.simpleRespuesta[i].setEsterraceria(damy ? (float) this.cal.getCapaCalculo(1).getDeformacionVerticalE2() : (float) this.cal.getCapaCalculo(0).getComplemento3());
                            cc.simpleRespuesta[i].setEscapa1(damy ? (float) this.cal.getCapaCalculo(0).getDeformacionPorTension() : (float) this.cal.getCapaCalculo(0).getComplemento4());
                            cc.simpleRespuesta[i].setDeflexionVertical((float) this.cal.getCapaCalculo(0).getDeflexionTotal());

                            break;
                        case 1:
                            cc.dualRespuesta[i].setTetacapa1(damy ? (float) this.cal.getCapaCalculo(0).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento1());
                            cc.dualRespuesta[i].setTetaterraceria(damy ? (float) this.cal.getCapaCalculo(1).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento2());
                            cc.dualRespuesta[i].setEsterraceria(damy ? (float) this.cal.getCapaCalculo(1).getDeformacionVerticalE2() : (float) this.cal.getCapaCalculo(0).getComplemento3());
                            cc.dualRespuesta[i].setEscapa1(damy ? (float) this.cal.getCapaCalculo(0).getDeformacionPorTension() : (float) this.cal.getCapaCalculo(0).getComplemento4());
                            cc.dualRespuesta[i].setDeflexionVertical((float) this.cal.getCapaCalculo(0).getDeflexionTotal());
                            break;
                        case 2:
                            cc.tandemRespuesta[i].setTetacapa1(damy ? (float) this.cal.getCapaCalculo(0).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento1());
                            cc.tandemRespuesta[i].setTetaterraceria(damy ? (float) this.cal.getCapaCalculo(1).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento2());
                            cc.tandemRespuesta[i].setEsterraceria(damy ? (float) this.cal.getCapaCalculo(1).getDeformacionVerticalE2() : (float) this.cal.getCapaCalculo(0).getComplemento3());
                            cc.tandemRespuesta[i].setEscapa1(damy ? (float) this.cal.getCapaCalculo(0).getDeformacionPorTension() : (float) this.cal.getCapaCalculo(0).getComplemento4());
                            cc.tandemRespuesta[i].setDeflexionVertical((float) this.cal.getCapaCalculo(0).getDeflexionTotal());

                            break;
                        case 3:
                            cc.tridemRespuesta[i].setTetacapa1(damy ? (float) this.cal.getCapaCalculo(0).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento1());
                            cc.tridemRespuesta[i].setTetaterraceria(damy ? (float) this.cal.getCapaCalculo(1).getEsfuerzoVerticalO() : (float) this.cal.getCapaCalculo(0).getComplemento2());
                            cc.tridemRespuesta[i].setEsterraceria(damy ? (float) this.cal.getCapaCalculo(1).getDeformacionVerticalE2() : (float) this.cal.getCapaCalculo(0).getComplemento3());
                            cc.tridemRespuesta[i].setEscapa1(damy ? (float) this.cal.getCapaCalculo(0).getDeformacionPorTension() : (float) this.cal.getCapaCalculo(0).getComplemento4());
                            cc.tridemRespuesta[i].setDeflexionVertical((float) this.cal.getCapaCalculo(0).getDeflexionTotal());
                            break;

                    }

                }
            }

            volumenTransito();
            repeticionesAdmisibles();
            repeticionesEsperadas();
            espectrosDano();
            sumas();
            vidaRemanente();

        }
    }

    /*Lista*/
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
                        cc.data[i].setSimple((float) altura * 100 < 0.01 ? 0 : (float) altura * 100);
                    }
                    case 2 -> {
                        cc.data[i].setDual((float) altura * 100 < 0.01 ? 0 : (float) altura * 100);
                    }
                    case 3 -> {
                        cc.data[i].setTridem((float) altura * 100 < 0.01 ? 0 : (float) altura * 100);
                    }
                    case 4 -> {
                        cc.data[i].setTandem((float) altura * 100 < 0.01 ? 0 : (float) altura * 100);
                    }
                }
            }

        }

    }

    /*Lista Ver funciones que faltan*/
    private void calculoDiferencial(int index, int tipo) {
        //this.cal.getCapaCalculo(index).getPesoNeumatico() ->Dim PesoNeum As Double
        //this.tipoEje - > Dim tipoeje As String
        //int i, u deben ser auxiliares
        //this.numCapas -> numCapas;
        //this.presion -> presionInlfado
        //pi -> pi
        //this.poisson -> poisson
        //this.pesoeje -> pesoEje

        double radioContacto, pesoNeum;
        int numLlantas, u;
        double he[], fCorrecion[], modElsatico[], espesor[];

        he = new double[numCapas - 1];
        fCorrecion = new double[numCapas];
        modElsatico = new double[numCapas];
        espesor = new double[numCapas];

        numLlantas = tipo == 0 ? 1 : tipo == 1 ? 2 : tipo == 2 ? 4 : tipo == 3 ? 6 : 3;
        pesoNeum = pesoEje / (2 * numLlantas);
        radioContacto = Math.pow(((pesoNeum * 2204.623) / (this.PI * this.presion)), 0.5) * 2.54;

        this.cal.getCapaCalculo(0).setRCC(radioContacto);
        this.cal.getCapaCalculo(0).setPesoNeumatico(pesoNeum);

        for (int i = 0; i < numCapas; i++) {
            espesor[i] = this.ep[i].getEspesor();
            modElsatico[i] = this.ep[i].getModulo();
            if (numCapas == 2) {
                fCorrecion[i] = 0.9;
            } else {
                if (i == 0) {
                    fCorrecion[i] = 1;
                } else {
                    fCorrecion[i] = 0.9;
                }
            }
        }
        u = 0;
        for (int i = 0; i < numCapas - 1; i++) {
            he[i] = fCorrecion[i] * (espesor[i] + (i == 0 ? 0 : he[i - 1])) * Math.pow((modElsatico[i] / modElsatico[i + 1]), (1 / 3));
            if (i == 0 || i == numCapas - 1) {
                this.cal.getCapaCalculo(u).setEspesorParcialEquivalente(he[i]);
                u++;
            }
        }

        if (numCapas == 2) {
            this.cal.getCapaCalculo(1).setEspesorParcialEquivalente(this.cal.getCapaCalculo(0).getEspesorParcialEquivalente());
        }

        this.cal.getCapaCalculo(0).setModuloElastico(modElsatico[1]);
        this.cal.getCapaCalculo(1).setModuloElastico(numCapas);

        coordenadas();//lista
        factores_Ahlvin();//lista
        deflexionCircular();//lista

        switch (tipo) {

            case 1:

                esfuerzosEjeSencillo();
                deformacionesUnitarias();
                deformacionesTension();
                deformacionesVerticales();
                break;

            case 2:

                if (pesoEje <= 2) {
                    esfuerzoVerticalPuntual();
                    esfuerzoRadialPuntual();
                    esfuerzoTangencialPuntual();
                } else {
                    esfuerzosEjeSencilloDual();
                }

                deformacionesUnitarias();
                deformacionesTension();
                deformacionesVerticales();
                break;
            case 3:

                if (pesoEje <= 8) {
                    esfuerzoVerticalPuntual();
                    esfuerzoRadialPuntual();
                    esfuerzoTangencialPuntual();
                } else {
                    esfuerzosEjeTandem();
                }

                deformacionesUnitarias();
                deformacionesTension();
                deformacionesVerticales();
                break;
            case 4:

                if (pesoEje <= 10) {
                    esfuerzoVerticalPuntual();
                    esfuerzoRadialPuntual();
                    esfuerzoTangencialPuntual();
                } else {
                    esfuerzosEjeTridem();
                }

                deformacionesUnitarias();
                deformacionesTension();
                deformacionesVerticales();
                break;
            case 5:

                if (pesoEje <= 10) {
                    esfuerzoVerticalPuntual();
                    esfuerzoRadialPuntual();
                    esfuerzoTangencialPuntual();
                } else {
                    esfuerzosEjeMedioTridem();
                }

                deformacionesUnitarias();
                deformacionesTension();
                deformacionesVerticales();
                break;

        }

        printResIndividual();
    }

    /*Lista*/
    public void coordenadas() {

        double R11, R21, R31, R41;
        double r_Llanta[] = new double[6];
        double r_Llanta_capa[] = new double[6];
        double r_Llanta_TNatur[] = new double[6];
        double angulo_Llanta[] = new double[6];
        double f_E_Vertical_ra[] = new double[4];
        double interfaz[] = new double[2];//escribir un código donde si existen más interfaces, la ultima interfaz la ponga en interfaz 2

        interfaz[0] = this.cal.getCapaCalculo(0).getEspesorParcialEquivalente();
        interfaz[numCapas - 1] = this.cal.getCapaCalculo(1).getEspesorParcialEquivalente();

        r_Llanta[0] = Math.pow((Math.pow(X, 2) + Math.pow(Y, 2)), 0.5);
        r_Llanta[1] = Math.pow((Math.pow(X, 2) + Math.pow((Y - S), 2)), 0.5);
        r_Llanta[2] = Math.pow((Math.pow((X - D), 2) + Math.pow(Y, 2)), 0.5);
        r_Llanta[3] = Math.pow((Math.pow((X - D), 2) + Math.pow((Y - S), 2)), 0.5);
        r_Llanta[4] = Math.pow((Math.pow((X - 2 * D), 2) + Math.pow(Y, 2)), 0.5);
        r_Llanta[5] = Math.pow((Math.pow((X - 2 * D), 2) + Math.pow((Y - S), 2)), 0.5);

        for (int i = 0; i < 6; i++) {
            r_Llanta_capa[i] = Math.pow((Math.pow(r_Llanta[i], 2) + Math.pow(interfaz[i], 2)), 0.5);
            r_Llanta_TNatur[i] = Math.pow((Math.pow(r_Llanta[i], 2) + Math.pow(interfaz[2], 2)), 0.5);
        }

        if (X == 0) {
            angulo_Llanta[0] = 90;
            angulo_Llanta[1] = 90;
        } else {
            angulo_Llanta[0] = Math.atan(Math.abs(Y) / X) / 1.74532925199433E-02;
            angulo_Llanta[1] = Math.atan(Math.abs((Y - S) / X)) / 1.74532925199433E-02;
        }

        if (X == D) {
            angulo_Llanta[2] = 90;
            angulo_Llanta[3] = 90;
        } else {
            angulo_Llanta[2] = Math.atan(Math.abs(Y / (X - D))) / 1.74532925199433E-02;
            angulo_Llanta[3] = Math.atan(Math.abs((Y - S) / (X - D))) / 1.74532925199433E-02;
        }

        if (X == 2 * D) {
            angulo_Llanta[4] = 90;
            angulo_Llanta[5] = 90;
        } else {
            angulo_Llanta[4] = Math.atan(Math.abs(Y / (X - 2 * D))) / 1.74532925199433E-02;
            angulo_Llanta[5] = Math.atan(Math.abs((Y - S) / (X - 2 * D))) / 1.74532925199433E-02;
        }

        R11 = r_Llanta[0] / this.cal.getCapaCalculo(0).getRCC();
        R21 = r_Llanta[1] / this.cal.getCapaCalculo(0).getRCC();
        R31 = r_Llanta[2] / this.cal.getCapaCalculo(0).getRCC();
        R41 = r_Llanta[3] / this.cal.getCapaCalculo(0).getRCC();

        f_E_Vertical_ra[0] = R11 < 14 ? R11 : 13.999;
        f_E_Vertical_ra[1] = R21 < 14 ? R21 : 13.999;
        f_E_Vertical_ra[2] = R31 < 14 ? R31 : 13.999;
        f_E_Vertical_ra[3] = R41 < 14 ? R41 : 13.999;

        for (int i = 0; i < 6; i++) {
            this.llantas.getLlanta(i).setAnguloHorizontal(angulo_Llanta[i]);
            this.llantas.getLlanta(i).setDistanciaHorizontal(r_Llanta[i]);
            this.llantas.getLlanta(i).setDistanciaRadialCarpeta(r_Llanta_capa[i]);
            this.llantas.getLlanta(i).setDistanciaRadialSubrasante(r_Llanta_TNatur[i]);

        }
        this.cal.getCapaCalculo(0).setEsfuerzoCortanteRZ(f_E_Vertical_ra[0]);
        this.cal.getCapaCalculo(0).setEsfuerzoCortanteYZ(f_E_Vertical_ra[1]);
        this.cal.getCapaCalculo(0).setEsfuerzoCortanteXZ(f_E_Vertical_ra[2]);
        this.cal.getCapaCalculo(0).setDeformacionVerticalE2(f_E_Vertical_ra[3]);

    }

    /*Lista*/
    public void factores_Ahlvin() {
        int buscax = 0, buscay = 0, posx1 = 0, posx2 = 0, posy1 = 0, posy2 = 0;
        double r_sobre_a, z_sobre_a, X1, Y1;
        double valorA, valref, valorC, valorI, valorK, radioContacto, z;
        double res_interpolacion1, res_interpolacion2, res_interpolacion3;
        double x_inter[], y_inter[];

        x_inter = new double[18];
        y_inter = new double[23];

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
        x_inter[16] = 1;
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

        // 'inicia el ciclo para las cuatro llantas
        for (int i = 0; i < 4; i++) {
            radioContacto = cal.getCapaCalculo(0).getRCC();
            z = cal.getCapaCalculo(0).getEspesorParcialEquivalente();//z = Sheets("calculos").Cells(3, 7)

            r_sobre_a = llantas.getLlanta(i).getRa();//Sheets("calculos").Cells(25 + llantas, 3)
// r_sobre_a = Sheets("ANALISIS PAVIMENTO").Cells(22 + llantas, 181) ' verificar como es que daba lo mismo si los valores no cambiaban o si?
            z_sobre_a = z / radioContacto;

            if (r_sobre_a > 14) {
                r_sobre_a = 13.999;
            }
            if (z_sobre_a > 10) {
                z_sobre_a = 9.999;
            }

            X1 = r_sobre_a;
            Y1 = z_sobre_a;

            for (buscax = 0; buscax < 18; buscax++) {
                if (X1 >= x_inter[buscax] && X1 < x_inter[buscax + 1]) {
                    posx1 = buscax;
                    posx2 = buscax + 1;
                    break;
                }
            }

            for (buscay = 0; buscay < 23; buscay++) {
                if (Y1 >= y_inter[buscay] && Y1 < y_inter[buscay + 1]) {
                    posy1 = buscay;
                    posy2 = buscay + 1;
                    break;
                }
            }

            //'interpolación para la primera tabla
            valorA = y_inter[buscay];
            valorC = y_inter[buscay + 1];
            valorI = DatosTablas.FactorEVertical.getValor(posy1, posx1);//Sheets("factor_e_vert").Cells(posy1 + 2, posx1 + 2)
            valorK = DatosTablas.FactorEVertical.getValor(posy2, posx1);//Sheets("factor_e_vert").Cells(posy2 + 2, posx1 + 2)

            valref = Y1;

            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK);

            valorI = DatosTablas.FactorEVertical.getValor(posy1, posx2);//Sheets("factor_e_vert").Cells(posy1 + 2, posx2 + 2;
            valorK = DatosTablas.FactorEVertical.getValor(posy2, posx2);//Sheets("factor_e_vert").Cells(posy2 + 2, posx2 + 2)

            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK);

            valorA = x_inter[buscax];
            valref = X1;
            valorC = x_inter[buscax + 1];

            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2);

            llantas.getLlanta(i).setFactorEVertical(res_interpolacion3);//Sheets("calculos").Cells(25 + llantas, 4) = res_interpolacion3

//'intepolación para la segunda tabla
            valorA = y_inter[buscay];
            valorC = y_inter[buscay + 1];
            valorI = DatosTablas.FactorERadial.getValor(posy1, posx1);//Sheets("factor_e_rad").Cells(posy1 + 2, posx1 + 2)
            valorK = DatosTablas.FactorERadial.getValor(posy2, posx1);//Sheets("factor_e_rad").Cells(posy2 + 2, posx1 + 2)

            valref = Y1;

            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK);

            valorI = DatosTablas.FactorERadial.getValor(posy1, posx2);//Sheets("factor_e_rad").Cells(posy1 + 2, posx2 + 2)
            valorK = DatosTablas.FactorERadial.getValor(posy2, posx2);//Sheets("factor_e_rad").Cells(posy2 + 2, posx2 + 2)

            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK);

            valorA = x_inter[buscax];
            valref = X1;
            valorC = x_inter[buscax + 1];

            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2);

            llantas.getLlanta(i).setFactorERadial(res_interpolacion3);// Sheets("calculos").Cells(25 + llantas, 5) = res_interpolacion3

            //interpolación para la tercera tabla
            valorA = y_inter[buscay];
            valorC = y_inter[buscay + 1];
            valorI = DatosTablas.FactorETangencial.getValor(posy1, posx1);//Sheets("factor_e_tan").Cells(posy1 + 2, posx1 + 2)
            valorK = DatosTablas.FactorETangencial.getValor(posy2, posx1);//Sheets("factor_e_tan").Cells(posy2 + 2, posx1 + 2)

            valref = Y1;

            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK);

            valorI = DatosTablas.FactorETangencial.getValor(posy1, posx2); //Sheets("factor_e_tan").Cells(posy1 + 2, posx2 + 2)
            valorK = DatosTablas.FactorETangencial.getValor(posy2, posx2); //Sheets("factor_e_tan").Cells(posy2 + 2, posx2 + 2)

            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK);

            valorA = x_inter[buscax];
            valref = X1;
            valorC = x_inter[buscax + 1];

            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2);

            llantas.getLlanta(i).setFactorETangencial(res_interpolacion3);//Sheets("calculos").Cells(25 + llantas, 6) = res_interpolacion3 

        }

    }

    /* Lista*/
    private double interpola(double a, double b, double c, double i, double k) {
        return (((k - i) * (b - a)) / (c - a)) + i;
    }

    /*Lista*/
    private void deflexionCircular() {
        double pesoNeum, radioContacto, A, psiAMpa, deflexionTotal;
        int numLlantas = 1, i;
        double espesor[], modElastico[], fCorreccion[], he[], z[], dz[];

        //variables guardas en datos
        //Pi -> PI
        //PesoEje -> pesoEje //= Sheets("NuevoFormatoPav").Cells(7, 6)     'inputs
        //tipoeje -> tipoEje //= Sheets("NuevoFormatoPav").Cells(8, 6)     'inputs
        //PresionInflado -> presion //= Sheets("avanzado").Cells(2, 2)     'inputs
        //NumCapas -> numCapas //= Sheets("NuevoFormatoPav").Cells(10, 6)   'inputs
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
        //Poisson -> poisson = Sheets("NuevoFormatoPav").Cells(11, 6)
        //'anotar una condicion donde aplique que solamente
        //'funciona odemark para 2 o mas capas
        for (i = 0; i < numCapas; i++) {
            espesor[i] = ep[i].getEspesor(); //Sheets("NuevoFormatoPav").Cells(13 + i, 5)
            modElastico[i] = ep[i].getModulo(); //Sheets("NuevoFormatoPav").Cells(13 + i, 6)
            if (numCapas == 2) {
                fCorreccion[i] = 0.9;
            } else {
                if (i == 0) {
                    fCorreccion[i] = 1;
                } else {
                    fCorreccion[i] = 0.8;
                }
            }
        }

        for (i = 0; i < numCapas; i++) {//For i = 1 To NumCapas - 1
            if (i == 0) {
                he[i] = fCorreccion[i] * (espesor[i] + he[0]) * Math.pow((modElastico[i] / modElastico[i + 1]), (1 / 3));
            }

            he[i] = fCorreccion[i] * (espesor[i] + he[i - 1]) * Math.pow((modElastico[i] / modElastico[i + 1]), (1 / 3));
        }
        //Sheets("calculos").Cells(48 + i, 3) = he(i) ' sirve para anotar los espesores equivalentes de las interfaces

        psiAMpa = 0.00689475719;
        deflexionTotal = 0;
        A = radioContacto;
        for (i = 0; i < numCapas; i++) {
            int r = i - 1 < 0 ? 0 : i - 1;

            double part1 = ((1 + poisson) * presion * psiAMpa * A) / (modElastico[i] * 0.001);

            double suma1 = espesor[i] + he[r];
            double suma2 = 1 - 2 * poisson;

            double div1 = he[r] / A;
            double div2 = suma1 / A;

            double pot1 = Math.pow(div1, 2);
            double pot2 = Math.pow(1 + pot1, 0.5);

            double pot3 = Math.pow((suma1 / A), 2);
            double pot4 = Math.pow((1 + pot3), 0.5);

            if (i != numCapas) {

                dz[i] = part1 * (1 / pot2 + suma2 * (pot2 - div1)) - part1 * (1 / pot4 + suma2 * (pot4 - div2));
            } else {

                dz[i] = part1 * (1 / pot2 + suma2 * (pot2 - div1));

            }
            deflexionTotal = deflexionTotal + dz[i];
        }
        this.cal.getCapaCalculo(0).setDeflexionTotal(deflexionTotal);   //Sheets("calculos").Cells(3, 22) = DeflexionTotal * 10 ' para que de en milimetros, ya que las entradas van en cms
    }

    /*Lista 1*/
    private void esfuerzosEjeSencillo() {
        double EvZ, Er1, Et1;
        double he[] = new double[numCapas];
        double radioContacto = this.cal.getCapaCalculo(0).getRCC();
        var tipoEje = this.tipoEje;

        for (int i = 0; i < numCapas - 1; i++) {
            he[i] = this.cal.getCapaCalculo(i).getEspesorParcialEquivalente();
            esfuerzos_Damy();
            EvZ = 6.894757 * (presion * (1 - ((Math.pow(he[i], 3)) / Math.pow((Math.pow(radioContacto, 2) + Math.pow(he[i], 2)), 1.5))));
            var r1 = (2 * he[i] * (1 + poisson));
            var r2 = Math.pow(radioContacto, 2) + Math.pow(he[i], 2);

            Er1 = 6.894757 * (presion / 2) * (1 + (2 * poisson) - (r1 / Math.pow(r2, 0.5)) + (((Math.pow(he[i], 3)) / (r2))));
            Et1 = Er1;
            this.cal.getCapaCalculo(i).setEsfuerzoVerticalO(EvZ);//Sheets("calculos").Cells(2 + i, 10) = EvZ
            this.cal.getCapaCalculo(i).setEsfuerzoTangencialO(Er1);//Sheets("calculos").Cells(2 + i, 11) = Er1
            this.cal.getCapaCalculo(i).setEsfuerzoRadialO(Et1);//Sheets("calculos").Cells(2 + i, 12) = Et1
            if (i == 0) {
                this.llantas.getLlanta(i).setOrNeumatico1(Er1);
                this.llantas.getLlanta(i).setOtNeumatico1(Er1);
            }
            if (i == 2) {
                this.llantas.getLlanta(i).setOrNeumatico2(Er1);
                this.llantas.getLlanta(i).setOtNeumatico2(Er1);
            }
        }

    }

    public void esfuerzos_Damy(int numCapa) {
        double xp, yp, Q, z, radio, parcial, suma, sigmaZ, S, D, xq, yq, sumaporllanta;
        int num_de_calculos, calculos;

        double X[] = new double[13];// As Double
        double Y[] = new double[13];// As Double
        double xprima[] = new double[13];// As Double
        double yprima[] = new double[13];// As Double
        double Teta[][] = new double[2][12];// As Double
        double C[][] = new double[2][12];// As Double
        double F[] = new double[12];// As Double
        double A[] = new double[12];// As Double
        double L[] = new double[12];// As Double
        double B[][] = new double[2][12];//(1 To 2, 1 To 12) As Double
        double W[][] = new double[2][12];// As Double
        double j[][] = new double[2][12];// As Double
        double n[][] = new double[2][12];// As Double

        double posicionx[] = new double[6];// As Double
        double posiciony[] = new double[6];// As Double
        double llantax[] = new double[6];// As Double
        double llantay[] = new double[6];// As Double

        switch (this.tipoEje) {
            case "Sencillo":
                calculos = 1;
                break;
            case "Sencillo Dual":
                calculos = 2;
                break;

            case "Tandem":
                calculos = 4;
                break;
            case "Tridem":
                calculos = 6;
        }

        Q = 6.895 * this.presion;
        radio = this.cal.getCapaCalculo(0).getRCC() / 100;//Sheets("calculos").Cells(3, 6) / 100
        xq = this.X / 100;//Sheets("avanzado").Cells(17, 2) / 100
        yq = this.Y / 100;//Sheets("avanzado").Cells(18, 2) / 100
        S = this.S / 100;//Sheets("avanzado").Cells(17, 4) / 100
        D = this.D / 100;//Sheets("avanzado").Cells(18, 4) / 100; 

        llantax[0] = 0;
        llantax[1] = 0;
        llantax[2] = D;
        llantax[3] = D;
        llantax[4] = 2 * D;
        llantax[5] = 2 * D;
        llantay[0] = 0;
        llantay[1] = S;
        llantay[2] = 0;
        llantay[3] = S;
        llantay[4] = 0;
        llantay[5] = S;
        
        z = this.cal.getCapaCalculo(numCapa).getEspesorParcialEquivalente()/100;//Sheets("calculos").Cells(2 + NumCapa, 7) / 100
        for (num_de_calculos = 0;num_de_calculos< calculos;num_de_calculos++){
      
        posicionx[num_de_calculos] = Math.abs(llantax[num_de_calculos] - xq);
        posiciony[num_de_calculos] = Math.abs(llantay[num_de_calculos] - yq);
      
      xp = posicionx[num_de_calculos];
      yp = posiciony[num_de_calculos];
      
        for (int i=0;i<12;i++){
            
            X[i] = radio * Math.cos((i - 1) * this.PI / 6);
            Y[i] = radio * Math.sin((i - 1) * this.PI / 6);
            X[1 + i] = radio *  Math.cos(i * this.PI / 6);
            Y[1 + i] = radio *  Math.sin(i * this.PI / 6);
            
            xprima[i] = X[i] - xp;
            xprima[i + 1] = X[i + 1] - xp;
            yprima[i] = Y[i] - yp;
            yprima[i + 1] = Y[i + 1] - yp;
            
            F[i] = xprima[i] * yprima[i+1] - xprima[i+1] * yprima[i];
            L[i] = Math.pow((Math.pow((xprima[i+1] - xprima[i]),2) + Math.pow((yprima[i+1] - yprima[i]) ,2)),(1 / 2));
            A[i] = Math.abs(z * L[i] / F[i]);
            C[0][i] = (xprima[i] * (xprima[i+1] - xprima[i]) + yprima[i] * (yprima[i+1] - yprima[i])) / F[i];
            C[1][i] = (xprima[i+1] * (xprima[i+1] - xprima[i]) + yprima[i+1] * (yprima[i+1] - yprima[i])) / F[i];
            
            Teta[0][i] = Math.atan(C[0][i]);
            Teta[1][ i] = Math.atan(C[1][i]);
            
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
        
                              }
      sigmaZ = (Q * suma) / (2 * Pi)
      Sheets("calculos").Cells(30 + num_de_calculos, 2 + NumCapa) = sigmaZ ' es el resultado de un calculo o un area y una profundidad
      suma = 0
      sumaporllanta = sumaporllanta + sigmaZ
                        }
                          /*

'datos de entrada









Pi = 3.14159265358979
sumaporllanta = 0
    
    
Sheets("calculos").Cells(5 + NumCapa, 10) = sumaporllanta
                           */
    }

    private void repeticionesAdmisibles() {
    }

    private void repeticionesEsperadas() {
    }

    private void espectrosDano() {
    }

    private void sumas() {
    }

    private void vidaRemanente() {
    }

    private void volumenTransito() {
    }

    private void deformacionesUnitarias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void deformacionesTension() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void deformacionesVerticales() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzoVerticalPuntual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzoRadialPuntual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzoTangencialPuntual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzosEjeSencilloDual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzosEjeTridem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void printResIndividual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzosEjeMedioTridem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void esfuerzosEjeTandem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

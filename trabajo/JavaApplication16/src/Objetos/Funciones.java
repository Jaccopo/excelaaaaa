package Objetos;

import Clases.TransitoEstatico;
import Objetos.Datos;
import DatosTablas.TablaDistribucionDeCarga;
import java.util.HashSet;
import javax.swing.JOptionPane;
import vista.Dialogos.Cargando;

public class Funciones extends Datos {

    private TablaDistribucionDeCarga tdc;
    private Cargando car;

    public Funciones() {
        super();
    }

    public void IniciarAnalisisEspectral(String tipoCarga, boolean activos[]) {
        this.tipoCarga = tipoCarga;
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

            //volumenTransito();// llama volumen transito aunque ya esta defindio en Transito Estatico los valores
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
        interfaz[1] = this.cal.getCapaCalculo(1).getEspesorParcialEquivalente();

        r_Llanta[0] = Math.pow((Math.pow(X, 2) + Math.pow(Y, 2)), 0.5);
        r_Llanta[1] = Math.pow((Math.pow(X, 2) + Math.pow((Y - S), 2)), 0.5);
        r_Llanta[2] = Math.pow((Math.pow((X - D), 2) + Math.pow(Y, 2)), 0.5);
        r_Llanta[3] = Math.pow((Math.pow((X - D), 2) + Math.pow((Y - S), 2)), 0.5);
        r_Llanta[4] = Math.pow((Math.pow((X - 2 * D), 2) + Math.pow(Y, 2)), 0.5);
        r_Llanta[5] = Math.pow((Math.pow((X - 2 * D), 2) + Math.pow((Y - S), 2)), 0.5);

        for (int i = 0; i < 6; i++) {
            r_Llanta_capa[i] = Math.pow((Math.pow(r_Llanta[i], 2) + Math.pow(interfaz[0], 2)), 0.5);
            r_Llanta_TNatur[i] = Math.pow((Math.pow(r_Llanta[i], 2) + Math.pow(interfaz[1], 2)), 0.5);
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

        for (i = 0; i < numCapas-1; i++) {//For i = 1 To NumCapas - 1
            if (i == 0) {
                he[i] = fCorreccion[i] * (espesor[i] + he[0]) * Math.pow((modElastico[i] / modElastico[i + 1==numCapas?i:i+1]), (1 / 3));
            }

            he[i] = fCorreccion[i] * (espesor[i] + he[i==0?0:i-1]) * Math.pow((modElastico[i] / modElastico[i + 1==numCapas?i:i+1]), (1 / 3));
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
            esfuerzos_Damy(i);
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

    /*Lista*/
    public void esfuerzos_Damy(int numCapa) {
        double xp, yp, Q, z, radio, parcial, suma = 0, sigmaZ, S, D, xq, yq, sumaporllanta;
        int num_de_calculos, calculos = 0;

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
        sumaporllanta = 0;
        z = this.cal.getCapaCalculo(numCapa).getEspesorParcialEquivalente() / 100;//Sheets("calculos").Cells(2 + NumCapa, 7) / 100
        for (num_de_calculos = 0; num_de_calculos < calculos; num_de_calculos++) {

            posicionx[num_de_calculos] = Math.abs(llantax[num_de_calculos] - xq);
            posiciony[num_de_calculos] = Math.abs(llantay[num_de_calculos] - yq);

            xp = posicionx[num_de_calculos];
            yp = posiciony[num_de_calculos];

            for (int i = 0; i < 12; i++) {

                X[i] = radio * Math.cos((i - 1) * this.PI / 6);
                Y[i] = radio * Math.sin((i - 1) * this.PI / 6);
                X[1 + i] = radio * Math.cos(i * this.PI / 6);
                Y[1 + i] = radio * Math.sin(i * this.PI / 6);

                xprima[i] = X[i] - xp;
                xprima[i + 1] = X[i + 1] - xp;
                yprima[i] = Y[i] - yp;
                yprima[i + 1] = Y[i + 1] - yp;

                F[i] = xprima[i] * yprima[i + 1] - xprima[i + 1] * yprima[i];
                L[i] = Math.pow((Math.pow((xprima[i + 1] - xprima[i]), 2) + Math.pow((yprima[i + 1] - yprima[i]), 2)), (1 / 2));
                A[i] = Math.abs(z * L[i] / F[i]);
                C[0][i] = (xprima[i] * (xprima[i + 1] - xprima[i]) + yprima[i] * (yprima[i + 1] - yprima[i])) / F[i];
                C[1][i] = (xprima[i + 1] * (xprima[i + 1] - xprima[i]) + yprima[i + 1] * (yprima[i + 1] - yprima[i])) / F[i];

                Teta[0][i] = Math.atan(C[0][i]);
                Teta[1][i] = Math.atan(C[1][i]);

                B[0][i] = (A[i] * C[0][i]) / (Math.pow((1 + (Math.pow(A[i], 2)) + Math.pow(C[0][i], 2)), (1 / 2)));

                B[1][i] = (A[i] * C[0][i]) / (Math.pow((1 + (Math.pow(A[i], 2)) + Math.pow(C[1][i], 2)), (1 / 2)));

                W[0][i] = (2 * A[i] * C[0][i]) / (1 + 1 * Math.pow((Math.pow(A[i], 2)) + Math.pow(C[0][i], 2), (1 / 2)));

                W[1][i] = (2 * A[i] * C[1][i]) / (1 + 1 * Math.pow((Math.pow(A[i], 2)) + Math.pow(C[1][i], 2), (1 / 2)));

                j[0][i] = C[0][i] / (Math.pow(Math.pow(1 + A[i], 2), (1 / 2)));

                j[1][i] = C[1][i] / (Math.pow(Math.pow(1 + A[i], 2), (1 / 2)));

                n[0][i] = (Math.pow(A[i], 2) * C[0][i]) / (1 + Math.pow(A[i], 2) + Math.pow(C[0][i], 2));

                n[1][i] = (Math.pow(A[i], 2) * C[1][i]) / (1 + Math.pow(A[i], 2) + Math.pow(C[1][i], 2));

                parcial = Teta[1][i] - Teta[1][i] - Math.atan(B[1][i]) + Math.atan(B[0][i]) + (B[1][i] - B[0][i]) / (Math.pow(A[i], 2) + 1);

                suma = suma + parcial;

            }
            sigmaZ = (Q * suma) / (2 * this.PI);
            this.llantas.getLlanta(num_de_calculos).setValorCapa(sigmaZ);
            suma = 0;
            sumaporllanta = sumaporllanta + sigmaZ;
        }
        this.cal.getCapaCalculo(numCapa).setAux1(sumaporllanta);//Sheets("calculos").Cells(5 + NumCapa, 10) = sumaporllanta     
    }

    /*Lista*/
    private void repeticionesAdmisibles() {

        int i, eje, NumMarcasClase;
        double f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0;
        double Nd = 0, Nf = 0;
        double DefZ[] = new double[4];
        double DefT[] = new double[4];
        String TipoModeloFatiga;
        String TipoModeloDeformacion;
        double Elastico;

        // Funcion de limpiar -> //Worksheets("Larguillo").Range("AB4:AI103").ClearContents
        TipoModeloFatiga = "IMT";
        TipoModeloDeformacion = "IMT";

        Elastico = this.ep[0].getModulo();

        switch (TipoModeloFatiga) {
            case "IMT":
                f1 = 0.000000000166;
                f2 = 4.32;
                f4 = 0.0000000618;
                f5 = 3.95;
                break;
        }
        espectros();

        NumMarcasClase = 100; // ya sabemos que es 100 el numero por larguillo y como lo manejan en el codigo anterior
        /*While Len(Sheets("Larguillo").Cells(i, 1)) > 0
            NumMarcasClase = NumMarcasClase + 1
            i = i + 1
        Wend*/

        //se hara por cada uno de los eje
        eje = 0;
        for (i = 0; i < NumMarcasClase; i++) {
            DefZ[eje] = this.cc.simpleRespuesta[i].getEsterraceria();//Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje)
            DefT[eje] = this.cc.simpleRespuesta[i].getEscapa1(); //Abs(Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje))
            Nd = f4 * (Math.pow(DefZ[eje], (-f5)));

            this.cc.nre[i].setSimDeformacion((float) Nd);  //Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje) = Nd
            this.cc.nre[i].setSimFatiga((float) Nf);//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje) = Nf

        }

        eje++; //dual
        for (i = 0; i < NumMarcasClase; i++) {
            DefZ[eje] = this.cc.dualRespuesta[i].getEsterraceria();//Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje)
            DefT[eje] = this.cc.dualRespuesta[i].getEscapa1(); //Abs(Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje))
            Nd = f4 * (Math.pow(DefZ[eje], (-f5)));

            this.cc.nre[i].setDualDeformacion((float) Nd);  //Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje) = Nd
            this.cc.nre[i].setDualFatiga((float) Nf);//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje) = Nf
        }
        eje++; //tridem
        for (i = 0; i < NumMarcasClase; i++) {
            DefZ[eje] = this.cc.tridemRespuesta[i].getEsterraceria();//Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje)
            DefT[eje] = this.cc.tridemRespuesta[i].getEscapa1(); //Abs(Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje))
            Nd = f4 * (Math.pow(DefZ[eje], (-f5)));

            this.cc.nre[i].setTRIDEMDeformacion((float) Nd);  //Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje) = Nd
            this.cc.nre[i].setTRIDEMFatiga((float) Nf);//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje) = Nf
        }
        eje++; //tandem
        for (i = 0; i < NumMarcasClase; i++) {
            DefZ[eje] = this.cc.tandemRespuesta[i].getEsterraceria();//Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje)
            DefT[eje] = this.cc.tandemRespuesta[i].getEscapa1(); //Abs(Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje))
            Nd = f4 * (Math.pow(DefZ[eje], (-f5)));

            this.cc.nre[i].setTANDEMDeformacion((float) Nd);  //Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje) = Nd
            this.cc.nre[i].setTANDEMFatiga((float) Nf);//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje) = Nf
        }

    }

    /*Lista*/
    private void repeticionesEsperadas() {

        int i, eje = 0;
        double NumEjes[] = new double[4];
        double RepEsperadas[] = new double[4];

        NumEjes[eje] = TransitoEstatico.sencilloPorcentajeRepresentivo; //Sheets("Tránsito").Cells(19 + eje, 7)
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = NumEjes[eje] * 0.01 * 0.5 * this.cc.data[i].getSimple();//Sheets("Larguillo").Cells(3 + i, 2 + eje)
            this.cc.ere[i].setSimple((float) RepEsperadas[eje]);//Sheets("Larguillo").Cells(3 + i, 36 + eje) = RepEsperadas(eje)
        }
        eje++;
        NumEjes[eje] = TransitoEstatico.dualPorcentajeRepresentivo; //Sheets("Tránsito").Cells(19 + eje, 7)
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = NumEjes[eje] * 0.01 * 0.5 * this.cc.data[i].getSimple();//Sheets("Larguillo").Cells(3 + i, 2 + eje)
            this.cc.ere[i].setDual((float) RepEsperadas[eje]);//Sheets("Larguillo").Cells(3 + i, 36 + eje) = RepEsperadas(eje)
        }
        eje++;
        NumEjes[eje] = TransitoEstatico.tandemPorcentajeRepresentivo; //Sheets("Tránsito").Cells(19 + eje, 7)
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = NumEjes[eje] * 0.01 * 0.5 * this.cc.data[i].getSimple();//Sheets("Larguillo").Cells(3 + i, 2 + eje)
            this.cc.ere[i].setTandem((float) RepEsperadas[eje]);//Sheets("Larguillo").Cells(3 + i, 36 + eje) = RepEsperadas(eje)
        }
        eje++;
        NumEjes[eje] = TransitoEstatico.tridemPorcentajeRepresentivo; //Sheets("Tránsito").Cells(19 + eje, 7)
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = NumEjes[eje] * 0.01 * 0.5 * this.cc.data[i].getSimple();//Sheets("Larguillo").Cells(3 + i, 2 + eje)
            this.cc.ere[i].setTridem((float) RepEsperadas[eje]);//Sheets("Larguillo").Cells(3 + i, 36 + eje) = RepEsperadas(eje)
        }

    }

    /*Lista*/
    private void espectrosDano() {
        int i, eje = 0;// As Integer
        double Nd, Nf, DañoDef, DañoFat;// As Double
        double RepEsperadas[] = new double[4];//(1 To 4) As Double

        //sencillo
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = this.cc.ere[i].getSimple();//Sheets("Larguillo").Cells(3 + i, 36 + eje)
            Nd = this.cc.nre[i].getSimDeformacion();//Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje)
            Nf = this.cc.nre[i].getSimFatiga();//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje)
            DañoDef = RepEsperadas[eje] / Nd;
            DañoFat = RepEsperadas[eje] / Nf;
            this.cc.ed[i].setSimpleDef((float) DañoDef); //  Sheets("Larguillo").Cells(3 + i, 40 + 2 * eje) = DañoDef
            this.cc.ed[i].setSimpleFat((float) DañoFat);//Sheets("Larguillo").Cells(3 + i, 41 + 2 * eje) = DañoFat
        }
        eje++;

        //dual
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = this.cc.ere[i].getDual();//Sheets("Larguillo").Cells(3 + i, 36 + eje)
            Nd = this.cc.nre[i].getDualDeformacion();//Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje)
            Nf = this.cc.nre[i].getDualFatiga();//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje)
            DañoDef = RepEsperadas[eje] / Nd;
            DañoFat = RepEsperadas[eje] / Nf;
            this.cc.ed[i].setDualDef((float) DañoDef); //  Sheets("Larguillo").Cells(3 + i, 40 + 2 * eje) = DañoDef
            this.cc.ed[i].setDualFat((float) DañoFat);//Sheets("Larguillo").Cells(3 + i, 41 + 2 * eje) = DañoFat
        }
        eje++;

        //tandem
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = this.cc.ere[i].getTandem();//Sheets("Larguillo").Cells(3 + i, 36 + eje)
            Nd = this.cc.nre[i].getTANDEMDeformacion();//Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje)
            Nf = this.cc.nre[i].getTANDEMFatiga();//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje)
            DañoDef = RepEsperadas[eje] / Nd;
            DañoFat = RepEsperadas[eje] / Nf;
            this.cc.ed[i].setTANDEMDef((float) DañoDef); //  Sheets("Larguillo").Cells(3 + i, 40 + 2 * eje) = DañoDef
            this.cc.ed[i].setTANDEMFat((float) DañoFat);//Sheets("Larguillo").Cells(3 + i, 41 + 2 * eje) = DañoFat
        }
        eje++;
        //tridem
        for (i = 0; i < 100; i++) {
            RepEsperadas[eje] = this.cc.ere[i].getTridem();//Sheets("Larguillo").Cells(3 + i, 36 + eje)
            Nd = this.cc.nre[i].getTRIDEMDeformacion();//Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje)
            Nf = this.cc.nre[i].getTRIDEMFatiga();//Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje)
            DañoDef = RepEsperadas[eje] / Nd;
            DañoFat = RepEsperadas[eje] / Nf;
            this.cc.ed[i].setTRIDEMEDef((float) DañoDef);
            //  Sheets("Larguillo").Cells(3 + i, 40 + 2 * eje) = DañoDef
            this.cc.ed[i].setTRIDEMFat((float) DañoFat);//Sheets("Larguillo").Cells(3 + i, 41 + 2 * eje) = DañoFat
        }

    }

    /*Lista*/
    private void sumas() {
        int i, eje, NumMarcasClase;// As Integer
        double Nd, Nf, DañoDef, DañoFat;// As Double
        double RepEsperadas[] = new double[4];// As Double
        double suma1, suma2, suma3, suma4, suma5, suma6, suma7, suma8;// As Double

        NumMarcasClase = 100;
        i = 0;
        suma1 = suma2 = suma3 = suma4 = suma5 = suma6 = suma7 = suma8 = 0;

        for (i = 0; i < 100; i++) {

            this.cc.et[i].setDefTotal(this.cc.ed[i].sumaDeformacion());  //Sheets("Larguillo").Cells(3 + i, 51) = //Sheets("Larguillo").Cells(3 + i, 42) + _
            //Sheets("Larguillo").Cells(3 + i, 44) + _
            //Sheets("Larguillo").Cells(3 + i, 46) + _
            //Sheets("Larguillo").Cells(3 + i, 48)

            this.cc.et[i].setFatTodos(this.cc.ed[i].sumaFatiga());
            //Sheets("Larguillo").Cells(3 + i, 52) = Sheets("Larguillo").Cells(3 + i, 43) + _
            //Sheets("Larguillo").Cells(3 + i, 45) + _
            //Sheets("Larguillo").Cells(3 + i, 47) + _
            //Sheets("Larguillo").Cells(3 + i, 49)

            suma1 = this.cc.ed[i].getSimpleDef() + suma1;//Sheets("Larguillo").Cells(3 + i, 42) + suma1
            this.cc.eda[i].setDefSimple((float) suma1);//Sheets("Larguillo").Cells(3 + i, 54) = suma1

            suma2 = this.cc.ed[i].getDualDef() + suma2;//Sheets("Larguillo").Cells(3 + i, 44) + suma2
            this.cc.eda[i].setDefDual((float) suma2);//Sheets("Larguillo").Cells(3 + i, 55) = suma2

            suma3 = this.cc.ed[i].getTANDEMDef() + suma3;//Sheets("Larguillo").Cells(3 + i, 46) + suma3
            this.cc.eda[i].setDefTANDEM((float) suma3);//Sheets("Larguillo").Cells(3 + i, 56) = suma3

            suma4 = this.cc.ed[i].getTRIDEMEDef() + suma4;//Sheets("Larguillo").Cells(3 + i, 48) + suma4
            this.cc.eda[i].setDefTRIDEM((float) suma4);//Sheets("Larguillo").Cells(3 + i, 57) = suma4

            this.cc.eda[i].sumaDeformaciones();

            //Sheets("Larguillo").Cells(3 + i, 58) = Sheets("Larguillo").Cells(3 + i, 54) + _
            //                                     Sheets("Larguillo").Cells(3 + i, 55) + _
            //                                   Sheets("Larguillo").Cells(3 + i, 56) + _
            //                                 Sheets("Larguillo").Cells(3 + i, 57)
            suma5 = this.cc.ed[i].getSimpleFat() + suma5;//Sheets("Larguillo").Cells(3 + i, 42) + suma1
            this.cc.eda[i].setFatSimple((float) suma5);//Sheets("Larguillo").Cells(3 + i, 54) = suma1

            suma6 = this.cc.ed[i].getDualFat() + suma6;//Sheets("Larguillo").Cells(3 + i, 44) + suma2
            this.cc.eda[i].setFatDual((float) suma6);//Sheets("Larguillo").Cells(3 + i, 55) = suma2

            suma7 = this.cc.ed[i].getTANDEMFat() + suma7;//Sheets("Larguillo").Cells(3 + i, 46) + suma3
            this.cc.eda[i].setFatTANDEM((float) suma7);//Sheets("Larguillo").Cells(3 + i, 56) = suma3

            suma8 = this.cc.ed[i].getTANDEMFat() + suma8;//Sheets("Larguillo").Cells(3 + i, 48) + suma4
            this.cc.eda[i].setFatTrideem((float) suma8);//Sheets("Larguillo").Cells(3 + i, 57) = suma4

            this.cc.eda[i].sumaFatiga();
            //Sheets("Larguillo").Cells(3 + i, 63) = Sheets("Larguillo").Cells(3 + i, 59) + _
            //                                     Sheets("Larguillo").Cells(3 + i, 60) + _
            //                                   Sheets("Larguillo").Cells(3 + i, 61) + _
            //                                 Sheets("Larguillo").Cells(3 + i, 62)
        }
    }

    private void vidaRemanente() {
        //se vera que afecta
    }

    //ya existe
    /*private void volumenTransito() {
    }*/
 /*Lista */
    private void deformacionesUnitarias() {
        double Ez, Er, Et, Epsilon_z, Epsilon_r, Epsilon_t, Ez_damy, Epsilon_z_damy;
        double ModElastico[] = new double[numCapas];// As Double

        for (int i = 0; i < numCapas - 1; i++) {

            Ez = this.cal.getCapaCalculo(i).getEsfuerzoVerticalO(); //Sheets("calculos").Cells(i + 2, 10)
            Er = this.cal.getCapaCalculo(i).getEsfuerzoRadialO();//Sheets("calculos").Cells(i + 2, 11)
            Et = this.cal.getCapaCalculo(i).getEsfuerzoTangencialO();//Sheets("calculos").Cells(i + 2, 12)
            ModElastico[i] = this.cal.getCapaCalculo(i).getModuloElastico(); //Sheets("calculos").Cells(i + 2, 3)

            Epsilon_z = (1 / ModElastico[i]) * (Ez - (poisson * (Er + Et)));
            Epsilon_r = (1 / ModElastico[i]) * (Er - (poisson * (Ez + Et)));
            Epsilon_t = (1 / ModElastico[i]) * (Et - (poisson * (Ez + Er)));
            //calculo con damy
            Ez_damy = this.cal.getCapaCalculo(i).getAux1(); //Sheets("calculos").Cells(i + 5, 10)
            Epsilon_z_damy = (1 / ModElastico[i]) * (Ez_damy - (poisson * (Er + Et)));
            this.cal.getCapaCalculo(i).setAux2(Epsilon_z_damy);
            this.cal.getCapaCalculo(i).setDeformacionVerticalE(Epsilon_z);//Sheets("calculos").Cells(i + 2, 13) = Epsilon_z
            this.cal.getCapaCalculo(i).setDeformacionRadialE(Epsilon_r);//Sheets("calculos").Cells(i + 2, 14) = Epsilon_r
            this.cal.getCapaCalculo(i).setDeformacionTangencialE(Epsilon_t);//Sheets("calculos").Cells(i + 2, 15) = Epsilon_t
        }
    }

    /*Lista */
    private void deformacionesTension() {
        double AX, BX, CX, DX, EX, FX, GX, HX, IX, JX, KX, LX;
        double AY, BY, CY, DY, EY, FY, GY, HY, IY, JY, KY, LY;
        double O, P, Q, R, S, T;//  As Double
        double DEFx, DEFy, GAMAxy;// As Double
        double Grados;// As Double

        double Angulo_Llanta1;// As Double
        double Angulo_Llanta2;// As Double
        double Angulo_Llanta3;// As Double
        double Angulo_Llanta4;// As Double
        double Angulo_Llanta5;// As Double
        double Angulo_Llanta6;// As Double

        double Er1, Er2, Er3, Er4, Er5, Er6, Et1, Et2, Et3, Et4, Et5, Et6;// As Double
        double Enormal_x = 0, Enormal_y = 0, Ecortante_xy = 0;// As Double
        double ModElastico[] = new double[numCapas - 1];// As Double
        double DefTension;// As Double
        double EvZ;// As Double
        double EvZ_damy, DEFx_damy, DEFy_damy, DefTension_damy;//As Double

        Grados = 1.74532925199433E-02;

        Angulo_Llanta1 = this.llantas.getLlanta(0).getAnguloHorizontal(); //Sheets("calculos").Cells(18, 3)
        Angulo_Llanta2 = this.llantas.getLlanta(1).getAnguloHorizontal(); // Sheets("calculos").Cells(19, 3)
        Angulo_Llanta3 = this.llantas.getLlanta(2).getAnguloHorizontal(); // Sheets("calculos").Cells(20, 3)
        Angulo_Llanta4 = this.llantas.getLlanta(3).getAnguloHorizontal(); // Sheets("calculos").Cells(21, 3)
        Angulo_Llanta5 = this.llantas.getLlanta(4).getAnguloHorizontal(); // Sheets("calculos").Cells(22, 3)
        Angulo_Llanta6 = this.llantas.getLlanta(5).getAnguloHorizontal(); // Sheets("calculos").Cells(23, 3)

        for (int i = 0; i < numCapas - 1; i++) {

            Er1 = i == 0 ? this.llantas.getLlanta(0).getOrNeumatico1() : this.llantas.getLlanta(0).getOrNeumatico2();//((Sheets("calculos").Cells(41, i + 2)
            Er2 = i == 0 ? this.llantas.getLlanta(1).getOrNeumatico1() : this.llantas.getLlanta(1).getOrNeumatico2();//Sheets("calculos").Cells(42, i + 2)
            Er3 = i == 0 ? this.llantas.getLlanta(2).getOrNeumatico1() : this.llantas.getLlanta(2).getOrNeumatico2();//Sheets("calculos").Cells(43, i + 2)
            Er4 = i == 0 ? this.llantas.getLlanta(3).getOrNeumatico1() : this.llantas.getLlanta(3).getOrNeumatico2();//Sheets("calculos").Cells(44, i + 2)
            Er5 = i == 0 ? this.llantas.getLlanta(4).getOrNeumatico1() : this.llantas.getLlanta(4).getOrNeumatico2();//Sheets("calculos").Cells(45, i + 2)
            Er6 = i == 0 ? this.llantas.getLlanta(5).getOrNeumatico1() : this.llantas.getLlanta(5).getOrNeumatico2();//Sheets("calculos").Cells(46, i + 2)

            Et1 = i == 0 ? this.llantas.getLlanta(0).getOtNeumatico1() : this.llantas.getLlanta(0).getOtNeumatico2();//Sheets("calculos").Cells(41, i + 4)
            Et2 = i == 0 ? this.llantas.getLlanta(1).getOtNeumatico1() : this.llantas.getLlanta(1).getOtNeumatico2();//Sheets("calculos").Cells(42, i + 4)
            Et3 = i == 0 ? this.llantas.getLlanta(2).getOtNeumatico1() : this.llantas.getLlanta(2).getOtNeumatico2();//Sheets("calculos").Cells(43, i + 4)
            Et4 = i == 0 ? this.llantas.getLlanta(3).getOtNeumatico1() : this.llantas.getLlanta(3).getOtNeumatico2();//Sheets("calculos").Cells(44, i + 4)
            Et5 = i == 0 ? this.llantas.getLlanta(4).getOtNeumatico1() : this.llantas.getLlanta(4).getOtNeumatico2();//Sheets("calculos").Cells(45, i + 4)
            Et6 = i == 0 ? this.llantas.getLlanta(5).getOtNeumatico1() : this.llantas.getLlanta(5).getOtNeumatico2();//Sheets("calculos").Cells(46, i + 4)

            //Esfuerzo normal x
            //COMPONENTE X ESFUERZO RADIAL
            AX = Er1 * (Math.pow(Math.cos(Angulo_Llanta1 * Grados), 2));
            CX = Er2 * (Math.pow(Math.cos(Angulo_Llanta2 * Grados), 2));
            EX = Er3 * (Math.pow(Math.cos(Angulo_Llanta3 * Grados), 2));
            GX = Er4 * (Math.pow(Math.cos(Angulo_Llanta4 * Grados), 2));
            IX = Er5 * (Math.pow(Math.cos(Angulo_Llanta5 * Grados), 2));
            KX = Er6 * (Math.pow(Math.cos(Angulo_Llanta6 * Grados), 2));

            //'COMPONENTE X ESFUERZO TANGENCIAL
            BX = Et1 * (Math.pow(Math.sin(Angulo_Llanta1 * Grados), 2));
            DX = Et2 * (Math.pow(Math.sin(Angulo_Llanta2 * Grados), 2));
            FX = Et3 * (Math.pow(Math.sin(Angulo_Llanta3 * Grados), 2));
            HX = Et4 * (Math.pow(Math.sin(Angulo_Llanta4 * Grados), 2));
            JX = Et5 * (Math.pow(Math.sin(Angulo_Llanta5 * Grados), 2));
            LX = Et6 * (Math.pow(Math.sin(Angulo_Llanta6 * Grados), 2));

            //'Esfuerzo normal y
            //'COMPONENTE Y ESFUERZO RADIAL
            AY = Er1 * (Math.pow(Math.sin(Angulo_Llanta1 * Grados), 2));
            CY = Er2 * (Math.pow(Math.sin(Angulo_Llanta2 * Grados), 2));
            EY = Er3 * (Math.pow(Math.sin(Angulo_Llanta3 * Grados), 2));
            GY = Er4 * (Math.pow(Math.sin(Angulo_Llanta4 * Grados), 2));
            IY = Er5 * (Math.pow(Math.sin(Angulo_Llanta5 * Grados), 2));
            KY = Er6 * (Math.pow(Math.sin(Angulo_Llanta6 * Grados), 2));

            //'COMPONENTE Y ESFUERZO TANGENCIAL
            BY = Et1 * (Math.pow(Math.cos(Angulo_Llanta1 * Grados), 2));
            DY = Et2 * (Math.pow(Math.cos(Angulo_Llanta2 * Grados), 2));
            FY = Et3 * (Math.pow(Math.cos(Angulo_Llanta3 * Grados), 2));
            HY = Et4 * (Math.pow(Math.cos(Angulo_Llanta4 * Grados), 2));
            JY = Et5 * (Math.pow(Math.cos(Angulo_Llanta5 * Grados), 2));
            LY = Et6 * (Math.pow(Math.cos(Angulo_Llanta6 * Grados), 2));

            //'ESFUERZO CORTANTE XY
            O = (Er1 - Et1) * (Math.sin(Angulo_Llanta1 * Grados)) * (Math.cos(Angulo_Llanta1 * Grados));
            P = (Er2 - Et2) * (Math.sin(Angulo_Llanta2 * Grados)) * (Math.cos(Angulo_Llanta2 * Grados));
            Q = (Er3 - Et3) * (Math.sin(Angulo_Llanta3 * Grados)) * (Math.cos(Angulo_Llanta3 * Grados));
            R = (Er4 - Et4) * (Math.sin(Angulo_Llanta4 * Grados)) * (Math.cos(Angulo_Llanta4 * Grados));
            S = (Er5 - Et5) * (Math.sin(Angulo_Llanta5 * Grados)) * (Math.cos(Angulo_Llanta5 * Grados));
            T = (Er6 - Et6) * (Math.sin(Angulo_Llanta6 * Grados)) * (Math.cos(Angulo_Llanta6 * Grados));

            switch (tipoEje) {
                case "Sencillo":
                    Enormal_x = AX + BX;
                    Enormal_y = AY + BY;
                    Ecortante_xy = O;
                    break;
                case "Sencillo Dual":
                    Enormal_x = AX + BX + CX + DX;
                    Enormal_y = AY + BY + CY + DY;
                    Ecortante_xy = O + P;
                    break;
                case "Tandem":
                    Enormal_x = AX + BX + CX + DX + EX + FX + GX + HX;
                    Enormal_y = AY + BY + CY + DY + EY + FY + GY + HY;
                    Ecortante_xy = O + P + Q + R;
                    break;
                case "Tridem":
                    Enormal_x = AX + BX + CX + DX + EX + FX + GX + HX + IX + JX + KX + LX;
                    Enormal_y = AY + BY + CY + DY + EY + FY + GY + HY + IY + JY + KY + LY;
                    Ecortante_xy = O + P + Q + R + S + T;
                    break;
                case "Medio Tridem":
                    Enormal_x = AX + BX + EX + FX + IX + JX;
                    Enormal_y = AY + BY + EY + FY + IY + JY;
                    Ecortante_xy = O + Q + S;
                    break;
            }

            this.cal.getCapaCalculo(i).setEsfuerzoNormalX(Enormal_x);
            this.cal.getCapaCalculo(i).setEsfuerzoNormalY(Enormal_y);//Sheets("calculos").Cells(2 + i, 24) = Enormal_y
            this.cal.getCapaCalculo(i).setEsfuerzoCortanteXY(Ecortante_xy);//Sheets("calculos").Cells(2 + i, 25) = Ecortante_xy

        }

        //'DEFORMACIÓN POR TENSIÓN
        for (int i = 0; i < numCapas - 1; i++) {
            EvZ = this.cal.getCapaCalculo(i).getEsfuerzoVerticalO();//Sheets("calculos").Cells(i + 2, 10)
            ModElastico[i] = this.cal.getCapaCalculo(i).getModuloElastico();//Sheets("calculos").Cells(i + 2, 3)
            Enormal_x = this.cal.getCapaCalculo(i).getEsfuerzoNormalX(); //Sheets("calculos").Cells(i + 2, 23)
            Enormal_y = this.cal.getCapaCalculo(i).getEsfuerzoNormalY();//Sheets("calculos").Cells(i + 2, 24)
            Ecortante_xy = this.cal.getCapaCalculo(i).getEsfuerzoCortanteXY();//Sheets("calculos").Cells(i + 2, 25)

            DEFx = (1 / ModElastico[i]) * (Enormal_x - (poisson * (Enormal_y + EvZ)));
            DEFy = (1 / ModElastico[i]) * (Enormal_y - (poisson * (Enormal_x + EvZ)));
            GAMAxy = (2 * (1 + poisson) * Ecortante_xy) / ModElastico[i];

            DefTension = ((DEFx + DEFy) / 2) - Math.pow(Math.pow((Math.pow(((DEFx - DEFy) / 2), 2)) + GAMAxy, 2), 0.5);
            this.cal.getCapaCalculo(i).setDeformacionPorTension(DefTension);//Sheets("calculos").Cells(2 + i, 30) = DefTension

            //'calculados con damy
            EvZ_damy = this.cal.getCapaCalculo(i).getAux1();//Sheets("calculos").Cells(i + 5, 10)
            DEFx_damy = (1 / ModElastico[i]) * (Enormal_x - (poisson * (Enormal_y + EvZ_damy)));
            DEFy_damy = (1 / ModElastico[i]) * (Enormal_y - (poisson * (Enormal_x + EvZ_damy)));
            DefTension_damy = ((DEFx_damy + DEFy_damy) / 2) - Math.pow(Math.pow(Math.pow(((DEFx_damy - DEFy_damy) / 2), 2) + GAMAxy, 2), 0.5);
            this.cal.getCapaCalculo(i).setAuxDamy(DefTension_damy);//Sheets("calculos").Cells(5 + i, 30) = DefTension_damy

        }
    }

    /*Lista */
    private void deformacionesVerticales() {
        // Dim i As Integer
        //Dim NumCapas As Integer
        double ModElastico[] = new double[numCapas - 1];// As Double
        double EvZ, Enormal_x, Enormal_y;// As Double
        double DefVertical;// As Double
        double EvZ_damy, DefVertical_damy;//  As Double

        //NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
        //Poisson = Sheets("calculos").Cells(3, 5)
        //ReDim ModElastico(NumCapas)
        for (int i = 0; i < numCapas - 1; i++) {// = 1 To 2 'NumCapas - 1
            ModElastico[i]
                    = //Sheets("calculos").Cells(i + 2, 3)
                    EvZ = this.cal.getCapaCalculo(i).getEsfuerzoVerticalO();// Sheets("calculos").Cells(i + 2, 10)
            Enormal_x = this.cal.getCapaCalculo(i).getEsfuerzoNormalX();//Sheets("calculos").Cells(i + 2, 23)
            Enormal_y = this.cal.getCapaCalculo(i).getEsfuerzoNormalY();//Sheets("calculos").Cells(i + 2, 24)

            DefVertical = (1 / ModElastico[i]) * (EvZ - ((Enormal_x + Enormal_y) * poisson));
            this.cal.getCapaCalculo(i).setDeformacionVerticalE2(DefVertical);//Sheets("calculos").Cells(i + 2, 29) = DefVertical

            //'calculados con damy
            EvZ_damy = this.cal.getCapaCalculo(i).getAux1();//Sheets("calculos").Cells(i + 5, 10)
            DefVertical_damy = (1 / ModElastico[i]) * (EvZ_damy - ((Enormal_x + Enormal_y) * poisson));
            this.cal.getCapaCalculo(i).setauxVerDamy(DefVertical_damy);//Sheets("calculos").Cells(i + 5, 29) = DefVertical_damy

        }//Next i
    }

    /*Lista */
    private void esfuerzoVerticalPuntual() {

        int NumCapa;//As Integer
        double R1, R2, R3, R4, R5, R6;//As Double
        double Ez1, Ez2, Ez3, Ez4, Ez5, Ez6;//As Double
        double z, P;// As Double
        double KgAKpa;// As Double

        double EzP;// As Double
        double PesoNeum;// As Double
        String tipoeje;// As String

        KgAKpa = 98.0665;

        //NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
        PesoNeum = this.cal.getCapaCalculo(0).getPesoNeumatico();//Sheets("calculos").Cells(3, 4)

        P = 1000 * PesoNeum;

        for (int i = 0; i < numCapas - 1; i++) {//1 To 2 'NumCapas - 1

            z = this.cal.getCapaCalculo(i).getEspesorParcialEquivalente();//Sheets("calculos").Cells(2 + i, 7);

            R1 = i == 0 ? this.llantas.getLlanta(0).getAnguloHorizontal() : this.llantas.getLlanta(0).getDistanciaHorizontal();//Sheets("calculos").Cells(18, 4 + i)
            R2 = i == 0 ? this.llantas.getLlanta(1).getAnguloHorizontal() : this.llantas.getLlanta(1).getDistanciaHorizontal();//Sheets("calculos").Cells(19, 4 + i)
            R3 = i == 0 ? this.llantas.getLlanta(2).getAnguloHorizontal() : this.llantas.getLlanta(2).getDistanciaHorizontal();//Sheets("calculos").Cells(20, 4 + i)
            R4 = i == 0 ? this.llantas.getLlanta(3).getAnguloHorizontal() : this.llantas.getLlanta(3).getDistanciaHorizontal();//Sheets("calculos").Cells(21, 4 + i)
            R5 = i == 0 ? this.llantas.getLlanta(4).getAnguloHorizontal() : this.llantas.getLlanta(4).getDistanciaHorizontal();//Sheets("calculos").Cells(22, 4 + i)
            R6 = i == 0 ? this.llantas.getLlanta(5).getAnguloHorizontal() : this.llantas.getLlanta(5).getDistanciaHorizontal();//Sheets("calculos").Cells(23, 4 + i)

            Ez1 = KgAKpa * (3 * P * Math.pow(z, 3)) / (2 * PI * Math.pow(R1, 5));
            Ez2 = KgAKpa * (3 * P * Math.pow(z, 3)) / (2 * PI * Math.pow(R2, 5));
            Ez3 = KgAKpa * (3 * P * Math.pow(z, 3)) / (2 * PI * Math.pow(R3, 5));
            Ez4 = KgAKpa * (3 * P * Math.pow(z, 3)) / (2 * PI * Math.pow(R4, 5));
            Ez5 = KgAKpa * (3 * P * Math.pow(z, 3)) / (2 * PI * Math.pow(R5, 5));
            Ez6 = KgAKpa * (3 * P * Math.pow(z, 3)) / (2 * PI * Math.pow(R6, 5));

            switch (tipoEje) {
                case "Sencillo":
                    EzP = Ez1;
                    break;
                case "Sencillo Dual":
                    EzP = Ez1 + Ez2;
                    break;
                case "Tandem":
                    EzP = Ez1 + Ez2 + Ez3 + Ez4;
                    break;
                case "Tridem":
                    EzP = Ez1 + Ez2 + Ez3 + Ez4 + Ez5 + Ez6;
                    break;
                case "Medio Tridem":
                    EzP = Ez1 + Ez3 + Ez5;
                    break;
                default:
                    EzP = 0;
            }

            this.cal.getCapaCalculo(i).setEsfuerzoVerticalO(EzP);

        }

        for (int numCapa = 0; numCapa < numCapas - 1; numCapa++) {// 'NumCapas - 1
            esfuerzos_Damy(numCapa);
        }//Next NumCapa
    }

    /*Lista*/
    private void esfuerzoRadialPuntual() {

        double ra1, ra2, ra3, ra4, ra5, ra6;// As Double
        double R1, R2, R3, R4, R5, R6;// As Double
        double Er1, Er2, Er3, Er4, Er5, Er6;// As Double
        double z, P;// As Double
        double KgAKpa;//As Double

        double PesoNeum;// As Double
        var tipoeje = this.tipoEje;// As String
        double ErP;// As Double

        KgAKpa = 98.0665;

        PesoNeum = this.cal.getCapaCalculo(0).getPesoNeumatico();//Sheets("calculos").Cells(3, 4)
        P = 1000 * PesoNeum;

        ra1 = this.llantas.getLlanta(0).getDistanciaHorizontal();//Sheets("calculos").Cells(18, 4)
        ra2 = this.llantas.getLlanta(0).getDistanciaHorizontal();
        ra3 = this.llantas.getLlanta(0).getDistanciaHorizontal();
        ra4 = this.llantas.getLlanta(0).getDistanciaHorizontal();
        ra5 = this.llantas.getLlanta(0).getDistanciaHorizontal();
        ra6 = this.llantas.getLlanta(0).getDistanciaHorizontal();

        for (int i = 0; i < numCapas - 1; i++) {

            z = this.cal.getCapaCalculo(i).getEspesorParcialEquivalente();//Sheets("calculos").Cells(2 + i, 7)

            R1 = (i == 0) ? this.llantas.getLlanta(0).getDistanciaRadialCarpeta() : this.llantas.getLlanta(0).getDistanciaRadialSubrasante();  //Sheets("calculos").Cells(18, 4 + i)
            R2 = (i == 0) ? this.llantas.getLlanta(1).getDistanciaRadialCarpeta() : this.llantas.getLlanta(1).getDistanciaRadialSubrasante();  //Sheets("calculos").Cells(19, 4 + i)
            R3 = (i == 0) ? this.llantas.getLlanta(2).getDistanciaRadialCarpeta() : this.llantas.getLlanta(2).getDistanciaRadialSubrasante();  //Sheets("calculos").Cells(20, 4 + i)
            R4 = (i == 0) ? this.llantas.getLlanta(3).getDistanciaRadialCarpeta() : this.llantas.getLlanta(3).getDistanciaRadialSubrasante();  //Sheets("calculos").Cells(21, 4 + i)
            R5 = (i == 0) ? this.llantas.getLlanta(4).getDistanciaRadialCarpeta() : this.llantas.getLlanta(4).getDistanciaRadialSubrasante();  //Sheets("calculos").Cells(22, 4 + i)
            R6 = (i == 0) ? this.llantas.getLlanta(5).getDistanciaRadialCarpeta() : this.llantas.getLlanta(5).getDistanciaRadialSubrasante();  //Sheets("calculos").Cells(23, 4 + i)

            Er1 = KgAKpa * ((P / (2 * PI)) * (((3 * z * Math.pow(ra1, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R1 * (R1 + z)))));
            Er2 = KgAKpa * ((P / (2 * PI)) * (((3 * z * Math.pow(ra2, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R2 * (R2 + z)))));
            Er3 = KgAKpa * ((P / (2 * PI)) * (((3 * z * Math.pow(ra3, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R3 * (R3 + z)))));
            Er4 = KgAKpa * ((P / (2 * PI)) * (((3 * z * Math.pow(ra4, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R4 * (R4 + z)))));
            Er5 = KgAKpa * ((P / (2 * PI)) * (((3 * z * Math.pow(ra5, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R5 * (R5 + z)))));
            Er6 = KgAKpa * ((P / (2 * PI)) * (((3 * z * Math.pow(ra6, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R6 * (R6 + z)))));

            switch (tipoeje) {
                case "Sencillo":
                    ErP = Er1;
                case "Sencillo Dual":
                    ErP = Er1 + Er2;
                case "Tandem":
                    ErP = Er1 + Er2 + Er3 + Er4;
                case "Tridem":
                    ErP = Er1 + Er2 + Er3 + Er4 + Er5 + Er6;
                case "Medio Tridem":
                    ErP = Er1 + Er3 + Er5;
                default:
                    ErP = 0;
            }

            this.cal.getCapaCalculo(i).setEsfuerzoRadialO(ErP);//Sheets("calculos").Cells(2 + i, 11) = ErP

            if (i == 0) {
                this.llantas.getLlanta(0).setOrNeumatico1(Er1);
                this.llantas.getLlanta(1).setOrNeumatico1(Er2);
                this.llantas.getLlanta(2).setOrNeumatico1(Er3);
                this.llantas.getLlanta(3).setOrNeumatico1(Er4);
                this.llantas.getLlanta(4).setOrNeumatico1(Er5);
                this.llantas.getLlanta(5).setOrNeumatico1(Er6);
            } else {
                this.llantas.getLlanta(0).setOrNeumatico2(Er1);
                this.llantas.getLlanta(1).setOrNeumatico2(Er2);
                this.llantas.getLlanta(2).setOrNeumatico2(Er3);
                this.llantas.getLlanta(3).setOrNeumatico2(Er4);
                this.llantas.getLlanta(4).setOrNeumatico2(Er5);
                this.llantas.getLlanta(5).setOrNeumatico2(Er6);
            }

        }
    }

    /*Lista */
    private void esfuerzoTangencialPuntual() {
        //Dim i As Integer
        //Dim NumCapas As Integer
        double R1, R2, R3, R4, R5, R6;// As Double
        double Et1, Et2, Et3, Et4, Et5, Et6;// As Double
        double z, P;// As Double
        double KgAKpa;// As Double

        double PesoNeum;// As Double
        String tipoeje = this.tipoEje;// As String
        double EtP;// As Double

        KgAKpa = 98.0665;

        PesoNeum = this.cal.getCapaCalculo(0).getPesoNeumatico(); //Sheets("calculos").Cells(3, 4)
        P = 1000 * PesoNeum;

        for (int i = 0; i < numCapas - 1; i++) {// = 1 To 2 'NumCapas - 1

            z = this.cal.getCapaCalculo(i).getEspesorParcialEquivalente(); //Sheets("calculos").Cells(2 + i, 7)

            R1 = i == 0 ? this.llantas.getLlanta(0).getAnguloHorizontal() : this.llantas.getLlanta(0).getDistanciaHorizontal(); //Sheets("calculos").Cells(18, 4 + i)
            R2 = i == 0 ? this.llantas.getLlanta(1).getAnguloHorizontal() : this.llantas.getLlanta(1).getDistanciaHorizontal(); //Sheets("calculos").Cells(19, 4 + i)
            R3 = i == 0 ? this.llantas.getLlanta(2).getAnguloHorizontal() : this.llantas.getLlanta(2).getDistanciaHorizontal(); //Sheets("calculos").Cells(20, 4 + i)
            R4 = i == 0 ? this.llantas.getLlanta(3).getAnguloHorizontal() : this.llantas.getLlanta(3).getDistanciaHorizontal(); // Sheets("calculos").Cells(21, 4 + i)
            R5 = i == 0 ? this.llantas.getLlanta(4).getAnguloHorizontal() : this.llantas.getLlanta(4).getDistanciaHorizontal(); //Sheets("calculos").Cells(22, 4 + i)
            R6 = i == 0 ? this.llantas.getLlanta(5).getAnguloHorizontal() : this.llantas.getLlanta(5).getDistanciaHorizontal(); // Sheets("calculos").Cells(23, 4 + i)

            Et1 = KgAKpa * (((1 - 2 * poisson) * (P / (2 * PI))) * ((1 / (R1 * (R1 + z))) - (z / Math.pow(R1, 3))));
            Et2 = KgAKpa * (((1 - 2 * poisson) * (P / (2 * PI))) * ((1 / (R2 * (R2 + z))) - (z / Math.pow(R1, 3))));
            Et3 = KgAKpa * (((1 - 2 * poisson) * (P / (2 * PI))) * ((1 / (R3 * (R3 + z))) - (z / Math.pow(R1, 3))));
            Et4 = KgAKpa * (((1 - 2 * poisson) * (P / (2 * PI))) * ((1 / (R4 * (R4 + z))) - (z / Math.pow(R1, 3))));
            Et5 = KgAKpa * (((1 - 2 * poisson) * (P / (2 * PI))) * ((1 / (R5 * (R5 + z))) - (z / Math.pow(R1, 3))));
            Et6 = KgAKpa * (((1 - 2 * poisson) * (P / (2 * PI))) * ((1 / (R6 * (R6 + z))) - (z / Math.pow(R1, 3))));

            switch (tipoeje) {
                case "Sencillo":
                    EtP = Et1;
                    break;
                case "Sencillo Dual":
                    EtP = Et1 + Et2;
                    break;
                case "Tandem":
                    EtP = Et1 + Et2 + Et3 + Et4;
                    break;
                case "Tridem":
                    EtP = Et1 + Et2 + Et3 + Et4 + Et5 + Et6;
                    break;
                case "Medio Tridem":
                    EtP = Et1 + Et3 + Et5;
                    break;
                default:
                    EtP = 0;

            }

            this.cal.getCapaCalculo(i).setEsfuerzoTangencialO(EtP);//Sheets("calculos").Cells(2 + i, 12) = EtP
            if (i == 0) {
                this.llantas.getLlanta(0).setOtNeumatico1(Et1);
                this.llantas.getLlanta(1).setOtNeumatico1(Et2);
                this.llantas.getLlanta(2).setOtNeumatico1(Et3);
                this.llantas.getLlanta(3).setOtNeumatico1(Et4);
                this.llantas.getLlanta(4).setOtNeumatico1(Et5);
                this.llantas.getLlanta(5).setOtNeumatico1(Et6);
            } else {
                this.llantas.getLlanta(0).setOtNeumatico2(Et1);
                this.llantas.getLlanta(1).setOtNeumatico2(Et2);
                this.llantas.getLlanta(2).setOtNeumatico2(Et3);
                this.llantas.getLlanta(3).setOtNeumatico2(Et4);
                this.llantas.getLlanta(4).setOtNeumatico2(Et5);
                this.llantas.getLlanta(5).setOtNeumatico2(Et6);

            }//Sheets("calculos").Cells(41, 4 + i) = Et1

        }
    }

    /*Lista */
    private void esfuerzosEjeSencilloDual() {
        double PsiAKpa;// As Double
        double Er1, Er2;// As Double
        double Et1, Et2;// As Double

        double PresionInflado = this.presion;// As Double
        double FEV1, FEV2, FEV3, FEV4, FER1, FER2, FER3, FER4, FET1, FET2, FET3, FET4;// As Double
        //Dim NumCapas As Integer
        int NumCapa;// As Integer
        String tipoeje = tipoEje;// As String

        PsiAKpa = 6.894757;

        FEV1 = this.llantas.getLlanta(0).getFactorEVertical();//Sheets("calculos").Cells(26, 4)
        FEV2 = this.llantas.getLlanta(1).getFactorEVertical();//Sheets("calculos").Cells(27, 4)
        FER1 = this.llantas.getLlanta(0).getFactorERadial();//Sheets("calculos").Cells(26, 5)
        FER2 = this.llantas.getLlanta(1).getFactorERadial();//Sheets("calculos").Cells(27, 5)
        FET1 = this.llantas.getLlanta(0).getFactorETangencial();//Sheets("calculos").Cells(26, 6)
        FET2 = this.llantas.getLlanta(1).getFactorETangencial();// Sheets("calculos").Cells(27, 6)

        //'ESFUERZO VERTICAL
        this.cal.getCapaCalculo(0).setEsfuerzoVerticalO(PsiAKpa * presion * (FEV1 + FEV2));//Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (FEV1 + FEV2)   

        //'para la primer capa
        E_VERTICAL_PTODOS_MA();

        //'para el resto de las capas
        for (NumCapa = 0; NumCapa < numCapas - 1; NumCapa++) {// ' NumCapas - 1
            esfuerzos_Damy(NumCapa);
        }

        //'ESFUERZO RADIAL
        this.cal.getCapaCalculo(0).setEsfuerzoRadialO(PsiAKpa * presion * (FER1 + FER2));//Sheets("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (FER1 + FER2)   

        //'para la primer capa
        Er1 = PsiAKpa * presion * FER1;

        //'para el resto de las capas
        Er2 = PsiAKpa * presion * FER2;

        E_RADIAL_PTODOS_MA();

        //'ESFUERZO TANGENCIAL
        this.cal.getCapaCalculo(0).setEsfuerzoTangencialO(PsiAKpa * presion * (FET1 + FET2));//Sheets("calculos").Cells(3, 12) = PsiAKpa * PresionInflado * (FET1 + FET2)   

        //'para la primer capa
        Et1 = PsiAKpa * presion * FET1;

        //'para el resto de las capas
        Et2 = PsiAKpa * presion * FET2;

        E_TANGENCIAL_PTODOS_MA();

        this.llantas.getLlanta(0).setOrNeumatico1(Er1);//Sheets("calculos").Cells(41, 3) = Er1
        this.llantas.getLlanta(1).setOrNeumatico1(Er2);//Sheets("calculos").Cells(42, 3) = Er2
        this.llantas.getLlanta(0).setOtNeumatico1(Et1);//Sheets("calculos").Cells(41, 5) = Et1
        this.llantas.getLlanta(1).setOtNeumatico1(Et2);//Sheets("calculos").Cells(42, 5) = Et2
    }

    /*Lista */
    private void esfuerzosEjeTridem() {
        double PsiAKpa;// As Double
        double Er1, Er2, Er3, Er4, Er5, Er6;// As Double
        double Et1, Et2, Et3, Et4, Et5, Et6;// As Double

        double PresionInflado = presion;// As Double
        double FEV1, FEV2, FEV3, FEV4, FER1, FER2, FER3, FER4, FET1, FET2, FET3, FET4;// As Double
        double X, Y, S, D;// As Double

        PsiAKpa = 6.894757;

        X = this.X;//Sheets("Avanzado").Cells(17, 2)
        Y = this.Y;//Sheets("Avanzado").Cells(18, 2)
        S = this.S;//Sheets("Avanzado").Cells(17, 4)
        D = this.D;//Sheets("Avanzado").Cells(18, 4)

        FEV1 = this.llantas.getLlanta(0).getFactorEVertical();// Sheets("calculos").Cells(26, 4)
        FEV2 = this.llantas.getLlanta(1).getFactorEVertical();//Sheets("calculos").Cells(27, 4)
        FEV3 = this.llantas.getLlanta(2).getFactorEVertical();//Sheets("calculos").Cells(28, 4)
        FEV4 = this.llantas.getLlanta(3).getFactorEVertical();//Sheets("calculos").Cells(29, 4)
        FER1 = this.llantas.getLlanta(0).getFactorERadial();//Sheets("calculos").Cells(26, 5)
        FER2 = this.llantas.getLlanta(1).getFactorERadial();//Sheets("calculos").Cells(27, 5)
        FER3 = this.llantas.getLlanta(2).getFactorERadial();//Sheets("calculos").Cells(28, 5)
        FER4 = this.llantas.getLlanta(3).getFactorERadial();//Sheets("calculos").Cells(29, 5)
        FET1 = this.llantas.getLlanta(0).getFactorETangencial();// Sheets("calculos").Cells(26, 6)
        FET2 = this.llantas.getLlanta(1).getFactorETangencial();//Sheets("calculos").Cells(27, 6)
        FET3 = this.llantas.getLlanta(2).getFactorETangencial();//Sheets("calculos").Cells(28, 6)
        FET4 = this.llantas.getLlanta(3).getFactorETangencial();//Sheets("calculos").Cells(29, 6)

        //'ESFUERZO VERTICAL
        if (X == D) {

            this.cal.getCapaCalculo(0).setEsfuerzoVerticalO(PsiAKpa * PresionInflado * (2 * (FEV1 + FEV2) + FEV3 + FEV4));//Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (2 * (FEV1 + FEV2) + FEV3 + FEV4);
        } else {
            this.cal.getCapaCalculo(0).setEsfuerzoVerticalO(PsiAKpa * PresionInflado * (FEV1 + FEV2 + FEV3 + FEV4));// Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (FEV1 + FEV2 + FEV3 + FEV4);
        }

        E_VERTICAL_PTODOS_MA();

        for (int numCapa = 0; numCapa < numCapas - 1; numCapa++) {
            esfuerzos_Damy(numCapa);
        }

        //'ESFUERZO RADIAL
        if (X == D) { //Then Sheets
            this.cal.getCapaCalculo(0).setEsfuerzoRadialO(PsiAKpa * PresionInflado * (2 * (FER1 + FER2) + FER3 + FER4));//("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (2 * (FER1 + FER2) + FER3 + FER4)

            Er1 = PsiAKpa * PresionInflado * FER1;
            Er2 = PsiAKpa * PresionInflado * FER2;
            Er3 = PsiAKpa * PresionInflado * FER3;
            Er4 = PsiAKpa * PresionInflado * FER4;
            Er5 = PsiAKpa * PresionInflado * FER1;
            Er6 = PsiAKpa * PresionInflado * FER2;

        } else {
            this.cal.getCapaCalculo(0).setEsfuerzoRadialO(PsiAKpa * PresionInflado * (FER1 + FER2 + FER3 + FER4));//("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (FER1 + FER2 + FER3 + FER4)
            Er1 = PsiAKpa * PresionInflado * FER1;
            Er2 = PsiAKpa * PresionInflado * FER2;
            Er3 = PsiAKpa * PresionInflado * FER3;
            Er4 = PsiAKpa * PresionInflado * FER4;
            Er5 = 0;
            Er6 = 0;

        }

        E_RADIAL_PTODOS_MA();

        //'ESFUERZO TANGENCIAL
        if (X == D) {// Then Sheets
            this.cal.getCapaCalculo(0).setEsfuerzoTangencialO(PsiAKpa * PresionInflado * (2 * (FET1 + FET2) + FET3 + FET4));//("calculos").Cells(3, 12) = PsiAKpa * PresionInflado * (2 * (FET1 + FET2) + FET3 + FET4)

            Et1 = PsiAKpa * PresionInflado * FET1;
            Et2 = PsiAKpa * PresionInflado * FET2;
            Et3 = PsiAKpa * PresionInflado * FET3;
            Et4 = PsiAKpa * PresionInflado * FET4;
            Et5 = PsiAKpa * PresionInflado * FET1;
            Et6 = PsiAKpa * PresionInflado * FET2;

        } else {
            this.cal.getCapaCalculo(0).setEsfuerzoTangencialO(PsiAKpa * PresionInflado * (FET1 + FET2) + FET3 + FET4);

            Et1 = PsiAKpa * PresionInflado * FET1;
            Et2 = PsiAKpa * PresionInflado * FET2;
            Et3 = PsiAKpa * PresionInflado * FET3;
            Et4 = PsiAKpa * PresionInflado * FET4;
            Et5 = 0;
            Et6 = 0;

        }

        E_TANGENCIAL_PTODOS_MA();

        this.llantas.getLlanta(0).setOrNeumatico1(Er1);// Sheets("calculos").Cells(41, 3) = Er1
        this.llantas.getLlanta(1).setOrNeumatico1(Er2);
        this.llantas.getLlanta(2).setOrNeumatico1(Er3);
        this.llantas.getLlanta(3).setOrNeumatico1(Er4);
        this.llantas.getLlanta(4).setOrNeumatico1(Er5);
        this.llantas.getLlanta(5).setOrNeumatico1(Er6);

        this.llantas.getLlanta(0).setOtNeumatico1(Et1);
        this.llantas.getLlanta(1).setOtNeumatico1(Et2);
        this.llantas.getLlanta(2).setOtNeumatico1(Et3);
        this.llantas.getLlanta(3).setOtNeumatico1(Et4);
        this.llantas.getLlanta(4).setOtNeumatico1(Et5);
        this.llantas.getLlanta(5).setOtNeumatico1(Et6);
    }

    /*Funcion Decrepita*/
    private void printResIndividual() {
        //Dim i As Integer
        /*Dim TipoAnalisis As String
        Dim NumCapas As Integer
        Dim X As Integer


        TipoAnalisis = Sheets("Avanzado").Cells(3, 4)
        NumCapas = Sheets("Análisis Individual").Cells(10, 6)

        If TipoAnalisis = "Individual" Then Sheets
        ("Análisis Individual").Cells(15, 25) = Sheets("calculos").Cells(3, 22)
        Sheets("Análisis Individual").Cells(17, 13) = Sheets("calculos").Cells(3, 30)
        Sheets("Análisis Individual").Cells(17, 28) = Sheets("calculos").Cells(6, 10)
            
        Select Case NumCapas Case Is = 2
        X = 19
        Case Is = 3
        X = 19
        Case Is = 4
        X = 21
        Case Is = 5
        X = 23
        Case Is >= 6
                X = 25
        Case Else
      
        End Select
        Sheets("Análisis Individual").Cells(X, 13) = Sheets("calculos").Cells(4, 29)
        Sheets("Análisis Individual").Cells(X, 28) = Sheets("calculos").Cells(7, 10)
        Else End If*/

        //Funcion inaxesible por usuario.
    }

    /*Lista */
    private void esfuerzosEjeMedioTridem() {

        double PsiAKpa;// As Double
        double Er1, Er2, Er3, Er4, Er5, Er6;// As Double
        double Et1, Et2, Et3, Et4, Et5, Et6;// As Double

        double PresionInflado = presion;// As Double
        double FEV1, FEV2, FEV3, FEV4, FER1, FER2, FER3, FER4, FET1, FET2, FET3, FET4;// As Double
        double X, Y, S, D;// As Double

        PsiAKpa = 6.894757;

        X = this.X;//Sheets("Avanzado").Cells(17, 2)
        Y = this.Y;//Sheets("Avanzado").Cells(18, 2)
        S = this.S;//Sheets("Avanzado").Cells(17, 4)
        D = this.D;//Sheets("Avanzado").Cells(18, 4)

        FEV1 = this.llantas.getLlanta(0).getFactorEVertical();// Sheets("calculos").Cells(26, 4)
        FEV2 = this.llantas.getLlanta(1).getFactorEVertical();//Sheets("calculos").Cells(27, 4)
        FEV3 = this.llantas.getLlanta(2).getFactorEVertical();//Sheets("calculos").Cells(28, 4)
        FEV4 = this.llantas.getLlanta(3).getFactorEVertical();//Sheets("calculos").Cells(29, 4)
        FER1 = this.llantas.getLlanta(0).getFactorERadial();//Sheets("calculos").Cells(26, 5)
        FER2 = this.llantas.getLlanta(1).getFactorERadial();//Sheets("calculos").Cells(27, 5)
        FER3 = this.llantas.getLlanta(2).getFactorERadial();//Sheets("calculos").Cells(28, 5)
        FER4 = this.llantas.getLlanta(3).getFactorERadial();//Sheets("calculos").Cells(29, 5)
        FET1 = this.llantas.getLlanta(0).getFactorETangencial();// Sheets("calculos").Cells(26, 6)
        FET2 = this.llantas.getLlanta(1).getFactorETangencial();//Sheets("calculos").Cells(27, 6)
        FET3 = this.llantas.getLlanta(2).getFactorETangencial();//Sheets("calculos").Cells(28, 6)
        FET4 = this.llantas.getLlanta(3).getFactorETangencial();//Sheets("calculos").Cells(29, 6)

        //'ESFUERZO VERTICAL
        if (X == D) {

            this.cal.getCapaCalculo(0).setEsfuerzoVerticalO(PsiAKpa * PresionInflado * (2 * FEV1 + FEV3));//Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (2 * (FEV1 + FEV2) + FEV3 + FEV4);
        } else {
            this.cal.getCapaCalculo(0).setEsfuerzoVerticalO(PsiAKpa * PresionInflado * (FEV1 + FEV3));// Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (FEV1 + FEV2 + FEV3 + FEV4);
        }

        E_VERTICAL_PTODOS_MA();

        
        for (int numCapa = 0; numCapa < numCapas - 1; numCapa++) {
            esfuerzos_Damy(numCapa);
        }

        //'ESFUERZO RADIAL
        if (X == D) { //Then Sheets
            this.cal.getCapaCalculo(0).setEsfuerzoRadialO(PsiAKpa * PresionInflado * (2 * FER1 + FER3));//("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (2 * (FER1 + FER2) + FER3 + FER4)

            Er1 = PsiAKpa * PresionInflado * FER1;
            Er2 = PsiAKpa * PresionInflado * FER2;
            Er3 = PsiAKpa * PresionInflado * FER3;
            Er4 = PsiAKpa * PresionInflado * FER4;
            Er5 = PsiAKpa * PresionInflado * FER1;
            Er6 = PsiAKpa * PresionInflado * FER2;

        } else {
            this.cal.getCapaCalculo(0).setEsfuerzoRadialO(PsiAKpa * PresionInflado * (FER1 + FER3));//("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (FER1 + FER2 + FER3 + FER4)
            Er1 = PsiAKpa * PresionInflado * FER1;
            Er2 = PsiAKpa * PresionInflado * FER2;
            Er3 = PsiAKpa * PresionInflado * FER3;
            Er4 = PsiAKpa * PresionInflado * FER4;
            Er5 = 0;
            Er6 = 0;

        }

        E_RADIAL_PTODOS_MA();

        //'ESFUERZO TANGENCIAL
        if (X == D) {// Then Sheets
            this.cal.getCapaCalculo(0).setEsfuerzoTangencialO(PsiAKpa * PresionInflado * (2 * FET1 + FET3));//("calculos").Cells(3, 12) = PsiAKpa * PresionInflado * (2 * (FET1 + FET2) + FET3 + FET4)

            Et1 = PsiAKpa * PresionInflado * FET1;
            Et2 = PsiAKpa * PresionInflado * FET2;
            Et3 = PsiAKpa * PresionInflado * FET3;
            Et4 = PsiAKpa * PresionInflado * FET4;
            Et5 = PsiAKpa * PresionInflado * FET1;
            Et6 = PsiAKpa * PresionInflado * FET2;

        } else {
            this.cal.getCapaCalculo(0).setEsfuerzoTangencialO(PsiAKpa * PresionInflado * (FET1 + FET3));

            Et1 = PsiAKpa * PresionInflado * FET1;
            Et2 = PsiAKpa * PresionInflado * FET2;
            Et3 = PsiAKpa * PresionInflado * FET3;
            Et4 = PsiAKpa * PresionInflado * FET4;
            Et5 = 0;
            Et6 = 0;

        }

        E_TANGENCIAL_PTODOS_MA();

        this.llantas.getLlanta(0).setOrNeumatico1(Er1);// Sheets("calculos").Cells(41, 3) = Er1
        this.llantas.getLlanta(1).setOrNeumatico1(Er2);
        this.llantas.getLlanta(2).setOrNeumatico1(Er3);
        this.llantas.getLlanta(3).setOrNeumatico1(Er4);
        this.llantas.getLlanta(4).setOrNeumatico1(Er5);
        this.llantas.getLlanta(5).setOrNeumatico1(Er6);

        this.llantas.getLlanta(0).setOtNeumatico1(Et1);
        this.llantas.getLlanta(1).setOtNeumatico1(Et2);
        this.llantas.getLlanta(2).setOtNeumatico1(Et3);
        this.llantas.getLlanta(3).setOtNeumatico1(Et4);
        this.llantas.getLlanta(4).setOtNeumatico1(Et5);
        this.llantas.getLlanta(5).setOtNeumatico1(Et6);
    }

    /*Lista */
    private void esfuerzosEjeTandem() {

        double PsiAKpa;// As Double
        double Er1, Er2, Er3, Er4, Er5, Er6;// As Double
        double Et1, Et2, Et3, Et4, Et5, Et6;// As Double

        double PresionInflado = presion;// As Double
        double FEV1, FEV2, FEV3, FEV4, FER1, FER2, FER3, FER4, FET1, FET2, FET3, FET4;// As Double
        double X, Y, S, D;// As Double

        PsiAKpa = 6.894757;

        X = this.X;//Sheets("Avanzado").Cells(17, 2)
        Y = this.Y;//Sheets("Avanzado").Cells(18, 2)
        S = this.S;//Sheets("Avanzado").Cells(17, 4)
        D = this.D;//Sheets("Avanzado").Cells(18, 4)

        FEV1 = this.llantas.getLlanta(0).getFactorEVertical();// Sheets("calculos").Cells(26, 4)
        FEV2 = this.llantas.getLlanta(1).getFactorEVertical();//Sheets("calculos").Cells(27, 4)
        FEV3 = this.llantas.getLlanta(2).getFactorEVertical();//Sheets("calculos").Cells(28, 4)
        FEV4 = this.llantas.getLlanta(3).getFactorEVertical();//Sheets("calculos").Cells(29, 4)
        FER1 = this.llantas.getLlanta(0).getFactorERadial();//Sheets("calculos").Cells(26, 5)
        FER2 = this.llantas.getLlanta(1).getFactorERadial();//Sheets("calculos").Cells(27, 5)
        FER3 = this.llantas.getLlanta(2).getFactorERadial();//Sheets("calculos").Cells(28, 5)
        FER4 = this.llantas.getLlanta(3).getFactorERadial();//Sheets("calculos").Cells(29, 5)
        FET1 = this.llantas.getLlanta(0).getFactorETangencial();// Sheets("calculos").Cells(26, 6)
        FET2 = this.llantas.getLlanta(1).getFactorETangencial();//Sheets("calculos").Cells(27, 6)
        FET3 = this.llantas.getLlanta(2).getFactorETangencial();//Sheets("calculos").Cells(28, 6)
        FET4 = this.llantas.getLlanta(3).getFactorETangencial();//Sheets("calculos").Cells(29, 6)

        //'ESFUERZO VERTICAL
        this.cal.getCapaCalculo(0).setEsfuerzoVerticalO(PsiAKpa * PresionInflado * (FEV1 + FEV2 + FEV3 + FEV4));//Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (2 * (FEV1 + FEV2) + FEV3 + FEV4);

        E_VERTICAL_PTODOS_MA();

        for (int numCapa = 0; numCapa < numCapas - 1; numCapa++) {
            esfuerzos_Damy(numCapa);
        }

        //'ESFUERZO RADIAL
        this.cal.getCapaCalculo(0).setEsfuerzoRadialO(PsiAKpa * PresionInflado * (FER1 + FER2 + FER3 + FER4));//("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (2 * (FER1 + FER2) + FER3 + FER4)

        Er1 = PsiAKpa * PresionInflado * FER1;
        Er2 = PsiAKpa * PresionInflado * FER2;
        Er3 = PsiAKpa * PresionInflado * FER3;
        Er4 = PsiAKpa * PresionInflado * FER4;

        E_RADIAL_PTODOS_MA();

        //'ESFUERZO TANGENCIAL
        this.cal.getCapaCalculo(0).setEsfuerzoTangencialO(PsiAKpa * PresionInflado * (FET1 + FET2 + FET3 + FET4));//("calculos").Cells(3, 12) = PsiAKpa * PresionInflado * (2 * (FET1 + FET2) + FET3 + FET4)

        Et1 = PsiAKpa * PresionInflado * FET1;
        Et2 = PsiAKpa * PresionInflado * FET2;
        Et3 = PsiAKpa * PresionInflado * FET3;
        Et4 = PsiAKpa * PresionInflado * FET4;

        E_TANGENCIAL_PTODOS_MA();

        this.llantas.getLlanta(0).setOrNeumatico1(Er1);// Sheets("calculos").Cells(41, 3) = Er1
        this.llantas.getLlanta(1).setOrNeumatico1(Er2);
        this.llantas.getLlanta(2).setOrNeumatico1(Er3);
        this.llantas.getLlanta(3).setOrNeumatico1(Er4);

        this.llantas.getLlanta(0).setOtNeumatico1(Et1);
        this.llantas.getLlanta(1).setOtNeumatico1(Et2);
        this.llantas.getLlanta(2).setOtNeumatico1(Et3);
        this.llantas.getLlanta(3).setOtNeumatico1(Et4);

    }

    /*Lista*/
    private void E_VERTICAL_PTODOS_MA() {
        int RENGLON;// As Integer
        int NCAPAS;// As Integer
        double A;// As Double
        double B1, B2, B3, B4, B5, B6;// As Double
        double C1, C2, C3, C4, C5, C6;// As Double
        double ra1, ra2, ra3, ra4, ra5, ra6;// As Double
        double R1, R2, R3, R4, R5, R6;// As Double
        double Ez1, Ez2, Ez3, Ez4, Ez5, Ez6;// As Double
        double z, P;// As Double
        double KgAKpa;//a As Double
        double PESO_EJE;// As Double

        double PesoNeum;// As Double
        String tipoeje = this.tipoEje;// As String
        double EvZ;// As Double

        KgAKpa = 98.0665;
        PesoNeum = this.cal.getCapaCalculo(0).getPesoNeumatico(); //Sheets("calculos").Cells(3, 4)
        z = this.cal.getCapaCalculo(1).getEspesorParcialEquivalente();// Sheets("calculos").Cells(4, 7)
        P = 1000 * PesoNeum;

        R1 = this.llantas.getLlanta(0).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(18, 6)
        R2 = this.llantas.getLlanta(1).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(19, 6)
        R3 = this.llantas.getLlanta(2).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(20, 6)
        R4 = this.llantas.getLlanta(3).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(21, 6)
        R5 = this.llantas.getLlanta(4).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(22, 6)
        R6 = this.llantas.getLlanta(5).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(23, 6)

        double aux1 = Math.pow(R1, 3);
        Ez1 = KgAKpa * (3 * P * aux1) / (2 * PI * Math.pow(R1, 5));
        Ez2 = KgAKpa * (3 * P * aux1) / (2 * PI * Math.pow(R1, 5));
        Ez3 = KgAKpa * (3 * P * aux1) / (2 * PI * Math.pow(R1, 5));
        Ez4 = KgAKpa * (3 * P * aux1) / (2 * PI * Math.pow(R1, 5));
        Ez5 = KgAKpa * (3 * P * aux1) / (2 * PI * Math.pow(R1, 5));
        Ez6 = KgAKpa * (3 * P * aux1) / (2 * PI * Math.pow(R1, 5));

        switch (tipoeje) {
            case "Sencillo":
                EvZ = Ez1;
                break;
            case "Sencillo Dual":
                EvZ = Ez1 + Ez2;
                break;
            case "Tandem":
                EvZ = Ez1 + Ez2 + Ez3 + Ez4;
                break;
            case "Tridem":
                EvZ = Ez1 + Ez2 + Ez3 + Ez4 + Ez5 + Ez6;
                break;
            case "Medio Tridem":
                EvZ = Ez1 + Ez3 + Ez5;
                break;
            default:
                EvZ = 0;

        }
        this.cal.getCapaCalculo(1).setEsfuerzoVerticalO(EvZ);//Sheets("calculos").Cells(4, 10) = EvZ
    }

    /*Lista*/
    private void E_RADIAL_PTODOS_MA() {

        double A;//As Double
        double ra1, ra2, ra3, ra4, ra5, ra6;//As Double
        double R1, R2, R3, R4, R5, R6;// As Double
        double Er1, Er2, Er3, Er4, Er5, Er6;// As Double
        double z, P;// As Double
        double KgAKpa;// As Double
        String tipoeje = this.tipoEje;// As String
        double PesoNeum;// As Double
        double ErZ;// As Double

        KgAKpa = 98.0665;
        z = this.cal.getCapaCalculo(1).getEspesorParcialEquivalente();// Sheets("calculos").Cells(4, 7)
        PesoNeum = this.cal.getCapaCalculo(0).getPesoNeumatico(); //Sheets("calculos").Cells(3, 4)
        P = 1000 * PesoNeum;
        A = P / (2 * PI);

        ra1 = this.llantas.getLlanta(0).getDistanciaHorizontal();//Sheets("calculos").Cells(18, 4)
        ra2 = this.llantas.getLlanta(1).getDistanciaHorizontal();//Sheets("calculos").Cells(19, 4)
        ra3 = this.llantas.getLlanta(2).getDistanciaHorizontal();//Sheets("calculos").Cells(20, 4)
        ra4 = this.llantas.getLlanta(3).getDistanciaHorizontal();//Sheets("calculos").Cells(21, 4)
        ra5 = this.llantas.getLlanta(4).getDistanciaHorizontal();//Sheets("calculos").Cells(22, 4)
        ra6 = this.llantas.getLlanta(5).getDistanciaHorizontal();//Sheets("calculos").Cells(23, 4)

        R1 = this.llantas.getLlanta(0).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(18, 6)
        R2 = this.llantas.getLlanta(1).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(19, 6)
        R3 = this.llantas.getLlanta(2).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(20, 6)
        R4 = this.llantas.getLlanta(3).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(21, 6)
        R5 = this.llantas.getLlanta(4).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(22, 6)
        R6 = this.llantas.getLlanta(5).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(23, 6)

        Er1 = KgAKpa * (A * (((3 * z * Math.pow(ra1, 2)) / Math.pow(R1, 5)) - ((1 - 2 * poisson) / (R1 * (R1 + z)))));// 'ecuacion 3.13
        Er2 = KgAKpa * (A * (((3 * z * Math.pow(ra2, 2)) / Math.pow(R2, 5)) - ((1 - 2 * poisson) / (R2 * (R2 + z)))));
        Er3 = KgAKpa * (A * (((3 * z * Math.pow(ra3, 2)) / Math.pow(R3, 5)) - ((1 - 2 * poisson) / (R3 * (R3 + z)))));
        Er4 = KgAKpa * (A * (((3 * z * Math.pow(ra4, 2)) / Math.pow(R4, 5)) - ((1 - 2 * poisson) / (R4 * (R4 + z)))));
        Er5 = KgAKpa * (A * (((3 * z * Math.pow(ra5, 2)) / Math.pow(R5, 5)) - ((1 - 2 * poisson) / (R5 * (R5 + z)))));
        Er6 = KgAKpa * (A * (((3 * z * Math.pow(ra6, 2)) / Math.pow(R6, 5)) - ((1 - 2 * poisson) / (R6 * (R6 + z)))));

        switch (tipoeje) {
            case "Sencillo":
                ErZ = Er1;
                break;
            case "Sencillo Dual":
                ErZ = Er1 + Er2;
                break;
            case "Tandem":
                ErZ = Er1 + Er2 + Er3 + Er4;
                break;
            case "Tridem":
                ErZ = Er1 + Er2 + Er3 + Er4 + Er5 + Er6;
                break;
            case "Medio Tridem":
                ErZ = Er1 + Er3 + Er5;
                break;
            default:
                ErZ = 0;
        }

        this.cal.getCapaCalculo(1).setEsfuerzoRadialO(ErZ);//Sheets("calculos").Cells(4, 11) = ErZ

        this.llantas.getLlanta(0).setOrNeumatico2(Er1);//Sheets("calculos").Cells(41, 4) = Er1  'ojo con esta parte no desechar aun
        this.llantas.getLlanta(1).setOrNeumatico2(Er2);//Sheets("calculos").Cells(42, 4) = Er2
        this.llantas.getLlanta(2).setOrNeumatico2(Er3);//Sheets("calculos").Cells(43, 4) = Er3
        this.llantas.getLlanta(3).setOrNeumatico2(Er4);//Sheets("calculos").Cells(44, 4) = Er4
        this.llantas.getLlanta(4).setOrNeumatico2(Er5);//Sheets("calculos").Cells(45, 4) = Er5
        this.llantas.getLlanta(5).setOrNeumatico2(Er6);//Sheets("calculos").Cells(46, 4) = Er6
    }

    /*Lista*/
    private void E_TANGENCIAL_PTODOS_MA() {

        double A;// As Double
        double ra1, ra2, ra3, ra4, ra5, ra6;// As Double
        double R1, R2, R3, R4, R5, R6;// As Double
        double Et1, Et2, Et3, Et4, Et5, Et6;// As Double
        double z, P;// As Double
        double KgAKpa;//a As Double
        double PESO_EJE;// As Double

        double PesoNeum;// As Double
        String tipoeje = this.tipoEje;// As String
        double EtZ;// As Double

        KgAKpa = 98.0665;

        z = this.cal.getCapaCalculo(1).getEspesorParcialEquivalente();// Sheets("calculos").Cells(4, 7)
        PesoNeum = this.cal.getCapaCalculo(0).getPesoNeumatico(); //Sheets("calculos").Cells(3, 4)
        P = 1000 * PesoNeum;
        A = (1 - 2 * poisson) * (P / (2 * PI));

        R1 = this.llantas.getLlanta(0).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(18, 6)
        R2 = this.llantas.getLlanta(1).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(19, 6)
        R3 = this.llantas.getLlanta(2).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(20, 6)
        R4 = this.llantas.getLlanta(3).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(21, 6)
        R5 = this.llantas.getLlanta(4).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(22, 6)
        R6 = this.llantas.getLlanta(5).getDistanciaRadialSubrasante();//Sheets("calculos").Cells(23, 6)

        Et1 = KgAKpa * (A * ((1 / (R1 * (R1 + z))) - (z / Math.pow(R1, 3))));// 'ecuacion 3.14
        Et2 = KgAKpa * (A * ((1 / (R2 * (R2 + z))) - (z / Math.pow(R2, 3))));//
        Et3 = KgAKpa * (A * ((1 / (R3 * (R3 + z))) - (z / Math.pow(R3, 3))));
        Et4 = KgAKpa * (A * ((1 / (R4 * (R4 + z))) - (z / Math.pow(R4, 3))));
        Et5 = KgAKpa * (A * ((1 / (R5 * (R5 + z))) - (z / Math.pow(R5, 3))));
        Et6 = KgAKpa * (A * ((1 / (R6 * (R6 + z))) - (z / Math.pow(R6, 3))));

        switch (tipoeje) {
            case "Sencillo":
                EtZ = Et1;
                break;
            case "Sencillo Dual":
                EtZ = Et1 + Et2;
                break;
            case "Tandem":
                EtZ = Et1 + Et2 + Et3 + Et4;
                break;
            case "Tridem":
                EtZ = Et1 + Et2 + Et3 + Et4 + Et5 + Et6;
                break;
            case "Medio Tridem":
                EtZ = Et1 + Et3 + Et5;
                break;
            default:
                EtZ = 0;

        }
        this.cal.getCapaCalculo(1).setEsfuerzoTangencialO(EtZ);//Sheets("calculos").Cells(4, 12) = EtZ

        this.llantas.getLlanta(0).setOtNeumatico2(Et1);//Sheets("calculos").Cells(41, 6) = Et1 //' ojo con estas anotaciones aun no se desechan
        this.llantas.getLlanta(1).setOtNeumatico2(Et2);//Sheets("calculos").Cells(42, 6) = Et2
        this.llantas.getLlanta(2).setOtNeumatico2(Et3);//Sheets("calculos").Cells(43, 6) = Et3
        this.llantas.getLlanta(3).setOtNeumatico2(Et4);//Sheets("calculos").Cells(44, 6) = Et4
        this.llantas.getLlanta(4).setOtNeumatico2(Et5);//Sheets("calculos").Cells(45, 6) = Et5
        this.llantas.getLlanta(5).setOtNeumatico2(Et6);//Sheets("calculos").Cells(46, 6) = Et6
    }

    public void distribuyemelos() {

        double modulocapa[] = new double[numCapas];
        double coefcapa[] = new double[numCapas];
        int combinaciones;
        int contador = 0, a;

        //Sheets("Análisis Probabilista").Cells(3, 12) = "Ejecutando análisis..."
        //num_capas = Sheets("Análisis Espectral").Cells(7, 6)
        //n = num_capas
        combinaciones = (int) Math.pow(2, numCapas);

        for (int capa = 0; capa < numCapas; capa++) {//capa = num_capas To 1 Step - 1
            modulocapa[capa] = this.ep[capa].getModulo();
            coefcapa[capa] = this.ep[capa].getCoeficienteVariacion();
            contador = 1;
            a = (int) Math.pow(2, (numCapas - capa));
            for (int i = 0; i < combinaciones; i++) {

                if (contador <= a) {// Then
                    this.cc.tablaRepeticiones[capa].setE(i, modulocapa[i] * (1 + coefcapa[capa] / 100));//Sheets("larguillo").Cells(3 + i, 81 + capa) = modulocapa[i] * (1 + coefcapa(capa) / 100)
                } else {
                    this.cc.tablaRepeticiones[capa].setE(i, modulocapa[i] * (1 - coefcapa[capa] / 100));//("larguillo").Cells(3 + i, 81 + capa) = modulocapa(capa) * (1 - coefcapa(capa) / 100)
                }
                contador++;
                if (contador > 2 * a) {
                    contador = 1;
                }

            }

        }
    }

    public void ejecutaCorridas() {

//num_capas = Sheets("Análisis Espectral").Cells(7, 6)
        int combinaciones = (int) Math.pow(2, numCapas);

        double sumavf = 0;
        double sumavd = 0;
        double sumavf2 = 0;
        double sumavd2 = 0;
        double z = 0;
//'''''Call pintacontornobarraazul                                                                'para que funcione con la hoja protegida quité esta linea

        for (int analisis = 0; analisis < combinaciones; analisis++) {

            for (int i = 0; i < numCapas; i++) {
                this.cc.tablaRepeticiones[i].setE(i, this.ep[i].getModuloEntreMil());
            }

//Sheets("Análisis Espectral").Cells(13, 6) = Sheets("larguillo").Cells(3 + analisis, 82)
//Sheets("Análisis Espectral").Cells(14, 6) = Sheets("larguillo").Cells(3 + analisis, 83)
//Sheets("Análisis Espectral").Cells(15, 6) = Sheets("larguillo").Cells(3 + analisis, 84)
//Sheets("Análisis Espectral").Cells(16, 6) = Sheets("larguillo").Cells(3 + analisis, 85)
//Sheets("Análisis Espectral").Cells(17, 6) = Sheets("larguillo").Cells(3 + analisis, 86)
//Sheets("Análisis Espectral").Cells(18, 6) = Sheets("larguillo").Cells(3 + analisis, 87)
            boolean soloParaAvanzar[] = new boolean[3];
            for (int i = 0; i < 3; i++) {
                soloParaAvanzar[i] = true;
            }
            this.IniciarAnalisisEspectral(this.tipoEje, soloParaAvanzar);

            double vidaf = this.cc.vi[0].getDefTodos();//Sheets("larguillo").Cells(4, 74)  'fatiga
            double vidad = this.cc.vi[0].getDefTodos();// Sheets("larguillo").Cells(4, 69)  'deformacion

            this.cc.tablaRepeticiones[analisis].setVf(vidaf);// = vidaf
            this.cc.tablaRepeticiones[analisis].setVf2(Math.pow(vidaf, 2));//Sheets("larguillo").Cells(3 + analisis, 89) = vidaf ^ 2
            this.cc.tablaRepeticiones[analisis].setVd(vidad);//Sheets("larguillo").Cells(3 + analisis, 90) = vidad
            this.cc.tablaRepeticiones[analisis].setVd(Math.pow(vidad, 2));//Sheets("larguillo").Cells(3 + analisis, 91) = vidad ^ 2

            sumavf = sumavf + vidaf;
            sumavf2 = sumavf2 + Math.pow(vidaf, 2);
            sumavd = sumavd + vidad;
            sumavd2 = sumavd2 + Math.pow(vidad, 2);

//int avan = 64 / combinaciones;
//'pone la fuente de la celda de avance color blanco
//'''''With Sheets("Análisis Probabilista").Cells(3, 13).Font                     'IDem
//'''''    .ThemeColor = xlThemeColorDark1                                        'idem
//'''''    .TintAndShade = 0                                                      'idem
//'''''End With                                                                   'idem
            //  Sheets("Análisis Probabilista").Cells(3, 13) = analisis / (combinaciones + 1)
            //  Sheets("Análisis Probabilista").Cells(3, 12) = "Ejecutando análisis..."
//'pinta de azul el avance
//'''''For Y = 14 + ((analisis - 1) * avan) To 14 + (analisis * avan)
//'''''With Sheets("Análisis probabilista").Cells(3, Y).Interior
//'''''        .Pattern = xlSolid
//'''''        .PatternColorIndex = xlAutomatic
//'''''        .Color = 16360849
//'''''        .TintAndShade = 0
//'''''        .PatternTintAndShade = 0
//'''''End With
//'''''Next Y
        }

        double Evf = sumavf / combinaciones;
        double Ev2f = sumavf2 / combinaciones;
        double Evd = sumavd / combinaciones;
        double Ev2d = sumavd2 / combinaciones;

        double Vvf = Ev2f - Math.pow(Evf, 2);
        double Vvd = Ev2d - Math.pow(Evd, 2);

        double PConfiabilidad = 0;
        var ValorConfiabilidad = this.getPorcentajeConfiabilidad();//Worksheets("Análisis Probabilista").ComboBoxConfiabilidad.Text

        switch (ValorConfiabilidad) {

            case "55%":
                PConfiabilidad = 55;
                z = -0.125661;
                break;
            case "60%":
                PConfiabilidad = 60;
                z = -0.253347;
                break;
            case "65%":
                PConfiabilidad = 65;
                z = -0.38532;
                break;
            case "70%":
                PConfiabilidad = 70;
                z = -0.524401;
                break;
            case "75%":
                PConfiabilidad = 75;
                z = -0.67449;
                break;
            case "80%":
                PConfiabilidad = 80;
                z = -0.841621;
                break;
            case "85%":
                PConfiabilidad = 85;
                z = -1.036433;
                break;
            case "90%":
                PConfiabilidad = 90;
                z = -1.281552;
                break;
            case "95%":
                PConfiabilidad = 95;
                z = -1.644854;
                break;
        }

        double Confiabilidad = PConfiabilidad / 100;

        double X = 1 - Confiabilidad;

        //'z = Application.WorksheetFunction.NormSInv(X)
        double Rf = Evf + z * Math.pow(Vvf, 0.5);
        double Rd = Evd + z * Math.pow(Vvd, 0.5);

        
        if (Rf > TransitoEstatico.añosProyecto) {
           this.vidaFatiga=0; // caso 0 ("Análisis Probabilista").Cells(15, 12) = " > " & CDbl(Sheets("Tránsito").años1.Text)
        } else if (Rf < 1) {
            this.vidaFatiga = 0.001;//caso 0.001("Análisis Probabilista").Cells(15, 12) = " < 1"
        } else {
            this.vidaFatiga = Rf;
        }

        
        if (Rd> TransitoEstatico.añosProyecto) {
           this.vidaDeformacion = 0; //caso 0("Análisis Probabilista").Cells(16, 12) = " > " & CDbl(Sheets("Tránsito").años1.Text)
        } else if (Rf < 1) {
            this.vidaDeformacion = 0.001;
            //("Análisis Probabilista").Cells(16, 12) = " < 1"
        } else {
            this.vidaDeformacion = Rd;
        }

    }

}

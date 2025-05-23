/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Clases.CargarClases;
import Clases.TablaDistribucionDeCarga;

public class CAnalisis {

    private TablaDistribucionDeCarga tdc;
    private CargarClases cc;

    public CAnalisis(CargarClases cc) {
        this.cc = cc;
    }

    public CargarClases getClases() {
        return this.cc;
    }

    public void IniciarAnalisisEspectral(String tipoCarga) {

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
        llenarValoresTablaxd();
    }

    private void llenarValoresTablaxd() {
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
                switch (j) {
                    case 1 -> {
                        cc.data[i].setSimple((float) altura);
                    }
                    case 2 -> {
                        cc.data[i].setDual((float) altura);
                    }
                    case 3 -> {
                        cc.data[i].setTridem((float) altura);
                    }
                    case 4 -> {
                        cc.data[i].setTandem((float) altura);
                    }
                }
            }

        }
        for (int i = 0; i < 100; i++) {
            System.out.println(cc.data[i].toString());
        }

    }
}

package Objetos;

import Objetos.Datos;
import Datos.TablaDistribucionDeCarga;
import javax.swing.JOptionPane;

public class Funciones extends Datos{
   
    private TablaDistribucionDeCarga tdc;
    
    public Funciones(){
        
    }
    
    
    public void IniciarAnalisisEspectral(String tipoCarga) {


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

    }

    private void calculoDiferencial(int tipoeje, float pesoEje) {

        

    }

    public void coordenadas() {

    }

    public void Factores_Aglvin() {
       

        
    }

    private double interpola(double a, double b, double c, double i, double k) {
        return (((k - i) * (b - a)) / (c - a)) + i;
    }

    private void deflexionCircular(double pesoEje, String tipoEje) {

       
    }

    private void esfuerzosEjeSencillo() {
       
    }

    public void Esfuerzos_Damy(String tipoeje, int numCapa) {

    }

}

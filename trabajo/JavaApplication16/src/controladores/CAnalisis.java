package controladores;

import Clases.CargarClases;
import Datos.TablaDistribucionDeCarga;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CAnalisis {

    private TablaDistribucionDeCarga tdc;
    private CargarClases cc;
    private final double PI = 3.14159265358979;
    private double poisson, presion;
    private boolean seleccion[];
    private int numCapas;
    private DefaultTableModel dtm;

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

    /**
     * GetPresion Retorna el valor por presion guardado en la clase.
     *
     * @return
     */
    public double getPresion() {
        return presion;
    }

    /**
     * set Presion Asigna el valor de presion.
     *
     * @param presion double
     */
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

    public void IniciarAnalisisEspectral(String tipoCarga, boolean[] seleccion, DefaultTableModel dtm) {

        this.seleccion = seleccion;
        this.numCapas = dtm.getRowCount();
        this.dtm = dtm;
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

        //se inicia el objeto capaCalculo
        var capaCalculo1 = new Objetos.CapaCalculo[dtm.getRowCount()];//donde se iran guardando los calculos creo jaja ta raro porque es de gmmmmm

        for (int i = 0; i < dtm.getRowCount(); i++) {
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
            espesor[j] = Double.parseDouble(dtm.getValueAt(j, 2) + "");
            modElastico[j] = Double.parseDouble(dtm.getValueAt(j, 3) + "") * 1000;
            fCorrecion[j] = numLlantas == 1 ? 0.9 : (j == 0 ? 1 : 0.8);// ni idea el porque pero bueno esto es asi
        }

        for (int j = 0; j < numCapas - 1; j++) {
            he[j] = fCorrecion[j] * (espesor[j] + he[(j - 1==0?j-1:0)]) * Math.pow((modElastico[j] / modElastico[j + 1]), (1 / 3));
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

    public void coordenadas(Objetos.CapaCalculo cac) {
        double r11, r21, r31, r41;
        double rLlanta1, rLlanta2, rLlanta3, rLlanta4, rLlanta5, rLlanta6;
        double rLlantaCapa1, rLlantaCapa2, rLlantaCapa3, rLlantaCapa4, rLlantaCapa5, rLlantaCapa6;
        double rLlantaCapa1Natur, rLlantaCapa2Natur, rLlantaCapa3Natur, rLlantaCapa4Natur, rLlantaCapa5Natur, rLlantaCapa6Natur;
        double anguloLlanta1, anguloLlanta2, anguloLlanta3, anguloLlanta4, anguloLlanta5, anguloLlanta6;
        double fEVerticalra11, fEVerticalra12, fEVerticalra13, fEVerticalra14;
        double interfaz[];

        interfaz = new double[numCapas];

        
    }

}

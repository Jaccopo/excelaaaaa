/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import modelo.Archivos;

public class CargarClases {

    public String columnasLarguilloNormal[] = {"Dato", "Carga Promedio", "Simple", "Dual", "Tandem", "Tridem"};//parte simple
    public LarguilloNormal data[] = new LarguilloNormal[100];

    public String columnasRespuestasEje[] = {"delta z capa1", "delta z terraceria", "epsilo z terraceria", "epsilon t capa1", "deflexion Vertical"};//

    public Respustaseje simpleRespuesta[] = new Respustaseje[100];
    public Respustaseje dualRespuesta[] = new Respustaseje[100];
    public Respustaseje tandemRespuesta[] = new Respustaseje[100];
    public Respustaseje tridemRespuesta[] = new Respustaseje[100];

    public String[] columnasRespuestasEje() {
        String nombres[] = "Simple,Dual,Tandem,Tridem".split(",");
        String tipos[] = "Deformacion ND, Fatiga NF".split(",");
        String cadenafinal = "";
        for (int i = 0; i < nombres.length; i++) {
            for (int j = 0; j < tipos.length; j++) {
                cadenafinal += nombres[i] + tipos[j];
                if (i + 1 <= nombres.length) {
                    cadenafinal += ",";
                }
            }

        }
        return cadenafinal.split(",");
    }
    public NumRepeEje nre[] = new NumRepeEje[100];

    public String[] colEspectroRepeticionesEsperadas = "Dimple,Dual,Tandem,Tridem".split(",");
    public EspecRepEspe ere[] = new EspecRepEspe[100];

    public EspectroDano ed[] = new EspectroDano[100];

    public String[] colsEspectroTotal = "Deformacion Todos,Fatiga Todos".split(",");
    public EspectroTotal et[] = new EspectroTotal[100];

    public String[] colDanoAcomulado = "Simple,Dual,Tandem,Tridem,Simple,Dual,Tandem,Tridem".split(",");
    public EspectroDanoAcomulado eda[] = new EspectroDanoAcomulado[100];

    public VidaUtil vi[] = new VidaUtil[100];
    public TablaRepeticiones tablaRepeticiones[] = new TablaRepeticiones[64];

    public boolean cargado = false;

    public CargarClases() {

        for (int j = 0; j < 100; j++) {
            data[j] = new LarguilloNormal((Archivos.getArchivo("archivos/larguillo.tbl"))[j].split("\t"));
            simpleRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-simple.tbl"))[j].split("\t"));
            dualRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-dual.tbl"))[j].split("\t"));
            tandemRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguilllo-Respuesta-eje-TANDEM.tbl"))[j].split("\t"));
            tridemRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-TRIDEM.tbl"))[j].split("\t"));
            nre[j] = new NumRepeEje((Archivos.getArchivo("archivos/larguillo-numero-repeticiones-eje.tbl"))[j].split("\t"));
            ere[j] = new EspecRepEspe((Archivos.getArchivo("archivos/larguillo-numero-repeticiones-eje.tbl"))[j].split("\t"));
            ed[j] = new EspectroDano((Archivos.getArchivo("archivos/larguillo-espectro-daño.tbl"))[j].split("\t"));
            et[j] = new EspectroTotal((Archivos.getArchivo("archivos/larguillo-espectro-daño-total.tbl"))[j].split("\t"));
            eda[j] = new EspectroDanoAcomulado((Archivos.getArchivo("archivos/larguillo-espectro-daño-acomulado.tbl"))[j].split("\t"));
        }

        for (int i = 0; i < 3; i++) {
            vi[i] = new VidaUtil((Archivos.getArchivo("archivos/larguillo-vida-util.tbl"))[i].split("\t"));
        }
        for (int i = 0; i < 10; i++) {
            tablaRepeticiones[i] = new TablaRepeticiones();
        }
        cargado = true;
    }

    public void limpiaProbablista() {
        for (int i = 0; i < 3; i++) {
            vi[i] = new VidaUtil();
        }
        for (int i = 0; i < 10; i++) {
            tablaRepeticiones[i] = new TablaRepeticiones();
        }
    }

}

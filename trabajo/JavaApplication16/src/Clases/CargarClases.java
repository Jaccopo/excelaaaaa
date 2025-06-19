/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import modelo.Archivos;

public class CargarClases {
    public  LarguilloNormal data[] = new LarguilloNormal[100];
    public  Respustaseje simpleRespuesta[] = new Respustaseje[100];
    public  Respustaseje dualRespuesta[] = new Respustaseje[100];
    public  Respustaseje tandemRespuesta[] = new Respustaseje[100];
    public  Respustaseje tridemRespuesta[] = new Respustaseje[100];
    public  NumRepeEje nre[] = new NumRepeEje[100];
    public  EspecRepEspe ere[] = new EspecRepEspe[100];
    public  EspectroDano ed[] = new EspectroDano[100];
    public  EspectroTotal et[] = new EspectroTotal[100];
    public  EspectroDanoAcomulado eda[] = new EspectroDanoAcomulado[100];
    public  VidaUtil vi[] = new VidaUtil[100];
    public  TablaRepeticiones tablaRepeticiones[] = new TablaRepeticiones[64];
    
    public boolean cargado= false;
    
    public CargarClases(){
    
     for (int j = 0; j < 100; j++) {
            data[j] = new LarguilloNormal((Archivos.getArchivo("archivos/larguillo.tbl"))[j].split("\t"));
            simpleRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-simple.tbl"))[j].split("\t"));
            dualRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-dual.tbl"))[j].split("\t"));
            tandemRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguilllo-Respuesta-eje-TANDEM.tbl"))[j].split("\t"));
            tridemRespuesta[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-TRIDEM.tbl"))[j].split("\t"));
            nre[j] = new NumRepeEje((Archivos.getArchivo("archivos/larguillo-numero-repeticiones-eje.tbl"))[j].split("\t"));
            ere[j] =new EspecRepEspe((Archivos.getArchivo("archivos/larguillo-numero-repeticiones-eje.tbl"))[j].split("\t"));
            ed[j] = new EspectroDano((Archivos.getArchivo("archivos/larguillo-espectro-daño.tbl"))[j].split("\t"));        
            et[j] = new EspectroTotal((Archivos.getArchivo("archivos/larguillo-espectro-daño-total.tbl"))[j].split("\t"));
            eda[j] =new EspectroDanoAcomulado((Archivos.getArchivo("archivos/larguillo-espectro-daño-acomulado.tbl"))[j].split("\t"));
            //System.out.println(data[j].toString());
            //System.out.println(j);
        }
        for (int i = 0; i < 3; i++) {
            vi[i] = new VidaUtil((Archivos.getArchivo("archivos/larguillo-vida-util.tbl"))[i].split("\t"));
        }
        for (int i = 0; i < 10; i++) {
            tablaRepeticiones[i] = new TablaRepeticiones();
        }
     cargado = true;
    }
}

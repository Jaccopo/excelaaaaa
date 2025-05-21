/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import modelo.Archivos;

/**
 *
 * @author aldoj
 */
public class CargarClases {
    public static LarguilloNormal data[] = new LarguilloNormal[100];
    public static Respustaseje simple[] = new Respustaseje[100];
    public static Respustaseje dual[] = new Respustaseje[100];
    public static Respustaseje tandem[] = new Respustaseje[100];
    public static Respustaseje tridem[] = new Respustaseje[100];
    public static NumRepeEje nre[] = new NumRepeEje[100];
    public static EspecRepEspe ere[] = new EspecRepEspe[100];
    public static EspectroDano ed[] = new EspectroDano[100];
    public static EspectroTotal et[] = new EspectroTotal[100];
    public static EspectroDanoAcomulado eda[] = new EspectroDanoAcomulado[100];
    public static VidaUtil vi[] = new VidaUtil[100];
    
    public static boolean cargado= false;
    
    public CargarClases(){
    
     for (int j = 0; j < 100; j++) {
            data[j] = new LarguilloNormal((Archivos.getArchivo("archivos/larguillo.uwu"))[j].split("\t"));
            simple[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-simple.uwu"))[j].split("\t"));
            dual[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-dual.uwu"))[j].split("\t"));
            tandem[j] = new Respustaseje((Archivos.getArchivo("archivos/larguilllo-Respuesta-eje-TANDEM.uwu"))[j].split("\t"));
            tridem[j] = new Respustaseje((Archivos.getArchivo("archivos/larguillo-Respuesta-eje-TRIDEM.uwu"))[j].split("\t"));
            nre[j] = new NumRepeEje((Archivos.getArchivo("archivos/larguillo-numero-repeticiones-eje.uwu"))[j].split("\t"));
            ere[j] =new EspecRepEspe((Archivos.getArchivo("archivos/larguillo-numero-repeticiones-eje.uwu"))[j].split("\t"));
            ed[j] = new EspectroDano((Archivos.getArchivo("archivos/larguillo-espectro-daño.uwu"))[j].split("\t"));        
            et[j] = new EspectroTotal((Archivos.getArchivo("archivos/larguillo-espectro-daño-total.uwu"))[j].split("\t"));
            eda[j] =new EspectroDanoAcomulado((Archivos.getArchivo("archivos/larguillo-espectro-daño-acomulado.uwu"))[j].split("\t"));
            
            System.out.println(j);
        }
        for (int i = 0; i < 3; i++) {
            vi[i] = new VidaUtil((Archivos.getArchivo("archivos/larguillo-vida-util.uwu"))[i].split("\t"));
        }
     cargado = true;
    }
}

package javaapplication16;

import Clases.LarguilloNormal;
import modelo.Archivos;
import vista.VentanaPrincipal;

public class JavaApplication16 {

    public static LarguilloNormal data[] = new LarguilloNormal[100];

    public static void main(String[] args) {
        //VentanaPrincipal.main(args);
        //Archivos.IniciarArchivo();

        for (int j = 0; j < 100; j++) {
            data[j] = new LarguilloNormal((Archivos.getArchivo("archivos/larguillo.uwu"))[j].split("\t"));
            System.out.println(data[j].toString());
        }
    }

}

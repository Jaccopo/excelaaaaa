package javaapplication16;

import Clases.*;
import javax.swing.JOptionPane;
import modelo.Archivos;
import vista.VentanaPrincipal;

public class JavaApplication16 implements Runnable{


    
    public static void main(String[] args) {
       
        JavaApplication16 jc = new JavaApplication16();
        
       // Thread tr = new Thread(jc);
        //tr.start();
        //CargarClases cc = new CargarClases();
        //VentanaPrincipal.main();
        TablaDistribucionDeCarga tddc = new TablaDistribucionDeCarga();
        tddc.asignarValores(1);

    }

    @Override
    public void run() {
        
        

    }

}

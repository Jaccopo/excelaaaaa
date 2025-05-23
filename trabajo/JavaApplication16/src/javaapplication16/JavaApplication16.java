package javaapplication16;

import Clases.*;
import javax.swing.JOptionPane;
import modelo.Archivos;
import vista.VentanaPrincipal;

public class JavaApplication16 {


    
    public static void main(String[] args) {
       
        var vp = new VentanaPrincipal();
        vp.setCc(new CargarClases());
        vp.setVisible(true);


    }

   

}

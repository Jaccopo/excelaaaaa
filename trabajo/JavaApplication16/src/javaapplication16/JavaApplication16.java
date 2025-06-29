package javaapplication16;
import Clases.CargarClases;
import DatosTablas.FactorERadial;
import DatosTablas.FactorETangencial;
import DatosTablas.FactorEVertical;
import vista.VentanaFinal;
import vista.VistaFormal;

public class JavaApplication16 {
    public static void main(String[] args) {
        FactorERadial.iniciarFactorRadial();
        FactorETangencial.iniciarFactor();
        FactorEVertical.iniciarFactor();
        
        VistaFormal vf = new VistaFormal(new CargarClases());
        vf.setVisible(true);
    }
}

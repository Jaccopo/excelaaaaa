package javaapplication16;
import DatosTablas.FactorERadial;
import DatosTablas.FactorETangencial;
import DatosTablas.FactorEVertical;
import vista.VentanaFinal;

public class JavaApplication16 {
    public static void main(String[] args) {
        FactorERadial.iniciarFactorRadial();
        FactorETangencial.iniciarFactor();
        FactorEVertical.iniciarFactor();
        VentanaFinal.main(args);
    }
}

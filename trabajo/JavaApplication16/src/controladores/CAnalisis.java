package controladores;

import Objetos.Funciones;
import Clases.CargarClases;
import javax.swing.table.DefaultTableModel;

public class CAnalisis {

    private Funciones funciones;
    
    public CAnalisis(CargarClases cc){
        funciones = new Funciones();
        funciones.setCc(cc);
    }
    
    public void CargarTabla(DefaultTableModel dtm){
        for (int i = 0; i < dtm.getRowCount(); i++) {
            funciones.getEp().getEp(i).setNumeroCapa(Integer.parseInt(dtm.getValueAt(i, 1)+""));
            funciones.getEp().getEp(i).setNombre(dtm.getValueAt(i, 2)+"");
            funciones.getEp().getEp(i).setEspesor(Double.parseDouble(dtm.getValueAt(i, 3)+""));
            funciones.getEp().getEp(i).setModulo(Double.parseDouble(dtm.getValueAt(i, 4)+"")*1000);
            funciones.getEp().getEp(i).setCoeficienteVariacion(Double.parseDouble(dtm.getValueAt(i, 5)+"")*1000);
        }
    }

    public CargarClases getClases() {
        return funciones.getCc();
    }

    public double getPoisson() {
        return funciones.getPoisson();
    }

    public void setPoisson(double valor) {
        funciones.setPoisson(valor);
   }

    public void setPresion(double valor) {
        funciones.setPoisson(valor);
    }

    public double getPresion() {
        return funciones.getPresion();
    }

    public void IniciarAnalisisEspectral(String tipoEje, boolean[] activos) {
        funciones.IniciarAnalisisEspectral(tipoEje);
    }
    
   
}

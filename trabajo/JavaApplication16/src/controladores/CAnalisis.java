package controladores;

import Objetos.Funciones;
import Clases.CargarClases;
import DatosTablas.TablaDistribucionDeCarga;
import javax.swing.table.DefaultTableModel;

public class CAnalisis {

    private Funciones funciones;
    private CTransito ct;
    private String vifat,videf;

    public String getVifat() {
        return vifat;
    }

    public String getFidef() {
        return videf;
    }
    
    public CAnalisis(CargarClases cc){
        funciones = new Funciones();
        funciones.setCc(cc);
    }
    
    public void setCTransito(CTransito ct){
        funciones.setCTransito(ct);
    }
    public void setTablaDistribucionDeCarga(TablaDistribucionDeCarga tddc){
        funciones.setTablaDistribucionDeCarga(tddc);
    }
    
    public void CargarTabla(DefaultTableModel dtm){

        for (int i = 0; i < dtm.getRowCount(); i++) {
            funciones.getEp(i).setNumeroCapa(Integer.parseInt(dtm.getValueAt(i, 0)+""));
            funciones.getEp(i).setNombre(dtm.getValueAt(i, 1)+"");
            funciones.getEp(i).setEspesor(i==dtm.getRowCount()-1?0:Double.parseDouble(dtm.getValueAt(i, 2)+""));
            funciones.getEp(i).setModulo(Double.parseDouble(dtm.getValueAt(i, 3)+"")*1000);
            funciones.getEp(i).setCoeficienteVariacion(Double.parseDouble(dtm.getValueAt(i, 4)+"")*1000);
        }
        funciones.setNumCapas(dtm.getRowCount());
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
        funciones.setPresion(valor);
    }

    public double getPresion() {
        return funciones.getPresion();
    }

    public void IniciarAnalisisEspectral(String tipoEje) {
        funciones.IniciarAnalisisEspectral(tipoEje);
        vifat = funciones.vifatiga;
        videf = funciones.videformacion;
    }
    public void IniciarAnalisisProbabilistico(String tipoEje,String confiabilidad) {
        funciones.iniciaAnalisisprobabilista(confiabilidad, tipoEje);
        vifat = funciones.vifatiga;
        videf = funciones.videformacion;
    }
    
   
}

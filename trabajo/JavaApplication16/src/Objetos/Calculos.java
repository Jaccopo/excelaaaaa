package Objetos;

public class Calculos {
    private CapaCalculo cc[];
    
    public Calculos (){
        cc = new CapaCalculo[2];
        for (int i = 0; i < 2; i++) {
            cc[i] = new CapaCalculo();
        }
    }
    
    public CapaCalculo getCapaCalculo(int index){
        return cc[index];
    }
}

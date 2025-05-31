package Objetos;

public class Calculos {
    private CapaCalculo cc[];
    
    public Calculos (){
        cc = new CapaCalculo[2];
    }
    
    public CapaCalculo getCapaCalculo(int index){
        return cc[index];
    }
}

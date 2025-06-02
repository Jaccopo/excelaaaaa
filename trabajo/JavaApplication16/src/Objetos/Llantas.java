package Objetos;

public class Llantas {
    private Llanta llanta[] = new Llanta[6];
    public Llantas(){
        for (int i = 0; i < 6; i++) {
            llanta[i] = new Llanta();
        }
    }
    public Llanta getLlanta(int numero){
        return llanta[numero];
    }
}

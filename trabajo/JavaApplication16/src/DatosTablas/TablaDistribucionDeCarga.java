/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosTablas;

/**
 *
 * @author aldoj
 */
public final class TablaDistribucionDeCarga {

    private final String dataBaja = "1.000\t0.000\t0.000\t1.200\t0.000\t0.000\t0.350\t0.000\t0.000" + " "
            + "1.000\t0.000\t0.000\t1.504\t0.000\t0.000\t0.500\t0.000\t0.000" + " "
            + "0.300\t0.250\t0.450\t2.000\t2.200\t2.700\t0.100\t0.200\t0.100" + " "
            + "0.600\t0.400\t0.000\t1.946\t2.600\t0.000\t0.270\t0.170\t0.000";

    private final String dataLegal = "1.000\t0.000\t0.000\t1.200\t0.000\t0.000\t0.350\t0.000\t0.000" + " "
            + "1.000\t0.000\t0.000\t1.700\t0.000\t0.000\t0.400\t0.000\t0.000" + " "
            + "0.400\t0.300\t0.300\t2.200\t2.772\t2.800\t0.100\t0.200\t0.100" + " "
            + "0.500\t0.500\t0.000\t1.946\t2.800\t0.000\t0.250\t0.200\t0.000";

    private final String dataSobreCarga = "1.000\t0.000\t0.000\t1.200\t0.000\t0.000\t0.350\t0.000\t0.000" + " "
            + "1.000\t0.000\t0.000\t1.800\t0.000\t0.000\t0.360\t0.000\t0.000" + " "
            + "0.400\t0.300\t0.300\t2.346\t2.772\t3.218\t0.100\t0.300\t0.120" + " "
            + "0.500\t0.500\t0.000\t2.100\t3.050\t0.000\t0.270\t0.270\t0.000";

    private final String dataAltaSobreCarga = "1.000\t0.000\t0.000\t1.200\t0.000\t0.000\t0.350\t0.000\t0.000" + " "
            + "1.000\t0.000\t0.000\t2.120\t0.000\t0.000\t0.320\t0.000\t0.000" + " "
            + "0.400\t0.200\t0.400\t2.700\t3.000\t3.400\t0.100\t0.300\t0.120" + " "
            + "0.500\t0.500\t0.000\t2.300\t3.300\t0.000\t0.280\t0.280\t0.000";

    private final String dataPersolanizado = "0.18\t0.50\t0.32\t1.7851\t1.7739\t1.6700\t0.0469\t0.0000\t0.1668" + " "
            + "0.5\t0.29\t0.21\t2.000\t2.2942\t2.4042\t0.000\t0.1220\t0.0321" + " "
            + "0.12\t0.31\t0.57\t4.7158\t2.9961\t3.0990\t1.6072\t0.0817\t0.0265" + " "
            + "0.36\t0.02\t0.62\t3.412\t3.5382\t3.4904\t0.0680\t0.0007\t0.0165";

    private float w1[];
    private float w2[];
    private float w3[];
    private float m1[];
    private float m2[];
    private float m3[];
    private float s1[];
    private float s2[];
    private float s3[];

    public TablaDistribucionDeCarga() {
        w1 = new float[4];
        w2 = new float[4];
        w3 = new float[4];
        m1 = new float[4];
        m2 = new float[4];
        m3 = new float[4];
        s1 = new float[4];
        s2 = new float[4];
        s3 = new float[4];
    }
    
     public TablaDistribucionDeCarga(int tipo) {
        w1 = new float[4];
        w2 = new float[4];
        w3 = new float[4];
        m1 = new float[4];
        m2 = new float[4];
        m3 = new float[4];
        s1 = new float[4];
        s2 = new float[4];
        s3 = new float[4];
        asignarValores(tipo);
    }

    public void asignarValores(int tipo) {
        float valor[][] = new float[4][9];
        String filas[] = null;

        switch (tipo) {
            case 1 :
                filas = dataBaja.split(" ");
                break;
        case 2:
                filas = dataLegal.split(" ");
        break;
            case 3 :
                filas = dataSobreCarga.split(" ");
           break;
            case 4 :
                filas = dataAltaSobreCarga.split(" ");
         break;
            case 5 :
                filas = dataPersolanizado.split(" ");
           break;
        };

        for (int i = 0; i < 4; i++) {
            String[] celda = filas[i].split("\t");
            for (int j = 0; j < 9; j++) {
                valor[i][j] = Float.parseFloat(celda[j]);
            }
            w1[i] = valor[i][0];//0
            w2[i] = valor[i][1];//1
            w3[i] = valor[i][2]; //2
            m1[i] = valor[i][3]; //3
            m2[i] = valor[i][4]; //4
            m3[i] = valor[i][5]; //5
            s1[i] = valor[i][6]; //6
            s2[i] = valor[i][7]; //7
            s3[i] = valor[i][8]; //8
        }
    }

    public float[] getW1() {
        return w1;
    }

    public void setW1(float[] w1) {
        this.w1 = w1;
    }

    public float[] getW2() {
        return w2;
    }

    public void setW2(float[] w2) {
        this.w2 = w2;
    }

    public float[] getW3() {
        return w3;
    }

    public void setW3(float[] w3) {
        this.w3 = w3;
    }

    public float[] getM1() {
        return m1;
    }

    public void setM1(float[] m1) {
        this.m1 = m1;
    }

    public float[] getM2() {
        return m2;
    }

    public void setM2(float[] m2) {
        this.m2 = m2;
    }

    public float[] getM3() {
        return m3;
    }

    public void setM3(float[] m3) {
        this.m3 = m3;
    }

    public float[] getS1() {
        return s1;
    }

    public void setS1(float[] s1) {
        this.s1 = s1;
    }

    public float[] getS2() {
        return s2;
    }

    public void setS2(float[] s2) {
        this.s2 = s2;
    }

    public float[] getS3() {
        return s3;
    }

    public void setS3(float[] s3) {
        this.s3 = s3;
    }
    
    

}

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

    private final String dataBaja = "1.000	0.000	0.000	1.200	0.000	0.000	0.350	0.000	0.000" + " "
            + "1.000	0.000	0.000	1.504	0.000	0.000	0.500	0.000	0.000" + " "
            + "0.300	0.250	0.450	2.000	2.200	2.700	0.100	0.200	0.100" + " "
            + "0.600	0.400	0.000	1.946	2.600	0.000	0.270	0.170	0.000";

    private final String dataLegal = "1.000	0.000	0.000	1.200	0.000	0.000	0.350	0.000	0.000" + " "
            + "1.000	0.000	0.000	1.700	0.000	0.000	0.400	0.000	0.000" + " "
            + "0.400	0.300	0.300	2.200	2.772	2.800	0.100	0.200	0.100" + " "
            + "0.500	0.500	0.000	1.946	2.800	0.000	0.250	0.200	0.000";

    private final String dataSobreCarga = "1.000	0.000	0.000	1.200	0.000	0.000	0.350	0.000	0.000" + " "
            + "1.000	0.000	0.000	1.800	0.000	0.000	0.360	0.000	0.000" + " "
            + "0.400	0.300	0.300	2.346	2.772	3.218	0.100	0.300	0.120" + " "
            + "0.500	0.500	0.000	2.100	3.050	0.000	0.270	0.270	0.000";

    private final String dataAltaSobreCarga = "1.000	0.000	0.000	1.200	0.000	0.000	0.350	0.000	0.000" + " "
            + "1.000	0.000	0.000	2.120	0.000	0.000	0.320	0.000	0.000" + " "
            + "0.400	0.200	0.400	2.700	3.000	3.400	0.100	0.300	0.120" + " "
            + "0.500	0.500	0.000	2.300	3.300	0.000	0.280	0.280	0.000";

    private final String dataPersolanizado = "0.18	0.50	0.32	1.7851	1.7739	1.6700	0.0469	0.0000	0.1668" + " "
            + "0.5	0.29	0.21	2.000	2.2942	2.4042	0.000	0.1220	0.0321" + " "
            + "0.12	0.31	0.57	4.7158	2.9961	3.0990	1.6072	0.0817	0.0265" + " "
            + "0.36	0.02	0.62	3.412	3.5382	3.4904	0.0680	0.0007	0.0165";

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

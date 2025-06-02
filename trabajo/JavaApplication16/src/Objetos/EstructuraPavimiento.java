/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author aldoj
 */
public class EstructuraPavimiento {

    private int numeroCapa;
    private String nombre;
    private double espesor;
    private double modulo; //kPascales
    private double coeficienteVariacion;
    public EstructuraPavimiento ep[];
    
    
    
    public EstructuraPavimiento() {
        ep = new EstructuraPavimiento[4];
    }

    public double getCoeficienteVariacion() {
        return coeficienteVariacion;
    }

    public void setCoeficienteVariacion(double coeficienteVariacion) {
        this.coeficienteVariacion = coeficienteVariacion;
    }

    public EstructuraPavimiento getEp(int index) {
        return ep[index];
    }

    
    

    public int getNumeroCapa() {
        return numeroCapa;
    }

    public void setNumeroCapa(int numeroCapa) {
        this.numeroCapa = numeroCapa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEspesor() {
        return espesor;
    }

    public void setEspesor(double espesor) {
        this.espesor = espesor;
    }

    public double getModulo() {
        return modulo;
    }

    public void setModulo(double modulo) {
        this.modulo = modulo*1000;
    }

}

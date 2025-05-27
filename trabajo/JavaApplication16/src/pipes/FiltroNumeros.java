/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pipes;

public class FiltroNumeros {

    public static boolean esNumero(String caracteres, int tipo) {
        if (caracteres.length() < 1) {
            return false;
        }
        try {
            switch (tipo) {
                case (1) -> {
                    Integer.parseInt(caracteres);
                }
                case (2) -> {
                    Double.parseDouble(caracteres);
                }
                case (3) -> {
                    Float.parseFloat(caracteres);
                }
            }

            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}

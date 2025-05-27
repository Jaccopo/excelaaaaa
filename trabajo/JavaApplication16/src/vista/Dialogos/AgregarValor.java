package vista.Dialogos;

import java.awt.Frame;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AgregarValor extends JDialog {

    private final boolean SI = true;
    private final boolean NO = false;
    private double valor = 0;
    protected boolean estado = NO;

    private JLabel texto;
    private JButton aceptar, cancelar;
    private JTextField numero;

    public AgregarValor(Frame r, boolean tipo, String nombre) {
        super(r, tipo);
        construirVista(nombre);

        aceptar.addActionListener((var e) -> {
            if (pipes.FiltroNumeros.esNumero(numero.getText(), 2)) {
                this.valor = Double.parseDouble(numero.getText());
                this.estado = SI;
                this.setVisible(NO);
            } else {
                JOptionPane.showMessageDialog(null, "Debe ser un numero.", "Error", JOptionPane.WARNING_MESSAGE);
            }

        });
        cancelar.addActionListener((var e) -> {
            this.estado = NO;
            this.setVisible(NO);
        });

    }

    private void construirVista(String nombre) {
        texto = new JLabel("Valor de " + nombre + " actual:");
        aceptar = new JButton("Cambiar");
        cancelar = new JButton("Cancelar");
        numero = new JTextField("0");

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        GroupLayout gl = new GroupLayout(getContentPane());
        getContentPane().setLayout(gl);
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(texto)
                .addComponent(numero)
                .addGroup(gl.createParallelGroup()
                        .addComponent(aceptar)
                        .addComponent(cancelar)
                )
        );
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(texto)
                .addComponent(numero)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(aceptar)
                        .addComponent(cancelar)
                )
        );
        pack();

    }

    public void setValor(double valor) {
        numero.setText(valor + "");
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public boolean isEstado() {
        return estado;
    }

}

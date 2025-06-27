package vista.Dialogos;

import Clases.LarguilloNormal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MostrarGrafica extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox simpleCheckBox;
    private JCheckBox tridemCheckBox;
    private JCheckBox tandemCheckBox;
    private JCheckBox dualCheckBox;
    private JPanel grafica;

    private LarguilloNormal larguilloNormal[];

    public void setLarguilloNormal(LarguilloNormal[] larguilloNormal) {
        this.larguilloNormal = larguilloNormal;
    }

    public MostrarGrafica(Object parent, boolean modal) {

        setContentPane(contentPane);
        this.setPreferredSize(new Dimension(600, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        

        grafica.setLayout(new GridLayout(1, 1));



        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}

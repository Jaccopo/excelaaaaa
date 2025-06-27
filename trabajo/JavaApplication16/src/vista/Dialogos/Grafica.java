package vista.Dialogos;

import Clases.LarguilloNormal;
import java.awt.BasicStroke;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica extends javax.swing.JDialog implements ItemListener {

    private LarguilloNormal ln[];
    ChartPanel chartPanel;

    public Grafica(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.simple.addItemListener(this);
        dual.addItemListener(this);
        tandem.addItemListener(this);
        tridem.addItemListener(this);
        limite.addItemListener(this);

    }

    public void setLargilloNormal(LarguilloNormal[] ln) {
        this.ln = ln;
        construirGragica();
    }

    private void construirGragica() {
        XYSeries s, d, ta, tr, xysimplelegal, xyduallegal, xytandemlegal, xytridemlegal;
        XYSeriesCollection xy = new XYSeriesCollection();
        float datos[] = new float[100];

        if (limite.isSelected()) {
            xysimplelegal = new XYSeries("Legal Sencillo");
            for (int i = 0; i < 26; i++) {
                xysimplelegal.add(7, i);
            }

            xyduallegal = new XYSeries("Legal dual");
            for (int i = 0; i < 26; i++) {
                xyduallegal.add(11, i);
            }

            xytandemlegal = new XYSeries("Legal tandem");
            for (int i = 0; i < 26; i++) {
                xytandemlegal.add(18, i);
            }

            xytridemlegal = new XYSeries("Legal tridem");
            for (int i = 0; i < 26; i++) {
                xytridemlegal.add(23, i);
            }

            xy.addSeries(xysimplelegal);
            xy.addSeries(xyduallegal);
            xy.addSeries(xytandemlegal);
            xy.addSeries(xytridemlegal);
        }
        if (simple.isSelected()) {

            for (int i = 0; i < 100; i++) {
                datos[i] = this.ln[i].getSimple();
            }
            s = setXYSeries("Simple", datos);
            xy.addSeries(s);
        }
        if (dual.isSelected()) {

            for (int i = 0; i < 100; i++) {
                datos[i] = this.ln[i].getDual();
            }
            d = setXYSeries("dual", datos);
            xy.addSeries(d);
        }
        if (tandem.isSelected()) {

            for (int i = 0; i < 100; i++) {
                datos[i] = this.ln[i].getTandem();
            }
            ta = setXYSeries("Tandem", datos);
            xy.addSeries(ta);
        }
        if (tridem.isSelected()) {

            for (int i = 0; i < 100; i++) {
                datos[i] = this.ln[i].getTridem();
            }
            tr = setXYSeries("Tridem", datos);
            xy.addSeries(tr);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                          "funcion",
                          "Carga Tonelada",
                          "Porcentaje",
                          xy);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();

        float grosorLinea = 3.0f;
        for (int i = 0; i < xy.getSeriesCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(grosorLinea));
        }

        if (chartPanel == null) {
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(panelGrafica.getSize());
            
        } else {
            chartPanel.setChart(chart);
            
        }
        panelGrafica.removeAll();
            panelGrafica.setLayout(new java.awt.BorderLayout());
            panelGrafica.add(chartPanel, java.awt.BorderLayout.CENTER);
            panelGrafica.revalidate();
            panelGrafica.repaint();

    }

    private XYSeries setXYSeries(String nombre, float datos[]) {
        XYSeries dataset = new XYSeries(nombre);
        for (int i = 0; i < 100; i++) {
            dataset.add(this.ln[i].getCargaPromedio(), datos[i]);
        }
        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelGrafica = new javax.swing.JPanel();
        simple = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        dual = new javax.swing.JCheckBox();
        tandem = new javax.swing.JCheckBox();
        tridem = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        limite = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelGrafica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        simple.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        simple.setSelected(true);
        simple.setText("Simple");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Seleccionalas categorias");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        dual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dual.setSelected(true);
        dual.setText("Dual");

        tandem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tandem.setSelected(true);
        tandem.setText("Tandem");

        tridem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tridem.setSelected(true);
        tridem.setText("Tridem");

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        limite.setText("Limite Legal carga");
        limite.setName("limite"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(simple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tandem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tridem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(limite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simple)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tandem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tridem)
                .addGap(16, 16, 16)
                .addComponent(limite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox dual;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox limite;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JCheckBox simple;
    private javax.swing.JCheckBox tandem;
    private javax.swing.JCheckBox tridem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox tipo = (JCheckBox) e.getSource();
        try {
            if (tipo.isSelected() && tipo.getName().equals("limite")) {
                simple.setSelected(true);
                dual.setSelected(true);
                tandem.setSelected(true);
                tridem.setSelected(true);
                construirGragica();
            } else if (tipo.getName().equals("limite")) {
                simple.setSelected(true);
                dual.setSelected(true);
                tandem.setSelected(true);
                tridem.setSelected(true);
                construirGragica();
            } else {
                if (!simple.isSelected() && !dual.isSelected() && !tandem.isSelected() && !tridem.isSelected()) {

                    JOptionPane.showMessageDialog(rootPane, "Uno debe estar seleccionado");
                    simple.setSelected(true);
                    construirGragica();

                } else if (simple.isSelected() || dual.isSelected() || tandem.isSelected() || tridem.isSelected()) {
                    construirGragica();
                }
            }
        } catch (NullPointerException ex) {
            if (!simple.isSelected() && !dual.isSelected() && !tandem.isSelected() && !tridem.isSelected()) {

                JOptionPane.showMessageDialog(rootPane, "Uno debe estar seleccionado");
                simple.setSelected(true);
                construirGragica();

            } else if (simple.isSelected() || dual.isSelected() || tandem.isSelected() || tridem.isSelected()) {
                construirGragica();
            }
        }

    }
}

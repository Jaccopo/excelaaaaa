package vista;

import Clases.CargarClases;
import Clases.TransitoEstatico;
import DatosTablas.FactorERadial;
import DatosTablas.FactorETangencial;
import DatosTablas.FactorEVertical;
import DatosTablas.TablaDistribucionDeCarga;
import controladores.CAnalisis;
import controladores.CTransito;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import vista.Dialogos.AgregarCapa;
import vista.Dialogos.EspectrosPersonalizados;
import vista.Dialogos.Grafica;
import vista.Dialogos.TransitoDialogo;
import vista.Dialogos.VerLarguillo;
import vista.Dialogos.VerTablas;

public class VistaFormal extends javax.swing.JFrame implements MouseListener, Runnable {

    //manejo
    private Color colorAnterior;
    private Color colorArriba;
    private Thread mostrarProgreso;
    //controladores
    private CTransito ct;
    private CAnalisis ca;

    private DefaultTableModel dtm;
    private CargarClases cc;
    private VerLarguillo vl;

    private TransitoDialogo td;
    private TablaDistribucionDeCarga tddc;

    public VistaFormal(CargarClases cc) {

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VistaFormal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(VistaFormal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(VistaFormal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(VistaFormal.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

        initComponents();

        this.cc = cc;
        this.ca = new CAnalisis(cc);

        tddc = new TablaDistribucionDeCarga();
        dtm = new DefaultTableModel(){
        @Override
            public boolean isCellEditable(int row, int column) {
                // Todas las celdas no editables
                return false;
            }
        
        };

        dtm = (DefaultTableModel) tabla.getModel();
        tabla.setModel(dtm);
        colorAnterior = this.jPanel1.getBackground();
        colorArriba = this.espectral.getBackground();

        espectral.addMouseListener(this);
        probabilistico.addMouseListener(this);
        graficos.addMouseListener(this);
        tablas.addMouseListener(this);

        mostrarProgreso = new Thread(this);
        cargarTransito.setBackground(Color.red);
        configView();

        editarCapa.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla primero");
            } else {
                String construye = "";
                for (int i = 1; i < dtm.getColumnCount(); i++) {
                    if (i + 1 != dtm.getColumnCount()) {
                        construye += dtm.getValueAt(fila, i) + ",";
                    } else {
                        construye += dtm.getValueAt(fila, i);
                    }
                }
                AgregarCapa ac = new AgregarCapa(this, true, 1, construye.split(","),fila+1==dtm.getRowCount());
                ac.setVisible(true);
                if (ac.getEstado()) {
                    String objeto[] = ((dtm.getRowCount() + 1) + ","
                                      + ac.getNombreCapa() + ","
                                      + (ac.getEspesorCapa()==0? "0":ac.getEspesorCapa())+ ","
                                      + ac.getModulo() + ","
                                      + ac.getCoeficiente()).split(",");
                    for (int i = 0; i < objeto.length; i++) {
                        dtm.setValueAt(objeto[i], fila, i);
                    }
                }

            }

        });

        agregarCapa.addActionListener(e -> {
            AgregarCapa ac = new AgregarCapa(this, true, 0, null,false);
            ac.setVisible(true);
            if (ac.getEstado()) {
                String objeto[] = ((dtm.getRowCount() + 1) + ","
                                  + ac.getNombreCapa() + ","
                                  + " " + ","
                                  + ac.getModulo() + ","
                                  + ac.getCoeficiente()).split(",");
                dtm.addRow(objeto);
                dtm.setValueAt(ac.getEspesorCapa(), dtm.getRowCount() - 2, 2);
            }
        });
        eliminarCapa.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(rootPane, "Desea eliminar la capa?", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                dtm.removeRow(dtm.getRowCount() - 1);
                dtm.setValueAt("", dtm.getRowCount() - 1, 2);
            }
        });

        personalizado.addActionListener(e -> {
            if (tipoCarga.getSelectedIndex() > 0) {
                EspectrosPersonalizados espectrosPersonalizados = new EspectrosPersonalizados(this, true, tddc);
                espectrosPersonalizados.setVisible(true);
                if (espectrosPersonalizados.isCargado()) {
                    tddc = espectrosPersonalizados.getTablaDistribucionDeCarga();
                    JOptionPane.showMessageDialog(this, "Se guardo exitosamente la personalizacion");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione nivel de carga.");
            }
        });
        cargarTransito.addActionListener(e -> {
            if (td == null) {
                td = new TransitoDialogo(this, true);
            } else {
                td.setCTransito(ct);
            }

            td.setVisible(true);
            if (td.isCargado()) {
                this.ct = td.getCTransito();
                JOptionPane.showMessageDialog(this, "Se cargo Transito exitosamente.");
                cargarTransito.setText(" Transito Cargado.");
                cargarTransito.setBackground(Color.green);
            } else {
                JOptionPane.showMessageDialog(this, "Error al cargar transito ");
                cargarTransito.setText(" Cargar Transito.");
                cargarTransito.setBackground(Color.red);
            }
        });

        tipoCarga.addItemListener(e -> {
            if (tipoCarga.getSelectedIndex() > 0) {
                tddc = new TablaDistribucionDeCarga(tipoCarga.getSelectedIndex());
            }

        });

    }

    private void configView() {
        espectral.setBackground(colorAnterior);
        probabilistico.setBackground(colorAnterior);
        graficos.setBackground(colorAnterior);
        tablas.setBackground(colorAnterior);

        espectral.setForeground(Color.black);
        probabilistico.setForeground(Color.black);
        graficos.setForeground(Color.black);
        tablas.setForeground(Color.black);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        graficos = new javax.swing.JLabel();
        espectral = new javax.swing.JLabel();
        probabilistico = new javax.swing.JLabel();
        tablas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        agregarCapa = new javax.swing.JButton();
        eliminarCapa = new javax.swing.JButton();
        confianza = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tipoCarga = new javax.swing.JComboBox<>();
        personalizado = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        vidaDeformacion = new javax.swing.JLabel();
        vidaFatiga = new javax.swing.JLabel();
        vidaDeformacionProbabilistico = new javax.swing.JLabel();
        vidaFatigaProbabilistico = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cargarTransito = new javax.swing.JButton();
        mensaje1 = new javax.swing.JLabel();
        mensaje3 = new javax.swing.JLabel();
        mensaje2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        poisson = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        presion = new javax.swing.JTextField();
        mensaje4 = new javax.swing.JLabel();
        mensaje5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        editarCapa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora de analisis de estructura de pavimento pw.");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(123, 190, 232));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication16/IMT_Logo.png"))); // NOI18N

        graficos.setBackground(new java.awt.Color(106, 168, 239));
        graficos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        graficos.setForeground(new java.awt.Color(255, 255, 255));
        graficos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        graficos.setText("Graficos");
        graficos.setOpaque(true);

        espectral.setBackground(new java.awt.Color(106, 168, 239));
        espectral.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        espectral.setForeground(new java.awt.Color(255, 255, 255));
        espectral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        espectral.setText("Analisis Espectral");
        espectral.setOpaque(true);

        probabilistico.setBackground(new java.awt.Color(106, 168, 239));
        probabilistico.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        probabilistico.setForeground(new java.awt.Color(255, 255, 255));
        probabilistico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probabilistico.setText("Analisis Probabilistico");
        probabilistico.setOpaque(true);

        tablas.setBackground(new java.awt.Color(106, 168, 239));
        tablas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tablas.setForeground(new java.awt.Color(255, 255, 255));
        tablas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tablas.setText("Ver tablas");
        tablas.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(espectral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(probabilistico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(graficos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espectral, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(probabilistico, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(graficos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "carpeta", "10", "3500", "20"},
                {"2", "base", "40", "400", "20"},
                {"3", "subbase", "", "100", "20"}
            },
            new String [] {
                "Numero de capas", "Nombre capa", "Espesor (cm)", "Modulo (MPa)", "Coe de var %"
            }
        ));
        tabla.setToolTipText("");
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jScrollPane2.setViewportView(tabla);

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Estructura del pavimento");
        jLabel1.setOpaque(true);

        agregarCapa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregarCapa.setText("Agregar Capa");

        eliminarCapa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        eliminarCapa.setText("Eliminar Capa");

        confianza.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        confianza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "60%", "65%", "70%", "75%", "80%", "85%", "90%", "95%" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nivel de confianza:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nivel de carga:");

        tipoCarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tipoCarga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Una opcion", "Legal", "Ligera Sobre Carga", "Alta Sobre Carga", "Muy Alta Sobre Carga", "Personalizado" }));

        personalizado.setText("Personalizar");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Vida por deformacion:");
        jLabel5.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(204, 204, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Vida por Fatiga:");
        jLabel6.setOpaque(true);

        vidaDeformacion.setBackground(new java.awt.Color(255, 255, 204));
        vidaDeformacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        vidaDeformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vidaDeformacion.setText(">20");
        vidaDeformacion.setOpaque(true);

        vidaFatiga.setBackground(new java.awt.Color(255, 255, 204));
        vidaFatiga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        vidaFatiga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vidaFatiga.setText(">20");
        vidaFatiga.setOpaque(true);

        vidaDeformacionProbabilistico.setBackground(new java.awt.Color(255, 255, 204));
        vidaDeformacionProbabilistico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        vidaDeformacionProbabilistico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vidaDeformacionProbabilistico.setText(">20");
        vidaDeformacionProbabilistico.setOpaque(true);

        vidaFatigaProbabilistico.setBackground(new java.awt.Color(255, 255, 204));
        vidaFatigaProbabilistico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        vidaFatigaProbabilistico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vidaFatigaProbabilistico.setText(">20");
        vidaFatigaProbabilistico.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(204, 204, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Vida por deformacion:");
        jLabel7.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Vida por Fatiga:");
        jLabel8.setOpaque(true);

        jLabel11.setBackground(new java.awt.Color(255, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Espectral");
        jLabel11.setOpaque(true);

        jLabel12.setBackground(new java.awt.Color(255, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Probabilistico");
        jLabel12.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vidaDeformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vidaFatiga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vidaDeformacionProbabilistico, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vidaFatigaProbabilistico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(vidaFatiga))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(vidaDeformacion)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(vidaFatigaProbabilistico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(vidaDeformacionProbabilistico))))
                .addContainerGap())
        );

        cargarTransito.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cargarTransito.setText("Cargar Transito");

        mensaje1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mensaje1.setForeground(new java.awt.Color(255, 102, 102));
        mensaje1.setText("*");

        mensaje3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mensaje3.setForeground(new java.awt.Color(255, 102, 102));
        mensaje3.setText("*");

        mensaje2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mensaje2.setForeground(new java.awt.Color(255, 102, 102));
        mensaje2.setText("*");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Presion Estandar:");

        poisson.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        poisson.setText(".35");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Possion:");

        presion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        presion.setText("90");

        mensaje4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mensaje4.setForeground(new java.awt.Color(255, 102, 102));
        mensaje4.setText("*");

        mensaje5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mensaje5.setForeground(new java.awt.Color(255, 102, 102));
        mensaje5.setText("*");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Todos los elementos marcados con\nastericos deben estar llenados\ncorrectamente.\n\nPara El analisis Espectral no es necesario\nel nivel de confianza, pero para\nel analisis probabilistico si.");
        jScrollPane1.setViewportView(jTextArea1);

        editarCapa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editarCapa.setText("Editar Capa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(personalizado))
                                    .addComponent(confianza, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(poisson, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mensaje4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(presion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cargarTransito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mensaje3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                    .addComponent(mensaje5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(agregarCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarCapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eliminarCapa)
                        .addComponent(agregarCapa))
                    .addComponent(editarCapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensaje1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(confianza)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensaje2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cargarTransito)
                            .addComponent(mensaje3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(poisson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(presion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensaje4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mensaje5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarCapa;
    private javax.swing.JButton cargarTransito;
    private javax.swing.JComboBox<String> confianza;
    private javax.swing.JButton editarCapa;
    private javax.swing.JButton eliminarCapa;
    private javax.swing.JLabel espectral;
    private javax.swing.JLabel graficos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel mensaje1;
    private javax.swing.JLabel mensaje2;
    private javax.swing.JLabel mensaje3;
    private javax.swing.JLabel mensaje4;
    private javax.swing.JLabel mensaje5;
    private javax.swing.JButton personalizado;
    private javax.swing.JTextField poisson;
    private javax.swing.JTextField presion;
    private javax.swing.JLabel probabilistico;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel tablas;
    private javax.swing.JComboBox<String> tipoCarga;
    private javax.swing.JLabel vidaDeformacion;
    private javax.swing.JLabel vidaDeformacionProbabilistico;
    private javax.swing.JLabel vidaFatiga;
    private javax.swing.JLabel vidaFatigaProbabilistico;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel entro = (JLabel) e.getSource();
        if (entro.getText().equals(espectral.getText())) {
            iniciarAnalisis(1);
        }
        if (entro.getText().equals(probabilistico.getText())) {
            iniciarAnalisis(2);
        }
        if (entro.getText().equals(graficos.getText())) {
            mostrarGraficos();
        }
        if (entro.getText().equals(tablas.getText())) {
            mostrarTablas();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel entro = (JLabel) e.getSource();
        entro.setForeground(Color.red);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel entro = (JLabel) e.getSource();
        entro.setForeground(Color.white);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel entro = (JLabel) e.getSource();
        entro.setBackground(colorArriba);
        entro.setForeground(Color.WHITE);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel entro = (JLabel) e.getSource();
        entro.setBackground(this.colorAnterior);
        entro.setForeground(Color.black);
    }

    private void iniciarAnalisis(int i) {
        if (tipoCarga.getSelectedIndex() > 0 && td != null) {
            try {
                ca.setPoisson(Double.parseDouble(poisson.getText()));
                ca.setPresion(Double.parseDouble(presion.getText()));
                ca.CargarTabla(dtm);
                ca.setCTransito(ct);
                ca.setTablaDistribucionDeCarga(tddc);
                if (i == 1) {

                    ca.IniciarAnalisisEspectral(tipoCarga.getModel().getSelectedItem() + "");
                    JOptionPane.showMessageDialog(this, "Calculos finalizados");
                    cc = ca.getClases();
                    this.vidaDeformacion.setText(ca.getFidef());
                    this.vidaFatiga.setText(ca.getVifat());

                } else {
                    if (confianza.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(this, "Selecciona un nivel de confianza valido");
                    } else {
                        ca.setPoisson(Double.parseDouble(poisson.getText()));
                        ca.setPresion(Double.parseDouble(presion.getText()));
                        ca.CargarTabla(dtm);
                        ca.setCTransito(ct);
                        ca.setTablaDistribucionDeCarga(tddc);

                        ca.IniciarAnalisisProbabilistico(tipoCarga.getModel().getSelectedItem() + "", confianza.getSelectedItem() + "");

                        JOptionPane.showMessageDialog(this, "Calculos finalizados");
                        cc = ca.getClases();
                        this.vidaDeformacionProbabilistico.setText(ca.getFidefpro());
                        this.vidaFatigaProbabilistico.setText(ca.getVifatpro());

                        this.vidaDeformacion.setText(ca.getFidef());
                        this.vidaFatiga.setText(ca.getVifat());

                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Revisa que poisson y presion si sean numeros.");
            }

        } else {

            JOptionPane.showMessageDialog(this, "Faltan elementos por seleccionar");
        }

    }

    private void mostrarGraficos() {
        Grafica gr = new Grafica(this, false);
        gr.setLargilloNormal(this.cc.data);
        gr.setVisible(true);
    }

    private void mostrarTablas() {
        VerTablas vt = new VerTablas(this, false, this.cc);
        vt.setVisible(rootPaneCheckingEnabled);

    }

    @Override
    public void run() {
    }
}

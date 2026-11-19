// JFrame Form generado por el diseñador de NetBeans: toda la GUI vive en initComponents().
package com.gremio.vista;

import com.gremio.controlador.CombateControlador;
import com.gremio.controlador.GremioControlador;
import com.gremio.modelo.Aventurero;
import com.gremio.modelo.Monstruo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * Ventana principal del simulador. La vista únicamente presenta componentes y
 * delega toda la lógica a los controladores; no hay paintComponent ni dibujo
 * manual. El archivo VentanaPrincipal.form acompaña a esta clase.
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static final Color FONDO = new Color(42, 26, 15);
    private static final Color TEXTO = new Color(240, 192, 96);
    private static final Color PANEL = new Color(34, 20, 9);
    private static final Color ORO = new Color(212, 168, 74);
    private static final Color VERDE = new Color(39, 174, 96);
    private static final Color AMARILLO = new Color(243, 156, 18);
    private static final Color ROJO = new Color(192, 57, 43);
    private static final Font TITULO = new Font("Serif", Font.BOLD, 16);

    private final GremioControlador gremioControlador = new GremioControlador();
    private CombateControlador combateControlador;
    private final Random dadoManual = new Random();
    private String claseSeleccionada = "Guerrero";
    private String monstruoSeleccionado = "Goblin";
    private String ultimaTirada = "—";

    /** Crea la ventana y aplica el estado inicial de los componentes. */
    public VentanaPrincipal() {
        initComponents();
        inicializarVista();
    }

    /** Puntos de vida disponibles en el formulario (opciones predeterminadas). */
    private static String[] puntosVida() {
        String[] valores = new String[10];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = String.valueOf((i + 1) * 10);
        }
        return valores;
    }

    /** Carga un icono vectorial del classpath y lo escala al tamaño pedido. */
    private static javax.swing.ImageIcon icono(String nombre, int size) {
        java.net.URL url = VentanaPrincipal.class.getResource("/com/gremio/recursos/" + nombre + ".png");
        if (url == null) {
            return new javax.swing.ImageIcon(new java.awt.image.BufferedImage(
                    1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB));
        }
        java.awt.Image imagen = new javax.swing.ImageIcon(url).getImage()
                .getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        return new javax.swing.ImageIcon(imagen);
    }

    /** Traduce el emoji del modelo al recurso gráfico de la clase. */
    private static javax.swing.Icon iconoClase(String emoji, int size) {
        switch (emoji) {
            case "⚔️": return icono("swords", size);
            case "🔮": return icono("crystal", size);
            case "✨": return icono("sparkle", size);
            case "🥋": return icono("gi", size);
            case "🛡️": return icono("shield", size);
            case "🏹": return icono("bow", size);
            case "🗡️": return icono("dagger", size);
            case "🎵": return icono("music", size);
            default: return icono("wizard", size);
        }
    }

    /** Traduce el emoji del monstruo al recurso gráfico correspondiente. */
    private static javax.swing.Icon iconoMonstruo(String emoji, int size) {
        switch (emoji) {
            case "👺": return icono("goblin", size);
            case "👹": return icono("ogro", size);
            case "🐉": return icono("dragon", size);
            default: return icono("swords", size);
        }
    }

    /** Convierte los iconos de texto de la bitácora en etiquetas legibles. */
    private static String sinEmojis(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("\uFE0F", "")
                .replace("🎯 ", "[ATAQUE] ")
                .replace("🎲 ", "[DADO] ")
                .replace("💥 ", "")
                .replace(" ⭐ ", " ")
                .replace("✗ ", "[FALLO] ")
                .replace("🏆 ", "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelInicio = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInicio = new javax.swing.JTextArea();
        btnComenzar = new javax.swing.JButton();
        panelCrear = new javax.swing.JPanel();
        lblTituloCrear = new javax.swing.JLabel();
        lblClase = new javax.swing.JLabel();
        cmbClase = new javax.swing.JComboBox();
        lblDadoClase = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNivel = new javax.swing.JLabel();
        spNivel = new javax.swing.JSpinner();
        lblPv = new javax.swing.JLabel();
        cmbPv = new javax.swing.JComboBox();
        lblAtributo = new javax.swing.JLabel();
        cmbAtributo = new javax.swing.JComboBox();
        btnCrear = new javax.swing.JButton();
        panelGremio = new javax.swing.JPanel();
        lblContador = new javax.swing.JLabel();
        btnIrCrear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelTarjetas = new javax.swing.JPanel();
        panelCombate = new javax.swing.JPanel();
        lblTituloCombate = new javax.swing.JLabel();
        btnGoblin = new javax.swing.JButton();
        btnOgro = new javax.swing.JButton();
        btnDragon = new javax.swing.JButton();
        lblMonsterHp = new javax.swing.JLabel();
        pbMonster = new javax.swing.JProgressBar();
        lblEstado = new javax.swing.JLabel();
        btnD4 = new javax.swing.JButton();
        btnD6 = new javax.swing.JButton();
        lblUltimaTirada = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextArea();
        btnIniciar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panelResumen = new javax.swing.JPanel();
        lblTituloResumen = new javax.swing.JLabel();
        btnAtajoCrear = new javax.swing.JButton();
        btnAtajoCombate = new javax.swing.JButton();
        btnReporteClase = new javax.swing.JButton();
        btnReporteNivel = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblReporte = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtActividad = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        lblLineas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gremio de aventureros");
        setBackground(new java.awt.Color(42, 26, 15));
        setForeground(new java.awt.Color(240, 192, 96));

        jTabbedPane1.setBackground(new java.awt.Color(26, 15, 8));
        jTabbedPane1.setForeground(new java.awt.Color(240, 192, 96));
        jTabbedPane1.setFont(new java.awt.Font("Serif", 1, 13)); // NOI18N

        panelInicio.setBackground(new java.awt.Color(42, 26, 15));

        lblTitulo.setFont(new java.awt.Font("Serif", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 192, 96));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_crest_32.png"))); // NOI18N
        lblTitulo.setText("Gremio de aventureros");

        lblSubtitulo.setFont(new java.awt.Font("Serif", 2, 16)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(196, 168, 120));
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Calabozos y Dragones");

        txtInicio.setEditable(false);
        txtInicio.setColumns(20);
        txtInicio.setLineWrap(true);
        txtInicio.setRows(8);
        txtInicio.setText("Bienvenido, maestro del gremio.");
        txtInicio.setWrapStyleWord(true);
        txtInicio.setBackground(new java.awt.Color(15, 8, 5));
        txtInicio.setForeground(new java.awt.Color(232, 213, 168));
        txtInicio.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(txtInicio);

        btnComenzar.setBackground(new java.awt.Color(212, 168, 74));
        btnComenzar.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        btnComenzar.setForeground(new java.awt.Color(26, 15, 8));
        btnComenzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_rocket_20.png"))); // NOI18N
        btnComenzar.setText("Comenzar");
        btnComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComenzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(btnComenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblTitulo)
                .addGap(6, 6, 6)
                .addComponent(lblSubtitulo)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(btnComenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jTabbedPane1.addTab("Inicio", panelInicio);

        panelCrear.setBackground(new java.awt.Color(42, 26, 15));

        lblTituloCrear.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        lblTituloCrear.setForeground(new java.awt.Color(240, 192, 96));
        lblTituloCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_wizard_22.png"))); // NOI18N
        lblTituloCrear.setText("Crear Aventurero :elige clase y completa los datos");

        lblClase.setForeground(new java.awt.Color(212, 168, 74));
        lblClase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_wizard_18.png"))); // NOI18N
        lblClase.setText("Clase:");

        cmbClase.setBackground(new java.awt.Color(34, 20, 9));
        cmbClase.setForeground(new java.awt.Color(240, 192, 96));
        cmbClase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Guerrero", "Mago", "Clérigo", "Monje", "Paladín", "Explorador", "Pícaro", "Bardo" }));
        cmbClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClaseActionPerformed(evt);
            }
        });

        lblDadoClase.setFont(new java.awt.Font("Serif", 2, 12)); // NOI18N
        lblDadoClase.setForeground(new java.awt.Color(196, 168, 120));
        lblDadoClase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_dado_18.png"))); // NOI18N
        lblDadoClase.setText("Daño d6 (dado de 6 caras) · Stat FUE (Fuerza)");

        lblNombre.setForeground(new java.awt.Color(212, 168, 74));
        lblNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_user_18.png"))); // NOI18N
        lblNombre.setText("Nombre:");

        txtNombre.setBackground(new java.awt.Color(34, 20, 9));
        txtNombre.setColumns(20);
        txtNombre.setForeground(new java.awt.Color(232, 213, 168));

        lblNivel.setForeground(new java.awt.Color(212, 168, 74));
        lblNivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_star_18.png"))); // NOI18N
        lblNivel.setText("Nivel (1-20):");

        spNivel.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));

        lblPv.setForeground(new java.awt.Color(212, 168, 74));
        lblPv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_heart_18.png"))); // NOI18N
        lblPv.setText("Puntos de Vida:");

        cmbPv.setBackground(new java.awt.Color(34, 20, 9));
        cmbPv.setForeground(new java.awt.Color(232, 213, 168));
        cmbPv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));

        lblAtributo.setForeground(new java.awt.Color(212, 168, 74));
        lblAtributo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_gift_18.png"))); // NOI18N
        lblAtributo.setText("Armadura Pesada (sí / no)");

        cmbAtributo.setBackground(new java.awt.Color(34, 20, 9));
        cmbAtributo.setForeground(new java.awt.Color(232, 213, 168));
        cmbAtributo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sí", "No" }));

        btnCrear.setBackground(new java.awt.Color(212, 168, 74));
        btnCrear.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(26, 15, 8));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_wizard_20.png"))); // NOI18N
        btnCrear.setText("Crear Aventurero");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
        panelCrear.setLayout(panelCrearLayout);
        panelCrearLayout.setHorizontalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloCrear)
                    .addComponent(lblClase)
                    .addComponent(cmbClase, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDadoClase)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNivel)
                    .addComponent(spNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPv)
                    .addComponent(cmbPv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAtributo)
                    .addComponent(cmbAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(316, Short.MAX_VALUE))
        );
        panelCrearLayout.setVerticalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTituloCrear)
                .addGap(24, 24, 24)
                .addComponent(lblClase)
                .addGap(4, 4, 4)
                .addComponent(cmbClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblDadoClase)
                .addGap(18, 18, 18)
                .addComponent(lblNombre)
                .addGap(4, 4, 4)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblNivel)
                .addGap(4, 4, 4)
                .addComponent(spNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblPv)
                .addGap(4, 4, 4)
                .addComponent(cmbPv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblAtributo)
                .addGap(4, 4, 4)
                .addComponent(cmbAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Crear", panelCrear);

        panelGremio.setBackground(new java.awt.Color(42, 26, 15));

        lblContador.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        lblContador.setForeground(new java.awt.Color(240, 192, 96));
        lblContador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_people_18.png"))); // NOI18N
        lblContador.setText("0 aventureros");

        btnIrCrear.setBackground(new java.awt.Color(34, 20, 9));
        btnIrCrear.setForeground(new java.awt.Color(240, 192, 96));
        btnIrCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_plus_18.png"))); // NOI18N
        btnIrCrear.setText("Crear otro");
        btnIrCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrCrearActionPerformed(evt);
            }
        });

        panelTarjetas.setBackground(new java.awt.Color(42, 26, 15));
        panelTarjetas.setLayout(new javax.swing.BoxLayout(panelTarjetas, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(panelTarjetas);

        javax.swing.GroupLayout panelGremioLayout = new javax.swing.GroupLayout(panelGremio);
        panelGremio.setLayout(panelGremioLayout);
        panelGremioLayout.setHorizontalGroup(
            panelGremioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(panelGremioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblContador)
                .addGap(360, 360, Short.MAX_VALUE)
                .addComponent(btnIrCrear)
                .addGap(20, 20, 20))
        );
        panelGremioLayout.setVerticalGroup(
            panelGremioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGremioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelGremioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContador)
                    .addComponent(btnIrCrear))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Gremio", panelGremio);

        panelCombate.setBackground(new java.awt.Color(42, 26, 15));

        lblTituloCombate.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        lblTituloCombate.setForeground(new java.awt.Color(240, 192, 96));
        lblTituloCombate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_swords_22.png"))); // NOI18N
        lblTituloCombate.setText("Combate: elige monstruo, ataca y tira el dado de daño");

        btnGoblin.setBackground(new java.awt.Color(34, 20, 9));
        btnGoblin.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnGoblin.setForeground(new java.awt.Color(240, 192, 96));
        btnGoblin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_goblin_28.png"))); // NOI18N
        btnGoblin.setText("Goblin 20 PV - Fácil");
        btnGoblin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonstruoActionPerformed(evt);
            }
        });

        btnOgro.setBackground(new java.awt.Color(34, 20, 9));
        btnOgro.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnOgro.setForeground(new java.awt.Color(240, 192, 96));
        btnOgro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_ogro_28.png"))); // NOI18N
        btnOgro.setText("Ogro 50 PV - Medio");
        btnOgro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonstruoActionPerformed(evt);
            }
        });

        btnDragon.setBackground(new java.awt.Color(34, 20, 9));
        btnDragon.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnDragon.setForeground(new java.awt.Color(240, 192, 96));
        btnDragon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_dragon_28.png"))); // NOI18N
        btnDragon.setText("Dragón 100 PV - Difícil");
        btnDragon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonstruoActionPerformed(evt);
            }
        });

        lblMonsterHp.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblMonsterHp.setForeground(new java.awt.Color(196, 168, 120));
        lblMonsterHp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_goblin_20.png"))); // NOI18N
        lblMonsterHp.setText("Goblin — 20/20 PV");

        pbMonster.setForeground(new java.awt.Color(192, 57, 43));
        pbMonster.setMaximum(20);
        pbMonster.setValue(20);
        pbMonster.setStringPainted(true);

        lblEstado.setBackground(new java.awt.Color(34, 20, 9));
        lblEstado.setFont(new java.awt.Font("Serif", 2, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(240, 192, 96));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_swords_20.png"))); // NOI18N
        lblEstado.setText("Elige un monstruo y pulsa «Iniciar combate»");
        lblEstado.setOpaque(true);

        btnD4.setBackground(new java.awt.Color(34, 20, 9));
        btnD4.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        btnD4.setForeground(new java.awt.Color(255, 255, 255));
        btnD4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_d4_36.png"))); // NOI18N
        btnD4.setText("d4");
        btnD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD4ActionPerformed(evt);
            }
        });

        btnD6.setBackground(new java.awt.Color(34, 20, 9));
        btnD6.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        btnD6.setForeground(new java.awt.Color(255, 255, 255));
        btnD6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_d6_36.png"))); // NOI18N
        btnD6.setText("d6");
        btnD6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD6ActionPerformed(evt);
            }
        });

        lblUltimaTirada.setForeground(new java.awt.Color(196, 168, 120));
        lblUltimaTirada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_dado_18.png"))); // NOI18N
        lblUltimaTirada.setText("Última tirada: —");

        txtConsola.setEditable(false);
        txtConsola.setColumns(20);
        txtConsola.setLineWrap(true);
        txtConsola.setRows(8);
        txtConsola.setText("Aquí aparecerá el desarrollo del combate.");
        txtConsola.setWrapStyleWord(true);
        txtConsola.setBackground(new java.awt.Color(10, 5, 5));
        txtConsola.setForeground(new java.awt.Color(201, 209, 217));
        txtConsola.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(txtConsola);

        btnIniciar.setBackground(new java.awt.Color(139, 26, 26));
        btnIniciar.setFont(new java.awt.Font("Serif", 1, 15)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(232, 213, 168));
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_sword_18.png"))); // NOI18N
        btnIniciar.setText("Iniciar combate");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(34, 20, 9));
        btnCancelar.setForeground(new java.awt.Color(240, 192, 96));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_cross_18.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCombateLayout = new javax.swing.GroupLayout(panelCombate);
        panelCombate.setLayout(panelCombateLayout);
        panelCombateLayout.setHorizontalGroup(
            panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(panelCombateLayout.createSequentialGroup()
                .addGroup(panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCombateLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblTituloCombate))
                    .addGroup(panelCombateLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnD4)
                        .addGap(40, 40, 40)
                        .addComponent(btnD6)
                        .addGap(40, 40, 40)
                        .addComponent(lblUltimaTirada))
                    .addGroup(panelCombateLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelCombateLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCombateLayout.createSequentialGroup()
                        .addComponent(btnGoblin, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnOgro, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDragon, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(lblMonsterHp, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(pbMonster, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelCombateLayout.setVerticalGroup(
            panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCombateLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTituloCombate)
                .addGap(16, 16, 16)
                .addGroup(panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoblin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOgro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDragon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(lblMonsterHp)
                .addGap(6, 6, 6)
                .addComponent(pbMonster, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnD4)
                    .addComponent(btnD6)
                    .addComponent(lblUltimaTirada))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(panelCombateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Combate", panelCombate);

        panelResumen.setBackground(new java.awt.Color(42, 26, 15));

        lblTituloResumen.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        lblTituloResumen.setForeground(new java.awt.Color(240, 192, 96));
        lblTituloResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_chart_22.png"))); // NOI18N
        lblTituloResumen.setText("Resumen : reportes, actividad y persistencia");

        btnAtajoCrear.setBackground(new java.awt.Color(212, 168, 74));
        btnAtajoCrear.setForeground(new java.awt.Color(26, 15, 8));
        btnAtajoCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_plus_18.png"))); // NOI18N
        btnAtajoCrear.setText("Crear");
        btnAtajoCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtajoCrearActionPerformed(evt);
            }
        });

        btnAtajoCombate.setBackground(new java.awt.Color(139, 26, 26));
        btnAtajoCombate.setForeground(new java.awt.Color(232, 213, 168));
        btnAtajoCombate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_swords_18.png"))); // NOI18N
        btnAtajoCombate.setText("Combatir");
        btnAtajoCombate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtajoCombateActionPerformed(evt);
            }
        });

        btnReporteClase.setBackground(new java.awt.Color(42, 90, 42));
        btnReporteClase.setForeground(new java.awt.Color(232, 213, 168));
        btnReporteClase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_chart_18.png"))); // NOI18N
        btnReporteClase.setText("Por clase");
        btnReporteClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteClaseActionPerformed(evt);
            }
        });

        btnReporteNivel.setBackground(new java.awt.Color(42, 90, 42));
        btnReporteNivel.setForeground(new java.awt.Color(232, 213, 168));
        btnReporteNivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_search_18.png"))); // NOI18N
        btnReporteNivel.setText("Nivel &gt; 3");
        btnReporteNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteNivelActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(34, 20, 9));
        btnLimpiar.setForeground(new java.awt.Color(240, 192, 96));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_broom_18.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lblReporte.setForeground(new java.awt.Color(196, 168, 120));
        lblReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_chart_18.png"))); // NOI18N
        lblReporte.setText("Reportes: aún no se han generado");

        txtActividad.setEditable(false);
        txtActividad.setColumns(20);
        txtActividad.setLineWrap(true);
        txtActividad.setRows(6);
        txtActividad.setText("Aquí aparecerán las acciones.");
        txtActividad.setWrapStyleWord(true);
        txtActividad.setBackground(new java.awt.Color(10, 5, 5));
        txtActividad.setForeground(new java.awt.Color(201, 209, 217));
        txtActividad.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(txtActividad);

        txtArchivo.setEditable(false);
        txtArchivo.setColumns(20);
        txtArchivo.setLineWrap(true);
        txtArchivo.setRows(6);
        txtArchivo.setText("El archivo está vacío.");
        txtArchivo.setWrapStyleWord(true);
        txtArchivo.setBackground(new java.awt.Color(10, 5, 5));
        txtArchivo.setForeground(new java.awt.Color(139, 125, 90));
        txtArchivo.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jScrollPane5.setViewportView(txtArchivo);

        lblLineas.setForeground(new java.awt.Color(212, 168, 74));
        lblLineas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gremio/recursos/icono_file_18.png"))); // NOI18N
        lblLineas.setText("0 líneas");

        javax.swing.GroupLayout panelResumenLayout = new javax.swing.GroupLayout(panelResumen);
        panelResumen.setLayout(panelResumenLayout);
        panelResumenLayout.setHorizontalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloResumen)
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addComponent(btnAtajoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnAtajoCombate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnReporteClase, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnReporteNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblReporte)
                    .addComponent(lblLineas))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        panelResumenLayout.setVerticalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTituloResumen)
                .addGap(16, 16, 16)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtajoCrear)
                    .addComponent(btnAtajoCombate)
                    .addComponent(btnReporteClase)
                    .addComponent(btnReporteNivel)
                    .addComponent(btnLimpiar))
                .addGap(12, 12, 12)
                .addComponent(lblReporte)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(lblLineas)
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Resumen", panelResumen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inicializarVista() {
        getContentPane().setBackground(FONDO);
        actualizarFormularioClase();
        resaltarMonstruo();
        pintarGremio();
        actualizarCombate();
        actualizarResumen();
    }

    private void btnComenzarActionPerformed(java.awt.event.ActionEvent evt) {
        jTabbedPane1.setSelectedIndex(1);
    }

    private void cmbClaseActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarFormularioClase();
    }

    /** Cambia el combo del atributo según la clase elegida; nunca hay texto libre. */
    private void actualizarFormularioClase() {
        claseSeleccionada = String.valueOf(cmbClase.getSelectedItem());
        lblAtributo.setText(GremioControlador.etiquetaAtributo(claseSeleccionada));
        lblAtributo.setIcon(icono("gift", 18));
        lblDadoClase.setText(GremioControlador.pistaClase(claseSeleccionada));
        lblDadoClase.setIcon(icono("dado", 18));

        String[] opciones;
        switch (claseSeleccionada) {
            case "Guerrero":
            case "Pícaro":
                opciones = new String[]{"Sí", "No"};
                break;
            case "Mago":
                opciones = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
                break;
            case "Monje":
                opciones = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
                break;
            case "Paladín":
                opciones = new String[]{"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
                break;
            case "Clérigo":
                opciones = new String[]{"Pelor", "Moradin", "Lolth", "Tiamat", "Boccob", "Yeenoghu"};
                break;
            case "Explorador":
                opciones = new String[]{"Bosque", "Montaña", "Desierto", "Pantano", "Costa", "Mazmorra"};
                break;
            case "Bardo":
                opciones = new String[]{"Laúd", "Arpa", "Flauta", "Tambor", "Lira", "Bocina"};
                break;
            default:
                opciones = new String[]{"Sí", "No"};
                break;
        }
        cmbAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(opciones));
        cmbAtributo.setSelectedIndex(0);
    }

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nombre = txtNombre.getText().trim();
            int nivel = (Integer) spNivel.getValue();
            int puntosVida = Integer.parseInt(String.valueOf(cmbPv.getSelectedItem()));
            String atributo = String.valueOf(cmbAtributo.getSelectedItem());
            Aventurero aventurero = gremioControlador.crearAventurero(
                    claseSeleccionada, nombre, nivel, puntosVida, atributo);
            gremioControlador.agregarAventurero(aventurero);

            JOptionPane.showMessageDialog(this,
                    "6 puntuaciones generadas con 4d6-drop-lowest:\n\n"
                    + aventurero.getDetalleStats() + "\n" + aventurero.toString(),
                    aventurero.getNombre() + " — " + aventurero.getDescripcionClase(),
                    JOptionPane.INFORMATION_MESSAGE);

            registrarActividad("[NUEVO] " + aventurero.getNombre()
                    + " creado como " + aventurero.getDescripcionClase());
            txtNombre.setText("");
            spNivel.setValue(1);
            cmbPv.setSelectedIndex(0);
            actualizarFormularioClase();
            pintarGremio();
            actualizarResumen();
            jTabbedPane1.setSelectedIndex(2);
        } catch (IllegalArgumentException | IOException e) {
            mostrarError(e);
        }
    }

    private void btnIrCrearActionPerformed(java.awt.event.ActionEvent evt) {
        jTabbedPane1.setSelectedIndex(1);
    }

    /** Pinta las tarjetas dinámicas del gremio (permitido por el examen). */
    private void pintarGremio() {
        panelTarjetas.removeAll();
        List<Aventurero> lista = gremioControlador.listarAventureros();
        lblContador.setText(lista.size() + " aventurero(s)");
        lblContador.setIcon(icono("people", 18));

        if (lista.isEmpty()) {
            JLabel vacio = new JLabel("No hay aventureros todavía. Crea uno en la pestaña «Crear».",
                    SwingConstants.CENTER);
            vacio.setIcon(icono("castle", 20));
            vacio.setForeground(new Color(160, 136, 88));
            vacio.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
            panelTarjetas.add(vacio);
        } else {
            for (Aventurero a : lista) {
                panelTarjetas.add(crearTarjeta(a));
            }
        }
        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    private JPanel crearTarjeta(final Aventurero a) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(PANEL);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 109, 47), 2),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));
        tarjeta.setMaximumSize(new java.awt.Dimension(1000, 190));
        tarjeta.setAlignmentX(LEFT_ALIGNMENT);

        JLabel nombre = new JLabel(a.getNombre() + "  —  " + a.getDescripcionClase());
        nombre.setIcon(iconoClase(a.getIcono(), 20));
        nombre.setFont(new Font("Serif", Font.BOLD, 16));
        nombre.setForeground(TEXTO);

        int porcentaje = (int) Math.round(a.getHpActual() * 100.0 / a.getPvMaximos());
        Color colorHp = porcentaje > 60 ? VERDE : (porcentaje > 30 ? AMARILLO : ROJO);
        JProgressBar barra = new JProgressBar(0, a.getPvMaximos());
        barra.setValue(a.getHpActual());
        barra.setForeground(colorHp);
        barra.setStringPainted(true);
        barra.setString(a.getHpActual() + "/" + a.getPvMaximos() + " PV");
        barra.setMaximumSize(new java.awt.Dimension(600, 18));
        barra.setAlignmentX(LEFT_ALIGNMENT);

        JLabel datos = new JLabel("Nv " + a.getNivel() + "   CA " + a.calcularCA()
                + "   Atq " + (a.getBonoAtaque() >= 0 ? "+" : "") + a.getBonoAtaque()
                + "   Daño d" + a.getDadoDano());
        datos.setForeground(new Color(232, 213, 168));
        JLabel stats = new JLabel(a.getResumenStats());
        stats.setForeground(new Color(168, 152, 112));
        JLabel atributo = new JLabel(a.getDescripcionAtributo());
        atributo.setFont(new Font("Serif", Font.ITALIC, 12));
        atributo.setForeground(new Color(212, 168, 74));

        JPanel acciones = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 2));
        acciones.setBackground(PANEL);
        acciones.setAlignmentX(LEFT_ALIGNMENT);
        acciones.add(botonCard("+1 Nv", "up", PANEL, TEXTO, e -> cambiarNivel(a, 1)));
        acciones.add(botonCard("+5 Nv", "up", PANEL, TEXTO, e -> cambiarNivel(a, 5)));
        acciones.add(botonCard("Daño", "burst", new Color(139, 26, 26), new Color(232, 213, 168), e -> aplicarDanoAventurero(a)));
        acciones.add(botonCard("Curar", "heal", new Color(42, 90, 42), new Color(232, 213, 168), e -> curarAventurero(a)));
        acciones.add(botonCard("Eliminar", "cross", PANEL, new Color(255, 123, 114), e -> eliminarAventurero(a)));

        tarjeta.add(nombre);
        tarjeta.add(javax.swing.Box.createVerticalStrut(6));
        tarjeta.add(barra);
        tarjeta.add(javax.swing.Box.createVerticalStrut(6));
        tarjeta.add(datos);
        tarjeta.add(stats);
        tarjeta.add(atributo);
        tarjeta.add(acciones);
        return tarjeta;
    }

    private JButton botonCard(String texto, String nombreIcono, Color fondo, Color textoColor,
                               java.awt.event.ActionListener listener) {
        JButton boton = new JButton(texto);
        if (nombreIcono != null) {
            boton.setIcon(icono(nombreIcono, 16));
        }
        boton.setFont(new Font("Serif", Font.BOLD, 11));
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFocusPainted(false);
        boton.addActionListener(listener);
        return boton;
    }

    private void cambiarNivel(Aventurero a, int cantidad) {
        try {
            gremioControlador.subirNivel(a, cantidad);
            registrarActividad("[NIVEL] " + a.getNombre() + " sube a Nv." + a.getNivel());
            pintarGremio();
            actualizarResumen();
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void aplicarDanoAventurero(Aventurero a) {
        String entrada = JOptionPane.showInputDialog(this,
                "¿Cuánto daño recibe " + a.getNombre() + "? (PV: "
                + a.getHpActual() + "/" + a.getPvMaximos() + ")", "10");
        if (entrada == null) {
            return;
        }
        try {
            int dano = Integer.parseInt(entrada.trim());
            gremioControlador.recibirDano(a, dano);
            registrarActividad("[DAÑO] " + a.getNombre() + " -" + dano + " PV → "
                    + a.getHpActual() + "/" + a.getPvMaximos());
            pintarGremio();
            actualizarResumen();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void curarAventurero(Aventurero a) {
        try {
            gremioControlador.curar(a);
            registrarActividad("[CURAR] " + a.getNombre() + " curado a " + a.getHpActual() + " PV");
            pintarGremio();
            actualizarResumen();
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void eliminarAventurero(Aventurero a) {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Eliminar a " + a.getNombre() + " del gremio?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            gremioControlador.eliminar(a);
            registrarActividad("[ELIMINAR] " + a.getNombre() + " eliminado del gremio");
            pintarGremio();
            actualizarResumen();
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void btnMonstruoActionPerformed(java.awt.event.ActionEvent evt) {
        if (combateControlador != null && combateControlador.isActivo()) {
            JOptionPane.showMessageDialog(this, "Termina el combate actual primero.",
                    "Combate en curso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Object origen = evt.getSource();
        if (origen == btnGoblin) {
            monstruoSeleccionado = "Goblin";
        } else if (origen == btnOgro) {
            monstruoSeleccionado = "Ogro";
        } else if (origen == btnDragon) {
            monstruoSeleccionado = "Dragón";
        }
        resaltarMonstruo();
        Monstruo vista = CombateControlador.crearMonstruo(monstruoSeleccionado);
        lblMonsterHp.setText(vista.getNombre() + " — "
                + vista.getHp() + "/" + vista.getHpMaximos() + " PV");
        lblMonsterHp.setIcon(iconoMonstruo(vista.getIcono(), 20));
        pbMonster.setMaximum(vista.getHpMaximos());
        pbMonster.setValue(vista.getHp());
    }

    private void resaltarMonstruo() {
        btnGoblin.setBackground("Goblin".equals(monstruoSeleccionado) ? ROJO : PANEL);
        btnOgro.setBackground("Ogro".equals(monstruoSeleccionado) ? ROJO : PANEL);
        btnDragon.setBackground("Dragón".equals(monstruoSeleccionado) ? ROJO : PANEL);
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Monstruo enemigo = CombateControlador.crearMonstruo(monstruoSeleccionado);
            combateControlador = new CombateControlador(gremioControlador.listarAventureros());
            combateControlador.iniciarCombate(enemigo);
            registrarActividad("[COMBATE] Combate iniciado contra " + enemigo.getNombre());
            actualizarCombate();
            actualizarResumen();
        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            mostrarError(e);
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (combateControlador != null) {
                combateControlador.cancelar();
                registrarActividad("[COMBATE] Combate cancelado");
            }
            actualizarCombate();
            actualizarResumen();
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void btnD4ActionPerformed(java.awt.event.ActionEvent evt) {
        tirarDadoManual(4);
    }

    private void btnD6ActionPerformed(java.awt.event.ActionEvent evt) {
        tirarDadoManual(6);
    }

    /** El usuario tira el dado: la vista solo entrega el número al controlador. */
    private void tirarDadoManual(int caras) {
        int valor = dadoManual.nextInt(caras) + 1;
        ultimaTirada = "d" + caras + " = " + valor;
        try {
            if (combateControlador != null && combateControlador.isEsperandoDano()) {
                if (combateControlador.getClaseDano() != caras) {
                    JOptionPane.showMessageDialog(this,
                            "Esta clase debe tirar el d" + combateControlador.getClaseDano() + ".",
                            "Dado incorrecto", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                combateControlador.aplicarDano(valor);
                registrarActividad("[DADO] Daño manual: d" + caras + " = " + valor);
                actualizarCombate();
                actualizarResumen();
            } else {
                gremioControlador.registrarEvento("DADO", "d" + caras + " = " + valor);
                registrarActividad("[DADO] Tirada libre: d" + caras + " = " + valor);
                actualizarResumen();
            }
        } catch (IllegalArgumentException | IOException e) {
            mostrarError(e);
        }
    }

    private void actualizarCombate() {
        if (combateControlador != null && combateControlador.getMonstruo() != null) {
            Monstruo m = combateControlador.getMonstruo();
            lblMonsterHp.setText(m.getNombre() + " — "
                    + m.getHp() + "/" + m.getHpMaximos() + " PV");
            lblMonsterHp.setIcon(iconoMonstruo(m.getIcono(), 20));
            pbMonster.setMaximum(m.getHpMaximos());
            pbMonster.setValue(m.getHp());
            int pct = (int) Math.round(m.getHp() * 100.0 / m.getHpMaximos());
            pbMonster.setForeground(pct > 60 ? VERDE : (pct > 30 ? AMARILLO : ROJO));
            lblEstado.setText(sinEmojis(combateControlador.getEstado()));
            lblEstado.setIcon(icono("swords", 20));
            txtConsola.setText(sinEmojis(CombateControlador.bitacoraComoTexto(combateControlador.getBitacora())));
            txtConsola.setCaretPosition(txtConsola.getDocument().getLength());

            boolean esperando = combateControlador.isEsperandoDano();
            int dado = combateControlador.getClaseDano();
            btnD4.setBackground(esperando && dado == 4 ? ORO : PANEL);
            btnD6.setBackground(esperando && dado == 6 ? ORO : PANEL);
            btnIniciar.setEnabled(!combateControlador.isActivo());
            btnCancelar.setEnabled(combateControlador.isActivo());
        } else {
            lblEstado.setText("Elige un monstruo y pulsa «Iniciar combate»");
            lblEstado.setIcon(icono("swords", 20));
            btnD4.setBackground(PANEL);
            btnD6.setBackground(PANEL);
            btnIniciar.setEnabled(true);
            btnCancelar.setEnabled(false);
        }
        lblUltimaTirada.setText("Última tirada: " + ultimaTirada);
        lblUltimaTirada.setIcon(icono("dado", 18));
    }

    private void btnAtajoCrearActionPerformed(java.awt.event.ActionEvent evt) {
        jTabbedPane1.setSelectedIndex(1);
    }

    private void btnAtajoCombateActionPerformed(java.awt.event.ActionEvent evt) {
        jTabbedPane1.setSelectedIndex(3);
    }

    private void btnReporteClaseActionPerformed(java.awt.event.ActionEvent evt) {
        Map<String, Integer> conteo = gremioControlador.generarReportePorClase();
        StringBuilder sb = new StringBuilder("═══ REPORTE: AVENTUREROS POR CLASE ═══\n");
        if (conteo.isEmpty()) {
            sb.append("No hay aventureros registrados.\n");
        } else {
            for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
                sb.append("• ").append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
            }
            sb.append("Total: ").append(gremioControlador.getCantidadAventureros()).append(" aventureros\n");
        }
        lblReporte.setText("Último reporte: aventureros por clase");
        lblReporte.setIcon(icono("chart", 18));
        registrarActividad(sb.toString());
        try {
            gremioControlador.registrarEvento("REPORTE", "Por clase: " + conteo);
            actualizarResumen();
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void btnReporteNivelActionPerformed(java.awt.event.ActionEvent evt) {
        List<Aventurero> filtrados = gremioControlador.generarReporteNivelMayor(3);
        StringBuilder sb = new StringBuilder("═══ REPORTE: NIVEL MAYOR A 3 ═══\n");
        if (filtrados.isEmpty()) {
            sb.append("Ningún aventurero supera el nivel 3.\n");
        } else {
            for (Aventurero a : filtrados) {
                sb.append("• ").append(a.getNombre())
                        .append(" — Nv.").append(a.getNivel()).append(", ")
                        .append(a.getHpActual()).append('/').append(a.getPvMaximos())
                        .append(" PV, CA ").append(a.calcularCA()).append('\n');
            }
        }
        lblReporte.setText("Último reporte: nivel mayor a 3");
        lblReporte.setIcon(icono("chart", 18));
        registrarActividad(sb.toString());
        try {
            gremioControlador.registrarEvento("REPORTE", "Nivel > 3: " + filtrados.size() + " aventurero(s)");
            actualizarResumen();
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        txtActividad.setText("");
        txtConsola.setText("");
        lblReporte.setText("Reportes: aún no se han generado");
        lblReporte.setIcon(icono("chart", 18));
    }

    private void actualizarResumen() {
        try {
            List<String> lineas = gremioControlador.leerRegistro();
            txtArchivo.setText(lineas.isEmpty() ? "El archivo está vacío."
                    : String.join(System.lineSeparator(), lineas));
            txtArchivo.setCaretPosition(txtArchivo.getDocument().getLength());
            lblLineas.setText(lineas.size() + " líneas");
            lblLineas.setIcon(icono("file", 18));
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void registrarActividad(String texto) {
        txtActividad.append(texto + System.lineSeparator());
        txtActividad.setCaretPosition(txtActividad.getDocument().getLength());
    }

    private void mostrarError(Exception e) {
        String mensaje = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        registrarActividad("[ERROR] " + mensaje);
    }

    /** Punto de entrada de la aplicación. */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
            javax.swing.UIManager.put("Panel.background", FONDO);
            javax.swing.UIManager.put("TabbedPane.background", new Color(26, 15, 8));
            javax.swing.UIManager.put("TabbedPane.foreground", TEXTO);
            javax.swing.UIManager.put("TabbedPane.selectionForeground", TEXTO);
            javax.swing.UIManager.put("TextArea.background", new Color(10, 5, 5));
            javax.swing.UIManager.put("TextArea.foreground", new Color(201, 209, 217));
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtajoCombate;
    private javax.swing.JButton btnAtajoCrear;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnComenzar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnD4;
    private javax.swing.JButton btnD6;
    private javax.swing.JButton btnDragon;
    private javax.swing.JButton btnGoblin;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnIrCrear;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnOgro;
    private javax.swing.JButton btnReporteClase;
    private javax.swing.JButton btnReporteNivel;
    private javax.swing.JComboBox cmbAtributo;
    private javax.swing.JComboBox cmbClase;
    private javax.swing.JComboBox cmbPv;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAtributo;
    private javax.swing.JLabel lblClase;
    private javax.swing.JLabel lblContador;
    private javax.swing.JLabel lblDadoClase;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLineas;
    private javax.swing.JLabel lblMonsterHp;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPv;
    private javax.swing.JLabel lblReporte;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloCombate;
    private javax.swing.JLabel lblTituloCrear;
    private javax.swing.JLabel lblTituloResumen;
    private javax.swing.JLabel lblUltimaTirada;
    private javax.swing.JPanel panelCombate;
    private javax.swing.JPanel panelCrear;
    private javax.swing.JPanel panelGremio;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelResumen;
    private javax.swing.JPanel panelTarjetas;
    private javax.swing.JProgressBar pbMonster;
    private javax.swing.JSpinner spNivel;
    private javax.swing.JTextArea txtActividad;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtConsola;
    private javax.swing.JTextArea txtInicio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

package gui;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JComboBox;

import entidad.Libro;
import entidad.SolicitudPrestamo;
import logica.GestorBiblioteca;

public class BibliotecaGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private GestorBiblioteca gestor;

    private JTextField txtCodigoLibro;
    private JTextField txtTituloLibro;
    private JTextField txtAutorLibro;
    private JTextField txtStockLibro;

    private JTextField txtCodigoUsuario;
    private JTextField txtNombreUsuario;
    private JTextField txtBuscarTituloLibro;
    private JComboBox<Libro> cboLibrosEncontrados;

    private JTextArea txtResultado;
    private JTable tablaLibros;
    private LibroTableModel modeloLibros;
    private JLabel lblEstado;

    public BibliotecaGUI() {
        gestor = new GestorBiblioteca();

        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("Biblioteca Universitaria - Sistema de Préstamos");
        setSize(1050, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearComponentes() {
        JPanel fondo = new JPanel(new BorderLayout(15, 15));
        fondo.setBackground(new Color(236, 239, 244));
        fondo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Biblioteca Universitaria", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitulo.setForeground(new Color(45, 52, 72));

        JLabel lblSubtitulo = new JLabel("Sistema de registro de libros, solicitudes y entregas con stock");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setForeground(new Color(90, 100, 115));

        JPanel cabecera = new JPanel(new GridLayout(2, 1));
        cabecera.setBackground(new Color(236, 239, 244));
        cabecera.add(lblTitulo);
        cabecera.add(lblSubtitulo);

        JPanel panelFormularios = new JPanel(new GridLayout(1, 2, 20, 20));
        panelFormularios.setBackground(new Color(236, 239, 244));
        panelFormularios.add(crearPanelRegistroLibro());
        panelFormularios.add(crearPanelSolicitudPrestamo());

        JPanel panelBotones = crearPanelBotonesGenerales();

        JPanel panelSuperior = new JPanel(new BorderLayout(15, 15));
        panelSuperior.setBackground(new Color(236, 239, 244));
        panelSuperior.add(panelFormularios, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        JPanel panelResultado = new JPanel(new BorderLayout(10, 10));
        panelResultado.setBackground(Color.WHITE);
        panelResultado.setPreferredSize(new Dimension(1000, 240));
        panelResultado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 225)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblResultado = new JLabel("Listado / Resultados");
        lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblResultado.setForeground(new Color(45, 52, 72));

        modeloLibros = new LibroTableModel(gestor.getLibros());
        tablaLibros = new JTable(modeloLibros);

        tablaLibros.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaLibros.setRowHeight(28);
        tablaLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaLibros.getTableHeader().setBackground(new Color(230, 236, 245));
        tablaLibros.getTableHeader().setForeground(new Color(35, 55, 85));

        tablaLibros.getColumnModel().getColumn(4).setCellRenderer(new EstadoCellRenderer());

        JScrollPane scrollResultado = new JScrollPane(tablaLibros);
        scrollResultado.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));

        panelResultado.add(lblResultado, BorderLayout.NORTH);
        panelResultado.add(scrollResultado, BorderLayout.CENTER);

        lblEstado = new JLabel("Sistema listo para registrar libros y solicitudes.", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setForeground(new Color(70, 80, 95));

        fondo.add(cabecera, BorderLayout.NORTH);
        fondo.add(panelSuperior, BorderLayout.CENTER);
        fondo.add(panelResultado, BorderLayout.SOUTH);

        add(fondo);
    }

    private JPanel crearPanelRegistroLibro() {
        JPanel panel = crearTarjetaFormulario("Registrar libro");

        JPanel campos = new JPanel(new GridLayout(4, 2, 12, 14));
        campos.setBackground(new Color(248, 249, 252));

        txtCodigoLibro = crearCampoTexto();
        txtTituloLibro = crearCampoTexto();
        txtAutorLibro = crearCampoTexto();
        txtStockLibro = crearCampoTexto();

        campos.add(crearEtiqueta("Código / ISBN:"));
        campos.add(txtCodigoLibro);

        campos.add(crearEtiqueta("Título:"));
        campos.add(txtTituloLibro);

        campos.add(crearEtiqueta("Autor:"));
        campos.add(txtAutorLibro);

        campos.add(crearEtiqueta("Stock:"));
        campos.add(txtStockLibro);

        JButton btnRegistrarLibro = crearBotonPrincipal("Registrar Nuevo Libro");
        btnRegistrarLibro.addActionListener(e -> registrarLibro());

        panel.add(campos, BorderLayout.CENTER);
        panel.add(btnRegistrarLibro, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelSolicitudPrestamo() {
        JPanel panel = crearTarjetaFormulario("Solicitar préstamo");

        JPanel campos = new JPanel(new GridLayout(4, 2, 12, 14));
        campos.setBackground(new Color(248, 249, 252));

        txtCodigoUsuario = crearCampoTexto();
        txtNombreUsuario = crearCampoTexto();
        txtBuscarTituloLibro = crearCampoTexto();
        cboLibrosEncontrados = new javax.swing.JComboBox<>();

        cboLibrosEncontrados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLibrosEncontrados.setBackground(Color.WHITE);

        campos.add(crearEtiqueta("Código usuario:"));
        campos.add(txtCodigoUsuario);

        campos.add(crearEtiqueta("Nombre usuario:"));
        campos.add(txtNombreUsuario);

        campos.add(crearEtiqueta("Buscar título:"));
        campos.add(txtBuscarTituloLibro);

        JButton btnBuscarLibro = crearBotonSecundario("Buscar libro");
        btnBuscarLibro.addActionListener(e -> buscarLibrosPorTitulo());

        campos.add(btnBuscarLibro);
        campos.add(cboLibrosEncontrados);

        JButton btnSolicitarPrestamo = crearBotonPrincipal("Solicitar préstamo");
        btnSolicitarPrestamo.addActionListener(e -> solicitarPrestamo());

        panel.add(campos, BorderLayout.CENTER);
        panel.add(btnSolicitarPrestamo, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearTarjetaFormulario(String titulo) {
        JPanel panel = new JPanel(new BorderLayout(12, 15));
        panel.setBackground(new Color(248, 249, 252));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 229, 236)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(45, 52, 72));

        panel.add(lblTitulo, BorderLayout.NORTH);

        return panel;
    }

    private JPanel crearPanelBotonesGenerales() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 15));
        panel.setBackground(new Color(236, 239, 244));
        panel.setBorder(new EmptyBorder(5, 0, 0, 0));

        JButton btnGestionarEntregas = crearBotonSecundario("Gestionar entregas");
        JButton btnVerLibros = crearBotonSecundario("Catálogo de Libros");
        JButton btnSalir = crearBotonSalir("Salir");

        btnGestionarEntregas.addActionListener(e -> abrirDialogoEntregas());
        btnVerLibros.addActionListener(e -> verLibros());
        btnSalir.addActionListener(e -> salir());

        panel.add(btnGestionarEntregas);
        panel.add(btnVerLibros);
        panel.add(btnSalir);

        return panel;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        etiqueta.setForeground(new Color(55, 65, 80));
        return etiqueta;
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();

        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(Color.BLACK);
        campo.setBackground(Color.WHITE);
        campo.setEditable(true);
        campo.setFocusable(true);
        campo.setEnabled(true);
        campo.setPreferredSize(new Dimension(220, 36));

        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 170, 185)),
                new EmptyBorder(6, 10, 6, 10)
        ));

        return campo;
    }

    private JButton crearBotonPrincipal(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(new Color(48, 96, 160));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(11, 15, 11, 15));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    private JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(new Color(230, 236, 245));
        boton.setForeground(new Color(35, 55, 85));
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(11, 15, 11, 15));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    private JButton crearBotonSalir(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(new Color(180, 60, 70));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(11, 15, 11, 15));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    private void registrarLibro() {
        try {
            String codigo = txtCodigoLibro.getText();
            String titulo = txtTituloLibro.getText();
            String autor = txtAutorLibro.getText();
            String stockTexto = txtStockLibro.getText();

            if (stockTexto == null || stockTexto.trim().isEmpty()) {
                throw new IllegalArgumentException("El stock del libro no puede estar vacío.");
            }

            int stock;

            try {
                stock = Integer.parseInt(stockTexto.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El stock debe ser un número entero.");
            }

            Libro libro = gestor.registrarLibro(codigo, titulo, autor, stock);

            JOptionPane.showMessageDialog(
                    this,
                    "Libro registrado correctamente:\n" + libro.toString(),
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCamposLibro();
            mostrarListadoLibros();

            lblEstado.setText("Libro registrado correctamente.");

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }
    
    private void buscarLibrosPorTitulo() {
        try {
            String tituloBuscado = txtBuscarTituloLibro.getText();

            java.util.List<Libro> resultados = gestor.buscarLibrosPorTitulo(tituloBuscado);

            cboLibrosEncontrados.removeAllItems();

            if (resultados.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron libros con ese título.");
            }

            for (Libro libro : resultados) {
                cboLibrosEncontrados.addItem(libro);
            }

            lblEstado.setText("Seleccione un libro de la lista de resultados.");

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void solicitarPrestamo() {
        try {
            String codigoUsuario = txtCodigoUsuario.getText();
            String nombreUsuario = txtNombreUsuario.getText();

            Libro libroSeleccionado = (Libro) cboLibrosEncontrados.getSelectedItem();

            SolicitudPrestamo solicitud = gestor.solicitarPrestamo(
                    codigoUsuario,
                    nombreUsuario,
                    libroSeleccionado
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitud registrada correctamente:\n" + solicitud.toString(),
                    "Solicitud registrada",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCamposPrestamo();

            modeloLibros.actualizarDatos();

            lblEstado.setText("Solicitud agregada a la cola de entregas.");

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void abrirDialogoEntregas() {
        ColaDialog dialogo = new ColaDialog(this, gestor, () -> {
            mostrarListadoLibros();
            lblEstado.setText("Libro entregado correctamente. Stock actualizado.");
        });

        dialogo.setVisible(true);
    }

    private void verLibros() {
        try {
            mostrarListadoLibros();
            lblEstado.setText("Mostrando libros registrados.");
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarListadoLibros() {
        modeloLibros.actualizarDatos();

        if (gestor.getLibros().isEmpty()) {
            lblEstado.setText("No hay libros registrados.");
        } else {
            lblEstado.setText("Catálogo de libros actualizado.");
        }
    }

    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de salir del sistema?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void mostrarError(String mensaje) {
        lblEstado.setText("Se encontró un error de validación.");
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
    }

    private void limpiarCamposLibro() {
        txtCodigoLibro.setText("");
        txtTituloLibro.setText("");
        txtAutorLibro.setText("");
        txtStockLibro.setText("");
        txtCodigoLibro.requestFocus();
    }

    private void limpiarCamposPrestamo() {
        txtCodigoUsuario.setText("");
        txtNombreUsuario.setText("");
        txtBuscarTituloLibro.setText("");
        cboLibrosEncontrados.removeAllItems();
        txtCodigoUsuario.requestFocus();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new BibliotecaGUI().setVisible(true);
        });
    }
}
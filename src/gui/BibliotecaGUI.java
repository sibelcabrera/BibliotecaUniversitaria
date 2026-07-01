package gui;

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

import entidad.Libro;
import entidad.SolicitudPrestamo;
import logica.GestorBiblioteca;

public class BibliotecaGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private GestorBiblioteca gestor;

    private JTextField txtCodigoLibro;
    private JTextField txtTituloLibro;
    private JTextField txtAutorLibro;

    private JTextField txtCodigoUsuario;
    private JTextField txtNombreUsuario;
    private JTextField txtCodigoLibroPrestamo;

    private JTextArea txtResultado;
    private JLabel lblEstado;

    public BibliotecaGUI() {
        gestor = new GestorBiblioteca();

        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("Biblioteca Universitaria - Sistema de Préstamos");
        setSize(1050, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearComponentes() {
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(236, 239, 244));
        fondo.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel tarjetaPrincipal = new JPanel(new BorderLayout(20, 20));
        tarjetaPrincipal.setBackground(Color.WHITE);
        tarjetaPrincipal.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Biblioteca Universitaria");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitulo.setForeground(new Color(45, 52, 72));

        JLabel lblSubtitulo = new JLabel("Sistema de registro de libros y gestión de préstamos mediante cola de atención");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitulo.setForeground(new Color(100, 110, 125));

        JPanel textosCabecera = new JPanel(new GridLayout(2, 1));
        textosCabecera.setBackground(Color.WHITE);
        textosCabecera.add(lblTitulo);
        textosCabecera.add(lblSubtitulo);

        cabecera.add(textosCabecera, BorderLayout.WEST);

        JPanel panelFormularios = new JPanel(new GridLayout(1, 2, 20, 20));
        panelFormularios.setBackground(Color.WHITE);
        panelFormularios.add(crearPanelRegistroLibro());
        panelFormularios.add(crearPanelSolicitudPrestamo());

        JPanel panelAcciones = crearPanelBotonesGenerales();

        JPanel panelResultado = new JPanel(new BorderLayout(10, 10));
        panelResultado.setBackground(new Color(248, 249, 252));
        panelResultado.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel lblResultado = new JLabel("Resultados del sistema");
        lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblResultado.setForeground(new Color(45, 52, 72));

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtResultado.setForeground(new Color(35, 35, 35));
        txtResultado.setBackground(Color.WHITE);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));

        panelResultado.add(lblResultado, BorderLayout.NORTH);
        panelResultado.add(scrollResultado, BorderLayout.CENTER);

        lblEstado = new JLabel("Sistema listo para registrar libros y solicitudes.", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setForeground(new Color(70, 80, 95));
        lblEstado.setBorder(new EmptyBorder(8, 0, 0, 0));

        tarjetaPrincipal.add(cabecera, BorderLayout.NORTH);
        tarjetaPrincipal.add(panelFormularios, BorderLayout.CENTER);
        tarjetaPrincipal.add(panelAcciones, BorderLayout.SOUTH);

        fondo.add(tarjetaPrincipal, BorderLayout.NORTH);
        fondo.add(panelResultado, BorderLayout.CENTER);
        fondo.add(lblEstado, BorderLayout.SOUTH);

        add(fondo);
    }

    private JPanel crearPanelRegistroLibro() {
        JPanel panel = crearTarjetaFormulario("Registrar libro");

        JPanel campos = new JPanel(new GridLayout(3, 2, 12, 14));
        campos.setBackground(new Color(248, 249, 252));

        txtCodigoLibro = crearCampoTexto();
        txtTituloLibro = crearCampoTexto();
        txtAutorLibro = crearCampoTexto();

        campos.add(crearEtiqueta("Código / ISBN:"));
        campos.add(txtCodigoLibro);

        campos.add(crearEtiqueta("Título:"));
        campos.add(txtTituloLibro);

        campos.add(crearEtiqueta("Autor:"));
        campos.add(txtAutorLibro);

        JButton btnRegistrarLibro = crearBotonPrincipal("Registrar libro");
        btnRegistrarLibro.addActionListener(e -> registrarLibro());

        panel.add(campos, BorderLayout.CENTER);
        panel.add(btnRegistrarLibro, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelSolicitudPrestamo() {
        JPanel panel = crearTarjetaFormulario("Solicitar préstamo");

        JPanel campos = new JPanel(new GridLayout(3, 2, 12, 14));
        campos.setBackground(new Color(248, 249, 252));

        txtCodigoUsuario = crearCampoTexto();
        txtNombreUsuario = crearCampoTexto();
        txtCodigoLibroPrestamo = crearCampoTexto();

        campos.add(crearEtiqueta("Código usuario:"));
        campos.add(txtCodigoUsuario);

        campos.add(crearEtiqueta("Nombre usuario:"));
        campos.add(txtNombreUsuario);

        campos.add(crearEtiqueta("Código libro:"));
        campos.add(txtCodigoLibroPrestamo);

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
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(5, 0, 0, 0));

        JButton btnAtender = crearBotonSecundario("Atender siguiente");
        JButton btnVerCola = crearBotonSecundario("Ver cola");
        JButton btnVerLibros = crearBotonSecundario("Ver libros");
        JButton btnSalir = crearBotonSalir("Salir");

        btnAtender.addActionListener(e -> atenderSiguienteSolicitud());
        btnVerCola.addActionListener(e -> verCola());
        btnVerLibros.addActionListener(e -> verLibros());
        btnSalir.addActionListener(e -> salir());

        panel.add(btnAtender);
        panel.add(btnVerCola);
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
        campo.setForeground(new Color(30, 30, 30));
        campo.setBackground(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 225)),
                new EmptyBorder(8, 10, 8, 10)
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

            Libro libro = gestor.registrarLibro(codigo, titulo, autor);

            String mensaje = "Libro registrado correctamente:\n" + libro.toString();

            txtResultado.setText(mensaje);
            lblEstado.setText("Libro registrado correctamente.");
            JOptionPane.showMessageDialog(this, mensaje, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

            limpiarCamposLibro();

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void solicitarPrestamo() {
        try {
            String codigoUsuario = txtCodigoUsuario.getText();
            String nombreUsuario = txtNombreUsuario.getText();
            String codigoLibro = txtCodigoLibroPrestamo.getText();

            SolicitudPrestamo solicitud = gestor.solicitarPrestamo(
                    codigoUsuario,
                    nombreUsuario,
                    codigoLibro
            );

            String mensaje = "Solicitud registrada correctamente:\n" + solicitud.toString();

            txtResultado.setText(mensaje);
            lblEstado.setText("Solicitud agregada a la cola.");
            JOptionPane.showMessageDialog(this, mensaje, "Solicitud registrada", JOptionPane.INFORMATION_MESSAGE);

            limpiarCamposPrestamo();

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void atenderSiguienteSolicitud() {
        try {
            SolicitudPrestamo solicitud = gestor.atenderSiguienteSolicitud();

            String mensaje = "Solicitud atendida correctamente:\n"
                    + solicitud.toString()
                    + "\n\nEl libro fue marcado como NO DISPONIBLE.";

            txtResultado.setText(mensaje);
            lblEstado.setText("Se atendió la siguiente solicitud de la cola.");
            JOptionPane.showMessageDialog(this, mensaje, "Solicitud atendida", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void verCola() {
        try {
            String textoCola = gestor.getColaSolicitudes().listarComoTexto();

            txtResultado.setText("=== COLA DE SOLICITUDES DE PRÉSTAMO ===\n\n" + textoCola);
            lblEstado.setText("Mostrando cola de solicitudes.");

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void verLibros() {
        try {
            if (gestor.getLibros().isEmpty()) {
                txtResultado.setText("No hay libros registrados.");
                lblEstado.setText("No existen libros registrados.");
                return;
            }

            StringBuilder resultado = new StringBuilder();
            resultado.append("=== LIBROS REGISTRADOS ===\n\n");

            int posicion = 1;

            for (Libro libro : gestor.getLibros()) {
                resultado.append(posicion)
                        .append(". ")
                        .append(libro.toString())
                        .append("\n");

                posicion++;
            }

            txtResultado.setText(resultado.toString());
            lblEstado.setText("Mostrando libros registrados.");

        } catch (Exception e) {
            mostrarError(e.getMessage());
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
        txtCodigoLibro.requestFocus();
    }

    private void limpiarCamposPrestamo() {
        txtCodigoUsuario.setText("");
        txtNombreUsuario.setText("");
        txtCodigoLibroPrestamo.setText("");
        txtCodigoUsuario.requestFocus();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new BibliotecaGUI().setVisible(true);
        });
    }
}
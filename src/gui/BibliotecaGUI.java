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
import javax.swing.UIManager;

import entidad.Libro;
import logica.GestorBiblioteca;
import entidad.SolicitudPrestamo;

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
        setSize(980, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("Sistema de Biblioteca Universitaria", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(new Color(80, 30, 45));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(15, 15));
        panelCentro.setBackground(new Color(245, 245, 245));

        JPanel panelFormularios = new JPanel(new GridLayout(1, 2, 15, 15));
        panelFormularios.setBackground(new Color(245, 245, 245));

        panelFormularios.add(crearPanelRegistroLibro());
        panelFormularios.add(crearPanelSolicitudPrestamo());

        JPanel panelBotones = crearPanelBotonesGenerales();

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(BorderFactory.createTitledBorder("Resultados del sistema"));

        lblEstado = new JLabel("Sistema listo para registrar libros y solicitudes.", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstado.setForeground(new Color(80, 30, 45));

        panelCentro.add(panelFormularios, BorderLayout.NORTH);
        panelCentro.add(panelBotones, BorderLayout.CENTER);
        panelCentro.add(scrollResultado, BorderLayout.SOUTH);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(lblEstado, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearPanelRegistroLibro() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Libro"));

        JPanel campos = new JPanel(new GridLayout(3, 2, 10, 10));
        campos.setBackground(Color.WHITE);

        txtCodigoLibro = new JTextField();
        txtTituloLibro = new JTextField();
        txtAutorLibro = new JTextField();

        campos.add(new JLabel("Código / ISBN:"));
        campos.add(txtCodigoLibro);

        campos.add(new JLabel("Título:"));
        campos.add(txtTituloLibro);

        campos.add(new JLabel("Autor:"));
        campos.add(txtAutorLibro);

        JButton btnRegistrarLibro = crearBoton("Registrar libro");
        btnRegistrarLibro.addActionListener(e -> registrarLibro());

        panel.add(campos, BorderLayout.CENTER);
        panel.add(btnRegistrarLibro, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelSolicitudPrestamo() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Solicitar Préstamo"));

        JPanel campos = new JPanel(new GridLayout(3, 2, 10, 10));
        campos.setBackground(Color.WHITE);

        txtCodigoUsuario = new JTextField();
        txtNombreUsuario = new JTextField();
        txtCodigoLibroPrestamo = new JTextField();

        campos.add(new JLabel("Código usuario:"));
        campos.add(txtCodigoUsuario);

        campos.add(new JLabel("Nombre usuario:"));
        campos.add(txtNombreUsuario);

        campos.add(new JLabel("Código libro:"));
        campos.add(txtCodigoLibroPrestamo);

        JButton btnSolicitarPrestamo = crearBoton("Solicitar préstamo");
        btnSolicitarPrestamo.addActionListener(e -> solicitarPrestamo());

        panel.add(campos, BorderLayout.CENTER);
        panel.add(btnSolicitarPrestamo, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelBotonesGenerales() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 15));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton btnAtender = crearBoton("Atender siguiente");
        JButton btnVerCola = crearBoton("Ver cola");
        JButton btnVerLibros = crearBoton("Ver libros");
        JButton btnSalir = crearBoton("Salir");

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

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setBackground(new Color(100, 35, 55));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, continúa con el diseño por defecto.
        }

        java.awt.EventQueue.invokeLater(() -> {
            new BibliotecaGUI().setVisible(true);
        });
    }
}
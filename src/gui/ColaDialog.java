package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import entidad.SolicitudPrestamo;
import logica.GestorBiblioteca;

public class ColaDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private GestorBiblioteca gestor;
    private JTextArea txtCola;
    private Runnable actualizarPantallaPrincipal;

    public ColaDialog(JFrame parent, GestorBiblioteca gestor, Runnable actualizarPantallaPrincipal) {
        super(parent, "Gestionar entregas", true);

        this.gestor = gestor;
        this.actualizarPantallaPrincipal = actualizarPantallaPrincipal;

        configurarDialogo();
        crearComponentes();
        actualizarCola();
    }

    private void configurarDialogo() {
        setSize(650, 450);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }

    private void crearComponentes() {
        JPanel fondo = new JPanel(new BorderLayout(15, 15));
        fondo.setBackground(new Color(236, 239, 244));
        fondo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Cola de solicitudes pendientes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(45, 52, 72));

        txtCola = new JTextArea();
        txtCola.setEditable(false);
        txtCola.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtCola.setForeground(Color.BLACK);
        txtCola.setBackground(Color.WHITE);
        txtCola.setLineWrap(true);
        txtCola.setWrapStyleWord(true);
        txtCola.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scroll = new JScrollPane(txtCola);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 225)));

        JButton btnEntregar = crearBotonPrincipal("Entregar siguiente libro");
        JButton btnCerrar = crearBotonSecundario("Cerrar");

        btnEntregar.addActionListener(e -> entregarSiguienteLibro());
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel(new BorderLayout(10, 10));
        panelBotones.setBackground(new Color(236, 239, 244));
        panelBotones.add(btnEntregar, BorderLayout.CENTER);
        panelBotones.add(btnCerrar, BorderLayout.EAST);

        fondo.add(lblTitulo, BorderLayout.NORTH);
        fondo.add(scroll, BorderLayout.CENTER);
        fondo.add(panelBotones, BorderLayout.SOUTH);

        add(fondo);
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

    private void actualizarCola() {
        txtCola.setText("=== SOLICITUDES PENDIENTES ===\n\n" + gestor.listarColaComoTexto());
    }

    private void entregarSiguienteLibro() {
        try {
            SolicitudPrestamo solicitud = gestor.entregarSiguienteSolicitud();

            JOptionPane.showMessageDialog(
                    this,
                    "Libro entregado correctamente:\n\n" + solicitud.toString(),
                    "Entrega realizada",
                    JOptionPane.INFORMATION_MESSAGE
            );

            actualizarCola();

            if (actualizarPantallaPrincipal != null) {
                actualizarPantallaPrincipal.run();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "No se pudo entregar",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
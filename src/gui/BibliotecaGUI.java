package gui;

import javax.swing.JFrame;

import logica.GestorBiblioteca;

/**
 * Ventana principal del sistema de biblioteca.
 * Debe permitir registrar libros, solicitar préstamos (encolar)
 * y atender la siguiente solicitud (desencolar).
 *
 * TODO: Construir la interfaz gráfica con Swing. Sugerencia de componentes:
 *  - Panel "Registrar Libro": campos código, título, autor + botón Registrar
 *  - Panel "Solicitar Préstamo": campos código usuario, nombre, código libro + botón Solicitar
 *  - Botones generales: Atender siguiente solicitud, Ver cola, Ver libros, Salir
 *  - Un área de texto (JTextArea) para mostrar resultados/mensajes
 *
 * TODO: Conectar cada botón con los métodos de GestorBiblioteca.
 * TODO: Mostrar los mensajes de validación (try/catch) con JOptionPane.
 */
public class BibliotecaGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private GestorBiblioteca gestor;

	public BibliotecaGUI() {
		gestor = new GestorBiblioteca();
		// TODO: configurar la ventana (título, tamaño, layout, etc.)
		// TODO: construir e incluir los paneles/componentes
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BibliotecaGUI().setVisible(true);
			}
		});
	}
}

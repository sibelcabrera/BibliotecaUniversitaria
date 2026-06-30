package logica;

import java.util.ArrayList;
import java.util.List;

import entidad.Libro;
import estructura.ColaPrestamos;

/**
 * Clase que contiene la lógica del sistema: registra libros,
 * valida datos y administra la cola de solicitudes de préstamo.
 *
 * TODO: Implementar todas las validaciones que pide la rúbrica
 * (campos vacíos, código duplicado, libro no disponible, cola
 * vacía al atender, etc.).
 */
public class GestorBiblioteca {

	private List<Libro> libros;
	private ColaPrestamos colaSolicitudes;

	public GestorBiblioteca() {
		libros = new ArrayList<>();
		colaSolicitudes = new ColaPrestamos();
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public ColaPrestamos getColaSolicitudes() {
		return colaSolicitudes;
	}

	// ---------- Gestión de libros ----------

	// TODO: registrarLibro(String codigo, String titulo, String autor)
	//       - validar campos vacíos
	//       - validar que el código no esté duplicado

	// TODO: buscarLibroPorCodigo(String codigo)

	// ---------- Gestión de solicitudes (cola) ----------

	// TODO: solicitarPrestamo(String codigoUsuario, String nombreUsuario, String codigoLibro)
	//       - validar datos del usuario
	//       - validar que el libro exista
	//       - validar que el libro esté disponible
	//       - encolar la solicitud

	// TODO: atenderSiguienteSolicitud()
	//       - validar que la cola no esté vacía
	//       - desencolar y marcar el libro como no disponible

}

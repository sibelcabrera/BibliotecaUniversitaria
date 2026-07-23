package logica;
import java.util.ArrayList;
import java.util.List;
import entidad.Libro;
import entidad.SolicitudPrestamo;
import entidad.Usuario;
import estructura.ColaPrestamos;

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
	public Libro registrarLibro(String codigo, String titulo, String autor) {
		if (codigo == null || codigo.trim().isEmpty()) {
			throw new IllegalArgumentException("El código del libro no puede estar vacío.");
		}
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("El título del libro no puede estar vacío.");
		}
		if (autor == null || autor.trim().isEmpty()) {
			throw new IllegalArgumentException("El autor del libro no puede estar vacío.");
		}
		if (buscarLibroPorCodigo(codigo) != null) {
			throw new IllegalArgumentException(
					"Ya existe un libro registrado con el código \"" + codigo.trim() + "\".");
		}
		Libro libro = new Libro(codigo.trim(), titulo.trim(), autor.trim());
		libros.add(libro);
		return libro;
	}
	/**
	 * Busca un libro por su código. Devuelve null si no existe.
	 */
	public Libro buscarLibroPorCodigo(String codigo) {
		if (codigo == null || codigo.trim().isEmpty()) {
			return null;
		}
		for (Libro libro : libros) {
			if (libro.getCodigo().equalsIgnoreCase(codigo.trim())) {
				return libro;
			}
		}
		return null;
	}

	/**
	 * Busca libros cuyo título contenga el texto indicado (búsqueda por
	 * nombre, no distingue mayúsculas/minúsculas ni requiere coincidencia
	 * exacta). Devuelve todas las coincidencias encontradas.
	 */
	public List<Libro> buscarLibrosPorTitulo(String textoFiltro) {
		List<Libro> resultado = new ArrayList<>();
		if (textoFiltro == null || textoFiltro.trim().isEmpty()) {
			return resultado;
		}
		String filtro = textoFiltro.trim().toLowerCase();
		for (Libro libro : libros) {
			if (libro.getTitulo().toLowerCase().contains(filtro)) {
				resultado.add(libro);
			}
		}
		return resultado;
	}

	// ---------- Gestión de solicitudes (cola) ----------
	/**
	 * Crea una solicitud de préstamo y la encola.
	 * Valida datos del usuario, existencia del libro y disponibilidad.
	 */
	public SolicitudPrestamo solicitarPrestamo(String codigoUsuario, String nombreUsuario, String codigoLibro) {
		if (codigoUsuario == null || codigoUsuario.trim().isEmpty()) {
			throw new IllegalArgumentException("El código de usuario no puede estar vacío.");
		}
		if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
		}
		if (codigoLibro == null || codigoLibro.trim().isEmpty()) {
			throw new IllegalArgumentException("El código del libro no puede estar vacío.");
		}
		Libro libro = buscarLibroPorCodigo(codigoLibro);
		if (libro == null) {
			throw new IllegalArgumentException(
					"No existe un libro registrado con el código \"" + codigoLibro.trim() + "\".");
		}
		if (!libro.isDisponible()) {
			throw new IllegalArgumentException(
					"El libro \"" + libro.getTitulo() + "\" no está disponible actualmente.");
		}
		Usuario usuario = new Usuario(codigoUsuario.trim(), nombreUsuario.trim());
		SolicitudPrestamo solicitud = new SolicitudPrestamo(usuario, libro);
		colaSolicitudes.encolar(solicitud);
		return solicitud;
	}
	/**
	 * Atiende (desencola) la siguiente solicitud y marca el libro como
	 * no disponible. Valida que la cola no esté vacía.
	 */
	public SolicitudPrestamo atenderSiguienteSolicitud() {
		if (colaSolicitudes.estaVacia()) {
			throw new IllegalStateException("No hay solicitudes pendientes en la cola.");
		}
		SolicitudPrestamo solicitud = colaSolicitudes.desencolar();
		Libro libro = solicitud.getLibro();
		libro.setDisponible(false);
		return solicitud;
	}
}

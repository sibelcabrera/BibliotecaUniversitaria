package entidad;

import java.util.regex.Pattern;

/**
 * Clase modelo que representa un libro del catálogo.
 * Todos los setters validan sus datos antes de asignarlos; el constructor
 * reutiliza los setters para no duplicar (ni saltarse) esas validaciones.
 *
 * Nota: el campo "disponible" y su relación con el stock son responsabilidad
 * de la persona encargada del stock; aquí solo se valida su tipo (boolean),
 * que no admite nulos.
 */
public class Libro {

	private static final int LONGITUD_MINIMA_CODIGO = 2;
	private static final int LONGITUD_MINIMA_TITULO = 2;
	private static final int LONGITUD_MINIMA_AUTOR = 2;
	private static final Pattern PATRON_CODIGO = Pattern.compile("^[0-9]+$");
	private static final Pattern PATRON_AUTOR = Pattern.compile("^[\\p{L} .'-]+$");

	private String codigo;
	private String titulo;
	private String autor;
	private boolean disponible;

	/**
	 * @throws IllegalArgumentException si alguno de los datos no cumple las
	 *         validaciones de su setter correspondiente (ver setCodigo,
	 *         setTitulo, setAutor).
	 */
	public Libro(String codigo, String titulo, String autor) {
		setCodigo(codigo);
		setTitulo(titulo);
		setAutor(autor);
		setDisponible(true);
	}

	public String getCodigo() {
		return codigo;
	}

	/**
	 * @throws IllegalArgumentException si el código es nulo, está vacío,
	 *         no alcanza la longitud mínima o contiene caracteres no permitidos.
	 */
	public void setCodigo(String codigo) {
		if (codigo == null || codigo.trim().isEmpty()) {
			throw new IllegalArgumentException("El código del libro no puede estar vacío.");
		}
		String valor = codigo.trim();
		if (valor.length() < LONGITUD_MINIMA_CODIGO) {
			throw new IllegalArgumentException(
					"El código del libro debe tener al menos " + LONGITUD_MINIMA_CODIGO + " caracteres.");
		}
		if (!PATRON_CODIGO.matcher(valor).matches()) {
			throw new IllegalArgumentException(
					"El código del libro debe ser numérico (solo dígitos).");
		}
		this.codigo = valor;
	}

	public String getTitulo() {
		return titulo;
	}

	/**
	 * @throws IllegalArgumentException si el título es nulo, está vacío
	 *         o no alcanza la longitud mínima.
	 */
	public void setTitulo(String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("El título del libro no puede estar vacío.");
		}
		String valor = titulo.trim();
		if (valor.length() < LONGITUD_MINIMA_TITULO) {
			throw new IllegalArgumentException(
					"El título del libro debe tener al menos " + LONGITUD_MINIMA_TITULO + " caracteres.");
		}
		this.titulo = valor;
	}

	public String getAutor() {
		return autor;
	}

	/**
	 * @throws IllegalArgumentException si el autor es nulo, está vacío,
	 *         no alcanza la longitud mínima o contiene caracteres no permitidos.
	 */
	public void setAutor(String autor) {
		if (autor == null || autor.trim().isEmpty()) {
			throw new IllegalArgumentException("El autor del libro no puede estar vacío.");
		}
		String valor = autor.trim();
		if (valor.length() < LONGITUD_MINIMA_AUTOR) {
			throw new IllegalArgumentException(
					"El autor del libro debe tener al menos " + LONGITUD_MINIMA_AUTOR + " caracteres.");
		}
		if (!PATRON_AUTOR.matcher(valor).matches()) {
			throw new IllegalArgumentException(
					"El autor del libro solo puede contener letras, espacios, apóstrofes, puntos y guiones.");
		}
		this.autor = valor;
	}

	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * No lanza excepciones: al ser un tipo primitivo boolean, no admite
	 * valores nulos ni inválidos, por lo que no requiere validación.
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		String estado = disponible ? "Disponible" : "No disponible";
		return "[" + codigo + "] " + titulo + " - " + autor + " (" + estado + ")";
	}

}

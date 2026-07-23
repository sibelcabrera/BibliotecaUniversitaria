package entidad;

import java.util.regex.Pattern;

/**
 * Todos los setters validan sus datos antes de asignarlos; el constructor
 * reutiliza los setters para no duplicar (ni saltarse) esas validaciones.
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
	private int stock;

	/**
	 * Constructor por defecto con stock en 1 para mantener compatibilidad.
	 */
	public Libro(String codigo, String titulo, String autor) {
		this(codigo, titulo, autor, 1);
	}

	/**
	 * Constructor completo con campo stock.
	 * 
	 * @throws IllegalArgumentException si alguno de los datos no cumple las validaciones.
	 */
	public Libro(String codigo, String titulo, String autor, int stock) {
		setCodigo(codigo);
		setTitulo(titulo);
		setAutor(autor);
		setStock(stock);
	}

	public String getCodigo() {
		return codigo;
	}

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

	public int getStock() {
		return stock;
	}

	/**
	 * Valida que el stock no sea un valor negativo.
	 * 
	 * @throws IllegalArgumentException si el stock es menor a 0.
	 */
	public void setStock(int stock) {
		if (stock < 0) {
			throw new IllegalArgumentException("El stock no puede ser negativo.");
		}
		this.stock = stock;
	}

	/**
	 * Disminuye en 1 el stock del libro al ser entregado prestado.
	 * 
	 * @throws IllegalStateException si el stock ya es 0.
	 */
	public void disminuirStock() {
		if (this.stock <= 0) {
			throw new IllegalStateException("No hay stock suficiente disponible para este libro.");
		}
		this.stock--;
	}

	/**
	 * Incrementa en 1 el stock del libro al ser devuelto.
	 */
	public void incrementarStock() {
		this.stock++;
	}

	/**
	 * Calculado automáticamente: está disponible si hay stock > 0.
	 */
	public boolean isDisponible() {
		return this.stock > 0;
	}

	@Override
	public String toString() {
		String estado = isDisponible() ? "Disponible (" + stock + ")" : "Agotado";
		return "[" + codigo + "] " + titulo + " - " + autor + " (" + estado + ")";
	}
}

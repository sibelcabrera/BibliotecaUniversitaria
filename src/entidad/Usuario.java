package entidad;

import java.util.regex.Pattern;

/**
 * Clase modelo que representa al usuario que solicita un préstamo.
 * Todos los setters validan sus datos antes de asignarlos; el constructor
 * reutiliza los setters para no duplicar (ni saltarse) esas validaciones.
 */
public class Usuario {

	private static final int LONGITUD_MINIMA_CODIGO = 2;
	private static final int LONGITUD_MINIMA_NOMBRE = 2;
	private static final Pattern PATRON_CODIGO = Pattern.compile("^[0-9]+$");
	private static final Pattern PATRON_NOMBRE = Pattern.compile("^[\\p{L} .'-]+$");

	private String codigo;
	private String nombre;

	/**
	 * @throws IllegalArgumentException si alguno de los datos no cumple las
	 *         validaciones de su setter correspondiente (ver setCodigo,
	 *         setNombre).
	 */
	public Usuario(String codigo, String nombre) {
		setCodigo(codigo);
		setNombre(nombre);
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
			throw new IllegalArgumentException("El código del usuario no puede estar vacío.");
		}
		String valor = codigo.trim();
		if (valor.length() < LONGITUD_MINIMA_CODIGO) {
			throw new IllegalArgumentException(
					"El código del usuario debe tener al menos " + LONGITUD_MINIMA_CODIGO + " caracteres.");
		}
		if (!PATRON_CODIGO.matcher(valor).matches()) {
			throw new IllegalArgumentException(
					"El código del usuario debe ser numérico (solo dígitos).");
		}
		this.codigo = valor;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * @throws IllegalArgumentException si el nombre es nulo, está vacío,
	 *         no alcanza la longitud mínima o contiene caracteres no permitidos.
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
		}
		String valor = nombre.trim();
		if (valor.length() < LONGITUD_MINIMA_NOMBRE) {
			throw new IllegalArgumentException(
					"El nombre del usuario debe tener al menos " + LONGITUD_MINIMA_NOMBRE + " caracteres.");
		}
		if (!PATRON_NOMBRE.matcher(valor).matches()) {
			throw new IllegalArgumentException(
					"El nombre del usuario solo puede contener letras, espacios, apóstrofes, puntos y guiones.");
		}
		this.nombre = valor;
	}

	@Override
	public String toString() {
		return "[" + codigo + "] " + nombre;
	}

}

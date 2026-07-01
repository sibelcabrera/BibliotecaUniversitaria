package entidad;

public class Libro {

	private String codigo;
	private String titulo;
	private String autor;
	private boolean disponible;

	public Libro(String codigo, String titulo, String autor) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.disponible = true;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		String estado = disponible ? "Disponible" : "No disponible";
		return "[" + codigo + "] " + titulo + " - " + autor + " (" + estado + ")";
	}

}
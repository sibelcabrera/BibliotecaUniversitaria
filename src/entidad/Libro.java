package entidad;

import java.util.regex.Pattern;

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

    public Libro(String codigo, String titulo, String autor) {
        this(codigo, titulo, autor, 1);
    }

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
            throw new IllegalArgumentException("El código del libro debe tener al menos " + LONGITUD_MINIMA_CODIGO + " caracteres.");
        }

        if (!PATRON_CODIGO.matcher(valor).matches()) {
            throw new IllegalArgumentException("El código del libro debe ser numérico.");
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
            throw new IllegalArgumentException("El título del libro debe tener al menos " + LONGITUD_MINIMA_TITULO + " caracteres.");
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
            throw new IllegalArgumentException("El autor del libro debe tener al menos " + LONGITUD_MINIMA_AUTOR + " caracteres.");
        }

        if (!PATRON_AUTOR.matcher(valor).matches()) {
            throw new IllegalArgumentException("El autor del libro solo puede contener letras, espacios, puntos, apóstrofes y guiones.");
        }

        this.autor = valor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        this.stock = stock;
    }

    public void disminuirStock() {
        if (stock <= 0) {
            throw new IllegalStateException("No hay stock suficiente disponible para este libro.");
        }

        stock--;
    }

    public void incrementarStock() {
        stock++;
    }

    public boolean isDisponible() {
        return stock > 0;
    }

    @Override
    public String toString() {
        String estado = isDisponible() ? "Disponible (" + stock + ")" : "Agotado";
        return "[" + codigo + "] " + titulo + " - " + autor + " (" + estado + ")";
    }
}
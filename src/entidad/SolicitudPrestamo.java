package entidad;

/**
 * Representa una solicitud de préstamo: relaciona un usuario con un libro.
 * Es el objeto que se va a encolar en la cola de atención.
 *
 * Esta clase es inmutable (no expone setters), por lo que la validación
 * se concentra en el constructor.
 */
public class SolicitudPrestamo {

    private Usuario usuario;
    private Libro libro;

    /**
     * @throws IllegalArgumentException si el usuario o el libro son nulos.
     */
    public SolicitudPrestamo(Usuario usuario, Libro libro) {
        if (usuario == null) {
            throw new IllegalArgumentException("La solicitud de préstamo debe tener un usuario asociado.");
        }
        if (libro == null) {
            throw new IllegalArgumentException("La solicitud de préstamo debe tener un libro asociado.");
        }
        this.usuario = usuario;
        this.libro = libro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Libro getLibro() {
        return libro;
    }
    @Override
    public String toString() {
        return "SolicitudPrestamo{" +
                "usuario=" + usuario +
                ", libro=" + libro +
                '}';
    }
}

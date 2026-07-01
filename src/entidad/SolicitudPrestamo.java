package entidad;

/**
 * Representa una solicitud de préstamo: relaciona un usuario con un libro.
 * Es el objeto que se va a encolar en la cola de atención.
 *
 * TODO: Definir cómo se relaciona Usuario con Libro dentro de esta clase.
 */
public class SolicitudPrestamo {

    private Usuario usuario;
    private Libro libro;

    public SolicitudPrestamo(Usuario usuario, Libro libro) {
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

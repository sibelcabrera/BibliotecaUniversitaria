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

    public Libro registrarLibro(String codigo, String titulo, String autor) {
        return registrarLibro(codigo, titulo, autor, 1);
    }

    public Libro registrarLibro(String codigo, String titulo, String autor, int stock) {
        if (buscarLibroPorCodigo(codigo) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un libro registrado con el código \"" + codigo.trim() + "\"."
            );
        }

        Libro libro = new Libro(codigo, titulo, autor, stock);
        libros.add(libro);

        return libro;
    }
    
    public List<Libro> buscarLibrosPorTitulo(String textoTitulo) {
        List<Libro> resultados = new ArrayList<>();

        if (textoTitulo == null || textoTitulo.trim().isEmpty()) {
            return resultados;
        }

        String filtro = textoTitulo.trim().toLowerCase();

        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(filtro)) {
                resultados.add(libro);
            }
        }

        return resultados;
    }

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
    
    public SolicitudPrestamo solicitarPrestamo(String codigoUsuario, String nombreUsuario, String codigoLibro) {
        Libro libro = buscarLibroPorCodigo(codigoLibro);

        if (libro == null) {
            throw new IllegalArgumentException(
                    "No existe un libro registrado con el código \"" + codigoLibro.trim() + "\"."
            );
        }

        return solicitarPrestamo(codigoUsuario, nombreUsuario, libro);
    }

    public SolicitudPrestamo solicitarPrestamo(String codigoUsuario, String nombreUsuario, Libro libro) {
        Usuario usuario = new Usuario(codigoUsuario, nombreUsuario);

        if (libro == null) {
            throw new IllegalArgumentException("Debe seleccionar un libro para solicitar el préstamo.");
        }

        if (!libro.isDisponible()) {
            throw new IllegalArgumentException(
                    "El libro \"" + libro.getTitulo() + "\" no tiene stock disponible."
            );
        }

        SolicitudPrestamo solicitud = new SolicitudPrestamo(usuario, libro);
        colaSolicitudes.encolar(solicitud);

        return solicitud;
    }

    public SolicitudPrestamo entregarSiguienteSolicitud() {
        if (colaSolicitudes.estaVacia()) {
            throw new IllegalStateException("No hay solicitudes pendientes para entregar.");
        }

        SolicitudPrestamo solicitud = colaSolicitudes.desencolar();
        Libro libro = solicitud.getLibro();

        libro.disminuirStock();

        return solicitud;
    }

    public String listarLibrosComoTexto() {
        if (libros.isEmpty()) {
            return "No hay libros registrados.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== LIBROS REGISTRADOS ===\n\n");

        int posicion = 1;

        for (Libro libro : libros) {
            sb.append(posicion)
              .append(". ")
              .append(libro.toString())
              .append("\n");

            posicion++;
        }

        return sb.toString();
    }

    public String listarColaComoTexto() {
        return colaSolicitudes.listarComoTexto();
    }
}
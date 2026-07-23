package logica;

import entidad.Libro;
import entidad.SolicitudPrestamo;
import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {

    private List<Libro> catalogo;

    public GestorBiblioteca() {
        this.catalogo = new ArrayList<>();
    }

    /**
     * Permite agregar un libro especificado con su cantidad inicial de stock.
     */
    public void agregarLibro(String codigo, String titulo, String autor, int stock) {
        Libro libro = new Libro(codigo, titulo, autor, stock);
        catalogo.add(libro);
    }

    /**
     * Procesa la entrega de un libro al usuario, disminuyendo su stock.
     */
    public void entregarPrestamo(SolicitudPrestamo solicitud) {
        if (solicitud == null || solicitud.getLibro() == null) {
            throw new IllegalArgumentException("La solicitud o el libro son nulos.");
        }
        
        Libro libro = solicitud.getLibro();
        // Disminuye stock de forma segura
        libro.disminuirStock();
    }

    /**
     * Procesa la devolución del libro prestado, incrementando su stock.
     */
    public void devolverLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro a devolver no puede ser nulo.");
        }
        
        // Incrementa stock
        libro.incrementarStock();
    }

    public List<Libro> getCatalogo() {
        return catalogo;
    }
}

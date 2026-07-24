package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import entidad.Libro;

public class LibroTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnas = {"Código", "Título", "Autor", "Stock", "Estado"};
    private List<Libro> libros;

    public LibroTableModel(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public int getRowCount() {
        return libros.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Libro libro = libros.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return libro.getCodigo();
            case 1:
                return libro.getTitulo();
            case 2:
                return libro.getAutor();
            case 3:
                return libro.getStock();
            case 4:
                return libro.isDisponible() ? "Disponible" : "Agotado";
            default:
                return "";
        }
    }

    public Libro getLibroAt(int fila) {
        return libros.get(fila);
    }

    public void actualizarDatos() {
        fireTableDataChanged();
    }
}
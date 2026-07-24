package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EstadoCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        JLabel etiqueta = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        if (!isSelected) {
            String estado = value != null ? value.toString() : "";

            if (estado.equalsIgnoreCase("Disponible")) {
                etiqueta.setBackground(new Color(210, 245, 222));
                etiqueta.setForeground(new Color(20, 120, 60));
            } else if (estado.equalsIgnoreCase("Agotado")) {
                etiqueta.setBackground(new Color(255, 220, 220));
                etiqueta.setForeground(new Color(160, 40, 40));
            } else {
                etiqueta.setBackground(Color.WHITE);
                etiqueta.setForeground(Color.BLACK);
            }
        }

        return etiqueta;
    }
}
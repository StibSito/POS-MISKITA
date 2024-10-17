package model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class OrderTableCellRenderer extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// Llama al método de la superclase para obtener el componente básico
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Obtener el estado del pedido en la fila actual
		String estado = table.getValueAt(row, 4).toString(); // Asumiendo que el estado está en la columna 4

		// Cambiar el color de fondo según el estado
		Color backgroundColor;
		switch (estado) {
		case "PENDIENTE":
			backgroundColor = Color.LIGHT_GRAY;
			break;
		case "EN PREPARACION":
			backgroundColor = Color.YELLOW;
			break;
		case "LISTO":
			backgroundColor = Color.GREEN;
			break;
		case "CANCELADO":
			backgroundColor = Color.RED;
			break;
		default:
			backgroundColor = Color.WHITE;
			break;
		}

		// Si la fila está seleccionada, cambia el color de fondo
		if (isSelected) {
			backgroundColor = Color.BLUE; // Cambia a un color diferente si la fila está seleccionada
			setForeground(Color.WHITE); // Cambia el color del texto para que sea legible
		} else {
			setForeground(Color.BLACK); // Color de texto predeterminado
		}

		setBackground(backgroundColor); // Aplica el color de fondo

		return this;
	}

}

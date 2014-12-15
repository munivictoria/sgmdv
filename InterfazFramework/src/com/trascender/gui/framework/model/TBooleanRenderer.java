package com.trascender.gui.framework.model;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TBooleanRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	private static final long serialVersionUID = 7373107567991124514L;
	private JCheckBox checkBox = new JCheckBox();
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if ( value != null && (value.getClass().equals(Boolean.class) || value.getClass().equals(boolean.class)) ) {
			if (value.equals(Boolean.TRUE)) {
				this.checkBox.setSelected(true);
			}
			else {
				this.checkBox.setSelected(false);
			}
			return this.checkBox;
		}
		else {
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

	public JCheckBox getCheckBox() {
		return checkBox;
	}
	
}

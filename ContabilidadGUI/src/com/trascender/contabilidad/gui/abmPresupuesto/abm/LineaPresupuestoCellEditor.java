package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

import com.trascender.gui.framework.util.Validador;

public class LineaPresupuestoCellEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = 4515164444949294410L;
	
	private JTextField textField;
	
	public LineaPresupuestoCellEditor(JTextField textField) {
		super(textField);
		this.textField = textField;
		super.setClickCountToStart(1);
	}
	
	@Override
	public boolean stopCellEditing() {
		super.stopCellEditing();
		return (Validador.isValidFloat(this.textField.getText()));
	}
	
	@Override
	public Object getCellEditorValue() {
		super.getCellEditorValue();
		return this.textField.getText();
	}

	
}

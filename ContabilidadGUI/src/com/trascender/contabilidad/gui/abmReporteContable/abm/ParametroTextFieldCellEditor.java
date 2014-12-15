package com.trascender.contabilidad.gui.abmReporteContable.abm;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

import com.trascender.gui.framework.util.Validador;

public class ParametroTextFieldCellEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = -43790236687516175L;
	
	private JTextField textField;
	
	public ParametroTextFieldCellEditor(JTextField textField) {
		super(textField);
		this.textField = textField;
		super.setClickCountToStart(1);
	}
	
	@Override
	public boolean stopCellEditing() {
		super.stopCellEditing();
		return true;//(Validador.isValidFloat(this.textField.getText()));
	}
	
	@Override
	public Object getCellEditorValue() {
		super.getCellEditorValue();
		return this.textField.getText();
	}

	
}
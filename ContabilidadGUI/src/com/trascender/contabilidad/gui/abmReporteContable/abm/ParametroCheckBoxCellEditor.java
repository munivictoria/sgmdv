package com.trascender.contabilidad.gui.abmReporteContable.abm;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;

public class ParametroCheckBoxCellEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = 8598067724049739086L;
	
	private JCheckBox checkBox;
	
	public ParametroCheckBoxCellEditor(JCheckBox checkBox) {
		super(checkBox);
		this.checkBox = checkBox;
		super.setClickCountToStart(1);
	}
	
	@Override
	public boolean stopCellEditing() {
		super.stopCellEditing();
		return true;
	}
	
	@Override
	public Object getCellEditorValue() {
		super.getCellEditorValue();
		return this.checkBox.isSelected();
	}

	
}
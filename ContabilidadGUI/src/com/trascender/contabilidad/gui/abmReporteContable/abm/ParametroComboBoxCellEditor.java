package com.trascender.contabilidad.gui.abmReporteContable.abm;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import com.trascender.contabilidad.recurso.persistent.ParametroReporteContable;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;

public class ParametroComboBoxCellEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = -5163529751279385852L;
	
	private JComboBox comboBox;
	
	public ParametroComboBoxCellEditor(JComboBox comboBox) {
		super(comboBox);
		this.comboBox = comboBox;
		comboBox.setModel(new TDefaultComboBoxModel(ParametroReporteContable.Tipo.values()));
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
		return this.comboBox.getSelectedItem();
	}

	
}

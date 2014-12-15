package com.trascender.gui.framework.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class TDateRenderer extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 5513255615940373091L;
	DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void setValue(Object value) {
		if (value!=null){
			this.setText(formato.format(value));	
		}
		else{
			this.setText("-");
		}
	}
	
}

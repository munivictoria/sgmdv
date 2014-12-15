package com.trascender.gui.framework.abmStandard;

import com.trascender.gui.framework.component.TTable;
import com.trascender.gui.framework.component.TTableScrollPane;

public class PnlTabla extends TTableScrollPane {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private TTable tblDatos;

	public PnlTabla() {
		this.init();
	}

	private void init() {
		this.tblDatos = new TTable();
		this.tblDatos.setShowHorizontalLines(false);
		this.setViewportView(this.tblDatos);
	}

	public TTable getTblDatos() {
		return tblDatos;
	}

}


package com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable;

import com.trascender.contabilidad.gui.abmTipoBalance.AdminTipoBalance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;

public class PnlSeleccionPlantillaBalance extends PnlSeleccion{

	private static final long serialVersionUID = 345552821247481011L;
	private TipoBalance plantillaBalance;
	
	public PnlSeleccionPlantillaBalance(ParametrosReporteContable pVentana) {
		super(pVentana);
	}
	
	@Override
	protected Long getIdObjetoSeleccionado() {
		return this.plantillaBalance.getIdTipoBalance();
	}
	
	@Override
	protected void setObjetoSeleccionado(Object pObjeto) {
		this.plantillaBalance = (TipoBalance) pObjeto;
	}
	
	@Override
	protected void seleccionarObjeto() throws Exception {
		AdminTipoBalance adminTipoBalance = new AdminTipoBalance(this.getVentana());
		TipoBalance locTipoBalance = adminTipoBalance.openSelect();
		if (locTipoBalance != null) {
			this.plantillaBalance = locTipoBalance;
			this.getTextField().setText(locTipoBalance.getNombre());
		}
	}
}

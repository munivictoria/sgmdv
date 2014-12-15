package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class AsociacionRefinanciacionTableModel extends TAbstractTableModel<AsociacionRefinanciacion> {

	private static final long serialVersionUID = 1L;

	public AsociacionRefinanciacionTableModel() throws Exception {
		super(AsociacionRefinanciacion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);	
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Período",AsociacionRefinanciacion.class.getDeclaredField("periodo"), Periodo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}

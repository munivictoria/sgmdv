package com.trascender.contabilidad.gui.abmRetencion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaRetencion;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaRetencionTableModel extends TAbstractTableModel<LineaRetencion> {

	private static final long serialVersionUID = -5948403870587221239L;

	public LineaRetencionTableModel() throws Exception {
		super(LineaRetencion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Parámetro de Retención", LineaRetencion.class.getDeclaredField("parametroRetencion")));
			locListaColumnas.add(new TColumnField("Monto", LineaRetencion.class.getDeclaredField("importe"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

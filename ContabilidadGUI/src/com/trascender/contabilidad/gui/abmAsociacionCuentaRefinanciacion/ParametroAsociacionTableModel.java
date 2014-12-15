package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ParametroAsociacionTableModel extends TAbstractTableModel<ParametroAsociacion> {

	private static final long serialVersionUID = -5948403870587221239L;

	public ParametroAsociacionTableModel() throws Exception {
		super(ParametroAsociacion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nro. Parametro", ParametroAsociacion.class.getDeclaredField("idParametroAsociacion")));
			locListaColumnas.add(new TColumnField("Cuenta", ParametroAsociacion.class.getDeclaredField("cuenta")));
			locListaColumnas.add(new TColumnField("Porcentaje", ParametroAsociacion.class.getDeclaredField("porcentaje"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
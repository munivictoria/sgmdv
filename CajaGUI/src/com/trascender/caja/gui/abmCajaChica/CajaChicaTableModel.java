package com.trascender.caja.gui.abmCajaChica;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class CajaChicaTableModel extends TAbstractTableModel<CajaChica> {

	private static final long serialVersionUID = 5510223468104693421L;
	
	public CajaChicaTableModel() throws Exception {
		super(CajaChica.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre",CajaChica.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Importe Reposicion",CajaChica.class.getDeclaredField("importeReposicion"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}

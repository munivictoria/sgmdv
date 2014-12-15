package com.trascender.caja.gui.abmConceptoMovimientoCajaChica;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica.Tipo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ConceptoMovimientoCajaChicaTableModel extends TAbstractTableModel<ConceptoMovimientoCajaChica> {

	private static final long serialVersionUID = -6764652331412305463L;

	public ConceptoMovimientoCajaChicaTableModel() throws Exception {
		super(ConceptoMovimientoCajaChica.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre",ConceptoMovimientoCajaChica.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Descipcion",ConceptoMovimientoCajaChica.class.getDeclaredField("descripcion")));
			locListaColumnas.add(new TColumnField("Tipo", ConceptoMovimientoCajaChica.class.getDeclaredField("tipo"),Tipo.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}

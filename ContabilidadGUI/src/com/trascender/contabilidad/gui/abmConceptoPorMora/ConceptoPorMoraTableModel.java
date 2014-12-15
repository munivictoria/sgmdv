package com.trascender.contabilidad.gui.abmConceptoPorMora;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class ConceptoPorMoraTableModel extends TAbstractTableModel<ConceptoPorMora> {

	private static final long serialVersionUID = -2156839737293529785L;

	public ConceptoPorMoraTableModel() throws Exception {
		super(ConceptoPorMora.class);
		List<TColumn> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);	
	}

	private List<TColumn> inicializarColumnas() throws Exception {
		List<TColumn> locListaColumnas = new ArrayList<TColumn>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", ConceptoPorMora.class.getDeclaredField("nombre")));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}

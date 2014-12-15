package com.trascender.contabilidad.gui.abmConceptoIngresoVario;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ConceptoSelladoAdministrativoTableModel extends TAbstractTableModel<ConceptoIngresoVario>{

	private static final long serialVersionUID = 3031390052023253211L;
	
	public ConceptoSelladoAdministrativoTableModel() throws Exception {
		super(ConceptoIngresoVario.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Nombre", ConceptoIngresoVario.class.getDeclaredField("nombre")));
			listaColumnas.add(new TColumnField("Descripción", ConceptoIngresoVario.class.getDeclaredField("descripcion")));
			listaColumnas.add(new TColumnField("Valor por Defecto", ConceptoIngresoVario.class.getDeclaredField("valorPorDefecto"), Float.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}

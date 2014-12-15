package com.trascender.contabilidad.gui.abmDigestoMunicipal;

import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class DigestoMunicipalTableModel extends TAbstractTableModel<DigestoMunicipal> {

	private static final long serialVersionUID = 4128566153221499787L;

	public DigestoMunicipalTableModel() throws Exception {
		super(DigestoMunicipal.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Descripción", DigestoMunicipal.class.getDeclaredField("descripcion")));
			locListaColumnas.add(new TColumnField("Tipo",   DigestoMunicipal.class.getDeclaredField("tipo"), DigestoMunicipal.Tipo.class));
			return locListaColumnas;	
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}

package com.trascender.contabilidad.gui.abmPersonaJuridica;


import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class PersonaJuridicaTableModel extends TAbstractTableModel<PersonaJuridica> {

	private static final long serialVersionUID = 5513255615940373091L;
	
	public PersonaJuridicaTableModel() throws Exception {
		super(PersonaJuridica.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("CUIT", Persona.class.getDeclaredField("cuim")));
			locListaColumnas.add(new TColumnField("Razón Social", PersonaJuridica.class.getDeclaredField("razonSocial")));
			locListaColumnas.add(new TColumnField("Titular", PersonaJuridica.class.getDeclaredField("titular"), PersonaFisica.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

	

}

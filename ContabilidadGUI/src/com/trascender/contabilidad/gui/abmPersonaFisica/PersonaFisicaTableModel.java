package com.trascender.contabilidad.gui.abmPersonaFisica;

import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class PersonaFisicaTableModel extends TAbstractTableModel<PersonaFisica> {
	
	private static final long serialVersionUID = 8286362882702510129L;
	
	public PersonaFisicaTableModel() throws Exception {
		super(PersonaFisica.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("CUIL",           Persona.class.getDeclaredField("cuim")));
			locListaColumnas.add(new TColumnField("Tipo Documento", PersonaFisica.class.getDeclaredField("tipoDocumento"), TipoDocumento.class));
			locListaColumnas.add(new TColumnField("Nº Documento",   PersonaFisica.class.getDeclaredField("numeroDocumento")));
			locListaColumnas.add(new TColumnField("Apellido",       PersonaFisica.class.getDeclaredField("apellido")));
			locListaColumnas.add(new TColumnField("Nombre",         PersonaFisica.class.getDeclaredField("nombre")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

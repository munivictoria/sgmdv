package com.trascender.contabilidad.gui.abmDocumentoRefinanciacion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion.EstadoRefinanciacion;

public class DocumentoRefinanciacionTableModel extends TAbstractTableModel<DocumentoRefinanciacion>{

	private static final long serialVersionUID = 8756955426894022138L;

	public DocumentoRefinanciacionTableModel() throws Exception {
		super(DocumentoRefinanciacion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Número de Refinanciación",DocumentoRefinanciacion.class.getDeclaredField("numeroRefinanciacion"), Integer.class));
			//locListaColumnas.add(new TColumn("Contribuyente",Obligacion.class.getDeclaredField("persona"), Persona.class));
			locListaColumnas.add(new TColumnField("Mes de Inicio",DocumentoRefinanciacion.class.getDeclaredField("mesInicioRefinanciacion"), Integer.class));
			locListaColumnas.add(new TColumnField("Año de Inicio",DocumentoRefinanciacion.class.getDeclaredField("anioInicioRefinanciacion"), Integer.class));
//			locListaColumnas.add(new TColumn("Total A Pagar",DocumentoRefinanciacion.class.getDeclaredField("numeroRefinanciacion"), Integer.class));
			locListaColumnas.add(new TColumnField("Estado", DocumentoRefinanciacion.class.getDeclaredField("estadoRefinanciacion"), EstadoRefinanciacion.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}

package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class RefinanciacionTableModel extends TAbstractTableModel<DocumentoRefinanciacion>{

	private static final long serialVersionUID = -6103960532797856220L;

	public RefinanciacionTableModel() throws Exception {
		super(DocumentoRefinanciacion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Nro. Refinanciación", DocumentoRefinanciacion.class.getDeclaredField("numeroRefinanciacion"), Integer.class));
			listaColumnas.add(new TColumnField("Mes de Inicio", DocumentoRefinanciacion.class.getDeclaredField("mesInicioRefinanciacion"), Integer.class));
			listaColumnas.add(new TColumnField("Año de Inicio", DocumentoRefinanciacion.class.getDeclaredField("anioInicioRefinanciacion"), Integer.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

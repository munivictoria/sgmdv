package com.trascender.contabilidad.gui.abmLibroBanco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco.Tipo;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineasLibroBancoTableModel extends TAbstractTableModel<LineaLibroBanco> {

	private static final long serialVersionUID = 653934218793843155L;
	
	public LineasLibroBancoTableModel() throws Exception {
		super(LineaLibroBanco.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Fecha", LineaLibroBanco.class.getDeclaredField("fecha"), Date.class));
			locListaColumnas.add(new TColumnField("Importe", LineaLibroBanco.class.getDeclaredField("importe"), Double.class));
			locListaColumnas.add(new TColumnField("Conciliado", LineaLibroBanco.class.getDeclaredField("conciliado"), Boolean.class));
			locListaColumnas.add(new TColumnField("Tipo", LineaLibroBanco.class.getDeclaredField("tipo"), Tipo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

	
	/**
	 * Agrega la lista de lineas del Libro banco al modelo
	 * @param pLineasLibroBanco
	 */
	public void setListaObjetos(Set<LineaLibroBanco> pLineasLibroBanco) {
		this.addRows(pLineasLibroBanco);
	}


}

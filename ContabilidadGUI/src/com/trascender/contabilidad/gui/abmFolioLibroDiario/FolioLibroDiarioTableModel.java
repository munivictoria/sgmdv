package com.trascender.contabilidad.gui.abmFolioLibroDiario;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class FolioLibroDiarioTableModel extends TAbstractTableModel<FolioLibroDiario> {
	
	private static final long serialVersionUID = 3807446378601600275L;	
	
	public FolioLibroDiarioTableModel() throws Exception {
		super(FolioLibroDiario.class);
		List<TColumnField> locListaColumnas = this.InicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> InicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField ("Número", FolioLibroDiario.class.getDeclaredField("numero")));
			locListaColumnas.add(new TColumnField ("Libro Diario", FolioLibroDiario.class.getDeclaredField("libroDiario"), LibroDiario.class));
			return locListaColumnas;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}

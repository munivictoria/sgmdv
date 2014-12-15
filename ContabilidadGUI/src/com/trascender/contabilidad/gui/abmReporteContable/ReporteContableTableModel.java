package com.trascender.contabilidad.gui.abmReporteContable;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ReporteContableTableModel extends TAbstractTableModel<ReporteContable>{

	private static final long serialVersionUID = 6584370620290398176L;
	
	public ReporteContableTableModel() throws Exception {
		super(ReporteContable.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Nombre", ReporteContable.class.getDeclaredField("nombre")));
			listaColumnas.add(new TColumnField("Nombre Archivo Jasper", ReporteContable.class.getDeclaredField("nombreArchivoJasper")));
			listaColumnas.add(new TColumnField("Estado", ReporteContable.class.getDeclaredField("estado")));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}

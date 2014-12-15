package com.trascender.contabilidad.gui.abmLineaMayor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaMayorTableModel extends TAbstractTableModel<LineaMayor> {
	
	private static final long serialVersionUID = -5757272402831426106L;

	public LineaMayorTableModel() throws Exception {
		super(LineaMayor.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Debe", LineaMayor.class.getDeclaredField("importeDebe"), Double.class));
			locListaColumnas.add(new TColumnField("Haber", LineaMayor.class.getDeclaredField("importeHaber"), Double.class));
			locListaColumnas.add(new TColumnField("Saldo", LineaMayor.class.getDeclaredField("saldo"), Double.class));
			locListaColumnas.add(new TColumnField("Fecha de Generación", LineaMayor.class.getDeclaredField("fechaGeneracion"), Date.class));
			locListaColumnas.add(new TColumnField("Folio Libro Diario", LineaMayor.class.getDeclaredField("folioLibroDiario"), FolioLibroDiario.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

	
}

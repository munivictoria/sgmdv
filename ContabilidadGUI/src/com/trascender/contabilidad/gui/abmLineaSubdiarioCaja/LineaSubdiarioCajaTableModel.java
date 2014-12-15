package com.trascender.contabilidad.gui.abmLineaSubdiarioCaja;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaSubdiarioCajaTableModel extends TAbstractTableModel<LineaSubdiarioCaja> {

	private static final long serialVersionUID = 8717084235257853005L;
	
	public LineaSubdiarioCajaTableModel() throws Exception {
		super(LineaSubdiarioCaja.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Día", LineaSubdiarioCaja.class.getDeclaredField("dia"), Integer.class));
			locListaColumnas.add(new TColumnField("Cuenta", LineaSubdiarioCaja.class.getDeclaredField("cuenta"), Cuenta.class));
			locListaColumnas.add(new TColumnField("Importe", LineaSubdiarioCaja.class.getDeclaredField("importe"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

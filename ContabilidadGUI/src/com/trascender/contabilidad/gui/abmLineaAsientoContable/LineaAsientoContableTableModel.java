package com.trascender.contabilidad.gui.abmLineaAsientoContable;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaAsientoContableTableModel extends TAbstractTableModel<LineaAsientoContable> {

	private static final long serialVersionUID = -3393359720630883844L;
	
	public LineaAsientoContableTableModel() throws Exception {
		super(LineaAsientoContable.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cuenta", LineaAsientoContable.class.getDeclaredField("cuenta"), Cuenta.class));
			locListaColumnas.add(new TColumnField("Debe",   LineaAsientoContable.class.getDeclaredField("importeDebe"), Double.class));
			locListaColumnas.add(new TColumnField("Haber",  LineaAsientoContable.class.getDeclaredField("importeHaber"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

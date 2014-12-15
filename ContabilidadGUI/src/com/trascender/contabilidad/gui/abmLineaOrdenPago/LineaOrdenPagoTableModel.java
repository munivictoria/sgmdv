package com.trascender.contabilidad.gui.abmLineaOrdenPago;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaOrdenPagoTableModel extends TAbstractTableModel<LineaOrdenPago> {

	
	private static final long serialVersionUID = 5513255615940373091L;
	
	public LineaOrdenPagoTableModel() throws Exception {
		super(LineaOrdenPago.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Factura", LineaOrdenPago.class.getDeclaredField("factura")));
			locListaColumnas.add(new TColumnField("Importe", LineaOrdenPago.class.getDeclaredField("importe"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

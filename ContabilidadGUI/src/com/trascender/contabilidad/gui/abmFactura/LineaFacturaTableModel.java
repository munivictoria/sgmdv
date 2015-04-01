package com.trascender.contabilidad.gui.abmFactura;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;



public class LineaFacturaTableModel extends TAbstractTableModel<LineaFactura>{

	private static final long serialVersionUID = -2055991792979641628L;

	public LineaFacturaTableModel() throws Exception {
		super(LineaFactura.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Bien Provisto", LineaFactura.class.getDeclaredField("bienProvisto"), BienProvisto.class));
			locListaColumnas.add(new TColumnField("Cantidad", LineaFactura.class.getDeclaredField("cantidad"), Double.class));
			locListaColumnas.add(new TColumnField("Monto Unitario", LineaFactura.class.getDeclaredField("montoUnitario"), Double.class));
			locListaColumnas.add(new TColumnField("Total", LineaFactura.class.getDeclaredField("total"), Double.class));
			locListaColumnas.add(new TColumnField("Factura", LineaFactura.class.getDeclaredField("factura"), Factura.class));
			locListaColumnas.add(new TColumnField("Cuenta", LineaFactura.class.getDeclaredField("cuenta"), CuentaRfr.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

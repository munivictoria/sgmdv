package com.trascender.contabilidad.gui.abmOrdenPago;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class OrdenPagoTableModel extends TAbstractTableModel<OrdenPago> {

	

	private static final long serialVersionUID = 5513255615940373091L;

	public OrdenPagoTableModel() throws Exception {
		super(OrdenPago.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nº", DocumentoEgreso.class.getDeclaredField("numero"), Integer.class));
			locListaColumnas.add(new TColumnField("Fecha Emisión", DocumentoEgreso.class.getDeclaredField("fechaEmision"), Date.class));
			locListaColumnas.add(new TColumnField("Fecha Pago", DocumentoEgreso.class.getDeclaredField("fechaPago"), Date.class));
			locListaColumnas.add(new TColumnField("Importe", DocumentoEgreso.class.getDeclaredField("importe"), Double.class));
			locListaColumnas.add(new TColumnField("Proveedor", OrdenPago.class.getDeclaredField("proveedor"), Proveedor.class));
			
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
	
}

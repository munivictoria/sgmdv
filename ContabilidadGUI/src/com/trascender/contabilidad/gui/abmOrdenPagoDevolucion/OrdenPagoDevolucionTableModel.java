package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class OrdenPagoDevolucionTableModel extends TAbstractTableModel<OrdenPagoDevolucion>{

	private static final long serialVersionUID = -6465193094698535845L;

	public OrdenPagoDevolucionTableModel() throws Exception {
		super(OrdenPagoDevolucion.class);
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
			locListaColumnas.add(new TColumnField("Persona", OrdenPagoDevolucion.class.getDeclaredField("persona"), Persona.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

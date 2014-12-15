package com.trascender.contabilidad.gui.abmRetencion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class RetencionTableModel extends TAbstractTableModel<ComprobanteRetencion>{

	private static final long serialVersionUID = -6103960532797856220L;

	public RetencionTableModel() throws Exception {
		super(ComprobanteRetencion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Orden de Pago", ComprobanteRetencion.class.getDeclaredField("ordenPago"), OrdenPago.class));
			listaColumnas.add(new TColumnField("Monto", ComprobanteRetencion.class.getDeclaredField("importe"), Double.class));
			listaColumnas.add(new TColumnField("Período", ComprobanteRetencion.class.getDeclaredField("periodo"), Periodo.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}

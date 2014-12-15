package com.trascender.contabilidad.gui.abmFactura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.compras.recurso.persistent.suministros.Factura.TipoFactura;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class FacturaTableModel extends TAbstractTableModel<Factura> {

	private static final long serialVersionUID = 5633651295938750409L;
	
	public FacturaTableModel() throws Exception {
		super(Factura.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Tipo Factura", Factura.class.getDeclaredField("tipoFactura"), TipoFactura.class));
			locListaColumnas.add(new TColumnField("Estado", Factura.class.getDeclaredField("estado"), Estado.class));
			locListaColumnas.add(new TColumnField("Fecha Emisión", Factura.class.getDeclaredField("fechaEmision"), Date.class));
			locListaColumnas.add(new TColumnField("Proveedor", Factura.class.getDeclaredField("proveedor"), Proveedor.class));
			locListaColumnas.add(new TColumnField("Total", Factura.class.getDeclaredField("total"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}

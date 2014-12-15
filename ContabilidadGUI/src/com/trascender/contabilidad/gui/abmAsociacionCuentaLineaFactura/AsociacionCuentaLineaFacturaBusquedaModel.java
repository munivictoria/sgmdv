package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class AsociacionCuentaLineaFacturaBusquedaModel extends TAbstractBusquedaModel<LineaFactura> {

	private Factura factura;
	
	@Override
	public List<LineaFactura> buscar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reiniciar() {
		// TODO Auto-generated method stub
		
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	

}

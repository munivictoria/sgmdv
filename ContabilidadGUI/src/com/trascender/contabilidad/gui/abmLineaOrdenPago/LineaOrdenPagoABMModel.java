package com.trascender.contabilidad.gui.abmLineaOrdenPago;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class LineaOrdenPagoABMModel extends TAbstractABMModel<LineaOrdenPago> {

	private Factura factura;
		@Override
	public void agregar() throws Exception {
		
	}

	@Override
	public void eliminar() throws Exception {
		
	}

	@Override
	public void modificar() throws Exception {
		
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double importe) {
		this.objetoABM.setImporte(importe);
	}
	
	public void setId(Long id) {
		this.objetoABM.setIdLineaOrdenPago(id);
	}
	
	public Long getId() {
		return this.objetoABM.getIdLineaOrdenPago();
	}

	public Factura getFactura() {
		return this.objetoABM.getFactura();
	}

	public void setFactura(Factura factura) {
		this.objetoABM.setFactura(factura);
	}
	
}

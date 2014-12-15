package com.trascender.contabilidad.recurso.transients;

import com.trascender.compras.recurso.persistent.suministros.Factura;

public class MovCuentaCorrienteIngreso extends MovCuentaCorriente{
	private static final long serialVersionUID = -2100102130897927272L;
	
	private Factura factura;

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
}

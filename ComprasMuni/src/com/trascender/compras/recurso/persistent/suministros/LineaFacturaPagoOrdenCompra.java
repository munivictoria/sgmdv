package com.trascender.compras.recurso.persistent.suministros;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="LIN_FAC_PAGO_ORD_COM")
public class LineaFacturaPagoOrdenCompra extends LineaFactura{

	private static final long serialVersionUID = 115421682462123713L;
	
	@ManyToOne
	@JoinColumn(name = "ID_PAGO_ORDEN_COMPRA", unique=true)
	private PagoOrdenCompra pagoOrdenCompra;
	
	public PagoOrdenCompra getPagoOrdenCompra() {
		return pagoOrdenCompra;
	}

	public void setPagoOrdenCompra(PagoOrdenCompra pagoOrdenCompra) {
		this.pagoOrdenCompra = pagoOrdenCompra;
	}

	@Override
	public String getNombre() {
		return this.pagoOrdenCompra.getNombre();
	}
}

package com.trascender.compras.recurso.persistent.suministros;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="LIN_FAC_LIN_ORD_COM")
public class LineaFacturaLineaOrdenCompra extends LineaFactura{

	private static final long serialVersionUID = -6003182540435381092L;
	
	@ManyToOne
	@JoinColumn(name = "ID_LINEA_ORDEN_COMPRA", unique=true)
	private LineaOrdenCompra lineaOrdenCompra;
	
	public LineaOrdenCompra getLineaOrdenCompra() {
		return lineaOrdenCompra;
	}
	public void setLineaOrdenCompra(LineaOrdenCompra lineaOrdenCompra) {
		this.lineaOrdenCompra = lineaOrdenCompra;
	}
	
	@Override
	public String getNombre() {
		return this.lineaOrdenCompra.getBien().getNombre();
	}
}

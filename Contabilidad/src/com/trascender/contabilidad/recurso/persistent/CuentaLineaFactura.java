package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.compras.recurso.persistent.suministros.LineaFactura;

@Entity
@Table(name = "CUENTA_LINEA_FACTURA")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaLineaFactura extends AsociacionCuenta{

	public static final long serialVersionUID = 8289215649926885012L;
	
	@ManyToOne
	@JoinColumn(name = "ID_LINEA_FACTURA")
	private LineaFactura lineaFactura;

	public LineaFactura getLineaFactura() {
		return lineaFactura;
	}
	
	public void setLineaFactura(LineaFactura pLineaFactura) {
		this.lineaFactura = pLineaFactura;
	}
	
	
	
	
}

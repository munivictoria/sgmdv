package com.trascender.compras.recurso.filtros;

import java.util.Date;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroLiquidacionCompra extends FiltroAbstracto<LiquidacionCompra>{
	
	private static final long serialVersionUID = 3514641750589152886L;
	
	public FiltroLiquidacionCompra() {
	}

	public FiltroLiquidacionCompra(String numero, Date fecha) {
		super();
		this.numero = numero;
		this.fecha = fecha;
	}

	private String numero;
	private Date fecha;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
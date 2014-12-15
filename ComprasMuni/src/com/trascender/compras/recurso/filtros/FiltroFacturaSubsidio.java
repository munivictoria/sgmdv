package com.trascender.compras.recurso.filtros;

import java.util.Date;

import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFacturaSubsidio extends FiltroAbstracto<FacturaSubsidio>{
	
	private static final long serialVersionUID = 6410096975271333053L;
	private Proveedor proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private FacturaProveedor.Estado estado;
	private DigestoMunicipal digestoMuncipal;
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public FacturaProveedor.Estado getEstado() {
		return estado;
	}
	public void setEstado(FacturaProveedor.Estado estado) {
		this.estado = estado;
	}
	public DigestoMunicipal getDigestoMuncipal() {
		return digestoMuncipal;
	}
	public void setDigestoMuncipal(DigestoMunicipal digestoMuncipal) {
		this.digestoMuncipal = digestoMuncipal;
	}

	
}

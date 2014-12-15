package com.trascender.compras.recurso.filtros;

import java.util.Date;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFacturaServicio extends FiltroAbstracto<FacturaServicio> {

	private static final long serialVersionUID = 6529508496312708673L;
	
	private Proveedor proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private FacturaProveedor.Estado estado;
	private Bien bien;
	
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
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}

	
}

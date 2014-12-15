package com.trascender.compras.recurso.filtros;

import java.util.Date;

import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFacturaContrato extends FiltroAbstracto<FacturaContrato> {

	private static final long serialVersionUID = -1005158002200018751L;

	public FiltroFacturaContrato() {
	}

	public FiltroFacturaContrato(Proveedor proveedor, Date fechaDesde, Date fechaHasta, Estado estado, Contrato contrato) {
		this.proveedor = proveedor;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.estado = estado;
		this.contrato = contrato;
	}

	private Proveedor proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private FacturaContrato.Estado estado;
	private Contrato contrato;

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

	public FacturaContrato.Estado getEstado() {
		return estado;
	}

	public void setEstado(FacturaContrato.Estado estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}

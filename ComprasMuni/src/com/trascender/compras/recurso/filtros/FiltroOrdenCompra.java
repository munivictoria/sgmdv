package com.trascender.compras.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra.Estado;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroOrdenCompra extends FiltroAbstracto<OrdenCompra> {

	public FiltroOrdenCompra() {
	}

	public FiltroOrdenCompra(Secretaria secretaria, Area area, Estado estado, Date fechaDesde, Date fechaHasta, Proveedor proveedor, Integer numero, Bien bien) {
		this.estado = estado;
		this.numero = numero;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.proveedor = proveedor;
		this.bien = bien;
	}

	private static final long serialVersionUID = -994862004260003976L;

	private OrdenCompra.Estado estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private Proveedor proveedor;
	private Integer numero;
	private Secretaria secretaria;
	private Area area;
	private Bien bien;
	private List<Long> listaIdBienes;
		
	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public OrdenCompra.Estado getEstado() {
		return estado;
	}

	public void setEstado(OrdenCompra.Estado estado) {
		this.estado = estado;
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Long> getListaIdBienes() {
		return listaIdBienes;
	}

	public void setListaIdBienes(List<Long> listaIdBienes) {
		this.listaIdBienes = listaIdBienes;
	}
	
}

package com.trascender.compras.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFacturaProveedor extends FiltroAbstracto<FacturaProveedor> {
	public FiltroFacturaProveedor() {
	}

	public FiltroFacturaProveedor(Proveedor proveedor, Date fechaDesde, Date fechaHasta, Estado estado, Secretaria secretaria, Area area, Bien bien,String numero, List<AtributoDinamico<?>> pListaAtributoDinamico) {
		this.proveedor = proveedor;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.estado = estado;
		this.secretaria = secretaria;
		this.area = area;
		this.bien = bien;
		this.numero = numero;
		listaAtributoDinamico = pListaAtributoDinamico;
	}

	private static final long serialVersionUID = -867110151944425562L;

	private Proveedor proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private FacturaProveedor.Estado estado;
	private Secretaria secretaria;
	private Area area;
	private Bien bien;
	private String numero;
	private List<AtributoDinamico<?>> listaAtributoDinamico;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

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
	
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}
	
	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> pListaAtributoDinamico) {
		listaAtributoDinamico = pListaAtributoDinamico;
	}
}

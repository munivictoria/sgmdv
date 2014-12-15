package com.trascender.contabilidad.recurso.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;

@Entity
@Table(name = "ORDEN_PAGO")
@PrimaryKeyJoinColumn(name = "ID_DOCUMENTO_EGRESO")
public class OrdenPago extends DocumentoEgreso{

	public static final long serialVersionUID = -321950396277002684L;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private Proveedor proveedor;
	
	@ManyToOne
	@JoinColumn(name = "ID_COMPROBANTE_RETENCION")
	private ComprobanteRetencion comprobanteRetencion;
	
	@OneToMany(mappedBy = "ordenPago", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaOrdenPago> lineasOrdenPago = new HashSet<LineaOrdenPago>();
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public Set<LineaOrdenPago> getLineasOrdenPago() {
		return lineasOrdenPago;
	}
	public void setLineasOrdenPago(Set<LineaOrdenPago> lineasOrdenPago) {
		this.lineasOrdenPago = lineasOrdenPago;
	}
	
	public ComprobanteRetencion getComprobanteRetencion() {
		return comprobanteRetencion;
	}
	
	public void setComprobanteRetencion(ComprobanteRetencion ComprobanteRetencion) {
		this.comprobanteRetencion = ComprobanteRetencion;
	}
}

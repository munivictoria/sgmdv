package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
@Table(name = "FACTURA_PROVEEDOR")
@PrimaryKeyJoinColumn(name = "ID_FACTURA")
public class FacturaProveedor extends Factura implements Serializable{
	
	public static final long serialVersionUID = -3386640593702671352L;
	
	@OneToMany(mappedBy = "factura", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<PagoOrdenCompra> listaPagosOrdenCompra = new ArrayList<PagoOrdenCompra>();

	public List<PagoOrdenCompra> getListaPagosOrdenCompra() {
		return listaPagosOrdenCompra;
	}

	public void setListaPagosOrdenCompra(List<PagoOrdenCompra> listaPagosOrdenCompra) {
		this.listaPagosOrdenCompra = listaPagosOrdenCompra;
	}
	
	public void addPagoOrdenCompra(PagoOrdenCompra pPago){
		pPago.setFactura(this);
		this.listaPagosOrdenCompra.add(pPago);
	}
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdFactura());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
//	@OneToMany(mappedBy = "factura", cascade = CascadeType.MERGE)
//	public Set<OrdenCompra> listaOrdenesDeCompra = new HashSet<OrdenCompra>();
//	
//	public Set<OrdenCompra> getListaOrdenesDeCompra() {
//		return listaOrdenesDeCompra;
//	}
//	public void setListaOrdenesDeCompra(Set<OrdenCompra> listaOrdenesDeCompra) {
//		this.listaOrdenesDeCompra = listaOrdenesDeCompra;
//	}
	
//	// ********************************************************************************************************************************/
//		// AUDITORIA
//
//		@Transient
//		private long llaveUsuarioAuditoria;
//		@Transient
//		private String comentarioAuditoria;
//
//		@OrderBy(value = "fecha")
//		@Where(clause = "id_recurso = " + serialVersionUID)
//		@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
//		private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
//
//		public List<LogAuditoria> getListaLogsAuditoria() {
//			return listaLogsAuditoria;
//		}
//
//		public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
//			this.listaLogsAuditoria = pListaLogsAuditoria;
//		}
//
//		public long getLlaveUsuarioAuditoria() {
//			return llaveUsuarioAuditoria;
//		}
//
//		public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
//			this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
//		}
//
//		public String getComentarioAuditoria() {
//			return comentarioAuditoria;
//		}
//
//		public void setComentarioAuditoria(String comentarioAuditoria) {
//			this.comentarioAuditoria = comentarioAuditoria;
//		}
//
//		public long getSerialVersionUID() {
//			return serialVersionUID;
//		}
//
//		public long getIdEntidad() {
//			return this.getIdFactura();
//		}
//
//		public String getNombrePropiedadId() {
//			return "idFactura";
//		}
//
//		public boolean isAuditable() {
//			return true;
//		}
}

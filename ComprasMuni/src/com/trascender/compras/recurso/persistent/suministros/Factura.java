package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Table(name = "FACTURA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Factura implements Serializable, EntidadTrascender{

	public enum Estado{
		CREADA, PROCESADA, DEVENGADA, PAGADA, CANCELADA
	}
	
	public enum TipoFactura{
		A,B,C,X
	}
	
	public static final long serialVersionUID = 1964586155130318782L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_factura")
	@SequenceGenerator(name = "gen_id_factura", sequenceName = "gen_id_factura",allocationSize = 1)
	@Column(name="ID_FACTURA")
	private long idFactura = -1;
	private String numero;
	
	@Column(name = "codigo_proveedor")
	private String codigoProveedor;
	
	@Column(name = "fecha_emision")
	private Date fechaEmision;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_FACTURA")
	private TipoFactura tipoFactura;
	
	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.CREADA;
	
	private Double total=0D;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaFactura> listaLineaFactura = new ArrayList<LineaFactura>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LIQUIDACION_COMPRA")
	private LiquidacionCompra liquidacionCompra;
	
	public abstract void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico);
	
	public abstract List<AtributoDinamico<?>> getListaAtributosDinamicos();
	
	public abstract void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos);
	
	public List<LineaFactura> getListaLineaFactura() {
		return listaLineaFactura;
	}
	public void setListaLineaFactura(List<LineaFactura> pListaLineaFactura) {
		this.listaLineaFactura = pListaLineaFactura;
	}
	public void addLineaFactura(LineaFactura pLinea){
		pLinea.setFactura(this);
		this.listaLineaFactura.add(pLinea);
	}

	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	public Estado getEstado() {
		return estado;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	
	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}
	public Double getTotal() {
		return total;
	}
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	public void setTotal(Double pTotal) {
		if(pTotal != null){
			this.total = pTotal;
		}else{
			if(!this.getListaLineaFactura().isEmpty()){
				this.total=0D;
				for(LineaFactura cadaLineaFactura: this.getListaLineaFactura()){
					this.total+=cadaLineaFactura.getTotal();
				}
			}
		}
		
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public LiquidacionCompra getLiquidacionCompra() {
		return liquidacionCompra;
	}
	public void setLiquidacionCompra(LiquidacionCompra liquidacionCompra) {
		this.liquidacionCompra = liquidacionCompra;
	}
	@Override
	public String toString(){
		DateFormat formato = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
		Date locFecha = null;
		if(this.fechaEmision!= null){
			locFecha = this.fechaEmision;
			formato.format(locFecha);
		}
		return "Fecha de Emisión: " + locFecha + " - Tipo de Factura: " +
		this.tipoFactura + " - Estado: " + this.estado + " - Total: $" + this.total.toString(); 
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idFactura ^ (idFactura >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (idFactura != other.idFactura)
			return false;
		return true;
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdFactura());
		}
	}
	
	// ********************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getIdEntidad() {
		return this.getIdFactura();
	}

	public String getNombrePropiedadId() {
		return "idFactura";
	}

	public boolean isAuditable() {
		return true;
	}
}

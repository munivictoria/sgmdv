package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


@Entity
@Table(name = "LOCAL_COMERCIAL")
public class LocalComercial implements Serializable, Cloneable, EntidadTrascender{
	
	public static final long serialVersionUID = -5934764824943508414L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_local_comercial")
	@SequenceGenerator(name = "gen_id_local_comercial", sequenceName = "gen_id_local_comercial", allocationSize = 1)
	@Column(name = "ID_LOCAL_COMERCIAL")
	private long idLocalComercial=-1;
	
	private String descripcion;
	
	private String telefono;
	
	@Column(name = "ID_PARCELA")
	private Long idParcela;
	
	@Column(name = "SUP_CUBIERTA_AFECTADA", nullable = false)
	private Double superficieCubiertaAfectada=0d;
	
	@Column(name = "SUP_SEMICUBIERTA_AFECTADA", nullable = false)
	private Double superficieSemicubiertaAfectada=0d;
	
	@Column(name = "NUMERO_INSCRIPCION", nullable = false)
	private String numeroInscripcion;
	
	@ManyToMany(mappedBy = "listaLocalesComerciales")
	private Set<DocumentoSHPS> listaDocumentosSHPS=new HashSet<DocumentoSHPS>();
	
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@OneToMany(mappedBy = "localComercial", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<InspeccionComercial> listaInspeccionesComerciales=new HashSet<InspeccionComercial>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public Set<InspeccionComercial> getListaInspeccionesComerciales() {
		return listaInspeccionesComerciales;
	}
	public void setListaInspeccionesComerciales(
			Set<InspeccionComercial> listaInspeccionesComerciales) {
		this.listaInspeccionesComerciales = listaInspeccionesComerciales;
	}

	public Set<DocumentoSHPS> getListaDocumentosSHPS() {
		return listaDocumentosSHPS;
	}
	public void setListaDocumentosSHPS(Set<DocumentoSHPS> listaDocumentosSHPS) {
		this.listaDocumentosSHPS = listaDocumentosSHPS;
	}

	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}
	
	public Double getSuperficieCubiertaAfectada() {
		return superficieCubiertaAfectada;
	}
	public void setSuperficieCubiertaAfectada(Double superficieCubiertaAfectada) {
		this.superficieCubiertaAfectada = superficieCubiertaAfectada;
	}
	
	public Double getSuperficieSemicubiertaAfectada() {
		return superficieSemicubiertaAfectada;
	}
	public void setSuperficieSemicubiertaAfectada(
			Double superficieSemicubiertaAfectada) {
		this.superficieSemicubiertaAfectada = superficieSemicubiertaAfectada;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public long getIdLocalComercial() {
		return idLocalComercial;
	}
	public void setIdLocalComercial(long idLocalComercial) {
		this.idLocalComercial = idLocalComercial;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getIdParcela() {
		return idParcela;
	}

	protected void setIdParcela(Long idParcela) {
		this.idParcela = idParcela;
	}
	
	
	public void setParcela(Parcela pParcela){
		if (pParcela==null){
			this.setIdParcela(null);
		}
		else{
			this.setIdParcela(pParcela.getIdParcela());
		}
	}
	
	@Override
	public int hashCode() {
		if(this.getIdLocalComercial()==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLocalComercial ^ (idLocalComercial >>> 32));
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
		final LocalComercial other = (LocalComercial) obj;
		if (idLocalComercial != other.idLocalComercial)
			return false;
		return true;
	}
	
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	@Override
	public LocalComercial clone() throws CloneNotSupportedException {
		return (LocalComercial)super.clone();
	}
	
	@Override
	public String toString() {
		String retorno="";
		if (this.getNumeroInscripcion()!=null){
			retorno=this.getNumeroInscripcion();
			if (this.getDescripcion()!=null){
				retorno+=" - ";
			}
		}
		retorno+=(this.getDescripcion()!=null)?this.getDescripcion():"";
		return retorno;
	}
	
	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

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

	public long getIdEntidad() {
		return this.idLocalComercial;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idLocalComercial";
	}

	public boolean isAuditable() {
		return true;
	}
}

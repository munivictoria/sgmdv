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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;


@Entity
@Table(name = "TRANSPORTE_VEHICULAR")
public class TransporteVehicular implements Serializable, Cloneable, EntidadTrascender{

	public static final long serialVersionUID = -7629472231648905118L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_transporte_vehicular")
	@SequenceGenerator(name = "gen_id_transporte_vehicular", sequenceName = "gen_id_transporte_vehicular", allocationSize = 1)
	@Column(name = "ID_TRANSPORTE_VEHICULAR")
	private long idTransporteVehicular=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_VEHICULO", nullable = false)
	private Vehiculo vehiculo;
	
	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;
	private String descripcion;
	
	@Column(name = "NUMERO_INSCRIPCION", nullable = false)
	private String numeroInscripcion;
	
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@ManyToMany(mappedBy = "listaTransportesVehiculares")
	private Set<DocumentoSHPS> listaDocumentosSHPS=new HashSet<DocumentoSHPS>();
	
	@OneToMany(mappedBy = "transporteVehicular", cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
	private Set<InspeccionVehicular> listaInspeccionesVehiculares=new HashSet<InspeccionVehicular>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public Set<InspeccionVehicular> getListaInspeccionesVehiculares() {
		return listaInspeccionesVehiculares;
	}
	public void setListaInspeccionesVehiculares(
			Set<InspeccionVehicular> listaInspeccionesVehiculares) {
		this.listaInspeccionesVehiculares = listaInspeccionesVehiculares;
	}

	public Set<DocumentoSHPS> getListaDocumentosSHPS() {
		return listaDocumentosSHPS;
	}
	public void setListaDocumentosSHPS(Set<DocumentoSHPS> listaDocumentosSHPS) {
		this.listaDocumentosSHPS = listaDocumentosSHPS;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public long getIdTransporteVehicular() {
		return idTransporteVehicular;
	}
	public void setIdTransporteVehicular(long idTransporteVehicular) {
		this.idTransporteVehicular = idTransporteVehicular;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 7;
		result = PRIME * result + (int) (idTransporteVehicular ^ (idTransporteVehicular >>> 32));
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
		final TransporteVehicular other = (TransporteVehicular) obj;
		if (idTransporteVehicular != other.idTransporteVehicular)
			return false;
		return true;
	}
	
	@Override
	public TransporteVehicular clone() throws CloneNotSupportedException {
		return (TransporteVehicular)super.clone();
	}
	
	@Override
	public String toString() {
		return ((this.getNumeroInscripcion()!=null)?(this.getNumeroInscripcion()+" - "):"") + ((this.getVehiculo()!=null)?this.getVehiculo().toString():""); 
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
		return this.idTransporteVehicular;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTransporteVehicular";
	}

	public boolean isAuditable() {
		return true;
	}
}

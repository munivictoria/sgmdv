package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


@Entity
@Table(name = "PLANTILLA_OBLIGACION")
public class PlantillaObligacion implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -4819764006737026507L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_plantilla_obligacion")
	@SequenceGenerator(name = "gen_id_plantilla_obligacion", sequenceName = "gen_id_plantilla_obligacion", allocationSize = 1)
	@Column(name = "ID_PLANTILLA_OBLIGACION")
	private long idPlantillaObligacion=-1;
	
	private String nombre;
	
	@OneToMany(mappedBy = "raiz", cascade = CascadeType.ALL, orphanRemoval = true)
	@Where(clause = "ID_PADRE is NULL") //FIXME analizar si no anda 
	private Set<PlantillaDocHabilitante> listaDocumentosHabilitantes=new HashSet<PlantillaDocHabilitante>();
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_OBLIGACION")
	private TipoObligacion tipoObligacion;
	
	private String descripcion;
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getIdPlantillaObligacion() {
		return idPlantillaObligacion;
	}
	public void setIdPlantillaObligacion(long idPlantillaObligacion) {
		this.idPlantillaObligacion = idPlantillaObligacion;
	}
	public Set<PlantillaDocHabilitante> getListaDocumentosHabilitantes() {
		return listaDocumentosHabilitantes;
	}
	public void setListaDocumentosHabilitantes(
			Set<PlantillaDocHabilitante> listaDocumentosHabilitantes) {
		this.listaDocumentosHabilitantes = listaDocumentosHabilitantes;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}
	public void setTipoObligacion(TipoObligacion tipo) {
		this.tipoObligacion = tipo;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idPlantillaObligacion ^ (idPlantillaObligacion >>> 32));
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
		PlantillaObligacion other = (PlantillaObligacion) obj;
		if (idPlantillaObligacion != other.idPlantillaObligacion)
			return false;
		return true;
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
		return this.idPlantillaObligacion;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idPlantillaObligacion";
	}

	public boolean isAuditable() {
		return true;
	}
}

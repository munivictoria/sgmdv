package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


/**
 * 
 * @author Mariano Lusardi
 * @hibernate.class table = "INSPECTOR"
 */

@Entity
@Table(name = "INSPECTOR")
public class Inspector implements Serializable, EntidadTrascender{

	/**
	 * 
	 */
	public static final long serialVersionUID = 6668382801105580298L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_inspector")
	@SequenceGenerator(name = "gen_id_inspector", sequenceName = "gen_id_inspector", allocationSize = 1)
	@Column(name = "ID_INSPECTOR")
	private long idInspector=-1;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private PersonaFisica persona;
	
	@OneToMany(mappedBy = "inspector", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<InspeccionSHPS> listaInspecciones=new HashSet<InspeccionSHPS>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();


	/**
	 * 
	 * @hibernate.id column = "ID_INSPECTOR" generator-class = "increment" unsaved-value = "-1"
	 */
	public long getIdInspector() {
		return idInspector;
	}

	public void setIdInspector(long idInspector) {
		this.idInspector = idInspector;
	}

	/**
	 * 
	 * @hibernate.set cascade = "all" inverse = "true" lazy = "true" 
	 * @hibernate.collection-one-to-many class = "com.trascender.habilitaciones.recurso.persistent.shps.InspeccionSHPS"
	 * @hibernate.collection-key column = "ID_INSPECTOR "
	 */
	public Set<InspeccionSHPS> getListaInspecciones() {
		return listaInspecciones;
	}

	public void setListaInspecciones(Set<InspeccionSHPS> listaInspecciones) {
		this.listaInspecciones = listaInspecciones;
	}

	/**
	 * 
	 * @hibernate.many-to-one cascade = "none" column = "ID_PERSONA"
	 */
	public PersonaFisica getPersona() {
		return persona;
	}

	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombrePersonaFisica(){
		try{
			return this.getPersona().toString();
		}
		catch(org.hibernate.LazyInitializationException e){
			return null;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idInspector ^ (idInspector >>> 32));
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
		Inspector other = (Inspector) obj;
		if (idInspector != other.idInspector)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getNombre();
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
		return this.idInspector;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idInspector";
	}

	public boolean isAuditable() {
		return true;
	}
}

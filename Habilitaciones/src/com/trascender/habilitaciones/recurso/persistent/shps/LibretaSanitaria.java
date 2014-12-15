package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Table(name = "LIBRETA_SANITARIA")
public class LibretaSanitaria implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -9205474308029442350L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_libreta_sanitaria")
	@SequenceGenerator(name = "gen_id_libreta_sanitaria", sequenceName = "gen_id_libreta_sanitaria", allocationSize = 1)
	@Column(name = "ID_LIBRETA_SANITARIA")
	private long idLibretaSanitaria=-1;
	
	@Column(name = "NUMERO_LIBRETA_SANITARIA", unique = true)
	private Integer numeroLibretaSanitaria;
	
	@OneToMany(mappedBy = "libretaSanitaria", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RenovacionLibretaSanitaria> listaRenovaciones=new HashSet<RenovacionLibretaSanitaria>();
	
	@OneToMany(mappedBy = "libretaSanitaria", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ConstanciaVacunacion> listaConstanciasVacunaciones=new HashSet<ConstanciaVacunacion>();
	
	@OneToMany(mappedBy = "libretaSanitaria", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<InhabilitacionTemporariaLS> listaInhabilitacionesTemporarias=new HashSet<InhabilitacionTemporariaLS>();
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA",nullable=false)
	private PersonaFisica personaFisica;
	
	private boolean activo=true;
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	
	public PersonaFisica getPersonaFisica() {
		return personaFisica;
	}

	public void setPersonaFisica(PersonaFisica personaFisicaRfr) {
		this.personaFisica = personaFisicaRfr;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<ConstanciaVacunacion> getListaConstanciasVacunaciones() {
		return listaConstanciasVacunaciones;
	}
	public void setListaConstanciasVacunaciones(
			Set<ConstanciaVacunacion> listaConstanciasVacunaciones) {
		this.listaConstanciasVacunaciones = listaConstanciasVacunaciones;
	}
	
	public Set<InhabilitacionTemporariaLS> getListaInhabilitacionesTemporarias() {
		return listaInhabilitacionesTemporarias;
	}
	public void setListaInhabilitacionesTemporarias(
			Set<InhabilitacionTemporariaLS> listaInhabilitacionesTemporarias) {
		this.listaInhabilitacionesTemporarias = listaInhabilitacionesTemporarias;
	}

	public Set<RenovacionLibretaSanitaria> getListaRenovaciones() {
		return listaRenovaciones;
	}
	public void setListaRenovaciones(
			Set<RenovacionLibretaSanitaria> listaRenovaciones) {
		this.listaRenovaciones = listaRenovaciones;
	}
	
	public Integer getNumeroLibretaSanitaria() {
		return numeroLibretaSanitaria;
	}
	public void setNumeroLibretaSanitaria(Integer numeroLibretaSanitaria) {
		this.numeroLibretaSanitaria = numeroLibretaSanitaria;
	}
	
	public long getIdLibretaSanitaria() {
		return idLibretaSanitaria;
	}
	public void setIdLibretaSanitaria(long idLibretaSanitaria) {
		this.idLibretaSanitaria = idLibretaSanitaria;
	}

	public String getNombrePersonaFisica(){
		return (this.getPersonaFisica()!=null)?(this.getPersonaFisica().getNombre()+" "+this.getPersonaFisica().getApellido()):"";
	}

	@Override
	public int hashCode() {
		if (this.getIdLibretaSanitaria()==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLibretaSanitaria ^ (idLibretaSanitaria >>> 32));
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
		final LibretaSanitaria other = (LibretaSanitaria) obj;
		if (idLibretaSanitaria != other.idLibretaSanitaria)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return (this.getNumeroLibretaSanitaria()!=null)?this.getNumeroLibretaSanitaria().toString():"" +" - " +this.personaFisica;
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
		return this.idLibretaSanitaria;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idLibretaSanitaria";
	}

	public boolean isAuditable() {
		return true;
	}
}

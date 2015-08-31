/**
 * 
 * © Copyright 2015, CoDeSoft Todos los derechos reservados.
 * 
 */

package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;

@Entity
@Table(name = "NUMERADOR")
public class Numerador implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = 1141058900746001896L;

	@Id
	@GeneratedValue(generator = "gen_id_numerador", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen_id_numerador", sequenceName = "gen_id_numerador", allocationSize = 1)
	@Column(name = "ID_NUMERADOR")
	private long idNumerador = -1;

	private String nombre;
	private Integer contador = 0;

	@Column(name = "RESETEA_CON_ANIO")
	private boolean reseteaConAnio = false;

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;

	public enum Estado {
		ACTIVO, INACTIVO;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(super.toString());
		}
	};

	@OrderBy(value = "anio")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numerador", orphanRemoval = true)
	private List<LineaNumerador> listaLineaNumerador = new ArrayList<LineaNumerador>();

	public long getIdNumerador() {
		return idNumerador;
	}

	public void setIdNumerador(long pIdNumerador) {
		idNumerador = pIdNumerador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer pContador) {
		contador = pContador;
	}

	public boolean isReseteaConAnio() {
		return reseteaConAnio;
	}

	public void setReseteaConAnio(boolean pReseteaConAnio) {
		reseteaConAnio = pReseteaConAnio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}

	public List<LineaNumerador> getListaLineaNumerador() {
		return listaLineaNumerador;
	}

	public void setListaLineaNumerador(List<LineaNumerador> pListaLineaNumerador) {
		listaLineaNumerador = pListaLineaNumerador;
	}
	
	public void incrementarContador() {
		this.contador += 1;
	}

	@Override
	public String toString() {
		return nombre;
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
		return this.idNumerador;
	}

	public String getNombrePropiedadId() {
		return "idNumerador";
	}

	public boolean isAuditable() {
		return true;
	}
	
}
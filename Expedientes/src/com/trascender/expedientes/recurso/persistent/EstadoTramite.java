/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.expedientes.enums.EstadoPlantilla;

@Entity
@Table(name = "EXP_ESTADO_TRAMITE")
public class EstadoTramite implements Serializable {

	public static final long serialVersionUID = 7864193048373596955L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_EstadoTramite")
	@SequenceGenerator(name = "gen_id_EstadoTramite", sequenceName = "gen_id_EstadoTramite", allocationSize = 1)
	@Column(name = "ID_EstadoTramite")
	private long idEstadoTramite = -1l;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "CIERRA_TRAMITE")
	private boolean cierraTramite = false;

	@Enumerated(EnumType.STRING)
	private EstadoPlantilla estado = EstadoPlantilla.ACTIVO;

	public long getIdEstadoTramite() {
		return idEstadoTramite;
	}

	public void setIdEstadoTramite(long idEstadoTramite) {
		this.idEstadoTramite = idEstadoTramite;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isCierraTramite() {
		return cierraTramite;
	}

	public void setCierraTramite(boolean cierraTramite) {
		this.cierraTramite = cierraTramite;
	}

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idEstadoTramite ^ (idEstadoTramite >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EstadoTramite other = (EstadoTramite) obj;
		if(idEstadoTramite != other.idEstadoTramite)
			return false;
		
		return true;
	}

}
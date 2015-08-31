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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EXP_ENTIDAD")
public class Entidad implements Serializable {

	private static final long serialVersionUID = 6106205460822982723L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_entidad")
	@SequenceGenerator(name = "gen_id_exp_entidad", sequenceName = "gen_id_exp_entidad", allocationSize = 1)
	@Column(name = "ID_ENTIDAD")
	private long idEntidad = -1l;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	private boolean activo = true;

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
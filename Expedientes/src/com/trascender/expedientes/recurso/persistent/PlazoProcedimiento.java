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
@Table(name = "EXP_PLAZOPROCEDIMIENTO")
public class PlazoProcedimiento implements Serializable {

	private static final long serialVersionUID = -7423716402016073225L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_plazoProcedimiento")
	@SequenceGenerator(name = "gen_id_exp_plazoProcedimiento", sequenceName = "gen_id_exp_plazoProcedimiento", allocationSize = 1)
	@Column(name = "ID_PLAZO")
	private long idPlazo = -1l;

	@Column(name = "DIAS", nullable = false)
	private Integer dias;

	@Column(name = "DIASCORRIDOS")
	private boolean diasCorridos;

	@Column(name = "CANTIDAD_EXTENSIONES")
	private Integer cantidadExtensiones;

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public boolean isDiasCorridos() {
		return diasCorridos;
	}

	public void setDiasCorridos(boolean diasCorridos) {
		this.diasCorridos = diasCorridos;
	}

	public long getIdPlazo() {
		return idPlazo;
	}

	public void setIdPlazo(long idPlazo) {
		this.idPlazo = idPlazo;
	}

	public Integer getCantidadExtensiones() {
		return cantidadExtensiones;
	}

	public void setCantidadExtensiones(Integer cantidadExtensiones) {
		this.cantidadExtensiones = cantidadExtensiones;
	}

}
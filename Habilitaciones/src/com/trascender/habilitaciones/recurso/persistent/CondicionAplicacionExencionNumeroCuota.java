package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CONDICION_APLICACION_EXENCION_NUMERO_CUOTA")
public class CondicionAplicacionExencionNumeroCuota implements Serializable{

	private static final long serialVersionUID = -2331161615270385372L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_condicion_aplicacion_exencion_numero_cuota")
	@SequenceGenerator(name = "gen_id_condicion_aplicacion_exencion_numero_cuota", sequenceName = "gen_id_condicion_aplicacion_exencion_numero_cuota", allocationSize = 1)
	@Column(name = "ID_CONDICION_APLICACION_EXENCION_NUMERO_CUOTA")
	private long idCondicionAplicacionExencionNumeroCuota = -1l;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EXENCION")
	private ExencionObligacion exencionObligacion;
	
	@Column(name="NUMERO_CUOTA")
	private Integer numeroCuota;
	
	@Column(name="NUMERO_PERIODO")
	private Integer numeroPeriodo;

	public long getIdCondicionAplicacionExencionNumeroCuota() {
		return idCondicionAplicacionExencionNumeroCuota;
	}

	public void setIdCondicionAplicacionExencionNumeroCuota(
			long idCondicionAplicacionExencionNumeroCuota) {
		this.idCondicionAplicacionExencionNumeroCuota = idCondicionAplicacionExencionNumeroCuota;
	}

	public ExencionObligacion getExencionObligacion() {
		return exencionObligacion;
	}

	public void setExencionObligacion(ExencionObligacion exencionObligacion) {
		this.exencionObligacion = exencionObligacion;
	}

	public Integer getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public Integer getNumeroPeriodo() {
		return numeroPeriodo;
	}

	public void setNumeroPeriodo(Integer numeroPeriodo) {
		this.numeroPeriodo = numeroPeriodo;
	}
}

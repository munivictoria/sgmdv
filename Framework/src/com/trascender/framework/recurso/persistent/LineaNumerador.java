/**
 * 
 * © Copyright 2015, CoDeSoft Todos los derechos reservados.
 * 
 */

package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_NUMERADOR")
public class LineaNumerador implements Serializable {

	private static final long serialVersionUID = -7277565101312469404L;

	@Id
	@GeneratedValue(generator = "gen_id_linea_numerador", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen_id_linea_numerador", sequenceName = "gen_id_linea_numerador", allocationSize = 1)
	@Column(name = "ID_LINEA_NUMERADOR")
	private long idLineaNumerador = -1;

	@ManyToOne
	@JoinColumn(name = "ID_NUMERADOR")
	private Numerador numerador;

	private Integer anio;
	private Integer contador = 0;

	public long getIdLineaNumerador() {
		return idLineaNumerador;
	}

	public void setIdLineaNumerador(long pIdLineaNumerador) {
		idLineaNumerador = pIdLineaNumerador;
	}

	public Numerador getNumerador() {
		return numerador;
	}

	public void setNumerador(Numerador pNumerador) {
		numerador = pNumerador;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer pAnio) {
		anio = pAnio;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer pContador) {
		contador = pContador;
	}
	
	public void incrementarContador() {
		this.contador += 1;
	}
	
	@Override
	public String toString() {
		return numerador.getNombre() + " [" + anio + "]";
	}

}
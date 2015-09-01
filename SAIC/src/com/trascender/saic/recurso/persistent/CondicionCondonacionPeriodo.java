package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONDICION_CONDONACION_PERIODO")
public class CondicionCondonacionPeriodo implements Serializable{
	private static final long serialVersionUID = 6957299351879681420L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_condicion_condonacion_periodo")
	@SequenceGenerator(name = "gen_id_condicion_condonacion_periodo", sequenceName = "gen_id_condicion_condonacion_periodo", allocationSize = 1)
	@Column(name = "ID_CONDICION_CONDONACION_PERIODO")
	private long idCondicionCondonacionPeriodo = -1;
	
	@JoinColumn(name = "ID_PLANTILLA")
	@ManyToOne
	private PlantillaPlanDePago plantilla;
	
	private Integer anio;
	
	public enum Condicion {
		TOTAL, PERIODO_ANTIGUO_POR_POSICION
	}
	
	@Enumerated(EnumType.STRING)
	private Condicion condicion;
	
	private Double porcentaje;

	public long getIdCondicionCondonacionPeriodo() {
		return idCondicionCondonacionPeriodo;
	}

	public void setIdCondicionCondonacionPeriodo(long idCondicionCondonacionPeriodo) {
		this.idCondicionCondonacionPeriodo = idCondicionCondonacionPeriodo;
	}

	public PlantillaPlanDePago getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlantillaPlanDePago plantilla) {
		this.plantilla = plantilla;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idCondicionCondonacionPeriodo ^ (idCondicionCondonacionPeriodo >>> 32));
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
		CondicionCondonacionPeriodo other = (CondicionCondonacionPeriodo) obj;
		if (idCondicionCondonacionPeriodo != other.idCondicionCondonacionPeriodo)
			return false;
		return true;
	}

}

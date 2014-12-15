package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PLANTILLA_SELLADO")
@PrimaryKeyJoinColumn(name = "ID_PLANTILLA_DOC_HABILITANTE")
public class PlantillaSellado extends PlantillaDocHabilitante{
	/**
	 * 
	 */
	public static final long serialVersionUID = 4029607882591209535L;

	private Double valor;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}

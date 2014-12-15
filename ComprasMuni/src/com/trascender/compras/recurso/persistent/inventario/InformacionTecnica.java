package com.trascender.compras.recurso.persistent.inventario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class InformacionTecnica implements Serializable{
	private static final long serialVersionUID = -1219006662960193990L;
	
	private String marca;
	private String modelo;
	
	@Column(name = "NUMERO_SERIE")
	private String numeroSerie;
	private String material;
	private String color;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public enum Estado{
		NUEVO, BUENO, REGULAR, MALO
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}

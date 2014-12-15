package com.trascender.habilitaciones.recurso.persistent.osp;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONSUMO_BASICO")
public class ConsumoBasico implements Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = -8188599443235598804L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_consumo_basico")
	@SequenceGenerator(name = "gen_id_consumo_basico", sequenceName = "gen_id_consumo_basico", allocationSize = 1)
	@Column(name = "ID_CONSUMO_BASICO")
	private long idConsumoBasico=-1;
	
	@Column(name = "CONSUMO_INICIAL")
	private Double consumoInicial;
	
	@Column(name = "CONSUMO_POR_EXCEDENTE")
	private Double consumoPorExcedente;
	
	@Column(name = "SUPERFICIE_MEJORAS_MINIMO")
	private Double superficieMejorasMinimo;
	
	@Column(name = "SUPERFICIE_MEJORAS_MAXIMO")
	private Double superficieMejorasMaximo;
	
	private boolean activo=true;
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Double getConsumoInicial() {
		return consumoInicial;
	}
	public void setConsumoInicial(Double consumoInicial) {
		this.consumoInicial = consumoInicial;
	}
	
	public Double getConsumoPorExcedente() {
		return consumoPorExcedente;
	}
	public void setConsumoPorExcedente(Double consumoPorExcedente) {
		this.consumoPorExcedente = consumoPorExcedente;
	}
	
	public long getIdConsumoBasico() {
		return idConsumoBasico;
	}
	public void setIdConsumoBasico(long idConsumoBasico) {
		this.idConsumoBasico = idConsumoBasico;
	}
	
	public Double getSuperficieMejorasMaximo() {
		return superficieMejorasMaximo;
	}
	public void setSuperficieMejorasMaximo(Double superficieMejorasMaximo) {
		this.superficieMejorasMaximo = superficieMejorasMaximo;
	}
	
	public Double getSuperficieMejorasMinimo() {
		return superficieMejorasMinimo;
	}
	public void setSuperficieMejorasMinimo(Double superficieMejorasMinimo) {
		this.superficieMejorasMinimo = superficieMejorasMinimo;
	}

	@Override
	public String toString(){
		StringBuilder locStringBuilder = new StringBuilder();
		NumberFormat locNumberFormat = NumberFormat.getInstance();
		locNumberFormat.setMinimumFractionDigits(2);
		locNumberFormat.setMaximumFractionDigits(2);
		
		locStringBuilder.append("[");
		if (this.getSuperficieMejorasMinimo()!=null){
			locStringBuilder.append(locNumberFormat.format(this.getSuperficieMejorasMinimo())+" - ");
		}
		if (this.getSuperficieMejorasMaximo()!=null){
			locStringBuilder.append(locNumberFormat.format(this.getSuperficieMejorasMaximo()));
		}
		locStringBuilder.append("] ");
		
		if (this.getConsumoInicial()!=null){
			locStringBuilder.append("Consumo inicial: "+locNumberFormat.format(this.getConsumoInicial())+" ");
		}
		if (this.getConsumoPorExcedente()!=null){
			locStringBuilder.append("Consumo por excedente: "+locNumberFormat.format(this.getConsumoPorExcedente()));
		}
		return locStringBuilder.toString();
	}
}

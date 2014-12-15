package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @hibernate.class table= "CONTRATO"
 */
@Entity
@Table(name="CONTRATO")
public class Contrato implements Serializable{
	
	public static final long serialVersionUID = 4843434288111857168L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_contrato")
	@SequenceGenerator(name = "gen_id_contrato", sequenceName = "gen_id_contrato",allocationSize = 1)
	@Column(name="ID_CONTRATO")
	private long idContrato = -1;
	
	@ManyToOne(targetEntity = Persona.class) 
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@Column(name="CODIGO_CONTRATO")
	private String codigoContrato;
	
	private String descripcion;
	
	@Column(name="FECHA_INICIO",nullable=false)
	private Date fechaInicio;
	
	@Column(name="FECHA_FIN",nullable=false)
	private Date fechaFin;
	
	private Double total;
	
	@Column(name="CANTIDAD_DE_CUOTAS")
	private Integer cantidadCuotas;
	
	@Column(name="PRECIO_POR_CUOTA")
	private Double precioPorCuota=0d;
	
	/**
	 * 
	 * @return the idContrato.
	 * @hibernate.id column="ID_CONTRATO" generator-class = "increment" unsaved-value="-1"
	 */
	public long getIdContrato() {
		return idContrato;
	}
	
	/**
	 * 
	 * @param pIdContrato
	 */
	public void setIdContrato(long pIdContrato) {
		idContrato = pIdContrato;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}

	
	public String getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(String pCodigoContrato) {
		codigoContrato = pCodigoContrato;
	}
	
	/**
	 * 
	 * @return the persona.
	 * @hibernate.any cascade = "none" id-type="long" meta-type="int"
	 * @hibernate.any-column="TIPO_PERSONA"
	 * @hibernate.any-column="ID_PERSONA" 
	 */
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona pPersona) {
		persona = pPersona;
	}
	
	/**
	 * 
	 * @return the fechaInicio
	 * @hibernate.property column = "FECHA_INICIO"
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date pFechaInicio) {
		fechaInicio = pFechaInicio;
	}
	
	/**
	 * 
	 * @return the fechaFin
	 * @hibernate.property column = "FECHA_FIN"
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date pFechaFin) {
		fechaFin = pFechaFin;
	}
	
	/**
	 * 
	 * @return the total.
	 * @hibernate.property
	 */
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double pTotal) {
		total = pTotal;
	}
	
	/**
	 * @return the cantidadCuotas.
	 * @hibernate.property
	 */
	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(Integer pCantidadCuotas) {
		cantidadCuotas = pCantidadCuotas;
	}
	
	/**
	 * 
	 * @return the precioPorCuota
	 * @hibernate.property
	 */
	public Double getPrecioPorCuota() {
		return precioPorCuota;
	}
	
	public void setPrecioPorCuota(Double pPrecioPorCuota) {
		precioPorCuota = pPrecioPorCuota;
	}
	
	public String toString(){
		String locCadena = "Código del Contrato: " + this.codigoContrato + " - Descripcion: ";
		if(this.descripcion!= null){
			locCadena+=this.descripcion;
		}
		locCadena+=" - Persona: " + this.persona.toString();
		return locCadena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idContrato ^ (idContrato >>> 32));
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
		Contrato other = (Contrato) obj;
		if (idContrato != other.idContrato)
			return false;
		return true;
	}
	
	
	
}

package com.trascender.contabilidad.recurso.persistent;

import java.util.Date;

public class PeriodoContable {

	private long idPeriodoContable = -1;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private Estado estado;
	public enum Estado {ACTIVO, INACTIVO;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	/**
	 * @hibernate.id column="ID_PERIODO_CONTABLE" generator-class="increment" unsaved-value="-1" 
	 * @return
	 */
	public long getIdPeriodoContable() {
		return idPeriodoContable;
	}
	public void setIdPeriodoContable(long idPeriodoContable) {
		this.idPeriodoContable = idPeriodoContable;
	}
	
	/**
	 * @hibernate.property column ="FECHA_INICIO"
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * @hibernate.property column ="FECHA_FIN"
	 * @return
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @hibernate.property column="ESTADO" type="com.trascender.contabilidad.util.enumerations.EstadoPeriodoContable"
	 * @return
	 */
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/**
	 * @hibernate.property column="NOMBRE"
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

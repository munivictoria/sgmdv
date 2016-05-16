package com.trascender.saic.recurso.references;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.trascender.framework.util.Util;

/**
 * La definitiva, para traer desde la base y mostrar en los Admin.
 * @author ferna
 *
 */
public class LiquidacionTasaRefer implements Serializable{
	private static final long serialVersionUID = -6743838262197133630L;
	
	private Double total;
	private String stringPersona;
	private boolean cancelada;
	private Long idParcela;
	private Date maxFechaVencimiento;
	private String estado;
	private Set<Long> idsRegistrosDeuda;
	private String tipo;
	private String periodo;
	private Integer anio;
	private String parcela;
	private Long idPersona;
	private String domicilioParcelario;
	private String aviso;
	private Date fechaCancelacion;
	
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public String getDomicilioParcelario() {
		return domicilioParcelario;
	}

	public void setDomicilioParcelario(String domicilioParcelario) {
		this.domicilioParcelario = domicilioParcelario;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public LiquidacionTasaRefer(){
		
	}
	
	public Integer getAnio() {
		return anio;
	}



	public void setAnio(Integer anio) {
		this.anio = anio;
	}



	public String getEstado(){
		if(this.estado.equals("CANCELADA") 
				|| this.estado.equals("VENCIDA") 
				|| this.estado.equals("VIGENTE")){
			if(!cancelada){//si no tiene reg de cancelacion, es vigente
				estado = "VIGENTE";
			}

			if(estado.equals("VIGENTE")){
				Date locFechaActual = Util.getFechaActualFormatoSimple();

				if(this.maxFechaVencimiento != null){//si es vigente y tiene una fecha de vencimiento, comprueba si no esta venciada
					estado = ((maxFechaVencimiento.after(locFechaActual) || locFechaActual.getTime() == maxFechaVencimiento.getTime())?"VIGENTE":"VENCIDA");
				}
				else{// si tiene una fecha de vencimiento, el estado es vencido
					estado = "VENCIDA";
				}
			}
		} else if (estado.equals("PAGADA")) {
			estado += " ("+Util.getString(fechaCancelacion)+")";
		}
		return estado;
	}
	
	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Set<Long> getIdsRegistrosDeuda() {
		return idsRegistrosDeuda;
	}

	public void setIdsRegistrosDeuda(Set<Long> idsRegistrosDeuda) {
		this.idsRegistrosDeuda = idsRegistrosDeuda;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getStringPersona() {
		return stringPersona;
	}
	public void setStringPersona(String stringPersona) {
		this.stringPersona = stringPersona;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	public Long getIdParcela() {
		return idParcela;
	}
	public void setIdParcela(Long idParcela) {
		this.idParcela = idParcela;
	}
	public Date getMaxFechaVencimiento() {
		return maxFechaVencimiento;
	}
	public void setMaxFechaVencimiento(Date maxFechaVencimiento) {
		this.maxFechaVencimiento = maxFechaVencimiento;
	}

	@Override
	public String toString() {
		return stringPersona + ", " + total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idsRegistrosDeuda == null) ? 0 : idsRegistrosDeuda
						.hashCode());
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
		LiquidacionTasaRefer other = (LiquidacionTasaRefer) obj;
		if (idsRegistrosDeuda == null) {
			if (other.idsRegistrosDeuda != null)
				return false;
		} else {
			return this.idsRegistrosDeuda.size() == other.idsRegistrosDeuda.size()
				&& this.idsRegistrosDeuda.containsAll(other.idsRegistrosDeuda);
		}
		return true;
	}


}

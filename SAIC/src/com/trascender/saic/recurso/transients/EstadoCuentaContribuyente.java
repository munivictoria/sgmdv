package com.trascender.saic.recurso.transients;

import java.io.Serializable;

import com.trascender.habilitaciones.recurso.persistent.Obligacion;

/**
 * Clase que contiene la información de Reporte de Estado de Cuenta
 * @author Maximiliano Fontanini
 * 
 */
public class EstadoCuentaContribuyente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2183566724724386259L;
	
	private Double totalAdeudado=0D;
	private Obligacion obligacion;
	
	
	public EstadoCuentaContribuyente(Obligacion pObligacion,Double pTotalAdeudado) {
		this.obligacion = pObligacion;
		this.totalAdeudado = pTotalAdeudado;
	}
	
	public String getNumeroTramite(){
		return ((this.obligacion.getNumeroTramite()!=null)?this.obligacion.getNumeroTramite():"")+"\n";
	}
	
	public String getStringObligacion() {
		return this.obligacion.toString()+"\n";
	}

	public String getEstado() {
		return this.obligacion.getEstado().toString()+"\n";
	}
	
	public Double getTotalAdeudado(){
		return this.totalAdeudado;
	}
	
	public Obligacion getObligacion(){
		return this.obligacion;
	}
}

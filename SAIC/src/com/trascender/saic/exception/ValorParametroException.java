package com.trascender.saic.exception;

import org.hibernate.Session;

import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;

public class ValorParametroException extends SaicException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1136897828649962491L;
	private Obligacion obligacion;
	private TipoParametro tipoParametro;

	public ValorParametroException(Obligacion pObligacion, TipoParametro pTipoParametro) {
		super(2154);
		
		this.obligacion = pObligacion;
		this.tipoParametro = pTipoParametro;
		
		
		this.setMsg();
	}

	protected void setMsg() {
		this.msg = "No se ha podido realizar la liquidación de la obligación: " + ( (this.getObligacion()!=null)?this.getObligacion().toString():"") +
		", porque no se pudo recuperar el valor del parámetro: " +((this.getTipoParametro()!=null)?this.getTipoParametro().toString():"");  
	}
	
	private Obligacion getObligacion(){
		return this.obligacion;
	}
	
	private TipoParametro getTipoParametro(){
		return this.tipoParametro;
	}
	
}
package com.trascender.habilitaciones.exception;

import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;

public class HabilitacionesErrorFormulaException extends HabilitacionesException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7846977860025802121L;

	
	private TipoParametro tipoParametro;

	public HabilitacionesErrorFormulaException(TipoParametro pTipoParametro) {
		super(2154);
		this.tipoParametro = pTipoParametro;
		
		
		this.setMsg();
	}

	protected void setMsg() {
		this.msg = "Error al validar la fórmula, el parámetro: " +((this.getTipoParametro()!=null)?this.getTipoParametro().toString():"") +
		" se encuentra en la fórmula pero no esta el la lista de parámetros que la componen.";  
	}
	
	private TipoParametro getTipoParametro(){
		return this.tipoParametro;
	}

}

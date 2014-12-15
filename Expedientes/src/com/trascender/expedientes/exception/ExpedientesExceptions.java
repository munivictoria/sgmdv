package com.trascender.expedientes.exception;

import com.trascender.framework.exception.TrascenderException;

public class ExpedientesExceptions extends TrascenderException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6540811708948411521L;

	public ExpedientesExceptions(int pCodeSystemException) {
		super(7100 + pCodeSystemException);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setMsg() {
		switch (getCodeTrascenderException()) {
		// Expediente
		case 7101:this.msg = "El Nombre ya se encuentra utilizado por otro Procedimiento.";break;
		//Plazos
		case 7202: this.msg = "Ya se han cumplido la cantidad maxima de Extensiones."; break;
		case 7203: this.msg = "Su Usuario no puede agregar una Extensión.";break;
		case 7204: this.msg = "Su Usuario ya realizó la Extensión permitida.";break;
		case 7205: this.msg = "La cantidad de días de Extensión supera la cantidad permitida.";break;
		default:
			this.msg = "Ha ocurrido un error inesperado.";
		}
	}
}

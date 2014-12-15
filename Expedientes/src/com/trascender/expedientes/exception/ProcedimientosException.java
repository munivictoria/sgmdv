package com.trascender.expedientes.exception;

import com.trascender.framework.exception.TrascenderException;

public class ProcedimientosException extends TrascenderException {

	private static final long serialVersionUID = 5268234782897447832L;

	public ProcedimientosException(int pCodeSystemException) {
		super(7000 + pCodeSystemException);
	}

	@Override
	protected void setMsg() {
		switch (getCodeTrascenderException()) {
		// Expediente
		case 7001:
			this.msg = "El Nombre ya se encuentra utilizado por otro Procedimiento.";
			break;
		default:
			this.msg = "Ha ocurrido un error inesperado.";
		}
	}

}
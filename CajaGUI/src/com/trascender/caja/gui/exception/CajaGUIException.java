package com.trascender.caja.gui.exception;

import com.trascender.framework.exception.TrascenderException;

public class CajaGUIException extends TrascenderException {

	private static final long serialVersionUID = 2823303556524582066L;
	
	private static final int  COD_PROC = 9000; // codigo del proceso
	
	public CajaGUIException(int pCodigo) {
		super(COD_PROC+pCodigo);
	}

	@Override
	protected void setMsg() {
		switch (this.getCodeTrascenderException()) {

		case COD_PROC + 0: this.msg = "El puerto de la impresora no ha sido configurado. Comuníquese con el administrador del sistema."; break;

		default: this.msg = "Ha ocurrido un error.";

		}
	}

}

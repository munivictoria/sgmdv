package com.trascender.gui.framework.exception;

import com.trascender.framework.exception.TrascenderException;

public class ErrorConexionException extends TrascenderException{

	private static final long serialVersionUID = 7497850413668388712L;

	public ErrorConexionException() {
		super(0);
	}
	
	@Override
	protected void setMsg() {
		this.msg = "Se ha perdido la conexión con el servidor. Debe volver a ingresar al sistema.";
	}
}

package com.trascender.framework.exception;

import com.trascender.framework.recurso.persistent.validacionDinamica.MensajeErrorVD;

public class ValidacionDinamicaException extends TrascenderException {

	private MensajeErrorVD mensaje;
	
	public ValidacionDinamicaException(int pNroMensaje) {
		super(pNroMensaje);
	}
	
	public ValidacionDinamicaException(MensajeErrorVD pError){
		super( ((pError.getNumeroMsg() != null)?pError.getNumeroMsg().intValue(): -1) + 10000);
		this.mensaje=pError;
		this.setMsg();
		
	}

	private static final long serialVersionUID = 3665548329736455502L;

	@Override
	protected void setMsg() {
		if(this.mensaje != null){
			this.msg= mensaje.getMensaje();
		}else{

			switch (codeTrascenderException) {
			case 10001:	this.msg = "El validador dinamico no pudo ser iniciado, por que uno de sus parametros es nulo. Por fabor Comuniquese con el Administrador."; break;
			case 10002:	this.msg = "El Sexo de la persona solo puede ser masculino. verifique."; break;
			
			}
		}
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}

}

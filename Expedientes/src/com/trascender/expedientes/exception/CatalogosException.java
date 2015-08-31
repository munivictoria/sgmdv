/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.exception;

import com.trascender.framework.exception.TrascenderException;

public class CatalogosException extends TrascenderException {

	public CatalogosException(int pCodeSystemException) {
		super(7200 + pCodeSystemException);
	}

	private static final long serialVersionUID = -2101870175684339280L;

	@Override
	protected void setMsg() {
		switch (getCodeTrascenderException()) {
			// Expediente
			case 7201: this.msg = "El Nombre ya se encuentra utilizado por otra Fase Cat\341logo."; break;
			case 7202: this.msg = "El Nombre ya se encuentra utilizado por otro Tramite Cat\341logo."; break;
			case 7203: this.msg = "El Nombre ya se encuentra utilizado por otro Documento Cat\341logo."; break;	
			case 7204: this.msg = "El Nombre ya se encuentra utilizado por otro Estado tr\341mite."; break;
			case 7205: this.msg = "El Tramite requiere al menos un Estado tr\341mite."; break;
			case 7206: this.msg = "No se ha podido restaurar el Tramite. Por favor, intente nuevamente."; break;
			case 7207: this.msg = "No se ha podido restaurar la Fase. Por favor, intente nuevamente."; break;
			case 7208: this.msg = "No se ha podido restaurar el Documento. Por favor, intente nuevamente."; break;
			case 7209: this.msg = "No se ha podido restaurar el Estado Trámite. Por favor, intente nuevamente."; break;
			
			default:
				this.msg = "Ha ocurrido un error inesperado.";
		}
	}
	
}
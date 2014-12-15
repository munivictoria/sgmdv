package com.trascender.habilitaciones.exception;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;

public class ObligacionTGIExistenteException extends HabilitacionesException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8276092511264295116L;
	private Persona persona;
	private Parcela parcela;

	public ObligacionTGIExistenteException(DocumentoTGI pDocumentoTGI) {
		super(1);
		this.persona=pDocumentoTGI.getObligacion().getPersona();
		this.parcela=pDocumentoTGI.getParcela();
		this.setMsg();
	}

	protected void setMsg() {
		this.msg = "Ya existe una Obligación TGI asociada a la Parcela: " + this.getParcela() + ", asignada a la Persona: " + this.getPersona()  + ". Por favor, verifique los datos de la Obligación.";
	}

	public Persona getPersona() {
		return persona;
	}

	public Parcela getParcela() {
		return parcela;
	}


}

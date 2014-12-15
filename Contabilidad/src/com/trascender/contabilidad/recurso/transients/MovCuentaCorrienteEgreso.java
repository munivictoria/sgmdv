package com.trascender.contabilidad.recurso.transients;

import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso;

public class MovCuentaCorrienteEgreso extends MovCuentaCorriente{
	private static final long serialVersionUID = -8917869669315087964L;
	
	private DocumentoEgreso documentoEgreso;

	public DocumentoEgreso getDocumentoEgreso() {
		return documentoEgreso;
	}

	public void setDocumentoEgreso(DocumentoEgreso documentoEgreso) {
		this.documentoEgreso = documentoEgreso;
	}

	
}

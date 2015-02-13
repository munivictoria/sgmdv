package com.trascender.habilitaciones.business.interfaces;

import javax.ejb.Local;

import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;

@Local
public interface BusinessDocumentoArrendamientoLocal {
	
	public void addDocumentoArrendamiento(DocumentoArrendamiento pDocumento);
	public void updateDocumentoArrendamiento(DocumentoArrendamiento pDocumento);
	public void deleteDocumentoArrendamiento(DocumentoArrendamiento pDocumento);
	public FiltroObligacionArrendamiento findListaObligacionesArrendamiento(FiltroObligacionArrendamiento pFiltro);
	public DocumentoArrendamiento getDocumentoArrendamiento(Obligacion pObligacion);

}

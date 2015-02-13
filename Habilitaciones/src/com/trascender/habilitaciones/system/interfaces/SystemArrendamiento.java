package com.trascender.habilitaciones.system.interfaces;

import javax.ejb.Remote;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;

@Remote
public interface SystemArrendamiento {
	
	public static final String JNDI_NAME = "ejb/SystemArrendamiento/remote";
	
	public void addDocumentoArrendamiento(DocumentoArrendamiento pDocumento) throws TrascenderException;
	public void updateDocumentoArrendamiento(DocumentoArrendamiento pDocumento) throws TrascenderException;
	public void deleteDocumentoArrendamiento(DocumentoArrendamiento pDocumento) throws TrascenderException;
	public FiltroObligacionArrendamiento findListaObligacionesArrendamiento(FiltroObligacionArrendamiento pFiltro) throws TrascenderException;
	public DocumentoArrendamiento getDocumentoArrendamiento(Obligacion pObligacion) throws TrascenderException;
	
	public void setLlave(long llave);

}

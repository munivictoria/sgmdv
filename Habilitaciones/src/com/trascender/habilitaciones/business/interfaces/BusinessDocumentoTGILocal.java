package com.trascender.habilitaciones.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;

/**
 * Local interface for BusinessDocumentoTGI.
 */
@Local
public interface BusinessDocumentoTGILocal {
	
	public static final String JNDI_NAME = "ejb/BusinessDocumentoTGILocal/local";
	
   /**
    * Agrega un documento de Tasa general inmobiliaria
    * @param pDocumentoTGI Documento a agregar
    * @return 
    * @throws Exception
    */
   public DocumentoTGI addDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception;

   /**
    * Actualiza los datos de un documento de tasa general inmobiliaria
    * @param pDocumentoTGI documento a actualizar
    * @return 
    * @throws Exception
    */
   public DocumentoTGI updateDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception;

   /**
    * Elimina un documento TGI físicamente
    * @param pDocumentoTGI
    * @throws Exception
    */
   public void deleteDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception;

   /**
    * Recupera un listado de los documentos SHPS desde la persona
    * @param pPersona
    * @return 
    * @throws Exception
    */
   public FiltroObligacionTGI findListaDocumentosTGI(FiltroObligacionTGI pFiltro) throws Exception;

   /**
    * Recupera un listado de obligaciones de TGI
    * @param pPersona
    * @param pNumeroRegistro
    * @return 
    * @throws Exception
    */
   public FiltroObligacionTGI findListaObligacionesTGI(FiltroObligacionTGI pFiltro) throws Exception;

   public DocumentoTGI getDocumentoTGI(Obligacion pObligacion) throws Exception;
}
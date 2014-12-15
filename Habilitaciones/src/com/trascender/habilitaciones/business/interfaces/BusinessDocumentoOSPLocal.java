package com.trascender.habilitaciones.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.filtros.FiltroConsumoBasico;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;

@Local
public interface BusinessDocumentoOSPLocal {
	
	public static final String JNDI_NAME = "ejb/BusinessDocumentoOSPLocal/local";
	
	
   /**
    * Agrega un documentoOSP
    * @param pDocumentoOSP
    * @return 
    * @throws Exception
    */
   public DocumentoOSP addDocumentoOSP(DocumentoOSP pDocumentoOSP ) throws Exception;

   /**
    * Actualiza los datos del documento OSP
    * @param pDocumentoOSP
    * @return 
    * @throws Exception
    */
   public DocumentoOSP updateDocumentoOSP( DocumentoOSP pDocumentoOSP ) throws Exception;

   /**
    * Setea el estado de la obligación en terminado
    * @param pDocumentoOSP
    * @throws Exception
    */
   public void deleteDocumentoOSP( DocumentoOSP pDocumentoOSP ) throws Exception;

   /**
    * Recupera el listado de documentos OSP asociados con la persona
    * @param pPersona persona asociada
    * @return Listado de documentos habilitantes
    * @throws Exception
    */
   public List findListaDocumentosOSP(Persona pPersona,
		   										Parcela pParcela,
		   										Integer pNumeroCuenta ) throws Exception;

   /**
    * Recupera el listado de obligaciones cuyos documentos son del tipo DocumentoOSP y cumplen con los parámetros ingresados
    * @param pPersona
    * @param pNumeroRegistro
    * @param pNumeroCuenta
    * @return 
    * @throws Exception
    */
   public FiltroObligacionOSP findListaObligacionesOSP(FiltroObligacionOSP pFiltro) throws java.lang.Exception;

   /**
    * Recupera el documento habilitante asociado con la obligación
    * @param pObligacion obligación asociada
    * @return DocumentoOSP asociado con la obligación
    * @throws Exception
    */
   public DocumentoOSP getDocumentoOSP(Obligacion pObligacion ) throws Exception;
   
   public DocumentoOSP getDocumentoOSPPorId(long pIdDocHabEsp ) throws Exception;

   public ConsumoBasico addConsumoBasico(ConsumoBasico pConsumoBasico ) throws Exception;

   public ConsumoBasico updateConsumoBasico(ConsumoBasico pConsumoBasico) throws Exception;

   public void deleteConsumoBasico(ConsumoBasico pConsumoBasico) throws Exception;

   public ConsumoBasico getConsumoBasico(DocumentoOSP pDocumentoOSP) throws Exception;

   public FiltroConsumoBasico getListaConsumosBasicos(FiltroConsumoBasico pFiltro) throws Exception;
   
   /**
    * Devuelve el último numero de cuenta OSP ocupado + 1
    */
   public Integer getSugerenciaNumeroCuenta();

}

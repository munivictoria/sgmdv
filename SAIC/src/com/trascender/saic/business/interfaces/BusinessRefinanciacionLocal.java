/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.saic.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.saic.recurso.filtros.FiltroPlantillaPlanDePago;
import com.trascender.saic.recurso.filtros.FiltroRefinanciacion;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

/**
 * Local interface for BusinessRefinanciacion.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
@Local
public interface BusinessRefinanciacionLocal
{
	public static final String JNDI_NAME="BusinessRefinanciacionLocal/local";
	
   public java.util.Set calcularCuotasRefinanciacion( com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion pDocumentoRefinanciacion ) throws Exception;

   public void addRefinanciacion( com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion pDocumentoRefinanciacion, AuditoriaTributaria pAuditoria ) throws java.lang.Exception;

   public void updateRefinanciacion( com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion pDocumentoRefinanciacion ) throws java.lang.Exception;
   
   public DocumentoRefinanciacion getDocumentoRefinanciacion(long pIdDocGeneradorDeuda) throws Exception;
		   
   public FiltroRefinanciacion findListaRefinanciaciones(FiltroRefinanciacion pFiltro) throws Exception;
   
   public void addPlantillaPlanDePago(PlantillaPlanDePago plantilla);
	
   public void updatePlantillaPlanDePago(PlantillaPlanDePago plantilla);
	
   public void deletePlantillaPlanDePago(PlantillaPlanDePago plantilla);
	
   public FiltroPlantillaPlanDePago findListaPlantillaPlanDePago(FiltroPlantillaPlanDePago filtro);

   public void deleteRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion)
		throws Exception;
}

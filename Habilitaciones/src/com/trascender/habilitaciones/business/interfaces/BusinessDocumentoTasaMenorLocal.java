package com.trascender.habilitaciones.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionTasaMenor;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;

@Local
public interface BusinessDocumentoTasaMenorLocal {
	
	public static final String JNDI_NAME = "ejb/BusinessDocumentoTasaMenorLocal/local";
	
	public PlantillaDocumentoTasaMenor addPlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException;
	public void updatePlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException;
	public void removePlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla);
	public FiltroPlantillaDocumentoTasaMenor findListaPlantillaDocumentoTasaMenor(FiltroPlantillaDocumentoTasaMenor pFiltro);
	public PlantillaDocumentoTasaMenor getPlantillaDocumentoPorId(long pId);
	
	public void addDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException;
	public void updateDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException;
	public void removeDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException;
	public List<DocumentoTasaMenor> findListaDocumentoTasaMenor(Persona pPersona, Parcela pParcela);
	public DocumentoTasaMenor getDocumentoTasaMenor(Obligacion pObligacion);
	public FiltroObligacionTasaMenor findListaObligacionesTasaMenor(FiltroObligacionTasaMenor pFiltro) throws Exception;
}

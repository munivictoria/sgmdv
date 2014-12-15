package com.trascender.habilitaciones.system.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;

@Remote
public interface SystemDocumentoTasaMenor {
	
	public static final String JNDI_NAME = "ejb/SystemDocumentoTasaMenor/remote";
	
	public void setLlave(long pLlave);
	
	public PlantillaDocumentoTasaMenor addPlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException;
	public void updatePlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException;
	public void removePlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException;
	public FiltroPlantillaDocumentoTasaMenor findListaPlantillaDocumentoTasaMenor(FiltroPlantillaDocumentoTasaMenor pFiltro) throws TrascenderException;
	public PlantillaDocumentoTasaMenor getPlantillaDocumentoPorId(long pId) throws TrascenderException;
	
	public void addDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException;
	public void updateDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException;
	public void removeDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException;
	public List<DocumentoTasaMenor> findListaDocumentoTasaMenor(Persona pPersona, Parcela pParcela) throws Exception;
	public DocumentoTasaMenor getDocumentoTasaMenor(Obligacion pObligacion) throws Exception;
	

}

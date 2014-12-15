package com.trascender.habilitaciones.system.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTasaMenorLocal;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTasaMenor;

@Stateful(name = "ejb/SystemDocumentoTasaMenor")
public class SystemDocumentoTasaMenorBean implements SystemDocumentoTasaMenor{
	
	private long llave;
	
	@EJB
	private BusinessDocumentoTasaMenorLocal businessDocumento;

	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public PlantillaDocumentoTasaMenor addPlantillaDocumentoTasaMenor(
			PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		return businessDocumento.addPlantillaDocumentoTasaMenor(pPlantilla);
	}

	public void updatePlantillaDocumentoTasaMenor(
			PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		businessDocumento.updatePlantillaDocumentoTasaMenor(pPlantilla);
	}

	public void removePlantillaDocumentoTasaMenor(
			PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		businessDocumento.removePlantillaDocumentoTasaMenor(pPlantilla);
	}

	public FiltroPlantillaDocumentoTasaMenor findListaPlantillaDocumentoTasaMenor(FiltroPlantillaDocumentoTasaMenor pFiltro) throws TrascenderException {
		return businessDocumento.findListaPlantillaDocumentoTasaMenor(pFiltro);
	}
	
	public PlantillaDocumentoTasaMenor getPlantillaDocumentoPorId(long pId) throws TrascenderException {
		return businessDocumento.getPlantillaDocumentoPorId(pId);
	}

	public void addDocumentoTasaMenor(DocumentoTasaMenor pDocumento)
			throws TrascenderException {
		this.businessDocumento.addDocumentoTasaMenor(pDocumento);
	}

	public void updateDocumentoTasaMenor(DocumentoTasaMenor pDocumento)
			throws TrascenderException {
		this.businessDocumento.updateDocumentoTasaMenor(pDocumento);
	}

	public void removeDocumentoTasaMenor(DocumentoTasaMenor pDocumento)
			throws TrascenderException {
		this.removeDocumentoTasaMenor(pDocumento);
	}

	public List<DocumentoTasaMenor> findListaDocumentoTasaMenor(
			Persona pPersona, Parcela pParcela) throws Exception {
		return this.businessDocumento.findListaDocumentoTasaMenor(pPersona, pParcela);
	}

	public DocumentoTasaMenor getDocumentoTasaMenor(Obligacion pObligacion)
			throws Exception {
		return this.businessDocumento.getDocumentoTasaMenor(pObligacion);
	}

}

package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;

public class FiltroObligacionTasaMenor extends FiltroAbstracto<Obligacion>{
	
	private static final long serialVersionUID = 5758967634110046844L;
	private Persona persona;
	private Integer nroParcela;
	private Parcela parcela;
	private PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor;
	private List<Long> listaIdPersonas;
	private List<Long> listaIdParcelas;
	
	
	public PlantillaDocumentoTasaMenor getPlantillaDocumentoTasaMenor() {
		return plantillaDocumentoTasaMenor;
	}
	public void setPlantillaDocumentoTasaMenor(
			PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor) {
		this.plantillaDocumentoTasaMenor = plantillaDocumentoTasaMenor;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Integer getNroParcela() {
		return nroParcela;
	}
	public void setNroParcela(Integer nroParcela) {
		this.nroParcela = nroParcela;
	}
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
	public List<Long> getListaIdParcelas() {
		return listaIdParcelas;
	}
	public void setListaIdParcelas(List<Long> listaIdParcelas) {
		this.listaIdParcelas = listaIdParcelas;
	}
}

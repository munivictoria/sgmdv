package com.trascender.habilitaciones.recurso.persistent.tasaMenor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;

@Entity
@DiscriminatorValue(DocumentoTasaMenor.DISCRIMINATOR_VALUE)
public class DocumentoTasaMenor extends DocHabilitanteEspecializado{
	
	public static final long serialVersionUID = -7455605909630287438L;
	public static final String DISCRIMINATOR_VALUE="MENOR";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLANTILLA_DOC_TASA_MENOR")
	private PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public PlantillaDocumentoTasaMenor getPlantillaDocumentoTasaMenor() {
		return plantillaDocumentoTasaMenor;
	}

	public void setPlantillaDocumentoTasaMenor(
			PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor) {
		this.plantillaDocumentoTasaMenor = plantillaDocumentoTasaMenor;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdDocHabilitanteEspecializado());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	public String toString(){
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		return  "[TASA MENOR] " + this.getPlantillaDocumentoTasaMenor().getNombre(); 
	}
	
	/**
	 * Crea los atributos dinamicos correspondientes con la PlantillaDocumentoTasaMenor, solo
	 * si no todavia no existen.
	 */
	public List<AtributoDinamico<?>> generarAtributosDinamicos() {
		List<AtributoDinamico<?>> locListaAtributos = new ArrayList<AtributoDinamico<?>>(this.listaAtributosDinamicos);
		if (this.plantillaDocumentoTasaMenor != null){
			for (PlantillaAtributoDinamico cadaPlantillaAtributo : this.plantillaDocumentoTasaMenor.getListaPlantillasAtributos()){
				boolean existe = false;
				for (AtributoDinamico<?> cadaAtributo : this.listaAtributosDinamicos){
					if (cadaAtributo.getPlantilla().equals(cadaPlantillaAtributo)){
						existe = true;
						break;
					}
				}
				if (!existe){
					AtributoDinamico<?> locAtributo = cadaPlantillaAtributo.generarAtributoDinamico();
					locAtributo.setIdRecurso(serialVersionUID);
					locListaAtributos.add(locAtributo);
				}
			}
		}
		return locListaAtributos;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}
}

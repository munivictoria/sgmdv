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
import javax.persistence.PostPersist;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;

@Entity
@DiscriminatorValue("TASA_MENOR")
public class RegistroValuadoTasaMenor extends RegistroValuado{

	public static final long serialVersionUID = 2245061386517336268L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLANTILLA_TASA_MENOR")
	private PlantillaDocumentoTasaMenor plantillaDocTasaMenor;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public PlantillaDocumentoTasaMenor getPlantillaDocTasaMenor() {
		return plantillaDocTasaMenor;
	}

	public void setPlantillaDocTasaMenor(
			PlantillaDocumentoTasaMenor plantillaDocTasaMenor) {
		this.plantillaDocTasaMenor = plantillaDocTasaMenor;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdRegistroValuado());
		pAtributoDinamico.setIdRecurso(serialVersionUID);
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

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(this.getIdRegistroValuado());
		}
	}

	/**
	 * Crea los atributos dinamicos correspondientes con la PlantillaDocumentoTasaMenor, solo
	 * si no todavia no existen.
	 */
	public List<AtributoDinamico<?>> generarAtributosDinamicos() {
		List<AtributoDinamico<?>> locListaAtributos = new ArrayList<AtributoDinamico<?>>(this.listaAtributosDinamicos);
		if (this.plantillaDocTasaMenor != null){
			for (PlantillaAtributoDinamico cadaPlantillaAtributo : this.plantillaDocTasaMenor.getListaPlantillasAtributos()){
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

	@Override
	public String toString(){
		String locResult = "REGISTRO VALUADO [" + plantillaDocTasaMenor.getNombre()+"] ";
		for (AtributoDinamico<?> cadaAtributo : listaAtributosDinamicos){
			locResult += "["+cadaAtributo.getNombre()+" = " + cadaAtributo.getValor()+"] ";
		}
		return locResult;
	}

}

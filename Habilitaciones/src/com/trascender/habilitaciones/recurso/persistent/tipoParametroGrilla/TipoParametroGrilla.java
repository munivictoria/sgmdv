package com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;

@Entity
@DiscriminatorValue("GRILLA")
public class TipoParametroGrilla extends TipoParametro implements Serializable{
	public static final long serialVersionUID = -6094835334123870088L;

	@OrderBy("idTipoParametroGrillaVariable")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grilla", orphanRemoval=true)
	private List<TipoParametroGrillaVariable> listaVariables = new ArrayList<TipoParametroGrillaVariable>();
	
	@OrderBy("nroFila")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grilla", orphanRemoval=true)
	private List<TipoParametroGrillaFila> filas = new ArrayList<TipoParametroGrillaFila>();
	
	//Para mantener las grillas en memoria.
	@Transient
	private static Map<Long, TipoParametroGrilla> mapaCache = new HashMap<Long, TipoParametroGrilla>();
	
	public List<TipoParametroGrillaVariable> getListaVariables() {
		return listaVariables;
	}

	public void setListaVariables(List<TipoParametroGrillaVariable> listaVariables) {
		this.listaVariables = listaVariables;
	}

	public List<TipoParametroGrillaFila> getFilas() {
		return filas;
	}

	public void setFilas(List<TipoParametroGrillaFila> filas) {
		this.filas = filas;
	}
	
	public void addFila(TipoParametroGrillaFila fila) {
		fila.setGrilla(this);
		this.filas.add(fila);
	}
	
	public void addVariable(TipoParametroGrillaVariable pVariable) {
		pVariable.setGrilla(this);
		this.listaVariables.add(pVariable);
	}
	
	public TipoParametroGrillaFila getFilaPorNumero(Integer numero){
		for (TipoParametroGrillaFila cadaFila : this.filas){
			if (cadaFila.getNroFila().equals(numero)){
				return cadaFila;
			}
		}
		return null;
	}
	
	public void imprimirFilas(){
		Collections.sort(this.filas);
		for (TipoParametroGrillaFila cadaFila : filas) {
			System.out.print(cadaFila.getCondicion());
			cadaFila.imprimir();
			System.out.println();
		}
	}

	@Override
	public Object getValor(
			DocHabilitanteEspecializado pDocHabilitanteEspecializado)
			throws Exception {
		//No se devuelve desde aqui.
		return null;
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.setNombreVariable(pNombreAtributo);
	}
	
	/**
	 * Devuelve la grilla desde el cache, o la agrega si no la encuentra
	 * @param tipoParametro
	 * @return
	 */
	public static TipoParametroGrilla getGrillaDeCache(TipoParametroGrilla tipoParametro) {
		TipoParametroGrilla grilla = mapaCache.get(tipoParametro.getIdTipoParametro());
		if (grilla == null) {
			//Refrescar todas las propiedades
			tipoParametro.getListaVariables().size();
			for (TipoParametroGrillaFila cadaFila : tipoParametro.getFilas()) {
				cadaFila.getColumnas().size();
			}
			mapaCache.put(tipoParametro.getIdTipoParametro(), tipoParametro);
			grilla = tipoParametro;
		}
		return grilla;
	}
	
}

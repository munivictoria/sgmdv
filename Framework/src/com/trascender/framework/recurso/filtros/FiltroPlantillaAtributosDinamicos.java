package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroPlantillaAtributosDinamicos extends FiltroAbstracto<PlantillaAtributoDinamico> {

	public FiltroPlantillaAtributosDinamicos() {
	}

	public FiltroPlantillaAtributosDinamicos(String pNombre, Long pIdRecurso, Recurso pRecurso, Boolean pBusqueda) {
		nombre = pNombre;
		idRecurso = pIdRecurso;
		recurso = pRecurso;
		busqueda = pBusqueda;
	}

	private static final long serialVersionUID = -8244629245601871136L;

	private String nombre;
	private Long idRecurso;
	private Recurso recurso;
	private Boolean busqueda;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso pRecurso) {
		recurso = pRecurso;
	}

	public Boolean getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(Boolean pBusqueda) {
		busqueda = pBusqueda;
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

}

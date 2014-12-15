package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;

public class FiltroPlantillaDocumentoTasaMenor extends FiltroAbstracto<PlantillaDocumentoTasaMenor>{
	
	private static final long serialVersionUID = 9079871819023202428L;
	private String nombre;
	
	public FiltroPlantillaDocumentoTasaMenor(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public FiltroPlantillaDocumentoTasaMenor() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

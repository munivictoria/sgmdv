package com.trascender.saic.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;

public class FiltroPlantillaPlanDePago extends FiltroAbstracto<PlantillaPlanDePago>{
	private static final long serialVersionUID = 7637991925568348644L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

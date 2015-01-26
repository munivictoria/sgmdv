package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroProcesoDB extends FiltroAbstracto<ProcesoDB> {
	
	private String nombre;
	
	private String nombreProceso;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String pNombreProceso) {
		nombreProceso = pNombreProceso;
	}

}

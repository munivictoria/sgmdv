package com.trascender.contabilidad.recurso.filtros;

import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroReporteContable extends FiltroAbstracto<ReporteContable>{

	private static final long serialVersionUID = 739071219261391562L;
	
	private String nombre;
	
	public FiltroReporteContable() {
	}
	
	public FiltroReporteContable(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTramiteProcedimiento extends FiltroAbstracto<TramiteProcedimiento> {

	private static final long serialVersionUID = 8779203284650860847L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

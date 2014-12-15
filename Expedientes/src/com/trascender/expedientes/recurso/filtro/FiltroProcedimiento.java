package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroProcedimiento  extends FiltroAbstracto<Procedimiento>{
	private static final long serialVersionUID = -2928140279185130791L;

	
	 private String nombre;


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	 
	 
	
	
}

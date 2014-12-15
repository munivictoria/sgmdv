package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroEstadoSolicitudSuministro extends FiltroAbstracto<EstadoSolicitudSuministro> {
	
	private static final long serialVersionUID = 330701264922005292L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

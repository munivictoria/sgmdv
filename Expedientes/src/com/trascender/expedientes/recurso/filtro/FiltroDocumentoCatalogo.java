package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroDocumentoCatalogo extends FiltroAbstracto<DocumentoCatalogo> {

	private static final long serialVersionUID = -767479264816049089L;

	private String nombre;
	private EstadoPlantilla estado;
	
	
	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

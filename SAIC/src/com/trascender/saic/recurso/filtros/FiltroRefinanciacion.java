
package com.trascender.saic.recurso.filtros;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion.EstadoRefinanciacion;

public class FiltroRefinanciacion extends FiltroAbstracto<DocumentoRefinanciacion> {

	private static final long serialVersionUID = 1953264814203304762L;

	private Persona persona;
	private Integer nroRefinanciacion;
	private EstadoRefinanciacion estado;
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public Integer getNroRefinanciacion() {
		return nroRefinanciacion;
	}
	
	public void setNroRefinanciacion(Integer nroRefinanciacion) {
		this.nroRefinanciacion = nroRefinanciacion;
	}

	public EstadoRefinanciacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoRefinanciacion estado) {
		this.estado = estado;
	}

}
package com.trascender.saic.recurso.filtros;

import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionSHPS extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = -4775387737788473522L;

	private Rubro rubro;
	private String nroInscripcion;
	
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public String getNroInscripcion() {
		return nroInscripcion;
	}

	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}
}

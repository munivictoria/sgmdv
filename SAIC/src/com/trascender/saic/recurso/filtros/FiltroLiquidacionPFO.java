package com.trascender.saic.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionPFO extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = 1346446338049792368L;

	private Obra obra;
	private Cuadra cuadra;
	private Calle calle;
	
	public Obra getObra() {
		return obra;
	}
	
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	
	public Cuadra getCuadra() {
		return cuadra;
	}
	
	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}
	
	public Calle getCalle() {
		return calle;
	}
	
	public void setCalle(Calle calle) {
		this.calle = calle;
	}
}
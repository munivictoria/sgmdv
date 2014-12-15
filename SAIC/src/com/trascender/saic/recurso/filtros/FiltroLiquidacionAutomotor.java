package com.trascender.saic.recurso.filtros;

import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionAutomotor extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = 4785165857852290920L;

	private Vehiculo vehiculo;

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
}
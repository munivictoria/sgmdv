package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

public class FiltroTituloPropiedadAutomotor extends FiltroAbstracto<TituloPropiedadAutomotor>{

	private static final long serialVersionUID = -4710295726074130716L;
	
	private Vehiculo vehiculo;
	private String codigo;
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}

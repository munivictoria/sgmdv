package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;

public class FiltroExencionObligacion extends FiltroAbstracto<ExencionObligacion>{

	private static final long serialVersionUID = 7474831327273918330L;
	
	private Obligacion obligacion;
	private Persona persona;
	private Integer anio;
	
	public Obligacion getObligacion() {
		return obligacion;
	}
	public void setObligacion(Obligacion obligacion) {
		this.obligacion = obligacion;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
}

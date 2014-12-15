package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class FiltroTipoTasa extends FiltroAbstracto<TipoTasa>{
	private static final long serialVersionUID = -8609559602908751918L;
	
	private String nombre;
	private TipoObligacion tipoObligacion;
	private Periodicidad periodicidad;
	private TipoTasa.Estado estado;
	
	
	public FiltroTipoTasa(){
	}
	
	public FiltroTipoTasa(String pNombre, TipoObligacion pTipoObligacion, Periodicidad pPeriodicidad, TipoTasa.Estado pEstado){
		this.nombre = pNombre;
		this.tipoObligacion = pTipoObligacion;
		this.periodicidad = pPeriodicidad;
		this.estado = pEstado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}
	public void setTipoObligacion(TipoObligacion tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}
	public TipoTasa.Estado getEstado() {
		return estado;
	}
	public void setEstado(TipoTasa.Estado estado) {
		this.estado = estado;
	}
	
}

package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroZona extends FiltroAbstracto<Zona>{
	private static final long serialVersionUID = -6998358577781541977L;
	
	private String nombre;
	private Zona.Estado estado;
	private Zonificacion zonificacion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Zona.Estado getEstado() {
		return estado;
	}
	public void setEstado(Zona.Estado estado) {
		this.estado = estado;
	}
	public Zonificacion getZonificacion() {
		return zonificacion;
	}
	public void setZonificacion(Zonificacion zonificacion) {
		this.zonificacion = zonificacion;
	}
	
}

package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;

public class FiltroTipoParametroGrupoZona extends FiltroAbstracto<TipoParametroGrupoZona>{
	
	private static final long serialVersionUID = -8216957602702496778L;
	private String nombre;
	private Zonificacion zonificacion;
	
	public FiltroTipoParametroGrupoZona(String nombre, Zonificacion zonificacion) {
		super();
		this.nombre = nombre;
		this.zonificacion = zonificacion;
	}

	public FiltroTipoParametroGrupoZona() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Zonificacion getZonificacion() {
		return zonificacion;
	}

	public void setZonificacion(Zonificacion zonificacion) {
		this.zonificacion = zonificacion;
	}
}

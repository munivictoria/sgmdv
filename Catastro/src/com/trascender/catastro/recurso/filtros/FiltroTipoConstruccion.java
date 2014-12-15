package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTipoConstruccion extends FiltroAbstracto<TipoConstruccion>{

	private static final long serialVersionUID = -8678191257981052428L;
	
	private Long idTipoConstruccion;
	private String nombre;
	private Boolean estado;
	
	public Long getIdTipoConstruccion() {
		return idTipoConstruccion;
	}
	public void setIdTipoConstruccion(Long idTipoConstruccion) {
		this.idTipoConstruccion = idTipoConstruccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}

package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroCalle extends FiltroAbstracto<Calle>{

	private static final long serialVersionUID = 5174435000061535052L;

	private java.lang.String nombre;
	private TipoCalle tipoCalle;
	private Boolean estado;
	private java.lang.String codigo;

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public java.lang.String getNombre() {
		return nombre;
	}
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}
	public TipoCalle getTipoCalle() {
		return tipoCalle;
	}
	public void setTipoCalle(TipoCalle tipoCalle) {
		this.tipoCalle = tipoCalle;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}




}

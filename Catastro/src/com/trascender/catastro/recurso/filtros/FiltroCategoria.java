package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroCategoria extends FiltroAbstracto<Categoria>{

	private static final long serialVersionUID = -1383394791606659274L;


	private String nombre;
	private Integer codigoCategoria;
	private Boolean estado;
	private TipoConstruccion tipoConstruccion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public TipoConstruccion getTipoConstruccion() {
		return tipoConstruccion;
	}
	public void setTipoConstruccion(TipoConstruccion tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}

}

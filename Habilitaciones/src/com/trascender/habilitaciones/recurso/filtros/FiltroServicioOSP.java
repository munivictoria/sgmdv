package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;


public class FiltroServicioOSP extends FiltroAbstracto<ServicioOSP> {
	
	private static final long serialVersionUID = 583169480238490101L;
	private String codigo;
	private String nombre;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}
	
	

}

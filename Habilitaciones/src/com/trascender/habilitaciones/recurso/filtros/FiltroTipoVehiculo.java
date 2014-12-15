package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;

/**
 * @author danilo
 *
 */
public class FiltroTipoVehiculo extends FiltroAbstracto<TipoVehiculo>{
	private static final long serialVersionUID = 8553705834675679436L;
	
	private String nombre;
	private String codigo;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	
	
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}
	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> listaAtributoDinamico) {
		this.listaAtributoDinamico = listaAtributoDinamico;
	}
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
	
}

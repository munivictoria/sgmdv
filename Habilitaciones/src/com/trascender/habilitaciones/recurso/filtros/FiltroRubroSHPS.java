package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;

public class FiltroRubroSHPS extends FiltroAbstracto<Rubro>{
	private static final long serialVersionUID = 3779303735789579011L;
	
	private String nombre;
	private TipoObligacion tipoObligacion;
	private String codigo;
	private RegAlicuota.Estado estado;
	private CodigoCiiu codigoCiuu;
	private List<AtributoDinamico<?>> listaAtributo;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public RegAlicuota.Estado getEstado() {
		return estado;
	}
	public void setEstado(RegAlicuota.Estado estado) {
		this.estado = estado;
	}
	public CodigoCiiu getCodigoCiuu() {
		return codigoCiuu;
	}
	public void setCodigoCiuu(CodigoCiiu codigoCiuu) {
		this.codigoCiuu = codigoCiuu;
	}
	public List<AtributoDinamico<?>> getListaAtributo() {
		return listaAtributo;
	}
	public void setListaAtributo(List<AtributoDinamico<?>> listaAtributo) {
		this.listaAtributo = listaAtributo;
	}	
}

package com.trascender.compras.recurso.filtros;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Bien.Estado;
import com.trascender.compras.recurso.persistent.suministros.Bien.Tipo;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroBien extends FiltroAbstracto<Bien>{

	private static final long serialVersionUID = -4786301703026554013L;
	
	public FiltroBien() {
	}
	public FiltroBien( String nombre, String descripcion, Estado estado, Tipo tipo, TipoBien tipoBien, Unidad unidad, CodigoCiiu codigoCiiu) {
		this.nombre = nombre;
		this.estado = estado;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.tipoBien = tipoBien;
		this.unidad= unidad;
		this.codigoCiiu= codigoCiiu;
	}
	
	private Unidad unidad;
	private String nombre;
	private String descripcion;
	private Bien.Estado estado;
	private Tipo tipo;
	private TipoBien tipoBien;
	private CodigoCiiu codigoCiiu;
	private List<Long> listaIdCodigosCiiu;
	
	public CodigoCiiu getCodigoCiiu() {
		return codigoCiiu;
	}
	public void setCodigoCiiu(CodigoCiiu codigoCiiu) {
		this.codigoCiiu = codigoCiiu;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Bien.Estado getEstado() {
		return estado;
	}
	public void setEstado(Bien.Estado estado) {
		this.estado = estado;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public TipoBien getTipoBien() {
		return tipoBien;
	}
	public void setTipoBien(TipoBien tipoBien) {
		this.tipoBien = tipoBien;
	}
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	public List<Long> getListaIdCodigosCiiu() {
		return listaIdCodigosCiiu;
	}
	public void setListaIdCodigosCiiu(List<Long> listaIdCodigosCiiu) {
		this.listaIdCodigosCiiu = listaIdCodigosCiiu;
	}
}
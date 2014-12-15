package com.trascender.compras.recurso.filtros;

import java.util.Date;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Articulo.EstadoContable;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroArticulo extends FiltroAbstracto<Articulo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7356003549614047879L;
	
	private String nombre;
	private String codigo;
	private Date fechaCompra;
	private Date fechaEntradaServicio;
	private EstadoContable estadoContable;
	private Area area;
	
	public FiltroArticulo() {
	}

	public FiltroArticulo(String pNombre, String pCodigo, Date pFechaCompra,
			Date pFechaEntradaServicio, EstadoContable pEstadoContable,
			Area pArea) {
		super();
		this.nombre = pNombre;
		this.codigo = pCodigo;
		this.fechaCompra = pFechaCompra;
		this.fechaEntradaServicio = pFechaEntradaServicio;
		this.estadoContable = pEstadoContable;
		this.area = pArea;
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

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaEntradaServicio() {
		return fechaEntradaServicio;
	}

	public void setFechaEntradaServicio(Date fechaEntradaServicio) {
		this.fechaEntradaServicio = fechaEntradaServicio;
	}

	public EstadoContable getEstadoContable() {
		return estadoContable;
	}

	public void setEstadoContable(EstadoContable estadoContable) {
		this.estadoContable = estadoContable;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
}

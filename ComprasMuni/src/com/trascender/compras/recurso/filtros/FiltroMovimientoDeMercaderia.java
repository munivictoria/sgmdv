package com.trascender.compras.recurso.filtros;

import java.util.Calendar;
import java.util.Date;

import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia.Tipo;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;


public class FiltroMovimientoDeMercaderia extends FiltroAbstracto<MovimientoDeMercaderia>{

	private static final long serialVersionUID = 4449307573385692541L;
	private Tipo tipo;
	private Usuario usuario;
	private Deposito depositoOrigen;
	private Deposito depositoDestino;
	private Area area;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Deposito getDepositoOrigen() {
		return depositoOrigen;
	}
	public void setDepositoOrigen(Deposito depositoOrigen) {
		this.depositoOrigen = depositoOrigen;
	}
	public Deposito getDepositoDestino() {
		return depositoDestino;
	}
	public void setDepositoDestino(Deposito depositoDestino) {
		this.depositoDestino = depositoDestino;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
}

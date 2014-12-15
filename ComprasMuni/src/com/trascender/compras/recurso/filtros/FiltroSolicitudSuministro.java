package com.trascender.compras.recurso.filtros;

import java.util.Date;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroSolicitudSuministro extends FiltroAbstracto<SolicitudSuministro> {

	public FiltroSolicitudSuministro() {
	}

	public FiltroSolicitudSuministro(Area area, Secretaria secretaria, Bien bien, EstadoSolicitudSuministro estado, Date fechaDesde, Date fechaHasta,
			Integer numero, Usuario usuario, Boolean urgente) {
		this.area = area;
		this.secretaria = secretaria;
		this.bien = bien;
		this.estado = estado;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.numero = numero;
		this.usuario = usuario;
		this.urgente = urgente;
	}

	private static final long serialVersionUID = 225339180267436062L;

	private Area area;
	private Secretaria secretaria;
	private Bien bien;
	private EstadoSolicitudSuministro estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer numero;
	private Usuario usuario;
	private Boolean urgente;

	public com.trascender.framework.recurso.persistent.Area getArea() {
		return area;
	}

	public void setArea(com.trascender.framework.recurso.persistent.Area area) {
		this.area = area;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public com.trascender.compras.recurso.persistent.suministros.Bien getBien() {
		return bien;
	}

	public void setBien(com.trascender.compras.recurso.persistent.suministros.Bien bien) {
		this.bien = bien;
	}

	public EstadoSolicitudSuministro getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitudSuministro estado) {
		this.estado = estado;
	}

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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getUrgente() {
		return urgente;
	}

	public void setUrgente(Boolean urgente) {
		this.urgente = urgente;
	}
}
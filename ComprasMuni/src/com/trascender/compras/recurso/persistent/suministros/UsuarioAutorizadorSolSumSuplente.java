package com.trascender.compras.recurso.persistent.suministros;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@PrimaryKeyJoinColumn(name = "ID_USUARIO_AUTORIZADOR")
@Table(name = "RELA_USR_AUTOR_SOL_SUM_SUPL")
public class UsuarioAutorizadorSolSumSuplente extends UsuarioAutorizadorSolicitudSuministro{
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_SUPLIDO")
	private UsuarioAutorizadorSolicitudSuministro usuarioSuplido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_DESDE")
	private Date fechaDesde;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HASTA")
	private Date fechaHasta;

	public UsuarioAutorizadorSolicitudSuministro getUsuarioSuplido() {
		return usuarioSuplido;
	}

	public void setUsuarioSuplido(
			UsuarioAutorizadorSolicitudSuministro usuarioSuplido) {
		this.usuarioSuplido = usuarioSuplido;
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

}

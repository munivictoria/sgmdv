
package com.trascender.saic.recurso.filtros;

import java.util.Calendar;
import java.util.Date;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;

public class FiltroLogLiquidacion extends FiltroAbstracto<LogLiquidacion> {

	private static final long serialVersionUID = -5364296191337435445L;

	private Obligacion obligacion;
	private TipoObligacion tipoObligacion;
	private Integer anio;
	private Integer nroPeriodo;
	private Integer nroCuota;
	private String usuario;
	private LogLiquidacion.Evento evento;
	private Date fechaDesde;
	private Date fechaHasta;

	public Obligacion getObligacion() {
		return obligacion;
	}

	public void setObligacion(Obligacion obligacion) {
		this.obligacion = obligacion;
	}

	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}

	public void setTipoObligacion(TipoObligacion tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getNroPeriodo() {
		return nroPeriodo;
	}

	public void setNroPeriodo(Integer nroPeriodo) {
		this.nroPeriodo = nroPeriodo;
	}

	public Integer getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(Integer nroCuota) {
		this.nroCuota = nroCuota;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LogLiquidacion.Evento getEvento() {
		return evento;
	}

	public void setEvento(LogLiquidacion.Evento evento) {
		this.evento = evento;
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

	public Date getFechaDesdeInicioDia() {
		if(fechaDesde != null) {
			Calendar fechaDesdeCalendar = Calendar.getInstance();
			fechaDesdeCalendar.setTime(fechaDesde);
			fechaDesdeCalendar.set(Calendar.MILLISECOND, 000);
			fechaDesdeCalendar.set(Calendar.SECOND, 00);
			fechaDesdeCalendar.set(Calendar.MINUTE, 00);
			fechaDesdeCalendar.set(Calendar.HOUR_OF_DAY, 00);

			return fechaDesdeCalendar.getTime();
		}
		return null;
	}

	public Date getFechaHastaFinDia() {
		if(fechaHasta != null) {
			Calendar fechaHastaCalendar = Calendar.getInstance();
			fechaHastaCalendar.setTime(fechaHasta);
			fechaHastaCalendar.set(Calendar.MILLISECOND, 999);
			fechaHastaCalendar.set(Calendar.SECOND, 59);
			fechaHastaCalendar.set(Calendar.MINUTE, 59);
			fechaHastaCalendar.set(Calendar.HOUR_OF_DAY, 23);

			return fechaHastaCalendar.getTime();
		}
		return null;
	}

}
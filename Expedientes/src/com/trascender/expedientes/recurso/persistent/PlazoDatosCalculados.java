/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.util.Util;

public class PlazoDatosCalculados {

	private List<DiaFeriado> listaTotalFeriados;
	private int cantidadDiasRestantes = -1;
	private Date fechaFinal;
	private Date fechaInicial;
	private int cantidadDias = -1;
	private boolean diasCorridos = false;
	private boolean vencido = false;

	public List<DiaFeriado> getListaTotalFeriados() {
		return listaTotalFeriados;
	}

	public void setListaTotalFeriados(List<DiaFeriado> listaTotalFeriados) {
		this.listaTotalFeriados = listaTotalFeriados;
	}

	public int getCantidadDiasRestantes() {
		return cantidadDiasRestantes;
	}

	public void setCantidadDiasRestantes(int cantidadDiasRestantes) {
		this.cantidadDiasRestantes = cantidadDiasRestantes;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public int getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(int cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	public boolean isDiasCorridos() {
		return diasCorridos;
	}

	public void setDiasCorridos(boolean diasCorridos) {
		this.diasCorridos = diasCorridos;
	}

	public boolean isVencido() {
		return vencido;
	}

	public void setVencido(boolean vencido) {
		this.vencido = vencido;
	}

	public PlazoDatosCalculados(Date fechaDesde, int cantidadDias, boolean diasCorridos, List<DiaFeriado> diasFeriados) {
		Collections.sort(diasFeriados, comparatorDiaFeriado);
		if(fechaDesde != null) {
			this.listaTotalFeriados = diasFeriados;
			this.fechaFinal = getFechaFin(fechaDesde, cantidadDias, diasCorridos);
			this.cantidadDiasRestantes = getCantidadDias(new Date(), fechaFinal, diasCorridos);
			this.fechaInicial = fechaDesde;
			this.cantidadDias = cantidadDias;
			this.diasCorridos = diasCorridos;
			this.setVencido(Util.isFechaAfterNoTima(new Date(), fechaFinal));
		}
	}

	private List<DiaFeriado> getListaFeriados(Date from) {
		DiaFeriado dia = null;
		List<DiaFeriado> retorno = new ArrayList<DiaFeriado>();
		for(DiaFeriado feriado : listaTotalFeriados) {
			if(feriado.getFecha().compareTo(from) >= 0) {
				dia = feriado;
			}
		}
		if(dia != null)
			retorno = listaTotalFeriados.subList(listaTotalFeriados.indexOf(dia), listaTotalFeriados.size() - 1);
		
		return retorno;
	}

	private Date incrementarFecha(Date fecha, int cantDias) {
		Calendar c = new GregorianCalendar();
		c.setTime(fecha);
		c.add(Calendar.DATE, cantDias);
		
		return c.getTime();
	}

	private Date getFechaFin(Date fechaInicio, int cantDias, boolean diasCorridos) {
		// getListaFeriados(fechaInicio);
		Date fechaFin = incrementarFecha(fechaInicio, cantDias);
		int inhabiles = getTotalInhablies(fechaInicio, fechaFin);

		if(!diasCorridos) {
			while(inhabiles > 0) {
				Date fechaProxima = incrementarFecha(fechaFin, inhabiles);
				
				// paso al siguiente d�a para no contarlo dos veces
				fechaFin = incrementarFecha(fechaFin, 1);
				inhabiles = getTotalInhablies(fechaFin, fechaProxima);
				fechaFin = fechaProxima;
			}
		}
		
		return fechaFin;
	}

	private int getTotalInhablies(Date date1, Date date2) {
		return getCantidadFeriados(date1, date2, false) + getCantSabYDom(date1, date2);
	}

	// excluye s�bados y domingos
	private int getCantidadFeriados(Date date1, Date date2, boolean contarFinDeSemana) {
		int cant = 0;
		Iterator<DiaFeriado> i = getListaFeriados(date1).iterator();
		while(i.hasNext()) {
			DiaFeriado diaFeriado = (DiaFeriado) i.next();
			if(diaFeriado.getFecha().compareTo(date1) >= 0) {
				if(diaFeriado.getFecha().compareTo(date2) > 0) {
					break;
				}
				Calendar c = new GregorianCalendar();
				c.setTime(diaFeriado.getFecha());
				if(contarFinDeSemana) {
					cant++;
				} else if(c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
					cant++;
				}
			}
		}

		return cant;
	}

	private int getCantSabYDom(Date date1, Date date2) {
		Calendar cInicial = new GregorianCalendar();
		cInicial.setTime(date1);
		Calendar cFinal = new GregorianCalendar();
		cFinal.setTime(date2);
		int cant = 0;
		while(cInicial.before(cFinal) || cInicial.equals(cFinal)) {
			if(cInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				cant++;
			}
			cInicial.add(Calendar.DATE, 1);
		}
		
		return cant;
	}

	private int getCantidadDias(Date date1, Date date2, boolean corridos) {
		Long cant = 0L;
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(date1);
		c2.setTime(date2);
		long diff = c2.getTimeInMillis() - c1.getTimeInMillis();

		cant = diff / (24 * 60 * 60 * 1000);
		if(!corridos) {
			cant -= Long.parseLong(Integer.valueOf(getCantidadFeriados(date1, date2, false) + getCantSabYDom(date1, date2)).toString());
		}

		/**
		 * Agrego un dia mas para tener en cuenta el dia siguiente, o sea, que el dia del vencimiento tambien cuente como vigente.
		 */
		return Integer.parseInt(cant.toString()) + 1;
	}

	private static Comparator<DiaFeriado> comparatorDiaFeriado = new Comparator<DiaFeriado>() {

		public int compare(DiaFeriado d1, DiaFeriado d2) {
			return d1.getFecha().compareTo(d2.getFecha());
		}
	};

}
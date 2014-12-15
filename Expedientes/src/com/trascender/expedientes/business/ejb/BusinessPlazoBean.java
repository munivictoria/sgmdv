package com.trascender.expedientes.business.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.expedientes.business.interfaces.BusinessPlazo;
import com.trascender.expedientes.recurso.persistent.Plazo;
import com.trascender.expedientes.recurso.persistent.PlazoProcedimiento;
import com.trascender.framework.recurso.persistent.DiaFeriado;

@Stateless(name = "BusinessPlazo")
public class BusinessPlazoBean implements BusinessPlazo, Serializable {

	private static final long serialVersionUID = 719094826877796926L;

	private List<DiaFeriado> listaFeriados = new ArrayList<DiaFeriado>();
	public int cantidadDiasRestantes;
	public Date fechaInicial;
	public Date fechaFinal;
	public int cantidadDias;
	public boolean diasCorridos;

	@PersistenceContext(name = "Vipians")
	private EntityManager entity;

	public List<DiaFeriado> getDiasFeriadosEntre(Date date1, Date date2) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, DiaFeriado.class);
		if (date1 != null) {
			locCriterio.add(Restriccion.MAYOR("fecha", date1));
		}
		if (date2 != null) {
			locCriterio.add(Restriccion.MENOR("fecha", date2));
		}
		locCriterio.add(Orden.ASC("fecha"));
		return locCriterio.list();

	}

	public void inicializarPlazoExpediente(Date fechaDesde, Plazo pPlazo) {
		if (pPlazo != null) {
			
			PlazoProcedimiento plazoProcedimiento = pPlazo.getPlazoProcedimiento();
			
			
			this.listaFeriados = getListaFeriados(fechaDesde);
			this.fechaFinal = getFechaFin(fechaDesde, cantidadDias, diasCorridos);
			this.cantidadDiasRestantes = getCantidadDias(new Date(), fechaFinal, diasCorridos);
			this.fechaInicial = fechaDesde;
			this.cantidadDias = plazoProcedimiento.getDias();
			this.diasCorridos = plazoProcedimiento.isDiasCorridos();
			
			
			
			
			
		}

	}

	private List<DiaFeriado> getListaFeriados(Date from) {
		try {
			listaFeriados = getDiasFeriadosEntre(from, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(listaFeriados, comparatorDiaFeriado);
		return listaFeriados;
	}

	private static Comparator<DiaFeriado> comparatorDiaFeriado = new Comparator<DiaFeriado>() {
		public int compare(DiaFeriado d1, DiaFeriado d2) {
			return d1.getFecha().compareTo(d2.getFecha());
		}
	};

	private static Date incrementarFecha(Date fecha, int cantDias) {
		Calendar c = new GregorianCalendar();
		c.setTime(fecha);
		c.add(Calendar.DATE, cantDias);
		return c.getTime();
	}

	private Date getFechaFin(Date fechaInicio, int cantDias, boolean diasCorridos) {
		getListaFeriados(fechaInicio);
		Date fechaFin = incrementarFecha(fechaInicio, cantDias);
		int inhabiles = getTotalInhablies(fechaInicio, fechaFin);

		if (!diasCorridos) {
			while (inhabiles > 0) {
				Date fechaProxima = incrementarFecha(fechaFin, inhabiles);
				// paso al siguiente día para no contarlo dos veces
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

	// excluye sábados y domingos
	private int getCantidadFeriados(Date date1, Date date2, boolean finDeSemana) {
		int cant = 0;
		Iterator<DiaFeriado> i = listaFeriados.iterator();
		while (i.hasNext()) {
			DiaFeriado diaFeriado = (DiaFeriado) i.next();
			if (diaFeriado.getFecha().compareTo(date1) >= 0) {
				if (diaFeriado.getFecha().compareTo(date2) > 0) {
					break;
				}
				Calendar c = new GregorianCalendar();
				c.setTime(diaFeriado.getFecha());
				if (finDeSemana) {
					cant++;
				} else if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
						&& c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
					cant++;
				}

			}
		}

		return cant;
	}

	private static int getCantSabYDom(Date date1, Date date2) {
		Calendar cInicial = new GregorianCalendar();
		cInicial.setTime(date1);
		Calendar cFinal = new GregorianCalendar();
		cFinal.setTime(date2);
		int cant = 0;
		while (cInicial.before(cFinal) || cInicial.equals(cFinal)) {
			if (cInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| cInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
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
		if (!corridos) {
			cant -= Long.parseLong(Integer.valueOf(
					getCantidadFeriados(date1, date2, false) + getCantSabYDom(date1, date2))
					.toString());
		}

		return Integer.parseInt(cant.toString());
	}

	@Override
	public void actualizarPlazoExpediente(Plazo pPlazo) {
		// TODO Auto-generated method stub
		
	}

	

}

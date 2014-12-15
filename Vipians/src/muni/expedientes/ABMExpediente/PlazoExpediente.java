package muni.expedientes.ABMExpediente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.presentacion.conversores.Conversor;

public class PlazoExpediente {


	private List<DiaFeriado> listaFeriados = new ArrayList<DiaFeriado>();
	public int cantidadDiasRestantes;
	public Date fechaInicial;
	public Date fechaFinal;
	public int cantidadDias;
	public boolean diasCorridos;

	public PlazoExpediente(Date fechaDesde, int cantidadDias, boolean diasCorridos) {
		this.listaFeriados = getListaFeriados(fechaDesde);
		this.fechaFinal = getFechaFin(fechaDesde, cantidadDias, diasCorridos);
		this.cantidadDiasRestantes = getCantidadDias(new Date(), fechaFinal, diasCorridos);
		this.fechaInicial = fechaDesde;
		this.cantidadDias = cantidadDias;
		this.diasCorridos = diasCorridos;

	}

	private List<DiaFeriado> getListaFeriados(Date from) {
		try {
			listaFeriados = getCommunicationExpedientesBean().getRemoteSystemExpedientes()
					.getDiasFeriadosEntre(from, null);

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
		System.out.println(Conversor.getStringDeFechaCorta(fechaInicio) + " -"
				+ Conversor.getStringDeFechaCorta(fechaFin) + "... " + inhabiles);
		if (!diasCorridos) {
			while (inhabiles > 0) {
				Date fechaProxima = incrementarFecha(fechaFin, inhabiles);
				// paso al siguiente d\355a para no contarlo dos veces
				fechaFin = incrementarFecha(fechaFin, 1);
				inhabiles = getTotalInhablies(fechaFin, fechaProxima);
				System.out.println(Conversor.getStringDeFechaCorta(fechaFin) + " -"
						+ Conversor.getStringDeFechaCorta(fechaProxima) + "... " + inhabiles);
				fechaFin = fechaProxima;
			}
		}
		return fechaFin;
	}

	private int getTotalInhablies(Date date1, Date date2) {
		return getCantidadFeriados(date1, date2, false) + getCantSabYDom(date1, date2);
	}

	// excluye s\341bados y domingos
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

		cant =  diff / (24 * 60 * 60 * 1000);
		if (!corridos) {
			cant -= Long.parseLong(Integer.valueOf(getCantidadFeriados(date1, date2, false) + getCantSabYDom(date1, date2)).toString());
		}

		return Integer.parseInt(cant.toString());
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {

		return (muni.CommunicationExpedientesBean) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("CommunicationExpedientesBean");
	}

	
}

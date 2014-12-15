
package com.trascender.framework.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

public class BusquedaPorLog implements Serializable {
	private static final long serialVersionUID = 2789356425744395523L;

	private String nombrePropiedad;
	private Evento evento;
	private String valor;
	private Date fechaDesde;
	private Date fechaHasta;
	private String usuario;
	private String comentario;

	public enum Evento {
		SE_CREO, SE_MODIFICO, VALOR_NUEVO_IGUAL, VALOR_VIEJO_IGUAL
	}

	public String getNombrePropiedad() {
		return nombrePropiedad;
	}

	public void setNombrePropiedad(String pNombrePropiedad) {
		nombrePropiedad = pNombrePropiedad;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento pEvento) {
		evento = pEvento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String pValor) {
		valor = pValor;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date pFechaDesde) {
		fechaDesde = pFechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date pFechaHasta) {
		fechaHasta = pFechaHasta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String pUsuario) {
		usuario = pUsuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String pComentario) {
		comentario = pComentario;
	}

	public static void addRestriccionesCriterio(Criterio pCriterio, long pSerialVerion, String pNombrePropiedadId, List<BusquedaPorLog> pLista) {
		addRestriccionesCriterio(pCriterio, pSerialVerion, pNombrePropiedadId, pLista, null);
	}

	public static void addRestriccionesCriterio(Criterio pCriterio, long pSerialVerion, String pNombrePropiedadId, List<BusquedaPorLog> pLista, String pAlias) {
		if(pLista != null) {
			if(pAlias == null || pAlias.equals(""))
				pAlias = "e";
			for(int i = 0; i < pLista.size(); i++) {
				BusquedaPorLog cadaBusqueda = pLista.get(i);

				if((cadaBusqueda.getComentario() != null && !cadaBusqueda.getComentario().equals("")) || cadaBusqueda.getEvento() != null || cadaBusqueda.getFechaDesde() != null
						|| cadaBusqueda.getFechaHasta() != null || (cadaBusqueda.getNombrePropiedad() != null && !cadaBusqueda.getNombrePropiedad().equals(""))
						|| (cadaBusqueda.getUsuario() != null && !cadaBusqueda.getUsuario().equals("")) || (cadaBusqueda.getValor() != null && !cadaBusqueda.getValor().equals(""))) {

					if(cadaBusqueda.getFechaDesde() != null) {
						Calendar fechaDesdeCalendar = Calendar.getInstance();
						fechaDesdeCalendar.setTime(cadaBusqueda.getFechaDesde());
						fechaDesdeCalendar.set(Calendar.MILLISECOND, 000);
						fechaDesdeCalendar.set(Calendar.SECOND, 00);
						fechaDesdeCalendar.set(Calendar.MINUTE, 00);
						fechaDesdeCalendar.set(Calendar.HOUR_OF_DAY, 00);
						cadaBusqueda.setFechaDesde(fechaDesdeCalendar.getTime());
					}

					if(cadaBusqueda.getFechaHasta() != null) {
						Calendar fechaHastaCalendar = Calendar.getInstance();
						fechaHastaCalendar.setTime(cadaBusqueda.getFechaHasta());
						fechaHastaCalendar.set(Calendar.MILLISECOND, 999);
						fechaHastaCalendar.set(Calendar.SECOND, 59);
						fechaHastaCalendar.set(Calendar.MINUTE, 59);
						fechaHastaCalendar.set(Calendar.HOUR_OF_DAY, 23);
						cadaBusqueda.setFechaHasta(fechaHastaCalendar.getTime());
					}

					String cadaLogAuditoria = "cadaLog_" + i;
					pCriterio.addEntidadCartesiana("LogAuditoria", cadaLogAuditoria);
					pCriterio.add(Restriccion.JPQL(cadaLogAuditoria + ".idEntidad = " + pAlias + "." + pNombrePropiedadId)).add(
							Restriccion.IGUAL(cadaLogAuditoria + ".idRecurso", pSerialVerion));

					if(cadaBusqueda.getNombrePropiedad().startsWith(">")) {
						String nomProp = cadaBusqueda.getNombrePropiedad().substring(2);
						pCriterio.add(Restriccion.LIKE(cadaLogAuditoria + ".propiedad", nomProp, false, Posicion.AL_FINAL));
					} else {
						pCriterio.add(Restriccion.LIKE(cadaLogAuditoria + ".propiedad", cadaBusqueda.getNombrePropiedad()));
					}

					pCriterio.add(Restriccion.LIKE(cadaLogAuditoria + ".usuario.user", cadaBusqueda.getUsuario()))
							.add(Restriccion.ILIKE(cadaLogAuditoria + ".comentario", cadaBusqueda.getComentario()))
							.add(Restriccion.MAYOR(cadaLogAuditoria + ".fecha", cadaBusqueda.getFechaDesde()))
							.add(Restriccion.MENOR(cadaLogAuditoria + ".fecha", cadaBusqueda.getFechaHasta())).setDistinct(true);

					if(cadaBusqueda.getEvento() != null) {
						if(cadaBusqueda.getEvento().equals(Evento.VALOR_NUEVO_IGUAL)) {
							pCriterio.add(Restriccion.ILIKE(cadaLogAuditoria + ".valorNuevo", cadaBusqueda.getValor()));
						} else if(cadaBusqueda.getEvento().equals(Evento.VALOR_VIEJO_IGUAL)) {
							pCriterio.add(Restriccion.ILIKE(cadaLogAuditoria + ".valorAnterior", cadaBusqueda.getValor()));
						} else if(cadaBusqueda.getEvento().equals(Evento.SE_CREO)) {
							pCriterio.add(Restriccion.IGUAL(cadaLogAuditoria + ".tipo", LogAuditoria.Tipo.CREO));
						}
					}
				}
			}
		}
	}
}

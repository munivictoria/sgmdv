/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.expedientes.business.interfaces.BusinessExpedientes;
import com.trascender.expedientes.business.interfaces.BusinessPlazo;
import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Plazo;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.expedientes.system.interfaces.SystemExpedientes;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "SystemExpedientes")
public class SystemExpedientesBean implements SystemExpedientes {

	private long llave = 0;

	@EJB
	private BusinessExpedientes locExpedientes;

	@EJB
	private BusinessPlazo locPlazo;

	@Override
	public void setLlave(long pLlave) {
		llave = pLlave;
	}

	@Override
	public void addExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws Exception, RemoteException {
		locExpedientes.addExpediente(pExpediente, pComentario, pUsuario);
	}

	@Override
	public void updateExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws Exception, RemoteException {
		locExpedientes.updateExpediente(pExpediente, pComentario, pUsuario);
	}

	@Override
	public void deleteExpediente(Expediente pExpediente, Usuario pUsuario) throws Exception, RemoteException {
		locExpedientes.deleteExpediente(pExpediente, pUsuario);
	}

	@Override
	public Expediente getExpedientePorId(long pId) throws Exception, RemoteException {
		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(llave);
		
		return locExpedientes.getExpedientePorId(pId, locUsuario);
	}

	@Override
	public FiltroExpediente findListaExpediente(FiltroExpediente pFiltro) throws Exception, RemoteException {
		pFiltro.setUsuario(SecurityMgr.getInstance().getUsuario(llave));
		
		return locExpedientes.findListaExpediente(pFiltro);
	}

	@Override
	public Tramite getTramitePorId(long idTramite) {
		return locExpedientes.getTramitePorId(idTramite);
	}
	
	@Override
	public void updateDocumentoSalida(Documento pDocumento, Usuario pUsuario) {
		locExpedientes.updateDocumentoSalida(pDocumento, pUsuario);
	}
	
	@Override
	public void registrarHitoDocumentoSalida(Documento pDocumento, Usuario pUsuario, boolean pProcesando) {
		locExpedientes.registrarHitoDocumentoSalida(pDocumento, pUsuario, pProcesando);
	}

	@Override
	public List<DiaFeriado> getDiasFeriadosEntre(Date date1, Date date2) throws Exception {
		return locExpedientes.getDiasFeriadosEntre(date1, date2);
	}

	@Override
	public void inicializarPlazo(Date fechaDesde, Plazo plazo) throws Exception {
		locPlazo.inicializarPlazoExpediente(fechaDesde, plazo);
	}

	@Override
	public List<Expediente> getListaExpedienteSoyResponsable(long llave) throws Exception {
		return locExpedientes.getListaExpedienteSoyResponsable(llave);
	}

	@Override
	public List<Expediente> getListaExpedientePorTramites() throws Exception {
		locExpedientes.getListaExpedientePorTramites();
		return null;
	}

	@Override
	public void actualizarFaseActivaActual(Expediente pExpediente, String pComentario, Usuario pUsuario) {
		locExpedientes.actualizarFaseActivaActual(pExpediente, pComentario, pUsuario);
	}

	@Override
	public JasperPrint getReporteAltasExpedientes(Expediente pExpediente, Usuario pUsuario) throws Exception {
		return locExpedientes.getReporteAltasExpedientes(pExpediente, pUsuario);
	}

	@Override
	public Long getExpedientePorNodoProcedimiento(long idNodoProcedimiento) {
		return locExpedientes.getExpedientePorNodoProcedimiento(idNodoProcedimiento);
	}

}
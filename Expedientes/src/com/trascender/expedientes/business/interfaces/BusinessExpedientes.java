/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.business.interfaces;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

@Local
public interface BusinessExpedientes {

	public final static String JNDI_NAME = "BusinessExpedientes";

	public void addExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws Exception, RemoteException;

	public void updateExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws Exception, RemoteException;

	public void deleteExpediente(Expediente pExpediente, Usuario pUsuario) throws Exception, RemoteException;

	public Expediente getExpedientePorId(long pId, Usuario locUsuario) throws Exception, RemoteException;

	public FiltroExpediente findListaExpediente(FiltroExpediente pFiltro) throws Exception, RemoteException;

	public Tramite getTramitePorId(long idTramite);
	
	public void updateDocumentoSalida(Documento pDocumento, Usuario pUsuario);
	
	public void registrarHitoDocumentoSalida(Documento pDocumento, Usuario pUsuario, boolean pProcesando);

	public List<DiaFeriado> getDiasFeriadosEntre(Date date1, Date date2) throws Exception;

	public abstract List<Expediente> getListaExpedienteSoyResponsable(long llave) throws Exception;

	public abstract List<Expediente> getListaExpedientePorTramites() throws Exception;

	public void actualizarFaseActivaActual(Expediente pExpediente, String pComentario, Usuario pUsuario);

	public JasperPrint getReporteAltasExpedientes(Expediente pExpediente, Usuario pUsuario) throws Exception;

	public Long getExpedientePorNodoProcedimiento(long idNodoProcedimiento);

}
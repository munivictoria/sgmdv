package com.trascender.expedientes.business.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

@Local
public interface BusinessExpedientes {

	public final static String JNDI_NAME = "BusinessExpedientes";

	public void addExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws java.lang.Exception,
			java.rmi.RemoteException;

	public void updateExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void deleteExpediente(Expediente pExpediente, Usuario pUsuario)
			throws java.lang.Exception, java.rmi.RemoteException;

	public Expediente getExpedientePorId(long pId, Usuario locUsuario) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroExpediente findListaExpediente(FiltroExpediente pFiltro)
			throws java.lang.Exception, java.rmi.RemoteException;

	public Tramite getTramitePorId(long idTramite);

	public List<DiaFeriado> getDiasFeriadosEntre(Date date1, Date date2) throws Exception;

	public abstract List<Expediente> getListaExpedienteSoyResponsable(long llave) throws Exception;

	public abstract List<Expediente> getListaExpedientePorTramites() throws Exception;

	public void actualizarFaseActivaActual(Expediente pExpediente, String pComentario, Usuario pUsuario);
	
	public JasperPrint getReporteAltasExpedientes(Expediente pExpediente,Usuario pUsuario) throws Exception;
	

}

package com.trascender.habilitaciones.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.filtros.FiltroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;

@Remote
public interface SystemExencionObligacion{
	
	public static final String JNDI_NAME = "ejb/SystemExencionObligacion/remote";

	public void setLlave(long pLlave) throws java.rmi.RemoteException;
	
	public ExencionObligacion addExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception, RemoteException;
	
	public ExencionObligacion updateExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception, RemoteException;
	
	public void deleteExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception, RemoteException;
	
	public ExencionObligacion getExencionObligacionByID(Long pId) throws Exception, RemoteException;
	
	public FiltroExencionObligacion findListaExencionesObligacion(FiltroExencionObligacion pFiltro) throws Exception, RemoteException;
	
	public void autorizarExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception, RemoteException;
	
	public void terminarExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception, RemoteException;
	
}

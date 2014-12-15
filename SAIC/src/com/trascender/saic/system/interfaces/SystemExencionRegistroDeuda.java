package com.trascender.saic.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;

@Remote
public interface SystemExencionRegistroDeuda {
	
	public static final String JNDI_NAME="ejb/SystemExencionRegistroDeuda/remote";

	public void setLlave(long pLlave) throws java.rmi.RemoteException;
	
	public ExencionRegistroDeuda addExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception, RemoteException;
	
	public ExencionRegistroDeuda updateExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception, RemoteException;

	public ExencionRegistroDeuda deleteExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception, RemoteException;
	
	public ExencionRegistroDeuda getExencionRegistroDeudaByID(Long pId) throws Exception, RemoteException;
	
	public List<ExencionRegistroDeuda> findListaExencionesRegistrosDeuda(String pNombre, 
			CuotaLiquidacion pCuota,
			PeriodoLiquidacion pPeriodo,
			CalendarioMunicipal pCalendario,
			Estado pEstado,
			Double pPorcentaje) throws Exception;
			
	public void autorizarExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception, RemoteException;
	
	public void terminarExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception, RemoteException;
	
}

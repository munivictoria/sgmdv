/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.expedientes.exception.ProcedimientosException;
import com.trascender.expedientes.recurso.filtro.FiltroFaseProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Usuario;

@Remote
public interface SystemProcedimientos {

	public final static String JNDI_NAME = "SystemProcedimientos/remote";

	public void setLlave(long pLlave) throws RemoteException;

	public Procedimiento addProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException, ProcedimientosException;

	public Procedimiento updateProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException, ProcedimientosException;

	public void deleteProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException;

	public Procedimiento getProcedimientoPorId(long pId) throws Exception, RemoteException;

	public FiltroProcedimiento findListaProcedimiento(FiltroProcedimiento pFiltro) throws Exception, RemoteException;

	public void addFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception, RemoteException;

	public void updateFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception, RemoteException;

	public void deleteFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception, RemoteException;

	public FaseProcedimiento getFaseProcedimientoPorId(long pId) throws Exception, RemoteException;

	public FiltroFaseProcedimiento findListaFaseProcedimiento(FiltroFaseProcedimiento pFiltro) throws Exception, RemoteException;

	public void addTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento) throws Exception, RemoteException;

	public void updateTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento) throws Exception, RemoteException;

	public void deleteTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento) throws Exception, RemoteException;

	public TramiteProcedimiento getTramiteProcedimientoPorId(long pId) throws Exception, RemoteException;

	public FiltroTramiteProcedimiento findListaTramiteProcedimiento(FiltroTramiteProcedimiento pFiltro) throws Exception, RemoteException;

	public Responsable getResponsablePorId(Long pId);

	public List<Procedimiento> getListaProcedimientosPuedoEmpezar(Usuario pUsuario) throws TrascenderException;

}
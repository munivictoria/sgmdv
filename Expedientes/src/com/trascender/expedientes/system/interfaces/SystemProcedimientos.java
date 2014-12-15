package com.trascender.expedientes.system.interfaces;

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

	public void setLlave(long pLlave) throws java.rmi.RemoteException;

	public void addProcedimiento(Procedimiento pProcedimiento) throws java.lang.Exception,
			java.rmi.RemoteException, ProcedimientosException;

	public void updateProcedimiento(Procedimiento pProcedimiento) throws java.lang.Exception,
			java.rmi.RemoteException, ProcedimientosException;

	public void deleteProcedimiento(Procedimiento pProcedimiento) throws java.lang.Exception,
			java.rmi.RemoteException;

	public Procedimiento getProcedimientoPorId(long pId) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroProcedimiento findListaProcedimiento(FiltroProcedimiento pFiltro)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void addFaseProcedimiento(FaseProcedimiento pFaseProcedimiento)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void updateFaseProcedimiento(FaseProcedimiento pFaseProcedimiento)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void deleteFaseProcedimiento(FaseProcedimiento pFaseProcedimiento)
			throws java.lang.Exception, java.rmi.RemoteException;

	public FaseProcedimiento getFaseProcedimientoPorId(long pId) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroFaseProcedimiento findListaFaseProcedimiento(FiltroFaseProcedimiento pFiltro)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void addTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void updateTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void deleteTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento)
			throws java.lang.Exception, java.rmi.RemoteException;

	public TramiteProcedimiento getTramiteProcedimientoPorId(long pId) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroTramiteProcedimiento findListaTramiteProcedimiento(
			FiltroTramiteProcedimiento pFiltro) throws java.lang.Exception,
			java.rmi.RemoteException;

	public Responsable getResponsablePorId(Long pId);
	
	public List<Procedimiento> getListaProcedimientosPuedoEmpezar(Usuario pUsuario) throws TrascenderException;
	
	
}

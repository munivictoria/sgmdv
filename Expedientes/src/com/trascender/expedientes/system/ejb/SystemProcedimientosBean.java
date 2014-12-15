package com.trascender.expedientes.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.trascender.expedientes.business.interfaces.BusinessProcedimientos;
import com.trascender.expedientes.exception.ProcedimientosException;
import com.trascender.expedientes.recurso.filtro.FiltroFaseProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.expedientes.system.interfaces.SystemProcedimientos;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Usuario;

@Stateless(name = "SystemProcedimientos")
public class SystemProcedimientosBean implements SystemProcedimientos {

	@SuppressWarnings("unused")
	private long llave = 0;

	@EJB
	private BusinessProcedimientos locProcedimientos;

	@Override
	public void setLlave(long pLlave) throws RemoteException {
		llave = pLlave;
		
	}

	@Override
	public void addProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException, ProcedimientosException {
		locProcedimientos.addProcedimiento(pProcedimiento);
		
	}

	@Override
	public void updateProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException, ProcedimientosException {
		locProcedimientos.updateProcedimiento(pProcedimiento);
	}

	@Override
	public void deleteProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException {
		locProcedimientos.deleteProcedimiento(pProcedimiento);
			}

	@Override
	public Procedimiento getProcedimientoPorId(long pId) throws Exception, RemoteException {
		return locProcedimientos.getProcedimientoPorId(pId);
	}

	@Override
	public FiltroProcedimiento findListaProcedimiento(FiltroProcedimiento pFiltro)
			throws Exception, RemoteException {
		return locProcedimientos.findListaProcedimiento(pFiltro);
	}

	@Override
	public void addFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception,
			RemoteException {
		locProcedimientos.addFaseProcedimiento(pFaseProcedimiento);
	}

	@Override
	public void updateFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception,
			RemoteException {
	locProcedimientos.updateFaseProcedimiento(pFaseProcedimiento);
	}

	@Override
	public void deleteFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception,
			RemoteException {
  locProcedimientos.deleteFaseProcedimiento(pFaseProcedimiento);
		}

	@Override
	public FaseProcedimiento getFaseProcedimientoPorId(long pId) throws Exception, RemoteException {
		return locProcedimientos.getFaseProcedimientoPorId(pId);
	}

	@Override
	public FiltroFaseProcedimiento findListaFaseProcedimiento(FiltroFaseProcedimiento pFiltro)
			throws Exception, RemoteException {
		return locProcedimientos.findListaFaseProcedimiento(pFiltro);
	}

	@Override
	public void addTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento)
			throws Exception, RemoteException {
		locProcedimientos.addTramiteProcedimiento(pTramiteProcedimiento);
		
	}

	@Override
	public void updateTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento)
			throws Exception, RemoteException {
locProcedimientos.updateTramiteProcedimiento(pTramiteProcedimiento);		
	}

	@Override
	public void deleteTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento)
			throws Exception, RemoteException {
 locProcedimientos.deleteTramiteProcedimiento(pTramiteProcedimiento); 
		
	}

	@Override
	public TramiteProcedimiento getTramiteProcedimientoPorId(long pId) throws Exception,
			RemoteException {

		return locProcedimientos.getTramiteProcedimientoPorId(pId);
	}

	@Override
	public FiltroTramiteProcedimiento findListaTramiteProcedimiento(
			FiltroTramiteProcedimiento pFiltro) throws Exception, RemoteException {
 		return locProcedimientos.findListaTramiteProcedimiento(pFiltro);
	}

	@Override
	public Responsable getResponsablePorId(Long pId) {
		return locProcedimientos.getResponsablePorId(pId);
	}

	@Override
	public List<Procedimiento> getListaProcedimientosPuedoEmpezar(
			Usuario pUsuario) throws TrascenderException {
		//TODO Pedir permiso Agregar Expediente
		return this.locProcedimientos.getListaProcedimientosPuedoEmpezar(pUsuario);
	}
	
	
}

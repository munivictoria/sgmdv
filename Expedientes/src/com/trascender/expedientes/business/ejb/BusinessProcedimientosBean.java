
package com.trascender.expedientes.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.expedientes.business.interfaces.BusinessProcedimientos;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.exception.ProcedimientosException;
import com.trascender.expedientes.recurso.filtro.FiltroFaseProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.NodoProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "BusinessProcedimientos")
public class BusinessProcedimientosBean implements BusinessProcedimientos {

	@PersistenceContext(name = "Vipians")
	private EntityManager entity;
	private static final long serialVersionUID = -5314134264098482795L;
	public static final String NAME = "EXP|Adm. Procedimientos";

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre(NAME);

		Recurso procedimiento = new Recurso();
		procedimiento.setIdRecurso(Procedimiento.serialVersionUID);
		procedimiento.setNombre("Procedimiento");
		procedimiento.setAtributosConsultables("Nombre", "nombre");
		procedimiento.setClase(Procedimiento.class);
		grupo.getListaRecursos().add(procedimiento);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@Override
	public void addProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException {
		validacionProcedimientoNombreRepetido(pProcedimiento);
		entity.persist(pProcedimiento);
	}

	@Override
	public void updateProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException {
		validacionProcedimientoNombreRepetido(pProcedimiento);
		entity.merge(pProcedimiento);
	}

	@Override
	public void deleteProcedimiento(Procedimiento pProcedimiento) throws Exception, RemoteException {
		pProcedimiento.setEstado(EstadoPlantilla.BAJA);

	}

	@Override
	public Procedimiento getProcedimientoPorId(long pId) throws Exception, RemoteException {
		Procedimiento locProcedimiento = (Procedimiento) Criterio.getInstance(this.entity, Procedimiento.class).add(Restriccion.IGUAL("idNodoProcedimiento", pId)).uniqueResult();

		locProcedimiento.toString();
		locProcedimiento.getListaAtributosDinamicos().size();
		// locProcedimiento.getListaFasesProcedimiento().size();
		// for (FaseProcedimiento f :
		// locProcedimiento.getListaFasesProcedimiento()) {
		// f.getListaTramitesProcedimientos().size();
		// }
		getLists(locProcedimiento);
		for(FaseProcedimiento fp : locProcedimiento.getListaFasesProcedimiento()) {
			fp.getListaFasesEspeciales().size();
		}

		// Responsable locResponsable = locProcedimiento.getResponsable();
		// if (locResponsable != null) {
		// locResponsable.getAreas().size();
		// locResponsable.getUsuarios().size();
		// }

		return entity.find(Procedimiento.class, pId);
	}

	private void getLists(NodoProcedimiento nodo) {
		nodo.getListaNodosHijos().size();
		Responsable locResponsableN = nodo.getResponsable();
		if(locResponsableN != null) {
			locResponsableN.getAreas().size();
			locResponsableN.getUsuarios().size();
			locResponsableN.getListaUsuariosExtensores().size();
		}
		for(NodoProcedimiento nh : nodo.getListaNodosHijos()) {
			getLists(nh);
		}
	}

	@Override
	public FiltroProcedimiento findListaProcedimiento(FiltroProcedimiento pFiltro) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(entity, Procedimiento.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	@Override
	public void addFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception, RemoteException {
		entity.persist(pFaseProcedimiento);
	}

	@Override
	public void updateFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception, RemoteException {
		entity.merge(pFaseProcedimiento);
	}

	@Override
	public void deleteFaseProcedimiento(FaseProcedimiento pFaseProcedimiento) throws Exception, RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public FaseProcedimiento getFaseProcedimientoPorId(long pId) throws Exception, RemoteException {
		FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) Criterio.getInstance(this.entity, FaseProcedimiento.class).add(Restriccion.IGUAL("idNodoProcedimiento", pId))
				.uniqueResult();

		refrescarFasesProcedimiento(locFaseProcedimiento);
		// locFaseProcedimiento.getListaTramitesProcedimientos().size();
		getLists(locFaseProcedimiento);

		return locFaseProcedimiento;
	}

	private void refrescarFasesProcedimiento(FaseProcedimiento pFaseProcedimiento) {
		pFaseProcedimiento.toString();
		for(TramiteProcedimiento tp : pFaseProcedimiento.getListaTramitesProcedimientos()) {
			tp.getListaDocumentosProcedimiento().size();
		}
		for(FaseProcedimiento fp : pFaseProcedimiento.getListaFasesEspeciales()) {
			refrescarFasesProcedimiento(fp);
		}
	}

	@Override
	public FiltroFaseProcedimiento findListaFaseProcedimiento(FiltroFaseProcedimiento pFiltro) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(entity, FaseProcedimiento.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));
		pFiltro.procesarYListar(locCriterio);
		return pFiltro;

	}

	@Override
	public void addTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento) throws Exception, RemoteException, ProcedimientosException {
		entity.persist(pTramiteProcedimiento);
	}

	@Override
	public void updateTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento) throws Exception, RemoteException, ProcedimientosException {
		entity.merge(pTramiteProcedimiento);

	}

	@Override
	public void deleteTramiteProcedimiento(TramiteProcedimiento pTramiteProcedimiento) throws Exception, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public TramiteProcedimiento getTramiteProcedimientoPorId(long pId) throws Exception, RemoteException {
		TramiteProcedimiento locTramiteP = (TramiteProcedimiento) Criterio.getInstance(this.entity, TramiteProcedimiento.class).add(Restriccion.IGUAL("idNodoProcedimiento", pId))
				.uniqueResult();
		locTramiteP.toString();
		// if (locTramiteP.getResponsable() != null) {
		// locTramiteP.getResponsable().getAreas().size();
		// locTramiteP.getResponsable().getUsuarios().size();
		// }
		getLists(locTramiteP);
		return entity.find(TramiteProcedimiento.class, pId);
	}

	@Override
	public FiltroTramiteProcedimiento findListaTramiteProcedimiento(FiltroTramiteProcedimiento pFiltro) throws Exception, RemoteException {

		Criterio locCriterio = Criterio.getInstance(entity, TramiteProcedimiento.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));
		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	@Override
	public Responsable getResponsablePorId(long pId) {
		Responsable locResponsable = (Responsable) Criterio.getInstance(this.entity, Responsable.class).add(Restriccion.IGUAL("idResponsable", pId)).uniqueResult();
		locResponsable.toString();
		locResponsable.getAreas().size();
		locResponsable.getUsuarios().size();
		locResponsable.getListaUsuariosExtensores().size();
		return entity.find(Responsable.class, pId);
	}

	private void validacionProcedimientoNombreRepetido(Procedimiento pProcedimiento) throws RemoteException, Exception, ProcedimientosException {
		FiltroProcedimiento fdc = new FiltroProcedimiento();
		fdc.setNombre(pProcedimiento.getNombre());
		fdc = findListaProcedimiento(fdc);
		if(pProcedimiento.getIdNodoProcedimiento() > 0 && fdc.getListaResultados().size() > 1)
			throw new ProcedimientosException(1);
		else if(!fdc.getListaResultados().isEmpty() && pProcedimiento.getIdNodoProcedimiento() <= 0)
			throw new ProcedimientosException(1);
	}

	@Override
	public List<Procedimiento> getListaProcedimientosPuedoEmpezar(Usuario pUsuario) {
		FiltroProcedimiento locFiltro = new FiltroProcedimiento();
		// TODO Poner estados ALTA
		List<Procedimiento> locListaProcedimientos = new ArrayList<Procedimiento>();
		try {
			locListaProcedimientos = this.findListaProcedimiento(locFiltro).getListaResultados();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(Iterator<Procedimiento> iterator = locListaProcedimientos.iterator(); iterator.hasNext();) {
			Procedimiento procedimiento = iterator.next();
			if(procedimiento.getResponsable() != null && procedimiento.getResponsable().esResponsable(pUsuario)) {
				continue;
			}
			if (procedimiento.getListaFasesProcedimiento() != null
					&& !procedimiento.getListaFasesProcedimiento().isEmpty()) {
				FaseProcedimiento locFaseProcedimiento = procedimiento.getListaFasesProcedimiento().get(0);
				if(locFaseProcedimiento.getResponsable() != null 
						&& locFaseProcedimiento.getResponsable().esResponsable(pUsuario)) {
					continue;
				}
			}
			iterator.remove();
		}
		return locListaProcedimientos;
	}

}

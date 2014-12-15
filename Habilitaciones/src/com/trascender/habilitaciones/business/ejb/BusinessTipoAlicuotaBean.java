
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoAlicuotaLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroRubroSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;

@Stateless(name = "ejb/BusinessTipoAlicuotaLocal")
public class BusinessTipoAlicuotaBean implements BusinessTipoAlicuotaLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4883108399364713992L;
	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Registros de Alícuotas");

//		Recurso tipoAlicuota = new Recurso();
//		tipoAlicuota.setIdRecurso(TipoAlicuota.serialVersionUID);
//		tipoAlicuota.setNombre("Tipo de Alícuota");
//		grupo.getListaRecursos().add(tipoAlicuota);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessParametroLocal businessParametro;

	@EJB
	private BusinessObligacionLocal businessObligacion;

	public BusinessTipoAlicuotaBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	public void ejbCreate() throws CreateException {
	}

	@PersistenceContext
	private EntityManager entityManager;

	public RegAlicuota addTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception {
		Rubro locRubro = (Rubro) pTipoAlicuota;
		this.validarTipoAlicuota(pTipoAlicuota);
		this.verficarRubroSHPS(pTipoAlicuota);

		List<TipoObligacion> locListaObligacion = businessObligacion.findListaTipoObligacion("SHPS", null);

		if(locListaObligacion == null || locListaObligacion.isEmpty()) {
			throw new HabilitacionesException(28);
		}

		TipoObligacion locTipoObligacion = locListaObligacion.get(0);
		locRubro.setTipoObligacion(locTipoObligacion);
		
		TrascenderEnverListener.setValoresEnAuditoriaBean(locRubro);

		this.entityManager.persist(locRubro);
		this.entityManager.flush();
		return pTipoAlicuota;
	}

	/**
	 * Valida que no aya un Reg de alicuota con un mismo codigo
	 * 
	 * @param pTipoAlicuota
	 * @throws Exception
	 */
	private void validarTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception {
		Rubro locRubro = (Rubro) pTipoAlicuota;
		// Se copian el rubro y el nombre por los requeridos de la base.
		locRubro.setCodigo(locRubro.getCodigoCiiu().getCodigo());
		locRubro.setNombre(locRubro.getCodigoCiiu().getDescripcion());

		Criterio locCriterio = Criterio.getInstance(this.entityManager, RegAlicuota.class)
				.add(Restriccion.LIKE("codigo", pTipoAlicuota.getCodigo(), true, Posicion.EXACTA))
				.add(Restriccion.IGUAL("estado", RegAlicuota.Estado.ACTIVO))
				.setProyeccion(Proyeccion.COUNT());

		if(pTipoAlicuota.getIdTipoAlicuota() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idTipoAlicuota", pTipoAlicuota.getIdTipoAlicuota())));
		}

//		if((Long) locCriterio.uniqueResult() > 0) {
//			throw new HabilitacionesException(79);
//		}

	}

	private void verficarRubroSHPS(RegAlicuota tipoAlicuota) {
		// TODO agregar controles de rubros shps

	}

	public RegAlicuota updateTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception {
		this.validarTipoAlicuota(pTipoAlicuota);
		TrascenderEnverListener.setValoresEnAuditoriaBean((Rubro)pTipoAlicuota);
		this.entityManager.merge(pTipoAlicuota);
		this.entityManager.flush();
		return pTipoAlicuota;
	}

	public void deleteTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception {
		pTipoAlicuota.setEstado(RegAlicuota.Estado.INACTIVO);
		entityManager.merge(pTipoAlicuota);
	}

	public FiltroRubroSHPS findListaRubros(FiltroRubroSHPS pFiltro) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Rubro.class);

		AtributoDinamico.addRestriccionesCriterio(locCriterio, Rubro.serialVersionUID, "idTipoAlicuota", pFiltro.getListaAtributo());

		locCriterio.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.setModoDebug(true)
				.add(Restriccion.IGUAL("tipoObligacion", pFiltro.getTipoObligacion()))
				.add(Restriccion.ILIKE("codigo", pFiltro.getCodigo()))
				.add(Restriccion.IGUAL("codigoCiiu", pFiltro.getCodigoCiuu()))
				.add(Restriccion.IGUAL("estado", ((pFiltro.getEstado() != null) ? pFiltro.getEstado() : RegAlicuota.Estado.ACTIVO)));
		pFiltro.procesarYListar(locCriterio);
		for (Rubro cadaRubro : pFiltro.getListaResultados()){
			System.out.println(cadaRubro.getNombre());
			System.out.println(cadaRubro.getMinimo());
			System.out.println(cadaRubro.getCodigo());
		}
		return pFiltro;
	}

	public ServicioOSP addServicioOSP(ServicioOSP pServicioOSP) throws Exception {
		this.validarServicioOSP(pServicioOSP);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pServicioOSP);
		this.entityManager.persist(pServicioOSP);
		this.entityManager.flush();
		return pServicioOSP;
	}

	/**
	 * Valida que no aya un servicio osp con el mismo codigo y algunas !nullidades requeridas
	 * 
	 * @param pServicioOSP
	 * @throws Exception
	 */
	private void validarServicioOSP(ServicioOSP pServicioOSP) throws Exception {
		this.verficarServicioOSP(pServicioOSP);

		Criterio locCriterio = Criterio.getInstance(this.entityManager, ServicioOSP.class).setProyeccion(Proyeccion.COUNT()).add(Restriccion.IGUAL("medido", pServicioOSP.isMedido()))
				.add(Restriccion.LIKE("codigo", pServicioOSP.getCodigo(), false, Posicion.EXACTA));

		if(pServicioOSP.getIdTipoAlicuota() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idTipoAlicuota", pServicioOSP.getIdTipoAlicuota())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(72);
		}

	}

	private void verficarServicioOSP(com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP pServicioOSP) throws HabilitacionesException {
		if(pServicioOSP.isMedido()) {
			if(pServicioOSP.getBaseConsumo() == null) {
				throw new HabilitacionesException(76);
			}
			if(pServicioOSP.getUnidadMedida() == null) {
				throw new HabilitacionesException(77);
			}
			if(pServicioOSP.getValorPorExcedente() == null) {
				throw new HabilitacionesException(78);
			}
		}
	}

	public ServicioOSP updateServicioOSP(ServicioOSP pServicioOSP) throws Exception {
		this.validarServicioOSP(pServicioOSP);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pServicioOSP);
		this.entityManager.merge(pServicioOSP);
		this.entityManager.flush();
		return pServicioOSP;
	}

	public void deleteServicioOSP(ServicioOSP pServicioOSP) throws Exception {
		this.validarDelServicioOSP(pServicioOSP);
		this.deleteTipoAlicuota(pServicioOSP);
	}

	// valida que el servicio a eliminar no este asociado a una obligacion
	private void validarDelServicioOSP(ServicioOSP pServicioOSP) throws HabilitacionesException {
		long locCantidad = Criterio.getInstance(this.entityManager, DocHabilitanteEspecializado.class).setProyeccion(Proyeccion.COUNT())
				.crearAlias("listaAsocRegAlicuota.registroAlicuota", "cadaRegAlicuota").add(Restriccion.IGUAL("cadaRegAlicuota", pServicioOSP)).uniqueResult();

		if(locCantidad > 0) {
			throw new HabilitacionesException(69);
		}
	}

	public FiltroServicioOSP findListaServiciosOSP(FiltroServicioOSP pFiltro) throws Exception {
		Criterio criterio = Criterio.getInstance(this.entityManager, ServicioOSP.class);

		AtributoDinamico.addRestriccionesCriterio(criterio, ServicioOSP.serialVersionUID, "idTipoAlicuota", pFiltro.getListaAtributosDinamicos());

		criterio.add(Restriccion.ILIKE("codigo", pFiltro.getCodigo()))
			.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
			.add(Restriccion.IGUAL("estado", RegAlicuota.Estado.ACTIVO));

		BusquedaPorLog.addRestriccionesCriterio(criterio, ServicioOSP.serialVersionUID, "idTipoAlicuota", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(criterio);
		return pFiltro;
	}

	public RegAlicuota getRegAlicuotaPorId(long pId) {
		RegAlicuota locRegAlicuota = entityManager.find(RegAlicuota.class, pId);
		
		if(locRegAlicuota != null) {
			locRegAlicuota.toString();
			//locRegAlicuota.getListaAtributosDinamicos().size();
		}
		return locRegAlicuota;
	}

	public RegAlicuota getTipoAlicuotaPorId(long pIdTipoAlicuota) throws Exception {
		RegAlicuota locTipoAlicuota = Criterio.getInstance(this.entityManager, RegAlicuota.class)
				.add(Restriccion.IGUAL("idTipoAlicuota", pIdTipoAlicuota))
				.uniqueResult();

		if(locTipoAlicuota == null) {
			throw new HabilitacionesException(0);
		}

		locTipoAlicuota.toString();
		return locTipoAlicuota;
	}
	
	public Rubro getRubroPorId(long pId) {
		Rubro locRubro = entityManager.find(Rubro.class, pId);
		
		if(locRubro != null) {
			locRubro.toString();
			locRubro.getListaAtributosDinamicos().size();
			locRubro.getListaLogsAuditoria().size();
		}
		
		return locRubro;
	}

	public ServicioOSP getServicioOSPPorId(long pIdServicioOSP) throws Exception {
		ServicioOSP locServicioOSP = Criterio.getInstance(this.entityManager, ServicioOSP.class)
				.add(Restriccion.IGUAL("idTipoAlicuota", pIdServicioOSP))
				.uniqueResult();

		if(locServicioOSP == null) {
			throw new HabilitacionesException(0);
		}

		locServicioOSP.toString();
		locServicioOSP.getListaAtributosDinamicos().size();
		locServicioOSP.getListaLogsAuditoria().size();
		
		return locServicioOSP;
	}
}
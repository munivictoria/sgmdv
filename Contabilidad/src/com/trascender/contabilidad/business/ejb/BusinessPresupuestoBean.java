
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.trascender.contabilidad.business.interfaces.BusinessPlanDeCuentaLocal;
import com.trascender.contabilidad.business.interfaces.BusinessPresupuestoLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.AsociacionCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.contabilidad.recurso.persistent.HistoricoPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.contabilidad.recurso.persistent.LineaHistoricoPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaHistoricoPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "ejb/BusinessPresupuestoLocal")
public class BusinessPresupuestoBean implements BusinessPresupuestoLocal {

	private static final long serialVersionUID = 2138471399178055963L;
	private static final String NOMBRE = "CUE|Adm Presupuesto";

	@EJB
	private BusinessPlanDeCuentaLocal businessPlanDeCuenta;

	@PersistenceContext
	private EntityManager entity;

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessPresupuestoBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessPresupuestoBean.NOMBRE);

		Recurso presupuesto = new Recurso();
		presupuesto.setNombre("Presupuesto");
		presupuesto.setIdRecurso(Presupuesto.serialVersionUID);
		presupuesto.setClase(Presupuesto.class);
		grupoRecursos.getListaRecursos().add(presupuesto);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	public BusinessPresupuestoBean() {
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
		this.inicializarBusinessPlanDeCuenta();
	}

	private void inicializarBusinessPlanDeCuenta() throws CreateException {
	}

	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.Presupuesto addPresupuesto(com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto) throws java.lang.Exception {
		pPresupuesto.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
		for(LineaPresupuesto locLineaPresupuesto : pPresupuesto.getLineaPresupuesto()) {
			Cuenta locCuenta = locLineaPresupuesto.getCuenta();
			locCuenta = entity.merge(locCuenta);
			if(locCuenta.getLineasPresupuesto().size() > 1) {
				List<Presupuesto> locListaPresupuesto = Criterio.getInstance(entity, Presupuesto.class).add(Restriccion.IGUAL("estado", Presupuesto.Estado.ACTIVO))
						.add(Restriccion.IGUAL("anio", pPresupuesto.getAnio())).add(Restriccion.IGUAL("lineaPresupuesto.cuenta", locCuenta)).list();
				if(!locListaPresupuesto.isEmpty()) {
					throw new TrascenderContabilidadException(91, locCuenta, locListaPresupuesto);
				}
			}
		}
		entity.clear();
		entity.persist(pPresupuesto);
		return pPresupuesto;
	}

	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.Presupuesto updatePresupuesto(com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto,
			com.trascender.framework.recurso.persistent.Usuario pUsuario) throws java.lang.Exception {
		HistoricoPresupuesto presupuestoModificado = this.crearHistorial(pPresupuesto, pUsuario);
		for(LineaPresupuesto locLineaPresupuesto : pPresupuesto.getLineaPresupuesto()) {
			Cuenta locCuenta = entity.merge(locLineaPresupuesto.getCuenta());
			if(locCuenta.getLineasPresupuesto().size() > 1) {
				List<Presupuesto> locListaPresupuesto = Criterio.getInstance(entity, Presupuesto.class).add(Restriccion.IGUAL("estado", Presupuesto.Estado.ACTIVO))
						.add(Restriccion.IGUAL("anio", pPresupuesto.getAnio())).add(Restriccion.IGUAL("lineaPresupuesto.cuenta", locCuenta))
						.add(Restriccion.DISTINTO("idPresupuesto", pPresupuesto.getIdPresupuesto())).setProyeccion(Proyeccion.COUNT()).uniqueResult();
				if(!locListaPresupuesto.isEmpty()) {
					throw new TrascenderContabilidadException(91, locCuenta, locListaPresupuesto);
				}
			}
		}
		entity.clear();
		entity.persist(presupuestoModificado);
		entity.merge(pPresupuesto);
		return pPresupuesto;
	}

	/**
	 * Creo el historial del presupuesto el historial es una "foto" del presupuesto antes de ser modificado
	 * 
	 * @param pPresupuesto
	 * @param pUsuario
	 * @return
	 */
	private com.trascender.contabilidad.recurso.persistent.HistoricoPresupuesto crearHistorial(com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto,
			com.trascender.framework.recurso.persistent.Usuario pUsuario) {
		HistoricoPresupuesto locHistorico = new HistoricoPresupuesto();
		locHistorico.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
		locHistorico.setPresupuesto(pPresupuesto);
		locHistorico.setUsuario(pUsuario);

		if(pPresupuesto.getTipo() == Presupuesto.Tipo.GASTOS) {
			for(LineaPresupuesto locLineaPresupuesto : pPresupuesto.getLineaPresupuesto()) {
				LineaPresupuestoGastos lineaPresupuestoGastos = (LineaPresupuestoGastos) locLineaPresupuesto;
				LineaHistoricoPresupuestoGastos locLinea = new LineaHistoricoPresupuestoGastos();

				locLinea.setMontoComprometido(lineaPresupuestoGastos.getMontoComprometido());
				locLinea.setMontoPagado(lineaPresupuestoGastos.getMontoPagado());
				locLinea.setMontoPresupuestado(lineaPresupuestoGastos.getMontoPresupuestado());
				locLinea.setNombreCuenta(lineaPresupuestoGastos.getCuenta().toString());

				locLinea.setHistoricoPresupuesto(locHistorico);
				locHistorico.getLineasHistoricoPresupuesto().add(locLinea);
			}
		}
		if(pPresupuesto.getTipo() == Presupuesto.Tipo.RECURSOS) {
			for(LineaPresupuesto locLineaPresupuesto : pPresupuesto.getLineaPresupuesto()) {
				LineaPresupuestoRecursos lineaPresupuestoRecursos = (LineaPresupuestoRecursos) locLineaPresupuesto;
				LineaHistoricoPresupuestoRecursos locLinea = new LineaHistoricoPresupuestoRecursos();

				locLinea.setMontoEstimado(lineaPresupuestoRecursos.getMontoEstimado());
				locLinea.setMontoRecaudado(lineaPresupuestoRecursos.getMontoRecaudado());
				locLinea.setNombreCuenta(lineaPresupuestoRecursos.getCuenta().toString());

				locLinea.setHistoricoPresupuesto(locHistorico);
				locHistorico.getLineasHistoricoPresupuesto().add(locLinea);
			}
		}
		return locHistorico;
	}

	@SuppressWarnings("unchecked")
	public void deletePresupuesto(com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto) throws java.lang.Exception {
		if(pPresupuesto.getEstado() == Presupuesto.Estado.ACTIVO) {
			throw new TrascenderContabilidadException(90);
		}
		for(LineaPresupuesto locLineaPresupuesto : pPresupuesto.getLineaPresupuesto()) {
			List<LineaAsientoContable> listaLineasAsientosContables = Criterio.getInstance(entity, LineaAsientoContable.class)
					.add(Restriccion.IGUAL("cuenta", locLineaPresupuesto.getCuenta()))
					.add(Restriccion.MAYOR("asientoContable.fecha", pPresupuesto.getPeriodo().getFechaInicio().getTime()))
					.add(Restriccion.MENOR("asientoContable.fecha", pPresupuesto.getPeriodo().getFechaFin().getTime())).list();
			if(!listaLineasAsientosContables.isEmpty()) {
				throw new TrascenderContabilidadException(92);
			}
		}
		entity.merge(pPresupuesto);
		entity.remove(pPresupuesto);
	}

	public void cerrarPresupuesto(com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto) throws java.lang.Exception {
		pPresupuesto.setEstado(Presupuesto.Estado.INACTIVO);
		desatacharCuentas(pPresupuesto);
		entity.merge(pPresupuesto);
	}

	/**
	 * Hago que las cuentas de los presupuestos pierdan las asociaciones El nombre es feo y que?
	 * 
	 * @param pPresupuesto
	 * @throws Exception
	 */
	private void desatacharCuentas(Presupuesto pPresupuesto) throws Exception {
		for(LineaPresupuesto locLineaPresupuesto : pPresupuesto.getLineaPresupuesto()) {

			AsociacionCuenta asociacionCuenta = Criterio.getInstance(entity, AsociacionCuenta.class).add(Restriccion.IGUAL("cuenta", locLineaPresupuesto.getCuenta())).uniqueResult();

			if(asociacionCuenta instanceof CuentaTipoTasa) {
				CuentaTipoTasa locCuentaTipoTasa = (CuentaTipoTasa) asociacionCuenta;
				locCuentaTipoTasa.setTipoTasa(null);
				entity.merge(locCuentaTipoTasa);
			}
			if(asociacionCuenta instanceof CuentaModificador) {
				CuentaModificador locCuentaModificador = (CuentaModificador) asociacionCuenta;
				locCuentaModificador.setTipoModificador(null);
				entity.merge(locCuentaModificador);
			}
			if(asociacionCuenta instanceof CuentaConceptoIngresoVario) {
				CuentaConceptoIngresoVario locCuentaConceptoIngresoVario = (CuentaConceptoIngresoVario) asociacionCuenta;
				locCuentaConceptoIngresoVario.setConceptoIngresoVario(null);
				entity.merge(locCuentaConceptoIngresoVario);
			}
		}
	}

	public void deleteListaLineaPresupuesto(Set<com.trascender.contabilidad.recurso.persistent.LineaPresupuesto> pListaLineaPresupuesto,
			com.trascender.framework.recurso.persistent.Usuario pUsuario) throws java.lang.Exception {
		for(LineaPresupuesto cadaLineaPresupuesto : pListaLineaPresupuesto) {
			entity.merge(cadaLineaPresupuesto);
			entity.remove(cadaLineaPresupuesto);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.Presupuesto getPresupuestoByID(Long pIdPresupuesto) throws java.lang.Exception {
		if(pIdPresupuesto == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pIdPresupuesto == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Presupuesto locPresupuesto = entity.find(Presupuesto.class, pIdPresupuesto);
		locPresupuesto.getDigestoMunicipal().toString();
		// locPresupuesto.getPeriodo().toString();
		for(LineaPresupuesto locLinea : locPresupuesto.getLineaPresupuesto()) {
			locLinea.toString();
			locLinea.getCuenta().toString();
		}
		return locPresupuesto;
	}

	public java.util.List findListaPresupuesto(com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo pTipo,
			com.trascender.contabilidad.recurso.persistent.Presupuesto.Estado pEstado, Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Presupuesto.class).add(Restriccion.IGUAL("tipo", pTipo)).add(Restriccion.IGUAL("estado", pEstado))
				.add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta));
		List listaPresupuestos = locCriterio.list();
		return listaPresupuestos;
	}

}

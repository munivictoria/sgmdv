
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.sql.Time;
import java.util.Calendar;
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
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.business.interfaces.BusinessStockLocal;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Articulo.EstadoContable;
import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.contabilidad.business.interfaces.BusinessPlanDeCuentaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.filtros.FiltroCuenta;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.BajaArticulo;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.CuentaHistoricaBalance;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.MovimientoCuentaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCuentaIngreso;
import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

@Stateless(name = "ejb/BusinessPlanDeCuentaBeanLocal")
public class BusinessPlanDeCuentaBean implements BusinessPlanDeCuentaLocal {

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessPlanDeCuentaBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessPlanDeCuentaBean.NOMBRE);

		Recurso tipoCuenta = new Recurso();
		tipoCuenta.setIdRecurso(TipoCuenta.serialVersionUID);
		tipoCuenta.setNombre("Tipo de Cuenta");
		tipoCuenta.setClase(TipoCuenta.class);
		grupoRecursos.getListaRecursos().add(tipoCuenta);

		Recurso tipoBalance = new Recurso();
		tipoBalance.setIdRecurso(TipoBalance.serialVersionUID);
		tipoBalance.setNombre("Tipo de Balance");
		tipoBalance.setClase(TipoBalance.class);
		grupoRecursos.getListaRecursos().add(tipoBalance);

		Recurso balance = new Recurso();
		balance.setIdRecurso(Balance.serialVersionUID);
		balance.setNombre("Balance");
		balance.setClase(Balance.class);
		grupoRecursos.getListaRecursos().add(balance);

		Recurso cuenta = new Recurso();
		cuenta.setIdRecurso(Cuenta.serialVersionUID);
		cuenta.setNombre("Cuenta");
		cuenta.setAtributosConsultables("Código Imputacion", "codigoImputacion", "Nombre", "nombre", "Abreviatura", "abreviatura", "Plan de Cuenta", "planDeCuenta", "Tipos de Cuenta",
				"stringTiposCuenta", Tipo.TEXTO_LARGO);
		cuenta.setClase(Cuenta.class);
		grupoRecursos.getListaRecursos().add(cuenta);

		Recurso planDeCuenta = new Recurso();
		planDeCuenta.setIdRecurso(PlanDeCuenta.serialVersionUID);
		planDeCuenta.setNombre("Plan de Cuenta");
		planDeCuenta.setClase(PlanDeCuenta.class);
		grupoRecursos.getListaRecursos().add(planDeCuenta);

		Recurso cuentaTipoTasa = new Recurso();
		cuentaTipoTasa.setIdRecurso(CuentaTipoTasa.serialVersionUID);
		cuentaTipoTasa.setNombre("Asociacion Cuenta/Fórmula de Cálculo");
		cuentaTipoTasa.setClase(CuentaTipoTasa.class);
		grupoRecursos.getListaRecursos().add(cuentaTipoTasa);

		Recurso cuentaTipoModificador = new Recurso();
		cuentaTipoModificador.setIdRecurso(CuentaModificador.serialVersionUID);
		cuentaTipoModificador.setNombre("Asociacion Cuenta/Modificador");
		cuentaTipoModificador.setClase(CuentaModificador.class);
		grupoRecursos.getListaRecursos().add(cuentaTipoModificador);

		Recurso cuentaConceptoIngresoVario = new Recurso();
		cuentaConceptoIngresoVario.setIdRecurso(CuentaConceptoIngresoVario.serialVersionUID);
		cuentaConceptoIngresoVario.setNombre("Asociacion Cuenta/Concepto Ingreso Vario");
		cuentaConceptoIngresoVario.setClase(CuentaConceptoIngresoVario.class);
		grupoRecursos.getListaRecursos().add(cuentaConceptoIngresoVario);

		Recurso cuentaLineaFactura = new Recurso();
		cuentaLineaFactura.setIdRecurso(CuentaLineaFactura.serialVersionUID);
		cuentaLineaFactura.setNombre("Asociacion Cuenta/Linea de Factura");
		cuentaLineaFactura.setClase(CuentaLineaFactura.class);
		grupoRecursos.getListaRecursos().add(cuentaLineaFactura);

		Recurso cuentaArticulo = new Recurso();
		cuentaArticulo.setIdRecurso(CuentaArticulo.serialVersionUID);
		cuentaArticulo.setNombre("Asociacion Cuenta/Articulo");
		cuentaArticulo.setClase(CuentaArticulo.class);
		grupoRecursos.getListaRecursos().add(cuentaArticulo);

		Recurso cuentaInteresRecargo = new Recurso();
		cuentaInteresRecargo.setIdRecurso(CuentaInteresRecargo.serialVersionUID);
		cuentaInteresRecargo.setNombre("Asociacion Cuenta/Interés-Recargo");
		cuentaInteresRecargo.setClase(CuentaInteresRecargo.class);
		grupoRecursos.getListaRecursos().add(cuentaInteresRecargo);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6740872676692745341L;
	private static final String NOMBRE = "CUE|Adm Plan de Cuenta";
	private PlanDeCuenta planDeCuenta;

	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;

	@EJB
	private BusinessStockLocal businessStockLocal;

	@PersistenceContext
	private EntityManager entity;

	public BusinessPlanDeCuentaBean() {
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

	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta addPlanDeCuenta(com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta)
			throws java.lang.Exception {
		this.acomodarNombresCuentas(pPlanDeCuenta);
		entity.persist(pPlanDeCuenta);
		return pPlanDeCuenta;
	}

	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta updatePlanDeCuenta(com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta)
			throws java.lang.Exception {
		this.acomodarNombresCuentas(pPlanDeCuenta);
		entity.merge(pPlanDeCuenta);
		return pPlanDeCuenta;
	}

	private void acomodarNombresCuentas(PlanDeCuenta pPlanDeCuenta) {
		boolean concatenando = SecurityMgr.getInstance().getMunicipalidad().isNombreCuentaConcatenado();
		for(Cuenta cadaCuenta : pPlanDeCuenta.getListaCuentas()) {
			cadaCuenta.armarCodigoImputacionCompleto(concatenando);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta getPlanDeCuentaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		PlanDeCuenta locPlanDeCuenta = null;
		Criterio locCriterio = Criterio.getInstance(entity, PlanDeCuenta.class).add(Restriccion.IGUAL("idPlanDeCuenta", pId)).add(Orden.ASC("listaCuentas.codigoImputacion"));
		locPlanDeCuenta = (PlanDeCuenta) locCriterio.uniqueResult();
		if(locPlanDeCuenta == null) {
			return null;
		}
		for(Cuenta locCuenta : locPlanDeCuenta.getListaCuentas()) {
			locCuenta.toString();
			locCuenta.getListaTipoCuenta().size();
			locCuenta.getCuentaPadre();
			this.obtenerHijos(locCuenta);
		}
		return locPlanDeCuenta;
	}

	public void obtenerHijos(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		for(Cuenta locCuentaHijo : pCuenta.getCuentasHijos()) {
			locCuentaHijo.toString();
			locCuentaHijo.getListaTipoCuenta().size();
			locCuentaHijo.getCuentaPadre();
			this.obtenerHijos(locCuentaHijo);
		}
	}

	public void deletePlanDeCuenta(com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta) throws java.lang.Exception {
		if(!pPlanDeCuenta.getListaCuentas().isEmpty()) {
			try {
				for(Cuenta locCuenta : pPlanDeCuenta.getListaCuentas()) {
					entity.merge(locCuenta);
					this.chequearCuentaPlan(locCuenta);
				}
			} catch(Exception e) {
				throw new TrascenderContabilidadException(180);
			}
		}
		entity.remove(pPlanDeCuenta);
	}

	public List findListaPlanDeCuenta(String pDescripcion, Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, PlanDeCuenta.class).add(Restriccion.ILIKE("descripcion", pDescripcion)).add(Restriccion.MAYOR("fechaAlta", pFechaDesde))
				.add(Restriccion.MENOR("fechaAlta", pFechaHasta));
		List<PlanDeCuenta> listaPlanesDeCuentas = locCriterio.list();
		return listaPlanesDeCuentas;
	}

	public com.trascender.contabilidad.recurso.persistent.Cuenta updateCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		entity.merge(pCuenta);
		return pCuenta;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pCuenta
	 * @throws java.lang.Exception
	 */
	public void deleteCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		// CHEQUEO QUE LA CUENTA NO ESTE EN USO.
		entity.merge(pCuenta);
		this.chequearCuenta(pCuenta);
		entity.remove(pCuenta);
	}

	private void chequearCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws Exception {
		if(pCuenta.getListaAsociacionCuenta() != null && !pCuenta.getLineasAsientoContable().isEmpty()) {
			throw new TrascenderContabilidadException(117);
		}
		if(pCuenta.getLineasAsientoContable() != null && !pCuenta.getLineasAsientoContable().isEmpty()) {
			throw new TrascenderContabilidadException(110);
		}
		if(pCuenta.getLineasFactura() != null && !pCuenta.getLineasFactura().isEmpty()) {
			throw new TrascenderContabilidadException(111);
		}
		if(pCuenta.getLineasPresupuesto() != null && !pCuenta.getLineasPresupuesto().isEmpty()) {
			throw new TrascenderContabilidadException(112);
		}
		if(pCuenta.getLineasSubdiarioCaja() != null && !pCuenta.getLineasSubdiarioCaja().isEmpty()) {
			throw new TrascenderContabilidadException(113);
		}
		if(pCuenta.getListaCuentasHistorico() != null && !pCuenta.getListaCuentasHistorico().isEmpty()) {
			throw new TrascenderContabilidadException(114);
		}
		if(pCuenta.getListaTiposBalance() != null && !pCuenta.getListaTiposBalance().isEmpty()) {
			throw new TrascenderContabilidadException(115);
		}
		if(pCuenta.getCuentasHijos() != null && !pCuenta.getCuentasHijos().isEmpty()) {
			throw new TrascenderContabilidadException(116);
		}
		if(pCuenta.getListaAsociacionCuenta() != null && !pCuenta.getListaAsociacionCuenta().isEmpty()) {
			throw new TrascenderContabilidadException(109);
		}
	}

	private void chequearCuentaPlan(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws Exception {
		if(pCuenta.getListaAsociacionCuenta() != null && !pCuenta.getListaAsociacionCuenta().isEmpty()) {
			throw new TrascenderContabilidadException(109);
		}
		if(pCuenta.getLineasAsientoContable() != null && !pCuenta.getLineasAsientoContable().isEmpty()) {
			throw new TrascenderContabilidadException(110);
		}
		if(pCuenta.getLineasFactura() != null && !pCuenta.getLineasFactura().isEmpty()) {
			throw new TrascenderContabilidadException(111);
		}
		if(pCuenta.getLineasPresupuesto() != null && !pCuenta.getLineasPresupuesto().isEmpty()) {
			throw new TrascenderContabilidadException(112);
		}
		if(pCuenta.getLineasSubdiarioCaja() != null && !pCuenta.getLineasSubdiarioCaja().isEmpty()) {
			throw new TrascenderContabilidadException(113);
		}
		if(pCuenta.getListaCuentasHistorico() != null && !pCuenta.getListaCuentasHistorico().isEmpty()) {
			throw new TrascenderContabilidadException(114);
		}
		if(pCuenta.getListaTiposBalance() != null && !pCuenta.getListaTiposBalance().isEmpty()) {
			throw new TrascenderContabilidadException(115);
		}
		if(pCuenta.getListaAsociacionCuenta() != null && !pCuenta.getLineasAsientoContable().isEmpty()) {
			throw new TrascenderContabilidadException(117);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.Cuenta getCuentaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Cuenta locCuenta = entity.find(Cuenta.class, pId);
		if(locCuenta != null) {
			locCuenta.toString();
			locCuenta.getListaTipoCuenta().size();
			if(locCuenta.getArea() != null) {
				locCuenta.getArea().toString();
			}
			// locCuenta.getCodigoImputacion().toString();
			// locCuenta.getCodigoImputacionCompleto().toString();
			locCuenta.getCuentaPadre();
			for(Cuenta cadaCuenta : locCuenta.getCuentasHijos()) {
				this.inicializarCuenta(cadaCuenta);
			}
			locCuenta.getLineasAsientoContable();
			locCuenta.getLineasFactura();
			locCuenta.getLineasPresupuesto();
			locCuenta.getLineasSubdiarioCaja();
			locCuenta.getListaAsociacionCuenta();
			locCuenta.getListaCuentasHistorico();
			locCuenta.getListaLineaMayor();
			locCuenta.getListaMovimientoCaja();
			locCuenta.getListaTiposBalance();
			locCuenta.getCodigoImputacionCompleto().toString();
		}
		return locCuenta;
	}

	public void validarCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws Exception {
		if(!pCuenta.getCuentasHijos().isEmpty()) {
			throw new TrascenderContabilidadException(119);
		}
	}

	private void inicializarCuenta(Cuenta pCuenta) {
		pCuenta.toString();
		for(Cuenta cadaCuenta : pCuenta.getCuentasHijos()) {
			this.inicializarCuenta(cadaCuenta);
		}

	}

	public FiltroCuenta findListaCuenta(FiltroCuenta pFiltro) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, Cuenta.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.ILIKE("abreviatura", pFiltro.getAbreviatura())).add(Restriccion.ILIKE("codigoImputacion", pFiltro.getCodigoImputacion()))
				.add(Restriccion.IGUAL("planDeCuenta", pFiltro.getPlanDeCuenta())).add(Restriccion.IGUAL("tipoCuenta", pFiltro.getTipoCuenta()))
				.add(Restriccion.IGUAL("area", pFiltro.getArea()));
		if(pFiltro.getAceptaAsientosContables() != null && pFiltro.getAceptaAsientosContables()) {
			locCriterio.add(Restriccion.ESTA_VACIO("cuentasHijos"));
		}

		pFiltro.procesarYListar(locCriterio);

		for(Cuenta cadaCuenta : pFiltro.getListaResultados()) {
			cadaCuenta.toString();
			cadaCuenta.getListaTipoCuenta().size();
			cadaCuenta.getCuentaPadre();
		}

		return pFiltro;
	}

	public com.trascender.contabilidad.recurso.persistent.TipoCuenta addTipoCuenta(com.trascender.contabilidad.recurso.persistent.TipoCuenta pTipoCuenta) throws java.lang.Exception {
		entity.persist(pTipoCuenta);
		return pTipoCuenta;
	}

	public com.trascender.contabilidad.recurso.persistent.TipoCuenta updateTipoCuenta(com.trascender.contabilidad.recurso.persistent.TipoCuenta pTipoCuenta) throws java.lang.Exception {
		entity.merge(pTipoCuenta);
		return pTipoCuenta;
	}

	public com.trascender.contabilidad.recurso.persistent.TipoCuenta getTipoCuentaByID(Long pIdTipoCuenta) throws java.lang.Exception {
		if(pIdTipoCuenta == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pIdTipoCuenta == -1) {
			throw new TrascenderContabilidadException(299);
		}
		TipoCuenta locTipoCuenta = entity.find(TipoCuenta.class, pIdTipoCuenta);
		if(locTipoCuenta != null) {
			for(Cuenta locCuenta : locTipoCuenta.getListaCuenta()) {
				locCuenta.toString();
			}
			locTipoCuenta.getListaTipoCuentaExcluidos().size();
		}
		return locTipoCuenta;
	}

	public void deleteTipoCuenta(com.trascender.contabilidad.recurso.persistent.TipoCuenta pTipoCuenta) throws java.lang.Exception {
		if(!pTipoCuenta.getListaCuenta().isEmpty()) {
			throw new TrascenderContabilidadException(162);
		}
		pTipoCuenta = entity.merge(pTipoCuenta);
		validarTipoCuentaExcluidos(pTipoCuenta);
		validarTipoCuentaEnCuentas(pTipoCuenta);
		entity.remove(pTipoCuenta);
	}

	private void validarTipoCuentaExcluidos(TipoCuenta pTipoCuenta) {
		List<TipoCuenta> locListaTipoCuenta = Criterio.getInstance(entity, TipoCuenta.class).add(Restriccion.MIEMBRO_DE("listaTipoCuentaExcluidos", pTipoCuenta)).list();
		for(TipoCuenta cadaTipoCuenta : locListaTipoCuenta) {
			cadaTipoCuenta.getListaTipoCuentaExcluidos().remove(pTipoCuenta);
		}
	}

	private void validarTipoCuentaEnCuentas(TipoCuenta pTipoCuenta) {
		List<Cuenta> locListaCuenta = Criterio.getInstance(entity, Cuenta.class).add(Restriccion.MIEMBRO_DE("listaTipoCuenta", pTipoCuenta)).list();
		for(Cuenta cadaCuenta : locListaCuenta) {
			cadaCuenta.getListaTipoCuenta().remove(pTipoCuenta);
		}
	}

	public List findListaTipoCuenta(String pNombre, com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura pAbreviatura,
			com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera pOperaMovimientosCaja, com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera pOperaAsientos)
			throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, TipoCuenta.class).add(Restriccion.ILIKE("nombre", pNombre)).add(Restriccion.IGUAL("abreviatura", pAbreviatura))
				.add(Restriccion.IGUAL("operaMovimientosCaja", pOperaMovimientosCaja)).add(Restriccion.IGUAL("operaAsientos", pOperaAsientos));

		List<TipoCuenta> listaTipoCuenta = locCriterio.list();
		return listaTipoCuenta;
	}

	public com.trascender.contabilidad.recurso.persistent.TipoBalance addTipoBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance) throws java.lang.Exception {
		pTipoBalance.setFecha(Calendar.getInstance().getTime());
		entity.merge(pTipoBalance);
		return pTipoBalance;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pTipoBalance
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.TipoBalance updateTipoBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance)
			throws java.lang.Exception {
		Long cantidad = Criterio.getInstance(entity, Balance.class).add(Restriccion.IGUAL("tipoBalance", pTipoBalance)).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderContabilidadException(168);
		}
		entity.merge(pTipoBalance);
		return pTipoBalance;
	}

	public com.trascender.contabilidad.recurso.persistent.TipoBalance getTipoBalanceByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		TipoBalance locTipoBalance = entity.find(TipoBalance.class, pId);
		if(locTipoBalance != null) {
			for(Cuenta locCuenta : locTipoBalance.getListaCuentas()) {
				locCuenta.getListaTipoCuenta().size();
				locCuenta.getCodigoImputacion();
				locCuenta.getCodigoImputacionCompleto();
				locCuenta.getPlanDeCuenta();
				locCuenta.getPlanDeCuenta().toString();
				locCuenta.getCodigoImputacionCompleto().toString();
			}
			for(Balance locBalance : locTipoBalance.getListaBalances()) {
				locBalance.toString();
				locBalance.getNombre();
			}
		}

		return locTipoBalance;
	}

	public void deleteTipoBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance) throws java.lang.Exception {
		Long cantidad = Criterio.getInstance(entity, Balance.class).add(Restriccion.IGUAL("tipoBalance", pTipoBalance)).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderContabilidadException(167);
		}
		entity.merge(pTipoBalance);
		entity.remove(pTipoBalance);
	}

	public List findListaTipoBalance(String pNombre, Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, TipoBalance.class).add(Restriccion.ILIKE("nombre", pNombre)).add(Restriccion.MAYOR("fecha", pFechaDesde))
				.add(Restriccion.MENOR("fecha", pFechaHasta));

		List locListaTipoBalance = locCriterio.list();
		locListaTipoBalance.toString();

		for(Object cadaObject : locListaTipoBalance) {
			TipoBalance locTipoBalance = new TipoBalance();
			locTipoBalance = (TipoBalance) cadaObject;
			locTipoBalance.toString();
			for(Balance cadaBalance : locTipoBalance.getListaBalances()) {
				cadaBalance.toString();
			}
		}
		return locListaTipoBalance;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Balance getBalanceByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Balance locBalance = entity.find(Balance.class, pId);
		if(locBalance != null) {
			for(CuentaHistoricaBalance locCuenta : locBalance.getListaCuentaHistoricoBalance()) {
				locCuenta.toString();

			}
			locBalance.getTipoBalance().toString();
			locBalance.getTipoBalance();
		}
		return locBalance;
	}

	public List findListaBalance(String pNombre, Date pFechaDesde, Date pFechaHasta, com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Balance.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.IGUAL("tipoBalance", pTipoBalance));
		List listaBalance = locCriterio.list();
		for(Object obj : listaBalance) {
			Balance locBalance = (Balance) obj;
			locBalance.getTipoBalance().toString();
			locBalance.toString();
		}
		return listaBalance;
	}

	public com.trascender.contabilidad.recurso.persistent.Balance generarBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance) throws java.lang.Exception {
		Balance locBalance = new Balance();
		pTipoBalance = entity.merge(pTipoBalance);

		// Guardo cada cuenta historica con los valores de la cuenta en el momento
		for(Object obj : pTipoBalance.getListaCuentas()) {
			Cuenta locCuenta = (Cuenta) obj;
			CuentaHistoricaBalance locCuentaHistorica = this.obtenerImportePresupuestado(locCuenta);
			locCuentaHistorica.setValor(this.obtenerImporteDeCuenta(locCuenta));

			// Paso los datos de la cuenta a la cuenta historica
			locCuentaHistorica.setAbreviatura(locCuenta.getAbreviatura());
			locCuentaHistorica.setNombre(locCuenta.getNombre());

			locCuentaHistorica.setCuenta(locCuenta);
			locCuentaHistorica.setBalance(locBalance);
			locBalance.getListaCuentaHistoricoBalance().add(locCuentaHistorica);
		}
		locBalance.setTipoBalance(pTipoBalance);
		locBalance.setFecha(Calendar.getInstance().getTime());
		return locBalance;
	}

	public com.trascender.contabilidad.recurso.persistent.Balance addBalance(com.trascender.contabilidad.recurso.persistent.Balance pBalance) throws java.lang.Exception {
		entity.persist(pBalance);
		return pBalance;
	}

	public void deleteBalance(com.trascender.contabilidad.recurso.persistent.Balance pBalance) throws java.lang.Exception {
		entity.merge(pBalance);
		entity.remove(pBalance);
	}

	private Double obtenerImporteDeCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		Double saldo = new Double(0);
		List locLista = Criterio.getInstance(entity, LineaMayor.class).add(Orden.ASC("fechaGeneracion")).add(Restriccion.IGUAL("mayor.cuenta", pCuenta)).list();
		if(!locLista.isEmpty()) {
			LineaMayor lineaReciente = (LineaMayor) locLista.get(0);
			saldo = lineaReciente.getSaldo();
		}
		return saldo;
	}

	public List findListaLibroDiario(Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception {
		List listaLibrosDiarios;
		Criterio locCriterio = Criterio.getInstance(entity, AsientoContable.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta));
		listaLibrosDiarios = locCriterio.list();
		return listaLibrosDiarios;
	}

	private void validarCuentaModificador(CuentaModificador pAsociacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaModificador.class).add(Restriccion.IGUAL("cuenta", pAsociacion.getCuenta()))
				.add(Restriccion.IGUAL("tipoModificador", pAsociacion.getTipoModificador())).add(Restriccion.DISTINTO("idAsociacionCuenta", pAsociacion.getIdAsociacionCuenta()))
				.setProyeccion(Proyeccion.COUNT());

		Long resultado = locCriterio.uniqueResult();

		if(resultado > 0) {
			throw new TrascenderContabilidadException(81);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaModificador addCuentaModificador(com.trascender.contabilidad.recurso.persistent.CuentaModificador pCuentaModificador)
			throws java.lang.Exception {
		validarCuentaModificador(pCuentaModificador);
		entity.persist(pCuentaModificador);
		return pCuentaModificador;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaModificador updateCuentaModificador(com.trascender.contabilidad.recurso.persistent.CuentaModificador pCuentaModificador)
			throws java.lang.Exception {
		validarCuentaModificador(pCuentaModificador);
		return entity.merge(pCuentaModificador);
	}

	public void deleteCuentaModificador(com.trascender.contabilidad.recurso.persistent.CuentaModificador pCuentaModificador) throws java.lang.Exception {
		pCuentaModificador = entity.merge(pCuentaModificador);
		entity.remove(pCuentaModificador);
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaModificador getCuentaModificadorByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		CuentaModificador locCuentaModificador = entity.find(CuentaModificador.class, pId);
		if(locCuentaModificador != null) {
			locCuentaModificador.getTipoModificador();
			locCuentaModificador.getCuenta().toString();
			// locCuentaModificador.getPeriodo();
			locCuentaModificador.toString();
			if(locCuentaModificador.getCuentaPagosAtrasados() != null) {
				locCuentaModificador.getCuentaPagosAtrasados().toString();
			}
		}
		return locCuentaModificador;
	}

	public List findListaCuentaModificador(Cuenta pCuenta, TipoModificador pTipoModificador) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaModificador.class).add(Restriccion.IGUAL("cuenta", pCuenta))
				.add(Restriccion.IGUAL("tipoModificador", pTipoModificador));

		List<CuentaModificador> listaCuentaTipoModificador = locCriterio.list();

		for(CuentaModificador cadaCuentaTipoTasa : listaCuentaTipoModificador) {
			cadaCuentaTipoTasa.getCuenta().toString();
			cadaCuentaTipoTasa.getCuenta().getStringCodigoImputacion();
			cadaCuentaTipoTasa.getTipoModificador().toString();
		}

		return listaCuentaTipoModificador;
	}

	public com.trascender.contabilidad.recurso.persistent.ParametroAsociacion addParametroAsociacion(
			com.trascender.contabilidad.recurso.persistent.ParametroAsociacion pParametroAsociacion) throws java.lang.Exception {
		this.validarDatosParametrosAsociacion(pParametroAsociacion);
		entity.persist(pParametroAsociacion);
		return pParametroAsociacion;
	}

	public com.trascender.contabilidad.recurso.persistent.ParametroAsociacion updateParametroAsociacion(
			com.trascender.contabilidad.recurso.persistent.ParametroAsociacion pParametroAsociacion) throws Exception {
		// this.validarDatosParametrosAsociacion(pParametroAsociacion);
		pParametroAsociacion = entity.merge(pParametroAsociacion);
		return pParametroAsociacion;
	}

	public void deleteParametroAsociacion(com.trascender.contabilidad.recurso.persistent.ParametroAsociacion pParametroAsociacion) throws Exception {
		pParametroAsociacion = entity.merge(pParametroAsociacion);
		entity.remove(pParametroAsociacion);
	}

	@SuppressWarnings("unchecked")
	public List<ParametroAsociacion> findListaParametroAsociacion() throws java.lang.Exception {
		List<ParametroAsociacion> listaParametroAsociacion = Criterio.getInstance(entity, ParametroAsociacion.class).list();
		for(ParametroAsociacion parametroAsociacion : listaParametroAsociacion) {
			parametroAsociacion.getCuenta().getListaTipoCuenta().size();
			parametroAsociacion.getCuenta().getCuentaPadre();
			if(parametroAsociacion.getCuenta().getCuentaPadre() != null) {
				parametroAsociacion.getCuenta().getCuentaPadre().toString();
			}
			parametroAsociacion.getCuenta().getCodigoImputacion();
		}
		return listaParametroAsociacion;
	}

	private void validarDatosParametrosAsociacion(ParametroAsociacion pParametroAsociacion) throws TrascenderContabilidadException {
		if(pParametroAsociacion.getCuenta() == null) {
			throw new TrascenderContabilidadException(730);
		}

		if(pParametroAsociacion.getPorcentaje() == null || pParametroAsociacion.getPorcentaje() < 0) {
			throw new TrascenderContabilidadException(731);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.ParametroAsociacion getParametroAsociacionByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		ParametroAsociacion locParametroAsociacion = entity.find(ParametroAsociacion.class, pId);
		locParametroAsociacion.getCuenta().toString();
		locParametroAsociacion.getCuenta().getListaTipoCuenta().size();
		locParametroAsociacion.getCuenta().getCuentaPadre();
		if(locParametroAsociacion.getCuenta().getCuentaPadre() != null) {
			locParametroAsociacion.getCuenta().getCuentaPadre().toString();
		}
		locParametroAsociacion.getCuenta().getCodigoImputacion();

		return locParametroAsociacion;
	}

	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion addAsociacionRefinanciacion(Set<ParametroAsociacion> pListaParametrosAsociacion, Integer pAnio)
			throws Exception {

		if(pListaParametrosAsociacion.isEmpty()) {
			throw new TrascenderContabilidadException(750);
		}
		;

		if(pAnio == null) {
			throw new TrascenderContabilidadException(751);
		}

		List<AsociacionRefinanciacion> locListaAsociaciones = this.findListaAsociacionRefinanciacion(pAnio);

		if(!locListaAsociaciones.isEmpty()) {
			throw new TrascenderContabilidadException(752);
		}

		Double locPorcentaje = 0d;
		for(ParametroAsociacion parametroAsociacion : pListaParametrosAsociacion) {
			locPorcentaje += parametroAsociacion.getPorcentaje();
		}

		if(locPorcentaje != 100d) {
			throw new TrascenderContabilidadException(753);
		}

		List<DocumentoRefinanciacion> locListaDocumentosRefinanciacion = Criterio.getInstance(entity, DocumentoRefinanciacion.class)
				.add(Restriccion.IGUAL("anioInicioRefinanciacion", pAnio)).list();

		AsociacionRefinanciacion locAsociacionRefinanciacion = new AsociacionRefinanciacion();
		for(DocumentoRefinanciacion cadaDocumentoRefinanciacion : locListaDocumentosRefinanciacion) {
			for(ParametroAsociacion cadaParametroAsociacion : pListaParametrosAsociacion) {
				CuentaRefinanciacion locCuentaRefinanciacion = new CuentaRefinanciacion();

				locCuentaRefinanciacion.setParametroAsociacion(cadaParametroAsociacion);
				locCuentaRefinanciacion.setCuenta(cadaParametroAsociacion.getCuenta());
				locCuentaRefinanciacion.setDocumentoRefinanciacion(cadaDocumentoRefinanciacion);
				locCuentaRefinanciacion.setImporte(cadaDocumentoRefinanciacion.getTotalAPagar() * cadaParametroAsociacion.getPorcentaje() / 100);

				locCuentaRefinanciacion.setAsociacionRefinanciacion(locAsociacionRefinanciacion);

				locAsociacionRefinanciacion.getListaCuentaRefinanciacion().add(locCuentaRefinanciacion);
			}
		}

		entity.persist(locAsociacionRefinanciacion);
		return locAsociacionRefinanciacion;
	}

	public com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion updateAsociacionRefinanciacion(AsociacionRefinanciacion pAsociacionRefinanciacion) {
		entity.merge(pAsociacionRefinanciacion);
		return pAsociacionRefinanciacion;
	}

	public void deleteAsociacionRefinanciacion(com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion pAsociacionRefinanciacion) throws java.lang.Exception {
		pAsociacionRefinanciacion = entity.merge(pAsociacionRefinanciacion);
		entity.remove(pAsociacionRefinanciacion);
	}

	public com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion getAsociacionRefinanciacionByID(Long pId) throws java.lang.Exception {
		AsociacionRefinanciacion locAsociacionRefinanciacion = entity.find(AsociacionRefinanciacion.class, pId);
		if(locAsociacionRefinanciacion != null) {
			for(CuentaRefinanciacion unaCuentaRef : locAsociacionRefinanciacion.getListaCuentaRefinanciacion()) {
				unaCuentaRef.getDocumentoRefinanciacion();
				unaCuentaRef.getDocumentoRefinanciacion().toString();
				unaCuentaRef.getParametroAsociacion();
			}

			for(ParametroAsociacion unParametroAsociacion : locAsociacionRefinanciacion.getListaParametrosAsociacion()) {
				unParametroAsociacion.toString();
				unParametroAsociacion.getCuenta();
			}
		}
		return locAsociacionRefinanciacion;
	}

	public List<AsociacionRefinanciacion> findListaAsociacionRefinanciacion(Integer pAnio) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, AsociacionRefinanciacion.class).add(Restriccion.IGUAL("anio", pAnio));
		List<AsociacionRefinanciacion> locListaAsociacionRefinanciacion = locCriterio.list();
		return locListaAsociacionRefinanciacion;
	}

	private void validarCuentaRefinacion(CuentaRefinanciacion pAsociacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaRefinanciacion.class).add(Restriccion.IGUAL("documentoRefinanciacion", pAsociacion.getDocumentoRefinanciacion()))
				.add(Restriccion.IGUAL("cuenta", pAsociacion.getCuenta())).add(Restriccion.DISTINTO("idAsociacionCuenta", pAsociacion.getIdAsociacionCuenta()))
				.setProyeccion(Proyeccion.COUNT());

		Long resultado = locCriterio.uniqueResult();

		if(resultado > 0) {
			throw new TrascenderContabilidadException(84);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion addCuentaRefinanciacion(
			com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion pCuentaRefinanciacion) throws java.lang.Exception {
		validarCuentaRefinacion(pCuentaRefinanciacion);
		entity.persist(pCuentaRefinanciacion);
		return pCuentaRefinanciacion;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion updateCuentaRefinanciacion(
			com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion pCuentaRefinanciacion) throws java.lang.Exception {
		validarCuentaRefinacion(pCuentaRefinanciacion);
		return entity.merge(pCuentaRefinanciacion);
	}

	public void deleteCuentaRefinanciacion(com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion pCuentaRefinanciacion) throws java.lang.Exception {
		pCuentaRefinanciacion = entity.merge(pCuentaRefinanciacion);
		entity.remove(pCuentaRefinanciacion);
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion getCuentaRefinanciacionByID(Long pId) throws java.lang.Exception {
		CuentaRefinanciacion locCuentaRefinanciacion = entity.find(CuentaRefinanciacion.class, pId);
		if(locCuentaRefinanciacion != null) {
			locCuentaRefinanciacion.getDocumentoRefinanciacion();
			locCuentaRefinanciacion.getCuenta().toString();
			// locCuentaRefinanciacion.getPeriodo();
			locCuentaRefinanciacion.toString();
		}
		return locCuentaRefinanciacion;
	}

	public List<CuentaRefinanciacion> findListaCuentaRefinanciacion(DocumentoRefinanciacion pRefinanciacion, Integer pAnio, Cuenta pCuenta) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, CuentaRefinanciacion.class).add(Restriccion.IGUAL("documentoRefinanciacion", pRefinanciacion))
				.add(Restriccion.IGUAL("cuenta", pCuenta));

		List<CuentaRefinanciacion> locListaCuentaRefinanciacion = locCriterio.list();

		for(CuentaRefinanciacion locCuentaRefinanciacion : locListaCuentaRefinanciacion) {
			locCuentaRefinanciacion.getDocumentoRefinanciacion().toString();
			// locCuentaRefinanciacion.getPeriodo().toString();
			locCuentaRefinanciacion.getCuenta().toString();
		}
		return locListaCuentaRefinanciacion;
	}

	private void validarCuentaConceptoIngresoVario(CuentaConceptoIngresoVario pAsociacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaConceptoIngresoVario.class).add(Restriccion.IGUAL("conceptoIngresoVario", pAsociacion.getConceptoIngresoVario()))
				.add(Restriccion.IGUAL("cuenta", pAsociacion.getCuenta())).add(Restriccion.DISTINTO("idAsociacionCuenta", pAsociacion.getIdAsociacionCuenta()))
				.setProyeccion(Proyeccion.COUNT());

		Long resultado = locCriterio.uniqueResult();

		if(resultado > 0) {
			throw new TrascenderContabilidadException(82);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario addCuentaConceptoIngresoVario(
			com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario pCuentaConceptoIngresoVario) throws java.lang.Exception {
		validarCuentaConceptoIngresoVario(pCuentaConceptoIngresoVario);
		entity.persist(pCuentaConceptoIngresoVario);
		return pCuentaConceptoIngresoVario;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario updateCuentaConceptoIngresoVario(
			com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario pCuentaConceptoIngresoVario) throws java.lang.Exception {
		validarCuentaConceptoIngresoVario(pCuentaConceptoIngresoVario);
		return entity.merge(pCuentaConceptoIngresoVario);
	}

	public void deleteCuentaConceptoIngresoVario(com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario pCuentaConceptoIngresoVario) throws java.lang.Exception {
		pCuentaConceptoIngresoVario = entity.merge(pCuentaConceptoIngresoVario);
		entity.remove(pCuentaConceptoIngresoVario);
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario getCuentaConceptoIngresoVarioByID(Long pId) throws java.lang.Exception {
		CuentaConceptoIngresoVario locCuentaConceptoIngresoVario = entity.find(CuentaConceptoIngresoVario.class, pId);
		if(locCuentaConceptoIngresoVario != null) {
			locCuentaConceptoIngresoVario.getConceptoIngresoVario();
			locCuentaConceptoIngresoVario.getCuenta().toString();
			// locCuentaConceptoIngresoVario.getPeriodo();
			locCuentaConceptoIngresoVario.toString();
		}

		return locCuentaConceptoIngresoVario;
	}

	public List<CuentaConceptoIngresoVario> findListaCuentaConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario, Integer pAnio, Cuenta pCuenta) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, CuentaConceptoIngresoVario.class).add(Restriccion.IGUAL("conceptoIngresoVario", pConceptoIngresoVario))
				.add(Restriccion.IGUAL("cuenta", pCuenta));

		List<CuentaConceptoIngresoVario> locListaCuentaConceptoIngresoVario = locCriterio.list();

		for(CuentaConceptoIngresoVario locCuentaConcepto : locListaCuentaConceptoIngresoVario) {
			locCuentaConcepto.getConceptoIngresoVario().toString();
			// locCuentaConcepto.getPeriodo().toString();
			locCuentaConcepto.getCuenta().toString();
		}

		return locListaCuentaConceptoIngresoVario;
	}

	private void validarCuentaTipoTasa(CuentaTipoTasa pAsociacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaTipoTasa.class).add(Restriccion.IGUAL("cuenta", pAsociacion.getCuenta()))
				.add(Restriccion.IGUAL("tipoTasa", pAsociacion.getTipoTasa())).add(Restriccion.DISTINTO("idAsociacionCuenta", pAsociacion.getIdAsociacionCuenta()))
				.setProyeccion(Proyeccion.COUNT());

		Long resultado = locCriterio.uniqueResult();

		if(resultado > 0) {
			throw new TrascenderContabilidadException(80);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa addCuentaTipoTasa(com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa pCuentaTipoTasa)
			throws java.lang.Exception {
		validarCuentaTipoTasa(pCuentaTipoTasa);
		entity.persist(pCuentaTipoTasa);
		return pCuentaTipoTasa;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa updateCuentaTipoTasa(com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa pCuentaTipoTasa)
			throws java.lang.Exception {
		validarCuentaTipoTasa(pCuentaTipoTasa);
		return entity.merge(pCuentaTipoTasa);
	}

	public void deleteCuentaTipoTasa(com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa pCuentaTipoTasa) throws java.lang.Exception {
		pCuentaTipoTasa = entity.merge(pCuentaTipoTasa);
		entity.remove(pCuentaTipoTasa);
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa getCuentaTipoTasaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		CuentaTipoTasa locCuentaTipoTasa = entity.find(CuentaTipoTasa.class, pId);
		if(locCuentaTipoTasa != null) {
			locCuentaTipoTasa.getTipoTasa();
			locCuentaTipoTasa.getCuenta();
			locCuentaTipoTasa.getCuenta().toString();
			if(locCuentaTipoTasa.getCuentaPagosAtrasados() != null) {
				locCuentaTipoTasa.getCuentaPagosAtrasados().toString();
			}
			// locCuentaTipoTasa.getPeriodo();
			locCuentaTipoTasa.toString();
		}

		return locCuentaTipoTasa;
	}

	public List findListaCuentaTipoTasa(Cuenta pCuenta, TipoTasa pTipoTasa, Integer pAnio) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaTipoTasa.class).add(Restriccion.IGUAL("cuenta", pCuenta)).add(Restriccion.IGUAL("tipoTasa", pTipoTasa));
		List<CuentaTipoTasa> listaCuentaTipoTasa = locCriterio.list();

		for(CuentaTipoTasa cadaCuentaTipoTasa : listaCuentaTipoTasa) {
			cadaCuentaTipoTasa.getCuenta().toString();
			cadaCuentaTipoTasa.getCuenta().getStringCodigoImputacion();
			cadaCuentaTipoTasa.getTipoTasa().toString();
		}
		return listaCuentaTipoTasa;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura addCuentaLineaFactura(com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura pCuentaLineaFactura)
			throws java.lang.Exception {

		List locListaCuenta = this.findListaCuentaLineaFactura(pCuentaLineaFactura.getLineaFactura(), pCuentaLineaFactura.getCuenta(), null);

		if(!locListaCuenta.isEmpty()) {
			throw new TrascenderContabilidadException(83);
		}
		entity.persist(pCuentaLineaFactura);
		return pCuentaLineaFactura;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura updateCuentaLineaFactura(
			com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura pCuentaLineaFactura) throws Exception {

		entity.merge(pCuentaLineaFactura);
		return pCuentaLineaFactura;
	}

	public void deleteCuentaLineaFactura(com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura pCuentaLineaFactura) throws Exception {
		entity.merge(pCuentaLineaFactura);
		entity.remove(pCuentaLineaFactura);
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura getCuentaLineaFacturaPorId(long pId) throws Exception {
		CuentaLineaFactura locCuentaLineaFactura = entity.find(CuentaLineaFactura.class, pId);

		if(locCuentaLineaFactura != null) {
			locCuentaLineaFactura.toString();
		}

		if(locCuentaLineaFactura.getCuenta() != null) {
			locCuentaLineaFactura.getCuenta().toString();
		}

		if(locCuentaLineaFactura.getLineaFactura() != null) {
			locCuentaLineaFactura.getLineaFactura().toString();
		}

		return locCuentaLineaFactura;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura getCuentaLineaFactura(com.trascender.compras.recurso.persistent.suministros.LineaFactura pLineaFactura)
			throws Exception {
		CuentaLineaFactura locCuenta = Criterio.getInstance(entity, CuentaLineaFactura.class).add(Restriccion.IGUAL("lineaFactura", pLineaFactura)).uniqueResult();
		if(locCuenta == null) {
			throw new TrascenderContabilidadException(200);
		}
		locCuenta.toString();
		locCuenta.getCuenta().toString();
		locCuenta.getLineaFactura().toString();
		// locCuenta.getPeriodo().toString();
		// locCuenta.getPeriodo().getFechaInicio();
		// locCuenta.getPeriodo().getFechaFin();
		return locCuenta;
	}

	public List findListaCuentaLineaFactura(LineaFactura pLineaFactura, Cuenta pCuenta, Periodo pPeriodo) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaLineaFactura.class).add(Restriccion.IGUAL("lineaFactura", pLineaFactura)).add(Restriccion.IGUAL("cuenta", pCuenta))
				.add(Restriccion.IGUAL("periodo", pPeriodo));

		List locListaCuentaLineaFactura = locCriterio.list();

		for(Object cadaObject : locListaCuentaLineaFactura) {
			CuentaLineaFactura locCuentaLineaFactura = new CuentaLineaFactura();
			locCuentaLineaFactura = (CuentaLineaFactura) cadaObject;
			locCuentaLineaFactura.toString();
		}

		return locListaCuentaLineaFactura;
	}

	public CuentaArticulo addCuentaArticulo(CuentaArticulo pCuentaArticulo) throws java.lang.Exception {
		validarCuentaArticulo(pCuentaArticulo);
		MovimientoCuentaIngreso locMovimientoCuentaIngreso = new MovimientoCuentaIngreso();
		locMovimientoCuentaIngreso.setImporte(pCuentaArticulo.getArticulo().getCosto());
		locMovimientoCuentaIngreso.setFecha(new Date());
		locMovimientoCuentaIngreso.setHora(new Time(locMovimientoCuentaIngreso.getFecha().getTime()));
		locMovimientoCuentaIngreso.setCuenta(pCuentaArticulo.getCuenta());
		pCuentaArticulo.setMovimientoCuentaIngreso(locMovimientoCuentaIngreso);
		entity.persist(pCuentaArticulo);
		pCuentaArticulo.getArticulo().setEstadoContable(EstadoContable.ACTIVO);
		businessStockLocal.updateArticulo(pCuentaArticulo.getArticulo());
		return pCuentaArticulo;
	}

	public CuentaArticulo updateCuentaArticulo(CuentaArticulo pCuentaArticulo) throws java.lang.Exception {
		if(pCuentaArticulo.getCuenta() != null) {
			pCuentaArticulo.getMovimientoCuentaIngreso().setCuenta(pCuentaArticulo.getCuenta());
		}
		validarCuentaArticulo(pCuentaArticulo);
		entity.merge(pCuentaArticulo);
		return pCuentaArticulo;
	}

	private void validarCuentaArticulo(CuentaArticulo pCuentaArticulo) throws Exception {
		Long cantidad = Criterio.getInstance(entity, CuentaArticulo.class).add(Restriccion.DISTINTO("idAsociacionCuenta", pCuentaArticulo.getIdAsociacionCuenta()))
				.add(Restriccion.IGUAL("articulo", pCuentaArticulo.getArticulo())).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad.intValue() > 0) {
			throw new TrascenderContabilidadException(85);
		}
	}

	// public CuentaArticulo deleteCuentaArticulo(CuentaArticulo pCuentaArticulo) throws java.lang.Exception;

	public List<CuentaArticulo> findListaCuentaArticulo(Articulo pArticulo, Integer pAnio, Cuenta pCuenta) throws java.lang.Exception {
		// Periodo locPeriodo = null;
		// if (pAnio != null){
		// locPeriodo = businessRegistroValuadoLocal.getPeriodo(Periodicidad.ANUAL, 1, pAnio);
		// }
		Criterio locCriterio = Criterio.getInstance(entity, CuentaArticulo.class).add(Restriccion.IGUAL("articulo", pArticulo)).add(Restriccion.IGUAL("cuenta", pCuenta));
		List<CuentaArticulo> locListaResultado = locCriterio.list();
		for(CuentaArticulo cadaCuentaArticulo : locListaResultado) {
			cadaCuentaArticulo.toString();
			// cadaCuentaArticulo.getPeriodo().toString();
		}
		return locListaResultado;
	}

	public CuentaArticulo getCuentaArticuloPorId(long pIdCuentaArticulo) throws Exception {
		CuentaArticulo locCuentaArticulo = entity.find(CuentaArticulo.class, pIdCuentaArticulo);
		if(locCuentaArticulo != null) {
			locCuentaArticulo.toString();
			// locCuentaArticulo.getPeriodo().toString();
			if(locCuentaArticulo.getBajaArticulo() != null) {
				locCuentaArticulo.getBajaArticulo().toString();
			}
		}
		return locCuentaArticulo;
	}

	public BajaArticulo addBajaArticulo(BajaArticulo pBajaArticulo) throws Exception {
		prepararMovimientosEgreso(pBajaArticulo);
		entity.persist(pBajaArticulo);
		Articulo locArticulo = pBajaArticulo.getCuentaArticulo().getArticulo();
		locArticulo.setEstadoContable(EstadoContable.INACTIVO);
		businessStockLocal.updateArticulo(locArticulo);
		return pBajaArticulo;
	}

	private void prepararMovimientosEgreso(BajaArticulo pBajaArticulo) {
		MovimientoCuentaEgreso locMovimientoEgreso = new MovimientoCuentaEgreso();
		locMovimientoEgreso.setCuenta(pBajaArticulo.getCuentaEgreso());
		locMovimientoEgreso.setFecha(pBajaArticulo.getFecha());
		locMovimientoEgreso.setImporte(pBajaArticulo.getCuentaArticulo().getArticulo().getCosto());
		pBajaArticulo.setMovimientoCuentaEgreso(locMovimientoEgreso);

		MovimientoCuentaIngreso locMovimientoIngreso = new MovimientoCuentaIngreso();
		locMovimientoIngreso.setCuenta(pBajaArticulo.getCuentaIngreso());
		locMovimientoIngreso.setFecha(pBajaArticulo.getFecha());
		locMovimientoIngreso.setImporte(pBajaArticulo.getCuentaArticulo().getArticulo().getCosto());
		pBajaArticulo.setMovimientoCuentaIngreso(locMovimientoIngreso);
	}

	public com.trascender.compras.recurso.persistent.reference.CuentaRfr getCuentaRfr(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws Exception {
		CuentaRfr locCuentaRfr = new CuentaRfr();
		locCuentaRfr.setAbreviatura(pCuenta.getAbreviatura());
		locCuentaRfr.setNombre(pCuenta.getNombre());
		locCuentaRfr.setIdCuenta(pCuenta.getIdCuenta());
		return locCuentaRfr;
	}

	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta importarPlanDeCuenta(com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta) throws Exception {
		PlanDeCuenta planDeCuentaBase = this.getPlanDeCuentaByID(pPlanDeCuenta.getIdPlanDeCuenta());
		this.planDeCuenta = new PlanDeCuenta();

		for(Cuenta pCuenta : planDeCuentaBase.getListaCuentas()) {
			if(pCuenta.getCuentaPadre() == null) {
				Cuenta locCuenta = this.importarCuenta(pCuenta);
			}
		}

		for(Cuenta cuentaDeNuevo : this.planDeCuenta.getListaCuentas()) {
			cuentaDeNuevo.getCodigoImputacionCompleto();
		}

		return this.planDeCuenta;
	}

	private com.trascender.contabilidad.recurso.persistent.Cuenta importarCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws Exception {
		Cuenta locCuenta = new Cuenta();

		locCuenta.setIdCuenta(-1);
		locCuenta.setAbreviatura(pCuenta.getAbreviatura());
		locCuenta.setNombre(pCuenta.getNombre());
		locCuenta.setListaTipoCuenta(pCuenta.getListaTipoCuenta());
		locCuenta.setPlanDeCuenta(this.planDeCuenta);

		this.planDeCuenta.getListaCuentas().add(locCuenta);

		for(Cuenta pCuentaHijo : pCuenta.getCuentasHijos()) {
			Cuenta locCuentaHijo = this.importarCuenta(pCuentaHijo);
			locCuentaHijo.setCuentaPadre(locCuenta);
			locCuenta.getCuentasHijos().add(locCuentaHijo);
		}

		return locCuenta;
	}

	// Obtengo el importe acumulado y el importe presupuestado
	private CuentaHistoricaBalance obtenerImportePresupuestado(Cuenta pCuenta) throws Exception {
		CuentaHistoricaBalance locCuentaHistoricaBalance = new CuentaHistoricaBalance();

		// Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodo(SecurityMgr.getInstance().getFechaActual(), Periodicidad.ANUAL);
		// Periodo locPeriodo = this.businessRegistroValuadoLocal.getPeriodo(locPeriodoTransient.getPeriodicidad(), locPeriodoTransient.getNumeroPeriodo(),
		// locPeriodoTransient.getFechaFin().get(Calendar.YEAR));
		LineaPresupuesto locLinea = Criterio.getInstance(entity, LineaPresupuesto.class).add(Restriccion.IGUAL("cuenta", pCuenta))
				.add(Restriccion.IGUAL("presupuesto.anio", Calendar.getInstance().get(Calendar.YEAR)))
				// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
				.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).uniqueResult();

		if(locLinea instanceof LineaPresupuestoRecursos) {
			LineaPresupuestoRecursos locLineaPresupuestoRecursos = (LineaPresupuestoRecursos) locLinea;
			if(locLineaPresupuestoRecursos.getMontoEstimado() != null) {
				locCuentaHistoricaBalance.setImportePresupuestado(locLineaPresupuestoRecursos.getMontoEstimado());
			} else {
				locCuentaHistoricaBalance.setImportePresupuestado(0D);
			}
			if(locLineaPresupuestoRecursos.getMontoRecaudado() != null) {
				locCuentaHistoricaBalance.setAcumulado(locLineaPresupuestoRecursos.getMontoRecaudado());
			} else {
				locCuentaHistoricaBalance.setAcumulado(0D);
			}
		}
		if(locLinea instanceof LineaPresupuestoGastos) {
			LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos) locLinea;
			if(locLineaPresupuestoGastos.getMontoPresupuestado() != null) {
				locCuentaHistoricaBalance.setImportePresupuestado(locLineaPresupuestoGastos.getMontoPresupuestado());
			} else {
				locCuentaHistoricaBalance.setImportePresupuestado(0D);
			}

			if(locLineaPresupuestoGastos.getMontoPagado() != null) {
				locCuentaHistoricaBalance.setAcumulado(locLineaPresupuestoGastos.getMontoPagado());
			} else {
				locCuentaHistoricaBalance.setAcumulado(0D);
			}
		}
		return locCuentaHistoricaBalance;
	}

	private void validarCuentaInteresRecargo(CuentaInteresRecargo pAsociacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaInteresRecargo.class).add(Restriccion.IGUAL("cuenta", pAsociacion.getCuenta()))
				.add(Restriccion.IGUAL("conceptoPorMora", pAsociacion.getConceptoPorMora())).add(Restriccion.DISTINTO("idAsociacionCuenta", pAsociacion.getIdAsociacionCuenta()))
				.setProyeccion(Proyeccion.COUNT());

		Long resultado = locCriterio.uniqueResult();

		if(resultado > 0) {
			throw new TrascenderContabilidadException(86);
		}
	}

	public CuentaInteresRecargo addCuentaInteresRecargo(CuentaInteresRecargo pCuentaInteresRecargo) throws java.lang.Exception {
		validarCuentaInteresRecargo(pCuentaInteresRecargo);
		entity.persist(pCuentaInteresRecargo);
		return pCuentaInteresRecargo;
	}

	public CuentaInteresRecargo updateCuentaInteresRecargo(CuentaInteresRecargo pCuentaInteresRecargo) throws java.lang.Exception {
		validarCuentaInteresRecargo(pCuentaInteresRecargo);
		return entity.merge(pCuentaInteresRecargo);
	}

	public void deleteCuentaInteresRecargo(CuentaInteresRecargo pCuentaInteresRecargo) throws java.lang.Exception {
		pCuentaInteresRecargo = entity.merge(pCuentaInteresRecargo);
		entity.remove(pCuentaInteresRecargo);
	}

	public CuentaInteresRecargo getCuentaInteresRecargoByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}

		CuentaInteresRecargo locCuentaInteresRecargo = entity.find(CuentaInteresRecargo.class, pId);
		if(locCuentaInteresRecargo != null) {
			locCuentaInteresRecargo.getConceptoPorMora();
			locCuentaInteresRecargo.getCuenta().toString();
			locCuentaInteresRecargo.toString();
		}

		return locCuentaInteresRecargo;
	}

	public List findListaCuentaInteresRecargo(Cuenta pCuenta, ConceptoPorMora pConceptoMora) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaInteresRecargo.class).add(Restriccion.IGUAL("cuenta", pCuenta))
				.add(Restriccion.IGUAL("conceptoPorMora", pConceptoMora));

		List<CuentaInteresRecargo> listaCuentaInteresRecargo = locCriterio.list();
		for(CuentaInteresRecargo cadaCuentaTipoTasa : listaCuentaInteresRecargo) {
			cadaCuentaTipoTasa.getCuenta().toString();
			cadaCuentaTipoTasa.getCuenta().getStringCodigoImputacion();
			cadaCuentaTipoTasa.getConceptoPorMora().toString();
		}

		return listaCuentaInteresRecargo;
	}
}
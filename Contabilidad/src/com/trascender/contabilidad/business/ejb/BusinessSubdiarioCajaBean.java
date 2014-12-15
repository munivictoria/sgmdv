
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.contabilidad.business.interfaces.BusinessSubdiarioCajaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.MovimientoCaja;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "ejb/BusinessSubdiarioCajaLocal")
public class BusinessSubdiarioCajaBean implements BusinessSubdiarioCajaLocal {

	private static final long serialVersionUID = 2677214272739788140L;
	private static final String NOMBRE = "CAJ|Adm Subdiario Caja";

	@PersistenceContext
	private EntityManager entity;

	public BusinessSubdiarioCajaBean() {
	}

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessSubdiarioCajaBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessSubdiarioCajaBean.NOMBRE);

		Recurso subdiario = new Recurso();
		subdiario.setNombre("Subidario");
		subdiario.setIdRecurso(SubdiarioCaja.serialVersionUID);
		subdiario.setClase(SubdiarioCaja.class);
		grupoRecursos.getListaRecursos().add(subdiario);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
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

	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja addSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja)
			throws java.lang.Exception {
		// for (LineaSubdiarioCaja locLineaSubdiarioCaja : pSubdiarioCaja.getLineasSubdiarioCaja()){
		// if (pSubdiarioCaja.getTipo() == SubdiarioCaja.Tipo.INGRESO){
		// this.modificarPresupuestoRecurso(locLineaSubdiarioCaja);
		// }
		// if(pSubdiarioCaja.getTipo() == SubdiarioCaja.Tipo.EGRESO){
		// this.modificarPresupuestoGastos(locLineaSubdiarioCaja);
		// }
		// if (pSubdiarioCaja.getTipo() == SubdiarioCaja.Tipo.COMPRAS){
		// this.modificarPresupuestoCompras(locLineaSubdiarioCaja);
		// }
		// }
		entity.persist(pSubdiarioCaja);
		return pSubdiarioCaja;
	}

	private Double obtenerTotalLineasFacturas(Cuenta pCuenta, Date pFecha) throws Exception {
		// busco las cuentas de lineas de factura que tienen asociacion entre alguna cuenta
		// y alguna linea de factura de la lista anterior

		// Cuenta Linea Factura no se utiliza mas, pues cada linea referencia directamente a la cuenta
		// a travez de CuentaRfr.

		/*
		 * List<CuentaLineaFactura> locListaCuentasLineasFactura = this.session.createCriteria(CuentaLineaFactura.class) .setFetchMode("cuenta", FetchMode.JOIN)
		 * .add(Restrictions.eq("cuenta", pCuenta)) .createAlias("lineaFactura", "cadaLineaFactura") .createAlias("cadaLineaFactura.factura","locFactura")
		 * .add(Restrictions.eq("locFactura.fechaEmision",pFecha)) .add(Restrictions.eq("locFactura.estado",Factura.Estado.PROCESADA)) .list();
		 * 
		 * for(CuentaLineaFactura cadaCuentaLineaFactura: locListaCuentasLineasFactura){ System.out.println(cadaCuentaLineaFactura);
		 * cadaCuentaLineaFactura.toString(); locTotalLineaFactura += cadaCuentaLineaFactura.getLineaFactura().getTotal(); }
		 */

		// List<LineaFactura> locListaLineaFacturas = this.session.createCriteria(LineaFactura.class)
		// .setFetchMode("cuenta", FetchMode.JOIN)
		// .createAlias("cuenta", "cadaCuenta")
		// .add(Restrictions.eq("cadaCuenta.idCuenta", pCuenta.getIdCuenta()))
		// .createAlias("factura", "cadaFactura")
		// .add(Restrictions.eq("cadaFactura.estado", Factura.Estado.PROCESADA))
		// .add(Restrictions.eq("cadaFactura.fechaEmision", pFecha))
		// .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
		// .list();

		Double suma = 0D;
		Criterio locCriterio = Criterio.getInstance(entity, LineaFactura.class).add(Restriccion.IGUAL("factura.estado", Factura.Estado.PROCESADA))
				.add(Restriccion.IGUAL("factura.fechaEmision", pFecha)).add(Restriccion.IGUAL("cuenta.idCuenta", pCuenta.getIdCuenta())).setProyeccion(Proyeccion.PROP("total"));
		List<Object[]> listaResultado = locCriterio.list();
		for(Object[] cadaArreglo : listaResultado) {
			suma += (Double) cadaArreglo[0];
		}
		System.out.println("SUMA" + suma);
		return suma;
	}

	@SuppressWarnings("unchecked")
	private Double obtenerTotalLineasOrdenDeCompra(Cuenta pCuenta, Date pFecha) throws Exception {
		Double locTotal = 0D;
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(pFecha);
		// Periodo locPeriodo = SecurityMgr.getInstance().getPeriodoAnual(locCalendar);
		locTotal = Criterio.getInstance(entity, LineaOrdenCompra.class).add(Restriccion.IGUAL("ordenCompra.fechaEmision", pFecha))
				.add(Restriccion.DISTINTO("ordenCompra.estado", Factura.Estado.CANCELADA)).add(Restriccion.IGUAL("cuenta.idCuenta", pCuenta.getIdCuenta()))
				.setProyeccion(Proyeccion.SUM("montoTotal")).uniqueResult();
		return locTotal;
	}

	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja updateSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja)
			throws java.lang.Exception {
		entity.merge(pSubdiarioCaja);
		return pSubdiarioCaja;
	}

	public void deleteSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja) throws java.lang.Exception {
		entity.merge(pSubdiarioCaja);
		entity.remove(pSubdiarioCaja);
	}

	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja getSubdiarioCajaByID(Long pIdSubdiarioCaja) throws java.lang.Exception {
		if(pIdSubdiarioCaja == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pIdSubdiarioCaja == -1) {
			throw new TrascenderContabilidadException(299);
		}
		SubdiarioCaja locSubdiarioCaja = entity.find(SubdiarioCaja.class, pIdSubdiarioCaja);
		for(LineaSubdiarioCaja locLineaSubdiarioCaja : locSubdiarioCaja.getLineasSubdiarioCaja()) {
			locLineaSubdiarioCaja.getCuenta();
			locLineaSubdiarioCaja.getCuenta().toString();
			locLineaSubdiarioCaja.getDia();
			locLineaSubdiarioCaja.getImporte();
		}
		return locSubdiarioCaja;
	}

	public List findListaSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo, Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception {
		return Criterio.getInstance(entity, SubdiarioCaja.class).add(Restriccion.IGUAL("tipo", pTipo)).add(Restriccion.MAYOR("fechaCreacion", pFechaDesde))
				.add(Restriccion.MENOR("fechaCreacion", pFechaHasta)).list();
	}

	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja generarSubdiarioCaja(Date pFecha, PlanDeCuenta pPlanDeCuenta,
			com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo) throws java.lang.Exception {
		pPlanDeCuenta = this.entity.merge(pPlanDeCuenta);
		Cuenta locCuenta = pPlanDeCuenta.getListaCuentas().iterator().next();
		SubdiarioCaja subdiarioCajaConsulta = buscarSubdiario(pFecha, pTipo, locCuenta);

		if(subdiarioCajaConsulta != null) {
			throw new TrascenderContabilidadException(60);
		}

		SubdiarioCaja locSubdiarioCaja = new SubdiarioCaja();
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(pFecha);
		if(pTipo == SubdiarioCaja.Tipo.INGRESO) {
			locSubdiarioCaja = armarSubdiarioCajaIngreso(locCalendar, pPlanDeCuenta);
		} else if(pTipo == SubdiarioCaja.Tipo.EGRESO) {
			locSubdiarioCaja = armarSubdiarioCajaEgreso(locCalendar, pPlanDeCuenta);
		} else if(pTipo == SubdiarioCaja.Tipo.COMPRAS) {
			locSubdiarioCaja = armarSubdiarioCajaDevengamiento(locCalendar, pPlanDeCuenta);
		}
		locSubdiarioCaja.setTipo(pTipo);
		locSubdiarioCaja.setFechaCreacion(pFecha);
		return locSubdiarioCaja;
	}

	// TODO: Deprecado. EL monto comprometido debería actualizarse por medio de la aceptación de la orden de compra
	private SubdiarioCaja armarSubdiarioCajaCompras(Calendar pCalendar, PlanDeCuenta pPlanDeCuenta) throws Exception {
		SubdiarioCaja locSubdiarioCaja = new SubdiarioCaja();
		List listaCuentas = Criterio.getInstance(entity, Cuenta.class).add(Restriccion.IGUAL("planDeCuenta", pPlanDeCuenta)).add(Restriccion.ESTA_VACIO("cuentasHijos"))
				.setDistinct(true).list();

		for(Object obj : listaCuentas) {
			Cuenta locCuenta = (Cuenta) obj;
			Double importe = new Double(0);
			locCuenta.toString();
			locCuenta.getCodigoImputacionCompleto();

			LineaSubdiarioCaja locLineaSubdiarioCaja = new LineaSubdiarioCaja();

			importe += this.obtenerTotalLineasFacturas(locCuenta, pCalendar.getTime());
			locLineaSubdiarioCaja.setCuenta(locCuenta);
			locLineaSubdiarioCaja.setSubdiarioCaja(locSubdiarioCaja);
			locLineaSubdiarioCaja.setImporte(importe);
			locLineaSubdiarioCaja.setDia(pCalendar.get(Calendar.DAY_OF_MONTH));
			locSubdiarioCaja.getLineasSubdiarioCaja().add(locLineaSubdiarioCaja);
		}
		return locSubdiarioCaja;
	}

	/*
	 * Actualizacion del método armarSubdiarioCajaCompras. Esto debería traer las facturas procesadas. Falta pasar las facturas de PROCESADA a DEVENGADA
	 */
	private SubdiarioCaja armarSubdiarioCajaDevengamiento(Calendar pCalendar, PlanDeCuenta pPlanDeCuenta) throws Exception {
		SubdiarioCaja locSubdiarioCaja = new SubdiarioCaja();
		List listaCuentas = Criterio.getInstance(entity, Cuenta.class).add(Restriccion.IGUAL("planDeCuenta", pPlanDeCuenta)).add(Restriccion.ESTA_VACIO("cuentasHijos"))
				.add(Restriccion.IGUAL("lineasFactura.factura.estado", Factura.Estado.PROCESADA)).list();

		for(Object obj : listaCuentas) {
			Cuenta locCuenta = (Cuenta) obj;
			Double importe = new Double(0);
			locCuenta.toString();
			locCuenta.getCodigoImputacionCompleto();

			LineaSubdiarioCaja locLineaSubdiarioCaja = new LineaSubdiarioCaja();
			importe += this.obtenerTotalLineasFacturas(locCuenta, pCalendar.getTime());
			locLineaSubdiarioCaja.setCuenta(locCuenta);
			locLineaSubdiarioCaja.setSubdiarioCaja(locSubdiarioCaja);
			locLineaSubdiarioCaja.setImporte(importe);
			locLineaSubdiarioCaja.setDia(pCalendar.get(Calendar.DAY_OF_MONTH));
			locSubdiarioCaja.getLineasSubdiarioCaja().add(locLineaSubdiarioCaja);
		}
		return locSubdiarioCaja;
	}

	private com.trascender.contabilidad.recurso.persistent.SubdiarioCaja buscarSubdiario(Date pFecha, SubdiarioCaja.Tipo pTipo, Cuenta pCuenta) {
		SubdiarioCaja subdiarioCaja = Criterio.getInstance(entity, SubdiarioCaja.class).add(Restriccion.IGUAL("fechaCreacion", pFecha)).add(Restriccion.IGUAL("tipo", pTipo))
				.add(Restriccion.IGUAL("lineasSubdiarioCaja.cuenta.planDeCuenta", pCuenta.getPlanDeCuenta())).uniqueResult();
		return subdiarioCaja;
	}

	private com.trascender.contabilidad.recurso.persistent.SubdiarioCaja armarSubdiarioCajaIngreso(Calendar pCalendar, PlanDeCuenta pPlanDeCuenta) {
		SubdiarioCaja locSubdiarioCaja = new SubdiarioCaja();
		// List listaCuentas = this.session.createCriteria(Cuenta.class)
		// .add(Restrictions.eq("planDeCuenta", pPlanDeCuenta))
		// .add(Restrictions.isEmpty("cuentasHijos"))
		// .createAlias("listaAsociacionCuenta", "cadaAsociacion")
		// .createAlias("cadaAsociacion.periodo", "cadaPeriodo")
		// .add(Restrictions.eq("cadaPeriodo.fechaInicio", locPeriodo.getFechaInicio()))
		// .add(Restrictions.eq("cadaPeriodo.periodicidad", locPeriodo.getPeriodicidad()))
		// .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
		// .list();
		// List listaCuentas = this.entity.createQuery("SELECT DISTINCT c FROM MovimientoCajaIngreso mci JOIN mci.cuenta c JOIN c.planDeCuenta p " +
		// "WHERE mci.fecha = :fecha AND p = :plan")
		// .setParameter("fecha", pCalendar.getTime())
		// .setParameter("plan", pPlanDeCuenta)
		// .getResultList();

		List<Cuenta> listaCuentas = Criterio.getInstance(entity, MovimientoCajaIngreso.class).add(Restriccion.IGUAL("cuenta.planDeCuenta", pPlanDeCuenta))
				.add(Restriccion.IGUAL("fecha", pCalendar.getTime())).setProyeccion(Proyeccion.PROP("cuenta")).setDistinct(true).list();

		for(Cuenta locCuenta : listaCuentas) {
			locCuenta.toString();
			locCuenta.getCodigoImputacionCompleto();

			Double importe = new Double(0);
			LineaSubdiarioCaja locLineaSubdiarioCaja = new LineaSubdiarioCaja();
			List listaMovimientos = this.obtenerListaDeMovimientosIngresoPorCuenta(pCalendar.getTime(), locCuenta);
			for(Object o : listaMovimientos) {
				MovimientoCaja locMovimientoCaja = (MovimientoCaja) o;
				importe += locMovimientoCaja.getImporte();
			}
			locLineaSubdiarioCaja.setCuenta(locCuenta);
			locLineaSubdiarioCaja.setSubdiarioCaja(locSubdiarioCaja);
			locLineaSubdiarioCaja.setImporte(importe);
			locLineaSubdiarioCaja.setDia(pCalendar.get(Calendar.DAY_OF_MONTH));
			locSubdiarioCaja.getLineasSubdiarioCaja().add(locLineaSubdiarioCaja);
		}
		return locSubdiarioCaja;
	}

	private com.trascender.contabilidad.recurso.persistent.SubdiarioCaja armarSubdiarioCajaEgreso(Calendar pCalendar, PlanDeCuenta pPlanDeCuenta) {
		SubdiarioCaja locSubdiarioCaja = new SubdiarioCaja();

		List<Cuenta> listaCuentas = Criterio.getInstance(entity, Cuenta.class).add(Restriccion.ESTA_VACIO("cuentasHijos")).add(Restriccion.IGUAL("planDeCuenta", pPlanDeCuenta))
				.add(Orden.ASC("codigoImputacion")).setDistinct(true).list();

		for(Cuenta locCuenta : listaCuentas) {
			Double importe = new Double(0);

			locCuenta.toString();
			locCuenta.getCodigoImputacionCompleto();

			LineaSubdiarioCaja locLineaSubdiarioCaja = new LineaSubdiarioCaja();
			// si no me equivoco aca deberia generarse
			List listaMovimientos = this.obtenerListaDeMovimientosEgresoPorCuenta(pCalendar.getTime(), locCuenta);
			for(Object o : listaMovimientos) {
				MovimientoCaja locMovimietoCaja = (MovimientoCaja) o;
				importe += locMovimietoCaja.getImporte();
			}
			locLineaSubdiarioCaja.setCuenta(locCuenta);
			locLineaSubdiarioCaja.setSubdiarioCaja(locSubdiarioCaja);
			locLineaSubdiarioCaja.setImporte(importe);
			locLineaSubdiarioCaja.setDia(pCalendar.get(Calendar.DAY_OF_MONTH));
			locSubdiarioCaja.getLineasSubdiarioCaja().add(locLineaSubdiarioCaja);
		}
		return locSubdiarioCaja;
	}

	private List obtenerListaDeMovimientosIngresoPorCuenta(Date pFecha, Cuenta pCuenta) {
		List listaMovimientos = Criterio.getInstance(entity, MovimientoCajaIngreso.class).add(Restriccion.IGUAL("fecha", pFecha)).add(Restriccion.IGUAL("cuenta", pCuenta))
				.add(Restriccion.IGUAL("detalleTicket.ticketCaja.estado", TicketCaja.Estado.ACTIVO)).list();
		return listaMovimientos;
	}

	private List obtenerListaDeMovimientosEgresoPorCuenta(Date pFecha, Cuenta pCuenta) {
		List listaMovimientos = Criterio.getInstance(entity, MovimientoCajaEgreso.class).add(Restriccion.IGUAL("fecha", pFecha)).add(Restriccion.IGUAL("cuenta", pCuenta)).list();

		return listaMovimientos;
	}

	public boolean validarAceptacionOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws java.lang.Exception {
		OrdenCompra locOrden = entity.find(OrdenCompra.class, pOrdenCompra.getIdOrdenCompra());
		boolean resultado = true;
		if(locOrden.getEstado() != OrdenCompra.Estado.APROBADA) {
			throw new TrascenderContabilidadException(760);
		}

		// Mapa para llevar la cuenta de los montos comprometidos de cada
		// caja
		Map<Long, Double> mapaMontos = new HashMap<Long, Double>();

		for(LineaOrdenCompra locLineaOrden : pOrdenCompra.getListaLineaOrdenCompra()) {
			// TODO La cuenta ya no se recupera de esta manera...
			// Long locIdCuenta = locLineaOrden.getLineaSolicitudSuministro().getCuentaRfr().getIdCuenta();
			Long locIdCuenta = 0l;
			Double locMonto = mapaMontos.get(locIdCuenta);
			if(locMonto != null) {
				locMonto += locLineaOrden.getMontoTotal();
			} else {
				mapaMontos.put(locIdCuenta, new Double(locLineaOrden.getMontoTotal()));
			}
		}

		for(Long locLlave : mapaMontos.keySet()) {
			Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodo(SecurityMgr.getInstance().getFechaActual(), Periodicidad.ANUAL);
			LineaPresupuestoGastos locLinea = Criterio.getInstance(entity, LineaPresupuestoGastos.class).add(Restriccion.IGUAL("cuenta.idCuenta", locLlave))
					.crearAlias("presupuesto.periodo", "cadaPeriodo").add(Restriccion.IGUAL("cadaPeriodo.fechaInicio", locPeriodoTransient.getFechaInicio()))
					.add(Restriccion.IGUAL("cadaPeriodo.periodicidad", locPeriodoTransient.getPeriodicidad()))
					.add(Restriccion.IGUAL("cadaPresupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).uniqueResult();

			if(locLinea == null) {
				throw new TrascenderContabilidadException(762);
			} else {
				Double locMontoComprometido = locLinea.getMontoComprometido() + mapaMontos.get(locLlave);
				if(locLinea.getMontoPresupuestado() < locMontoComprometido) {
					resultado = false;
					break;
				}
			}
		}
		return resultado;
	}

	public void aceptarOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws Exception {

		OrdenCompra locOrden = entity.find(OrdenCompra.class, pOrdenCompra.getIdOrdenCompra());
		if(locOrden.getEstado() != OrdenCompra.Estado.APROBADA) {
			throw new TrascenderContabilidadException(760);
		}

		// Mapa para llevar la cuenta de los montos comprometidos de cada
		// caja
		Map<Long, Double> mapaMontos = new HashMap<Long, Double>();

		for(LineaOrdenCompra locLineaOrden : pOrdenCompra.getListaLineaOrdenCompra()) {
			// TODO No se recupera de esta manera.
			// Long locIdCuenta = locLineaOrden.getLineaSolicitudSuministro().getCuentaRfr().getIdCuenta();
			Long locIdCuenta = 0l;
			Double locMonto = mapaMontos.get(locIdCuenta);
			if(locMonto != null) {
				locMonto += locLineaOrden.getMontoTotal();
			} else {
				mapaMontos.put(locIdCuenta, new Double(locLineaOrden.getMontoTotal()));
			}
		}

		for(Long locLlave : mapaMontos.keySet()) {
			Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodo(SecurityMgr.getInstance().getFechaActual(), Periodicidad.ANUAL);

			LineaPresupuestoGastos locLinea = Criterio.getInstance(entity, LineaPresupuestoGastos.class).add(Restriccion.IGUAL("cuenta.idCuenta", locLlave))
					.crearAlias("presupuesto.periodo", "cadaPeriodo").add(Restriccion.IGUAL("cadaPeriodo.fechaInicio", locPeriodoTransient.getFechaInicio()))
					.add(Restriccion.IGUAL("cadaPeriodo.periodicidad", locPeriodoTransient.getPeriodicidad()))
					.add(Restriccion.IGUAL("cadaPresupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).uniqueResult();
			if(locLinea == null) {
				throw new TrascenderContabilidadException(762);
			} else {
				Double locMontoComprometido = locLinea.getMontoComprometido() + mapaMontos.get(locLlave);

				// TODO No se sabe si deberia tirar excepcion o no, por ahora se puede usar el metodo
				// validarAceptacionOrdenCompra de este mismo bean que avisa si el monto de la orden de
				// compra supera el monto prespuestado de alguna de las cajas.

				// if (locLinea.getMontoPresupuestado() < locMontoComprometido) {
				// throw new TrascenderContabilidadException(761);
				// }
				locLinea.setMontoComprometido(locMontoComprometido);
			}
		}
	}

}
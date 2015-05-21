
package com.trascender.contabilidad.business.ejb;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.business.interfaces.BusinessPlanDeCuentaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.ArqueoCaja;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.HistoricoReimpresionTicket;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCancelado;
import com.trascender.contabilidad.recurso.transients.ResumenActualCajaDataSource;
import com.trascender.contabilidad.recurso.transients.ResumenActualCajaIngresoVarioDS;
import com.trascender.contabilidad.reporte.dataSource.ReporteCajaDinamico;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.THashMap;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionFormula;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;
import com.trascender.saic.recurso.persistent.RegistroCancelacionManual;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.Tasa;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

@Stateless(name = "ejb/BusinessCajaLocal")
public class BusinessCajaBean implements BusinessCajaLocal {

	private long llave;

	private Map<Long, CuentaTipoTasa> mapaCuentaTipoTasa;
	private Map<Long, CuentaModificador> mapaCuentaModificador;
	private Map<Long, CuentaInteresRecargo> mapaCuentaInteresRecargo;

	@Override
	public void setLlave(long llave) {
		this.llave = llave;
	}

	public void cargarMapasAsociacionesCuentas() {
		if(mapaCuentaTipoTasa == null && mapaCuentaModificador == null && mapaCuentaInteresRecargo == null) {
			mapaCuentaTipoTasa = new HashMap<Long, CuentaTipoTasa>();
			List<CuentaTipoTasa> locListaCuentasTipoTasa = Criterio.getInstance(entity, CuentaTipoTasa.class).list();
			for(CuentaTipoTasa cadaCuenta : locListaCuentasTipoTasa) {
				mapaCuentaTipoTasa.put(cadaCuenta.getTipoTasa().getIdTipoTasa(), cadaCuenta);
			}

			mapaCuentaModificador = new HashMap<Long, CuentaModificador>();
			List<CuentaModificador> locListaCuentasModificador = Criterio.getInstance(entity, CuentaModificador.class).list();
			for(CuentaModificador cadaCuenta : locListaCuentasModificador) {
				mapaCuentaModificador.put(cadaCuenta.getTipoModificador().getIdTipoModificador(), cadaCuenta);
			}

			mapaCuentaInteresRecargo = new HashMap<Long, CuentaInteresRecargo>();
			List<CuentaInteresRecargo> locListaCuentasInteresRecargo = Criterio.getInstance(entity, CuentaInteresRecargo.class).list();
			for(CuentaInteresRecargo cadaCuenta : locListaCuentasInteresRecargo) {
				mapaCuentaInteresRecargo.put(cadaCuenta.getConceptoPorMora().getIdConceptoPorMora(), cadaCuenta);
			}
		}
	}

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessCajaBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessCajaBean.NOMBRE);

		Recurso caja = new Recurso();
		caja.setIdRecurso(Caja.serialVersionUID);
		caja.setNombre("Caja");
		caja.setClase(Caja.class);
		grupoRecursos.getListaRecursos().add(caja);

		Recurso ticketCaja = new Recurso();
		ticketCaja.setIdRecurso(TicketCaja.serialVersionUID);
		ticketCaja.setNombre("Tickets de Caja");
		ticketCaja.setClase(TicketCaja.class);
		grupoRecursos.getListaRecursos().add(ticketCaja);

		Recurso planillaDiaria = new Recurso();
		planillaDiaria.setIdRecurso(PlanillaDiariaCaja.serialVersionUID);
		planillaDiaria.setNombre("Planilla Diaria");
		planillaDiaria.setClase(PlanillaDiariaCaja.class);
		grupoRecursos.getListaRecursos().add(planillaDiaria);

		Recurso moneda = new Recurso();
		moneda.setIdRecurso(Moneda.serialVersionUID);
		moneda.setNombre("Tipos de Monedas");
		moneda.setClase(Moneda.class);
		grupoRecursos.getListaRecursos().add(moneda);

		Recurso movimientoCajaIngreso = new Recurso();
		movimientoCajaIngreso.setIdRecurso(MovimientoCajaIngreso.serialVersionUID);
		movimientoCajaIngreso.setNombre("Movimientos de Caja de Ingreso");
		movimientoCajaIngreso.setClase(MovimientoCajaIngreso.class);
		grupoRecursos.getListaRecursos().add(movimientoCajaIngreso);

		Recurso movimientoCajaEgreso = new Recurso();
		movimientoCajaEgreso.setIdRecurso(MovimientoCajaEgreso.serialVersionUID);
		movimientoCajaEgreso.setNombre("Movimientos de Caja de Egreso");
		movimientoCajaEgreso.setClase(MovimientoCajaEgreso.class);
		grupoRecursos.getListaRecursos().add(movimientoCajaEgreso);

		Recurso registroDeuda = new Recurso();
		registroDeuda.setIdRecurso(RegistroDeuda.serialVersionUID);
		registroDeuda.setNombre("Registro de Cobro");
		registroDeuda.setClase(RegistroDeuda.class);
		grupoRecursos.getListaRecursos().add(registroDeuda);

		Recurso historicoTicketCaja = new Recurso();
		historicoTicketCaja.setIdRecurso(HistoricoReimpresionTicket.serialVersionUID);
		historicoTicketCaja.setNombre("Reimpresion de Ticket");
		historicoTicketCaja.setClase(HistoricoReimpresionTicket.class);
		grupoRecursos.getListaRecursos().add(historicoTicketCaja);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	/**
	 * 
	 */
	private static Object lockCaja = new Object();
	private static final long serialVersionUID = 3759392325916979490L;

	@EJB
	private BusinessLiquidacionTasaLocal businessLiquidacionTasa;

	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;

	@EJB
	private BusinessPlanDeCuentaLocal businessPlanDeCuenta;

	@EJB
	private BusinessReLiquidacionLocal businessReliquidacion;

	@PersistenceContext
	private EntityManager entity;

	// Ticket para los metodos de cancelar y devolver... es más facil así
	private TicketCancelado ticketCancelado;
	private static final String NOMBRE = "CAJ|Adm Caja";

	public BusinessCajaBean() {
		super();

	}

	private SessionContext ctx;

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbCreate() throws CreateException {
	}

	private void inicializarBusinessRegistroValuado() throws CreateException {
	}

	private void inicializarBusinessLiquidacionTasa() throws CreateException {
	}

	public com.trascender.contabilidad.recurso.persistent.Caja addCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja) throws java.lang.Exception {
		this.validarCaja(pCaja);
		if(Criterio.getInstance(entity, Caja.class).add(Restriccion.LIKE("ipAddress", pCaja.getIpAddress(), false)).uniqueResult() != null) {
			throw new TrascenderContabilidadException(46);
		}
		pCaja.setEstado(Caja.Estado.ACTIVO);
		entity.persist(pCaja);
		return pCaja;
	}

	/**
	 * Valida una caja
	 * 
	 * @param pCaja
	 * @throws TrascenderException
	 */
	private void validarCaja(Caja pCaja) throws TrascenderException {
		if(!Util.verificarCadenaLlena(pCaja.getNombre())) {
			throw new TrascenderContabilidadException(40);
		}

		if(!Util.verificarCadenaLlena(pCaja.getNumero())) {
			throw new TrascenderContabilidadException(40);
		}
		if(!Util.verificarCadenaLlena(pCaja.getIpAddress())) {
			throw new TrascenderContabilidadException(40);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.Caja updateCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja) throws java.lang.Exception {
		// PRE
		validarCaja(pCaja);
		entity.merge(pCaja);
		return pCaja;
	}

	public void deleteCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja) throws java.lang.Exception {
		entity.merge(pCaja);
		entity.remove(pCaja);
	}

	public java.util.List findListaCaja(java.lang.String pNombre, com.trascender.contabilidad.recurso.persistent.Caja.Estado pEstado) throws java.lang.Exception {
		// Busqueda por Estado
		if(pEstado == null) {
			pEstado = Caja.Estado.ACTIVO;
		}
		Criterio locCriterio = Criterio.getInstance(entity, Caja.class).add(Restriccion.ILIKE("nombre", pNombre)).add(Restriccion.IGUAL("estado", pEstado));
		return locCriterio.list();
	}

	public com.trascender.contabilidad.recurso.persistent.Caja findCajaByID(Long pIdCaja) throws java.lang.Exception {
		if(pIdCaja == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pIdCaja == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Caja locCaja = entity.find(Caja.class, pIdCaja);
		return locCaja;
	}

	/**
	 * Busca una caja por su IP Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pIPAddress
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja findCajaByIP(String pIPAddress) throws java.lang.Exception {
		return Criterio.getInstance(entity, Caja.class).add(Restriccion.IGUAL("ipAddress", pIPAddress)).add(Restriccion.DISTINTO("estado", Caja.Estado.INACTIVO)).uniqueResult();
	}

	public com.trascender.contabilidad.recurso.persistent.ArqueoCaja addArqueoCaja(com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja) throws java.lang.Exception {
		if(pArqueoCaja.getCantidad() < 0 || pArqueoCaja.getMoneda() == null || pArqueoCaja.getPlanillaDiariaCaja() == null) {
			throw new TrascenderContabilidadException(130);
		}
		entity.persist(pArqueoCaja);
		return pArqueoCaja;
	}

	public com.trascender.contabilidad.recurso.persistent.ArqueoCaja updateArqueoCaja(com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja) throws java.lang.Exception {
		if(pArqueoCaja.getCantidad() < 0 || pArqueoCaja.getMoneda() == null || pArqueoCaja.getPlanillaDiariaCaja() == null) {
			throw new TrascenderContabilidadException(131);
		}
		entity.merge(pArqueoCaja);
		return pArqueoCaja;
	}

	public void deleteArqueoCaja(com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja) throws java.lang.Exception {
		entity.merge(pArqueoCaja);
		entity.remove(pArqueoCaja);
	}

	public com.trascender.contabilidad.recurso.persistent.ArqueoCaja findArqueoCajaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		ArqueoCaja locArqueoCaja = entity.find(ArqueoCaja.class, pId);
		return locArqueoCaja;
	}

	public List findListaArqueoCaja(PlanillaDiariaCaja pPlanillaDiariaCaja, Moneda pMoneda) throws java.lang.Exception {
		List locListaArqueosCaja = null;
		Criterio locCriterio = Criterio.getInstance(entity, ArqueoCaja.class).add(Restriccion.IGUAL("planillaDiarioCaja", pPlanillaDiariaCaja))
				.add(Restriccion.IGUAL("moneda", pMoneda));
		locListaArqueosCaja = locCriterio.list();
		return locListaArqueosCaja;
	}

	private void validarMoneda(Moneda pMoneda) throws TrascenderContabilidadException {
		if((pMoneda.getNombre() == null) || (pMoneda.getNombre().trim().equals(""))) {
			throw new TrascenderContabilidadException(30);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.Moneda addMoneda(com.trascender.contabilidad.recurso.persistent.Moneda pMoneda) throws java.lang.Exception {
		validarMoneda(pMoneda);
		entity.persist(pMoneda);
		return pMoneda;
	}

	public com.trascender.contabilidad.recurso.persistent.Moneda updateMoneda(com.trascender.contabilidad.recurso.persistent.Moneda pMoneda) throws java.lang.Exception {
		validarMoneda(pMoneda);
		entity.merge(pMoneda);
		return pMoneda;
	}

	public com.trascender.contabilidad.recurso.persistent.Moneda findMonedaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Moneda locMoneda = entity.find(Moneda.class, pId);
		return locMoneda;
	}

	public java.util.List findListaMoneda(String pNombre) throws java.lang.Exception {
		return Criterio.getInstance(entity, Moneda.class).add(Restriccion.ILIKE("nombre", pNombre)).list();
	}

	public void deleteMoneda(com.trascender.contabilidad.recurso.persistent.Moneda pMoneda) throws java.lang.Exception {
		entity.merge(pMoneda);
		entity.remove(pMoneda);
	}

	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso findMovimientoCajaEgreso(long pId) throws java.lang.Exception {
		MovimientoCajaEgreso locMovimientoCajaEgreso = entity.find(MovimientoCajaEgreso.class, pId);
		return locMovimientoCajaEgreso;
	}

	@SuppressWarnings("unchecked")
	public List<MovimientoCajaEgreso> findListaMovimientoCajaEgreso(Date pFechaDesde, Date pFechaHasta, Double pImporteDesde, Double pImporteHasta) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, MovimientoCajaEgreso.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.MAYOR("importe", pImporteDesde)).add(Restriccion.MENOR("fecha", pFechaHasta));

		List<MovimientoCajaEgreso> listaMovimientoCajaEgreso = locCriterio.list();

		for(MovimientoCajaEgreso locMovimiento : listaMovimientoCajaEgreso) {
			locMovimiento.getDocumentoEgreso().toString();
			locMovimiento.getPlanilla();
			locMovimiento.getCuenta().toString();
		}
		return listaMovimientoCajaEgreso;
	}

	public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja addPlanillaDiariaCaja(com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja)
			throws java.lang.Exception {
		entity.persist(pPlanillaDiariaCaja);
		return pPlanillaDiariaCaja;
	}

	public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja updatePlanillaDiariaCaja(
			com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja) throws java.lang.Exception {
		pPlanillaDiariaCaja = entity.merge(pPlanillaDiariaCaja);
		for(TicketCaja cadaTicket : pPlanillaDiariaCaja.getTickets()) {
			cadaTicket.setPlanillaDiariaCaja(pPlanillaDiariaCaja);
		}
		return pPlanillaDiariaCaja;
	}

	public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja findPlanillaDiariaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		PlanillaDiariaCaja locPlanillaDiariaCaja = entity.find(PlanillaDiariaCaja.class, pId);
		return locPlanillaDiariaCaja;
	}

	public java.util.List findListaPlanillaDiariaCaja(java.util.Date pFechaDesde, java.util.Date pFechaHasta, com.trascender.framework.recurso.persistent.Usuario pUsuario)
			throws java.lang.Exception {
		return Criterio.getInstance(entity, PlanillaDiariaCaja.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.IGUAL("usuario", pUsuario)).list();
	}

	/**
	 * Realiza el cobro de un registro de deuda o un Ingreso Vario. Genera el movimiento correspondiente junto con el concepto. Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pTicketCaja
	 * @return
	 * @throws java.lang.Exception
	 */
	// TODO
	public com.trascender.contabilidad.recurso.persistent.TicketCaja addTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja) throws java.lang.Exception {
		double montoTotal = 0;
		if(pTicketCaja.getDetalles().size() < 0) {
			throw new TrascenderContabilidadException(44);
		}
		if(pTicketCaja.getCaja() == null) {
			throw new TrascenderContabilidadException(57);
		}

		this.entity.persist(pTicketCaja);

		// Seteo los movimientos, los conceptos, y todo lo que corresponda, en su lugar
		for(DetalleTicketCaja locDetalle : pTicketCaja.getDetalles()) {
			Pagable deuda = locDetalle.getDeuda();

			if(deuda instanceof RegistroDeuda) {
				// Saldo la deuda
				RegistroDeuda locDeuda = this.entity.find(RegistroDeuda.class, deuda.getId());
				/**
				 * Las siguiente validaciones se harán cuando se recupere la deuda, aqui es ineficiente. Fernando.
				 */

				if(locDeuda instanceof LiquidacionTasa || locDeuda.getTipoDeuda().equals(RegistroDeuda.TipoDeuda.RELIQUIDACION)) {
					LiquidacionTasa locLiquidacion = (LiquidacionTasa) locDeuda;
					this.actualizarPlanes(locLiquidacion, pTicketCaja.getUsuario());

					businessLiquidacionTasa.generarLogLiquidacion(locLiquidacion, pTicketCaja.getUsuario(), LogLiquidacion.Evento.PAGO_CAJA, null);
				}
				locDeuda.setRegistroCancelacion(locDetalle);
				locDetalle.setDeuda(locDeuda);
				locDeuda.setEstado(EstadoRegistroDeuda.PAGADA);
			} else if(locDetalle.getDeuda() instanceof IngresoVario) {
				// Saldo la deuda
				IngresoVario locDeuda = (IngresoVario) locDetalle.getDeuda();
				locDeuda.setRegistroCancelacion(locDetalle);
				locDeuda.setEstado(IngresoVario.Estado.PAGADO);
				locDetalle.setDeuda(locDeuda);
				this.entity.merge(locDeuda);
			}

			locDetalle.setTicketCaja(pTicketCaja);
			montoTotal += locDetalle.getImporte();
		}

		pTicketCaja.setImporteTotal(montoTotal);
		pTicketCaja.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
		pTicketCaja.setEstado(TicketCaja.Estado.ACTIVO);

		double totalPagable = 0;
		for(DetalleTicketCaja detalle : pTicketCaja.getDetalles()) {
			totalPagable += detalle.getDeuda().getMonto();
		}
		totalPagable = Util.redondear(totalPagable, 2);

		if(Util.redondear(pTicketCaja.getImporteTotal(), 2) != totalPagable) {
			throw new TrascenderContabilidadException(790);
		}

		// Codigo sincronizado para salvar los tickets y que no se genere dos veces el mismo numero.
		synchronized(lockCaja) {
			Integer locMaximoNumero = (Integer) entity.createQuery("SELECT MAX(tc.numero) FROM TicketCaja tc").getSingleResult();

			if(locMaximoNumero != null && !locMaximoNumero.equals(0)) {
				locMaximoNumero++;
			} else {
				locMaximoNumero = 1;
			}
			pTicketCaja.setNumero(locMaximoNumero);
		}

		// pTicketCaja = entity.merge(pTicketCaja);
		// for (DetalleTicketCaja locDetalleOtraVez : pTicketCaja.getDetalles()){
		// RegistroDeuda locDeudaOtraVez = (RegistroDeuda)locDetalleOtraVez.getDeuda();
		// locDeudaOtraVez.setRegistroCancelacion(locDetalleOtraVez);
		// this.entity.merge(locDeudaOtraVez);
		// }
		// this.entity.clear();
		// return pTicketCaja;
		this.entity.flush();
		// this.setearDeNuevo(pTicketCaja);
		this.entity.clear();
		return pTicketCaja;
	}

	/**
	 * Toma el resto de las Liquidaciones generadas bajo la misma Tasa y las marca como No Optadas.
	 * 
	 * @param pLiquidacion
	 */
	private void actualizarPlanes(LiquidacionTasa pLiquidacion, Usuario pUsuario) {
		Tasa locTasa = (Tasa) pLiquidacion.getDocGeneradorDeuda();
		locTasa.setPlanElegido(pLiquidacion.getTipoTasa().getPlan());
		List<LiquidacionTasa> liquidacionesNoOptadas = new ArrayList<LiquidacionTasa>();

		Criterio locCriterio = Criterio.getInstance(entity, LiquidacionTasa.class).add(Restriccion.IGUAL("docGeneradorDeuda", locTasa)).add(Restriccion.NULO("registroCancelacion"))
				.add(Restriccion.DISTINTO("tipoTasa.plan", locTasa.getPlanElegido()));
		List<LiquidacionTasa> locListaARevisar = locCriterio.list();
		for(LiquidacionTasa cadaLiquidacion : locListaARevisar) {
			if((cadaLiquidacion.getEstado() == EstadoRegistroDeuda.VIGENTE || cadaLiquidacion.getEstado() == EstadoRegistroDeuda.VENCIDA)) {
				liquidacionesNoOptadas.add(cadaLiquidacion);
			}
		}
		if(!liquidacionesNoOptadas.isEmpty()) {
			RegistroCancelacionManual locRegistro = new RegistroCancelacionManual();
			locRegistro.setComentario("Liquidacion No Optada, cancelada bajo el Plan: " + locTasa.getPlanElegido());
			locRegistro.setUsuario(pUsuario);
			locRegistro.setFechaCancelacion(new Date());
			locRegistro = this.entity.merge(locRegistro);
			for(LiquidacionTasa cadaLiquidacion : liquidacionesNoOptadas) {
				cadaLiquidacion.setEstado(EstadoRegistroDeuda.NO_OPTADA);
				cadaLiquidacion.setRegistroCancelacion(locRegistro);
			}
		}
	}

	/**
	 * Valido Todos los tickets que se van a cobrar Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pListaTicketCaja
	 * @throws Exception
	 */
	public void validarListaTicketCaja(List<TicketCaja> pListaTicketCaja) throws Exception {
		// this.abrirSession();
		// try{
		// for (TicketCaja locTicketCaja : pListaTicketCaja){
		// if (locTicketCaja.getDetalles().size() <0) {
		// throw new TrascenderContabilidadException (44);
		// }
		// if (locTicketCaja.getCaja() == null) {
		// throw new TrascenderContabilidadException (57);
		// }
		// for (DetalleTicketCaja locDetalle : locTicketCaja.getDetalles()){
		// Pagable deuda = locDetalle.getDeuda();
		// // if (deuda instanceof LiquidacionTasa){
		// // this.getCuenta((LiquidacionTasa)deuda);
		// // }
		// if (deuda instanceof IngresoVario){
		// this.getCuenta((IngresoVario)deuda);
		// }
		// }
		// }
		// }
		// catch (Exception e){
		// throw e;
		// }
		// finally{
		// this.cerrarSession();
		// }
	}

	public List<MovimientoCajaIngreso> getListaMovimientosCaja(List<Pagable> pListaDeudas, boolean conIntereses) throws Exception {
		List<MovimientoCajaIngreso> locListaTemporal = new ArrayList<MovimientoCajaIngreso>();
		for(Pagable cadaPagable : pListaDeudas) {
			if(cadaPagable instanceof LiquidacionTasa) {
				LiquidacionTasa locLiquidacion = (LiquidacionTasa) cadaPagable;
				if(conIntereses) {
					LiquidacionTasa liquidacionConInteres = businessReliquidacion.calcularIntereses(locLiquidacion, new Date(), true, false, false);
					locListaTemporal.addAll(this.getListaMovimientosCaja(liquidacionConInteres));
				} else {
					locListaTemporal.addAll(this.getListaMovimientosCaja(cadaPagable));
				}
			}
		}
		THashMap<Cuenta> locMapaImportesPorCuenta = new THashMap<Cuenta>();
		for(MovimientoCajaIngreso cadaMovimientoTemporal : locListaTemporal) {
			locMapaImportesPorCuenta.add(cadaMovimientoTemporal.getCuenta(), cadaMovimientoTemporal.getImporte());
		}
		List<MovimientoCajaIngreso> locListaRetorno = new ArrayList<MovimientoCajaIngreso>();
		for(Cuenta cadaCuenta : locMapaImportesPorCuenta.keySet()) {
			MovimientoCajaIngreso locNuevoMovimiento = new MovimientoCajaIngreso();
			locNuevoMovimiento.setCuenta(cadaCuenta);
			locNuevoMovimiento.setImporte(Util.redondear(locMapaImportesPorCuenta.get(cadaCuenta), 2));
			locListaRetorno.add(locNuevoMovimiento);
		}
		return locListaRetorno;
	}

	/**
	 * Busca un listado de MOvimientos de Caja ingreso por cada Detalle del pagable
	 * 
	 * @param locDeuda
	 * @return Un listado con los movimientos de un detalle
	 * @throws Exception
	 */
	private List<MovimientoCajaIngreso> getListaMovimientosCaja(Pagable locDeuda) throws TrascenderException {

		List<MovimientoCajaIngreso> locListaRetorno = new ArrayList<MovimientoCajaIngreso>();
		if(locDeuda instanceof LiquidacionTasa) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) locDeuda;

			// Comienza con el importe de la liquidacion, para luego sumarle los modificadores.
			Double importeTotal = locLiquidacionTasa.getMonto();

			// El movimiento para la propia liquidacion
			MovimientoCajaIngreso locMovimientoCajaIngreso = new MovimientoCajaIngreso();

			// //Obtengo la liquidacion original.
			// LiquidacionTasa locLiquidacionOriginal = this.getLiquidacionOriginal(locLiquidacionTasa);

			// Alto stored procedure que deberia agilizar mucho el recupero de la fecha original de la liquidacion,
			// sobre todo en el caso de reliquidaciones.
			Date fechaLiquidacionOriginal = (Date) this.entity.createNativeQuery("SELECT fecha FROM p_fecha_vencimiento_original(:id_deuda)")
					.setParameter("id_deuda", locLiquidacionTasa.getIdRegistroDeuda()).getSingleResult();

			// La cuenta asociada a la liquidacion
			locMovimientoCajaIngreso.setCuenta(this.getCuenta(locLiquidacionTasa, fechaLiquidacionOriginal));
			if(locMovimientoCajaIngreso.getCuenta() == null) {
				throw new TrascenderContabilidadException(56);
			}
			// La fecha actual.
			locMovimientoCajaIngreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());

			// Los movimientos correspondientes a los modificacores.
			for(ModificadorLiquidacion locModificadorLiquidacion : locLiquidacionTasa.getListaModificadoresLiquidacion()) {
				if(locModificadorLiquidacion instanceof ModificadorLiquidacionFormula) {
					ModificadorLiquidacionFormula locModificadorFormula = (ModificadorLiquidacionFormula) locModificadorLiquidacion;
					MovimientoCajaIngreso locCadaMovimientoCajaIngreso = new MovimientoCajaIngreso();
					locCadaMovimientoCajaIngreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
					locCadaMovimientoCajaIngreso.setImporte(locModificadorFormula.getValorModificador());
					Cuenta locCuenta = this.getCuenta(locLiquidacionTasa, locModificadorFormula, fechaLiquidacionOriginal);
					locCadaMovimientoCajaIngreso.setCuenta(locCuenta);
					locCadaMovimientoCajaIngreso.setImporte(locModificadorLiquidacion.getValorModificador());
					if(locCuenta != null) {
						locListaRetorno.add(locCadaMovimientoCajaIngreso);
						importeTotal -= locModificadorLiquidacion.getValorModificador();
					}
				}
			}
			// TODO Buscar la asociacion intereses y recargos.
			if(locLiquidacionTasa.getInteres() != null && locLiquidacionTasa.getInteres() > 0D) {
				MovimientoCajaIngreso locMovimientoIngresoInteres = new MovimientoCajaIngreso();
				locMovimientoIngresoInteres.setImporte(locLiquidacionTasa.getInteres());
				locMovimientoIngresoInteres.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
				Cuenta locCuenta = this.getCuenta(locLiquidacionTasa, locLiquidacionTasa.getTipoTasa().getInteres(), fechaLiquidacionOriginal);
				locMovimientoIngresoInteres.setCuenta(locCuenta);
				if(locCuenta != null) {
					locListaRetorno.add(locMovimientoIngresoInteres);
					importeTotal -= locMovimientoIngresoInteres.getImporte();
				}
			}

			if(locLiquidacionTasa.getRecargo() != null && locLiquidacionTasa.getRecargo() > 0D) {
				MovimientoCajaIngreso locMovimientoIngresoRecargo = new MovimientoCajaIngreso();
				locMovimientoIngresoRecargo.setImporte(locLiquidacionTasa.getRecargo());
				locMovimientoIngresoRecargo.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
				Cuenta locCuenta = this.getCuenta(locLiquidacionTasa, locLiquidacionTasa.getTipoTasa().getRecargo(), fechaLiquidacionOriginal);
				locMovimientoIngresoRecargo.setCuenta(locCuenta);
				if(locCuenta != null) {
					locListaRetorno.add(locMovimientoIngresoRecargo);
					importeTotal -= locMovimientoIngresoRecargo.getImporte();
				}
			}

			locMovimientoCajaIngreso.setImporte(importeTotal);
			locListaRetorno.add(locMovimientoCajaIngreso);
		} else if(locDeuda instanceof CuotaRefinanciacion) {
			AsociacionRefinanciacion locAsociacionRefinanciacion = this.getAsociacionRefinanciacion((DocumentoRefinanciacion) ((CuotaRefinanciacion) locDeuda).getDocGeneradorDeuda());// toma
																																														// pa
																																														// vo'
																																														// ahorramos
																																														// un
																																														// par
																																														// de
																																														// lineas
																																														// y
																																														// metimos
																																														// doble
																																														// casteo!!
																																														// futuro
																																														// pasante!!
																																														// esto
																																														// es
																																														// java
																																														// (Jose
																																														// -
																																														// Nacho)
			for(CuentaRefinanciacion cadaCuentaRefinanciacion : locAsociacionRefinanciacion.getListaCuentaRefinanciacion()) {
				MovimientoCajaIngreso locMovimientoCajaIngreso = new MovimientoCajaIngreso();
				locMovimientoCajaIngreso.setCuenta(cadaCuentaRefinanciacion.getCuenta());
				locMovimientoCajaIngreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
				locMovimientoCajaIngreso.setImporte(cadaCuentaRefinanciacion.getImporte());
				locListaRetorno.add(locMovimientoCajaIngreso);
			}
		} else if(locDeuda instanceof IngresoVario) {
			// En caso que no sea una liquidación no va a tener modificadores, así que no hay tanto drama
			IngresoVario locIngresoVario = (IngresoVario) locDeuda;
			// recorrer las imputaciones y generar movimientosCajaIngreso

			for(ImputacionIngresoVario cadaImputacion : locIngresoVario.getListaImputacionIngresos()) {
				MovimientoCajaIngreso locMovimientoCajaIngreso = new MovimientoCajaIngreso();
				locMovimientoCajaIngreso.setCuenta(cadaImputacion.getCuenta());
				locMovimientoCajaIngreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
				locMovimientoCajaIngreso.setImporte(cadaImputacion.getMonto());
				locListaRetorno.add(locMovimientoCajaIngreso);
			}

		} else {
			MovimientoCajaIngreso locMovimientoCajaIngreso = new MovimientoCajaIngreso();
			locMovimientoCajaIngreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
			locMovimientoCajaIngreso.setImporte(locDeuda.getMonto());
			locListaRetorno.add(locMovimientoCajaIngreso);
		}
		return locListaRetorno;
	}

	/**
	 * Obtiene, en caso de existir, la cuenta del modificador de la liquidacion
	 * 
	 * @param locDocumentoRefinanciacion
	 * @return
	 */
	private Cuenta getCuenta(DocumentoRefinanciacion pDocumentoRefinanciacion) throws TrascenderException {
		CuentaRefinanciacion locDocumento = Criterio.getInstance(entity, CuentaRefinanciacion.class).add(Restriccion.IGUAL("documentoRefinanciacion", pDocumentoRefinanciacion))
				.uniqueResult();
		if(locDocumento == null) {
			return null;
		}
		return locDocumento.getCuenta();
	}

	@SuppressWarnings("unchecked")
	private AsociacionRefinanciacion getAsociacionRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion) throws TrascenderException {
		List<CuentaRefinanciacion> locListaCuentaRefinanciacion = Criterio.getInstance(entity, CuentaRefinanciacion.class)
				.add(Restriccion.IGUAL("documentoRefinanciacion", pDocumentoRefinanciacion)).list();
		if(!locListaCuentaRefinanciacion.isEmpty()) {
			for(CuentaRefinanciacion cuentaRefinanciacion : locListaCuentaRefinanciacion) {
				cuentaRefinanciacion.toString();
				cuentaRefinanciacion.getDocumentoRefinanciacion().toString();
				cuentaRefinanciacion.getCuenta().toString();
				cuentaRefinanciacion.getParametroAsociacion().toString();
			}
			return locListaCuentaRefinanciacion.get(0).getAsociacionRefinanciacion();
		} else {

			int locAnio = Calendar.getInstance().get(Calendar.YEAR);

			AsociacionRefinanciacion locAsociacionRefinanciacion = Criterio.getInstance(entity, AsociacionRefinanciacion.class).uniqueResult();
			if(locAsociacionRefinanciacion == null) {
				throw new TrascenderContabilidadException(757);
			}
			for(ParametroAsociacion cadaParametroAsociacion : locAsociacionRefinanciacion.getListaParametrosAsociacion()) {
				CuentaRefinanciacion locCuentaRefinanciacion = new CuentaRefinanciacion();

				locCuentaRefinanciacion.setParametroAsociacion(cadaParametroAsociacion);
//				locCuentaRefinanciacion.setCuenta(cadaParametroAsociacion.getCuenta());
				locCuentaRefinanciacion.setDocumentoRefinanciacion(pDocumentoRefinanciacion);
				locCuentaRefinanciacion.setImporte(pDocumentoRefinanciacion.getTotalAPagar() * cadaParametroAsociacion.getPorcentaje() / 100);

				locCuentaRefinanciacion.setAsociacionRefinanciacion(locAsociacionRefinanciacion);

				locAsociacionRefinanciacion.getListaCuentaRefinanciacion().add(locCuentaRefinanciacion);
			}
			return this.businessPlanDeCuenta.updateAsociacionRefinanciacion(locAsociacionRefinanciacion);
		}
	}

	private Cuenta getCuenta(LiquidacionTasa pLiquidacion, ConceptoPorMora pConceptoPorMora, Date pFechaLiquidacionOriginal) throws TrascenderException {
		Boolean pagoAtrasado = getPagoAtrasadoBusquedaCuenta(pLiquidacion, pFechaLiquidacionOriginal);

		CuentaInteresRecargo locCuentaInteresRecargo = null;

		if(mapaCuentaInteresRecargo == null) {
			Criterio locCriterio = Criterio.getInstance(entity, CuentaInteresRecargo.class).add(Restriccion.IGUAL("conceptoPorMora", pConceptoPorMora));
			List<CuentaInteresRecargo> locListaResultado = locCriterio.list();

			if(locListaResultado.isEmpty()) {
				return null;
			}

			locCuentaInteresRecargo = locListaResultado.get(0);
		} else {
			locCuentaInteresRecargo = mapaCuentaInteresRecargo.get(pConceptoPorMora.getIdConceptoPorMora());

			if(locCuentaInteresRecargo == null) {
				return null;
			}
		}

		if(pagoAtrasado) {
			// No es de este año, trato de conseguir la cuenta para pagos atrasados.
			Cuenta locCuentaPagosAtrasados = locCuentaInteresRecargo.getCuentaPagosAtrasados();
			if(locCuentaPagosAtrasados == null) {
				throw new TrascenderContabilidadException(597, pConceptoPorMora, null);
			} else {
				return locCuentaPagosAtrasados;
			}
		} else {
			return locCuentaInteresRecargo.getCuenta();
		}

	}

	/**
	 * Obtiene, en caso de existir, la cuenta del modificador de la liquidacion
	 * 
	 * @param pModificadorLiquidacion
	 * @param LiquidacionTasa
	 *            La liquidacion a la que pertenece el modificador, debe ser la liquidacion original en el caso de una reliquidacion, la cual se obtiene a
	 *            travez de getLiquidacionOriginal(pLiquidacion)
	 * @return
	 */
	private Cuenta getCuenta(LiquidacionTasa pLiquidacion, ModificadorLiquidacionFormula pModificadorLiquidacion, Date pFechaLiquidacionOriginal) throws TrascenderException {
		Boolean pagoAtrasado = getPagoAtrasadoBusquedaCuenta(pLiquidacion, pFechaLiquidacionOriginal);

		CuentaModificador locCuentaModificador = null;

		if(mapaCuentaModificador == null) {
			Criterio locCriterio = Criterio.getInstance(entity, CuentaModificador.class).add(Restriccion.IGUAL("tipoModificador", pModificadorLiquidacion.getTipoModificador()));
			List locListaCuentaTipoTasa = locCriterio.list();

			if(locListaCuentaTipoTasa.isEmpty()) {
				return null;
			}

			locCuentaModificador = (CuentaModificador) locListaCuentaTipoTasa.get(0);
		} else {
			locCuentaModificador = mapaCuentaModificador.get(pModificadorLiquidacion.getTipoModificador().getIdTipoModificador());

			if(locCuentaModificador == null) {
				return null;
			}
		}

		if(pagoAtrasado) {
			// No es de este año, trato de conseguir la cuenta para pagos atrasados.
			Cuenta locCuentaPagosAtrasados = locCuentaModificador.getCuentaPagosAtrasados();
			if(locCuentaPagosAtrasados == null) {
				throw new TrascenderContabilidadException(595, pModificadorLiquidacion.getTipoModificador(), null);
			} else {
				return locCuentaPagosAtrasados;
			}
		} else {
			return locCuentaModificador.getCuenta();
		}
	}

	private Integer getAnioBusquedaCuenta(LiquidacionTasa pLiquidacionTasa) {
		Integer anioActual = Calendar.getInstance().get(Calendar.YEAR);
		Integer anioCalendario = pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio();
		if(anioCalendario == anioActual) {
			return anioActual;
		} else {
			return anioCalendario;
		}
	}

	private Boolean getPagoAtrasadoBusquedaCuenta(LiquidacionTasa pLiquidacionTasa, Date pFechaVencimientoOriginal) {
		Integer anioActual = Calendar.getInstance().get(Calendar.YEAR);
		Integer anioCalendario = pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio();
		// El calendario es de un año pasado y la deuda ya esta vencida.
		return anioCalendario < anioActual && Util.isFechaAfterNoTima(new Date(), pFechaVencimientoOriginal);
	}

	/**
	 * Obtiene una cuenta a partir de una liquidacion, debe existir una y solo una cuentaTipoTasa para el período del comienzo de la liquidación
	 * 
	 * @param locLiquidacionTasa
	 * @return
	 */
	private Cuenta getCuenta(LiquidacionTasa pLiquidacionTasa, Date pFechaVencimiento) throws TrascenderException {

		Cuenta locCuenta = null;
		Boolean pagoAtrasado = getPagoAtrasadoBusquedaCuenta(pLiquidacionTasa, pFechaVencimiento);

		// Busco la asociacion para el año obtenido.
		TipoTasa locTipoTasa = pLiquidacionTasa.getTipoTasa();
		CuentaTipoTasa locAsociacion = null;

		if(mapaCuentaTipoTasa == null) {
			List<CuentaTipoTasa> locListaCuentasTipoTasa = Criterio.getInstance(entity, CuentaTipoTasa.class).add(Restriccion.IGUAL("tipoTasa.idTipoTasa", locTipoTasa.getIdTipoTasa()))
					.setModoDebug(true).list();

			// se controla con el año de liquidacion xq aveces los periodos no coinciden
			if(locListaCuentasTipoTasa.isEmpty()) {
				throw new TrascenderContabilidadException(117, locTipoTasa, null);
			}
			// Selecciono la cuenta asociada solo si el año de la asociacion coincide con el año actual,
			// si no coincide, se imputa en la cuenta para pagos atrasados.
			locAsociacion = locListaCuentasTipoTasa.get(0);
		} else {
			locAsociacion = mapaCuentaTipoTasa.get(locTipoTasa.getIdTipoTasa());

			if(locAsociacion == null) {
				throw new TrascenderContabilidadException(117, locTipoTasa, null);
			}
		}

		if(pagoAtrasado) {
			if(locAsociacion.getCuentaPagosAtrasados() == null) {
				throw new TrascenderContabilidadException(108, locTipoTasa, null);
			} else {
				locCuenta = locAsociacion.getCuentaPagosAtrasados();
			}
		} else {
			locCuenta = locAsociacion.getCuenta();
		}
		return locCuenta;
	}

	/**
	 * Obtiene una cuenta a partir de una liquidacion, debe existir una y solo una cuentaTipoTasa para el período del comienzo de la liquidación
	 * 
	 * @param locLiquidacionTasa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Cuenta getCuenta(IngresoVario locIngresoVario) throws TrascenderException {
		// Calendar pFecha = SecurityMgr.getInstance().getFechaActual();
		// Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodoAnual(pFecha);
		// try{
		// Periodo locPeriodo = this.businessRegistroValuadoLocal.getPeriodo(locPeriodoTransient.getPeriodicidad(), locPeriodoTransient.getNumeroPeriodo(),
		// locPeriodoTransient.getFechaFin().get(Calendar.YEAR));
		// CuentaConceptoSellado locCuentaConceptoSellado = (CuentaConceptoSellado) this.session.createCriteria(CuentaConceptoSellado.class)
		// .add(Restrictions.eq("conceptoSelladoAdministrativo", locSelladoAdministrativo.getConceptoSelladoAdministrativo()))
		// .add(Restrictions.eq("periodo", locPeriodo))
		// .setFetchMode("cuenta", FetchMode.JOIN)
		// .uniqueResult();
		//
		// if (locCuentaConceptoSellado==null){
		// List<CuentaConceptoSellado> locListaConceptoSellado = this.session.createCriteria(CuentaConceptoSellado.class)
		// .add(Restrictions.eq("conceptoSelladoAdministrativo", locSelladoAdministrativo.getConceptoSelladoAdministrativo()))
		// .createAlias("periodo", "cadaPeriodo")
		// .addOrder(Order.desc("cadaPeriodo.fechaInicio"))
		// .setFetchMode("cuenta", FetchMode.JOIN)
		// .list();
		// if (!locListaConceptoSellado.isEmpty()){
		// locCuentaConceptoSellado = locListaConceptoSellado.get(0);
		// }
		// }

		// Calendar fechaActual = Calendar.getInstance();

		// Periodo locPeriodo = businessRegistroValuadoLocal.getPeriodo(Periodicidad.ANUAL, 1, fechaActual.get(Calendar.YEAR));

		CuentaConceptoIngresoVario locCuentaConceptoIngresoVario = (CuentaConceptoIngresoVario) Criterio.getInstance(entity, CuentaConceptoIngresoVario.class)
				.add(Restriccion.IGUAL("conceptoIngresoVario", locIngresoVario.getConceptoIngresoVario()))
				// .createAlias("periodo", "cadaPeriodo")
				// .add(Restrictions.ge("cadaPeriodo.fechaInicio", fechaActual))
				// .add(Restrictions.le("cadaPeriodo.fechaFin", fechaActual)))
				.uniqueResult();

		if(locCuentaConceptoIngresoVario == null) {
			throw new TrascenderContabilidadException(118, locIngresoVario, null);
		}
		return locCuentaConceptoIngresoVario.getCuenta();
	}

	/**
	 * Obtiene un ticket de caja Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pIdTicketCaja
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.TicketCaja findTicketCajaByID(Long pIdTicketCaja) throws java.lang.Exception {
		if(pIdTicketCaja == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pIdTicketCaja == -1) {
			throw new TrascenderContabilidadException(299);
		}
		TicketCaja locTicketCaja = entity.find(TicketCaja.class, pIdTicketCaja);
		locTicketCaja.getCaja();
		locTicketCaja.getCaja().toString();
		locTicketCaja.getUsuario();
		locTicketCaja.getUsuario().toString();
		locTicketCaja.toString();
		locTicketCaja.getListaPagosTicket().toString();

		for(DetalleTicketCaja locDetalleTicketCaja : locTicketCaja.getDetalles()) {
			locDetalleTicketCaja.toString();
			Pagable locDeuda = this.getDeudaPorParche(locDetalleTicketCaja);
			locDetalleTicketCaja.setDeuda(locDeuda);
			if(locDeuda != null) {
				locDeuda.toString();
				locDeuda.getNombre();
				locDeuda.getPersona().toString();
				if(locDeuda instanceof LiquidacionTasa) {
					LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) locDeuda;
					locLiquidacionTasa.getFechaVencimiento();
					if(locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela() != null) {
						locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela().toString();
					}
				}
			}
		}
		return locTicketCaja;
	}

	/**
	 * Un parche orrivle porque el diseño está hecho para el culo
	 * 
	 * @param pSession
	 * @param locDetalleTicketCaja
	 * @return
	 */
	private Pagable getDeudaPorParche(DetalleTicketCaja locDetalleTicketCaja) {
		Pagable locPagable = Criterio.getInstance(entity, LiquidacionTasa.class).add(Restriccion.IGUAL("registroCancelacion", locDetalleTicketCaja)).uniqueResult();
		if(locPagable == null) {
			locPagable = Criterio.getInstance(entity, IngresoVario.class).add(Restriccion.IGUAL("registroCancelacion", locDetalleTicketCaja)).uniqueResult();
		}
		if(locPagable == null) {
			locPagable = Criterio.getInstance(entity, CuotaRefinanciacion.class).add(Restriccion.IGUAL("registroCancelacion", locDetalleTicketCaja)).uniqueResult();
		}
		return locPagable;
	}
	
	/**
	 * Para minimizar la cantidad de consultas a la base.
	 * @param listaTickets
	 */
	private void setDeudasPorParche(List<TicketCaja> listaTickets) {
		Set<Long> listaIdsRegCancelacion = new HashSet<Long>();
		for (TicketCaja cadaTicketCaja : listaTickets) {
			for (DetalleTicketCaja cadaDetalle : cadaTicketCaja.getDetalles()) {
				listaIdsRegCancelacion.add(cadaDetalle.getIdRegistroCancelacion());
			}
		}
		
		Criterio locCriterio = Criterio.getInstance(entity, LiquidacionTasa.class)
				.add(Restriccion.EN("registroCancelacion.idRegistroCancelacion", listaIdsRegCancelacion))
				.crearFetchAlias("docGeneradorDeuda.obligacion", "cadaObligacion")
				.setProyeccion(Proyeccion.MAP("registroCancelacion.idRegistroCancelacion", "e"));

		Map<Long, Pagable> locMapa = locCriterio.mapList();
		
		for (TicketCaja cadaTicketCaja : listaTickets) {
			for (DetalleTicketCaja cadaDetalle : cadaTicketCaja.getDetalles()) {
				cadaDetalle.setDeuda(locMapa.get(cadaDetalle.getIdRegistroCancelacion()));
			}
		} 
		
	}

	/**
	 * Obtiene un ticket de caja por número Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pNumeroTicket
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.TicketCaja getTicketCajaPorNumero(Integer pNumeroTicket) throws java.lang.Exception {
		if(pNumeroTicket == null) {
			throw new TrascenderContabilidadException(59);
		}
		TicketCaja locTicketCaja = Criterio.getInstance(entity, TicketCaja.class).add(Restriccion.IGUAL("numero", pNumeroTicket)).uniqueResult();

		for(DetalleTicketCaja locDetalleTicketCaja : locTicketCaja.getDetalles()) {
			locDetalleTicketCaja.toString();
			locDetalleTicketCaja.setDeuda(this.getDeudaPorParche(locDetalleTicketCaja));
			if(locDetalleTicketCaja.getDeuda() != null) {
				locDetalleTicketCaja.getDeuda().toString();
				locDetalleTicketCaja.getDeuda().getNombre();
			}
		}
		locTicketCaja.toString();
		locTicketCaja.getUsuario().toString();
		locTicketCaja.getUsuario();
		locTicketCaja.getCaja().toString();
		locTicketCaja.getCaja();
		return locTicketCaja;
	}

	public java.util.List findListaTicketCaja(java.util.Date pFechaDesde, java.util.Date pFechaHasta, Integer pNumero, Usuario pUsuario, Caja pCaja) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, TicketCaja.class)
				.add(Restriccion.IGUAL("numero", pNumero))
				.add(Restriccion.MAYOR("fecha", pFechaDesde))
				.add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.IGUAL("usuario", pUsuario))
				.add(Restriccion.IGUAL("caja", pCaja))
				.setModoDebug(true);
		List<TicketCaja> listaTickets = locCriterio.list();
		
		this.setDeudasPorParche(listaTickets);
		
		for(Object obj : listaTickets) {
			TicketCaja locTicketCaja = (TicketCaja) obj;
			locTicketCaja.toString();
			locTicketCaja.getCaja().toString();
			locTicketCaja.getUsuario().toString();
			for(DetalleTicketCaja cadaDetalle : locTicketCaja.getDetalles()) {
				if(locTicketCaja.getEstado().equals(TicketCaja.Estado.ACTIVO)) {
					cadaDetalle.getTicketCaja().getStringPersona();
					cadaDetalle.getTicketCaja().getNroParcela();
				}
			}
		}
		return listaTickets;
	}

	public java.util.List findResumenCajaActual(com.trascender.contabilidad.recurso.persistent.Caja pCaja, Usuario pUsuario, TicketCaja.Estado pEstado, Date pFechaDesde,
			Date pFechaHasta) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, TicketCaja.class).setDistinct(true).add(Restriccion.IGUAL("caja", pCaja)).add(Restriccion.IGUAL("usuario", pUsuario))
				.add(Restriccion.MAYOR("detalles.fechaCancelacion", pFechaDesde)).add(Restriccion.MENOR("detalles.fechaCancelacion", pFechaHasta))
				.add(Restriccion.IGUAL("estado", pEstado)).setModoDebug(true);

		List listaTickets = locCriterio.list();

		for(Object obj : listaTickets) {
			TicketCaja ticket = (TicketCaja) obj;
			ticket.getImporteTotal();
		}
		return listaTickets;
	}

	public void cancelarTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja,
			com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja) throws java.lang.Exception {
		pTicketCaja = this.entity.merge(pTicketCaja);

		this.ticketCancelado = new TicketCancelado();

		this.ticketCancelado.setTicketCaja(pTicketCaja);

		if(pTicketCaja.getEstado() != TicketCaja.Estado.ACTIVO) {
			throw new TrascenderContabilidadException(55);
		}

		pTicketCaja.setEstado(TicketCaja.Estado.CANCELADO);

		for(DetalleTicketCaja locDetalleTicketCaja : pTicketCaja.getDetalles()) {
			this.entity.merge(locDetalleTicketCaja);

			Pagable locPagable = getDeudaPorParche(locDetalleTicketCaja);
			if(locPagable instanceof LiquidacionTasa) {
				LiquidacionTasa locLiqTasa = (LiquidacionTasa) locPagable;
				Usuario locUsuario = SecurityMgr.getInstance().getUsuario(this.llave);
				businessLiquidacionTasa.generarLogLiquidacion(locLiqTasa, locUsuario, LogLiquidacion.Evento.ANULO_PAGO_CAJA, null);
			}

			this.volverAtrasDeuda(locDetalleTicketCaja);
			locDetalleTicketCaja.getMovimientosCajaIngreso().clear();
		}
		this.entity.persist(this.ticketCancelado);
	}

	/**
	 * Devuelve un Registro Deuda a el estado anterior de ser cobrado.
	 * 
	 * @param locDetalleTicketCaja
	 */
	@SuppressWarnings("unchecked")
	private void volverAtrasDeuda(DetalleTicketCaja locDetalleTicketCaja) {

		Pagable locPagable = getDeudaPorParche(locDetalleTicketCaja);
		RegistroDeuda locRegistroDeuda = this.businessLiquidacionTasa.volverAtrasDeuda(locDetalleTicketCaja);

		if(locRegistroDeuda != null) {
			this.ticketCancelado.setIdDeuda(this.completarCodigoBarra(locPagable.getId(), 1));
		} else {
			// this.ticketCancelado.setIdDeuda(this.completarCodigoBarra(getDeudaPorParche(locDetalleTicketCaja).getId(), 2));
			// El caso del ingreso vario
			IngresoVario locIngresoVario = Criterio.getInstance(entity, IngresoVario.class).add(Restriccion.IGUAL("registroCancelacion", locDetalleTicketCaja)).uniqueResult();
			if(locIngresoVario != null) {
				locIngresoVario.setRegistroCancelacion(null);
				locIngresoVario.setEstado(IngresoVario.Estado.CREADO);
				this.entity.merge(locIngresoVario);
			}
		}
	}

	/**
	 * Devuelve un cobro y vuelve atrás las liquidaciones Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pTicketCaja
	 * @throws Exception
	 */
	public TicketCaja devolverTicketCaja(TicketCaja pTicketCaja) throws Exception {
		this.entity.merge(pTicketCaja);
		this.entity.refresh(pTicketCaja);

		this.ticketCancelado = new TicketCancelado();

		this.ticketCancelado.setTicketCaja(pTicketCaja);

		if(pTicketCaja.getEstado() != TicketCaja.Estado.ACTIVO) {
			throw new TrascenderContabilidadException(55);
		}

		pTicketCaja.setEstado(TicketCaja.Estado.DEVUELTO);

		for(DetalleTicketCaja locDetalleTicketCaja : pTicketCaja.getDetalles()) {
			this.entity.merge(locDetalleTicketCaja);
			this.volverAtrasDeuda(locDetalleTicketCaja);
		}
		this.entity.persist(this.ticketCancelado);
		return pTicketCaja;
	}

	/**
	 * A partir del id de la deuda armo el codigo de barra completo
	 * 
	 * @param pCodigo
	 * @param pTipoDeuda
	 * @return
	 */
	public Long completarCodigoBarra(Long pCodigo, int pTipoDeuda) {
		String locCodigo = pCodigo.toString();
		String locCodigoNuevo = new String();
		int numeroCeros = 18 - locCodigo.length();
		if(pTipoDeuda == 1) {
			locCodigoNuevo += "1";
			for(int i = 0; i < numeroCeros; i++) {
				locCodigoNuevo += "0";
			}
		} else {
			locCodigoNuevo += "2";
			for(int i = 0; i < numeroCeros; i++) {
				locCodigoNuevo += "0";
			}
		}

		locCodigoNuevo += locCodigo;
		return Long.valueOf(locCodigoNuevo);
	}

	/**
	 * Obtiene un ticket cancelado a traves de un ticket de caja
	 * 
	 * @param pTicketCaja
	 * @return
	 * @throws Exception
	 */
	public TicketCancelado getTicketCanceladoPorTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja) throws Exception {
		TicketCancelado locTicketCancelado = Criterio.getInstance(entity, TicketCancelado.class).add(Restriccion.IGUAL("ticketCaja", pTicketCaja)).uniqueResult();
		return locTicketCancelado;

	}

	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso findMovimientoCajaIngresoByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		MovimientoCajaIngreso movimiento = entity.find(MovimientoCajaIngreso.class, pId);
		return movimiento;
	}

	public java.util.List<MovimientoCajaIngreso> findListaMovimientoCajaIngreso(java.util.Date pFechaDesde, java.util.Date pFechaHasta, Double pImporteDesde, Double pImporteHasta,
			TicketCaja.Estado pEstado) {

		Criterio locCriterio = Criterio.getInstance(entity, MovimientoCajaIngreso.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.MAYOR("importe", pImporteDesde)).add(Restriccion.MENOR("importe", pImporteHasta)).add(Restriccion.IGUAL("detalleTicket.ticketCaja.estado", pEstado));
		List<MovimientoCajaIngreso> listaMovimientosIngresos = locCriterio.list();

		for(MovimientoCajaIngreso locMovimiento : listaMovimientosIngresos) {
			if(locMovimiento.getCuenta() != null) {
				locMovimiento.getCuenta().toString();
			}
			if(locMovimiento.getDetalleTicket() != null) {
				locMovimiento.getDetalleTicket().toString();
			}
			locMovimiento.getLineaPresupuestoRecursos();
		}
		for(MovimientoCajaIngreso ingreso : listaMovimientosIngresos) {
			System.out.println(ingreso.getDetalleTicket());
		}
		return listaMovimientosIngresos;
	}

	public com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja findDetalleTicketCajaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		DetalleTicketCaja locDetalleTicketCaja = entity.find(DetalleTicketCaja.class, pId);
		return locDetalleTicketCaja;
	}

	public java.util.List findListaDetalleTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja, Double pImporte, Long pNumeroLinea)
			throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, DetalleTicketCaja.class).add(Restriccion.IGUAL("ticketCaja", pTicketCaja)).add(Restriccion.IGUAL("importe", pImporte))
				.add(Restriccion.IGUAL("numeroLinea", pNumeroLinea));
		List<DetalleTicketCaja> listaDetalleTicketCaja = locCriterio.list();
		return listaDetalleTicketCaja;
	}

	/**
	 * Restaura una caja al estado Activo Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pCaja
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja restaurarCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja) throws java.lang.Exception {
		if(pCaja.getEstado() == Caja.Estado.INACTIVO) {
			pCaja.setEstado(Caja.Estado.ACTIVO);
			entity.merge(pCaja);
			return pCaja;
		} else {
			throw new TrascenderContabilidadException(42);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja getPlanillaDiariaDelDia(Usuario pUsuario) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, PlanillaDiariaCaja.class).add(Restriccion.IGUAL("fecha", SecurityMgr.getInstance().getFechaActual().getTime()))
				.add(Restriccion.IGUAL("usuario", pUsuario));
		PlanillaDiariaCaja locPlanillaDiaria = locCriterio.uniqueResult();
		return locPlanillaDiaria;
	}

	// TODO
	// private void cobrarTGI(RegistroDeuda locDeuda, Tasa locTasa, DetalleTicketCaja locDetalle) throws java.lang.Exception{
	// List listaRegistrosDeuda = businessLiquidacionTasa.getListaLiquidacionesTGI(locDeuda);
	// TasaTGI locTasaTGI = (TasaTGI) locTasa;
	// this.entity.refresh(locTasaTGI);
	// TipoTasa tipoTasa = locTasaTGI.getTipoTasa();
	// RegistroDeuda locRegistroDeuda = null;
	//
	// //AL MOMENTO DE PAGAR LA SEGUNDA CUOTA , SI ES QUE TIENE, LAS DEMAS VAN A ESTAR CANCELADAS POR LO QUE NO VA A PASAR POR ESTE CODIGO.
	// //Pregunto si la cantidad de cuotas son distintos para no cancelar las demás cuotas de la liquidación
	// // this.session.evict(listaRegistrosDeuda);
	//
	// entity.clear();
	//
	// for (Object obj : listaRegistrosDeuda){
	// locRegistroDeuda = (RegistroDeuda) obj;
	// // this.session.evict(obj);
	// // DocGeneradorDeuda locDocGeneradorDeuda = locRegistroDeuda.getDocGeneradorDeuda();
	// // this.session.evict(locDocGeneradorDeuda);
	// // locDocGeneradorDeuda = (TasaTGI) this.session.load(TasaTGI.class, locDocGeneradorDeuda.getIdDocGeneradorDeuda());
	// // TasaTGI tasaTGI= (TasaTGI) locDocGeneradorDeuda;
	//
	// //El documento generador de la deuda es si o si una tasa tgi, de no ser asi no habria llegado a este metodo.
	// TasaTGI tasaTGI = (TasaTGI) locRegistroDeuda.getDocGeneradorDeuda();
	// TipoTasa locTipoTasa = tasaTGI.getTipoTasa();
	//
	// if (locTasaTGI.getPeriodicidad() == Periodicidad.ANUAL && locTasaTGI.getTipoTasa().getCantidadCuotas() == 1 ){
	// locRegistroDeuda.setEstado(EstadoRegistroDeuda.PAGADA_ANUAL);
	// }
	// else if ((locTasaTGI.getPeriodicidad() == Periodicidad.ANUAL && locTasaTGI.getTipoTasa().getCantidadCuotas() == 3)
	// && !(tasaTGI.getPeriodicidad() == Periodicidad.ANUAL && tasaTGI.getTipoTasa().getCantidadCuotas() == 3) ){
	// locRegistroDeuda.setEstado(EstadoRegistroDeuda.PAGADA_EN_TERCIOS);
	// }
	// if (locTasaTGI.getPeriodicidad() == Periodicidad.BIMESTRAL && (tasaTGI.getPeriodicidad() != Periodicidad.BIMESTRAL)){
	// locRegistroDeuda.setEstado(EstadoRegistroDeuda.PAGADA_BIMESTRAL);
	// }
	//
	// if (locTipoTasa.getCantidadCuotas() == 1){
	// //La desactivo solo si pertenece al mismo período, sino puede q yo cancela la primera bimestral y la segunda todavía no
	// //y si no controlo me la desactivaría antes de tiempo
	// if((!locTipoTasa.getCantidadCuotas().equals(tipoTasa.getCantidadCuotas())) || (locTipoTasa.getPeriodicidad() != tipoTasa.getPeriodicidad()) ){
	// tasaTGI.setEstado(TasaTGI.Estado.INACTIVO);
	// }
	// locRegistroDeuda.setDocGeneradorDeuda(tasaTGI);
	//
	// }
	// else {
	// if((!locTipoTasa.getCantidadCuotas().equals(tipoTasa.getCantidadCuotas())) || (locTipoTasa.getPeriodicidad() != tipoTasa.getPeriodicidad()) ){
	// tasaTGI.setEstado(TasaTGI.Estado.INACTIVO);
	// // locRegistroDeuda.setDocGeneradorDeuda(tasaTGI);
	// }
	// }
	// if(locRegistroDeuda.getIdRegistroDeuda() != locDeuda.getIdRegistroDeuda()){
	// if (entity.contains(locRegistroDeuda.getDocGeneradorDeuda())){
	// entity.detach(locRegistroDeuda.getDocGeneradorDeuda());
	// }
	// this.entity.merge(locRegistroDeuda);
	// }
	// this.entity.flush();
	// this.entity.clear();
	// // this.session.update(tasaTGI);
	// }
	// locTasaTGI.setEstado(TasaTGI.Estado.ACTIVO);
	// this.entity.merge(locTasaTGI);
	// this.entity.merge(locDeuda);
	// }

	private void setearDeNuevo(TicketCaja pTicketCaja) throws java.lang.Exception {
		// ACA SE REPITE EL CODIGO DE ARRIBA, PERO ESTA VEZ SE SETEA EL REGISTRO DE CANCELACION AL REGISTRO DE DEUDA.
		// NO SE PUEDE REALIZAR ANTES PORQUE EL REGISTRO DE CANCELACION NO ESTA EN LA BASE DE DATOS.
		for(DetalleTicketCaja locDetalleOtraVez : pTicketCaja.getDetalles()) {
			if(locDetalleOtraVez.getDeuda() != null && locDetalleOtraVez.getDeuda() instanceof RegistroDeuda) {
				RegistroDeuda locDeudaOtraVez = (RegistroDeuda) locDetalleOtraVez.getDeuda();
				locDeudaOtraVez.setRegistroCancelacion(locDetalleOtraVez);
				locDeudaOtraVez.getFechaEmision();
				locDeudaOtraVez.getMonto();
				locDeudaOtraVez.getRegistroCancelacion();
				locDeudaOtraVez.toString();
				this.entity.merge(locDeudaOtraVez);
				// this.session.update(locDeudaOtraVez);
				this.entity.flush();
				this.entity.detach(locDeudaOtraVez);

			} else if(locDetalleOtraVez.getDeuda() != null && locDetalleOtraVez.getDeuda() instanceof IngresoVario) {
				IngresoVario locDeudaOtraVez = (IngresoVario) locDetalleOtraVez.getDeuda();
				locDeudaOtraVez.setRegistroCancelacion(locDetalleOtraVez);
				locDeudaOtraVez.getFechaEmision();
				locDeudaOtraVez.getValor();
				locDeudaOtraVez.getRegistroCancelacion();
				this.entity.merge(locDeudaOtraVez);
				this.entity.flush();
				this.entity.detach(locDeudaOtraVez);
			}
		}
	}

	/**
	 * Busca una deuda en el sistema. Si empieza con un 1 se busca un Registro de Deuda, si empieza con un 2 se busca un IngresoVario Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Pagable getDeudaByID(Long pId) throws Exception {
		try {
			String locNuevoID = pId.toString().substring(1);
			Long nuevoId = Long.valueOf(locNuevoID);
			if(pId.toString().startsWith("1")) {
				return this.getRegistroDeuda(nuevoId);
			} else {
				if(pId.toString().startsWith("2")) {
					return this.getIngresoVario(nuevoId);
				} else {
					throw new TrascenderContabilidadException(58);
				}
			}
		} catch(Exception e) {
			throw e;
		}
	}

	/**
	 * Devuelve el detalle del ticket, con los movimientos de caja ingresos ya armados, de esta forma se validaran al momento de cargar el codigo.
	 */
	// TODO
	public List<DetalleTicketCaja> getListaDetalleByIdLiquidacion(String codigo) throws TrascenderException {
		List<DetalleTicketCaja> locListaDetalles = new ArrayList<DetalleTicketCaja>();
		List<Pagable> listaPagables = new ArrayList<Pagable>();
		if(codigo.startsWith("1")) {
			listaPagables.add(this.getRegistroDeuda(Long.parseLong(codigo.substring(1))));
		} else if(codigo.startsWith("2")) {
			listaPagables.add(this.getIngresoVario(Long.valueOf(codigo.substring(1))));
		} else if(codigo.toString().startsWith("3")) {
			listaPagables.addAll(this.getListaPagablesTasaAgrupada(codigo));
		}
		for(Pagable cadaPagable : listaPagables) {
			List<MovimientoCajaIngreso> listaMovimientos = this.getListaMovimientosCaja(cadaPagable);
			if(listaMovimientos.isEmpty()) {
				throw new TrascenderContabilidadException(56);
			}
			DetalleTicketCaja locDetalle = new DetalleTicketCaja();
			locDetalle.addAll(listaMovimientos);
			locDetalle.setFechaCancelacion(SecurityMgr.getInstance().getFechaActual().getTime());
			locDetalle.setDeuda(cadaPagable);
			locDetalle.setIdRegistroDeuda(cadaPagable.getId());
			locDetalle.setImporte(cadaPagable.getMonto());
			locDetalle.getDeuda().getNombre();
			locListaDetalles.add(locDetalle);
		}
		return locListaDetalles;
	}

	private Pagable getRegistroDeuda(Long pId) throws TrascenderException {
		RegistroDeuda locRegistroDeuda = entity.find(RegistroDeuda.class, pId);

		if(locRegistroDeuda.getEstado() == RegistroDeuda.EstadoRegistroDeuda.RELIQUIDADA) {
			throw new TrascenderContabilidadException(105);
		}

		if(locRegistroDeuda.getEstado() == RegistroDeuda.EstadoRegistroDeuda.ANULADA) {
			throw new TrascenderContabilidadException(103);
		}
		
		if(locRegistroDeuda.getEstado() == RegistroDeuda.EstadoRegistroDeuda.VENCIDA) {
			throw new TrascenderContabilidadException(102);
		}
		
		if(locRegistroDeuda.getEstado() == RegistroDeuda.EstadoRegistroDeuda.PAGADA) {
			throw new TrascenderContabilidadException(106, locRegistroDeuda.getEstado(), null);
		}

		if(locRegistroDeuda.getEstado() == RegistroDeuda.EstadoRegistroDeuda.REFINANCIADA) {
			throw new TrascenderContabilidadException(104);
		}

		if(locRegistroDeuda.getRegistroCancelacion() != null) {
			throw new TrascenderContabilidadException(413);
		}

		if(locRegistroDeuda instanceof LiquidacionTasa) {
			LiquidacionTasa locLiquidacion = (LiquidacionTasa) locRegistroDeuda;
			for(ModificadorLiquidacion locModificadores : locLiquidacion.getListaModificadoresLiquidacion()) {
				locModificadores.toString();
			}
			locLiquidacion.getPersona().toString();
			locLiquidacion.getListaVencimientos();
			locLiquidacion.getValorTotal();
			locLiquidacion.getMonto();
			locLiquidacion.toString();
			// locLiquidacion.getPeriodo().getNombrePeriodo();
			locLiquidacion.getDocGeneradorDeuda().toString();
			if(locLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela() != null) {
				locLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela().toString();
			}
			
			if(locLiquidacion.getEstado() == RegistroDeuda.EstadoRegistroDeuda.VENCIDA) {
				try {
					businessReliquidacion.setLlave(llave);
					locLiquidacion = businessReliquidacion.calcularIntereses(locLiquidacion, new Date(), true, true, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return locLiquidacion;
		} else {
			return locRegistroDeuda;
		}
	}

	private Pagable getIngresoVario(Long pId) throws TrascenderException {
		IngresoVario locIngresoVario = Criterio.getInstance(entity, IngresoVario.class).add(Restriccion.IGUAL("idIngresoVario", pId)).uniqueResult();
		locIngresoVario.getValor();
		locIngresoVario.toString();
		if(locIngresoVario.getRegistroCancelacion() != null) {
			throw new TrascenderContabilidadException(413); // TODO:VER SOLUCION EN ESTO... ME DA PROBLEMA CUANDO QUIERA OBTENER UN INGRESO VARIO PAGADO Y
															// CANCELADO PARA REIMPRIMIR
		}
		if(locIngresoVario.getEstado().equals(IngresoVario.Estado.ANULADO)) {
			throw new TrascenderContabilidadException(47);
		}
		// //Validacion de la asociacion de cuentas.
		// this.getCuenta(locIngresoVario);
		return locIngresoVario;
	}

	private List<Pagable> getListaPagablesTasaAgrupada(String codigo) throws TrascenderException {
		// Tengo id's! los busco de la base.
		List<Pagable> listaResultado = new ArrayList<Pagable>();
		for(Long cadaId : decodificarCodigos(codigo)) {
			listaResultado.add(getRegistroDeuda(cadaId));
		}
		return listaResultado;
	}

	private List<Long> decodificarCodigos(String codigo) {
		List<Long> locListaIds = new ArrayList<Long>();
		if(codigo.startsWith("3")) {
			codigo = codigo.substring(1); // Quito el 3 inicial, si lo tiene
		}
		for(int i = 0; i < 3; i++) {
			// Armo subcodigos de a 8 digitos.
			String subcodigo = codigo.substring(i * 8, (i + 1) * 8);
			if(!subcodigo.equals("00000000")) {
				Long idOriginal = Long.parseLong(subcodigo.toLowerCase(), 36);
				locListaIds.add(idOriginal);
			}
		}
		return locListaIds;
	}

	/**
	 * Obtiene la persona que esta relacionada con un registro de deuda
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pID
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.framework.recurso.persistent.Persona getPersonaPorDeuda(Long pId) throws java.lang.Exception {
		Persona locPersona = null;
		String locNuevoID = pId.toString().substring(1);
		Long nuevoId = Long.valueOf(locNuevoID);
		try {
			if(pId.toString().startsWith("1")) {
				locPersona = this.getPersonaDeRegistroDeuda(nuevoId);
			} else {
				locPersona = this.getPersonaDeIngresoVario(nuevoId);
			}
			locPersona.toString();
			return locPersona;
		} catch(Exception e) {
			throw e;
		}
	}

	private com.trascender.framework.recurso.persistent.Persona getPersonaDeRegistroDeuda(Long pId) throws java.lang.Exception {
		RegistroDeuda locRegistroDeuda = Criterio.getInstance(entity, RegistroDeuda.class).add(Restriccion.IGUAL("idRegistroDeuda", pId)).uniqueResult();
		Persona locPersona = locRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona();
		locPersona.toString();
		return locPersona;
	}

	private com.trascender.framework.recurso.persistent.Persona getPersonaDeIngresoVario(Long pId) throws java.lang.Exception {
		Persona locPersona = Criterio.getInstance(entity, IngresoVario.class).add(Restriccion.IGUAL("idIngresoVario", pId)).setProyeccion(Proyeccion.PROP("persona")).uniqueResult();
		locPersona.toString();
		return locPersona;
	}

	public HistoricoReimpresionTicket addHistoricoReimpresionTicket(HistoricoReimpresionTicket pHistoricoReimpresionTicket) throws Exception {
		pHistoricoReimpresionTicket.setFecha(SecurityMgr.getInstance().getFechaActual());
		entity.persist(pHistoricoReimpresionTicket);
		return pHistoricoReimpresionTicket;
	}
	
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) {
		// this.abrirSession();
		ResumenActualCajaDataSource dataSource = null;
		Criterio locCriterio = Criterio.getInstance(entity, TicketCaja.class)
				.setDistinct(true)
				.setModoDebug(true)
				.add(Restriccion.IGUAL("usuario.idUsuario", pIdUsuario))
				.add(Restriccion.IGUAL("caja.idCaja", pIdCaja))
				.add(Restriccion.MAYOR("detalles.fechaCancelacion", pFechaDesde))
				.add(Restriccion.MENOR("detalles.fechaCancelacion", pFechaHasta));
		// .add(Restriccion.OR(
		// Restriccion.LIKE("codigoBarras", "1", false, Posicion.AL_PRINCIPIO),
		// Restriccion.LIKE("codigoBarras", "3", false, Posicion.AL_PRINCIPIO)));

		List<TicketCaja> listaTickets = locCriterio.list();
		Iterator<TicketCaja> itTicketCaja = listaTickets.iterator();
		while(itTicketCaja.hasNext()) {
			TicketCaja locTicket = itTicketCaja.next();
			for(DetalleTicketCaja locDetalle : locTicket.getDetalles()) {
				this.setLiquidacionAlTicket(locDetalle);
			}
		}
		Collections.sort(listaTickets, new Comparator<TicketCaja>() {
			@Override
			public int compare(TicketCaja o1, TicketCaja o2) {
				return o1.getNumero().compareTo(o2.getNumero());
			}
		});

		this.cargarMapasAsociacionesCuentas();

		dataSource = new ResumenActualCajaDataSource(listaTickets, this);
		return dataSource;
	}

	public JasperPrint generarReporteCajaPorIngresoVario(Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) 
		throws Exception{
		ResumenActualCajaIngresoVarioDS dataSource = null;
		Criterio locCriterio = Criterio.getInstance(entity, TicketCaja.class)
				.setDistinct(true)
				.setModoDebug(true)
				.add(Restriccion.IGUAL("usuario.idUsuario", pIdUsuario))
				.add(Restriccion.IGUAL("caja.idCaja", pIdCaja))
				.add(Restriccion.MAYOR("detalles.fechaCancelacion", pFechaDesde))
				.add(Restriccion.MENOR("detalles.fechaCancelacion", pFechaHasta))
				.add(Restriccion.LIKE("codigoBarras", "2", false, Posicion.AL_PRINCIPIO));

		List<TicketCaja> listaTickets = locCriterio.list();
		Iterator<TicketCaja> itTicketCaja = listaTickets.iterator();
		while(itTicketCaja.hasNext()) {
			TicketCaja locTicket = itTicketCaja.next();
			for(DetalleTicketCaja locDetalle : locTicket.getDetalles()) {
				this.setIngresoAlTicket(locDetalle);
			}
		}
		Collections.sort(listaTickets, new Comparator<TicketCaja>() {
			@Override
			public int compare(TicketCaja o1, TicketCaja o2) {
				return o1.getNumero().compareTo(o2.getNumero());
			}
		});
		dataSource = new ResumenActualCajaIngresoVarioDS(listaTickets);
		String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
		File fileReporte = new File(rutaReportes + dataSource.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(fileReporte);
		reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, dataSource.getMapaParametros(), dataSource);
		return jasperPrint;
	}

	private void setLiquidacionAlTicket(DetalleTicketCaja locDetalle) {
		LiquidacionTasa locLiquidacion = Criterio.getInstance(entity, LiquidacionTasa.class)
				.add(Restriccion.IGUAL("registroCancelacion", locDetalle))
				.uniqueResult();
		if(locLiquidacion == null && locDetalle.getTicketCaja().getEstado() == TicketCaja.Estado.ACTIVO) {
			//Si la liquidacion es nula pero el ticket esta ACTIVO, hubo algún problema,
			//recuperamos la deuda de nuevo.
			locLiquidacion = Criterio.getInstance(entity, LiquidacionTasa.class)
					.add(Restriccion.IGUAL("idRegistroDeuda", locDetalle.getIdRegistroDeuda()))
					.uniqueResult();
			if (locLiquidacion != null) {
				locDetalle.setRegistroDeudaReatachado(true);
				//Marcar paga de nuevo
				this.actualizarPlanes(locLiquidacion, locDetalle.getTicketCaja().getUsuario());
				businessLiquidacionTasa.generarLogLiquidacion(locLiquidacion, 
						locDetalle.getTicketCaja().getUsuario(), LogLiquidacion.Evento.PAGO_CAJA, null);
				locLiquidacion.setRegistroCancelacion(locDetalle);
				locLiquidacion.setEstado(EstadoRegistroDeuda.PAGADA);
				
				entity.merge(locDetalle);
			}
		}
		locDetalle.setDeuda(locLiquidacion);
	}

	private void setIngresoAlTicket(DetalleTicketCaja locDetalle) {
		LiquidacionTasa locIngreso = Criterio.getInstance(entity, IngresoVario.class).add(Restriccion.IGUAL("registroCancelacion", locDetalle)).uniqueResult();
		if(locIngreso != null) {
			locDetalle.setDeuda(locIngreso);
		}
	}

	public void addPagosPagoFacil(File pFile) {
		System.out.println("Direccion del archivo de pago facil");
		System.out.println(pFile.getAbsolutePath());
	}

	@Override
	@Deprecated
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(List<TicketCaja> pListaTickets) {
		return null;
	}

	public JasperPrint generarReporteCajaPorTasaDinamico(Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) {
		Criterio locCriterio = Criterio.getInstance(entity, TicketCaja.class).setDistinct(true).setModoDebug(true).add(Restriccion.IGUAL("usuario.idUsuario", pIdUsuario))
				.add(Restriccion.IGUAL("caja.idCaja", pIdCaja)).add(Restriccion.MAYOR("detalles.fechaCancelacion", pFechaDesde))
				.add(Restriccion.MENOR("detalles.fechaCancelacion", pFechaHasta));

		List<TicketCaja> listaTickets = locCriterio.list();
		Iterator<TicketCaja> itTicketCaja = listaTickets.iterator();
		while(itTicketCaja.hasNext()) {
			TicketCaja locTicket = itTicketCaja.next();
			for(DetalleTicketCaja locDetalle : locTicket.getDetalles()) {
				this.setLiquidacionAlTicket(locDetalle);
			}
		}
		Collections.sort(listaTickets, new Comparator<TicketCaja>() {
			@Override
			public int compare(TicketCaja o1, TicketCaja o2) {
				return o1.getNumero().compareTo(o2.getNumero());
			}
		});

		ReporteCajaDinamico reporte = new ReporteCajaDinamico(null, listaTickets);
		return null;
	}
}


package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.trascender.compras.business.ejb.BusinessProveedorBean;
import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.business.interfaces.BusinessBancoLocal;
import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco.Tipo;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.LineaRetencion;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.transients.CuentaCorriente;
import com.trascender.contabilidad.recurso.transients.MovCuentaCorrienteEgreso;
import com.trascender.contabilidad.recurso.transients.MovCuentaCorrienteIngreso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;

@Stateless(name = "ejb/BusinessBancoLocal")
public class BusinessBancoBean implements BusinessBancoLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3899594774240361343L;
	private static final String NOMBRE = "CUE|Adm Banco";

	@EJB
	private BusinessFacturaLocal businessFactura;

	@EJB
	private BusinessCajaLocal businessCaja;

	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuado;

	public BusinessBancoBean() {
	}

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessBancoBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessBancoBean.NOMBRE);

		Recurso banco = new Recurso();
		banco.setIdRecurso(Banco.serialVersionUID);
		banco.setNombre("Banco");
		banco.setClase(Banco.class);
		grupoRecursos.getListaRecursos().add(banco);

		Recurso cuentaBancaria = new Recurso();
		cuentaBancaria.setIdRecurso(CuentaBancaria.serialVersionUID);
		cuentaBancaria.setNombre("Cuenta Bancaria");
		cuentaBancaria.setClase(CuentaBancaria.class);
		grupoRecursos.getListaRecursos().add(cuentaBancaria);

		Recurso cheque = new Recurso();
		cheque.setIdRecurso(Cheque.serialVersionUID);
		cheque.setNombre("Cheque");
		cheque.setClase(Cheque.class);
		grupoRecursos.getListaRecursos().add(cheque);

		Recurso debito = new Recurso();
		debito.setIdRecurso(Debito.serialVersionUID);
		debito.setNombre("Debito");
		debito.setClase(Debito.class);
		grupoRecursos.getListaRecursos().add(debito);

		Recurso comprobanteRetencion = new Recurso();
		comprobanteRetencion.setIdRecurso(ComprobanteRetencion.serialVersionUID);
		comprobanteRetencion.setNombre("Comprabante Retención");
		comprobanteRetencion.setClase(ComprobanteRetencion.class);
		grupoRecursos.getListaRecursos().add(comprobanteRetencion);

		Recurso libroBanco = new Recurso();
		libroBanco.setIdRecurso(LibroBanco.serialVersionUID);
		libroBanco.setNombre("Libro Banco");
		libroBanco.setClase(LibroBanco.class);
		grupoRecursos.getListaRecursos().add(libroBanco);

		Recurso ordenPago = new Recurso();
		ordenPago.setIdRecurso(OrdenPago.serialVersionUID);
		ordenPago.setNombre("Orden Pago");
		ordenPago.setClase(OrdenPago.class);
		grupoRecursos.getListaRecursos().add(ordenPago);

		Recurso boletaDeposito = new Recurso();
		boletaDeposito.setIdRecurso(BoletaDeposito.serialVersionUID);
		boletaDeposito.setNombre("Boleta Depósito");
		boletaDeposito.setClase(BoletaDeposito.class);
		grupoRecursos.getListaRecursos().add(boletaDeposito);

		Recurso opdevolucion = new Recurso();
		opdevolucion.setIdRecurso(OrdenPagoDevolucion.serialVersionUID);
		opdevolucion.setNombre("Devolución");
		opdevolucion.setClase(OrdenPagoDevolucion.class);
		grupoRecursos.getListaRecursos().add(opdevolucion);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);

		// //Cuenta corriente del proveedor, se agrega en el menu de suministros
		Grupo locGrupoSuministros = new Grupo();
		locGrupoSuministros.setId(BusinessProveedorBean.serialVersionUID);
		locGrupoSuministros.setNombre(BusinessProveedorBean.NOMBRE);

		Recurso cuentaCorriente = new Recurso();
		cuentaCorriente.setNombre("Cuenta Corriente");
		cuentaCorriente.setIdRecurso(CuentaCorriente.serialVersionUID);
		cuentaCorriente.setClase(CuentaCorriente.class);
		locGrupoSuministros.getListaRecursos().add(cuentaCorriente);

		SecurityMgr.getInstance().addGrupo(locGrupoSuministros);
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	@PersistenceContext
	private EntityManager entity;

	public void ejbCreate() throws CreateException {
	}

	public com.trascender.contabilidad.recurso.persistent.Banco addBanco(com.trascender.contabilidad.recurso.persistent.Banco pBanco) throws java.lang.Exception {
		entity.persist(pBanco);
		return pBanco;
	}

	public com.trascender.contabilidad.recurso.persistent.Banco updateBanco(com.trascender.contabilidad.recurso.persistent.Banco pBanco) throws java.lang.Exception {
		entity.merge(pBanco);
		return pBanco;
	}

	public com.trascender.contabilidad.recurso.persistent.Banco getBancoByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		return entity.find(Banco.class, pId);
	}

	public void deleteBanco(com.trascender.contabilidad.recurso.persistent.Banco pBanco) throws java.lang.Exception {
		pBanco = entity.merge(pBanco);
		entity.remove(pBanco);
	}

	public List findListaBanco(String pNombre, String pSucursal) throws java.lang.Exception {
		return Criterio.getInstance(entity, Banco.class).add(Restriccion.ILIKE("nombre", pNombre)).add(Restriccion.ILIKE("sucursal", pSucursal)).list();
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaBancaria addCuentaBancaria(com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria)
			throws java.lang.Exception {
		entity.persist(pCuentaBancaria);
		return pCuentaBancaria;
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaBancaria updateCuentaBancaria(com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria)
			throws java.lang.Exception {
		entity.merge(pCuentaBancaria);
		return pCuentaBancaria;
	}

	public CuentaBancaria getCuentaBancariaByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		CuentaBancaria locCuentaBancaria = entity.find(CuentaBancaria.class, pId);
		locCuentaBancaria.getTipoCuenta().toString();
		locCuentaBancaria.getBanco().toString();
		locCuentaBancaria.getTitularCuentaBancaria().toString();
		locCuentaBancaria.toString();
		return locCuentaBancaria;
	}

	public void deleteCuentaBancaria(com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria) throws java.lang.Exception {
		pCuentaBancaria = entity.merge(pCuentaBancaria);
		if(!pCuentaBancaria.getBoletasDeposito().isEmpty()) {
			throw new TrascenderContabilidadException(120);
		}
		if(!pCuentaBancaria.getLibrosBanco().isEmpty()) {
			throw new TrascenderContabilidadException(121);
		}
		if(!pCuentaBancaria.getListaMovimientoBancario().isEmpty()) {
			throw new TrascenderContabilidadException(122);
		}
		entity.remove(pCuentaBancaria);
	}

	public List findListaCuentaBancaria(String pTipoCuenta, String pNumero, boolean pPropia, com.trascender.contabilidad.recurso.persistent.Banco pBanco) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CuentaBancaria.class).add(Restriccion.ILIKE("tipoCuenta", pTipoCuenta)).add(Restriccion.ILIKE("numero", pNumero))
				.add(Restriccion.IGUAL("banco", pBanco));

		List listaCuentaBancaria = locCriterio.list();

		for(Object obj : listaCuentaBancaria) {
			CuentaBancaria locCuentaBancaria = (CuentaBancaria) obj;
			locCuentaBancaria.toString();
			locCuentaBancaria.getTitularCuentaBancaria().toString();
		}
		return listaCuentaBancaria;
	}

	public com.trascender.contabilidad.recurso.persistent.LibroBanco addLibroBanco(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco) throws java.lang.Exception {
		LibroBanco locLibroBanco = Criterio.getInstance(entity, LibroBanco.class).add(Restriccion.IGUAL("cuentaBancaria", pLibroBanco.getCuentaBancaria()))
				.add(Restriccion.IGUAL("estado", LibroBanco.Estado.ACTIVO)).uniqueResult();

		if(locLibroBanco != null) {
			throw new TrascenderContabilidadException(190);
		}

		Double importeDeposito = new Double(0);
		Double importeExtraccion = new Double(0);
		for(LineaLibroBanco locLinea : pLibroBanco.getLineasLibroBanco()) {
			if(!locLinea.isConciliado() && locLinea.getTipo() == Tipo.DEPOSITO) {
				importeDeposito += locLinea.getImporte();
			}
			if(!locLinea.isConciliado() && locLinea.getTipo() == Tipo.EXTRACCION) {
				importeExtraccion += locLinea.getImporte();
			}
		}

		for(LineaLibroBanco locLinea : pLibroBanco.getLineasLibroBanco()) {
			if(locLinea.getImporte() == 0) {
				throw new TrascenderContabilidadException(191);
			}
		}
		entity.persist(pLibroBanco);
		return pLibroBanco;
	}

	public com.trascender.contabilidad.recurso.persistent.LibroBanco updateLibroBanco(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco) throws java.lang.Exception {
		entity.merge(pLibroBanco);
		return pLibroBanco;
	}

	public LibroBanco getLibroBancoById(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}

		LibroBanco locLibroBanco = entity.find(LibroBanco.class, pId);

		locLibroBanco.getCuentaBancaria().toString();
		for(LineaLibroBanco locLineaLibroBanco : locLibroBanco.getLineasLibroBanco()) {
			locLineaLibroBanco.toString();
			locLineaLibroBanco.getTipo().toString();
			locLineaLibroBanco.getFecha();
			if(locLineaLibroBanco.getObservaciones() != null) {
				locLineaLibroBanco.getObservaciones().toString();
			}
			for(MovimientoBancario cadaMovimiento : locLineaLibroBanco.getMovimientoBancario()) {
				cadaMovimiento.toString();
			}
			locLineaLibroBanco.getBoletasDeposito();
		}
		locLibroBanco.toString();
		return locLibroBanco;
	}

	public void deleteLibroBanco(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco) throws java.lang.Exception {
		entity.merge(pLibroBanco);
		entity.remove(pLibroBanco);
	}

	public List findListaLibroBanco(String pNombre, com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, LibroBanco.class).add(Restriccion.ILIKE("nombre", pNombre)).add(Restriccion.IGUAL("cuentaBancaria", pCuentaBancaria));

		List listaLibroBanco = locCriterio.list();

		for(Object obj : listaLibroBanco) {
			LibroBanco locLibroBanco = (LibroBanco) obj;
			locLibroBanco.getLineasLibroBanco();
			locLibroBanco.getCuentaBancaria().toString();
			locLibroBanco.toString();
		}
		return listaLibroBanco;
	}

	public com.trascender.contabilidad.recurso.persistent.BoletaDeposito addBoletaDeposito(com.trascender.contabilidad.recurso.persistent.BoletaDeposito pBoletaDeposito,
			com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		// Movimientos de egreso correspondientes
		MovimientoCajaEgreso locMovimientoCajaEgreso = new MovimientoCajaEgreso();
		locMovimientoCajaEgreso.setCuenta(pCuenta);
		locMovimientoCajaEgreso.setFecha(new Date());
		locMovimientoCajaEgreso.setImporte(pBoletaDeposito.getImporte());
		pBoletaDeposito.getListaMovimientoCajaEgreso().add(locMovimientoCajaEgreso);
		this.entity.persist(pBoletaDeposito);
		return pBoletaDeposito;
	}

	public com.trascender.contabilidad.recurso.persistent.BoletaDeposito updateBoletaDeposito(com.trascender.contabilidad.recurso.persistent.BoletaDeposito pBoletaDeposito,
			com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		entity.merge(pBoletaDeposito);
		// Movimientos de egreso correspondientes
		MovimientoCajaEgreso locMovimientoCajaEgreso = pBoletaDeposito.getListaMovimientoCajaEgreso().iterator().next();
		locMovimientoCajaEgreso.setCuenta(pCuenta);
		locMovimientoCajaEgreso.setFecha(new Date());
		locMovimientoCajaEgreso.setImporte(pBoletaDeposito.getImporte());
		return pBoletaDeposito;
	}

	public BoletaDeposito getBoletaDepositoByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		BoletaDeposito locBoletaDeposito = entity.find(BoletaDeposito.class, pId);
		locBoletaDeposito.toString();
		for(MovimientoCajaEgreso locMovimientoCajaEgreso : locBoletaDeposito.getListaMovimientoCajaEgreso()) {
			locMovimientoCajaEgreso.toString();
			locMovimientoCajaEgreso.getCuenta().toString();
		}

		return locBoletaDeposito;
	}

	public void deleteBoletaDeposito(com.trascender.contabilidad.recurso.persistent.BoletaDeposito pBoletaDeposito) throws java.lang.Exception {
		pBoletaDeposito = entity.merge(pBoletaDeposito);
		entity.remove(pBoletaDeposito);
	}

	public List findListaBoletaDeposito(String pNumeroBoleta, Double pImporteDesde, Double pImporteHasta, Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception {
		return Criterio.getInstance(entity, BoletaDeposito.class).add(Restriccion.IGUAL("numeroBoleta", pNumeroBoleta)).add(Restriccion.MAYOR("importe", pImporteDesde))
				.add(Restriccion.MENOR("importe", pImporteHasta)).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta)).list();
	}

	public com.trascender.contabilidad.recurso.persistent.Cheque addCheque(com.trascender.contabilidad.recurso.persistent.Cheque pCheque) throws java.lang.Exception {
		if(pCheque.isPostdatado()) {
			if(pCheque.getFechaEmision().compareTo(pCheque.getFechaPago()) >= 0) {
				throw new TrascenderContabilidadException(71);
			}
		}
		entity.persist(pCheque);
		return pCheque;
	}

	public com.trascender.contabilidad.recurso.persistent.Cheque updateCheque(com.trascender.contabilidad.recurso.persistent.Cheque pCheque) throws java.lang.Exception {
		pCheque = entity.merge(pCheque);
		for(OrdenPago locOrdenPago : pCheque.getOrdenesPago()) {
			Proveedor locProveedor = locOrdenPago.getProveedor();
			for(OrdenPago ordenPago : pCheque.getOrdenesPago()) {
				if(ordenPago.getProveedor() != locProveedor) {
					throw new TrascenderContabilidadException(70);
				}
			}
		}
		return pCheque;
	}

	public void deleteCheque(com.trascender.contabilidad.recurso.persistent.Cheque pCheque) throws java.lang.Exception {
		pCheque = entity.merge(pCheque);
		entity.remove(pCheque);
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws java.lang.Exception
	 */
	public Cheque getChequeByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Cheque locCheque = entity.find(Cheque.class, pId);
		locCheque.getCuentaBancaria().toString();
		locCheque.toString();
		return locCheque;
	}

	public List findListaCheque(String pNumeroCheque, Date pFechaEmisionDesde, Date pFechaEmisionHasta, Date pFechaPagoDesde, Date pFechaPagoHasta, Double pImporteDesde,
			Double pImporteHasta) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Cheque.class).add(Restriccion.ILIKE("numeroCheque", pNumeroCheque))
				.add(Restriccion.MAYOR("fechaEmision", pFechaEmisionDesde)).add(Restriccion.MENOR("fechaEmision", pFechaEmisionHasta)).add(Restriccion.MAYOR("importe", pImporteDesde))
				.add(Restriccion.MENOR("importe", pImporteHasta)).add(Restriccion.MAYOR("fechaPago", pFechaPagoDesde)).add(Restriccion.MENOR("fechaPago", pFechaPagoHasta));

		List listaCheque = locCriterio.list();
		return listaCheque;
	}

	public OrdenPagoDevolucion addOrdenPago(OrdenPagoDevolucion pOrdenPago) throws Exception {
		Calendar locCalendarInicio = SecurityMgr.getInstance().getFechaActual();
		locCalendarInicio.set(Calendar.MONTH, Calendar.JANUARY);
		locCalendarInicio.set(Calendar.DAY_OF_MONTH, 1);
		Calendar locCalendarFin = SecurityMgr.getInstance().getFechaActual();
		locCalendarFin.set(Calendar.MONTH, Calendar.DECEMBER);
		locCalendarFin.set(Calendar.DAY_OF_MONTH, 31);
		List listaOrdenPago = Criterio.getInstance(entity, DocumentoEgreso.class).add(Restriccion.MAYOR("fechaEmision", locCalendarInicio.getTime()))
				.add(Restriccion.MENOR("fechaEmision", locCalendarFin.getTime())).add(Orden.DESC("numero")).list();

		if(listaOrdenPago.isEmpty()) {
			pOrdenPago.setNumero(1);
		} else {
			DocumentoEgreso locOrdenPago = (DocumentoEgreso) listaOrdenPago.get(0);
			pOrdenPago.setNumero(locOrdenPago.getNumero() + 1);
		}

		Double importe = new Double(0);
		for(LineaOrdenPagoDevolucion linea : pOrdenPago.getLineaOrdenPagoDev()) {
			linea.setOrdenPago(pOrdenPago);
			linea.setImporte(linea.getTicketCaja().getImporteTotal());
			importe += linea.getImporte();
		}
		pOrdenPago.setImporte(importe);

		this.entity.persist(pOrdenPago);
		return pOrdenPago;
	}

	public OrdenPagoDevolucion updateOrdenPago(OrdenPagoDevolucion pOrdenPagoDevolucion) throws Exception {
		if(pOrdenPagoDevolucion.getEstado() != DocumentoEgreso.Estado.CREADA) {
			throw new TrascenderContabilidadException(137);
		}

		Double importe = new Double(0);
		for(LineaOrdenPagoDevolucion linea : pOrdenPagoDevolucion.getLineaOrdenPagoDev()) {
			linea.setOrdenPago(pOrdenPagoDevolucion);
			linea.setImporte(linea.getTicketCaja().getImporteTotal());
			importe += linea.getImporte();
		}
		pOrdenPagoDevolucion.setImporte(importe);

		this.entity.merge(pOrdenPagoDevolucion);
		return pOrdenPagoDevolucion;
	}

	public OrdenPagoDevolucion getOrdenPagoDevolucionByID(Long pId) throws Exception {
		OrdenPagoDevolucion locOrdenPago = entity.find(OrdenPagoDevolucion.class, pId);
		locOrdenPago.getPersona().toString();
		for(MovimientoBancario locMov : locOrdenPago.getMovimientosBancarios()) {
			locMov.getCuentaBancaria().toString();
			locMov.toString();
		}
		for(LineaOrdenPagoDevolucion locLinea : locOrdenPago.getLineaOrdenPagoDev()) {
			locLinea.toString();
		}

		return locOrdenPago;
	}

	public OrdenPagoDevolucion confirmarOrdenPago(OrdenPagoDevolucion pOrdenPagoDevolucion) throws Exception {
		pOrdenPagoDevolucion = entity.find(OrdenPagoDevolucion.class, pOrdenPagoDevolucion.getIdDocumentoEgreso());
		// Verifico que la orden de pago este en estado Creada
		if(pOrdenPagoDevolucion.getEstado() == DocumentoEgreso.Estado.CANCELADA) {
			throw new TrascenderContabilidadException(131);
		}
		if(pOrdenPagoDevolucion.getEstado() == DocumentoEgreso.Estado.CONFIRMADA) {
			throw new TrascenderContabilidadException(132);
		}
		this.validarDatosOrdenPago(pOrdenPagoDevolucion);
		this.hacerMovimientos(pOrdenPagoDevolucion);
		pOrdenPagoDevolucion.setEstado(DocumentoEgreso.Estado.CONFIRMADA);
		return pOrdenPagoDevolucion;
	}

	private void hacerMovimientos(OrdenPagoDevolucion pOrdenPagoDevolucion) throws Exception {
		for(LineaOrdenPagoDevolucion locLineaOrdenPago : pOrdenPagoDevolucion.getLineaOrdenPagoDev()) {
			TicketCaja locTicketCaja = this.businessCaja.devolverTicketCaja(locLineaOrdenPago.getTicketCaja());
			for(DetalleTicketCaja locDetalle : locTicketCaja.getDetalles()) {
				for(MovimientoCajaIngreso locMovimiento : locDetalle.getMovimientosCajaIngreso()) {
					MovimientoCajaEgreso locEgreso = new MovimientoCajaEgreso();
					locEgreso.setCuenta(locMovimiento.getCuenta());
					locEgreso.setImporte(locMovimiento.getImporte());
					locEgreso.setDocumentoEgreso(pOrdenPagoDevolucion);
					locEgreso.setPlanilla(locTicketCaja.getPlanillaDiariaCaja());
					locEgreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
					pOrdenPagoDevolucion.getListaMovimientoCajaEgreso().add(locEgreso);
				}
			}
		}
	}

	public com.trascender.contabilidad.recurso.persistent.OrdenPago addOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws java.lang.Exception {

		// Busco si hay ordenes de pago en el año, para sacar el numero correlativo
		Calendar locCalendarInicio = SecurityMgr.getInstance().getFechaActual();
		locCalendarInicio.set(Calendar.MONTH, Calendar.JANUARY);
		locCalendarInicio.set(Calendar.DAY_OF_MONTH, 1);
		Calendar locCalendarFin = SecurityMgr.getInstance().getFechaActual();
		locCalendarFin.set(Calendar.MONTH, Calendar.DECEMBER);
		locCalendarFin.set(Calendar.DAY_OF_MONTH, 31);
		Integer numeroOrdenPago = Criterio.getInstance(entity, DocumentoEgreso.class).add(Restriccion.MAYOR("fechaEmision", locCalendarInicio.getTime()))
				.add(Restriccion.MENOR("fechaEmision", locCalendarFin.getTime())).add(Orden.DESC("numero")).setProyeccion(Proyeccion.PROP("numero")).uniqueResult();

		pOrdenPago.setNumero(numeroOrdenPago != null ? numeroOrdenPago : 1);

		for(LineaOrdenPago linea : pOrdenPago.getLineasOrdenPago()) {
			linea.setOrdenPago(pOrdenPago);
		}

		Double importe = new Double(0);
		for(LineaOrdenPago linea : pOrdenPago.getLineasOrdenPago()) {
			linea.setImporte(linea.getFactura().getTotal());
			importe += linea.getImporte();
		}

		pOrdenPago.setImporte(importe);
		validarFacturasOrdenPago(pOrdenPago);
		this.entity.merge(pOrdenPago);
		return pOrdenPago;
	}

	private void validarFacturasOrdenPago(OrdenPago pOrdenPago) {
		for(LineaOrdenPago cadaLineaOrdenPago : pOrdenPago.getLineasOrdenPago()) {
			if(cadaLineaOrdenPago.getFactura().getEstado().equals(Factura.Estado.CREADA)) {
				cadaLineaOrdenPago.getFactura().setEstado(Factura.Estado.PROCESADA);
			}
		}

		if(pOrdenPago.getIdDocumentoEgreso() != -1) {
			OrdenPago locOrdenPersistida = Criterio.getInstance(entity, OrdenPago.class).add(Restriccion.IGUAL("idDocumentoEgreso", pOrdenPago.getIdDocumentoEgreso())).uniqueResult();

			for(LineaOrdenPago cadaLineaPersistida : locOrdenPersistida.getLineasOrdenPago()) {
				for(LineaOrdenPago cadaLineaParametro : pOrdenPago.getLineasOrdenPago()) {
					if(cadaLineaPersistida.getFactura().equals(cadaLineaParametro.getFactura())) {
						break;
					}
				}
				cadaLineaPersistida.getFactura().setEstado(Factura.Estado.CREADA);
			}

		}
	}

	public com.trascender.contabilidad.recurso.persistent.OrdenPago confirmarOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws Exception {
		pOrdenPago = entity.find(OrdenPago.class, pOrdenPago.getIdDocumentoEgreso());
		// Verifico que la orden de pago este en estado Creada
		if(pOrdenPago.getEstado() == DocumentoEgreso.Estado.CANCELADA) {
			throw new TrascenderContabilidadException(131);
		}
		if(pOrdenPago.getEstado() == DocumentoEgreso.Estado.CONFIRMADA) {
			throw new TrascenderContabilidadException(132);
		}
		this.validarDatosOrdenPago(pOrdenPago);
		this.hacerMovimientos(pOrdenPago);
		// Seteo a -1 los ids, porque en hacerMovimientos() se cambia el id.
		for(MovimientoCajaEgreso movimientoCaja : pOrdenPago.getListaMovimientoCajaEgreso()) {
			movimientoCaja.setIdMovimientoCajaEgreso(-1);
		}
		pOrdenPago.setEstado(OrdenPago.Estado.CONFIRMADA);
		this.entity.clear();
		this.entity.merge(pOrdenPago);
		return pOrdenPago;
	}

	public com.trascender.contabilidad.recurso.persistent.OrdenPago cancelarOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws Exception {
		// Verifico que la orden de pago este en estado Creada
		if(pOrdenPago.getEstado() == OrdenPago.Estado.CANCELADA) {
			throw new TrascenderContabilidadException(133);
		}
		if(pOrdenPago.getEstado() == OrdenPago.Estado.CONFIRMADA) {
			throw new TrascenderContabilidadException(134);
		}

		pOrdenPago.setEstado(OrdenPago.Estado.CANCELADA);
		this.entity.merge(pOrdenPago);
		return pOrdenPago;
	}

	private void validarDatosOrdenPago(OrdenPagoDevolucion pOrdenPago) throws Exception {
		Double importe = new Double(0);
		Double importeAIgualar = new Double(0);

		for(MovimientoBancario locMovimientoBancario : pOrdenPago.getMovimientosBancarios()) {
			importeAIgualar += locMovimientoBancario.getImporte();
		}
		for(LineaOrdenPagoDevolucion locLineaOrdenPago : pOrdenPago.getLineaOrdenPagoDev()) {
			importe += locLineaOrdenPago.getImporte();
		}
		pOrdenPago.setImporte(importe);
		if(pOrdenPago.getImporte() < importeAIgualar) {
			throw new TrascenderContabilidadException(136);
		}
		if(pOrdenPago.getImporte() > importeAIgualar) {
			throw new TrascenderContabilidadException(135);
		}
	}

	/**
	 * Veo que el monto de la orden de pago sea satisfecho por el cheque y/o comprobante de retencion
	 */
	private void validarDatosOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws Exception {
		Double importe = new Double(0);
		Double importeAIgualar = new Double(0);
		if(pOrdenPago.getComprobanteRetencion() != null) {
			importeAIgualar += pOrdenPago.getComprobanteRetencion().getImporte();
		}
		for(MovimientoBancario locMovimientoBancario : pOrdenPago.getMovimientosBancarios()) {
			importeAIgualar += locMovimientoBancario.getImporte();
		}
		for(LineaOrdenPago locLineaOrdenPago : pOrdenPago.getLineasOrdenPago()) {
			importe += locLineaOrdenPago.getImporte();
		}
		pOrdenPago.setImporte(importe);

		if(pOrdenPago.getImporte() > importeAIgualar) {
			throw new TrascenderContabilidadException(130);
		}
		if(pOrdenPago.getImporte() < importeAIgualar) {
			throw new TrascenderContabilidadException(134);
		}
	}

	private void hacerMovimientos(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws Exception {
		for(LineaOrdenPago locLineaOrdenPago : pOrdenPago.getLineasOrdenPago()) {
			Factura locFactura = this.businessFactura.getFacturaPorId(locLineaOrdenPago.getFactura().getIdFactura());

			for(LineaFactura locLineaFactura : locFactura.getListaLineaFactura()) {
				locLineaFactura.setTotal(null);
				Cuenta locCuenta = this.obtenerCuenta(locLineaFactura.getCuenta().getIdCuenta());
				MovimientoCajaEgreso locMovimientoCajaEgreso = new MovimientoCajaEgreso();
				locMovimientoCajaEgreso.setCuenta(locCuenta);
				locMovimientoCajaEgreso.setDocumentoEgreso(pOrdenPago);
				locMovimientoCajaEgreso.setImporte(locLineaFactura.getTotal());
				locMovimientoCajaEgreso.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
				pOrdenPago.getListaMovimientoCajaEgreso().add(locMovimientoCajaEgreso);
			}
		}
	}

	private com.trascender.contabilidad.recurso.persistent.Cuenta obtenerCuenta(Long pId) {
		Cuenta locCuenta = entity.find(Cuenta.class, pId);
		return locCuenta;
	}

	public com.trascender.contabilidad.recurso.persistent.OrdenPago updateOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws java.lang.Exception {
		if(pOrdenPago.getEstado() != DocumentoEgreso.Estado.CREADA) {
			throw new TrascenderContabilidadException(137);
		}
		for(LineaOrdenPago cadaLineaOrdenPago : pOrdenPago.getLineasOrdenPago()) {
			cadaLineaOrdenPago.setOrdenPago(pOrdenPago);
		}

		Double importe = new Double(0);
		for(LineaOrdenPago linea : pOrdenPago.getLineasOrdenPago()) {
			linea.setImporte(linea.getFactura().getTotal());
			importe += linea.getImporte();
		}
		pOrdenPago.setImporte(importe);

		if(pOrdenPago.getComprobanteRetencion() != null) {
			entity.remove(pOrdenPago.getComprobanteRetencion());
			pOrdenPago.setComprobanteRetencion(null);
		}
		validarFacturasOrdenPago(pOrdenPago);
		entity.merge(pOrdenPago);
		return pOrdenPago;
	}

	public void deleteOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago) throws java.lang.Exception {
		entity.merge(pOrdenPago);
		if(pOrdenPago.getComprobanteRetencion() != null) {
			entity.remove(pOrdenPago.getComprobanteRetencion());
		}

		for(LineaOrdenPago cadaLinea : pOrdenPago.getLineasOrdenPago()) {
			cadaLinea.getFactura().setEstado(Factura.Estado.CREADA);
		}

		entity.remove(pOrdenPago);
	}

	public void deleteOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion pOrdenPago) throws java.lang.Exception {
		entity.merge(pOrdenPago);
		entity.remove(pOrdenPago);
	}

	public com.trascender.contabilidad.recurso.persistent.OrdenPago getOrdenPagoByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		OrdenPago locOrdenPago = entity.find(OrdenPago.class, pId);

		if(locOrdenPago == null) {
			return null;
		}

		if(locOrdenPago.getComprobanteRetencion() != null) {
			locOrdenPago.getComprobanteRetencion().toString();
			for(LineaRetencion unaLinea : locOrdenPago.getComprobanteRetencion().getLineaRetencion()) {
				unaLinea.toString();
				unaLinea.getParametroRetencion().toString();
				unaLinea.getComprobanteRetencion().toString();
			}
		}

		for(MovimientoBancario locMovimientoBancario : locOrdenPago.getMovimientosBancarios()) {
			locMovimientoBancario.toString();
			locMovimientoBancario.getCuentaBancaria().toString();
		}
		locOrdenPago.getProveedor().toString();
		for(LineaOrdenPago locLineaOrdenPago : locOrdenPago.getLineasOrdenPago()) {
			locLineaOrdenPago.getFactura();
			if(locLineaOrdenPago.getFactura() != null) {
				locLineaOrdenPago.getFactura().toString();
			}
			locLineaOrdenPago.toString();
		}
		return locOrdenPago;
	}

	public com.trascender.contabilidad.recurso.persistent.LineaOrdenPago getLineaOrdenPagoByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		LineaOrdenPago locLineaOrdenPago = entity.find(LineaOrdenPago.class, pId);
		return locLineaOrdenPago;
	}

	public com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion getLineaOrdenPagoDevolucionByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		LineaOrdenPagoDevolucion locLineaOrdenPago = entity.find(LineaOrdenPagoDevolucion.class, pId);
		locLineaOrdenPago.getTicketCaja().toString();
		locLineaOrdenPago.toString();
		locLineaOrdenPago.getOrdenPago();
		return locLineaOrdenPago;
	}

	public List findListaOrdenPagoDev(Date pFechaEmisionDesde, Date pFechaEmisionHasta, Date pFechaPagoDesde, Date pFechaPagoHasta, Double pImporteDesde, Double pImporteHasta,
			Persona pPersona, OrdenPago.Estado pEstado) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, OrdenPagoDevolucion.class).add(Restriccion.IGUAL("persona", pPersona)).add(Restriccion.IGUAL("estado", pEstado))
				.add(Restriccion.MAYOR("fechaEmision", pFechaEmisionDesde)).add(Restriccion.MENOR("fechaEmision", pFechaEmisionHasta)).add(Restriccion.MAYOR("importe", pImporteDesde))
				.add(Restriccion.MENOR("importe", pImporteHasta)).add(Restriccion.MAYOR("fechaPago", pFechaPagoDesde)).add(Restriccion.MENOR("fechaPago", pFechaPagoHasta));

		List<OrdenPagoDevolucion> listaOrdenPago = locCriterio.list();

		if(listaOrdenPago == null || listaOrdenPago.isEmpty()) {
			return null;
		}
		for(OrdenPagoDevolucion unaOrden : listaOrdenPago) {
			unaOrden.toString();
			unaOrden.getPersona().toString();
		}

		return listaOrdenPago;
	}

	public List findListaOrdenPago(Date pFechaEmisionDesde, Date pFechaEmisionHasta, Date pFechaPagoDesde, Date pFechaPagoHasta, Double pImporteDesde, Double pImporteHasta,
			Proveedor pProveedor, OrdenPago.Estado pEstado) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, OrdenPago.class).add(Restriccion.IGUAL("proveedor", pProveedor)).add(Restriccion.MAYOR("fechaEmision", pFechaEmisionDesde))
				.add(Restriccion.MENOR("fechaEmision", pFechaEmisionHasta)).add(Restriccion.MAYOR("importe", pImporteDesde)).add(Restriccion.MENOR("importe", pImporteHasta))
				.add(Restriccion.MAYOR("fechaPago", pFechaPagoDesde)).add(Restriccion.MENOR("fechaPago", pFechaPagoHasta));

		List<OrdenPago> listaOrdenPago = locCriterio.list();

		for(OrdenPago unaOrden : listaOrdenPago) {
			unaOrden.toString();
			unaOrden.getProveedor().toString();
			if(unaOrden.getComprobanteRetencion() != null) {
				unaOrden.getComprobanteRetencion().toString();
				for(LineaRetencion unaLinea : unaOrden.getComprobanteRetencion().getLineaRetencion()) {
					unaLinea.toString();
					unaLinea.getParametroRetencion().toString();
				}
			}

			for(LineaOrdenPago unaLinea : unaOrden.getLineasOrdenPago()) {
				unaLinea.toString();
				if(unaLinea.getFactura() != null) {
					unaLinea.getFactura().toString();
				}
			}
		}
		return listaOrdenPago;
	}

	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion addComprobanteRetencion(
			com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion pComprobanteRetencion, com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
			throws java.lang.Exception {

		if(pComprobanteRetencion.getLineaRetencion().isEmpty()) {
			throw new TrascenderContabilidadException(720);
		}

		if(pComprobanteRetencion.getPeriodo() == null) {
			throw new TrascenderContabilidadException(721);
		}

		List<OrdenPago> listaOrdenesPagoMes = Criterio.getInstance(entity, OrdenPago.class)
				.add(Restriccion.MAYOR("fechaEmision", pComprobanteRetencion.getPeriodo().getFechaInicio().getTime()))
				.add(Restriccion.MENOR("fechaEmision", pComprobanteRetencion.getPeriodo().getFechaFin().getTime())).add(Restriccion.IGUAL("proveedor", pProveedor))
				.add(Restriccion.IGUAL("estado", DocumentoEgreso.Estado.CREADA)).add(Orden.DESC("idDocumentoEgreso")).list();

		for(OrdenPago unaOrden : listaOrdenesPagoMes) {
			unaOrden.toString();
			unaOrden.getProveedor().toString();
			if(unaOrden.getComprobanteRetencion() != null) {
				unaOrden.getComprobanteRetencion().toString();
				for(LineaRetencion unaLinea : unaOrden.getComprobanteRetencion().getLineaRetencion()) {
					unaLinea.toString();
					unaLinea.getParametroRetencion().toString();
				}
			}

			for(LineaOrdenPago unaLinea : unaOrden.getLineasOrdenPago()) {
				unaLinea.toString();
				unaLinea.getFactura().toString();
			}
		}

		if(listaOrdenesPagoMes.isEmpty()) {
			throw new TrascenderContabilidadException(722);
		}

		OrdenPago locUltimaOrdenPago = listaOrdenesPagoMes.get(0);

		// Me fijo si la ultima orden de pago ya no tiene asignada una retención
		ComprobanteRetencion locComprobanteRetencion = this.getComprobanteRetencionByOrdenPago(locUltimaOrdenPago);

		if(locComprobanteRetencion != null) {
			throw new TrascenderContabilidadException(725);
		}

		Double locTotalComprobanteRentecion = 0d;

		/*
		 * Por cada parametro de retencion de pRetencion recorro las ordenes de pago del mes para ver si ya no fue cobrada, si es así le descuento el monto.
		 */
		// for (LineaRetencion unaLinea : pComprobanteRetencion.getLineaRetencion()) {
		// boolean encontrado = false;
		// Iterator<OrdenPago> itOrdenPago = listaOrdenesPagoMes.iterator();
		// while(itOrdenPago.hasNext() && encontrado == false) {
		// OrdenPago locOrdenPago = (OrdenPago)itOrdenPago.next();
		// if(locOrdenPago.getComprobanteRetencion() != null) {
		// for (LineaRetencion unaLineaVieja : locOrdenPago.getComprobanteRetencion().getLineaRetencion()) {
		// if(unaLineaVieja.getParametroRetencion().equals(unaLinea.getParametroRetencion())) {
		// unaLinea.setImporte(unaLinea.getImporte() - unaLineaVieja.getImporte());
		// locTotalRetenciones += unaLinea.getImporte();
		//
		// encontrado = true;
		// }
		// }
		// }
		// }
		// }

		for(LineaRetencion unaLinea : pComprobanteRetencion.getLineaRetencion()) {
			Double locMontoTotalAcumulado = 0d;
			for(OrdenPago locOrdenPago : listaOrdenesPagoMes) {
				if(locOrdenPago.getComprobanteRetencion() != null) {
					for(LineaRetencion unaLineaVieja : locOrdenPago.getComprobanteRetencion().getLineaRetencion()) {
						if(unaLineaVieja.getParametroRetencion().equals(unaLinea.getParametroRetencion())) {
							locMontoTotalAcumulado += unaLineaVieja.getImporte();
						}
					}
				}
			}

			unaLinea.setImporte(unaLinea.getImporte() - locMontoTotalAcumulado);
			locTotalComprobanteRentecion += unaLinea.getImporte();
			unaLinea.setComprobanteRetencion(pComprobanteRetencion);
		}

		pComprobanteRetencion.setImporte(locTotalComprobanteRentecion);

		pComprobanteRetencion.setOrdenPago(locUltimaOrdenPago);
		locUltimaOrdenPago.setComprobanteRetencion(pComprobanteRetencion);
		locUltimaOrdenPago.setImporte(locUltimaOrdenPago.getImporte() - pComprobanteRetencion.getImporte());

		entity.merge(locUltimaOrdenPago);
		return pComprobanteRetencion;
	}

	public void deleteComprobanteRetencion(com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion pComprobanteRetencion) throws java.lang.Exception {
		OrdenPago locOrdenPago = (OrdenPago) Criterio.getInstance(entity, OrdenPago.class).add(Restriccion.IGUAL("comprobanteRetencion", pComprobanteRetencion)).uniqueResult();

		if(locOrdenPago.getComprobanteRetencion() != null) {
			locOrdenPago.getComprobanteRetencion().toString();
		}

		for(LineaOrdenPago unaLinea : locOrdenPago.getLineasOrdenPago()) {
			unaLinea.toString();
			unaLinea.getFactura().toString();
		}

		if(locOrdenPago.getEstado().equals(OrdenPago.Estado.CONFIRMADA)) {
			throw new TrascenderContabilidadException(724);
		}

		locOrdenPago.setImporte(locOrdenPago.getImporte() + locOrdenPago.getComprobanteRetencion().getImporte());
		// locOrdenPago.getComprobanteRetencion().setOrdenPago(null);
		locOrdenPago.setComprobanteRetencion(null);

		// pComprobanteRetencion.setOrdenPago(null);
		entity.clear();
		this.updateOrdenPago(locOrdenPago);

		entity.merge(pComprobanteRetencion);
		this.entity.remove(pComprobanteRetencion);
	}

	@SuppressWarnings("unchecked")
	public Double calcularMontoRetencion(ParametroRetencion pParametroRetencion, Periodo pPeriodo, Proveedor pProveedor) throws java.lang.Exception {
		List<OrdenPago> listaOrdenesPagoMes = this.findListaOrdenPago(pPeriodo.getFechaInicio().getTime(), pPeriodo.getFechaFin().getTime(), null, null, null, null, pProveedor,
				OrdenPago.Estado.CREADA);

		if(listaOrdenesPagoMes == null) {
			throw new TrascenderContabilidadException(722);
		}

		/*
		 * Ordenes de Pago sobre las cuales se calculan la retenecion
		 */
		for(OrdenPago unaOrdenPago : listaOrdenesPagoMes) {
			unaOrdenPago.toString();
		}

		/*
		 * Sumatoria de los montos de las Ordenes de Pago
		 */
		Double locMontoOrdenesPago = 0d;
		if(!listaOrdenesPagoMes.isEmpty()) {
			for(OrdenPago unaOrden : listaOrdenesPagoMes) {
				locMontoOrdenesPago += unaOrden.getImporte();
			}
		}

		Double locMontoSujetoRetencion = 0d;

		Double locTotalRetencion = 0d;

		/*
		 * Me fijo si el monto de la suma de las órdenes de pago del mes del proveedor supera el monto mínimo de la retención, si es así calculo la retención
		 */
		if(locMontoOrdenesPago > pParametroRetencion.getImporteMinimo()) {
			if(pParametroRetencion.isDeducirIVA()) {
				locMontoSujetoRetencion = locMontoOrdenesPago - locMontoOrdenesPago * 0.21 - pParametroRetencion.getImporteMinimo();
			} else {
				locMontoSujetoRetencion = locMontoOrdenesPago - pParametroRetencion.getImporteMinimo();
			}

			if(pParametroRetencion.getPorcentaje() < 100) {
				locMontoSujetoRetencion = locMontoSujetoRetencion - locMontoSujetoRetencion * (pParametroRetencion.getPorcentaje() / 100);
			}

			locTotalRetencion += locMontoSujetoRetencion * (pParametroRetencion.getAlicuota() / 100d);
		}
		return locTotalRetencion;
	}

	public com.trascender.contabilidad.recurso.persistent.LineaRetencion getLineaRetencionByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		LineaRetencion locLineaRetencion = entity.find(LineaRetencion.class, pId);

		locLineaRetencion.getParametroRetencion().toString();
		locLineaRetencion.getComprobanteRetencion().toString();

		return locLineaRetencion;
	}

	public com.trascender.contabilidad.recurso.persistent.ParametroRetencion addParametroRetencion(com.trascender.contabilidad.recurso.persistent.ParametroRetencion pParametroRetencion)
			throws java.lang.Exception {
		this.validarDatosParametrosRetencion(pParametroRetencion);
		entity.persist(pParametroRetencion);
		return pParametroRetencion;
	}

	public com.trascender.contabilidad.recurso.persistent.ParametroRetencion updateParametroRetencion(
			com.trascender.contabilidad.recurso.persistent.ParametroRetencion pParametroRetencion) throws Exception {

		this.validarDatosParametrosRetencion(pParametroRetencion);

		entity.merge(pParametroRetencion);
		return pParametroRetencion;
	}

	public void deleteParametroRetencion(com.trascender.contabilidad.recurso.persistent.ParametroRetencion pParametroRetencion) throws Exception {
		entity.merge(pParametroRetencion);
		entity.remove(pParametroRetencion);
	}

	@SuppressWarnings("unchecked")
	public List<ParametroRetencion> findListaParametroRetencion() throws java.lang.Exception {
		return Criterio.getInstance(entity, ParametroRetencion.class).list();
	}

	private void validarDatosParametrosRetencion(ParametroRetencion pParametroRetencion) throws TrascenderContabilidadException {
		if(pParametroRetencion.getNombre().trim() == "" || pParametroRetencion.getNombre() == null) {
			throw new TrascenderContabilidadException(150);
		}

		if(pParametroRetencion.getAlicuota() == null || pParametroRetencion.getAlicuota() < 0) {
			throw new TrascenderContabilidadException(151);
		}

		if(pParametroRetencion.getImporteMinimo() == null || pParametroRetencion.getImporteMinimo() < 0) {
			throw new TrascenderContabilidadException(152);
		}

		if(pParametroRetencion.getPorcentaje() == null || pParametroRetencion.getPorcentaje() < 0) {
			throw new TrascenderContabilidadException(153);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.ParametroRetencion getParametroRetencionByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		ParametroRetencion locParametroRetencion = entity.find(ParametroRetencion.class, pId);
		return locParametroRetencion;
	}

	@SuppressWarnings("unchecked")
	public List<ComprobanteRetencion> findListaRetencion(Periodo pPeriodo, Proveedor pProveedor) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ComprobanteRetencion.class).add(Restriccion.IGUAL("ordenPago.proveedor", pProveedor))
				.add(Restriccion.MAYOR("ordenPago.fechaEmision", pPeriodo.getFechaInicio().getTime()))
				.add(Restriccion.MENOR("ordenPago.fechaEmision", pPeriodo.getFechaFin().getTime()));
		List<ComprobanteRetencion> listaRetenciones = locCriterio.list();

		for(Object obj : listaRetenciones) {
			ComprobanteRetencion locRetencion = (ComprobanteRetencion) obj;
			locRetencion.toString();
			locRetencion.getOrdenPago().toString();

			locRetencion.getLineaRetencion().toString();
		}

		return listaRetenciones;
	}

	public com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion getComprobanteRetencionByID(Long pId) throws Exception {
		ComprobanteRetencion locComprobante = entity.find(ComprobanteRetencion.class, pId);

		if(locComprobante != null) {
			locComprobante.toString();
			if(locComprobante.getOrdenPago() != null) {
				locComprobante.getOrdenPago().toString();
				locComprobante.getOrdenPago().getProveedor().toString();
			}

			for(LineaRetencion unaLinea : locComprobante.getLineaRetencion()) {
				unaLinea.toString();
				unaLinea.getParametroRetencion().toString();
			}
		}
		return locComprobante;
	}

	public com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion getComprobanteRetencionByOrdenPago(OrdenPago pOrdenPago) throws Exception {
		ComprobanteRetencion locComprobante = Criterio.getInstance(entity, ComprobanteRetencion.class).add(Restriccion.IGUAL("ordenPago", pOrdenPago)).uniqueResult();

		if(locComprobante != null) {
			locComprobante.toString();
			locComprobante.getOrdenPago().toString();
			locComprobante.getOrdenPago().getProveedor().toString();

			for(LineaRetencion unaLinea : locComprobante.getLineaRetencion()) {
				unaLinea.toString();
				unaLinea.getParametroRetencion().toString();
			}
		}
		return locComprobante;
	}

	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.LibroBanco generarLibroBancoDiario(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco, Date pFecha)
			throws java.lang.Exception {
		// Obtengo todas los cheques, con orden o sin orden de pago y las boletas de deposito
		// despues tomo los montos y creo las lineas del libro de banco, ya sean depositos o extracciones
		List<Cheque> listaChequesOrdenesPago = Criterio.getInstance(entity, Cheque.class).add(Restriccion.IGUAL("cuentaBancaria", pLibroBanco.getCuentaBancaria()))
				.add(Restriccion.NOT(Restriccion.ESTA_VACIO(("ordenesPago")))).add(Restriccion.IGUAL("fechaPago", pFecha)).list();

		List<Cheque> listaChequesSinOrdenesPago = Criterio.getInstance(entity, Cheque.class).add(Restriccion.IGUAL("cuentaBancaria", pLibroBanco.getCuentaBancaria()))
				.add(Restriccion.ESTA_VACIO("ordenesPago")).add(Restriccion.IGUAL("fechaPago", pFecha)).list();

		List<BoletaDeposito> listaBoletaDeposito = Criterio.getInstance(entity, BoletaDeposito.class).add(Restriccion.IGUAL("cuentaBancaria", pLibroBanco.getCuentaBancaria()))
				.add(Restriccion.IGUAL("fecha", pFecha)).list();

		if(!listaBoletaDeposito.isEmpty()) {
			Double importe = new Double(0);
			LineaLibroBanco locLinea = new LineaLibroBanco();
			locLinea.getBoletasDeposito().addAll(listaBoletaDeposito);
			locLinea.setLibroBanco(pLibroBanco);
			locLinea.setTipo(LineaLibroBanco.Tipo.DEPOSITO);
			locLinea.setFecha(pFecha);
			locLinea.setConciliado(false);
			for(Object obj : listaBoletaDeposito) {
				BoletaDeposito boletaDeposito = (BoletaDeposito) obj;
				importe += boletaDeposito.getImporte();
			}
			locLinea.setImporte(importe);
			pLibroBanco.getLineasLibroBanco().add(locLinea);
		}

		if(!listaChequesSinOrdenesPago.isEmpty()) {
			Double importe = new Double(0);
			LineaLibroBanco locLinea = new LineaLibroBanco();
			// locLinea.getCheques().addAll(listaChequesSinOrdenesPago);
			locLinea.setLibroBanco(pLibroBanco);
			locLinea.setTipo(LineaLibroBanco.Tipo.EXTRACCION);
			locLinea.setFecha(pFecha);
			locLinea.setConciliado(false);
			for(Object obj : listaChequesSinOrdenesPago) {
				Cheque cheque = (Cheque) obj;
				importe += cheque.getImporte();
			}
			locLinea.setImporte(importe);
			pLibroBanco.getLineasLibroBanco().add(locLinea);
		}

		if(!listaChequesOrdenesPago.isEmpty()) {
			Double importe = new Double(0);
			LineaLibroBanco locLinea = new LineaLibroBanco();
			// locLinea.getCheques().addAll(listaChequesOrdenesPago);
			locLinea.setLibroBanco(pLibroBanco);
			locLinea.setTipo(LineaLibroBanco.Tipo.EXTRACCION);
			locLinea.setFecha(pFecha);
			locLinea.setConciliado(false);
			for(Object obj : listaChequesOrdenesPago) {
				Cheque cheque = (Cheque) obj;
				importe += cheque.getImporte();
			}
			locLinea.setImporte(importe);
			pLibroBanco.getLineasLibroBanco().add(locLinea);
		}

		return pLibroBanco;
	}

	public com.trascender.contabilidad.recurso.persistent.Debito addDebito(com.trascender.contabilidad.recurso.persistent.Debito pDebito) throws Exception {
		entity.persist(pDebito);
		return pDebito;
	}

	public com.trascender.contabilidad.recurso.persistent.Debito updateDebito(com.trascender.contabilidad.recurso.persistent.Debito pDebito) throws Exception {
		entity.merge(pDebito);
		return pDebito;
	}

	public com.trascender.contabilidad.recurso.persistent.Debito getDebitoByID(Long pId) throws Exception {
		Debito locDebito = entity.find(Debito.class, pId);
		locDebito.getCuentaBancaria().toString();
		locDebito.toString();
		return locDebito;
	}

	public void deleteDebito(com.trascender.contabilidad.recurso.persistent.Debito pDebito) throws Exception {
		pDebito = entity.merge(pDebito);
		entity.remove(pDebito);
	}

	public List findListaDebito(Date pFechaDesde, Date pFechaHasta, Double pImporteDesde, Double pImporteHasta) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Debito.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.MAYOR("importe", pFechaDesde)).add(Restriccion.MENOR("importe", pFechaHasta));
		List listaDebito = locCriterio.list();
		return listaDebito;
	}

	public CuentaCorriente generarCuentaCorriente(Proveedor pProveedor) {
		CuentaCorriente locCuentaCorriente = new CuentaCorriente();
		Criterio locCriterio = Criterio.getInstance(entity, FacturaProveedor.class).add(Restriccion.IGUAL("proveedor", pProveedor));
		List<Factura> listaFacturas = locCriterio.list();
		for(Factura cadaFactura : listaFacturas) {
			MovCuentaCorrienteIngreso locMovimientoIngreso = new MovCuentaCorrienteIngreso();
			locMovimientoIngreso.setFactura(cadaFactura);
			locMovimientoIngreso.setImporte(cadaFactura.getTotal());
			locMovimientoIngreso.setFecha(cadaFactura.getFechaEmision());
			locCuentaCorriente.addMovimientoCuentaCorriente(locMovimientoIngreso);
		}
		locCriterio = Criterio.getInstance(entity, OrdenPago.class).add(Restriccion.IGUAL("proveedor", pProveedor));
		List<OrdenPago> listaOrdenPago = locCriterio.list();
		for(OrdenPago cadaOrdenPago : listaOrdenPago) {
			MovCuentaCorrienteEgreso locMovimientoEgreso = new MovCuentaCorrienteEgreso();
			locMovimientoEgreso.setDocumentoEgreso(cadaOrdenPago);
			locMovimientoEgreso.setImporte(cadaOrdenPago.getImporte());
			locMovimientoEgreso.setFecha(cadaOrdenPago.getFechaEmision());
			locCuentaCorriente.addMovimientoCuentaCorriente(locMovimientoEgreso);
		}
		locCuentaCorriente.setSaldo();
		return locCuentaCorriente;
	}

}


package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import ar.trascender.criterio.enums.Posicion;

import com.trascender.compras.business.interfaces.BusinessOrdenCompraLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroCondicionCompra;
import com.trascender.compras.recurso.filtros.FiltroOrdenCompra;
import com.trascender.compras.recurso.filtros.FiltroTipoOrdenCompra;
import com.trascender.compras.recurso.filtros.FiltroUsuarioFirmante;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.CondicionCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.TransferenciaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "BusinessOrdenCompraLocal")
public class BusinessOrdenCompraBean implements BusinessOrdenCompraLocal {
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessOrdenCompraBean.serialVersionUID);
		grupo.setNombre(BusinessOrdenCompraBean.NOMBRE);

		Recurso ordenCompra = new Recurso();
		ordenCompra.setIdRecurso(OrdenCompra.serialVersionUID);
		ordenCompra.setNombre("Orden de Compra");
		ordenCompra
				.setAtributosConsultables("Nº", "numero", "Fecha de Emisión", "fechaEmision", Tipo.FECHA, "Proveedor", "proveedor", "Estado", "estado", "Precio", "total", Tipo.MONTO);
		ordenCompra.setClase(OrdenCompra.class);
		grupo.getListaRecursos().add(ordenCompra);

		Recurso tipoOrdenCompra = new Recurso();
		tipoOrdenCompra.setIdRecurso(TipoOrdenCompra.serialVersionUID);
		tipoOrdenCompra.setNombre("Tipo Orden Compra");
		tipoOrdenCompra.setAtributosConsultables("Nombre", "nombre", "Monto Mínimo", "montoMinimo", "Monto Máximo", "montoMaximo");
		tipoOrdenCompra.setClase(TipoOrdenCompra.class);
		// grupo.getListaRecursos().add(tipoOrdenCompra);

		// Recurso condicionCompra = new Recurso();
		// condicionCompra.setIdRecurso(CondicionCompra.serialVersionUID);
		// condicionCompra.setNombre("Condición de Compra");
		// condicionCompra.setAtributosConsultables("Nombre", "nombre", "Cantidad Vencimiento", "cantidadVencimientos", "Días entre Vencimientos",
		// "diasEntreVencimientos");
		// grupo.getListaRecursos().add(condicionCompra);

		Recurso usuarioFirmante = new Recurso();
		usuarioFirmante.setIdRecurso(UsuarioAutorizadorOrdenCompra.serialVersionUID);
		usuarioFirmante.setNombre("Usuario Firmante");
		usuarioFirmante.setAtributosConsultables("Usuario", "usuario", "Imprime Orden Nueva", "imprimeOrdenNueva");
		usuarioFirmante.setClase(UsuarioAutorizadorOrdenCompra.class);
		grupo.getListaRecursos().add(usuarioFirmante);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	/**
	 * 
	 */
	public static final long serialVersionUID = -699116692831266229L;
	public static final String NOMBRE = "COM|Adm de Ordenes de Compra";

	@PersistenceContext
	private EntityManager entity;

	public BusinessOrdenCompraBean() {
		super();
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {

	}

	/**
	 * Agrega un tipo de orden de Compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pTipoOrdenCompra
	 * @return
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra addTipoOrdenCompra(
			com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra pTipoOrdenCompra) throws java.lang.Exception {

		if(pTipoOrdenCompra.getEstado() != TipoOrdenCompra.Estado.ACTIVO) {
			throw new TrascenderComprasException(382);
		}

		if(pTipoOrdenCompra.getMontoMaximo() < 0 || pTipoOrdenCompra.getMontoMinimo() < 0) {
			throw new TrascenderComprasException(383);
		}

		if(pTipoOrdenCompra.getMontoMaximo() <= pTipoOrdenCompra.getMontoMinimo()) {
			throw new TrascenderComprasException(385);
		}

		validarTipoOrdenCompra(pTipoOrdenCompra);
		entity.persist(pTipoOrdenCompra);
		return pTipoOrdenCompra;
	}

	/**
	 * Busca un listado de Tipos de Ordenes de Compra por Nombre Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroTipoOrdenCompra findListaTiposOrdenesCompra(FiltroTipoOrdenCompra filtro) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, TipoOrdenCompra.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.IGUAL("estado", TipoOrdenCompra.Estado.ACTIVO)).add(Orden.ASC("nombre"));

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * metodo deprecado
	 * 
	 * @param pTipoOrdenCompra
	 * @return
	 * @throws java.lang.Exception
	 */
	// @Deprecated
	// private boolean checkRange(TipoOrdenCompra pTipoOrdenCompra)
	// throws java.lang.Exception {
	// // PRE: Se garantiza como entrada que min < max
	// double min = pTipoOrdenCompra.getMontoMinimo();
	// double max = pTipoOrdenCompra.getMontoMaximo();
	// boolean result = false;
	// Session locSession = GestorPersistenciaCompras.getInstance()
	// .getSession();
	// try {
	// Criteria locFinderMin = locSession
	// .createCriteria(TipoOrdenCompra.class)
	// .add(Restrictions.and(Restrictions.and(
	// Restrictions.gt("montoMinimo", min),
	// Restrictions.lt("montoMinimo", max)), Restrictions
	// .ne("idTipoOrdenCompra",
	// pTipoOrdenCompra.getIdTipoOrdenCompra())))
	// .add(Restrictions.eq("estado",
	// TipoOrdenCompra.Estado.ACTIVO));
	// Criteria locFinderMax = locSession
	// .createCriteria(TipoOrdenCompra.class)
	// .add(Restrictions.and(Restrictions.and(
	// Restrictions.gt("montoMaximo", min),
	// Restrictions.lt("montoMaximo", max)), Restrictions
	// .ne("idTipoOrdenCompra",
	// pTipoOrdenCompra.getIdTipoOrdenCompra())))
	// .add(Restrictions.eq("estado",
	// TipoOrdenCompra.Estado.ACTIVO));
	//
	// Criteria locFinderEq = locSession
	// .createCriteria(TipoOrdenCompra.class)
	// .add(Restrictions.and(Restrictions.and(
	// Restrictions.eq("montoMinimo", min),
	// Restrictions.eq("montoMaximo", max)), Restrictions
	// .ne("idTipoOrdenCompra",
	// pTipoOrdenCompra.getIdTipoOrdenCompra())))
	// .add(Restrictions.eq("estado",
	// TipoOrdenCompra.Estado.ACTIVO));
	//
	// Criteria locFinderInclude = locSession
	// .createCriteria(TipoOrdenCompra.class)
	// .add(Restrictions.and(Restrictions.and(
	// Restrictions.le("montoMinimo", min),
	// Restrictions.ge("montoMaximo", max)), Restrictions
	// .ne("idTipoOrdenCompra",
	// pTipoOrdenCompra.getIdTipoOrdenCompra())))
	// .add(Restrictions.eq("estado",
	// TipoOrdenCompra.Estado.ACTIVO));
	//
	// if (locFinderMax.list().size() + locFinderMin.list().size()
	// + locFinderEq.list().size()
	// + locFinderInclude.list().size() > 0)
	// result = true;
	// } catch (Exception e) {
	// throw e;
	// } finally {
	// locSession.close();
	// }
	// return result;
	// }

	/**
	 * Modifica un tipo de Orden de Compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra updateTipoOrdenCompra(
			com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra pTipoOrdenCompra) throws java.lang.Exception {
		// PRE:
		if(pTipoOrdenCompra.getEstado() != TipoOrdenCompra.Estado.ACTIVO)
			throw new TrascenderComprasException(382);
		if(pTipoOrdenCompra.getMontoMaximo() < 0 || pTipoOrdenCompra.getMontoMinimo() < 0)
			throw new TrascenderComprasException(383);
		if(pTipoOrdenCompra.getMontoMaximo() <= pTipoOrdenCompra.getMontoMinimo())
			throw new TrascenderComprasException(385);

		validarTipoOrdenCompra(pTipoOrdenCompra);

		TipoOrdenCompra locTipoOrdenCompra = entity.find(TipoOrdenCompra.class, pTipoOrdenCompra.getIdTipoOrdenCompra());
		locTipoOrdenCompra.toString();
		entity.detach(locTipoOrdenCompra);
		// L�gica para la actualizaci�n de las personas que pueden firmar
		entity.merge(pTipoOrdenCompra);
		return pTipoOrdenCompra;
	}

	private void validarTipoOrdenCompra(TipoOrdenCompra pTipoOrdenCompra) throws Exception {
		Long cantidad = (Long) Criterio.getInstance(entity, TipoOrdenCompra.class).add(Restriccion.LIKE("nombre", pTipoOrdenCompra.getNombre(), false, Posicion.EXACTA))
				.add(Restriccion.DISTINTO("idTipoOrdenCompra", pTipoOrdenCompra.getIdTipoOrdenCompra())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderComprasException(394);
		}
		// for (UsuarioAutorizadorOrdenCompra cadaUsuarioAutorizador : pTipoOrdenCompra.getListaUsuariosAutorizados()) {
		// cadaUsuarioAutorizador.setTipoOrdenCompra(pTipoOrdenCompra);
		// }
	}

	/**
	 * Elimina un tipo de orden de compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteTipoOrdenCompra(TipoOrdenCompra pTipoOrdenCompra) throws java.lang.Exception {
		try {
			this.validarDeleteTipoOrdenCompra(pTipoOrdenCompra);
			entity.remove(entity.merge(pTipoOrdenCompra));
		} catch(Exception e) {
			pTipoOrdenCompra.setEstado(TipoOrdenCompra.Estado.INACTIVO);
			entity.merge(pTipoOrdenCompra);
		}
	}

	private void validarDeleteTipoOrdenCompra(TipoOrdenCompra pTipoOrdenCompra) throws TrascenderComprasException {
		long locCantidadTipoOrden = (Long) Criterio.getInstance(this.entity, OrdenCompra.class).add(Restriccion.IGUAL("tipoOrdenCompra", pTipoOrdenCompra))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(locCantidadTipoOrden > 0) {
			throw new TrascenderComprasException(69);
		}

	}

	/**
	 * Obtiene un tipo de orden de compra por ID Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra findTipoOrdenCompraByID(long pId) throws java.lang.Exception {
		// PRE:
		TipoOrdenCompra locTipoOrdenCompra = entity.find(TipoOrdenCompra.class, pId);
		locTipoOrdenCompra.toString();

		return locTipoOrdenCompra;
	}

	/**
	 * Agrega una condicion de compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.CondicionCompra addCondicionCompra(
			com.trascender.compras.recurso.persistent.suministros.CondicionCompra pCondicionCompra) throws java.lang.Exception {
		validarCondicionCompra(pCondicionCompra);
		pCondicionCompra.setEstado(CondicionCompra.Estado.ACTIVO);
		entity.persist(pCondicionCompra);
		return pCondicionCompra;
	}

	/**
	 * Modifica una Condicion de Compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.CondicionCompra updateCondicionCompra(
			com.trascender.compras.recurso.persistent.suministros.CondicionCompra pCondicionCompra) throws java.lang.Exception {
		if(pCondicionCompra.getEstado() != CondicionCompra.Estado.ACTIVO)
			throw new TrascenderComprasException(400);
		validarCondicionCompra(pCondicionCompra);
		entity.merge(pCondicionCompra);
		return pCondicionCompra;
	}

	private void validarCondicionCompra(CondicionCompra pCondicionCompra) throws Exception {
		Long cantidad = Criterio.getInstance(entity, CondicionCompra.class).add(Restriccion.LIKE("nombre", pCondicionCompra.getNombre(), false))
				.add(Restriccion.DISTINTO("idCondicionCompra", pCondicionCompra.getIdCondicionCompra())).add(Restriccion.IGUAL("estado", CondicionCompra.Estado.ACTIVO))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(405);
		}
	}

	/**
	 * Elimina una condicion de compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteCondicionCompra(com.trascender.compras.recurso.persistent.suministros.CondicionCompra pCondicionCompra) throws java.lang.Exception {
		// controlo si hay ordenes de compra con esta condición para tirar
		// un error mas específico
		// al intentar eliminar

		Long cantidad = Criterio.getInstance(entity, OrdenCompra.class).add(Restriccion.IGUAL("condicionCompra", pCondicionCompra)).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderComprasException(407);
		}
		entity.remove(entity.merge(pCondicionCompra));
	}

	/**
	 * Busca una lista de condiciones de compra por Nombre Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroCondicionCompra findListaCondicionCompra(FiltroCondicionCompra filtro) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CondicionCompra.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.IGUAL("estado", CondicionCompra.Estado.ACTIVO));

		filtro.procesarYListar(locCriterio);

		return filtro;

	}

	private void validarOrdenCompra(OrdenCompra pOrdenCompra) throws TrascenderException {
		if(pOrdenCompra.getProveedor().getEstado().equals(Proveedor.Estado.INACTIVO)) {
			throw new TrascenderComprasException(80);
		}
		this.actualizarLineasSolicitudes(pOrdenCompra);

		// Calculo el monto total
		pOrdenCompra.setTotal();

		for(LineaOrdenCompra cadaLineaOrdenCompra : pOrdenCompra.getListaLineaOrdenCompra()) {
			cadaLineaOrdenCompra.setOrdenCompra(pOrdenCompra);
		}

		if(pOrdenCompra.getListaPagosOrdenCompra().isEmpty()) {
			pOrdenCompra.generarPago("Pago contado", pOrdenCompra.getTotal());
		} else {
			double montoTotalPagos = 0.0d;
			for(PagoOrdenCompra cadaPago : pOrdenCompra.getListaPagosOrdenCompra()) {
				cadaPago.setOrdenCompra(pOrdenCompra);
				montoTotalPagos += cadaPago.getMonto();
			}
			if(montoTotalPagos > pOrdenCompra.getTotal()) {
				throw new TrascenderComprasException(87);
			}
		}
	}

	/**
	 * Agrega una orden de Compra. Valida que las solicitudes de suministro que estan asociadas a las lineas de orden de compra esten es el estado Metodo que ni
	 * idea porque está acá Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public OrdenCompra addOrdenCompra(OrdenCompra pOrdenCompra) throws Exception {

		this.validarOrdenCompra(pOrdenCompra);

		// Para mantener un número correlativo dentro de un año
		Calendar locCalendarInicio = SecurityMgr.getInstance().getFechaActual();
		locCalendarInicio.set(Calendar.MONTH, Calendar.JANUARY);
		locCalendarInicio.set(Calendar.DAY_OF_MONTH, 1);
		Calendar locCalendarFin = SecurityMgr.getInstance().getFechaActual();
		locCalendarFin.set(Calendar.MONTH, Calendar.DECEMBER);
		locCalendarFin.set(Calendar.DAY_OF_MONTH, 31);

		Long cantidad = Criterio.getInstance(entity, OrdenCompra.class).add(Restriccion.MAYOR("fechaEmision", locCalendarInicio.getTime()))
				.add(Restriccion.MENOR("fechaEmision", locCalendarFin.getTime())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		pOrdenCompra.setNumero((cantidad.intValue() + 1));

		// Guardo la orden de compra
		pOrdenCompra.setEstado(OrdenCompra.Estado.NUEVA);
		// No se suben los valores de Auditoria pues ya se subio desde la Contratacion que da de alta esta Orden de compra
		// TrascenderEnverListener.setValoresEnAuditoriaBean(pOrdenCompra);
		pOrdenCompra = entity.merge(pOrdenCompra);
		this.actualizarLineasSolicitudSuministro(pOrdenCompra);
		entity.flush();
		return pOrdenCompra;
	}

	private void actualizarLineasSolicitudSuministro(OrdenCompra pOrdenCompra) {
		for(LineaOrdenCompra cadaLinea : pOrdenCompra.getListaLineaOrdenCompra()) {
			for(LineaSolicitudSuministro cadaLineaSol : cadaLinea.getLineaContratacion().getListaLineasSolicitudSuministro()) {
				cadaLineaSol.setLineaOrdenCompra(cadaLinea);
			}
		}
	}

	/**
	 * Verifica que las lineas de orden de compra esten asociadas a lineas de solicitud de suministro de una Solicitud Aceptada y que los bienes coincidan.
	 * 
	 * @param pOrdenCompra
	 * @throws Exception
	 */
	private void actualizarLineasSolicitudes(OrdenCompra pOrdenCompra) throws TrascenderException {
		for(LineaOrdenCompra locLineaOC : pOrdenCompra.getListaLineaOrdenCompra()) {
			for(LineaSolicitudSuministro cadaLineaSol : locLineaOC.getLineaContratacion().getListaLineasSolicitudSuministro()) {
				// if (!cadaLineaSol.getSolicitudSuministro().getEstado()
				// .equals(SolicitudSuministro.Estado.APROBADA)) {
				// throw new TrascenderComprasException(53);
				// }
				if(!locLineaOC.getBien().equals(cadaLineaSol.getBien())) {
					throw new TrascenderComprasException(76, cadaLineaSol, locLineaOC);
				}
			}
		}
	}

	/**
	 * Busca una lista de Ordenes de compra Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pEstado
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pProveedor
	 * @param pTipoOrdenCompra
	 * @return
	 * @throws java.lang.Exception
	 */
	public FiltroOrdenCompra findListaOrdenCompra(FiltroOrdenCompra filtro) throws java.lang.Exception {
		// Si no existe un orden por Numero, lo agregamos
		if(filtro.getMapaOrden().get("numero") == null) {
			filtro.getMapaOrden().put("numero", FiltroAbstracto.DESC);
		}

		Criterio locCriterio = Criterio.getInstance(entity, OrdenCompra.class)
				.add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.IGUAL("numero", filtro.getNumero()))
				.add(Restriccion.MAYOR("fechaEmision", filtro.getFechaDesde()))
				.add(Restriccion.MENOR("fechaEmision", filtro.getFechaHasta()))
				.add(Restriccion.IGUAL("proveedor", filtro.getProveedor()))
				.setDistinct(true)
				.setModoDebug(true);

		locCriterio.crearAlias("listaLineaOrdenCompra.lineaContratacion.listaLineasSolicitudSuministro", "cadaLineaSolSum")
				.add(Restriccion.IGUAL("cadaLineaSolSum.solicitudSuministro.area.secretaria", filtro.getSecretaria()))
				.add(Restriccion.IGUAL("cadaLineaSolSum.solicitudSuministro.area", filtro.getArea()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, OrdenCompra.serialVersionUID, "idOrdenCompra", filtro.getListaBusquedaPorLogs());

		if(filtro.getBien() != null) {
			locCriterio.add(Restriccion.IGUAL("cadaLineaSolSum.bien", filtro.getBien()));
		} else {
			locCriterio.add(Restriccion.EN("cadaLineaSolicitudSuministro.bien.idBien", filtro.getListaIdBienes()));
		}

		filtro.procesarYListar(locCriterio);

		for(OrdenCompra cadaOrdenCompra : filtro.getListaResultados()) {
			cadaOrdenCompra.getTotal();
			if(cadaOrdenCompra.getCondicionCompra() != null) {
				cadaOrdenCompra.getCondicionCompra().toString();
			}
			cadaOrdenCompra.getProveedor().toString();
			cadaOrdenCompra.getListaFirmaPermisos().size();
			cadaOrdenCompra.toString();
		}

		return filtro;
	}

	/**
	 * Obtiene una orden de compra por ID Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.OrdenCompra findOrdenCompraByID(long pID) throws java.lang.Exception {
		OrdenCompra locOrdenCompra = entity.find(OrdenCompra.class, pID);
		for(AutorizacionOrdenCompra aoc : locOrdenCompra.getListaFirmaPermisos()) {
			aoc.toString();
			aoc.getFirmaPermiso().toString();
		}
		locOrdenCompra.getListaPagosOrdenCompra().size();

		if(locOrdenCompra.getCondicionCompra() != null) {
			locOrdenCompra.getCondicionCompra().toString();
		}
		locOrdenCompra.getProveedor().toString();
		locOrdenCompra.getProveedor().getListaBienes();
		locOrdenCompra.getProveedor().getDomicilio().toString();
		locOrdenCompra.toString();
		for(LineaOrdenCompra cadaLinea : locOrdenCompra.getListaLineaOrdenCompra()) {
			// Por algunas ordenes de compra viejas que no tienen
			// la cuenta asociada directamente.
			if(cadaLinea.getCuentaRfr() != null) {
				cadaLinea.getCuentaRfr().toString();
			}
			if(cadaLinea.getLineaMovimientoMercaderia() != null) {
				cadaLinea.getLineaMovimientoMercaderia().toString();
				cadaLinea.getLineaMovimientoMercaderia().getStock().toString();
			}
			for(LineaSolicitudSuministro cadaLineaSS : cadaLinea.getLineaContratacion().getListaLineasSolicitudSuministro()) {
				cadaLineaSS.toString();
				if(cadaLineaSS.getCuenta() != null)
					cadaLineaSS.getCuenta().toString();
			}
		}

		for(TransferenciaOrdenCompra cadaTrasnferecncia : locOrdenCompra.getListaTransferencias()) {
			cadaTrasnferecncia.toString();
		}
		locOrdenCompra.getListaLogsAuditoria().size();
		return locOrdenCompra;
	}

	/**
	 * Modifico una orden de compra. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.OrdenCompra updateOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra)
			throws java.lang.Exception {

		validarOrdenCompra(pOrdenCompra);
		if(pOrdenCompra.getEstado() != OrdenCompra.Estado.APROBADA && pOrdenCompra.getEstado() != OrdenCompra.Estado.NUEVA) {
			throw new TrascenderComprasException(72);
		}
		// Persisto las LineasOrdenCompra no persistidas, pues quiere actualizar
		// las lineas solicitud suministro y al no existir en la base
		// lanza exepcion.
		for(LineaOrdenCompra locLinea : pOrdenCompra.getListaLineaOrdenCompra()) {
			if(locLinea.getIdLineaOrdenCompra() == -1) {
				entity.persist(locLinea);
			}
		}
		// List<LineaOrdenCompra> lineasBorradas =
		// getLineasBorradas(pOrdenCompra);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pOrdenCompra);
		pOrdenCompra = entity.merge(pOrdenCompra);
		this.actualizarLineasSolicitudes(pOrdenCompra);
		entity.flush();

		// for (LineaOrdenCompra cadaLineaBorrada : lineasBorradas) {
		// for (LineaSolicitudSuministro cadaLineaSolSum : cadaLineaBorrada
		// .getListaLineasSolicitudSuministro()) {
		// cadaLineaSolSum.setLineaOrdenCompra(null);
		// entity.merge(cadaLineaSolSum);
		// }
		// }
		return pOrdenCompra;
	}

	private List<LineaOrdenCompra> getLineasBorradas(OrdenCompra pOrdenCompra) {
		List<LineaOrdenCompra> listaResultado = new ArrayList<LineaOrdenCompra>();
		OrdenCompra ordenVieja = Criterio.getInstance(entity, OrdenCompra.class).add(Restriccion.IGUAL("idOrdenCompra", pOrdenCompra.getIdOrdenCompra())).uniqueResult();
		for(LineaOrdenCompra lineaVieja : ordenVieja.getListaLineaOrdenCompra()) {
			if(!pOrdenCompra.getListaLineaOrdenCompra().contains(lineaVieja)) {
				listaResultado.add(lineaVieja);
			}
		}
		entity.detach(ordenVieja);
		return listaResultado;
	}

	/**
	 * Firma la orden compra con el usuario actual, que fue seteado en el system.
	 * 
	 * Deprecated: por ahora no hay firmas de ordenes de compra.
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public OrdenCompra firmarOrdenCompra(OrdenCompra pOrdenCompra, FirmaPermiso pFirmaPermiso) throws java.lang.Exception {
		OrdenCompra.Estado locEstado = Criterio.getInstance(entity, OrdenCompra.class).add(Restriccion.IGUAL("idOrdenCompra", pOrdenCompra.getIdOrdenCompra()))
				.setProyeccion(Proyeccion.PROP("estado")).uniqueResult();

		if(!pOrdenCompra.getEstado().equals(OrdenCompra.Estado.NUEVA)) {
			throw new TrascenderComprasException(85);
		}

		Criterio locCriterio = Criterio.getInstance(entity, UsuarioAutorizadorOrdenCompra.class).add(Restriccion.IGUAL("usuario", pFirmaPermiso.getUsuario()))
				.add(Restriccion.IGUAL("eliminado", false)).setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();

		if(cantidad == 0) {
			throw new TrascenderComprasException(420);
		}

		if(pOrdenCompra.yaFirmo(pFirmaPermiso.getUsuario())) {
			throw new TrascenderComprasException(44);
		}

		pOrdenCompra.addFirma(pFirmaPermiso);

		locCriterio = Criterio.getInstance(this.entity, UsuarioAutorizadorOrdenCompra.class).add(Restriccion.IGUAL("eliminado", false)).setProyeccion(Proyeccion.COUNT());

		cantidad = locCriterio.uniqueResult();

		if(cantidad.equals(new Long(pOrdenCompra.getListaFirmaPermisos().size()))) {
			pOrdenCompra.setEstado(OrdenCompra.Estado.APROBADA);
		}
		TrascenderEnverListener.setValoresEnAuditoriaBean(pOrdenCompra);
		entity.merge(pOrdenCompra);
		entity.flush();
		return pOrdenCompra;
	}

	/**
	 * La Ordenes de compra se finalizan como ANULADAS, RESCINDIDAS
	 * 
	 * @param pOrdenCompra
	 */
	public void finalizarOrdenCompra(OrdenCompra pOrdenCompra) throws TrascenderException {
		if(!pOrdenCompra.getEstado().equals(OrdenCompra.Estado.ANULADA) && !pOrdenCompra.getEstado().equals(OrdenCompra.Estado.RESCINDIDA)) {
			throw new TrascenderComprasException(81);
		}

		// Se desasocian las Lineas de Solicitud Suministro asociadas a esta
		// Orden de Compra.
		for(LineaOrdenCompra cadaLineaOrdenCompra : pOrdenCompra.getListaLineaOrdenCompra()) {
			// for (LineaSolicitudSuministro cadaLineaSolicitud :
			// cadaLineaOrdenCompra
			// .getListaLineasSolicitudSuministro()) {
			// cadaLineaSolicitud.setLineaOrdenCompra(null);
			// }
		}
		entity.merge(pOrdenCompra);
	}

	/**
	 * Busca un tipo de orden de compra por monto Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra findTipoOrdenCompraByMonto(java.lang.Double pMonto) throws java.lang.Exception {
		return Criterio.getInstance(entity, TipoOrdenCompra.class).add(Restriccion.IGUAL("estado", TipoOrdenCompra.Estado.ACTIVO))
				.add(Restriccion.AND(Restriccion.MAYOR("montoMinimo", pMonto), Restriccion.MENOR("montoMinimo", pMonto))).uniqueResult();
	}

	@SuppressWarnings("unused")
	private void validarSolicitudSuministroConOrdenDeCompra(LineaSolicitudSuministro pLineaSolicitudSuministro) throws Exception {
		SolicitudSuministro locSolicitudSuministro = entity.find(SolicitudSuministro.class, pLineaSolicitudSuministro.getSolicitudSuministro().getIdSolicitudSuministro());
		// locSolicitudSuministro.verificarLineasSolicitud();
		entity.merge(locSolicitudSuministro);
	}

	public void transferirOrdenCompra(OrdenCompra pOrdenCompra, Proveedor pNuevoProveedor, String pComentario) throws Exception {
		if(pOrdenCompra == null || pNuevoProveedor == null) {
			throw new TrascenderComprasException(82);
		}

		if(pOrdenCompra.getProveedor().getIdProveedor() == pNuevoProveedor.getIdProveedor()) {
			throw new TrascenderComprasException(83);
		}

		if(pOrdenCompra.getEstado() != OrdenCompra.Estado.APROBADA) {
			throw new TrascenderComprasException(84);
		}

		pOrdenCompra.setProveedor(pNuevoProveedor);
		this.validarOrdenCompra(pOrdenCompra);

		pOrdenCompra.getListaTransferencias().add(new TransferenciaOrdenCompra(pOrdenCompra, pComentario));

		TrascenderEnverListener.setValoresEnAuditoriaBean(pOrdenCompra);
		this.entity.merge(pOrdenCompra);
		this.entity.flush();
	}

	public UsuarioAutorizadorOrdenCompra addUsuarioAutorizadorOrdenCompra(UsuarioAutorizadorOrdenCompra pUsuarioAutorizador) throws Exception {

		validarUsuarioFirmante(pUsuarioAutorizador);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pUsuarioAutorizador);

		this.entity.persist(pUsuarioAutorizador);
		this.entity.flush();

		return pUsuarioAutorizador;
	}

	public UsuarioAutorizadorOrdenCompra updateUsuarioAutorizadorOrdenCompra(UsuarioAutorizadorOrdenCompra pUsuarioAutorizador) throws Exception {
		validarUsuarioFirmante(pUsuarioAutorizador);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pUsuarioAutorizador);
		this.entity.merge(pUsuarioAutorizador);
		this.entity.flush();
		return pUsuarioAutorizador;
	}

	public void deleteUsuarioAutorizadorOrdenCompra(UsuarioAutorizadorOrdenCompra pUsuarioAutorizador) throws Exception {
		pUsuarioAutorizador.setEliminado(true);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pUsuarioAutorizador);
		this.entity.merge(pUsuarioAutorizador);
	}

	private void validarUsuarioFirmante(UsuarioAutorizadorOrdenCompra pUsuarioFirmante) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(entity, UsuarioAutorizadorOrdenCompra.class).add(Restriccion.IGUAL("usuario", pUsuarioFirmante.getUsuario()))
				.add(Restriccion.DISTINTO("idUsuarioAutorizador", pUsuarioFirmante.getIdUsuarioAutorizador())).add(Restriccion.IGUAL("eliminado", false))
				.setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(88);
		}
	}

	public FiltroUsuarioFirmante findListaUsuariosFirmantes(FiltroUsuarioFirmante pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entity, UsuarioAutorizadorOrdenCompra.class).add(Restriccion.IGUAL("eliminado", false));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, UsuarioAutorizadorOrdenCompra.serialVersionUID, "idUsuarioAutorizador", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public UsuarioAutorizadorOrdenCompra getUsuarioFirmantePorId(long pIdUsuarioFirmante) {
		UsuarioAutorizadorOrdenCompra locUsuarioFirmante = entity.find(UsuarioAutorizadorOrdenCompra.class, pIdUsuarioFirmante);
		if(locUsuarioFirmante != null) {
			locUsuarioFirmante.getListaLogsAuditoria().size();
		}
		return locUsuarioFirmante;
	}
}

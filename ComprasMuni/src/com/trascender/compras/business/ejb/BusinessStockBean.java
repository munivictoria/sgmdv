
package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
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

import com.trascender.compras.business.interfaces.BusinessSolicitudSuministroLocal;
import com.trascender.compras.business.interfaces.BusinessStockLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.exception.TrascenderInventarioException;
import com.trascender.compras.recurso.filtros.FiltroArticulo;
import com.trascender.compras.recurso.filtros.FiltroDeposito;
import com.trascender.compras.recurso.filtros.FiltroMovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia.Tipo;
import com.trascender.compras.recurso.persistent.inventario.PaseArticulo;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "BusinessStockLocal")
public class BusinessStockBean implements BusinessStockLocal {

	private static final long serialVersionUID = 4826603468085460470L;
	private static final String NOMBRE = "DEP|Adm de Deposito";
	@EJB
	private BusinessSolicitudSuministroLocal locSolicitudSuministroEjb;

	@PersistenceContext
	private EntityManager entity;

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setNombre(BusinessStockBean.NOMBRE);
		grupoRecursos.setId(BusinessStockBean.serialVersionUID);

		// Recurso stock = new Recurso();
		// stock.setNombre("Stock");
		// stock.setIdRecurso(Stock.serialVersionUID);
		// stock.setAtributosConsultables("Bien", "bien", "Cantidad", "cantidad", "Depósito","deposito");
		// grupoRecursos.getListaRecursos().add(stock);

		Recurso deposito = new Recurso();
		deposito.setNombre("Deposito");
		deposito.setIdRecurso(Deposito.serialVersionUID);
		deposito.setAtributosConsultables("Nombre", "nombre", "Área", "area");
		deposito.setClase(Deposito.class);
		grupoRecursos.getListaRecursos().add(deposito);

		Recurso movimientoMercaderia = new Recurso();
		movimientoMercaderia.setNombre("Movimiento de Mercaderia");
		movimientoMercaderia.setIdRecurso(MovimientoDeMercaderia.serialVersionUID);
		movimientoMercaderia.setAtributosConsultables("Depósito", "deposito", "Tipo", "tipo", "Motivo", "motivo", "Fecha", "fechaDate", AtributoConsultable.Tipo.FECHA);
		movimientoMercaderia.setClase(MovimientoDeMercaderia.class);
		grupoRecursos.getListaRecursos().add(movimientoMercaderia);

		Recurso articulo = new Recurso();
		articulo.setNombre("Articulo");
		articulo.setIdRecurso(Articulo.serialVersionUID);
		articulo.setAtributosConsultables("Código", "codigo", "Nombre", "nombre", "Fecha Compra", "fechaCompra", AtributoConsultable.Tipo.FECHA, "Fecha de Entrada en Servicio",
				"fechaPuestaServicio", AtributoConsultable.Tipo.FECHA, "Estado", "estadoContable");
		articulo.setClase(Articulo.class);
		grupoRecursos.getListaRecursos().add(articulo);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {

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
	 * Agrego un Stock Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public Stock addStock(Stock pStock) throws Exception {
		this.validarStock(pStock);
		this.entity.persist(pStock);
		return pStock;
	}

	/**
	 * Modifico un Stock Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public Stock updateStock(Stock pStock) throws Exception {
		this.validarStock(pStock);
		this.entity.merge(pStock);
		return pStock;
	}

	private void validarStock(Stock pStock) throws TrascenderComprasException {
		Long cantidad = Criterio.getInstance(entity, Stock.class).add(Restriccion.IGUAL("bien", pStock.getBien())).add(Restriccion.IGUAL("deposito", pStock.getDeposito()))
				.add(Restriccion.DISTINTO("idStock", pStock.getIdStock())).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(120);
		}
	}

	/**
	 * Obtiene un Stock por ID Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Stock getStockByID(long pId) throws Exception {
		Stock locStock = entity.find(Stock.class, pId);
		locStock.toString();
		return locStock;
	}

	/**
	 * Elimina un stock. Chequea que el stock no se haya usado durante todo el año, de ser así lo elimina.
	 * 
	 * @param pStock
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteStock(Stock pStock) throws Exception {
		if(pStock.getCantidad() != 0) {
			throw new TrascenderInventarioException(122);
		}
		Long cantidad = Criterio.getInstance(entity, MovimientoDeMercaderia.class).add(Restriccion.OR(Restriccion.IGUAL("stock", pStock), Restriccion.IGUAL("stockDestino", pStock)))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderInventarioException(123);
		}
		entity.remove(pStock);
	}

	/**
	 * Busca una lista de Stock Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pBien
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Stock> findListaStock(Bien pBien, Deposito pDeposito) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Stock.class).add(Restriccion.IGUAL("bien", pBien)).add(Restriccion.IGUAL("deposito", pDeposito));
		List<Stock> locListaStock = locCriterio.list();
		for(Stock locStock : locListaStock) {
			locStock.getBien().toString();
			locStock.getDeposito().toString();
		}
		return locListaStock;
	}

	/**
	 * Agrega un deposito Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	public Deposito addDeposito(Deposito pDeposito) throws Exception {
		this.validarDeposito(pDeposito);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pDeposito);

		entity.persist(pDeposito);
		entity.flush();

		return pDeposito;
	}

	/**
	 * Valida si no existe un deposito con los datos del deposito a agregar
	 * 
	 * @param pDeposito
	 * @throws TrascenderInventarioException
	 */
	private void validarDeposito(Deposito pDeposito) throws TrascenderInventarioException {
		Long cantidad = Criterio.getInstance(entity, Deposito.class).add(Restriccion.IGUAL("area", pDeposito.getArea())).add(Restriccion.LIKE("nombre", pDeposito.getNombre(), false))
				.add(Restriccion.DISTINTO("idDeposito", pDeposito.getIdDeposito())).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderInventarioException(150);
		}

		for(Stock cadaStock : pDeposito.getListaStock()) {
			cadaStock.setDeposito(pDeposito);
		}
	}

	/**
	 * Modifica un deposito Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	public Deposito updateDeposito(Deposito pDeposito) throws Exception {
		this.validarDeposito(pDeposito);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pDeposito);

		entity.merge(pDeposito);

		this.entity.flush();

		return pDeposito;
	}

	/**
	 * Obtiene un deposito por ID Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Deposito getDepositoByID(long pId) throws Exception {
		Deposito locDeposito = entity.find(Deposito.class, pId);
		if(locDeposito != null) {
			locDeposito.getArea().toString();
			locDeposito.getListaLogsAuditoria().size();
			locDeposito.getDomicilio().toString();
			for(Stock cadaStock : locDeposito.getListaStock()) {
				cadaStock.toString();
			}
		}
		return locDeposito;
	}

	/**
	 * Elimina un deposito Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pDeposito
	 * @throws Exception
	 */
	public void deleteDeposito(Deposito pDeposito) throws Exception {
		List locResult = Criterio.getInstance(entity, Stock.class).add(Restriccion.IGUAL("deposito", pDeposito)).list();
		if(!locResult.isEmpty()) {
			throw new TrascenderInventarioException(151);
		}
		entity.remove(entity.merge(pDeposito));
	}

	/**
	 * Busca una lista de depositos Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pNombre
	 * @param pArea
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public FiltroDeposito findListaDeposito(FiltroDeposito pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Deposito.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre())).add(Restriccion.IGUAL("area", pFiltro.getArea()))
				.add(Restriccion.EN("area", pFiltro.getListaAreas()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Deposito.serialVersionUID, "idDeposito", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(Deposito deposito : pFiltro.getListaResultados()) {
			deposito.getArea().toString();
			deposito.getDomicilio().toString();
			deposito.getListaStock().size();
		}
		return pFiltro;
	}

	/**
	 * Agrega un movimiento de mercaderia. Si es de Tipo Egreso o Movimiento busca el Stock correspondiente. De no encontrarlo tira excepcion Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia addMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception {
		validarMovimientoDeMercaderia(pMovimientoDeMercaderia);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pMovimientoDeMercaderia);

		entity.persist(pMovimientoDeMercaderia);
		entity.flush();

		actualizarMovimientoDeMercaderia(pMovimientoDeMercaderia);
		return pMovimientoDeMercaderia;
	}

	public void validarMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception {
		// valido que la cantidad no sea superior a la disponible en el stock de
		// origen, para el caso de EGRESO y MOVIMIENTO
		pMovimientoDeMercaderia.validarCantidades();

		// valido, en caso de ser Tipo.MOVIMIENTO, que los bienes del stock
		// origen y destino sean los mismos
		pMovimientoDeMercaderia.validarTiposStock();

		pMovimientoDeMercaderia.validarCantidadesPrevias();

	}

	/**
	 * Modifica un movimiento de mercaderia Si el movimiento no está en el estado Pendiente, tira excepcion. Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia updateMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception {
		// if (pMovimientoDeMercaderia.getEstado() != MovimientoDeMercaderia.Estado.PENDIENTE) {
		// throw new TrascenderInventarioException(141);
		// }
		validarMovimientoDeMercaderia(pMovimientoDeMercaderia);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pMovimientoDeMercaderia);

		entity.merge(pMovimientoDeMercaderia);

		this.entity.flush();

		return pMovimientoDeMercaderia;
	}

	/**
	 * Borra un movimiento de mercaderia, el movimiento está en estado pendiente lo borra fisicamente, sino lo borrá logicamente. Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @throws Exception
	 */
	public void deleteMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception {
		// if (pMovimientoDeMercaderia.getEstado() != MovimientoDeMercaderia.Estado.PENDIENTE) {
		// throw new TrascenderInventarioException(142);
		// }
		entity.remove(this.entity.merge(pMovimientoDeMercaderia));
	}

	/**
	 * Actualiza los movimientos de mercaderia. El movimiento pasa a estar en estado recibido Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia actualizarMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception {

		// Si es de Ingreso, sumo las cantidades al Stock.
		if(pMovimientoDeMercaderia.getTipo() == Tipo.INGRESO) {
			for(LineaMovimientoMercaderia cadaLineaMovimiento : pMovimientoDeMercaderia.getListaLineasMovimientoMercaderia()) {
				Stock locStock = cadaLineaMovimiento.getStock();
				sumarRestarStock(locStock, cadaLineaMovimiento.getCantidad());
			}
		}

		// Si es de egreso, y tiene stock origen, entonces
		// actualiza stock origen
		if(pMovimientoDeMercaderia.getTipo() == Tipo.EGRESO) {
			for(LineaMovimientoMercaderia cadaLineaMovimiento : pMovimientoDeMercaderia.getListaLineasMovimientoMercaderia()) {
				if(cadaLineaMovimiento.getStock() != null) {
					sumarRestarStock(cadaLineaMovimiento.getStock(), -cadaLineaMovimiento.getCantidad());
				}
			}
		}

		// Si es de Movimiento, se suman y quitan de los stocks.
		if(pMovimientoDeMercaderia.getTipo() == Tipo.MOVIMIENTO) {
			for(LineaMovimientoMercaderia cadaLineaMovimiento : pMovimientoDeMercaderia.getListaLineasMovimientoMercaderia()) {
				if(cadaLineaMovimiento.getStock() != null && cadaLineaMovimiento.getStockDestino() != null) {
					sumarRestarStock(cadaLineaMovimiento.getStock(), -cadaLineaMovimiento.getCantidad());
					sumarRestarStock(cadaLineaMovimiento.getStockDestino(), cadaLineaMovimiento.getCantidad());
				}
			}
		}

		entity.merge(pMovimientoDeMercaderia);

		return pMovimientoDeMercaderia;
	}

	private void sumarRestarStock(Stock pStock, Double cantidad) {
		Stock locStock = entity.find(Stock.class, pStock.getIdStock());
		Double locCantidad = locStock.getCantidad();
		locCantidad += cantidad;
		locStock.setCantidad(locCantidad);
		entity.merge(locStock);
	}

	/**
	 * Obtiene un movimiento de mercaderia por ID Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia getMovimientoDeMercaderiaByID(long pId) throws Exception {
		MovimientoDeMercaderia locMovimientoDeMercaderia = entity.find(MovimientoDeMercaderia.class, pId);
		if(locMovimientoDeMercaderia != null) {
			locMovimientoDeMercaderia.getListaLogsAuditoria().size();
			for(LineaMovimientoMercaderia cadaLinea : locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia()) {
				if(cadaLinea.getLineaSolicitudSuministro() != null) {
					cadaLinea.getLineaSolicitudSuministro().toString();
					cadaLinea.getLineaSolicitudSuministro().getListaLineasMovimientosMercaderia().size();
				}
				if(cadaLinea.getLineaOrdenCompra() != null) {
					cadaLinea.getLineaOrdenCompra().toString();
				}
				if(cadaLinea.getStock() != null) {
					cadaLinea.getStock().toString();
				}
				if(cadaLinea.getStockDestino() != null) {
					cadaLinea.getStockDestino().toString();
				}
			}
		}

		return locMovimientoDeMercaderia;
	}

	/**
	 * Busca una lista de Movimientos de mercaderia Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @param pArea
	 * @param pUsuario
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public FiltroMovimientoDeMercaderia findListaMovimientoDeMercaderia(FiltroMovimientoDeMercaderia pFiltro) throws Exception {
		Calendar fechaDesde = null;
		Calendar fechaHasta = null;
		if(pFiltro.getFechaDesde() != null) {
			fechaDesde = Calendar.getInstance();
			fechaDesde.setTime(pFiltro.getFechaDesde());
		}

		if(pFiltro.getFechaHasta() != null) {
			fechaHasta = Calendar.getInstance();
			fechaHasta.setTime(pFiltro.getFechaHasta());
		}

		Criterio locCriterio = Criterio.getInstance(entity, MovimientoDeMercaderia.class).add(Restriccion.IGUAL("deposito", pFiltro.getDepositoOrigen()))
				.add(Restriccion.IGUAL("deposito.area", pFiltro.getArea())).add(Restriccion.MAYOR("fecha", fechaDesde)).add(Restriccion.MENOR("fecha", fechaHasta))
				.add(Restriccion.IGUAL("tipo", pFiltro.getTipo())).add(Restriccion.IGUAL("usuario", pFiltro.getUsuario()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, MovimientoDeMercaderia.serialVersionUID, "idMovimientoDeMercaderia", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(MovimientoDeMercaderia cadaMovimiento : pFiltro.getListaResultados()) {
			cadaMovimiento.getListaLineasMovimientoMercaderia().size();
		}

		return pFiltro;
	}

	/**
	 * Valida el nivel del stock con el punto de reposicion. Si cae por debajo, genera una solicitud de suministro.
	 * 
	 * @param pStock
	 * @param pUsuario
	 * @throws Exception
	 */
	private void validarPuntoDeReposicion(Stock pStock, Usuario pUsuario) throws Exception {
		if(pStock.getCantidadAComprar() != null && !pStock.getCantidadAComprar().equals(0D) && pStock.getCantidadLimite() != null && !pStock.getCantidadLimite().equals(0D)) {
			if(pStock.getCantidad() <= pStock.getCantidadLimite()) {
				SolicitudSuministro locSolicitudSuministro = new SolicitudSuministro();
				locSolicitudSuministro.setArea(pStock.getDeposito().getArea());
				locSolicitudSuministro.setUsuario(pUsuario);
				locSolicitudSuministro.setFechaEmision(SecurityMgr.getInstance().getFechaActual().getTime());
				locSolicitudSuministro.setDescripcion("Generación automática por modulo de Inventario");

				LineaSolicitudSuministro locLinea = new LineaSolicitudSuministro();
				locLinea.setBien(pStock.getBien());
				locLinea.setCantidad(pStock.getCantidadAComprar());

				locLinea.setSolicitudSuministro(locSolicitudSuministro);
				locSolicitudSuministro.getListaLineaSolSuministro().add(locLinea);
				this.locSolicitudSuministroEjb.addSolicitudSuministro(locSolicitudSuministro);
			}
		}
	}

	public Articulo addArticulo(Articulo pArticulo) throws Exception {
		validarArticulo(pArticulo);
		entity.persist(pArticulo);
		return pArticulo;
	}

	public Articulo updateArticulo(Articulo pArticulo) throws Exception {
		validarArticulo(pArticulo);
		entity.merge(pArticulo);
		return pArticulo;
	}

	private void validarArticulo(Articulo pArticulo) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Articulo.class).add(Restriccion.LIKE("codigo", pArticulo.getCodigo(), false))
				.add(Restriccion.DISTINTO("idArticulo", pArticulo.getIdArticulo())).setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if(cantidad.intValue() > 0) {
			throw new TrascenderComprasException(551);
		}
	}

	public void deleteArticulo(Articulo pArticulo) throws Exception {
	}

	public FiltroArticulo findListaArticulo(FiltroArticulo pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Articulo.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre())).add(Restriccion.ILIKE("codigo", pFiltro.getCodigo()))
				.add(Restriccion.IGUAL("fechaCompra", pFiltro.getFechaCompra())).add(Restriccion.IGUAL("fechaPuestaServicio", pFiltro.getFechaEntradaServicio()))
				.add(Restriccion.IGUAL("estadoContable", pFiltro.getEstadoContable())).add(Restriccion.IGUAL("area", pFiltro.getArea()));

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public Articulo getArticuloPorId(long pIdArticulo) throws Exception {
		Articulo locArticulo = entity.find(Articulo.class, pIdArticulo);
		if(locArticulo != null) {
			if(locArticulo.getInformacionTecnica() != null) {
				locArticulo.getInformacionTecnica().toString();
			}
			for(PaseArticulo cadaPaseArticulo : locArticulo.getListaPaseArticulo()) {
				cadaPaseArticulo.toString();
			}
		}
		return locArticulo;
	}
}

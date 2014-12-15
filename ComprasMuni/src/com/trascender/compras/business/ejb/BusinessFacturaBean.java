
package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroFacturaContrato;
import com.trascender.compras.recurso.filtros.FiltroFacturaProveedor;
import com.trascender.compras.recurso.filtros.FiltroFacturaServicio;
import com.trascender.compras.recurso.filtros.FiltroFacturaSubsidio;
import com.trascender.compras.recurso.filtros.FiltroLiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.LineaFacturaBien;
import com.trascender.compras.recurso.persistent.suministros.LineaFacturaLineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaFacturaPagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro.Estado;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "BusinessFacturaLocal")
public class BusinessFacturaBean implements BusinessFacturaLocal {

	static {
		Grupo grupoFactura = new Grupo();
		grupoFactura.setId(BusinessFacturaBean.serialVersionUID);
		grupoFactura.setNombre(BusinessFacturaBean.NOMBRE);

		Recurso factura = new Recurso();
		factura.setIdRecurso(FacturaProveedor.serialVersionUID);
		factura.setNombre("Factura de Proveedores");
		factura.setAtributosConsultables("Fecha de Emisión", "fechaEmision", Tipo.FECHA, "Tipo de Factura", "tipoFactura", "Proveedor", "proveedor", "Estado", "estado", "Monto",
				"total", Tipo.MONTO);
		factura.setClase(FacturaProveedor.class);
		grupoFactura.getListaRecursos().add(factura);

		Recurso facturaSubsidio = new Recurso();
		facturaSubsidio.setIdRecurso(FacturaSubsidio.serialVersionUID);
		facturaSubsidio.setNombre("Factura por Subsidio");
		facturaSubsidio.setAtributosConsultables("Fecha de Emisión", "fechaEmision", Tipo.FECHA, "Tipo de Factura", "tipoFactura", "Digesto Municipal", "digestoMunicipal", "Proveedor",
				"proveedor", "Estado", "estado", "Monto", "total", Tipo.MONTO);
		facturaSubsidio.setClase(FacturaSubsidio.class);
		grupoFactura.getListaRecursos().add(facturaSubsidio);

		Recurso facturaContrato = new Recurso();
		facturaContrato.setIdRecurso(FacturaContrato.serialVersionUID);
		facturaContrato.setNombre("Factura por Contrato");
		facturaContrato.setAtributosConsultables("Fecha de Emisión", "fechaEmision", Tipo.FECHA, "Tipo de Factura", "tipoFactura", "Proveedor", "proveedor", "Estado", "estado",
				"Monto", "total", Tipo.MONTO);
		facturaContrato.setClase(FacturaContrato.class);
		grupoFactura.getListaRecursos().add(facturaContrato);

		Recurso facturaServicio = new Recurso();
		facturaServicio.setIdRecurso(FacturaServicio.serialVersionUID);
		facturaServicio.setNombre("Factura por Servicio");
		facturaServicio.setAtributosConsultables("Fecha de Emisión", "fechaEmision", Tipo.FECHA, "Tipo de Factura", "tipoFactura", "Proveedor", "proveedor", "Estado", "estado",
				"Monto", "total", Tipo.MONTO);
		facturaServicio.setClase(FacturaServicio.class);
		grupoFactura.getListaRecursos().add(facturaServicio);

		Recurso facturaTodas = new Recurso();
		facturaTodas.setIdRecurso(Factura.serialVersionUID);
		facturaTodas.setNombre("Factura");
		facturaTodas.setClase(Factura.class);
		grupoFactura.getListaRecursos().add(facturaTodas);

		Recurso liquidacionCompra = new Recurso();
		liquidacionCompra.setIdRecurso(LiquidacionCompra.serialVersionUID);
		liquidacionCompra.setNombre("Liquidación de Compra");
		liquidacionCompra.setAtributosConsultables("Número", "numero", "Fecha", "fecha", Tipo.FECHA, "Nº de Facturas", "stringListaFacturas", Tipo.TEXTO_LARGO);
		liquidacionCompra.setClase(LiquidacionCompra.class);
		grupoFactura.getListaRecursos().add(liquidacionCompra);

		SecurityMgr.getInstance().addGrupo(grupoFactura);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699336445027164145L;
	private static final String NOMBRE = "COM|Adm de Facturas";

	@PersistenceContext
	private EntityManager entity;

	public BusinessFacturaBean() {
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
	 * Agrego una factura. Si las lineas de facturas tienen cuentas contables la Factura pasa a estado Procesada, de no ser así quedan con estado Creada.
	 * Verifica que todas las ordenes de compra esten Comprometidas, de no ser así tira excepcion. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pFactura
	 * @return
	 * @throws java.lang.Exception
	 */
	// public
	// com.trascender.compras.recurso.persistent.suministros.FacturaProveedor
	// addFactura(
	// com.trascender.compras.recurso.persistent.suministros.FacturaProveedor
	// pFactura)
	// throws java.lang.Exception {
	// // PRE:
	// this.validarFactura(pFactura);
	// System.out.println("Lista de ordenes de compra");
	// System.out.println(pFactura.getListaOrdenesDeCompra());
	// if (pFactura.getListaOrdenesDeCompra() != null){
	// for(OrdenCompra ordenCompra : pFactura.getListaOrdenesDeCompra()){
	// ordenCompra.setFactura(pFactura);
	// if (ordenCompra.getEstado() == OrdenCompra.Estado.APROBADA){
	// ordenCompra.setEstado(OrdenCompra.Estado.CUMPLIDA);
	// } else {
	// throw new TrascenderComprasException(77);
	// }
	// }
	// }
	//
	// Double totalFactura= new Double(0);
	// for(LineaFactura locLFP: pFactura.getListaLineaFactura()){
	// locLFP.setFactura(pFactura);
	// totalFactura += locLFP.getTotal();
	// }
	//
	// pFactura.setTotal(totalFactura);
	// recibirLineasSolSum(pFactura);
	// this.entity.merge(pFactura);
	// return pFactura;
	// }

	public com.trascender.compras.recurso.persistent.suministros.FacturaProveedor addFactura(com.trascender.compras.recurso.persistent.suministros.FacturaProveedor pFactura)
			throws java.lang.Exception {
		// PRE:
		this.validarFactura(pFactura);
		this.validarNumeroFactura(pFactura);
		System.out.println("Lista de ordenes de compra");
		System.out.println(pFactura.getListaLineaFactura());

		for(LineaFactura locLFP : pFactura.getListaLineaFactura()) {
			locLFP.setFactura(pFactura);
		}

		pFactura.setTotal(null);
		recibirLineasSolSum(pFactura);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pFactura);

		pFactura = this.entity.merge(pFactura);
		for(LineaFactura cadaLinea : pFactura.getListaLineaFactura()) {
			validarMontoLineaFactura(cadaLinea);
		}

		entity.flush();

		this.validarEstadoOrdenCompra(pFactura);
		this.validarPagos(pFactura);
		return pFactura;
	}

	private void recibirLineasSolSum(FacturaProveedor pFactura) {

		if(pFactura.getListaLineaFactura() != null) {
			for(LineaFactura auxLineaFactura : pFactura.getListaLineaFactura()) {
				if(auxLineaFactura instanceof LineaFacturaLineaOrdenCompra && ((LineaFacturaLineaOrdenCompra) auxLineaFactura).getLineaOrdenCompra() != null) {
					LineaOrdenCompra locLineaOrdenCompra = ((LineaFacturaLineaOrdenCompra) auxLineaFactura).getLineaOrdenCompra();
					if(locLineaOrdenCompra.getLineaContratacion() != null) {
						LineaContratacion locLineaContratacion = locLineaOrdenCompra.getLineaContratacion();
						for(LineaSolicitudSuministro auxSolicitudSuministro : locLineaContratacion.getListaLineasSolicitudSuministro()) {
							auxSolicitudSuministro.setEstado(Estado.RECIBIDA);
							this.entity.merge(auxSolicitudSuministro);
						}
					}
				}
			}
		}
		// for (OrdenCompra cadaOrden : pFactura.getl)// ----- VER AQUI
		// for (OrdenCompra cadaOrden : pFactura.getListaOrdenesDeCompra()){
		// for (LineaOrdenCompra auxLineaOrdenC :
		// cadaOrden.getListaLineaOrdenCompra()) {
		// for (LineaSolicitudSuministro auxSolicitudSum :
		// auxLineaOrdenC.getLineaContratacion().getListaLineasSolicitudSuministro()){
		// auxSolicitudSum.setEstado(Estado.RECIBIDA);
		// this.entity.merge(auxSolicitudSum);
		// }
		// }
		// }
	}

	/**
	 * Valida que los datos de la factura sean correctos.
	 * 
	 * @param pFactura
	 * @throws Exception
	 */
	private void validarFactura(Factura pFactura) throws Exception {

		if(pFactura.getProveedor() == null) {
			throw new TrascenderComprasException(122);
		}

		if(pFactura.getProveedor().getEstado().equals(Proveedor.Estado.INACTIVO)) {
			throw new TrascenderComprasException(127);
		}

		if(pFactura.getFechaEmision() == null) {
			pFactura.setFechaEmision(SecurityMgr.getInstance().getFechaActual().getTime());
		}

		if(pFactura.getTipoFactura() == null) {
			pFactura.setTipoFactura(Factura.TipoFactura.X);
		}

		if(!pFactura.getListaLineaFactura().isEmpty()) {
			for(LineaFactura cadaLineaFactura : pFactura.getListaLineaFactura()) {
				cadaLineaFactura.setFactura(pFactura);
			}
		} else {
			throw new TrascenderComprasException(910);
		}
		if(verificarLineasFactura(pFactura))
			pFactura.setEstado(Factura.Estado.PROCESADA);
	}

	/**
	 * Actualiza el precio de las lineas de otros documentos asociados a la Linea de la factura.
	 * 
	 * @param pLinea
	 */
	private void validarMontoLineaFactura(LineaFactura pLinea) {
		if(pLinea instanceof LineaFacturaLineaOrdenCompra) {
			// Actualizar la Linea de Orden de compra y la Linea de Presupuesto
			LineaFacturaLineaOrdenCompra lfoc = (LineaFacturaLineaOrdenCompra) pLinea;
			if(!lfoc.getMontoUnitario().equals(lfoc.getLineaOrdenCompra().getMontoUnitario())) {
				LineaOrdenCompra loc = lfoc.getLineaOrdenCompra();
				loc.setMontoUnitario(lfoc.getMontoUnitario());
				loc.setMontoTotal(loc.getMontoTotal());// Recalculo
				loc.getOrdenCompra().setTotal();// Recalculo
				// Si cambia el total de la Orden de Compra y tiene un solo Pago, este deberia cambiar su precio tambien.
				if(loc.getOrdenCompra() != null && loc.getOrdenCompra().getListaPagosOrdenCompra().size() == 1) {
					PagoOrdenCompra locPago = loc.getOrdenCompra().getListaPagosOrdenCompra().get(0);
					// Solo si aun no se pagó o si la factura es la misma que estamos cambiando
					if(locPago.getFactura() == null || locPago.getFactura().equals(pLinea.getFactura())) {
						locPago.setMonto(loc.getOrdenCompra().getTotal());
					}
				}

				entity.merge(loc.getOrdenCompra());

				LineaOfertaContratacion locon = loc.getLineaContratacion().getLineaOfertaContratacionAdjudicada();
				locon.setPrecioUnitario(pLinea.getMontoUnitario());
				locon.setPrecioTotal(locon.getPrecioTotal());// Recalculo.
				entity.merge(locon);
			}
		} else if(pLinea instanceof LineaFacturaPagoOrdenCompra) {
			LineaFacturaPagoOrdenCompra lfpoc = (LineaFacturaPagoOrdenCompra) pLinea;
			PagoOrdenCompra poc = lfpoc.getPagoOrdenCompra();
			if(!pLinea.getMontoUnitario().equals(poc.getMonto())) {
				poc.setMonto(pLinea.getMontoUnitario());
				poc.getOrdenCompra().setTotal();// Actualizar
				entity.merge(poc.getOrdenCompra());
			}
		}
	}

	/**
	 * Verifica que las lineas de factura tengan cuenta contable, retorna true de ser así, sino retorna false.
	 * 
	 * @param pFactura
	 * @return
	 */
	private boolean verificarLineasFactura(Factura pFactura) {
		for(LineaFactura linea : pFactura.getListaLineaFactura()) {
			if(linea.getCuenta() == null) {
				return false;
			}
		}
		return true;
	}

	public FiltroFacturaProveedor findListaFacturaProveedor(FiltroFacturaProveedor pFiltro) throws java.lang.Exception {
		Criterio locCriterio = Criterio
				.getInstance(entity, FacturaProveedor.class)
				.add(Restriccion.IGUAL("proveedor", pFiltro.getProveedor()))
				.add(Restriccion.MAYOR("fechaEmision", pFiltro.getFechaDesde()))
				.add(Restriccion.MENOR("fechaEmision", pFiltro.getFechaHasta()))
				.add(Restriccion.ILIKE("numero", pFiltro.getNumero()))
				.add(Restriccion.IGUAL("listaPagosOrdenCompra.ordenCompra.listaLineaOrdenCompra.lineaContratacion.listaLineasSolicitudSuministro.bien", pFiltro.getBien()))
				.add(Restriccion.IGUAL("listaPagosOrdenCompra.ordenCompra.listaLineaOrdenCompra.lineaContratacion.listaLineasSolicitudSuministro.solicitudSuministro.area.secretaria",
						pFiltro.getSecretaria()))
				.add(Restriccion.IGUAL("listaPagosOrdenCompra.ordenCompra.listaLineaOrdenCompra.lineaContratacion.listaLineasSolicitudSuministro.solicitudSuministro.area",
						pFiltro.getArea()));
		if(pFiltro.getEstado() != null) {
			locCriterio.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		} else {
			locCriterio.add(Restriccion.DISTINTO("estado", Factura.Estado.CANCELADA));
		}
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, FacturaProveedor.serialVersionUID, "idFactura", pFiltro.getListaBusquedaPorLogs());
		AtributoDinamico.addRestriccionesCriterio(locCriterio, FacturaProveedor.serialVersionUID, "idFactura", pFiltro.getListaAtributoDinamico());
		pFiltro.procesarYListar(locCriterio);
		for(FacturaProveedor cadaFacturaProveedor : pFiltro.getListaResultados()) {
			cadaFacturaProveedor.toString();
			if(cadaFacturaProveedor.getProveedor() != null) {
				if(!cadaFacturaProveedor.getProveedor().getListaBienProvisto().isEmpty()) {
					cadaFacturaProveedor.getProveedor().getListaBienProvisto().toString();
				}
				if(!cadaFacturaProveedor.getListaLineaFactura().isEmpty()) {
					cadaFacturaProveedor.getListaLineaFactura().toString();

					for(LineaFactura cadaLineaFactura : cadaFacturaProveedor.getListaLineaFactura()) {
						cadaLineaFactura.toString();
						if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
							((LineaFacturaBien) cadaLineaFactura).getBien().toString();
						}
					}
				}
				if(!cadaFacturaProveedor.getListaPagosOrdenCompra().isEmpty()) {
					cadaFacturaProveedor.getListaPagosOrdenCompra().toString();

					for(PagoOrdenCompra cadaPago : cadaFacturaProveedor.getListaPagosOrdenCompra()) {
						cadaPago.toString();
					}
				}
			}
		}
		return pFiltro;
	}

	public com.trascender.compras.recurso.persistent.suministros.FacturaProveedor findFacturaProveedorByID(long pID) throws java.lang.Exception {
		FacturaProveedor locFacturaProveedor = entity.find(FacturaProveedor.class, pID);

		if(locFacturaProveedor != null) {
			locFacturaProveedor.toString();
			locFacturaProveedor.getListaLogsAuditoria().size();
			for(LineaFactura cadaLineaFactura : locFacturaProveedor.getListaLineaFactura()) {
				cadaLineaFactura.toString();
			}
			// for (LineaFactura auxLineaFactura :
			// locFacturaProveedor.getListaLineaFactura()){
			// OrdenCompra ordenCompra =
			// auxLineaFactura.getLineaOrdenCompra().getOrdenCompra();
			// }//---------------------------------------------------------------------------------------------------------------------***

			Proveedor locProveedor = locFacturaProveedor.getProveedor();
			entity.refresh(locProveedor);
			locProveedor.toString();
			locFacturaProveedor.setProveedor(locProveedor);
			locFacturaProveedor.getProveedor().getDomicilio().toString();
			if(locFacturaProveedor.getProveedor().getProveedorLocal() != null) {
				locFacturaProveedor.getProveedor().getProveedorLocal().toString();
			}
			for(LineaFactura locLineaFactura : locFacturaProveedor.getListaLineaFactura()) {
				if(locLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) locLineaFactura).getBien() != null) {
					((LineaFacturaBien) locLineaFactura).getBien().toString();
				}
				if(locLineaFactura.getCuenta() != null) {
					locLineaFactura.getCuenta().toString();
				}
			}
			locFacturaProveedor.toString();
			locFacturaProveedor.getListaAtributosDinamicos().size();
		}

		return locFacturaProveedor;
	}

	/**
	 * Modifica una Factura de proveedor. La Factura debe estar en Estado Creada o Procesada, de lo contrario tira excepcion Verifico que las lineas de la
	 * factura tengan cuentas contables, de ser así la factura pasa a estado Procesada Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pFactura
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaProveedor updateFacturaProveedor(com.trascender.compras.recurso.persistent.suministros.FacturaProveedor pFactura)
			throws java.lang.Exception {
		validarModificacion(pFactura);
		pFactura.setEstado(Factura.Estado.CREADA);
		this.validarFactura(pFactura);
		// Seteo el total en null para que lo recalcule
		pFactura.setTotal(null);
		// volverPendienteLineasSolSum(pFactura);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pFactura);
		pFactura = entity.merge(pFactura);
		for(LineaFactura cadaLinea : pFactura.getListaLineaFactura()) {
			this.validarMontoLineaFactura(cadaLinea);
		}
		this.entity.flush();
		recibirLineasSolSum(pFactura);
		this.validarEstadoOrdenCompra(pFactura);
		this.validarPagos(pFactura);
		return pFactura;
	}

	// private void volverPendienteLineasSolSum(FacturaProveedor pFactura){
	// Criterio locCriterio = Criterio.getInstance(entity, LineaFactura.class)
	// .add(Restriccion.IGUAL("factura", pFactura));
	//
	// List<LineaFactura> locListaLineasDB = locCriterio.list();
	// List<LineaFactura> locListaLineas = pFactura.getListaLineaFactura();
	//
	// locListaLineasDB.remove(locListaLineas);
	// for (LineaFactura auxLocListaLineas : locListaLineasDB){
	// List<LineaSolicitudSuministro> locSS =
	// auxLocListaLineas.getLineaOrdenCompra().getLineaContratacion().getListaLineasSolicitudSuministro();
	// for (LineaSolicitudSuministro auxSS : locSS){
	// auxSS.setEstado(Estado.PENDIENTE);
	// this.entity.merge(auxSS);
	// }
	// }
	// }

	/**
	 * Elimina una factura. El borrado es lógico, le cambia el estado a Cancelada. SOlamente se puede eliminar una factura en estado Creada Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pFactura
	 * @throws java.lang.Exception
	 */
	public void deleteFacturaProveedor(com.trascender.compras.recurso.persistent.suministros.FacturaProveedor pFactura) throws java.lang.Exception {
		validarModificacion(pFactura);
		// cambio el estado de la factura
		FacturaProveedor locFactura = this.entity.find(FacturaProveedor.class, pFactura.getIdFactura());
		locFactura.toString();
		locFactura.setEstado(Factura.Estado.CANCELADA);

		for(PagoOrdenCompra cadaPago : locFactura.getListaPagosOrdenCompra()) {
			cadaPago.setFactura(null);
			OrdenCompra locOrden = cadaPago.getOrdenCompra();
			locOrden.setEstado(OrdenCompra.Estado.APROBADA);
			entity.merge(locOrden);
		}

		entity.merge(locFactura);
	}

	/**
	 * Agrego una factura por subsidio. Si las lineas de facturas tienen cuentas contables la Factura pasa a estado Procesada, de no ser así quedan con estado
	 * Creada. Verifica que todas las ordenes de compra esten Comprometidas, de no ser así tira excepcion. Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaSubsidio
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio addFacturaSubsidio(
			com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio) throws java.lang.Exception {
		if(verificarLineasFactura(pFacturaSubsidio))
			pFacturaSubsidio.setEstado(Factura.Estado.PROCESADA);
		this.validarFactura(pFacturaSubsidio);
		Double locTotalFactura = 0D;
		for(LineaFactura cadaLineaFactura : pFacturaSubsidio.getListaLineaFactura()) {
			cadaLineaFactura.setFactura(pFacturaSubsidio);
			locTotalFactura += cadaLineaFactura.getTotal();
		}
		pFacturaSubsidio.setTotal(locTotalFactura);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pFacturaSubsidio);

		entity.persist(pFacturaSubsidio);

		entity.flush();

		return pFacturaSubsidio;
	}

	/**
	 * Modifica una Factura por subsidio. La Factura debe estar en Estado Creada o Procesada, de lo contrario tira excepcion Verifico que las lineas de la
	 * factura tengan cuentas contables, de ser así la factura pasa a estado Procesada Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaSubsidio
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio updateFacturaSubsidio(
			com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio) throws java.lang.Exception {

		validarModificacion(pFacturaSubsidio);
		if(verificarLineasFactura(pFacturaSubsidio))
			pFacturaSubsidio.setEstado(Factura.Estado.PROCESADA);
		this.validarFactura(pFacturaSubsidio);

		// Seteo el total en null para que lo recalcule
		pFacturaSubsidio.setTotal(null);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pFacturaSubsidio);
		entity.merge(pFacturaSubsidio);
		this.entity.flush();
		return pFacturaSubsidio;
	}

	/**
	 * Elimina una factura. El borrado es lógico, le cambia el estado a Cancelada. Solamente se puede eliminar una factura en estado Creada Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaSubsidio
	 * @throws Exception
	 */
	public void deleteFacturaSubsidio(com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio) throws Exception {
		validarModificacion(pFacturaSubsidio);
		pFacturaSubsidio.setEstado(Factura.Estado.CANCELADA);
		entity.merge(pFacturaSubsidio);
	}

	/**
	 * Busca una lista de Facturas por subsidio Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pProveedor
	 * @param pDesde
	 * @param pHasta
	 * @param pEstado
	 * @return
	 * @throws java.lang.Exception
	 */

	@SuppressWarnings("unchecked")
	public FiltroFacturaSubsidio findListaFacturaSubsidio(FiltroFacturaSubsidio pFiltro) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, FacturaSubsidio.class).add(Restriccion.IGUAL("proveedor", pFiltro.getProveedor()))
				.add(Restriccion.MAYOR("fechaEmision", pFiltro.getFechaDesde())).add(Restriccion.MENOR("fechaEmision", pFiltro.getFechaHasta()))
				.add(Restriccion.IGUAL("digestoMunicipal", pFiltro.getDigestoMuncipal()));
		if(pFiltro.getEstado() != null) {
			locCriterio.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		} else {
			locCriterio.add(Restriccion.DISTINTO("estado", Factura.Estado.CANCELADA));
		}
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Factura.serialVersionUID, "idFactura", pFiltro.getListaBusquedaPorLogs());
		pFiltro.procesarYListar(locCriterio);
		for(FacturaSubsidio cadaFacturaSubsidio : pFiltro.getListaResultados()) {
			cadaFacturaSubsidio.toString();
			cadaFacturaSubsidio.getProveedor().toString();
			cadaFacturaSubsidio.getDigestoMunicipal().toString();

			for(LineaFactura cadaLineaFactura : cadaFacturaSubsidio.getListaLineaFactura()) {
				cadaLineaFactura.toString();
				if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
					((LineaFacturaBien) cadaLineaFactura).getBien().toString();
				}
			}
		}
		return pFiltro;
	}

	/**
	 * Obtiene una factura por subsidio por ID Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio getFacturaSubsidioPorId(long pId) throws Exception {
		FacturaSubsidio locFacturaSubsidio = entity.find(FacturaSubsidio.class, pId);
		if(locFacturaSubsidio != null) {
			locFacturaSubsidio.getListaLogsAuditoria().size();
			locFacturaSubsidio.toString();

			this.validarFactura(locFacturaSubsidio);

			for(BienProvisto cadaBienProvisto : locFacturaSubsidio.getProveedor().getListaBienProvisto()) {
				cadaBienProvisto.toString();
				cadaBienProvisto.getProveedor().toString();
			}
			if(!locFacturaSubsidio.getListaLineaFactura().isEmpty()) {
				for(LineaFactura cadaLineaFactura : locFacturaSubsidio.getListaLineaFactura()) {
					cadaLineaFactura.toString();
					if(cadaLineaFactura.getCuenta() != null) {
						cadaLineaFactura.getCuenta().toString();
					}
					if(cadaLineaFactura.getTotal() != null) {
						cadaLineaFactura.getTotal();
					} else
						cadaLineaFactura.setTotal(0d);
					if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
						((LineaFacturaBien) cadaLineaFactura).getBien().toString();
					}
				}
			}
			if(locFacturaSubsidio.getDigestoMunicipal() != null) {
				locFacturaSubsidio.getDigestoMunicipal().toString();
			}

		}
		return locFacturaSubsidio;
	}

	/**
	 * Agrego una factura por contrato. Si las lineas de facturas tienen cuentas contables la Factura pasa a estado Procesada, de no ser así quedan con estado
	 * Creada. Verifica que todas las ordenes de compra esten Comprometidas, de no ser así tira excepcion. Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaContrato
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato addFacturaContrato(
			com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato) throws Exception {
		if(verificarLineasFactura(pFacturaContrato))
			pFacturaContrato.setEstado(Factura.Estado.PROCESADA);
		this.validarFactura(pFacturaContrato);
		// Seteo el total en null para que lo recalcule
		pFacturaContrato.setTotal(null);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pFacturaContrato);

		entity.persist(pFacturaContrato);
		entity.flush();

		return pFacturaContrato;
	}

	/**
	 * Metodo deprecado
	 * 
	 * @param pFactura
	 * @param pSession
	 */
	@Deprecated
	private void addLineasFactura(Factura pFactura, Session pSession) {
		for(LineaFactura cadaLineaFactura : pFactura.getListaLineaFactura()) {
			if(cadaLineaFactura.getCantidad() != null && cadaLineaFactura.getCantidad().doubleValue() != 0D) {
				cadaLineaFactura.setTotal(null);
				cadaLineaFactura.setFactura(pFactura);
				pSession.saveOrUpdate(cadaLineaFactura);
			}
		}
	}

	/**
	 * Modifica una Factura por contrato. La Factura debe estar en Estado Creada o Procesada, de lo contrario tira excepcion Verifico que las lineas de la
	 * factura tengan cuentas contables, de ser así la factura pasa a estado Procesada Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaContrato
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato updateFacturaContrato(
			com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato) throws Exception {
		validarModificacion(pFacturaContrato);
		if(verificarLineasFactura(pFacturaContrato))
			pFacturaContrato.setEstado(Factura.Estado.PROCESADA);
		this.validarFactura(pFacturaContrato);

		// Seteo el total en null para que lo recalcule
		pFacturaContrato.setTotal(null);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pFacturaContrato);
		entity.merge(pFacturaContrato);
		this.entity.flush();
		return pFacturaContrato;
	}

	// /**
	// * metodo deprecado
	// * @param pFactura
	// * @param pSession
	// * @throws Exception
	// */
	// @Deprecated
	// private void updateListaLineasFactura(Factura pFactura,
	// Session pSession) throws Exception {
	// try{
	// List<LineaFactura> locListaLineasFactura = new ArrayList<LineaFactura>();
	//
	// for(LineaFactura cadaLineaFactura: pFactura.getListaLineaFactura()){
	// if(cadaLineaFactura != null &&
	// cadaLineaFactura.getCantidad().doubleValue() != 0D &&
	// cadaLineaFactura.getBien().getProveedor().getIdProveedor() ==
	// pFactura.getProveedor().getIdProveedor()){
	// pSession.saveOrUpdate(cadaLineaFactura);
	// }
	// else{
	// locListaLineasFactura.add(cadaLineaFactura);
	// }
	//
	// }
	//
	// if(!locListaLineasFactura.isEmpty()){
	// if(locListaLineasFactura.size()<pFactura.getListaLineaFactura().size()){
	// for(LineaFactura cadaLineaFactura: locListaLineasFactura){
	// pFactura.getListaLineaFactura().remove(cadaLineaFactura);
	// pSession.delete(cadaLineaFactura);
	// }
	// }
	// else{
	// throw new TrascenderComprasException(910);
	// }
	// }
	//
	// }catch(Exception e){
	// e.printStackTrace();
	// throw e;
	// }
	//
	// }

	/**
	 * Elimina una factura. El borrado es lógico, le cambia el estado a Cancelada. SOlamente se puede eliminar una factura en estado Creada Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaContrato
	 * @throws Exception
	 */
	public void deleteFacturaContrato(com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato) throws Exception {
		validarModificacion(pFacturaContrato);
		pFacturaContrato.setEstado(Factura.Estado.CANCELADA);
		entity.merge(pFacturaContrato);
	}

	/**
	 * Obtiene una factura por contrato por ID Business method
	 * 
	 * @ejb.interface-method view-type ="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato getFacturaContratoPorID(Long pId) throws Exception {
		FacturaContrato locFacturaContrato = entity.find(FacturaContrato.class, pId);

		if(locFacturaContrato != null) {
			locFacturaContrato.toString();
			locFacturaContrato.getListaLogsAuditoria().size();
			this.validarFactura(locFacturaContrato);

			for(BienProvisto cadaBienProvisto : locFacturaContrato.getProveedor().getListaBienProvisto()) {
				if(cadaBienProvisto.getBien() != null) {
					cadaBienProvisto.getBien().toString();
				}
			}
			if(!locFacturaContrato.getListaLineaFactura().isEmpty()) {
				for(LineaFactura cadaLineaFactura : locFacturaContrato.getListaLineaFactura()) {
					cadaLineaFactura.toString();
					if(cadaLineaFactura.getCuenta() != null) {
						cadaLineaFactura.getCuenta().toString();
					}
					if(cadaLineaFactura.getTotal() != null) {
						cadaLineaFactura.getTotal();
					} else
						cadaLineaFactura.setTotal(0d);

					if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
						((LineaFacturaBien) cadaLineaFactura).getBien().toString();
					}
				}
			}
			if(locFacturaContrato.getContrato() != null) {
				locFacturaContrato.getContrato().toString();
			}
		}
		return locFacturaContrato;
	}

	/**
	 * Busca una lista de Factura por contrato por los parametros Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pProveedor
	 * @param pDesde
	 * @param pHasta
	 * @param pEstado
	 * @param pContrato
	 * @return
	 * @throws java.lang.Exception
	 */
	public FiltroFacturaContrato findListaFacturaContrato(FiltroFacturaContrato pFiltro) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, FacturaContrato.class).add(Restriccion.IGUAL("proveedor", pFiltro.getProveedor()))
				.add(Restriccion.MAYOR("fechaEmision", pFiltro.getFechaDesde())).add(Restriccion.MENOR("fechaEmision", pFiltro.getFechaHasta()));
		if(pFiltro.getEstado() != null) {
			locCriterio.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		} else {
			locCriterio.add(Restriccion.DISTINTO("estado", Factura.Estado.CANCELADA));
		}
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Factura.serialVersionUID, "idFactura", pFiltro.getListaBusquedaPorLogs());
		pFiltro.procesarYListar(locCriterio);
		for(FacturaContrato cadaFacturaContrato : pFiltro.getListaResultados()) {
			cadaFacturaContrato.toString();
			cadaFacturaContrato.getProveedor().toString();
			cadaFacturaContrato.getContrato().toString();

			for(LineaFactura cadaLineaFactura : cadaFacturaContrato.getListaLineaFactura()) {
				cadaLineaFactura.toString();
				if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
					((LineaFacturaBien) cadaLineaFactura).getBien().toString();
				}
			}
		}
		return pFiltro;
	}

	/**
	 * Agrego una factura por servicio. Si las lineas de facturas tienen cuentas contables la Factura pasa a estado Procesada, de no ser así quedan con estado
	 * Creada. Verifica que todas las ordenes de compra esten Comprometidas, de no ser así tira excepcion. Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaServicio
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaServicio addFacturaServicio(
			com.trascender.compras.recurso.persistent.suministros.FacturaServicio pFacturaServicio) throws Exception {
		if(verificarLineasFactura(pFacturaServicio))
			pFacturaServicio.setEstado(Factura.Estado.PROCESADA);
		this.validarFactura(pFacturaServicio);
		// Seteo el total en null para que lo calcule
		pFacturaServicio.setTotal(null);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pFacturaServicio);

		entity.persist(pFacturaServicio);
		entity.flush();

		return pFacturaServicio;
	}

	/**
	 * Modifica una Factura de servicio. La Factura debe estar en Estado Creada o Procesada, de lo contrario tira excepcion Verifico que las lineas de la
	 * factura tengan cuentas contables, de ser así la factura pasa a estado Procesada Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaServicio
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaServicio updateFacturaServicio(
			com.trascender.compras.recurso.persistent.suministros.FacturaServicio pFacturaServicio) throws Exception {
		validarModificacion(pFacturaServicio);

		if(verificarLineasFactura(pFacturaServicio))
			pFacturaServicio.setEstado(Factura.Estado.PROCESADA);
		this.validarFactura(pFacturaServicio);
		// Seteo el total en null para que lo recalcule
		pFacturaServicio.setTotal(null);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pFacturaServicio);
		entity.merge(pFacturaServicio);
		this.entity.flush();
		return pFacturaServicio;
	}

	/**
	 * Obtiene una factura por servicio por ID Business method
	 * 
	 * @ejb.interface-method view-type ="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaServicio getFacturaServicioPorId(Long pId) throws Exception {
		FacturaServicio locFacturaServicio = entity.find(FacturaServicio.class, pId);
		if(locFacturaServicio != null) {
			locFacturaServicio.toString();
			locFacturaServicio.getListaLogsAuditoria().size();
			this.validarFactura(locFacturaServicio);

			for(BienProvisto cadaBienProvisto : locFacturaServicio.getProveedor().getListaBienProvisto()) {
				cadaBienProvisto.toString();
			}

			if(!locFacturaServicio.getListaLineaFactura().isEmpty()) {
				for(LineaFactura cadaLineaFactura : locFacturaServicio.getListaLineaFactura()) {
					cadaLineaFactura.toString();
					if(cadaLineaFactura.getCuenta() != null) {
						cadaLineaFactura.getCuenta().toString();
					}
					if(cadaLineaFactura.getTotal() != null) {
						cadaLineaFactura.getTotal();
					} else
						cadaLineaFactura.setTotal(0d);
					if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
						((LineaFacturaBien) cadaLineaFactura).getBien().toString();
					}
				}
			}
			if(locFacturaServicio.getBien() != null) {
				locFacturaServicio.getBien().toString();
			}
		}
		return locFacturaServicio;
	}

	/**
	 * Elimina una factura. El borrado es lógico, le cambia el estado a Cancelada. Solamente se puede eliminar una factura en estado Creada Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pFacturaServicio
	 * @throws Exception
	 */
	public void deleteFacturaServicio(com.trascender.compras.recurso.persistent.suministros.FacturaServicio pFacturaServicio) throws Exception {
		validarModificacion(pFacturaServicio);
		pFacturaServicio.setEstado(Factura.Estado.CANCELADA);
		entity.merge(pFacturaServicio);
	}

	/**
	 * Busca una lista de Factura por servicio Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pProveedor
	 * @param pDesde
	 * @param pHasta
	 * @param pEstado
	 * @param pBien
	 * @return
	 * @throws java.lang.Exception
	 */
	public FiltroFacturaServicio findListaFacturaServicio(FiltroFacturaServicio pFiltro) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, FacturaServicio.class).add(Restriccion.IGUAL("proveedor", pFiltro.getProveedor()))
				.add(Restriccion.MAYOR("fechaEmision", pFiltro.getFechaDesde())).add(Restriccion.MENOR("fechaEmision", pFiltro.getFechaHasta()))
				.add(Restriccion.IGUAL("bien", pFiltro.getBien()));
		if(pFiltro.getEstado() != null) {
			locCriterio.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		} else {
			locCriterio.add(Restriccion.DISTINTO("estado", Factura.Estado.CANCELADA));
		}
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Factura.serialVersionUID, "idFactura", pFiltro.getListaBusquedaPorLogs());
		pFiltro.procesarYListar(locCriterio);
		for(FacturaServicio cadaFacturaServicio : pFiltro.getListaResultados()) {
			cadaFacturaServicio.toString();
			if(cadaFacturaServicio.getBien() != null) {
				cadaFacturaServicio.getBien().toString();
			}
			if(!cadaFacturaServicio.getListaLineaFactura().isEmpty()) {
				for(LineaFactura cadaLineaFactura : cadaFacturaServicio.getListaLineaFactura()) {
					cadaLineaFactura.toString();
					if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
						((LineaFacturaBien) cadaLineaFactura).getBien().toString();
					}
				}
			}
			if(cadaFacturaServicio.getProveedor() != null) {
				cadaFacturaServicio.getProveedor().toString();
			}
		}
		return pFiltro;
	}

	/**
	 * Obtiene una Linea de Factura por ID Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.LineaFactura getLineaFacturaPorId(long pId) throws Exception {
		LineaFactura locLineaFactura = entity.find(LineaFactura.class, pId);
		if(locLineaFactura != null) {
			locLineaFactura.toString();
			locLineaFactura.getTotal();
			if(locLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) locLineaFactura).getBien() != null) {
				((LineaFacturaBien) locLineaFactura).getBien().toString();
			}

			if(locLineaFactura.getFactura() != null) {
				locLineaFactura.getFactura().toString();
			}
			if(locLineaFactura.getCuenta() != null) {
				locLineaFactura.getCuenta().toString();
			}
		}
		return locLineaFactura;
	}

	/**
	 * Obtiene una factura (clase abstracta) por ID
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.Factura getFacturaPorId(long pId) throws Exception {
		Factura locFactura = entity.find(Factura.class, pId);

		if(locFactura != null) {
			locFactura.toString();

			if(locFactura.getProveedor() != null) {
				locFactura.getProveedor().toString();
				if(!locFactura.getProveedor().getListaBienProvisto().isEmpty()) {
					for(BienProvisto cadaBienProvisto : locFactura.getProveedor().getListaBienProvisto()) {
						cadaBienProvisto.toString();
					}
				}
			}

			if(!locFactura.getListaLineaFactura().isEmpty()) {
				for(LineaFactura cadaLineaFactura : locFactura.getListaLineaFactura()) {
					cadaLineaFactura.toString();
					if(cadaLineaFactura.getCuenta() != null) {
						cadaLineaFactura.getCuenta().toString();
					}
					if(cadaLineaFactura.getTotal() != null) {
						cadaLineaFactura.getTotal();
					} else
						cadaLineaFactura.setTotal(0d);
					if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
						((LineaFacturaBien) cadaLineaFactura).getBien().toString();
					}
				}
			}
		}
		return locFactura;
	}

	public List<Factura> findListaFactura(com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor, java.util.Date pDesde, java.util.Date pHasta,
			com.trascender.compras.recurso.persistent.suministros.Factura.Estado pEstado) throws java.lang.Exception {
		List<Factura> locListaFacturas = new ArrayList<Factura>();
		Criterio locCriterio = Criterio.getInstance(entity, Factura.class).add(Restriccion.IGUAL("proveedor", pProveedor)).add(Restriccion.MAYOR("fechaEmision", pDesde))
				.add(Restriccion.MENOR("fechaEmision", pHasta));
		if(pEstado != null) {
			locCriterio.add(Restriccion.IGUAL("estado", pEstado));
		} else {
			locCriterio.add(Restriccion.DISTINTO("estado", Factura.Estado.CANCELADA));
		}
		locListaFacturas = locCriterio.list();

		for(Factura cadaFactura : locListaFacturas) {
			cadaFactura.toString();
			if(!cadaFactura.getListaLineaFactura().isEmpty()) {
				for(LineaFactura cadaLineaFactura : cadaFactura.getListaLineaFactura()) {
					cadaLineaFactura.toString();
					if(cadaLineaFactura instanceof LineaFacturaBien && ((LineaFacturaBien) cadaLineaFactura).getBien() != null) {
						((LineaFacturaBien) cadaLineaFactura).getBien().toString();
					}
				}
			}
			if(cadaFactura.getProveedor() != null) {
				cadaFactura.getProveedor().toString();
			}
		}
		return locListaFacturas;
	}

	/**
	 * Comprueba el estado de la Factura lanza E si una factura esta en estado Creada o Procesada
	 * 
	 * @param pFactura
	 * @throws TrascenderComprasException
	 */
	private void validarModificacion(Factura pFactura) throws TrascenderComprasException {
		if(pFactura.getEstado() != Factura.Estado.CREADA && pFactura.getEstado() != Factura.Estado.PROCESADA) {
			throw new TrascenderComprasException(124);
		}
	}

	private void validarEstadoOrdenCompra(FacturaProveedor pFactura) {
		// Un set, para no repetirlas.
		Set<OrdenCompra> locListaOrdenCompra = new HashSet<OrdenCompra>();
		if(pFactura.getListaLineaFactura() != null) {

			for(PagoOrdenCompra auxPago : pFactura.getListaPagosOrdenCompra()) {
				OrdenCompra ordenCompra = auxPago.getOrdenCompra();

				locListaOrdenCompra.add(ordenCompra);
			}
		}

		for(OrdenCompra ordenCompra : locListaOrdenCompra) {
			ordenCompra.validarCumplimiento();
		}
	}

	public void validarNumeroFactura(FacturaProveedor pFactura) throws TrascenderComprasException {

		Criterio locCriterio = Criterio.getInstance(entity, FacturaProveedor.class).add(Restriccion.IGUAL("proveedor", pFactura.getProveedor()))
				.add(Restriccion.IGUAL("tipoFactura", pFactura.getTipoFactura())).add(Restriccion.IGUAL("numero", pFactura.getNumero()));

		locCriterio.setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new TrascenderComprasException(128);
		}
	}

	public void validarPagos(FacturaProveedor pFactura) throws TrascenderComprasException {

		Collections.sort(pFactura.getListaPagosOrdenCompra(), new Comparator<PagoOrdenCompra>() {
			public int compare(PagoOrdenCompra p1, PagoOrdenCompra p2) {
				return (int) (p1.getIdPagoOrdenCompra() - p2.getIdPagoOrdenCompra());
			}
		});

		// obtengo todas las Ordenes de Compra en base a los pagos de pFactura
		List<OrdenCompra> locListaOrdenCompra = new ArrayList<OrdenCompra>();
		for(PagoOrdenCompra cadaPago : pFactura.getListaPagosOrdenCompra()) {
			OrdenCompra locOrdenCompra = cadaPago.getOrdenCompra();
			if(!locListaOrdenCompra.contains(locOrdenCompra)) {
				locListaOrdenCompra.add(locOrdenCompra);
			}
		}

		for(OrdenCompra cadaOrdenCompra : locListaOrdenCompra) {
			for(PagoOrdenCompra cadaPagoFactura : pFactura.getListaPagosOrdenCompra()) {
				if(cadaPagoFactura.getOrdenCompra().equals(cadaOrdenCompra)) {
					for(PagoOrdenCompra cadaPagoOrdenCompra : cadaOrdenCompra.getListaPagosOrdenCompra()) {
						if(cadaPagoFactura.getIdPagoOrdenCompra() > cadaPagoOrdenCompra.getIdPagoOrdenCompra() && cadaPagoOrdenCompra.getFactura() == null) {
							throw new TrascenderComprasException(129);
						}
					}
				}
			}
		}
	}

	private Integer getNumeroLiquidacionCompra() {
		Criterio locCriterio = Criterio.getInstance(entity, LiquidacionCompra.class).setProyeccion(Proyeccion.MAX("numero"));
		String stringNroMaximo = locCriterio.uniqueResult();
		if(stringNroMaximo == null) {
			stringNroMaximo = "0";
		}
		Integer numeroMaximo = Integer.parseInt(stringNroMaximo);
		return numeroMaximo;
	}

	public void addLiquidacionCompra(LiquidacionCompra pLiquidacionCompra) throws Exception {
		pLiquidacionCompra.setNumero((this.getNumeroLiquidacionCompra() + 1) + "");
		this.verificarLiquidacionCompra(pLiquidacionCompra);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pLiquidacionCompra);
		this.entity.merge(pLiquidacionCompra);
		this.entity.flush();
	}

	public LiquidacionCompra updateLiquidacionCompra(LiquidacionCompra pLiquidacionCompra) throws Exception {
		this.verificarLiquidacionCompra(pLiquidacionCompra);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pLiquidacionCompra);
		pLiquidacionCompra = this.entity.merge(pLiquidacionCompra);
		this.entity.flush();
		return pLiquidacionCompra;
	}

	public void deleteLiquidacionCompra(LiquidacionCompra pLiquidacionCompra) throws Exception {
		// TrascenderEnverListener.setValoresEnAuditoriaBean(pLiquidacionCompra);
		this.entity.remove(this.entity.merge(pLiquidacionCompra)); // .-------> no deberia borrarse, sino cambiarle el estado y mergear.
		// this.entity.flush();
	}

	public LiquidacionCompra getLiquidacionCompraPorId(Long pId) throws Exception {
		LiquidacionCompra locLiquidacionCompra = Criterio.getInstance(this.entity, LiquidacionCompra.class).add(Restriccion.IGUAL("idLiquidacionCompra", pId)).uniqueResult();

		if(locLiquidacionCompra != null) {
			locLiquidacionCompra.toString();
			locLiquidacionCompra.getListaLogsAuditoria().size();

			for(Factura cadaFactura : locLiquidacionCompra.getListaFacturas()) {
				cadaFactura.toString();
				cadaFactura.getProveedor().getDomicilio().getDomicilioCompleto().toString();
				for(LineaFactura cadaLinea : cadaFactura.getListaLineaFactura()) {
					cadaLinea.getNombre().toString();
				}
			}
		}

		return locLiquidacionCompra;
	}

	public FiltroLiquidacionCompra findListaLiquidacionCompra(FiltroLiquidacionCompra pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entity, LiquidacionCompra.class).add(Restriccion.IGUAL("numero", pFiltro.getNumero()))
				.add(Restriccion.IGUAL("fecha", pFiltro.getFecha()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, LiquidacionCompra.serialVersionUID, "idLiquidacionCompra", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(LiquidacionCompra cadaLiquidacionCompra : pFiltro.getListaResultados()) {
			cadaLiquidacionCompra.toString();
			cadaLiquidacionCompra.getListaFacturas().size();
		}

		return pFiltro;
	}

	private void verificarLiquidacionCompra(LiquidacionCompra pLiquidacionCompra) {
		for(Factura cadaFactura : pLiquidacionCompra.getListaFacturas()) {
			cadaFactura.setLiquidacionCompra(pLiquidacionCompra);
		}

		if(pLiquidacionCompra.getIdLiquidacionCompra() != -1) {
			LiquidacionCompra locLiquidacionCompra = Criterio.getInstance(this.entity, LiquidacionCompra.class)
					.add(Restriccion.IGUAL("idLiquidacionCompra", pLiquidacionCompra.getIdLiquidacionCompra())).uniqueResult();

			for(Factura cadaFactura : locLiquidacionCompra.getListaFacturas()) {
				if(!pLiquidacionCompra.getListaFacturas().contains(cadaFactura)) {
					cadaFactura.setLiquidacionCompra(null);
					this.entity.merge(cadaFactura);
				}
			}
		}
	}
}

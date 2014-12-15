
package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.business.interfaces.BusinessLicitacionLocal;
import com.trascender.compras.business.interfaces.BusinessOrdenCompraLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroContratacion;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.RepresentanteActaApertura;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "BusinessLicitacionLocal")
public class BusinessLicitacionBean implements BusinessLicitacionLocal {
	private static final long serialVersionUID = 6075260406697217212L;
	private static final String NOMBRE = "COM|Adm de Contrataciones";

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessLicitacionBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessLicitacionBean.NOMBRE);

		Recurso recursoLicitacion = new Recurso();
		recursoLicitacion.setIdRecurso(Contratacion.serialVersionUID);
		recursoLicitacion.setNombre("Contrataciones");
		recursoLicitacion.setAtributosConsultables("Nº Contratacion", "numero", "Objeto", "objeto", "Tipo", "tipo", "Estado", "estado", "Bienes", "bienes", Tipo.TEXTO_LARGO);
		recursoLicitacion.setClase(Contratacion.class);
		grupoRecursos.getListaRecursos().add(recursoLicitacion);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	@PersistenceContext
	private EntityManager entity;

	@EJB
	private BusinessOrdenCompraLocal businessOrdenCompra;

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
	}

	public void ejbCreate() {

	}

	private Integer getNumeroContratacion(Contratacion pContratacion) {
		// Calendar locCalendarInicio = Calendar.getInstance();
		// locCalendarInicio.set(Calendar.MONTH, Calendar.JANUARY);
		// locCalendarInicio.set(Calendar.DAY_OF_MONTH, 1);
		//
		// Calendar locCalendarFin = Calendar.getInstance();
		// locCalendarFin.set(Calendar.MONTH, Calendar.DECEMBER);
		// locCalendarFin.set(Calendar.DAY_OF_MONTH, 31);

		Criterio locCriterio = Criterio.getInstance(entity, Contratacion.class).setProyeccion(Proyeccion.PROP("MAX(numero)").SIN_PROCESAR_ENTIDADES()).setModoDebug(true);

		Integer numeroContratacion = locCriterio.uniqueResult();
		if(numeroContratacion == null) {
			numeroContratacion = 0;
		}
		numeroContratacion++;

		return numeroContratacion;
	}

	public Contratacion addContratacion(Contratacion pContratacion) throws Exception {
		pContratacion.setEstado(Contratacion.Estado.NUEVA);
		pContratacion.setNumero(this.getNumeroContratacion(pContratacion));
		validarContratacion(pContratacion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pContratacion);
		pContratacion = entity.merge(pContratacion);
		entity.flush();
		validarAdjudicacion(pContratacion);
		return pContratacion;
	}

	public Contratacion updateContratacion(Contratacion pContratacion) throws Exception {

		TrascenderEnverListener.setValoresEnAuditoriaBean(pContratacion);

		validarContratacion(pContratacion);

		// Contratacion.Estado locEstado = (Estado) Criterio.getInstance(entity, Contratacion.class)
		// .add(Restriccion.IGUAL("idContratacion", pContratacion.getIdContratacion()))
		// .setProyeccion(Proyeccion.PROP("estado"))
		// .uniqueResult();
		// if (!locEstado.equals(Contratacion.Estado.NUEVA) && !locEstado.equals(Contratacion.Estado.EN_PREPARACION)){
		// throw new TrascenderComprasException(503);
		// }

		Contratacion contratacionPreMerge = pContratacion;

		pContratacion = entity.merge(pContratacion);

		this.entity.flush();

		TrascenderEnverListener.setValoresEnAuditoriaBean(contratacionPreMerge);
		validarAdjudicacion(pContratacion);
		this.entity.flush();
		return pContratacion;
	}

	/**
	 * Valida la adjudicacion y genera las Ordendes de Compra.
	 * 
	 * @param pContratacion
	 */
	private void validarAdjudicacion(Contratacion pContratacion) throws Exception {
		if(!pContratacion.isCompletamenteAdjudicada()) {
			return;
		}
		Map<Proveedor, OrdenCompra> locMapaOrdenesCompra = new HashMap<Proveedor, OrdenCompra>();
		for(LineaContratacion cadaLineaContratacion : pContratacion.getListaLineasContratacion()) {
			if(cadaLineaContratacion.getLineaOfertaContratacionAdjudicada() == null) {
				throw new TrascenderComprasException(90);
			}
			LineaOfertaContratacion locLineaOferta = cadaLineaContratacion.getLineaOfertaContratacionAdjudicada();
			OrdenCompra locOrdenCompra = locMapaOrdenesCompra.get(locLineaOferta.getOfertaContratacion().getProveedor());
			if(locOrdenCompra != null) {
				LineaOrdenCompra locLinea = this.generarLineaOrdenCompra(locLineaOferta);
				locOrdenCompra.addLineaOrdenCompra(locLinea);
			} else {
				locOrdenCompra = this.generarOrdenCompra(locLineaOferta);
			}
			locMapaOrdenesCompra.put(locOrdenCompra.getProveedor(), locOrdenCompra);
		}
		for(OrdenCompra cadaOrdenCompra : locMapaOrdenesCompra.values()) {
			businessOrdenCompra.addOrdenCompra(cadaOrdenCompra);
		}
		pContratacion.setEstado(Contratacion.Estado.ADJUDICADA);
	}

	private OrdenCompra generarOrdenCompra(LineaOfertaContratacion pLineaOferta) {
		OrdenCompra locOrdenCompra = new OrdenCompra();
		locOrdenCompra.setFechaEmision(new Date());
		locOrdenCompra.setProveedor(pLineaOferta.getOfertaContratacion().getProveedor());
		LineaOrdenCompra locLineaOrdenCompra = this.generarLineaOrdenCompra(pLineaOferta);
		locOrdenCompra.addLineaOrdenCompra(locLineaOrdenCompra);
		return locOrdenCompra;
	}

	private LineaOrdenCompra generarLineaOrdenCompra(LineaOfertaContratacion pLineaOferta) {
		LineaOrdenCompra locLineaOrdenCompra = new LineaOrdenCompra();
		locLineaOrdenCompra.setBien(pLineaOferta.getLineaContratacion().getBien());
		locLineaOrdenCompra.setCantidad(pLineaOferta.getLineaContratacion().getCantidad());
		locLineaOrdenCompra.setMontoUnitario(pLineaOferta.getPrecioUnitario());
		locLineaOrdenCompra.setMontoTotal(pLineaOferta.getPrecioTotal());
		locLineaOrdenCompra.setLineaContratacion(pLineaOferta.getLineaContratacion());
		return locLineaOrdenCompra;
	}

	private void validarContratacion(Contratacion pContratacion) throws Exception {
		// Mientras se va conformando, la Contratacion pude no tener numero.
		if(pContratacion.getNumero() != null) {
			Long locCantidad = Criterio.getInstance(entity, Contratacion.class).add(Restriccion.DISTINTO("idContratacion", pContratacion.getIdContratacion()))
					.add(Restriccion.IGUAL("numero", pContratacion.getNumero())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

			if(locCantidad.intValue() > 0) {
				throw new TrascenderComprasException(501);
			}
		}

		// No se puede cargar Actas, Presupuestos o Adjudicaciones sin elegir al menos el Tipo de Contratacion
		if(!pContratacion.getListaOfertasContratacion().isEmpty() && pContratacion.getTipo() == null) {
			throw new TrascenderComprasException(525);
		}

		for(OfertaContratacion cadaOferta : pContratacion.getListaOfertasContratacion()) {
			cadaOferta.setContratacion(pContratacion);
		}

		for(LineaContratacion cadaLinea : pContratacion.getListaLineasContratacion()) {
			cadaLinea.setContratacion(pContratacion);
		}

		if(pContratacion.getActaApertura() != null) {
			pContratacion.getActaApertura().setContratacion(pContratacion);
			for(RepresentanteActaApertura cadaRepresentante : pContratacion.getActaApertura().getListaRepresentantes()) {
				cadaRepresentante.setActaApertura(pContratacion.getActaApertura());
			}
		}
	}

	public FiltroContratacion findListaContratacion(FiltroContratacion pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Contratacion.class).add(Restriccion.IGUAL("fechaPublicacion", pFiltro.getFechaPublicacion()))
				.add(Restriccion.IGUAL("numero", pFiltro.getNumero())).add(Restriccion.ILIKE("objeto", pFiltro.getObjeto())).add(Restriccion.IGUAL("tipo", pFiltro.getTipo()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado())).setModoDebug(true).setDistinct(true);

		locCriterio.crearAlias("listaLineasContratacion.listaLineasSolicitudSuministro", "cadaLineaSolSum")
				.add(Restriccion.IGUAL("cadaLineaSolSum.solicitudSuministro.area", pFiltro.getArea()))
				.add(Restriccion.IGUAL("cadaLineaSolSum.solicitudSuministro.area.secretaria", pFiltro.getSecretaria()));

		if(pFiltro.getBien() != null) {
			locCriterio.add(Restriccion.IGUAL("cadaLineaSolSum.bien", pFiltro.getBien()));
		} else {
			locCriterio.add(Restriccion.EN("cadaLineaSolSuministro.bien.idBien", pFiltro.getListaIdBienes()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Contratacion.serialVersionUID, "idContratacion", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(Contratacion cadaContratacion : pFiltro.getListaResultados()) {
			for(Proveedor cadaProveedor : cadaContratacion.getListaProveedoresAutorizados()) {
				cadaProveedor.getListaCodigosCiiu().size();
				cadaProveedor.getDomicilio().toString();
			}
			cadaContratacion.getListaLineasContratacion().size();
		}

		return pFiltro;
	}

	public Contratacion getContratacionPorId(long pIdContratacion) throws Exception {
		Contratacion locContratacion = entity.find(Contratacion.class, pIdContratacion);
		if(locContratacion != null) {
			locContratacion.getListaLogsAuditoria().size();
			locContratacion.getListaLineasContratacion().size();
			for(LineaContratacion cadaLinea : locContratacion.getListaLineasContratacion()) {
				cadaLinea.getListaLineasSolicitudSuministro().toString();
				for(LineaSolicitudSuministro cadaLineaSolicitud : cadaLinea.getListaLineasSolicitudSuministro()) {
					cadaLineaSolicitud.getListaLineaContratacion().size();
					cadaLineaSolicitud.getListaLineasMovimientosMercaderia().size();
				}
			}
			for(OfertaContratacion cadaOferta : locContratacion.getListaOfertasContratacion()) {
				refrescarOferta(cadaOferta);
			}
			if(locContratacion.getActaApertura() != null) {
				locContratacion.getActaApertura().toString();
			}
			for(Proveedor cadaProveedor : locContratacion.getListaProveedoresAutorizados()) {
				cadaProveedor.toString();
				cadaProveedor.getDomicilio().toString();
			}
			if(locContratacion.getActaApertura() != null) {
				locContratacion.getActaApertura().toString();
				locContratacion.getActaApertura().getListaRepresentantes().toString();
			}
		}
		return locContratacion;
	}

	private void refrescarOferta(OfertaContratacion pOferta) {
		pOferta.toString();
		for(LineaOfertaContratacion cadaRenglonOferta : pOferta.getListaLineasOfertasContratacion()) {
			cadaRenglonOferta.toString();
		}
	}

	public void deleteContratacion(Contratacion pContratacion) throws Exception {
		Contratacion.Estado locEstado = Criterio.getInstance(entity, Contratacion.class).add(Restriccion.IGUAL("idContratacion", pContratacion.getIdContratacion()))
				.setProyeccion(Proyeccion.PROP("estado")).uniqueResult();
		if(!locEstado.equals(Contratacion.Estado.NUEVA) && !locEstado.equals(Contratacion.Estado.EN_PREPARACION)) {
			throw new TrascenderComprasException(504);
		}
		pContratacion.setEstado(Contratacion.Estado.CANCELADA);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pContratacion);
		pContratacion = entity.merge(pContratacion);
		this.desasociarLineasSolicitud(pContratacion);
		entity.flush();
	}

	private void desasociarLineasSolicitud(Contratacion pContratacion) {
		for(LineaContratacion cadaLineaContratacion : pContratacion.getListaLineasContratacion()) {
			for(LineaSolicitudSuministro cadaLineaSolSum : cadaLineaContratacion.getListaLineasSolicitudSuministro()) {
				// cadaLineaSolSum.setLineaContratacion(null);
				cadaLineaSolSum.setListaLineaContratacion(new ArrayList<LineaContratacion>());
			}
		}
	}

	public OfertaContratacion addOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception {
		validarOfertaLicitacion(pOfertaLicitacion);
		entity.persist(pOfertaLicitacion);
		return pOfertaLicitacion;
	}

	public OfertaContratacion updateOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception {
		validarOfertaLicitacion(pOfertaLicitacion);
		entity.merge(pOfertaLicitacion);
		return pOfertaLicitacion;
	}

	private void validarOfertaLicitacion(OfertaContratacion pOfertaContratacion) throws Exception {
		Contratacion locLicitacion = entity.find(Contratacion.class, pOfertaContratacion.getContratacion().getIdContratacion());
		if(locLicitacion.getTipo().toString().contains("PRIVADA")) {
			if(!locLicitacion.getListaProveedoresAutorizados().contains(pOfertaContratacion.getProveedor())) {
				throw new TrascenderComprasException(502);
			}
		}
		for(LineaOfertaContratacion cadaOfertaRenglon : pOfertaContratacion.getListaLineasOfertasContratacion()) {
			cadaOfertaRenglon.setOfertaContratacion(pOfertaContratacion);
		}
	}

	public List<OfertaContratacion> findListaOfertaLicitacion(Contratacion pLicitacion, Date pFechaOferta, Proveedor pProveedor) throws Exception {
		List<OfertaContratacion> locListaResultado;
		Criterio locCriterio = Criterio.getInstance(entity, OfertaContratacion.class).add(Restriccion.IGUAL("licitacion", pLicitacion))
				.add(Restriccion.IGUAL("fechaOferta", pFechaOferta)).add(Restriccion.IGUAL("proveedor", pProveedor));
		locListaResultado = locCriterio.list();
		return locListaResultado;
	}

	public OfertaContratacion getOfertaLicitacionPorId(long pIdLicitacion) throws Exception {
		OfertaContratacion locOferta = entity.find(OfertaContratacion.class, pIdLicitacion);
		if(locOferta != null) {
			locOferta.toString();
			locOferta.getProveedor().toString();
		}

		return locOferta;
	}

	public void deleteOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception {
		pOfertaLicitacion = this.entity.getReference(OfertaContratacion.class, pOfertaLicitacion.getIdOfertaContratacion());
		// if (!pOfertaLicitacion.getLicitacion().getEstado().equals(Licitacion.Estado.VIGENTE)){
		// throw new TrascenderComprasException(505);
		// }
		entity.remove(pOfertaLicitacion);
	}

	public ActaApertura addActaApertura(ActaApertura pActaApertura) throws Exception {
		this.validarActaApertura(pActaApertura);
		pActaApertura = this.entity.merge(pActaApertura);
		return pActaApertura;
	}

	private void validarActaApertura(ActaApertura pActaApertura) throws Exception {
		if(pActaApertura == null) {
			throw new TrascenderComprasException(600);
		}

		Long locResult = Criterio.getInstance(this.entity, ActaApertura.class).add(Restriccion.IGUAL("licitacion", pActaApertura.getContratacion()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idActaApertura", pActaApertura.getIdActaApertura()))).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(locResult.longValue() > 0) {
			throw new TrascenderComprasException(601);
		}

		pActaApertura.getContratacion().setActaApertura(pActaApertura);

		for(RepresentanteActaApertura cadaRepresentante : pActaApertura.getListaRepresentantes()) {
			cadaRepresentante.setActaApertura(pActaApertura);
		}
	}

	public ActaApertura updateActaApertura(ActaApertura pActaApertura) throws Exception {
		this.validarActaApertura(pActaApertura);
		pActaApertura = this.entity.merge(pActaApertura);
		return pActaApertura;
	}

	public boolean deleteActaApertura(ActaApertura pActaApertura) throws Exception {
		this.validarDeleteActaApertura(pActaApertura);

		pActaApertura = this.entity.getReference(ActaApertura.class, pActaApertura.getIdActaApertura());
		this.entity.remove(pActaApertura);

		return true;
	}

	private void validarDeleteActaApertura(ActaApertura pActaApertura) throws Exception {
		if(!pActaApertura.getContratacion().getEstado().equals(Contratacion.Estado.CERRADA)) {
			throw new TrascenderComprasException(603);
		}

		if(((Long) Criterio.getInstance(this.entity, Contratacion.class).setProyeccion(Proyeccion.COUNT()).add(Restriccion.IGUAL("actaApertura", pActaApertura)).uniqueResult())
				.longValue() > 0) {
			throw new TrascenderComprasException(602);
		}
	}

	public ActaApertura getActaAperturaById(Long pId) throws Exception {
		ActaApertura locActaApertura = Criterio.getInstance(this.entity, ActaApertura.class).add(Restriccion.IGUAL("idActaApertura", pId)).uniqueResult();

		if(locActaApertura != null) {
			locActaApertura.toString();
			locActaApertura.getContratacion().toString();

			for(RepresentanteActaApertura cadaRepresentante : locActaApertura.getListaRepresentantes()) {
				cadaRepresentante.getPersona().toString();
			}
		}

		return locActaApertura;
	}

	public List<ActaApertura> findListaActaApertura(Contratacion pLicitacion, Proveedor pProveedor, Date pFechaDesde, Date pFechaHasta) {
		List<ActaApertura> locListaResultado = Criterio.getInstance(this.entity, ActaApertura.class).add(Restriccion.MENOR("fechaApertura", pFechaHasta))
				.add(Restriccion.MAYOR("fechaApertura", pFechaDesde)).add(Restriccion.IGUAL("licitacion", pLicitacion)).add(Restriccion.IGUAL("listaOferentes", pProveedor)).list();
		return locListaResultado;
	}

}

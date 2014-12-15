
package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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

import com.trascender.compras.business.interfaces.BusinessSolicitudSuministroLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroAutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroEstadoSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroSolicitudSuministro;
import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.FirmaPermisoSolicitud;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaPresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.PresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.ReglaFirmaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolSumSuplente;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;

@Stateless(name = "BusinessSolicitudSuministroLocal")
public class BusinessSolicitudSuministroBean implements BusinessSolicitudSuministroLocal {
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessSolicitudSuministroBean.serialVersionUID);
		grupo.setNombre(BusinessSolicitudSuministroBean.NOMBRE);

		Recurso solicitudSuministro = new Recurso();
		solicitudSuministro.setIdRecurso(SolicitudSuministro.serialVersionUID);
		solicitudSuministro.setNombre("Solicitud de Suministro");
		solicitudSuministro.setAtributosConsultables("Nº", "numero", "Fecha de Emisión", "fechaEmision", Tipo.FECHA, "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Area", "area", "Estado", "stringEstado", "Bienes",
				"bienes", Tipo.TEXTO_LARGO, "Urgente", "stringUrgente");
		solicitudSuministro.setClase(SolicitudSuministro.class);
		grupo.getListaRecursos().add(solicitudSuministro);

		Recurso autorizacion = new Recurso();
		autorizacion.setIdRecurso(AutorizacionSolicitudSuministro.serialVersionUID);
		autorizacion.setNombre("Autorización de Solicitudes de Suministro");
		autorizacion.setAtributosConsultables("Área", "area", "Usuarios", "stringUsuarios", Tipo.TEXTO_LARGO);
		autorizacion.setClase(AutorizacionSolicitudSuministro.class);
		grupo.getListaRecursos().add(autorizacion);

		Recurso estadoSolicitudSuministro = new Recurso();
		estadoSolicitudSuministro.setIdRecurso(EstadoSolicitudSuministro.serialVersionUID);
		estadoSolicitudSuministro.setNombre("Estado Solicitud Suministro");
		estadoSolicitudSuministro.setAtributosConsultables("Nombre", "nombre", "Inicial", "estadoInicial", Tipo.BOOLEANO, "Final", "estadoFinal", Tipo.BOOLEANO, "Modificable",
				"modificable", Tipo.BOOLEANO, "Contratación", "usadoEnContratacion", Tipo.BOOLEANO, "Movimientos", "usadoEnMovimientos", Tipo.BOOLEANO);
		estadoSolicitudSuministro.setClase(EstadoSolicitudSuministro.class);
		grupo.getListaRecursos().add(estadoSolicitudSuministro);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	/**
	 * 
	 */
	public static final long serialVersionUID = 8992782539619652680L;
	public static final String NOMBRE = "COM|Solicitudes de Suministro";
	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;

	@PersistenceContext
	private EntityManager entity;

	public BusinessSolicitudSuministroBean() {
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

	private Integer getNumeroSolicitudSuministro(SolicitudSuministro pSolicitud) {
		Calendar locCalendarInicio = Calendar.getInstance();
		locCalendarInicio.set(Calendar.MONTH, Calendar.JANUARY);
		locCalendarInicio.set(Calendar.DAY_OF_MONTH, 1);

		Calendar locCalendarFin = Calendar.getInstance();
		locCalendarFin.set(Calendar.MONTH, Calendar.DECEMBER);
		locCalendarFin.set(Calendar.DAY_OF_MONTH, 31);

		Criterio locCriterio = Criterio.getInstance(entity, SolicitudSuministro.class).add(Restriccion.MAYOR("fechaEmision", locCalendarInicio.getTime()))
				.add(Restriccion.MENOR("fechaEmision", locCalendarFin.getTime())).setProyeccion(Proyeccion.COUNT());

		if(SecurityMgr.getInstance().getMunicipalidad().isNumSolSuministroPorArea() == true) {
			locCriterio.add(Restriccion.IGUAL("area", pSolicitud.getArea()));
		}

		Long numeroSolicitud = locCriterio.uniqueResult();
		numeroSolicitud++;
		return Integer.valueOf(numeroSolicitud.intValue());
	}

	/**
	 * Agrega una solicitud de suministro. Le genera automáticamente el numero. Los numeros son correlativos por año. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro addSolicitudSuministro(
			com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro pSolicitudSuministro) throws java.lang.Exception {

		pSolicitudSuministro.setNumero(this.getNumeroSolicitudSuministro(pSolicitudSuministro));

		for(LineaSolicitudSuministro locLinea : pSolicitudSuministro.getListaLineaSolSuministro()) {
			locLinea.setSolicitudSuministro(pSolicitudSuministro);
		}
		pSolicitudSuministro.setFechaEmision(SecurityMgr.getInstance().getFechaActual().getTime());

		EstadoSolicitudSuministro locEstadoInicial = Criterio.getInstance(entity, EstadoSolicitudSuministro.class).add(Restriccion.IGUAL("estadoInicial", true)).uniqueResult();

		if(locEstadoInicial == null) {
			throw new TrascenderComprasException(63);
		}

		pSolicitudSuministro.setEstado(locEstadoInicial);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pSolicitudSuministro);

		entity.persist(pSolicitudSuministro);
		entity.flush();

		return pSolicitudSuministro;
	}

	/**
	 * Busca una lista de solicitudes de suministro. Este metodo es para la gente que puede ver todas las solicitudes de todas las areas. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroSolicitudSuministro findListadoSolicitudSuministro(FiltroSolicitudSuministro filtro) throws java.lang.Exception {
		// Si no esta ordenado por Numero, lo agregamos
		if(filtro.getMapaOrden().get("numero") == null) {
			filtro.getMapaOrden().put("numero", FiltroAbstracto.DESC);
		}
		Criterio locCriterio = Criterio.getInstance(entity, SolicitudSuministro.class).add(Restriccion.IGUAL("area", filtro.getArea()))
				.add(Restriccion.IGUAL("area.secretaria", filtro.getSecretaria())).add(Restriccion.IGUAL("listaLineaSolSuministro.bien", filtro.getBien()))
				.add(Restriccion.IGUAL("estado", filtro.getEstado())).add(Restriccion.MAYOR("fechaEmision", filtro.getFechaDesde()))
				.add(Restriccion.MENOR("fechaEmision", filtro.getFechaHasta())).add(Restriccion.IGUAL("numero", filtro.getNumero()))
				.add(Restriccion.IGUAL("urgente", filtro.getUrgente())).setDistinct(true);

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, SolicitudSuministro.serialVersionUID, "idSolicitudSuministro", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		for(Object obj : filtro.getListaResultados()) {
			SolicitudSuministro locSolicitud = (SolicitudSuministro) obj;
			locSolicitud.toString();
			if(locSolicitud.getUsuario() != null) {
				locSolicitud.getUsuario().toString();
			}
			if(locSolicitud.getArea() != null) {
				locSolicitud.getArea().toString();
			}
			if(locSolicitud.getListaPresupuestos() != null) {
				locSolicitud.getListaPresupuestos().size();
			}
		}
		return filtro;
	}

	public CuentaRfr getCuentaRfr(LineaSolicitudSuministro pLinea) {
		if(pLinea.getSolicitudSuministro() == null)
			return null;
		if(pLinea.getSolicitudSuministro().getArea() == null)
			return null;
		if(pLinea.getSolicitudSuministro().getArea().getSecretaria() == null)
			return null;
		if(pLinea.getSolicitudSuministro().getArea().getSecretaria().getCodigoImputacion() == null)
			return null;
		String[] prefijosSecretaria = pLinea.getSolicitudSuministro().getArea().getSecretaria().getCodigoImputacion().split(",");
		// Recorremos los Tipo Bien hasta que encontremos uno con Codigo de Imputacion
		Bien locBien = entity.find(Bien.class, pLinea.getBien().getIdBien());
		for(TipoBien cadaTipoBien : locBien.getListaTipoBien()) {
			if(cadaTipoBien.getCodigoImputacion() != null) {
				String[] sufijosCategoria = cadaTipoBien.getCodigoImputacion().split(",");
				for(String cadaPrefijo : prefijosSecretaria) {
					for(String cadaSufijoCategoria : sufijosCategoria) {
						String locCodigo = cadaPrefijo.trim() + cadaSufijoCategoria;
						CuentaRfr locCuenta = this.getCuentaPorCodigo(locCodigo);
						if(locCuenta != null) {
							return locCuenta;
						}
					}
				}
			}
		}
		return null;
	}

	private CuentaRfr getCuentaPorCodigo(String pCodigoImputacion) {
		return Criterio.getInstance(entity, CuentaRfr.class).add(Restriccion.IGUAL("codigoImputacionCompleto", pCodigoImputacion)).uniqueResult();
	}

	/**
	 * Busca una lista de solicitudes de suministro. Este metodo es para la gente que NO puede ver todas las solicitudes de todas las areas, solamente las del
	 * area/s propia/s. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroSolicitudSuministro findListadoSolicitudSuministroPorUsuario(FiltroSolicitudSuministro filtro) throws java.lang.Exception {
		// Si no esta ordenado por Numero, lo agregamos
		if(filtro.getMapaOrden().get("numero") == null) {
			filtro.getMapaOrden().put("numero", FiltroAbstracto.DESC);
		}

		Set listaSolicitudesProvision = new HashSet();
		List locListaRetorno = new ArrayList();
		Criterio locCriterio = Criterio.getInstance(entity, SolicitudSuministro.class).add(Restriccion.IGUAL("area", filtro.getArea()))
				.add(Restriccion.IGUAL("listaSolicitudSuministro.bien", filtro.getBien())).add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.MAYOR("fechaEmision", filtro.getFechaDesde())).add(Restriccion.MENOR("fechaEmision", filtro.getFechaHasta()));

		if(filtro.getArea() != null) {
			for(Area cadaArea : filtro.getUsuario().getListaAreas()) {
				if(filtro.getArea().getIdArea() == cadaArea.getIdArea()) {
					locCriterio.add(Restriccion.IGUAL("area", cadaArea));
					listaSolicitudesProvision.addAll(locCriterio.list());
					break;
				}
			}
		} else {
			List<Area> listaArea = new ArrayList<Area>();
			for(Area cadaArea : filtro.getUsuario().getListaAreas()) {
				cadaArea = entity.find(Area.class, cadaArea.getIdArea());
				listaArea.add(cadaArea);
			}
			locCriterio.add(Restriccion.EN("area", listaArea));
			listaSolicitudesProvision.addAll(locCriterio.list());
		}
		filtro.procesarYListar(locCriterio);

		for(Object obj : listaSolicitudesProvision) {
			SolicitudSuministro locSolicitud = (SolicitudSuministro) obj;
			locSolicitud.toString();
			if(locSolicitud.getUsuario() != null) {
				locSolicitud.getUsuario().toString();
			}
			if(locSolicitud.getArea() != null) {
				locSolicitud.getArea().toString();
			}
			// locSolicitud.setBienes(locSolicitud.getBienes());
			locListaRetorno.add(locSolicitud);
		}
		return filtro;
	}

	/**
	 * modifica una solicitud de suministro. No se puede modificar una solicitud en estado cancelada o en curso. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro updateSolicitudSuministro(
			com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro pSolicitudSuministro) throws java.lang.Exception {

		if(!pSolicitudSuministro.getEstado().isModificable()) {
			throw new TrascenderComprasException(52);
		}

		for(LineaSolicitudSuministro locLinea : pSolicitudSuministro.getListaLineaSolSuministro()) {
			locLinea.setSolicitudSuministro(pSolicitudSuministro);
		}

		// Chequear que si se cambió de Area y las solicitudes se numeran por Area, generar un numero nuevo.
		if(SecurityMgr.getInstance().getMunicipalidad().isNumSolSuministroPorArea()) {
			Area locAreaActual = Criterio.getInstance(entity, SolicitudSuministro.class)
					.add(Restriccion.IGUAL("idSolicitudSuministro", pSolicitudSuministro.getIdSolicitudSuministro())).setProyeccion(Proyeccion.PROP("area")).uniqueResult();
			if(!locAreaActual.equals(pSolicitudSuministro.getArea())) {
				pSolicitudSuministro.setNumero(this.getNumeroSolicitudSuministro(pSolicitudSuministro));
			}
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pSolicitudSuministro);
		pSolicitudSuministro = entity.merge(pSolicitudSuministro);
		entity.flush();
		return pSolicitudSuministro;
	}

	public void updateCuentaRfr(com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro pSolicitudSuministro) {

		for(LineaSolicitudSuministro cadaLinea : pSolicitudSuministro.getListaLineaSolSuministro()) {
			entity.merge(cadaLinea);
		}
	}

	/**
	 * Busca una solicitud de suministro por id Business method
	 * 
	 */
	public com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro findSolicitudSuministroByID(long pIdSolicitudSuministro) {
		SolicitudSuministro locSolicitudSuministro = entity.find(SolicitudSuministro.class, pIdSolicitudSuministro);

		if(locSolicitudSuministro != null) {
			locSolicitudSuministro.getListaLineaSolSuministro().size();
			for(PresupuestoSolicitudSuministro cadaPresupuesto : locSolicitudSuministro.getListaPresupuestos()) {
				refrescarPresupuesto(cadaPresupuesto);
			}
		}

		locSolicitudSuministro.toString();
		for(LineaSolicitudSuministro locLinea : locSolicitudSuministro.getListaLineaSolSuministro()) {
			locLinea.getBien().toString();
			for(LineaMovimientoMercaderia cadaLinea : locLinea.getListaLineasMovimientosMercaderia()) {
				cadaLinea.toString();
				cadaLinea.getStock().toString();
			}
			if(locLinea.getListaLineaContratacion() != null) {
				locLinea.getListaLineaContratacion().size();
			}

			if(locLinea.getCuenta() != null) {
				locLinea.getCuenta().toString();
			}

			if(locLinea.getLineaOrdenCompra() != null) {
				locLinea.getLineaOrdenCompra().toString();
			}
		}
		for(FirmaPermisoSolicitud cadaFirma : locSolicitudSuministro.getListaFirmaPermiso()) {
			cadaFirma.toString();
		}
		locSolicitudSuministro.getListaLogsAuditoria().size();

		return locSolicitudSuministro;
	}

	private void refrescarPresupuesto(PresupuestoSolicitudSuministro pPresupuesto) {
		pPresupuesto.toString();
		for(LineaPresupuestoSolicitudSuministro cadaRenglonPresupuesto : pPresupuesto.getListaLineasPresupuestoSolicitud()) {
			cadaRenglonPresupuesto.toString();
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local" Agrego una autorizacion de solicitud de suministros
	 * @param pAutorizacion
	 * @return
	 * @throws Exception
	 */
	public AutorizacionSolicitudSuministro addAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws Exception {
		this.validarAutorizacionSolicitudSuministro(pAutorizacion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pAutorizacion);
		entity.persist(pAutorizacion);
		entity.flush();

		return pAutorizacion;
	}

	private void validarAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws TrascenderException {
		if(pAutorizacion.getArea() != null) {
			Long locCantidad = Criterio.getInstance(entity, AutorizacionSolicitudSuministro.class)
					.add(Restriccion.DISTINTO("idAutorizacionSolicitudSuministro", pAutorizacion.getIdAutorizacionSolicitudSuministro()))
					.add(Restriccion.IGUAL("area", pAutorizacion.getArea())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

			if(locCantidad > 0) {
				throw new TrascenderComprasException(150);
			}
		}

		for(UsuarioAutorizadorSolicitudSuministro cadaUsuario : pAutorizacion.getListaUsuarios()) {
			cadaUsuario.setAutorizacion(pAutorizacion);
		}
	}

	public AutorizacionSolicitudSuministro updateAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws Exception {
		this.validarAutorizacionSolicitudSuministro(pAutorizacion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pAutorizacion);

		entity.merge(pAutorizacion);

		this.entity.flush();

		return pAutorizacion;
	}

	public AutorizacionSolicitudSuministro getAutorizacionSolicitudSuministroByID(Long pId) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entity, AutorizacionSolicitudSuministro.class).add(Restriccion.IGUAL("idAutorizacionSolicitudSuministro", pId));

		AutorizacionSolicitudSuministro locAutorizacionSolicitudSuministro = locCriterio.uniqueResult();

		if(locAutorizacionSolicitudSuministro != null) {
			locAutorizacionSolicitudSuministro.getListaLogsAuditoria().size();
			for(UsuarioAutorizadorSolicitudSuministro cadaUsuarioAutorizador : locAutorizacionSolicitudSuministro.getListaUsuarios()) {
				cadaUsuarioAutorizador.getListaEstadosFirmables().size();
				cadaUsuarioAutorizador.getListaEstadosFinalizables().size();
				cadaUsuarioAutorizador.getListaEstadosFinalizacion().size();
			}
			for(ReglaFirmaSolicitudSuministro cadaRegla : locAutorizacionSolicitudSuministro.getListaReglasFirma()) {
				cadaRegla.toString();
			}
		}

		return locAutorizacionSolicitudSuministro;
	}

	public EstadoSolicitudSuministro getEstadoSolicitudSuministroByID(Long pId) throws Exception {
		EstadoSolicitudSuministro locEstadoSolicitudSuministro = entity.find(EstadoSolicitudSuministro.class, pId);

		if(locEstadoSolicitudSuministro != null) {
			locEstadoSolicitudSuministro.getListaLogsAuditoria().size();
		}

		return locEstadoSolicitudSuministro;
	}

	/**
	 * Borra una autorizacion de solicitud de suministro Business method
	 * 
	 * @ejb.interface-method view-type="local" Elimina una autorizacion de solicitudes de suministros
	 * @param pAutorizacion
	 * @throws Exception
	 */
	public void deleteAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws Exception {
		pAutorizacion = entity.merge(pAutorizacion);
		entity.remove(pAutorizacion);
	}

	/**
	 * Busca la lista de autorizaciones de solicitud de suministro Business method
	 * 
	 * @ejb.interface-method view-type="local" Obtengo una lista de autorizaciones de solicitudes de suministros
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public FiltroAutorizacionSolicitudSuministro findListaAutorizacionSolicitudSuministro(FiltroAutorizacionSolicitudSuministro filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, AutorizacionSolicitudSuministro.class).add(Restriccion.IGUAL("area", filtro.getArea()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, AutorizacionSolicitudSuministro.serialVersionUID, "idAutorizacionSolicitudSuministro", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		for(AutorizacionSolicitudSuministro aut : filtro.getListaResultados()) {
			aut.toString();
			for(ReglaFirmaSolicitudSuministro cadaRegla : aut.getListaReglasFirma()) {
				cadaRegla.getListaUsuarios().size();
			}
			for(UsuarioAutorizadorSolicitudSuministro cadaUsuario : aut.getListaTodosLosUsuarios()) {
				cadaUsuario.getListaEstadosFirmables().size();
			}
		}

		return filtro;
	}

	public List<LineaSolicitudSuministro> findListaLineasSolicitudSuministro(boolean filtrarPorCodCiiu, boolean filtrarPorCategoria, List<Proveedor> pListaProveedores, Bien pBien,
			SolicitudSuministro pSolicitud) throws TrascenderException {
		// if (pProveedor == null){
		// throw new TrascenderComprasException(61);
		// }
		//
		// if (pProveedor.getListaCodigosCiiu() == null ||
		// pProveedor.getListaCodigosCiiu().isEmpty()){
		// throw new TrascenderComprasException(62);
		// }
		Criterio locCriterio = Criterio
				.getInstance(entity, LineaSolicitudSuministro.class)
				.add(Restriccion.OR(Restriccion.ESTA_VACIO("listaLineaContratacion"), Restriccion.JPQL("e.cantidad > (select sum(lc.cantidad) from LineaContratacion lc "
						+ "join lc.listaLineasSolicitudSuministro cadaLineaSolSum "/* rela on lc.id_linea_contratacion = rela.id_linea_contratacion */+ "where e = cadaLineaSolSum)")))
				.add(Restriccion.IGUAL("solicitudSuministro.estado.usadoEnContratacion", Boolean.TRUE)).add(Restriccion.IGUAL("bien", pBien))
				.add(Restriccion.IGUAL("solicitudSuministro", pSolicitud));

		/**
		 * Busca Lineas de Solicitud que los Proveedores pasados por paramatros puedan satisfacer, según correspondan sus Codigos CIIU con los de los bienes.
		 */
		if(pListaProveedores != null && !pListaProveedores.isEmpty()) {
			if(filtrarPorCodCiiu) {
				Set<CodigoCiiu> locListaCodigosCiiu = new HashSet<CodigoCiiu>();
				for(Proveedor cadaProveedor : pListaProveedores) {
					locListaCodigosCiiu.addAll(cadaProveedor.getListaCodigosCiiu());
				}
				locCriterio.crearAlias("bien.listaCodigosCiiu", "cadaCodigoCiiu").add(Restriccion.EN("cadaCodigoCiiu", locListaCodigosCiiu));
			}
			if(filtrarPorCategoria) {
				Set<TipoBien> locListaTipoBien = new HashSet<TipoBien>();
				for(Proveedor cadaProveedor : pListaProveedores) {
					locListaTipoBien.addAll(cadaProveedor.getListaTipoBien());
				}
				locCriterio.crearAlias("bien.listaTipoBien", "cadaTipoBien").add(Restriccion.EN("cadaTipoBien", locListaTipoBien));
			}
		}

		List<LineaSolicitudSuministro> locListaResultado = locCriterio.list();

		for(LineaSolicitudSuministro cadaLineaSolicitud : locListaResultado) {
			cadaLineaSolicitud.toString();
			cadaLineaSolicitud.getListaLineasPresupuestoSolicitudSuministro().size();
			cadaLineaSolicitud.getListaLineaContratacion().size();
			cadaLineaSolicitud.getListaLineasMovimientosMercaderia().size();
		}

		return locListaResultado;
	}

	public void finalizarSolicitud(SolicitudSuministro pSolicitud) throws TrascenderException {
		// if (pSolicitud.getEstado() != Estado.ANULADA &&
		// pSolicitud.getEstado() != Estado.CUMPLIDA){
		// throw new TrascenderComprasException(51);
		// }
		TrascenderEnverListener.setValoresEnAuditoriaBean(pSolicitud);
		entity.merge(pSolicitud);
		entity.flush();
	}

	public SolicitudSuministro firmarSolicitudSuminstro(SolicitudSuministro pSolicitud, FirmaPermisoSolicitud pFirma) throws TrascenderException {
		AutorizacionSolicitudSuministro locAutorizacion = this.validarFirma(pSolicitud, pFirma.getFirmaPermiso().getUsuario());
		pSolicitud.getListaFirmaPermiso().add(pFirma);
		pFirma.setSolicitudSuministro(pSolicitud);
		locAutorizacion.validarSolicitudSegunReglas(pSolicitud);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pSolicitud);
		entity.merge(pSolicitud);
		entity.flush();
		return pSolicitud;
	}

	/**
	 * Valida Persona firmante, cantidad de firma, Estado final de la Solicitud, etc.
	 * 
	 * @param pSolicitudSuministro
	 * @param pFirma
	 * @throws TrascenderException
	 */
	public AutorizacionSolicitudSuministro validarFirma(SolicitudSuministro pSolicitudSuministro, Usuario pUsuario) throws TrascenderException {
		AutorizacionSolicitudSuministro locAutorizacion = null;
		pSolicitudSuministro = this.findSolicitudSuministroByID(pSolicitudSuministro.getIdSolicitudSuministro());
		if(pSolicitudSuministro.yaFirmo(pUsuario)) {
			throw new TrascenderComprasException(927);
		}
		// Busca la Autorizacion (con o sin Area) para la solicitud.
		locAutorizacion = Criterio.getInstance(entity, AutorizacionSolicitudSuministro.class)
				.add(Restriccion.OR(Restriccion.IGUAL("area", pSolicitudSuministro.getArea()), Restriccion.NULO("area"))).add(Restriccion.IGUAL("listaUsuarios.usuario", pUsuario))
				.add(Orden.DESC("area"))
				// Ordeno
				.add(Restriccion.IGUAL("listaReglasFirma.listaUsuarios.usuario", pUsuario)).add((Restriccion.IGUAL("listaReglasFirma.urgente", pSolicitudSuministro.isUrgente())))
				.setModoDebug(true).uniqueResult();

		if(locAutorizacion == null) {
			throw new TrascenderComprasException(64);
		}
		// Validar Usuarios, fechas, estados.
		locAutorizacion.validarFirma(pUsuario, pSolicitudSuministro);
		return locAutorizacion;
	}

	public EstadoSolicitudSuministro addEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderComprasException {
		validarEstadoSolSum(pEstadoSolSum);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pEstadoSolSum);
		pEstadoSolSum = this.entity.merge(pEstadoSolSum);
		this.entity.flush();

		return pEstadoSolSum;
	}

	private void validarEstadoSolSum(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderComprasException {
		Criterio locCriterio = Criterio.getInstance(entity, EstadoSolicitudSuministro.class).add(Restriccion.IGUAL("nombre", pEstadoSolSum.getNombre()))
				.add(Restriccion.DISTINTO("idEstadoSolicitudSuministro", pEstadoSolSum.getIdEstadoSolicitudSuministro())).setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(65);
		}
		if(pEstadoSolSum.isEstadoInicial()) {
			locCriterio = Criterio.getInstance(entity, EstadoSolicitudSuministro.class).add(Restriccion.IGUAL("estadoInicial", true))
					.add(Restriccion.DISTINTO("idEstadoSolicitudSuministro", pEstadoSolSum.getIdEstadoSolicitudSuministro())).setProyeccion(Proyeccion.COUNT());
			cantidad = locCriterio.uniqueResult();
			if(cantidad > 0) {
				throw new TrascenderComprasException(66);
			}
		}
	}

	public void updateEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderComprasException {
		validarEstadoSolSum(pEstadoSolSum);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pEstadoSolSum);

		pEstadoSolSum = this.entity.merge(pEstadoSolSum);

		this.entity.flush();
	}

	public void deleteEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderComprasException {
		validarBorradoSolicitudSuministro(pEstadoSolSum);
		pEstadoSolSum = this.entity.merge(pEstadoSolSum);
		this.entity.remove(pEstadoSolSum);
	}

	public FiltroEstadoSolicitudSuministro findListaEstadoSolSum(FiltroEstadoSolicitudSuministro pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entity, EstadoSolicitudSuministro.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, EstadoSolicitudSuministro.serialVersionUID, "idEstadoSolicitudSuministro", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	public List<EstadoSolicitudSuministro> findListaEstadoSolSumFinal() {
		Criterio locCriterio = Criterio.getInstance(entity, EstadoSolicitudSuministro.class).add(Restriccion.IGUAL("estadoFinal", true));
		return locCriterio.list();
	}

	private void validarBorradoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderComprasException {
		Criterio locCriterio = Criterio.getInstance(this.entity, SolicitudSuministro.class).add(Restriccion.IGUAL("estado", pEstadoSolSum)).setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(67);
		}
		locCriterio = Criterio.getInstance(this.entity, ReglaFirmaSolicitudSuministro.class).add(Restriccion.IGUAL("estado", pEstadoSolSum)).setProyeccion(Proyeccion.COUNT());
		cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(68);
		}
	}

	public List<EstadoSolicitudSuministro> getListaEstadosFinalizacion(Usuario pUsuario, SolicitudSuministro pSolicitud) {
		Criterio locCriterio = Criterio.getInstance(entity, UsuarioAutorizadorSolicitudSuministro.class).add(Restriccion.IGUAL("usuario", pUsuario))
				.add(Restriccion.OR(Restriccion.NULO("autorizacion.area"), Restriccion.IGUAL("autorizacion.area", pSolicitud.getArea())))
				.add(Restriccion.MIEMBRO_DE("listaEstadosFinalizables", pSolicitud.getEstado())).crearAlias("listaEstadosFinalizacion", "cadaEstado")
				.setProyeccion(Proyeccion.PROP("cadaEstado"));

		List locListaEstadosFinalizacion = locCriterio.list();

		Date hoy = new Date();
		locCriterio = Criterio.getInstance(entity, UsuarioAutorizadorSolSumSuplente.class).add(Restriccion.IGUAL("usuario", pUsuario)).add(Restriccion.MENOR("fechaDesde", hoy))
				.add(Restriccion.MAYOR("fechaHasta", hoy)).add(Restriccion.OR(Restriccion.NULO("autorizacion.area"), Restriccion.IGUAL("autorizacion.area", pSolicitud.getArea())))
				.add(Restriccion.MIEMBRO_DE("usuarioSuplido.listaEstadosFinalizables", pSolicitud.getEstado())).crearAlias("usuarioSuplido.listaEstadosFinalizacion", "cadaEstado")
				.setProyeccion(Proyeccion.PROP("cadaEstado"));

		locListaEstadosFinalizacion.addAll(locCriterio.list());

		return locListaEstadosFinalizacion;
	}

	public boolean validarOperarSolicitudesUrgentes(Area pArea, Usuario pUsuario) {
		Long locCantidad = Criterio.getInstance(entity, UsuarioAutorizadorSolicitudSuministro.class)
				.add(Restriccion.OR(Restriccion.IGUAL("autorizacion.area", pArea), Restriccion.NULO("autorizacion.area"))).add(Restriccion.IGUAL("usuario", pUsuario))
				.add(Restriccion.IGUAL("operaUrgentes", true)).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(locCantidad != null && locCantidad > 0) {
			return true;
		}
		Date hoy = new Date();
		locCantidad = Criterio.getInstance(entity, UsuarioAutorizadorSolSumSuplente.class)
				.add(Restriccion.OR(Restriccion.IGUAL("autorizacion.area", pArea), Restriccion.NULO("autorizacion.area"))).add(Restriccion.IGUAL("usuario", pUsuario))
				.add(Restriccion.IGUAL("usuarioSuplido.operaUrgentes", true)).add(Restriccion.MENOR("fechaDesde", hoy)).add(Restriccion.MAYOR("fechaHasta", hoy))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(locCantidad != null && locCantidad > 0) {
			return true;
		}

		return false;
	}

	public List<LineaOrdenCompra> getListaLineaOrdenCompraPorSolicitud(SolicitudSuministro pSolicitudSuministro) {
		Criterio locCriterio = Criterio.getInstance(entity, LineaOrdenCompra.class)
				.add(Restriccion.IGUAL("lineaContratacion.listaLineasSolicitudSuministro.solicitudSuministro", pSolicitudSuministro)).setModoDebug(true);
		List<LineaOrdenCompra> locListaResultado = locCriterio.list();
		return locListaResultado;
	}
}

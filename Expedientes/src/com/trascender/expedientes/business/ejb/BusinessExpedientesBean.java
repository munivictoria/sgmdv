/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.business.ejb;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.expedientes.business.interfaces.BusinessExpedientes;
import com.trascender.expedientes.enums.Estado;
import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Fase;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.Hito;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.expedientes.recurso.persistent.VersionEjecucionReporte;
import com.trascender.expedientes.reportes.AltasExpedientesDS;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "BusinessExpedientes")
public class BusinessExpedientesBean implements BusinessExpedientes, Serializable {

	private static final String RESPONSABLE_TRAMITE = "responsableTramite";
	private static final String TRAMITE_PROCEDIMIENTO = "tramiteProcedimiento";
	private static final String RESPONSABLE_FASE = "responsableFase";
	private static final String FASE_PROCEDIMIENTO = "faseProcedimiento";
	@SuppressWarnings("unused")
	private static final String RESPONSABLE_PROCEDIMIENTO = "responsableProcedimiento";
	private static final String PROCEDIMIENTO = "procedimiento";

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	@PersistenceContext(name = "Vipians")
	private EntityManager entity;
	private static final long serialVersionUID = 2832417949467777426L;
	public static final String NAME = "EXP|Adm. de Expedientes";

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre(NAME);

		Recurso expediente = new Recurso();
		expediente.setIdRecurso(Expediente.serialVersionUID);
		expediente.setNombre("Expediente");
		expediente.setAtributosConsultables("Nº Registro", "nroRegistro", "Fecha Registro", "fechaRegistro", Tipo.FECHA, "Procedimiento", "stringProcedimiento", "Interesado",
				"stringInteresado", "Estado", "estado", "Fase Actual", "stringFaseActivaSegunPermisos");
		expediente.setClase(Expediente.class);

		grupo.getListaRecursos().add(expediente);
		SecurityMgr.getInstance().addGrupo(grupo);
	}
	
	@EJB
	private BusinessParametroLocal businessParametro;

	@Override
	public void addExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws Exception, RemoteException {
		Procedimiento locProcedimiento = (Procedimiento) pExpediente.getNodoProcedimiento();

		actualizarFaseActivaActual(pExpediente, pComentario, pUsuario);
		
		Long siguienteNro = businessParametro.getNumeroSiguiente(locProcedimiento.getNumerador());
		pExpediente.setNroRegistro(siguienteNro);
		
		pExpediente = entity.merge(pExpediente);
	}

	@Override
	public void updateExpediente(Expediente pExpediente, String pComentario, Usuario pUsuario) throws Exception, RemoteException {
		Expediente expBD = getExpedientePorId(pExpediente.getIdNodoExpediente(), pUsuario);

		// actualizarFaseActivaActual(pExpediente, pComentario, pUsuario);
		registrarCambioEstadoExpediente(pExpediente, pComentario, expBD.getEstado(), pUsuario);
		entity.merge(pExpediente);
	}

	@Override
	public void deleteExpediente(Expediente pExpediente, Usuario pUsuario) throws Exception, RemoteException {
		pExpediente.setEstado(Estado.ELIMINADO);
		entity.merge(pExpediente);
	}

	@Override
	public Expediente getExpedientePorId(long pId, Usuario pUsuario) throws Exception, RemoteException {
		Expediente locExpediente = (Expediente) Criterio.getInstance(this.entity, Expediente.class)
				.add(Restriccion.IGUAL("idNodoExpediente", pId))
				.uniqueResult();

		locExpediente.toString();
		locExpediente.getListaNodosExpedientes().size();
		locExpediente.getInteresado().toString();
		if(locExpediente.getInteresado().getDomicilio() != null) {
			locExpediente.getInteresado().getDomicilio().toString();
		}
		
		for(NodoExpediente f : locExpediente.getListaNodosExpedientes()) {
			f.getListaNodosExpedientes().size();
			FaseProcedimiento cadaProcedimiento = (FaseProcedimiento) f.getNodoProcedimiento();
			cadaProcedimiento.getListaFasesEspeciales().size();
			
			for(NodoExpediente tramite : f.getListaNodosExpedientes()) {
				for(Documento cadaDoc : ((Tramite) tramite).getListaDocumentos()) {
					cadaDoc.getListaVersionesEjecucionReporte().size();
					for(VersionEjecucionReporte cadaVersion : cadaDoc.getListaVersionesEjecucionReporte()) {
						cadaVersion.getListaParametrosValuadosReporte().size();
					}
				}
			}
		}

		getHitos(locExpediente);
		Responsable locResponsable = locExpediente.getNodoProcedimiento().getResponsable();
		getResponsablesLazy(locResponsable);

		locExpediente.setFaseActivaSegunPermisos(pUsuario);

		return locExpediente;
	}

	// private Long getNumeroRegistroExpediente(Expediente pExpediente) {
	// Calendar locCalendarInicio = Calendar.getInstance();
	// locCalendarInicio.set(Calendar.MONTH, Calendar.JANUARY);
	// locCalendarInicio.set(Calendar.DAY_OF_MONTH, 1);
	//
	// Calendar locCalendarFin = Calendar.getInstance();
	// locCalendarFin.set(Calendar.MONTH, Calendar.DECEMBER);
	// locCalendarFin.set(Calendar.DAY_OF_MONTH, 31);
	//
	// Criterio locCriterio = Criterio.getInstance(entity, Expediente.class).add(Restriccion.MAYOR("fechaRegistro", locCalendarInicio.getTime()))
	// .add(Restriccion.MENOR("fechaRegistro", locCalendarFin.getTime())).setProyeccion(Proyeccion.PROP("MAX(nroRegistro)").SIN_PROCESAR_ENTIDADES());
	//
	// Long numeroExpediente = locCriterio.uniqueResult();
	// if(numeroExpediente == null) {
	// numeroExpediente = 0l;
	// }
	// numeroExpediente++;
	//
	// return numeroExpediente;
	// }

	private void getResponsablesLazy(Responsable pResponsable) {
		if(pResponsable != null) {
			pResponsable.getAreas().size();
			pResponsable.getUsuarios().size();
			pResponsable.getListaAreasResponsables().size();
			pResponsable.getListaUsuariosResponsables().size();
			pResponsable.getListaUsuariosExtensores().size();
		}
	}

	@Override
	public FiltroExpediente findListaExpediente(FiltroExpediente pFiltro) throws Exception, RemoteException {
		if(pFiltro.getMapaOrden().get("nroRegistro") == null) {
			pFiltro.getMapaOrden().put("nroRegistro", FiltroAbstracto.DESC);
		}
		Criterio locCriterio = Criterio.getInstance(entity, Expediente.class).add(Restriccion.ILIKE("asunto", pFiltro.getAsunto()))
				.add(Restriccion.IGUAL("nodoProcedimiento", pFiltro.getProcedimiento())).add(Restriccion.MAYOR("fechaRegistro", pFiltro.getFechaRegistroDesde()))
				.add(Restriccion.MENOR("fechaRegistro", pFiltro.getFechaRegistroHasta())).add(Restriccion.IGUAL("nroRegistro", pFiltro.getNroRegistro()))
				.add(Restriccion.IGUAL("interesado", pFiltro.getInteresado())).add(Restriccion.IGUAL("estado", pFiltro.getEstado()));

		pFiltro.procesarYListar(locCriterio);

		for(Expediente cadaExpediente : pFiltro.getListaResultados()) {
			cadaExpediente.setFaseActivaSegunPermisos(pFiltro.getUsuario());
		}

		return pFiltro;
	}

	@Override
	public Tramite getTramitePorId(long idTramite) {
		Tramite locTramite = (Tramite) Criterio.getInstance(this.entity, Tramite.class).add(Restriccion.IGUAL("idNodoExpediente", idTramite)).uniqueResult();

		locTramite.toString();
		locTramite.getListaDocumentos().size();
		getHitos(locTramite);

		Responsable locResponsable = locTramite.getNodoProcedimiento().getResponsable();
		getResponsablesLazy(locResponsable);

		return entity.find(Tramite.class, idTramite);
	}

	@Override
	public void updateDocumentoSalida(Documento pDocumento, Usuario pUsuario) {
		registrarHitoDocumentoSalida(pDocumento, pUsuario, true);
		entity.merge(pDocumento);
	}

	@Override
	public void registrarHitoDocumentoSalida(Documento pDocumento, Usuario pUsuario, boolean pProcesando) {
		Tramite locTramite = (Tramite) pDocumento.getTramite();
		
		String locAccionHito = null;
		if(pProcesando) {
			locAccionHito = "procesado (" + String.valueOf(pDocumento.getListaVersionesEjecucionReporte().size()) + ")";
		} else {
			locAccionHito = "impreso";
		}
		// String locAccionHito = pProcesando ? "procesado" : "impreso";
		
		Hito locHito = new Hito();
		locHito.setNombre(locTramite.getClass().getName());
		locHito.setDescripcion("El documento de salida '" + pDocumento.getNombre() + "' fue " + locAccionHito + ".");
		locHito.setFecha(new Date());
		locHito.setNodoExpediente(locTramite);
		locHito.setUsuario(pUsuario);
		entity.merge(locHito);
	}

	@Override
	public List<DiaFeriado> getDiasFeriadosEntre(Date date1, Date date2) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, DiaFeriado.class);

		if(date1 != null) {
			locCriterio.add(Restriccion.MAYOR("fecha", date1));
		}
		if(date2 != null) {
			locCriterio.add(Restriccion.MENOR("fecha", date2));
		}
		locCriterio.add(Orden.ASC("fecha"));

		return locCriterio.list();
	}

	@Override
	public List<Expediente> getListaExpedientePorTramites() throws Exception {
		String tramiteRA = "tramiteResponsableArea";
		String tramiteRU = "tramiteResponsableUsuario";
		String faseRA = "faseResponsableArea";
		String faseRU = "faseResponsableUsuario";
		String expRA = "expResponsableAera";
		String expRU = "expResponsableUsuario";

		Criterio locCriterio = Criterio.getInstance(entity, Tramite.class).crearAliasLeft("nodoPadre", "fase").crearAlias("fase.nodoPadre", "expediente")
				.crearAliasLeft("nodoProcedimiento", TRAMITE_PROCEDIMIENTO).crearAliasLeft(TRAMITE_PROCEDIMIENTO + ".responsable", RESPONSABLE_TRAMITE)
				.crearAliasLeft(RESPONSABLE_TRAMITE + ".areas", tramiteRA).crearAliasLeft(RESPONSABLE_TRAMITE + ".usuarios", tramiteRU)
				.crearAliasLeft("fase" + ".nodoProcedimiento", FASE_PROCEDIMIENTO).crearAliasLeft(FASE_PROCEDIMIENTO + ".responsable", RESPONSABLE_FASE)
				.crearAliasLeft(RESPONSABLE_FASE + ".areas", faseRA).crearAliasLeft(RESPONSABLE_FASE + ".usuarios", faseRU)
				.crearAliasLeft("expediente" + ".nodoProcedimiento", PROCEDIMIENTO).crearAliasLeft(PROCEDIMIENTO + ".responsable", "responsableExpediente")
				.crearAliasLeft("responsableExpediente" + ".areas", expRA).crearAliasLeft("responsableExpediente" + ".usuarios", expRU).setProyeccion(Proyeccion.PROP("expediente"))
				.setDistinct(true);

		return locCriterio.list();
	}

	@Override
	public List<Expediente> getListaExpedienteSoyResponsable(long llave) throws Exception {
		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(llave);
		Set<Area> locListaAreas = getAreasUsuario(locUsuario);

		List<Expediente> locResultado = new ArrayList<Expediente>();

		// Responsable del procedimiento
		List<Expediente> listaTemporal = Criterio.getInstance(entity, Expediente.class)
				//.add(Restriccion.IGUAL("estado", Estado.CERRADO).NEGADA())
				.add(Restriccion.AND(Restriccion.DISTINTO("estado", Estado.CERRADO), Restriccion.DISTINTO("estado", Estado.ELIMINADO)))
				.crearAliasLeft("nodoProcedimiento.responsable.listaUsuariosResponsables.usuario", "cadaUsuario")
				.crearAliasLeft("nodoProcedimiento.responsable.listaAreasResponsables.area", "cadaArea")
				.setDistinct(true)
				.list();

		locResultado.addAll(listaTemporal);

		// Responsable de la fase actual.
		listaTemporal = Criterio.getInstance(entity, Expediente.class)
				//.add(Restriccion.IGUAL("estado", Estado.CERRADO).NEGADA())
				.add(Restriccion.AND(Restriccion.DISTINTO("estado", Estado.CERRADO), Restriccion.DISTINTO("estado", Estado.ELIMINADO)))
				.add(Restriccion.NOT(Restriccion.EN("e", locResultado)))
				.crearAliasLeft("faseActual.nodoProcedimiento.responsable.listaUsuariosResponsables.usuario", "cadaUsuario")
				.crearAliasLeft("faseActual.nodoProcedimiento.responsable.listaAreasResponsables.area", "cadaArea")
				.add(Restriccion.OR(Restriccion.IGUAL("cadaUsuario", locUsuario), Restriccion.EN("cadaArea", locListaAreas)))
				.setDistinct(true)
				.list();

		locResultado.addAll(listaTemporal);

		// Responsable del los tramites de la fase actual.
		listaTemporal = Criterio.getInstance(entity, Expediente.class)
				//.add(Restriccion.IGUAL("estado", Estado.CERRADO).NEGADA())
				.add(Restriccion.AND(Restriccion.DISTINTO("estado", Estado.CERRADO), Restriccion.DISTINTO("estado", Estado.ELIMINADO)))
				.add(Restriccion.NOT(Restriccion.EN("e", locResultado)))
				.crearAliasLeft("faseActual.listaNodosExpedientes.nodoProcedimiento.responsable.listaUsuariosResponsables.usuario", "cadaUsuario")
				.crearAliasLeft("faseActual.listaNodosExpedientes.nodoProcedimiento.responsable.listaAreasResponsables.area", "cadaArea")
				.add(Restriccion.OR(Restriccion.IGUAL("cadaUsuario", locUsuario), Restriccion.EN("cadaArea", locListaAreas)))
				.add(Restriccion.IGUAL("faseActual.listaNodosExpedientes.estadoTramite.cierraTramite", false))
				.setDistinct(true)
				.list();

		locResultado.addAll(listaTemporal);
		
		for(Expediente cadaExpediente : locResultado) {
			cadaExpediente.setFaseActivaSegunPermisos(locUsuario);
		}
		
		return locResultado;
	}

	@SuppressWarnings("unused")
	private Restriccion getRestriccionVencida(Boolean plazoVencido) {
		if(plazoVencido != null && plazoVencido) {
			Calendar locCalendar = Calendar.getInstance();
			locCalendar.set(Calendar.HOUR_OF_DAY, 23);
			locCalendar.set(Calendar.MINUTE, 59);
			locCalendar.set(Calendar.SECOND, 59);
			locCalendar.set(Calendar.MILLISECOND, 999);

			return Restriccion.MENOR("plazo.fechaFin", locCalendar.getTime());
		}

		return null;
	}

	private Set<Area> getAreasUsuario(Usuario pUsuario) {
		return new HashSet<Area>(pUsuario.getListaAreas());
	}

	private void getHitos(NodoExpediente nodoE) {
		nodoE.getListaHitos().size();
		for(NodoExpediente ne : nodoE.getListaNodosExpedientes()) {
			getHitos(ne);
		}
	}

	@Override
	public void actualizarFaseActivaActual(Expediente pExpediente, String pComentario, Usuario pUsuario) {
		Fase locNuevaActiva = (Fase) pExpediente.getListaFasesOrdenada().get(pExpediente.getIndexActiva());

		if(!pExpediente.getFaseActual().equals(locNuevaActiva)) {
			pExpediente.setFaseActual(locNuevaActiva);
			String descripcion = "Fase activa pasó a " + locNuevaActiva.getPlantilla().toString();
			pExpediente.addHito(descripcion, pComentario, pUsuario);
		}
	}

	private void registrarCambioEstadoExpediente(Expediente pExpediente, String pComentario, Estado pAnteriorEstadoExpediente, Usuario pUsuario) {
		Estado nuevoEstado = pExpediente.getEstado();
		if(!nuevoEstado.equals(pAnteriorEstadoExpediente)) {
			pExpediente.addHito("Expediente pasó a estado " + nuevoEstado, pComentario, pUsuario);
			if(nuevoEstado.equals(Estado.CERRADO)) {
				pExpediente.setFechaFin(new Date());
				pExpediente.setFaseActual(null);
			}
		}
	}

	public JasperPrint getReporteAltasExpedientes(Expediente pExpediente, Usuario pUsuario) throws Exception {
		Expediente locExpediente = entity.find(Expediente.class, pExpediente.getIdNodoExpediente());
		AltasExpedientesDS locAltasExpedientesDS = new AltasExpedientesDS(locExpediente, pUsuario);
		String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes() + "Expediente/";
		File fileReporte = new File(rutaReportes + locAltasExpedientesDS.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(fileReporte);
		reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(reporte, locAltasExpedientesDS.getMapaParametros(), locAltasExpedientesDS);
		} catch(Exception e) {
			e.printStackTrace();
		}

		entityManager.clear();

		return jasperPrint;
	}

	@Override
	public Long getExpedientePorNodoProcedimiento(long idNodoProcedimiento) {
		Criterio locCriterio = Criterio.getInstance(this.entity, NodoExpediente.class)
				.add(Restriccion.IGUAL("nodoProcedimiento.idNodoProcedimiento", idNodoProcedimiento))
				.setProyeccion(Proyeccion.COUNT()).setModoDebug(true);
		Long cant = locCriterio.uniqueResult();
		return cant;
	}

}
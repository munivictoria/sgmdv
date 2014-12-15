
package com.trascender.framework.business.ejb;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ConfiguracionAtributoTabla;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;
import com.trascender.framework.recurso.persistent.ParametroSistema;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoBooleano;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoCadena;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValidacionDinamica;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "ejb/BusinessParametro")
public class BusinessParametroBean implements BusinessParametroLocal {

	static {
		Grupo grupo = new Grupo();
		grupo.setNombre(BusinessParametroBean.NOMBRE);
		grupo.setId(BusinessParametroBean.serialVersionUID);

		Recurso recursoAtributos = new Recurso();
		recursoAtributos.setIdRecurso(PlantillaAtributoDinamico.serialVersionUID);
		recursoAtributos.setNombre("Atributos Dinámicos");
		recursoAtributos.setAtributosConsultables("Nombre", "nombre", "Nombre Recurso", "nombreRecurso", "Tipo", "tipo");
		recursoAtributos.setClase(PlantillaAtributoDinamico.class);

		Recurso recursoConfiguracionRec = new Recurso();
		recursoConfiguracionRec.setIdRecurso(ConfiguracionRecurso.serialVersionUID);
		recursoConfiguracionRec.setNombre("Configuración de Recursos.");
		recursoConfiguracionRec.setAtributosConsultables("Recurso", "nombreRecurso", "Alias", "nombreAlias");
		recursoConfiguracionRec.setClase(ConfiguracionRecurso.class);

		// Recurso recursoReporte = new Recurso();
		// recursoReporte.setIdRecurso(ReportesJasper.serialVersionUID);
		// recursoReporte.setNombre("Reportes Jasper");
		// recursoReporte.setAtributosConsultables("Nombre", "nombre");

		grupo.getListaRecursos().add(recursoConfiguracionRec);
		grupo.getListaRecursos().add(recursoAtributos);
		// grupo.getListaRecursos().add(recursoReporte);
		SecurityMgr.getInstance().addGrupo(grupo);
	}

	private static final long serialVersionUID = -575137816902087037L;
	private static final String NOMBRE = "FRM|Adm. Configuración de Sistema.";

	@PersistenceContext(name = "vipians")
	private EntityManager entity;

	public void setSessionContext(SessionContext pArg0) throws EJBException, RemoteException {

	}

	public ParametroSistema getParametroPorId(long pIdParametro) throws Exception {

		ParametroSistema locParametro = entity.find(ParametroSistema.class, pIdParametro);
		locParametro.toString();
		if(locParametro.getValor() != null) {
			locParametro.getValor().toString();
		}
		return locParametro;
	}

	public ParametroSistema getParametroPorCodigo(String pCodigo) throws Exception {

		return (ParametroSistema) Criterio.getInstance(entity, ParametroSistema.class).add(Restriccion.IGUAL("codigo", pCodigo)).uniqueResult();
	}

	public void addParametro(ParametroSistema pParametro) throws Exception {
		this.validarParametro(pParametro);
		this.entity.persist(pParametro);

	}

	private void validarParametro(ParametroSistema pParametro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ParametroSistema.class)
				.add(Restriccion.OR(Restriccion.LIKE("nombre", pParametro.getNombre(), false, Posicion.EXACTA), Restriccion.IGUAL("codigo", pParametro.getCodigo())))

				.add(Restriccion.NOT(Restriccion.IGUAL("idParametroSistema", pParametro.getIdParametroSistema()))).setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new TrascenderFrameworkException(80);
		}

	}

	public void updateParametro(ParametroSistema pParametro) throws Exception {
		this.validarParametro(pParametro);
		this.entity.merge(pParametro);

	}

	public void deleteParametro(ParametroSistema pParametro) throws Exception {
		ParametroSistema locParamEliminar = this.entity.find(ParametroSistema.class, pParametro.getIdParametroSistema());
		entity.remove(locParamEliminar);

	}

	public ParametroSistema getParametroPorNombre(String pNombre) throws Exception {
		return Criterio.getInstance(entity, ParametroSistema.class).add(Restriccion.LIKE("nombre", pNombre, false)).uniqueResult();
	}

	public void addConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws Exception {
		this.validarConfiguracionRecurso(pConfiguracion);
		this.entity.persist(pConfiguracion);

		recargarMapaConfiguracionesRecurso();
	}

	private void validarConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws Exception {
		if(pConfiguracion.getIdRecurso() != null) {
			Criterio locCriterio = Criterio.getInstance(this.entity, ConfiguracionRecurso.class).add(Restriccion.IGUAL("idRecurso", pConfiguracion.getIdRecurso()));

			// if(!pConfiguracion.getListaUsuarios().isEmpty()) {
			// locCriterio.crearAliasLeft("listaUsuarios", "cadaUsuario").add(Restriccion.EN("cadaUsuario", pConfiguracion.getListaUsuarios()));
			// } else {
			// locCriterio.add(Restriccion.ESTA_VACIO("listaUsuarios"));
			// }

			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idConfiguracionRecurso", pConfiguracion.getIdConfiguracionRecurso()))).setProyeccion(Proyeccion.COUNT())
					.setDistinct(true).setModoDebug(true);

			if((Long) locCriterio.uniqueResult() > 0) {
				throw new TrascenderFrameworkException(86);
			}
		} else {
			throw new TrascenderFrameworkException(82);
		}

		// if(pConfiguracion.getOrden() != null){
		// if((Long)Criterio.getInstance(this.entity, ConfiguracionRecurso.class)
		// .add(Restriccion.IGUAL("idRecurso", pConfiguracion.getIdRecurso()))
		// .add(Restriccion.IGUAL("orden", pConfiguracion.getOrden()))
		// .add(Restriccion.NOT(Restriccion.IGUAL("idConfiguracionRecurso",pConfiguracion.getIdConfiguracionRecurso())))
		// .setProyeccion(Proyeccion.COUNT())
		// .uniqueResult() > 0){
		// throw new TrascenderFrameworkException(87);
		// }
		// }else{
		// throw new TrascenderFrameworkException(83);
		// }

		// if(pConfiguracion.getNombreAlias() != null){
		// if((Long)Criterio.getInstance(this.entity, ConfiguracionRecurso.class)
		// .add(Restriccion.IGUAL("idRecurso", pConfiguracion.getIdRecurso()))
		// .add(Restriccion.IGUAL("nombreAlias", pConfiguracion.getNombreAlias()))
		// .add(Restriccion.NOT(Restriccion.IGUAL("idConfiguracionRecurso",pConfiguracion.getIdConfiguracionRecurso())))
		// .setProyeccion(Proyeccion.COUNT())
		// .uniqueResult() > 0){
		// throw new TrascenderFrameworkException(89);
		// }
		// }else{
		// throw new TrascenderFrameworkException(85);
		// }

		// Me fijo si hay alguna validacion nueva para "pre-validar"
		// si se puede agregar la validacion sin que haya conflictos de consistencia
		if(pConfiguracion.getValidacionDinamica() != null && !pConfiguracion.getValidacionDinamica().getListaComponentes().isEmpty()) {
			for(ComponenteValidacion cadaComponente : pConfiguracion.getValidacionDinamica().getListaComponentes()) {
				if(cadaComponente.getIdComponenteValidacion() < 1) {
					this.validarComponente(cadaComponente);
				}
			}
		}

		for(ConjuntoAtributoTabla cadaConjunto : pConfiguracion.getListaConjuntoAtributosTabla()) {
			cadaConjunto.setConfiguracionRecurso(pConfiguracion);
			for(ConfiguracionAtributoTabla cadaConfigAtrTab : cadaConjunto.getListaAtributosTabla()) {
				cadaConfigAtrTab.setConjuntoAtributoTabla(cadaConjunto);
			}
		}

	}

	public void updateConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws Exception {
		this.validarConfiguracionRecurso(pConfiguracion);
		this.entity.merge(pConfiguracion);
		this.entity.flush();

		recargarMapaConfiguracionesRecurso();
	}

	public void deleteConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws Exception {
		ConfiguracionRecurso locParamEliminar = this.entity.find(ConfiguracionRecurso.class, pConfiguracion.getIdConfiguracionRecurso());
		entity.remove(locParamEliminar);

		recargarMapaConfiguracionesRecurso();
	}

	private void recargarMapaConfiguracionesRecurso() {
		SecurityMgr.getInstance().armarMapaConfiguracionesRecurso();
	}

	public PlantillaAtributoDinamico addPlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantilla);
		pPlantilla = entity.merge(pPlantilla);
		this.entity.flush();
		return pPlantilla;
	}

	public PlantillaAtributoDinamico updatePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws Exception {

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantilla);

		pPlantilla = entity.merge(pPlantilla);
		entity.flush();
		return pPlantilla;
	}

	public void deletePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws Exception {
		pPlantilla = entity.merge(pPlantilla);
		Query locQuery = entity.createQuery("DELETE FROM AtributoDinamico d WHERE d.plantilla = :plantilla");
		locQuery.setParameter("plantilla", pPlantilla);
		locQuery.executeUpdate();
		entity.remove(pPlantilla);
	}

	public FiltroPlantillaAtributosDinamicos findListaPlantillaAtritbutosDinamicos(FiltroPlantillaAtributosDinamicos filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, PlantillaAtributoDinamico.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.IGUAL("idRecurso", filtro.getIdRecurso())).add(Restriccion.IGUAL("busqueda", filtro.getBusqueda()));
		if(filtro.getRecurso() != null) {
			locCriterio.add(Restriccion.IGUAL("idRecurso", filtro.getRecurso().getIdRecurso()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, PlantillaAtributoDinamico.serialVersionUID, "idPlantillaAtributoDinamico", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Devuelve un listado de ID's de entidades que tengan Atributos Dinamicos con valores iguales a la lista recibida como parametro. Ideal para usar como
	 * subconsulta.
	 * 
	 * @return El listado de ID's correspondientes, NULL si ningun AtributoDinamico fue valido (no se deberia incluir la subconsulta)
	 * 
	 */
	public Set<Long> findListaIdsEntidad(long pIdRecurso, List<AtributoDinamico<?>> pListadoAtributosDinamicos) {
		// Si la lista queda vacia despues de la validacion directamente
		if(!esListaValida(pListadoAtributosDinamicos)) {
			return null;
		}
		// Si, hay que buscarlas una por una, no encontre una forma de hacer todo en una sola busqueda,
		// la herencia complica mucho...
		Set<Long> locListaResultado = new HashSet<Long>();
		for(AtributoDinamico<?> cadaAtributo : pListadoAtributosDinamicos) {
			Criterio locCriterio = Criterio.getInstance(entity, cadaAtributo.getClass()).setModoDebug(true).add(Restriccion.IGUAL("idRecurso", pIdRecurso))
					.add(Restriccion.EN("idEntidad", locListaResultado)).setProyeccion(Proyeccion.PROP("idEntidad")).add(getRestriccionDeAtributo(cadaAtributo));
			locListaResultado = new HashSet(locCriterio.list());
			// Si la lista esta vacia, no se sigue buscando, pues ya hay un criterio incumplido
			if(locListaResultado.isEmpty()) {
				break;
			}
		}
		return locListaResultado;
	}

	private Restriccion getRestriccionDeAtributo(AtributoDinamico<?> pAtributo) {
		if(pAtributo instanceof AtributoDinamicoCadena) {
			return Restriccion.ILIKE("valor", pAtributo.getValorString());
		}
		return Restriccion.IGUAL("valor", pAtributo.getValor());
	}

	/**
	 * Valida la lista de Atributos, quita los que esten nulos o invalidos y al final devuelve TRUE solo si la lista contiene elementos.
	 * 
	 * @param pListaAtributoDinamicos
	 * @return
	 */
	private boolean esListaValida(List<AtributoDinamico<?>> pListaAtributoDinamicos) {
		if(pListaAtributoDinamicos == null) {
			return false;
		}
		for(Iterator<AtributoDinamico<?>> cadaIterator = pListaAtributoDinamicos.iterator(); cadaIterator.hasNext();) {
			AtributoDinamico<?> cadaAtributo = cadaIterator.next();
			if(cadaAtributo.getValor() == null) {
				cadaIterator.remove();
			} else if(cadaAtributo instanceof AtributoDinamicoBooleano) {
				if(((AtributoDinamicoBooleano) cadaAtributo).getValor() == false) {
					cadaIterator.remove();
				}
			} else if(cadaAtributo instanceof AtributoDinamicoCadena) {
				if(((AtributoDinamicoCadena) cadaAtributo).getValor().trim().isEmpty()) {
					cadaIterator.remove();
				}
			}
		}
		return pListaAtributoDinamicos.size() > 0;
	}

	public ConfiguracionRecurso getConfiguracionRecursoPorId(Long pIdConfiguracion) throws Exception {
		if(pIdConfiguracion == null) {
			throw new TrascenderFrameworkException(1);
		}

		ConfiguracionRecurso locConfigRecurso = this.entity.find(ConfiguracionRecurso.class, pIdConfiguracion);

		if(locConfigRecurso != null) {
			locConfigRecurso.toString();
			if(locConfigRecurso.getValidacionDinamica() != null) {
				locConfigRecurso.getValidacionDinamica().toString();
				for(ComponenteValidacion cadaComponente : locConfigRecurso.getValidacionDinamica().getListaComponentes()) {
					cadaComponente.toString();
				}
			}

			for(ConjuntoAtributoTabla cadaConjunto : locConfigRecurso.getListaConjuntoAtributosTabla()) {
				for(Usuario cadaUsuario : cadaConjunto.getListaUsuarios()) {
					cadaUsuario.toString();
				}
				for(ConfiguracionAtributoTabla cadaConfiguracion : cadaConjunto.getListaAtributosTabla()) {
					cadaConfiguracion.toString();
				}
			}

			for(String cadaString : locConfigRecurso.getListaAtributosConfigurables()) {
				cadaString.toString();
			}

		}

		return locConfigRecurso;
	}

	public ConfiguracionRecurso getConfiguracionRecursoPorNombre(String pNombre) throws Exception {
		Long locIdConfig = Criterio.getInstance(entity, ConfiguracionRecurso.class).add(Restriccion.LIKE("nombreAlias", pNombre))
				.setProyeccion(Proyeccion.PROP("idConfiguracionRecurso")).uniqueResult();

		if(locIdConfig != null) {
			return this.getConfiguracionPorRecurso(locIdConfig);
		}

		return null;
	}

	public ConfiguracionRecurso getConfiguracionPorRecurso(Long pIdRecurso) throws Exception {
		Long locConfig = Criterio.getInstance(entity, ConfiguracionRecurso.class).add(Restriccion.IGUAL("idRecurso", pIdRecurso))
				.setProyeccion(Proyeccion.PROP("idConfiguracionRecurso")).uniqueResult();

		if(locConfig != null) {
			return this.getConfiguracionRecursoPorId(locConfig);
		}

		return null;
	}

	public ValidacionDinamica getValidacionBySerial(Long pSerialVersion) throws Exception {
		ValidacionDinamica locValidacion = Criterio.getInstance(this.entity, ConfiguracionRecurso.class).add(Restriccion.IGUAL("idRecurso", pSerialVersion))
				.setProyeccion(Proyeccion.PROP("validacionDinamica")).uniqueResult();

		if(locValidacion != null) {
			locValidacion.toString();
			for(ComponenteValidacion cadaValidacion : locValidacion.getListaComponentes()) {
				cadaValidacion.toString();
				cadaValidacion.getMensajeError().toString();
				if(cadaValidacion.getValor() != null) {
					cadaValidacion.getValor().toString();
				}
			}
		}

		return locValidacion;
	}

	public void addValidacionARecurso(ComponenteValidacion pComponente, Long idRecurso) throws Exception {
		this.validarComponente(pComponente);
		ValidacionDinamica locValidacion = this.getValidacionBySerial(idRecurso);

		if(locValidacion != null) {
			locValidacion.addComponente(pComponente);
		} else {
			throw new TrascenderFrameworkException(100);
		}
	}

	public FiltroConfiguracionRecurso findListaConfiguracionRecurso(FiltroConfiguracionRecurso pFiltro) {
		Criterio locCriterio = Criterio.getInstance(this.entity, ConfiguracionRecurso.class);
		if(pFiltro.getUsuario() != null) {
			locCriterio.crearAlias("listaUsuarios", "cadaUsuario").add(Restriccion.IGUAL("cadaUsuario", pFiltro.getUsuario()));
		}
		locCriterio.add(Restriccion.ILIKE("nombreAlias", pFiltro.getNombreAlias()))
				.add(Restriccion.IGUAL("idRecurso", ((pFiltro != null && pFiltro.getRecurso() != null) ? pFiltro.getRecurso().getIdRecurso() : null))).setDistinct(true);

		pFiltro.procesarYListar(locCriterio);

		for(ConfiguracionRecurso cadaConf : pFiltro.getListaResultados()) {
			cadaConf.getListaAtributosConfigurables().size();
			for(String cadaString : cadaConf.getListaAtributosConfigurables()) {
				cadaString.toString();
			}
			cadaConf.getListaConjuntoAtributosTabla().size();
			for(ConjuntoAtributoTabla cadaConjunto : cadaConf.getListaConjuntoAtributosTabla()) {
				for(Usuario cadaUsuario : cadaConjunto.getListaUsuarios()) {
					cadaUsuario.toString();
				}
				for(ConfiguracionAtributoTabla cadaConfAtrTab : cadaConjunto.getListaAtributosTabla()) {
					cadaConfAtrTab.toString();
				}
			}
		}

		return pFiltro;
	}

	private void validarComponente(ComponenteValidacion pComponente) throws Exception {
		if(pComponente.getMensajeError() == null) {
			throw new TrascenderFrameworkException(101);
		}

		if(pComponente.getMensajeError().getNumeroMsg() != null && pComponente.getMensajeError().getMensaje() != null) {
			throw new TrascenderFrameworkException(102);
		}

		this.preValidarValidacion(pComponente);

	}

	// Me fijo si todos los datos cumplen con la reestricion que voy a agregar.
	private void preValidarValidacion(ComponenteValidacion pComponente) throws Exception {
		Class locClase = SecurityMgr.getInstance().getClassFromSerialID(pComponente.getValidacionDinamica().getIdRecurso());
		if(locClase == null) {
			throw new TrascenderFrameworkException(104);
		}

		String locClassName = locClase.getName();
		String locQuery = "SELECT COUNT(e) FROM " + locClassName + " e " + " WHERE " + pComponente.getValidacionSimple();

		System.out.println(locQuery);

		System.out.println(pComponente.getMapaParametros().size() + "[" + pComponente.getMapaParametros().entrySet().iterator().next().getKey() + " - "
				+ pComponente.getMapaParametros().entrySet().iterator().next().getValue() + "]");

		Query locQueryFinal = this.entity.createQuery(locQuery);

		System.out.println(pComponente.getMapaParametros().size());
		for(Entry<String, Object> cadaParametro : pComponente.getMapaParametros().entrySet()) {
			locQueryFinal.setParameter(cadaParametro.getKey(), cadaParametro.getValue());
		}

		Long locResultado = (Long) locQueryFinal.getSingleResult();

		if(locResultado > 0) {
			throw new TrascenderFrameworkException(103);
		}
	}

	public List<String> getListaNombresPropiedadesLogs(long pSerialVersionUID) {
		Criterio locCriterio = Criterio
				.getInstance(entity, LogAuditoria.class)
				.setDistinct(true)
				.add(Restriccion.IGUAL("idRecurso", pSerialVersionUID))
				.add(Restriccion.NOT(Restriccion.NULO("propiedad")))
				.setProyeccion(
						Proyeccion.PROP("CASE WHEN (LOCATE('-->', propiedad) <> 0) THEN CONCAT(' > ', SUBSTRING(propiedad, LOCATE('-->', propiedad) + 4)) ELSE propiedad END")
								.SIN_PROCESAR_ENTIDADES());

		return locCriterio.list();
	}

	public Map<String, Usuario> getMapaUsuariosLogs(long pSerialVersionUID) {
		Criterio locCriterio = Criterio.getInstance(entity, LogAuditoria.class).setDistinct(true).add(Restriccion.IGUAL("idRecurso", pSerialVersionUID))
				.setProyeccion(Proyeccion.PROP("usuario"));

		Map<String, Usuario> mapaResultado = new HashMap<String, Usuario>();

		for(Object cadaObj : locCriterio.list()) {
			Usuario cadaUsr = (Usuario) cadaObj;
			mapaResultado.put(cadaUsr.toString(), cadaUsr);
		}

		return mapaResultado;
	}

	/**
	 * Busca un bien por id Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public PlantillaAtributoDinamico getPlantillaAtributoDinamicoPorId(long pIdPlantilla) throws java.lang.Exception {
		PlantillaAtributoDinamico locPlantilla = entity.find(PlantillaAtributoDinamico.class, pIdPlantilla);
		if(locPlantilla != null) {
			// locPlantilla.toString();
			locPlantilla.getListaLogsAuditoria().size();
		}
		return locPlantilla;
	}

	public ReportesJasper getReporteJasperPorId(long pIdReporte) throws java.lang.Exception {
		ReportesJasper locReporte = entity.find(ReportesJasper.class, pIdReporte);
		if(locReporte != null) {
			locReporte.toString();
		}
		return locReporte;
	}

	public ReportesJasper addReporteJasper(ReportesJasper pReporteJasper) throws Exception {

		this.entity.persist(pReporteJasper);
		this.entity.flush();
		return pReporteJasper;
	}

	public ReportesJasper updateReporteJasper(ReportesJasper pReporteJasper) throws Exception {
		pReporteJasper = entity.merge(pReporteJasper);
		entity.flush();
		return pReporteJasper;
	}

	public FiltroReportesJasper findListaReportesJasper(FiltroReportesJasper filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ReportesJasper.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()));

		filtro.procesarYListar(locCriterio);
		return filtro;
	}
}

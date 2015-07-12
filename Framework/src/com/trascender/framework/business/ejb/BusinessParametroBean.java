
package com.trascender.framework.business.ejb;

import java.awt.Image;
import java.io.File;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroProcesoDB;
import com.trascender.framework.recurso.filtros.FiltroReporte;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ConfiguracionAccesosDirectos;
import com.trascender.framework.recurso.persistent.ConfiguracionAtributoTabla;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;
import com.trascender.framework.recurso.persistent.ParametroSistema;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoBooleano;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoCadena;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.persistent.reporteDinamico.OpcionParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
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
		
		Recurso recursoProcesoDB = new Recurso();
		recursoProcesoDB.setIdRecurso(ProcesoDB.serialVersionUID);
		recursoProcesoDB.setNombre("Procesos DB");
		recursoProcesoDB.setAtributosConsultables("Nombre", "nombre", "Nombre Proceso", "nombreProceso");
		recursoProcesoDB.setClase(ProcesoDB.class);

		Recurso reporte = new Recurso();
		reporte.setNombre("Reporte");
		reporte.setIdRecurso(Reporte.serialVersionUID);
		reporte.setAtributosConsultables("Nombre", "nombre");
		reporte.setClase(Reporte.class);

		grupo.getListaRecursos().add(recursoConfiguracionRec);
		grupo.getListaRecursos().add(recursoAtributos);
		grupo.getListaRecursos().add(recursoProcesoDB);
		grupo.getListaRecursos().add(reporte);
		SecurityMgr.getInstance().addGrupo(grupo);
	}

	private static final long serialVersionUID = -575137816902087037L;
	private static final String NOMBRE = "FRM|Adm. Configuración de Sistema.";

	@PersistenceContext(name = "vipians")
	private EntityManager entity;
	
	@Resource(mappedName = "java:/vipiansDS", shareable = true)
	private DataSource datasource;

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
	
	public FiltroProcesoDB findListaProcesosDB(FiltroProcesoDB pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entity, ProcesoDB.class)
				.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.ILIKE("nombreProceso", pFiltro.getNombreProceso()));
		
		pFiltro.procesarYListar(locCriterio);
		
		return pFiltro;
	}
	
	public String ejecutarProcesoDB(Long idProceso, String parametros) {
		ProcesoDB locProceso = entity.find(ProcesoDB.class, idProceso);
		
		if (parametros == null) {
			parametros = "";
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(locProceso.getNombreProceso());
		sql.append("(").append(parametros).append(")");
		
		Query query = entity.createNativeQuery(sql.toString());
		String mensaje = query.getSingleResult().toString();
		return mensaje;
	}
	
	public ConfiguracionAccesosDirectos getConfiguracionPorUsuario(Long idUsuario) {
		Criterio locCriterio = Criterio.getInstance(entity, ConfiguracionAccesosDirectos.class)
				.add(Restriccion.IGUAL("usuario.idUsuario", idUsuario));
		ConfiguracionAccesosDirectos locConfiguracion = locCriterio.uniqueResult();
		if (locConfiguracion != null) {
			locConfiguracion.getListaAccesosDirecto().size();
		}
		return locConfiguracion;
	}

	public void addAccesoDirecto(Long pIdRecurso, Usuario pUsuario) {
		ConfiguracionAccesosDirectos locConfiguracion = getConfiguracionPorUsuario(pUsuario.getIdUsuario());
		if (locConfiguracion == null) {
			locConfiguracion = new ConfiguracionAccesosDirectos();
			locConfiguracion.setUsuario(pUsuario);
		}
		if (locConfiguracion.addAccesoDirecto(pIdRecurso)) {
			entity.merge(locConfiguracion);
		}
	}
	
	@Override
	public void addReporte(Reporte pReporte) throws Exception {
		validarReporte(pReporte);
		entity.persist(pReporte);
	}

	@Override
	public Reporte updateReporte(Reporte pReporte) throws Exception {
		validarReporte(pReporte);

		return entity.merge(pReporte);
	}

	@Override
	public void deleteReporte(Reporte pReporte) throws Exception {
		pReporte.setEstado(Reporte.Estado.INACTIVO);
		entity.merge(pReporte);
	}

	@Override
	public Reporte getReporteByID(Long pIdReporte) throws Exception {
		Reporte locReporte = entity.find(Reporte.class, pIdReporte);

		locReporte.getListaParametroReporte().size();
		for(ParametroReporte cadaParametro : locReporte.getListaParametroReporte()) {
			cadaParametro.getListaOpciones().size();
		}
		locReporte.getListaUsuario().size();

		return locReporte;
	}

	@Override
	public FiltroReporte findListaReporte(FiltroReporte pFiltro) {
		Criterio locCriterio = Criterio.getInstance(this.entity, Reporte.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("estado", Reporte.Estado.ACTIVO));

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	private void validarReporte(Reporte pReporte) {
		for(ParametroReporte cadaParametro : pReporte.getListaParametroReporte()) {
			cadaParametro.setReporte(pReporte);
			for(OpcionParametroReporte cadaOpcion : cadaParametro.getListaOpciones()) {
				cadaOpcion.setParametroReporte(cadaParametro);
			}
		}
	}

	@Override
	public List<Reporte> getListaMenuReporte(Usuario pUsuarioLogueado) {
		if(pUsuarioLogueado == null) {
			return new ArrayList<Reporte>();
		}

		Criterio locCriterio = Criterio.getInstance(this.entity, Reporte.class).add(Restriccion.IGUAL("estado", Reporte.Estado.ACTIVO)).add(Orden.ASC("nombre"))
				.add(Restriccion.MIEMBRO_DE("listaUsuario", pUsuarioLogueado));

		List<Reporte> locListaRetorno = locCriterio.list();

		for(Reporte cadaReporte : locListaRetorno) {
			cadaReporte.getListaParametroReporte().size();
			cadaReporte.getListaUsuario().size();
		}

		return locListaRetorno;
	}

	public JasperPrint getReporte(Reporte pReporte, Map<String, Object> pMapaParametros) throws Exception {
		pMapaParametros.put("P_TITULO", SecurityMgr.getInstance().getMunicipalidad().getEncabezadoReportes());
		pMapaParametros.put("P_IMAGEN", (Image) new ImageIcon(SecurityMgr.getInstance().getMunicipalidad().getLogo()).getImage());

		String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
		JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(rutaReportes + "Dinamicos/" + pReporte.getNombreArchivoJasper()));
		reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

		Connection conexion = datasource.getConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, pMapaParametros, conexion);
		conexion.close();

		return jasperPrint;
	}

	public List<Reporte> getListaReportesPorUsuario(long idUsuario) {
		Criterio locCriterio = Criterio.getInstance(this.entity, Reporte.class).crearAlias("listaUsuario", "cadaUsuario").add(Restriccion.IGUAL("cadaUsuario.idUsuario", idUsuario))
				.add(Restriccion.IGUAL("estado", Reporte.Estado.ACTIVO));

		List<Reporte> locListaRetorno = locCriterio.list();

		for(Reporte cadaReporte : locListaRetorno) {
			cadaReporte.getListaParametroReporte().size();
			cadaReporte.getListaUsuario().size();
		}

		return locListaRetorno;
	}
}

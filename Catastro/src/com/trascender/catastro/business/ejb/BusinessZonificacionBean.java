
package com.trascender.catastro.business.ejb;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.filtros.FiltroZonificacion;
import com.trascender.catastro.recurso.persistent.AsociacionParcela;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCalle;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCuadra;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaManzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.recurso.persistent.Zonificacion.Estado;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessRegistroPropiedad" display-name="Name for BusinessRegistroPropiedad" description="Description for BusinessRegistroPropiedad"
 *           jndi-name="ejb/BusinessZonificacion" type="Stateless" view-type="local"
 */
@Stateless(name = "BusinessZonificacionLocal")
public class BusinessZonificacionBean implements BusinessZonificacionLocal {

	private static final long serialVersionUID = -2850599334550913843L;
	static {
		Grupo locGrupo = new Grupo();
		locGrupo.setId(serialVersionUID);
		locGrupo.setNombre("CAT|Administración de Zonificación");

		ArrayList<Recurso> locListaRecursos = new ArrayList<Recurso>();

		Recurso locZonificacion = new Recurso();
		locZonificacion.setNombre("Zonificación");
		locZonificacion.setIdRecurso(Zonificacion.serialVersionUID);
		locZonificacion.setAtributosConsultables("Nombre", "nombre");
		locZonificacion.setClase(Zonificacion.class);
		locListaRecursos.add(locZonificacion);

		Recurso locZona = new Recurso();
		locZona.setIdRecurso(Zona.serialVersionUID);
		locZona.setNombre("Zona");
		locZona.setAtributosConsultables("Nombre", "nombre", "Zonificación", "zonificacion", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Estado", "estado");
		locZona.setClase(Zona.class);
		locListaRecursos.add(locZona);

		locGrupo.setListaRecursos(locListaRecursos);

		SecurityMgr.getInstance().addGrupo(locGrupo);
	}

	// private Session session;
	@PersistenceContext(name = "vipians")
	private EntityManager entityManager;

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
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
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroZona findListaZonas(FiltroZona pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Zona.class)
				.add(Restriccion.IGUAL("estado", ((pFiltro.getEstado() != null) ? pFiltro.getEstado() : Zona.Estado.ACTIVO))).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("zonificacion", pFiltro.getZonificacion()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Zona.serialVersionUID, "idZona", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;

	}

	@SuppressWarnings("unchecked")
	public FiltroZonificacion findListaZonificacion(FiltroZonificacion pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Zonificacion.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("estado", Zonificacion.Estado.ACTIVO));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Zonificacion.serialVersionUID, "idZonificacion", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public void addZonificacion(Zonificacion pZonificacion) throws Exception {
		this.validarZonificacion(pZonificacion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pZonificacion);

		this.entityManager.merge(pZonificacion);
		this.entityManager.flush();
	}

	/**
	 * Valida que no aya una zonificacion con el mismo nombre.
	 */
	private void validarZonificacion(Zonificacion pZonificacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Zonificacion.class).add(Restriccion.IGUAL("estado", Estado.ACTIVO))
				.add(Restriccion.LIKE("nombre", pZonificacion.getNombre(), false, Posicion.EXACTA));

		if(pZonificacion.getIdZonificacion() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idZonificacion", pZonificacion.getIdZonificacion())));
		}

		if((Long) locCriterio.setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new CatastroException(92);
		}

	}

	/**
	 * Actualiza los datos de una zonificacion
	 * 
	 * @param pZonificacion
	 * @throws Exception
	 */
	public void updateZonificacion(Zonificacion pZonificacion) throws Exception {
		this.validarZonificacion(pZonificacion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pZonificacion);

		this.entityManager.merge(pZonificacion);

		this.entityManager.flush();
	}

	public void removeZonificacion(Zonificacion pZonificacion) throws Exception {
		if(pZonificacion.getListaZonas() != null) {
			for(Zona cadaZona : pZonificacion.getListaZonas())
				if(cadaZona.getEstado().equals(Zona.Estado.ACTIVO)) {
					throw new CatastroException(90);
				}
		}

		pZonificacion.setEstado(Estado.ELIMINADO);
		this.updateZonificacion(pZonificacion);
	}

	/**
	 * Retorna la zona a la que pertenece la parcela o null en caso que no encuentre ninguna
	 * 
	 * @param pParcela
	 * @param pZonificacion
	 * @return
	 */
	public Zona getZonaFromParcela(Parcela pParcela, Zonificacion pZonificacion) {
		BigDecimal idZona = null;
		try {
			idZona = (BigDecimal) this.entityManager.createNativeQuery("SELECT * FROM p_zona_from_parcela(:id_parcela, :id_zonificacion, :limitar)")
					.setParameter("id_parcela", pParcela.getIdParcela()).setParameter("id_zonificacion", pZonificacion.getIdZonificacion()).setParameter("limitar", true)
					.getSingleResult();
		} catch(NoResultException e) {
			// La parcela no tiene zonas de esa zonificacion.
		}

		Zona locZona = null;
		if(idZona != null) {
			locZona = this.entityManager.find(Zona.class, idZona.longValue());
		}

		return locZona;
	}

	public List<Zona> getListaZonasFromParcela(Parcela pParcela, Zonificacion pZonificacion) {
		List<BigDecimal> idsZona = this.entityManager.createNativeQuery("SELECT * FROM p_zona_from_parcela(:id_parcela, :id_zonificacion, :limitar)")
				.setParameter("id_parcela", pParcela.getIdParcela()).setParameter("id_zonificacion", pZonificacion.getIdZonificacion()).setParameter("limitar", false).getResultList();

		List listaZonas = new ArrayList();
		if(idsZona != null) {
			for(BigDecimal id : idsZona) {
				listaZonas.add(this.entityManager.find(Zona.class, id.longValue()));
			}
		}

		return listaZonas;
	}

	/**
	 * Recupera una zona segun el id.
	 */
	public Zona getZonaPorId(long pId) {

		Zona locZona = this.entityManager.find(Zona.class, pId);
		locZona.getZonificacion().toString();

		for(AsociacionParcelaBridge cadaAsociacion : locZona.getListaAsociacionParcela()) {
			cadaAsociacion.toString();
		}
		return locZona;
	}

	@SuppressWarnings("unchecked")
	public List<Zona> getZonasFromParcela(Parcela pParcela) {

		List<Zona> locListaZonas = new ArrayList<Zona>();
		List<Zonificacion> locListaZonificaciones = Criterio.getInstance(this.entityManager, Zonificacion.class).list();
		for(Zonificacion cadaZonificacion : locListaZonificaciones) {
			Zona locZona = this.getZonaFromParcela(pParcela, cadaZonificacion);
			if(locZona != null) {
				locListaZonas.add(locZona);
			}
		}
		return locListaZonas;
	}

	@SuppressWarnings("unchecked")
	public List<Zona> getZonasFromParcelaSinLimitar(Parcela pParcela) {

		List<Zona> locListaZonas = new ArrayList<Zona>();
		List<Zonificacion> locListaZonificaciones = Criterio.getInstance(this.entityManager, Zonificacion.class).list();
		for(Zonificacion cadaZonificacion : locListaZonificaciones) {
			List locZonasAux = this.getListaZonasFromParcela(pParcela, cadaZonificacion);
			if(locZonasAux != null) {
				locListaZonas.addAll(locZonasAux);
			}
		}
		return locListaZonas;
	}

	/**
	 * Obtiene una asociación de parcelas con calle a partir de una zonificación y una parcela
	 * 
	 * @param pParcela
	 * @param pZonificacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private AsociacionParcelaCalle getAsociacionParcelaCalle(Parcela pParcela, Zonificacion pZonificacion) {
		AsociacionParcelaCalle locAsoc = Criterio.getInstance(this.entityManager, AsociacionParcelaCalle.class)
				.crearAlias("calle.listaCuadras.listaParcelasPorCuadra.parcela", "cadaParcela").add(Restriccion.IGUAL("zona.zonificacion", pZonificacion))
				.add(Orden.ASC("zona.prioridad")).add(Restriccion.IGUAL("cadaParcela", pParcela)).setMaxResults(1).uniqueResult();

		return locAsoc;
	}

	/**
	 * Obtiene una asociación de zona a manzana a partir de una zonificación y una parcela
	 * 
	 * @param pParcela
	 * @param pZonificacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private AsociacionParcelaManzana getAsociacionParcelaManzana(Parcela pParcela, Zonificacion pZonificacion) {
		AsociacionParcelaManzana locAsoc = Criterio.getInstance(this.entityManager, AsociacionParcelaManzana.class)
				.crearAlias("manzana.listaCuadrasDelimitantes.listaParcelasPorCuadra.parcela", "cadaParcela").add(Restriccion.IGUAL("zona.zonificacion", pZonificacion))
				.add(Orden.ASC("zona.prioridad")).add(Restriccion.IGUAL("cadaParcela", pParcela)).setMaxResults(1).uniqueResult();

		return locAsoc;
	}

	/**
	 * Obtiene una asociacion zona - cuadra a partir de la zonificación y de la parcela
	 * 
	 * @param pParcela
	 * @param pZonificacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private AsociacionParcelaCuadra getAsociacionParcelaCuadra(Parcela pParcela, Zonificacion pZonificacion) {
		AsociacionParcelaCuadra locAsoc = Criterio.getInstance(this.entityManager, AsociacionParcelaCuadra.class).crearAlias("cuadra.listaParcelasPorCuadra.parcela", "cadaParcela")
				.add(Restriccion.IGUAL("zona.zonificacion", pZonificacion)).add(Orden.ASC("zona.prioridad")).add(Restriccion.IGUAL("cadaParcela", pParcela)).setMaxResults(1)
				.uniqueResult();

		return locAsoc;
	}

	/**
	 * Obtiene una asociación - Parcela A a partir de la parcela y la zonificación
	 * 
	 * @param pParcela
	 * @param pZonificacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private AsociacionParcela getAsociacionParcela(Parcela pParcela, Zonificacion pZonificacion) {

		AsociacionParcela locAsoc = Criterio.getInstance(this.entityManager, AsociacionParcela.class).add(Restriccion.IGUAL("parcela", pParcela))
				.add(Restriccion.IGUAL("zona.zonificacion", pZonificacion)).add(Orden.ASC("zona.prioridad")).setMaxResults(1).uniqueResult();

		return locAsoc;

	}

	/**
	 * Recupera una zonificacion
	 * 
	 * @param pIdZonificacion
	 * @return
	 */
	public Zonificacion getZonificacionPorId(Long pIdZonificacion) {

		Zonificacion locZonificacion = (Zonificacion) Criterio.getInstance(this.entityManager, Zonificacion.class).crearAlias("listaZonas", "cadaZona")
				.add(Restriccion.IGUAL("idZonificacion", pIdZonificacion)).uniqueResult();

		for(Zona cadaZona : locZonificacion.getListaZonas()) {
			cadaZona.toString();
		}

		if(locZonificacion != null) {
			locZonificacion.getListaLogsAuditoria().size();
		}

		return locZonificacion;
	}

	public Zona getZonaById(Long idZona) throws Exception {
		Zona locZona = this.entityManager.find(Zona.class, idZona);
		locZona.toString();
		locZona.getZonificacion().toString();
		locZona.getListaAsociacionParcela().size();
		locZona.getListaLogsAuditoria().size();
		for(AsociacionParcelaBridge cadaZona : locZona.getListaAsociacionParcela()) {
			for(Parcela cadaParcela : cadaZona.getListaParcelas()) {
				if(cadaParcela.getTituloPropiedad() != null) {
					cadaParcela.getTituloPropiedad().toString();
				}
			}
		}
		return locZona;
	}

	/**
	 * Retorna una lista con 4 mapas, para obtener a que zona pertence una parcela, en el orden: Parcela-Zona, Cuadra-Zona, Manzana-Zona, Calle-Zona. Debe
	 * usarse solo en la liquidación, pues levanta toda la base. Devuelve los nombres de las zonas.
	 * 
	 * @return
	 */
	public List<Map<Long, String>> getMapaParcelaNombreZona(Zonificacion pZonifiacion) {
		List<Map<Long, String>> locListaResultados = new ArrayList<Map<Long, String>>();
		Criterio locCriterio = Criterio.getInstance(entityManager, AsociacionParcela.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.nombre", "parcela.idParcela"));
		Map<Long, String> locMapa = new HashMap<Long, String>();
		List<Object[]> resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (String) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		locCriterio = Criterio.getInstance(entityManager, AsociacionParcelaCuadra.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.nombre", "cuadra.listaParcelasPorCuadra.parcela.idParcela"));
		locMapa = new HashMap<Long, String>();
		resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (String) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		locCriterio = Criterio.getInstance(entityManager, AsociacionParcelaManzana.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.nombre", "manzana.listaCuadrasDelimitantes.listaParcelasPorCuadra.parcela.idParcela"));
		locMapa = new HashMap<Long, String>();
		resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (String) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		locCriterio = Criterio.getInstance(entityManager, AsociacionParcelaCalle.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.nombre", "calle.listaCuadras.listaParcelasPorCuadra.parcela.idParcela"));
		locMapa = new HashMap<Long, String>();
		resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (String) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		return locListaResultados;
	}

	/**
	 * Retorna una lista con 4 mapas, para obtener a que zona pertence una parcela, en el orden: Parcela-Zona, Cuadra-Zona, Manzana-Zona, Calle-Zona. Debe
	 * usarse solo en la liquidación, pues levanta toda la base. Trabaja con los id's de los objetos, para miniza costos.
	 * 
	 * @return
	 */
	public List<Map<Long, Long>> getMapaParcelaZona(Zonificacion pZonifiacion) {
		List<Map<Long, Long>> locListaResultados = new ArrayList<Map<Long, Long>>();
		Criterio locCriterio = Criterio.getInstance(entityManager, AsociacionParcela.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.idZona", "parcela.idParcela"));
		Map<Long, Long> locMapa = new HashMap<Long, Long>();
		List<Object[]> resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (Long) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		locCriterio = Criterio.getInstance(entityManager, AsociacionParcelaCuadra.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.idZona", "cuadra.listaParcelasPorCuadra.parcela.idParcela"));
		locMapa = new HashMap<Long, Long>();
		resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (Long) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		locCriterio = Criterio.getInstance(entityManager, AsociacionParcelaManzana.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.idZona", "manzana.listaCuadrasDelimitantes.listaParcelasPorCuadra.parcela.idParcela"));
		locMapa = new HashMap<Long, Long>();
		resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (Long) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		locCriterio = Criterio.getInstance(entityManager, AsociacionParcelaCalle.class).add(Restriccion.IGUAL("zona.zonificacion", pZonifiacion))
				.setProyeccion(Proyeccion.PROP("zona.idZona", "calle.listaCuadras.listaParcelasPorCuadra.parcela.idParcela"));
		locMapa = new HashMap<Long, Long>();
		resultados = locCriterio.list();
		for(Object[] cadaResultado : resultados) {
			locMapa.put((Long) cadaResultado[1], (Long) cadaResultado[0]);
		}
		locListaResultados.add(locMapa);

		return locListaResultados;
	}

}

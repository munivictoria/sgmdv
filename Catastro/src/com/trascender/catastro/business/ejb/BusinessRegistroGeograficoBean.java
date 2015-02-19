
package com.trascender.catastro.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessRegistroGeograficoLocal;
import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroCalle;
import com.trascender.catastro.recurso.filtros.FiltroCuadra;
import com.trascender.catastro.recurso.filtros.FiltroManzana;
import com.trascender.catastro.recurso.filtros.FiltroTipoCalle;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCalle;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCuadra;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaManzana;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.persistent.Servicio;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessRegistroGeografico" display-name="Name for BusinessRegistroGeografico" description="Description for BusinessRegistroGeografico"
 *           jndi-name="ejb/BusinessRegistroGeografico" type="Stateless" view-type="local"
 */
@Stateless(name = "BusinessRegistroGeograficoLocal")
public class BusinessRegistroGeograficoBean implements BusinessRegistroGeograficoLocal {
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessRegistroGeograficoBean.serialVersionUID);
		grupo.setNombre(BusinessRegistroGeograficoBean.NAME);

		Recurso tipoCalle = new Recurso();
		Recurso calle = new Recurso();
		Recurso cuadra = new Recurso();
		Recurso manzana = new Recurso();
		// Recurso servicio = new Recurso();

		tipoCalle.setIdRecurso(TipoCalle.serialVersionUID);
		tipoCalle.setNombre("Tipo de Calle");
		tipoCalle.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO);
		tipoCalle.setClase(TipoCalle.class);
		calle.setIdRecurso(Calle.serialVersionUID);
		calle.setNombre("Calle");
		calle.setAtributosConsultables("Código", "codigo", "Nombre", "nombre", "Tipo calle", "tipoCalle");
		calle.setClase(Calle.class);
		cuadra.setIdRecurso(Cuadra.serialVersionUID);
		cuadra.setNombre("Cuadra");
		cuadra.setAtributosConsultables("Calle", "calle", "Nº desde", "numeracionDesde", "Nº hasta", "numeracionHasta", "Comienza en", "calleComienza", "Finaliza en", "calleFinaliza",
				"Numeración", "tipoNumeracion");
		cuadra.setClase(Cuadra.class);
		manzana.setIdRecurso(Manzana.serialVersionUID);
		manzana.setNombre("Manzana");
		manzana.setAtributosConsultables("Nombre", "nombre", "Número", "nroManzana");
		manzana.setClase(Manzana.class);
		// servicio.setIdRecurso(Servicio.serialVersionUID);
		// servicio.setNombre("Servicio");
		// servicio.setAtributosConsultables("Nombre", "nombre","Descripción", "descripcion");
		grupo.getListaRecursos().add(tipoCalle);
		grupo.getListaRecursos().add(calle);
		grupo.getListaRecursos().add(cuadra);
		grupo.getListaRecursos().add(manzana);
		// grupo.getListaRecursos().add(servicio);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	private static final long serialVersionUID = -7932374405965163874L;

	public static final String NAME = "CAT|Adm. de Registro Geográfico";

	@EJB
	private BusinessRegistroParcelarioLocal businessParcela;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	public BusinessRegistroGeograficoBean() {
		super();
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
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
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Calle addCalle(com.trascender.catastro.recurso.persistent.Calle pCalle) throws Exception {

		this.validarCalle(pCalle);

		pCalle.setActivo(true);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pCalle);

		this.entityManager.persist(pCalle);
		this.entityManager.flush();
		this.entityManager.refresh(pCalle);
		return pCalle;
	}

	/**
	 * Valida que no aya una calle con el mismo codigo
	 * 
	 * @param pCalle
	 * @throws Exception
	 */
	private void validarCalle(Calle pCalle) throws Exception {
		if(pCalle.getTipoCalle() == null) {
			throw new CatastroException(21);
		}

		// El codigo de Calle ya no es requerido, por lo que se valida unicidad
		// solo si se ingreso uno.
		if(pCalle.getCodigo() != null && !pCalle.getCodigo().trim().isEmpty()) {
			Criterio locCriterio = Criterio.getInstance(this.entityManager, Calle.class);
			locCriterio.add(Restriccion.LIKE("codigo", pCalle.getCodigo(), false)).add(Restriccion.IGUAL("activo", new Boolean(true)))
					.add(Restriccion.DISTINTO("idCalle", pCalle.getIdCalle())).setProyeccion(Proyeccion.COUNT());
			if((Long) locCriterio.uniqueResult() > 0) {
				throw new CatastroException(20);
			}
		}

		// Si es una baja logica de calle verifico que no aya una cuadra activa
		if(pCalle.getIdCalle() != -1) {
			if(!pCalle.isActivo()) {
				// pCalle= this.getCallePorId(pCalle.getIdCalle());

				Calle locCalle = this.getCallePorId(pCalle.getIdCalle());
				locCalle.getListaCuadras().toString();
				if(!locCalle.getListaCuadras().isEmpty()) {
					boolean hayCuadraActiva = false;
					for(Cuadra locCuadra : locCalle.getListaCuadras()) {
						if(locCuadra.isActivo()) {
							hayCuadraActiva = true;
						}
					}
					if(hayCuadraActiva) {
						throw new CatastroException(42);
					}
				}
			}

		}

	}

	public FiltroCalle findCalle(FiltroCalle filtro) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Calle.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.IGUAL("tipoCalle", filtro.getTipoCalle())).add(Restriccion.IGUAL("activo", filtro.getEstado())).add(Restriccion.IGUAL("codigo", filtro.getCodigo()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Calle.serialVersionUID, "idCalle", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Busca una calle segun el codigo
	 */
	public com.trascender.catastro.recurso.persistent.Calle findCalle(String pCodigo) {
		return (Calle) Criterio.getInstance(this.entityManager, Calle.class).add(Restriccion.LIKE("codigo", pCodigo, false, Posicion.AL_PRINCIPIO)).uniqueResult();
	}

	/**
	 * Valida la que no se repita un nombre para un TipoCalle activo
	 * 
	 * @param pTipoCalle
	 * @throws Exception
	 */
	private void validarTipoCalle(TipoCalle pTipoCalle) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoCalle.class).add(Restriccion.LIKE("nombre", pTipoCalle.getNombre(), false, Posicion.EXACTA))
				.add(Restriccion.IGUAL("activo", new Boolean(true))).setProyeccion(Proyeccion.COUNT());

		if(pTipoCalle.getIdTipoCalle() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idTipoCalle", pTipoCalle.getIdTipoCalle())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(23);
		}

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.TipoCalle addTipoCalle(com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle) throws Exception {

		this.validarTipoCalle(pTipoCalle);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoCalle);

		this.entityManager.persist(pTipoCalle);
		this.entityManager.flush();
		this.entityManager.refresh(pTipoCalle);

		return pTipoCalle;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.TipoCalle updateTipoCalle(com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle) throws Exception {

		this.validarTipoCalle(pTipoCalle);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoCalle);

		this.entityManager.merge(pTipoCalle);

		this.entityManager.flush();

		return pTipoCalle;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroTipoCalle findListaTiposCalle(FiltroTipoCalle filtro) {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoCalle.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.NOT(Restriccion.IGUAL("activo", new Boolean(false))));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoCalle.serialVersionUID, "idTipoCalle", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Business method
	 * 
	 * @throws CatastroException
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Servicio addServicio(com.trascender.catastro.recurso.persistent.Servicio pServicio) throws Exception {

		this.validarServicio(pServicio);

		this.entityManager.persist(pServicio);
		this.entityManager.flush();
		this.entityManager.refresh(pServicio);

		return pServicio;

	}

	/**
	 * Valida que no aya un servicio con el mismo nombre
	 * 
	 * @param pServicio
	 */
	private void validarServicio(Servicio pServicio) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Servicio.class).setProyeccion(Proyeccion.COUNT());

		if(pServicio.getIdServicio() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idServicio", pServicio.getIdServicio())));
		}

		locCriterio.add(Restriccion.LIKE("nombre", pServicio.getNombre(), false, Posicion.EXACTA)).add(Restriccion.IGUAL("estado", Servicio.Estado.ACTIVO));

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(37);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Servicio updateServicio(com.trascender.catastro.recurso.persistent.Servicio pServicio) throws Exception {

		this.validarServicio(pServicio);

		this.entityManager.merge(pServicio);

		return pServicio;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findServicio(java.lang.String pNombre) {

		return Criterio.getInstance(this.entityManager, Servicio.class).add(Restriccion.LIKE("nombre", pNombre, false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.IGUAL("estado", Servicio.Estado.ACTIVO)).list();

	}

	/**
	 * Business method
	 * 
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Zona addZona(com.trascender.catastro.recurso.persistent.Zona pZona) throws Exception {
		this.validarZona(pZona);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pZona);

		this.entityManager.persist(pZona);
		this.entityManager.flush();
		this.entityManager.refresh(pZona);

		return pZona;
	}

	/**
	 * Valida que no aya una zona con el mismo nombre
	 * 
	 * @param pSession
	 * @param pZona
	 * @throws Exception
	 */
	private void validarZona(Zona pZona) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Zona.class);

		locCriterio.add(Restriccion.LIKE("nombre", pZona.getNombre(), false)).add(Restriccion.IGUAL("estado", Zona.Estado.ACTIVO))
				.add(Restriccion.DISTINTO("idZona", pZona.getIdZona())).setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(27);
		}

	}

	/**
	 * 
	 * @param pZona
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteZona(com.trascender.catastro.recurso.persistent.Zona pZona) throws Exception {

		this.validarAsociacionesZona(pZona);
		pZona.setEstado(Zona.Estado.INACTIVO);
		this.updateZona(pZona);
	}

	/**
	 * Valida que la Zona no tenga Parcelas asociadas
	 * 
	 * @param pZona
	 * @throws CatastroException
	 */
	private void validarAsociacionesZona(Zona pZona) throws CatastroException {

		if(pZona.getListaAsociacionParcela().size() > 0) {
			throw new CatastroException(100);
		}

	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteTipoCalle(com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle) throws Exception {

		this.validarAsociacionesTipoCalle(pTipoCalle);
		pTipoCalle.setActivo(false);

		this.entityManager.merge(pTipoCalle);
	}

	/**
	 * Valida que el tipo de calle no tenga calles asociadas
	 * 
	 * @param pTipoCalle
	 * @throws CatastroException
	 */
	private void validarAsociacionesTipoCalle(TipoCalle pTipoCalle) throws CatastroException {
		if((Long) Criterio.getInstance(this.entityManager, Calle.class).add(Restriccion.IGUAL("tipoCalle", pTipoCalle)).setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new CatastroException(36);
		}

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Zona updateZona(com.trascender.catastro.recurso.persistent.Zona pZona) throws Exception {

		this.validarZona(pZona);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pZona);
		this.entityManager.merge(pZona);
		this.entityManager.flush();
		return pZona;
	}

	/**
	 * Valida que no aya una manzana activa con ese mismo nombre
	 * 
	 * @param pManzana
	 * @throws Exception
	 */
	private void validarManzana(Manzana pManzana) throws Exception {
		if(pManzana.getListaCuadrasDelimitantes() != null && !pManzana.getListaCuadrasDelimitantes().isEmpty() && !pManzana.isActivo()) {
			throw new CatastroException(69);
		}

		if(!pManzana.isActivo() && pManzana.getIdManzana() != -1) {
			if((Long) Criterio.getInstance(this.entityManager, Parcela.class).add(Restriccion.IGUAL("manzana", pManzana)).setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
				throw new CatastroException(107);
			}
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Manzana.class);

		locCriterio.add(Restriccion.OR(Restriccion.LIKE("nombre", pManzana.getNombre(), false, Posicion.EXACTA), Restriccion.IGUAL("nroManzana", pManzana.getNroManzana()))).add(
				Restriccion.IGUAL("activo", new Boolean(true)));
		// .setProyeccion(Proyeccion.COUNT());

		if(pManzana.getIdManzana() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idManzana", pManzana.getIdManzana())));
		}

		for(Object cadaOb : locCriterio.list()) {
			Manzana cadaManzana = (Manzana) cadaOb;

			if(pManzana.getNombre().equalsIgnoreCase(cadaManzana.getNombre())) {
				throw new CatastroException(30);
			}

			if(pManzana.getNroManzana().equals(cadaManzana.getNroManzana())) {
				throw new CatastroException(40);
			}
		}
		if(pManzana.getListaCuadrasDelimitantes() != null && !pManzana.getListaCuadrasDelimitantes().isEmpty()) {
			for(Cuadra locCuadra : pManzana.getListaCuadrasDelimitantes()) {
				locCuadra.setManzana(pManzana);
			}
		}

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana addManzana(com.trascender.catastro.recurso.persistent.Manzana pManzana) throws Exception {

		this.validarManzana(pManzana);

		pManzana.setActivo(true);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pManzana);

		this.entityManager.persist(pManzana);
		this.entityManager.merge(pManzana);// merge en cascada a las cuadras
		this.entityManager.flush();

		return pManzana;

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana updateManzana(com.trascender.catastro.recurso.persistent.Manzana pManzana) throws Exception {

		this.validarManzana(pManzana);
		this.validarAsociacionesParcelaManzana(pManzana);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pManzana);

		Manzana locManzana = this.entityManager.find(Manzana.class, pManzana.getIdManzana());

		locManzana.toString();
		locManzana.getListaCuadrasDelimitantes().toString();

		entityManager.detach(locManzana);

		for(Cuadra cadaCuadra : locManzana.getListaCuadrasDelimitantes()) {

			if(!pManzana.getListaCuadrasDelimitantes().contains(cadaCuadra)) {
				System.out.println("BORRE 1 CUADRA: " + cadaCuadra);
				cadaCuadra.setManzana(null);
				// for( Object cadaObjeto : Criterio.getInstance(this.entityManager, ParcelaPorCuadra.class)
				// .crearAlias("cuadra.manzana", "locManzana")
				// .add(Restriccion.IGUAL("locManzana", pManzana))
				// .list()){
				// this.entityManager.remove(cadaObjeto);
				// }
			}
			this.entityManager.merge(cadaCuadra);
		}

		this.entityManager.merge(pManzana);

		this.entityManager.flush();

		return pManzana;
	}

	/**
	 * Recupera una lista de manzanas a partir de las primeras letras del nombre
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroManzana findListaManzanas(FiltroManzana filtro) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Manzana.class);

		AtributoDinamico.addRestriccionesCriterio(locCriterio, Manzana.serialVersionUID, "idManzana", filtro.getListaAtributoDinamico());

		locCriterio.add(Restriccion.ILIKE("nombre", filtro.getNombre())).add(Restriccion.IGUAL("nroManzana", filtro.getNumero())).add(Restriccion.IGUAL("activo", new Boolean(true)))
				.add(Restriccion.IGUAL("listaCuadrasDelimitantes.calle", filtro.getCalle()))
				.add(Restriccion.IGUAL("listaCuadrasDelimitantes.listaParcelasPorCuadra.cuadra", filtro.getCuadra()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Manzana.serialVersionUID, "idManzana", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Recupera una manzana a partir del número de manzana
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana findManzana(Integer pNumero) throws Exception {

		Manzana locManzana = (Manzana) Criterio.getInstance(this.entityManager, Manzana.class).add(Restriccion.IGUAL("nroManzana", pNumero)).uniqueResult();

		if(locManzana == null) {
			throw new CatastroException(32);
		}

		locManzana.toString();
		for(Object cadaOb : locManzana.getListaCuadrasDelimitantes()) {
			cadaOb.toString();
		}

		return locManzana;

	}

	/**
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana getManzanaPorId(long pId) throws Exception {
		Manzana locManzana = (Manzana) Criterio.getInstance(this.entityManager, Manzana.class).add(Restriccion.IGUAL("idManzana", pId)).uniqueResult();

		locManzana.toString();
		locManzana.getListaAsociacionParcelaManzana().size();
		for(Cuadra cadaCuadra : locManzana.getListaCuadrasDelimitantes()) {
			cadaCuadra.toString();
		}
		locManzana.getListaAtributosDinamicos().size();

		if(locManzana != null) {
			locManzana.getListaLogsAuditoria().size();
		}

		return locManzana;
	}

	public TipoCalle getTipoCallePorId(long pIdTipoCalle) throws Exception {
		TipoCalle locTipoCalle = this.entityManager.find(TipoCalle.class, pIdTipoCalle);

		locTipoCalle.toString();

		if(locTipoCalle != null) {
			locTipoCalle.getListaLogsAuditoria().size();
		}

		return locTipoCalle;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Cuadra addCuadra(com.trascender.catastro.recurso.persistent.Cuadra pCuadra) throws Exception {

		this.validarCuadra(pCuadra);
		pCuadra.setActivo(true);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pCuadra);

		this.entityManager.persist(pCuadra);
		this.entityManager.flush();
		this.entityManager.refresh(pCuadra);

		return pCuadra;
	}

	/**
	 * Valida que las numeraciones de las cuadras sean validas
	 * 
	 * @param pCuadra
	 * @throws CatastroException
	 */
	private void validarCuadra(Cuadra pCuadra) throws CatastroException {
		// if ((pCuadra.getNumeracionDesde() <= 0)
		// || (pCuadra.getNumeracionHasta() <= 0)) {
		// throw new CatastroException(47);
		// }

		if((Long) Criterio.getInstance(this.entityManager, Cuadra.class).add(Restriccion.DISTINTO("idCuadra", pCuadra.getIdCuadra()))
				.add(Restriccion.IGUAL("calle", pCuadra.getCalle())).add(Restriccion.IGUAL("calleComienza", pCuadra.getCalleComienza()))
				.add(Restriccion.IGUAL("calleFinaliza", pCuadra.getCalleFinaliza())).add(Restriccion.IGUAL("tipoNumeracion", pCuadra.getTipoNumeracion()))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new CatastroException(109);
		}

		// if (pCuadra.getNumeracionDesde() >= pCuadra.getNumeracionHasta())
		// throw new CatastroException(46);

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public com.trascender.catastro.recurso.persistent.Cuadra updateCuadra(com.trascender.catastro.recurso.persistent.Cuadra pCuadra) throws Exception {

		this.validarCuadra(pCuadra);
		this.validarAsociacionesParcelaCuadra(pCuadra);

		if(!pCuadra.isActivo()) {
			this.validarAsociacionesCuadra(pCuadra);
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pCuadra);

		this.entityManager.merge(pCuadra);

		this.entityManager.flush();

		return pCuadra;
	}

	/**
	 * Valida en el caso de que la cuadra se este dando de baja... no tenga parcelas asociadas.
	 * 
	 * @param pCuadra
	 * @throws CatastroException
	 */
	private void validarAsociacionesCuadra(Cuadra pCuadra) throws CatastroException {
		// validar que no haya parcelas asociadas
		if((Long) Criterio.getInstance(this.entityManager, ParcelaPorCuadra.class).add(Restriccion.IGUAL("cuadra", pCuadra)).setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new CatastroException(48);
		}

		// validar que no haya manzana asociada

		if((Long) Criterio.getInstance(entityManager, Manzana.class).crearAlias("listaCuadrasDelimitantes", "locCuadra").add(Restriccion.IGUAL("locCuadra", pCuadra))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new CatastroException(17);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroCuadra findListaCuadras(FiltroCuadra filtro) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Cuadra.class).add(Restriccion.IGUAL("calle", filtro.getCalle()))
				.add(Restriccion.IGUAL("calleComienza", filtro.getCalleComienza())).add(Restriccion.IGUAL("calleFinaliza", filtro.getCalleFinaliza()))
				.add(Restriccion.IGUAL("tipoNumeracion", filtro.getTipoNumeracion())).add(Restriccion.MAYOR("numeracionDesde", filtro.getNumeracionDesde()))
				.add(Restriccion.MENOR("numeracionHasta", filtro.getNumeracionHasta())).add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.IGUAL("manzana", filtro.getManzana())).add(Restriccion.IGUAL("activo", true));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Cuadra.serialVersionUID, "idCuadra", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Calle updateCalle(com.trascender.catastro.recurso.persistent.Calle pCalle) throws Exception {
		this.validarCalle(pCalle);
		this.validarAsociacionesParcelaCalle(pCalle);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pCalle);

		this.entityManager.merge(pCalle);

		this.entityManager.flush();

		updateDomiciliosArmados(pCalle);
		
		return pCalle;
	}
	
	private void updateDomiciliosArmados(Calle pCalle) {
		Criterio locCriterio = Criterio.getInstance(entityManager, Domicilio.class)
				.add(Restriccion.OR(
						Restriccion.IGUAL("relacionCalle.idAbstractCalle", pCalle.getIdCalle()),
						Restriccion.IGUAL("relacionCalleComienza.idAbstractCalle", pCalle.getIdCalle()),
						Restriccion.IGUAL("relacionCalleFinaliza.idAbstractCalle", pCalle.getIdCalle())));
		List<Domicilio> listaDomicilios = locCriterio.list();
		for (Domicilio cadaDomicilio : listaDomicilios) {
			cadaDomicilio.armarDomicilio();
		}
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteServicio(com.trascender.catastro.recurso.persistent.Servicio pServicio) throws Exception {

		this.validarAsociacionesServicio(pServicio);

		pServicio = this.entityManager.merge(pServicio);
		this.entityManager.remove(pServicio);
	}

	private void validarAsociacionesServicio(Servicio pServicio) {
		// TODO buscar a que esta asociado el servicio y validar XD
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaCuadrasPorManzana(com.trascender.catastro.recurso.persistent.Manzana pManzana) throws Exception {
		Manzana locManzana = (Manzana) Criterio.getInstance(this.entityManager, Manzana.class).add(Restriccion.IGUAL("idManzana", pManzana.getIdManzana())).uniqueResult();

		locManzana.toString();
		for(Object cadaOb : locManzana.getListaCuadrasDelimitantes()) {
			cadaOb.toString();
		}

		return locManzana.getListaCuadrasDelimitantes();

	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Calle getCallePorId(Long pId) throws Exception {

		Calle locCalle = (Calle) Criterio.getInstance(this.entityManager, Calle.class).add(Restriccion.IGUAL("idCalle", pId)).uniqueResult();

		locCalle.toString();
		locCalle.getListaAsociacionParcelaCalle().size();

		if(locCalle != null) {
			locCalle.getListaLogsAuditoria().size();
		}

		for(Object cadaOb : locCalle.getListaCuadras()) {
			cadaOb.toString();
		}

		for(Object cadaOb : locCalle.getListaCuadrasComenzadas()) {
			cadaOb.toString();
		}

		for(Object cadaOb : locCalle.getListaCuadrasFinalizadas()) {
			cadaOb.toString();
		}

		return locCalle;

	}

	/**
	 * recupera una cuadra por el número de identificación único
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Cuadra getCuadraPorId(Long pId) throws Exception {

		Cuadra locCuadra = (Cuadra) Criterio.getInstance(this.entityManager, Cuadra.class).add(Restriccion.IGUAL("idCuadra", pId)).uniqueResult();

		locCuadra.toString();
		locCuadra.getListaAsociacionParcelaCuadra().size();

		if(locCuadra != null) {
			locCuadra.getListaLogsAuditoria().size();
		}

		for(Object cadaOb : locCuadra.getListaParcelasPorCuadra()) {
			cadaOb.toString();
		}
		locCuadra.getListaAtributosDinamicos().size();

		return locCuadra;

	}

	private void validarAsociacionesParcelaManzana(Manzana pManzana) {
		List<AsociacionParcelaManzana> locListaAsociacionesPersistidas = new ArrayList<AsociacionParcelaManzana>();

		for(AsociacionParcelaManzana cadaAsociacion : pManzana.getListaAsociacionParcelaManzana()) {
			if(cadaAsociacion.getIdAsociacionParcela() != -1) {
				locListaAsociacionesPersistidas.add(cadaAsociacion);
			}
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsociacionParcelaManzana.class).add(Restriccion.IGUAL("manzana", pManzana))
				.add(Restriccion.NOT(Restriccion.EN("e", locListaAsociacionesPersistidas)));
		locCriterio.setModoDebug(true);

		for(Object cadaObj : locCriterio.list()) {
			AsociacionParcelaManzana cadaAsociacion = (AsociacionParcelaManzana) cadaObj;
			entityManager.remove(cadaAsociacion);
		}
	}

	private void validarAsociacionesParcelaCalle(Calle pCalle) {
		List<AsociacionParcelaCalle> locListaAsociacionesPersistidas = new ArrayList<AsociacionParcelaCalle>();

		for(AsociacionParcelaCalle cadaAsociacion : pCalle.getListaAsociacionParcelaCalle()) {
			if(cadaAsociacion.getIdAsociacionParcela() != -1) {
				locListaAsociacionesPersistidas.add(cadaAsociacion);
			}
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsociacionParcelaCalle.class).add(Restriccion.IGUAL("calle", pCalle))
				.add(Restriccion.NOT(Restriccion.EN("e", locListaAsociacionesPersistidas)));
		locCriterio.setModoDebug(true);

		for(Object cadaObj : locCriterio.list()) {
			AsociacionParcelaCalle cadaAsociacion = (AsociacionParcelaCalle) cadaObj;
			entityManager.remove(cadaAsociacion);
		}
	}

	private void validarAsociacionesParcelaCuadra(Cuadra pCuadra) {
		List<AsociacionParcelaCuadra> locListaAsociacionesPersistidas = new ArrayList<AsociacionParcelaCuadra>();

		for(AsociacionParcelaCuadra cadaAsociacion : pCuadra.getListaAsociacionParcelaCuadra()) {
			if(cadaAsociacion.getIdAsociacionParcela() != -1) {
				locListaAsociacionesPersistidas.add(cadaAsociacion);
			}
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsociacionParcelaCuadra.class).add(Restriccion.IGUAL("cuadra", pCuadra))
				.add(Restriccion.NOT(Restriccion.EN("e", locListaAsociacionesPersistidas)));
		locCriterio.setModoDebug(true);

		for(Object cadaObj : locCriterio.list()) {
			AsociacionParcelaCuadra cadaAsociacion = (AsociacionParcelaCuadra) cadaObj;
			entityManager.remove(cadaAsociacion);
		}
	}
}

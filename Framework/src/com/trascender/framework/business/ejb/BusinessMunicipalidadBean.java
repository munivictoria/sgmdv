
package com.trascender.framework.business.ejb;

import java.rmi.RemoteException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
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
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.filtros.FiltroCodigoCiiu;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.filtros.FiltroDigestoMunicipal;
import com.trascender.framework.recurso.filtros.FiltroLocalidad;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.filtros.FiltroProvincia;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.framework.util.Util;

@Stateless(name = "ejb/BusinessMunicipalidad")
public class BusinessMunicipalidadBean implements BusinessMunicipalidadLocal {

	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessMunicipalidadBean.serialVersionUID);
		grupo.setNombre(BusinessMunicipalidadBean.NOMBRE);

		// RECURSOS ADMINISTRADOS
		Recurso municipalidad = new Recurso();
		Recurso area = new Recurso();
		Recurso localidad = new Recurso();
		Recurso provincia = new Recurso();
		Recurso pais = new Recurso();
		Recurso codigoCiiu = new Recurso();
		Recurso diaFeriado = new Recurso();
		Recurso digestoMunicipal = new Recurso();
		Recurso secretaria = new Recurso();

		municipalidad.setIdRecurso(Municipalidad.serialVersionUID);
		municipalidad.setNombre("Municipalidad");
		municipalidad.setAtributosConsultables("Nombre", "nombre", "Teléfono", "telefono", "Domicilio", "domicilio");
		municipalidad.setClase(Municipalidad.class);

		area.setIdRecurso(Area.serialVersionUID);
		area.setNombre("Área");
		area.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Secretaría", "secretaria");
		area.setClase(Area.class);

		secretaria.setIdRecurso(Secretaria.serialVersionUID);
		secretaria.setNombre("Secretaría");
		secretaria.setAtributosConsultables("Nombre", "nombre");
		secretaria.setClase(Secretaria.class);

		localidad.setIdRecurso(Localidad.serialVersionUID);
		localidad.setNombre("Localidad");
		localidad.setAtributosConsultables("Nombre", "nombre", "Código postal", "codigoPostal", "Provincia", "provincia");
		localidad.setClase(Localidad.class);

		provincia.setIdRecurso(Provincia.serialVersionUID);
		provincia.setNombre("Provincia");
		provincia.setAtributosConsultables("Nombre", "nombre", "Abreviatura", "abreviatura", "País", "pais");
		provincia.setClase(Provincia.class);

		pais.setIdRecurso(Pais.serialVersionUID);
		pais.setNombre("País");
		pais.setAtributosConsultables("Nombre", "nombre", "Abreviatura", "abreviatura");
		pais.setClase(Pais.class);

		codigoCiiu.setIdRecurso(CodigoCiiu.serialVersionUID);
		codigoCiiu.setNombre("Codigos CIIU");
		codigoCiiu.setAtributosConsultables("Código", "codigo", "Descripción", "descripcion", Tipo.TEXTO_LARGO);
		codigoCiiu.setClase(CodigoCiiu.class);

		Recurso calendario = new Recurso();
		calendario.setIdRecurso(Calendario.serialVersionUID);
		calendario.setNombre("Calendario Municipal");
		calendario.setAtributosConsultables("Nombre", "nombre", "Año", "anio", "Plan", "plan");
		calendario.setClase(Calendario.class);
		grupo.getListaRecursos().add(calendario);

		grupo.getListaRecursos().add(area);
		grupo.getListaRecursos().add(secretaria);
		grupo.getListaRecursos().add(municipalidad);
		grupo.getListaRecursos().add(localidad);
		grupo.getListaRecursos().add(provincia);
		grupo.getListaRecursos().add(pais);
		grupo.getListaRecursos().add(diaFeriado);
		grupo.getListaRecursos().add(codigoCiiu);

		diaFeriado.setIdRecurso(DiaFeriado.serialVersionUID);
		diaFeriado.setNombre("Día Feriado");
		diaFeriado.setAtributosConsultables("Nombre", "nombre", "Fecha", "fecha", Tipo.FECHA);
		diaFeriado.setClase(DiaFeriado.class);

		Grupo locGrupoDigesto = new Grupo();
		locGrupoDigesto.setNombre("MGC| Adm. de Despacho");
		locGrupoDigesto.setId(DigestoMunicipal.serialVersionUID);

		digestoMunicipal.setIdRecurso(DigestoMunicipal.serialVersionUID);
		digestoMunicipal.setNombre("Admin. Dec. Ord. Res.");
		digestoMunicipal.setAtributosConsultables("Número", "numero", "Tipo", "tipo", "Eje Temático", "ejeTematico", "Descripción", "descripcion", "Estado", "estado");
		digestoMunicipal.setClase(DigestoMunicipal.class);
		locGrupoDigesto.getListaRecursos().add(digestoMunicipal);

		SecurityMgr.getInstance().addGrupo(grupo);
		SecurityMgr.getInstance().addGrupo(locGrupoDigesto);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -575137816902099037L;

	private static final String NOMBRE = "FRM|Adm. de la Municipalidad";

	@PersistenceContext(name = "vipians")
	private EntityManager entity;

	@EJB
	private BusinessParametroLocal businessParametro;

	public BusinessMunicipalidadBean() {
		super();
	}

	public void setSessionContext(SessionContext pCtx) throws EJBException, RemoteException {

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
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateMunicipalidad(com.trascender.framework.recurso.persistent.Municipalidad pMunicipalidad) throws java.lang.Exception {

		if(pMunicipalidad.getIdMunicipalidad() == -1) {
			pMunicipalidad.setIdMunicipalidad(Municipalidad.ID_MUNICIPALIDAD_CRESPO);
			entity.persist(pMunicipalidad);
		} else {
			TrascenderEnverListener.setValoresEnAuditoriaBean(pMunicipalidad);

			entity.merge(pMunicipalidad);

			entity.flush();
		}
		SecurityMgr.getInstance().setMunicipalidad(pMunicipalidad);
	}

	public FiltroMunicipalidad getMunicipalidad(FiltroMunicipalidad pFiltro) throws java.lang.Exception {
		Municipalidad locMunicipalidad = entity.find(Municipalidad.class, Municipalidad.ID_MUNICIPALIDAD_CRESPO);
		if(locMunicipalidad == null) {
			locMunicipalidad = new Municipalidad();
			locMunicipalidad.setIdMunicipalidad(Municipalidad.ID_MUNICIPALIDAD_CRESPO);
			locMunicipalidad.setNombre("Municipalidad por defecto");
			entity.persist(locMunicipalidad);
		}

		if(locMunicipalidad != null) {
			locMunicipalidad.getListaLogsAuditoria().size();
		}

		List<Municipalidad> locLista = new ArrayList<Municipalidad>(1);
		locLista.add(locMunicipalidad);
		pFiltro.setListaResultados(locLista);

		return pFiltro;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void addArea(Area pArea) throws java.lang.Exception {

		this.validarArea(pArea);
		pArea.setEstado(Area.Estado.ACTIVO);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pArea);
		entity.persist(pArea);
		this.entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateArea(Area pArea) throws Exception {

		this.validarArea(pArea);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pArea);

		entity.merge(pArea);

		entity.flush();
	}

	public FiltroArea findArea(FiltroArea filtro) throws java.lang.Exception {

		if(filtro.getEstado() == null) {
			filtro.setEstado(Area.Estado.ACTIVO);
		}

		Criterio locCriterio = Criterio.getInstance(entity, Area.class).add(Restriccion.ILIKE("nombre", filtro.getNombre())).add(Restriccion.IGUAL("estado", filtro.getEstado()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Area.serialVersionUID, "idArea", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public Pais addPais(com.trascender.framework.recurso.persistent.Pais pPais) throws Exception {

		this.validarPais(pPais);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPais);

		entity.persist(pPais);
		entity.flush();

		return pPais;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updatePais(com.trascender.framework.recurso.persistent.Pais pPais) throws Exception {
		this.validarPais(pPais);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPais);

		entity.merge(pPais);

		entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void removePais(com.trascender.framework.recurso.persistent.Pais pPais) throws Exception {
		pPais = entity.find(Pais.class, pPais.getIdPais());
		validarBorradoPais(pPais);
		entity.remove(pPais);
	}

	private void validarBorradoPais(Pais pPais) throws TrascenderException {
		Long cantidad = Criterio.getInstance(entity, Provincia.class).add(Restriccion.IGUAL("pais", pPais)).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderFrameworkException(17);
		}
	}

	public Pais getPaisPorId(long pId) {
		Pais locPais = entity.find(Pais.class, pId);

		if(locPais != null) {
			locPais.getListaLogsAuditoria().size();
		}

		return locPais;
	}

	public DiaFeriado getDiaFeriadoPorId(long pId) {
		DiaFeriado locDiaFeriado = entity.find(DiaFeriado.class, pId);

		if(locDiaFeriado != null) {
			locDiaFeriado.getListaLogsAuditoria().size();
		}

		return locDiaFeriado;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public Provincia addProvincia(com.trascender.framework.recurso.persistent.Provincia pProvincia) throws Exception {

		this.validarProvincia(pProvincia);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pProvincia);
		entity.persist(pProvincia);
		entity.flush();
		return pProvincia;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateProvincia(com.trascender.framework.recurso.persistent.Provincia pProvincia) throws Exception {
		this.validarProvincia(pProvincia);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pProvincia);

		entity.merge(pProvincia);

		entity.flush();
	}

	public FiltroProvincia findProvincia(FiltroProvincia filtro) throws com.trascender.framework.exception.TrascenderFrameworkException {

		Criterio locCriterio = Criterio.getInstance(entity, Provincia.class).add(Restriccion.ILIKE("nombre", filtro.getProvincia())).add(Restriccion.IGUAL("pais", filtro.getPais()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Provincia.serialVersionUID, "idProvincia", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	public Provincia getProvinciaPorId(long pId) {
		Provincia locProvincia = entity.find(Provincia.class, pId);

		if(locProvincia != null) {
			locProvincia.getListaLogsAuditoria().size();
		}

		return locProvincia;
	}

	public FiltroPais findPais(FiltroPais filtro) throws com.trascender.framework.exception.TrascenderFrameworkException {

		Criterio locCriterio = Criterio.getInstance(entity, Pais.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Pais.serialVersionUID, "idPais", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void addLocalidad(com.trascender.framework.recurso.persistent.Localidad pLocalidad) throws Exception {
		validarLocalidad(pLocalidad);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pLocalidad);
		entity.persist(pLocalidad);
		entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateLocalidad(com.trascender.framework.recurso.persistent.Localidad pLocalidad) throws Exception {
		validarLocalidad(pLocalidad);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pLocalidad);

		entity.merge(pLocalidad);

		entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void removeLocalidad(com.trascender.framework.recurso.persistent.Localidad pLocalidad) throws Exception {
		this.validarBorradoLocalidad(pLocalidad);
		pLocalidad = entity.find(Localidad.class, pLocalidad.getIdLocalidad());
		entity.remove(pLocalidad);
	}

	private void validarBorradoLocalidad(Localidad pLocalidad) throws TrascenderException {
		// Valida que no este seleccionada en ningun domicilio
		Criterio locCriterio = Criterio.getInstance(entity, Domicilio.class).add(Restriccion.IGUAL("localidad", pLocalidad)).setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderFrameworkException(18);
		}
	}

	public FiltroLocalidad findLocalidad(FiltroLocalidad filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, Localidad.class).setModoDebug(true);

		if(filtro.getCodPostal() != null && filtro.getCodPostal().trim() != "") {
			if(filtro.getCodPostal().length() > 4) {
				throw new TrascenderFrameworkException(330);
			}

			if(!comprobarCodigoPostal(filtro.getCodPostal())) {
				throw new TrascenderFrameworkException(331);
			}

			locCriterio.add(Restriccion.LIKE("codigoPostal", filtro.getCodPostal()));
		}

		locCriterio.add(Restriccion.ILIKE("nombre", filtro.getNombre()));
		locCriterio.add(Restriccion.IGUAL("provincia", filtro.getProvincia()));
		locCriterio.add(Restriccion.IGUAL("provincia.pais", filtro.getPais()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Localidad.serialVersionUID, "idLocalidad", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		for(Localidad cadaLocalidad : filtro.getListaResultados()) {
			cadaLocalidad.toString();
			cadaLocalidad.getProvincia().toString();
			cadaLocalidad.getProvincia().getPais().toString();

		}

		return filtro;
	}

	public Localidad getLocalidadPorId(long pId) {
		Localidad locLocalidad = entity.find(Localidad.class, pId);

		if(locLocalidad != null) {
			locLocalidad.getListaLogsAuditoria().size();
			locLocalidad.toString();
			locLocalidad.getProvincia().toString();
			locLocalidad.getProvincia().getPais().toString();
		}

		return locLocalidad;
	}

	private void validarLocalidad(Localidad pLocalidad) throws TrascenderFrameworkException {
		Long cantidad = (Long) Criterio.getInstance(entity, Localidad.class).add(Restriccion.LIKE("nombre", pLocalidad.getNombre(), false, Posicion.EXACTA))
				.add(Restriccion.IGUAL("provincia", pLocalidad.getProvincia())).add(Restriccion.DISTINTO("idLocalidad", pLocalidad.getIdLocalidad())).setProyeccion(Proyeccion.COUNT())
				.uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderFrameworkException(4);
		}

	}

	/**
	 * Valida un codigo postal (de una localidad. 4 digitos)
	 * 
	 * @param pCodigoPostal
	 * @return
	 */
	private Boolean comprobarCodigoPostal(String pCodigoPostal) {
		Boolean locIsValido = false;

		int locContador = 0;
		String locNumeros = "0123456789";

		for(Character cadaCaracterCP : pCodigoPostal.toCharArray()) {
			for(Character cadaCaracterNumerico : locNumeros.toCharArray()) {
				if(cadaCaracterCP == cadaCaracterNumerico) {
					locContador++;
				}
			}
		}
		if(locContador == pCodigoPostal.length()) {
			locIsValido = true;
		}
		return locIsValido;

	}

	/**
	 * comprueba si la provincia es valida (por nombre y codigo)
	 * 
	 * @param pProvincia
	 * @return True= si la provincia es valida - False= si la provincia es invalida
	 * @throws Exception
	 */
	private void validarProvincia(Provincia pProvincia) throws Exception {
		List<Provincia> listaProvincias = Criterio
				.getInstance(entity, Provincia.class)
				.add(Restriccion.OR(Restriccion.LIKE("nombre", pProvincia.getNombre(), false, Posicion.EXACTA),
						Restriccion.LIKE("codigo", pProvincia.getCodigo(), false, Posicion.EXACTA), Restriccion.LIKE("abreviatura", pProvincia.getAbreviatura(), false, Posicion.EXACTA)))
				.add(Restriccion.IGUAL("pais", pProvincia.getPais())).add(Restriccion.DISTINTO("idProvincia", pProvincia.getIdProvincia())).list();

		for(Provincia cadaProvincia : listaProvincias) {

			if(cadaProvincia.getIdProvincia() != pProvincia.getIdProvincia()) {

				if(cadaProvincia.getNombre().equalsIgnoreCase(pProvincia.getNombre())) {
					throw new TrascenderFrameworkException(11);
				}

				if(cadaProvincia.getCodigo().equalsIgnoreCase(pProvincia.getCodigo())) {
					throw new TrascenderFrameworkException(12);
				}

				if(cadaProvincia.getAbreviatura().equalsIgnoreCase(pProvincia.getAbreviatura())) {
					throw new TrascenderFrameworkException(13);
				}
			}
		}

	}

	/**
	 * Valida unicidad para un pais
	 * 
	 * @param pPais
	 * @throws TrascenderFrameworkException
	 */

	private void validarPais(Pais pPais) throws TrascenderFrameworkException {

		List<Pais> listaPais = Criterio
				.getInstance(entity, Pais.class)
				.add(Restriccion.OR(Restriccion.LIKE("nombre", pPais.getNombre(), false, Posicion.EXACTA),
						Restriccion.LIKE("abreviatura", pPais.getAbreviatura(), false, Posicion.EXACTA))).add(Restriccion.DISTINTO("idPais", pPais.getIdPais())).list();

		for(Pais locPais : listaPais) {
			if(locPais.getNombre().equalsIgnoreCase(pPais.getNombre())) {
				throw new TrascenderFrameworkException(14);
			}
			if(locPais.getAbreviatura().equalsIgnoreCase(pPais.getAbreviatura())) {
				throw new TrascenderFrameworkException(15);
			}
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void removeProvincia(com.trascender.framework.recurso.persistent.Provincia pProvincia) throws Exception {
		pProvincia = entity.find(Provincia.class, pProvincia.getIdProvincia());

		if((Long) Criterio.getInstance(this.entity, Localidad.class).add(Restriccion.IGUAL("provincia", pProvincia)).setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new TrascenderFrameworkException(16);
		}

		entity.remove(pProvincia);
	}

	/**
	 * Agrega un dia feriado al sistema
	 * 
	 * @param pDiaFeriado
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.DiaFeriado addDiaFeriado(com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado) throws Exception {
		this.validarDiaFeriado(pDiaFeriado);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDiaFeriado);
		entity.persist(pDiaFeriado);
		entity.flush();
		return pDiaFeriado;

	}

	/**
	 * Valida Unicidad para un area(Activa)
	 * 
	 * @param pArea
	 * @throws Exception
	 */
	private void validarArea(Area pArea) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, Area.class).add(Restriccion.LIKE("nombre", pArea.getNombre(), false, Posicion.EXACTA))
				.add(Restriccion.IGUAL("estado", Area.Estado.ACTIVO)).add(Restriccion.DISTINTO("idArea", pArea.getIdArea())).setProyeccion(Proyeccion.COUNT());

		Long cantidad = (Long) locCriterio.uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderFrameworkException(0);
		}

	}

	/**
	 * Valida unicidad para un dia feriado
	 * 
	 * @param pDiaFeriado
	 * @throws Exception
	 */
	private void validarDiaFeriado(DiaFeriado pDiaFeriado) throws Exception {
		List locListDiasFeriados = Criterio.getInstance(entity, DiaFeriado.class)
				.add(Restriccion.OR((Restriccion.IGUAL("fecha", pDiaFeriado.getFecha())), Restriccion.LIKE("nombre", pDiaFeriado.getNombre(), false, Posicion.EXACTA)))
				.add(Restriccion.DISTINTO("idDiaFeriado", pDiaFeriado.getIdDiaFeriado())).list();

		for(Object cadaOb : locListDiasFeriados) {
			DiaFeriado cadaDiaFeriado = (DiaFeriado) cadaOb;

			if(pDiaFeriado.getFecha().equals(cadaDiaFeriado.getFecha())) {
				throw new TrascenderFrameworkException(332);
			}

			Calendar fechaDiaFeriado = Calendar.getInstance();
			fechaDiaFeriado.setTime(pDiaFeriado.getFecha());
			int locAnioDelDiaFeriado = fechaDiaFeriado.get(Calendar.YEAR);
			FiltroDiaFeriado filtro = new FiltroDiaFeriado();
			filtro.setAnio(locAnioDelDiaFeriado);
			filtro.setNombre(pDiaFeriado.getNombre());
			List<DiaFeriado> locHayDiasFeriados = (List<DiaFeriado>) findListadoDiasFeriados(filtro);

			if(!locHayDiasFeriados.isEmpty()) {
				throw new TrascenderFrameworkException(333);
			}
		}

	}

	/**
	 * Actualiza los datos de un d�a feriado
	 * 
	 * @param pDiaFeriado
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.DiaFeriado updateDiaFeriado(com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado) throws Exception {
		validarDiaFeriado(pDiaFeriado);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pDiaFeriado);

		entity.merge(pDiaFeriado);

		entity.flush();

		return pDiaFeriado;
	}

	/**
	 * Elimina un d�a feriado f�sicamente
	 * 
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteDiaFeriado(DiaFeriado pDiaFeriado) throws Exception {
		pDiaFeriado = (DiaFeriado) Criterio.getInstance(this.entity, DiaFeriado.class).add(Restriccion.IGUAL("idDiaFeriado", pDiaFeriado.getIdDiaFeriado())).uniqueResult();

		this.entity.remove(pDiaFeriado);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroDiaFeriado findListadoDiasFeriados(FiltroDiaFeriado filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, DiaFeriado.class).add(Restriccion.LIKE("nombre", filtro.getNombre(), false, Posicion.AL_PRINCIPIO));

		if(filtro.getAnio() != null) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, filtro.getAnio());
			cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));

			Calendar cal2 = Calendar.getInstance();
			cal2.set(Calendar.YEAR, filtro.getAnio());
			cal2.set(Calendar.DAY_OF_YEAR, cal2.getActualMaximum(Calendar.DAY_OF_YEAR));
			locCriterio.add(Restriccion.MAYOR("fecha", cal.getTime()));
			locCriterio.add(Restriccion.MENOR("fecha", cal2.getTime()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DiaFeriado.serialVersionUID, "idDiaFeriado", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * permite validar Digesto Municipal - Captura de Excepciones.
	 * 
	 * @param pDigestoMunicipal
	 * @throws TrascenderFrameworkException
	 */
	private void validarDigestoMunicipal(DigestoMunicipal pDigestoMunicipal) throws TrascenderFrameworkException {
		if(pDigestoMunicipal.getTipo() == null) {
			throw new TrascenderFrameworkException(200);
		}
		if(pDigestoMunicipal.getFecha() == null) {
			throw new TrascenderFrameworkException(203);
		}
		if(pDigestoMunicipal.getFecha().after(Calendar.getInstance().getTime())) {
			throw new TrascenderFrameworkException(204);
		}
		if(pDigestoMunicipal.getDescripcion() == null) {
			throw new TrascenderFrameworkException(205);
		}

		Long cantidad = (Long) Criterio.getInstance(entity, DigestoMunicipal.class).add(Restriccion.IGUAL("numero", pDigestoMunicipal.getNumero()))
				.add(Restriccion.IGUAL("tipo", pDigestoMunicipal.getTipo())).add(Restriccion.NOT(Restriccion.IGUAL("idDigestoMunicipal", pDigestoMunicipal.getIdDigestoMunicipal())))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderFrameworkException(206);
		}

	}

	/**
	 * 
	 * @param pDigestoMunicipal
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.DigestoMunicipal addDigestoMunicipal(com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal)
			throws Exception {
		this.validarDigestoMunicipal(pDigestoMunicipal);
		System.out.println("DIGESTOS EN CONCORDANCIA: " + pDigestoMunicipal.getListaConcordancias());
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDigestoMunicipal);
		this.entity.merge(pDigestoMunicipal);
		this.entity.flush();
		return pDigestoMunicipal;
	}

	/**
	 * 
	 * @param pDigestoMunicipal
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.DigestoMunicipal updateDigestoMunicipal(com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal)
			throws Exception {

		this.validarDigestoMunicipal(pDigestoMunicipal);
		this.validarQuitadoConconrdancias(pDigestoMunicipal);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDigestoMunicipal);
		entity.merge(pDigestoMunicipal);
		this.entity.flush();
		return pDigestoMunicipal;
	}

	private void validarQuitadoConconrdancias(DigestoMunicipal pDigesto) {
		Criterio locCriterio = Criterio.getInstance(entity, DigestoMunicipal.class).setModoDebug(true).add(Restriccion.IGUAL("idDigestoMunicipal", pDigesto.getIdDigestoMunicipal()))
				.setProyeccion(Proyeccion.PROP("listaConcordancias"));

		List<DigestoMunicipal> locListaDigestos = locCriterio.list();
		for(DigestoMunicipal cadaDigesto : locListaDigestos) {
			if(!pDigesto.getListaConcordancias().contains(cadaDigesto)) {
				cadaDigesto.getListaConcordancias().remove(pDigesto);
			}
		}
	}

	/**
	 * 
	 * @param pDigestoMunicipal
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteDigestoMuncipal(com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal) throws Exception {
		if(!pDigestoMunicipal.getListaConcordancias().isEmpty()) {
			throw new TrascenderFrameworkException(328);
		}
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDigestoMunicipal);
		pDigestoMunicipal = entity.find(DigestoMunicipal.class, pDigestoMunicipal.getIdDigestoMunicipal());
		entity.remove(pDigestoMunicipal);
		// Una solucion medio chancha para detectar excepciones lanzadas de la base. :ohGodWhy: kill me please.
		try {
			entity.flush();
		} catch(Exception e) {
			e.printStackTrace();
			if(Util.getRootCause(e) instanceof BatchUpdateException) {
				throw new TrascenderFrameworkException(210);
			}
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public List<DigestoMunicipal> findListaDigestoMunicipalPorConcordancia(DigestoMunicipal pNodo) throws Exception {

		// aseguramiento de limpieza de basura
		if(pNodo != null) {
			pNodo.getListaConcordancias().clear();
		}

		Criterio locCriterio = Criterio.getInstance(entity, DigestoMunicipal.class);
		if(pNodo != null) {
			locCriterio.add(Restriccion.IGUAL("listaConcordancias", pNodo));
		}

		List<DigestoMunicipal> locListaDigestoMunicipal = locCriterio.list();
		for(DigestoMunicipal cadaDigestoMunicipal : locListaDigestoMunicipal) {
			cadaDigestoMunicipal.toString();
			this.refrescarDigestosHijos(cadaDigestoMunicipal);
		}

		return locListaDigestoMunicipal;
	}

	/**
	 * Recupera el digesto municipal por número de identificación único
	 * 
	 * @param pIdDigestoMunicipal
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	public DigestoMunicipal getDigestoMunicipalPorId(long pIdDigestoMunicipal) {
		DigestoMunicipal locDigesto = entity.find(DigestoMunicipal.class, pIdDigestoMunicipal);

		locDigesto.toString();
		for(DigestoMunicipal cadaDigesto : locDigesto.getListaConcordancias()) {
			refrescarDigestosHijos(cadaDigesto);
		}
		locDigesto.getListaAtributosDinamicos().size();
		locDigesto.getListaLogsAuditoria().size();

		return locDigesto;
	}

	/**
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "local"
	 */
	public Localidad getLocalidadMunicipal() throws TrascenderException {
		Municipalidad locMunicipalidad = entity.find(Municipalidad.class, 1l);
		if(locMunicipalidad == null) {
			throw new TrascenderFrameworkException(1010);
		}
		Localidad locLocalidad = null;
		if(locMunicipalidad.getDomicilio() != null) {
			locLocalidad = locMunicipalidad.getDomicilio().getLocalidad();
		}
		return locLocalidad;
	}

	private void refrescarDigestosHijos(DigestoMunicipal pDigesto) {
		for(DigestoMunicipal cadaDigesto : pDigesto.getListaConcordancias()) {
			cadaDigesto.toString();
		}
	}

	public FiltroCodigoCiiu findListaCodigosCiiu(FiltroCodigoCiiu filtro) {
		Criterio locCriterio = Criterio.getInstance(entity, CodigoCiiu.class).add(Restriccion.IGUAL("grupoCiiu", filtro.getGrupoCiiu()))
				.add(Restriccion.IGUAL("grupoCiiu.seccionCiiu", filtro.getSeccionCiiu())).add(Restriccion.LIKE("codigo", filtro.getCodigo(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.ILIKE("descripcion", filtro.getInformacion()));

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	public List<AuxIdEntidad> findListaAuxIdCodigoCiiu(String cadena) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, CodigoCiiu.class);

		locCriterio.add(Restriccion.ILIKE("codigo||' '||descripcion", cadena).SIN_PROCESAR_ENTIDADES());

		locCriterio.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idCodigoCiiu", "'['||codigo||'] ' ||descripcion").SIN_PROCESAR_ENTIDADES());

		List<AuxIdEntidad> locListaCodigosCiiu = locCriterio.list();

		return locListaCodigosCiiu;
	}

	public Area getAreaById(Long pId) throws Exception {
		Area locArea = Criterio.getInstance(this.entity, Area.class).add(Restriccion.IGUAL("idArea", pId)).uniqueResult();

		if(locArea != null) {
			locArea.getListaLogsAuditoria().size();
		}

		return locArea;
	}

	public CodigoCiiu getCodigoCiiuById(Long pId) throws Exception {
		CodigoCiiu locCodigoCiiu = Criterio.getInstance(this.entity, CodigoCiiu.class).add(Restriccion.IGUAL("idCodigoCiiu", pId)).uniqueResult();

		if(locCodigoCiiu != null) {
			locCodigoCiiu.toString();
			locCodigoCiiu.getGrupoCiiu().toString();
			locCodigoCiiu.getGrupoCiiu().getSeccionCiiu().toString();
		}

		return locCodigoCiiu;
	}

	public List<SeccionCiiu> findListaSeccionCiiu(String pCodigo, String pNombre) {
		Criterio locCriterio = Criterio.getInstance(entity, SeccionCiiu.class).add(Restriccion.ILIKE("codigo", pCodigo)).add(Restriccion.ILIKE("nombre", pNombre))
				.add(Orden.ASC("codigo"));
		return locCriterio.list();
	}

	public List<GrupoCiiu> findListaGrupoCiiu(String pCodigo, String pNombre, SeccionCiiu pSeccion) {
		Criterio locCriterio = Criterio.getInstance(entity, GrupoCiiu.class).add(Restriccion.ILIKE("codigo", pCodigo)).add(Restriccion.ILIKE("nombre", pNombre))
				.add(Restriccion.IGUAL("seccionCiiu", pSeccion)).add(Orden.ASC("codigo"));
		return locCriterio.list();
	}
	
	public void addCodigoCiiu(CodigoCiiu pCodigo) {
		this.entity.merge(pCodigo);
	}
	
	public void updateCodigoCiiu(CodigoCiiu pCodigo) {
		this.entity.merge(pCodigo);
	}
	
	public void deleteCodigoCiiu(CodigoCiiu pCodigo) {
		this.entity.remove(this.entity.merge(pCodigo));
	}

	public FiltroDigestoMunicipal findListaDigestosMunicipales(FiltroDigestoMunicipal pFiltro) {

		Criterio locCriterio = Criterio.getInstance(entity, DigestoMunicipal.class).add(Restriccion.IGUAL("numero", pFiltro.getNumero()))
				.add(Restriccion.IGUAL("tipo", pFiltro.getTipo())).add(Restriccion.IGUAL("estado", pFiltro.getEstado())).add(Restriccion.IGUAL("ejeTematico", pFiltro.getEjeTematico()));

		if(pFiltro.getAnio() != null) {
			Calendar locFechaDesde = Calendar.getInstance();
			Calendar locFechaHasta = Calendar.getInstance();

			locFechaDesde.set(pFiltro.getAnio(), Calendar.JANUARY, 1, 1, 1);
			locFechaHasta.set(pFiltro.getAnio(), Calendar.DECEMBER, 30, 1, 1);

			locCriterio.add(Restriccion.MAYOR("fecha", locFechaDesde.getTime()));
			locCriterio.add(Restriccion.MENOR("fecha", locFechaHasta.getTime()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DigestoMunicipal.serialVersionUID, "idDigestoMunicipal", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public Secretaria addSecretaria(Secretaria pSecretaria) {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pSecretaria);
		pSecretaria = this.entity.merge(pSecretaria);
		this.entity.flush();
		return pSecretaria;
	}

	public void updateSecretaria(Secretaria pSecretaria) {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pSecretaria);

		this.entity.merge(pSecretaria);

		this.entity.flush();
	}

	public void deleteSecretaria(Secretaria pSecretaria) throws TrascenderFrameworkException {
		if(pSecretaria.getListaAreas() == null) {
			this.entity.remove(this.entity.merge(pSecretaria));
		} else {
			throw new TrascenderFrameworkException(501);
		}
	}

	public FiltroSecretaria findListaSecretarias(FiltroSecretaria pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entity, Secretaria.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Secretaria.serialVersionUID, "idSecretaria", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public Secretaria getSecretariaPorId(long idSecretaria) {
		Secretaria locSecretaria = entity.find(Secretaria.class, idSecretaria);

		if(locSecretaria != null) {
			locSecretaria.getListaAreas().size();
			locSecretaria.getListaLogsAuditoria().size();
		}

		return locSecretaria;
	}

}

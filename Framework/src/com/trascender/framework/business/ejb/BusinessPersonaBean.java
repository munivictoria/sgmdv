
package com.trascender.framework.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.business.interfaces.BusinessPersonaLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroPersonaFisica;
import com.trascender.framework.recurso.filtros.FiltroPersonaJuridica;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.Socio;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessPersona" display-name="Name for BusinessPersona" description="Description for BusinessPersona" jndi-name="ejb/BusinessPersona"
 *           type="Stateless" view-type="local"
 */
@Stateless(name = "ejb/BusinessPersona")
public class BusinessPersonaBean implements BusinessPersonaLocal {
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessPersonaBean.serialVersionUID);
		grupo.setNombre(BusinessPersonaBean.NOMBRE);

		// RECURSOS ADMINISTRADOS
		Recurso personaFisica = new Recurso();
		Recurso personaJuridica = new Recurso();

		// INICIALIZACI�N DE RECURSOS
		personaFisica.setIdRecurso(PersonaFisica.serialVersionUID);
		personaFisica.setNombre("Persona Física");
		personaFisica.setAtributosConsultables("Cuil", "cuim", "Apellido", "apellido", "Nombre", "nombre", "Tipo Doc.", "tipoDocumento", "Nº Doc.", "numeroDocumento", "Estado civil",
				"estadoCivil");
		personaFisica.setClase(PersonaFisica.class);

		personaJuridica.setIdRecurso(PersonaJuridica.serialVersionUID);
		personaJuridica.setNombre("Persona Jurídica");
		personaJuridica.setAtributosConsultables("Cuit", "cuim", "Razón social", "razonSocial", "Estado", "estado");
		personaJuridica.setClase(PersonaJuridica.class);

		grupo.getListaRecursos().add(personaFisica);
		grupo.getListaRecursos().add(personaJuridica);
		SecurityMgr.getInstance().addGrupo(grupo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5546372250722018284L;
	public static final String NOMBRE = "FRM|Adm. de Personas";

	@PersistenceContext(name = "vipians")
	private EntityManager entity;

	@EJB
	private BusinessParametroLocal businessParametro;

	public BusinessPersonaBean() {
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
	 * Permite agregar una persona f�sica
	 * 
	 * @throws TrascenderFrameworkeExeption
	 *             (13) la persona fisica ya est� registrada Business method
	 * @ejb.interface-method view-type = "local"
	 */
	public void addPersonaFisica(PersonaFisica pPersona) throws java.lang.Exception {

		this.validarPersonaFisica(pPersona);
		pPersona.setEstado(Persona.Estado.ACTIVO);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPersona);
		this.entity.persist(pPersona);
		pPersona.postPersist();
		// Se hace flush para persistir correctamente los atributos dinamicos.
		this.entity.flush();
	}

	/**
	 * Valida que la persona física sea única por numero de documento y cuil
	 * 
	 * @param pPersona
	 *            persona a validar
	 */
	private void validarPersonaFisica(PersonaFisica pPersona) throws Exception {
		// ValidadorDinamicoCore.getInstance(this.entity,
		// PersonaFisica.class).empezarValidacion(pPersona);
		// Le agrego un filtro por Tipo de Documento, pues el mismo numero
		// puede repetirse si los tipos son distintos.
		Criterio locCriterio = Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("estado", Persona.Estado.ACTIVO))
				.add(Restriccion.IGUAL("tipoDocumento", pPersona.getTipoDocumento())).add(Restriccion.IGUAL("numeroDocumento", pPersona.getNumeroDocumento()))
				.add(Restriccion.DISTINTO("idPersona", pPersona.getIdPersona())).setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderFrameworkException(28);
		}

		locCriterio = Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("estado", Persona.Estado.ACTIVO)).add(Restriccion.IGUAL("cuim", pPersona.getCuil()))
				.add(Restriccion.DISTINTO("idPersona", pPersona.getIdPersona())).setProyeccion(Proyeccion.COUNT());

		cantidad = locCriterio.uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderFrameworkException(29);
		}

		locCriterio = Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("estado", Persona.Estado.ELIMINADO))
				.add(Restriccion.OR(Restriccion.IGUAL("cuim", pPersona.getCuil()), Restriccion.IGUAL("numeroDocumento", pPersona.getNumeroDocumento())))
				.add(Restriccion.DISTINTO("idPersona", pPersona.getIdPersona())).setProyeccion(Proyeccion.COUNT());

		cantidad = locCriterio.uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderFrameworkException(30);
		}
	}

	/**
	 * Permite actualizar los datos de una persona f�sica
	 * 
	 * @throws TrascenderFrameworkException
	 *             (14) la persona f�sica no existe Business method
	 * @ejb.interface-method view-type = "local"
	 */
	public void updatePersonaFisica(PersonaFisica pPersona) throws Exception {
		this.validarPersonaFisica(pPersona);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPersona);

		this.entity.merge(pPersona);
		this.entity.flush();
	}

	public void removePersonaFisica(PersonaFisica pPersona) throws TrascenderException {
		if(pPersona.getEstado() == Persona.Estado.ELIMINADO) {
			throw new TrascenderFrameworkException(26);
		}
		pPersona.setEstado(Persona.Estado.ELIMINADO);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPersona);
		this.entity.merge(pPersona);
		this.entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroPersonaFisica findPersonaFisica(FiltroPersonaFisica filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, PersonaFisica.class);

		AtributoDinamico.addRestriccionesCriterio(locCriterio, PersonaFisica.serialVersionUID, "idPersona", filtro.getListaAtributoDinamico());

		locCriterio.add(Restriccion.IGUAL("cuim", filtro.getCuil())).add(Restriccion.ILIKE("nombre", filtro.getNombre())).add(Restriccion.ILIKE("apellido", filtro.getApellido()))
				.add(Restriccion.IGUAL("fechaNacimiento", filtro.getFechaNacimiento())).add(Restriccion.IGUAL("tipoDocumento", filtro.getTipoDocumento()))
				.add(Restriccion.IGUAL("edad", filtro.getEdad())).add(Restriccion.IGUAL("sexo", filtro.getSexo())).add(Restriccion.IGUAL("telefono", filtro.getTelefono()))
				.add(Restriccion.IGUAL("celular", filtro.getCelular())).add(Restriccion.IGUAL("email", filtro.getEmail())).add(Restriccion.IGUAL("estado", filtro.getEstado()));

		if(filtro.getNumeroDocumento() != null) {
			locCriterio.add(Restriccion.EN("numeroDocumento", Arrays.asList(filtro.getNumeroDocumento().replace(" ", ",").split(","))));
		}
		// locCriterio.add(Orden.ASC("apellido")).add(Orden.ASC("nombre"));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, PersonaFisica.serialVersionUID, "idPersona", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	private TipoDocumento getTipoDocumentoFromString(String pTipoDocumento) {
		for(TipoDocumento cadaTipo : TipoDocumento.values()) {
			if(cadaTipo.toString().equalsIgnoreCase(pTipoDocumento))
				return cadaTipo;
		}
		return null;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void addPersonaJuridica(com.trascender.framework.recurso.persistent.PersonaJuridica pPersonaJuridica) throws java.lang.Exception {

		// if (pPersonaJuridica.getTitular() == null) {
		// throw new TrascenderFrameworkException(23);
		// }
		this.validarPersonaJuridica(pPersonaJuridica);
		pPersonaJuridica.setEstado(Persona.Estado.ACTIVO);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPersonaJuridica);
		this.entity.persist(pPersonaJuridica);
		pPersonaJuridica.postPersist();
		// Flush para que persista los atributos dinamicos.
		this.entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updatePersonaJuridica(com.trascender.framework.recurso.persistent.PersonaJuridica pPersonaJuridica) throws java.lang.Exception {

		validarPersonaJuridica(pPersonaJuridica);
		pPersonaJuridica.toString();

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPersonaJuridica);

		this.entity.merge(pPersonaJuridica);
		this.entity.flush();
	}

	/**
	 * Actualiza una persona
	 * 
	 * @param pPersona
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updatePersona(com.trascender.framework.recurso.persistent.Persona pPersona) throws Exception {
		this.entity.merge(pPersona);
	}

	/**
	 * Business method
	 * 
	 * @throws TrascenderFrameworkException
	 *             Cuando no se pueden recuperar los listados de personas jur�dicas
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroPersonaJuridica findPersonaJuridica(FiltroPersonaJuridica filtro) throws TrascenderFrameworkException {

		Criterio locCriterio = Criterio.getInstance(entity, PersonaJuridica.class);

		AtributoDinamico.addRestriccionesCriterio(locCriterio, PersonaJuridica.serialVersionUID, "idPersona", filtro.getListaAtributoDinamico());

		locCriterio.add(Restriccion.IGUAL("cuim", filtro.getCuit())).add(Restriccion.ILIKE("razonSocial", filtro.getRazonSocial())).add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.ILIKE("nombreFantasia", filtro.getNombreFantasia()));

		if(filtro.getTitular() != null) {
			locCriterio.crearAlias("listaSocios.persona", "cadaPersona").add(Restriccion.IGUAL("cadaPersona", filtro.getTitular()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, PersonaJuridica.serialVersionUID, "idPersona", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	public PersonaFisica getPersonaFisicaPorCuim(String pCuim) throws Exception {

		Criterio locCriterio = Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("cuim", pCuim));

		PersonaFisica locPersona = (PersonaFisica) locCriterio.uniqueResult();
		if(locPersona == null) {
			throw new TrascenderFrameworkException(26);
		}
		return locPersona;
	}

	private void validarPersonaJuridica(PersonaJuridica pPersona) throws TrascenderFrameworkException {
		Criterio locCriterio = Criterio.getInstance(entity, PersonaJuridica.class).add(Restriccion.IGUAL("cuim", pPersona.getCuim()))
				.add(Restriccion.DISTINTO("idPersona", pPersona.getIdPersona())).setProyeccion(Proyeccion.COUNT());

		Long cantidad = (Long) locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderFrameworkException(22);
		}

		for(Socio cadaSocio : pPersona.getListaSocios()) {
			cadaSocio.setPersonaJuridica(pPersona);
		}
	}

	public PersonaJuridica getPersonaJuridicaPorId(long pId) throws Exception {
		PersonaJuridica locPersonaJuridica = (PersonaJuridica) this.getPersonaPorId(pId, PersonaJuridica.class);
		locPersonaJuridica.toString();

		for(Socio cadaSocio : locPersonaJuridica.getListaSocios()) {
			cadaSocio.toString();
		}

		if(locPersonaJuridica != null) {
			locPersonaJuridica.getListaLogsAuditoria().size();
		}

		locPersonaJuridica.getListaAtributosDinamicos().size();
		locPersonaJuridica.getEstado().toString();
		locPersonaJuridica.getDomicilio().toString();
		locPersonaJuridica.getDomicilioPostal().toString();

		return locPersonaJuridica;
	}

	public PersonaJuridica getPersonaJuridicaPorCuit(String pCuil) throws TrascenderFrameworkException {

		Criterio locCriterio = Criterio.getInstance(entity, PersonaJuridica.class).add(Restriccion.IGUAL("cuim", pCuil));

		PersonaJuridica locPersona = (PersonaJuridica) locCriterio.uniqueResult();
		if(locPersona == null) {
			throw new TrascenderFrameworkException(27);
		} else {
			for(Socio cadaSocio : locPersona.getListaSocios()) {
				cadaSocio.toString();
			}
			locPersona.getListaAtributosDinamicos().size();
		}
		return locPersona;
	}

	public List getListadoPersonasFisicas(com.trascender.framework.recurso.persistent.Persona.Estado pEstado) {
		return Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("estado", pEstado)).list();
	}

	public com.trascender.framework.recurso.persistent.PersonaFisica getPersonaFisicaPorId(long pId) throws Exception {
		PersonaFisica locPersona = (PersonaFisica) this.getPersonaPorId(pId, PersonaFisica.class);
		locPersona.getListaAtributosDinamicos().size();
		locPersona.getDomicilio().toString();
		locPersona.getDomicilioPostal().toString();
		if(locPersona != null) {
			locPersona.getListaLogsAuditoria().size();
		}
		return locPersona;
	}

	public Persona getPersonaPorId(Long pIdPersona, Class pTipoPersona) throws Exception {
		Persona locPersona = null;

		locPersona = this.entity.find(pTipoPersona, pIdPersona);
		locPersona.toString();
		locPersona.getDomicilio().toString();
		locPersona.getDomicilioPostal().toString();
		return locPersona;
	}

	public List<AuxIdEntidad> findListaAuxIdPersona(String cadena) {
		Criterio locCriterioFisica = Criterio.getInstance(entity, PersonaFisica.class);
		locCriterioFisica.setModoDebug(true);
		locCriterioFisica.add(Restriccion.ILIKE("cuim||' '||apellido||' '||nombre", cadena));

		locCriterioFisica.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idPersona", "apellido||', '||nombre||' ['||cuim||']'"));

		List<AuxIdEntidad> locListaFisicas = locCriterioFisica.list();

		Criterio locCriterioJuridica = Criterio.getInstance(entity, PersonaJuridica.class);
		locCriterioJuridica.add(Restriccion.ILIKE("razonSocial||' '||cuim", cadena));

		locCriterioJuridica.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idPersona", "razonSocial||' ['||cuim||']'"));

		List<AuxIdEntidad> locListaJuridicas = locCriterioJuridica.list();

		List<AuxIdEntidad> locListaPersonas = new ArrayList<AuxIdEntidad>();
		locListaPersonas.addAll(locListaFisicas);
		locListaPersonas.addAll(locListaJuridicas);

		return locListaPersonas;
	}
}
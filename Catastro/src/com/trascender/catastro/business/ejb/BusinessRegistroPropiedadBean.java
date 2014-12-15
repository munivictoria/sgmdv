
package com.trascender.catastro.business.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.business.interfaces.BusinessRegistroPropiedadLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroTituloPropiedad;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.recurso.rfr.DocHabEspecializadoRfr;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.referencia.ObligacionRfr;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="BusinessRegistroPropiedad" display-name="Name for BusinessRegistroPropiedad" description="Description for BusinessRegistroPropiedad"
 *           jndi-name="ejb/BusinessRegistroPropiedad" type="Stateless" view-type="local"
 */
@Stateless(name = "BusinessRegistroPropiedadLocal")
public class BusinessRegistroPropiedadBean implements BusinessRegistroPropiedadLocal {

	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessRegistroPropiedadBean.serialVersionUID);
		grupo.setNombre(BusinessRegistroPropiedadBean.NAME);

		Recurso locRecTituloPropiedad = new Recurso();
		locRecTituloPropiedad.setIdRecurso(TituloPropiedadParcelario.serialVersionUID);
		locRecTituloPropiedad.setNombre("Titulo Propiedad Parcelario");
		locRecTituloPropiedad.setAtributosConsultables("Fecha inscripción", "fechaInscripcion", Tipo.FECHA, "Parcela", "parcela", "Tomo", "nroTomo", "Folio", "nroFolio");
		locRecTituloPropiedad.setClase(TituloPropiedadParcelario.class);
		grupo.getListaRecursos().add(locRecTituloPropiedad);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141099089568575088L;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	private static final String NAME = "CAT|Adm. de Registro de Propiedades";

	public BusinessRegistroPropiedadBean() {
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
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TituloPropiedadParcelario addTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad) throws Exception {

		this.validarTituloPropiedad(pTituloPropiedad);

		for(RegistroPropietario cadaRegistroPropietario : pTituloPropiedad.getListaRegistrosPropietarios()) {
			cadaRegistroPropietario.setTituloPropiedad(pTituloPropiedad);
			this.actualizarCantidadPropiedades(cadaRegistroPropietario.getPersona(), 1);
		}

		this.entityManager.persist(pTituloPropiedad);

		Parcela locParcela = pTituloPropiedad.getParcela();
		locParcela.setTituloPropiedad(pTituloPropiedad);
		this.entityManager.merge(locParcela);

		return pTituloPropiedad;

	}

	/**
	 * Valida que un titulo de propiedad no tenga valores nulos
	 * 
	 * @param pTituloPropiedad
	 * @throws Exception
	 */
	private void validarTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad) throws Exception {
		// Valido valores nulos
		// if (this.getTituloPropiedad(pTituloPropiedad) != null) {
		// throw new CatastroException(0);
		// }

		if(pTituloPropiedad.getParcela() == null) {
			throw new CatastroException(3);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TituloPropiedad.class).add(Restriccion.IGUAL("parcela", pTituloPropiedad.getParcela()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idTituloPropiedad", pTituloPropiedad.getIdTituloPropiedad()))).setProyeccion(Proyeccion.COUNT());

		if(pTituloPropiedad.getIdTituloPropiedad() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idTituloPropiedad", pTituloPropiedad.getIdTituloPropiedad())));
		}

		if(((Long) locCriterio.uniqueResult()) > 0) {
			throw new CatastroException(5);
		}

	}

	/**
	 * Actualiza la cantidad de propiedades que una persona posee
	 * 
	 * @param pPersona
	 * @param pPropiedad
	 *            cantidad de propiedades que agrega
	 * @throws Exception
	 */
	private void actualizarCantidadPropiedades(Persona pPersona, int pPropiedad) throws Exception {

		pPersona = this.entityManager.find(Persona.class, pPersona.getIdPersona());
		pPersona.setCantidadPropiedades(pPersona.getCantidadPropiedades() + pPropiedad);
	}

	/**
	 * @param pTituloPropiedad
	 *            titulo de propiedad que se desea actualizar
	 * @return el titulo de propidad con los datos actualizados
	 */
	@SuppressWarnings("unchecked")
	private TituloPropiedadParcelario getTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad) {

		List locLista = Criterio.getInstance(this.entityManager, TituloPropiedad.class).add(Restriccion.IGUAL("fechaInscripcion", pTituloPropiedad.getFechaInscripcion()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idTituloPropiedad", pTituloPropiedad.getIdTituloPropiedad()))).setMaxResults(1).list();

		if(locLista.isEmpty())
			return null;
		else
			return (TituloPropiedadParcelario) locLista.get(0);

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public TituloPropiedadParcelario updateTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad) throws Exception {

		this.validarTituloPropiedad(pTituloPropiedad);

		TituloPropiedad locTituloPropiedad = this.getTituloPropiedadPorId(pTituloPropiedad.getIdTituloPropiedad());
		this.entityManager.detach(locTituloPropiedad);
		if(locTituloPropiedad == null) {
			throw new CatastroException(6);
		}

		// Recorro la lista de propietarios del titulo original para ver si se
		// quito alguno
		for(RegistroPropietario cadaRegistroPropietario : locTituloPropiedad.getListaRegistrosPropietarios()) {
			if(!pTituloPropiedad.getListaRegistrosPropietarios().contains(cadaRegistroPropietario)) {
				this.actualizarCantidadPropiedades(cadaRegistroPropietario.getPersona(), -1);
				this.validarObligaciones(pTituloPropiedad, cadaRegistroPropietario.getPersona(), pTituloPropiedad.getListaRegistrosPropietarios().get(0).getPersona());
			}
		}

		// Recorro la lista de propietarios del titulo modificado para ver si se
		// agrego alguno respecto de la vieja
		for(RegistroPropietario cadaRegistroPropietario : pTituloPropiedad.getListaRegistrosPropietarios()) {
			if(!locTituloPropiedad.getListaRegistrosPropietarios().contains(cadaRegistroPropietario)) {
				this.actualizarCantidadPropiedades(cadaRegistroPropietario.getPersona(), 1);
			}
		}

		for(RegistroPropietario cadaRegistroPropietario : pTituloPropiedad.getListaRegistrosPropietarios()) {
			cadaRegistroPropietario.setTituloPropiedad(pTituloPropiedad);
		}

		pTituloPropiedad = this.entityManager.merge(pTituloPropiedad);

		return pTituloPropiedad;

	}

	// Actualiza las obligaciones si se cambia el titular de la parcela
	private void validarObligaciones(TituloPropiedad pTitulo, Persona pPersonaAnterior, Persona pPersonaNueva) {
		List<ObligacionRfr> locListaObligacionesParcela = Criterio.getInstance(entityManager, DocHabEspecializadoRfr.class)
				.add(Restriccion.IGUAL("parcela", ((TituloPropiedadParcelario) pTitulo).getParcela())).setProyeccion(Proyeccion.PROP("obligacion")).list();
		if(!locListaObligacionesParcela.isEmpty()) {
			// ID = 0 significa que es una persona temporal, usada en la
			// migración, y no debería generar actualización de deudas,
			// solo cambiar el titular.
			// if (pPersonaAnterior.getIdPersona() == 0) {
			for(ObligacionRfr cadaObligacion : locListaObligacionesParcela) {
				cadaObligacion.setPersona(pPersonaNueva);
			}
			// }
		}
	}

	public FiltroTituloPropiedad findTituloPropiedad(FiltroTituloPropiedad filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TituloPropiedad.class).add(Restriccion.MAYOR("fechaInscripcion", filtro.getFechaDesde()))
				.add(Restriccion.MENOR("fechaInscripcion", filtro.getFechaHasta())).add(Restriccion.IGUAL("parcela", filtro.getParcela()))
				.add(Restriccion.IGUAL("tipoTransaccionCatastral", filtro.getTipoTransaccionCatastral())).add(Restriccion.IGUAL("nroTomo", filtro.getNrotomo()))
				.add(Restriccion.IGUAL("nroFolio", filtro.getNroFolio())).add(Restriccion.IGUAL("asiento", filtro.getNroAsiento()));

		if(filtro.getPersona() != null) {
			locCriterio.crearAlias("listaRegistrosPropietarios", "cadaPropietario").add(Restriccion.IGUAL("cadaPropietario.persona", filtro.getPersona()));
		}

		filtro.procesarYListar(locCriterio);

		for(TituloPropiedad cadaTituloProp : filtro.getListaResultados()) {
			cadaTituloProp.toString();
			for(RegistroPropietario cadaRegistroProp : cadaTituloProp.getListaRegistrosPropietarios()) {
				cadaRegistroProp.toString();
			}
		}

		return filtro;
	}

	/**
	 * Recupera el primer propietario de una parcela
	 * 
	 * @param pParcela
	 *            parcela a la que se quiere recuperar un propietario
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Long getIdPropietario(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {
		return this.getPropietario(pParcela).getIdPersona();
	}

	/**
	 * Recupera la persona que es propietaria de la parcela
	 * 
	 * @param pParcela
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 * 
	 */
	@SuppressWarnings("unchecked")
	public com.trascender.framework.recurso.persistent.Persona getPropietario(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {

		List locListaPropietarios = pParcela.getTituloPropiedad().getListaRegistrosPropietarios();
		// if ((pParcela != null) && (pParcela.getIdParcela() != -1)) {
		// locListaPropietarios = Criterio
		// .getInstance(this.entityManager, RegistroPropietario.class)
		// .crearAlias("tituloPropiedad.parcela", "locParcela")
		// .add(Restriccion.IGUAL("locParcela", pParcela)).list();
		// }

		if((locListaPropietarios != null) && (!locListaPropietarios.isEmpty())) {
			RegistroPropietario locRegistroPropietario = (RegistroPropietario) locListaPropietarios.get(0);

			return locRegistroPropietario.getPersona();
		}

		return null;

	}

	/**
	 * 
	 * @param pIdTituloPropiedad
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	public TituloPropiedad getTituloPropiedadPorId(Long pIdTituloPropiedad) throws Exception {
		TituloPropiedad locTituloPropiedad = Criterio.getInstance(this.entityManager, TituloPropiedad.class).add(Restriccion.IGUAL("idTituloPropiedad", pIdTituloPropiedad))
				.uniqueResult();

		if(locTituloPropiedad != null) {
			for(RegistroPropietario locRegistroPropietario : locTituloPropiedad.getListaRegistrosPropietarios()) {
				locRegistroPropietario.toString();
				locRegistroPropietario.getPersona().toString();
			}
		}

		return locTituloPropiedad;
	}

}

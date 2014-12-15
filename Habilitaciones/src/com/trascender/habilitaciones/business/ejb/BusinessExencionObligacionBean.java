
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessExencionObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencion;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionNumeroCuota;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

@Stateless(name = "ejb/BusinessExencionObligacionLocal")
public class BusinessExencionObligacionBean implements BusinessExencionObligacionLocal {

	private static final long serialVersionUID = 3738327541670344592L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("SAI|Exenciones");

		int locIndiceGrupo = -1;
		locIndiceGrupo = SecurityMgr.getInstance().getListaGrupos().indexOf(grupo);
		if(locIndiceGrupo != -1) {
			grupo = SecurityMgr.getInstance().getListaGrupos().get(locIndiceGrupo);
			SecurityMgr.getInstance().getListaGrupos().remove(locIndiceGrupo);
		}

		Recurso exencionObl = new Recurso();
		exencionObl.setNombre("Exenciones de Obligaciones");
		exencionObl.setAtributosConsultables("Nombre", "nombre", "Valor", "valor", "Estado", "estado");
		exencionObl.setIdRecurso(ExencionObligacion.serialVersionUID);
		exencionObl.setClase(ExencionObligacion.class);
		grupo.getListaRecursos().add(exencionObl);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	/**
	 * 
	 * @throws CreateException
	 * @ejb.create-method view-type = "local"
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
	 * Agrega una exencion a una obligacion
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public ExencionObligacion addExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception {

		this.validarExencionObligacion(pExencionObligacion);
		this.entityManager.persist(pExencionObligacion);
		this.entityManager.flush();
		this.entityManager.refresh(pExencionObligacion);

		return pExencionObligacion;
	}

	/**
	 * Validar si hay una exencion con el mismo nombre
	 * 
	 * @param pExencionObligacion
	 * @throws Exception
	 */
	private void validarExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, ExencionObligacion.class)
				.add(Restriccion.LIKE("nombre", pExencionObligacion.getNombre(), true, Posicion.EXACTA)).setProyeccion(Proyeccion.COUNT());

		if(pExencionObligacion.getIdExencion() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idExencion", pExencionObligacion.getIdExencion())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(914);
		}

		for(CondicionAplicacionExencion cadaCondicionExencion : pExencionObligacion.getListaCondicionAplicacion()) {
			cadaCondicionExencion.setExencionObligacion(pExencionObligacion);
		}

		for(CondicionAplicacionExencionNumeroCuota cadaCondicionExencion : pExencionObligacion.getListaCondicionAplicacionNumeroCuota()) {
			cadaCondicionExencion.setExencionObligacion(pExencionObligacion);
		}
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public ExencionObligacion updateExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception {

		this.validarExencionObligacion(pExencionObligacion);

		pExencionObligacion = this.entityManager.merge(pExencionObligacion);

		return pExencionObligacion;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public ExencionObligacion getExencionObligacionByID(Long pId) throws Exception {
		ExencionObligacion locExencion = (ExencionObligacion) Criterio.getInstance(this.entityManager, ExencionObligacion.class).add(Restriccion.IGUAL("idExencion", pId))
				.uniqueResult();

		if(locExencion != null) {
			locExencion.toString();

			for(Obligacion cadaObligacion : locExencion.getListaObligacion()) {
				cadaObligacion.toString();
			}

			for(CondicionAplicacionExencion cadaCondicion : locExencion.getListaCondicionAplicacion()) {
				cadaCondicion.getNombre();
			}

			if(locExencion.getDigestoMunicipal() != null) {
				locExencion.getDigestoMunicipal().toString();
			}

			if(!locExencion.getListaFirmas().isEmpty()) {
				locExencion.getListaFirmas().toString();
			}

			locExencion.getListaCondicionAplicacionNumeroCuota().size();
		}
		return locExencion;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception {
		pExencionObligacion.setEstado(Exencion.Estado.TERMINADA);

		this.entityManager.merge(pExencionObligacion);
	}

	@SuppressWarnings("unchecked")
	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroExencionObligacion findListaExencionesObligacion(FiltroExencionObligacion pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ExencionObligacion.class).add(Restriccion.IGUAL("obligacion", pFiltro.getObligacion()))
				.add(Restriccion.IGUAL("obligacion.persona", pFiltro.getPersona()));

		if(pFiltro.getAnio() != null) {
			locCriterio
					.addEntidadCartesiana("CondicionAplicacionExcencionAnio", "cadaAnio")
					.addEntidadCartesiana("CondicionAplicacionExcencionCalendario", "cadaCalendario")
					.addEntidadCartesiana("CondicionAplicacionExcencionPeriodo", "cadaPeriodo")
					.addEntidadCartesiana("CondicionAplicacionExcencionCuota", "cadaCuota")
					.setDistinct(true)
					.add(Restriccion.OR(
							Restriccion.AND(Restriccion.IGUAL("cadaAnio.anio", pFiltro.getAnio()), Restriccion.JPQL("cadaAnio.exencionObligacion.idExencion = e.idExencion")),
							Restriccion.AND(Restriccion.IGUAL("cadaCalendario.calendario.anio", pFiltro.getAnio()),
									Restriccion.JPQL("cadaCalendario.exencionObligacion.idExencion = e.idExencion")),
							Restriccion.AND(Restriccion.IGUAL("cadaPeriodo.periodo.calendario.anio", pFiltro.getAnio()),
									Restriccion.JPQL("cadaPeriodo.exencionObligacion.idExencion = e.idExencion")),
							Restriccion.AND(Restriccion.IGUAL("cadaCuota.cuota.periodo.calendario.anio", pFiltro.getAnio()),
									Restriccion.JPQL("cadaCuota.exencionObligacion.idExencion = e.idExencion"))));
		}

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}
}

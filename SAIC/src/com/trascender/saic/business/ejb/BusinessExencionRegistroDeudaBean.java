
package com.trascender.saic.business.ejb;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.saic.business.interfaces.BusinessExencionRegistroDeudaLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroExencionRegistroDeuda;

/**
 * @ejb.bean name="BusinessExencionRegistroDeuda" display-name="Name for BusinessExencionRegistroDeuda"
 *           description="Description for BusinessExencionRegistroDeuda" jndi-name="ejb/BusinessExencionRegistroDeuda" type="Stateless" view-type="local"
 */

@Stateless(name = "BusinessExencionRegistroDeudaLocal")
public class BusinessExencionRegistroDeudaBean implements BusinessExencionRegistroDeudaLocal {

	private static final long serialVersionUID = 3738327541670344592L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("SAI|Exenciones");

		// primero recupero el grupo armado en habilitaciones, si existe lo modifico agregando
		int locIndiceGrupo = -1;
		locIndiceGrupo = SecurityMgr.getInstance().getListaGrupos().indexOf(grupo);

		if(locIndiceGrupo != -1) {
			grupo = SecurityMgr.getInstance().getListaGrupos().get(locIndiceGrupo);
			SecurityMgr.getInstance().getListaGrupos().remove(locIndiceGrupo);
		}

		Recurso locRecurso = new Recurso();
		locRecurso.setNombre("Exenciones de Registros de Deuda");
		locRecurso.setIdRecurso(ExencionRegistroDeuda.serialVersionUID);
		locRecurso.setClase(ExencionRegistroDeuda.class);
		grupo.getListaRecursos().add(locRecurso);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	// private Session session = GestorPersistenciaSaic.getInstance().getSession();

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
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public ExencionRegistroDeuda addExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception {

		this.validarExencionRegistroDeuda(pExencionRegistroDeuda);

		this.entityManager.merge(pExencionRegistroDeuda);

		return pExencionRegistroDeuda;
	}

	/**
	 * Valida que no aya una excencion con un mimo nombre
	 * 
	 * @param pExencionRegistroDeuda
	 * @throws Exception
	 */
	private void validarExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, ExencionRegistroDeuda.class)
				.add(Restriccion.LIKE("nombre", pExencionRegistroDeuda.getNombre(), true, Posicion.EXACTA)).setProyeccion(Proyeccion.COUNT());

		if(pExencionRegistroDeuda.getIdExencion() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idExencion", pExencionRegistroDeuda.getIdExencion())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new SaicException(805);
		}
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public ExencionRegistroDeuda updateExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception {
		this.validarExencionRegistroDeuda(pExencionRegistroDeuda);
		Set<RegistroExencionRegistroDeuda> listaAEliminar = new HashSet<RegistroExencionRegistroDeuda>();

		for(RegistroExencionRegistroDeuda cadaRegistroExencionRegistroDeuda : pExencionRegistroDeuda.getListaRegistrosExencion()) {
			cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setRegistroExencionRegistroDeuda(cadaRegistroExencionRegistroDeuda);
			// Antes de cambiar su estado guardo el mismo y conservo su valor en estadoAnterior
			// Si es la primera vez que se actualiza la exención cada registro deuda debe tener estado distinto de exento
			// por eso se lo actualiza, pero si se vuelve a actualizar la exención y no hago el control de que el registro deuda
			// tenga estado distinto de EXENTO, voy a tener a partir de esa vez estado anterior y estado iguales a EXENTO
			if(pExencionRegistroDeuda.getEstado().equals(Exencion.Estado.VIGENTE)
					&& !cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.EXENTO)) {
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstadoAnterior(cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getEstado());
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstado(EstadoRegistroDeuda.EXENTO);
			}
			// Si está terminada paso el estado de los registros de deuda a su estado anterior
			else if(pExencionRegistroDeuda.getEstado().equals(Exencion.Estado.TERMINADA)) {
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstado(cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getEstadoAnterior());
			}

			if(cadaRegistroExencionRegistroDeuda.getExencionRegistroDeuda() == null) {
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setRegistroExencionRegistroDeuda(null);
				if(pExencionRegistroDeuda.getEstado().equals(Exencion.Estado.VIGENTE)) {
					cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstado(cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getEstadoAnterior());
				}
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getMonto();
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getMontoExencion();

				cadaRegistroExencionRegistroDeuda.setExencionRegistroDeuda(pExencionRegistroDeuda);
				listaAEliminar.add(cadaRegistroExencionRegistroDeuda);

			}
			if(cadaRegistroExencionRegistroDeuda.getIdRegistroExencionRegistroDeuda() != -1) {
				this.entityManager.merge(cadaRegistroExencionRegistroDeuda.getRegistroDeuda());
			}
		}

		pExencionRegistroDeuda.getListaRegistrosExencion().removeAll(listaAEliminar);
		listaAEliminar.clear();

		this.entityManager.merge(pExencionRegistroDeuda);

		return pExencionRegistroDeuda;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public ExencionRegistroDeuda deleteExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception {
		pExencionRegistroDeuda.setEstado(Estado.TERMINADA);

		for(RegistroExencionRegistroDeuda cadaRegistroExencionRegistroDeuda : pExencionRegistroDeuda.getListaRegistrosExencion()) {
			// Antes de cambiar su estado guardo el mismo y conservo su valor en estadoAnterior
			if(pExencionRegistroDeuda.getEstado().equals(Exencion.Estado.VIGENTE)) {
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstadoAnterior(cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getEstado());
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstado(EstadoRegistroDeuda.EXENTO);
			}

			if(cadaRegistroExencionRegistroDeuda.getExencionRegistroDeuda() == null) {
				cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setRegistroExencionRegistroDeuda(null);
				if(pExencionRegistroDeuda.getEstado().equals(Exencion.Estado.VIGENTE)) {
					cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setEstado(cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getEstadoAnterior());
				}
				pExencionRegistroDeuda.getListaRegistrosExencion().remove(cadaRegistroExencionRegistroDeuda);
			}
			this.entityManager.merge(cadaRegistroExencionRegistroDeuda.getRegistroDeuda());
		}

		this.entityManager.merge(pExencionRegistroDeuda);

		return pExencionRegistroDeuda;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public ExencionRegistroDeuda getExencionRegistroDeudaByID(Long pId) throws Exception {
		ExencionRegistroDeuda locExencion = (ExencionRegistroDeuda) Criterio.getInstance(this.entityManager, ExencionRegistroDeuda.class).add(Restriccion.IGUAL("idExencion", pId))
				.uniqueResult();
		if(locExencion != null) {
			locExencion.toString();
			if(locExencion.getListaRegistrosExencion() != null && !locExencion.getListaRegistrosExencion().isEmpty()) {
				locExencion.getListaRegistrosExencion().toString();
				for(RegistroExencionRegistroDeuda cadaRegistroExencionRegistroDeuda : locExencion.getListaRegistrosExencion()) {
					cadaRegistroExencionRegistroDeuda.toString();
					cadaRegistroExencionRegistroDeuda.getRegistroDeuda().toString();
					cadaRegistroExencionRegistroDeuda.getRegistroDeuda().getStringObligacion();
					cadaRegistroExencionRegistroDeuda.getStringRegistroExencionRegDeuda();
				}
			}

			if(locExencion.getDigestoMunicipal() != null) {
				locExencion.getDigestoMunicipal().toString();
			}

			if(!locExencion.getListaFirmas().isEmpty()) {
				locExencion.getListaFirmas().toString();
			}
		}
		return locExencion;
	}

	@Override
	@SuppressWarnings("unchecked")
	/**
	 * @ejb.interface-method view-type = "local"
	 */
	public List<ExencionRegistroDeuda> findListaExencionesRegistrosDeuda(String pNombre, CuotaLiquidacion pCuota, PeriodoLiquidacion pPeriodo, CalendarioMunicipal pCalendario,
			Estado pEstado, Double pPorcentaje) throws Exception {

		List<ExencionRegistroDeuda> locLista = Criterio.getInstance(this.entityManager, ExencionRegistroDeuda.class)
				.add(Restriccion.LIKE("nombre", pNombre, true, Posicion.AL_PRINCIPIO)).add(Restriccion.IGUAL("cuotaLiquidacion", pCuota))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pPeriodo)).add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pCalendario))
				.add(Restriccion.IGUAL("estado", pEstado)).add(Restriccion.IGUAL("porcentaje", pPorcentaje)).list();

		for(ExencionRegistroDeuda cadaExencionRegistroDeuda : locLista) {
			cadaExencionRegistroDeuda.toString();
		}

		return locLista;
	}

}

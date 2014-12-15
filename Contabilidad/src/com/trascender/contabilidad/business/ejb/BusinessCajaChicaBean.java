
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.contabilidad.business.interfaces.BusinessCajaChicaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "ejb/BusinessCajaChicaLocal")
public class BusinessCajaChicaBean implements BusinessCajaChicaLocal {

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessCajaChicaBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessCajaChicaBean.NOMBRE);

		Recurso cajaChica = new Recurso();
		cajaChica.setIdRecurso(CajaChica.serialVersionUID);
		cajaChica.setNombre("Caja Chica");
		cajaChica.setClase(CajaChica.class);
		grupoRecursos.getListaRecursos().add(cajaChica);

		Recurso conceptoMovimientoCajaChica = new Recurso();
		conceptoMovimientoCajaChica.setIdRecurso(ConceptoMovimientoCajaChica.serialVersionUID);
		conceptoMovimientoCajaChica.setNombre("Conceptos de Movimientos de Caja Chica");
		conceptoMovimientoCajaChica.setClase(ConceptoMovimientoCajaChica.class);
		grupoRecursos.getListaRecursos().add(conceptoMovimientoCajaChica);

		Recurso movimientoCajaChica = new Recurso();
		movimientoCajaChica.setIdRecurso(MovimientoCajaChica.serialVersionUID);
		movimientoCajaChica.setNombre("Movimientos de Caja Chica");
		movimientoCajaChica.setClase(MovimientoCajaChica.class);
		grupoRecursos.getListaRecursos().add(movimientoCajaChica);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5836535987509729373L;
	private static final String NOMBRE = "CAJ|Adm Caja Chica";

	@PersistenceContext
	private EntityManager entity;

	public BusinessCajaChicaBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbCreate() throws CreateException {
	}

	// Caja Chica

	public com.trascender.contabilidad.recurso.persistent.CajaChica addCajaChica(com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) throws java.lang.Exception {
		if((pCajaChica.getNombre() != null) && (!pCajaChica.getNombre().trim().equals("")) && (pCajaChica.getUsuario() != null) && (pCajaChica.getImporteReposicion() > 0)) {
			entity.persist(pCajaChica);
			return pCajaChica;
		} else {
			throw new TrascenderContabilidadException(20);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CajaChica updateCajaChica(com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) throws java.lang.Exception {
		if((pCajaChica.getNombre() != null) && (!pCajaChica.getNombre().trim().equals("")) && (pCajaChica.getUsuario() != null) && (pCajaChica.getImporteReposicion() > 0)) {
			entity.merge(pCajaChica);
			return pCajaChica;
		} else {
			throw new TrascenderContabilidadException(21);
		}
	}

	public void deleteCajaChica(com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) throws java.lang.Exception {
		entity.merge(pCajaChica);
		entity.remove(pCajaChica);
	}

	public com.trascender.contabilidad.recurso.persistent.CajaChica findCajaChicaByID(long pIdCajaChica) throws java.lang.Exception {
		CajaChica locCajaChica = entity.find(CajaChica.class, pIdCajaChica);
		return locCajaChica;
	}

	public java.util.List findListaCajaChica(java.lang.String pNombre) throws java.lang.Exception {
		List<CajaChica> listaCajasChicas = Criterio.getInstance(entity, CajaChica.class).add(Restriccion.ILIKE("nombre", pNombre)).list();
		for(CajaChica cadaCajaChica : listaCajasChicas) {
			cadaCajaChica.getUsuario().toString();
		}
		return listaCajasChicas;
	}

	public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica addConceptoMovimientoCajaChica(
			com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica) throws java.lang.Exception {
		if((pConceptoMovimientoCajaChica.getTipo() != null) && (pConceptoMovimientoCajaChica.getNombre() != null) && (!pConceptoMovimientoCajaChica.getNombre().trim().equals(""))) {
			entity.persist(pConceptoMovimientoCajaChica);
			return pConceptoMovimientoCajaChica;
		} else {
			throw new TrascenderContabilidadException(10);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica updateConceptoMovimientoCajaChica(
			com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica) throws java.lang.Exception {
		if((pConceptoMovimientoCajaChica.getNombre() != null) && (!pConceptoMovimientoCajaChica.getNombre().trim().equals(""))) {
			entity.merge(pConceptoMovimientoCajaChica);
			return pConceptoMovimientoCajaChica;
		} else {
			throw new TrascenderContabilidadException(11);
		}
	}

	public void deleteConceptoMovimientoCajaChica(com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica) throws java.lang.Exception {
		entity.merge(pConceptoMovimientoCajaChica);
		entity.remove(pConceptoMovimientoCajaChica);
	}

	public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica findConceptoMovimientoCajaChicaByID(Long pId) throws java.lang.Exception {
		ConceptoMovimientoCajaChica locConceptoMovimientoCajaChica = entity.find(ConceptoMovimientoCajaChica.class, pId);
		return locConceptoMovimientoCajaChica;
	}

	public java.util.List findListaConceptoMovimientoCajaChica(String pNombre) throws java.lang.Exception {
		return Criterio.getInstance(entity, ConceptoMovimientoCajaChica.class).add(Restriccion.ILIKE("nombre", pNombre)).list();
	}

	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica addMovimientoCajaChica(com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica)
			throws java.lang.Exception {
		if((pMovCajaChica.getCajaChica() != null) && (pMovCajaChica.getConceptoMovimiento() != null) && (pMovCajaChica.getImporte() != 0)) {
			pMovCajaChica.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
			entity.persist(pMovCajaChica);
			return pMovCajaChica;
		}
		throw new TrascenderContabilidadException(1);
	}

	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica updateMovimientoCajaChica(com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica)
			throws java.lang.Exception {
		entity.merge(pMovCajaChica);
		return pMovCajaChica;
	}

	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica findMovimientoCajaChicaByID(long pId) throws java.lang.Exception {
		MovimientoCajaChica locMovCajaChica = entity.find(MovimientoCajaChica.class, pId);
		locMovCajaChica.toString();
		locMovCajaChica.getCajaChica().toString();
		locMovCajaChica.getConceptoMovimiento().toString();
		return locMovCajaChica;
	}

	public void deleteMovimientoCajaChica(com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovimientoCajaChica) throws java.lang.Exception {
		entity.merge(pMovimientoCajaChica);
		entity.remove(pMovimientoCajaChica);
	}

	public java.util.List findListaMovimientoCajaChica(java.util.Date pFechaDesde, java.util.Date pFechaHasta,
			com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica, com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica)
			throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, MovimientoCajaChica.class).add(Restriccion.MAYOR("fechaHora", pFechaDesde)).add(Restriccion.MENOR("fechaHora", pFechaHasta))
				.add(Restriccion.IGUAL("cajaChica", pCajaChica)).add(Restriccion.IGUAL("conceptoMovimiento", pConceptoMovimientoCajaChica));

		List<MovimientoCajaChica> listaMovimientos = locCriterio.list();
		for(MovimientoCajaChica cadaMovimiento : listaMovimientos) {
			cadaMovimiento.toString();
			cadaMovimiento.getCajaChica().toString();
			cadaMovimiento.getConceptoMovimiento().toString();
		}
		return listaMovimientos;
	}

}

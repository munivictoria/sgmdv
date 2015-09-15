
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.contabilidad.business.interfaces.BusinessIngresoVarioLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.filtros.FiltroIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

@Stateless(name = "ejb/BusinessIngresoVarioLocal")
public class BusinessIngresoVarioBean implements BusinessIngresoVarioLocal {
	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessIngresoVarioBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessIngresoVarioBean.NOMBRE);

		Recurso ingresoVario = new Recurso();
		ingresoVario.setIdRecurso(IngresoVario.serialVersionUID);
		ingresoVario.setNombre("Ingresos Varios");
		ingresoVario.setAtributosConsultables("Persona", "persona", "Concepto", "conceptoIngresoVario", "Monto", "valor", Tipo.MONTO, "Fecha Emisión", "fechaEmision", Tipo.FECHA, "Estado",
				"estado");
		ingresoVario.setClase(IngresoVario.class);
		grupoRecursos.getListaRecursos().add(ingresoVario);

		Recurso conceptoIngresoVario = new Recurso();
		conceptoIngresoVario.setIdRecurso(ConceptoIngresoVario.serialVersionUID);
		conceptoIngresoVario.setNombre("Conceptos Ingresos Varios");
		conceptoIngresoVario.setAtributosConsultables("Nombre", "nombre", "Valor por Defecto", "valorPorDefecto");
		conceptoIngresoVario.setClase(ConceptoIngresoVario.class);
		grupoRecursos.getListaRecursos().add(conceptoIngresoVario);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	private static final long serialVersionUID = 5513255615940373091L;
	private static final String NOMBRE = "CAJ|Adm. Ingresos Varios";

	@PersistenceContext
	private EntityManager entity;

	public BusinessIngresoVarioBean() {
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

	// Concepto Ingreso Vario
	public ConceptoIngresoVario addConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws Exception {
		this.validarConceptoIngresoVario(pConceptoIngresoVario);
		entity.persist(pConceptoIngresoVario);
		return pConceptoIngresoVario;
	}

	public ConceptoIngresoVario updateConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws java.lang.Exception {
		this.validarConceptoIngresoVario(pConceptoIngresoVario);
		entity.merge(pConceptoIngresoVario);
		return pConceptoIngresoVario;
	}

	private void validarConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class)
				.add(Restriccion.DISTINTO("idConceptoIngresoVario", pConceptoIngresoVario.getIdConceptoIngresoVario()))
				.add(Restriccion.LIKE("nombre", pConceptoIngresoVario.getNombre(), false)).setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderContabilidadException(516);
		}
		pConceptoIngresoVario.calcularValorPorDefecto();
	}

	public void deleteConceptoIngresoVario(com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario) throws java.lang.Exception {
		this.validarEliminarConceptoIngresoVario(pConceptoIngresoVario);
		entity.remove(entity.merge(pConceptoIngresoVario));
	}

	private void validarEliminarConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws TrascenderContabilidadException {
		long locCantidadDepen = Criterio.getInstance(this.entity, IngresoVario.class).add(Restriccion.IGUAL("conceptoIngresoVario", pConceptoIngresoVario))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(locCantidadDepen > 0) {
			throw new TrascenderContabilidadException(515);
		}

	}

	public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario getConceptoIngresoVarioByID(Long pIdConceptoIngresoVario) throws java.lang.Exception {
		ConceptoIngresoVario locConceptoIngresoVario = entity.find(ConceptoIngresoVario.class, pIdConceptoIngresoVario);
		locConceptoIngresoVario.getListaRelaConceptoIngresoVarioCuenta().size();
		locConceptoIngresoVario.getListaIngresoVario().size();
		locConceptoIngresoVario.getListaRoles().size();
		locConceptoIngresoVario.getListaUsuarios().size();
		return locConceptoIngresoVario;
	}
	
	public List<AuxIdEntidad> findListaAuxIdConceptoIngresoVario(String cadena, Usuario pUsuario) {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class);
		locCriterio.add(Restriccion.ILIKE("nombre", cadena));
		
		if (!pUsuario.getUser().equals("root")) {
			Restriccion[] arregloRestricciones = new Restriccion[pUsuario.getListaRoles().size() + 1];
			int i = 0;
			for(Iterator<Rol> locIterador = pUsuario.getListaRoles().iterator(); locIterador.hasNext();) {
				Rol cadaRol = locIterador.next();
				arregloRestricciones[i] = Restriccion.IGUAL("cadaRol", cadaRol);
				i++;
			}
			arregloRestricciones[i] = Restriccion.IGUAL("cadaUsuario", pUsuario);
	
			locCriterio.add(Restriccion.OR(arregloRestricciones));
			locCriterio.crearAliasLeft("listaRoles", "cadaRol");
			locCriterio.crearAliasLeft("listaUsuarios", "cadaUsuario");
		}
		locCriterio.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idConceptoIngresoVario", "nombre"));
		locCriterio.setModoDebug(true);
		List<AuxIdEntidad> locListaResultado = locCriterio.list();
		return locListaResultado;
	}

	public com.trascender.contabilidad.recurso.persistent.IngresoVario addIngresoVario(com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws java.lang.Exception {
		this.validarImputaciones(pIngresoVario);
		Integer locNumero = Criterio.getInstance(entity, IngresoVario.class).add(Orden.DESC("numero")).setMaxResults(1).setProyeccion(Proyeccion.PROP("numero")).uniqueResult();
		if(locNumero == null) {
			locNumero = new Integer(1);
		}
		pIngresoVario.setNumero(locNumero + 1);
		Obligacion locObligacion = pIngresoVario.getDocGeneradorDeuda().getObligacion();
		locObligacion.setNombre("Ingreso vario");
		locObligacion = entity.merge(locObligacion);
		pIngresoVario.getDocGeneradorDeuda().setObligacion(locObligacion);
		entity.persist(pIngresoVario);
		return pIngresoVario;
	}

	public com.trascender.contabilidad.recurso.persistent.IngresoVario updateIngresoVario(com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws java.lang.Exception {
		this.validarImputaciones(pIngresoVario);
		entity.merge(pIngresoVario);
		return pIngresoVario;
	}

	public com.trascender.contabilidad.recurso.persistent.IngresoVario getIngresoVarioByID(Long pIdIngresoVario) throws java.lang.Exception {
		
		Criterio locCriterio = Criterio.getInstance(entity, IngresoVario.class)
//				.add(Restriccion.IGUAL("idRegistroDeuda", pIdIngresoVario))
				.add(Restriccion.ID(pIdIngresoVario))
				.crearFetchAlias("docGeneradorDeuda.obligacion.persona", "persona")
				.setModoDebug(true);
		IngresoVario locIngresoVario = locCriterio.uniqueResult();
		if(locIngresoVario != null) {
			locIngresoVario.getConceptoIngresoVario().getListaRelaConceptoIngresoVarioCuenta().size();
			locIngresoVario.getFechaEmision();
			locIngresoVario.getValor();
			locIngresoVario.getNombre();
			locIngresoVario.getPersona();
			locIngresoVario.getPersona().toString();
			locIngresoVario.getListaImputacionIngresos().size();
		}
		return locIngresoVario;
	}

	public void deleteIngresoVario(IngresoVario pIngresoVario) throws Exception {
		this.validarEliminarIngresoVario(pIngresoVario);
		pIngresoVario.setEstado(EstadoRegistroDeuda.ANULADA);
		this.entity.merge(pIngresoVario);
	}

	/**
	 * Valida que el IngresoVario no sea nulo <br>
	 * y que no permita borrar uno que no este en estado creado.
	 * 
	 * @param pIngresoVario
	 * @throws Exception
	 */
	private void validarEliminarIngresoVario(IngresoVario pIngresoVario) throws Exception {
		if(pIngresoVario == null) {
			throw new TrascenderContabilidadException(42);
		}

		if(!pIngresoVario.getEstado().equals(EstadoRegistroDeuda.VIGENTE)) {
			throw new TrascenderContabilidadException(41);
		}

	}

	private void validarImputaciones(IngresoVario pIngresoVario) {
		for(ImputacionIngresoVario cadaImputacion : pIngresoVario.getListaImputacionIngresos()) {
			cadaImputacion.setIngresoVario(pIngresoVario);
		}
	}

	public FiltroIngresoVario findListaIngresoVario(FiltroIngresoVario pFiltro) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, IngresoVario.class)
			.add(Restriccion.IGUAL("conceptoIngresoVario", pFiltro.getConceptoIngresoVario()))
			.add(Restriccion.IGUAL("estado", pFiltro.getEstado()))
			.add(Restriccion.MAYOR("fechaEmision", pFiltro.getFechaDesde()))
			.add(Restriccion.MENOR("fechaEmision", pFiltro.getFechaHasta()));

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("persona.idPersona", pFiltro.getListaIdPersonas()));
		}

		pFiltro.procesarYListar(locCriterio);

		for(IngresoVario cadaIngresoVario : pFiltro.getListaResultados()) {
			cadaIngresoVario.getPersona().toString();
		}
		return pFiltro;
	}

	public FiltroConceptoIngresoVario findListaConceptoIngresoVario(FiltroConceptoIngresoVario pFiltro) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));

		pFiltro.procesarYListar(locCriterio);

		for(ConceptoIngresoVario cadaConcepto : pFiltro.getListaResultados()) {
			cadaConcepto.getListaRelaConceptoIngresoVarioCuenta().size();
			cadaConcepto.getListaIngresoVario().size();
			cadaConcepto.getListaRoles().size();
			cadaConcepto.getListaUsuarios().size();
		}

		return pFiltro;
	}

	public List<ConceptoIngresoVario> findListaConceptoIngresoVarioPorUsuario(Usuario pUsuario) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class);

		Restriccion[] arregloRestricciones = new Restriccion[pUsuario.getListaRoles().size() + 1];

		int i = 0;
		for(Iterator<Rol> locIterador = pUsuario.getListaRoles().iterator(); locIterador.hasNext();) {
			Rol cadaRol = locIterador.next();
			arregloRestricciones[i] = Restriccion.IGUAL("cadaRol", cadaRol);
			i++;
		}

		arregloRestricciones[i] = Restriccion.IGUAL("cadaUsuario", pUsuario);

		locCriterio.add(Restriccion.OR(arregloRestricciones));
		locCriterio.crearAliasLeft("listaRoles", "cadaRol");
		locCriterio.crearAliasLeft("listaUsuarios", "cadaUsuario");
		locCriterio.setModoDebug(true);

		List<ConceptoIngresoVario> listaResultado = locCriterio.list();
		for(ConceptoIngresoVario cadaConcepto : listaResultado) {
			cadaConcepto.getListaRelaConceptoIngresoVarioCuenta().size();
			cadaConcepto.getListaIngresoVario().size();
			cadaConcepto.getListaRoles().size();
			cadaConcepto.getListaUsuarios().size();
		}

		return listaResultado;
	}
}


package com.trascender.framework.business.ejb;

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

import com.trascender.framework.business.interfaces.BusinessContratoLocal;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroContrato;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "ejb/BusinessContrato")
public class BusinessContratoBean implements BusinessContratoLocal {
	static{
		Grupo grupoContrato = new Grupo();
		grupoContrato.setId(BusinessContratoBean.serialVersionUID);
		grupoContrato.setNombre(BusinessContratoBean.NOMBRE);
		
		Recurso contrato = new Recurso();
		contrato.setIdRecurso(Contrato.serialVersionUID);
		contrato.setNombre("Contrato");
		contrato.setAtributosConsultables("Codigo", "codigoContrato", "Fecha de inicio", "fechaInicio", Tipo.FECHA);
		contrato.setClase(Contrato.class);
		
		grupoContrato.getListaRecursos().add(contrato);
		SecurityMgr.getInstance().addGrupo(grupoContrato);
	}
	
	private static final long serialVersionUID = -1627438680225679545L;

	public static final String NOMBRE = "FRM|Adm. de Contratos";
	
	@PersistenceContext(name = "Vipians")
	private EntityManager entity;

	public BusinessContratoBean() {
		super();
	}
	

	public void setSessionContext(SessionContext pArg0)
			throws EJBException,
			RemoteException {
		
	}
	
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	public void ejbPassivate() throws EJBException,	RemoteException {
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		
	}
	
	/**
	 * Default create method
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException{
		
	}
	
	/**
	 * Permite agregar un Contrato
	 * @throws Exception
	 * Business method  
	 * @ejb.interface-method  view-type = "local"
	 */
	public void addContrato(Contrato pContrato) throws Exception{
		
		this.validarContrato(pContrato);
		entity.persist(pContrato);
		
	}


	private void validarContrato(Contrato pContrato) throws TrascenderFrameworkException {
		if(pContrato.getPersona() == null){
			throw new TrascenderFrameworkException(711);
		}
		if(pContrato.getFechaInicio() == null){
			throw new TrascenderFrameworkException(700);
		}
		if(pContrato.getFechaFin() == null){
			throw new TrascenderFrameworkException(701);
		}
		
		if(pContrato.getFechaFin().before(pContrato.getFechaInicio())){
			throw new TrascenderFrameworkException(704);
		}
		
		if(pContrato.getPrecioPorCuota() == null || pContrato.getPrecioPorCuota()==0f){
			throw new TrascenderFrameworkException(702);
		}
		if(pContrato.getCantidadCuotas() == null || pContrato.getCantidadCuotas().equals(0f)){
			throw new TrascenderFrameworkException(703);
		}
		
		long locCantidadContratos =  (Long) Criterio.getInstance(entity, Contrato.class)
		.add(Restriccion.IGUAL("codigoContrato", pContrato.getCodigoContrato()))
		.add(Restriccion.DISTINTO("idContrato", pContrato.getIdContrato()))
		.setProyeccion(Proyeccion.COUNT()).uniqueResult();
		
		if(locCantidadContratos>0){
			throw new TrascenderFrameworkException(709);
		}
		
	}
	
	/**
	 * Permite actualizar un Contrato
	 * @param pContrato
	 * Business method
	 * @throws Exception
	 */
	public void updateContrato(Contrato pContrato) throws Exception{

		this.validarContrato(pContrato);
		this.entity.merge(pContrato);
	}
	
	/**
	 * Permite eliminar un contrato
	 * @param pContrato
	 * Business method
	 * @throws Exception
	 */
	public void deleteContrato(Contrato pContrato) throws Exception{
		this.validarContratoAsociaciones(pContrato);
		Contrato locContrato = this.entity.find(Contrato.class, pContrato.getIdContrato());
		
		this.entity.remove(locContrato);
		 
	}
	
	private void validarContratoAsociaciones(Contrato pContrato) throws Exception {
		//Valida que no este asociado a una factura contrato.
		if(((Long)this.entity.createQuery("SELECT COUNT(e) FROM FacturaContrato e WHERE e.contrato = :locContrato")
				.setParameter("locContrato", pContrato)
				.getSingleResult()) > 0){
			throw new TrascenderFrameworkException(712);
		}
		
	}

	/**
	 * Permite buscar un contrato por Id
	 * Business method
	 * @param pId
	 * @return Contrato
	 * @throws Exception
	 */
	public Contrato getContratoPorId(long pId) throws Exception{
		Contrato locContrato = entity.find(Contrato.class, pId);
		if (locContrato != null) {
			locContrato.toString();
			locContrato.getPersona().toString();
		}
		return locContrato;
	}

	/**
	 * Permite recuperar una lista de contratos
	 * @param pPersona
	 * @param pFechaInicioDesde
	 * @param pFechaInicioHasta
	 * @return 
	 * @throws TrascenderFrameworkException
	 */

	public FiltroContrato findListaContratos(FiltroContrato filtro)
			throws Exception {
		if((filtro.getFechaInicioDesde() != null && filtro.getFechaInicioHasta() != null) && (filtro.getFechaFinHasta().before(filtro.getFechaInicioDesde()))){
			throw new TrascenderFrameworkException(704);
		}
		
		Criterio locCriterio = Criterio.getInstance(entity, Contrato.class);
		locCriterio.add(Restriccion.IGUAL("persona", filtro.getPersona()));
		locCriterio.add(Restriccion.MAYOR("fechaInicio", filtro.getFechaInicioDesde()));
		locCriterio.add(Restriccion.MENOR("fechaInicio", filtro.getFechaInicioHasta()));
		locCriterio.add(Restriccion.MAYOR("fechaFin", filtro.getFechaFinDesde()));
		locCriterio.add(Restriccion.MENOR("fechaFin", filtro.getFechaFinHasta()));
		locCriterio.setModoDebug(true);
		//List locListado = locCriterio.list();
		filtro.procesarYListar(locCriterio);
		
		for(Contrato locContrato: filtro.getListaResultados()){
			Persona locPersona = locContrato.getPersona();
			locPersona.toString();
			locContrato.toString();
		}		
		return filtro;

	}
	
	
}

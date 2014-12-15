package com.trascender.accionSocial.business.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.accionSocial.business.interfaces.BusinessFichaSocialLocal;
import com.trascender.accionSocial.exception.TrascenderAccionSocialException;
import com.trascender.accionSocial.recurso.filtros.FiltroFichaSocial;
import com.trascender.accionSocial.recurso.filtros.FiltroObraSocial;
import com.trascender.accionSocial.recurso.persistent.Beneficiario;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;
import com.trascender.accionSocial.recurso.persistent.SolicitudBeneficio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "BusinessFichaSocialLocal")
public class BusinessFichaSocialBean implements BusinessFichaSocialLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	static{
		Grupo grupoFichaSocial = new Grupo();
		grupoFichaSocial.setNombre(BusinessFichaSocialBean.NOMBRE);
		
		Recurso beneficio = new Recurso();
		beneficio.setIdRecurso(Beneficio.serialVersionUID);
		beneficio.setNombre("Beneficio");
		
		Recurso fichaSocial = new Recurso();
		fichaSocial.setIdRecurso(FichaSocial.serialVersionUID);
		fichaSocial.setNombre("Ficha Social");
		
		Recurso obraSocial = new Recurso();
		obraSocial.setIdRecurso(ObraSocial.serialVersionUID);
		obraSocial.setNombre("Obra social");
		obraSocial.setAtributosConsultables("Nombre", "nombre");
		
		grupoFichaSocial.getListaRecursos().add(beneficio);
		grupoFichaSocial.getListaRecursos().add(fichaSocial);
		grupoFichaSocial.getListaRecursos().add(obraSocial);
		SecurityMgr.getInstance().addGrupo(grupoFichaSocial);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5359436794616878070L;
	public static final String NOMBRE = "ACC|Adm. Ficha social";
	
	public void ejbCreate(){
		
	}
	
	/**
	 * 
	 * @param pObraSocial
	 * @return
	 * @throws Exception
	 */
	public ObraSocial addObraSocial(ObraSocial pObraSocial) throws Exception{
		
		this.validarObraSocial(pObraSocial);	
		this.entityManager.persist(pObraSocial);
		
		return pObraSocial;
	}
	
	/**
	 * Valida que no aya una obra social con el mismo nombre
	 * @param pObraSocial
	 * @throws TrascenderAccionSocialException
	 */
	private void validarObraSocial(ObraSocial pObraSocial) throws TrascenderAccionSocialException {
		Long locCantidadObras = (Long) Criterio.getInstance(this.entityManager, ObraSocial.class)
			.add(Restriccion.ILIKE("nombre", pObraSocial.getNombre().trim()))
			.add(Restriccion.DISTINTO("idObraSocial", pObraSocial.getIdObraSocial()))
			.setProyeccion(Proyeccion.COUNT()).uniqueResult();
		
		if(locCantidadObras.longValue() > 0){
			throw new TrascenderAccionSocialException(40);
			
		}
	}

	/**
	 * 
	 * @param pObraSocial
	 * @return
	 * @throws Exception
	 */
	public ObraSocial updateObraSocial(ObraSocial pObraSocial) throws Exception{
		
		this.validarObraSocial(pObraSocial);
		this.entityManager.merge(pObraSocial);
		
		return pObraSocial;
	}
	
	/**
	 * 
	 * @param pObraSocial
	 * @throws Exception
	 */
	public void deleteObraSocial(ObraSocial pObraSocial) throws Exception{
		Criterio locCriterio = Criterio.getInstance(entityManager, Beneficiario.class)
			.add(Restriccion.IGUAL("obraSocial", pObraSocial))
			.setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if (cantidad > 0){
			throw new TrascenderAccionSocialException(41);
		}
		this.entityManager.remove(this.entityManager.merge(pObraSocial));
	}
	
	/**
	 * 
	 * @param pNombre
	 * @return
	 * @throws Exception
	 */
	public FiltroObraSocial findListaObraSocial(FiltroObraSocial pFiltro) throws Exception{
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ObraSocial.class)
			.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));
		
		pFiltro.procesarYListar(locCriterio);
		
		return pFiltro;
	}
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public ObraSocial getObraSocialPorId(long pId) throws Exception{
		ObraSocial locObra	= (ObraSocial) Criterio.getInstance(this.entityManager, ObraSocial.class)
				.add(Restriccion.IGUAL("idObraSocial", pId))
				.uniqueResult();

		locObra.toString();
		return locObra;
	}
	
	/**
	 * 
	 * @param pNombre
	 * @param pTipoBeneficio
	 * @return
	 */
	public List findListaBeneficio(String pNombre, Beneficio.TipoBeneficio pTipoBeneficio){
		List listaResultado = Criterio.getInstance(this.entityManager, Beneficio.class)
			.add(Restriccion.ILIKE("nombre", pNombre))
			.add(Restriccion.IGUAL("tipoBeneficio", pTipoBeneficio))
			.list();

		return listaResultado;
	}
	
	/**
	 * 
	 * @param pBeneficio
	 * @return
	 * @throws Exception
	 */
	public Beneficio addbeneficio(Beneficio pBeneficio) throws Exception{
		this.validarBeneficio(pBeneficio);
			this.entityManager.persist(pBeneficio);
			return pBeneficio;
	}
	
	/**
	 * Valida que no aya un beneficio con el mismo nombre
	 * @param pBeneficio
	 * @throws TrascenderAccionSocialException
	 */
	private void validarBeneficio(Beneficio pBeneficio) throws TrascenderAccionSocialException {
		Long locCantidad = (Long) Criterio.getInstance(this.entityManager, Beneficio.class)
		.add(Restriccion.ILIKE("nombre", pBeneficio.getNombre()))
		.add(Restriccion.DISTINTO("idBeneficio", pBeneficio.getIdBeneficio()))
		.setProyeccion(Proyeccion.COUNT())
		.uniqueResult();
		
		if (locCantidad.longValue() > 0){
			throw new TrascenderAccionSocialException(20);
		}
		
	}

	/**
	 * 
	 * @param pBeneficio
	 * @return
	 * @throws Exception
	 */
	public Beneficio updateBeneficio(Beneficio pBeneficio) throws Exception{
		this.validarBeneficio(pBeneficio);
		this.entityManager.merge(pBeneficio);
		return pBeneficio;
	}
	
	/**
	 * 
	 * @param pBeneficio
	 * @throws Exception
	 */
	public void deleteBeneficio(Beneficio pBeneficio) throws Exception{
		this.validarRelacionBeneficio(pBeneficio);
		
		this.entityManager.remove(this.entityManager.merge(pBeneficio));
	}
	
	/**
	 * Valida si un beneficio esta asociado a alguna solucitud.
	 * @param pBeneficio
	 * @throws Exception
	 */
	private void validarRelacionBeneficio(Beneficio pBeneficio) throws Exception {
			Long locContador = (Long) Criterio.getInstance(this.entityManager, FichaSocial.class)
			.crearAlias("listaSolicitudBeneficio", "cadaSolicitud")
			.add(Restriccion.IGUAL("cadaSolicitud.beneficio", pBeneficio))
			.setProyeccion(Proyeccion.COUNT())
			.uniqueResult();
		
		if(locContador.longValue() > 0){
			throw new TrascenderAccionSocialException(21);
		}
		
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Beneficio getBeneficioPorId(long pId) throws Exception{
		Beneficio locBeneficio = (Beneficio) Criterio.getInstance(this.entityManager, Beneficio.class)
				.add(Restriccion.IGUAL("idBeneficio", pId))
				.uniqueResult();
		locBeneficio.toString();
		locBeneficio.getTipoBeneficio().toString();
		return locBeneficio;
	}
	
	/**
	 * 
	 * @param pFichaSocial
	 * @return
	 * @throws Exception
	 */
	public FichaSocial addFichaSocial(FichaSocial pFichaSocial) throws Exception{
		
		this.validarFichaSocial(pFichaSocial);
		this.entityManager.persist(pFichaSocial);
		
		return pFichaSocial;
	}
	
	/**
	 * 
	 * @param pFichaSocial
	 * @return
	 * @throws Exception
	 */
	public FichaSocial updateFichaSocial(FichaSocial pFichaSocial) throws Exception{
		
		this.validarFichaSocial(pFichaSocial);
		this.entityManager.merge(pFichaSocial);
		
		return pFichaSocial;
	}
	
	/**
	 * @param pFichaSocial
	 * @throws TrascenderAccionSocialException
	 */
	private void validarFichaSocial(FichaSocial pFichaSocial) throws TrascenderAccionSocialException{				
		
		long locCantidad = (Long) Criterio.getInstance(this.entityManager, FichaSocial.class)
			.add(Restriccion.IGUAL("numero", pFichaSocial.getNumero()))
			.add(Restriccion.DISTINTO("idFichaSocial", pFichaSocial.getIdFichaSocial()))
			.setProyeccion(Proyeccion.COUNT())
			.uniqueResult();
		
		if (locCantidad  > 0){
			throw new TrascenderAccionSocialException(1);
		}

		/** valido que el titular ingresado no este en otra ficha social, Ya sea 
		 * como titular o como miembro de un grupo familiar
		 */
		
		if(!this.validarBeneficiario(pFichaSocial.getTitular(), pFichaSocial.getIdFichaSocial())){
			throw new TrascenderAccionSocialException(2);
		}
		
		
		/** validar que cada miembro del grupo familiar no este en otra ficha social, ya sea
		 * como miembro de otro grupo familiar, o como titular
		 */

		for (Beneficiario cadaBeneficiario: pFichaSocial.getGrupoFamiliar()) {
			if(!this.validarBeneficiario(cadaBeneficiario, pFichaSocial.getIdFichaSocial())){
				throw new TrascenderAccionSocialException(3);
			}
			
		}
		
	}
	
	/** valido que el titular ingresado no este en otra ficha social, Ya sea 
	 * como titular o como miembro de un grupo familiar
	 */
	private boolean validarBeneficiario(Beneficiario pBeneficiario, long pIdFichaSocial) {
		Long locCantidad = (Long)Criterio.getInstance(this.entityManager,FichaSocial.class)
		.add(Restriccion.DISTINTO("idFichaSocial", pIdFichaSocial))
		.crearAlias("grupoFamiliar", "cadaFamiliar")
		.crearAlias("titular", "locTitular")
		.add(Restriccion.OR(Restriccion.IGUAL("locTitular.persona", pBeneficiario.getPersona()), 
							Restriccion.IGUAL("cadaFamiliar.persona",pBeneficiario.getPersona())))
		.setProyeccion(Proyeccion.COUNT())
		.uniqueResult();
		
		
		return locCantidad.longValue() < 1;
		
	}

	/**
	 * 
	 * @param pFichaSocial
	 * @throws Exception
	 */
	public void deleteFichaSocial(FichaSocial pFichaSocial) throws Exception{
		this.entityManager.remove(this.entityManager.merge(pFichaSocial));
	}
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public FichaSocial getFichaSocialPorId(long pId) throws Exception{
		FichaSocial locFichaSocial = (FichaSocial) Criterio.getInstance(this.entityManager, FichaSocial.class)
				.add(Restriccion.IGUAL("idFichaSocial", pId))
				.uniqueResult();
			locFichaSocial.getTitular().toString();
			locFichaSocial.getAspectoEconomico().toString();
			locFichaSocial.getAspectoHabitacional().toString();
			for (SolicitudBeneficio cadaSolicitud : locFichaSocial.getListaSolicitudBeneficio()){
				cadaSolicitud.toString();
			}
			for (Beneficiario locBeneficiario : locFichaSocial.getGrupoFamiliar()){
				locBeneficiario.toString();
			}
		return locFichaSocial;
	}
	
	/**
	 * 
	 * @param pNumero
	 * @param pTitular
	 * @param pFamiliar
	 * @param pBeneficio
	 * @return
	 * @throws Exception
	 */
	public FiltroFichaSocial findListaFichaSocial(FiltroFichaSocial pFiltro) throws Exception{
		
		 Criterio locCriterio = Criterio.getInstance(this.entityManager, FichaSocial.class)
				.add(Restriccion.IGUAL("numero", pFiltro.getNumero()))
				.add(Restriccion.IGUAL("titular.persona", pFiltro.getTitular()))
				.add(Restriccion.IGUAL("grupoFamiliar.persona", pFiltro.getFamiliar()))
				.add(Restriccion.IGUAL("listaSolicitudBeneficio.beneficio", pFiltro.getBeneficio()))
				.setModoDebug(true);
		
		
		pFiltro.procesarYListar(locCriterio);
		
		List<FichaSocial> listaResultado = pFiltro.getListaResultados();
		
		for (Object locObj : listaResultado){
			FichaSocial locFicha = (FichaSocial) locObj;
			locFicha.getTitular().toString();
		}
		return pFiltro;
	}
	
}

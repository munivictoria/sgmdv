package com.trascender.framework.recurso.persistent.validacionDinamica;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;

import com.trascender.framework.exception.ValidacionDinamicaException;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.AlcanceValidacion;

/**
 * La finalidad de esta clase es hacer uso funcional de las validaciones dinamicas.<br>
 * No es condicion sinecuan para la utilizacion de los mismos pero posee el carcter de utilidad.
 * @author jsantacruz
 *
 */
public class ValidadorDinamicoCore {

	private ValidacionDinamica validaciones;
	private EntityManager entityManager;
	private String stringClass;
	
	@Transient
	private Class clase;
	
	public static ValidadorDinamicoCore getInstance(EntityManager pEntity, Class pClase) throws Exception {
//		if( (pClase == null || (pEntity == null)){
//			throw new ValidacionDinamicaException(1);
//		} desactivado mientras testeo
		ValidadorDinamicoCore locValidador = new ValidadorDinamicoCore();
		locValidador.setEntityManager(pEntity);
		locValidador.setClase(pClase);
		
		return locValidador;
	}

	
	public void setValidaciones(ValidacionDinamica pValidaciones) {
		validaciones = pValidaciones;
	}


	public void setEntityManager(EntityManager pEntityManager) {
		entityManager = pEntityManager;
	}

	public void setClase(Class pClase) {
		if(pClase != null){
			this.stringClass = pClase.getName();
		}
		clase = pClase;
	}

	public void empezarValidacion(Object pObeto) throws Exception{
		if(this.cargarValidaciones() > 0){
		this.validaciones.setClase(this.clase);
		this.validacionesPrimerNivel(pObeto);
		
		StringBuilder locValidacion;
		
			Query locQuery = null;
			for(ComponenteValidacion cadaComponente : this.validaciones.getListaComponentes()){
				cadaComponente.setObjeto(pObeto);
				locValidacion  = new StringBuilder();
				locValidacion.append("SELECT COUNT(e) FROM "+ this.clase.getName() + " e WHERE ");
				locValidacion.append(cadaComponente.getValidacion());
				
				locQuery = this.entityManager.createQuery(locValidacion.toString());
				System.out.println(locValidacion.toString());
				for(String cadaParametro : cadaComponente.getMapaParametros().keySet()){
					System.out.println(cadaParametro+ " - " +cadaComponente.getMapaParametros().get(cadaParametro));
					locQuery.setParameter(cadaParametro, cadaComponente.getMapaParametros().get(cadaParametro));
				}
				
				if( ((Long)locQuery.getSingleResult()) > 0){
					throw new ValidacionDinamicaException(cadaComponente.getMensajeError());
				}
			}
			
		}
	}

	
	private void validacionesPrimerNivel(Object pObjeto) throws Exception {
		Iterator<ComponenteValidacion> locIteracion = this.validaciones.getListaComponentes().iterator();
		while(locIteracion.hasNext()){
			ComponenteValidacion cadaComponente = locIteracion.next();
			if(cadaComponente.getAlcance().equals(AlcanceValidacion.INTEGRIDAD)){
				System.out.println("VALIDANDO INTEGRIDAD:");
				cadaComponente.setObjeto(pObjeto);
				cadaComponente.validar();
				cadaComponente.setObjeto(null);
			}
			locIteracion.remove();
		}
		
	}


	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private int cargarValidaciones() throws Exception{
		this.validaciones = (ValidacionDinamica) this.entityManager.createQuery("SELECT vd FROM ValidacionDinamica vd " +
				"WHERE vd.idRecurso= :locClassString")
				.setParameter("locClassString", this.clase.getDeclaredField("serialVersionUID").getLong(this.clase.newInstance()))
				.getSingleResult();
		
		if(this.validaciones != null){
			this.validaciones.toString();
			
			for(ComponenteValidacion cadaComponente : this.validaciones.getListaComponentes()){
				cadaComponente.toString();
				for(ComponenteValidacion cadaComponenteHijo : cadaComponente.getListaComponenteValidacion()){
					cadaComponenteHijo.toString();
				}
			}
			
			this.entityManager.detach(this.validaciones);
			return 1;
		}
		
		return 0;
	}
	
	
}

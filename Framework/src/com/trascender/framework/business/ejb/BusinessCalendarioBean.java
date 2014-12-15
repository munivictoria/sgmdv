package com.trascender.framework.business.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.business.interfaces.BusinessCalendarioLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Calendario.EstadoCalendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;

@Stateless(name="ejb/BusinessCalendario")
public class BusinessCalendarioBean implements BusinessCalendarioLocal {

	@PersistenceContext(name="Vipians")
	private EntityManager entityManager;

	public Calendario addCalendario(
			Calendario pCalendario)
					throws Exception {
		this.validarCalendario(pCalendario);

		pCalendario.setEstado(EstadoCalendario.INACTIVO);

		this.entityManager.merge(pCalendario);
		this.entityManager.flush();

		return pCalendario;
	}

	public Calendario updateCalendario(
			Calendario pCalendario) throws Exception {



		return this.entityManager.merge(pCalendario);
	}

	public Collection<Calendario> findListaCalendarios(
			Long pIdTipoObligacion, 
			Integer pAnio,
			EstadoCalendario pEstado) throws Exception {
		List<Calendario> locListaResultado = new ArrayList<Calendario>();

		Criterio locCriterio = Criterio
				.getInstance(this.entityManager, Calendario.class)
				.add(Restriccion.IGUAL("anio", pAnio))
				.add(Restriccion.IGUAL("estado", pEstado));

		if(pIdTipoObligacion != null){
			Long locIdCalendario = (Long) this.entityManager.createQuery("SELECT cadaCalendario.idCalendario FROM TipoObligacion e " +
					"LEFT JOIN e.listaCalendarioMunicipal cadaCalendario WHERE e.idTipoObligacion = :locId")
					.setParameter("locId", pIdTipoObligacion)
					.getSingleResult();

			locCriterio.add(Restriccion.IGUAL("idCalendario", locIdCalendario));
		}

		locListaResultado = locCriterio.list();

		for(Calendario cadaCalendario : locListaResultado){
			cadaCalendario.toString();
			for(Periodo cadaPeriodo : cadaCalendario.getListaPeriodos()){
				cadaPeriodo.toString();
			}
		}

		return locListaResultado;
	}

	/**
	 * Valido que no haya un calendario asociado para el mismo anio. Y que la
	 * lista de periodos no este vacia.
	 * 
	 * @param pCalendario
	 * @param pTipoObligacion
	 * @throws Exception
	 */
	private void validarCalendario(Calendario pCalendario) throws Exception {
		if (((Long) Criterio.getInstance(this.entityManager, Calendario.class)
				.setProyeccion(Proyeccion.COUNT())
				.add(Restriccion.IGUAL("anio", pCalendario.getAnio()))
				.uniqueResult()) > 0) {
			throw new TrascenderFrameworkException(250);
		}

		if (pCalendario.getListaPeriodos().isEmpty()) {
			throw new TrascenderFrameworkException(251);
		}

		for (Periodo cadaPeriodo : pCalendario.getListaPeriodos()) {
			if (cadaPeriodo.getCalendario() == null) {
				cadaPeriodo.setCalendario(pCalendario);
			}
		}
	}

	/**
	 * Recupera un período según los 2 parámetros
	 * 
	 * @param pPeriodicidad periodicidad a la que corresponde el período
	 * @param pNumeroPeriodo número de período
	 * @param pAño año al que corresponde el período
	 */
	public Periodo getPeriodo(Calendar pFecha,
			Integer pNumeroPeriodo,
			Integer pAño,
			Periodicidad pPeriodicidad,
			Long pIdTipoObligacion)
					throws TrascenderException {


		Criterio locCriterioPeriodo = Criterio.getInstance(this.entityManager, Periodo.class)
				.add(Restriccion.IGUAL("calendario.anio", pAño))
				.add(Restriccion.IGUAL("calendario.estado", EstadoCalendario.ACTIVO))
				.add(Restriccion.IGUAL("calendario.periodicidad", pPeriodicidad ))
				.add(Restriccion.MAYOR("fechaInicio", pFecha))
				.add(Restriccion.MENOR("fechaFin", pFecha))
				.add(Restriccion.IGUAL("numeroPeriodo", pNumeroPeriodo))
				.setModoDebug(true);

		//		Criterio locCriterio = Criterio.getInstance(this.entityManager, Calendario.class)
		//				.add(Restriccion.IGUAL("anio", pAño))
		//				.add(Restriccion.IGUAL("estado", EstadoCalendario.ACTIVO))
		//				.setModoDebug(true);
		//		
		if(pIdTipoObligacion != null){
			Long locIdCalendario = (Long) this.entityManager.createQuery("SELECT cadaCalendario.idCalendario FROM TipoObligacion e " +
					"LEFT JOIN e.listaCalendarioMunicipal cadaCalendario WHERE e.idTipoObligacion = :locId")
					.setParameter("locId", pIdTipoObligacion)
					.getSingleResult();

			System.out.println("ID CALENDARIO DEL TIPO OBLIGACION: " + locIdCalendario);
			locCriterioPeriodo.add(Restriccion.IGUAL("calendario.idCalendario", locIdCalendario));
		}
		//		
		//		Calendario locCalendario = locCriterio.uniqueResult();
		//		
		//		System.out.println("CALENDARIO ENCOTNRADO: "+ locCalendario);
		//		if(locCalendario != null){
		//			return locCalendario.findPeriodo(pFecha, pNumeroPeriodo);
		//		}else{
		//			return null;
		//		}

		return locCriterioPeriodo.uniqueResult();


	}

	/**
	 * Recupera el siguiente período según los 3 parámetros
	 * 
	 * @param pPeriodicidad periodicidad a la que corresponde el período
	 * @param pNumeroPeriodo número de período actual
	 * @param pAño año al que corresponde el período
	 */
	public Periodo getSiguientePeriodo(
			Periodicidad pPeriodicidad,
			Integer pNumeroPeriodo, 
			Integer pAño,
			Long pIdTipoObligacion) throws Exception {

		Long locIdCalendario = (Long) this.entityManager.createQuery("SELECT e.idCalendario FROM TipoObligacion e " +
				"WHERE e.idTipoObligacion = :locId")
				.setParameter("locId", pIdTipoObligacion)
				.getSingleResult();

		Periodo locPeriodo = Criterio.getInstance(this.entityManager, Periodo.class)
				.add(Restriccion.IGUAL("calendario.anio", pAño))
				.add(Restriccion.IGUAL("calendario.periodicidad", pPeriodicidad))
				.add(Restriccion.IGUAL("calendario.idCalendario", locIdCalendario))
				.add(Restriccion.IGUAL("numeroPeriodo", (pNumeroPeriodo+1)))
				.uniqueResult();

		return locPeriodo; 
	}

}

package com.trascender.habilitaciones.business.ejb;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessPeriodoLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.recurso.transients.FiltroCuotaLiquidacion;

@Stateless(name="ejb/BusinessPeriodo")
public class BusinessPeriodoBean implements BusinessPeriodoLocal {

	@PersistenceContext(name="vipians")
	private EntityManager entityManager;


	public FiltroCalendarioMunicipal findListaCalendariosMunicipales(FiltroCalendarioMunicipal pFiltro) 
			throws Exception {

		Criterio locCriterio = Criterio
				.getInstance(this.entityManager, CalendarioMunicipal.class)
				.add(Restriccion.IGUAL("anio", pFiltro.getAnio()))
				.add(Restriccion.IGUAL("periodicidad", pFiltro.getPeriodicidad()))
				.add(Restriccion.IGUAL("plan.tipoObligacion", pFiltro.getTipoObligacion()))
				.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("plan", pFiltro.getPlan())) ;

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, CalendarioMunicipal.serialVersionUID, "idCalendario", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(CalendarioMunicipal cadaCalendario : pFiltro.getListaResultados()){
			cadaCalendario.toString();
			for(Periodo cadaPeriodo : cadaCalendario.getListaPeriodos()){
				cadaPeriodo.toString();
				((PeriodoLiquidacion)cadaPeriodo).getListaCuotas().size();
			}
		}

		return pFiltro;
	}

	//	/**
	//	 * Recupera un período según los 2 parámetros
	//	 * 
	//	 * @param pPeriodicidad periodicidad a la que corresponde el período
	//	 * @param pNumeroPeriodo número de período
	//	 * @param pAño año al que corresponde el período
	//	 */
	//	public Periodo getPeriodo(Calendar pFecha,
	//				Integer pNumeroPeriodo,
	//				Integer pAño,
	//				Periodicidad pPeriodicidad,
	//				TipoObligacion pTipoObligacion)
	//			throws TrascenderException {
	//		
	//		//Por ahora y mientras todo lo de Periodo no este normalizado
	//		//el TipoObligacion va a ser requerido para localizar un periodo
	//		if(pTipoObligacion == null){
	//			throw new HabilitacionesException(250);
	//		}
	//		
	//		Criterio locCriterioPeriodo = Criterio.getInstance(this.entityManager, Periodo.class)
	//			.add(Restriccion.IGUAL("calendario.anio", pAño))
	//			.add(Restriccion.IGUAL("calendario.estado", EstadoCalendario.ACTIVO))
	//			.add(Restriccion.IGUAL("calendario.periodicidad", pPeriodicidad ))
	//			.add(Restriccion.MAYOR("fechaInicio", pFecha))
	//			.add(Restriccion.MENOR("fechaFin", pFecha))
	//			.add(Restriccion.IGUAL("numero", pNumeroPeriodo))
	//			.setModoDebug(true);
	//
	//		if(pTipoObligacion != null){
	//			
	//			List<Long> locListaIdes = Criterio.getInstance(this.entityManager, TipoObligacion.class)
	//				.crearAlias("listaCalendarioMunicipal", "cadaCalendario")
	////				.setNombreRaiz("e")	
	//				.add(Restriccion.IGUAL("idTipoObligacion", pTipoObligacion.getIdTipoObligacion()))
	//				.setProyeccion(Proyeccion.PROP("cadaCalendario.idCalendario"))
	//				.setModoDebug(true)
	//				.list();
	//			
	//			System.out.println("LISTA IDES RECUPERADOS:> " + locListaIdes);
	//			
	//			//tengo q poner esta restriccion ya que si el Tipo Obligacion
	//			//no tiene asociado ningun calendario la lista viene nula.
	//			if(locListaIdes != null && !locListaIdes.isEmpty()){
	//				locCriterioPeriodo.add(Restriccion.EN("calendario.idCalendario", locListaIdes));
	//			}
	//		}
	//		
	//		return locCriterioPeriodo.uniqueResult();
	//	}

	/**
	 * @param pPeriodicidad periodicidad a la que corresponde el período
	 * @param pNumeroPeriodo número de período actual el metodo se va a encargar de encontrar el numero siguiente.
	 * @param pAño año al que corresponde el período
	 * @return null si no existe.
	 */
	public CuotaLiquidacion getCuotaAnterior(CuotaLiquidacion pCuota) throws Exception {
		CuotaLiquidacion locCuotaRetorno;
		int locNumeroCuota = pCuota.getNumero(); 
		int locNumeroPeriodo = pCuota.getPeriodo().getNumero();

		if(locNumeroCuota == 1 && locNumeroPeriodo <= 1){
			return null;
		}

		if(locNumeroCuota == 1){
			locNumeroPeriodo--;
			for(CuotaLiquidacion cadaCuota : ((PeriodoLiquidacion)pCuota.getPeriodo().getCalendario()
					.findPeriodo(null, locNumeroPeriodo)).getListaCuotas()){
				if(cadaCuota.getNumero().intValue() > locNumeroCuota){
					locNumeroCuota = cadaCuota.getNumero().intValue();
				}
			}
		}


		locCuotaRetorno = ((PeriodoLiquidacion)pCuota.getPeriodo().getCalendario().findPeriodo(null, locNumeroPeriodo))
				.getCuotaLiquidacion(locNumeroCuota);
		return locCuotaRetorno;
	}

	/**
	 * @param pPeriodicidad periodicidad a la que corresponde el período
	 * @param pNumeroPeriodo número de período actual el metodo se va a encargar de encontrar el numero siguiente.
	 * @param pAño año al que corresponde el período
	 * @return null si no existe.
	 */
	public CuotaLiquidacion getCuotaSiguiente(CuotaLiquidacion pCuota) throws Exception {
		CuotaLiquidacion locCuotaRetorno;
		int locNumeroCuota = pCuota.getNumero(); 
		int locNumeroPeriodo = pCuota.getPeriodo().getNumero();

		if(locNumeroCuota == 1 && locNumeroPeriodo == 1){
			return null;
		}

		if(locNumeroCuota == 1){
			locNumeroPeriodo--;
			for(CuotaLiquidacion cadaCuota : ((PeriodoLiquidacion)pCuota.getPeriodo().getCalendario()
					.findPeriodo(null, locNumeroPeriodo)).getListaCuotas()){
				if(cadaCuota.getNumero().intValue() > locNumeroCuota){
					locNumeroCuota = cadaCuota.getNumero().intValue();
				}
			}
		}


		locCuotaRetorno = ((PeriodoLiquidacion)pCuota.getPeriodo().getCalendario().findPeriodo(null, locNumeroPeriodo)).getCuotaLiquidacion(locNumeroCuota);
		return locCuotaRetorno;
	}

	public CalendarioMunicipal addCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception {
		this.validarCalendario(pCalendario);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pCalendario);
		//		pCalendario.getTipoObligacion().addCalendarioMunicipal(pCalendario);
		pCalendario = this.entityManager.merge(pCalendario);
		this.actualizarTipoObligaicon(pCalendario);

		this.entityManager.flush();

		return pCalendario;
	}

	/**
	 * Es necesario actualizar el T.O. aparte para que se cascadeen correctamente los cambios. 
	 * @param pCalendario
	 */
	private void actualizarTipoObligaicon(CalendarioMunicipal pCalendario) {
		//		TipoObligacion locTipoObligacion = Criterio.getInstance(this.entityManager, TipoObligacion.class)
		//				.add(Restriccion.IGUAL("idTipoObligacion", pCalendario.getTipoObligacion().getIdTipoObligacion()))
		//				.uniqueResult();
		//		locTipoObligacion.getListaCalendarioMunicipal().toString();

		//		if(pCalendario.getIdCalendario() != -1l){
		//		//Verifico si se cambio tipoObligacion
		//		Long locIdTipoObAnterior =(Long)Criterio.getInstance(this.entityManager, Calendario.class)
		//				.add(Restriccion.IGUAL("idCalendario", pCalendario.getIdCalendario()))
		//				.setProyeccion(Proyeccion.PROP("tipoObligacion.idTipoObligacion"))
		//				.uniqueResult();
		//				
		//		if(locIdTipoObAnterior != null && !locIdTipoObAnterior.equals(locTipoObligacion.getIdTipoObligacion())){
		//			//Si se cambio el tipo obligacion tengo q cambiar la asociacion del vieljo al nuevo
		//			//Elimino la asociacion a pata para que sea mas rapido 
		//			//y dejo que la cascada se encargue de persistir la nueva relacion
		//			int locCantFilasAfectadas = this.entityManager.createNativeQuery("DELETE FROM RELA_TIPO_OB_CALENDARIO rela WHERE " +
		//					"rela.id_tipo_obligacion = :locIdTO AND rela.id_calendario = :locIdCalendario")
		//					.setParameter("locIdTO", locIdTipoObAnterior)
		//					.setParameter("locidCalendario", pCalendario.getIdCalendario())
		//					.executeUpdate();
		//			
		//			System.out.println("SE AFECTARON " + locCantFilasAfectadas + " FILAS AL ELIMINAR LA ASOCIACION.");
		//		}// else no tengo q hacer nada por que la asociacion al calendario ya estaria hecha
		//		}
		//		locTipoObligacion.addCalendarioMunicipal(pCalendario);
		//		this.entityManager.merge(locTipoObligacion);
	}

	public CalendarioMunicipal updateCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception{
		this.validarCalendario(pCalendario);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pCalendario);

		//		for(Periodo cadaPeriodo : pCalendario.getListaPeriodos()){
		//			PeriodoLiquidacion cadaPeriodoLiq = (PeriodoLiquidacion) cadaPeriodo;
		//			for(CuotaLiquidacion cadaCuota : cadaPeriodoLiq.getListaCuotas()){
		//				CuotaLiquidacion cadaCuotaAnterior = entityManager.find(CuotaLiquidacion.class, cadaCuota.getIdCuotaLiquidacion());

		//				Iterator<Calendar> iteratorVencAnteriores = cadaCuotaAnterior.getListaVencimientos().iterator();
		//				for (Iterator<Calendar> iteratorVencActuales = cadaCuota.getListaVencimientos().iterator(); iteratorVencActuales.hasNext();) {
		//					Calendar cadaVencActual = iteratorVencActuales.next();
		//TODO
		///Hacer esto con un procedure en la base.
		//					if(iteratorVencActuales.hasNext()){
		//						Calendar cadaVencAnterior = iteratorVencAnteriores.next();
		//						if(cadaVencActual.compareTo(cadaVencAnterior) != 0){
		//							Criterio locCriterio = Criterio.getInstance(entityManager, LiquidacionTasa.class)
		//									.crearAlias("listaVencimientos", "cadaVencimiento")
		//									.add(Restriccion.IGUAL("cadaVencimiento.fecha", cadaVencActual))
		//									.setProyeccion(Proyeccion.PROP("cadaVencimiento.idVencimiento"));
		//						}
		//					} else {
		//						break;
		//					}
		//			}
		//		}
		//		}

		pCalendario = this.entityManager.merge(pCalendario);

		entityManager.flush();

		return pCalendario;
	}

	/**
	 * Metodo especifico para validar asociaciones de periodos antes de un update/delete.
	 * @param pCalendario
	 * @throws Exception
	 */
	private void isCalendarioModificable(CalendarioMunicipal pCalendario) throws Exception{
		//			//Verifico si alguno de los periodos del calendario a modificar no esta asociado a ninguna liquidacion. 
		//			//PD: Chanchada nuevamente por tema de visibilidad.
		//			if( ((Long)this.entityManager.createQuery("SELECT COUNT(e) FROM LiquidacionTasa e " +
		//					"WHERE e.periodo.calendario.idCalendario = :locId")
		//					.setParameter("locId", pCalendario.getIdCalendario())
		//					.getSingleResult()) > 0){
		//				throw new HabilitacionesException(255);
		//			}
		//			
		//			Long cantidad = Criterio.getInstance(entityManager, LiquidacionTasa.class)
		//					.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pCalendario))
		//					.setProyeccion(Proyeccion.COUNT())
		//					.uniqueResult();
		//			
		//			//Verifico que no aya ningun periodo asociado a una exencion vigente o terminada.
		//			if( ((Long)this.entityManager.createQuery("SELECT COUNT(e) FROM Exencion e " +
		//					"WHERE e.periodo.calendario.idCalendario = :locId " +
		//					"AND (e.estado IS NOT NULL AND e.estado <> 'CREADA')")
		//					.setParameter("locId", pCalendario.getIdCalendario())
		//					.getSingleResult()) > 0){
		//				throw new HabilitacionesException(255);
		//			}

	}

	/**
	 * Valido que no haya un calendario asociado para el mismo anio. Y que la
	 * lista de periodos no este vacia.
	 * 
	 * @param pCalendario
	 * @param pTipoObligacion
	 * @throws Exception
	 */
	private void validarCalendario(CalendarioMunicipal pCalendario) throws Exception{
		if(pCalendario == null){
			throw new HabilitacionesException(251);
		}

		if(pCalendario.getIdCalendario() > 0){
			this.isCalendarioModificable(pCalendario);
		}

		if(pCalendario.getTipoObligacion() == null){
			throw new HabilitacionesException(252);
		}

		if (((Long) Criterio.getInstance(this.entityManager, Calendario.class)
				.setProyeccion(Proyeccion.COUNT())
				.add(Restriccion.IGUAL("anio", pCalendario.getAnio()))
				.add(Restriccion.IGUAL("plan", pCalendario.getPlan()))
				.add(Restriccion.LIKE("nombre", pCalendario.getNombre(),false))
				.add(Restriccion.DISTINTO("idCalendario", pCalendario.getIdCalendario()))
				.uniqueResult()) > 0) 
		{
			throw new HabilitacionesException(253);
		}

		if (pCalendario.getListaPeriodos().isEmpty()) {
			throw new HabilitacionesException(254);
		}

		for (Periodo cadaPeriodo : pCalendario.getListaPeriodos()) {
			if (cadaPeriodo.getCalendario() == null) {
				cadaPeriodo.setCalendario(pCalendario);
			}
		}
	}

	public CalendarioMunicipal getCalendarioMunicipalById(Long pId) throws Exception {
		CalendarioMunicipal locCalendarioMunicipal = Criterio.getInstance(this.entityManager, CalendarioMunicipal.class)
				.add(Restriccion.IGUAL("idCalendario", pId))
				.uniqueResult();

		if(locCalendarioMunicipal != null){
			locCalendarioMunicipal.getListaLogsAuditoria().size();
			for(Periodo cadaPeriodo : locCalendarioMunicipal.getListaPeriodos()){
				cadaPeriodo.toString();
				PeriodoLiquidacion cadaPeriodoLiquidacion = (PeriodoLiquidacion) cadaPeriodo;
				for(CuotaLiquidacion cadaCuota : cadaPeriodoLiquidacion.getListaCuotas()){
					cadaCuota.toString();
					cadaCuota.getListaVencimientos().size();
				}
			}
		}

		return locCalendarioMunicipal;
	}

	public FiltroCuotaLiquidacion findListaCuotas(FiltroCuotaLiquidacion pFiltro){
		Criterio locCriterio = Criterio.getInstance(this.entityManager, CuotaLiquidacion.class)
				.add(Restriccion.IGUAL("numero", pFiltro.getNumeroCuota()))
				.add(Restriccion.IGUAL("periodo.numero", pFiltro.getNumeroPeriodo()))
				.add(Restriccion.IGUAL("periodo", pFiltro.getPeriodo()))
				.add(Restriccion.IGUAL("periodo.calendario", pFiltro.getCalendario()))
				.add(Restriccion.MAYOR("fechaInicio", pFiltro.getFechaInicio()))
				.add(Restriccion.MENOR("fechaFin", pFiltro.getFechaFin()))
				.add(Restriccion.MIEMBRO_DE("listaVencimientos", pFiltro.getFechaVencimiento()));

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public CuotaLiquidacion getCuotaPorId(Long pId) throws TrascenderException{
		CuotaLiquidacion locCuota = Criterio.getInstance(this.entityManager, CuotaLiquidacion.class)
				.add(Restriccion.IGUAL("idCuotaLiquidacion", pId))
				.uniqueResult();

		if(locCuota != null){
			locCuota.getListaVencimientos().size();
			for(Calendar cadaCalendar : locCuota.getListaVencimientos()){
				cadaCalendar.toString();
			}
		}

		return locCuota;
	}

}

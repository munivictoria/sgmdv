package com.trascender.saic.business.ejb;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.business.interfaces.BusinessCalendarioLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado.Estado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.refinanciacion.DocumentoRef;
import com.trascender.saic.business.interfaces.BusinessAuditoriaTributariaLocal;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.business.interfaces.BusinessRefinanciacionLocal;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroPlantillaPlanDePago;
import com.trascender.saic.recurso.filtros.FiltroRefinanciacion;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda.TipoDocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago.TipoCalculoInteres;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;


/**
 * @ejb.bean name="BusinessRefinanciacion"
 *           display-name="Name for BusinessRefinanciacion"
 *           description="Description for BusinessRefinanciacion"
 *           jndi-name="ejb/BusinessRefinanciacion"
 *           type="Stateless"
 *           view-type="local"
 */
@Stateless(name="BusinessRefinanciacionLocal")
public class BusinessRefinanciacionBean implements BusinessRefinanciacionLocal{
	
	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuado;
	@EJB
	private BusinessLiquidacionTasaLocal businessLiquidacionTasa;
	@EJB
	private BusinessReLiquidacionLocal businessReLiquidacionTasa;
	@EJB
	private BusinessAuditoriaTributariaLocal businessAuditoria;
	@EJB
	private BusinessObligacionLocal businessObligacion;
	@EJB
	private BusinessCalendarioLocal businessCalendario;
	
	@PersistenceContext(name="Vipians")
	private EntityManager entityManager;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6066365215721537177L;
	
	
	
	/**
	 * 
	 * @param pDocumentoRefinanciacion
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.Set<CuotaRefinanciacion> calcularCuotasRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception{
		if (pDocumentoRefinanciacion.getCantidadCuotas()==null){
			throw new SaicException(362);
		}
		
		if (pDocumentoRefinanciacion.getTasaNominalAnual()==null){
			throw new SaicException(363);
		}
		
		if ((pDocumentoRefinanciacion.getMesInicioRefinanciacion()==null)||(pDocumentoRefinanciacion.getAnioInicioRefinanciacion()==null)){
			throw new SaicException(364);
		}
		
		if (pDocumentoRefinanciacion.getAnioInicioRefinanciacion()==null){
			throw new SaicException(365);
		}
		//Nacho: calculo de nuevo el capital con la suma del recargo y la multa, descontando la entrega
		Double capital = this.calcularValorReal(pDocumentoRefinanciacion);
		
		//Fernando: Lo comento porque los montos nunca dan iguales, faltan sumar ciertos intereses y desbalancea todo.
//		if (capital < pDocumentoRefinanciacion.getCantidadCuotas() * pDocumentoRefinanciacion.getMontoMinimoPorCuota()){
//			throw new SaicException(366);
//		}
		
		Set<CuotaRefinanciacion> locListaCuotasRefinanciacion = new HashSet<CuotaRefinanciacion>();
		Double cuotaPura = this.calcularCuotaPura(pDocumentoRefinanciacion.getTasaNominalAnual(), capital, pDocumentoRefinanciacion.getCuotasPorAnio(), pDocumentoRefinanciacion.getCantidadCuotas(), pDocumentoRefinanciacion.getPlantilla().getTipoCalculoInteres());
		
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.set(pDocumentoRefinanciacion.getAnioInicioRefinanciacion(), pDocumentoRefinanciacion.getMesInicioRefinanciacion()-1, pDocumentoRefinanciacion.getDiaVencimiento());

		Date locFechaActual = SecurityMgr.getInstance().getFechaActual().getTime();

		for (int i=1; i<=pDocumentoRefinanciacion.getCantidadCuotas();i++){
			CuotaRefinanciacion locCuotaRefinanciacion = new CuotaRefinanciacion();
			locCuotaRefinanciacion.setFechaEmision(locFechaActual);
			locCuotaRefinanciacion.setNumeroCuota(i);
			locCuotaRefinanciacion.setDocGeneradorDeuda(pDocumentoRefinanciacion);
			locCuotaRefinanciacion.setValorCuota(cuotaPura);
			locCuotaRefinanciacion.setInteres(this.calcularInteresCuota(pDocumentoRefinanciacion.getTasaNominalAnual(), pDocumentoRefinanciacion.getCapital(), pDocumentoRefinanciacion.getCuotasPorAnio(), pDocumentoRefinanciacion.getCantidadCuotas(), i, pDocumentoRefinanciacion.getPlantilla().getTipoCalculoInteres()));
			locCuotaRefinanciacion.setSaldoCapital(this.calcularSaldoCapitalCuota(pDocumentoRefinanciacion.getTasaNominalAnual(), pDocumentoRefinanciacion.getCapital(), pDocumentoRefinanciacion.getCuotasPorAnio(), pDocumentoRefinanciacion.getCantidadCuotas(), i));
			locCuotaRefinanciacion.setTipoDeuda(TipoDeuda.REFINANCIACION);
			
			// Si es la primer cuota, su vencimiento es el dia de hoy...
			Date fecha = new Date();
			if(i > 1) {
				fecha = locCalendar.getTime();
				fecha = Util.llevarFechaALunes(fecha);
				locCalendar.add(Calendar.MONTH, 1);
			}
			locCuotaRefinanciacion.setFechaVencimiento(fecha);
			
			locCuotaRefinanciacion.setNumeroRegistroDeuda(i);
			
			locListaCuotasRefinanciacion.add(locCuotaRefinanciacion);

			locCalendar.add(Calendar.MONTH,1);
		}
		
		pDocumentoRefinanciacion.getListaRegistrosDeuda().clear();
		pDocumentoRefinanciacion.getListaRegistrosDeuda().addAll(locListaCuotasRefinanciacion);
		
		return locListaCuotasRefinanciacion;
	}
	
	private Double calcularValorReal(DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception{
		try{
			Double capital = pDocumentoRefinanciacion.getCapital();
			
			if (pDocumentoRefinanciacion.getMulta() >0){
				capital +=capital * (pDocumentoRefinanciacion.getMulta()/100);
			}
			if (pDocumentoRefinanciacion.getRecargo() >0){
				capital +=capital * (pDocumentoRefinanciacion.getRecargo()/100);
			}
			capital = capital - pDocumentoRefinanciacion.getEntrega();
			
			return capital;
		}
		catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * 
	 * @param pRegCancelacionPorRefinanciacion
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public void addRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion, AuditoriaTributaria pAuditoria) throws Exception{
			this.validarDocumentoRefinanciación(pDocumentoRefinanciacion);

			if (pDocumentoRefinanciacion.getListaRegistrosDeuda().isEmpty()){
				this.calcularCuotasRefinanciacion(pDocumentoRefinanciacion);
			}
			
			
			Integer locNumeroRefinanciacion = Criterio.getInstance(entityManager, DocumentoRefinanciacion.class)
				.setProyeccion(Proyeccion.MAX("numeroRefinanciacion")).uniqueResult();
//			Integer locNumeroRefinanciacion = (Integer)this.entityManager.createNativeQuery("Select max( cast(numero_refinanciacion as integer)) from documento_refinanciacion").executeUpdate();
			if (locNumeroRefinanciacion == null){
				locNumeroRefinanciacion = 0;
			}
			locNumeroRefinanciacion++;
			pDocumentoRefinanciacion.setNumeroRefinanciacion(locNumeroRefinanciacion);
			
			DocumentoRef locDocHabilitanteEspecializado = new DocumentoRef();
			
			Obligacion locObligacion = pDocumentoRefinanciacion.getObligacion();
			
			Integer locNumeroTramite = Criterio.getInstance(entityManager, Obligacion.class).setProyeccion(Proyeccion.MAX("numeroTramite")).uniqueResult();
			if (locNumeroTramite == null){
				locNumeroTramite = 0;
			}
//			Integer locNumeroTramite = (Integer)this.entityManager.createNativeQuery("Select max( cast(numero_tramite as integer)) from obligacion").executeUpdate();

			locNumeroTramite++;
			locObligacion.setNumeroTramite(locNumeroTramite);
			
			locDocHabilitanteEspecializado.setLlaveUsuarioAuditoria(locObligacion.getLlaveUsuarioAuditoria());
			locObligacion.setDocumentoEspecializado(locDocHabilitanteEspecializado);
			
			this.setValoresPorDefecto(pDocumentoRefinanciacion);
		
			pDocumentoRefinanciacion.setTipoDocGeneradorDeuda(TipoDocGeneradorDeuda.REFINANCIACION);
			
			locDocHabilitanteEspecializado.setObligacion(locObligacion);
			locDocHabilitanteEspecializado.setDomicilio(locObligacion.getPersona().getDomicilio());
			locDocHabilitanteEspecializado.setEstado(Estado.ACTIVO);
			locDocHabilitanteEspecializado.setFechaCreacion(Calendar.getInstance().getTime());
			
//			pDocumentoRefinanciacion.getObligacion().setDocumentoEspecializado(locDocHabilitanteEspecializado);
			
//			Obligacion locObligacionTemporal = this.entityManager.merge(pDocumentoRefinanciacion.getObligacion());
//			System.out.println(locObligacionTemporal);
		
			for (RegistroDeuda cadaRegistroDeuda : pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()){
				entityManager.detach(cadaRegistroDeuda);
			}
			this.entityManager.detach(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion());
			
			TrascenderEnverListener.setValoresEnAuditoriaBean(locObligacion.getDocumentoEspecializado());
			pDocumentoRefinanciacion.setObligacion(this.entityManager.merge(locObligacion));
			this.entityManager.persist(pDocumentoRefinanciacion);
			for (RegistroDeuda cadaRegistroDeuda: pDocumentoRefinanciacion.getListaRegistrosDeuda()){
				cadaRegistroDeuda.setDocGeneradorDeuda(pDocumentoRefinanciacion);
				//Creo el locRegistroDeuda para setearle el documento refinanciacion.
				this.entityManager.persist(cadaRegistroDeuda);
			}
			
			this.entityManager.flush();
			
//			this.entityManager.detach(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda());
			
			System.out.println(pDocumentoRefinanciacion.getObligacion());

//			RegistroDeuda locRegistroDeuda = pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda().iterator().next();
			//si se trata de reliquidaciones tgi q solo traiga las reliquidaciones de esta y asi si son liquidaciones
//			List<RegistroDeuda> locListaRegistrosDeudaAsociados = this.businessReLiquidacionTasa.getListaRegistrosDeudaAsociados(locRegistroDeuda, locRegistroDeuda.getTipoDeuda());

//			if(locRegistroDeuda.getDocGeneradorDeuda() instanceof TasaTGI){
//				//Tomo el primer registro deuda a refinanciar, ese me sirve para saber si es tercio o bimestre la deuda q se refinancia
//				TasaTGI locTasaTGI = (TasaTGI) locRegistroDeuda.getDocGeneradorDeuda();
//				//Si refinancio la bimestral es xq no pague nada, por eso todas las otras quedan como Refinanciada bimestral
//				if((locTasaTGI.getTipoTasa().getPeriodicidad().equals(Periodicidad.BIMESTRAL) && locTasaTGI.getTipoTasa().getPeriodicidadCuotas().equals(Periodicidad.BIMESTRAL))){
//					for(RegistroDeuda cadaRegistroDeuda : locListaRegistrosDeudaAsociados){
//						//No se cambia el estado del bimestre
//						if(locRegistroDeuda.getIdRegistroDeuda() != cadaRegistroDeuda.getIdRegistroDeuda()){
//							cadaRegistroDeuda.setEstado(EstadoRegistroDeuda.REFINANCIADA_BIMESTRAL);
//							this.entityManager.merge(cadaRegistroDeuda);
//						}
//					}
//				}
//			}

			for(RegistroDeuda cadaRegistroDeuda: pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()){
//				cadaRegistroDeuda.toString();
//				cadaRegistroDeuda.getDocGeneradorDeuda().toString();
//				cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
//				cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona().toString();
				
				cadaRegistroDeuda.setRegistroCancelacion(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion());

				//Por defecto todas quedan como refinanciadas, en el caso de que se refinancie la deuda anual tgi, los periodos de tercios y primer bimestre
				//pasan a tener estado REFINANCIADA_ANUAL
				cadaRegistroDeuda.setEstado(EstadoRegistroDeuda.REFINANCIADA);
				this.entityManager.merge(cadaRegistroDeuda);
			}
			
			if(pAuditoria != null){
				pAuditoria.setDocumentoRefinanciacion(pDocumentoRefinanciacion);
				this.businessAuditoria.updateAuditoriaTributaria(pAuditoria);
			}
	}
	
	private void validarDocumentoRefinanciación(DocumentoRefinanciacion pDocumentoRefinanciacion)  throws TrascenderException {
//		if (pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getDigestoMunicipal() == null){
//			throw new SaicException(361);
//		}
	}

	/**
	 * Setea todos los valores del documento de refinanciación por defecto
	 */
	private void setValoresPorDefecto(DocumentoRefinanciacion pDocumentoRefinanciacion) {
		Calendar locCalendario = Calendar.getInstance();
		
		if (pDocumentoRefinanciacion.getFechaDesde()==null){
			pDocumentoRefinanciacion.setFechaDesde(locCalendario.getTime());
		}
		if (pDocumentoRefinanciacion.getCantidadCuotas()==null){
			pDocumentoRefinanciacion.setCantidadRegDeuda(pDocumentoRefinanciacion.getListaRegistrosDeuda().size());
		}
		if (pDocumentoRefinanciacion.getCantidadRegDeuda()==null){
			pDocumentoRefinanciacion.setCantidadRegDeuda(pDocumentoRefinanciacion.getListaRegistrosDeuda().size());
		}
		if (pDocumentoRefinanciacion.getNombre()==null){
			DateFormat locFormato = DateFormat.getDateInstance(DateFormat.SHORT);
			pDocumentoRefinanciacion.setNombre("["+locFormato.format(locCalendario.getTime())+"] - Refinanciación ");
		}
	}

	/**
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception{
			this.entityManager.merge(pDocumentoRefinanciacion);
//		this.session = GestorPersistenciaSaic.getInstance().getSession();
//		Transaction tx=null;
//		try{
//			tx = this.session.beginTransaction();
//			this.session.update(pDocumentoRefinanciacion);
//			tx.commit();
//		}
//		catch(Exception e){
//			if ((tx!=null)&&(tx.isActive())){
//				tx.rollback();
//			}
//			throw e;
//		}
//		finally{
//			if ((this.session!=null)&&(this.session.isOpen())){
//				this.session.close();
//			}
//		}
	}
	
	@Override
	public DocumentoRefinanciacion getDocumentoRefinanciacion(long pIdDocGeneradorDeuda) throws Exception {
		DocumentoRefinanciacion locDocumentoRetorno = (DocumentoRefinanciacion) Criterio.getInstance(this.entityManager, DocumentoRefinanciacion.class)
				.add(Restriccion.IGUAL("idDocGeneradorDeuda", pIdDocGeneradorDeuda))
				.uniqueResult();

		if(locDocumentoRetorno != null) {
			locDocumentoRetorno.toString();
			if(locDocumentoRetorno.getObligacion() != null) {
//				locDocumentoRetorno.getObligacion().getListaLibresDeuda().size();
				if(locDocumentoRetorno.getObligacion().getPersona() != null) {
					locDocumentoRetorno.getObligacion().getPersona().toString();
					locDocumentoRetorno.getObligacion().getPersona().getDomicilio().toString();
					locDocumentoRetorno.getObligacion().getPersona().getDomicilio().getLocalidad().toString();
				}
			}
			locDocumentoRetorno.getStringNombreRefinanciacion();
			locDocumentoRetorno.getStringInmuebles();
			locDocumentoRetorno.getStringComercios();
			locDocumentoRetorno.getRegCancelacionPorRefinanciacion().getMultasTotal();
			if(locDocumentoRetorno.getRegCancelacionPorRefinanciacion().getDigestoMunicipal() != null) {
				locDocumentoRetorno.getRegCancelacionPorRefinanciacion().getDigestoMunicipal().toString();
			}

			for(RegistroDeuda cadaRegistro : locDocumentoRetorno.getListaRegistrosDeuda()) {
				cadaRegistro.toString();
				cadaRegistro.getStringPeriodoLiquidado();
//				cadaRegistro.getConceptoDeuda();
				if(cadaRegistro instanceof LiquidacionTasa) {
					businessLiquidacionTasa.getLiquidacionTasaCompleta((LiquidacionTasa) cadaRegistro);
				}
			}

			if(locDocumentoRetorno.getRegCancelacionPorRefinanciacion() != null) {
				for(RegistroDeuda cadaRegDeuda : locDocumentoRetorno.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()) {
					cadaRegDeuda.getStringPeriodoLiquidado();
					cadaRegDeuda.getStringObligacion();
//					cadaRegDeuda.getConceptoDeuda();
				}
			}
			
//			if(locDocumentoRetorno.getListaLiquidacionesRefinanciadas() != null) {
//				locDocumentoRetorno.getListaLiquidacionesRefinanciadas().size();
//				for(RegistroDeuda cadaRegDeuda : locDocumentoRetorno.getListaLiquidacionesRefinanciadas()) {
//					cadaRegDeuda.getStringPeriodoLiquidado();
//					cadaRegDeuda.getStringObligacion();
//				}
//			}
			
		}

		return locDocumentoRetorno;
	}
	
	@Override
	public FiltroRefinanciacion findListaRefinanciaciones(FiltroRefinanciacion pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoRefinanciacion.class)
				.add(Restriccion.IGUAL("numeroRefinanciacion", pFiltro.getNroRefinanciacion()))
				.add(Restriccion.IGUAL("estadoRefinanciacion", pFiltro.getEstado()));

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("obligacion.persona", pFiltro.getPersona()));
		}
		
		pFiltro.procesarYListar(locCriterio);
				
		for(DocumentoRefinanciacion documentoRefinanciacion : pFiltro.getListaResultados()) {
			documentoRefinanciacion.getListaRegistrosDeuda().size();
			documentoRefinanciacion.getObligacion().toString();
			documentoRefinanciacion.getObligacion().getPersona().toString();
		}

		return pFiltro;
	}
	
	/**
	 * @param pTasaNominalAnual
	 * @param pCapital
	 * @param pCuotasPorAnio
	 * @param pCantidadCuotas
	 * @return
	 */
	private Double calcularCuotaPura(Double pTasaNominalAnual, Double pCapital, Integer pCuotasPorAnio, Integer pCantidadCuotas, TipoCalculoInteres tipoCalculo){
		if (tipoCalculo == TipoCalculoInteres.FRANCÉS || tipoCalculo == TipoCalculoInteres.ALEMÁN) {
			Double locDivision = (pTasaNominalAnual / pCuotasPorAnio / 100);
			if(Math.pow(locDivision + 1, pCantidadCuotas) - 1 == 0) {
				return pCapital / pCantidadCuotas;
			} else {
				return new Double(pCapital * locDivision * Math.pow(locDivision + 1, pCantidadCuotas) / (Math.pow(locDivision + 1, pCantidadCuotas) - 1)).doubleValue();
			}
		}
		else {
			return new Double(pCapital / pCantidadCuotas);
		}
	}
	
	/**
	 * Calcula el interes de una cuota determinada
	 * @param pTasaNominalAnual
	 * @param pCapital
	 * @param pCuotasPorAnio
	 * @param pCantidadCuotas
	 * @param pNumeroCuota
	 * @return
	 */
	private Double calcularInteresCuota(Double pTasaNominalAnual, 
										Double pCapital, 
										Integer pCuotasPorAnio, 
										Integer pCantidadCuotas,
										Integer pNumeroCuota,
										TipoCalculoInteres tipoInteres){
		
		Double locDivisor = pTasaNominalAnual / pCuotasPorAnio / 100;
		if (tipoInteres == TipoCalculoInteres.FRANCÉS || tipoInteres == TipoCalculoInteres.ALEMÁN) {
			//TODO Hacer el aleman.
			if(Math.pow(locDivisor + 1, pCantidadCuotas) - 1 == 0) {
				return 0d;
			} else {
				return new Double(pCapital * locDivisor * (Math.pow(locDivisor + 1, pCantidadCuotas) - Math.pow(locDivisor + 1, pNumeroCuota - 1))
						/ (Math.pow(locDivisor + 1, pCantidadCuotas) - 1)).doubleValue();
			}
		} else {
			return new Double(pCapital * (pTasaNominalAnual / 100) / pCantidadCuotas);
		}
	}
	
	/**
	 * Calcula el capital de una cuota
	 * @param pTasaNominalAnual
	 * @param pCapital
	 * @param pCuotasPorAnio
	 * @param pCantidadCuotas
	 * @param pNumeroCuota
	 * @return
	 */
	private Double calcularSaldoCapitalCuota(Double pTasaNominalAnual, Double pCapital, Integer pCuotasPorAnio, Integer pCantidadCuotas, Integer pNumeroCuota){
		Double locDivisor = (pTasaNominalAnual / pCuotasPorAnio / 100);
		Double locAuxiliar = locDivisor+1;
		if (Math.pow(locAuxiliar, pCantidadCuotas)-1 == 0){
			return 0d;
		}
		else{
			return new Double(pCapital * (Math.pow(locAuxiliar, pCantidadCuotas) - Math.pow(locAuxiliar, pNumeroCuota)) / 
						(Math.pow(locAuxiliar, pCantidadCuotas)-1)).doubleValue();
		}
	}
	
	/**
	 * Calcula la tasa efectiva anual
	 * @param pTasaNominalAnual
	 * @param pCuotasPorAnio
	 * @return
	 */
	public Double calcularTasaEfectivaAnual(Double pTasaNominalAnual, int pCuotasPorAnio){
		Double interesMensual = (pTasaNominalAnual/100); //Interés mensual
		Double base = 1+(interesMensual/pCuotasPorAnio);
		Double potencia = new Double(Math.pow(base, pCuotasPorAnio)).doubleValue();
		return (potencia-1)*100;
	}
	
	public List<CuotaRefinanciacion> findCuotasRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion, CuotaRefinanciacion pCuotaRefinanciacion) throws Exception{
		List<CuotaRefinanciacion> locListaRetorno = new ArrayList<CuotaRefinanciacion>();
		
			Criterio locCriterio = Criterio.getInstance(this.entityManager, CuotaRefinanciacion.class);
			
			//Agrego los pParams
			locCriterio
						.add(Restriccion.IGUAL("docGeneradorDeuda", pDocumentoRefinanciacion))
						.add(Restriccion.IGUAL("listaRegistroDeuda", pCuotaRefinanciacion));
		
		return locListaRetorno;
	}
	
	private void validarPlantillaPlanDePago(PlantillaPlanDePago plantilla) {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaPlanDePago.class)
				.add(Restriccion.ID(plantilla.getIdPlantillaPlanDePago()).NEGADA())
				.add(Restriccion.LIKE("nombre", plantilla.getNombre(), false))
				.setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if (cantidad > 0) {
			throw new RuntimeException("Plantilla repetida");
		}
		
		for (ParametroAsociacion cadaParametro : plantilla.getListaParametrosAsociacion()) {
			cadaParametro.setPlantilla(plantilla);
		}
	}
	
	public void addPlantillaPlanDePago(PlantillaPlanDePago plantilla) {
		validarPlantillaPlanDePago(plantilla);
		TrascenderEnverListener.setValoresEnAuditoriaBean(plantilla);
		this.entityManager.merge(plantilla);
		this.entityManager.flush();
	}
	
	public void updatePlantillaPlanDePago(PlantillaPlanDePago plantilla) {
		validarPlantillaPlanDePago(plantilla);
		TrascenderEnverListener.setValoresEnAuditoriaBean(plantilla);
		this.entityManager.merge(plantilla);
		this.entityManager.flush();
	}
	
	public void deletePlantillaPlanDePago(PlantillaPlanDePago plantilla) {
		this.entityManager.remove(this.entityManager.merge(plantilla));
	}
	
	public FiltroPlantillaPlanDePago findListaPlantillaPlanDePago(FiltroPlantillaPlanDePago filtro) {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaPlanDePago.class)
				.add(Restriccion.ILIKE("nombre", filtro.getNombre()));
		filtro.procesarYListar(locCriterio);
		for (PlantillaPlanDePago cadaPlantilla : filtro.getListaResultados()) {
			cadaPlantilla.getListaLogsAuditoria().size();
			cadaPlantilla.getListaParametrosAsociacion().size();
		}
		return filtro;
	}
	
}

package com.trascender.saic.business.ejb;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado.Estado;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.refinanciacion.DocumentoRef;
import com.trascender.saic.business.interfaces.BusinessAuditoriaTributariaLocal;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.business.interfaces.BusinessRefinanciacionLocal;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda.TipoDocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.TasaTGI;
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
	
//	private BusinessRegistroValuadoLocal getBusinessRegistroValuado() throws Exception{
//		if (this.businessRegistroValuado==null){
//			Context locContext = new InitialContext();
//			BusinessRegistroValuadoLocalHome locHome = (BusinessRegistroValuadoLocalHome) PortableRemoteObject.narrow(locContext.lookup(BusinessRegistroValuadoLocalHome.JNDI_NAME),BusinessRegistroValuadoLocalHome.class);
//			this.businessRegistroValuado = locHome.create();
//		}
//		return this.businessRegistroValuado;
//	}
	
//	private BusinessReLiquidacionLocal getBusinessReLiquidacionTasa() throws Exception{
//		if (this.businessReLiquidacionTasa ==null){
//			Context locContext = new InitialContext();
//			BusinessReLiquidacionLocalHome locHome = (BusinessReLiquidacionLocalHome) PortableRemoteObject.narrow(locContext.lookup(BusinessReLiquidacionLocalHome.JNDI_NAME),BusinessReLiquidacionLocalHome.class);
//			this.businessReLiquidacionTasa = locHome.create();
//		}
//		return this.businessReLiquidacionTasa;
//	}
	
//	@SuppressWarnings("unused")
//	private BusinessLiquidacionTasaLocal getBusinessLiquidacionTasa() throws Exception{
//		if (this.businessLiquidacionTasa==null){
//			Context locContext = new InitialContext();
//			BusinessLiquidacionTasaLocalHome locHome = (BusinessLiquidacionTasaLocalHome) PortableRemoteObject.narrow(locContext.lookup(BusinessLiquidacionTasaLocalHome.JNDI_NAME),BusinessLiquidacionTasaLocalHome.class);
//			this.businessLiquidacionTasa = locHome.create();
//		}
//		return this.businessLiquidacionTasa;
//	}
	
	
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
		Double cuotaPura = this.calcularCuotaPura(pDocumentoRefinanciacion.getTasaNominalAnual(), capital, pDocumentoRefinanciacion.getCuotasPorAnio(), pDocumentoRefinanciacion.getCantidadCuotas());
		
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.set(pDocumentoRefinanciacion.getAnioInicioRefinanciacion(), pDocumentoRefinanciacion.getMesInicioRefinanciacion()-1, pDocumentoRefinanciacion.getDiaVencimiento());

		Date locFechaActual = SecurityMgr.getInstance().getFechaActual().getTime();

		for (int i=1; i<=pDocumentoRefinanciacion.getCantidadCuotas();i++){
			CuotaRefinanciacion locCuotaRefinanciacion = new CuotaRefinanciacion();
			locCuotaRefinanciacion.setFechaEmision(locFechaActual);
			locCuotaRefinanciacion.setNumeroCuota(i);
			locCuotaRefinanciacion.setDocGeneradorDeuda(pDocumentoRefinanciacion);
			locCuotaRefinanciacion.setValorCuota(cuotaPura);
			locCuotaRefinanciacion.setInteres(this.calcularInteresCuota(pDocumentoRefinanciacion.getTasaNominalAnual(), pDocumentoRefinanciacion.getCapital(), pDocumentoRefinanciacion.getCuotasPorAnio(), pDocumentoRefinanciacion.getCantidadCuotas(), i) );
			locCuotaRefinanciacion.setSaldoCapital(this.calcularSaldoCapitalCuota(pDocumentoRefinanciacion.getTasaNominalAnual(), pDocumentoRefinanciacion.getCapital(), pDocumentoRefinanciacion.getCuotasPorAnio(), pDocumentoRefinanciacion.getCantidadCuotas(), i));
			locCuotaRefinanciacion.setTipoDeuda(TipoDeuda.REFINANCIACION);
			locCuotaRefinanciacion.setFechaVencimiento(locCalendar.getTime());
			
			locCuotaRefinanciacion.setNumeroRegistroDeuda(i);
			
			locListaCuotasRefinanciacion.add(locCuotaRefinanciacion);

//			Periodo locPeriodo = this.businessCalendario.getPeriodo(null,
//					locCalendar.get(Calendar.MONTH)+1,	
//					locCalendar.get(Calendar.YEAR),
//					Periodicidad.MENSUAL,
//					this.businessObligacion.getTipoObligacionFromObligacion(locCuotaRefinanciacion.getDocGeneradorDeuda().getObligacion()).getIdTipoObligacion());
			//FIXME cambio periodo
//			locCuotaRefinanciacion.setCuotaLiquidacion((CuotaLiquidacion)locPeriodo);
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

			RegistroDeuda locRegistroDeuda = pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda().iterator().next();
			//si se trata de reliquidaciones tgi q solo traiga las reliquidaciones de esta y asi si son liquidaciones
			List<RegistroDeuda> locListaRegistrosDeudaAsociados = this.businessReLiquidacionTasa.getListaRegistrosDeudaAsociados(locRegistroDeuda, locRegistroDeuda.getTipoDeuda());

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
//		this.session = GestorPersistenciaSaic.getInstance().getSession();
//		Transaction tx=null;
//		try{
//			tx = this.session.beginTransaction();
//			this.validarDocumentoRefinanciación(pDocumentoRefinanciacion);
//
//			if (pDocumentoRefinanciacion.getListaRegistrosDeuda().isEmpty()){
//				this.calcularCuotasRefinanciacion(pDocumentoRefinanciacion);
//			}
//
//			Integer locNumeroRefinanciacion = (Integer)this.session.createSQLQuery("Select max( cast(numero_refinanciacion as integer)) from documento_refinanciacion").list().get(0);
//			if (locNumeroRefinanciacion == null){
//				locNumeroRefinanciacion = 0;
//			}
//			locNumeroRefinanciacion++;
//			pDocumentoRefinanciacion.setNumeroRefinanciacion(locNumeroRefinanciacion);
//			
//			DocumentoRef locDocHabilitanteEspecializado = new DocumentoRef();
//			
//			Obligacion locObligacion = pDocumentoRefinanciacion.getObligacion();
//			
//			Integer locNumeroTramite = (Integer)this.session.createSQLQuery("Select max( cast(numero_tramite as integer)) from obligacion").list().get(0);
//
//			locNumeroTramite++;
//			locObligacion.setNumeroTramite(locNumeroTramite);
//			
//			locObligacion.setDocumentoEspecializado(locDocHabilitanteEspecializado);
//			
//			this.setValoresPorDefecto(pDocumentoRefinanciacion);
//		
//			pDocumentoRefinanciacion.setTipoDocGeneradorDeuda(TipoDocGeneradorDeuda.REFINANCIACION);
//			
//			locDocHabilitanteEspecializado.setObligacion(locObligacion);
//			locDocHabilitanteEspecializado.setDomicilio(locObligacion.getPersona().getDomicilio());
//			locDocHabilitanteEspecializado.setEstado(Estado.ACTIVO);
//			locDocHabilitanteEspecializado.setFechaCreacion(Calendar.getInstance().getTime());
//			pDocumentoRefinanciacion.getObligacion().setDocumentoEspecializado(locDocHabilitanteEspecializado);
//		
//			this.session.save(pDocumentoRefinanciacion.getObligacion());
//			
//			this.session.evict(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion());
//			this.session.evict(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda());
//			
//			this.session.save(pDocumentoRefinanciacion);
//
//			for (RegistroDeuda cadaRegistroDeuda: pDocumentoRefinanciacion.getListaRegistrosDeuda()){
//				cadaRegistroDeuda.setDocGeneradorDeuda(pDocumentoRefinanciacion);
//				this.session.saveOrUpdate(cadaRegistroDeuda);
//			}
//
//			RegistroDeuda locRegistroDeuda = pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda().iterator().next();
//			//si se trata de reliquidaciones tgi q solo traiga las reliquidaciones de esta y asi si son liquidaciones
//			List<RegistroDeuda> locListaRegistrosDeudaAsociados = this.getBusinessReLiquidacionTasa().getListaRegistrosDeudaAsociados(locRegistroDeuda, locRegistroDeuda.getTipoDeuda());
//
//			if(!this.session.isOpen()){
//				this.session = GestorPersistenciaSaic.getInstance().getSession();
//			}
//			
//			if(locRegistroDeuda.getDocGeneradorDeuda() instanceof TasaTGI){
//				//Tomo el primer registro deuda a refinanciar, ese me sirve para saber si es tercio o bimestre la deuda q se refinancia
//				TasaTGI locTasaTGI = (TasaTGI) locRegistroDeuda.getDocGeneradorDeuda();
//				//Si refinancio la bimestral es xq no pague nada, por eso todas las otras quedan como Refinanciada bimestral
//				if((locTasaTGI.getTipoTasa().getPeriodicidad().equals(Periodicidad.BIMESTRAL) && locTasaTGI.getTipoTasa().getPeriodicidadCuotas().equals(Periodicidad.BIMESTRAL))){
//					for(RegistroDeuda cadaRegistroDeuda : locListaRegistrosDeudaAsociados){
//						//No se cambia el estado del bimestre
//						if(locRegistroDeuda.getIdRegistroDeuda() != cadaRegistroDeuda.getIdRegistroDeuda()){
//							cadaRegistroDeuda.setEstado(EstadoRegistroDeuda.REFINANCIADA_BIMESTRAL);
//							this.session.merge(cadaRegistroDeuda);
//						}
//					}
//				}
//			}
//
//			for(RegistroDeuda cadaRegistroDeuda: pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()){
//				cadaRegistroDeuda.toString();
//				cadaRegistroDeuda.getDocGeneradorDeuda().toString();
//				cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
//				cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona().toString();
//				
//				cadaRegistroDeuda.setRegistroCancelacion(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion());
//
//				//Por defecto todas quedan como refinanciadas, en el caso de que se refinancie la deuda anual tgi, los periodos de tercios y primer bimestre
//				//pasan a tener estado REFINANCIADA_ANUAL
//				cadaRegistroDeuda.setEstado(EstadoRegistroDeuda.REFINANCIADA);
//				this.session.merge(cadaRegistroDeuda);
//			}
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
	
	/**
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoRefinanciacion getDocumentoRefinanciacion(long pIdDocGeneradorDeuda) throws Exception {
		DocumentoRefinanciacion locDocumentoRetorno = (DocumentoRefinanciacion) Criterio.getInstance(this.entityManager, DocumentoRefinanciacion.class)	
			.add(Restriccion.IGUAL("idDocGeneradorDeuda", pIdDocGeneradorDeuda))
			.uniqueResult();
		
		if (locDocumentoRetorno != null){
			locDocumentoRetorno.toString();
			locDocumentoRetorno.getObligacion().getPersona().toString();
			locDocumentoRetorno.getStringNombreRefinanciacion();
			locDocumentoRetorno.getStringInmuebles();
			locDocumentoRetorno.getStringComercios();
			locDocumentoRetorno.getRegCancelacionPorRefinanciacion().getMultasTotal();
			
			for(RegistroDeuda cadaRegistro : locDocumentoRetorno.getListaRegistrosDeuda()){
				cadaRegistro.toString();
				cadaRegistro.getStringPeriodoLiquidado();
				if (cadaRegistro instanceof LiquidacionTasa){
					businessLiquidacionTasa.getLiquidacionTasaCompleta((LiquidacionTasa) cadaRegistro);
				}
			}
			
			if(locDocumentoRetorno.getRegCancelacionPorRefinanciacion() != null){
				for(RegistroDeuda cadaRegDeuda : locDocumentoRetorno.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()){
					cadaRegDeuda.getStringPeriodoLiquidado();
					cadaRegDeuda.getStringObligacion();
				}
			}
		}
			
			
		return locDocumentoRetorno;
	}
	
	/**
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentoRefinanciacion> findListaRefinanciaciones(Persona pPersona, 
				Integer pNumeroRefinanciacion) throws Exception {
		List<DocumentoRefinanciacion> locListaDocumentoRefinanciacion = new ArrayList<DocumentoRefinanciacion>();
			Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoRefinanciacion.class)	
				.add(Restriccion.IGUAL("obligacion.persona", pPersona))
				.add(Restriccion.IGUAL("numeroRefinanciacion", pNumeroRefinanciacion));
			
			locListaDocumentoRefinanciacion = locCriterio.list();
			
			for (DocumentoRefinanciacion documentoRefinanciacion : locListaDocumentoRefinanciacion ) {
				documentoRefinanciacion.getListaRegistrosDeuda().size();
				documentoRefinanciacion.getObligacion().toString();
				documentoRefinanciacion.getObligacion().getPersona().toString();
			}
			
		return locListaDocumentoRefinanciacion;
	}
	
	/**
	 * @param pTasaNominalAnual
	 * @param pCapital
	 * @param pCuotasPorAnio
	 * @param pCantidadCuotas
	 * @return
	 */
	private Double calcularCuotaPura(Double pTasaNominalAnual, Double pCapital, Integer pCuotasPorAnio, Integer pCantidadCuotas){
		Double locDivision = (pTasaNominalAnual/pCuotasPorAnio/100);
		if (Math.pow(locDivision+1,pCantidadCuotas)-1 == 0){
			return pCapital/pCantidadCuotas;
		}
		else{
			return new Double(pCapital*locDivision* Math.pow(locDivision+1, pCantidadCuotas)/(Math.pow(locDivision+1,pCantidadCuotas)-1)).doubleValue(); 
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
										Integer pNumeroCuota){
		
		Double locDivisor = pTasaNominalAnual / pCuotasPorAnio / 100;
		if (Math.pow(locDivisor+1,pCantidadCuotas)-1 == 0){
			return 0d;
		}
		else{
			return new Double(pCapital * locDivisor *(Math.pow(locDivisor+1, pCantidadCuotas) - Math.pow(locDivisor+1, pNumeroCuota-1)) / (Math.pow(locDivisor+1, pCantidadCuotas)-1)).doubleValue(); 
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
//			.setFetchJoin("docGeneradorDeuda")
						.add(Restriccion.IGUAL("docGeneradorDeuda", pDocumentoRefinanciacion))
						.add(Restriccion.IGUAL("listaRegistroDeuda", pCuotaRefinanciacion));
		
		return locListaRetorno;
	
//		List<CuotaRefinanciacion> locListaRetorno = new ArrayList<CuotaRefinanciacion>();
//		
//		try{
//			Criteria locCriteria = this.session.createCriteria(CuotaRefinanciacion.class);
//					
//			if(pDocumentoRefinanciacion != null){
//						locCriteria.setFetchMode("docGeneradorDeuda", FetchMode.JOIN)
//						.add(Restrictions.eq("docGeneradorDeuda", pDocumentoRefinanciacion));
//			}
//			
//			if(pCuotaRefinanciacion != null){
//				
//				locCriteria.createAlias("listaRegistroDeuda", "locListaRegistroDeuda")
//				.add(Restrictions.eq("locListaRegistroDeuda", pCuotaRefinanciacion));
//			}
//			
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		
//		
//		
//		return locListaRetorno;
	}
	
}

package com.trascender.saic.business.ejb;

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

import com.trascender.framework.business.interfaces.BusinessUsuarioLocal;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.business.interfaces.BusinessAuditoriaTributariaLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria.EstadoAuditoriaTributaria;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.Intimacion;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.LogCambiosAuditoriaTributaria;

@Stateless(name="BusinessAuditoriaTributariaLocal")
public class BusinessAuditoriaTributariaBean implements BusinessAuditoriaTributariaLocal{

	private static final long serialVersionUID = -4623325673536993207L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private BusinessUsuarioLocal businessUsuario;
	
	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @return La auditoria que se agrego
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria addAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
			this.validarAuditoriaTributaria(pAuditoriaTributaria);
//			System.out.println(pAuditoriaTributaria.getListaLogCambios()+ " "+ pAuditoriaTributaria.getListaLogCambios().isEmpty());
			
			Set locListaCambio = pAuditoriaTributaria.getListaLogCambios();
			pAuditoriaTributaria.getListaLogCambios().clear();
			pAuditoriaTributaria = this.entityManager.merge(pAuditoriaTributaria);
			
			for(LogCambiosAuditoriaTributaria cadaCambio : pAuditoriaTributaria.getListaLogCambios()){
				cadaCambio.setAuditoriaTributaria(pAuditoriaTributaria);
				this.entityManager.persist(cadaCambio);
			}
			
			this.entityManager.flush();
		return pAuditoriaTributaria;
	}
	

	/**
	 * Valida que una auditoria no sea nula, tenga asociada una deuda
	 * y si alguna de las deudas esta asociada a otra auditoria.
	 * @param pAuditoriaTributaria
	 * @throws Exception
	 */
	private void validarAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		if(pAuditoriaTributaria == null){
			throw new SaicException(400);
		}
		
		if(pAuditoriaTributaria.getListaRegistroDeuda().isEmpty()){
			throw new SaicException(401);
		}
		
	}

	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @return la auditoria que se actualizo
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria updateAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
			this.validarAuditoriaTributaria(pAuditoriaTributaria);
			this.entityManager.merge(pAuditoriaTributaria);
		
		return pAuditoriaTributaria;
	}
	
	/**
	 * Valida si la Auditoria tenia una refinanciacion para poder actualizar en el caso que se refinancie.
	 * @param pAuditoriaTributaria
	 * @return
	 * @throws Exception
	 */
	private boolean vieneRefinanciada(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(pAuditoriaTributaria.getIdAuditoriaTributaria() != -1){
				Long locCantidad = (Long) Criterio.getInstance(this.entityManager, AuditoriaTributaria.class)
							.add(Restriccion.IGUAL("idAuditoriaTributaria", pAuditoriaTributaria.getIdAuditoriaTributaria()))
							.add(Restriccion.NULO("documentoRefinanciacion"))
							.setProyeccion(Proyeccion.COUNT())
							.uniqueResult();
				
				if(locCantidad > 0){
					throw new SaicException(412);
				}
				return true;
			}else {
				throw new SaicException(413);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Borra una auditoria solo si la auditoria esta en estado normal y no esta firmada.
	 * @param pAuditoriaTributaria
	 * @return un valor booleando si la operacion fue exitosa
	 * @throws Exception
	 */
	@Override
	public boolean deleteAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
			if((pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.NORMAL) 
					&& (pAuditoriaTributaria.getFirma() == null))){
				this.entityManager.remove(this.entityManager.merge(pAuditoriaTributaria));
				return true;
			}else {
				throw new SaicException(404);
			}
	}
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria getAuditoriaTributariaById(Long pId) throws Exception{
		 AuditoriaTributaria locAuditoria = (AuditoriaTributaria) Criterio.getInstance(this.entityManager, AuditoriaTributaria.class)
			.add(Restriccion.IGUAL("idAuditoriaTributaria", pId))
			.uniqueResult();
			
		if(locAuditoria == null){
			throw new SaicException(416);
		}	
		
		locAuditoria.toString();
		if(locAuditoria.getListaIntimaciones()!=null){
		locAuditoria.getListaIntimaciones().toString();
		}
			for(Intimacion cadaIntimacion : locAuditoria.getListaIntimaciones()){
				cadaIntimacion.toString();
			}
			if(locAuditoria.getListaLogCambios()!=null){
				locAuditoria.getListaLogCambios().toString();
			}
			for(LogCambiosAuditoriaTributaria cadaCambio : locAuditoria.getListaLogCambios()){
				cadaCambio.toString();
			}
			if(locAuditoria.getListaRegistroDeuda()!=null){
				locAuditoria.getListaIntimaciones().toString();
			}
			for(RegistroDeuda cadaRegistroDeuda : locAuditoria.getListaRegistroDeuda()){
				cadaRegistroDeuda.toString();
				cadaRegistroDeuda.getDocGeneradorDeuda().toString();
				cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
				cadaRegistroDeuda.getCuotaLiquidacion().toString();
				cadaRegistroDeuda.getStringObligacion();
				
				if(cadaRegistroDeuda instanceof LiquidacionTasa){
					LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa)cadaRegistroDeuda;
					locLiquidacionTasa.getListaModificadoresLiquidacion().toString();
					for(ModificadorLiquidacion cadaModPorRegistro : locLiquidacionTasa.getListaModificadoresLiquidacion()){
						cadaModPorRegistro.toString();
					}
					locLiquidacionTasa.getListaVencimientos().toString();
					for(Vencimiento cadaVencimientoPorRegistro : locLiquidacionTasa.getListaVencimientos() ){
						cadaVencimientoPorRegistro.toString();
					}
					locLiquidacionTasa.getListaParametrosValuados().toString();
					for(ParametroValuado cadaParametroValuadoPorRegistro : locLiquidacionTasa.getListaParametrosValuados()){
						cadaParametroValuadoPorRegistro.toString();
					}
					locLiquidacionTasa.getListaAlicuotasLiquidadas().toString();
					for(AlicuotaLiquidada cadaAlicuota : locLiquidacionTasa.getListaAlicuotasLiquidadas()){
						cadaAlicuota.toString();
					}
				}
			}
			
		
		return locAuditoria;
	}
	
	/**
	 * devuelve una lista con todas las auditorias tributarias segun los parametros
	 * @param pContribuyente
	 * @param pTipoObligacion
	 * @param pEstadoAuditoria
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @return
	 */
	public Set<AuditoriaTributaria> findListaAuditoriaTributaria(Persona pContribuyente, 
										TipoObligacion pTipoObligacion, 
										AuditoriaTributaria.EstadoAuditoriaTributaria pEstadoAuditoria, 
										Date pFechaDesde, Date pFechaHasta) throws Exception{
		
		Criterio locCriteria = Criterio.getInstance(this.entityManager, AuditoriaTributaria.class)
				.add(Restriccion.IGUAL("contribuyente", pContribuyente))
				.add(Restriccion.IGUAL("tipoObligacion", pTipoObligacion));
			
			if(pEstadoAuditoria != null){
				if(pEstadoAuditoria.equals(AuditoriaTributaria.EstadoAuditoriaTributaria.PROVISORIO)){
					locCriteria.add(Restriccion.NULO(("documentoRefinanciacion")))
								.add(Restriccion.IGUAL("estado", pEstadoAuditoria));
				}else if(pEstadoAuditoria.toString().toUpperCase().contains("REFINANCIADA")){
					locCriteria.add(Restriccion.AND(Restriccion.NOT(Restriccion.NULO("documentoRefinanciacion"))
							, Restriccion.OR(Restriccion.IGUAL("estado", EstadoAuditoriaTributaria.PROVISORIO)
											, Restriccion.IGUAL("estado", pEstadoAuditoria)) 
								));
				}else{
					locCriteria.add(Restriccion.IGUAL("estado", pEstadoAuditoria));
				}
			}
			
				locCriteria.add(Restriccion.MAYOR("fechaCreacion", pFechaDesde))
				.add(Restriccion.MENOR("fechaCreacion", pFechaHasta));
			
				Set locListaResultado = new HashSet(locCriteria.list());
//			locListaResultado.addAll(locCriteria.list());
				
			
			
			
			for(Object cadaObject : locListaResultado){
				AuditoriaTributaria cadaAuditoria = (AuditoriaTributaria) cadaObject;
				cadaAuditoria.toString();
				cadaAuditoria.getContribuyente().toString();
				cadaAuditoria.getListaRegistroDeuda().toString();
				for(RegistroDeuda cadaRegistroPorAuditoria : cadaAuditoria.getListaRegistroDeuda()){
					cadaRegistroPorAuditoria.toString();
					//si el registro es intancia de liquidacion, cargo la lista de modificadores, y vencimientos
					if(cadaRegistroPorAuditoria instanceof LiquidacionTasa){
						LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa)cadaRegistroPorAuditoria;
						locLiquidacionTasa.getListaModificadoresLiquidacion().toString();
						for(ModificadorLiquidacion cadaModPorRegistro : locLiquidacionTasa.getListaModificadoresLiquidacion()){
							cadaModPorRegistro.toString();
						}
						locLiquidacionTasa.getListaVencimientos().toString();
						for(Vencimiento cadaVencimientoPorRegistro : locLiquidacionTasa.getListaVencimientos() ){
							cadaVencimientoPorRegistro.toString();
						}
						locLiquidacionTasa.getListaParametrosValuados().toString();
						for(ParametroValuado cadaParametroValuadoPorRegistro : locLiquidacionTasa.getListaParametrosValuados()){
							cadaParametroValuadoPorRegistro.toString();
						}
						locLiquidacionTasa.getListaAlicuotasLiquidadas().toString();
						for(AlicuotaLiquidada cadaAlicuota : locLiquidacionTasa.getListaAlicuotasLiquidadas()){
							cadaAlicuota.toString();
						}
					}
				}
				
				
//				if (cadaAuditoria.getDocumentoRefinanciacion() != null) {
//					cadaAuditoria.getDocumentoRefinanciacion().toString();
//					for (RegistroDeuda cadaRegistro : cadaAuditoria.getDocumentoRefinanciacion().getListaRegistrosDeuda()) {
//						cadaRegistro.toString();
//					}
//				}
			}
			
		
		return locListaResultado;
	}
	
	/**
	 * @return una firma que Da validez juridica a una Auditoria.
	 */
	@Override
	public FirmaPermiso firmarAuditoriaTributaria(Usuario pUsuario) throws Exception{
		try{
			if (pUsuario == null){
				throw new TrascenderFrameworkException(62);
			}

			FirmaPermiso locFirma = new FirmaPermiso();
			locFirma.setUsuario(pUsuario);
			locFirma.setFechaHora(Calendar.getInstance().getTime());
			
			return locFirma;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


}


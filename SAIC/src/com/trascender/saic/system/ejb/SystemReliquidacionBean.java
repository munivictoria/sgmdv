package com.trascender.saic.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.Reliquidacion;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;
import com.trascender.saic.system.interfaces.SystemReliquidacion;

/**
 * @ejb.bean name="SystemReliquidacion"
 *           display-name="Name for SystemReliquidacion"
 *           description="Description for SystemReliquidacion"
 *           jndi-name="ejb/SystemReliquidacion"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateful(name="ejb/SystemReliquidacion")
public class SystemReliquidacionBean implements SystemReliquidacion {

	private static final long serialVersionUID = -43603628275582398L;
//	static {
//		Grupo grupo = new Grupo();
//		grupo.setId(serialVersionUID);
//		grupo.setNombre("Adm. Reliquidaciones");
//		
//		
//		Recurso reliquidacion = new Recurso();
//		reliquidacion.setIdRecurso(Reliquidacion.serialVersionUID);
//		reliquidacion.setNombre("Reliquidaciones");
//		
//		grupo.getListaRecursos().add(reliquidacion);
//		
//		SecurityMgr.getInstance().addGrupo(grupo);
//	}
	
	@EJB
	private BusinessReLiquidacionLocal reliquidacionBean;
	
	private long llave;
	/**
	 * 
	 */
	

	public SystemReliquidacionBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
//		try{
//			Context ctx = new InitialContext();
//			Object o = ctx.lookup(BusinessReLiquidacionLocalHome.JNDI_NAME);
//			BusinessReLiquidacionLocalHome home = (BusinessReLiquidacionLocalHome)PortableRemoteObject.narrow(o, BusinessReLiquidacionLocalHome.class);
//			this.reliquidacionBean = home.create();
//		}
//		catch(Exception e){
//			throw new CreateException("No se ha podido instanciar la capa de negocios");
//		}
	}

	

	/**
	 * 
	 * @param pLiquidacionTasa
	 * @param pFechaReLiquidacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public List<com.trascender.saic.recurso.persistent.Reliquidacion> reliquidarObligacion(
			com.trascender.saic.recurso.persistent.LiquidacionTasa pLiquidacionTasa, 
			java.util.Date pFechaReLiquidacion, 
			List<String> pListaNombresNuevosParametrosValuados,
			List<ParametroValuadoAlicuota> pListaNuevosParametrosAlicuotas,
			Map<String, Object> pMapaValoresFijos,
			com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal,
			boolean pAplicarIntereses) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Reliquidacion.serialVersionUID,Permiso.Accion.INSERT)){
				this.reliquidacionBean.setLlave(this.llave);
				//Si es una LiquidacionAgrupada, debo llamar al metodo varias veces.
				if (pLiquidacionTasa instanceof LiquidacionTasaAgrupada){
					for (LiquidacionTasa cadaLiquidacion : 
						((LiquidacionTasaAgrupada) pLiquidacionTasa).getListaLiquidacionesTasa()){
						this.reliquidacionBean.reliquidarObligacion(cadaLiquidacion, 
								pFechaReLiquidacion, pListaNombresNuevosParametrosValuados, 
								pListaNuevosParametrosAlicuotas, pMapaValoresFijos, pDigestoMunicipal, pAplicarIntereses, true);
					}
					return null;
				} else {
					return this.reliquidacionBean.reliquidarObligacion(pLiquidacionTasa, 
							pFechaReLiquidacion,pListaNombresNuevosParametrosValuados, 
							pListaNuevosParametrosAlicuotas, pMapaValoresFijos, pDigestoMunicipal,pAplicarIntereses ,true);
				}
			}
			else{
				throw new SaicException(772);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(360);
		}
	}
	
	@Override
	public com.trascender.saic.recurso.persistent.Reliquidacion getReliquidacionPorId(long pId) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Reliquidacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.reliquidacionBean.getReliquidacionPorId(pId);
			}
			else{
				throw new SaicException(772);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(761);
		}
	}
	
	@Override
	public com.trascender.saic.recurso.persistent.Reliquidacion getReliquidacionPorIdNuevaDeuda(long pIdDeuda) 
	throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Reliquidacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.reliquidacionBean.getReliquidacionPorIdNuevaDeuda(pIdDeuda);
			}
			else{
				throw new SaicException(772);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(761);
		}
	}
	
	/**
	 * 
	 * @param pLiquidacionTasa
	 * @param pFechaReLiquidacion
	 * @param pFechaNuevoVencimiento
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public List<com.trascender.saic.recurso.persistent.Reliquidacion> reliquidarObligacion(
			com.trascender.saic.recurso.persistent.LiquidacionTasa pLiquidacionTasa, 
			java.util.Date pFechaReLiquidacion,java.util.Date pFechaNuevoVencimiento,
			boolean pAplicarIntereses
			//com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal
			) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Reliquidacion.serialVersionUID,Permiso.Accion.INSERT)){
				this.reliquidacionBean.setLlave(this.llave);
				return this.reliquidacionBean.reliquidarObligacion(
						pLiquidacionTasa, pFechaReLiquidacion,
						pFechaNuevoVencimiento, pAplicarIntereses);//, pDigestoMunicipal);
			}
			else{
				throw new SaicException(772);
			}
			
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(360);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param pPersona
	 * @param pTipoDeuda
	 * @return
	 * @throws TrascenderException
	 */
	@Override
	public List findListaRegistrosDeudaContribuyente(Persona pPersona,
			TipoDeuda pTipoDeuda,
			Periodo pPeriodo,
			EstadoRegistroDeuda pEstadoRegistroDeuda,
			TipoObligacion pTipoObligacion,
			com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Reliquidacion.serialVersionUID,Permiso.Accion.SELECT)){
				return this.reliquidacionBean.findListaRegistrosDeudaContribuyente(
						pPersona, 
						pTipoDeuda,
						pPeriodo,
						pEstadoRegistroDeuda,
						pTipoObligacion,
						pParcela
						);
			}
			else{
				throw new SaicException(772);
			}
			
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(760);
		}
	}
	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}

	@Override
	public LiquidacionTasa calcularIntereses(LiquidacionTasa pLiquidacionTasa,
			Date pFecha, boolean pAplicarInteres, boolean pGuardarCambios)
			throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoTasasUnificadas,Permiso.Accion.UPDATE)){
				this.reliquidacionBean.setLlave(this.llave);
				return this.reliquidacionBean.calcularIntereses(pLiquidacionTasa, pFecha, pAplicarInteres, pGuardarCambios, false);
			}
			else{
				throw new SaicException(772);
			}
			
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(360);
		}
	}

	@Override
	public LiquidacionTasa notificar(LiquidacionTasa pLiquidacionTasa,
			Date fechaNotificacion, Date fechaApremio, String comentario) throws TrascenderException{
		Usuario usuario = SecurityMgr.getInstance().getUsuario(llave);
		return reliquidacionBean.notificar(pLiquidacionTasa, fechaNotificacion, fechaApremio, usuario, comentario);
	}

	@Override
	public void notificar(List<LiquidacionTasa> listaLiquidaciones,
			Date fechaNotificacion, Date fechaApremio,
			String comentario) throws TrascenderException {
		Usuario usuario = SecurityMgr.getInstance().getUsuario(llave);
		reliquidacionBean.notificar(listaLiquidaciones, fechaNotificacion, fechaApremio, usuario, comentario);
	}
}


package com.trascender.saic.system.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Sellado;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.RegistroValuadoTasaMenor;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroDDJJSHPS;
import com.trascender.saic.recurso.filtros.FiltroValorMedidor;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

/**
 * @ejb.bean name="SystemRegistroValuado" display-name="Name for SystemRegistroValuado" description="Description for SystemRegistroValuado"
 *           jndi-name="ejb/SystemRegistroValuado" type="Stateful" view-type="remote"
 */
@Stateful(name = "ejb/SystemRegistroValuado")
public class SystemRegistroValuadoBean implements SystemRegistroValuado {

	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;

	private long llave;

	// private void setBusinessRegistroValuado() throws Exception{
	// Context ctx=new InitialContext();
	// Object o=ctx.lookup(BusinessRegistroValuadoLocalHome.JNDI_NAME);
	// BusinessRegistroValuadoLocalHome home=(BusinessRegistroValuadoLocalHome)PortableRemoteObject.narrow(o,BusinessRegistroValuadoLocalHome.class);
	// this.businessRegistroValuadoLocal=home.create();
	// }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1064984236582723539L;

	public SystemRegistroValuadoBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
		// try{
		// this.setBusinessRegistroValuado();
		// }
		// catch(Exception e){
		// throw new CreateException();
		// }
	}

	/**
	 * 
	 * @param pPersona
	 * @param pPeriodo
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 */
	public DeclaracionJuradaSHPS getNuevaDDJJSHPS(Persona pPersona, CuotaLiquidacion pCuota, String pNroInscripcion, ArrayList plistaAtributosDinamicos) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Obligacion.serialVersionUID, Permiso.Accion.SELECT)) {
				System.out.println("LA persona: " + pPersona);
				System.out.println("LA cuota: " + pCuota);
				return this.businessRegistroValuadoLocal.getNuevaDDJJSHPS(pPersona, pCuota, pNroInscripcion, plistaAtributosDinamicos);
			} else {
				throw new SaicException(701);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(301);
		}
	}

	/**
	 * 
	 * @param pPersona
	 * @param pAño
	 * @param pPeriodicidad
	 * @param pNumeroPeriodo
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroDDJJSHPS getListaDDJJSHPS(FiltroDDJJSHPS pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJuradaSHPS.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRegistroValuadoLocal.getListaDDJJSHPS(pFiltro);
			} else {
				throw new SaicException(702);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(302);
		}
	}

	public DeclaracionJuradaSHPS getDDJJSHPSporId(long pIdDeclaracion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJuradaSHPS.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRegistroValuadoLocal.getDDJJSHPSporId(pIdDeclaracion);
			} else {
				throw new SaicException(702);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(302);
		}
	}

	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;

	}

	/**
	 * 
	 * @param pListaDDJJSHPS
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addDDJJSHPS(DeclaracionJuradaSHPS pDDJJSHPS) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJuradaSHPS.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessRegistroValuadoLocal.addDDJJSHPS(pDDJJSHPS);
			} else {
				throw new SaicException(703);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(303);
		}
	}
	
	public DeclaracionJuradaSHPS addDDJJSHPSParaLiquidar(DeclaracionJuradaSHPS pDDJJSHPS) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJuradaSHPS.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessRegistroValuadoLocal.addDDJJSHPSParaLiquidar(pDDJJSHPS);
			} else {
				throw new SaicException(703);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(303);
		}
	}

	/**
	 * 
	 * @param pDeclaracionJuradaSHPS
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateDDJJSHPS(com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS pDeclaracionJuradaSHPS) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJuradaSHPS.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessRegistroValuadoLocal.updateDDJJSHPS(pDeclaracionJuradaSHPS);
			} else {
				throw new SaicException(704);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(304);
		}
	}

	/**
	 * @ejb.interface-method view-type ="remote"
	 * @param pDeclaracionJuradaSHPS
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteDDJJSHPS(com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS pDeclaracionJuradaSHPS)
			throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJuradaSHPS.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessRegistroValuadoLocal.deleteDDJJSHPS(pDeclaracionJuradaSHPS);
			} else {
				throw new SaicException(704);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(310);
		}
	}

	/**
	 * 
	 * @param pCalle
	 * @param pPeriodo
	 * @param pServicio
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List getListaNuevasMedidasServiciosOSP(com.trascender.catastro.recurso.persistent.Calle pCalle, CuotaLiquidacion pCuota,
			com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP pServicio, String pCodigoMedidor) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValorMedidor.serialVersionUID, Permiso.Accion.SELECT)) {
				if(pServicio == null && pCodigoMedidor == null) {
					throw new SaicException(312);
				}
				return this.businessRegistroValuadoLocal.getListaNuevasMedidasServiciosOSP(pCalle, pCuota, pServicio, pCodigoMedidor);
			} else {
				throw new SaicException(705);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(305);
		}

	}

	/**
	 * 
	 * @param pValorMedidor
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateValorMedidor(com.trascender.habilitaciones.recurso.persistent.ValorMedidor pValorMedidor) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValorMedidor.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessRegistroValuadoLocal.updateValorMedidor(pValorMedidor);
			} else {
				throw new SaicException(706);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(306);
		}
	}

	/**
	 * 
	 * @param pValoresMedidores
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addListaValorMedidor(java.util.List pValoresMedidores) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValorMedidor.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessRegistroValuadoLocal.addListaValorMedidor(pValoresMedidores);
			} else {
				throw new SaicException(707);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(307);
		}
	}

	public com.trascender.habilitaciones.recurso.persistent.ValorMedidor getValorMedidorPorId(long pIdRegistroValuado) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValorMedidor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRegistroValuadoLocal.getValorMedidorPorId(pIdRegistroValuado);
			} else {
				throw new SaicException(708);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(313);
		}
	}

	/**
	 * 
	 * @param pCalle
	 * @param pAño
	 * @param pPeriodicidad
	 * @param pNumeroPeriodo
	 * @param pServicioOSP
	 * @param pCodigoMedidor
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroValorMedidor findListaValoresMedidor(FiltroValorMedidor pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValorMedidor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRegistroValuadoLocal.findListaValoresMedidor(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(308);
		}

	}

	/**
	 * 
	 * @param pPersona
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List findListaPagoSellado(com.trascender.framework.recurso.persistent.Persona pPersona, com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion,
			Boolean pPagado) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Sellado.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRegistroValuadoLocal.findListaPagoSellado(pPersona, pObligacion, pPagado);
			} else {
				throw new SaicException(709);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(309);
		}
	}

	@Override
	public List<RegistroValuadoTasaMenor> findListaRegistrosValuadosTasaMenor(Persona pPersona, CuotaLiquidacion pCuota, PeriodoLiquidacion pPeriodo, Calendario pCalendario,
			PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		return businessRegistroValuadoLocal.findListaRegistrosValuadosTasaMenor(pPersona, pCuota, pPeriodo, pCalendario, pPlantilla);
	}

	@Override
	public List<RegistroValuadoTasaMenor> generarRegistrosTasaMenor(Persona pPersona, CuotaLiquidacion pCuota, PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		return businessRegistroValuadoLocal.generarRegistrosTasaMenor(pPersona, pCuota, pPlantilla);
	}

	@Override
	public void addRegistrosValuadosTasaMenor(List<RegistroValuadoTasaMenor> pListaRegistrosValuados) throws TrascenderException {
		this.businessRegistroValuadoLocal.addRegistrosValuadosTasaMenor(pListaRegistrosValuados);
	}

	@Override
	public void updateRegistroValuadoTasaMenor(RegistroValuadoTasaMenor pRegistroValuado) throws TrascenderException {
		this.businessRegistroValuadoLocal.updateRegistroValuadoTasaMenor(pRegistroValuado);
	}

	@Override
	public void deleteRegistroValuadoTasaMenor(RegistroValuadoTasaMenor pRegistroValuadoTasaMenor) throws Exception {
		this.deleteRegistroValuadoTasaMenor(pRegistroValuadoTasaMenor);
	}

	@Override
	public RegistroValuadoTasaMenor getRegistroValuadoTasaMenorPorId(long pId) {
		return this.businessRegistroValuadoLocal.getRegistroValuadoTasaMenorPorId(pId);
	}

}

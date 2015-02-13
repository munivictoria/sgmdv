
package com.trascender.saic.system.ejb;

import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessRefinanciacionLocal;
import com.trascender.saic.exception.ResultadoLiquidacion;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroCobroExterno;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionArrendamiento;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionAutomotor;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionCementerio;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionOSP;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionPFO;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTGI;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaMenor;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.CobroExterno;
import com.trascender.saic.recurso.persistent.CobroExterno.EntidadRecaudadora;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.transients.HistorialPagosTasas;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;

@Stateless(name = "ejb/SystemLiquidacionTasa")
public class SystemLiquidacionTasaBean implements SystemLiquidacionTasa {

	private static final long serialVersionUID = 3289209208111711842L;
	private long llave;

	@EJB
	private BusinessLiquidacionTasaLocal businessLiquidacionTasaLocal;
	@EJB
	private BusinessRefinanciacionLocal businessRefinanciacionLocal;

	// private void setBusinessLiquidacionTasa() throws Exception{
	// Context ctx=new InitialContext();
	// Object o=ctx.lookup(BusinessLiquidacionTasaLocalHome.JNDI_NAME);
	// BusinessLiquidacionTasaLocalHome home=(BusinessLiquidacionTasaLocalHome)PortableRemoteObject.narrow(o, BusinessLiquidacionTasaLocalHome.class);
	// this.businessLiquidacionTasaLocal=home.create();
	// }
	//
	//
	// public BusinessImpresionLocal getBusinessImpresionLocal() throws Exception {
	// Context ctx = new InitialContext();
	// Object o=ctx.lookup(BusinessImpresionLocalHome.JNDI_NAME);
	// BusinessImpresionLocalHome home = (BusinessImpresionLocalHome)PortableRemoteObject.narrow(o, BusinessImpresionLocalHome.class);
	// return home.create();
	// }

	public SystemLiquidacionTasaBean() {
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
		// this.setBusinessLiquidacionTasa();
		// this.setBusinessRefinanciacion();
		// }
		// catch(Exception e){
		// e.printStackTrace();
		// throw new CreateException("No se ha podido inicializar la capa de negocios");
		// }
	}

	// private void setBusinessRefinanciacion() throws ClassCastException, CreateException, NamingException {
	// this.businessRefinanciacionLocal = ((BusinessRefinanciacionLocalHome)
	// PortableRemoteObject.narrow(new InitialContext().lookup(BusinessRefinanciacionLocalHome.JNDI_NAME), BusinessRefinanciacionLocalHome.class)
	// ).create();
	// }

	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * 
	 * @param pPeriodo
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public ResultadoLiquidacion liquidarTgi(CuotaLiquidacion[] pCuota, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.setLlave(this.llave);
				return this.businessLiquidacionTasaLocal.liquidarTgi(pCuota, pPersona, pParcela, pIgnorarPlan);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(320);
		}
	}
	
	public ResultadoLiquidacion liquidarArrendamiento(CuotaLiquidacion[] pCuota, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoArrendamiento, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.setLlave(this.llave);
				return this.businessLiquidacionTasaLocal.liquidarArrendamiento(pCuota, pPersona, pParcela, pIgnorarPlan);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(320);
		}
	}


	/**
	 * 
	 * @param pPeriodo
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public ResultadoLiquidacion liquidarSHPS(Persona pPersona, CuotaLiquidacion[] pCuotas, FiltroObligacionSHPS pFiltro, Boolean pIgnorarPlan) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoSHPS, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.setLlave(this.llave);
				return this.businessLiquidacionTasaLocal.liquidarSHPS(pPersona, pCuotas, pFiltro, pIgnorarPlan);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(321);
		}
	}

	/**
	 * Recupera una liquidación a partir del número de identificación
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public LiquidacionTasa getLiquidacionTasaPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.getLiquidacionTasaPorId(pId);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(323);
		}
	}
	
	@Override
	public LogLiquidacion getLogLiquidacionesPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LogLiquidacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.getLogLiquidacionesPorId(pId);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(339);
		}
	}

	@Override
	public FiltroLiquidacionTGI findListaLiquidacionesTGI(FiltroLiquidacionTGI pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoTGI, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesTGI(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(324);
		}
	}
	
	@Override
	public FiltroLiquidacionArrendamiento findListaLiquidacionesArrendamiento(FiltroLiquidacionArrendamiento pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoArrendamiento, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesArrendamiento(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(324);
		}
	}

	@Override
	public FiltroLiquidacionOSP findListaLiquidacionesOSP(FiltroLiquidacionOSP pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoOSP, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesOSP(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(326);
		}
	}

	/**
	 * Liquida la tasa de OSP
	 * 
	 * @param pServicio
	 * @param pCalle
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public ResultadoLiquidacion liquidarOSP(ServicioOSP pServicio, Calle pCalle, CuotaLiquidacion[] pCuota, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoOSP, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.setLlave(this.llave);
				return this.businessLiquidacionTasaLocal.liquidarOSP(pServicio, pCalle, pCuota, pPersona, pParcela, pIgnorarPlan);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(325);
		}
	}

	/**
	 * 
	 * @param pIdDocGeneradorDeuda
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public DocumentoRefinanciacion getDocumentoRefinanciacion(long pIdDocGeneradorDeuda) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRefinanciacionLocal.getDocumentoRefinanciacion(pIdDocGeneradorDeuda);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(327);
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public com.trascender.saic.recurso.persistent.RegistroDeuda getRegistroDeudaPorId(Long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.getRegistroDeudaPorId(pId);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(327);
		}
	}

	@Override
	public ResultadoLiquidacion liquidarPFO(com.trascender.framework.recurso.persistent.Persona pPersona, com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra,
			com.trascender.catastro.recurso.persistent.Calle pCalle, CuotaLiquidacion[] pCuota) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoPFO, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.setLlave(this.llave);
				return this.businessLiquidacionTasaLocal.liquidarPFO(pPersona, pObra, pCalle, pCuota);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(328);
		}
	}

	@Override
	public FiltroLiquidacionPFO findListaLiquidacionesPFO(FiltroLiquidacionPFO pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoPFO, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesPFO(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(329);
		}
	}

	/**
	 * 
	 * @param pPersona
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public FiltroLiquidacionSHPS findListaLiquidacionesSHPS(FiltroLiquidacionSHPS pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoSHPS, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesSHPS(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(330);
		}
	}

	/**
	 * Anula las Liquidaciones no canceladas de la obligación, solo usar después de llamar al anular obligación
	 * 
	 * @param pObligacion
	 * @return
	 * @throws TrascenderException
	 *             en caso que el registro de deuda no sea una liquidación o que ya se encuentre vencida
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public void anularRegistrosDeudaSinCancelar(Obligacion pObligacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessLiquidacionTasaLocal.anularRegistrosDeudaSinCancelar(pObligacion);
			} else {
				throw new SaicException(772);
			}

		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(333);
		}

	}

	/**
	 * Recupera el último vencimiento de un registro de deuda (en caso de ser una liquidación)
	 */
	@Override
	public com.trascender.saic.recurso.persistent.Vencimiento getVencimientoActualPorRegistroDeuda(com.trascender.saic.recurso.persistent.RegistroDeuda pRegistroDeuda)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.getVencimientoActualPorRegistroDeuda(pRegistroDeuda);
			} else {
				throw new SaicException(772);
			}

		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(331);
		}

	}

	@Override
	public void generarLiquidacionPruebaTGI(CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.generarLiquidacionPruebaTGI(pCuota, pPersona, pParcela);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(320);
		}
	}

	@Override
	public List generarLiquidacionPruebaOSP(ServicioOSP pServicio, Calle pCalle, CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoOSP, Permiso.Accion.INSERT)) {
				return this.businessLiquidacionTasaLocal.generarLiquidacionPruebaOSP(pServicio, pCalle, pCuota, pPersona, pParcela);
			} else {
				// TODO VER NÚMERO DE ERROR
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public List generarLiquidacionPruebaSHPS(Persona pPersona, CuotaLiquidacion pCuota, FiltroObligacionSHPS pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoOSP, Permiso.Accion.INSERT)) {
				return this.businessLiquidacionTasaLocal.generarLiquidacionPruebaSHPS(pPersona, pCuota, pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(333);
		}
	}

	/**
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.List generarLiquidacionPruebaPFO(com.trascender.framework.recurso.persistent.Persona pPersona, com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra,
			com.trascender.catastro.recurso.persistent.Calle pCalle, CuotaLiquidacion pCuota) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoPFO, Permiso.Accion.INSERT)) {
				return this.businessLiquidacionTasaLocal.generarLiquidacionPruebaPFO(pPersona, pObra, pCalle, pCuota);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pDocumentoRefinanciacion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public void addRefinanciacion(com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion pDocumentoRefinanciacion, AuditoriaTributaria pAuditoriaTributaria)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoRefinanciacion.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessRefinanciacionLocal.addRefinanciacion(pDocumentoRefinanciacion, pAuditoriaTributaria);
			} else {
				// TODO VER NUMERO DE ERROR
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			// TODO VER NÚMERO DE ERROR
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pDocumentoRefinanciacion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public void updateRefinanciacion(com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion pDocumentoRefinanciacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoRefinanciacion.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessRefinanciacionLocal.updateRefinanciacion(pDocumentoRefinanciacion);
			} else {
				throw new SaicException(772);
				// TODO ver error
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			// TODO VER NÚMERO DE ERROR
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pDocumentoRefinanciacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.Set<CuotaRefinanciacion> calcularCuotasRefinanciacion(com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion pDocumentoRefinanciacion)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRefinanciacionLocal.calcularCuotasRefinanciacion(pDocumentoRefinanciacion);
			} else {
				// TODO VER NÚMOER DE ERROR
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pPersona
	 * @param pNumeroRefinanciacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public List<DocumentoRefinanciacion> findListaRefinanciaciones(Persona pPersona, Integer pNumeroRefinanciacion) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessRefinanciacionLocal.findListaRefinanciaciones(pPersona, pNumeroRefinanciacion);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pObra
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public boolean comprobarObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.comprobarObra(pObra);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public HistorialPagosTasas getHistorialPagos(Obligacion pObligacion, int pAnios) {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.getHistorialPagos(pObligacion, pAnios);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultadoLiquidacion liquidarTasaMenor(Persona pPersona, Parcela pParcela, TipoObligacion pTipoObligacionTipoTasa, CuotaLiquidacion[] pCuotas, Boolean pIgnorarPlan)
			throws Exception {
		this.businessLiquidacionTasaLocal.setLlave(this.llave);
		return businessLiquidacionTasaLocal.liquidarTasaMenor(pPersona, pParcela, pTipoObligacionTipoTasa, pCuotas, pIgnorarPlan);
	}

	@Override
	public FiltroLiquidacionTasaMenor findListaLiquidacionesTasaMenor(FiltroLiquidacionTasaMenor pFiltro) throws Exception {
		return businessLiquidacionTasaLocal.findListaLiquidacionesTasaMenor(pFiltro);
	}

	@Override
	public void eliminarLiquidacionesFisicamente(List<LiquidacionTasa> pListaLiquidacion, String comentario) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoTasasUnificadas, Permiso.Accion.AUDITH)) {
				this.businessLiquidacionTasaLocal.setLlave(llave);
				this.businessLiquidacionTasaLocal.eliminarLiquidacionesFisicamente(pListaLiquidacion, comentario);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public void addRegistroCancelacionManual(List<LiquidacionTasa> pListaLiquidacion, String comentario) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoTasasUnificadas, Permiso.Accion.AUDITH)) {
				Usuario locUsuario = SecurityMgr.getInstance().getUsuario(llave);
				this.businessLiquidacionTasaLocal.addRegistroCancelacionManual(pListaLiquidacion, comentario, locUsuario);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public void updateLiquidacionTasa(List<LiquidacionTasa> pLista, String comentario) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoTasasUnificadas, Permiso.Accion.AUDITH)) {
				this.businessLiquidacionTasaLocal.setLlave(llave);
				this.businessLiquidacionTasaLocal.updateLiquidacionTasa(pLista, comentario);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public void marcarImpaga(List<LiquidacionTasa> pListaLiquidacion, String comentario) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoTasasUnificadas, Permiso.Accion.AUDITH)) {
				this.businessLiquidacionTasaLocal.setLlave(llave);
				this.businessLiquidacionTasaLocal.marcarImpaga(pListaLiquidacion, comentario);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public FiltroLiquidacionAutomotor findListaLiquidacionesAutomotor(FiltroLiquidacionAutomotor pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoAutomotor, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesAutomotor(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(334);
		}
	}

	@Override
	public FiltroLiquidacionCementerio findListaLiquidacionesCementerio(FiltroLiquidacionCementerio pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoCementerio, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaLiquidacionesCementerio(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(334);
		}
	}

	public List findListaDocsGeneradoresDeuda(Obligacion pObligacion) {
		return this.businessLiquidacionTasaLocal.findListaDocsGeneradoresDeuda(pObligacion);
	}

	@Override
	public ResultadoLiquidacion liquidarAutomotor(Vehiculo pVehiculo, Persona pPersona, CuotaLiquidacion pCuota) throws Exception {
		this.businessLiquidacionTasaLocal.setLlave(this.llave);
		return this.businessLiquidacionTasaLocal.liquidarAutomotor(pVehiculo, pPersona, pCuota);
	}

	@Override
	public ResultadoLiquidacion liquidarCementerio(ParcelaCementerio pParcelaCementerio, CuotaLiquidacion pCuota, Persona pPersona, TipoSepultura pTipoSepultura) throws Exception {
		this.businessLiquidacionTasaLocal.setLlave(this.llave);
		return this.businessLiquidacionTasaLocal.liquidarCementerio(pParcelaCementerio, pCuota, pPersona, pTipoSepultura);
	}

	@Override
	public FiltroCobroExterno findListaCobroExterno(FiltroCobroExterno pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, CobroExterno.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.findListaCobroExterno(pFiltro);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(336);
		}
	}

	@Override
	public CobroExterno getCobroExternoById(Long pId) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, CobroExterno.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessLiquidacionTasaLocal.getCobroExternoById(pId);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(337);
		}
	}

	public void procesarArchivoCobroExterno(File pArchivo, EntidadRecaudadora pEntidadRecaudadora) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, CobroExterno.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessLiquidacionTasaLocal.procesarArchivoCobroExterno(pArchivo, pEntidadRecaudadora);
			} else {
				throw new SaicException(772);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(338);
		}
	}

	public String getCodigoClientePagoFacil(Long pIdLiquidacionTasa) {
		return this.businessLiquidacionTasaLocal.getCodigoClientePagoFacil(pIdLiquidacionTasa);
	}

	public void generarLogLiquidacion(LiquidacionTasa pLiquidacion, LogLiquidacion.Evento pEvento, String pComentario) {
		try {
			Usuario usuario = SecurityMgr.getInstance().getUsuario(this.llave);
			if(usuario != null) {
				this.businessLiquidacionTasaLocal.generarLogLiquidacion(pLiquidacion, usuario, pEvento, pComentario);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<LogLiquidacion> getListaLogLiquidacion(FiltroLogLiquidacion pFiltro) throws TrascenderException {
		try {
			return this.businessLiquidacionTasaLocal.getListaLogLiquidacion(pFiltro);
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(324);
		}
	}
	
	public FiltroLogLiquidacion findListaLogLiquidacion(FiltroLogLiquidacion pFiltro) throws TrascenderException {
		try {
			return this.businessLiquidacionTasaLocal.findListaLogLiquidacion(pFiltro);
		} catch(Exception e) {
			e.printStackTrace();
			throw new SaicException(324);
		}
	}
}

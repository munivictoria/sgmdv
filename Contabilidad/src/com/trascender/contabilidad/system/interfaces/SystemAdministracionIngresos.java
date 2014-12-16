/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.contabilidad.system.interfaces;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.filtros.FiltroIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.HistoricoReimpresionTicket;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCancelado;
import com.trascender.contabilidad.recurso.transients.ResumenActualCajaDataSource;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.saic.recurso.interfaces.Pagable;

/**
 * Remote interface for SystemAdministracionIngresos.
 */

@Remote
public interface SystemAdministracionIngresos
{

	public static final String JNDI_NAME = "ejb/SystemAdministracionIngresos/remote";
	
   public void setSystemPlanillaDiaria( com.trascender.contabilidad.system.interfaces.SystemAdministracionPlanillaDiaria pSystemAdministracionPlanillaDiaria )
      throws java.rmi.RemoteException;

   public void setLlave( long llave )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCaja
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.Caja addCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pNombre
    * @param pEstado
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaCaja( java.lang.String pNombre,com.trascender.contabilidad.recurso.persistent.Caja.Estado pEstado )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCaja
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.Caja updateCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCaja
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIdCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja findCajaByID( java.lang.Long pIdCaja )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIPAddress
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja findCajaByIP( java.lang.String pIPAddress )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja restaurarCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pDetalleTicketCaja
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.TicketCaja addTicketCaja( com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIdTicketCaja
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.TicketCaja findTicketCajaByID( java.lang.Long pIdTicketCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
   
   /**
    * Business method
    * @param pIdTicketCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.TicketCaja getTicketCajaPorNumero(Integer pNumeroTicket) 
   		throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pTicketCaja
    * @return 
    * @throws java.lang.Exception    */
   public TicketCancelado getTicketCanceladoPorTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja)
   		throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pCaja
    * @param pNumero
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaTicketCaja(java.util.Date pFechaDesde, java.util.Date pFechaHasta, Integer pNumero, com.trascender.framework.recurso.persistent.Usuario pUsuario, com.trascender.contabilidad.recurso.persistent.Caja pCaja)
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
	 * Business method
	 * @param pCaja
	 * @param pUsuario
	 * @param pEstado
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findResumenCajaActual(com.trascender.contabilidad.recurso.persistent.Caja 
			pCaja, com.trascender.framework.recurso.persistent.Usuario pUsuario, com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado pEstado,
			Date pFechaDesde, Date pFechaHasta) 
				throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
				
   /**
    * Business method
    * @param pIdRegistroDeuda
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.saic.recurso.persistent.RegistroDeuda findRegistroDeudaByID( java.lang.Long pIdRegistroDeuda )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pRegistroDeuda
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.saic.recurso.persistent.Vencimiento getVencimientoPorRegistroDeuda( com.trascender.saic.recurso.persistent.RegistroDeuda pRegistroDeuda )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param Id
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.framework.recurso.persistent.Persona getPersonaPorDeuda( java.lang.Long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIngresoVario
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.IngresoVario addIngresoVario( com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIngresoVario
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.IngresoVario updateIngresoVario( com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIdIngresoVario
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.IngresoVario getIngresoVarioByID( java.lang.Long pIdIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIngresoVario
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteIngresoVario( com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pConceptoIngresoVario
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario addConceptoIngresoVario( com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pConceptoIngresoVario
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario updateConceptoIngresoVario( com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIdConceptoIngresoVario
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario getConceptoIngresoVarioByID( java.lang.Long pIdConceptoIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pConceptoIngresoVario
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteConceptoIngresoVario( com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pPersona
    * @param pConceptoSelladoAdministrativo
    * @param pFiltro
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public FiltroIngresoVario findListaIngresoVario(FiltroIngresoVario pFiltroFiltroIngresoVario)
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pFiltro
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public FiltroConceptoIngresoVario findListaConceptoIngresoVario(FiltroConceptoIngresoVario pFiltro)
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   public List<ConceptoIngresoVario> findListaConceptoIngresoVarioPorUsuario(Usuario pUsuario) throws java.lang.Exception;
   
   /**
    * Business method
    * @param pPersona
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaPagoSellado( com.trascender.framework.recurso.persistent.Persona pPersona )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pTicketCaja
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void cancelarTicketCaja( com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pId
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.saic.recurso.interfaces.Pagable getDeudaByID( java.lang.Long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
   
   /**
    * Busca un deuda en el sistema, y devuelve el detalle correspondiente.
    * @param pId
    * @return
    * @throws Exception
    */
   public List<DetalleTicketCaja> getListaDetalleByIdLiquidacion(String codigo) throws Exception, java.rmi.RemoteException;

   /**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pTicketCaja
	 * @return
	 * @throws TrascenderException
	 */
	public HistoricoReimpresionTicket reimprimirTicket(TicketCaja pTicketCaja) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="remote"
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pImporteDesde
	 * @param pImporteHasta
	 * @return
	 * @throws java.lang.Exception
	 * @throws java.rmi.RemoteException
	 */
	public java.util.List<MovimientoCajaIngreso> findListaMovimientoCajaIngreso(java.util.Date pFechaDesde, java.util.Date pFechaHasta, Double pImporteDesde, Double pImporteHasta, TicketCaja.Estado pEstado) throws
	java.lang.Exception, java.rmi.RemoteException;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pListaTicketCaja
	 * @throws Exception
	 */
	public void validarListaTicketCaja(List<TicketCaja> pListaTicketCaja) throws Exception, java.rmi.RemoteException;
	
	/**
	 * Devuelve el reporte de cobros por tasa, para el reporte de Resumen Actual de Caja.
	 * @param pIdUsuario
	 * @param pIdCaja
	 * @return
	 * @throws Exception
	 * @throws java.rmi.RemoteException
	 */
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(
			Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) throws Exception, java.rmi.RemoteException;
	
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(List<TicketCaja> pListaTickets) throws TrascenderException;
	
	public JasperPrint generarReporteCajaPorTasaDinamico(
			Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta);
	
	public void addPagosPagoFacil(File pFile) throws Exception, RemoteException;
	
	public List<MovimientoCajaIngreso> getListaMovimientosCaja(List<Pagable> pListaDeudas, boolean conIntereses) throws Exception;
}
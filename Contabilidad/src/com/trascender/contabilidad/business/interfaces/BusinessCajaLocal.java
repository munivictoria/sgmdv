/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.contabilidad.business.interfaces;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.HistoricoReimpresionTicket;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCancelado;
import com.trascender.contabilidad.recurso.transients.ResumenActualCajaDataSource;
import com.trascender.saic.recurso.interfaces.Pagable;

/**
 * Local interface for BusinessCaja.
 */
@Local
public interface BusinessCajaLocal
{
	
	public static final String JNDI_NAME = "ejb/BusinessCajaLocal";
	
	
	public void setLlave(long pLlave);
	
   /**
    * Agrega un Caja Business method
    * @param pCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja addCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja ) throws java.lang.Exception;

   /**
    * Modifica un Caja Business method
    * @param pCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja updateCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja ) throws java.lang.Exception;

   /**
    * Elimina una Caja Business method
    * @param pCaja
    * @throws java.lang.Exception    */
   public void deleteCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja ) throws java.lang.Exception;

   /**
    * Trae una lista de Cajas Business method
    * @param pNombre
    * @param pEstado
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaCaja( java.lang.String pNombre,com.trascender.contabilidad.recurso.persistent.Caja.Estado pEstado ) throws java.lang.Exception;

   /**
    * Busca una caja por el ID Business method
    * @param pIdCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja findCajaByID( java.lang.Long pIdCaja ) throws java.lang.Exception;

   /**
    * Busca una caja por su IP Business method
    * @param pIPAddress
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja findCajaByIP( java.lang.String pIPAddress ) throws java.lang.Exception;

   /**
    * Agrega un arqueo de caja. Business method
    * @param pArqueoCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ArqueoCaja addArqueoCaja( com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja ) throws java.lang.Exception;

   /**
    * Modifica un arqueo de caja Business method
    * @param pArqueoCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ArqueoCaja updateArqueoCaja( com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja ) throws java.lang.Exception;

   /**
    * Borra un arqueo de Caja Business method
    * @param pArqueoCaja
    * @throws java.lang.Exception    */
   public void deleteArqueoCaja( com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja ) throws java.lang.Exception;

   /**
    * Otiene un arqueo de caja por su ID Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ArqueoCaja findArqueoCajaByID( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Obtiene una lista de arqueos de caja Business method
    * @param pPlanillaDiariaCaja
    * @param pMoneda
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaArqueoCaja( com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja,com.trascender.contabilidad.recurso.persistent.Moneda pMoneda ) throws java.lang.Exception;

   /**
    * Business method
    * @param pMoneda
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Moneda addMoneda( com.trascender.contabilidad.recurso.persistent.Moneda pMoneda ) throws java.lang.Exception;

   /**
    * Business method
    * @param pMoneda
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Moneda updateMoneda( com.trascender.contabilidad.recurso.persistent.Moneda pMoneda ) throws java.lang.Exception;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Moneda findMonedaByID( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Business method
    * @param pNombre
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaMoneda( java.lang.String pNombre ) throws java.lang.Exception;

   /**
    * Business method
    * @param pMoneda
    * @throws java.lang.Exception    */
   public void deleteMoneda( com.trascender.contabilidad.recurso.persistent.Moneda pMoneda ) throws java.lang.Exception;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso findMovimientoCajaEgreso( long pId ) throws java.lang.Exception;

   /**
    * Business method
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pImporteDesde
    * @param pImporteHasta
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List<MovimientoCajaEgreso> findListaMovimientoCajaEgreso( java.util.Date pFechaDesde,java.util.Date pFechaHasta,java.lang.Double pImporteDesde,java.lang.Double pImporteHasta ) throws java.lang.Exception;

   /**
    * Business method
    * @param pPlanillaDiariaCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja addPlanillaDiariaCaja( com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja ) throws java.lang.Exception;

   /**
    * Business method
    * @param pPlanillaDiariaCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja updatePlanillaDiariaCaja( com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja ) throws java.lang.Exception;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja findPlanillaDiariaByID( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Business method
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pUsuario
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaPlanillaDiariaCaja( java.util.Date pFechaDesde,java.util.Date pFechaHasta,com.trascender.framework.recurso.persistent.Usuario pUsuario ) throws java.lang.Exception;

   /**
    * Realiza el cobro de un registro de deuda o un Ingreso Vario. Genera el movimiento correspondiente junto con el concepto. Business method
    * @param pTicketCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.TicketCaja addTicketCaja( com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja ) throws java.lang.Exception;

   /**
    * Obtiene un ticket de caja Business method
    * @param pIdTicketCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.TicketCaja findTicketCajaByID( java.lang.Long pIdTicketCaja ) throws java.lang.Exception;

   /**
    * Obtiene un ticket de caja por número Business method
    * @param pIdTicketCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.TicketCaja getTicketCajaPorNumero(Integer pNumeroTicket) throws java.lang.Exception;
   
   /**
    * Obtiene una lista de tickets de caja Business method
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pNombre
    * @param pUsuario
    * @param pCaja
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaTicketCaja( java.util.Date pFechaDesde,java.util.Date pFechaHasta,Integer pNumero,com.trascender.framework.recurso.persistent.Usuario pUsuario,com.trascender.contabilidad.recurso.persistent.Caja pCaja ) throws java.lang.Exception;

   /**
	 * Business method
	 * @param pCaja
	 * @param pUsuario
	 * @param pEstado
	 * @return
	 * @throws java.lang.Exception
	 */
	public java.util.List findResumenCajaActual(com.trascender.contabilidad.recurso.persistent.Caja 
			pCaja, com.trascender.framework.recurso.persistent.Usuario pUsuario, com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado pEstado,
			Date pFechaDesde, Date pFechaHasta) throws java.lang.Exception;
   
   /**
    * Cancela un ticket de Caja Business method
    * @param pTicketCaja
    * @throws java.lang.Exception    */
   public void cancelarTicketCaja( com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja,com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja ) throws java.lang.Exception;

   /**
    * Obtiene un movimiento de caja de ingreso Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso findMovimientoCajaIngresoByID( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Obtiene una lista de movimientos de caja de ingreso
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pImporte
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List<MovimientoCajaIngreso> findListaMovimientoCajaIngreso( java.util.Date pFechaDesde,java.util.Date pFechaHasta,java.lang.Double pImporteDesde,java.lang.Double pImporteHasta, TicketCaja.Estado pEstado);

   /**
    * Obtiene un detalle de ticket de caja Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja findDetalleTicketCajaByID( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Obtiene una lista de detalles de tickets de caja Business method
    * @param pTicketCaja
    * @param pImporte
    * @param pNumeroLinea
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaDetalleTicketCaja( com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja,java.lang.Double pImporte,java.lang.Long pNumeroLinea ) throws java.lang.Exception;

   /**
    * Restaura una caja al estado Activo Business method
    * @param pCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.Caja restaurarCaja( com.trascender.contabilidad.recurso.persistent.Caja pCaja ) throws java.lang.Exception;

   /**
    * Obtiene la planilla diara de caja del dia Business method
    * @param pUsuario
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja getPlanillaDiariaDelDia( com.trascender.framework.recurso.persistent.Usuario pUsuario ) throws java.lang.Exception;

   /**
    * Busca una deuda en el sistema. Si empieza con un 1 se busca un Registro de Deuda, si empieza con un 2 se busca un IngresoVario Business method
    * @param pId
    * @return 
    * @throws Exception    */
   public com.trascender.saic.recurso.interfaces.Pagable getDeudaByID( java.lang.Long pId ) throws java.lang.Exception;
   
   /**
    * Busca una deuda en el sistema, y arma el detalle correspondiente.
    * @param pId
    * @return
    * @throws Exception
    */
   public List<DetalleTicketCaja> getListaDetalleByIdLiquidacion(String codigo) throws Exception;

   /**
    * Obtiene la persona que esta relacionada con un registro de deuda Business method
    * @param pID
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.framework.recurso.persistent.Persona getPersonaPorDeuda( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Obtiene la el TicketCancelado a partir del TicketCaja Business method
    * @param pTicketCaja
    * @return 
    * @throws java.lang.Exception    */
   public TicketCancelado getTicketCanceladoPorTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja) throws Exception;
   
   /**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pHistoricoReimpresionTicket
	 * @return
	 * @throws Exception
	 */
	public HistoricoReimpresionTicket addHistoricoReimpresionTicket(HistoricoReimpresionTicket pHistoricoReimpresionTicket) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pListaTicketCaja
	 * @throws Exception
	 */
	public void validarListaTicketCaja(List<TicketCaja> pListaTicketCaja) throws Exception;
	
	/**
	 * Devuelve un cobro y vuelve atrás las liquidaciones
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pTicketCaja
	 * @throws Exception
	 */
	public TicketCaja devolverTicketCaja(TicketCaja pTicketCaja) throws Exception;
	
	/**
	 * Devuelve el reporte de cobros por Tasa, para el resumen actual de caja.
	 * @param pIdUsuario
	 * @param pIdCaja
	 * @return
	 * @throws Exception
	 */
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) throws Exception;
	
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(List<TicketCaja> pListaTickets);
	
	public void addPagosPagoFacil(File pFile);
	
	public JasperPrint generarReporteCajaPorTasaDinamico(
			Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta);
	
	public List<MovimientoCajaIngreso> getListaMovimientosCaja(List<Pagable> pListaDeudas, boolean conIntereses) throws Exception;
}

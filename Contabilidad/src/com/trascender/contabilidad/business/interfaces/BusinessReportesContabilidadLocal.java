package com.trascender.contabilidad.business.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.reporte.interfaz.InterfazModuloContable;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

@Local
public interface BusinessReportesContabilidadLocal {

	public final static String JNDI_NAME = "ejb/BusinessReportesContabilidad";
	
	public InterfazModuloContable generarInterfazModuloContable();
	
	public JasperPrint getReporteIngresoVario(long idIngresoVario, Usuario pUsuario) throws Exception;
	
	public JasperPrint getReporteBalanceContable(long idBalanceContable) throws Exception;
	
	public JasperPrint getReporteCheque(long idCheque) throws Exception;
	
	public JasperPrint getReporteOrdenPago(long idOrdenPago) throws Exception;
	
	public JasperPrint getReporteOrdenPagoDevolucion(long idOrdenPagoDevolucion) throws Exception;
	
	public JasperPrint getReportePlanDeCuenta(long idOrdenPagoDevolucion) throws Exception;
	
	public JasperPrint getReporteLibroDiario(long idLibroDiario) throws Exception;
	
	public JasperPrint getReporteLibroBanco(long idLibroDiario) throws Exception;
	
	public JasperPrint getReporteMayor(long idLibroDiario) throws Exception;
	
	public JasperPrint getReportePresupuesto(long idPresupuesto) throws Exception;
	
	public JasperPrint getReportePlanillaDiariaCaja(List<TicketCaja> pListaTickets) throws Exception;
	
	public JasperPrint getReporteLiquidacionDeuda(List<LiquidacionTasaAgrupada> pListaLiquidacion) throws Exception;
	
	public JasperPrint getReporteMovimientosEgreso(Date fechaDesde, Date fechaHasta) 
			throws Exception;
	
	public JasperPrint getReporteMovimientosIngreso(Date fechaDesde, Date fechaHasta) 
			throws Exception;
}

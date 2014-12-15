package com.trascender.contabilidad.system.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.recurso.filtros.FiltroReporteContable;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.reporte.interfaz.InterfazModuloContable;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

@Remote
public interface SystemReportesContabilidad {
	
	public static final String JNDI_NAME = "ejb/SystemReportesContabilidad/remote";
	
	public void setLlave(long pLlave) throws Exception;
	
	public JasperPrint getReporteIngresoVario(long idIngresoVario) throws Exception;
	
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

	public InterfazModuloContable generarInterfazModuloContable(); 

	public JasperPrint getReporteLiquidacionDeuda(List<LiquidacionTasaAgrupada> pListaLiquidacion) throws Exception;	
	
	public void addReporteContable(ReporteContable pReporteContable) throws Exception;

    public ReporteContable updateReporteContable(ReporteContable pReporteContable) throws Exception;
    
    public void deleteReporteContable(ReporteContable pReporteContable) throws Exception;
    
    public ReporteContable getReporteContableByID(Long pIdReporteContable) throws Exception;
    
    public FiltroReporteContable findListaReporteContable(FiltroReporteContable pFiltro) throws Exception;
    
    public List<ReporteContable> getListaMenuReporteContable(Usuario pUsuarioLogueado);
    
    public JasperPrint getReporteContable(ReporteContable pReporteContable, Map<String, Object> pMapaParametros) throws Exception;
}

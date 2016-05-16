package com.trascender.contabilidad.system.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.business.interfaces.BusinessReportesContabilidadLocal;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.reporte.interfaz.InterfazModuloContable;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

@Stateful(name = "ejb/SystemReportesContabilidad")
public class SystemReportesContabilidadBean implements SystemReportesContabilidad{
	
	private long llave = 0;
	
	@EJB
	private BusinessReportesContabilidadLocal businessReportesContabilidad;
	
	public void setLlave(long pLlave) throws Exception{
		this.llave = pLlave;
	}
	
	@Override
	public JasperPrint getReporteIngresoVario(long idIngresoVario) throws Exception {
		return businessReportesContabilidad.getReporteIngresoVario(idIngresoVario, this.getUsuario());
	}
	
	@Override
	public JasperPrint getReporteBalanceContable(long idBalanceContable) throws Exception {
		return businessReportesContabilidad.getReporteBalanceContable(idBalanceContable);
	}
	
	@Override
	public JasperPrint getReporteCheque(long idCheque) throws Exception {
		return businessReportesContabilidad.getReporteCheque(idCheque);
	}
	
	@Override
	public JasperPrint getReporteOrdenPago(long idOrdenPago) throws Exception {
		return businessReportesContabilidad.getReporteOrdenPago(idOrdenPago);
	}
	
	@Override
	public JasperPrint getReporteOrdenPagoDevolucion(long idOrdenPagoDev) throws Exception {
		return businessReportesContabilidad.getReporteOrdenPagoDevolucion(idOrdenPagoDev);
	}
	
	@Override
	public JasperPrint getReportePlanDeCuenta(long idPlanDeCuenta) throws Exception {
		return businessReportesContabilidad.getReportePlanDeCuenta(idPlanDeCuenta);
	}
	
	@Override
	public JasperPrint getReporteLibroDiario(long idLibroDiario) throws Exception {
		return businessReportesContabilidad.getReporteLibroDiario(idLibroDiario);
	}
	
	@Override
	public JasperPrint getReporteMayor(long idMayor) throws Exception {
		return businessReportesContabilidad.getReporteMayor(idMayor);
	}
	
	@Override
	public JasperPrint getReporteLibroBanco(long idLibroBanco) throws Exception {
		return businessReportesContabilidad.getReporteLibroBanco(idLibroBanco);
	}
	
	@Override
	public JasperPrint getReportePresupuesto(long idPresupuesto) throws Exception {
		return businessReportesContabilidad.getReportePresupuesto(idPresupuesto);
	}
		
	@Override
	public JasperPrint getReportePlanillaDiariaCaja(List<TicketCaja> pListaTickets) throws Exception {
		return businessReportesContabilidad.getReportePlanillaDiariaCaja(pListaTickets);
	}
	
	private Usuario getUsuario() throws TrascenderException{
		return SecurityMgr.getInstance().getUsuario(llave);
	}

	@Override
	public InterfazModuloContable generarInterfazModuloContable() {
		return businessReportesContabilidad.generarInterfazModuloContable();
	}
	
	@Override
	public JasperPrint getReporteLiquidacionDeuda(List<LiquidacionTasaAgrupada> pListaLiquidacion) throws Exception {
		return businessReportesContabilidad.getReporteLiquidacionDeuda(pListaLiquidacion);
	}

	@Override
	public JasperPrint getReporteMovimientosEgreso(Date fechaDesde,
			Date fechaHasta) throws Exception {
		return businessReportesContabilidad.getReporteMovimientosEgreso(fechaDesde, fechaHasta);
	}

	@Override
	public JasperPrint getReporteMovimientosIngreso(Date fechaDesde,
			Date fechaHasta) throws Exception {
		return businessReportesContabilidad.getReporteMovimientosIngreso(fechaDesde, fechaHasta);
	}

}

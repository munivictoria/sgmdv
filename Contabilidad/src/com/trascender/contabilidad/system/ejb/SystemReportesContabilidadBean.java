package com.trascender.contabilidad.system.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.business.interfaces.BusinessReportesContabilidadLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.filtros.FiltroReporteContable;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.reporte.interfaz.InterfazModuloContable;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
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
	public void addReporteContable(ReporteContable pReporteContable) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ReporteContable.serialVersionUID, Permiso.Accion.INSERT))
			{
				this.businessReportesContabilidad.addReporteContable(pReporteContable);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(780);
		}
	}

	@Override
	public ReporteContable updateReporteContable(ReporteContable pReporteContable) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ReporteContable.serialVersionUID, Permiso.Accion.UPDATE))
			{
				return this.businessReportesContabilidad.updateReporteContable(pReporteContable);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(781);
		}
	}

	@Override
	public void deleteReporteContable(ReporteContable pReporteContable) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ReporteContable.serialVersionUID, Permiso.Accion.DELETE))
			{
				this.businessReportesContabilidad.deleteReporteContable(pReporteContable);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(783);
		}
	}

	@Override
	public ReporteContable getReporteContableByID(Long pIdReporteContable) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ReporteContable.serialVersionUID, Permiso.Accion.SELECT))
			{
				return this.businessReportesContabilidad.getReporteContableByID(pIdReporteContable);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(784);
		}
	}

	@Override
	public FiltroReporteContable findListaReporteContable(FiltroReporteContable pFiltro) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ReporteContable.serialVersionUID, Permiso.Accion.SELECT))
			{
				return this.businessReportesContabilidad.findListaReporteContable(pFiltro);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(782);
		}
	}

	public List<ReporteContable> getListaMenuReporteContable(Usuario pUsuarioLogueado){
		return this.businessReportesContabilidad.getListaMenuReporteContable(pUsuarioLogueado);
	}
	
	public JasperPrint getReporteContable(ReporteContable pReporteContable, Map<String, Object> pMapaParametros) throws Exception{
		return this.businessReportesContabilidad.getReporteContable(pReporteContable, pMapaParametros);
	}
}


package com.trascender.contabilidad.business.ejb;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.contabilidad.business.interfaces.BusinessBancoLocal;
import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.business.interfaces.BusinessIngresoVarioLocal;
import com.trascender.contabilidad.business.interfaces.BusinessLibroDiarioLocal;
import com.trascender.contabilidad.business.interfaces.BusinessPlanDeCuentaLocal;
import com.trascender.contabilidad.business.interfaces.BusinessPresupuestoLocal;
import com.trascender.contabilidad.business.interfaces.BusinessReportesContabilidadLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.reporte.dataSource.BalanceContableDS;
import com.trascender.contabilidad.reporte.dataSource.ChequeDS;
import com.trascender.contabilidad.reporte.dataSource.ChequeDiferidoDS;
import com.trascender.contabilidad.reporte.dataSource.IngresoVarioDS;
import com.trascender.contabilidad.reporte.dataSource.LibroBancoDS;
import com.trascender.contabilidad.reporte.dataSource.LibroDiarioDS;
import com.trascender.contabilidad.reporte.dataSource.LiquidacionDeudaDS;
import com.trascender.contabilidad.reporte.dataSource.MayorDS;
import com.trascender.contabilidad.reporte.dataSource.OrdenPagoDS;
import com.trascender.contabilidad.reporte.dataSource.OrdenPagoDevolucionDS;
import com.trascender.contabilidad.reporte.dataSource.PlanDeCuentaDS;
import com.trascender.contabilidad.reporte.dataSource.PlanillaDiariaCajaDS;
import com.trascender.contabilidad.reporte.dataSource.PlanillaDiariaCajaIngresoVarioDS;
import com.trascender.contabilidad.reporte.dataSource.PlanillaDiariaCajaTasaDS;
import com.trascender.contabilidad.reporte.dataSource.PresupuestoGastosDS;
import com.trascender.contabilidad.reporte.dataSource.PresupuestoRecursosDS;
import com.trascender.contabilidad.reporte.interfaz.InterfazModuloContable;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

@Stateless(name = "ejb/BusinessReportesContabilidad")
public class BusinessReportesContabilidadBean implements BusinessReportesContabilidadLocal {

	private static final long serialVersionUID = 8867049974303192409L;
	private static final String NOMBRE = "CUE|Adm Reporte Contable";

	@PersistenceContext
	private EntityManager entity;

	@EJB
	private BusinessIngresoVarioLocal businessIngresoVario;
	@EJB
	private BusinessPlanDeCuentaLocal businessPlanDeCuenta;
	@EJB
	private BusinessBancoLocal businessBanco;
	@EJB
	private BusinessLibroDiarioLocal businessLibroDiario;
	@EJB
	private BusinessPresupuestoLocal businessPresupuesto;
	@EJB
	private BusinessCajaLocal businessCaja;
	@Resource(mappedName = "java:/vipiansDS", shareable = true)
	private DataSource datasource;

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessReportesContabilidadBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessReportesContabilidadBean.NOMBRE);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	private String parametroTitulo;
	private String parametroSubtitulo;

	public InterfazModuloContable generarInterfazModuloContable() {
		List<MovimientoCajaIngreso> locListaMovimientos = businessCaja.findListaMovimientoCajaIngreso(new Date(), new Date(), null, null, null);
		InterfazModuloContable locInterfazModuloContable = new InterfazModuloContable(locListaMovimientos);
		return locInterfazModuloContable;
	}

	@Override
	public JasperPrint getReporteIngresoVario(long idIngresoVario, Usuario pUsuario) throws Exception {
		try {
			IngresoVario locIngreso = this.businessIngresoVario.getIngresoVarioByID(idIngresoVario);
			IngresoVarioDS ds = new IngresoVarioDS(locIngreso, pUsuario);
			String ruta = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
			File locFile = new File(ruta + ds.getNombreReporte());
			JasperReport JR = (JasperReport) JRLoader.loadObject(locFile);
			JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(JR, ds.getMapaParametros(), ds);
			return jasperPrint;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderContabilidadException(952);
		}
	}

	@Override
	public JasperPrint getReporteBalanceContable(long idBalanceContable) throws Exception {
		Balance balance = businessPlanDeCuenta.getBalanceByID(idBalanceContable);
		BalanceContableDS ds = new BalanceContableDS(balance);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReporteCheque(long idCheque) throws Exception {
		Cheque locCheque = this.businessBanco.getChequeByID(idCheque);
		JasperPrint locJasperPrint = null;
		if(locCheque.isPostdatado()) {
			ChequeDiferidoDS ds = new ChequeDiferidoDS(locCheque);
			locJasperPrint = getJasperPrint(ds);
		} else {
			ChequeDS ds = new ChequeDS(locCheque);
			locJasperPrint = getJasperPrint(ds);
		}

		return locJasperPrint;
	}

	@Override
	public JasperPrint getReporteOrdenPago(long idOrdenPago) throws Exception {
		OrdenPago ordenPago = businessBanco.getOrdenPagoByID(idOrdenPago);
		System.out.println("+++++++++ ORDEN DE PAGO: " + ordenPago);
		OrdenPagoDS ds = new OrdenPagoDS(ordenPago);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReporteOrdenPagoDevolucion(long idOrdenPagoDev) throws Exception {
		OrdenPagoDevolucion ordenPagoDevolucion = businessBanco.getOrdenPagoDevolucionByID(idOrdenPagoDev);
		OrdenPagoDevolucionDS ds = new OrdenPagoDevolucionDS(ordenPagoDevolucion);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReportePlanDeCuenta(long idPlanDeCuenta) throws Exception {
		PlanDeCuenta planDeCuenta = businessPlanDeCuenta.getPlanDeCuentaByID(idPlanDeCuenta);
		PlanDeCuentaDS ds = new PlanDeCuentaDS(planDeCuenta);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReporteLibroDiario(long idLibroDiario) throws Exception {
		LibroDiario libroDiario = businessLibroDiario.getLibroDiarioByID(idLibroDiario);
		LibroDiarioDS ds = new LibroDiarioDS(libroDiario);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReporteMayor(long idMayor) throws Exception {
		Mayor mayor = businessLibroDiario.getMayorByID(idMayor);
		MayorDS ds = new MayorDS(mayor);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReporteLibroBanco(long idLibroBanco) throws Exception {
		LibroBanco libroBanco = businessBanco.getLibroBancoById(idLibroBanco);
		LibroBancoDS ds = new LibroBancoDS(libroBanco);
		return getJasperPrint(ds);
	}

	@Override
	public JasperPrint getReportePresupuesto(long idPresupuesto) throws Exception {
		Presupuesto presupuesto = businessPresupuesto.getPresupuestoByID(idPresupuesto);
		JasperPrint locJasperPrint = null;

		if(presupuesto.getTipo().equals(Presupuesto.Tipo.GASTOS)) {
			PresupuestoGastosDS ds = new PresupuestoGastosDS(presupuesto);
			locJasperPrint = this.getJasperPrint(ds);
		} else if(presupuesto.getTipo().equals(Presupuesto.Tipo.RECURSOS)) {
			PresupuestoRecursosDS ds = new PresupuestoRecursosDS(presupuesto);
			locJasperPrint = getJasperPrint(ds);
		}

		return locJasperPrint;
	}

	@Override
	public JasperPrint getReportePlanillaDiariaCaja(List<TicketCaja> pListaTickets) throws Exception {

		URL urlMaestro = null;
		JasperReport jr = null;
		JasperPrint jasperPrint = null;

		PlanillaDiariaCajaDS dsPlanillaCaja = new PlanillaDiariaCajaDS(pListaTickets);
		urlMaestro = this.getClass().getResource("/com/trascender/contabilidad/reporte/compilado/" + dsPlanillaCaja.getNombreReporte());
		jr = (JasperReport) JRLoader.loadObject(urlMaestro);
		jasperPrint = JasperFillManager.fillReport(jr, dsPlanillaCaja.getMapaParametros(), dsPlanillaCaja);

		PlanillaDiariaCajaTasaDS dsPlanillaCajaTasa = new PlanillaDiariaCajaTasaDS(pListaTickets);
		urlMaestro = this.getClass().getResource("/com/trascender/contabilidad/reporte/compilado/" + dsPlanillaCajaTasa.getNombreReporte());
		jr = (JasperReport) JRLoader.loadObject(urlMaestro);
		jasperPrint = JasperFillManager.fillReport(jr, dsPlanillaCajaTasa.getMapaParametros(), dsPlanillaCajaTasa);

		PlanillaDiariaCajaIngresoVarioDS dsPlanillaCajaIngresoVario = new PlanillaDiariaCajaIngresoVarioDS(pListaTickets);
		urlMaestro = this.getClass().getResource("/com/trascender/contabilidad/reporte/compilado/" + dsPlanillaCajaIngresoVario.getNombreReporte());
		jr = (JasperReport) JRLoader.loadObject(urlMaestro);
		jasperPrint = JasperFillManager.fillReport(jr, dsPlanillaCajaIngresoVario.getMapaParametros(), dsPlanillaCajaIngresoVario);

		return jasperPrint;
	}

	private JasperPrint getJasperPrint(TrascenderDataSource pTrascenderDataSource) throws Exception {
		File locFile = new File(SecurityMgr.getInstance().getMunicipalidad().getRutaReportes() + pTrascenderDataSource.getNombreReporte());
		JasperReport jr = (JasperReport) JRLoader.loadObject(locFile);
		jr.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jr, pTrascenderDataSource.getMapaParametros(), pTrascenderDataSource);
		return jasperPrint;
	}

	@Override
	public JasperPrint getReporteLiquidacionDeuda(List<LiquidacionTasaAgrupada> pListaLiquidacion) throws Exception {
		String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
		JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(rutaReportes + "Reporte_Liquidacion_Deuda.jasper"));
		reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		for(LiquidacionTasaAgrupada cadaAgrupada : pListaLiquidacion) {
			for(int i = 0; i < cadaAgrupada.getListaLiquidacionesTasa().size(); i++) {
				LiquidacionTasa cadaLiquidacion = cadaAgrupada.getListaLiquidacionesTasa().get(i);
				cadaAgrupada.getListaLiquidacionesTasa().set(i, entity.find(LiquidacionTasa.class, cadaLiquidacion.getIdRegistroDeuda()));
			}
		}
		TrascenderDataSource ds = new LiquidacionDeudaDS(pListaLiquidacion, businessCaja, this.getTituloReporte(), this.getSubtituloReporte());
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, ds.getMapaParametros(), ds);
		return jasperPrint;
	}

	private byte[] getLogoMunicipalidad() {
		return SecurityMgr.getInstance().getMunicipalidad().getLogo();
	}

	private String getTituloReporte() {
		if(this.parametroTitulo == null) {
			try {
				this.parametroTitulo = SecurityMgr.getInstance().getMunicipalidad().getEncabezadoReportes();
			} catch(Exception e) {
				this.parametroTitulo = null;
			}
		}
		return parametroTitulo;
	}

	private String getSubtituloReporte() {
		if(this.parametroSubtitulo == null) {
			try {
				this.parametroSubtitulo = SecurityMgr.getInstance().getMunicipalidad().getSubencabezadoReportes();
			} catch(Exception e) {
				this.parametroSubtitulo = null;
			}
		}
		return parametroSubtitulo;
	}

	private void setParametrosMunicipalidad(Map<String, Object> pMapaParametros) {
		pMapaParametros.put("PAR_TITULO", SecurityMgr.getInstance().getMunicipalidad().getEncabezadoReportes());
		pMapaParametros.put("PAR_SUBTITULO", SecurityMgr.getInstance().getMunicipalidad().getSubencabezadoReportes());
		pMapaParametros.put("PAR_IMAGEN", new ImageIcon(SecurityMgr.getInstance().getMunicipalidad().getLogo()).getImage());
	}
}

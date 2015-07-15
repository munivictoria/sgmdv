package com.trascender.contabilidad.gui.main;

import java.awt.Component;

import javax.swing.UIManager;

import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.gui.principal.MainContabilidad;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class ContabilidadGUI extends AppManager {
	
	private AdministracionSystemsContabilidad adminSystemsContabilidad;
	private AdministracionSystemsCompras adminSystemsCompras;
	private AdministracionSystemsCaja adminSystemsCaja;
	private AdministracionSystemsSAIC adminSystemsLiquidacionTasa;
	
	private static final ContabilidadGUI instance = new ContabilidadGUI();
	
	private MainContabilidad mainController = null;
	
	public static ContabilidadGUI getInstance() {
		return instance;
	}
	
	private ContabilidadGUI() {
		super();
		try {
			UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
			this.mainController = new MainContabilidad();
			this.adminSystemsContabilidad = new AdministracionSystemsContabilidad();
			this.adminSystemsCompras = new AdministracionSystemsCompras();
			this.adminSystemsCaja = new AdministracionSystemsCaja();
			this.adminSystemsLiquidacionTasa = new AdministracionSystemsSAIC();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			MainContabilidad main = ContabilidadGUI.getInstance().getMainController();
	        
			boolean habilitar = false;
			for (int i = 0; i < main.getView().getMenuAdministracionContable().getItemCount(); i++) {
				Component c = main.getView().getMenuAdministracionContable().getItem(i); 
				if (c != null) c.setEnabled(habilitar);
			}
			for (int i = 0; i < main.getView().getMenuAdministracionBancaria().getItemCount(); i++) {
				Component c = main.getView().getMenuAdministracionBancaria().getItem(i); 
				if (c != null) c.setEnabled(habilitar);
			}
			for (int i = 0; i < main.getView().getMenuAdministracionTesoreria().getItemCount(); i++) {
				Component c = main.getView().getMenuAdministracionTesoreria().getItem(i);
				if (c != null) c.setEnabled(habilitar);
			}
				
			main.open();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void habilitarMenuesSegunPermisos() throws Exception {
		//if (AppManager.getInstance().getUsuario() != null) {
			Permiso permiso = null;
			boolean habilitar = false;
			
			// Plan de Cuentas
			permiso = AppManager.getInstance().getPermiso(PlanDeCuenta.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminPlanDeCuenta().setEnabled(habilitar);
			
			// Plantilla de Balance
			permiso = AppManager.getInstance().getPermiso(TipoBalance.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminTipoBalance().setEnabled(habilitar);
			
			// Tipo de Cuenta
			permiso = AppManager.getInstance().getPermiso(TipoCuenta.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminTipoCuenta().setEnabled(habilitar);
			
			// Generar Balance
			permiso = AppManager.getInstance().getPermiso(Balance.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiGenerarBalances().setEnabled(habilitar);
			
			// Generar Mayor
			permiso = AppManager.getInstance().getPermiso(Mayor.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiGenerarMayores().setEnabled(habilitar);
			
			// Generar Subdiario Caja
			permiso = AppManager.getInstance().getPermiso(SubdiarioCaja.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiGenerarSubdiarioCaja().setEnabled(habilitar);
			
			
			
			// Libro Diario
			permiso = AppManager.getInstance().getPermiso(LibroDiario.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminLibroDiario().setEnabled(habilitar);
			
			// Presupuesto
			permiso = AppManager.getInstance().getPermiso(Presupuesto.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminPresupuesto().setEnabled(habilitar);
			
			// Asiento Contable
			permiso = AppManager.getInstance().getPermiso(AsientoContable.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsientoContable().setEnabled(habilitar);
			
			
			
			// Asociacion Cuenta-Formula
			permiso = AppManager.getInstance().getPermiso(CuentaTipoTasa.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaTipoTasa().setEnabled(habilitar);
			
			// Asociacion Cuenta-Modificador
			permiso = AppManager.getInstance().getPermiso(CuentaModificador.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaModificador().setEnabled(habilitar);
			
			permiso = AppManager.getInstance().getPermiso(CuentaInteresRecargo.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaInteresRecargo().setEnabled(habilitar);
			
//			 Asociacion Cuenta-Refinanciacion
			permiso = AppManager.getInstance().getPermiso(DocumentoRefinanciacion.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaRefinanciacion().setEnabled(habilitar);
			
			// Asociacion Cuenta-SolicitudSuministroa
			permiso = AppManager.getInstance().getPermiso(SolicitudSuministro.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaSolicitudSuministro().setEnabled(habilitar);
			
			// Asociacion Cuenta-Concepto Ingreso Vario
			permiso = AppManager.getInstance().getPermiso(CuentaConceptoIngresoVario.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaConceptoIngresoVario().setEnabled(habilitar);
			
			// Asociacion Cuenta-Linea de Factura
			permiso = AppManager.getInstance().getPermiso(CuentaLineaFactura.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaLineaFactura().setEnabled(habilitar);
			
			// Asociacion Cuenta-Articulo
			permiso = AppManager.getInstance().getPermiso(CuentaArticulo.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminAsociacionCuentaArticulo().setEnabled(habilitar);
			
			// Banco
			permiso = AppManager.getInstance().getPermiso(Banco.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminBanco().setEnabled(habilitar);
			
			// CuentaBancaria
			permiso = AppManager.getInstance().getPermiso(CuentaBancaria.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminCuentaBancaria().setEnabled(habilitar);
		
			// LibroBanco
			permiso = AppManager.getInstance().getPermiso(LibroBanco.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminLibroBanco().setEnabled(habilitar);

			// Cheque
			permiso = AppManager.getInstance().getPermiso(Cheque.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminCheque().setEnabled(habilitar);

			// DebitoBancario
			permiso = AppManager.getInstance().getPermiso(Debito.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminDebitoBancario().setEnabled(habilitar);

			// BoletaDeposito
			permiso = AppManager.getInstance().getPermiso(BoletaDeposito.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminBoletaDeposito().setEnabled(habilitar);

			// ComprobanteRetencion
			permiso = AppManager.getInstance().getPermiso(ComprobanteRetencion.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminComprobanteRetencion().setEnabled(habilitar);

			// OrdenPago
			permiso = AppManager.getInstance().getPermiso(OrdenPago.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminOrdenPago().setEnabled(habilitar);
			
			// OrdenPagoDevolucion
			permiso = AppManager.getInstance().getPermiso(OrdenPagoDevolucion.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminDevolucion().setEnabled(habilitar);
			
			// MovimientoCajaEgreso
			permiso = AppManager.getInstance().getPermiso(MovimientoCajaEgreso.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionTesoreria().getMiAdminEgresoTesoreria().setEnabled(habilitar);
			
			// MovimientoCajaIngreso
			permiso = AppManager.getInstance().getPermiso(MovimientoCajaIngreso.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionTesoreria().getMiAdminIngresoTesoreria().setEnabled(habilitar);
			
			// Importar Plan de Cuentas
			permiso = AppManager.getInstance().getPermiso(PlanDeCuenta.serialVersionUID);
			habilitar = (permiso != null && permiso.isSelect());
			this.getMainController().getView().getMenuAdministracionContable().getMiAdminImportarPlanDeCuenta().setEnabled(habilitar);
			
//			permiso = AppManager.getInstance().getPermiso(ReporteContable.serialVersionUID);
//			habilitar = (permiso != null && permiso.isSelect());
//			this.getMainController().getView().getMenuReportes().getMiReporteContable().setEnabled(habilitar);
//			
//			try{
//				List<ReporteContable> locListaReportes = this.getAdminSystemsContabilidad().getSystemReportesContabilidad().getListaMenuReporteContable(AppManager.getInstance().getUsuario());
//				this.getMainController().getView().getMenuReportes().armarItemsMenuReportes(locListaReportes);
//			} catch (TrascenderException e) {
//				e.printStackTrace();
//			}
			
			// Resumen Caja
//			permiso = AppManager.getInstance().getPermiso(Caja.serialVersionUID);
//			habilitar = (permiso != null && permiso.isSelect());
//			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminResumenCajas().setEnabled(habilitar);
		//}
			
//			// Retencion
//			permiso = AppManager.getInstance().getPermiso(ComprobanteRetencion.serialVersionUID);
//			habilitar = (permiso != null && permiso.isSelect());
//			this.getMainController().getView().getMenuAdministracionBancaria().getMiAdminRetencion().setEnabled(habilitar);
	}
	
	public AdministracionSystemsContabilidad getAdminSystemsContabilidad() {
		return adminSystemsContabilidad;
	}
	
	public AdministracionSystemsCompras getAdminSystemsCompras() {
		return adminSystemsCompras;
	}

	@Override
	public MainContabilidad getMainController() {
		return mainController;
	}

	public AdministracionSystemsCaja getAdminSystemsCaja() {
		return adminSystemsCaja;
	}

	public AdministracionSystemsSAIC getAdminSystemsLiquidacionTasa() {
		return adminSystemsLiquidacionTasa;
	}
	
}

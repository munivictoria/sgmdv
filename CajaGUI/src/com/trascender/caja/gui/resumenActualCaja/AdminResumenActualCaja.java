
package com.trascender.caja.gui.resumenActualCaja;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImageRenderer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.caja.gui.enumerations.EstadoTicket;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado;
import com.trascender.contabilidad.recurso.transients.ResumenActualCajaDataSource;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class AdminResumenActualCaja extends AdminController<TicketCaja> {
	
	private AdminResumenActualCajaView view;
	private ResumenActualCajaBusquedaModel busquedaModel = new ResumenActualCajaBusquedaModel();
	private ResumenActualCajaTableModel tableModel;
	
//	private enum Obligacion {
//		TGI, OYSP, PLAN_FINANCIACION_OBRA, SHPS, SELLADO;
//		
//		public static String MostrarDatos(String cadena){
//			String s = "";
//			if (cadena =="TGI") {
//				s = "TGI - Tasa General Inmobiliaria";
//			}
//			if (cadena =="OYSP") {
//				s =  "OYSP - Obras y Servicios Públicos";
//			}
//			if (cadena =="PLAN_FINANCIACION_OBRA") {
//				s =  "PFO - Plan Financiación Obra";
//			}
//			if (cadena =="SHPS") {
//				s =  "SHPS - Salud, Higiene y Profilaxis";
//			}
//			if (cadena =="SELLADO") {
//				s =  "Sellado Administrativo";
//			}
//			return s;
//		}
//		
//		@Override
//		public String toString() {
//			return Obligacion.MostrarDatos(super.toString());
//			
//		}
//	}
	
	
	
	public AdminResumenActualCaja(JDialog owner) {
		try {
			this.tableModel = new ResumenActualCajaTableModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.view = new AdminResumenActualCajaView(owner);
		this.init();
	}
	
	public AdminResumenActualCaja(JFrame owner) {
		try {
			this.tableModel = new ResumenActualCajaTableModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.view = new AdminResumenActualCajaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();

		this.getView().getTfCaja().setText(AppManager.getInstance().getUsuario().getNombrePersonaFisica()
				+ " [" 
				+ AppManager.getInstance().getUsuario().toString() 
				+ "]");
		this.getView().getBusquedaModel().reiniciar();//Para que ponga las fechas actuales.
		this.actualizarBusquedaView();
		this.setPropiedadesBotones();
		this.setTextoBtnConsultar();
	}

	private void setPropiedadesBotones() {
		this.getView().getPnlPie().getBtnConsultar().setVisible(true);
		this.getView().getPnlPie().getBtnConsultar().setEnabled(false);
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		this.getView().getTfCaja().setEditable(false);
	}
		
	private void setModels() {
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values()));
//		this.getView().getCbObligacion().setModel(new TDefaultComboBoxModel(Obligacion.values()));
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnImprimirListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ResumenActualCajaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setCaja(CajaGUI.getInstance().getCaja());
		locModel.setUsuario(AppManager.getInstance().getUsuario());

		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		
		if (locEstado != null) {
			if (locEstado.equals(Estado.ACTIVO)){//EstadoTicket.COBROS
				locModel.setEstadoTicket(EstadoTicket.COBROS); 
			} 
			if (locEstado.equals(Estado.CANCELADO) || locEstado.equals(Estado.DEVUELTO)){//EstadoTicket.ANULACIONES
					locModel.setEstadoTicket(EstadoTicket.ANULACIONES); 
			}
			 
		}else locModel.setEstadoTicket(EstadoTicket.TODOS);
		
		String stringFechaDesde = this.getView().getFtfFechaDesde().getText();
		String stringFechaHasta = this.getView().getFtfFechaHasta().getText();
		String stringHoraDesde = this.getView().getFtfHoraDesde().getText();
		String stringHoraHasta = this.getView().getFtfHoraHasta().getText();
		if (stringHoraDesde.trim().equals(":")) stringHoraDesde = "00:01";
		if (stringHoraHasta.trim().equals(":")) stringHoraHasta = "23:59";
		Date fechaDesde = Conversor.getDateConHora(stringFechaDesde, stringHoraDesde);
		Date fechaHasta = Conversor.getDateConHora(stringFechaHasta, stringHoraHasta);
		
		locModel.setFechaHoraDesde(fechaDesde);
		locModel.setFechaHoraHasta(fechaHasta);
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstadoTicket());
		this.getView().getPnlResultadoCantidadTickets().getLblNumero().setText("0");
		this.getView().getPnlResultadoMontoCobrado().getLblNumero().setText("0,00");
		this.getView().getFtfFechaDesde().setText(Conversor.getString(this.getBusquedaModel().getFechaHoraDesde()));
		this.getView().getFtfFechaHasta().setText(Conversor.getString(this.getBusquedaModel().getFechaHoraHasta()));
		this.getView().getFtfHoraDesde().setText(Conversor.getStringHora(this.getBusquedaModel().getFechaHoraDesde()));
		this.getView().getFtfHoraHasta().setText(Conversor.getStringHora(this.getBusquedaModel().getFechaHoraHasta()));
	}

	@Override
	protected ResumenActualCajaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected ResumenActualCajaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminResumenActualCajaView getView() {
		return this.view;
	}
	
	public void setTextoBtnConsultar() {
//		this.getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlPie().getBtnConsultar().setText(MessagesCaja.getString("Application.btnImprimir"));
		this.getView().getPnlPie().getBtnConsultar().setMnemonic(MessagesCaja.getChar("Application.btnImprimirMnemonic"));
		this.getView().getPnlPie().getBtnConsultar().setToolTipText(MessagesCaja.getString("Application.btnImprimirToolTip"));
	}
	
	void buscar() {
		
		final AdminResumenActualCaja controller = this;
		Thread locThread = new Thread(new Runnable(){
			
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TicketCaja> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista); 
//					if (controller.getView().getCbEstado().getSelectedIndex() == -1 && !locLista.isEmpty()) {
						controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
//					}else {
//						controller.getView().getPnlPie().getBtnConsultar().setEnabled(false);
//					}
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
					
					int locTotalTicket = 0;
					final List<TicketCaja> listaTicketCaja = controller.getTableModel().getListaObjetos();
					Double locTotal = new Double(0.00);
					if (listaTicketCaja != null) {
						for(TicketCaja ticketCaja: listaTicketCaja){
							if(ticketCaja.getImporteTotal() != null && ticketCaja.getEstado().equals(Estado.ACTIVO)){
								locTotal += ticketCaja.getImporteTotal();
								locTotalTicket ++;
							}
						}
					}
					
					NumberFormat dispFormat = NumberFormat.getNumberInstance();
					dispFormat.setMaximumFractionDigits(2);
					dispFormat.setMinimumFractionDigits(2);
					dispFormat.setGroupingUsed(true);
					
					controller.getView().getPnlResultadoMontoCobrado().getLblNumero().setText(dispFormat.format(locTotal));
					
					dispFormat.setMaximumFractionDigits(0);
					dispFormat.setMinimumFractionDigits(0);
					dispFormat.setGroupingUsed(false);
					controller.getView().getPnlResultadoCantidadTickets().getLblNumero().setText(dispFormat.format(locTotalTicket));
				}
			}
		});
		locThread.start();
	}
	
	void imprimirResumenes() throws Exception {
//		this.imprimirPlanillaDiaria();
		this.imprimirPlanillaDiariaPorTasa();
//		this.imprimirPlanillaDiariaPorSellado();
	}
	
	
	void imprimirPlanillaDiaria() throws Exception {
		JasperReport masterReport = null;
		try {

			URL urlMaestro = this.getClass().getResource("/reports/Reporte_Planilla_Diaria_Caja.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}
		
		JasperReport subReport = null;
		try {

			URL urlSubReport = this.getClass().getResource("/reports/Reporte_Planilla_Diaria_Caja_Subreporte.jasper");
			
			subReport = (JasperReport) JRLoader.loadObject(urlSubReport);
		} catch (JRException e) {
			System.out.println("Error cargando el subreporte: " + e.getMessage());
		}

		ConexionReportes.getInstance().conectar();
		
		URL urlImagen = this.getClass().getResource("/reports/logo.gif");
		String locLogo = Conversor.getString(urlImagen);
		
		final List<TicketCaja> listaTicketCaja = this.getTableModel().getListaObjetos();
		Double locTotal = new Double(0.00);
		if (listaTicketCaja != null) {
			for(TicketCaja ticketCaja: listaTicketCaja){
				if(ticketCaja.getImporteTotal() != null && ticketCaja.getEstado().equals(Estado.ACTIVO)){
					locTotal += ticketCaja.getImporteTotal();
				}
			}
		}
		
		this.getView().getTfCaja().setText(AppManager.getInstance().getUsuario().getNombrePersonaFisica()
				+ " [" 
				+ AppManager.getInstance().getUsuario().toString() 
				+ "]");
		
		
		System.out.println("ID_USUARIO " + this.getBusquedaModel().getUsuario().getIdUsuario());
		System.out.println("ID_CAJA    " + this.getBusquedaModel().getCaja().getIdCaja());
		System.out.println("---- sin busquedaModel ----");
		System.out.println("ID_USUARIO  " + AppManager.getInstance().getUsuario().getIdUsuario());
		System.out.println("ID_CAJA     " + CajaGUI.getInstance().getCaja().getIdCaja());
		
		
		Map masterParams = new HashMap();
//		masterParams.put("ID_USUARIO", new Integer((int)this.getBusquedaModel().getUsuario().getIdUsuario()));
//		masterParams.put("ID_CAJA", new Integer((int)this.getBusquedaModel().getCaja().getIdCaja()));
		masterParams.put("ID_USUARIO", new Integer((int)AppManager.getInstance().getUsuario().getIdUsuario()));
		masterParams.put("ID_CAJA", new Integer((int)CajaGUI.getInstance().getCaja().getIdCaja()));
		masterParams.put("PAR_IMAGEN", locLogo);
		masterParams.put("PAR_TOTAL", locTotal);
		
		masterParams.put("PAR_SUBREPORTE", subReport);
		
		JasperPrint masterPrint = null;
		
		try {
			masterPrint = JasperFillManager.fillReport(masterReport, masterParams, ConexionReportes.getInstance().getConnection());
			
		} catch (JRException e) {
			System.out.println("Error llenando el reporte maestro: " + e.getMessage());
			try {
				ConexionReportes.getInstance().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
		
		// Obtener la resolucion de pantalla del usuario
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension dimension = toolkit.getScreenSize();

		JDialog locDialog = new JDialog(this.view);
		JRViewer locViewer = new JRViewer(masterPrint);
		locDialog.add(locViewer);
		locDialog.pack();
		locDialog.setModal(true);
		locDialog.setSize(dimension.width, dimension.height);
		locDialog.setTitle("Vista previa de Planilla Diaria de Caja");
		locDialog.setVisible(true);

		ConexionReportes.getInstance().desconectar();		
	}
	
	void imprimirPlanillaDiariaPorTasa() throws Exception {
		JasperReport masterReport = null;
		try {

			URL urlMaestro = this.getClass().getResource("/reports/Reporte_Planilla_Diaria_Caja_Tasa.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}
		
		this.getView().getTfCaja().setText(AppManager.getInstance().getUsuario().getNombrePersonaFisica()
				+ " [" 
				+ AppManager.getInstance().getUsuario().toString() 
				+ "]");
		
		this.actualizarBusquedaModel();
		Date fechaDesde = this.getBusquedaModel().getFechaHoraDesde();
		Date fechaHasta = this.getBusquedaModel().getFechaHoraHasta();
		
		ResumenActualCajaDataSource dataSource = CajaGUI.getInstance().getAdminSystemsCaja()
					.getSystemAdministracionIngresos().generarReporteCajaPorTasa(
							AppManager.getInstance().getUsuario().getIdUsuario(),
							CajaGUI.getInstance().getCaja().getIdCaja(),
							fechaDesde, fechaHasta);
		
		System.out.println("DATA SOURCE");
		System.out.println(dataSource);
		
		Map<String, Object> masterParams = new HashMap<String, Object>();
//		masterParams.put("ID_USUARIO", new Integer((int)AppManager.getInstance().getUsuario().getIdUsuario()));
//		masterParams.put("ID_CAJA", new Integer((int)CajaGUI.getInstance().getCaja().getIdCaja()));

		byte[] logo = CajaGUI.getInstance().getAdminSystemsCaja().getSystemParametro().getLogoMunicipalidad();
		masterParams.put("PAR_IMAGEN", JRImageRenderer.getInstance(logo));
		masterParams.put("IMPORTE_TOTAL", dataSource.getImporteTotal());
		masterParams.put("NRO_CAJA", CajaGUI.getInstance().getCaja().getNumero().toString());
		masterParams.put("CAJERO", AppManager.getInstance().getUsuario().toString());
		masterParams.put("CAJERO_PERSONA", AppManager.getInstance().getUsuario().getNombrePersonaFisica());
		masterParams.put("CANTIDAD_TOTAL_TICKET", dataSource.getCantidadTickets());
		masterParams.put("FECHA_DESDE", Conversor.getString(fechaDesde));
		masterParams.put("FECHA_HASTA", Conversor.getString(fechaHasta));
		masterParams.put("HORA_DESDE", Conversor.getStringHora(fechaDesde));
		masterParams.put("HORA_HASTA", Conversor.getStringHora(fechaHasta));
		masterParams.put("TIPO_PAGOS_DS", dataSource.getTipoPagoDS());
		masterParams.put("CANCELADOS_DS", dataSource.getCanceladosDS());
		masterParams.put("PAR_LISTA_MOV_CAJA_INGRESO", dataSource.getListaMovimientoCajaIngreso());
		JasperPrint masterPrint = null;
		
		try {
			masterPrint = JasperFillManager.fillReport(masterReport, masterParams, dataSource);
			
		} catch (JRException e) {
			System.out.println("Error llenando el reporte maestro: " + e.getMessage());
			e.printStackTrace();
//			try {
//				ConexionReportes.getInstance().getConnection().close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
		}	
		
		// Obtener la resolucion de pantalla del usuario
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension dimension = toolkit.getScreenSize();

		JDialog locDialog = new JDialog(this.view);
		JRViewer locViewer = new JRViewer(masterPrint);
		locDialog.add(locViewer);
		locDialog.pack();
		locDialog.setModal(true);
		locDialog.setSize(dimension.width, dimension.height);
		locDialog.setTitle("Vista previa de Planilla Diaria de Caja - Tasa");
		
		locDialog.setVisible(true);

//		ConexionReportes.getInstance().desconectar();		
	}
	
	void imprimirPlanillaDiariaPorSellado() throws Exception {
		JasperReport masterReport = null;
		try {

			URL urlMaestro = this.getClass().getResource("/reports/Reporte_Planilla_Diaria_Caja_Sellado.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}
		
		JasperReport subReport = null;
		try {

			URL urlSubReport = this.getClass().getResource("/reports/Reporte_Planilla_Diaria_Caja_Sellado_Subreporte.jasper");
			
			subReport = (JasperReport) JRLoader.loadObject(urlSubReport);
		} catch (JRException e) {
			System.out.println("Error cargando el subreporte: " + e.getMessage());
		}

		ConexionReportes.getInstance().conectar();
		
		URL urlImagen = this.getClass().getResource("/reports/logo.gif");
		String locLogo = Conversor.getString(urlImagen);
		
		final List<TicketCaja> listaTicketCaja = this.getTableModel().getListaObjetos();
		Double locTotal = new Double(0.00);
		int locCantidadTickets = 0;
		if (listaTicketCaja != null) {
			for(TicketCaja ticketCaja: listaTicketCaja){
				if(ticketCaja.getImporteTotal() != null && ticketCaja.getEstado().equals(Estado.ACTIVO)){
					locTotal += ticketCaja.getImporteTotal();
					locCantidadTickets++;
				}
			}
		}
		
		this.getView().getTfCaja().setText(AppManager.getInstance().getUsuario().getNombrePersonaFisica()
				+ " [" 
				+ AppManager.getInstance().getUsuario().toString() 
				+ "]");
		
		
		System.out.println("ID_USUARIO " + this.getBusquedaModel().getUsuario().getIdUsuario());
		System.out.println("ID_CAJA    " + this.getBusquedaModel().getCaja().getIdCaja());
		System.out.println("---- sin busquedaModel ----");
		System.out.println("ID_USUARIO  " + AppManager.getInstance().getUsuario().getIdUsuario());
		System.out.println("ID_CAJA     " + CajaGUI.getInstance().getCaja().getIdCaja());
		
		
		Map masterParams = new HashMap();
//		masterParams.put("ID_USUARIO", new Integer((int)this.getBusquedaModel().getUsuario().getIdUsuario()));
//		masterParams.put("ID_CAJA", new Integer((int)this.getBusquedaModel().getCaja().getIdCaja()));
		masterParams.put("ID_USUARIO", new Integer((int)AppManager.getInstance().getUsuario().getIdUsuario()));
		masterParams.put("ID_CAJA", new Integer((int)CajaGUI.getInstance().getCaja().getIdCaja()));
		masterParams.put("PAR_IMAGEN", locLogo);
		
		
		masterParams.put("PAR_SUBREPORTE", subReport);
		
		
		JasperPrint masterPrint = null;
		
		try {
			masterPrint = JasperFillManager.fillReport(masterReport, masterParams, ConexionReportes.getInstance().getConnection());
			
		} catch (JRException e) {
			System.out.println("Error llenando el reporte maestro: " + e.getMessage());
			try {
				ConexionReportes.getInstance().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
		
		// Obtener la resolucion de pantalla del usuario
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension dimension = toolkit.getScreenSize();

		JDialog locDialog = new JDialog(this.view);
		JRViewer locViewer = new JRViewer(masterPrint);
		locDialog.add(locViewer);
		locDialog.pack();
		locDialog.setModal(true);
		locDialog.setSize(dimension.width, dimension.height);
		locDialog.setTitle("Vista previa de Planilla Diaria de Caja - Sellados Administrativos");
		locDialog.setVisible(true);

		ConexionReportes.getInstance().desconectar();		
	}
	
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
		
	}
	
}


class BtnBuscarListener implements ActionListener {
	private AdminResumenActualCaja controller;
	
	public BtnBuscarListener(AdminResumenActualCaja controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
			this.controller.actualizarBusquedaModel();
			this.controller.actualizarBusquedaView();

		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnReiniciarListener implements ActionListener {
	private AdminResumenActualCaja controller;

	public BtnReiniciarListener(AdminResumenActualCaja controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(false);
			this.controller.setBtnReiniciarListener();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnImprimirListener implements ActionListener {
	private AdminResumenActualCaja controller;
	
	public BtnImprimirListener(AdminResumenActualCaja controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (AppManager.getInstance().showConfirmMsg(this.controller.getView(), "Se imprimirán en orden consecutivo los siguientes reportes:\n" + 
					"··> Planilla Diaria de Caja \n" + 
					"··> Planilla Diaria de Caja por Tasa \n" + 
					"··> Planilla Diaria de Caja por Sellado Administrativo")) {
				this.controller.imprimirResumenes();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
	
}


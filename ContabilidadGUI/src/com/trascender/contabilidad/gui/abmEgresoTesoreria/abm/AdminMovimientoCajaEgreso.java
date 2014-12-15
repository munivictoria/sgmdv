package com.trascender.contabilidad.gui.abmEgresoTesoreria.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminMovimientoCajaEgreso extends AdminController<MovimientoCajaEgreso>{
	
	public AdminMovimientoCajaEgresoView view;
	public MovimientoCajaEgresoBusquedaModel busquedaModel = new MovimientoCajaEgresoBusquedaModel();
	public MovimientoCajaEgresoTableModel tableModel = new MovimientoCajaEgresoTableModel();
	
	public AdminMovimientoCajaEgreso(JFrame owner) throws Exception {
		this.view = new AdminMovimientoCajaEgresoView(owner);
		this.init();
	}
	
	public AdminMovimientoCajaEgreso(JDialog owner) throws Exception {
		this.view = new AdminMovimientoCajaEgresoView(owner);
		this.init();
	}

	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setModels(){
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnImprimirMovimientoCajaEgresoListener(this));
	}
	
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfFechaDesde().getText());
		lblNulos.add(this.getView().getLblFechaDesde());
		
		attFecha.add(this.getView().getTfFechaDesde().getText());
		lblFecha.add(this.getView().getLblFechaDesde());
		
		attNulos.add(this.getView().getTfFechaHasta().getText());
		lblNulos.add(this.getView().getLblFechaHasta());
		
		attFecha.add(this.getView().getTfFechaHasta().getText());
		lblFecha.add(this.getView().getLblFechaHasta());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaDesde().getText(), this.getView().getLblFechaDesde()));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaHasta().getText(), this.getView().getLblFechaHasta()));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.getAbmErrores().getView().setListaErrores(listaErrores);
			this.getAbmErrores().open();
		}
		
		return validacionOK;
	}
		
	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionHasta()));
	}
	
	public void actualizarBusquedaModel() {
		MovimientoCajaEgresoBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setFechaEmisionDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaEmisionHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
			
		locModel.fireActualizarDatos();
	}
		
	public AdminMovimientoCajaEgresoView getView() {
		return view;
	}

	void buscar() throws Exception {
		final AdminMovimientoCajaEgreso controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<MovimientoCajaEgreso> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	void imprimir() throws Exception {
		JasperReport masterReport = null;
		try {

			URL urlMaestro = this.getClass().getResource("/reports/Reporte_Egreso_Tesoreria.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}

		JasperReport subReport = null;
		try {

			URL urlSubReport = this.getClass().getResource("/reports/Reporte_Egreso_Tesoreria_Subreporte.jasper");
			
			subReport = (JasperReport) JRLoader.loadObject(urlSubReport);
		} catch (JRException e) {
			System.out.println("Error cargando el subreporte: " + e.getMessage());
		}
		
		ConexionReportes.getInstance().conectar();
		
		URL urlImagen = this.getClass().getResource("/reports/logo.png");
		String locLogo = Conversor.getString(urlImagen);
		
		DateFormat df = DateFormat.getDateInstance();

		Map masterParams = new HashMap();
		masterParams.put("PAR_IMAGEN", locLogo);
		masterParams.put("PAR_FECHA_DESDE", df.parse(this.view.getTfFechaDesde().getText()));
		masterParams.put("PAR_FECHA_HASTA", df.parse(this.view.getTfFechaHasta().getText()));
		masterParams.put("PAR_CROSSTAB", subReport);
		
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
		locDialog.setTitle("Vista previa de Egreso de Tesorería");
		locDialog.setVisible(true);

		ConexionReportes.getInstance().desconectar();			
	}

	public MovimientoCajaEgresoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public MovimientoCajaEgresoTableModel getTableModel() {
		return tableModel;
	}
	
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
}

class BtnBuscarListener implements ActionListener {
	private AdminMovimientoCajaEgreso controller;

	public BtnBuscarListener(AdminMovimientoCajaEgreso controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.validarDatos()) {
				this.controller.buscar();
				this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(true);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnReiniciarListener implements ActionListener {
	private AdminMovimientoCajaEgreso controller;

	public BtnReiniciarListener(AdminMovimientoCajaEgreso controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.setBtnReiniciarListener();
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnImprimirMovimientoCajaEgresoListener implements ActionListener {
	private AdminMovimientoCajaEgreso controller;

	public BtnImprimirMovimientoCajaEgresoListener(
			AdminMovimientoCajaEgreso controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimir();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}



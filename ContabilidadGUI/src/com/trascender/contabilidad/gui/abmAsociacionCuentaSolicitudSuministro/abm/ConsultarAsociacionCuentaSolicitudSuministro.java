package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.FirmaPermisoTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.LineaSolicitudSuministroTableModel;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarAsociacionCuentaSolicitudSuministro extends ABMAsociacionCuentaSolicitudSuministro {

	private ConsultarAsociacionCuentaSolicitudSuministroView view;
	private AsociacionCuentaSolicitudSuministroABMModel abmModel = new AsociacionCuentaSolicitudSuministroABMModel();
	private FirmaPermisoTableModel firmaPermisoTableModel = new FirmaPermisoTableModel();
	private LineaSolicitudSuministroTableModel lineaSolicitudSuministroTableModel = new LineaSolicitudSuministroTableModel();
	
	public ConsultarAsociacionCuentaSolicitudSuministro(JDialog owner) throws Exception {
		this.view = new ConsultarAsociacionCuentaSolicitudSuministroView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.disabledAll();
		this.setVisibleAll();
		this.setListeners();
		this.setTextoBtnAceptar();
	}
	
	private void setTextoBtnAceptar() {
		this.getView().getPnlPie().getBtnAceptar().setText("Firmar");
		this.getView().getPnlPie().getBtnAceptar().setMnemonic(KeyEvent.VK_F);
		this.getView().getPnlPie().getBtnAceptar().setToolTipText("Permite firmar la Solicitud de Suministros seleccionada.");	
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnFirmarListener(this));
	}
	
	private void disabledAll() {
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnAgregar().setEnabled(false);
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnModificar().setEnabled(false);
	}
	
	@Override
	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ConsultarAsociacionCuentaSolicitudSuministroView getView() {
		return this.view;
	}

	private void setVisibleAll() {
		this.getView().getPnlPie().getBtnImprimir().setVisible(true);
		this.getView().getPnlPie().getBtnAceptar().setVisible(true);
	}
	
	@Override
	public boolean validarDatos() {
		return super.validarDatos();
	}
	
	void firmarSolicitudSuministro() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().firmar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	public void imprimirSolicitudSuministro() throws SQLException, ClassNotFoundException {
		JasperReport masterReport = null;
		try {
			URL urlMaestro = this.getClass().getResource("/reports/reporte_Solicitud_Suministro.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}

		ConexionReportes.getInstance().conectar();
		
		URL urlImagen = this.getClass().getResource("/reports/logo.png");
		String locLogo = Conversor.getString(urlImagen);
		
		Map masterParams = new HashMap();
		
		System.out.println("ID_SOLICITUD_SUMINISTRO   "+ new Integer((int)this.getAbmModel().getObjetoABM().getIdSolicitudSuministro()));
		System.out.println("PAR_USUARIO               " + AppManager.getInstance().getUsuario().getNombrePersonaFisica());
		System.out.println("PAR_IMAGEN                " + locLogo);
		masterParams.put("ID_SOLICITUD_SUMINISTRO", new Integer((int)this.getAbmModel().getObjetoABM().getIdSolicitudSuministro()));
		masterParams.put("PAR_USUARIO", AppManager.getInstance().getUsuario().getNombrePersonaFisica());
		masterParams.put("PAR_IMAGEN", locLogo);
		
		JasperPrint masterPrint = null;
		
		try {
			masterPrint = JasperFillManager.fillReport(masterReport, masterParams, ConexionReportes.getInstance().getConnection());
		} catch (JRException e) {
			System.out.println("Error llenando el reporte maestro: " + e.getMessage());
			try {
				ConexionReportes.getInstance().getConnection().close();
			} catch (SQLException e1) {
			}
		}	
		
		// Obtener la resolucion de pantalla del usuario
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension dimension = toolkit.getScreenSize();

		JDialog locDialog = new JDialog(this.getView());
		JRViewer locViewer = new JRViewer(masterPrint);
		locDialog.add(locViewer);
		locDialog.pack();
		locDialog.setModal(true);
		locDialog.setSize(dimension.width, dimension.height);
		locDialog.setTitle("Vista previa de Solicitud de Suministros");
		locDialog.setVisible(true);

		ConexionReportes.getInstance().desconectar();			
	}

	@Override
	public FirmaPermisoTableModel getFirmaPermisoTableModel() {
		return this.firmaPermisoTableModel;
	}

	@Override
	public LineaSolicitudSuministroTableModel getLineaSolicitudSuministroTableModel() {
		return this.lineaSolicitudSuministroTableModel;
	}
}

class BtnFirmarListener implements ActionListener {
	
	private ConsultarAsociacionCuentaSolicitudSuministro controller;
	
	public BtnFirmarListener(ConsultarAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.firmarSolicitudSuministro();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}

class BtnImprimirListener implements ActionListener {
	
	private ConsultarAsociacionCuentaSolicitudSuministro controller;
	
	public BtnImprimirListener(ConsultarAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirSolicitudSuministro();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}



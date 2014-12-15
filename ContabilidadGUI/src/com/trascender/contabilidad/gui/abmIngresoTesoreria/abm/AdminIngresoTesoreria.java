package com.trascender.contabilidad.gui.abmIngresoTesoreria.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class AdminIngresoTesoreria {
	AdminIngresoTesoreriaView view;
	
	public AdminIngresoTesoreria() {
		this.view = new AdminIngresoTesoreriaView();
		this.setListener();
		this.view.setVisible(true);
	}

	private void setListener() {
		this.view.getPnlPie().getBtnCancelar().addActionListener(new BtnSalirListener(this));
		this.view.getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}

	public void salir() {
		this.view.dispose();
	}
	
	public void imprimirIngresoTesoreria() throws Exception {
		JasperReport masterReport = null;
		try {

			URL urlMaestro = this.getClass().getResource("/reports/Reporte_Ingreso_Tesoreria.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}

		JasperReport subReport = null;
		try {

			URL urlSubReport = this.getClass().getResource("/reports/Reporte_Ingreso_Tesoreria_Crosstab.jasper");
			
			subReport = (JasperReport) JRLoader.loadObject(urlSubReport);
		} catch (JRException e) {
			System.out.println("Error cargando el subreporte: " + e.getMessage());
		}
		
		ConexionReportes.getInstance().conectar();
		
		URL urlImagen = this.getClass().getResource("/reports/logo.png");
		String locLogo = Conversor.getString(urlImagen);
		
		Map masterParams = new HashMap();
		masterParams.put("PAR_IMAGEN", locLogo);
	//	masterParams.put("PAR_FECHA_DESDE", DateFormat.parse(this.view.getTfFechaDesde().getText()));
		masterParams.put("PAR_FECHA_HASTA", new Date(this.view.getTfFechaHasta().getText()));
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
		locDialog.setTitle("Vista previa de Ingreso de Tesorería");
		locDialog.setVisible(true);

		ConexionReportes.getInstance().desconectar();			
	}
	
	public AdminIngresoTesoreriaView getView() {
		return this.view;
	}
}

class BtnSalirListener implements ActionListener {

	private AdminIngresoTesoreria controller;
	
	public BtnSalirListener(AdminIngresoTesoreria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
			this.controller.salir();
	}
}

class BtnImprimirListener implements ActionListener {
	
	private AdminIngresoTesoreria controller;
	
	public BtnImprimirListener(AdminIngresoTesoreria controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirIngresoTesoreria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}

}


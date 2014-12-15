package com.trascender.contabilidad.gui.abmLibroDiario.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.trascender.contabilidad.gui.abmLibroDiario.LibroDiarioABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarLibroDiario extends ABMLibroDiario {

	private ConsultarLibroDiarioView view;
	private LibroDiarioABMModel abmModel = new LibroDiarioABMModel();
	
	public ConsultarLibroDiario(JDialog owner) {
		this.view = new ConsultarLibroDiarioView(owner);
		this.init();
	}
	@Override
	protected void init() {
		super.init();
		this.disableAll();
		this.setListeners();
	}
	
	private void disableAll() {
		this.getView().getTfCodigoAutorizacion().setEditable(false);
		this.getView().getTfNumero().setEditable(false);
		this.getView().getTfCantidadFolios().setEditable(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnImprimirListener(this));
	}

	@Override
	public LibroDiarioABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLibroDiarioView getView() {
		return this.view;
	}
	
	void imprimirLibroDiario() throws Exception {
		LibroDiario locLibroDiario = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteLibroDiario(locLibroDiario.getIdLibroDiario());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista previa de Libro Diario");
	}

}

class BtnImprimirListener implements ActionListener {

	private ConsultarLibroDiario controller;
	
	public BtnImprimirListener(ConsultarLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.contabilidad.gui.abmLibroBanco.LibroBancoABMModel;
import com.trascender.contabilidad.gui.abmLibroBanco.LineasLibroBancoTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarLibroBanco extends ABMLibroBanco {

	public ConsultarLibroBancoView view;
	private LibroBancoABMModel abmModel = new LibroBancoABMModel();
	private LineasLibroBancoTableModel abmTableModel = new LineasLibroBancoTableModel();
	
	
	public ConsultarLibroBanco(JDialog owner) throws Exception {
		this.view = new ConsultarLibroBancoView(owner);
		this.init();
	}
	
	public ConsultarLibroBanco(JFrame owner) throws Exception {
		this.view = new ConsultarLibroBancoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.enableBotonesTabla(false);
		this.getView().getLblFechaGeneracion().setVisible(false);
		this.getView().getTfFechaGeneracion().setVisible(false);
	}
	
	private void disabledAll() {
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfCuentaBancaria().setEditable(false);
		this.getView().getBtnGenerarLibroBanco().setEnabled(false);
		this.getView().getPnlBotonesSeleccion().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnImprimirListener(this));
	}
	
	@Override
	public LibroBancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineasLibroBancoTableModel getAbmTableModel() {
		return this.abmTableModel;
	}

	@Override
	public ABMLibroBancoView getView() {
		return this.view;
	}

	void imprimirLibroBanco() throws Exception {
		LibroBanco locLibroBanco = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteLibroBanco(locLibroBanco.getIdLibroBanco());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista previa de Libro Banco");
	}
}

class BtnImprimirListener implements ActionListener {

	private ConsultarLibroBanco controller;
	
	public BtnImprimirListener(ConsultarLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}


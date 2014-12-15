package com.trascender.contabilidad.gui.abmMayor.abm;

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

import com.trascender.contabilidad.gui.abmLineaMayor.LineaMayorTableModel;
import com.trascender.contabilidad.gui.abmMayor.MayorABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarMayor extends ABMMayor {

	private ConsultarMayorView view;
	private MayorABMModel abmModel = new MayorABMModel();
	private LineaMayorTableModel tableModel = new LineaMayorTableModel();
	
	public ConsultarMayor(JDialog owner) throws Exception {
		this.view = new ConsultarMayorView(owner);
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
	}
	
	private void visibleAll() {
		this.getView().getBtnGenerarMayor().setVisible(false);
		this.getView().getPnlBotonesSeleccionCuenta().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(true);
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
	}

	private void disabledAll() {
		this.getView().getTfMes().setEditable(false);
		this.getView().getTfAnio().setEditable(false);
		this.getView().getTfCuenta().setEditable(false);
		this.getView().getCbTipo().setEnabled(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	@Override
	public MayorABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public ConsultarMayorView getView() {
		return this.view;
	}
	
	void imprimirMayor() throws Exception {
		Mayor locMayor = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteMayor(locMayor.getIdMayor());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista previa de Mayor");
	} 
	
	@Override
	public LineaMayorTableModel getTableModel() {
		return this.tableModel;
	}
}
	
class BtnImprimirListener implements ActionListener {
	
	private ConsultarMayor controller;
	
	public BtnImprimirListener(ConsultarMayor controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}

}
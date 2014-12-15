package com.trascender.contabilidad.gui.abmCheque.abm;

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

import com.trascender.contabilidad.gui.abmCheque.ChequeABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.framework.util.ObtenerNumeroEnLetras;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarCheque extends ABMCheque{
	
	private ConsultarChequeView view;
	private ChequeABMModel abmModel = new ChequeABMModel();
	
	public ConsultarCheque(JDialog owner) {
		this.view = new ConsultarChequeView(owner);
		this.init();
	}
	
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
	}
	
	private void visibleAll() {
		this.getView().getPnlPie().getBtnImprimir().setVisible(true);
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
	}

	private void disabledAll() {
		this.getView().getTfNumeroCheque().setEditable(false);
		this.getView().getTfFechaEmision().setEditable(false);
		this.getView().getTfFechaPago().setEditable(false);
		this.getView().getTfImporte().setEditable(false);
		this.getView().getChkPostdatado().setEnabled(false);
		this.getView().getTfCuentaBancaria().setEditable(false);
		
		this.getView().getPnlBotonesSeleccion().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	@Override
	public ChequeABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ConsultarChequeView getView() {
		return this.view;
	} 

	public void imprimirCheque() throws Exception {
		Cheque locCheque = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteCheque(locCheque.getIdMovimientoBancario());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista previa de los datos del Cheque");
	}
}

class BtnImprimirListener implements ActionListener {
	
	private ConsultarCheque controller;
	
	public BtnImprimirListener(ConsultarCheque controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}




package com.trascender.contabilidad.gui.abmBalance.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.trascender.contabilidad.gui.abmBalance.BalanceABMModel;
import com.trascender.contabilidad.gui.abmBalance.CuentaHistoricaBalanceTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.CuentaHistoricaBalance;
import com.trascender.contabilidad.reporte.dataSource.BalanceContableDS;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarBalance extends ABMBalance {

	private ConsultarBalanceView view;
	private BalanceABMModel abmModel = new BalanceABMModel();
	private CuentaHistoricaBalanceTableModel tableModel = new CuentaHistoricaBalanceTableModel();

	public ConsultarBalance(JDialog owner, Balance pBalance) throws Exception {
		this.view = new ConsultarBalanceView(owner);
		this.abmModel.setObjetoABM(pBalance);
		this.init();
	}
	
	public ConsultarBalance(JFrame owner, Balance pBalance) throws Exception {
		this.view = new ConsultarBalanceView(owner);
		this.abmModel.setObjetoABM(pBalance);
		this.init();
	}
	
	@Override
	public BalanceABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setPropiedadesComponentes();
		this.setListeners();
		this.setVisibleAll();
	}
	
	private void setVisibleAll() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(true);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setPropiedadesComponentes() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(true);
		this.getView().getPnlBotonesSeleccionTipoBalance().setVisible(false);
		this.getView().getBtnGenerarBalance().setVisible(false);
		
		this.getView().getTfNombre().setEditable(false);
		this.getView().getFtfFecha().setEditable(false);
		this.getView().getPnlTabla().getTblDatos().setEnabled(false);
	}
	
	public void instanciarTableModel() {
		this.getView().setTableModel(this.tableModel);
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getFtfFecha().setValue(Conversor.getString(this.getAbmModel().getFecha()));
		this.getView().getTfTipoBalance().setText(Conversor.getVacioSiNull(this.getAbmModel().getTipoBalance()));
		
		if (this.getAbmModel().getListaCuentaHistoricoBalance() != null) {
			this.getTableModel().setListaObjetos(new ArrayList<CuentaHistoricaBalance>(this.getAbmModel().getListaCuentaHistoricoBalance()));
		}
	}
	
	@Override
	public CuentaHistoricaBalanceTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public ABMBalanceView getView() {
		return this.view;
	}

	public void imprimirBalance() throws Exception {
		Balance locBalance = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteBalanceContable(locBalance.getIdBalance());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista prévia reporte Balance");
	}
}

class BtnImprimirListener implements ActionListener {
	
	private ConsultarBalance controller;
	
	public BtnImprimirListener(ConsultarBalance controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}



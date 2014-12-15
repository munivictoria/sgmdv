package com.trascender.contabilidad.gui.abmBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmBalance.BalanceABMModel;
import com.trascender.contabilidad.gui.abmBalance.CuentaHistoricaBalanceTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.AdminTipoBalance;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.CuentaHistoricaBalance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMBalance extends ABMController<Balance> {

	public abstract ABMBalanceView getView();
	public abstract BalanceABMModel getAbmModel();
	public abstract CuentaHistoricaBalanceTableModel getTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionTipoBalance().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoBalanceListener(this));
		this.getView().getPnlBotonesSeleccionTipoBalance().getBtnLimpiar().addActionListener(new BtnLimpiarTipoBalanceListener(this));

		this.getView().getBtnGenerarBalance().addActionListener(new BtnGenerarBalanceListener(this));
	}
	
	@Override
	protected void actualizarABMModel() {
		BalanceABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setFecha(Conversor.getDate(this.getView().getFtfFecha().getText()));
		
		locModel.fireActualizarDatos();
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
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getFtfFecha().getText());
		lblNulos.add(this.getView().getLblFecha());
		
		attNulos.add(this.getView().getTfTipoBalance().getText());
		lblNulos.add(this.getView().getLblTipoBalance());
		
		attFechas.add(this.getView().getFtfFecha().getText());
		lblFechas.add(this.getView().getLblFecha());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	public boolean validarDatosGenerarBalance() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfTipoBalance().getText());
		lblNulos.add(this.getView().getLblTipoBalance());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	void seleccionarTipoBalance() throws Exception {
		this.getAbmModel().getListaCuentaHistoricoBalance().clear();
		this.getTableModel().clearTable();
		AdminTipoBalance adminTipoBalance = new AdminTipoBalance(this.getView());
		TipoBalance locTipoBalance = adminTipoBalance.openSelect();
		
		if (locTipoBalance != null) {
			this.getAbmModel().setTipoBalance(locTipoBalance);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarTipoBalance() throws Exception {
		this.getAbmModel().setTipoBalance(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void generarBalance() throws Exception {
		if (this.validarDatosGenerarBalance()) {
			this.getTableModel().clearTable();
			this.actualizarABMModel();
			this.getAbmModel().generarBalance();
			this.getTableModel().addRows(this.getAbmModel().getObjetoABM().getListaCuentaHistoricoBalance());
			
		}
	}
	
}


class BtnSeleccionarTipoBalanceListener implements ActionListener {
	private ABMBalance controller;
	public BtnSeleccionarTipoBalanceListener(ABMBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnLimpiarTipoBalanceListener implements ActionListener {
	private ABMBalance controller;
	public BtnLimpiarTipoBalanceListener(ABMBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnGenerarBalanceListener implements ActionListener {
	private ABMBalance controller;
	public BtnGenerarBalanceListener(ABMBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.generarBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


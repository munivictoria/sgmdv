package com.trascender.contabilidad.gui.abmBoletaDeposito.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmBoletaDeposito.BoletaDepositoABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmCuentaBancaria.AdminCuentaBancaria;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMBoletaDeposito extends ABMController<BoletaDeposito>{

	public abstract ABMBoletaDepositoView getView();
	public abstract BoletaDepositoABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(getAbmModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBtnSeleccionCuentaBancaria().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaBancariaListener(this));
		this.getView().getPnlBtnSeleccionCuentaBancaria().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaBancariaListener(this));
		
		this.getView().getPnlBtnSeleccionCuentaAfectada().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaAfectadaListener(this));
		this.getView().getPnlBtnSeleccionCuentaAfectada().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaAfectadaListener(this));
	}
	
	
	@Override
	protected void actualizarABMModel() {
		BoletaDepositoABMModel locModel = this.getAbmModel();
		
		locModel.setNumeroBoleta(Conversor.getNullSiVacio(this.getView().getTfNumeroBoleta().getText()));
		locModel.setImporte(Conversor.getDouble(this.getView().getTfImporte().getValue().toString()));	
		locModel.setFecha(Conversor.getDate(this.getView().getTfFecha().getText()));
		locModel.setObservaciones(Conversor.getNullSiVacio(this.getView().getTaObservaciones().getText()));
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNumeroBoleta().setText(Conversor.getVacioSiNull(this.getAbmModel().getNumeroBoleta()));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
		this.getView().getTfFecha().setText(Conversor.getString(this.getAbmModel().getFecha()));
		this.getView().getTfCuentaBancaria().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaBancaria()));
		this.getView().getTfCuentaAfectada().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaAfectada()));
		this.getView().getTaObservaciones().setText(Conversor.getVacioSiNull(this.getAbmModel().getObservaciones()));
	
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		List<Object> attDecimal = new ArrayList<Object>();
		List<JLabel> lblDecimal = new ArrayList<JLabel>();
		
		List<String> attPositivo = new ArrayList<String>();
		List<JLabel> lblPositivo = new ArrayList<JLabel>();
		
		attEnteros.add(this.getView().getTfNumeroBoleta().getText());
		lblEnteros.add(this.getView().getLblNumeroBoleta());
		
		attNulos.add(this.getView().getTfNumeroBoleta().getText());
		lblNulos.add(this.getView().getLblNumeroBoleta());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		attDecimal.add(this.getView().getTfImporte().getValue());
		lblDecimal.add(this.getView().getLblImporte());
		attPositivo.add(this.getView().getTfImporte().getValue().toString());  
		lblPositivo.add(this.getView().getLblImporte());
		
		attNulos.add(this.getView().getTfFecha().getText());
		lblNulos.add(this.getView().getLblFecha());
		attFecha.add(this.getView().getTfFecha().getText());
		lblFecha.add(this.getView().getLblFecha());
		
		attNulos.add(this.getView().getTfCuentaBancaria().getText());
		lblNulos.add(this.getView().getLblCuentaBancaria());
		
		attNulos.add(this.getView().getTfCuentaAfectada().getText());
		lblNulos.add(this.getView().getLblCuentaAfectada());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
			listaErrores.addAll(Validador.validarDecimales(attDecimal, lblDecimal));
			listaErrores.addAll(Validador.validarPositivos(attPositivo, lblPositivo));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFecha().getText(), this.getView().getLblFecha()));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	

	void seleccionarCuentaBancaria() throws Exception {
		AdminCuentaBancaria adminCuentaBancaria = new AdminCuentaBancaria(this.getView());
		CuentaBancaria locCuentaBancaria = adminCuentaBancaria.openSelect();
		
		if (locCuentaBancaria != null) {
			CuentaBancaria cuentaBancaria = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());
			this.getAbmModel().setCuentaBancaria(cuentaBancaria);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuentaBancaria() {
		this.getAbmModel().setCuentaBancaria(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void seleccionarCuentaAfectada() throws Exception {
		AdminCuenta adminCuentaAfectada = new AdminCuenta(this.getView());
		Cuenta locCuenta = adminCuentaAfectada.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			this.actualizarABMModel();
			this.getAbmModel().setCuentaAfectada(cuenta);
			this.actualizarView();
		}
	}
	
	void limpiarCuentaAfectada() {
		this.getAbmModel().setCuentaAfectada(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
}

class BtnSeleccionarCuentaBancariaListener implements ActionListener {

	private ABMBoletaDeposito controller;
	
	public BtnSeleccionarCuentaBancariaListener(ABMBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaBancariaListener implements ActionListener {

	private ABMBoletaDeposito controller;
	
	public BtnLimpiarCuentaBancariaListener(ABMBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnSeleccionarCuentaAfectadaListener implements ActionListener {

	private ABMBoletaDeposito controller;
	
	public BtnSeleccionarCuentaAfectadaListener(ABMBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaAfectada();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaAfectadaListener implements ActionListener {

	private ABMBoletaDeposito controller;
	
	public BtnLimpiarCuentaAfectadaListener(ABMBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaAfectada();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

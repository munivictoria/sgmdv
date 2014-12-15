package com.trascender.contabilidad.gui.abmCuentaBancaria.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmBanco.AdminBanco;
import com.trascender.contabilidad.gui.abmCuentaBancaria.CuentaBancariaAbmModel;
import com.trascender.contabilidad.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.contabilidad.gui.abmPersonaJuridica.AdminPersonaJuridica;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMCuentaBancaria extends ABMController<CuentaBancaria> {

	public abstract ABMCuentaBancariaView getView();
	public abstract CuentaBancariaAbmModel getAbmModel();
	
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionBanco().getBtnSeleccionar().addActionListener(new BtnSeleccionarBancoListener(this));
		this.getView().getPnlBotonesSeleccionBanco().getBtnLimpiar().addActionListener(new BtnLimpiarBancoListener(this));
		
		this.getView().getPnlBotonesSeleccionPersona().getBtnSeleccionarPersonaFisica().addActionListener(new BtnAgregarPersonaFisicaListener(this));
		this.getView().getPnlBotonesSeleccionPersona().getBtnSeleccionarPersonaJuridica().addActionListener(new BtnAgregarPersonaJuridicaListener(this));
		this.getView().getPnlBotonesSeleccionPersona().getBtnLimpiar().addActionListener(new BtnLimpiarPersonaListener(this));
	}

	@Override
	protected void actualizarABMModel() {
		CuentaBancariaAbmModel locModel = this.getAbmModel();
		
		locModel.setTipoCuenta(Conversor.getNullSiVacio(this.getView().getTfTipoCuenta().getText()));
		locModel.setNumero(Conversor.getNullSiVacio(this.getView().getTfNumero().getText()));
		locModel.setPropia(this.getView().getChkPropia().isSelected());
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfTipoCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getTipoCuenta()));
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(this.getAbmModel().getNumero()));
		this.getView().getChkPropia().setSelected(this.getAbmModel().isPropia());
		this.getView().getTfBanco().setText(Conversor.getVacioSiNull(this.getAbmModel().getBanco()));
		this.getView().getTfTitularCuentaBancaria().setText(Conversor.getVacioSiNull(this.getAbmModel().getTitularCuentaBancaria()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfTipoCuenta().getText());
		lblNulos.add(this.getView().getLblTipoCuenta());
		
		attNulos.add(this.getView().getTfNumero().getText());
		lblNulos.add(this.getView().getLblNumero());
		
		attNulos.add(this.getView().getTfBanco().getText());
		lblNulos.add(this.getView().getLblBanco());
		
		attNulos.add(this.getView().getTfTitularCuentaBancaria().getText());
		lblNulos.add(this.getView().getLblTitularCuentaBancaria());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
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
	
	void seleccionarBanco() throws Exception { 
		AdminBanco adminBanco = new AdminBanco(this.getView());
		Banco locBanco = adminBanco.openSelect();
		
		if (locBanco != null) {
			Banco banco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getBancoByID(locBanco.getIdBanco());
			this.getAbmModel().setBanco(banco);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarBanco() {
		this.getAbmModel().setBanco(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void seleccionarPersonaFisica() throws Exception { 
		AdminPersonaFisica adminPersonaFisica = new AdminPersonaFisica(this.getView());
		PersonaFisica locPersonaFisica= adminPersonaFisica.openSelect();
		
		if (locPersonaFisica != null) {
			PersonaFisica personaFisica= AppManager.getInstance().getAdminSystems().getSystemPersonaFisica().getPersonaFisicaPorId(locPersonaFisica.getIdPersona());
			this.getAbmModel().setTitularCuentaBancaria(personaFisica);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void seleccionarPersonaJuridica() throws Exception { 
		AdminPersonaJuridica adminPersonaJuridica = new AdminPersonaJuridica(this.getView());
		PersonaJuridica locPersonaJuridica= adminPersonaJuridica.openSelect();
		
		if (locPersonaJuridica != null) {
			PersonaJuridica personaJuridica = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemPersonaJuridica().getPersonaJuridicaPorId(locPersonaJuridica.getIdPersonaJuridica());
			this.getAbmModel().setTitularCuentaBancaria(personaJuridica);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarPersona() {
		this.getAbmModel().setTitularCuentaBancaria(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
}

class BtnSeleccionarBancoListener implements ActionListener {

	private ABMCuentaBancaria controller;
	
	public BtnSeleccionarBancoListener(ABMCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarBancoListener implements ActionListener {

	private ABMCuentaBancaria controller;
	
	public BtnLimpiarBancoListener(ABMCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarPersonaFisicaListener implements ActionListener {

	private ABMCuentaBancaria controller;
	
	public BtnAgregarPersonaFisicaListener(ABMCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPersonaFisica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarPersonaJuridicaListener implements ActionListener {

	private ABMCuentaBancaria controller;
	
	public BtnAgregarPersonaJuridicaListener(ABMCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPersonaJuridica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarPersonaListener implements ActionListener {

	private ABMCuentaBancaria controller;
	
	public BtnLimpiarPersonaListener(ABMCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarPersona();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
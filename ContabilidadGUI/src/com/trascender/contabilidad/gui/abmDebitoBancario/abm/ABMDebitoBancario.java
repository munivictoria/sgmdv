package com.trascender.contabilidad.gui.abmDebitoBancario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuentaBancaria.AdminCuentaBancaria;
import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioABMModel;
import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMDebitoBancario extends ABMController<Debito>{

	public abstract ABMDebitoBancarioView getView();
	public abstract DebitoBancarioABMModel getAbmModel();
	public abstract DebitoBancarioTableModel getDebitoBancarioTableModel();
	
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
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaBancariaListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaBancariaListener(this));
	}

	@Override
	protected void actualizarABMModel() {
		DebitoBancarioABMModel locModel = this.getAbmModel();
		
		locModel.setFecha(Conversor.getDate(this.getView().getTfFecha().getText()));
		locModel.setImporte(Conversor.getDouble(this.getView().getTfImporte().getValue().toString()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfFecha().setValue(Conversor.getString(this.getAbmModel().getFecha()));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
		//this.getView().getTfImporte().setValue(Conversor.getVacioSiNull(this.getAbmModel().getImporte()));
		this.getView().getTfCuentaBancaria().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaBancaria()));
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		List<Object> attDecimales = new ArrayList<Object>();
		List<JLabel> lblDecimales = new ArrayList<JLabel>();
		
		List<String> attPositivo = new ArrayList<String>();
		List<JLabel> lblPositivo = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfFecha().getText());
		lblNulos.add(this.getView().getLblFecha());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		
		attNulos.add(this.getView().getTfCuentaBancaria().getText());
		lblNulos.add(this.getView().getLblCuentaBancaria());
		
		attFecha.add(this.getView().getTfFecha().getText());
		lblFecha.add(this.getView().getLblFecha());
		
		attDecimales.add(this.getView().getTfImporte().getValue());
		lblDecimales.add(this.getView().getLblImporte());
		
		attPositivo.add(this.getView().getTfImporte().getText());
		lblPositivo.add(this.getView().getLblImporte());	
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
			listaErrores.addAll(Validador.validarDecimales(attDecimales, lblDecimales));
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
	
	public void seleccionarCuentaBancaria() throws Exception { 
		AdminCuentaBancaria adminCuentaBancaria = new AdminCuentaBancaria(this.getView());
		CuentaBancaria locCuentaBancaria= adminCuentaBancaria.openSelect();
		
		if (locCuentaBancaria != null) {
			CuentaBancaria cuentaBancaria = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());
			this.getAbmModel().setCuentaBancaria(cuentaBancaria);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	public void limpiarCuentaBancaria() {
		this.getAbmModel().setCuentaBancaria(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
}

class BtnSeleccionarCuentaBancariaListener implements ActionListener {

	private ABMDebitoBancario controller;
	
	public BtnSeleccionarCuentaBancariaListener(ABMDebitoBancario controller) {
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
	private ABMDebitoBancario controller;
	
	public BtnLimpiarCuentaBancariaListener(ABMDebitoBancario controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaBancaria();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
